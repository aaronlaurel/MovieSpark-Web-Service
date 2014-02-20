package edu.cmu.mgmt.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import edu.cmu.mgmt.model.flickr.FlickrLocation;
import edu.cmu.mgmt.model.flickr.FlickrPerson;
import edu.cmu.mgmt.model.flickr.FlickrPhoto.Photo;
import edu.cmu.mgmt.model.flickr.FlickrPhotos;
import edu.cmu.mgmt.model.flickr.FlickrUser;
import edu.cmu.mgmt.service.FlickrService;
import edu.cmu.mgmt.utils.XMLUtils;

public class FlickrServiceImpl implements FlickrService {
	private static final String API_HOST = "http://api.flickr.com/services/rest/?method=";
	private static final String API_UPLOAD = "http://up.flickr.com/services/upload/";
	private static final String CHARSET_NAME = "UTF-8";

	public FlickrUser getLoginInfo(Token accessToken,
			OAuthService flickrOAuthService) {
		OAuthRequest oAuthRequest = new OAuthRequest(Verb.GET, API_HOST
				+ "flickr.test.login");
		flickrOAuthService.signRequest(accessToken, oAuthRequest);
		Response response = oAuthRequest.send();
		return XMLUtils.xmlToUser(response.getBody());
	}

	public FlickrPerson getPeopleInfo(Token accessToken,
			OAuthService flickrOAuthService, String user_id) {
		OAuthRequest oAuthRequest = new OAuthRequest(Verb.GET, API_HOST
				+ "flickr.people.getInfo&user_id=" + user_id);
		flickrOAuthService.signRequest(accessToken, oAuthRequest);
		Response response = oAuthRequest.send();
		return XMLUtils.xmlToPerson(response.getBody());
	}

	public Photo getPhotoInfo(Token accessToken,
			OAuthService flickrOAuthService, String photo_id) {
		OAuthRequest oAuthRequest = new OAuthRequest(Verb.GET, API_HOST
				+ "flickr.photos.getInfo&photo_id=" + photo_id);
		flickrOAuthService.signRequest(accessToken, oAuthRequest);
		Response response = oAuthRequest.send();
		return XMLUtils.xmlToPhoto(response.getBody());
	}

	public FlickrPhotos getPhotosForLocation(Token accessToken,
			OAuthService flickrOAuthService, String lat, String lon) {
		OAuthRequest oAuthRequest = new OAuthRequest(Verb.GET, API_HOST
				+ "flickr.photos.search&lat=" + lat + "&lon=" + lon
				+ "&per_page=25&text=movie&sort=relevance");
		flickrOAuthService.signRequest(accessToken, oAuthRequest);
		Response response = oAuthRequest.send();
		return XMLUtils.xmlToPhotos(response.getBody());
	}

	public FlickrLocation getPhotoLocation(Token accessToken,
			OAuthService flickrOAuthService, String photo_id) {
		OAuthRequest oAuthRequest = new OAuthRequest(Verb.GET, API_HOST
				+ "flickr.photos.geo.getLocation&photo_id=" + photo_id);
		flickrOAuthService.signRequest(accessToken, oAuthRequest);
		Response response = oAuthRequest.send();
		FlickrLocation flickrLocation = XMLUtils.xmlToLocation(response
				.getBody());
		return flickrLocation;
	}

	public String uploadPhoto(Token accessToken,
			OAuthService flickrOAuthService, Map<String, Object> parameters) {
		OAuthRequest oAuthRequest = new OAuthRequest(Verb.POST, API_UPLOAD);
		// add parameters into request, but do not add photo
		buildMultipartRequest(parameters, oAuthRequest);

		// sign
		flickrOAuthService.signRequest(accessToken, oAuthRequest);

		// add all parameter into request again
		parameters.putAll(oAuthRequest.getOauthParameters());
		oAuthRequest.addPayload(buildMultipartBody(parameters,
				getMultipartBoundary()));

		Response response = oAuthRequest.send();
		return XMLUtils.xmlToPhotoId(response.getBody());
	}

	// -----------------------------------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------------------------
	private void buildMultipartRequest(Map<String, Object> parameters,
			OAuthRequest request) {
		request.addHeader("Content-Type", "multipart/form-data; boundary="
				+ getMultipartBoundary());
		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			String key = entry.getKey();
			if (!key.equals("photo")) {
				request.addQuerystringParameter(key,
						String.valueOf(entry.getValue()));
			}
		}
	}

	private String getMultipartBoundary() {
		return "---------------------------7d273f7a0d3";
	}

	private byte[] buildMultipartBody(Map<String, Object> parameters,
			String boundary) {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		try {
			buffer.write(("--" + boundary + "\r\n").getBytes(CHARSET_NAME));
			for (Entry<String, Object> entry : parameters.entrySet()) {
				String key = entry.getKey();
				writeParam(key, entry.getValue(), buffer, boundary);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return buffer.toByteArray();
	}

	private void writeParam(String name, Object value,
			ByteArrayOutputStream buffer, String boundary) throws IOException {
		if (value instanceof InputStream) {
			buffer.write(("Content-Disposition: form-data; name=\"" + name + "\"; filename=\"image.jpg\";\r\n")
					.getBytes(CHARSET_NAME));
			buffer.write(("Content-Type: image/jpeg" + "\r\n\r\n")
					.getBytes(CHARSET_NAME));
			InputStream in = (InputStream) value;
			byte[] buf = new byte[512];

			int res = -1;
			while ((res = in.read(buf)) != -1) {
				buffer.write(buf, 0, res);
			}
			buffer.write(("\r\n" + "--" + boundary + "\r\n")
					.getBytes(CHARSET_NAME));
		} else if (value instanceof byte[]) {
			buffer.write(("Content-Disposition: form-data; name=\"" + name + "\"; filename=\"image.jpg\";\r\n")
					.getBytes(CHARSET_NAME));
			buffer.write(("Content-Type: image/jpeg" + "\r\n\r\n")
					.getBytes(CHARSET_NAME));
			buffer.write((byte[]) value);
			buffer.write(("\r\n" + "--" + boundary + "\r\n")
					.getBytes(CHARSET_NAME));
		} else {
			buffer.write(("Content-Disposition: form-data; name=\"" + name + "\"\r\n\r\n")
					.getBytes(CHARSET_NAME));
			buffer.write(((String) value).getBytes(CHARSET_NAME));
			buffer.write(("\r\n" + "--" + boundary + "\r\n")
					.getBytes(CHARSET_NAME));
		}
	}
	// -----------------------------------------------------------------------------------------------------------
}
