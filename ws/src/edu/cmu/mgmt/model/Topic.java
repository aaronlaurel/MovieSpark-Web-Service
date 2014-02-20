package edu.cmu.mgmt.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import edu.cmu.mgmt.common.TopicType;

@Entity
@Table(name = "topic")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class Topic implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private Integer topic_id;
	private String user_id;
	private String title;
	private Date create_date;
	private String url;
	private Integer rank;
	private TopicType topicTpye;

	@Id
	@GeneratedValue(strategy = IDENTITY)
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

	@Column(name = "title", unique = true, nullable = false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "create_date")
	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	@Column(name = "url")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "rank")
	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "topicTpye")
	public TopicType getTopicTpye() {
		return topicTpye;
	}

	public void setTopicTpye(TopicType topicTpye) {
		this.topicTpye = topicTpye;
	}

}
