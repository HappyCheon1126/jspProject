<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>join.jsp</title>
  <jsp:include page="/include/bs4.jsp" />
  <script>
    'use strict';
    
    function joinCheck() {
    	let mid = $("#mid").val().trim();
    	let pwd = $("#pwd").val().trim();
    	let name = $("#name").val().trim();
    	
    	if(mid == "" || pwd == "" || name == "") {
    		alert("아이디/비밀번호/성명 은 필수 입력입니다.");
    		$("#mid").focus();
    		return false;
    	}
    	
    	let query = {
    			mid : mid,
    			pwd : pwd,
    			name: name
    	}
    	
    	$.ajax({
    		url  : "joinOk.alo",
    		type : "post",
    		data : query,
    		success:function(res) {
  				alert(res);
    			if(res.indexOf(mid) != -1) location.href = 'login.alo';
    		},
    		error : function() {
    			alert("전송 오류");
    		}
    	});
    }
  </script>
  <style>
    th {
      background-color: #eee;
      text-align: center;
    }
  </style>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<p><br/></p>
<div class="container text-center">
<form name="myform">
  <h2>회 원 가 입</h2>
  <table class="table table-bordered">
    <tr>
      <th>아이디</th>
      <td><input type="text" name="mid" id="mid" class="form-control" autofocus required /></td>
    </tr>
    <tr>
      <th>비밀번호</th>
      <td><input type="password" name="pwd" id="pwd" class="form-control" required/></td>
    </tr>
    <tr>
      <th>성명</th>
      <td><input type="text" name="name" id="name" class="form-control" required /></td>
    </tr>
    <tr>
      <td colspan="2">
        <input type="button" value="회원가입" onclick="joinCheck()" class="btn btn-success" />
        <input type="reset" value="다시입력" class="btn btn-warning" />
        <input type="button" value="돌아가기" onclick="location.href='login.alo';" class="btn btn-primary" />
      </td>
    </tr>
  </table>
</form>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>