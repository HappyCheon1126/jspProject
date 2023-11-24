<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>api1.jsp</title>
  <jsp:include page="/include/bs4.jsp" />
</head>
<body>
<jsp:include page="/include/header.jsp" />
<p><br/></p>
<div class="container">
  <h2>API 연습</h2>
  <hr/>
  <div>
    <input type="button" value="fetch연습" onclick="location.href='apiFetch.st';" class="btn btn-success mr-3"/>
    <input type="button" value="API연습" onclick="location.href='apiTest.st';" class="btn btn-primary"/>
  </div>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>