<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="vo" class="study.j1031.T3VO" />
<!--
  서블릿에서는 getter()와 setter()을 이용해서 값을 불러오거나 저장한다.
  jsp에서는 getProperty와 setProperty을 이용해서 값을 불러오거나 저장한다.
-->
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>t3Ok4.jsp</title>
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
      <td><jsp:getProperty property="name" name="vo"/>/${vo.name}</td>
    </tr>
    <tr>
      <th>나이</th>
      <td><jsp:getProperty property="age" name="vo"/>/${vo.age}</td>
    </tr>
    <tr>
      <th>성별</th>
      <td><jsp:getProperty property="gender" name="vo"/>/${vo.gender}</td>
    </tr>
    <tr>
      <th>취미</th>
      <td><jsp:getProperty property="hobby" name="vo"/>/${vo.hobby}</td>
    </tr>
    <tr>
      <th>직업</th>
      <td><jsp:getProperty property="job" name="vo"/>/${vo.job}</td>
    </tr>
    <tr>
      <th>주소</th>
      <td><jsp:getProperty property="address" name="vo"/>/${vo.address}</td>
    </tr>
  </table>
  <p><a href="t3.jsp" class="btn btn-success">돌아가기</a></p>
</div>
<p><br/></p>
</body>
</html>