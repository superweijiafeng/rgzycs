package com.fjw.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Cat implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7242244637415689802L;
	private int id;
	private int navId;
	private int catId;
	private String title;
	private FileContent image;
	private String content;
	private Timestamp timestamp;
	private ArrayList<Art> arts=new ArrayList<Art>();
	
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
	public ArrayList<Art> getArts() {
		return arts;
	}
	public void setArts(ArrayList<Art> arts) {
		this.arts = arts;
	}
	public void addArt(Art art) {
		arts.add(art);
	}
	
}
