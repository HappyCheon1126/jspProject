package member;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import guest.GuestDAO;
import guest.GuestVO;

public class MemberMainCommand implements MemberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String mid = (String) session.getAttribute("sMid");
		
		MemberDAO memberDao = new MemberDAO();
		MemberVO mVo = memberDao.getMemberMidCheck(mid);
		
		GuestDAO guestDao = new GuestDAO();
		ArrayList<GuestVO> gVos = guestDao.getMemberSearch(mid, mVo.getName(), mVo.getNickName());
		
		request.setAttribute("mVo", mVo);
		request.setAttribute("gVos", gVos);
		
		
	}

}
