<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Video Details - Online Entertainment</title>
    <style>
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
        .active-btn{
            background:#1976d2
        }
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
        }

        header {
            background: linear-gradient(135deg, #FFD700 0%, #FFC700 100%);
            padding: 20px 0;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        .header-container {
            max-width: 1400px;
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
            max-width: 1400px;
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

        /* Details Layout */
        .details-layout {
            display: grid;
            grid-template-columns: 1fr 320px;
            gap: 20px;
        }

        /* Main Video Section */
        .video-section {
            background: white;
            border: 3px solid #d4a574;
            border-radius: 4px;
            overflow: hidden;
        }

        .video-player {
            width: 100%;
            height: 400px;
            background: white;
            border: 2px solid #d4a574;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 18px;
            color: #666;
            margin: 12px;
        }

        .video-info {
            padding: 20px;
            background: white;
        }

        .video-title-section {
            background-color: #c8e6c9;
            padding: 15px;
            border: 1px solid #a5d6a7;
            border-radius: 4px;
            margin-bottom: 15px;
        }

        .video-title-section h2 {
            color: #333;
            font-weight: bold;
            font-size: 24px;
            text-transform: uppercase;
            letter-spacing: 1px;
            margin: 0;
            margin-bottom: 8px;
        }

        .description-section {
            margin-bottom: 15px;
        }

        .description-label {
            font-weight: bold;
            color: #333;
            text-transform: uppercase;
            font-size: 14px;
            margin-bottom: 8px;
        }

        .description-text {
            color: #666;
            line-height: 1.6;
            font-size: 14px;
        }

        .action-buttons {
            display: flex;
            gap: 15px;
            margin-top: 20px;
            padding-top: 20px;
            border-top: 1px solid #ddd;
        }

        .action-btn {
            flex: 1;
            padding: 12px 20px;
            border: none;
            border-radius: 6px;
            font-weight: bold;
            color: white;
            cursor: pointer;
            font-size: 16px;
            transition: opacity 0.3s ease;
        }



        .share-btn {
            background-color: #ff9800;
        }

        .share-btn:hover {
            background-color: #f57c00;
        }

        /* Sidebar - Related Videos */
        .sidebar {
            position: sticky;
            top: 20px;
            height: fit-content;
        }

        .sidebar-title {
            font-size: 16px;
            font-weight: bold;
            color: #333;
            margin-bottom: 15px;
            text-transform: uppercase;
            letter-spacing: 1px;
            padding-bottom: 10px;
            border-bottom: 2px solid #FFD700;
        }

        .related-video-list {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }

        .related-video-item {
            background: white;
            border: 2px solid #a5d6a7;
            border-radius: 4px;
            overflow: hidden;
            transition: box-shadow 0.3s ease;
            cursor: pointer;
        }

        .related-video-item:hover {
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
        }

        .related-video-poster {
            width: 100%;
            height: 80px;
            background: white;
            border: 1px solid #a5d6a7;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 12px;
            color: #666;
        }

        .related-video-info {
            padding: 10px;
        }

        .related-video-title {
            font-size: 12px;
            font-weight: bold;
            color: #333;
            text-decoration: underline;
            word-wrap: break-word;
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
        @media (max-width: 1000px) {
            .details-layout {
                grid-template-columns: 1fr;
            }

            .sidebar {
                position: static;
            }

            .related-video-list {
                flex-direction: row;
                flex-wrap: wrap;
            }

            .related-video-item {
                flex: 1;
                min-width: 150px;
            }

            .video-player {
                height: 300px;
            }
        }

        @media (max-width: 768px) {
            .header-container {
                flex-direction: column;
                gap: 20px;
            }

            nav {
                flex-direction: column;
                gap: 15px;
            }

            .video-title-section h2 {
                font-size: 18px;
            }

            .video-player {
                height: 200px;
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
                <a href="${pageContext.request.contextPath}/MyFavorites?userid=${userid}&id=${id}">My Favorites</a>
                <div class="account-dropdown">
                    <span class="dropdown-toggle">My Account ▼</span>
                    <div class="dropdown-menu">
                        <a href="indexFirst">Đăng xuất</a>
                        <a href="ChangePassword?iduser=${userid}">Đổi mật khẩu</a>
                        <a href="EditProfile?iduser=${userid}">Chỉnh sửa thông tin</a>
                    </div>
                </div>
            </nav>
        </div>
    </header>

    <!-- Main Content -->
    <div class="container">
        <!-- Back Link -->
        <div class="back-link">
            <a href="${pageContext.request.contextPath}/indexUser?userid=${userid}">← Back to Featured Content</a>
        </div>

        <!-- Details Layout -->
        <div class="details-layout">
            <!-- Main Video Section -->
            <section class="video-section">
                <!-- Video Player -->
                <div class="video-player">
                	<img src="${pageContext.request.contextPath}/Saved Pictures/${main.poster}" alt="${main.title}" width="100%" height="400">
                </div>

                <!-- Video Info -->
                <div class="video-info">
                    <!-- Title Section -->
                    <div class="video-title-section">
                        <h2>${main.title}</h2>
                    </div>

                    <!-- Description Section -->
                    <div class="description-section">
                        <div class="description-label">Mô tả</div>
                        <p class="description-text">
							${main.description}
                        </p>
                    </div>
                    <!-- Action Buttons -->
                    <form method = "post">
                    <div class="action-buttons">
                    		<c:choose>
                        			<c:when test="${likedIds.contains(main.id)}"><button formaction = "${pageContext.request.contextPath}/CardDetails/unlike?id=${main.id}&userid=${userid}" class="video-btn like-btn btnlike active-btn action-btn">Unlike</button></c:when>
                        			<c:otherwise><button formaction = "${pageContext.request.contextPath}/CardDetails/like?id=${main.id}&userid=${userid}" class="video-btn like-btn btnlike action-btn">Like</button></c:otherwise>
                        	</c:choose>
                            <button class="action-btn share-btn" style="width: 100%;" formaction = "${pageContext.request.contextPath}/CardDetails/Share?id=${main.id}&userid=${userid}">Share</button>
                    </div>             
                    </form>
                </div>
            </section>

            <!-- Sidebar - Related Videos -->
            <aside class="sidebar">
                <h3 class="sidebar-title">Related Videos</h3>
                <div class="related-video-list">
                <c:forEach items = "${list}" var = "a">
                <a href="CardDetails?id=${a.id}&userid=${userid}" class="video-card-link">
                	<div class="related-video-item">
                        <div class="related-video-poster">
							<img src="${pageContext.request.contextPath}/Saved Pictures/${a.poster}" alt="${a.title}" width="100%" height="80">
						</div>
                        <div class="related-video-info">
                            <div class="related-video-title">${a.title}</div>
                        </div>
                    </div>
                   </a>
                </c:forEach>
                </div>
            </aside>
        </div>
    </div>
	
    <!-- Footer -->
    <footer>
        <p>&copy; 2025 Online Entertainment. All rights reserved.</p>
        <p>Contact us: info@onlineentertainment.com | Phone: 1-800-ENTERTAIN</p>
    </footer>
    <script>
        const likeButtons = document.querySelectorAll(".btnlike");
        likeButtons.forEach(element => {
            element.addEventListener("click", function(){
            element.classList.toggle("active-btn");
                if(element.innerText === "Like"){
                    element.innerText = "Unlike";
                }else{
                    element.innerText = "Like";
                }
            });
        });
    </script>
</body>
</html>
