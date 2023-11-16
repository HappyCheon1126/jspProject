package admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;
import member.MemberVO;

public class AdminMemberInforCommand implements AdminInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pag = request.getParameter("pag")==null ? 1 : Integer.parseInt(request.getParameter("pag"));
		int pageSize = request.getParameter("pageSize")==null ? 5 : Integer.parseInt(request.getParameter("pageSize"));
		int idx = request.getParameter("idx")==null ? 0 : Integer.parseInt(request.getParameter("idx"));
		int level = request.getParameter("level")==null ? 99 : Integer.parseInt(request.getParameter("level"));
		
		MemberDAO dao = new MemberDAO();
		
		MemberVO vo = dao.getMemberIdxSearch(idx);
		
		// 레벨을 문자로..
		String strLevel = "";
		if(vo.getLevel() == 0) strLevel = "관리자";
		if(vo.getLevel() == 1) strLevel = "준회원";
		if(vo.getLevel() == 2) strLevel = "정회원";
		if(vo.getLevel() == 3) strLevel = "우수회원";
		
		request.setAttribute("pag", pag);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("level", level);
		request.setAttribute("level", level);
		request.setAttribute("vo", vo);
		request.setAttribute("strLevel", strLevel);
	}

}
