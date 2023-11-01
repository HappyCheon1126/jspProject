<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="sDan" value="${param.startDan}"/>
<c:set var="eDan" value="${param.endDan}"/>
<c:set var="su" value="${param.su}"/>
<c:if test="${sDan+0 > eDan}">
  <c:set var="temp" value="${sDan}"/>
  <c:set var="sDan" value="${eDan}"/>
  <c:set var="eDan" value="${temp}"/>
</c:if>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>ex3_jstl.jsp</title>
  <jsp:include page="/include/bs4.jsp" />
  <style>
    input {margin: 10px}
  </style>
</head>
<body>
<p><br/></p>
<div class="container">
  <h3>구구단 연습</h3>
  <form>
    <div>시작단 : <input type="number" name="startDan" value="${sDan}" autofocus class="form-control" /></div>
    <div>끝단 : <input type="number" name="endDan" value="${eDan}" autofocus class="form-control" /></div>
    <div>한줄에 출력될 개수 : <input type="number" name="su" value="${su}" autofocus class="form-control" /></div>
    <div><input type="submit" value="계산" class="btn btn-success"/></div>
  </form>
  <hr/>
  <div id="demo">
    <c:set var="cnt" value="0"/>
    <c:if test="${!empty sDan && !empty eDan}">
      <table class="table text-center">
        <tr>
		      <c:forEach var="i" begin="${sDan}" end="${eDan}">
			      <td>* ${i} 단 *<br/>
				    	<c:forEach var="j" begin="1" end="9">
					      ${i} * ${j} = ${i * j}<br/>
					    </c:forEach>
				    </td>
				    <c:set var="cnt" value="${cnt + 1}"/>
				    <c:if test="${cnt % su == 0}"></td></tr><tr></c:if>
			    </c:forEach>
		    </tr>
	    </table>
    </c:if>
  </div>
</div>
<p><br/></p>
</body>
</html>