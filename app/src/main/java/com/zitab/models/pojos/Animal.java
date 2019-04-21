package com.zitab.models.pojos;

import java.io.Serializable;

public class Animal implements Serializable {
	private String url;
	private String title;
	
	public String getUrl() {
		return url;
	}
	
	public String getTitle() {
		return title;
	}
	
	@Override
	public String toString() {
		return "Animal{" +
				"url='" + url + '\'' +
				", title='" + title + '\'' +
				'}';
	}
}