package board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardGoodCheckPlusCommand implements BoardInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// (+1/-1 처리로 통합했음)
		/*
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		BoardDAO dao = new BoardDAO();
		
		// 좋아요수 증가처리
		dao.setBoardGoodCheckPlus(idx);
				
		response.getWriter().write("1");
		*/
	}

}
