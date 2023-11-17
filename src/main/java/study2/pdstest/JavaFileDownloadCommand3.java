package study2.pdstest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study2.StudyInterface;

public class JavaFileDownloadCommand3 implements StudyInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String realPath = request.getServletContext().getRealPath("/images/pdstest/");
		
		String fileName= request.getParameter("file")==null? "" : request.getParameter("file");
		
		File file = new File(realPath + fileName);
		
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		
		fis = new FileInputStream(file);
		bis = new BufferedInputStream(fis);
		//bos = new BufferedOutputStream(fis);
		ServletOutputStream sos = response.getOutputStream();
		//sos = 
		
//		fos = new FileOutputStream("aaa.jpg");
	//	bos = new BufferedOutputStream(fos);
		byte[] b = new byte[2048];
		int i = 0;
//		while(i=(bis.read(b)) != -1) {
		while((i = bis.read(b, 0, b.length)) != -1) {
			sos.write(b, 0, i);
		}
		sos.flush();
			
		bis.close();
		fis.close();
	}

}
