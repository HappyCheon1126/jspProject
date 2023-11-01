<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>jstl4Res.jsp</title>
  <jsp:include page="/include/bs4.jsp" />
</head>
<body>
<p><br/></p>
<div class="container">
  <h2>개별 회원 자료....</h2>
  <p>성명 : ${vo.name}</p>
  <p>나이 : ${vo.age}</p>
  <p>성별 : ${vo.gender}</p>
  <p>직업 : ${vo.job}</p>
  <p>주소 : ${vo.address}</p>
  <hr/>
  <p><a href="${ctp}/study/1101_JSTL/jstl4.jsp" class="btn btn-success">돌아가기</a></p>
</div>
<p><br/></p>
</body>
</html>