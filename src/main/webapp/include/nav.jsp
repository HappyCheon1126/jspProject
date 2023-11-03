<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	<!-- 햄버거버튼 -->
  <!-- <a class="navbar-brand" href="http://localhost:9090/javaProject">Home</a> -->
  <a class="navbar-brand" href="http://192.168.50.20:9090/javaProject">Home</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="collapsibleNavbar">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="${ctp}/GuestList">Guest</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="${ctp}/BoardList">Board</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/PdsList">Pds</a>
      </li>    
      <li class="nav-item ml-2">
        <div class="dropdown">
			    <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown">Study1</button>
			    <div class="dropdown-menu">
			      <a class="dropdown-item" href="${ctp}/study/password/passForm.jsp">암호화연습</a>
			      <a class="dropdown-item" href="#">Link 2</a>
			      <a class="dropdown-item" href="#">Link 3</a>
			    </div>
			  </div>
      </li>    
    </ul>
  </div>  
</nav>