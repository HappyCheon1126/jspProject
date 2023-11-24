package webMessage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Pagination;
import pds.PdsVO;

public class WebMessageCommand implements WebMessageInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String mid = (String) session.getAttribute("sMid");
		
		int idx = request.getParameter("idx")==null ? 0 : Integer.parseInt(request.getParameter("idx"));
		int mSw = request.getParameter("mSw")==null ? 1 : Integer.parseInt(request.getParameter("mSw"));
		int mFlag = request.getParameter("mFlag")==null ? 0 : Integer.parseInt(request.getParameter("mFlag"));
		
		WebMessageDAO dao = new WebMessageDAO();
		WebMessageVO vo = null;
		
		// 메세지 내용상세보기처리... mSw=6 일때처리..
		if(mSw == 6) {
			vo = dao.getWmMessageContent(idx, mFlag);
			request.setAttribute("vo", vo);
		}
		else {
			
			// 페이징처리 
			int pag = request.getParameter("pag")==null ? 1 : Integer.parseInt(request.getParameter("pag"));
		  int pageSize = request.getParameter("pageSize")==null ? 5 : Integer.parseInt(request.getParameter("pageSize"));
			int startIndexNo = (pag - 1) * pageSize;
			ArrayList<WebMessageVO> vos = dao.getMessageList(mid, mSw, startIndexNo, pageSize);
			request.setAttribute("vos", vos);
			int totRecCnt = dao.getTotRecCnt(mid, mSw);
			Pagination.pageChange(request, pag, pageSize, totRecCnt, startIndexNo, "", "", "");
		}
		request.setAttribute("mSw", mSw);
		request.setAttribute("mFlag", mFlag);
	}

}
