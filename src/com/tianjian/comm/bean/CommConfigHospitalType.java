package com.tianjian.comm.bean;

/**
 * CommConfigHospitalType generated by MyEclipse Persistence Tools
 */

public class CommConfigHospitalType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4164310946084096110L;
	private String itemCode;
	private String itemName;
	private String inputCode;
	private Long levelFlag;
	private String parentItemCode;
	private String comments;
	private Long seqNo;

	// Constructors

	/** default constructor */
	public CommConfigHospitalType() {
	}

	/** minimal constructor */
	public CommConfigHospitalType(String itemCode) {
		this.itemCode = itemCode;
	}

	/** full constructor */
	public CommConfigHospitalType(String itemCode, String itemName,
			String inputCode, Long levelFlag, String parentItemCode,
			String comments, Long seqNo) {
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.inputCode = inputCode;
		this.levelFlag = levelFlag;
		this.parentItemCode = parentItemCode;
		this.comments = comments;
		this.seqNo = seqNo;
	}

	// Property accessors

	public String getItemCode() {
		return this.itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getInputCode() {
		return this.inputCode;
	}

	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}

	public Long getLevelFlag() {
		return this.levelFlag;
	}

	public void setLevelFlag(Long levelFlag) {
		this.levelFlag = levelFlag;
	}

	public String getParentItemCode() {
		return this.parentItemCode;
	}

	public void setParentItemCode(String parentItemCode) {
		this.parentItemCode = parentItemCode;
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