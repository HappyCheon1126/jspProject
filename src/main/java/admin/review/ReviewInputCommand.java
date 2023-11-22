package admin.review;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.AdminDAO;
import admin.AdminInterface;

public class ReviewInputCommand implements AdminInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String part = request.getParameter("part")==null ? "" : request.getParameter("part");
		int partIdx = request.getParameter("partIdx")==null ? 0 : Integer.parseInt(request.getParameter("partIdx"));
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
		int star = request.getParameter("star")==null ? 0 : Integer.parseInt(request.getParameter("star"));
		String content = request.getParameter("content")==null ? "" : request.getParameter("content");
		
		ReviewVO vo = new ReviewVO();
		
		vo.setPart(part);
		vo.setPartIdx(partIdx);
		vo.setMid(mid);
		vo.setStar(star);
		vo.setContent(content);
		
		AdminDAO dao = new AdminDAO();
		
		int res = dao.setReviewInputOk(vo);
		
		response.getWriter().write(res+"");
	}

}
