package com.fjw.domain;

import java.io.Serializable;

public class FileContent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7004038584187694955L;
	private int id;
	private String filename;
	private String showFilename;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getShowFilename() {
		return showFilename;
	}
	public void setShowFilename(String showFilename) {
		this.showFilename = showFilename;
	}
	
}
