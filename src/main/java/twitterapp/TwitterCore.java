package twitterapp;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public abstract class TwitterCore {
	protected Twitter twitter;

	private final TwitterUserData myUserData;
	
	/**
	 * 
	 * @throws TwitterException
	 */
	public TwitterCore() throws TwitterException {
		this.twitter = TwitterFactory.getSingleton();
		this.myUserData = new TwitterUserData(twitter.showUser(twitter.getId()));
	}

	public final TwitterUserData getMyUserData() {
		return this.myUserData;
	}
}
