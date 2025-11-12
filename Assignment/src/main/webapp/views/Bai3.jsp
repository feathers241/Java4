<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html> 
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method = "post">
		<label>Nhập vào id người dùng :</label><br>
		<input name = "id"><br>
		<button>Tìm kiếm</button>
	</form>
			<tr><h3>${mess}</h3></tr>
	<table border = "1" cellspacing = "5" cellpadding = "3">
		<c:forEach items = "${list}" var = "a">
			<tr>
				<th>${a.id}</th>
				<th>${a.title}</th>
			</tr>
		</c:forEach>
	</table>
</body>
</html> 