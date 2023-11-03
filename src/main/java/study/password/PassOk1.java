package study.password;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.SecurityUtil;

@SuppressWarnings("serial")
@WebServlet("/password/passOk1")
public class PassOk1 extends HttpServlet {
  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  	String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
  	String pwd = request.getParameter("pwd")==null ? "" : request.getParameter("pwd").toUpperCase();
  	int idx = request.getParameter("idx")==null ? 0 : Integer.parseInt(request.getParameter("idx"));
  	
  	System.out.println("== 원본 자료 ==");
  	System.out.println("mid : " + mid);
  	System.out.println("pwd : " + pwd);
  	System.out.println("idx : " + idx);
  	
  	if(idx == 1) {
  		// 숫자만을 암호화 하는 경우
  		// 암호화를 위한 암호화키(0x1234ABCD)
  		int key = 0x1234ABCD;
  		
  		int encPwd, decPwd;
  		
  		// 암호화...(EOR(배타적OR) 암호화) => 이진수 EOR : ^
  		encPwd = Integer.parseInt(pwd) ^ key;
  		
  		System.out.println("== 암호화된 비밀번호 ==");
    	System.out.println("mid : " + mid);
    	System.out.println("pwd : " + encPwd);
    	System.out.println();
    	
    	// 복호화...
    	decPwd = encPwd ^ key;
    	
    	System.out.println("== 복호화된 비밀번호 ==");
    	System.out.println("mid : " + mid);
    	System.out.println("pwd : " + decPwd);
    	
    	PrintWriter out = response.getWriter();
    	out.println("<script>");
    	out.println("alert('원본 비번 : "+pwd+" \\n암호화된 비번 : "+encPwd+"');");
    	out.println("location.href='"+request.getContextPath()+"/study/password/passForm.jsp';");
    	out.println("</script>");
  	}
  	else if(idx == 1) {
  		System.out.println();
  		// 숫자와 영문 대/소문자의 혼합(아스키코드 2자리로 변환후 암호화 처리하도록 한다. : 영문소문자로 입력시는 대분자로 변경해서 처리하도록 하겠음)
  		long intPwd;
  		String strPwd = "";
  		System.out.println("1.원본 비밀번호(예:ABCD) :" + pwd);
  		for(int i=0; i<pwd.length(); i++) {
  			intPwd = pwd.charAt(i);
  			strPwd += intPwd;
  		}
  		System.out.println("2.ASCII코드문자로 변환된 비밀번호(예:65666768) :" + strPwd);
  		
  		intPwd = Long.parseLong(strPwd);
  		
  		long encPwd, key = 0x1234ACBF;
  		
  		encPwd = intPwd ^ key;
  		strPwd = String.valueOf(encPwd);
  		System.out.println("3.암호화된 비밀번호(예:______) :" + strPwd);
  		// 암호화된 비번과 암호화키를 db에 저장시켜놓는다.
  		
  		// DB에 저장된 암호를 다시 불러와서 복호화(디코딩) 시켜준다.
  		long decPwd;
  		intPwd = Long.parseLong(strPwd);
  		decPwd = intPwd ^ key;
  		System.out.println("복호화된 비번 : " + decPwd);  		
  		
  		// 복호화(복호화 되는 비번은 숫자이기에 2개씩 분리시켜서 처리한다.)
  		strPwd = String.valueOf(decPwd);
  		
  		char ch;
  		String result = "";
  		for(int i=0; i<strPwd.length(); i+=2) {
  			ch = (char) Integer.parseInt(strPwd.substring(i,i+2));
  			result += ch;
  		}
  		System.out.println("최종적으로 복호화된 비밀번호 : " + result);
  		
  		PrintWriter out = response.getWriter();
    	out.println("<script>");
    	out.println("alert('원본 비번 : "+pwd+" \\n암호화된 비번 : "+encPwd+"');");
    	out.println("location.href='"+request.getContextPath()+"/study/password/passForm.jsp';");
    	out.println("</script>");
  	}
  	else {
  		SecurityUtil security = new SecurityUtil();
  		String shaPwd = security.encryptSHA256(pwd);
  		
  		System.out.println("원본 pwd : " + pwd);
  		System.out.println("암호화 pwd : " + shaPwd);
  		
  		PrintWriter out = response.getWriter();
    	out.println("<script>");
    	out.println("alert('원본 비번 : "+pwd+" \\n암호화된 비번 : "+shaPwd+"');");
    	out.println("location.href='"+request.getContextPath()+"/study/password/passForm.jsp';");
    	out.println("</script>");
  	}
  }
}
