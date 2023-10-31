<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>login3.jsp</title>
  <jsp:include page="/include/bs4.jsp" />
</head>
<body>
<p><br/></p>
<div class="container">
  <form name="loginForm" method="post" action="${pageContext.request.contextPath}/j1030/loginOk3" >
  	<table class="table table-bordered">
  	  <tr>
  	    <th colspan="2" class="text-center"><h2>회원 로그인</h2></th>
  	  </tr>
  	  <tr>
  	    <th>아이디</th>
  	    <td><input type="text" name="mid" id="mid" value="admin" class="form-control" autofocus required /></td>
  	  </tr>
  	  <tr>
  	    <th>비밀번호</th>
  	    <td><input type="password" name="pwd" id="pwd" value="1234" class="form-control" required /></td>
  	  </tr>
  	  <tr>
  	    <th>닉네임</th>
  	    <td><input type="text" name="nickName" id="nickName" value="관리맨" class="form-control" required /></td>
  	  </tr>
  	  <tr>
  	    <th colspan="2" class="text-center">
  	      <input type="submit" value="로그인" class="btn btn-success mr-3" />
  	      <input type="reset" value="다시입력" class="btn btn-warning" />
  	    </th>
  	  </tr>
  	</table>
  </form>
</div>
<p><br/></p>
</body>
</html>