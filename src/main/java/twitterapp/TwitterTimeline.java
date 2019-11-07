package twitterapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import twitter4j.TwitterException;
import twitter4j.Status;

public final class TwitterTimeline extends TwitterCore {

	private List<Status> timelineStatusList;

	private List<Long> timelineStatusIdList;

	private Map<Long, TwitterStatusData[]> timelineStatusDataMap;

	public TwitterTimeline() throws TwitterException {
		super();
		this.timelineStatusIdList = new ArrayList<>();
		this.timelineStatusDataMap = new HashMap<>();
	}

	public final void setTimelineStatusList(String timelineName, String timelineTargetScreenName)
			throws TwitterException {
		if ((timelineName == null) || (timelineName.isEmpty())) {
			timelineName = "HomeTimeline";
		}
		if ((timelineTargetScreenName == null) || (timelineTargetScreenName.isEmpty())) {
			timelineTargetScreenName = this.getMyUserData().getUserScreenName();
		}
		if (timelineName.equals("HomeTimeline")) {
			this.timelineStatusList = twitter.getHomeTimeline();
		} else if (timelineName.equals("MyUserTimeline")) {
			this.timelineStatusList = twitter.getUserTimeline();
		} else if (timelineName.equals("MyFavorites")) {
			this.timelineStatusList = twitter.getFavorites();
		} else if (timelineName.equals("MyMentions")) {
			this.timelineStatusList = twitter.getMentionsTimeline();
		} else if (timelineName.equals("UserTimeline")) {
			this.timelineStatusList = twitter.getUserTimeline(timelineTargetScreenName);
		} else if (timelineName.equals("UserFavorites")) {
			this.timelineStatusList = twitter.getFavorites(timelineTargetScreenName);
		}
		this.setTimelineStatusDataMap();
	}

	private final void setTimelineStatusDataMap() throws TwitterException {
		for (Status timelineStatus : this.timelineStatusList) {
			Status quotedStatus = timelineStatus.getQuotedStatus();
			long timelineStatusId = timelineStatus.getId();
			this.timelineStatusIdList.add(timelineStatusId);
			if (timelineStatus.isRetweet()) {
				TwitterStatusData[] twitterStatusDatas = new TwitterStatusData[2];
				twitterStatusDatas[0] = new TwitterStatusData(timelineStatus);
				twitterStatusDatas[1] = new TwitterStatusData(timelineStatus.getRetweetedStatus());
				this.timelineStatusDataMap.put(timelineStatusId, twitterStatusDatas);
			} else if (quotedStatus != null) {
				TwitterStatusData[] twitterStatusDatas = new TwitterStatusData[2];
				twitterStatusDatas[0] = new TwitterStatusData(timelineStatus);
				twitterStatusDatas[1] = new TwitterStatusData(quotedStatus);
				this.timelineStatusDataMap.put(timelineStatusId, twitterStatusDatas);
			} else {
				TwitterStatusData[] twitterStatusDatas = new TwitterStatusData[1];
				twitterStatusDatas[0] = new TwitterStatusData(timelineStatus);
				this.timelineStatusDataMap.put(timelineStatusId, twitterStatusDatas);
			}
		}
	}

	public final void doRetweetStatus(long statusId, String retweetStatus) throws TwitterException {
		Status status = twitter.showStatus(statusId);
		if (status.isRetweeted()) {
			if (retweetStatus.equals("undoRetweet")) {
				twitter.unRetweetStatus(statusId);
			}
		} else {
			if (retweetStatus.equals("doRetweet")) {
				twitter.retweetStatus(statusId);
			}
		}
	}

	public final void doFavoriteStatus(long statusId, String favorityStatus) throws TwitterException {
		Status status = twitter.showStatus(statusId);
		if (status.isFavorited()) {
			if (favorityStatus.equals("undoFavority")) {
				twitter.destroyFavorite(statusId);
			}
		} else {
			if (favorityStatus.equals("doFavority")) {
				twitter.createFavorite(statusId);
			}
		}
	}

	public final List<Status> getTimelineStatusList() {
		return this.timelineStatusList;
	}

	public final List<Long> getTimelineStatusIdList() {
		return this.timelineStatusIdList;
	}

	public final Map<Long, TwitterStatusData[]> getTimelineStatusDataMap() {
		return this.timelineStatusDataMap;
	}
}
