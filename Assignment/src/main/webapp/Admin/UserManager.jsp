<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        <a href="index.html">Home</a>
        <a href="VideoManager.html">Videos</a>
        <a href="UserManager.html">Users</a>
        <a href="ReportManager.html">Reports</a>
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
        <div style="color:#666;font-size:13px">Stored users: <span id="userCount">0</span></div>
      </div>

      <div id="edition" class="tab-panel">
        <form id="userForm" onsubmit="return false">
          <div class="form-row">
            <div class="field">
              <label>Username</label>
              <input id="username" type="text" required />
            </div>
            <div class="field">
              <label>Password</label>
              <input id="password" type="password" />
            </div>
          </div>

          <div class="form-row">
            <div class="field">
              <label>Fullname</label>
              <input id="fullname" type="text" />
            </div>
            <div class="field">
              <label>Email Address</label>
              <input id="email" type="email" />
            </div>
          </div>

          <div class="form-row">
            <div class="field">
              <label>Role</label>
              <select id="role">
                <option>Admin</option>
                <option>Editor</option>
                <option>User</option>
              </select>
            </div>
            <div class="field"></div>
          </div>

          <div class="actions">
            <button id="btnDelete" class="btn-delete" onclick="deleteUser()">Delete</button>
            <button id="btnUpdate" class="btn-update" onclick="saveUser()">Update</button>
            <button type="button" onclick="resetForm()">Reset</button>
          </div>
        </form>
      </div>

      <div id="list" class="tab-panel" style="display:none">
        <table id="userTable">
          <thead>
            <tr><th>Username</th><th>Password</th><th>Fullname</th><th>Email</th><th>Role</th><th>Action</th></tr>
          </thead>
          <tbody></tbody>
        </table>

        <div class="pager">
          <button onclick="goFirst()">|&lt;</button>
          <button onclick="goPrev()">&lt;&lt;</button>
          <button onclick="goNext()">&gt;&gt;</button>
          <button onclick="goLast()">&gt;|</button>
        </div>
      </div>
    </section>
  </main>

  <script>
    // Simple client-side user manager (localStorage)
    const STORAGE_KEY = 'user_manager_v1';
    let users = JSON.parse(localStorage.getItem(STORAGE_KEY) || '[]');
    let editingIndex = -1;
    const pageSize = 10;
    let currentPage = 0;

    // Tabs
    document.querySelectorAll('.tab').forEach(t=>t.addEventListener('click',()=>{
      document.querySelectorAll('.tab').forEach(x=>x.classList.remove('active'));
      t.classList.add('active');
      document.querySelectorAll('.tab-panel').forEach(p=>p.style.display='none');
      document.getElementById(t.dataset.target).style.display='block';
      if(t.dataset.target === 'list') renderList();
    }));

    function saveUser(){
      const u = document.getElementById('username').value.trim();
      if(!u){alert('Username required');return}
      const payload = {
        username: u,
        password: document.getElementById('password').value,
        fullname: document.getElementById('fullname').value,
        email: document.getElementById('email').value,
        role: document.getElementById('role').value
      };
      if(editingIndex >=0){
        users[editingIndex] = payload;
      } else {
        users.push(payload);
      }
      persist();
      resetForm();
      alert('Saved');
    }

    function deleteUser(){
      if(editingIndex<0){alert('No user selected');return}
      if(!confirm('Delete this user?')) return;
      users.splice(editingIndex,1);
      persist();
      resetForm();
    }

    function persist(){
      localStorage.setItem(STORAGE_KEY, JSON.stringify(users));
      document.getElementById('userCount').textContent = users.length;
      renderList();
    }

    function resetForm(){
      editingIndex = -1;
      document.getElementById('userForm').reset();
      document.getElementById('btnUpdate').textContent = 'Update';
    }


    function editUser(idx){
      const u = users[idx];
      if(!u) return;
      editingIndex = idx;
      document.getElementById('username').value = u.username;
      document.getElementById('password').value = u.password;
      document.getElementById('fullname').value = u.fullname;
      document.getElementById('email').value = u.email;
      document.getElementById('role').value = u.role;
      document.querySelectorAll('.tab').forEach(x=>x.classList.remove('active'));
      document.querySelector('[data-target="edition"]').classList.add('active');
      document.querySelectorAll('.tab-panel').forEach(p=>p.style.display='none');
      document.getElementById('edition').style.display='block';
      document.getElementById('btnUpdate').textContent = 'Save';
    }

    function goFirst(){ currentPage = 0; renderList(); }
    function goPrev(){ if(currentPage>0) currentPage--; renderList(); }
    function goNext(){ if((currentPage+1)*pageSize < users.length) currentPage++; renderList(); }
    function goLast(){ currentPage = Math.max(0, Math.ceil(users.length/pageSize)-1); renderList(); }

    function escapeHtml(s){ return (s||'').toString().replace(/&/g,'&amp;').replace(/</g,'&lt;').replace(/>/g,'&gt;') }

    // seed sample user if empty
    if(users.length===0){
      users.push({username:'TeonV',password:'123456',fullname:'Nguyễn Văn Tèo',email:'teonv@gmail.com',role:'Admin'});
      persist();
    } else {
      document.getElementById('userCount').textContent = users.length;
      renderList();
    }
  </script>
</body>
</html>
