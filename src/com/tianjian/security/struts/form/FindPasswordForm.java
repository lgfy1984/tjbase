package com.tianjian.security.struts.form;

import org.apache.struts.action.ActionForm;

public class FindPasswordForm extends ActionForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7353634219008348067L;
	private String userName;
	private String email;
	private String verbId;
	private String message;
	public String getVerbId() {
		return verbId;
	}
	public void setVerbId(String verbId) {
		this.verbId = verbId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
