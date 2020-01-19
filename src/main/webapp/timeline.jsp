<%@page import="twitterapp.TwitterStatusReactionsData"%>
<%@page import="twitterapp.TwitterStatusTextData"%>
<%@page import="twitterapp.TwitterUserData"%>
<%@page import="twitterapp.TwitterStatusData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
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
<link rel="icon" href="/images/favicon.ico">
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
					<li><a href="/admin/timeline"><span>Timeline機能</span></a>
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
					<span>Timeline機能</span>
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
				<p>
					こちらのアカウントについてのHomeTimelineやUserTimeline、ふぁぼ、自分にメンションがついているツイートを見ることができます。
					<br> また、ユーザー名を入力することでそのアカウントのUserTimeline、ふぁぼツイートを見ることができます。<br>デフォルトではHomeTimelineが選択されます。
				</p>
				<form action="/admin/timeline" method="get">
					<fieldset>
						<legend>自分に関してのタイムライン</legend>
						<p>
							<label><input type="radio" name="timeline_name"
								value="HomeTimeline" checked="checked">HomeTimeline</label> <label><input
								type="radio" name="timeline_name" value="MyUserTimeline">MyUserTimeline</label>
						</p>
						<p>
							<label><input type="radio" name="timeline_name"
								value="MyFavorites">MyFavorites</label> <label><input
								type="radio" name="timeline_name" value="MyMentions">MyMentions</label>
						</p>
					</fieldset>
					<fieldset>
						<legend>他のアカウントについてのタイムライン</legend>
						<p>
							<label>ユーザー名(半角英数字およびアンダーバーで記述):@<input type="text"
								name="timeline_target_screen_name" maxlength="15"
								pattern="^[0-9A-Z_a-z]+$"></label>
						</p>
						<p>
							<label><input type="radio" name="timeline_name"
								value="UserTimeline">UserTimeline</label> <label><input
								type="radio" name="timeline_name" value="UserFavorites">UserFavorites</label>
						</p>
					</fieldset>
					<p>
						<input type="submit" value="タイムラインを表示する" formmethod="get"
							formaction="/admin/timeline">
					</p>
				</form>
				<%
					List<Long> timelineStatusIdList = (List<Long>) request.getAttribute("timelineStatusIdList");
					Map<Long, TwitterStatusData[]> timelineStatusDataMap = (Map<Long, TwitterStatusData[]>) request
							.getAttribute("timelineStatusDataMap");
				%>
				<form action="/admin/timeline" method="post">
					<input type="submit" value="RT,Favする" formmethod="post"
						formaction="/admin/timeline">
					<%
						for (long timelineStatusId : timelineStatusIdList) {
							TwitterStatusData[] tmpStatusDatas = timelineStatusDataMap.get(timelineStatusId);
					%>
					<%
						if (tmpStatusDatas[0].isRetweetStatus()) {
					%>
					<fieldset>
						<legend>リツイート</legend>
						<%
							TwitterUserData twitterUserData = tmpStatusDatas[0].getStatusUserData();
						%>
						<div class="article-account-box">
							<div class="article-account-row">
								<div>
									<img src=<%=twitterUserData.getUserImageURL()%> width="64"
										height="64">
								</div>
								<div><%=twitterUserData.getUserName()%>(@<%=twitterUserData.getUserScreenName()%>)
								</div>
							</div>
							<div class="article-account-row">
								<div>
									ツイート数:<%=twitterUserData.getUserStatusesCount()%></div>
								<div>
									お気に入り数:<%=twitterUserData.getUserFavouritesCount()%></div>
								<div>
									フォロー数:<%=twitterUserData.getUserFriendsCount()%></div>
								<div>
									フォロワー数:<%=twitterUserData.getUserFollowersCount()%></div>
							</div>
						</div>
						<fieldset>
							<%
								twitterUserData = tmpStatusDatas[1].getStatusUserData();
							%>
							<div class="article-account-box">
								<div class="article-account-row">
									<div>
										<img src=<%=twitterUserData.getUserImageURL()%> width="64"
											height="64">
									</div>
									<div><%=twitterUserData.getUserName()%>(@<%=twitterUserData.getUserScreenName()%>)
									</div>
								</div>
								<div class="article-account-row">
									<div>
										ツイート数:<%=twitterUserData.getUserStatusesCount()%></div>
									<div>
										お気に入り数:<%=twitterUserData.getUserFavouritesCount()%></div>
									<div>
										フォロー数:<%=twitterUserData.getUserFriendsCount()%></div>
									<div>
										フォロワー数:<%=twitterUserData.getUserFollowersCount()%></div>
								</div>
							</div>
							<%=TwitterStatusTextData.toStringStatusText(tmpStatusDatas[1].getStatusTextData())%>
							<%=TwitterStatusTextData.toStringStatusMediaURLList(tmpStatusDatas[1].getStatusTextData(),
							tmpStatusDatas[1].getStatusId())%>
							<%=TwitterStatusReactionsData.toStringStatusReactions(
							tmpStatusDatas[1].getStatusReactionsData(), tmpStatusDatas[1].getStatusId())%>
							<%=TwitterStatusData.toStringStatusCreateTime(tmpStatusDatas[1])%>
						</fieldset>
					</fieldset>
					<%
						} else if (tmpStatusDatas[0].isQuotedStatus()) {
					%>
					<fieldset>
						<legend>引用リツイート</legend>
						<%
							TwitterUserData twitterUserData = tmpStatusDatas[0].getStatusUserData();
						%>
						<div class="article-account-box">
							<div class="article-account-row">
								<div>
									<img src=<%=twitterUserData.getUserImageURL()%> width="64"
										height="64">
								</div>
								<div><%=twitterUserData.getUserName()%>(@<%=twitterUserData.getUserScreenName()%>)
								</div>
							</div>
							<div class="article-account-row">
								<div>
									ツイート数:<%=twitterUserData.getUserStatusesCount()%></div>
								<div>
									お気に入り数:<%=twitterUserData.getUserFavouritesCount()%></div>
								<div>
									フォロー数:<%=twitterUserData.getUserFriendsCount()%></div>
								<div>
									フォロワー数:<%=twitterUserData.getUserFollowersCount()%></div>
							</div>
						</div>
						<%=TwitterStatusTextData.toStringStatusText(tmpStatusDatas[0].getStatusTextData())%>
						<%=TwitterStatusTextData.toStringStatusMediaURLList(tmpStatusDatas[0].getStatusTextData(),
							tmpStatusDatas[0].getStatusId())%>
						<%=TwitterStatusData.toStringStatusCreateTime(tmpStatusDatas[0])%>
						<fieldset>
							<%
								twitterUserData = tmpStatusDatas[1].getStatusUserData();
							%>
							<div class="article-account-box">
								<div class="article-account-row">
									<div>
										<img src=<%=twitterUserData.getUserImageURL()%> width="64"
											height="64">
									</div>
									<div><%=twitterUserData.getUserName()%>(@<%=twitterUserData.getUserScreenName()%>)
									</div>
								</div>
								<div class="article-account-row">
									<div>
										ツイート数:<%=twitterUserData.getUserStatusesCount()%></div>
									<div>
										お気に入り数:<%=twitterUserData.getUserFavouritesCount()%></div>
									<div>
										フォロー数:<%=twitterUserData.getUserFriendsCount()%></div>
									<div>
										フォロワー数:<%=twitterUserData.getUserFollowersCount()%></div>
								</div>
							</div>
							<%=TwitterStatusTextData.toStringStatusText(tmpStatusDatas[1].getStatusTextData())%>
							<%=TwitterStatusTextData.toStringStatusMediaURLList(tmpStatusDatas[1].getStatusTextData(),
							tmpStatusDatas[1].getStatusId())%>
							<%=TwitterStatusReactionsData.toStringStatusReactions(
							tmpStatusDatas[1].getStatusReactionsData(), tmpStatusDatas[1].getStatusId())%>
							<%=TwitterStatusData.toStringStatusCreateTime(tmpStatusDatas[1])%>
						</fieldset>
					</fieldset>
					<%
						} else {
					%>
					<fieldset>
						<legend>通常ツイート</legend>
						<%
							TwitterUserData twitterUserData = tmpStatusDatas[0].getStatusUserData();
						%>
						<div class="article-account-box">
							<div class="article-account-row">
								<div>
									<img src=<%=twitterUserData.getUserImageURL()%> width="64"
										height="64">
								</div>
								<div><%=twitterUserData.getUserName()%>(@<%=twitterUserData.getUserScreenName()%>)
								</div>
							</div>
							<div class="article-account-row">
								<div>
									ツイート数:<%=twitterUserData.getUserStatusesCount()%></div>
								<div>
									お気に入り数:<%=twitterUserData.getUserFavouritesCount()%></div>
								<div>
									フォロー数:<%=twitterUserData.getUserFriendsCount()%></div>
								<div>
									フォロワー数:<%=twitterUserData.getUserFollowersCount()%></div>
							</div>
						</div>
						<%=TwitterStatusTextData.toStringStatusText(tmpStatusDatas[0].getStatusTextData())%>
						<%=TwitterStatusTextData.toStringStatusMediaURLList(tmpStatusDatas[0].getStatusTextData(),
							tmpStatusDatas[0].getStatusId())%>
						<%=TwitterStatusReactionsData.toStringStatusReactions(
							tmpStatusDatas[0].getStatusReactionsData(), tmpStatusDatas[0].getStatusId())%>
						<%=TwitterStatusData.toStringStatusCreateTime(tmpStatusDatas[0])%>
					</fieldset>
					<%
						}
					%>
					<%
						}
					%>
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