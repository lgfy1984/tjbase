package com.tianjian.comm.bean;

/**
 * CommDictPublicLevel generated by MyEclipse Persistence Tools
 */
public class CommDictPublicLevel implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 998998474788660844L;
	// Fields
	/**项目代码*/
	private String itemCode;
	/**项目名称*/
	private String itemName;
	/**输入码*/
	private String inputCode;
	/**父项目代码*/
	private String parentItemCode;
	/**所在级别*/
	private Long classLevel;
	/**序号*/
	private Long seqInLevel;
	/**字典类别*/
	private String tableCode;
	/**备注*/
	private String comments;
	private com.tianjian.comm.bean.CommDictPublicClass tableCodeObject;
	private com.tianjian.comm.bean.CommDictPublicLevel parentItemCodeObject;
	

	// Constructors
	/** default constructor */
	public CommDictPublicLevel() {}

	/** minimal constructor */
	public CommDictPublicLevel(String tableCode) {
		this.tableCode = tableCode;
	}

	/** full constructor */
	public CommDictPublicLevel(String itemName, String inputCode, String parentItemCode, Long classLevel, Long seqInLevel, String tableCode, String comments,
			com.tianjian.comm.bean.CommDictPublicClass tableCodeObject,com.tianjian.comm.bean.CommDictPublicLevel parentItemCodeObject) {
		this.itemName = itemName;
		this.inputCode = inputCode;
		this.parentItemCode = parentItemCode;
		this.classLevel = classLevel;
		this.seqInLevel = seqInLevel;
		this.tableCode = tableCode;
		this.comments = comments;
		this.tableCodeObject = tableCodeObject;
		this.parentItemCodeObject = parentItemCodeObject;
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

	public String getParentItemCode() {
		return this.parentItemCode;
	}

	public void setParentItemCode(String parentItemCode) {
		this.parentItemCode = parentItemCode;
	}

	public Long getClassLevel() {
		return this.classLevel;
	}

	public void setClassLevel(Long classLevel) {
		this.classLevel = classLevel;
	}

	public Long getSeqInLevel() {
		return this.seqInLevel;
	}

	public void setSeqInLevel(Long seqInLevel) {
		this.seqInLevel = seqInLevel;
	}

	public String getTableCode() {
		return this.tableCode;
	}

	public void setTableCode(String tableCode) {
		this.tableCode = tableCode;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	
	public com.tianjian.comm.bean.CommDictPublicClass getTableCodeObject() {
		return tableCodeObject;
	}

	
	public void setTableCodeObject(com.tianjian.comm.bean.CommDictPublicClass tableCodeObject) {
		this.tableCodeObject = tableCodeObject;
	}

	
	public com.tianjian.comm.bean.CommDictPublicLevel getParentItemCodeObject() {
		return parentItemCodeObject;
	}

	
	public void setParentItemCodeObject(com.tianjian.comm.bean.CommDictPublicLevel parentItemCodeObject) {
		this.parentItemCodeObject = parentItemCodeObject;
	}

	
	
}