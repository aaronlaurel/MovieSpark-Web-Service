package edu.cmu.mgmt.model.twitter;

import com.google.gson.annotations.SerializedName;

public class Oembed {

	@SerializedName("html")
	private String html;

	@SerializedName("author_name")
	private String author_name;

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getAuthor_name() {
		return author_name;
	}

	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}

}
