<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>t3_ApplicationMain.jsp</title>
  <jsp:include page="/include/bs4.jsp" />
  <style>
    div {margin: 20px;}
  </style>
</head>
<body>
<p><br/></p>
<div class="container">
  <h2>Application연습 메인메뉴</h2>
  <hr/>
  <form name="myform" method="post" action="t3_ApplicationSave.jsp">
    <div>아이디 :
      <input type="text" name="mid" value="${aMid}" autofocus class="form-control" />
    </div>
    <div>닉네임 :
      <input type="text" name="nickName" value="${aNickName}" class="form-control" />
    </div>
    <div>성명 :
      <input type="text" name="name" value="${aName}" class="form-control" />
    </div>
    <hr/>
	  <div class="row">
	    <div class="col"><button type="submit" class="btn btn-success form-control">어클리케이션저장</button></div>
	    <div class="col"><a href="t3_ApplicationCheck.jsp" class="btn btn-primary form-control">어플리케이션값확인</a></div>
	    <div class="col"><a href="t3_ApplicationNameCheck.jsp" class="btn btn-info form-control">전체 어플리케이션이름확인</a></div>
	    <div class="col"><a href="t3_ApplicationMidDelete.jsp" class="btn btn-info form-control">(어플리케이션)아이디 삭제</a></div>
	  </div>
  </form>
</div>
<p><br/></p>
</body>
</html>