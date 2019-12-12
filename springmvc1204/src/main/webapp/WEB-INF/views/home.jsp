<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- jstl의 core 기능을 사용하기 위한 태그 라이브러리 설정 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- session을 사용할 수 없도록 하는 설정 -->
<%@ page session="false" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Home</title>
</head>
<body>
	<a href = "json">JSON</a><br/>
	<a href = "header">JSON</a><br/>
	
	<a href = "first">첫번째 요청</a><br/>
<!--  	<img src="idol05.png" /><br/>
  	 	<img src="https://file.mk.co.kr/meet/neds/2019/07/image_readtop_2019_573268_15642684623837739.jpg"/><br/>
 -->	
 	<input type="button" value="사용자 요청" id="btn" /><br/>
 	<a href="detail/123">제목</a><br>
 	
 	<form action="login" method="post">
 		아이디:<input typr="text" required="required"
 		name="id" /><br/>
 		비밀번호:<input type="password" required="required"
 		name="pw" /><br/>
 		<input type="submit" value="로그인" />
 	</form>
 	
</body>

<script>
	document.getElementById("btn").addEventListener(
			"click", function(e){		
		location.href ="event"
	});
</script>

</html>
