package pds;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PdsTotalDownCommand implements PdsInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = request.getParameter("idx")==null ? 0 : Integer.parseInt(request.getParameter("idx"));
		
		// DB에 저장된 파일의 정보(원본파일/시스템에 저장된파일)을 가져와서 각각의 파일로 분리작업한다.
		PdsDAO dao = new PdsDAO();
		PdsVO vo = dao.getPdsIdxSearch(idx);
		
		String[] fNames = vo.getfName().split("/");
		String[] fSNames = vo.getfSName().split("/");
		
		// 파일 압축에 필요한 객체들을 선언 준비한다.
		FileInputStream fis = null;
		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		ServletOutputStream sos = null;
		
		String realPath = request.getServletContext().getRealPath("/images/pds/");
		String zipPath = request.getServletContext().getRealPath("/images/pds/temp/");
		String zipName = vo.getTitle() + ".zip";
		
		fos = new FileOutputStream(zipPath + zipName);
		zos = new ZipOutputStream(fos);
		
		byte[] b = new byte[2048];
		int data = 0;
		
		// 각각의 파일을 압축처리한다.
		for(int i=0; i<fNames.length; i++) {
			File file = new File(realPath + fSNames[i]);
			
			fis = new FileInputStream(file);
			zos.putNextEntry((new ZipEntry(fSNames[i])));
			
			while((data = fis.read(b, 0, b.length)) != -1) {
				zos.write(b, 0, data);
			}
			zos.flush();
			zos.closeEntry();
			fis.close();
		}
		zos.close();
		
		// 서버에서 압축작업이 완료되면, 압축파일을 클라이언트로 전송하고, 서버에 존재하는 압축화일을 삭제한다.
		/* 프로토콜형식에 맞도록 헤더에 정보를 제공해 준다. */
		String downLoadName = "";
		if(request.getHeader("user-agent").indexOf("MSIE") == -1) {
			downLoadName = new String(zipName.getBytes("UTF-8"), "8859_1");
		}
		else {
			downLoadName = new String(zipName.getBytes("EUC-KR"), "8859_1");
		}
		
		// 헤더에 정보를 첨부...
		response.setHeader("Content-Disposition", "attachment;filename="+downLoadName);
		
		fis = new FileInputStream(zipPath + zipName);
		sos = response.getOutputStream();
		
		// 전송할 객체를 생성한후, 실제 전송은 Byte단위로 처리한다.
		while((data = fis.read(b, 0, b.length)) != -1) {
			sos.write(b, 0, data);
		}
		sos.flush();
		// 여기까지 다운로드가 완료된다.
		
		sos.close();
		fis.close();
		// 클라이언트에 압축파일을 전송 완료한다.
		
		// 서버에 존재하는 zip파일을 삭제처리한다.
		new File(zipPath + zipName).delete();
		
		// 다운로드 횟수 증가처리...
		dao.setPdsDownNumCheck(idx);
	}

}
