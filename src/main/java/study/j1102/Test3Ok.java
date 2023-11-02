package study.j1102;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/j1102/Test3Ok")
public class Test3Ok extends HttpServlet {
  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  	System.out.println("이곳은 Test3Ok service입니다.");
  	
  	String logoName = getServletContext().getInitParameter("logoName");
  	String homeAddress = getServletContext().getInitParameter("homeAddress");
  	
  	HttpSession session = request.getSession();
  	
  	session.setAttribute("sLogoName", logoName);
  	session.setAttribute("sHomeAddress", homeAddress);
  	
  	String viewPage = "/study/1102_web_xml/test3_init.jsp";
  	RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
  	dispatcher.forward(request, response);
  }
}
