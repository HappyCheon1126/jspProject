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
		
		ArrayList<MemberVO> mVos = mDao.getMemberList(0,999,1);
		ArrayList<MemberVO> m99Vos = mDao.getMemberList(0,999,99);
		
		request.setAttribute("mCount", mVos.size());
		request.setAttribute("m99Count", m99Vos.size());
	}

}
