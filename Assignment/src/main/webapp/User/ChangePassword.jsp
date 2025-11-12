<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width,initial-scale=1" />
  <title>Change Password - Online Entertainment</title>
  <style>*{box-sizing:border-box;margin:0;padding:0}body{font-family:Arial,Helvetica,sans-serif;background:#f5f5f5}header{background:linear-gradient(135deg,#FFD700 0%,#FFC700 100%);padding:20px 0;box-shadow:0 2px 5px rgba(0,0,0,0.1)}.header-container{max-width:1200px;margin:0 auto;display:flex;justify-content:space-between;align-items:center;padding:0 20px}.logo{font-size:24px;font-weight:bold;color:#d32f2f}.container{max-width:600px;margin:40px auto;padding:20px}.box{background:#fff;border:3px solid #d4a574;padding:20px;border-radius:6px}.box h2{background:#c8e6c9;padding:12px;margin:-20px -20px 20px -20px;border-bottom:2px solid #d4a574}.form-group{margin-bottom:15px}label{display:block;margin-bottom:6px;font-weight:bold;color:#333}input{width:100%;padding:12px;border:2px solid #d4a574;border-radius:4px;margin-bottom:8px}.actions{text-align:right;margin-top:12px}.btn{padding:10px 18px;border-radius:6px;border:none;font-weight:bold;cursor:pointer}.btn-primary{background:#1976d2;color:#fff}</style>
</head>
<body>
  <header><div class="header-container"><div class="logo">Online Entertainment</div></div></header>
  <div class="container">
    <div class="box">
      <h2>Change Password</h2>
      <form id="changeForm">
        <div class="form-group"><label for="current">Current password</label><input id="current" type="password" required></div>
        <div class="form-group"><label for="newpass">New password</label><input id="newpass" type="password" required></div>
        <div class="form-group"><label for="confirm">Confirm new password</label><input id="confirm" type="password" required></div>
        <div class="actions"><button class="btn btn-primary" type="submit">Change</button></div>
      </form>
    </div>
  </div>
  <footer style="background:#333;color:#fff;text-align:center;padding:20px;margin-top:40px"><p>&copy; 2025 Online Entertainment</p></footer>
  <script>document.getElementById('changeForm').addEventListener('submit',function(e){e.preventDefault();alert('Password changed (demo).');history.back();});</script>
</body>
</html>