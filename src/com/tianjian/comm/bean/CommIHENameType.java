package com.tianjian.comm.bean;


/**
 * CommConfigAbo generated by MyEclipse Persistence Tools
 */
public class CommIHENameType implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8953774197771169403L;
	/**
	 * 
	 */

	// Fields
	
	private String id;
	private long seqNo;
	private String nameTypeCode;
	private String nameTypeName;
	private String inputCode;
	private String comments;

	// Constructors
	/** default constructor */
	public CommIHENameType() {
		String id = "";
		String nameTypeCode = "";
		String nameTypeName = "";
		String inputCode = "";
		String comments = "";
	}

	/** full constructor */
	public CommIHENameType(String id,String nameTypeCode, Long seqNo, String nameTypeName, String inputCode, String comments) {
		this.id = id;
		this.seqNo = seqNo;
		this.nameTypeCode = nameTypeCode;
		this.nameTypeName = nameTypeName;
		this.inputCode = inputCode;
		this.comments = comments;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(long seqNo) {
		this.seqNo = seqNo;
	}

	public String getNameTypeCode() {
		return nameTypeCode;
	}

	public void setNameTypeCode(String nameTypeCode) {
		this.nameTypeCode = nameTypeCode;
	}

	public String getNameTypeName() {
		return nameTypeName;
	}

	public void setNameTypeName(String nameTypeName) {
		this.nameTypeName = nameTypeName;
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

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	

	// Property accessors
	
}