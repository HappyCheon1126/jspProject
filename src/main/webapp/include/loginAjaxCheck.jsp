<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!-- memberCheck.jsp -->
<c:if test="${empty sMid}">
  <script>location.href="login.alo";</script>
</c:if>