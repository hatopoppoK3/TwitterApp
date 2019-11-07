package twitterapp;

import twitter4j.TwitterException;

public final class TwitterTweet extends TwitterCore {

	public TwitterTweet() throws TwitterException {
		super();
	}

	/**
	 * ツイートの実行
	 * @param tweet
	 * @throws TwitterException (文字数が140以上,null文字,空文字の場合投げる)
	 */
	public final void updateStatus(String tweet) throws TwitterException {
		if((tweet == null) || (tweet.isEmpty())) {
			throw new TwitterException("text error");
		}
		if(tweet.length()>140) {
			throw new TwitterException("text error");
		}
		this.twitter.updateStatus(tweet);
	}

	public final void deleteStatus(long StatusId) throws TwitterException {
		this.twitter.destroyStatus(StatusId);
	}
}
