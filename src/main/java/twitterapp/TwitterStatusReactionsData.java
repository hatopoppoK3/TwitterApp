package twitterapp;

import twitter4j.Status;
import twitter4j.TwitterException;

public class TwitterStatusReactionsData {
	private int statusRetweetCount;

	private int statusFavorityCount;

	private boolean isStatusRetweeted;

	private boolean isStatusFavorited;

	public TwitterStatusReactionsData(Status status) throws TwitterException {
		this.statusRetweetCount = status.getRetweetCount();
		this.statusFavorityCount = status.getFavoriteCount();
		this.isStatusRetweeted = status.isRetweeted();
		this.isStatusFavorited = status.isFavorited();
	}

	public final int getStatusRetweetCount() {
		return this.statusRetweetCount;
	}

	public final int getStatusFavorityCount() {
		return this.statusFavorityCount;
	}

	public final boolean isStatusRetweeted() {
		return this.isStatusRetweeted;
	}

	public final boolean isStatusFavorited() {
		return this.isStatusFavorited;
	}

	/**
	 * @param statusId
	 * @return HTML
	 *         <input type="hidden" name="timeline_status_id" value="statusId" >
	 */
	private static final String toStringStatusReactionsId(long statusId) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<input type=\"hidden\" name=\"timeline_status_id\" value=\"");
		stringBuilder.append(statusId);
		stringBuilder.append("\">");

		return stringBuilder.toString();
	}

	/**
	 * @param twitterStatusReactionsData
	 * @param statusId
	 * @return HTML
	 *         <p>
	 *         <label>RTする<input type="radio" name="0statusId" value=
	 *         "doRetweet"></label><label>RTしない<input type="radio" name= "0statusId"
	 *         value="undoRetweet" checked=
	 *         "checked"></label>RT:twitterStatusReactionsData.statusRetweetCount
	 *         </p>
	 */
	private static final String toStringStatusRetweetReactions(TwitterStatusReactionsData twitterStatusReactionsData,
			long statusId) {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("<p><label>RTする<input type=\"radio\" name=\"0");
		stringBuilder.append(statusId);
		stringBuilder.append("\" value=\"doRetweet\"");
		if (twitterStatusReactionsData.isStatusRetweeted) {
			stringBuilder.append(" checked=\"checked\"");
		}
		stringBuilder.append("></label>");

		stringBuilder.append("<label>RTしない<input type=\"radio\" name=\"0");
		stringBuilder.append(statusId);
		stringBuilder.append("\" value=\"undoRetweet\"");
		if (!twitterStatusReactionsData.isStatusRetweeted) {
			stringBuilder.append(" checked=\"checked\"");
		}
		stringBuilder.append("></label>");

		stringBuilder.append("RT:");
		stringBuilder.append(twitterStatusReactionsData.statusRetweetCount);
		stringBuilder.append("</p>");

		return stringBuilder.toString();
	}

	/**
	 * @param twitterStatusReactionsData
	 * @param statusId
	 * @return HTML
	 *         <p>
	 *         <label>ふぁぼする<input type="radio" name="1statusId" value=
	 *         "doFavority"></label><label>ふぁぼしない<input type="radio" name=
	 *         "1statusId" value="undoFavority" checked=
	 *         "checked"></label>ふぁぼ:twitterStatusReactionsData.statusFavorityCount
	 *         </p>
	 * 
	 */
	private static final String toStringStatusFavorityReactions(TwitterStatusReactionsData twitterStatusReactionsData,
			long statusId) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<p><label>ふぁぼする<input type=\"radio\" name=\"1");
		stringBuilder.append(statusId);
		stringBuilder.append("\" value=\"doFavority\"");
		if (twitterStatusReactionsData.isStatusFavorited) {
			stringBuilder.append(" checked=\"checked\"");
		}
		stringBuilder.append("></label>");

		stringBuilder.append("<label>ふぁぼしない<input type=\"radio\" name=\"1");
		stringBuilder.append(statusId);
		stringBuilder.append("\" value=\"undoFavority\"");
		if (!twitterStatusReactionsData.isStatusFavorited) {
			stringBuilder.append(" checked=\"checked\"");
		}
		stringBuilder.append("></label>");

		stringBuilder.append("ふぁぼ:");
		stringBuilder.append(twitterStatusReactionsData.statusFavorityCount);
		stringBuilder.append("</p>");

		return stringBuilder.toString();
	}

	public static final String toStringStatusReactions(TwitterStatusReactionsData twitterStatusReactionsData,
			long statusId) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(TwitterStatusReactionsData.toStringStatusReactionsId(statusId));
		stringBuilder.append(
				TwitterStatusReactionsData.toStringStatusRetweetReactions(twitterStatusReactionsData, statusId));
		stringBuilder.append(
				TwitterStatusReactionsData.toStringStatusFavorityReactions(twitterStatusReactionsData, statusId));

		return stringBuilder.toString();
	}

	@Override
	public final String toString() {
		return "statusRetweetCount:" + this.statusRetweetCount + "\n" + "statusFavorityCount:"
				+ this.statusFavorityCount + "\n" + "isStatusRetweeted:" + this.isStatusRetweeted + "\n"
				+ "isStatusFavorited:" + this.isStatusFavorited;
	}
}
