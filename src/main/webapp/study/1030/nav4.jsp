<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- nav4.jsp -->
<%@ include file="../../include/bs4.jsp" %>
<script>
  function logoutCheck() {
	  let ans = confirm("로그아웃 하시겠습니까?");
	  if(ans) {
		  location.href = "${pageContext.request.contextPath}/j1030/logout4?mid=${param.mid}";
	  }
  }
</script>
<div style="height:140px; text-align:center">
  <p><br/></p>
  <a href="main4.jsp?mid=${param.mid}&nickName=${param.nickName}" class="btn btn-outline-primary">Home</a> |
  <a href="main4.jsp?mid=${param.mid}&nickName=${param.nickName}&flag=guest" class="btn btn-outline-primary">Guest</a> |
  <a href="main4.jsp?mid=${param.mid}&nickName=${param.nickName}&flag=board" class="btn btn-outline-primary">Board</a> |
  <a href="main4.jsp?mid=${param.mid}&nickName=${param.nickName}&flag=pds" class="btn btn-outline-primary">Pds</a> |
  <a href="javascript:logoutCheck()" class="btn btn-outline-warning">Logout</a>
</div>