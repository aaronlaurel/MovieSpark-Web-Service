package edu.cmu.mgmt.model.flickr;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import edu.cmu.mgmt.model.flickr.FlickrPhoto.Photo;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "rsp")
public class FlickrPhotos {

	@XmlAttribute
	private String stat;

	@XmlElement(name = "photos")
	private FlickrPhotosList flickrPhotos;

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public FlickrPhotosList getFlickrPhotos() {
		return flickrPhotos;
	}

	public void setFlickrPhotos(FlickrPhotosList flickrPhotos) {
		this.flickrPhotos = flickrPhotos;
	}

	public List<Photo> getPhotoList() {
		return flickrPhotos.getPhotos();
	}

	public String toString() {
		return "stat = " + stat + "\n" + getFlickrPhotos().toString();
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType
	@XmlRootElement(name = "photos")
	public static class FlickrPhotosList {
		@XmlAttribute
		private int page;
		@XmlAttribute
		private int pages;
		@XmlAttribute
		private int perpage;
		@XmlAttribute
		private int total;

		@XmlElement(name = "photo", type = Photo.class)
		private List<Photo> photos = new ArrayList<Photo>();

		public int getPage() {
			return page;
		}

		public void setPage(int page) {
			this.page = page;
		}

		public int getPages() {
			return pages;
		}

		public void setPages(int pages) {
			this.pages = pages;
		}

		public int getPerpage() {
			return perpage;
		}

		public void setPerpage(int perpage) {
			this.perpage = perpage;
		}

		public int getTotal() {
			return total;
		}

		public void setTotal(int total) {
			this.total = total;
		}

		public List<Photo> getPhotos() {
			return photos;
		}

		public void setPhotos(List<Photo> photos) {
			this.photos = photos;
		}

		public String toString() {
			StringBuilder sbBuilder = new StringBuilder();
			for (Photo p : photos) {
				sbBuilder.append(p.toString() + "\n");
			}
			return sbBuilder.toString();
		}

	}
}
