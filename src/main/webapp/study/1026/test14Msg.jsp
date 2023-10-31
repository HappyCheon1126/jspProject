<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- test14Msg.jsp -->
<script>
  alert("${name}님 로그아웃 되셨습니다.");
  location.href = '${pageContext.request.contextPath}/study/1026/test14.jsp';
</script>