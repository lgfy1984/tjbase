package com.tianjian.comm.bean;

/**
 * CommConfigCardtype entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class CommConfigCardtype implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1148495124349527301L;
	private String itemCode;
	private Long seqNo;
	private String itemName;
	private String inputCode;
	private Long stopFlag;
	private Long displayFlag;
	private String comments;

	// Constructors

	/** default constructor */
	public CommConfigCardtype() {
	}

	/** minimal constructor */
	public CommConfigCardtype(String itemCode) {
		this.itemCode = itemCode;
	}

	/** full constructor */
	public CommConfigCardtype(String itemCode, Long seqNo, String itemName,
			String inputCode, Long stopFlag, Long displayFlag, String comments) {
		this.itemCode = itemCode;
		this.seqNo = seqNo;
		this.itemName = itemName;
		this.inputCode = inputCode;
		this.stopFlag = stopFlag;
		this.displayFlag = displayFlag;
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

	public Long getStopFlag() {
		return this.stopFlag;
	}

	public void setStopFlag(Long stopFlag) {
		this.stopFlag = stopFlag;
	}

	public Long getDisplayFlag() {
		return this.displayFlag;
	}

	public void setDisplayFlag(Long displayFlag) {
		this.displayFlag = displayFlag;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}