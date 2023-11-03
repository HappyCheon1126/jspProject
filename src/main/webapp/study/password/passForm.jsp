<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>passForm.jsp</title>
  <jsp:include page="/include/bs4.jsp" />
  <script>
    'use strict';
    
    function pwdCheck(idx) {
    	let pwd = myform.pwd.value.trim();
    	myform.idx.value = idx;
    	
    	if(pwd == "") {
    		alert("비밀번호를 입력하세요");
    		myform.pwd.focus();
    	}
    	else {
    		myform.submit();
    	}
    }
  </script>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<p><br/></p>
<div class="container">
  <h2>비밀번호 암호와 연습</h2>
  <p>(비밀번호를 10자 이내로 입력하세요)</p>
  <hr/>
  <form name="myform" method="post" action="${ctp}/password/passOk1">
    <table class="table table-bordered">
  	  <tr>
  	    <td colspan="2" class="text-center"><h2>회원 로그인</h2></td>
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
  	    <td colspan="2" class="text-center">
  	      <input type="button" value="숫자비번" onclick="pwdCheck(1)" class="btn btn-success mr-2" />
  	      <input type="button" value="혼합비번" onclick="pwdCheck(2)" class="btn btn-primary mr-2" />
  	      <input type="button" value="SHA256" onclick="pwdCheck(3)" class="btn btn-primary mr-2" />
  	      <input type="reset" value="다시입력" class="btn btn-warning mr-2" />
  	    </td>
  	  </tr>
  	</table>
  	<input type="hidden" name="idx" />
  </form>
  <hr/>
  <pre>
  SHA(Secure Hash Algorithm)
  : SHA 단방향식 암호화 기법으로, 암호학적 해시함수들의 모임이다.
  일반적으로 복호화 할 수 없도록 만든 알고리즘으로, SHA-2라고 한다.
  해시함수가 출력되는 암축된 문장을 다이제스트(Digest)라고 하는데,
  이때 SHA-2가 생성해주는 다이제스트의 출력기리는 256, 512Bit가 있다.
  여기서 256Bit의 출력길이를 갖는 SHA-2암호화를 'SHA-256암호화 방식'이라고 한다.
  </pre>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>