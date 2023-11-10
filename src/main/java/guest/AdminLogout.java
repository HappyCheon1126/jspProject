package guest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/guest/adminLogout")
public class AdminLogout extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		session.removeAttribute("sAdmin");
		
		//response.sendRedirect(request.getContextPath()+"/GuestList");
		
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('관리자 로그아웃 되었습니다.');");
		out.println("location.href='"+request.getContextPath()+"/GuestList';");
		out.println("</script>");
	}
}
