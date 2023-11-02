package study.j1102;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet(urlPatterns="/j1102/Test4Ok", initParams= {@WebInitParam(name="logoName",value="그린 자바 주식회사"),@WebInitParam(name="homeAddress",value="http://192.168.50.20:9090/javaProject/study/1102_web_xml/test1.jsp")})
public class Test4Ok extends HttpServlet {
  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  	System.out.println("이곳은 Test4Ok service입니다.");
  	
  	String logoName = getInitParameter("logoName");
  	String homeAddress = getInitParameter("homeAddress");
  	
  	HttpSession session = request.getSession();
  	
  	session.setAttribute("sLogoName", logoName);
  	session.setAttribute("sHomeAddress", homeAddress);
  	
  	String viewPage = "/study/1102_web_xml/test4_init.jsp";
  	RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
  	dispatcher.forward(request, response);
  }
}
