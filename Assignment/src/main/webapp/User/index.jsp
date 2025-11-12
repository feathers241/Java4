<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Online Entertainment</title>
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


        /* Main Content */
        .container {
            max-width: 1200px;
            margin: 40px auto;
            padding: 0 20px;
        }

        .hero {
            text-align: center;
            padding: 60px 20px;
            background: white;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        }

        .hero h1 {
            font-size: 48px;
            color: #d32f2f;
            margin-bottom: 20px;
        }

        .hero p {
            font-size: 18px;
            color: #666;
            margin-bottom: 30px;
        }

        .btn {
            display: inline-block;
            padding: 12px 30px;
            margin: 10px;
            background-color: #FFD700;
            color: #333;
            text-decoration: none;
            border-radius: 4px;
            font-weight: bold;
            transition: background-color 0.3s ease;
            border: none;
            cursor: pointer;
            font-size: 16px;
        }

        .btn:hover {
            background-color: #FFC700;
        }

        .btn-primary {
            background-color: #0051ba;
            color: white;
        }

        .btn-primary:hover {
            background-color: #003d8f;
        }

        /* Content Sections */
        .content-section {
            margin: 40px 0;
        }

        .section-title {
            font-size: 32px;
            color: #333;
            margin-bottom: 30px;
            border-bottom: 3px solid #FFD700;
            padding-bottom: 10px;
        }

        .content-grid {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 20px;
            margin-bottom: 40px;
            width: 100%;
        }

        .content-grid .video-card-link {
            width: 100%;
        }

        .video-card-wrapper {
            display: flex;
            flex-direction: column;
            height: 100%;
        }

        .video-card-wrapper .video-card-link {
            flex: 1;
        }

        /* Video Card Styles */
        .video-card-link {
            text-decoration: none;
            color: inherit;
            display: block;
            cursor: pointer;
            transition: transform 0.3s ease;
        }

        .video-card-link:hover {
            transform: translateY(-5px);
        }

        .video-card {
            background: white;
            border: 2px solid #d4a574;
            border-radius: 4px;
            overflow: hidden;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        }

        .video-card-outer {
            padding: 8px;
            background: white;
        }

        .poster {
            width: 100%;
            height: 180px;
            background: white;
            border: 2px solid #d4a574;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 18px;
            color: #333;
            margin-bottom: 10px;
        }

        .video-title-section {
            background-color: #c8e6c9;
            padding: 12px;
            border-bottom: 2px solid #d4a574;
            margin-bottom: 8px;
        }

        .video-title-section h3 {
            color: #333;
            font-weight: bold;
            font-size: 16px;
            text-transform: uppercase;
            letter-spacing: 1px;
            margin: 0;
        }

        .video-buttons {
            padding: 12px;
            text-align: center;
            display: flex;
            gap: 10px;
            justify-content: center;
        }

        .video-btn {
            padding: 8px 20px;
            border: none;
            border-radius: 6px;
            font-weight: bold;
            color: white;
            cursor: pointer;
            font-size: 14px;
            transition: opacity 0.3s ease;
        }

        .like-btn {
            background-color: #66bb6a;
        }

        .like-btn:hover {
            background-color: #4caf50;
        }

        .share-btn {
            background-color: #ff9800;
            text-decoration: none;
            display: inline-block;
            padding: 8px 20px;
        }

        .share-btn:hover {
            background-color: #f57c00;
        }

        .share-link {
            flex: 1;
        }

        .like-link {
            flex: 1;
            text-decoration: none;
        }

        /* Pagination Controls */
        .pagination-controls {
            display: flex;
            justify-content: center;
            gap: 10px;
            margin-top: 30px;
            margin-bottom: 40px;
        }

        .pagination-btn {
            width: 45px;
            height: 45px;
            border: none;
            border-radius: 6px;
            background-color: #999;
            color: white;
            font-weight: bold;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .pagination-btn:hover {
            background-color: #777;
        }

        /* Carousel Container */
        .carousel-container {
            position: relative;
        }

        .carousel-dots {
            display: flex;
            justify-content: center;
            gap: 15px;
            margin-top: 20px;
            padding-bottom: 20px;
        }

        .dot {
            width: 12px;
            height: 12px;
            border-radius: 50%;
            background-color: #ddd;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .dot.active {
            background-color: #FFD700;
        }

        /* Card Styles for Other Sections */
        .card {
            background: white;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }

        .card:hover {
            transform: translateY(-5px);
            box-shadow: 0 4px 16px rgba(0, 0, 0, 0.2);
        }

        .card-image {
            width: 100%;
            height: 200px;
            background: linear-gradient(135deg, #FFD700 0%, #FFC700 100%);
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 48px;
        }

        .card-content {
            padding: 20px;
        }

        .card-content h3 {
            color: #d32f2f;
            margin-bottom: 10px;
        }

        .card-content p {
            color: #666;
            font-size: 14px;
            line-height: 1.6;
        }

        .trending-grid {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 20px;
            margin-bottom: 40px;
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

            .hero h1 {
                font-size: 36px;
            }

            .section-title {
                font-size: 24px;
            }

            .content-grid {
                grid-template-columns: 1fr;
            }
        }

        @media (min-width: 769px) and (max-width: 1024px) {
            .content-grid {
                grid-template-columns: repeat(2, 1fr);
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
                <a href="MyFavorite.html">My Favorites</a>
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
        <!-- Hero Section -->
        <section class="hero">
            <h1>Welcome to Online Entertainment</h1>
            <p>Your ultimate destination for entertainment and streaming content</p>
            <button class="btn btn-primary">Explore Now</button>
            <button class="btn">Learn More</button>
        </section>

        <!-- Featured Content -->
        <section class="content-section">
            <h2 class="section-title">Featured Content</h2>
            <div class="carousel-container">
                <div class="content-grid">
                    <div class="video-card-wrapper">
                        <a href="CardDetail.html" class="video-card-link">
                            <div class="video-card">
                                <div class="video-card-outer">
                                    <div class="poster">POSTER</div>
                                </div>
                                <div class="video-title-section">
                                    <h3>Video Title 1</h3>
                                </div>
                            </div>
                        </a>
                        <div class="video-buttons">
                            <a href="Like.html" class="like-link">
                                <button class="video-btn like-btn">Like</button>
                            </a>
                            <a href="Share.html" class="share-link">
                                <button class="video-btn share-btn">Share</button>
                            </a>
                        </div>
                    </div>

                    <div class="video-card-wrapper">
                        <a href="CardDetail.html" class="video-card-link">
                            <div class="video-card">
                                <div class="video-card-outer">
                                    <div class="poster">POSTER</div>
                                </div>
                                <div class="video-title-section">
                                    <h3>Video Title 2</h3>
                                </div>
                            </div>
                        </a>
                        <div class="video-buttons">
                            <a href="Like.html" class="like-link">
                                <button class="video-btn like-btn">Like</button>
                            </a>
                            <a href="Share.html" class="share-link">
                                <button class="video-btn share-btn">Share</button>
                            </a>
                        </div>
                    </div>

                    <div class="video-card-wrapper">
                        <a href="CardDetail.html" class="video-card-link">
                            <div class="video-card">
                                <div class="video-card-outer">
                                    <div class="poster">POSTER</div>
                                </div>
                                <div class="video-title-section">
                                    <h3>Video Title 3</h3>
                                </div>
                            </div>
                        </a>
                        <div class="video-buttons">
                            <a href="Like.html" class="like-link">
                                <button class="video-btn like-btn">Like</button>
                            </a>
                            <a href="Share.html" class="share-link">
                                <button class="video-btn share-btn">Share</button>
                            </a>
                        </div>
                    </div>

                    <div class="video-card-wrapper">
                        <a href="CardDetail.html" class="video-card-link">
                            <div class="video-card">
                                <div class="video-card-outer">
                                    <div class="poster">POSTER</div>
                                </div>
                                <div class="video-title-section">
                                    <h3>Video Title 4</h3>
                                </div>
                            </div>
                        </a>
                        <div class="video-buttons">
                            <a href="Like.html" class="like-link">
                                <button class="video-btn like-btn">Like</button>
                            </a>
                            <a href="Share.html" class="share-link">
                                <button class="video-btn share-btn">Share</button>
                            </a>
                        </div>
                    </div>

                    <div class="video-card-wrapper">
                        <a href="CardDetail.html" class="video-card-link">
                            <div class="video-card">
                                <div class="video-card-outer">
                                    <div class="poster">POSTER</div>
                                </div>
                                <div class="video-title-section">
                                    <h3>Video Title 5</h3>
                                </div>
                            </div>
                        </a>
                        <div class="video-buttons">
                            <a href="Like.html" class="like-link">
                                <button class="video-btn like-btn">Like</button>
                            </a>
                            <a href="Share.html" class="share-link">
                                <button class="video-btn share-btn">Share</button>
                            </a>
                        </div>
                    </div>

                    <div class="video-card-wrapper">
                        <a href="CardDetail.html" class="video-card-link">
                            <div class="video-card">
                                <div class="video-card-outer">
                                    <div class="poster">POSTER</div>
                                </div>
                                <div class="video-title-section">
                                    <h3>Video Title 6</h3>
                                </div>
                            </div>
                        </a>
                        <div class="video-buttons">
                            <a href="Like.html" class="like-link">
                                <button class="video-btn like-btn">Like</button>
                            </a>
                            <a href="Share.html" class="share-link">
                                <button class="video-btn share-btn">Share</button>
                            </a>
                        </div>
                    </div>
                </div>

                <!-- Pagination Controls -->
                <div class="pagination-controls">
                    <button class="pagination-btn" title="First">|&lt;</button>
                    <button class="pagination-btn" title="Previous">&lt;&lt;</button>
                    <button class="pagination-btn" title="Next">&gt;&gt;</button>
                    <button class="pagination-btn" title="Last">&gt;|</button>
                </div>

                <!-- Carousel Dots -->
                <div class="carousel-dots">
                    <div class="dot active"></div>
                    <div class="dot"></div>
                </div>
            </div>
        </section>

        <!-- Popular Section -->
        <section class="content-section">
            <h2 class="section-title">Trending Now</h2>
            <div class="trending-grid">
                <div class="card">
                    <div class="card-image">⭐</div>
                    <div class="card-content">
                        <h3>Premium Originals</h3>
                        <p>Exclusive content created specifically for our platform.</p>
                    </div>
                </div>
                <div class="card">
                    <div class="card-image">🏆</div>
                    <div class="card-content">
                        <h3>Award Winners</h3>
                        <p>Critically acclaimed entertainment that won major awards.</p>
                    </div>
                </div>
                <div class="card">
                    <div class="card-image">🔥</div>
                    <div class="card-content">
                        <h3>Hot Picks</h3>
                        <p>The hottest content trending right now on our platform.</p>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <!-- Footer -->
    <footer>
        <p>&copy; 2025 Online Entertainment. All rights reserved.</p>
        <p>Contact us: info@onlineentertainment.com | Phone: 1-800-ENTERTAIN</p>
    </footer>
</body>
</html>
