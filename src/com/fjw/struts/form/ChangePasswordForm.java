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
 * @struts.form name="changePasswordForm"
 */
public class ChangePasswordForm extends ActionForm {
	/*
	 * Generated fields
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = -1271429777235924009L;

	/** newPassword2 property */
	private String newPassword2;

	/** newPassword1 property */
	private String newPassword1;

	/** oldPassword property */
	private String oldPassword;

	/*
	 * Generated Methods
	 */

	/** 
	 * Returns the newPassword2.
	 * @return String
	 */
	public String getNewPassword2() {
		return newPassword2;
	}

	/** 
	 * Set the newPassword2.
	 * @param newPassword2 The newPassword2 to set
	 */
	public void setNewPassword2(String newPassword2) {
		this.newPassword2 = newPassword2;
	}

	/** 
	 * Returns the newPassword1.
	 * @return String
	 */
	public String getNewPassword1() {
		return newPassword1;
	}

	/** 
	 * Set the newPassword1.
	 * @param newPassword1 The newPassword1 to set
	 */
	public void setNewPassword1(String newPassword1) {
		this.newPassword1 = newPassword1;
	}

	/** 
	 * Returns the oldPassword.
	 * @return String
	 */
	public String getOldPassword() {
		return oldPassword;
	}

	/** 
	 * Set the oldPassword.
	 * @param oldPassword The oldPassword to set
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
}