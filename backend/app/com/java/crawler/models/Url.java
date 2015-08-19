package com.java.crawler.models;

import play.modules.morphia.Model;

import com.google.code.morphia.annotations.Entity;

@Entity
public class Url extends Model {

	public String link;

	public Url() {
		super();
	}

	public Url(String link) {
		super();
		this.link = link;
	}

}
