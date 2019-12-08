package twitterappservlet;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.TwitterException;
import twitterapp.TwitterTimeline;
import twitterapp.TwitterUserData;

@WebServlet(name = "TwitterTimelineServlet", urlPatterns = { "/admin/timeline" })
public class TwitterTimelineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger TimelineLog = Logger.getLogger(TwitterTimeline.class.getName());

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		try {
			/**
			 * timelineName:jspから表示するタイムラインの種類を取得 timelineTargetScreenName:jspから表示するユーザの取得
			 */
			String timelineName = request.getParameter("timeline_name");
			String timelineTargetScreenName = request.getParameter("timeline_target_screen_name");

			TwitterTimeline twitterTimeline = new TwitterTimeline();
			twitterTimeline.setTimelineStatusList(timelineName, timelineTargetScreenName);

			/**
			 * UserName取得,UserImageURLの取得
			 */
			request.setAttribute("toStringMyUserData",
					TwitterUserData.toStringUserData(twitterTimeline.getMyUserData()));
			/**
			 * 取得した諸情報をtimeline.jspの変数にセット
			 */
			request.setAttribute("timelineStatusIdList", twitterTimeline.getTimelineStatusIdList());
			request.setAttribute("timelineStatusDataMap", twitterTimeline.getTimelineStatusDataMap());
			/**
			 * timeline.jspにforward
			 */
			RequestDispatcher dispatcher = request.getRequestDispatcher("/timeline.jsp");
			dispatcher.forward(request, response);

			/**
			 * アクセスできたログを記録
			 */
			TimelineLog.info("TimelineServlet access success." + timelineName + "displayed.");
		} catch (TwitterException e) {
			/**
			 * エラーになった機能およびメッセージをセット
			 */
			request.setAttribute("twitterService", "Timeline機能");
			request.setAttribute("errorMessage", "エラーが発生しました。時間をおいて試してください。");

			/**
			 * failure.jspにforward
			 */
			RequestDispatcher dispatcher = request.getRequestDispatcher("/failure.jsp");
			dispatcher.forward(request, response);

			/**
			 * アクセスに失敗したログを記録
			 */
			TimelineLog.info("TimelineServlet access failure.failure.jsp sendRedirect.");
			e.printStackTrace();
		}
	}

	/**
	 * doPost:timeline.jspにおけるRT,ふぁぼ機能を実装する
	 * <input type="hidden" name="timeline_status_id" value="statusId">
	 * timelineに表示されているstatusIdを受け取る
	 * <input type="radio" name="0statusId" value="doRetweet or undoRetweet" checked
	 * ="checked"> RetweetについてのnameはstatusIdの先頭に0を加えたものである。入力によってdo or
	 * undoを決定する。また現在の状態に応じてcheckedを加える
	 * <input type="radio" name="1statusId" value="doFavority or undoFavority"
	 * checked="checked"> FavorityについてのnameはstatusIdの先頭に1を加えたものである。入力によってdo or
	 * undoを決定する。また現在の状態に応じてcheckedを加える
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			/**
			 * timelineStatusIdStrings:<input type="hidden" name="timeline_status_id" value=
			 * "statusId">を受け取る
			 */
			String[] timelineStatusIdStrings = request.getParameterValues("timeline_status_id");
			TwitterTimeline twitterTimeline = new TwitterTimeline();

			/**
			 * 現在表示されているtimelineについて入力を受け取る
			 */
			for (String timelineStatusIdString : timelineStatusIdStrings) {
				long timelineStatusId = new Long(timelineStatusIdString);
				StringBuilder stringBuilder = new StringBuilder();

				/**
				 * stringBuilderで0statusIdの形を作成。
				 * <input type="radio" name="0statusId" value="doRetweet or undoRetweet" checked
				 * ="checked"> の入力をretweetValueで受け取る。 Retweetをtwittertimelineのインスタンスメソッドで実装する
				 */
				stringBuilder.append(0);
				stringBuilder.append(timelineStatusId);
				String retweetValue = request.getParameter(stringBuilder.toString());
				twitterTimeline.doRetweetStatus(timelineStatusId, retweetValue);

				/**
				 * stringBuilderで1statusIdの形を作成。
				 * <input type="radio" name="1statusId" value="doFavority or undoFavority"
				 * checked="checked"> の入力をfavorityValueで受け取る。
				 * Favorityをtwittertimelineのインスタンスメソッドで実装する
				 */
				stringBuilder.replace(0, 1, "1");
				String favorityValue = request.getParameter(stringBuilder.toString());
				twitterTimeline.doFavoriteStatus(timelineStatusId, favorityValue);
			}

			/**
			 * リツイート、ふぁぼできたログを記録
			 */
			TimelineLog.info("TimelineServlet doRetweet and do Favority success.");

			doGet(request, response);
		} catch (TwitterException e) {
			/**
			 * エラーになった機能およびメッセージをセット
			 */
			request.setAttribute("twitterService", "Timeline機能");
			request.setAttribute("errorMessage", "エラーが発生しました。時間をおいて試してください。");

			/**
			 * failure.jspにforward
			 */
			RequestDispatcher dispatcher = request.getRequestDispatcher("/failure.jsp");
			dispatcher.forward(request, response);

			/**
			 * エラーログの記録
			 */
			TimelineLog.info("TimelineServlet doRetweet and do Favority success.");
			e.printStackTrace();
		}
	}
}