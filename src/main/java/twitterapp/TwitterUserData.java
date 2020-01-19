package twitterapp;

import twitter4j.TwitterException;
import twitter4j.User;

public class TwitterUserData {

	private long userId;

	private String userScreenName;

	private String userName;

	private String userImageURL;

	private String userBannerImageURL;

	private int userStatusesCount;

	private int userFavouritesCount;

	private int userFriendsCount;

	private int userFollowersCount;

	public TwitterUserData(User user) throws TwitterException {
		this.userId = user.getId();
		this.userScreenName = user.getScreenName();
		this.userName = user.getName();
		this.userImageURL = user.getProfileImageURLHttps();
		this.userBannerImageURL = user.getProfileBackgroundImageUrlHttps();
		this.userStatusesCount = user.getStatusesCount();
		this.userFavouritesCount = user.getFavouritesCount();
		this.userFriendsCount = user.getFriendsCount();
		this.userFollowersCount = user.getFollowersCount();
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

	public final int getUserStatusesCount() {
		return this.userStatusesCount;
	}

	public final int getUserFavouritesCount() {
		return this.userFavouritesCount;
	}

	public final int getUserFriendsCount() {
		return this.userFriendsCount;
	}

	public final int getUserFollowersCount() {
		return this.userFollowersCount;
	}

	@Override
	public final String toString() {
		return "userId:" + this.userId + "\n" + "userScreenName:" + this.userScreenName + "\n" + "userName:"
				+ this.userName + "\n" + "userImageURL:" + this.userImageURL + "\n" + "userBannerImageURL:"
				+ this.userBannerImageURL + "\n" + "userStatusesCount:" + this.userStatusesCount + "\n"
				+ "userFavouritesCount:" + this.userFavouritesCount + "\n" + "userFriendsCount:" + this.userFriendsCount
				+ "\n" + "userFollowersCount:" + this.userFollowersCount + "\n";
	}
}
