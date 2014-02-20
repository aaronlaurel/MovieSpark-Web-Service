package edu.cmu.mgmt.service;

import java.util.Map;

import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

import edu.cmu.mgmt.model.flickr.FlickrLocation;
import edu.cmu.mgmt.model.flickr.FlickrPerson;
import edu.cmu.mgmt.model.flickr.FlickrPhoto.Photo;
import edu.cmu.mgmt.model.flickr.FlickrPhotos;
import edu.cmu.mgmt.model.flickr.FlickrUser;

public interface FlickrService {

	public FlickrUser getLoginInfo(Token accessToken,
			OAuthService flickrOAuthService);

	public FlickrPerson getPeopleInfo(Token accessToken,
			OAuthService flickrOAuthService, String user_id);

	public Photo getPhotoInfo(Token accessToken,
			OAuthService flickrOAuthService, String photo_id);

	public FlickrLocation getPhotoLocation(Token accessToken,
			OAuthService flickrOAuthService, String photo_id);

	public String uploadPhoto(Token accessToken,
			OAuthService flickrOAuthService, Map<String, Object> parameters);

	public FlickrPhotos getPhotosForLocation(Token accessToken,
			OAuthService flickrOAuthService, String lat, String lon);
}
