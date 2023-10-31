<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  String flag = request.getParameter("flag")==null ? "" : request.getParameter("flag");
  // String mSw = request.getParameter("mSw")==null ? "" : request.getParameter("mSw");
  // String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
  // pageContext.setAttribute("mSw", mSw);
  // pageContext.setAttribute("mid", mid);
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>main3.jsp</title>
  <%-- <%@ include file = "../../include/bs4.jsp" %> --%>
  <%-- <jsp:include page="../../include/bs4.jsp" /> --%>
  <%-- <jsp:include page="${pageContext.request.contextPath}/include/bs4.jsp" />  (X) --%>
  <jsp:include page="/include/bs4.jsp" />	<!-- jsp액션태그 처음의 '/'은 ContextPath의 root명으로 간주된다. -->
  <script>
    'use strict';
    
    //if('${mSw}' == 'No' || '${mid}' == '') {
    if('${param.mSw}' == 'No' || ${empty param.nickName}) {
    	alert("회원정보를 확인해 보시고 다시 로그인 하세요.");
    	location.href = "login3.jsp";
    }
    else if('${param.mSw}' == 'Ok') {
      //alert("${mid}회원님~ 방문을 환영합니다.");
      alert("${param.nickName}회원님~ 방문을 환영합니다.");
    }
  </script>
</head>
<body>
<div class="container">
	<!-- 헤더영역(로고/메뉴/Top Image 등등...) -->
	<div id="header" class="text-center" style="background-color:skyblue">
	  <%@ include file = "nav3.jsp" %>
	  <div class="text-right">${param.mid}/${param.nickName}님 로그인 중입십니다.</div>
	</div>
	<p><br/></p>
	<div class="container" style="text-align:center">
<%  if(flag.equals("guest")) { %>
			<%@ include file="guest2.jsp" %>
<%  } else if(flag.equals("board")) { %>
			<%@ include file="board2.jsp" %>
<%  } else if(flag.equals("pds")) { %>
			<%@ include file="pds2.jsp" %>
<%  } else { %>
		  <h2>이곳은 메인 화면(Home) 입니다.</h2>
		  <hr/>
		  <p><img src="../../images/1.jpg" width="600px" /></p>
		  <hr/>
<%  } %>
	</div>
	<p><br/></p>
	<!-- 푸터영역(Copyright/Email Address/사업자등록증... Image 등등...) -->
	<div id="footer" class="text-center">
	  <%@ include file = "footer.jsp" %>
	</div>
</div>
</body>
</html>