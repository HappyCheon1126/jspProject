package member;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberDelelteOkCommand implements MemberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = request.getParameter("idx")==null ? 0 : Integer.parseInt(request.getParameter("idx"));
		String photo = request.getParameter("photo")==null ? "" : request.getParameter("photo");
		
		MemberDAO dao = new MemberDAO();
		
		int res = dao.setMemberDeleteOk(idx);
		
		if(res == 1 && !photo.equals("noimages.jpg")) {
			String realPath = request.getServletContext().getRealPath("/images/member/");
			new File(realPath + photo).delete();
		}
	}

}
