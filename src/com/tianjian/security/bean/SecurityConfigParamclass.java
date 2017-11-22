package com.tianjian.security.bean;

/**
 * SecurityConfigParamclass entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SecurityConfigParamclass implements java.io.Serializable {

	// Fields

	private String classCode;
	private String className;
	private String inputCode;
	private String functionDescription;
	private String comments;
	private String projectCode;

	// Constructors

	/** default constructor */
	public SecurityConfigParamclass() {
	}

	/** full constructor */
	public SecurityConfigParamclass(String className, String inputCode,
			String functionDescription, String comments) {
		this.className = className;
		this.inputCode = inputCode;
		this.functionDescription = functionDescription;
		this.comments = comments;

	}


	public String getClassCode() {
		return this.classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getInputCode() {
		return this.inputCode;
	}

	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}

	public String getFunctionDescription() {
		return this.functionDescription;
	}

	public void setFunctionDescription(String functionDescription) {
		this.functionDescription = functionDescription;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}	
	
	
}