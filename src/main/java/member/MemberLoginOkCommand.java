package member;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.SecurityUtil;

public class MemberLoginOkCommand implements MemberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
		String pwd = request.getParameter("pwd")==null ? "" : request.getParameter("pwd");
		
		MemberDAO dao = new MemberDAO();
		
		MemberVO vo = dao.getMemberMidCheck(mid);
		
		//if(!vo.getMid().equals(mid)) {
		if(vo.getMid() == null || vo.getUserDel().equals("OK") || !vo.getMid().equals(mid)) {
			request.setAttribute("msg", "아이디를 확인하세요");
			request.setAttribute("url", "memberLogin.mem");
			return;
		}
		
		SecurityUtil security = new SecurityUtil();
		pwd = security.encryptSHA256(pwd);
		
		if(!vo.getPwd().equals(pwd)) {
			request.setAttribute("msg", "비밀번호를 확인하세요");
			request.setAttribute("url", "memberLogin.mem");
			return;
		}
		
		// 로그인 성공시 처리할 내용들.....(1.세션저장, 2.쿠키저장, 3.방문횟수(총방문횟수,오늘방문횟수), 4.포인트저장....
		
		// 방문포인트 처리하기?(최종접속일/방문카운트도 함께 업데이트 처리)
		// 하루의 방문포인트는 매번 10포인트씩 주기로 한다. 단, 최대 50포이터까지만 인정한다.
		
		// 날짜 비교
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strToday = sdf.format(today);
		// System.out.println("strToday : " + strToday);
		
		if(strToday.equals(vo.getLastDate().substring(0,10))) {
			// 오늘 다시 방문한 경우
			vo.setTodayCnt(vo.getTodayCnt()+1);
			if(vo.getTodayCnt() <= 5) vo.setPoint(vo.getPoint()+10);
		}
		else {
			// 오늘 처음 방문한 경우는 방문카운트를 1로 초기화하고 10포인트 증정
			vo.setTodayCnt(1);
			vo.setPoint(vo.getPoint()+10);
		}
		
		// 자동 등업처리(정회원)
		// 가입후 10일경과후 5회 이상 접속시 자동 정회원 등업처리(단, 1일 1회로 한정... 여기선 고려하지 않음)
		
		try {
			Date today2 = new Date();
			// 날짜비교를 getTime()로 처리할 경우는 날짜형식을 아래와 같이(yyyy-MM-dd) 맞춰주지 않아도 됨...
//			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
//			String strToday2 = sdf2.format(today2);
//			Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(strToday2);
			Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(vo.getStartDate());
			
			//long diffDate = (date1.getDate() - date2.getDate());	// 날짜차이가 일수로 계산...
			long diffDate = (today2.getTime() - date2.getTime()) / (60*60*24*1000);	// 날짜차이가 일수로 계산...
			// System.out.println("가입후 지난 날짜 : " + diffDate);
			if(vo.getLevel()==1 && diffDate <= 10 && vo.getVisitCnt() >= 5) vo.setLevel(2); 
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(vo.getLevel()==1 && vo.getVisitCnt()+1 >= 5) vo.setLevel(2);
		
		// 3.DB작업(변경된 내용들을 DB에 저장(갱신))
		dao.setLoginUpdate(vo);
		
		// 1.세션처리
		String strLevel = "";
		if(vo.getLevel() == 0) strLevel = "관리자";
		else if(vo.getLevel() == 1) strLevel = "준회원";
		else if(vo.getLevel() == 2) strLevel = "정회원";
		else if(vo.getLevel() == 3) strLevel = "우수회원";
		
		HttpSession session = request.getSession();
		session.setAttribute("sMid", mid);
		session.setAttribute("sNickName", vo.getNickName());
		session.setAttribute("sLevel", vo.getLevel());
		session.setAttribute("strLevel", strLevel);
//		session.setAttribute("sPoint", vo.getPoint());
//		session.setAttribute("sLastDate", vo.getLastDate());
//		session.setAttribute("sTodayCnt", vo.getTodayCnt());
		
		
		// 2.쿠키저장
		String idSave = request.getParameter("idSave")==null ? "off" : "on";
		Cookie cookieMid = new Cookie("cMid", mid);
		cookieMid.setPath("/");
		if(idSave.equals("on")) {
			cookieMid.setMaxAge(60*60*24*7);
		}
		else {
			cookieMid.setMaxAge(0);
		}
		response.addCookie(cookieMid);
		
		// 처리 완료후 메세지 출력후 회원 메인창으로 전송한다.
		request.setAttribute("msg", mid+"님 로그인 되었습니다.");
		request.setAttribute("url", "memberMain.mem");
	}

}
