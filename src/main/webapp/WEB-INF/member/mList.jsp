<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>mList.jsp</title>
  <jsp:include page="/include/bs4.jsp" />
  <script>
    'use strict';
    
    function pageSizeCheck() {
    	let pageSize = $("#pageSize").val();
    	location.href = "mList.mem?pageSize="+pageSize;
    }
  </script>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<p><br/></p>
<div class="container">
  <h2 class="text-center">전체 회원 리스트</h2>
  <table class="table table-borderless m-0 p-0 text-right">
    <tr>
      <td>
        <div>
          <select name="pageSize" id="pageSize" onchange="pageSizeCheck()">
            <option value="3"  ${pageSize == 3? "selected" : ""}>3</option>
            <option value="5"  ${pageSize == 5 ? "selected" : ""}>5</option>
            <option value="10" ${pageSize == 10 ? "selected" : ""}>10</option>
            <option value="15" ${pageSize == 15 ? "selected" : ""}>15</option>
            <option value="20" ${pageSize == 20 ? "selected" : ""}>20</option>
          </select> 건
        </div>
      </td>
    </tr>
  </table>
  <table class="table table-hover text-center">
    <tr class="table-dark text-dark">
      <th>번호</th>
      <th>아이디</th>
      <th>닉네임</th>
      <th>성명</th>
      <th>성별</th>
      <th>공개여부</th>
      <th>오늘방문횟수</th>
      <th>회원등급</th>
    </tr>
    <c:forEach var="vo" items="${vos}" varStatus="st">
      <tr>
        <td>${curScrStartNo}</td>
        <td><a href="mInfor.mem?idx=${vo.idx}">${vo.mid}</a></td>
        <td>${vo.nickName}</td>
        <%-- 
        <td>
          <c:if test="${vo.userInfor == '공개'}">${vo.name}</c:if>
          <c:if test="${vo.userInfor != '공개'}">비공개</c:if>
        </td>
         --%>
        <c:if test="${vo.userInfor == '공개' || vo.mid == sMid || sLevel == 0}">
	        <td>${vo.name}</td>
	        <td>${vo.gender}</td>
	        <td>${vo.userInfor}</td>
	        <td>${vo.todayCnt}</td>
	        <td>
	          <c:choose>
	            <c:when test="${vo.level == 0}">관리자</c:when>
	            <c:when test="${vo.level == 1}">준회원</c:when>
	            <c:when test="${vo.level == 2}">정회원</c:when>
	            <c:when test="${vo.level == 3}">우수회원</c:when>
	            <c:otherwise></c:otherwise>
	          </c:choose>
	        </td>
        </c:if>
        <c:if test="${vo.userInfor != '공개' && vo.mid != sMid && sLevel != 0}">
          <td colspan="5">비공개</td>
        </c:if>
      </tr>
      <c:set var="curScrStartNo" value="${curScrStartNo - 1}"/>
    </c:forEach>
    <tr><td colspan="8" class="m-0 p-0"></td></tr>
  </table>
</div>

<!-- 블록페이지 시작(1블록의 크기를 3개(3Page)로 한다. 한페이지 기본은 5개 -->
<br/>
<div class="text-center">
  <ul class="pagination justify-content-center">
    <c:if test="${pag > 1}"><li class="page-item"><a class="page-link text-secondary" href="mList.mem?pag=1&pageSize=${pageSize}">첫페이지</a></li></c:if>
  	<c:if test="${curBlock > 0}"><li class="page-item"><a class="page-link text-secondary" href="mList.mem?pag=${(curBlock-1)*blockSize+1}&pageSize=${pageSize}">이전블록</a></li></c:if>
  	<c:forEach var="i" begin="${(curBlock*blockSize)+1}" end="${(curBlock*blockSize)+blockSize}" varStatus="st">
	    <c:if test="${i <= totPage && i == pag}"><li class="page-item active"><a class="page-link bg-secondary border-secondary" href="mList.mem?pag=${i}&pageSize=${pageSize}">${i}</a></li></c:if>
	    <c:if test="${i <= totPage && i != pag}"><li class="page-item"><a class="page-link text-secondary" href="mList.mem?pag=${i}&pageSize=${pageSize}">${i}</a></li></c:if>
  	</c:forEach>
  	<c:if test="${curBlock < lastBlock}"><li class="page-item"><a class="page-link text-secondary" href="mList.mem?pag=${(curBlock+1)*blockSize+1}&pageSize=${pageSize}">다음블록</a></li></c:if>
  	<c:if test="${pag < totPage}"><li class="page-item"><a class="page-link text-secondary" href="mList.mem?pag=${totPage}&pageSize=${pageSize}">마지막페이지</a></li></c:if>
  </ul>
</div>
<!-- 블록페이지 끝 -->
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>