package study2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileUpload1OkCommand implements StudyInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fName = request.getParameter("fName")==null ? "" : request.getParameter("fName");
		System.out.println("pdstest에서 넘어온 파일명 : " + fName);
		
		
		
		request.setAttribute("msg", "파일이 업로드 되었습니다.");
		request.setAttribute("url", "fileUpload1.st");
	}

}
