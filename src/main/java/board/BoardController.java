package board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("*.bo")
public class BoardController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardInterface command = null;
		String viewPage = "/WEB-INF/board";
		
		String com = request.getRequestURI();
		com = com.substring(com.lastIndexOf("/"),com.lastIndexOf("."));
		
		HttpSession session = request.getSession();
		int level = session.getAttribute("sLevel")==null ? 99 : (int) session.getAttribute("sLevel");
		
		if(level > 4) {
			request.getRequestDispatcher("/").forward(request, response);
		}
		else if(com.equals("/boardList")) {
			command = new BoardListCommand();
			command.execute(request, response);
			viewPage += "/boardList.jsp";
		}
		else if(com.equals("/boardInput")) {
			viewPage += "/boardInput.jsp";
		}
		else if(com.equals("/boardInputOk")) {
			command = new BoardInputOkCommand();
			command.execute(request, response);
			viewPage = "/include/message.jsp";
		}
		else if(com.equals("/boardContent")) {
			command = new BoardContentCommand();
			command.execute(request, response);
			viewPage += "/boardContent.jsp";
		}
		else if(com.equals("/boardGoodCheck")) {
			command = new BoardGoodCheckCommand();
			command.execute(request, response);
			return;
		}
		/* 아래 좋아요 +1/-1 은 중복되기에 통합처리했음
		else if(com.equals("/boardGoodCheckPlus")) {
			command = new BoardGoodCheckPlusCommand();
			command.execute(request, response);
			return;
		}
		else if(com.equals("/boardGoodCheckMinus")) {
			command = new BoardGoodCheckMinusCommand();
			command.execute(request, response);
			return;
		}
		*/
		else if(com.equals("/boardGoodCheckPlusMinus")) {
			command = new BoardGoodCheckPlusMinusCommand();
			command.execute(request, response);
			return;
		}
		else if(com.equals("/boardUpdate")) {
			command = new BoardUpdateCommand();
			command.execute(request, response);
			viewPage += "/boardUpdate.jsp";
		}
		else if(com.equals("/boardUpdateOk")) {
			command = new BoardUpdateOkCommand();
			command.execute(request, response);
			viewPage = "/include/message.jsp";
		}
		else if(com.equals("/boardDelete")) {
			command = new BoardDeleteCommand();
			command.execute(request, response);
			viewPage = "/include/message.jsp";
		}
		else if(com.equals("/boardSearch")) {
			command = new BoardSearchCommand();
			command.execute(request, response);
			viewPage += "/boardSearchList.jsp";
		}
		else if(com.equals("/boardReplyInput")) {
			command = new BoardReplyInputCommand();
			command.execute(request, response);
			return;
		}
		else if(com.equals("/boardReplyDelete")) {
			command = new BoardReplyDeleteCommand();
			command.execute(request, response);
			return;
		}
		
		request.getRequestDispatcher(viewPage).forward(request, response);
	}
}
