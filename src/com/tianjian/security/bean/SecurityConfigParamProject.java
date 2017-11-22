package com.tianjian.security.bean;

/**
 * SecurityConfigParamclass entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SecurityConfigParamProject implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String projectCode;
	private String projectName;
	private String inputCode;
	private String comments;
	private Long seqNo;
//	private String projectCode;

	// Constructors

	/** default constructor */
	public SecurityConfigParamProject() {
	}

	/** full constructor */
	public SecurityConfigParamProject(String projectCode, String projectName,
			String inputCode, String comments, Long seqNo) {
		this.projectCode = projectCode;
		this.projectName = projectName;
		this.inputCode = inputCode;
		this.comments = comments;
		this.seqNo = seqNo;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getInputCode() {
		return inputCode;
	}

	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Long getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Long seqNo) {
		this.seqNo = seqNo;
	}

	
	
}