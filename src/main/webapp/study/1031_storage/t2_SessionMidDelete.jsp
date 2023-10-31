<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- t2_SessionMidDelete.jsp -->
<%
	session.removeAttribute("sMid");
%>
<script>
  alert("아이디 세션이 삭제되었습니다.");
  location.href = "t2_SessionMain.jsp";
</script>