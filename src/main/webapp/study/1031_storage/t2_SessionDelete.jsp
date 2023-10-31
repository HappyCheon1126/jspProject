<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- t2_SessionDelete.jsp -->
<%
  session.invalidate();	// 현재 브라우저에 생성되어 있는 모든 세션을 삭제한다.
%>
<script>
  alert("모든 세션이 삭제되었습니다.");
  location.href = "t2_SessionMain.jsp";
</script>