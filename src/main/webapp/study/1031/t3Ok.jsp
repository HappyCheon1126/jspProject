<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  // request.setCharacterEncoding("utf-8");

  String name = request.getParameter("name")==null ? "" : request.getParameter("name");
  String age = request.getParameter("age")==null ? "" : request.getParameter("age");
  
  pageContext.setAttribute("name", name);
  request.setAttribute("age", age);
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>t3Ok.jsp</title>
  <jsp:include page="/include/bs4.jsp" />
</head>
<body>
<p><br/></p>
<div class="container">
  <h2>처리된 자료를 View에 출력시켜보자</h2>
  <table class="table table-bordered">
    <tr>
      <th>성명</th>
      <td><%=name%>/${name}</td>
    </tr>
    <tr>
      <th>나이</th>
      <td><%=age%>/${age}</td>
    </tr>
    <tr>
      <th>성별</th>
      <td></td>
    </tr>
    <tr>
      <th>취미</th>
      <td></td>
    </tr>
    <tr>
      <th>직업</th>
      <td></td>
    </tr>
    <tr>
      <th>주소</th>
      <td></td>
    </tr>
  </table>
  <p><a href="t3.jsp" class="btn btn-success">돌아가기</a></p>
</div>
<p><br/></p>
</body>
</html>