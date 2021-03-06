package com.tianjian.comm.bean;

/**
 * CommConfigSettype entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class CommConfigSettype implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3395526038744824921L;
	private String itemCode;
	private Long seqNo;
	private String itemName;
	private String inputCode;
	private String comments;

	// Constructors

	/** default constructor */
	public CommConfigSettype() {
	}

	/** full constructor */
	public CommConfigSettype(Long seqNo, String itemName, String inputCode,
			String comments) {
		this.seqNo = seqNo;
		this.itemName = itemName;
		this.inputCode = inputCode;
		this.comments = comments;
	}

	// Property accessors

	public String getItemCode() {
		return this.itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public Long getSeqNo() {
		return this.seqNo;
	}

	public void setSeqNo(Long seqNo) {
		this.seqNo = seqNo;
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

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}