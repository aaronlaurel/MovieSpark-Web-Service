package edu.cmu.mgmt.utils;

import com.google.gson.Gson;

import edu.cmu.mgmt.model.twitter.Oembed;
import edu.cmu.mgmt.model.twitter.Tweet;
import edu.cmu.mgmt.model.twitter.TweetList;
import edu.cmu.mgmt.model.twitter.TwitterUser;

public class JsonUtils {
	private static Gson gson = new Gson();

	public static TweetList jsonToTweetList(String response) {
		TweetList tweets = null;
		if (response != null && !response.isEmpty()) {
			tweets = gson.fromJson(response, TweetList.class);
		}
		return tweets;
	}

	public static TwitterUser jsonToUser(String response) {
		TwitterUser twitterUser = null;
		if (response != null && !response.isEmpty()) {
			twitterUser = gson.fromJson(response, TwitterUser.class);
		}
		return twitterUser;
	}

	public static Tweet jsonToTweet(String response) {
		Tweet tweet = null;
		if (response != null && !response.isEmpty()) {
			tweet = gson.fromJson(response, Tweet.class);
		}
		return tweet;
	}

	public static Oembed jsonToOembed(String response) {
		Oembed oembed = null;
		if (response != null && !response.isEmpty()) {
			oembed = gson.fromJson(response, Oembed.class);
		}
		return oembed;
	}

}
