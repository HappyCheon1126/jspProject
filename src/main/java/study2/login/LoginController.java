package study2.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("*.lo")
public class LoginController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginInterface command = null;
		String viewPage = "/WEB-INF/study2/login";
		
		String com = request.getRequestURI();
		com = com.substring(com.lastIndexOf("/"), com.lastIndexOf("."));
		
		if(com.equals("/login")) {
			viewPage += "/login.jsp";
		} 
		else if(com.equals("/join")) {
			viewPage += "/join.jsp";
		}
		else if(com.equals("/loginOk")) {
			command = new LoginOkCommand();
			command.execute(request, response);			
			viewPage = "/include/message.jsp";
		}
		else if(com.equals("/logout")) {
			command = new LogoutCommand();
			command.execute(request, response);			
			viewPage = "/include/message.jsp";
		}
		else if(com.equals("/join")) {
			viewPage += "/join.jsp";
		}
		else if(com.equals("/joinOk")) {
			command = new JoinOkCommand();
			command.execute(request, response);			
			viewPage = "/include/message.jsp";
		}
		else if(com.equals("/memberMain")) {
//			command = new MemberMainCommand();
//			command.execute(request, response);			
			viewPage += "/memberMain.jsp";
		}
		else if(com.equals("/memberSearch")) {
			command = new MemberSearchCommand();
			command.execute(request, response);			
			viewPage = "/include/message.jsp";
		}
		else if(com.equals("/memberMidSearch")) {
			command = new MemberMidSearchCommand();
			command.execute(request, response);			
			viewPage += "/memberSearch.jsp";
		}
		else if(com.equals("/memberList")) {
			command = new MemberListCommand();
			command.execute(request, response);			
			viewPage += "/memberList.jsp";
		}
		else if(com.equals("/update")) {
			command = new UpdateCommand();
			command.execute(request, response);			
			viewPage += "/update.jsp";
		}
		else if(com.equals("/updateOk")) {
			command = new UpdateOkCommand();
			command.execute(request, response);			
			viewPage = "/include/message.jsp";
		}	else if(com.equals("/deleteOk")) {
			command = new DeleteOkCommand();
			command.execute(request, response);			
			viewPage = "/include/message.jsp";
		}
		
		request.getRequestDispatcher(viewPage).forward(request, response);
	}
}
