<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>t2_vo.jsp</title>
  <jsp:include page="/include/bs4.jsp" />
</head>
<body>
<p><br/></p>
<div class="container">
  <h2>성적자료</h2>
  <form method="post" action="<%=request.getContextPath()%>/j1031/t2_vo">
  	<p>성명 : <input type="text" name="name" value="홍길동" class="form-control"/></p>
  	<p>학번 : <input type="text" name="hakbun" value="A1234" class="form-control"/></p>
  	<p>국어 : <input type="text" name="kor" value="100" class="form-control"/></p>
  	<p>영어 : <input type="text" name="eng" value="90" class="form-control"/></p>
  	<p>수학 : <input type="text" name="mat" value="80" class="form-control"/></p>
  	<p>사회 : <input type="text" name="soc" value="70" class="form-control"/></p>
  	<p>과학 : <input type="text" name="sci" value="60" class="form-control"/></p>
  	<p>
  	  <input type="submit" value="전송" class="btn btn-success" />
  	  <input type="reset" value="취소" class="btn btn-warning" />
  	</p>
  </form>
</div>
<p><br/></p>
</body>
</html>