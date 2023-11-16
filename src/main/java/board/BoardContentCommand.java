package board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BoardContentCommand implements BoardInterface {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = request.getParameter("idx")==null ? 0 : Integer.parseInt(request.getParameter("idx"));
		int pag = request.getParameter("pag")==null ? 1 : Integer.parseInt(request.getParameter("pag"));
		int pageSize = request.getParameter("pageSize")==null ? 5 : Integer.parseInt(request.getParameter("pageSize"));
		String flag = request.getParameter("flag")==null ? "" : request.getParameter("flag");
		String search = request.getParameter("search")==null ? "" : request.getParameter("search");
		String searchString = request.getParameter("searchString")==null ? "" : request.getParameter("searchString");
		
		BoardDAO dao = new BoardDAO();
		
		// 게시글 조회수 1 증가시키기(중복 불허)
		HttpSession session = request.getSession();
		ArrayList<String> boardContentIdx = (ArrayList) session.getAttribute("sBoardContentIdx");
		if(boardContentIdx == null) {
			boardContentIdx = new ArrayList<String>();
		}
		String imsiContextIdx = "board" + idx;
		if(!boardContentIdx.contains(imsiContextIdx)) {
			dao.setBoardReadNumPlus(idx);
			boardContentIdx.add(imsiContextIdx);
		}
		session.setAttribute("sBoardContentIdx", boardContentIdx);
		
		BoardVO vo = dao.getBoardContent(idx);
		
		request.setAttribute("vo", vo);
		request.setAttribute("pag", pag);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("flag", flag);
		request.setAttribute("search", search);
		request.setAttribute("searchString", searchString);
		
		// 이전글 / 다음글 처리
		BoardVO preVo = dao.getPreNexSearch(idx, "preVo");
		BoardVO nextVo = dao.getPreNexSearch(idx, "nextVo");
		request.setAttribute("preVo", preVo);
		request.setAttribute("nextVo", nextVo);
		
		// 댓글 처리
		ArrayList<BoardReplyVO> replyVos = dao.getBoardReply(idx);
		request.setAttribute("replyVos", replyVos);
	}

}
