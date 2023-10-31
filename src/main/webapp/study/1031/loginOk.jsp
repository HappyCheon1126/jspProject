<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- loginOk.jsp -->
<%
  String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
  String pwd = request.getParameter("pwd")==null ? "" : request.getParameter("pwd");
  String flag = mid.substring(0,1).toUpperCase();
%>
<jsp:include page="/include/bs4.jsp" />
<div class="container">
  <p><br/></p>
  <h2>회원정보(loginOk.jsp)</h2>
  <p>아이디 : <%=mid %></p>
  <p>비밀번호 : <%=pwd %></p>
  <p><a href="login.jsp" class="btn btn-success">돌아가기</a></p>
  
<%  if(flag.equals("I")) { %>
		  <jsp:forward page="loginResI.jsp">
		    <jsp:param value="<%=mid%>" name="mid"/>
		    <jsp:param value="<%=pwd%>" name="pwd"/>
		  </jsp:forward>
<%  } else if(flag.equals("C")) { %>
		  <jsp:forward page="loginResC.jsp">
		    <jsp:param value="<%=mid%>" name="mid"/>
		    <jsp:param value="<%=pwd%>" name="pwd"/>
		  </jsp:forward>
<%  } else { %>
		  <jsp:forward page="loginRes.jsp">
		    <jsp:param value="<%=mid%>" name="mid"/>
		    <jsp:param value="<%=pwd%>" name="pwd"/>
		  </jsp:forward>
<%  } %>
</div>