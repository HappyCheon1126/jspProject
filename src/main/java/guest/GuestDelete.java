package guest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/guestDelete")
public class GuestDelete extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = request.getParameter("idx")==null ? 0 : Integer.parseInt(request.getParameter("idx"));
		
		GuestDAO dao = new GuestDAO();
		
		int res = dao.setGuestDelete(idx);
		
		PrintWriter out = response.getWriter();
		if(res != 0) {
    	out.println("<script>");
    	out.println("alert('방명록에 글이 삭제되었습니다.');");
    	out.println("location.href='"+request.getContextPath()+"/GuestList';");
    	out.println("</script>");
		}
		else {
			out.println("<script>");
    	out.println("alert('방명록 글 삭제 실패~~');");
    	out.println("location.href='"+request.getContextPath()+"/GuestList';");
    	out.println("</script>");
		}
	}
}
