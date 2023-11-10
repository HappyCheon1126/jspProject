package study2.ajax3;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberSearchCommand implements LoginInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
		
		LoginDAO dao = new LoginDAO();
		
		LoginVO vo = dao.getLoginSearch(mid);
		
		String res = "검색된 정보 : ";
		if(vo.getName() != null) {
			res += "성명은 / " + vo.getName() + "/ 아이디 : " + vo.getMid() + "/ 포인트 : " + vo.getPoint();
		}
		else {
			res += "없습니다.";
		}
		response.getWriter().write(res);
	}

}
