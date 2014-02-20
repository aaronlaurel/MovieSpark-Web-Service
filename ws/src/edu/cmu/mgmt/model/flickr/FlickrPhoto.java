package edu.cmu.mgmt.model.flickr;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import edu.cmu.mgmt.model.flickr.FlickrLocation.Photo.Location;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "rsp")
public class FlickrPhoto {
	@XmlAttribute
	private String stat;

	@XmlElement(name = "photo")
	private Photo photo;

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType
	public static class Photo {
		@XmlAttribute
		private String id;
		@XmlAttribute
		private String owner;
		@XmlAttribute
		private String secret;
		@XmlAttribute
		private String server;
		@XmlAttribute
		private String farm;
		@XmlAttribute
		private String title;
		@XmlAttribute
		private boolean ispublic;
		@XmlAttribute
		private boolean isfriend;
		@XmlAttribute
		private boolean isfamily;

		private Location photoLocation;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getOwner() {
			return owner;
		}

		public void setOwner(String owner) {
			this.owner = owner;
		}

		public String getSecret() {
			return secret;
		}

		public void setSecret(String secret) {
			this.secret = secret;
		}

		public String getServer() {
			return server;
		}

		public void setServer(String server) {
			this.server = server;
		}

		public String getFarm() {
			return farm;
		}

		public void setFarm(String farm) {
			this.farm = farm;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public boolean isIspublic() {
			return ispublic;
		}

		public void setIspublic(boolean ispublic) {
			this.ispublic = ispublic;
		}

		public boolean isIsfriend() {
			return isfriend;
		}

		public void setIsfriend(boolean isfriend) {
			this.isfriend = isfriend;
		}

		public boolean isIsfamily() {
			return isfamily;
		}

		public void setIsfamily(boolean isfamily) {
			this.isfamily = isfamily;
		}

		public Location getPhotoLocation() {
			return photoLocation;
		}

		public void setPhotoLocation(Location photoLocation) {
			this.photoLocation = photoLocation;
		}

		public String getUrl() {
			String url = "http://farm" + farm + ".staticflickr.com/" + server
					+ "/" + id + "_" + secret + ".jpg";
			return url;
		}

		public String getSquareUrl() {
			String url = "http://farm" + farm + ".staticflickr.com/" + server
					+ "/" + id + "_" + secret + "_q" + ".jpg";
			return url;
		}
	}
}
