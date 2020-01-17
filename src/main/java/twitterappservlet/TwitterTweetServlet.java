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
import twitterapp.TwitterTweet;

@WebServlet(name = "TwitterTweetServlet", urlPatterns = { "/admin/tweet" })
public class TwitterTweetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger TweetLog = Logger.getLogger(TwitterTweet.class.getName());;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		try {
			TwitterTweet twitterTweet = new TwitterTweet();

			/**
			 * myUserDataをset
			 */
			request.setAttribute("myUserData", twitterTweet.getMyUserData());

			/**
			 * 問題なければtweet.jspにフォワード
			 */
			RequestDispatcher dispatcher = request.getRequestDispatcher("/tweet.jsp");
			dispatcher.forward(request, response);

			/**
			 * アクセスログの記録
			 */
			TweetLog.info("TweetServlet access success.");
		} catch (TwitterException e) {
			/**
			 * どの機能でエラーを起こし、エラーメッセージのset
			 */
			request.setAttribute("twitterService", "ツイート機能");
			request.setAttribute("errorMessage", "エラーが発生しました。時間をおいて試してください。");

			/**
			 * failure.jspにフォワード
			 */
			RequestDispatcher dispatcher = request.getRequestDispatcher("/failure.jsp");
			dispatcher.forward(request, response);

			/**
			 * エラーログの記録
			 */
			TweetLog.info("TweetServlet access failure.failure.jsp sendRedirect.");
			e.printStackTrace();
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			TwitterTweet twitterTweet = new TwitterTweet();

			/**
			 * tweetテキストの取得
			 */
			String tweet = request.getParameter("tweet");

			/**
			 * ツイートの実行
			 */
			twitterTweet.updateStatus(tweet);

			/**
			 * 成功したメッセージとその機能のset
			 */
			request.setAttribute("twitterService", "ツイート機能");
			request.setAttribute("message", "ツイートに成功しました。");

			/**
			 * success.jspにフォワード
			 */
			RequestDispatcher dispatcher = request.getRequestDispatcher("/success.jsp");
			dispatcher.forward(request, response);

			/**
			 * 成功したログを記録
			 */
			TweetLog.info("TweetServlet tweet success.");
		} catch (TwitterException e) {
			/**
			 　　エラーになった機能とエラーメッセージのset
			 */
			request.setAttribute("twitterService", "ツイート機能");
			request.setAttribute("errorMessage", "エラーが発生しました。時間をおいて試してください。");

			/**
			 * failure.jspにフォワード
			 */
			RequestDispatcher dispatcher = request.getRequestDispatcher("/failure.jsp");
			dispatcher.forward(request, response);

			/**
			 * エラーログを記録
			 */
			TweetLog.info("TweetServlet tweet failure.failure.jsp sendRedirect.");
		}
	}
}