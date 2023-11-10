package study2.ajax2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserSearchCommand implements UserInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = request.getParameter("idx")==null ? 0 : Integer.parseInt(request.getParameter("idx"));
		
		UserDAO dao = new UserDAO();
		
		UserVO vo = dao.getIdxSearch(idx);
		
		String str = "";
		if(vo.getMid() == null) str = "찾는 자료가 없습니다.";
		else {
			str = idx + "/" + vo.getMid() + "/" + vo.getName() + "/" + vo.getAge() + "/" + vo.getAddress();
		}
		response.getWriter().write(str);
	}

}
