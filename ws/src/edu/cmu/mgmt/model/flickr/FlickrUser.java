package edu.cmu.mgmt.model.flickr;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "rsp")
public class FlickrUser {
	@XmlAttribute
	private String stat;

	@XmlElement(name = "user")
	private User user;

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String toString() {
		return "<rsp stat = " + stat + ">\n" + user.toString();
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType
	@XmlRootElement(name = "user")
	public static class User {
		@XmlAttribute
		private String id;
		private String username;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String toString() {
			return "<user id = \"" + id + "\"> username = " + username + "\n";
		}
	}
}
