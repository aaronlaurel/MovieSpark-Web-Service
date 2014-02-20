package edu.cmu.mgmt.model.flickr;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "rsp")
public class FlickrLocation {
	@XmlAttribute
	private String stat;

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
		private Location location;

		public Location getLocation() {
			return location;
		}

		public void setLocation(Location location) {
			this.location = location;
		}

		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType
		public static class Location {
			@XmlAttribute
			private String latitude;

			@XmlAttribute
			private String longitude;

			@XmlAttribute
			private String accuracy;

			public String getLatitude() {
				return latitude;
			}

			public void setLatitude(String latitude) {
				this.latitude = latitude;
			}

			public String getLongitude() {
				return longitude;
			}

			public void setLongitude(String longitude) {
				this.longitude = longitude;
			}

			public String getAccuracy() {
				return accuracy;
			}

			public void setAccuracy(String accuracy) {
				this.accuracy = accuracy;
			}

		}
	}

}
