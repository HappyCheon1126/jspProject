package study.database;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/database/updateOk")
public class UpdateOk extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
		String pwd = request.getParameter("pwd")==null ? "" : request.getParameter("pwd");
		String name = request.getParameter("name")==null ? "" : request.getParameter("name");
		
		LoginDAO dao = new LoginDAO();
		
		// 비밀번호 체크하기..
		LoginVO vo = dao.getLoginCheck(mid, pwd);
		
		PrintWriter out = response.getWriter();
		if(vo.getMid() == null) {
			out.println("<script>");
			out.println("alert('비밀번호 오류~~ 비밀번호를 확인하세요.');");
			out.println("location.href='"+request.getContextPath()+"/study/database/update.jsp';");
			out.println("</script>");
			return;
		}
		
		vo = new LoginVO();
		vo.setMid(mid);
		// vo.setPwd(pwd);
		vo.setName(name);
		
		int res = dao.setUpdateOk(vo);
		
		if(res != 0) {
			HttpSession session = request.getSession();
			session.setAttribute("sName", name);
			out.println("<script>");
			out.println("alert('"+mid+"님 정보가 수정되었습니다.');");
			out.println("location.href='"+request.getContextPath()+"/study/database/memberMain.jsp';");
			out.println("</script>");
		}
		else {
			out.println("<script>");
			out.println("alert('회원 정보 수정 실패~~~');");
			out.println("location.href='"+request.getContextPath()+"/study/database/memberMain.jsp';");
			out.println("</script>");			
		}
	}
}
