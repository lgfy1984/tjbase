package com.tianjian.comm.bean;

/**
 * CommWarningSms entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class CommWarningSms implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 366336431265315077L;
	private String itemCode;
	private String itemName;
	private String inputCode;
	private String contents;
	private String comments;
	private Long seqNo;

	// Constructors

	/** default constructor */
	public CommWarningSms() {
	}

	/** minimal constructor */
	public CommWarningSms(String itemCode, String itemName) {
		this.itemCode = itemCode;
		this.itemName = itemName;
	}

	/** full constructor */
	public CommWarningSms(String itemCode, String itemName, String inputCode,
			String contents, String comments, Long seqNo) {
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.inputCode = inputCode;
		this.contents = contents;
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

	public String getContents() {
		return this.contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
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