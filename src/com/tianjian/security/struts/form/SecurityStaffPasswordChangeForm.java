package com.tianjian.security.struts.form;

import java.io.Serializable;
import org.apache.struts.action.ActionForm;

public class SecurityStaffPasswordChangeForm extends ActionForm implements Serializable {

	private static final long serialVersionUID = 2717849952365436900L;
	private String staffId;// 用户名
	private String passwd;// 用户密码
	private String newPasswd;
	private String verbId;
	private String message;

	public SecurityStaffPasswordChangeForm() {
		this.staffId = "";
		this.passwd = "";
		this.newPasswd = "";
		this.verbId = "";
		this.message = "";
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getNewPasswd() {
		return newPasswd;
	}

	public void setNewPasswd(String newPasswd) {
		this.newPasswd = newPasswd;
	}

	public String getVerbId() {
		return verbId;
	}

	public void setVerbId(String verbId) {
		this.verbId = verbId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}