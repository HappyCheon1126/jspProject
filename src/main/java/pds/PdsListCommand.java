package pds;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Pagination;

public class PdsListCommand implements PdsInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pag = request.getParameter("pag")==null ? 1 : Integer.parseInt(request.getParameter("pag"));
		int pageSize = request.getParameter("pageSize")==null ? 5 : Integer.parseInt(request.getParameter("pageSize"));
		String part = request.getParameter("part")==null ? "전체" : request.getParameter("part");
		
		PdsDAO dao = new PdsDAO();
		
		// 1.페이징처리에 필요한, 보여줄 페이지의 시작인덱스번호(startIndexNo)를 구한후, 
		// 2.넘겨줄 List 내용들을 DB에서 가져온후, 
		// 3.request저장소에 담아준다. 
		int startIndexNo = (pag - 1) * pageSize;
		ArrayList<PdsVO> vos = dao.getPdsList(startIndexNo, pageSize, part);
		request.setAttribute("vos", vos);
		
		// 4.전체 레코드건수(totRecCnt)를 구한후, 
		// 5.페이징처리할 메소드를 호출한다.
		int totRecCnt = dao.getTotRecCnt(part);
		Pagination.pageChange(request, pag, pageSize, totRecCnt, startIndexNo, part, "", "");
	}

}
