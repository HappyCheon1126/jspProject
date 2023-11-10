<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<%
	// 로그인창에 아이디 체크 유무에 대한 처리
	// 쿠키를 검색해서 cMid가 있을때 가져와서 아이디입력창에 뿌릴수 있게 한다.
	Cookie[] cookies = request.getCookies();

	if(cookies != null) {
		for(int i=0; i<cookies.length; i++) {
			if(cookies[i].getName().equals("cMid")) {
				pageContext.setAttribute("mid", cookies[i].getValue());
				break;
			}
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>login.jsp</title>
  <jsp:include page="/include/bs4.jsp" />
  <style>
    th {
      background-color: #eee;
      text-align: center;
    }
  </style>
  <script>
    'use strict';
    
  	function loginCheck() {
  		//let mid = document.getElementById("mid").value;
  		let mid = $("#mid").val();
  		let pwd = $("#pwd").val();
  		let idSave = document.getElementById('idSave').checked ? "on" : "off";
  		//let idSave = $('#idSave').is(':checked') ? "on" : "off";
  		
  		let query = {
  				mid : mid,
  				pwd : pwd,
  				idSave : idSave
  		}
  		
  		$.ajax({
  			url  : "loginOk.alo",
  			type : "post",
  			data : query,
  			success:function(res) {
  				alert(res);
  				if(res.indexOf(mid) == -1) location.href = "login.alo";
  				else location.href = "memberMain.alo";
  			},
  			error : function() {
  				alert("전송오류!!");
  			}
  		});
  	}
  </script>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<p><br/></p>
<div class="container">
  <%-- <form name="loginForm" method="post" action="${ctp}/database~~~@@@1111/loginOk.lo" > --%>
  <!-- <form name="loginForm" method="post" action="loginOk.alo" > -->
  <form name="loginForm">
  	<table class="table table-bordered">
  	  <tr>
  	    <td colspan="2" class="text-center"><h2>회원 로그인(AJax사용)</h2></td>
  	  </tr>
  	  <tr>
  	    <th>아이디</th>
  	    <td><input type="text" name="mid" id="mid" value="${mid}" class="form-control" autofocus required /></td>
  	  </tr>
  	  <tr>
  	    <th>비밀번호</th>
  	    <td><input type="password" name="pwd" id="pwd" value="1234" class="form-control" required /></td>
  	  </tr>
  	  <tr>
  	    <td colspan="2" class="text-center">
  	      <input type="button" value="로그인" onclick="loginCheck()" class="btn btn-success mr-2" />
  	      <input type="reset" value="다시입력" class="btn btn-warning mr-2" />
  	      <input type="button" value="회원가입" onclick="location.href='join.alo';" class="btn btn-info mr-3" />
  	      <input type="checkbox" name="idSave" id="idSave" checked /> 아이디저장
  	    </td>
  	  </tr>
  	</table>
  </form>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>