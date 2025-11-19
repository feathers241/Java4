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
		<label>Nhập vào keyword video :</label>
		<input name = "keyword"/>
		<button formaction = "${pageContext.request.contextPath}/Bai3/Keyword">Tìm kiếm</button>
	</form>
	<table border = "1" cellspacing = "5" cellpadding = "5">
		<tr>
			<th>ID</th>
			<th>title</th>
			<th>poster</th>
			<th>views</th>
			<th>description</th>
			<th>Trạng thái</th>
		</tr>
		<c:forEach items="${list}" var="a">
			<tr>
				<th>${a.id}</th>
				<th>${a.title}</th>
				<th><img src="${pageContext.request.contextPath}/Saved Pictures/${a.poster}" alt="${a.title}" width="100%" height="100"></th>
				<th>${a.views}</th>
				<th>${a.description}</th>
				<th>${a.active ? 'Đang hoạt động' : 'Không hoạt động'}</th>
			</tr>
		</c:forEach>
	</table>
	<h3>${mess}</h3>
</body>
</html>