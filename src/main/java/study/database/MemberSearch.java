package study.database;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/database/MemberSearch")
public class MemberSearch extends HttpServlet {
  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  	String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
  	
  	LoginDAO dao = new LoginDAO();
  	
  	LoginVO vo = dao.getLoginSearch(mid);
  	
  	request.setAttribute("vo", vo);
  	
  	String viewPage = "/study/database/memberSearch.jsp";
  	RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
  	dispatcher.forward(request, response);  	
  }
}
