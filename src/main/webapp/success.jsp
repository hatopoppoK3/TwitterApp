<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Style-Type" content="text/html">
<meta http-equiv="content-language" content="ja">
<meta charset="UTF-8">
<title>TwitterApp</title>
<link rel="stylesheet" type="text/css" media="all" href="/css/base.css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/gh/fancyapps/fancybox@3.5.7/dist/jquery.fancybox.min.css" />
<link rel="icon" href="/images/favicon.ico" />

</head>

<body>
	<div class="container">
		<header class="header-template">
			<h1 class="header-title">
				<a href="/">TwitterApp</a>
			</h1>
			<nav class="header-nav">
				<ul>
					<li><a href="/"><span>ホーム</span></a></li>
				</ul>
			</nav>
		</header>
		<div class="main-content">
			<div class="sidebar-template">
				<nav class="sidebar-nav">
					<h2>
						<span>機能一覧</span>
					</h2>
					<ul>
						<li><a href="/admin/tweet">ツイート機能</a></li>
						<li><a href="/admin/timeline">Timeline機能</a></li>
					</ul>
				</nav>
			</div>
			<div class="article-template">
				<h2 class="article-title">
					<span><%=(String) request.getAttribute("twitterService")%></span>
				</h2>
				<p>
					<%=(String) request.getAttribute("message")%>
				</p>
			</div>
		</div>
		<footer class="footer-template">
			<h2>footer</h2>
		</footer>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/jquery@3.4.1/dist/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/gh/fancyapps/fancybox@3.5.7/dist/jquery.fancybox.min.js"></script>
</body>
</html>