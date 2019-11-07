package twitterapp;

import java.util.ArrayList;
import java.util.List;

import twitter4j.MediaEntity;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.URLEntity;

public class TwitterStatusTextData {

	private String statusText;

	private List<String> statusMediaURLList;

	private List<String> statusURLList;

	public TwitterStatusTextData(Status status) throws TwitterException {
		this.statusText = status.getText();
		this.statusMediaURLList = new ArrayList<>();
		this.setStatusMediaURLList(status);
		this.statusURLList = new ArrayList<>();
		this.setStatusURLList(status);
	}

	private final void setStatusMediaURLList(Status status) throws TwitterException {
		MediaEntity[] mediaEntities = status.getMediaEntities();
		for (MediaEntity mediaEntity : mediaEntities) {
			this.statusMediaURLList.add(mediaEntity.getMediaURL());
		}
	}

	private final void setStatusURLList(Status status) throws TwitterException {
		URLEntity[] urlEntities = status.getURLEntities();
		for (URLEntity urlEntity : urlEntities) {
			this.statusURLList.add(urlEntity.getURL());
		}
	}

	public final String getStatusText() {
		return this.statusText;
	}

	public final List<String> getStatusMediaURList() {
		return this.statusMediaURLList;
	}

	public final List<String> getStatusURLList() {
		return this.statusURLList;
	}

	/**
	 * @param twitterStatusTextData
	 * @return HTML
	 *         <p>
	 *         twitterStatusTextData.statusText
	 *         <a target="_blank" href="statusURL">statusURL</a>
	 *         </p>
	 */
	public static final String toStringStatusText(TwitterStatusTextData twitterStatusTextData) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<p>");
		stringBuilder.append(twitterStatusTextData.statusText);
		for (String statusURL : twitterStatusTextData.statusURLList) {
			int stringIndex = stringBuilder.indexOf(statusURL);
			stringBuilder.insert(stringIndex + statusURL.length(), "</a>");
			stringBuilder.insert(stringIndex, "<a target=\"_blank\" href=\"" + statusURL + "\">");
		}
		stringBuilder.append("</p>");

		return stringBuilder.toString();
	}

	/**
	 * @param twitterStatusTextData
	 * @param statusId
	 * @return HTML
	 *         <p>
	 *         <a data-fancybox="statusId" href="mediaURL"><img src="mediaURL" width
	 *         ="256" height="auto"></a>
	 *         </p>
	 */
	public static final String toStringStatusMediaURLList(TwitterStatusTextData twitterStatusTextData, long statusId) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<p>");
		for (String mediaURL : twitterStatusTextData.statusMediaURLList) {
			stringBuilder.append("<a data-fancybox=\"");
			stringBuilder.append(statusId);
			stringBuilder.append("\" href=\"");
			stringBuilder.append(mediaURL);
			stringBuilder.append("\"><img src=\"");
			stringBuilder.append(mediaURL);
			stringBuilder.append("\" width=\"256\" height=\"auto\"></a>");
		}
		stringBuilder.append("</p>");

		return stringBuilder.toString();
	}

	@Override
	public final String toString() {
		return "statusText:" + this.statusText + "\n" + "statusMediaURLList:" + this.statusMediaURLList + "\n"
				+ "statusURLList:" + this.statusURLList;
	}
}
