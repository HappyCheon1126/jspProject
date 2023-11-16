package admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.member.MemberLevelChangeCommand;
import member.MemberListCommand;

@SuppressWarnings("serial")
@WebServlet("*.ad")
public class AdminController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminInterface command = null;
		String viewPage = "/WEB-INF/admin";
		
		String com = request.getRequestURI();
		com = com.substring(com.lastIndexOf("/"),com.lastIndexOf("."));
		
		HttpSession session = request.getSession();
		int level = session.getAttribute("sLevel")==null ? 99 : (int) session.getAttribute("sLevel");
		
		if(com.equals("/main")) {
			command = new MainCommand();
			command.execute(request, response);
			viewPage = "/WEB-INF/main/main.jsp";
		}
		else if(level > 0) {
			request.getRequestDispatcher("/").forward(request, response);
		}
		else if(com.equals("/adminMain")) {
			viewPage += "/adminMain.jsp";
		}
		else if(com.equals("/adminLeft")) {
			viewPage += "/adminLeft.jsp";
		}
		else if(com.equals("/adminContent")) {
			command = new AdminContentCommand();
			command.execute(request, response);
			viewPage += "/adminContent.jsp";
		}
		else if(com.equals("/adminMemberList")) {
			command = new MemberListCommand();
			command.execute(request, response);
			viewPage += "/member/adminMemberList.jsp";
		}
		else if(com.equals("/adminMemberLevelChange")) {
			command = new MemberLevelChangeCommand();
			command.execute(request, response);
			return;
		}
		else if(com.equals("/adminMemberInfor")) {
			command = new AdminMemberInforCommand();
			command.execute(request, response);
			viewPage += "/member/adminMemberInfor.jsp";
		}
		
		request.getRequestDispatcher(viewPage).forward(request, response);
	}
}
