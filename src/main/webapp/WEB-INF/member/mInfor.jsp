<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>mInfor.jsp</title>
  <jsp:include page="/include/bs4.jsp" />
</head>
<body>
<jsp:include page="/include/header.jsp" />
<p><br/></p>
<div class="container">
  <h2>${vo.mid}회원 상세 정보 보기</h2>
  <br/>
  <table class="table">
    <tr><td>아이디 : ${vo.mid}</td></tr>
    <tr><td>별명 : ${vo.nickName}</td></tr>
    <tr><td>성명 : ${vo.name}</td></tr>
    <tr><td>생일 : ${fn:substring(vo.birthday,0,10)}</td></tr>
    <tr><td>전화번호 : ${vo.tel}</td></tr>
    <tr><td>주소 : ${vo.address}</td></tr>
    <tr><td>이메일 : ${vo.email}</td></tr>
    <tr><td>홈페이지 : ${vo.homePage}</td></tr>
    <tr><td>직업 : ${vo.job}</td></tr>
    <tr><td>취미 : ${vo.hobby}</td></tr>
    <tr><td>사진 : <img src="${ctp}/images/member/${vo.photo}" width="150px"/></td></tr>
    <tr><td>자기소개 : ${vo.content}</td></tr>
    <tr><td>정보공개유무 : ${vo.userInfor}</td></tr>
    <tr><td>탈퇴신청여부 : 
          <c:if test="${vo.userDel == 'OK'}"><font color="red"><b>탈퇴신청</b></font>
	          <c:if test="${vo.deleteDiff >= 30}">(<a href="javascript:memberDeleteOk(${vo.idx})" title="30일경과">x</a>)</c:if>
          </c:if>
          <c:if test="${vo.userDel != 'OK'}">활동중</c:if>
    </td></tr>
    <tr><td>포인트 : ${vo.point}</td></tr>
    <tr><td>회원등급 : ${strLevel}</td></tr>
    <tr><td>총방문횟수 : ${vo.visitCnt}</td></tr>
    <tr><td>최초가입일 : ${fn:substring(vo.startDate,0,10)}</td></tr>
    <tr><td>최종접속일 : ${fn:substring(vo.lastDate,0,10)}</td></tr>
    <tr><td>오늘접속횟수 : ${vo.todayCnt}</td></tr>
  </table>
  <div><a href="javascript:history.back();" class="btn btn-warning">돌아가기</a></div>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>