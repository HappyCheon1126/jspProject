<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>jstl2.jsp</title>
  <jsp:include page="/include/bs4.jsp" />
</head>
<body>
<p><br/></p>
<div class="container">
  <h2>JSTL(Java Standard Tag Library)</h2>
  <h3>반복문(core라이브러리 사용... - forEach문)</h3>
  <pre>
    사용법1 : < c : forEach var="변수" begin="초기값" end="최종값" step="증감값" varStatus="매개변수" >
            < /c : forEach >
    사용법2 : < c : forEach var="변수" items=" $ {객체명/배열}" varStatus="매개변수" >
            < /c : forEach >
    사용법3 : < c : forTokens var="변수" items=" $ {객체명/배열}" delims="구분기호" >
            < /c : forEach >
  </pre>
  <p>사용법1 :<br/>
    <%-- <c:forEach var="i" begin="1" end="10" step="1">   step 1일경우는 생략가능 --%>
    <c:forEach var="i" begin="1" end="10">
      ${i} /
    </c:forEach>
  </p>
  <p>사용법2 :<br/>
<%
    String[] cards = {"국민","BC","현대","삼성","비자","LG","농협"};
    pageContext.setAttribute("cards", cards);
%>
    <c:forEach var="card" items="${cards}">
    	${card} /
    </c:forEach>
  </p>
  <p>사용법3 :<br/>
    <c:set var="hobbys" value="등산/낚시/수영/바둑/바이크/승마/독서" />
    취미 : ${hobbys}<br/>
    <c:forTokens var="hobby" items="${hobbys}" delims="/">
      ${hobby},
    </c:forTokens>
  </p>
  <hr/>
  <p><b>각종 사용예제</b><br/>
    <c:forEach var="card" items="${cards}" varStatus="st">
    	${st.index} / ${st.count}. ${card}<br/>
    </c:forEach>
    <br/><br/>
    -문제1: 모든카드를 출력하되, 국민카드는 파랑색, 삼성카드는 빨강색으로 출력하시오.<br/>
    <c:forEach var="card" items="${cards}">
    	<c:if test="${card == '국민'}"><font color="blue">${card},</font></c:if>
    	<c:if test="${card == '삼성'}"><font color="red">${card},</font></c:if>
    	<c:if test="${card != '국민' && card != '삼성'}">${card},</c:if>
    </c:forEach>
    <br/><br/>
    -문제2: 첫번째까드는 빨강색, 마지막카드는 파랑색으로 출력하시오.<br/>
    <c:forEach var="card" items="${cards}" varStatus="st">
    	<c:if test="${st.first}"><font color="red">${card},</font></c:if>
    	<c:if test="${st.last}"><font color="blue">${card},</font></c:if>
    </c:forEach>
    <br/><br/>
    -문제3: 모든카드를 출력하되, 첫번째까드는 빨강색, 마지막카드는 파랑색으로 출력하시오.<br/>
    <c:forEach var="card" items="${cards}" varStatus="st">
    	<c:if test="${st.first}"><font color="red">${card},</font></c:if>
    	<c:if test="${st.last}"><font color="blue">${card},</font></c:if>
    	<c:if test="${!st.first && !st.last}">${card},</c:if>
    </c:forEach>
    <br/><br/>
    <c:forEach var="card" items="${cards}" varStatus="st">
      <c:choose>
        <c:when test="${st.first}"><font color='red'>${card},</font></c:when>
        <c:when test="${st.last}"><font color='blue'>${card},</font></c:when>
        <c:otherwise>${card},</c:otherwise>
      </c:choose>
    </c:forEach>
    <br/><br/>
    -문제4: 구구단 연습...<br/>
    <h5>5단</h5>
    <c:forEach var="i" begin="1" end="9">
      5 * ${i} = ${5 * i}<br/>
    </c:forEach>
    <br/><br/>
    <h5>2~5단(이중For문)</h5>
    <c:forEach var="i" begin="2" end="5">
      * ${i} 단 *<br/>
    	<c:forEach var="j" begin="1" end="9">
	      ${i} * ${j} = ${i * j}<br/>
	    </c:forEach>
	    <br/>
    </c:forEach>
  </p>
  <hr/>
  <h3>5장의 그림 출력하기(1줄에 3개의 그림씩 출력?)</h3>
  <c:forEach var="i" begin="1" end="5" varStatus="st">
  	<img src="${pageContext.request.contextPath}/images/${i}.jpg" width="100px" />
    <c:if test="${st.count % 3 == 0}"><br/></c:if>
  </c:forEach>
  <hr/>
  <h3>2차원배열값처리</h3>
<%
  String[][] sinsang = {
  		{"홍길동","서울","23"},
  		{"김말숙","청주","19"},
  		{"이기자","인천","33"},
  		{"소나무","제주","29"},
  		{"오하늘","청주","42"}
  };
  pageContext.setAttribute("sinsang", sinsang);
%>
  <c:forEach var="sin" items="${sinsang}">
  	<c:forEach var="s" items="${sin}">
  		${s} /
  	</c:forEach>
  	<br/>
  </c:forEach>
</div>
<p><br/></p>
</body>
</html>