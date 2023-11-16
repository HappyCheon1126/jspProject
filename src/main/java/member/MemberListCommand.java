package member;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.AdminInterface;
import guest.GuestVO;

public class MemberListCommand implements AdminInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int level = request.getParameter("level")==null ? 99 : Integer.parseInt(request.getParameter("level"));
		MemberDAO dao = new MemberDAO();
		
		// 1.현재 페이지번호를 구한다.
		int pag = request.getParameter("pag")==null ? 1 : Integer.parseInt(request.getParameter("pag"));
		
		// 2.한페이지의 분량을 결정한다.
		int pageSize = request.getParameter("pageSize")==null ? 3 : Integer.parseInt(request.getParameter("pageSize"));
		
		// 3.총 레코드 건수를 구한다.(sql명령어중 count함수 사용)
		int totRecCnt = dao.getTotRecCnt(level);
		
		// 4.총 페이지 건수를 구한다.
		int totPage = (totRecCnt % pageSize)==0 ? (totRecCnt / pageSize) : (totRecCnt / pageSize) + 1 ;
		
		// 5.현재페이지에 출력할 '시작 인덱스번호'를 구한다.
		int startIndexNo = (pag - 1) * pageSize;
		
		// 6. 현재 화면에 표시될 시작번호를 구한다.
		int curScrStartNo = totRecCnt - startIndexNo;
		
		
		// 블록페이징처리...(시작블록의 번호를 0번으로 처리했다.)
		// 1.블록의 크기결정(여기선 3으로 결정했다.)
		int blockSize = 3;
		
		// 2.현재페이지가 속한 블록 번호를 구한다.(예:총레코드갯수 38개일경우, 1/2/3페이지는 0블록, 4/5/6페이지는 1블록, 7/8페이지는 2블록
		int curBlock = (pag - 1) / blockSize;
		
		// 3. 마지막 블록을 구한다.
		int lastBlock = (totPage - 1) / blockSize;
		
		// 지정된 페이지의 자료를 요청한 한페이지의 분량만큼 가져온다.
		ArrayList<MemberVO> vos = dao.getMemberList(startIndexNo, pageSize, level);
		
		request.setAttribute("vos", vos);
		request.setAttribute("pag", pag);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("totPage", totPage);
		request.setAttribute("curScrStartNo", curScrStartNo);
		request.setAttribute("blockSize", blockSize);
		request.setAttribute("curBlock", curBlock);
		request.setAttribute("lastBlock", lastBlock);
		
		
		String strLevel = "";
		if(level == 0) strLevel = "관리자";
		else if(level == 1) strLevel = "준회원";
		else if(level == 2) strLevel = "정회원";
		else if(level == 3) strLevel = "우수회원";
		else strLevel = "전체회원";
		
		request.setAttribute("level", level);
		request.setAttribute("strLevel", strLevel);
	}

}
