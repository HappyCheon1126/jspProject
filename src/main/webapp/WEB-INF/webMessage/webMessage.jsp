<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>webMessage.jsp</title>
  <jsp:include page="/include/bs4.jsp" />
  <script>
    'use strict';
    
    function wmDeleteAll() {
    	let ans = confirm("휴지통을 모두 비우시겠습니까?");
    	if(!ans) return false;
    	
    	$.ajax({
    		url  : "wmDeleteAll.wm",
    		type : "post",
    		success:function(res) {
    			if(res == "1") {
    				alert("휴지통의 모든 메세지가 삭제 되었습니다.");
    				location.reload();
    			}
    			else {
    				alert("삭제 실패~~");
    			}
    		},
    		error : function() {
    			alert("전송실패~~");
    		}
    	});
    }
  </script>
  <style>
    #leftWindow {
      float : left;
      width : 25%;
      height: 500px;
      text-align: center;
      background-color: #ddd;
    }
    #rightWindow {
      float : left;
      width : 75%;
      height: 500px;
      text-align: left;
      background-color: #eee;
      overflow: auto;
    }
    #footerTopMargin {
      clear: both;
      margin: 10px;
    }
    h3 {text-align: center}
  </style>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<p><br/></p>
<div class="container">
  <h3 class="text-center">메세지 관리</h3>
  <div>(현재접속자:<font color='red'>${sMid}</font>)</div>
  <div id="leftWindow">
    <p><br/></p>
    <p><a href="wmMessage.wm?mSw=0">메세지작성</a></p>
    <p><a href="wmMessage.wm?mSw=1&mFlag=11">받은메세지(1)</a></p>
    <p><a href="wmMessage.wm?mSw=2">새메세지(2)</a></p>
    <p><a href="wmMessage.wm?mSw=3&mFlag=13">보낸메세지(3)</a></p>
    <p><a href="wmMessage.wm?mSw=4">수신확인(4)</a></p>
    <p><a href="wmMessage.wm?mSw=5&mFlag=15">휴지통(5)</a></p>
    <p><a href="javascript:wmDeleteAll()">휴지통비우기</a></p>
  </div>
  <div id="rightWindow">
    <p>
      <c:if test="${mSw == 0}">
        <h3>메세지 작성</h3>
        <jsp:include page="wmInput.jsp" />
      </c:if>
      <c:if test="${mSw == 1}">
        <h3>받은 메세지</h3>
        <jsp:include page="wmList.jsp" />
      </c:if>
      <c:if test="${mSw == 2}">
        <h3>신규 메세지</h3>
        <jsp:include page="wmList.jsp" />
      </c:if>
      <c:if test="${mSw == 3}">
        <h3>보낸 메세지</h3>
        <jsp:include page="wmList.jsp" />
      </c:if>
      <c:if test="${mSw == 4}">
        <h3>수신 확인</h3>
        <jsp:include page="wmList.jsp" />
      </c:if>
      <c:if test="${mSw == 5}">
        <h3>휴지통 목록</h3>
        <jsp:include page="wmList.jsp" />
      </c:if>
      <c:if test="${mSw == 6}">
        <h3>메세지 내용보기</h3>
        <jsp:include page="wmContent.jsp" />
      </c:if>
    </p>
  </div>
</div>
<br/>
<div id="footerTopMargin">&nbsp;</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>