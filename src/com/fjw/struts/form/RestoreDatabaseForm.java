/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.fjw.struts.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

/** 
 * MyEclipse Struts
 * Creation date: 04-16-2016
 * 
 * XDoclet definition:
 * @struts.form name="restoreDatabaseForm"
 */
public class RestoreDatabaseForm extends ActionForm {
	/*
	 * Generated fields
	 */

	/** file property */
	private FormFile file;

	/*
	 * Generated Methods
	 */

	/** 
	 * Returns the file.
	 * @return FormFile
	 */
	public FormFile getFile() {
		return file;
	}

	/** 
	 * Set the file.
	 * @param file The file to set
	 */
	public void setFile(FormFile file) {
		this.file = file;
	}
}