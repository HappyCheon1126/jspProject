<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- t3_ApplicationCheck.jsp -->
<jsp:include page="/include/bs4.jsp" />
<p><br/></p>
<div class="container">
<%
  request.setCharacterEncoding("utf-8");
  //response.setContentType("text/html; charset=utf-8");

  String mid = (String) application.getAttribute("aMid");
  String nickName = (String) application.getAttribute("aNickName");
  String name = (String) application.getAttribute("aName");
%>
  <h2>세션값 출력?</h2>
  <p>아이디 : <%=mid %></p>
  <p>닉네임 : <%=nickName %></p>
  <p>성명 : <%=name %></p>
  <hr/>
  <p><a href="t3_ApplicationMain.jsp" class="btn btn-success">돌아가기</a></p>
</div>