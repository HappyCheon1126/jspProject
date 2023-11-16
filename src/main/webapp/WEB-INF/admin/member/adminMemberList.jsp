<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>adminMemberList.jsp</title>
  <jsp:include page="/include/bs4.jsp" />
  <script>
    'use strict';
    
    function levelChange(e) {
    	let ans = confirm("선택한 회원의 등급을 변경하시겠습니까?");
    	if(!ans) {
    		location.reload();
    		return false;
    	}
    	
    	let items = e.value.split("/");
    	let query = {
    			idx   : items[1],
    			level : items[0]
    	}
    	
    	$.ajax({
    		url  : "adminMemberLevelChange.ad",
    		type : "post",
    		data : query,
    		success:function(res) {
    			if(res != "0") {
    				alert("등급 수정 완료!");
    				location.reload();
    			}
    			else alert("등급 수정 실패~");
    		},
    		error : function() {
    			alert("전송 오류~");
    		}
    	});
    }
    
    // 레벨 검색
    function levelItemCheck() {
    	let level = $("#levelItem").val();
    	location.href = "adminMemberList.ad?level="+level;
    }
    
    // 탈퇴 신청회원 실제로 제거하기
    function memberDeleteOk(idx) {
    	let ans = confirm("선택된 회원을 삭제하시겠습니까?");
    	if(!ans) return false;
    	
    	$.ajax({
    		url  : "memberDelelteOk.mem",
    		type : "post",
    		data : {idx : idx},
    		success:function() {
    			alert("회원 삭제 완료!!");
    			location.reload();
    		},
    		error : function() {
    			alert("전송 오류~~");
    		}
    	});
    }
    
    // 페이지 사이즈 지정하기
    function pageSizeCheck() {
    	let pageSize = $("#pageSize").val();
    	location.href = "adminMemberList.ad?level=${level}&pageSize="+pageSize;
    }
  </script>
</head>
<body>
<p><br/></p>
<div class="container">
  <h2 class="text-center">${strLevel} 리스트</h2>
  <table class="table table-borderless m-0 p-0">
    <tr>
      <td>
        <div>등급별검색
          <select name="levelItem" id="levelItem" onchange="levelItemCheck()">
            <option value="99" ${level > 4 ? "selected" : ""}>전체검색</option>
            <option value="0"  ${level == 0 ? "selected" : ""}>관리자</option>
            <option value="1"  ${level == 1 ? "selected" : ""}>준회원</option>
            <option value="2"  ${level == 2 ? "selected" : ""}>정회원</option>
            <option value="3"  ${level == 3 ? "selected" : ""}>우수회원</option>
          </select>
        </div>
      </td>
      <td class="text-right">
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
      <th>공개여부</th>
      <th>오늘방문횟수</th>
      <th>탈퇴신청</th>
      <th>레벨</th>
    </tr>
    <c:forEach var="vo" items="${vos}" varStatus="st">
      <tr>
        <td>${curScrStartNo}</td>
        <td><a href="adminMemberInfor.ad?idx=${vo.idx}&pag=${pag}&pageSize=${pageSize}&level=${level}">${vo.mid}</a></td>
        <%-- <td><a href="adminMemberInfor.ad?idx=${vo.idx}">${vo.mid}</a></td> --%>
        <td>${vo.nickName}</td>
        <td>${vo.name}</td>
        <td>${vo.userInfor}</td>
        <td>${vo.todayCnt}</td>
        <td>
          <c:if test="${vo.userDel == 'OK'}"><font color="red"><b>탈퇴신청</b></font>
            <c:if test="${vo.deleteDiff >= 30}">(<a href="javascript:memberDeleteOk(${vo.idx})" title="30일경과">x</a>)</c:if>
          </c:if>
          <c:if test="${vo.userDel != 'OK'}">활동중</c:if>
        </td>
        <td>
          <form name="levelForm">
            <select name="level" onchange="levelChange(this)">
              <option value="0/${vo.idx}" ${vo.level==0 ? "selected" : ""}>관리자</option>
              <option value="1/${vo.idx}" ${vo.level==1 ? "selected" : ""}>준회원</option>
              <option value="2/${vo.idx}" ${vo.level==2 ? "selected" : ""}>정회원</option>
              <option value="3/${vo.idx}" ${vo.level==3 ? "selected" : ""}>우수회원</option>
            </select>
          </form>
        </td>
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
    <c:if test="${pag > 1}"><li class="page-item"><a class="page-link text-secondary" href="adminMemberList.ad?level=${level}&pag=1&pageSize=${pageSize}">첫페이지</a></li></c:if>
  	<c:if test="${curBlock > 0}"><li class="page-item"><a class="page-link text-secondary" href="adminMemberList.ad?level=${level}&pag=${(curBlock-1)*blockSize+1}&pageSize=${pageSize}">이전블록</a></li></c:if>
  	<c:forEach var="i" begin="${(curBlock*blockSize)+1}" end="${(curBlock*blockSize)+blockSize}" varStatus="st">
	    <c:if test="${i <= totPage && i == pag}"><li class="page-item active"><a class="page-link bg-secondary border-secondary" href="adminMemberList.ad?level=${level}&pag=${i}&pageSize=${pageSize}">${i}</a></li></c:if>
	    <c:if test="${i <= totPage && i != pag}"><li class="page-item"><a class="page-link text-secondary" href="adminMemberList.ad?level=${level}&pag=${i}&pageSize=${pageSize}">${i}</a></li></c:if>
  	</c:forEach>
  	<c:if test="${curBlock < lastBlock}"><li class="page-item"><a class="page-link text-secondary" href="adminMemberList.ad?level=${level}&pag=${(curBlock+1)*blockSize+1}&pageSize=${pageSize}">다음블록</a></li></c:if>
  	<c:if test="${pag < totPage}"><li class="page-item"><a class="page-link text-secondary" href="adminMemberList.ad?level=${level}&pag=${totPage}&pageSize=${pageSize}">마지막페이지</a></li></c:if>
  </ul>
</div>
<!-- 블록페이지 끝 -->
<p><br/></p>
</body>
</html>