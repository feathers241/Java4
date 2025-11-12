<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        <a href="index.html">Home</a>
        <a href="VideoManager.html">Videos</a>
        <a href="UserManager.html">Users</a>
        <a href="ReportManager.html">Reports</a>
      </nav>
    </div>
  </header>

  <main>
    <div class="tabs">
      <div class="tab" data-target="edition">Video Edition</div>
      <div class="tab" data-target="list">Video List</div>
    </div>

    <div class="panel">
      <div id="edition" class="tab-panel">
        <div class="editor">
          <div>
            <div class="poster-box" id="posterBox">POSTER</div>
          </div>
          <div>
            <div class="field"><label for="ytid">Youtube Id?</label><input id="ytid" type="text"></div>
            <div class="field"><label for="title">Video Title?</label><input id="title" type="text"></div>
            <div class="field"><label for="views">View Count?</label><input id="views" type="number" value="0"></div>
            <div class="status-row">
              <label style="margin:0">Status:</label>
              <label><input id="statusActive" name="status" type="radio" value="active" checked> Active</label>
              <label><input id="statusInactive" name="status" type="radio" value="inactive"> Inactive</label>
            </div>
            <div class="field"><label for="desc">Description?</label><textarea id="desc"></textarea></div>

            <div class="actions-row">
              <button class="btn create" id="btnCreate">Create</button>
              <button class="btn update" id="btnUpdate">Update</button>
              <button class="btn delete" id="btnDelete">Delete</button>
              <button class="btn reset" id="btnReset">Reset</button>
            </div>
          </div>
        </div>
      </div>

      <div id="list" class="tab-panel" style="display:none">
        <div style="margin-bottom:8px;display:flex;justify-content:space-between;align-items:center">
          <div style="font-weight:700;color:#c84b22">Video List</div>
          <div style="color:#666;font-size:13px"><span id="totalCount">0</span> videos</div>
        </div>

        <table id="videoTable">
          <thead>
            <tr><th>Youtube Id</th><th>Video Title</th><th>View Count</th><th>Status</th><th></th></tr>
          </thead>
          <tbody></tbody>
        </table>

        <div class="pager">
          <button class="pgbtn" id="first">|&lt;</button>
          <button class="pgbtn" id="prev">&lt;&lt;</button>
          <div id="pageInfo" style="padding:6px 10px;color:#666"></div>
          <button class="pgbtn" id="next">&gt;&gt;</button>
          <button class="pgbtn" id="last">&gt;|</button>
        </div>
      </div>
    </div>
  </main>

  <script>
    // Simple localStorage-backed manager for demo
    const STORAGE_KEY = 'video_manager_v1';
    let videos = JSON.parse(localStorage.getItem(STORAGE_KEY) || 'null') || [
      {ytid:'Ytet_bPiRCU', title:'Lâu ghê mới gặp', views:105, status:'active', desc:'Sample video'}
    ];
    let editingIndex = null;

    // Pagination
    let page = 1; const perPage = 5;

    // Elements
    const tbody = document.querySelector('#videoTable tbody');
    const totalCountEl = document.getElementById('totalCount');
    const pageInfo = document.getElementById('pageInfo');

    function save(){ localStorage.setItem(STORAGE_KEY, JSON.stringify(videos)); }

    function renderList(){
      const start = (page-1)*perPage; const end = start+perPage;
      const pageItems = videos.slice(start,end);
      tbody.innerHTML = '';
      pageItems.forEach((v,i)=>{
        const tr = document.createElement('tr');
        tr.innerHTML = `<td>${v.ytid}</td><td>${v.title}</td><td>${v.views}</td><td>${v.status}</td><td><span class="edit-link" data-index="${start+i}">Edit</span></td>`;
        tbody.appendChild(tr);
      });
      totalCountEl.textContent = videos.length;
      const totalPages = Math.max(1, Math.ceil(videos.length/perPage));
      pageInfo.textContent = `Page ${page} of ${totalPages}`;
    }

    function resetForm(){
      document.getElementById('ytid').value='';
      document.getElementById('title').value='';
      document.getElementById('views').value=0;
      document.getElementById('desc').value='';
      document.getElementById('statusActive').checked=true;
      editingIndex = null;
    }

    function createVideo(){
      const v = { ytid:document.getElementById('ytid').value.trim(), title:document.getElementById('title').value.trim(), views:parseInt(document.getElementById('views').value)||0, status: document.getElementById('statusActive').checked? 'active':'inactive', desc:document.getElementById('desc').value };
      if(!v.ytid || !v.title){ alert('Youtube Id and Title are required'); return; }
      videos.unshift(v);
      save(); renderList(); resetForm();
    }

    function updateVideo(){
      if(editingIndex===null){ alert('Select a video to edit first (click Edit in the list)'); return; }
      const v = videos[editingIndex];
      v.ytid = document.getElementById('ytid').value.trim();
      v.title = document.getElementById('title').value.trim();
      v.views = parseInt(document.getElementById('views').value)||0;
      v.status = document.getElementById('statusActive').checked? 'active':'inactive';
      v.desc = document.getElementById('desc').value;
      save(); renderList(); resetForm();
    }

    function deleteVideo(){
      if(editingIndex===null){ alert('Select a video to delete'); return; }
      if(!confirm('Delete this video?')) return;
      videos.splice(editingIndex,1);
      save(); renderList(); resetForm();
    }

    // Edit from list
    document.addEventListener('click', function(e){
      if(e.target && e.target.classList.contains('edit-link')){
        const idx = parseInt(e.target.getAttribute('data-index'));
        editingIndex = idx;
        const v = videos[idx];
        document.getElementById('ytid').value=v.ytid;
        document.getElementById('title').value=v.title;
        document.getElementById('views').value=v.views;
        document.getElementById('desc').value=v.desc||'';
        if(v.status==='active') document.getElementById('statusActive').checked=true; else document.getElementById('statusInactive').checked=true;
        window.scrollTo({top:0,behavior:'smooth'});
      }
    });

    // Tab handling (match UserManager behavior)
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
    // make first tab active on load
    const _first = document.querySelector('.tab'); if(_first) _first.classList.add('active');

    // Buttons
    document.getElementById('btnCreate').addEventListener('click', createVideo);
    document.getElementById('btnUpdate').addEventListener('click', updateVideo);
    document.getElementById('btnDelete').addEventListener('click', deleteVideo);
    document.getElementById('btnReset').addEventListener('click', resetForm);

    // Pager
    document.getElementById('first').addEventListener('click', ()=>{ page=1; renderList(); });
    document.getElementById('prev').addEventListener('click', ()=>{ if(page>1) page--; renderList(); });
    document.getElementById('next').addEventListener('click', ()=>{ const totalPages = Math.max(1, Math.ceil(videos.length/perPage)); if(page<totalPages) page++; renderList(); });
    document.getElementById('last').addEventListener('click', ()=>{ page = Math.max(1, Math.ceil(videos.length/perPage)); renderList(); });

    // init
    renderList();
    resetForm();
  </script>
</body>
</html>
