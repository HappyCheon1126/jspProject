<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="vo" class="study.j1031.T3VO" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>t3Ok5.jsp</title>
  <jsp:setProperty property="*" name="vo" />
  <jsp:include page="/include/bs4.jsp" />
</head>
<body>
<p><br/></p>
<div class="container">
  <h2>처리된 자료를 View에 출력시켜보자</h2>
  <table class="table table-bordered">
    <tr>
      <th>성명</th>
      <td>${vo.name}/${param.name}</td>
    </tr>
    <tr>
      <th>나이</th>
      <td>${vo.age}/${param.age}</td>
    </tr>
    <tr>
      <th>성별</th>
      <td>${vo.gender}/${param.gender}</td>
    </tr>
    <tr>
      <th>취미</th>
      <td>${vo.hobby}/${param.hobby}</td>
    </tr>
    <tr>
      <th>직업</th>
      <td>${vo.job}/${param.job}</td>
    </tr>
    <tr>
      <th>주소</th>
      <td>${vo.address}/${param.address}</td>
    </tr>
  </table>
  <p><a href="t3.jsp" class="btn btn-success">돌아가기</a></p>
</div>
<p><br/></p>
</body>
</html>