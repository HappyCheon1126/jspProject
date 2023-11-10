package study2.ajax1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study2.StudyInterface;
import study2.login.LoginDAO;
import study2.login.LoginVO;

public class AjaxTest1OkCommand implements StudyInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
		
		LoginDAO dao = new LoginDAO();
		
		LoginVO vo = dao.getLoginSearch(mid);
		
		if(vo.getName() != null) {
			request.setAttribute("msg", vo.getName()+"님 환영합니다.");
		}
		else {
			request.setAttribute("msg", "등록된 회원이 아닙니다.");
		}
		request.setAttribute("url", "ajaxTest1.st");
	}

}
