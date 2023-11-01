<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>ex4_jstl.jsp</title>
  <jsp:include page="/include/bs4.jsp" />
  <script>
    'use strict';
    
    let pictureArray = [];
    let pictureNameArray = [];
    
    function pictureChange() {
    	let img = myform.picture.value;
    	location.href = "ex4_jstl.jsp?img="+img;
    }
    
    function pictureCheck() {
    	let picture = myform.picture.value;
    	let pictureName = myform.pictureName.value.trim();
    	
    	if(pictureName == "") {
    		alert("그림 이름을 입력하세요!");
    		myform.pictureName.focus();
    	}
    	else {
    		pictureArray.push(picture);
    		pictureNameArray.push(pictureName);
    		alert("선택하신 그림을 저장하였습니다.");
    		myform.pictureName.value = "";
    	}
    }
    
    function pictureView() {
    	if(pictureArray.length == 0) {
    		alert("저장된 그림이 없습니다.");
    		return false;
    	}
    	
    	let str = '';
    	for(let i=0; i<pictureArray.length; i++) {
    		str += '<img src="${ctp}/images/'+pictureArray[i]+'" width="100px" />&nbsp;';
    		str += '<font color="blue"><b>'+pictureNameArray[i]+'</b></font>';
    		str += '<br/><br/>';
    	}
    	demo.innerHTML = str;
    }
    
    function pictureReset() {
    	// location.reload();
    	
    	pictureArray.splice(0,pictureArray.length);
    	pictureNameArray.splice(0,pictureNameArray.length);
    	demo.innerHTML = "";
    	alert("저장소의 그림을 모두 제거시켰습니다.");
    }
  </script>
</head>
<body>
<p><br/></p>
<div class="container">
  <pre>
    콤보상자에 숫자 1~5까지 기억시켜서 화면에 보여준다.
    이때 숫자를 선택하면 아래쪽(demo)으로 선택한 숫자의 그림파일을 출력시켜주시오.
    콤보상자옆에 텍스트박스를 만들어준후, 그곳에 아래 출력된 파일의 '이름'을 적어준후 '저장'버튼을 클릭하여 저장시켜준다.
    '전체그림출력'버튼을 만들어서 이 버튼을 클릭하면 지금까지 저장된 그림파일들을 모두 demo에 차례되로 출력시켜줄수 있도록 한다.
  </pre>
  <h2>그림 골라서 저장하여 출력하기</h2>
  <form name="myform">
    <div>그림고르기
      <c:set var="img" value="${param.img}"/>
      <select name="picture" onchange="pictureChange()">
        <c:forEach var="i" begin="1" end="5">
          <c:set var="tempImg" value="${i}.jpg"/>
        	<option <c:if test="${tempImg==img}">selected</c:if>>${i}.jpg</option>
        </c:forEach>
      </select>
    </div>
    <div>
      <c:if test="${empty img}"><img src="${ctp}/images/1.jpg" width="150px" /></c:if>
      <c:if test="${!empty img}"><img src="${ctp}/images/${img}" width="150px" /></c:if>
    </div>
    <hr/>
    <div>그림이름짓기 :
      <input type="text" name="pictureName" autofocus />
      <input type="button" value="그림정보저장" onclick="pictureCheck()" class="btn btn-success" />
      <input type="button" value="저장그림모두보기" onclick="pictureView()" class="btn btn-primary" />
      <input type="button" value="저장그림모두제거" onclick="pictureReset()" class="btn btn-danger" />
    </div>
  </form>
  <hr/>
  <div id="demo"></div>
</div>
<p><br/></p>
</body>
</html>