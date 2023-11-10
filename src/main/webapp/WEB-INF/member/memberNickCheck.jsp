<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>memberNickCheck.jsp</title>
  <jsp:include page="/include/bs4.jsp" />
  <script>
    'use strict';
    
    // 사용가능한 아이드를 부모창으로 전송후 창닫기
    function sendCheck() {
    	opener.window.document.myform.nickName.value = '${nickName}';
    	opener.window.document.myform.name.focus();
    	window.close();
    }
    
    // 닉네임 재검색
    function nickNameCheck() {
    	let nickName = childForm.nickName.value;
    	
    	if(nickName.trim() == "") {
    		alert("닉네임을 입력하세요");
    		childForm.nickName.focus();
    	}
    	else {
    		childForm.submit();
    	}
    }
  </script>
</head>
<body>
<p><br/></p>
<div class="container">
  <h3>닉네임 체크폼</h3>
  <c:if test="${res == 1}">
    <h4><font color="blue"><b>${nickName}</b></font>닉네임은 사용 가능합니다.</h4>
    <p><input type="button" value="창닫기" onclick="sendCheck()"/>
  </c:if>
  <c:if test="${res != 1}">
    <h4><font color="blue"><b>${nickName}</b></font>닉네임은 이미 사용중인 아이디입니다.</h4>
    <form name="childForm" method="post" action="memberNickCheck.mem">
      <p>
        <input type="text" name="nickName">
        <input type="button" value="닉네임재검색" onclick="nickNameCheck()"/>
      </p>
    </form>
  </c:if>
</div>
<p><br/></p>
</body>
</html>