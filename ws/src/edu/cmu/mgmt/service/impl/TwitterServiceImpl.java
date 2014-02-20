package edu.cmu.mgmt.service.impl;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import edu.cmu.mgmt.model.twitter.Oembed;
import edu.cmu.mgmt.model.twitter.Tweet;
import edu.cmu.mgmt.model.twitter.TweetList;
import edu.cmu.mgmt.model.twitter.TwitterUser;
import edu.cmu.mgmt.service.TwitterService;
import edu.cmu.mgmt.utils.JsonUtils;

public class TwitterServiceImpl implements TwitterService {
	private static final String API_HOST = "https://api.twitter.com/1.1/";

	public TwitterUser getTwitterUser(Token accessToken,
			OAuthService twitterOAuthService) {
		OAuthRequest oAuthRequest = new OAuthRequest(Verb.GET, API_HOST
				+ "account/verify_credentials.json");
		twitterOAuthService.signRequest(accessToken, oAuthRequest);
		Response response = oAuthRequest.send();
		TwitterUser user = JsonUtils.jsonToUser(response.getBody());
		return user;
	}

	public TweetList getUserTweets(Token accessToken,
			OAuthService twitterOAuthService, String username) {
		OAuthRequest oAuthRequest = new OAuthRequest(Verb.GET, API_HOST
				+ "statuses/user_timeline.json");
		oAuthRequest.addQuerystringParameter("screen_name", username);
		twitterOAuthService.signRequest(accessToken, oAuthRequest);
		Response response = oAuthRequest.send();
		TweetList tweets = JsonUtils.jsonToTweetList(response.getBody());
		return tweets;
	}

	public Tweet createTweet(Token accessToken,
			OAuthService twitterOAuthService, String status) {
		OAuthRequest oAuthRequest = new OAuthRequest(Verb.POST, API_HOST
				+ "statuses/update.json");
		oAuthRequest.addBodyParameter("status", status);
		twitterOAuthService.signRequest(accessToken, oAuthRequest);
		Response response = oAuthRequest.send();
		Tweet tweet = JsonUtils.jsonToTweet(response.getBody());
		return tweet;
	}

	public Tweet replyTweet(Token accessToken,
			OAuthService twitterOAuthService, String status, Tweet t) {
		OAuthRequest oAuthRequest = new OAuthRequest(Verb.POST, API_HOST
				+ "statuses/update.json");
		oAuthRequest.addBodyParameter("status", status);
		oAuthRequest.addBodyParameter("in_reply_to_status_id", t.getId());
		twitterOAuthService.signRequest(accessToken, oAuthRequest);
		Response response = oAuthRequest.send();
		Tweet tweet = JsonUtils.jsonToTweet(response.getBody());
		return tweet;
	}

	public Tweet reTweet(Token accessToken, OAuthService twitterOAuthService,
			String tid, String status) {
		OAuthRequest oAuthRequest = new OAuthRequest(Verb.POST, API_HOST
				+ "statuses/retweet/" + tid + ".json");
		oAuthRequest.addBodyParameter("status", status);
		twitterOAuthService.signRequest(accessToken, oAuthRequest);
		Response response = oAuthRequest.send();
		Tweet tweet = JsonUtils.jsonToTweet(response.getBody());
		return tweet;
	}

	public Oembed getOemTweet(Token accessToken,
			OAuthService twitterOAuthService, String tweetId) {
		OAuthRequest oAuthRequest = new OAuthRequest(Verb.GET, API_HOST
				+ "statuses/oembed.json");
		oAuthRequest.addQuerystringParameter("id", tweetId);
		twitterOAuthService.signRequest(accessToken, oAuthRequest);
		Response response = oAuthRequest.send();
		Oembed oembed = JsonUtils.jsonToOembed(response.getBody());
		return oembed;
	}

}
