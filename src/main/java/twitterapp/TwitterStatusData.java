package twitterapp;

import java.util.Date;

import twitter4j.Status;
import twitter4j.TwitterException;

public class TwitterStatusData {

	private boolean isRetweetStatus;

	private boolean isQuotedStatus;

	private long statusId;

	private TwitterUserData statusUserData;

	private TwitterStatusTextData statusTextData;

	private TwitterStatusReactionsData statusReactionsData;

	private Date statusCreateTime;

	public TwitterStatusData(Status status) throws TwitterException {
		this.isRetweetStatus = status.isRetweet();
		this.setIsQuotedStatus(status);
		this.statusId = status.getId();
		this.statusUserData = new TwitterUserData(status.getUser());
		this.statusTextData = new TwitterStatusTextData(status);
		this.statusReactionsData = new TwitterStatusReactionsData(status);
		this.statusCreateTime = status.getCreatedAt();
	}

	private final void setIsQuotedStatus(Status status) throws TwitterException {
		Status quotedStatus = status.getQuotedStatus();
		if (quotedStatus == null) {
			this.isQuotedStatus = false;
		} else {
			this.isQuotedStatus = true;
		}
	}

	public final boolean isRetweetStatus() {
		return this.isRetweetStatus;
	}

	public final boolean isQuotedStatus() {
		return this.isQuotedStatus;
	}

	public final long getStatusId() {
		return this.statusId;
	}

	public final TwitterUserData getStatusUserData() {
		return this.statusUserData;
	}

	public final TwitterStatusTextData getStatusTextData() {
		return this.statusTextData;
	}

	public final TwitterStatusReactionsData getStatusReactionsData() {
		return this.statusReactionsData;
	}

	public final Date getStatusCreatedStatus() {
		return this.statusCreateTime;
	}

	/**
	 * @return HTML
	 *         <p>
	 *         twitterStatusData.statusCreateTime
	 *         </p>
	 */
	public static final String toStringStatusCreateTime(TwitterStatusData twitterStatusData) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<p>CreateTime:");
		stringBuilder.append(twitterStatusData.statusCreateTime);
		stringBuilder.append("</p>");

		return stringBuilder.toString();
	}

	@Override
	public final String toString() {
		return "isRetweetStatus:" + this.isRetweetStatus + "\n" + "isQuotedStatus:" + this.isQuotedStatus + "\n"
				+ "statusId:" + statusId + "\n" + "statusUserData:" + this.statusUserData.toString() + "\n"
				+ "statusTextData:" + this.statusTextData.toString() + "\n" + "statusReactionsData:"
				+ this.statusReactionsData.toString() + "\n" + "statusCreateTime:" + this.statusCreateTime;
	}
}
