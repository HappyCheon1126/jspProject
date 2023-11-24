<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>apiFetch.jsp</title>
  <jsp:include page="/include/bs4.jsp" />
  <script>
    'use strict';
    
    function fetchCheck1() {
    	fetch("https://infuser.odcloud.kr/oas/docs?namespace=15084592/v1")
    	.then(function(res){
    		return res.json();
    	})
    	.then(function(json){
    		console.log(json)
    	});
    }
    
    function fetchCheck2() {
    	fetch("https://jsonplaceholder.typicode.com/posts")
    	.then(function(res){
    		return res.json();
    	})
    	.then(function(json){
    		console.log(json)
    	});
    }
    
    function fetchCheck3() {
    	fetch("https://jsonplaceholder.typicode.com/posts")
    	.then(res => res.json())
    	.then(json => console.log(json));
    }
    
    async function fetchCheck4() {
    	let res = await fetch("https://jsonplaceholder.typicode.com/posts");
    	console.log("res",res);
    	let str = res.json();
    	console.log("str",str);
    	
    	/* 
    	let result = str.data.map((item, i) => [
    		(i+1) + "."
    		+ "유저아이디 : " + item.userId
    		+ "제목 : " + item.title
    		+ "<br/>"
    	]);
    	 */
    	$("#demo").html(str);
    }
  </script>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<p><br/></p>
<div class="container">
  <h2>Fetch API 연습</h2>
  <pre>
    Fetch : 자바스크립트를 이용한 비동기식 요청을 사용할 수 있게 하는 API
    fetch는 주로 Web의 API를 호출하고 응답데이터를 받아올수 있게 한다.
    방식 : fetch(URL)
  </pre>
  <h4>Fetch 를 사용한 JSON자료 호출</h4>
  <div>
    <input type="button" value="fetch연습1" onclick="fetchCheck1()" class="btn btn-success mr-3"/>
    <input type="button" value="fetch연습2" onclick="fetchCheck2()" class="btn btn-primary mr-3"/>
    <input type="button" value="fetch연습3" onclick="fetchCheck3()" class="btn btn-info mr-3"/>
    <input type="button" value="fetch연습4" onclick="fetchCheck4()" class="btn btn-warning mr-3"/>
    <input type="button" value="돌아가기" onclick="location.href='api1.st';" class="btn btn-warning"/>
  </div>
  <hr/>
  <div id="demo"></div>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>