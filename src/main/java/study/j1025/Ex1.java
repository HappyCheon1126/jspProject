package study.j1025;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Ex1")
public class Ex1 extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		
		int i = 0, tot = 0;
		
		while(i < 100) {
			i++;
			tot += i;
		}
		
		System.out.println("1~100까지의 합은? " + tot + "입니다.");
		
		PrintWriter out = response.getWriter();
		
		out.println("1~100까지의 합은??? ");
		out.println(tot + " 입니다.<br/>");
		out.println("<input type='button' value='다시시작' onclick='location.reload()' />");
	}

}
