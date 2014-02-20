package edu.cmu.mgmt.model.flickr;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "rsp")
public class FlickrPerson {
	@XmlAttribute
	private String stat;

	private Person person;

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String toString() {
		return "rsp" + "\nstat=" + stat + "\nperson\n" + person;
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType
	public static class Person {
		@XmlAttribute
		private String id;
		@XmlAttribute
		private String nsid;

		private String username;
		private String realname;
		private String mbox_sha1sum;

		// skipped some part:
		// ispro, iconserver, iconfarm, path_alias, mbox_sha1sum, location,
		// description,
		private String photosurl;
		private String profileurl;
		private String mobileurl;
		private Photos photos;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getNsid() {
			return nsid;
		}

		public void setNsid(String nsid) {
			this.nsid = nsid;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getRealname() {
			return realname;
		}

		public void setRealname(String realname) {
			this.realname = realname;
		}

		public String getMbox_sha1sum() {
			return mbox_sha1sum;
		}

		public void setMbox_sha1sum(String mbox_sha1sum) {
			this.mbox_sha1sum = mbox_sha1sum;
		}

		public String getPhotosurl() {
			return photosurl;
		}

		public void setPhotosurl(String photosurl) {
			this.photosurl = photosurl;
		}

		public String getProfileurl() {
			return profileurl;
		}

		public void setProfileurl(String profileurl) {
			this.profileurl = profileurl;
		}

		public String getMobileurl() {
			return mobileurl;
		}

		public void setMobileurl(String mobileurl) {
			this.mobileurl = mobileurl;
		}

		public Photos getPhotos() {
			return photos;
		}

		public void setPhotos(Photos photos) {
			this.photos = photos;
		}

		public String toString() {
			return "Person <id=" + id + " nsid=" + nsid + ">\n"
					+ " username = " + username + "\n" + " realname="
					+ realname + "\n" + " mbox_sha1sum=" + mbox_sha1sum + "\n"
					+ " photosurl=" + photosurl + "\n" + " profileurl="
					+ profileurl + "\n" + " mobileurl=" + mobileurl + "\n"
					+ " Photos \n" + photos + "\n";
		}

		@XmlType
		@XmlAccessorType(XmlAccessType.FIELD)
		public static class Photos {
			private String firstdatetaken;
			private String firstdate;
			private int count;
			private int views;

			public String getFirstdatetaken() {
				return firstdatetaken;
			}

			public void setFirstdatetaken(String firstdatetaken) {
				this.firstdatetaken = firstdatetaken;
			}

			public String getFirstdate() {
				return firstdate;
			}

			public void setFirstdate(String firstdate) {
				this.firstdate = firstdate;
			}

			public int getCount() {
				return count;
			}

			public void setCount(int count) {
				this.count = count;
			}

			public int getViews() {
				return views;
			}

			public void setViews(int views) {
				this.views = views;
			}

			public String toString() {
				return "firstdatetaken = " + firstdatetaken + " firstdate = "
						+ firstdate + " count = " + count + " views = " + views;
			}
		}

	}
}
