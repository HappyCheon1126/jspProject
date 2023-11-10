package guest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/GuestInputOk")
public class GuestInputOk extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name")==null ? "" : request.getParameter("name");
		String email = request.getParameter("email")==null ? "" : request.getParameter("email");
		String homePage = request.getParameter("homePage")==null ? "" : request.getParameter("homePage");
		String content = request.getParameter("content")==null ? "" : request.getParameter("content");
		String hostIp = request.getParameter("hostIp")==null ? "" : request.getParameter("hostIp");
		
		name = name.replace("<", "&lt;");
		name = name.replace(">", "&gt;");
		
		GuestVO vo = new GuestVO();
		
		vo.setName(name);
		vo.setEmail(email);
		vo.setHomePage(homePage);
		vo.setContent(content);
		vo.setHostIp(hostIp);
		
		GuestDAO dao = new GuestDAO();
		
		int res = dao.setGuestInputOk(vo);
		
		PrintWriter out = response.getWriter();
		if(res != 0) {
    	out.println("<script>");
    	out.println("alert('방명록에 글이 등록되었습니다.');");
    	out.println("location.href='"+request.getContextPath()+"/GuestList';");
//    	out.println("location.href='"+request.getContextPath()+"/guest/guestList.jsp';");
    	out.println("</script>");
		}
		else {
			out.println("<script>");
    	out.println("alert('방명록 글 등록 실패~~');");
    	out.println("location.href='"+request.getContextPath()+"/guest/guestInput.jsp';");
    	out.println("</script>");
		}
	}
}
