package com.tianjian.comm.struts.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;



public class CommDictPublicLevelForm extends ActionForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2602854951440701924L;
	/**
	 * 
	 */

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
	private String classLevel;
	/**序号*/
	private String seqInLevel;
	/**字典类别*/
	private String tableCode;
	/**备注*/
	private String comments;
	//-------定义排序等功能列-----------------------
    private String verbId;
    private String message;
    private String orderNo;
    private String asc;
    //-------处理代码对应的字典名称------------------
	private String parentItemName;
	private String tableName;
	 //-------------------------------------------
	private String idHidden;
	//--------所有字典都定义成数组-------------------
	/**项目代码*/
	private String[] parentItemCodes;
	/**项目名称*/
	private String[] parentItemNames;
	/**字典类别Id*/
	private String[] tableCodes;
	/**字典类别名称*/
	private String[] tableNames;
	
	 //-----定义query查询列表---------------
	/**项目代码*/
	private String[] itemCodeList;
	/**项目名称*/
	private String[] itemNameList;
	/**输入码*/
	private String[] inputCodeList;
	/**父类代码*/
	private String[] parentItemCodeList;
	/**父类Name*/
	private String[] parentItemNameList;
	/**级别*/
	private String[] classLevelList;
	/**序号*/
	private String[] seqInLevelList;
	/**备注*/
	private String[] commentsList;
	/**字典类别代码*/
	private String[] tableCodeList;
	/**字典类别名称*/
	private String[] tableNameList;
	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		// TODO Auto-generated method stub
		return null;
	}


	public CommDictPublicLevelForm() {
		super();
		// TODO Auto-generated constructor stub
		  itemCode = "";
		/**项目名称*/
		  itemName = "";
		/**输入码*/
		  inputCode = "";
		/**父项目代码*/
		  parentItemCode = "";
		/**所在级别*/
		  classLevel = "";
		/**序号*/
		  seqInLevel = "";
		/**字典类别*/
		  tableCode = "";
		/**备注*/
		  comments = "";
		//-------定义排序等功能列-----------------------
	      verbId = "";
	      message = "";
	      orderNo = "";
	      asc = "";
	    //-------处理代码对应的字典名称------------------
		  parentItemName = "";
		  tableName = "";
		 //-------------------------------------------
		  idHidden = "";
	
	}


	
	public String getItemCode() {
		return itemCode;
	}


	
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}


	
	public String getItemName() {
		return itemName;
	}


	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	
	public String getInputCode() {
		return inputCode;
	}


	
	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}


	
	public String getParentItemCode() {
		return parentItemCode;
	}


	
	public void setParentItemCode(String parentItemCode) {
		this.parentItemCode = parentItemCode;
	}


	
	public String getClassLevel() {
		return classLevel;
	}


	
	public void setClassLevel(String classLevel) {
		this.classLevel = classLevel;
	}


	
	public String getSeqInLevel() {
		return seqInLevel;
	}


	
	public void setSeqInLevel(String seqInLevel) {
		this.seqInLevel = seqInLevel;
	}


	
	public String getTableCode() {
		return tableCode;
	}


	
	public void setTableCode(String tableCode) {
		this.tableCode = tableCode;
	}


	
	public String getComments() {
		return comments;
	}


	
	public void setComments(String comments) {
		this.comments = comments;
	}


	

	
	public String getVerbId() {
		return verbId;
	}


	
	public void setVerbId(String verbId) {
		this.verbId = verbId;
	}


	
	public String getMessage() {
		return message;
	}


	
	public void setMessage(String message) {
		this.message = message;
	}


	
	public String getOrderNo() {
		return orderNo;
	}


	
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}


	
	public String getAsc() {
		return asc;
	}


	
	public void setAsc(String asc) {
		this.asc = asc;
	}


	
	public String getParentItemName() {
		return parentItemName;
	}


	
	public void setParentItemName(String parentItemName) {
		this.parentItemName = parentItemName;
	}


	
	public String getTableName() {
		return tableName;
	}


	
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}


	
	public String getIdHidden() {
		return idHidden;
	}


	
	public void setIdHidden(String idHidden) {
		this.idHidden = idHidden;
	}

 
	
	
	public String[] getParentItemCodes() {
		return parentItemCodes;
	}


	
	public void setParentItemCodes(String[] parentItemCodes) {
		this.parentItemCodes = parentItemCodes;
	}


	
	public String[] getParentItemNames() {
		return parentItemNames;
	}


	
	public void setParentItemNames(String[] parentItemNames) {
		this.parentItemNames = parentItemNames;
	}


	
	public String[] getTableCodes() {
		return tableCodes;
	}


	
	public void setTableCodes(String[] tableCodes) {
		this.tableCodes = tableCodes;
	}


	
	public String[] getTableNames() {
		return tableNames;
	}


	
	public void setTableNames(String[] tableNames) {
		this.tableNames = tableNames;
	}


	public String[] getItemCodeList() {
		return itemCodeList;
	}


	
	public void setItemCodeList(String[] itemCodeList) {
		this.itemCodeList = itemCodeList;
	}


	
	public String[] getItemNameList() {
		return itemNameList;
	}


	
	public void setItemNameList(String[] itemNameList) {
		this.itemNameList = itemNameList;
	}


	
	public String[] getInputCodeList() {
		return inputCodeList;
	}


	
	public void setInputCodeList(String[] inputCodeList) {
		this.inputCodeList = inputCodeList;
	}


	
	public String[] getParentItemCodeList() {
		return parentItemCodeList;
	}


	
	public void setParentItemCodeList(String[] parentItemCodeList) {
		this.parentItemCodeList = parentItemCodeList;
	}


	
	public String[] getParentItemNameList() {
		return parentItemNameList;
	}


	
	public void setParentItemNameList(String[] parentItemNameList) {
		this.parentItemNameList = parentItemNameList;
	}


	
	public String[] getClassLevelList() {
		return classLevelList;
	}


	
	public void setClassLevelList(String[] classLevelList) {
		this.classLevelList = classLevelList;
	}


	
	public String[] getSeqInLevelList() {
		return seqInLevelList;
	}


	
	public void setSeqInLevelList(String[] seqInLevelList) {
		this.seqInLevelList = seqInLevelList;
	}


	
	public String[] getCommentsList() {
		return commentsList;
	}


	
	public void setCommentsList(String[] commentsList) {
		this.commentsList = commentsList;
	}


	
	public String[] getTableCodeList() {
		return tableCodeList;
	}


	
	public void setTableCodeList(String[] tableCodeList) {
		this.tableCodeList = tableCodeList;
	}


	
	public String[] getTableNameList() {
		return tableNameList;
	}


	
	public void setTableNameList(String[] tableNameList) {
		this.tableNameList = tableNameList;
	}


	
}
