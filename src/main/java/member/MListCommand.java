package member;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.AdminInterface;
import guest.GuestVO;

public class MListCommand implements MemberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO dao = new MemberDAO();
		
		// 페이징처리
		int pag = request.getParameter("pag")==null ? 1 : Integer.parseInt(request.getParameter("pag"));
		int pageSize = request.getParameter("pageSize")==null ? 3 : Integer.parseInt(request.getParameter("pageSize"));
		int totRecCnt = dao.getTotRecCnt(99);
		int totPage = (totRecCnt % pageSize)==0 ? (totRecCnt / pageSize) : (totRecCnt / pageSize) + 1 ;
		int startIndexNo = (pag - 1) * pageSize;
		int curScrStartNo = totRecCnt - startIndexNo;
		
		int blockSize = 3;
		int curBlock = (pag - 1) / blockSize;
		int lastBlock = (totPage - 1) / blockSize;
		
		ArrayList<MemberVO> vos = dao.getMemberList(startIndexNo, pageSize, 99);
		
		request.setAttribute("vos", vos);
		request.setAttribute("pag", pag);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("totPage", totPage);
		request.setAttribute("curScrStartNo", curScrStartNo);
		request.setAttribute("blockSize", blockSize);
		request.setAttribute("curBlock", curBlock);
		request.setAttribute("lastBlock", lastBlock);
		
		
//		String strLevel = "";
//		if(level == 0) strLevel = "관리자";
//		else if(level == 1) strLevel = "준회원";
//		else if(level == 2) strLevel = "정회원";
//		else if(level == 3) strLevel = "우수회원";
//		else strLevel = "전체회원";
//		
//		request.setAttribute("level", level);
//		request.setAttribute("strLevel", strLevel);
	}

}
