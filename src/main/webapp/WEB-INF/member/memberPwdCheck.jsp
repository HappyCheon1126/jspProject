<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>memberPwdCheck.jsp</title>
  <jsp:include page="/include/bs4.jsp" />
  <script>
    'use strict';
    
    $(function() {
    	$("#pwdDemo").hide();
    });
    
    function pwdReCheck() {
    	let pwd = $("#pwd").val().trim();
    	if(pwd == "") {
    		alert("비밀번호를 입력하세요");
    		$("#pwd").focus();
    		return false;
    	}
    	
    	$.ajax({
    		url  : "memberPwdCheckAjax.mem",
    		type : "post",
    		data : {pwd : pwd},
    		success:function(res){
    			if(res == "1") $("#pwdDemo").show();
    			else alert("비밀번호가 틀립니다. 확인하세요");
    		},
    		error : function() {
    			alert('전송오류!');
    		}
    	});
    }
    
    function pwdReCheckOk() {
    	let pwdCheck = $("#pwdCheck").val();
    	let pwdCheckRe = $("#pwdCheckRe").val();
    	
    	if(pwdCheck == "" || pwdCheckRe == "") {
    		alert("변경할 비밀번호를 입력하세요");
    		$("#pwdCheck").focus();
    		return false;
    	}
    	else if(pwdCheck != pwdCheckRe) {
    		alert("새로 입력한 비밀번호가 서로 틀립니다. 확인하세요");
    		$("#pwdCheck").focus();
    		return false;
    	}
    	
    	$.ajax({
    		url  : "memberPwdChangeOk.mem",
    		type : "post",
    		data : {pwd : pwdCheck},
    		success:function(res) {
    			if(res == "1") {
    				alert("비밀번호가 변경되었습니다.\n다시 로그인 하세요.");
    				location.href = "memberLogout.mem";
    			}
    		},
    		error : function() {
    			alert("전송오류!");
    		}
    	});
    }
  </script>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<p><br/></p>
<div class="container">
  <form name="myform" method="post" action="memberPwdCheckOk.mem">
	  <table class="table table-bordered m-0">
		  <tr>
		    <td colspan="2" class="text-center">
		      <h3>비밀번호 확인</h3>
		      <div>(회원 정보 수정을 하기위해 기본 비밀번호를 확인합니다.)</div>
		    </td>
		  </tr>
		  <tr>
		    <th>비밀번호</th>
		    <td><input type="password" name="pwd" id="pwd" class="form-control" required /></td>
		  </tr>
		  <tr>
		    <td colspan="2" class="text-center">
		      <input type="button" value="비밀번호변경" onclick="pwdReCheck()" class="btn btn-success mr-2" />
		      <input type="submit" value="회원정보변경" class="btn btn-primary mr-2" />
		      <input type="reset" value="다시입력" class="btn btn-warning mr-2" />
		      <input type="button" value="돌아가기" onclick="location.href='memberMain.mem';" class="btn btn-info" />
		    </td>
		  </tr>
	  </table>
	</form>
	<hr/>
	<div id="pwdDemo">
	  <form>
		  <table class="table table-borderless">
		    <tr>
		      <td>변경할 비밀번호를 입력하세요.</td>
		      <td><input type="password" name="pwdCheck" id="pwdCheck" class="form-control"/></td>
		    </tr>
		    <tr>
		      <td>비밀번호 확인</td>
		      <td><input type="password" name="pwdCheckRe" id="pwdCheckRe" class="form-control"/></td>
		    </tr>
		    <tr>
		      <td colspan="2"><input type="button" value="비밀번호변경하기" onclick="pwdReCheckOk()" class=" btn btn-secondary form-control"/></td>
		    </tr>
		  </table>
	  </form>
	</div>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>