<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width,initial-scale=1" />
  <title>Report Manager</title>
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
    .filter-row{display:flex;gap:12px;align-items:center;margin-bottom:12px}
    select,input[type=text]{padding:8px;border:1px solid #ddd;border-radius:4px}
    table{width:100%;border-collapse:collapse;margin-top:8px}
    th,td{padding:10px;border-bottom:1px solid #eee;text-align:left}
    th{background:#fafafa;font-weight:700}
    .pager{display:flex;gap:8px;justify-content:flex-end;margin-top:8px}
    .pgbtn{padding:6px 10px;border-radius:6px;border:1px solid #ccc;background:#f3f3f3;cursor:pointer}
    .small{font-size:13px;color:#666}
    .small-link{color:#1976d2;cursor:pointer;text-decoration:underline}
    @media (max-width:900px){.filter-row{flex-direction:column;align-items:flex-start}}
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
        <a href="#">Reports</a>
      </nav>
    </div>
  </header>

  <main class="container">
    <section class="panel">
      <div style="display:flex;justify-content:space-between;align-items:center">
        <div class="tabs">
          <div class="tab active" data-target="favPanel">Favorites</div>
          <div class="tab" data-target="favUsersPanel">Favorite Users</div>
          <div class="tab" data-target="sharedPanel">Shared Friends</div>
        </div>
        <div class="small">Report Manager</div>
      </div>

      <!-- Favorites -->
      <div id="favPanel" class="tab-panel">

        <table id="favTable">
          <thead>
            <tr><th>Video Title</th><th>Favorite Count</th><th>Latest Date</th><th>Oldest Date</th></tr>
          </thead>
          <tbody></tbody>
        </table>

        <div class="pager"><div class="small">Showing <span id="favCount">0</span> rows</div></div>
      </div>

      <!-- Favorite Users -->
      <div id="favUsersPanel" class="tab-panel" style="display:none">
        <div class="filter-row">
          <label class="small">Video Title?</label>
          <select id="favUsersVideoSelect"></select>
          <div style="flex:1"></div>
        </div>

        <table id="favUsersTable">
          <thead>
            <tr><th>Username</th><th>Fullname</th><th>Email</th><th>Favorite Date</th></tr>
          </thead>
          <tbody></tbody>
        </table>

        <div class="pager"><div class="small">Showing <span id="favUsersCount">0</span> rows</div></div>
      </div>

      <!-- Shared Friends -->
      <div id="sharedPanel" class="tab-panel" style="display:none">

        <table id="sharedTable">
          <thead>
            <tr><th>Sender Name</th><th>Sender Email</th><th>Receiver Email</th><th>Sent Date</th></tr>
          </thead>
          <tbody></tbody>
        </table>

        <div class="pager"><div class="small">Showing <span id="sharedCount">0</span> rows</div></div>
      </div>
    </section>
  </main>

  <script>
    // Simple report manager demo using localStorage
    const STORAGE_KEY = 'report_manager_v1';
    let db = JSON.parse(localStorage.getItem(STORAGE_KEY) || 'null') || {
      videos: [{id:'vid1',title:'Lâu ghê mới gặp'}],
      favorites: [{videoId:'vid1',count:100,latest:'2020-12-31',oldest:'2020-01-01'}],
      favUsers: [{videoId:'vid1',username:'TeonV',fullname:'Nguyễn Văn Tèo',email:'teonv@gmail.com',date:'2020-01-01'}],
      shared: [{videoId:'vid1',sender:'Nguyễn Văn Tèo',senderEmail:'teonv@gmail.com',receiverEmail:'poly@gmail.com',sent:'2020-01-01'}]
    };

    function persist(){ localStorage.setItem(STORAGE_KEY, JSON.stringify(db)); }

    // Populate selects
    function populateVideoSelects(){
      const s = document.getElementById('favUsersVideoSelect');
      if(!s) return;
      s.innerHTML = '';
      db.videos.forEach(v=>{ const o = document.createElement('option'); o.value = v.id; o.textContent = v.title; s.appendChild(o); });
    }

    function escapeHtml(s){ return (s||'').toString().replace(/&/g,'&amp;').replace(/</g,'&lt;').replace(/>/g,'&gt;'); }

    // Tab handling
    document.querySelectorAll('.tab').forEach(t=>t.addEventListener('click',()=>{
      document.querySelectorAll('.tab').forEach(x=>x.classList.remove('active'));
      t.classList.add('active');
      document.querySelectorAll('.tab-panel').forEach(p=>p.style.display='none');
      const target = t.dataset.target; document.getElementById(target).style.display='block';
      if(target==='favPanel') renderFavorites(); if(target==='favUsersPanel') renderFavUsers(); if(target==='sharedPanel') renderShared();
    }));

    // init
    populateVideoSelects(); renderFavorites(); renderFavUsers(); renderShared(); persist();
  </script>
</body>
</html>
