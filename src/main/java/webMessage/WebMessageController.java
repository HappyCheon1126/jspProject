package webMessage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("*.wm")
public class WebMessageController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WebMessageInterface command = null;
		String viewPage = "/WEB-INF/webMessage";
		
		String com = request.getRequestURI();
		com = com.substring(com.lastIndexOf("/"),com.lastIndexOf("."));
		
		HttpSession session = request.getSession();
		int level = session.getAttribute("sLevel")==null ? 999 : (int) session.getAttribute("sLevel");
		
		if(level > 4) {
			request.getRequestDispatcher("/").forward(request, response);
		}
		else if(com.equals("/wmMessage")) {
			command = new WebMessageCommand();
			command.execute(request, response);
			viewPage += "/webMessage.jsp";
		}
		else if(com.equals("/wmInputOk")) {
			command = new WmInputOkCommand();
			command.execute(request, response);
			viewPage = "/include/message.jsp";
		}
		else if(com.equals("/wmDeleteCheck")) {
			command = new WmDeleteCheckCommand();
			command.execute(request, response);
			viewPage = "/include/message.jsp";
		}
		else if(com.equals("/wmDeleteAll")) {
			command = new WmDeleteAllCommand();
			command.execute(request, response);
			return;
		}
		else if(com.equals("/wmDeleteOne")) {
			command = new WmDeleteOneCommand();
			command.execute(request, response);
			return;
		}
				
		request.getRequestDispatcher(viewPage).forward(request, response);
	}
}
