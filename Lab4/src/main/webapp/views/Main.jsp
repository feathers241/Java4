<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head> 
<meta charset="UTF-8">
<title>Giao diện</title>
<style>

</style>
</head>
<body>
	<form method = "post">
		ID:<br>
		<input name = "id" value = "${id}"><br>
		Fullname:<br>
		<input name = "fullname" value = "${fullname}"><br>
		Password:<br>
		<input name = "password" value = "${password}"><br>
		Email:<br>
		<input name = "email" value = "${email}"><br>
		Vai trò :
		<input type = "radio" name = "role" value = "true" ${role? 'checked' : ''}>Admin
		<input type = "radio" name = "role" value = "false" ${role? "":"checked"}>User
		<br>
		<button formaction = "${pageContext.request.contextPath}/Main/Create">Create</button>
		<button formaction = "${pageContext.request.contextPath}/Main/Update">Update</button>
		<button formaction = "${pageContext.request.contextPath}/Main/Delete">Delete</button>
		<button formaction = "${pageContext.request.contextPath}/Main/Reset">Reset</button>
	</form>
	${mess}
	<hr>
	<table border = "1" cellspacing = "5" cellpadding = "5">
		<tr style = "background-color: gray">
			<th>ID</th>
			<th>Fullname</th>
			<th>Email</th>
			<th>Vai trò</th>
			<th></th>
		</tr>
		<c:forEach items = "${list}" var = "a">
			<tr>
				<th>${a.id}</th>
				<th>${a.fullname}</th>
				<th>${a.email}</th>
				<th>${a.admin? 'Admin' : 'User'}</th>
				<th><a href = "${pageContext.request.contextPath}/Main/Edit?edit=${a.id}">Edit</a></th>
			</tr>
		</c:forEach>
		
	</table>
</body>
</html>