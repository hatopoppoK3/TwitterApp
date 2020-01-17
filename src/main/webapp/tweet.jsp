<%@page import="twitterapp.TwitterUserData"%>
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
					<li><a href="/admin/tweet"><span>ツイート機能</span></a>
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
					<span>ツイート機能</span>
				</h2>
				<%
					TwitterUserData myUserData = (TwitterUserData) request.getAttribute("myUserData");
				%>
				<div class="article-account-box">
					<div class="article-account-row">
						<div>
							<img src=<%=myUserData.getUserImageURL()%> width="64" height="64">
						</div>
						<div><%=myUserData.getUserName()%>(@<%=myUserData.getUserScreenName()%>)
						</div>
					</div>
					<div class="article-account-row">
						<div>
							ツイート数:<%=myUserData.getUserStatusesCount()%></div>
						<div>
							お気に入り数:<%=myUserData.getUserFavouritesCount()%></div>
						<div>
							フォロー数:<%=myUserData.getUserFriendsCount()%></div>
						<div>
							フォロワー数:<%=myUserData.getUserFollowersCount()%></div>
					</div>
				</div>
				<p>テキストBOXにテキストを打ち込むことで上のアカウントでツイートすることができます。</p>
				<form action="/admin/tweet" method="post">
					ツイート内容:<br>
					<textarea rows="5" cols="30" name="tweet"></textarea>
					<br> <input type="submit" value="ツイート"> <input
						type="reset" value="リセット">
				</form>
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