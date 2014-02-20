package edu.cmu.mgmt.model.vo;

import edu.cmu.mgmt.common.TopicType;

public class CategoryVo {
	private TopicType type;
	private int amounts;

	public TopicType getType() {
		return type;
	}

	public void setType(TopicType type) {
		this.type = type;
	}

	public int getAmounts() {
		return amounts;
	}

	public void setAmounts(int amounts) {
		this.amounts = amounts;
	}

}
