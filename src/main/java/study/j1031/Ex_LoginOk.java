package study.j1031;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/j1031/ex_LoginOk")
public class Ex_LoginOk extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
		String pwd = request.getParameter("pwd")==null ? "" : request.getParameter("pwd");
		PrintWriter out = response.getWriter();
		
		if((mid.equals("admin") && pwd.equals("1234")) ||(mid.equals("atom") && pwd.equals("1234"))) {
			// 회원 인증 성공시 처리...
			// 회원인증후 처리(1.자주사용하는자료를 세션에 저장(아이디,성명,닉네임),  2.쿠키에 아이디 저장또는 제거하기)
			// 1.세션처리
			HttpSession session = request.getSession();
			session.setAttribute("sMid", mid);
			
			// 2.쿠키에 아이디를 저장/해제 처리한다.
			// 로그인시 아이디저장시킨다고 체크하면 쿠키에 아이디 저장하고, 그렇지 않으면 쿠키에서 아이디를 제거한다.
			String idSave = request.getParameter("idSave")==null ? "off" : "on";
			Cookie cookieMid = new Cookie("cMid", mid);
			cookieMid.setPath("/");
			if(idSave.equals("on")) {
				cookieMid.setMaxAge(60*60*24*7);
			}
			else {
				cookieMid.setMaxAge(0);
			}
			response.addCookie(cookieMid);
			
			// 정상 로그인Ok이후에 모든 처리가 끝나면 ex_member.jsp로 보내준다.
			out.print("<script>");
			out.print("alert('"+mid+"님 로그인 되었습니다.');");
			out.print("location.href='"+request.getContextPath()+"/study/1031_storage/ex_member.jsp';");
			out.print("</script>");
		}
		else {
			// 회원 인증 실패시 처리... 다시 로그인창으로 보내준다.
			out.print("<script>");
			out.print("alert('로그인 실패~~');");
			out.print("location.href='"+request.getContextPath()+"/study/1031_storage/ex_login.jsp';");
			out.print("</script>");
		}
	}
}
