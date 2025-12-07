<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method = "post">
		<label>Nhập vào username : </label><input name = "username" value = "${getUsername}"/><br>
		<label>Nhập vào password : </label><input name = "password" value = "${getPassword}"/><br>
		Rememeber me ? <input type = "checkbox" name = "remember" value = "true" ${getRemember}/><br>
		<button type = "submit">Đăng nhập</button>
	</form>
	<h1>${mess}</h1>
</body>