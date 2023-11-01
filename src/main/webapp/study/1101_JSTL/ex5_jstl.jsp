<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>ex5_jstl.jsp</title>
  <jsp:include page="/include/bs4.jsp" />
  <script>
    'use strict';
    
    // 전체자료 검색
    function allList() {
    	location.href = "${ctp}/j1101/ex5_jstlOk?flag=all";
    }
    
    // 개별 자료 검색
    function nameCheck() {
    	let name = myform.name.value;
    	if(name.trim() == "") {
    		alert("검색할 성명을 입력하세요");
    		myform.name.focus();
    	}
    	else {
    		location.href = "${ctp}/j1101/ex5_jstlOk?flag=name&name="+name;
    	}
    }
    
    // 성별 검색
    function genderCheck() {
    	let gender = myform.gender.value;
    	if(gender == "") alert("성별을 선택해 주세요");
    	else location.href = "${ctp}/j1101/ex5_jstlOk?flag=gender&gender="+gender;
    }
  </script>
</head>
<body>
<p><br/></p>
<div class="container">
  <h2>회원 자료 출력하기</h2>
  <form name="myform" method="post">
  	<input type="button" value="회원전체리스트" onclick="allList()" class="btn btn-success" />
  	<hr/>
  	검색할 회원명을 입력하세요?
  	<div class="input-group">
	  	<input type="text" name="name" value="${param.name}" class="form-control" />
  	  <div class="input-group-append">
		  	<input type="button" value="개별회원검색" onclick="nameCheck()" class="btn btn-primary" />
	  	</div>
  	</div>
  	<hr/>
  	검색할 성별을 입력하세요?
  	<div class="form-check-inline">
	  	<input type="radio" name="gender" value="남자" <c:if test="${param.gender == '남자'}">checked</c:if> class="form-check-input" /> 남자
	  </div>
	  <div class="form-check-inline">
	  	<input type="radio" name="gender" value="여자" <c:if test="${param.gender == '여자'}">checked</c:if> class="form-check-input" /> 여자
	  </div>
  	<div class="form-check-inline">
  		<input type="button" value="성별검색" onclick="genderCheck()" class="btn btn-info" />
  	</div>
  </form>
  <hr/>
  <div id="demo" class="text-center">
    <c:if test="${fn:length(vos) != 0}">
		  <h3 class="text-center m-4">${title}</h3>
		  <table class="table table-hover  text-center">
		    <tr class="table-dark text-dark">
		      <th>성명</th>
		      <th>나이</th>
		      <th>성별</th>
		      <th>직업</th>
		      <th>주소</th>
		    </tr>
		    <c:forEach var="vo" items="${vos}" varStatus="st">
		    	<tr>
		    	  <td>${vo.name}</td>
		    	  <td>${vo.age}</td>
		    	  <td>${vo.gender}</td>
		    	  <td>${vo.job}</td>
		    	  <td>${vo.address}</td>
		    	</tr>
		    </c:forEach>
		    <tr><td colspan="5" class="m-0 p-0"></td></tr>
		  </table>
	  </c:if>
	  <c:if test="${!empty param.flag && fn:length(vos) == 0}">
	    <b>검색된 자료가 없습니다.</b>
	  </c:if>
  </div>
</div>
<p><br/></p>
</body>
</html>