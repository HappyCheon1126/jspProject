<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/include/loginCheck.jsp" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>memberMain.jsp</title>
  <jsp:include page="/include/bs4.jsp" />
  <script>
    'use strict';
    
    // 개별조회버튼 클릭시 demo에 입력폼을 보여주고 입력을 받을 수 있도록 처리한다.
    function searchCheck() {
    	let str = '';
    	str += '검색할 아이디 : ';
    	str += '<input type="text" name="mid" id="mid" value="admin" />';
    	str += '<input type="button" value="검색" onclick="memberSearch()" class="btn btn-success"/>';
    	
    	demo.innerHTML = str;
    }
    
    function memberSearch() {
    	let mid = document.getElementById("mid").value;
    	if(mid.trim() == "") {
    		alert("검색할 아이디를 입력하세요");
    	}
    	else {
    		location.href = 'memberSearch.lo?mid='+mid;
    	}
    }
    
    function logoutCheck() {
    	let ans = confirm("로그아웃 하시겠습니까?");
    	if(ans) location.href = "logout.lo";
    }
    
    // 회원 탈퇴처리
    function deleteCheck() {
    	let ans = confirm("정말 회원 탈퇴 하시겠습니까?");
    	if(ans) location.href = "deleteOk.lo";
    }
  </script>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<p><br/></p>
<div class="container text-center">
  <h2>회원 메인 화면</h2>
  <div>${sName}님 로그인 중이십니다.</div>
  <hr/>
  <p>현재 보유중인 포인트 : ${sPoint}</p>
  <p>최종 접속일 : ${fn:substring(sLastDate,0,16)}</p>
  <p>오늘 접속횟수 : ${sTodayCount}회</p>
  <hr/>
  <c:set var="random"><%=(int)(Math.random()*5)+1 %></c:set>
  <p><img src="${ctp}/images/${random}.jpg" width="300px"/></p>
  <hr/>
  <div class="row" style="font-size:10px">
    <div class="col"><a href="javascript:searchCheck()" class="btn btn-success">개별조회</a></div>
    <div class="col"><a href="memberList.lo" class="btn btn-primary">전체조회</a></div>
    <div class="col"><a href="update.lo" class="btn btn-info">정보수정</a></div>
    <div class="col"><a href="javascript:logoutCheck()" class="btn btn-warning">로그아웃</a></div>
    <div class="col"><a href="javascript:deleteCheck()" class="btn btn-danger">회원탈퇴</a></div>
  </div>
  <hr/>
  <div id="demo"></div>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>