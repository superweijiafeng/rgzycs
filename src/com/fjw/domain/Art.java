package com.fjw.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class Art implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3627132217035447742L;
	private int id;
	private int navId;
	private int catId;
	private int artId;
	private String title;
	private FileContent image;
	private Timestamp timestamp;
	private String content;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNavId() {
		return navId;
	}
	public void setNavId(int navId) {
		this.navId = navId;
	}
	public int getCatId() {
		return catId;
	}
	public void setCatId(int catId) {
		this.catId = catId;
	}
	public int getArtId() {
		return artId;
	}
	public void setArtId(int artId) {
		this.artId = artId;
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
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
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
	
}
