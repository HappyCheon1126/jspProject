<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/include/loginAjaxCheck.jsp" %>
<c:set var="ctp" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>memberMain.jsp</title>
  <jsp:include page="/include/bs4.jsp" />
  <script>
    'use strict';
    
    // 개별조회버튼 클릭시 demo에 입력폼을 보여주고 입력을 받을 수 있도록 처리한다.
    function searchCheck() {
    	let str = '';
    	str += '검색할 아이디 : ';
    	str += '<input type="text" name="mid" id="mid" value="admin" />';
    	str += '<input type="button" value="검색" onclick="memberSearch()" class="btn btn-success"/>';
    	
    	demo.innerHTML = str;
    }
    
    function memberSearch() {
    	let mid = document.getElementById("mid").value;
    	if(mid.trim() == "") {
    		alert("검색할 아이디를 입력하세요");
    		document.getElementById("mid").focus();
    		return;
    	}
    	//location.href = 'memberSearch.lo?mid='+mid;
    	
    	$.ajax({
    		url : "memberSearch.alo",
    		type : "post",
    		data : {mid:mid},
    		success:function(res) {
    			demo.innerHTML = res;
    		},
    		error  :function() {
    			alert("전송 실패");
    		}
    	});
    	
    }
    
    function logoutCheck() {
    	let ans = confirm("로그아웃 하시겠습니까?");
    	if(!ans) return false;
    	
    	$.ajax({
    		url  : "logout.alo",
    		type : "post",
    		success:function(res) {
    			alert('${sMid}' + "님 로그아웃 되셨습니다.");
    			location.href = 'login.alo';
    		}
    	});
    }
    
    // 회원 탈퇴처리
    function deleteCheck() {
    	let ans = confirm("정말 회원 탈퇴 하시겠습니까?");
    	if(!ans) return;
    	
    	let mid = "${sMid}";
    	
    	$.ajax({
    		url : "deleteOk.alo",
    		type : "post",
    		data : {mid : mid},
    		success : function(res) {
					if(res == "1") {
	    			alert(mid + "님이 회원 탈퇴되었습니다.");
						location.href = "login.alo";
					}
					else {
						alert("회원 탈퇴 실패");
					}
				},
				error : function() {
						alert("전송 오류");
				}
    	});
    }
    
    // 전체조회
    function listCheck() {
    	$.ajax({
    		url  : "memberList.alo",
    		type : "post",
    		success:function(res) {
    			let js = JSON.parse(res);
    			console.log("js", js);
    			
    			let str = '<table class="table table-hover">';
    			str += '<tr class="table-dark text-bark"><th>번호</th><th>아이디</th><th>성명</th><th>최종접속일</th><th>오늘방문카운트</th></tr>';
    			for(let j of js) {
    				str += '<tr>';
    				str += '<td>'+j.idx+'</td>';
    				str += '<td>'+j.mid+'</td>';
    				str += '<td>'+j.name+'</td>';
    				str += '<td>'+j.lastDate+'</td>';
    				str += '<td>'+j.todayCount+'</td>';
    				str += '</tr>';
    			}
    			str += '<tr><td colspan="5" class="m-0 p-0"></td></tr>';
    			str += '</table>';
    			
    			$("#demo").html(str);
    		},
    		error : function() {
    			alert("전송실패~~");
    		}
    	});
    }
  </script>
</head>
<body>
<jsp:include page="/include/header.jsp" />
<p><br/></p>
<div class="container text-center">
  <h2>회원 메인 화면</h2>
  <div>${sName}님 로그인 중이십니다.</div>
  <hr/>
  <p>현재 보유중인 포인트 : ${sPoint}</p>
  <p>최종 접속일 : ${fn:substring(sLastDate,0,16)}</p>
  <p>오늘 접속횟수 : ${sTodayCount}회</p>
  <hr/>
  <c:set var="random"><%=(int)(Math.random()*5)+1 %></c:set>
  <p><img src="${ctp}/images/${random}.jpg" width="300px"/></p>
  <hr/>
  <div class="row" style="font-size:10px">
    <div class="col"><a href="javascript:searchCheck()" class="btn btn-success">개별조회</a></div>
    <div class="col"><a href="javascript:listCheck()" class="btn btn-primary">전체조회</a></div>
    <div class="col"><a href="update.alo" class="btn btn-info">정보수정</a></div>
    <div class="col"><a href="javascript:logoutCheck()" class="btn btn-warning">로그아웃</a></div>
    <div class="col"><a href="javascript:deleteCheck()" class="btn btn-danger">회원탈퇴</a></div>
  </div>
  <hr/>
  <div id="demo"></div>
</div>
<p><br/></p>
<jsp:include page="/include/footer.jsp" />
</body>
</html>