<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>다운로드 받을 파일 목록 보기</title>
</head>
<body>
	<%@ taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core" %>
	
	<div align="center">
		<h3>파일 목록</h3>
		<table>
			<tr>
				<th>파일이름</th>
			</tr>
			<c:forEach var="name" items="${list}">
				<tr>
					<td><a href ="download?filename=${name}">${name}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>