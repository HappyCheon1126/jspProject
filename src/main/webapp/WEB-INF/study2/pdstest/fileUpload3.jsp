<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>fileUpload3.jsp</title>
  <jsp:include page="/include/bs4.jsp" />
  <script>
    'use strict';
    
    function fCheck() {
    	let fName1 = document.getElementById("file1").value;
    	let ext = fName1.substring(fName1.lastIndexOf(".")+1).toLowerCase();
    	let maxSize = 1024 * 1024 * 10;   // 1KByte=1024Byte=10^3Byte=2^10Byte, 1MByte=2^20Byte=10^6Byte, 1GByte=2^30Byte=10^9Byte, 1TByte=2^40Byte=10^12Byte)
    	
    	if(fName1.trim() == "") {
    		alert("업로드할 파일을 선택하세요!");
    		return false;
    	}
    	
    	let fileSize = document.getElementById("file1").files[0].size;
    	
    	if(ext != 'jpg' && ext != 'gif' && ext != 'png' && ext != 'zip' && ext != 'hwp' && ext != 'ppt' && ext != 'pptx' && ext != 'xlsx') {
    		alert("업로드 가능한 파일은 'jgp/gif/png/zip/hwp/ppt/pptx/xlsx' 만 가능합니다.");
    	}
    	else if(fileSize > maxSize) {
    		alert("업로드할 파일의 최대용량은 10MByte입니다.");
    	}
    	else {
    		myform.submit();
    	}
    	
    }
    
    let cnt = 1;
    // 파일박스 추가하기
    function fileBoxAppend() {
    	cnt++;
    	let fileBox = '';
    	fileBox += '<div id="fBox'+cnt+'">';
    	fileBox += '<input type="file" name="fName'+cnt+'" id="file'+cnt+'" class="form-control-file mb-2 border mb-2" style="float:left; width:85%;"/>';
    	fileBox += '<input type="button" value="삭제" onclick="deleteBox('+cnt+')" class="btn btn-danger form-control mb-2 ml-2" style="width:10%;"/>';
    	fileBox += '</div>';
    	$("#fileBox").append(fileBox);
    }
    
    // 파일 박스 삭제
    function deleteBox(cnt) {
    	$("#fBox"+cnt).remove();
    }
  </script>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<p><br/></p>
<div class="container">
  <h2>파일 업로드 연습(멀티파일처리)</h2>
  <p>COS라이브러리를 이용한 파일 업로드</p>
  <div>(http://www.servlets.com/cos/)</div>
  <hr/>
  <form name="myform" method="post" action="fileUpload3Ok.st" enctype="multipart/form-data">
    파일명 :
    <div>
      <input type="button" value="파일박스추가" onclick="fileBoxAppend()" class="btn btn-primary mb-2"/>
	    <input type="file" name="fName1" id='file1' class="form-control-file mb-2 border"/>
    </div>
	  <div id="fileBox"></div>
    <input type="button" value="전송" onclick="fCheck()" class="btn btn-success form-control"/>
  </form>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>