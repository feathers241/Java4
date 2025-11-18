<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width,initial-scale=1" />
  <title>Forgot Password - Online Entertainment</title>
  <style>*{box-sizing:border-box;margin:0;padding:0}body{font-family:Arial,Helvetica,sans-serif;background:#f5f5f5}header{background:linear-gradient(135deg,#FFD700 0%,#FFC700 100%);padding:20px 0;box-shadow:0 2px 5px rgba(0,0,0,0.1)}.header-container{max-width:1200px;margin:0 auto;display:flex;justify-content:space-between;align-items:center;padding:0 20px}.logo{font-size:24px;font-weight:bold;color:#d32f2f}.container{max-width:600px;margin:40px auto;padding:20px}.box{background:#fff;border:3px solid #d4a574;padding:20px;border-radius:6px}.box h2{background:#c8e6c9;padding:12px;margin:-20px -20px 20px -20px;border-bottom:2px solid #d4a574}.form-group{margin-bottom:15px}label{display:block;margin-bottom:6px;font-weight:bold;color:#333}input{width:100%;padding:12px;border:2px solid #d4a574;border-radius:4px}.actions{text-align:right;margin-top:12px}.btn{padding:10px 18px;border-radius:6px;border:none;font-weight:bold;cursor:pointer}.btn-primary{background:#1976d2;color:#fff}.small-links{margin-top:12px;display:flex;justify-content:space-between}.small-links a{color:#0051ba;text-decoration:none}</style>
</head>
<body>
  <header>
    <div class="header-container"><div class="logo">Online Entertainment</div></div>
  </header>
  <div class="container">
    <div class="box">
      <h2>Forgot Password</h2>
      <form id="forgotForm" method ="post">
        <div class="form-group">
          <label for="email">Nhập vào username hoặc email đã đăng kí</label>
          <input id="email" name = "email"/>
        </div>
        <div class="actions">
          <button type="submit" class="btn btn-primary">Send Reset Link</button>
        </div>
        <div class="form-group">
          <label>Nhập vào 4 số đã gửi vào email để nhận mã OTP:</label>
          <input id="email"  name = "otp"/>
          <input type = "hidden" name = "randomNum" value = "${randomNum}">
        </div>
        <div class="actions">
          <button type="submit" class="btn btn-primary" formaction = "${pageContext.request.contextPath}/ForgotPassword/otp">Kiểm tra OTP</button>
          <h4>${mess}</h4>
        </div>
      </form>
    </div>
  </div>
  <footer style="background:#333;color:#fff;text-align:center;padding:20px;margin-top:40px"><p>&copy; 2025 Online Entertainment</p></footer>
  <script>document.getElementById('forgotForm').addEventListener('submit',function(e){e.preventDefault();alert('Nếu email có tồn tại, hệ thống sẽ gửi mã OTP vào email sau đó hãy nhập vào bên dưới. Nếu email không tồn tại trong hệ thống của chúng tôi, xin hãy nhấn vào nút đăng ký bên dưới để tạo 1 tài khoản mới, chứ đừng nên chờ email gửi mã OTP một cách vô vọng như cách bạn chờ người yêu cũ sẽ quay lại với mình vậy, thứ bạn nhớ chỉ là những kỉ niệm hạnh phúc bên người ấy ở quá khứ thôi, chứ không thật sự là chính con người của người ấy, nếu đọc xong dòng này và email bạn có trong hệ thống của chúng tôi thì bạn nên đi kiểm tra ngay và nhập mã OTP và bên dưới để khôi phục mật khẩu nhé');history.back();});</script>
</body>
</html>