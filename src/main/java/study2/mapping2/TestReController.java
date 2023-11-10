package study2.mapping2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("*.re")
public class TestReController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TestReInterface command = null;
		String viewPage = "/WEB-INF/study2/mapping2";
		
		String uri = request.getRequestURI();
		String com = uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
		
		if(com.equals("/test5")) {
			viewPage += "/test5.jsp";
		}
		else if(com.equals("/test5_2")) {
			viewPage += "/test5_2.jsp";
		}
		else if(com.equals("/test5_3")) {
			viewPage += "/test5_3.jsp";
		}
		else if(com.equals("/test5_4")) {
			command = new Test5_4Command();
			command.execute(request, response);
			viewPage += "/test5.jsp";
		}
		else if(com.equals("/test5_5")) {
			command = new Test5_5Command();
			command.execute(request, response);
			viewPage += "/test5_5.jsp";
		}
		else if(com.equals("/test5_6")) {
			command = new Test5_6Command();
			command.execute(request, response);
			//viewPage += "/test5.jsp";
			viewPage = "/include/message.jsp";
		}
		
		request.getRequestDispatcher(viewPage).forward(request, response);
	}
}
