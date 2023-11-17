<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>fileUpload2.jsp</title>
  <jsp:include page="/include/bs4.jsp" />
  <script>
    'use strict';
    
    function fCheck() {
    	let fName1 = document.getElementById("file1").value;
    	let fName2 = document.getElementById("file2").value;
    	let fName3 = document.getElementById("file3").value;
    	let ext1 = fName1.substring(fName1.lastIndexOf(".")+1).toLowerCase();
    	let ext2 = fName2.substring(fName2.lastIndexOf(".")+1).toLowerCase();
    	let ext3 = fName3.substring(fName3.lastIndexOf(".")+1).toLowerCase();
    	let maxSize = 1024 * 1024 * 10;   // 1KByte=1024Byte=10^3Byte=2^10Byte, 1MByte=2^20Byte=10^6Byte, 1GByte=2^30Byte=10^9Byte, 1TByte=2^40Byte=10^12Byte)
    	
    	if(fName1.trim() == "" || fName2.trim() == "" || fName3.trim() == "") {
    		alert("업로드할 파일을 선택하세요!");
    		return false;
    	}
    	
    	let fileSize1 = document.getElementById("file1").files[0].size;
    	let fileSize2 = document.getElementById("file2").files[0].size;
    	let fileSize3 = document.getElementById("file3").files[0].size;
    	let fileSize = fileSize1 + fileSize2 + fileSize3;
    	if(ext1 != 'jpg' && ext1 != 'gif' && ext1 != 'png' && ext1 != 'zip' && ext1 != 'hwp' && ext1 != 'ppt' && ext != 'pptx' && ext != 'xlsx') {
    		alert("업로드 가능한 파일은 'jgp/gif/png/zip/hwp/ppt/pptx/xlsx' 만 가능합니다.");
    	}
    	else if(ext2 != 'jpg' && ext2 != 'gif' && ext2 != 'png' && ext2 != 'zip' && ext2 != 'hwp' && ext2 != 'ppt' && ext != 'pptx' && ext != 'xlsx') {
    		alert("업로드 가능한 파일은 'jgp/gif/png/zip/hwp/ppt/pptx/xlsx' 만 가능합니다.");
    	}
    	else if(ext3 != 'jpg' && ext3 != 'gif' && ext3 != 'png' && ext3 != 'zip' && ext3 != 'hwp' && ext3 != 'ppt' && ext != 'pptx' && ext != 'xlsx') {
    		alert("업로드 가능한 파일은 'jgp/gif/png/zip/hwp/ppt/pptx/xlsx' 만 가능합니다.");
    	}
    	else if(fileSize > maxSize) {
    		alert("업로드할 파일의 최대용량은 10MByte입니다.");
    	}
    	else {
    		myform.submit();
    	}
    	
    }
  </script>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<p><br/></p>
<div class="container">
  <h2>파일 업로드 연습(싱글파일처리)</h2>
  <p>COS라이브러리를 이용한 파일 업로드</p>
  <div>(http://www.servlets.com/cos/)</div>
  <hr/>
  <form name="myform" method="post" action="fileUpload2Ok.st" enctype="multipart/form-data">
    파일명 :
    <input type="file" name="fName1" id='file1' class="form-control-file mb-2 border"/>
    <input type="file" name="fName2" id='file2' class="form-control-file mb-2 border"/>
    <input type="file" name="fName3" id='file3' class="form-control-file mb-2 border"/>
    <input type="button" value="전송" onclick="fCheck()" class="btn btn-success form-control"/>
  </form>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>