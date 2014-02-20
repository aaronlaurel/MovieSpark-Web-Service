package edu.cmu.mgmt.service;

import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

import edu.cmu.mgmt.model.twitter.Oembed;
import edu.cmu.mgmt.model.twitter.Tweet;
import edu.cmu.mgmt.model.twitter.TweetList;
import edu.cmu.mgmt.model.twitter.TwitterUser;

public interface TwitterService {
	public TwitterUser getTwitterUser(Token accessToken,
			OAuthService twitterOAuthService);

	public TweetList getUserTweets(Token accessToken,
			OAuthService twitterOAuthService, String username);

	public Tweet createTweet(Token accessToken,
			OAuthService twitterOAuthService, String status);

	public Tweet reTweet(Token accessToken, OAuthService twitterOAuthService,
			String tid, String status);

	public Tweet replyTweet(Token accessToken,
			OAuthService twitterOAuthService, String status, Tweet t);

	public Oembed getOemTweet(Token accessToken,
			OAuthService twitterOAuthService, String tweetId);

}
