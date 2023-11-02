<%@ page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>jstl7.jsp</title>
  <jsp:include page="/include/bs4.jsp" />
</head>
<body>
<p><br/></p>
<div class="container">
  <h2>Format 라이브러리(형식을 지정...)</h2>
  <pre>
    사용용도 : 형식문자열을 지정할때 사용함(쉼표, 화폐단위, 백분율...)
    사용법 : < fmt : 명령어... value="$ {값/변수}" pattern="표현패턴" type="화펴단위" />
  </pre>
  <c:set var="won1" value="7654321"/>
  <c:set var="won2" value="7654.321"/>
  <div>
    won1 : ${won1}<br/>
    won2 : ${won2}<br/><hr/>
    1.천단위 쉼표?<br/>
    <fmt:formatNumber value="1234567" /><br/>
    <fmt:formatNumber value="${won1}" /><br/>
    <fmt:formatNumber value="${won2}" /><br/>
    <fmt:formatNumber value="${won2}" pattern="0,000" /><br/>
    <fmt:formatNumber value="${won2}" pattern="0,000.0" /><br/>
    <fmt:formatNumber value="1234.567" pattern="0,000.0" /><br/>
    <fmt:formatNumber value="${1234.567}" pattern="0,000,000.0" /><br/>
    <fmt:formatNumber value="${1234.567}" pattern="#,##0.0" /><br/>
    <fmt:formatNumber value="${34.567}" pattern="#,##0.0" /><br/>
    <hr/>
    
    2.화페단위<br/>
    원화 : <fmt:formatNumber value="${won1}" type="currency" /><br/>
    US달러 : <fmt:formatNumber value="${won1}" type="currency" currencyCode="USD" /><br/>
    <hr/>
    
    3.백분율<br/>
    0.98765 : <fmt:formatNumber value="0.98765" type="percent" /><br/>
    0.98765 : <fmt:formatNumber value="0.98765" type="percent" pattern="0.0%" /><br/>
    <hr/>
    
    4.날짜<br/>
    오늘날짜1 : <%=new Date() %><br/>
    <c:set var="today" value="<%=new Date() %>"/><br/>
    오늘날짜2 : ${today} / <fmt:formatDate value="${today}"/><br/>
    오늘날짜3 : <fmt:formatDate value="${today}" pattern="yyyy-MM-dd" /><br/>
    오늘시간4 : <fmt:formatDate value="${today}" pattern="hh : mm : ss" /><br/>
    오늘시간5 : <fmt:formatDate value="${today}" pattern="yyyy년 MM월 dd일 hh시 mm분 ss초" /><br/>
    <hr/>
    
    5.국가별설정(로케일)<br/>
    톰캣서버의 기본 로케일 : <%=response.getLocale() %><br/>
    톰캣서버의 기본 로케일변경(미국식) : <fmt:setLocale value="en_US"/>
                                 <fmt:formatNumber value="${won1}" type="currency" /><br/>
    <hr/>
    
    6.URL이동 : location.href = '';  => redirect   : core라이브러리에 존재함<br/>
    <%-- <c:redirect url="jstl6.jsp" /> --%>
    
    7.import   : core라이브러리에 존재함<br/>
    <c:import url="/include/bs4.jsp" />
  </div>
</div>
<p><br/></p>
</body>
</html>