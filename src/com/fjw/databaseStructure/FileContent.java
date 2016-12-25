package com.fjw.databaseStructure;

import java.io.Serializable;



public class FileContent implements Serializable{
	private Integer id;
	private String filename;
	private String showFilename;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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