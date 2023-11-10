package member;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.SecurityUtil;

public class MemberPasswordSearchCommand implements MemberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
    String email = request.getParameter("email")==null ? "" : request.getParameter("email");
		
		MemberDAO dao = new MemberDAO();
		
		int res = dao.getMemberMidEmailSearch(mid, email);
		
		String str = "";
		if(res != 0) {
			// 임시 비밀번호 발급
			UUID uid = UUID.randomUUID();
			String pwdUid = uid.toString().substring(0,8);
			str += "새 발급 비밀번호 : <font color=blue><b>" + pwdUid + "</b></font>";
			
			// 새로 발급받은 비밀번호를 DB에 등록시켜준다.(비밀번호 암호화 처리후 등록)
			SecurityUtil security = new SecurityUtil();
			pwdUid = security.encryptSHA256(pwdUid);
			dao.setMemberPwdUpdate(mid, pwdUid);
		}
		else str += "아이디와 메일주소가 일치하지 않습니다.";
		
		response.getWriter().write(str);
	}

}
