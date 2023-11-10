package study2.ajax3;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.SecurityUtil;
import study.database.LoginDAO;
import study.database.LoginVO;

public class UpdateOkCommand implements LoginInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
		String pwd = request.getParameter("pwd")==null ? "" : request.getParameter("pwd");
		String name = request.getParameter("name")==null ? "" : request.getParameter("name");
		
		LoginDAO dao = new LoginDAO();
		
		// 비밀번호 체크하기..
		SecurityUtil security = new SecurityUtil();
		pwd = security.encryptSHA256(pwd);
		LoginVO vo = dao.getLoginCheck(mid, pwd);
		
		if(vo.getMid() == null) {
			response.getWriter().write("0");
		}
		else {
			vo = new LoginVO();
			vo.setMid(mid);
			vo.setName(name);
			System.out.println("vo : " + vo);
			int res = dao.setUpdateOk(vo);
			System.out.println("res : " + res);
			if(res != 0) {
				HttpSession session = request.getSession();
				session.setAttribute("sName", name);
				response.getWriter().write("1");
			}
			else {
				response.getWriter().write("0");
			}
		}
	}

}
