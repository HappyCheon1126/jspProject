<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- test13Logout.jsp -->
<%
  String name = request.getParameter("name");
	pageContext.setAttribute("name", name);
%>

<script>
  alert("${name}님 로그아웃 되었습니다.(MVC)");
  <%-- location.href = "<%=request.getContextPath()%>/study/1026/test13.jsp"; --%>
  <%-- location.href = "<%=request.getContextPath()%>/study/1026/test13.jsp"; --%>
  location.href = "${pageContext.request.contextPath}/study/1026/test13.jsp";
  /* location.href = "test13.jsp"; */
</script>