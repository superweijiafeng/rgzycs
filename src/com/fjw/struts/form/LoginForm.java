/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.fjw.struts.form;

import org.apache.struts.action.ActionForm;

/** 
 * MyEclipse Struts
 * Creation date: 04-13-2016
 * 
 * XDoclet definition:
 * @struts.form name="loginForm"
 */
public class LoginForm extends ActionForm {
	/*
	 * Generated fields
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = -5832377104607709624L;

	/** isKeepPass property */
	private Boolean isKeepPass;

	/** username property */
	private String username;

	/** checkcode property */
	private String checkcode;

	/** password property */
	private String password;

	/*
	 * Generated Methods
	 */

	/** 
	 * Returns the isKeepPass.
	 * @return Boolean
	 */
	public Boolean getIsKeepPass() {
		return isKeepPass;
	}

	/** 
	 * Set the isKeepPass.
	 * @param isKeepPass The isKeepPass to set
	 */
	public void setIsKeepPass(Boolean isKeepPass) {
		this.isKeepPass = isKeepPass;
	}

	/** 
	 * Returns the username.
	 * @return String
	 */
	public String getUsername() {
		return username;
	}

	/** 
	 * Set the username.
	 * @param username The username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/** 
	 * Returns the checkcode.
	 * @return String
	 */
	public String getCheckcode() {
		return checkcode;
	}

	/** 
	 * Set the checkcode.
	 * @param checkcode The checkcode to set
	 */
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	/** 
	 * Returns the password.
	 * @return String
	 */
	public String getPassword() {
		return password;
	}

	/** 
	 * Set the password.
	 * @param password The password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}