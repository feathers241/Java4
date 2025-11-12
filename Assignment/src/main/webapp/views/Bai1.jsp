<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head> 
<body>
	<c:url value = "${pagecontext.requets.contextPath}/Bai1" var = "url"/>
	<form method = "post">
		<label>Nhập vào username hoặc email :</label>
		<input name = "id">
		<button formaction = "${url}/findidemail">Tìm kiếm</button>
	</form>
	<table border = "1" cellspacing = "5" cellpadding = "5">
		<tr>
			<th>ID</th>
			<th>Password</th>
			<th>Fullname</th>
			<th>Email</th>
			<th>Vai trò</th>
		</tr>
		<c:forEach items="${list}" var="a">
			<tr>
				<th>${a.id}</th>
				<th>${a.password}</th>
				<th>${a.fullname}</th>
				<th>${a.email}</th>
				<th>${a.admin ? 'Admin' : 'User'}</th>
			</tr>
		</c:forEach>
	</table>
	<br><hr>
	<form method = "post">
		<label>Nhập vào keyword video :</label>
		<input name = "keyword"/>
		<button formaction = "${url}/findkeyword">Tìm kiếm</button>
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
		<c:forEach items="${video}" var="a">
			<tr>
				<th>${a.id}</th>
				<th>${a.title}</th>
				<th>${a.poster}</th>
				<th>${a.views}</th>
				<th>${a.description}</th>
				<th>${a.active ? 'Đang hoạt động' : 'Không hoạt động'}</th>
			</tr>
		</c:forEach>
	</table>
	<br><hr>
	<h2>10 Video được yêu thích nhất</h2><br>
	<table border = "1" cellspacing = "5" cellpadding = "5">
		<tr>
			<th>ID</th>
			<th>title</th>
			<th>poster</th>
			<th>views</th>
			<th>description</th>
			<th>Trạng thái</th>
		</tr>
		<c:forEach items="${like}" var="a">
			<tr>
				<th>${a.id}</th>
				<th>${a.title}</th>
				<th>${a.poster}</th>
				<th>${a.views}</th>
				<th>${a.description}</th>
				<th>${a.active ? 'Đang hoạt động' : 'Không hoạt động'}</th>
			</tr>
		</c:forEach>
	</table>
	<br><hr>
	<h2>Danh sách video không được yêu thích</h2><br>
	<table border = "1" cellspacing = "5" cellpadding = "5">
		<tr>
			<th>ID</th>
			<th>title</th>
			<th>poster</th>
			<th>views</th>
			<th>description</th>
			<th>Trạng thái</th>
		</tr>
		<c:forEach items="${notlike}" var="a">
			<tr>
				<th>${a.id}</th>
				<th>${a.title}</th>
				<th>${a.poster}</th>
				<th>${a.views}</th>
				<th>${a.description}</th>
				<th>${a.active ? 'Đang hoạt động' : 'Không hoạt động'}</th>
			</tr>
		</c:forEach>
	</table>
	<br><hr>
	<h2>Video được chia sẻ trong 2024</h2><br>
	<table border="1" cellspacing="5" cellpadding="5">
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Poster</th>
        <th>Views</th>
        <th>Description</th>
        <th>Ngày chia sẻ</th>
    </tr>
    <c:forEach items="${share2024}" var="a">
        <tr>
            <td>${a[0]}</td>
            <td>${a[1]}</td>
            <td>${a[2]}</td>
            <td>${a[3]}</td>
            <td>${a[4]}</td>
            <td>${a[5]}</td>
        </tr>
    </c:forEach>
</table>
<h2>Thông tin chia sẻ</h2><br>
	<table border="1" cellspacing="5" cellpadding="5">
    <tr>
        <th>Title</th>
        <th>Số lượng chia sẻ</th>
        <th>Ngày chia sẻ đầu tiên</th>
        <th>Ngày chia sẻ cuối cùng</th>
    </tr>
    <c:forEach items="${shareinfo}" var="a">
        <tr>
            <td>${a[0]}</td>
            <td>${a[1]}</td>
            <td>${a[2]}</td>
            <td>${a[3]}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>