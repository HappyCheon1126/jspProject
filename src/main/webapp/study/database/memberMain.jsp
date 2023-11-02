<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    
    function searchCheck() {
    	let str = '';
    	str += '검색할 아이디 : ';
    	str += '';
    	str += '';
    	
    	demo.innerHTML = str;
    }
  </script>
</head>
<body>
<p><br/></p>
<div class="container text-center">
  <h2>회원 메일 화면</h2>
  <div>${sName}님 로그인 중이십니다.</div>
  <hr/>
  
  <p>현재 보유중인 포인트 : ${sPoint}</p>
  <p>최종 접속일 : ${sLastDate}</p>
  <p>오늘 접속횟수 : ${sTodayCount}회</p>
  <hr/>
  <c:set var="random"><%=(int)(Math.random()*5)+1 %></c:set>
  <p><img src="${ctp}/images/${random}.jpg" width="300px"/></p>
  <hr/>
  <div class="row">
    <div class="col"></div>
    <div class="col"><a href="javascript:searchCheck()" class="btn btn-success">개별조회</a></div>
    <div class="col"><a href="${ctp}/database/memberList" class="btn btn-primary">전체조회</a></div>
    <div class="col"><a href="${ctp}/database/memberLogout" class="btn btn-danger">로그아웃</a></div>
    <div class="col"></div>
  </div>
  <hr/>
  <div id="demo"></div>
</div>
<p><br/></p>
</body>
</html>