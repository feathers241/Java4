<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width,initial-scale=1" />
  <title>Video Manager - Administration</title>
  <style>
    *{box-sizing:border-box}
    body{font-family:Arial,Helvetica,sans-serif;background:#fff;margin:0;color:#222}
  .admin-header{background: linear-gradient(#2b2b2b,#0b0b0b 40%, #000);color:#fff;padding:18px 0;box-shadow:0 6px 18px rgba(0,0,0,0.5)}
    .wrap{max-width:1200px;margin:0 auto;display:flex;align-items:center;justify-content:space-between;padding:0 20px}
    .brand{font-weight:800;color:#ffea00;font-size:28px}
    nav.admin-nav{display:flex;gap:20px}
    nav.admin-nav a{color:#8ad100;text-decoration:none;font-weight:700;padding:6px 8px}

.tabs {
  max-width: 1200px;
  margin: 18px auto;
  padding: 0 20px;
  display: flex;              /* ✅ cho tab nằm cùng 1 hàng */
  gap: 4px;                   /* khoảng cách giữa 2 tab */
  border-bottom: 1px solid #ddd;
}

.tab {
  padding: 8px 14px;
  border: 1px solid #e0e0e0;
  background: #f8f8f8;
  border-radius: 6px 6px 0 0;
  cursor: pointer;
  font-weight: 600;
  transition: background 0.2s;
}

.tab:hover {
  background: #fff;
}

.tab.active {
  background: #fff;
  border-bottom: 1px solid #fff;
  font-weight: 700;
}


  .panel{max-width:1200px;margin:0 auto;padding:18px;border-radius:6px;background:#fff;box-shadow:0 2px 8px rgba(0,0,0,0.08)}

  .editor{display:grid;grid-template-columns:300px 1fr;gap:16px}
    .poster-box{border:2px solid #e6a88a;padding:18px;display:flex;align-items:center;justify-content:center;height:200px;background:#fafafa}
    .field{margin-bottom:10px}
    label{display:block;font-weight:700;color:#c84b22;margin-bottom:6px}
    input[type=text],input[type=number],textarea{width:100%;padding:8px;border:2px solid #e6a88a;border-radius:4px}
    textarea{min-height:80px}

    .status-row{display:flex;gap:12px;align-items:center;margin:8px 0}
    .actions-row{margin-top:12px;background:#f3f3f3;padding:10px;border-radius:4px;display:flex;gap:12px;justify-content:flex-end}
    .btn{padding:8px 18px;border-radius:6px;border:none;cursor:pointer;font-weight:700}
    .btn.create{background:#d9534f;color:#fff}
    .btn.update{background:#f0ad4e;color:#fff}
    .btn.delete{background:#c9302c;color:#fff}
    .btn.reset{background:#6c757d;color:#fff}

    /* list */
    .list-panel{margin-top:18px}
    table{width:100%;border-collapse:collapse}
    th,td{padding:8px;border-bottom:1px solid #eee;text-align:left}
    th{background:#f9f9f9;color:#333}
    .edit-link{color:#2a6fb0;text-decoration:underline;cursor:pointer}

    .pager{display:flex;align-items:center;gap:8px;justify-content:flex-end;padding:8px 0}
    .pager .pgbtn{padding:6px 10px;border-radius:6px;border:1px solid #ccc;background:#e9e9e9;cursor:pointer}

    @media (max-width:800px){.editor{grid-template-columns:1fr}.brand{font-size:18px}}
  </style>
</head>
<body>

  <header class="admin-header">
    <div class="wrap">
      <div class="brand">Administration Tool</div>
      <nav class="admin-nav">
        <a href="indexAdmin?adminid=${adminid}">Home</a>
        <a href="#">Videos</a>
        <a href="UserManager?adminid=${adminid}">Users</a>
        <a href="ReportManager?adminid=${adminid}">Reports</a>
      </nav>
    </div>
  </header>

  <main>
    <div class="tabs">
      <div class="tab" data-target="edition">Video Edition</div>
      <div class="tab" data-target="list">Video List</div>
    </div>

    <div class="panel">
    <form method = "post">
      <div id="edition" class="tab-panel" style = "display:${EditorDisplay}">
        <div class="editor">
          <div>
            <div class="poster-box" id="posterBox" ><img src="${pageContext.request.contextPath}/Saved Pictures/${VideoPoster}" alt="${a.title}" width="100%" height="200"></div>
               <input type = "file" value ="${pageContext.request.contextPath}/Saved Pictures/${poster}" name = "PosterName">
               <input type = "hidden" value = "${poster}" name = "hiddenposter">
          </div>
          <div>
            <div class="field"><label for="ytid">Youtube Id?</label><input id="ytid" type="text" value = "${Youtubeid}" name = "Youtubeid"></div>
            <div class="field"><label for="title">Video Title?</label><input id="title" type="text" value = "${VideoTitle}" name = "VideoTitle"></div>
            <div class="field"><label for="views">View Count?</label><input id="views" type="number" value="${ViewCount}" name = "ViewCount"></div>
            <div class="status-row">
              <label style="margin:0">Status:</label>
              <label><input id="statusActive" name="Status" type="radio" value="true" ${Status ? 'checked' : ''}> Active</label>
              <label><input id="statusInactive" name="Status" type="radio" value="false" ${Status ? '' : 'checked'}> Inactive</label>
            </div>
            <div class="field"><label for="desc">Description?</label><textarea id="desc" name = "Description">${Description}</textarea></div>

            <div class="actions-row">
              <button class="btn create" id="btnCreate" formaction = "${pageContext.request.contextPath}/VideoManager/Create">Create</button>
              <button class="btn update" id="btnUpdate" formaction = "${pageContext.request.contextPath}/VideoManager/Update">Update</button>
              <button class="btn delete" id="btnDelete" formaction = "${pageContext.request.contextPath}/VideoManager/Delete">Delete</button>
              <button class="btn reset" id="btnReset" formaction = "${pageContext.request.contextPath}/VideoManager/Reset">Reset</button>
            </div>
          </div>
        </div>
      </div>
     </form>

      <div id="list" class="tab-panel"  style = "display:${ListDisplay}" >
        <div style="margin-bottom:8px;display:flex;justify-content:space-between;align-items:center">
          <div style="font-weight:700;color:#c84b22">Video List</div>
          <div style="color:#666;font-size:13px"><span id="totalCount">${VideoTotal }</span> videos</div>
        </div>

        <table id="videoTable">
          <thead>
            <tr>
	            <th>Youtube Id</th>
	            <th>Video Title</th><th>View Count</th>
	            <th>Status</th>
	            <th></th>
            </tr>
          </thead>
          <tbody>
          	<c:forEach items = "${VideoList}" var = "v">
          		 <tr>
		            <th><a href = "http://localhost:8080/Assignment/DetailFirst?id=${v.id}">${v.id}</a></th>
		            <th>${v.title}</th>
		            <th>${v.views}</th>
		            <th>${v.active ? 'Đang hoạt động' : 'Không hoạt động' }</th>
		            <th><a href = "${pageContext.request.contextPath}/VideoManager?videoid=${v.id}">Edit</a></th>
           		 </tr>
          	</c:forEach>
          </tbody>
        </table>

        <div class="pager">
          <a href = "${pageContext.request.contextPath}/VideoManager?page=1&display=block"><button class="pgbtn" id="first">|&lt;</button></a>
          <a href = "${pageContext.request.contextPath}/VideoManager?page=${page > 1 ? page-1 : 1}&display=block"><button class="pgbtn" id="prev">&lt;&lt;</button></a>
          
          <div id="pageInfo" style="padding:6px 10px;color:#666"></div>
          
          <a href = "${pageContext.request.contextPath}/VideoManager?page=${page < 1 ? 1 : page+1}&display=block"><button class="pgbtn" id="next">&gt;&gt;</button></a>
          <a href = "${pageContext.request.contextPath}/VideoManager?page=${last}&display=block"><button class="pgbtn" id="last">&gt;|</button></a>
        </div>
      </div>
    </div>
  </main>
  <script>
//Tab handling (match UserManager behavior)
  document.querySelectorAll('.tab').forEach(t => t.addEventListener('click', ()=>{
    document.querySelectorAll('.tab').forEach(x=>x.classList.remove('active'));
    t.classList.add('active');
    document.querySelectorAll('.tab-panel').forEach(p=>p.style.display='none');
    const target = t.dataset.target;
    const panel = document.getElementById(target);
    if(panel) panel.style.display = 'block';
    if(target === 'list') renderList();
    else window.scrollTo({top:0,behavior:'smooth'});
  }));
  // make first tab active on load   const _first = document.querySelector('.tab'); if(_first) _first.classList.add('active');
  </script>
</body>
</html>
