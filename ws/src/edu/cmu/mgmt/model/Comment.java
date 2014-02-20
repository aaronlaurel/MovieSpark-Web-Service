package edu.cmu.mgmt.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "comment")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class Comment implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private Integer comment_id;
	private Integer photo_id;
	private String tweet_id;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	public Integer getComment_id() {
		return comment_id;
	}

	public void setComment_id(Integer comment_id) {
		this.comment_id = comment_id;
	}

	@Column(name = "photo_id")
	public Integer getPhoto_id() {
		return photo_id;
	}

	public void setPhoto_id(Integer photo_id) {
		this.photo_id = photo_id;
	}

	@Column(name = "tweet_id")
	public String getTweet_id() {
		return tweet_id;
	}

	public void setTweet_id(String tweet_id) {
		this.tweet_id = tweet_id;
	}

}
