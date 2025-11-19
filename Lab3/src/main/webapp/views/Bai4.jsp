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
	<h3>${mess}</h3>
	<h2>Thông tin chia sẻ</h2><br>
	<table border="1" cellspacing="5" cellpadding="5">
    <tr>
        <th>Title</th>
        <th>Người thích</th>
        <th>Ngày thích</th>
    </tr>
    <c:forEach items="${list}" var="a">
        <tr>
            <td>${a[0]}</td>
            <td>${a[1]}</td>
            <td>${a[2]}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>