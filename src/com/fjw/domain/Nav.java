package com.fjw.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Nav implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8345779547550145261L;
	private int navId;
	private String title;
	private FileContent image;
	private String content;
	private Timestamp timestamp;
	private ArrayList<Cat> cats=new ArrayList<Cat>();
	
	public int getNavId() {
		return navId;
	}

	public void setNavId(int navId) {
		this.navId = navId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public FileContent getImage() {
		return image;
	}

	public void setImage(FileContent image) {
		this.image = image;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public ArrayList<Cat> getCats() {
		return cats;
	}

	public void setCats(ArrayList<Cat> cats) {
		this.cats = cats;
	}

	public void addCat(Cat cat) {
		this.cats.add(cat);
	}
}
