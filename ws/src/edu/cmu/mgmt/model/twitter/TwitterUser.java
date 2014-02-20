package edu.cmu.mgmt.model.twitter;

import com.google.gson.annotations.SerializedName;

public class TwitterUser {

	@SerializedName("id_str")
	private String id_str;

	@SerializedName("screen_name")
	private String screenName;

	@SerializedName("name")
	private String name;

	@SerializedName("profile_image_url")
	private String profileImageUrl;

	public String getId_str() {
		return id_str;
	}

	public void setId_str(String id_str) {
		this.id_str = id_str;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return "TwitterUser [id_str=" + id_str + ", screenName=" + screenName
				+ ", name=" + name + ", profileImageUrl=" + profileImageUrl
				+ "]";
	}

}