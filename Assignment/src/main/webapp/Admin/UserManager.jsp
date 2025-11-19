<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width,initial-scale=1" />
  <title>User Manager</title>
  <style>
    :root{--muted:#777}
    *{box-sizing:border-box}
    body{font-family:Segoe UI,Roboto,Arial,sans-serif;background:#efefef;margin:0;color:#222}
    .admin-header{background: linear-gradient(#2b2b2b,#0b0b0b 40%, #000);color:#fff;padding:18px 0;box-shadow:0 6px 18px rgba(0,0,0,0.5)}
    .wrap{max-width:1200px;margin:0 auto;display:flex;align-items:center;justify-content:space-between;padding:0 20px}
    .brand{font-weight:800;color:#ffea00;font-size:28px}
    nav.admin-nav{display:flex;gap:20px}
    nav.admin-nav a{color:#8ad100;text-decoration:none;font-weight:700;padding:6px 8px}
    .container{max-width:1200px;margin:28px auto;padding:0 20px}
    .panel{background:#fff;border-radius:6px;padding:18px;box-shadow:0 2px 8px rgba(0,0,0,0.08);margin-bottom:20px}
    .tabs{display:flex;gap:6px;margin-bottom:12px}
    .tab{padding:8px 14px;border:1px solid #e0e0e0;background:#f8f8f8;border-radius:4px 4px 0 0;cursor:pointer}
    .tab.active{background:#fff;border-bottom:1px solid #fff;font-weight:700}
    .form-row{display:flex;gap:12px;margin-bottom:12px}
    .form-row .field{flex:1}
    label{display:block;font-size:13px;color:var(--muted);margin-bottom:6px}
    input[type=text], input[type=password], input[type=email], select{width:100%;padding:8px;border:1px solid #ddd;border-radius:4px}
    .actions{display:flex;gap:8px;justify-content:flex-end;margin-top:8px}
    button{padding:8px 14px;border-radius:6px;border:none;cursor:pointer}
    .btn-Create{background:#123;color:#fff}
    .btn-update{background:#d32f2f;color:#fff}
    .btn-delete{background:#888;color:#fff}
    table{width:100%;border-collapse:collapse;margin-top:8px}
    th,td{padding:10px;border-bottom:1px solid #eee;text-align:left}
    th{background:#fafafa;font-weight:700}
    .pager{display:flex;gap:8px;justify-content:center;margin-top:12px}
    .thumb{width:48px;height:32px;background:#f4f4f4;border-radius:4px;display:inline-flex;align-items:center;justify-content:center;color:#999}
    .small-link{color:#1976d2;cursor:pointer;text-decoration:underline}
    @media (max-width:900px){.form-row{flex-direction:column}}
  </style>
</head>
<body>
  <header class="admin-header">
    <div class="wrap">
      <div class="brand">Administration Tool</div>
      <nav class="admin-nav">
        <a href="indexAdmin?adminid=${adminid}">Home</a>
        <a href="VideoManager?adminid=${adminid}">Videos</a>
        <a href="CategoryManager?adminid=${adminid}">Category</a>
        <a href="#">Users</a>
        <a href="HistoryManager?adminid=${adminid}">History</a>
        <a href="ReportManager?adminid=${adminid}">Reports</a>
      </nav>
    </div>
  </header>

  <main class="container">
    <section class="panel">
      <div style="display:flex;justify-content:space-between;align-items:center">
        <div style="display:flex;align-items:center;gap:12px">
          <div class="tabs">
            <div class="tab active" data-target="edition">User Edition</div>
            <div class="tab" data-target="list">User List</div>
          </div>
        </div>
        <div style="color:#666;font-size:13px">Stored users: <span id="userCount">${UserTotal}</span></div>
      </div>

      <div id="edition" class="tab-panel" style = "display:${EditDisplay}">
        <form id="userForm" method = "post">
          <div class="form-row">
            <div class="field">
              <label>Username</label>
              <input id="username" type="text" required name = "id" value = "${id}"/>
            </div>
            <div class="field">
              <label>Password</label>
              <input id="password" type="password" name = "password" value = "${password}"/>
            </div>
          </div>

          <div class="form-row">
            <div class="field">
              <label>Fullname</label>
              <input id="fullname" type="text" name = "fullname" value = "${fullname}"/>
            </div>
            <div class="field">
              <label>Email Address</label>
              <input id="email" type="email" name = "email" value = "${email}" />
            </div>
          </div>

          <div class="form-row">
            <div class="field">
              <label>Role</label>
              <select id="role" name = "role">
                <option value = "true" ${isAdmin ? 'selected' : ''}>Admin</option>
                <option value = "false" ${isAdmin ? '' : 'selected'}>User</option>
              </select>
            </div>
            <div class="field"></div>
          </div>

          <div class="actions">
          	<button id="btnCreate" class="btn-Create" formaction = "${pageContext.request.contextPath}/UserManager/Create">Create</button>
            <button id="btnDelete" class="btn-delete" formaction = "${pageContext.request.contextPath}/UserManager/Delete">Delete</button>
            <button id="btnUpdate" class="btn-update" formaction = "${pageContext.request.contextPath}/UserManager/Update">Update</button>
            <button type="button" formaction = "${pageContext.request.contextPath}/UserManager/Reset">Reset</button>
          </div>
        </form>
      </div>
	
      <div id="list" class="tab-panel" style="display:${ListDisplay}">
        <table id="userTable">
          <thead>
            <tr>
	            <th>Username</th>
	            <th>Password</th>
	            <th>Fullname</th>
	            <th>Email</th>
	            <th>Role</th>
	            <th>Action</th>
            </tr>
          </thead>
          <tbody>
          <c:forEach items = "${UserList}" var = "u">
          <tr>
          		<td>${u.id}</td>
	            <td>${u.password}</td>
	            <td>${u.fullname}</td>
	            <td>${u.email}</td> 
	            <td>${u.admin ? 'Admin' : 'User'}</td>
	            <td><a href = "${pageContext.request.contextPath}/UserManager?userid=${u.id}">Edit</a></td>
			</tr>
          </c:forEach>
          </tbody>
        </table>

        <div class="pager">
          <a href = "${pageContext.request.contextPath}/UserManager?page=1&display=block"><button>|&lt;</button></a>
          <a href = "${pageContext.request.contextPath}/UserManager?page=${page > 1 ? page-1 : 1}&display=block"><button>&lt;&lt;</button></a>
          <a href = "${pageContext.request.contextPath}/UserManager?page=${page < 1 ? 1 : page+1}&display=block"><button>&gt;&gt;</button></a>
          <a href = "${pageContext.request.contextPath}/UserManager?page=${last}&display=block"><button>&gt;|</button></a>
        </div>
      </div>
    </section>
  </main>

  <script>

    // Tabs
    document.querySelectorAll('.tab').forEach(t=>t.addEventListener('click',()=>{
      document.querySelectorAll('.tab').forEach(x=>x.classList.remove('active'));
      t.classList.add('active');
      document.querySelectorAll('.tab-panel').forEach(p=>p.style.display='none');
      document.getElementById(t.dataset.target).style.display='block';
      if(t.dataset.target === 'list') renderList();
    }));
  </script>
</body>
</html>
