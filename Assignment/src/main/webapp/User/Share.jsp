<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Send Video to Your Friend - Online Entertainment</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
        }

        /* Header and Navigation */
        header {
            background: linear-gradient(135deg, #FFD700 0%, #FFC700 100%);
            padding: 20px 0;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        .header-container {
            max-width: 1200px;
            margin: 0 auto;
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 0 20px;
        }

        .logo {
            font-size: 28px;
            font-weight: bold;
            color: #d32f2f;
            text-transform: uppercase;
            letter-spacing: 2px;
        }

        nav {
            display: flex;
            gap: 40px;
            align-items: center;
        }

        nav a {
            text-decoration: none;
            color: #0051ba;
            font-weight: bold;
            font-size: 16px;
            transition: color 0.3s ease;
        }

        nav a:hover {
            color: #d32f2f;
        }

       .account-dropdown {
  position: relative;
  display: inline-block;
}

.dropdown-toggle {
  color: #0051ba;
  font-weight: bold;
  font-size: 16px;
  cursor: pointer;
  padding: 8px 12px;
  transition: color 0.3s ease;
}

.dropdown-toggle:hover {
  color: #d32f2f;
}

.dropdown-menu {
  display: none;
  position: absolute;
  right: 0;
  top: calc(100% + 2px); /* tránh khoảng trống */
  background-color: white;
  border: 2px solid #ffb3b3;
  border-radius: 6px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.15);
  min-width: 180px;
  z-index: 1000;
  overflow: hidden;
}

.dropdown-menu a {
  display: block;
  padding: 12px 18px;
  color: #333;
  text-decoration: none;
  font-size: 14px;
  transition: background-color 0.3s ease, color 0.3s ease;
}

.dropdown-menu a:hover {
  background-color: #ffe6e6;
  color: #d32f2f;
}

/* Hiển thị dropdown khi hover vào toggle hoặc menu */
.account-dropdown:hover .dropdown-menu {
  display: block;
}

/* Thêm hiệu ứng mượt mà */
.dropdown-menu {
  opacity: 0;
  transform: translateY(-5px);
  transition: opacity 0.2s ease, transform 0.2s ease;
}

.account-dropdown:hover .dropdown-menu {
  opacity: 1;
  transform: translateY(0);
}


        /* Main Container */
        .container {
            max-width: 900px;
            margin: 40px auto;
            padding: 0 20px;
        }

        /* Back Link */
        .back-link {
            margin-bottom: 20px;
        }

        .back-link a {
            color: #0051ba;
            text-decoration: none;
            font-weight: bold;
            transition: color 0.3s ease;
        }

        .back-link a:hover {
            color: #d32f2f;
        }

        /* Share Form Box */
        .share-form-container {
            background: white;
            border: 3px solid #d4a574;
            border-radius: 4px;
            overflow: hidden;
        }

        /* Form Header */
        .form-header {
            background-color: #c8e6c9;
            padding: 20px;
            border-bottom: 2px solid #d4a574;
        }

        .form-header h2 {
            color: #333;
            font-weight: bold;
            font-size: 20px;
            text-transform: uppercase;
            letter-spacing: 1px;
            margin: 0;
        }

        /* Form Content */
        .form-content {
            padding: 30px;
        }

        .form-group {
            margin-bottom: 25px;
        }

        .form-label {
            display: block;
            color: #333;
            font-weight: bold;
            font-size: 16px;
            text-transform: uppercase;
            letter-spacing: 0.5px;
            margin-bottom: 12px;
        }

        .form-input {
            width: 100%;
            padding: 15px;
            border: 2px solid #d4a574;
            border-radius: 4px;
            font-size: 14px;
            font-family: Arial, sans-serif;
            transition: border-color 0.3s ease;
        }

        .form-input:focus {
            outline: none;
            border-color: #ff9800;
            box-shadow: 0 0 8px rgba(255, 152, 0, 0.2);
        }

        /* Form Footer */
        .form-footer {
            padding: 20px 30px;
            background-color: #f0f0f0;
            border-top: 1px solid #ddd;
            text-align: right;
        }

        .send-btn {
            padding: 12px 40px;
            background-color: #ff9800;
            color: white;
            border: none;
            border-radius: 6px;
            font-weight: bold;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            text-transform: uppercase;
            letter-spacing: 0.5px;
        }

        .send-btn:hover {
            background-color: #f57c00;
        }

        .send-btn:active {
            transform: scale(0.98);
        }

        /* Success Message */
        .success-message {
            display: none;
            background-color: #c8e6c9;
            color: #2e7d32;
            padding: 15px;
            border-radius: 4px;
            margin-top: 20px;
            text-align: center;
            font-weight: bold;
        }

        /* Footer */
        footer {
            background-color: #333;
            color: white;
            text-align: center;
            padding: 20px;
            margin-top: 60px;
        }

        footer p {
            margin: 5px 0;
        }

        /* Responsive Design */
        @media (max-width: 768px) {
            .header-container {
                flex-direction: column;
                gap: 20px;
            }

            nav {
                flex-direction: column;
                gap: 15px;
            }

            .form-header h2 {
                font-size: 18px;
            }

            .form-content {
                padding: 20px;
            }

            .form-footer {
                text-align: center;
            }

            .send-btn {
                width: 100%;
            }
        }
    </style>
</head>
<body>
    <!-- Header -->
    <header>
        <div class="header-container">
            <div class="logo">Online Entertainment</div>
            <nav>
                <a href="index.html">My Favorites</a>
                <div class="account-dropdown">
                    <span class="dropdown-toggle">My Account ▼</span>
                    <div class="dropdown-menu">
                        <a href="#login">Login</a>
                        <a href="#forgot-password">Forgot Password</a>
                        <a href="#registration">Registration</a>
                        <a href="#logoff">Logoff</a>
                        <a href="#change-password">Change Password</a>
                        <a href="#edit-profile">Edit Profile</a>
                    </div>
                </div>
            </nav>
        </div>
    </header>

    <!-- Main Content -->
    <div class="container">
        <!-- Back Link -->
        <div class="back-link">
            <a href="javascript:history.back();">← Back</a>
        </div>

        <!-- Share Form -->
        <div class="share-form-container">
            <!-- Form Header -->
            <div class="form-header">
                <h2>Send Video to Your Friend</h2>
            </div>

            <!-- Form Content -->
            <form class="form-content" id="shareForm">
                <div class="form-group">
                    <label for="friendEmail" class="form-label">Your Friend's Email?</label>
                    <input 
                        type="email" 
                        id="friendEmail" 
                        class="form-input" 
                        placeholder="Enter your friend's email address" 
                        required
                    >
                </div>

                <!-- Form Footer with Send Button -->
                <div class="form-footer">
                    <button type="submit" class="send-btn">Send</button>
                </div>
            </form>

            <!-- Success Message -->
            <div class="success-message" id="successMessage">
                ✓ Video shared successfully! Your friend will receive it shortly.
            </div>
        </div>
    </div>

    <!-- Footer -->
    <footer>
        <p>&copy; 2025 Online Entertainment. All rights reserved.</p>
        <p>Contact us: info@onlineentertainment.com | Phone: 1-800-ENTERTAIN</p>
    </footer>

    <script>
        // Handle form submission
        document.getElementById('shareForm').addEventListener('submit', function(e) {
            e.preventDefault();
            
            const emailInput = document.getElementById('friendEmail');
            const email = emailInput.value.trim();
            
            // Basic email validation
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            
            if (emailRegex.test(email)) {
                // Hide form and show success message
                document.getElementById('shareForm').style.display = 'none';
                document.getElementById('successMessage').style.display = 'block';
                
                // Optionally redirect after a delay
                setTimeout(function() {
                    history.back();
                }, 3000);
            } else {
                alert('Please enter a valid email address.');
                emailInput.focus();
            }
        });
    </script>
</body>
</html>
