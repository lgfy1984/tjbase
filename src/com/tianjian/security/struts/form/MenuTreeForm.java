/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.tianjian.security.struts.form;

import org.apache.struts.action.ActionForm;

/** 
 * MyEclipse Struts
 * Creation date: 04-01-2014
 * 
 * XDoclet definition:
 * @struts.form name="menuTreeForm"
 */
public class MenuTreeForm extends ActionForm {
	private static final long serialVersionUID = -5289044079635366157L;
	private String message;
	private String pid;
	private String type;
	private String publicClassId;
	private String publicId;
	private String menuId;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPublicClassId() {
		return publicClassId;
	}
	public void setPublicClassId(String publicClassId) {
		this.publicClassId = publicClassId;
	}
	public String getPublicId() {
		return publicId;
	}
	public void setPublicId(String publicId) {
		this.publicId = publicId;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
}