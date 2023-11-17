<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>fileDownload.jsp</title>
  <jsp:include page="/include/bs4.jsp" />
  <script>
    'use strict';
    
    function fileDelCheck(fileName) {
    	//alert("flieName : " + fileName);
    	$.ajax({
    		url  : "tempFileDelete.st",
    		type : "post",
    		data : {fileName : fileName},
    		success:function(res) {
    			if(res == "1") {
	    			alert("삭제완료");
	    			location.reload();
    			}
    			else alert("삭제 실패!!");
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
  <h2>fileDownload.jsp(저장된 파일정보)</h2>
  <hr/>
  <c:forEach var="file" items="${files}" varStatus="st">
    <div>
      <a href="${ctp}/images/pdstest/${file}" download="${file}">${file}</a> :
      <input type="button" value="자바다운로드" onclick="location.href='javaFileDownload.st?file=${file}';" class="btn btn-secondary"/> :
      <c:set var="fNameArr" value="${fn:split(file,'.')}"/>
      <c:set var="exeName" value="${fn:toLowerCase(fNameArr[fn:length(fNameArr)-1])}"/>
      <c:if test="${exeName == 'zip'}">압축파일</c:if>
      <c:if test="${exeName == 'hwp'}">한글파일</c:if>
      <c:if test="${exeName == 'ppt' || exeName == 'pptx'}">파워포인트파일</c:if>
      <c:if test="${exeName == 'jpg' || exeName == 'git' || exeName == 'png'}">
      	<img src="${ctp}/images/pdstest/${file}" width="150px"/>
      </c:if> &nbsp;
      <input type="button" value="삭제" onclick="fileDelCheck('${file}')" class="btn btn-danger"/>
    </div>
    <hr/>
  </c:forEach>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>