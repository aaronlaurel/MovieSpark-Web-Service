package edu.cmu.mgmt.model.vo;

import edu.cmu.mgmt.common.TopicType;

public class TopicVo {
	private int picStat;
	private int commStat;
	private String title;
	private int rank;
	private TopicType type;

	public int getPicStat() {
		return picStat;
	}

	public void setPicStat(int picStat) {
		this.picStat = picStat;
	}

	public int getCommStat() {
		return commStat;
	}

	public void setCommStat(int commStat) {
		this.commStat = commStat;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public TopicType getType() {
		return type;
	}

	public void setType(TopicType type) {
		this.type = type;
	}

}
