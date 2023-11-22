package admin.complaint;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.AdminDAO;
import admin.AdminInterface;
import admin.board.BoardComplaintVO;

public class BoardComplaintInputCommand implements AdminInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String part = request.getParameter("part")==null ? "" : request.getParameter("part");
		int partIdx = request.getParameter("partIdx")==null ? 0 : Integer.parseInt(request.getParameter("partIdx"));
		String cpMid = request.getParameter("cpMid")==null ? "" : request.getParameter("cpMid");
		String cpContent = request.getParameter("cpContent")==null ? "" : request.getParameter("cpContent");
		
		AdminDAO dao = new AdminDAO();
		BoardComplaintVO vo = new BoardComplaintVO();
		
		vo.setPart(part);
		vo.setPartIdx(partIdx);
		vo.setCpMid(cpMid);
		vo.setCpContent(cpContent);
		
		int res = dao.setBoardComplaintInput(vo);
				
		response.getWriter().write(res+"");
	}

}
