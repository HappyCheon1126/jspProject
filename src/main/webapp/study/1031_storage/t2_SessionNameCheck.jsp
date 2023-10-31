<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- t2_SessionNameCheck.jsp -->
<jsp:include page="/include/bs4.jsp" />
<p><br/></p>
<div class="container">
<%
  Enumeration names = session.getAttributeNames();

  while(names.hasMoreElements()) {
  	String sName = (String) names.nextElement();
  	out.println("세션이름 : " + sName + "<br/>");
  }
%>
<hr/>
<p><a href="t2_SessionMain.jsp" class="btn btn-success">돌아가기</a></p>
</div>