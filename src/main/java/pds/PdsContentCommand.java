package pds;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.review.ReviewVO;

public class PdsContentCommand implements PdsInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = request.getParameter("idx")==null ? 0 : Integer.parseInt(request.getParameter("idx"));
		int pag = request.getParameter("pag")==null ? 1 : Integer.parseInt(request.getParameter("pag"));
		int pageSize = request.getParameter("pageSize")==null ? 5 : Integer.parseInt(request.getParameter("pageSize"));
		String part = request.getParameter("part")==null ? "전체" : request.getParameter("part");
		
		PdsDAO dao = new PdsDAO();
		
		// 해당글의 상세내역 가져오기
		PdsVO vo = dao.getPdsIdxSearch(idx);
		
		// 해당글의 리뷰내용 가져오기
		ArrayList<ReviewVO> rVos = dao.getReviewList(idx,"pds");
		
		int reviewTot = 0;
		for(ReviewVO r : rVos) {
			reviewTot += r.getStar();
		}
		double reviewAvg = 0.0;
		if(rVos.size() != 0) reviewAvg = (double) reviewTot / rVos.size();
		
		request.setAttribute("vo", vo);
		request.setAttribute("pag", pag);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("part", part);
		request.setAttribute("rVos", rVos);
		request.setAttribute("reviewAvg", reviewAvg);
		
	}

}
