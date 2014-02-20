package edu.cmu.mgmt.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "picture")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class Picture implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private Integer picture_id;
	private Integer topic_id;
	private String user_id;
	private String title;
	private String description;
	private Date create_date;
	private String flickr_pid;
	private String url;
	private Integer click_count;
	private String movie;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	public Integer getPicture_id() {
		return picture_id;
	}

	public void setPicture_id(Integer picture_id) {
		this.picture_id = picture_id;
	}

	@Column(name = "topic_id")
	public Integer getTopic_id() {
		return topic_id;
	}

	public void setTopic_id(Integer topic_id) {
		this.topic_id = topic_id;
	}

	@Column(name = "userid")
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	@Column(name = "title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "create_date")
	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	@Column(name = "flickr_pid")
	public String getFlickr_pid() {
		return flickr_pid;
	}

	public void setFlickr_pid(String flickr_pid) {
		this.flickr_pid = flickr_pid;
	}

	@Column(name = "url")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "click_count")
	public Integer getClick_count() {
		return click_count;
	}

	public void setClick_count(Integer click_count) {
		this.click_count = click_count;
	}

	@Column(name = "movie")
	public String getMovie() {
		return movie;
	}

	public void setMovie(String movie) {
		this.movie = movie;
	}

}
