package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.SecurityUtil;

public class MemberJoinOkCommand implements MemberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String realPath = request.getServletContext().getRealPath("/images/member");
		int maxSize = 1024 * 1024 * 5;
		String encoding = "UTF-8";
		
		MultipartRequest multipartRequest = new MultipartRequest(request, realPath, maxSize, encoding, new DefaultFileRenamePolicy());
		
		// String originalFileName = multipartRequest.getOriginalFileName("fName");
		String photo = multipartRequest.getFilesystemName("fName");
		if(photo == null) photo = "noimage.jpg";
		
		String mid = multipartRequest.getParameter("mid")==null ? "" : multipartRequest.getParameter("mid");
		String pwd = multipartRequest.getParameter("pwd")==null ? "" : multipartRequest.getParameter("pwd");
		String nickName = multipartRequest.getParameter("nickName")==null ? "" : multipartRequest.getParameter("nickName");
		String name = multipartRequest.getParameter("name")==null ? "" : multipartRequest.getParameter("name");
		String gender = multipartRequest.getParameter("gender")==null ? "" : multipartRequest.getParameter("gender");
		String birthday = multipartRequest.getParameter("birthday")==null ? "" : multipartRequest.getParameter("birthday");
		String tel = multipartRequest.getParameter("tel")==null ? "" : multipartRequest.getParameter("tel");
		String address = multipartRequest.getParameter("address")==null ? "" : multipartRequest.getParameter("address");
		String email = multipartRequest.getParameter("email")==null ? "" : multipartRequest.getParameter("email");
		String homePage = multipartRequest.getParameter("homePage")==null ? "" : multipartRequest.getParameter("homePage");
		String job = multipartRequest.getParameter("job")==null ? "" : multipartRequest.getParameter("job");
		String content = multipartRequest.getParameter("content")==null ? "" : multipartRequest.getParameter("content");
		String userInfor = multipartRequest.getParameter("userInfor")==null ? "" : multipartRequest.getParameter("userInfor");
		
		// 취미 전송에 대한 처리(여러개가 올수 있기에 배열로 처리)
		String[] hobbys = multipartRequest.getParameterValues("hobby");
		String hobby = "";
		if(hobbys.length != 0) {
			for(String h : hobbys) {
				hobby += h + "/";
			}
		}
		hobby = hobby.substring(0,hobby.lastIndexOf("/"));
		
		// Back End 체크.....(DB에 저장된 자료들 중에서 Null값과 길이에 대한 체크.... 중복체크(아이디/닉네임)...
		
		// 아이디/닉네임 중복체크
		MemberDAO dao = new MemberDAO();
		
		MemberVO vo = dao.getMemberMidCheck(mid);
		if(vo.getMid() != null) {
			request.setAttribute("msg", "이미 사용중인 아이디 입니다.");
			request.setAttribute("url", "memberJoin.mem");
			return;
		}
		
		vo = dao.getMemberNickCheck(nickName);
		if(vo.getNickName() != null) {
			request.setAttribute("msg", "이미 사용중인 닉네임 입니다.");
			request.setAttribute("url", "memberJoin.mem");
			return;
		}
		
		// 비밀번호 암호화처리(sha256)
		SecurityUtil security = new SecurityUtil();
		pwd = security.encryptSHA256(pwd);
		
		// 체크가 모드 끝난 자료들을 VO에 담아서 DB에 저장시켜준다.
		vo = new MemberVO();
		vo.setMid(mid);
		vo.setPwd(pwd);
		vo.setNickName(nickName);
		vo.setName(name);
		vo.setGender(gender);
		vo.setBirthday(birthday);
		vo.setTel(tel);
		vo.setAddress(address);
		vo.setEmail(email);
		vo.setHomePage(homePage);
		vo.setJob(job);
		vo.setHobby(hobby);
		vo.setPhoto(photo);
		vo.setContent(content);
		vo.setUserInfor(userInfor);
		
		// System.out.println("vo : " + vo);
		
		int res = dao.setMemberJoinOk(vo);
		
		if(res == 1) {
			request.setAttribute("msg", "회원에 가입되셨습니다.\\n다시 로그인해 주세요.");
			request.setAttribute("url", "memberLogin.mem");
		}
		else {
			request.setAttribute("msg", "회원가입 실패~~");
			request.setAttribute("url", "memberJoin.mem");
		}
	}

}
