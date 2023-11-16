package admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;
import member.MemberVO;

public class AdminContentCommand implements AdminInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO mDao = new MemberDAO();
		
		ArrayList<MemberVO> mVos = mDao.getMemberList(0,5,1);
		
		request.setAttribute("mCount", mVos.size());
	}

}
