package twitterapp;

import twitter4j.TwitterException;
import twitter4j.User;

public class TwitterUserData {

	private long userId;

	private String userScreenName;

	private String userName;

	private String userImageURL;

	private String userBannerImageURL;

	public TwitterUserData(User user) throws TwitterException {
		this.userId = user.getId();
		this.userScreenName = user.getScreenName();
		this.userName = user.getName();
		this.userImageURL = user.getProfileImageURLHttps();
		this.userBannerImageURL = user.getProfileBackgroundImageUrlHttps();
	}

	public final long getUserId() throws TwitterException {
		return this.userId;
	}

	public final String getUserScreenName() {
		return this.userScreenName;
	}

	public final String getUserName() {
		return this.userName;
	}

	public final String getUserImageURL() {
		return this.userImageURL;
	}

	public final String getUserBannerImgeURL() {
		return this.userBannerImageURL;
	}

	/**
	 * @param twitterUserData
	 * @return HTML
	 *         <p>
	 *         <img src="twitterUserData.userImageURL" width="64" height=
	 *         "64">twitterUserData.userName(@twitterUserData.userScreenName)
	 *         </p>
	 */
	public static final String toStringUserData(TwitterUserData twitterUserData) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<p><img src=\"");
		stringBuilder.append(twitterUserData.userImageURL);
		stringBuilder.append("\" width=\"64\" height=\"64\">");
		stringBuilder.append(twitterUserData.userName);
		stringBuilder.append("(@");
		stringBuilder.append(twitterUserData.userScreenName);
		stringBuilder.append(")</p>");

		return stringBuilder.toString();
	}

	@Override
	public final String toString() {
		return "userId:" + this.userId + "\n" + "userScreenName:" + this.userScreenName + "\n" + "userName:"
				+ this.userName + "\n" + "userImageURL:" + this.userImageURL + "\n" + "userBannerImageURL:"
				+ this.userBannerImageURL;
	}
}
