<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width,initial-scale=1" />
  <title>Edit Profile - Online Entertainment</title>
  <style>*{box-sizing:border-box;margin:0;padding:0}body{font-family:Arial,Helvetica,sans-serif;background:#f5f5f5}header{background:linear-gradient(135deg,#FFD700 0%,#FFC700 100%);padding:20px 0;box-shadow:0 2px 5px rgba(0,0,0,0.1)}.header-container{max-width:1200px;margin:0 auto;display:flex;justify-content:space-between;align-items:center;padding:0 20px}.logo{font-size:24px;font-weight:bold;color:#d32f2f}.container{max-width:800px;margin:40px auto;padding:20px}.box{background:#fff;border:3px solid #d4a574;padding:20px;border-radius:6px}.form-group{margin-bottom:15px}label{display:block;margin-bottom:6px;font-weight:bold;color:#333}input,textarea{width:100%;padding:12px;border:2px solid #d4a574;border-radius:4px}textarea{min-height:100px}.actions{text-align:right;margin-top:12px}.btn{padding:10px 18px;border-radius:6px;border:none;font-weight:bold;cursor:pointer}.btn-primary{background:#1976d2;color:#fff}</style>
</head>
<body>
  <header><div class="header-container"><div class="logo">Online Entertainment</div></div></header>
  <div class="container">
    <div class="box">
      <h2>Edit Profile</h2>
      <form id="profileForm">
        <div class="form-group"><label for="name">Full name</label><input id="name" type="text"></div>
        <div class="form-group"><label for="bio">Bio</label><textarea id="bio"></textarea></div>
        <div class="actions"><button class="btn btn-primary" type="submit">Save</button></div>
      </form>
    </div>
  </div>
  <footer style="background:#333;color:#fff;text-align:center;padding:20px;margin-top:40px"><p>&copy; 2025 Online Entertainment</p></footer>
  <script>document.getElementById('profileForm').addEventListener('submit',function(e){e.preventDefault();alert('Profile saved (demo).');history.back();});</script>
</body>
</html>