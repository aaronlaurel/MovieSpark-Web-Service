package edu.cmu.mgmt.utils.oauth;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.FlickrApi;
import org.scribe.builder.api.TwitterApi;
import org.scribe.oauth.OAuthService;

public class ServiceConnect {
	public static final String TWITTER_OAUTH_CONSUMER_KEY = "eKK2mlbumnrq2EQQ8VrOtA";
	public static final String TWITTER_OAUTH_CONSUMER_SECRET = "UsVW68JHlWij9GrHBHGHJN63uYTqPhAj0ZJWMvxAVk";
	public static final String FLICKR_OAUTH_CONSUMER_KEY = "50ec324e853d45577d4c0ab1a04e0645";
	public static final String FLICKR_OAUTH_CONSUMER_SECRET = "73aff9cfaa4d5dc9";
	public static final String TWITTER_CALLBACK_URL = "http://localhost:8080/ws/redirect_flickr.do";
	public static final String FLICKR_CALLBACK_URL = "http://localhost:8080/ws/auth_flickr.do";

	/**
	 * Create the TwitterOAuthService object
	 */
	public static OAuthService connectToTwitter() {
		OAuthService service = new ServiceBuilder()
				.provider(TwitterApi.SSL.class)
				.apiKey(TWITTER_OAUTH_CONSUMER_KEY)
				.apiSecret(TWITTER_OAUTH_CONSUMER_SECRET)
				.callback(TWITTER_CALLBACK_URL).build();
		return service;
	}

	/**
	 * Create the FlickerOAuthService object
	 */
	public static OAuthService connectToFlicker() {
		OAuthService service = new ServiceBuilder().provider(FlickrApi.class)
				.apiKey(FLICKR_OAUTH_CONSUMER_KEY)
				.apiSecret(FLICKR_OAUTH_CONSUMER_SECRET)
				.callback(FLICKR_CALLBACK_URL).build();
		return service;
	}
}
