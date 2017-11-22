package com.tianjian.comm.bean;

/**
 * CommConfigHospitalClass entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class CommConfigHospitalClass implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8811703003304501585L;
	private String classCode;
	private String className;
	private String inputCode;
	private String comments;
	private Long seqNo;

	// Constructors

	/** default constructor */
	public CommConfigHospitalClass() {
	}

	/** minimal constructor */
	public CommConfigHospitalClass(String classCode, String className,
			String inputCode) {
		this.classCode = classCode;
		this.className = className;
		this.inputCode = inputCode;
	}

	/** full constructor */
	public CommConfigHospitalClass(String classCode, String className,
			String inputCode, String comments, Long seqNo) {
		this.classCode = classCode;
		this.className = className;
		this.inputCode = inputCode;
		this.comments = comments;
		this.seqNo = seqNo;
	}

	// Property accessors

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

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Long getSeqNo() {
		return this.seqNo;
	}

	public void setSeqNo(Long seqNo) {
		this.seqNo = seqNo;
	}

}