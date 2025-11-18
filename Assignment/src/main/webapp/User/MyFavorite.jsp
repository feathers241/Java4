<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>My Favorites - Online Entertainment</title>
    <style>
        * {box-sizing: border-box; margin:0; padding:0}
        body {font-family: Arial, sans-serif; background:#f5f5f5}
        header {background: linear-gradient(135deg,#FFD700 0%,#FFC700 100%); padding:20px 0; box-shadow:0 2px 5px rgba(0,0,0,0.1)}
        .header-container{max-width:1200px;margin:0 auto;display:flex;justify-content:space-between;align-items:center;padding:0 20px}
        .logo{font-size:28px;font-weight:bold;color:#d32f2f;text-transform:uppercase;letter-spacing:2px}
        nav a{color:#0051ba;text-decoration:none;font-weight:bold;margin-left:20px}

        .container{max-width:1200px;margin:40px auto;padding:0 20px}
        .section-title{font-size:28px;color:#333;margin-bottom:20px;border-bottom:3px solid #FFD700;padding-bottom:10px}

        /* Grid: 3 cards per row on desktop */
        .content-grid{display:grid;grid-template-columns:repeat(3,1fr);gap:20px}

        .video-card-wrapper{display:flex;flex-direction:column;height:100%}
        .video-card-link{text-decoration:none;color:inherit;display:block}
        .video-card{background:#fff;border:2px solid #d4a574;border-radius:4px;overflow:hidden;display:flex;flex-direction:column;height:100%}
        .video-card-outer{padding:8px;background:#fff}
        .poster{width:100%;height:160px;background:#fff;border:2px solid #d4a574;display:flex;align-items:center;justify-content:center;color:#333;font-size:18px;margin-bottom:10px}
        .video-title-section{background:#c8e6c9;padding:12px;border-bottom:2px solid #d4a574}
        .video-title-section h3{margin:0;color:#333;font-size:16px;text-transform:uppercase}

        /* Buttons row under card */
        .video-buttons{display:flex;gap:12px;justify-content:center;padding:12px;background:#f6f6f6;border-top:1px solid #eee}
        .video-btn{padding:8px 20px;border-radius:6px;border:none;font-weight:bold;color:white;cursor:pointer}
        .unlike-btn{background:#1976d2}
        .unlike-btn:hover{background:#1565c0}
        .share-btn{background:#ff9800}
        .share-btn:hover{background:#f57c00}

        /* Footer */
        footer{background:#333;color:#fff;text-align:center;padding:20px;margin-top:40px}

        /* Responsive rules */
        @media (max-width:1024px){.content-grid{grid-template-columns:repeat(2,1fr)}}
        @media (max-width:768px){.content-grid{grid-template-columns:1fr}.poster{height:140px}}

        /* small niceties */
        .empty-state{background:#fff;border:2px solid #d4a574;padding:30px;border-radius:4px;text-align:center}
        .empty-state h3{font-size:20px;margin-bottom:12px}
        .empty-state p{color:#666}
        .active-btn{
            background-color: #66bb6a;
        }
    </style>
</head>
<body>
    <header>
        <div class="header-container">
            <div class="logo">Online Entertainment</div>
            <nav>
                <a href="${pageContext.request.contextPath}/indexUser?userid=${userid}">Home</a>
            </nav>
        </div>
    </header>

    <div class="container">
        <h2 class="section-title">My Favorites</h2>

        <!-- If there are liked videos, show them in a 3-column grid. This is a static demo with 6 cards. -->
		<form method = "post">
        <div class="content-grid">
        <c:forEach items = "${list}" var = "a">
        	<div class="video-card-wrapper">
                <a href="${pageContext.request.contextPath}/CardDetails?id=${a.id}&userid=${userid}" class="video-card-link">
                    <div class="video-card">
                        <div class="video-card-outer">
                            <div class="poster">
                            	<img src="${pageContext.request.contextPath}/Saved Pictures/${a.poster}" alt="${a.title}" width="100%" height="180">
                            </div>
                            <div class="video-title-section"><h3>${a.title}</h3></div>
                        </div>
                    </div>
                </a>
                <div class="video-buttons">
                    <button class="video-btn unlike-btn btnlike" formaction = "${pageContext.request.contextPath}/MyFavorites/unlike?id=${a.id}&userid=${userid}">Unlike</button>
                   <button class="video-btn share-btn" formaction = "${pageContext.request.contextPath}/MyFavorites/Share?id=${a.id}&userid=${userid}">Share</button>
                </div>
            </div>
        </c:forEach>
        </div>
        </form>

        <!-- Example empty state (uncomment to use when the user has no favorites) -->
        <!--
        <div class="empty-state">
            <h3>No favorites yet</h3>
            <p>You haven't liked any videos yet. Browse featured content and click "Like" to add videos here.</p>
        </div>
        -->
    </div>
	<script>
        const likeButtons = document.querySelectorAll(".btnlike");
        likeButtons.forEach(element => {
            element.addEventListener("click", function(){
            element.classList.toggle("active-btn");
                if(element.innerText === "Unlike"){
                    element.innerText = "Like";
                }else{
                    element.innerText = "Unlike";
                }
            });
        });
    </script>
    <footer>
        <p>&copy; 2025 Online Entertainment. All rights reserved.</p>
        <p>Contact us: info@onlineentertainment.com | Phone: 1-800-ENTERTAIN</p>
    </footer>
</body>
</html>