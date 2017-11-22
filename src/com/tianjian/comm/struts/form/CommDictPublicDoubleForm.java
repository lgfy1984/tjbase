package com.tianjian.comm.struts.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;



public class CommDictPublicDoubleForm extends ActionForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 892132997299480727L;
	// Fields
	/**字典ID*/
	private String id;
	/**字典类别*/
	private String classCode;
	/**字典代码*/
	private String dictCode;
	/**字典名称*/
	private String dictValue;
	/**输入码*/
	private String inputCode;
	/**备注*/
	private String comments;
	/**序号*/
	private String seqNo;
	//-------定义排序等功能列-----------------------
    private String verbId;
    private String message;
    private String orderNo;
    private String asc;
    //-------处理代码对应的字典名称------------------
	private String className;
	 //-------------------------------------------
	private String idHidden;
	//--------所有字典都定义成数组-------------------
	/**字典类别Id*/
	private String[] classCodes;
	/**字典类别名称*/
	private String[] classNames;
	
	 //-----定义query查询列表---------------
	private String[] idList;
	/**字典类别Id*/
	private String[] classCodeList;
	/**字典类别Name*/
	private String[] classNameList;
	/**字典代码*/
	private String[] dictCodeList;
	/**字典名称*/
	private String[] dictValueList;
	/**输入码*/
	private String[] inputCodeList;
	/**备注*/
	private String[] commentsList;
	/**序号*/
	private String[] seqNoList;
	
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


	public CommDictPublicDoubleForm() {
		super();
		// TODO Auto-generated constructor stub
		 id="";
		/**字典类别Id*/
		 classCode="";
		/**字典代码*/
		 dictCode="";
		/**字典名称*/
		 dictValue="";
		/**输入码*/
		 inputCode="";
		/**备注*/
		 comments="";
		/**序号*/
		 seqNo="";
		//-------定义排序等功能列-----------------------
	     verbId="";
	     message="";
	     orderNo="";
	     asc="";
	    //-------处理代码对应的字典名称------------------
		 className="";
		 //-------------------------------------------
		 idHidden="";
	
	}

	// Property accessors
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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


	
	public String getSeqNo() {
		return seqNo;
	}


	
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
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



	
	public String[] getIdList() {
		return idList;
	}


	
	public void setIdList(String[] idList) {
		this.idList = idList;
	}


	
	
	public String[] getInputCodeList() {
		return inputCodeList;
	}


	
	public void setInputCodeList(String[] inputCodeList) {
		this.inputCodeList = inputCodeList;
	}


	
	public String[] getCommentsList() {
		return commentsList;
	}


	
	public void setCommentsList(String[] commentsList) {
		this.commentsList = commentsList;
	}


	
	public String[] getSeqNoList() {
		return seqNoList;
	}


	
	public void setSeqNoList(String[] seqNoList) {
		this.seqNoList = seqNoList;
	}


	

	


	public String getIdHidden() {
		return idHidden;
	}


	
	public void setIdHidden(String idHidden) {
		this.idHidden = idHidden;
	}


	
	public String getClassCode() {
		return classCode;
	}


	
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}


	
	public String getDictCode() {
		return dictCode;
	}


	
	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}


	
	public String getDictValue() {
		return dictValue;
	}


	
	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}


	
	public String getClassName() {
		return className;
	}


	
	public void setClassName(String className) {
		this.className = className;
	}


	
	public String[] getClassCodes() {
		return classCodes;
	}


	
	public void setClassCodes(String[] classCodes) {
		this.classCodes = classCodes;
	}


	
	public String[] getClassNames() {
		return classNames;
	}


	
	public void setClassNames(String[] classNames) {
		this.classNames = classNames;
	}


	
	public String[] getClassCodeList() {
		return classCodeList;
	}


	
	public void setClassCodeList(String[] classCodeList) {
		this.classCodeList = classCodeList;
	}


	
	public String[] getClassNameList() {
		return classNameList;
	}


	
	public void setClassNameList(String[] classNameList) {
		this.classNameList = classNameList;
	}


	
	public String[] getDictCodeList() {
		return dictCodeList;
	}


	
	public void setDictCodeList(String[] dictCodeList) {
		this.dictCodeList = dictCodeList;
	}


	
	public String[] getDictValueList() {
		return dictValueList;
	}


	
	public void setDictValueList(String[] dictValueList) {
		this.dictValueList = dictValueList;
	}


	
	
}
