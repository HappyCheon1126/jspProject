package pds;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.SecurityUtil;

public class PdsDeleteOkCommand implements PdsInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = request.getParameter("idx")==null ? 0 : Integer.parseInt(request.getParameter("idx"));
		//String fSName_ = request.getParameter("fSName")==null ? "" : request.getParameter("fSName");
		String pwd = request.getParameter("pwd")==null ? "" : request.getParameter("pwd");
		
		
		// 암호화된 비밀번호와 일치하는지 체크..
		SecurityUtil security = new SecurityUtil();
		pwd = security.encryptSHA256(pwd);
		
		PdsDAO dao = new PdsDAO();
		
		PdsVO vo = dao.getPdsIdxSearch(idx);
		
		String res = "0";
		if(vo.getPwd().equals(pwd)) {
			// 여러개의 파일일때 '/'로 분리처리
			String[] fSNames = vo.getfSName().split("/");
			
			// 서버에 존재하는 파일을 삭제처리한다.
			String realPath = request.getServletContext().getRealPath("/images/pds/");
			for(String fSName : fSNames) {
				new File(realPath + fSName).delete();
			}
			
			// 파일 삭제후 삭제된 정보를 DB에서 제거시킨다.
			res = dao.setPdsDeleteOk(idx);
		}
		response.getWriter().write(res);
	}

}
