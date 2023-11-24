<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>wmList.jsp</title>
  <jsp:include page="/include/bs4.jsp" />
  <script>
    'use strict';
    
    setTimeout("location.reload()", 1000*15);
    
    function msgDel(idx) {
    	let ans = confirm("선택하신 메세지를 삭제하시겠습니까?");
    	if(!ans) return false;
    	
    	let query = {
    			idx  : idx,
    			mFlag: 13
    	}
    	
    	$.ajax({
    		url  : "wmDeleteOne.wm",
    		type : "post",
    		data : query,
    		success:function(res) {
    			if(res == "1") {
    				alert("메세지가 삭제 되었습니다.");
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
</head>
<body>
<p><br/></p>
<div class="container">
  <table class="table table-hover">
    <tr class="table-dark text-dark">
	    <th>번호</th>
	    <th>  <!-- 1:받은메세지, 2:새메세지, 3:보낸메세지, 4:수신확인, 5:휴지통, 6:상세보기 -->
	      <c:if test="${mSw==1 || mSw==2 || mSw==5}">보낸사람</c:if>
	      <c:if test="${mSw==3 || mSw==4}">받은사람</c:if>
	    </th>
	    <th>제목</th>
	    <th>
	      <c:if test="${mSw==1 || mSw==2 || mSw==5}">보낸날짜</c:if>
	      <c:if test="${mSw==3 || mSw==4}">받은사람</c:if>
	    </th>
    </tr>
    <c:forEach var="vo" items="${vos}" varStatus="st">
      <tr>
        <td>${vo.idx}</td>
        <td>
          <c:if test="${mSw==1 || mSw==2 || mSw==5}">${vo.sendId}</c:if>
	      	<c:if test="${mSw==3 || mSw==4}">${vo.receiveId}</c:if>
        </td>
        <td>
          <c:if test="${mSw!=4}"><a href="wmMessage.wm?mSw=6&idx=${vo.idx}&mFlag=${mFlag}">${vo.title}</a></c:if>
          <c:if test="${mSw==4}">${vo.title}</c:if>
          <c:if test="${vo.receiveSw=='n'}"><img src="${ctp}/images/new.gif"/></c:if>
          <c:if test="${mSw==3}">
            <a href="javascript:msgDel(${vo.idx})" class="badge badge-danger">삭제</a>
          </c:if>
        </td>
        <td>
          <c:if test="${vo.hour_diff < 24}">${fn:substring(vo.receiveDate,11,19)}</c:if>
          <c:if test="${vo.hour_diff >= 24}">${fn:substring(vo.receiveDate,0,16)}</c:if>
        </td>
      </tr>
      <tr><td colspan="4" class="m-0 p-0"></td></tr>
    </c:forEach>
  </table>
</div>
<!-- 블록페이지 시작(1블록의 크기를 3개(3Page)로 한다. 한페이지 기본은 5개 -->
<br/>
<div class="text-center">
  <ul class="pagination justify-content-center pagination-sm">
    <c:if test="${totPage == 0}"><b>자료가 없습니다.</b></c:if>
    <c:if test="${totPage != 0}">
	    <c:if test="${pag > 1}"><li class="page-item"><a class="page-link text-secondary" href="wmMessage.wm?mSw=${mSw}&pag=1&pageSize=${pageSize}">첫페이지</a></li></c:if>
	  	<c:if test="${curBlock > 0}"><li class="page-item"><a class="page-link text-secondary" href="wmMessage.wm?mSw=${mSw}&pag=${(curBlock-1)*blockSize+1}&pageSize=${pageSize}">이전블록</a></li></c:if>
	  	<c:forEach var="i" begin="${(curBlock*blockSize)+1}" end="${(curBlock*blockSize)+blockSize}" varStatus="st">
		    <c:if test="${i <= totPage && i == pag}"><li class="page-item active"><a class="page-link bg-secondary border-secondary" href="wmMessage.wm?mSw=${mSw}&pag=${i}&pageSize=${pageSize}">${i}</a></li></c:if>
		    <c:if test="${i <= totPage && i != pag}"><li class="page-item"><a class="page-link text-secondary" href="wmMessage.wm?mSw=${mSw}&pag=${i}&pageSize=${pageSize}">${i}</a></li></c:if>
	  	</c:forEach>
	  	<c:if test="${curBlock < lastBlock}"><li class="page-item"><a class="page-link text-secondary" href="wmMessage.wm?mSw=${mSw}&pag=${(curBlock+1)*blockSize+1}&pageSize=${pageSize}">다음블록</a></li></c:if>
	  	<c:if test="${pag < totPage}"><li class="page-item"><a class="page-link text-secondary" href="wmMessage.wm?mSw=${mSw}&pag=${totPage}&pageSize=${pageSize}">마지막페이지</a></li></c:if>
  	</c:if>
  </ul>
</div>
<!-- 블록페이지 끝 -->
<p><br/></p>
</body>
</html>