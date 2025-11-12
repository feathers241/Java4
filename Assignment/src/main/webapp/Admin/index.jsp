<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width,initial-scale=1" />
	<title>Administration Tool</title>
	<style>
		:root{--bg:#0b0b0b;--accent:#8ad100;--panel:#121212;--muted:#999}
		*{box-sizing:border-box}
		body{font-family:Segoe UI,Roboto,Arial,sans-serif;background:#efefef;margin:0;color:#222}

		/* Glossy black header */
		.admin-header{
			background: linear-gradient(#2b2b2b,#0b0b0b 40%, #000);
			color:#fff;
			padding:18px 0;
			box-shadow:0 6px 18px rgba(0,0,0,0.5);
		}

		.admin-header .wrap{max-width:1200px;margin:0 auto;display:flex;align-items:center;justify-content:space-between;padding:0 20px}
		.brand{font-weight:800;color:#ffea00;font-size:28px;letter-spacing:1px;text-shadow:0 2px 0 rgba(0,0,0,0.6)}

		nav.admin-nav{display:flex;gap:28px}
		nav.admin-nav a{color:var(--accent);text-decoration:none;font-weight:700;padding:6px 8px}
		nav.admin-nav a:hover{color:#fff}

		/* Main layout */
		.container{max-width:1200px;margin:28px auto;padding:0 20px}
		.top-row{display:grid;grid-template-columns:repeat(4,1fr);gap:16px;margin-bottom:20px}

		.stat-card{background:#fff;border-radius:6px;padding:18px;box-shadow:0 2px 8px rgba(0,0,0,0.08);display:flex;flex-direction:column;justify-content:space-between}
		.stat-card .label{font-size:13px;color:var(--muted);font-weight:700;margin-bottom:6px}
		.stat-card .value{font-size:26px;font-weight:800;color:#222}
		.stat-card .sub{font-size:12px;color:#666;margin-top:8px}

		/* Panels */
		.panel{background:#fff;border-radius:6px;padding:18px;box-shadow:0 2px 8px rgba(0,0,0,0.08);margin-bottom:20px}
		.panel h3{margin:0 0 12px 0;font-size:18px;color:#222}

		.grid-2{display:grid;grid-template-columns:2fr 1fr;gap:16px}

		table{width:100%;border-collapse:collapse}
		table th,table td{padding:10px;border-bottom:1px solid #eee;text-align:left;font-size:14px}
		table th{background:#fafafa;font-weight:700}

		.related-list{display:flex;flex-direction:column;gap:10px}
		.related-item{display:flex;gap:12px;align-items:center}
		.thumb{width:64px;height:44px;background:#f4f4f4;border:2px solid #e6e6e6;border-radius:4px;display:flex;align-items:center;justify-content:center;color:#999}

		/* Responsive */
		@media (max-width:900px){.top-row{grid-template-columns:repeat(2,1fr)}.grid-2{grid-template-columns:1fr}.brand{font-size:20px}}
		@media (max-width:520px){.top-row{grid-template-columns:1fr}}
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
		<section class="top-row">
			<div class="stat-card">
				<div>
					<div class="label">Total Videos</div>
					<div class="value">1,248</div>
				</div>
				<div class="sub">Updated 5 minutes ago</div>
			</div>

			<div class="stat-card">
				<div>
					<div class="label">Active Users</div>
					<div class="value">3,412</div>
				</div>
				<div class="sub">Last 24 hours</div>
			</div>

			<div class="stat-card">
				<div>
					<div class="label">New Signups</div>
					<div class="value">72</div>
				</div>
				<div class="sub">Today</div>
			</div>

			<div class="stat-card">
				<div>
					<div class="label">Reports</div>
					<div class="value">8</div>
				</div>
				<div class="sub">Pending review</div>
			</div>
		</section>

		<section class="grid-2">
			<div class="panel">
				<h3>Recent Videos</h3>
				<table>
					<thead>
						<tr><th>Title</th><th>Uploaded</th><th>Views</th><th>Status</th></tr>
					</thead>
					<tbody>
						<tr><td>Summer Highlights</td><td>2d</td><td>12,345</td><td>Published</td></tr>
						<tr><td>Interview: Team Lead</td><td>3d</td><td>3,210</td><td>Published</td></tr>
						<tr><td>How to: Make a Trailer</td><td>4d</td><td>1,120</td><td>Draft</td></tr>
					</tbody>
				</table>
			</div>

			<aside class="panel">
				<h3>Quick Actions</h3>
				<div style="display:flex;flex-direction:column;gap:8px">
					<a href="VideoManager.html" style="text-decoration:none"><button style="padding:10px;border-radius:6px;border:none;background:#1976d2;color:#fff;cursor:pointer">Create New Video</button></a>
					<a href="ReportManager.html" style="text-decoration:none"><button style="padding:10px;border-radius:6px;border:none;background:#4caf50;color:#fff;cursor:pointer">Approve Reports</button></a>
					<a href="UserManager.html" style="text-decoration:none"><button style="padding:10px;border-radius:6px;border:none;background:#ff9800;color:#fff;cursor:pointer">Manage Users</button></a>
				</div>

				<h3 style="margin-top:18px">Recent Signups</h3>
				<div class="related-list">
					<div class="related-item"><div class="thumb">AV</div><div><strong>alice</strong><div style="font-size:12px;color:#666">Joined 1d ago</div></div></div>
					<div class="related-item"><div class="thumb">BM</div><div><strong>bob</strong><div style="font-size:12px;color:#666">Joined 2d ago</div></div></div>
					<div class="related-item"><div class="thumb">CJ</div><div><strong>carol</strong><div style="font-size:12px;color:#666">Joined 3d ago</div></div></div>
				</div>
			</aside>
		</section>

	</main>
</body>
</html>

