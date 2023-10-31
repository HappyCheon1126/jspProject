<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- t1_CookiesSave.jsp -->
<%
  String mid = "hkd1234";
  Cookie cookieMid = new Cookie("cMid", mid);
  cookieMid.setMaxAge(60*60*24);	// 쿠키의 만료시간(초) : 60*60*24  - 1일
  
  String pwd = "1234";
  Cookie cookiePwd = new Cookie("cPwd", pwd);
  cookiePwd.setMaxAge(60*60*24);
  
  String tel = "010-1234-5678";
  Cookie cookieTel = new Cookie("cTel", tel);
  cookieTel.setMaxAge(60);
  
  response.addCookie(cookieMid);
  response.addCookie(cookiePwd);
  response.addCookie(cookieTel);
%>
<script>
  alert("쿠키가 저장되었습니다.");
  location.href = "t1_CookiesMain.jsp";
</script>