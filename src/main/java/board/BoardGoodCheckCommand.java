package board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BoardGoodCheckCommand implements BoardInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		BoardDAO dao = new BoardDAO();
		
		// 좋아요수 증가처리
		String sw = "0";
		HttpSession session = request.getSession();
		ArrayList<String> goodIdxObj = (ArrayList) session.getAttribute("sGoodIdxObj");
		if(goodIdxObj == null) {
			goodIdxObj = new ArrayList<String>();
		}
		String imsiGoodIdx = "boardGood" + idx;
		if(!goodIdxObj.contains(imsiGoodIdx)) {
			dao.setBoardGoodCheck(idx);
			goodIdxObj.add(imsiGoodIdx);
			sw = "1";
		}
		session.setAttribute("sGoodIdxObj", goodIdxObj);
		
				
		response.getWriter().write(sw);
	}

}
