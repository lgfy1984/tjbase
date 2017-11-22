package com.tianjian.comm.struts.form;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;


public class CommDictPublicClassForm  extends ActionForm implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -60887391331975533L;
	// Fields
	
	/**类别代码*/
	private String classCode;
	/**类别名称*/
	private String className;
	/**输入码*/
	private String inputCode;
	/**备注*/
	private String comments;
	/**序号*/
	private String seqNo;
	
    private String classCodeHidden;
    
    private String verbId;
    
    private String message;
	/**排序*/
	private String orderNo;
     /**升序*/
	private String asc;
	
	private String[] classCodeList;
	private String[] classNameList;
	private String[] inputCodeList;
	private String[] commentsList;
	private String[] seqNoList;

	public CommDictPublicClassForm(String classCode, String className, String inputCode, String comments, String seqNo) {
		super();
		this.classCode = classCode;
		this.className = className;
		this.inputCode = inputCode;
		this.comments = comments;
		this.seqNo = seqNo;
	}

	public CommDictPublicClassForm() {
		super();
		this.classCode ="";
		this.className = "";
		this.inputCode = "";
		this.comments = "";
		this.seqNo = "";
		this.orderNo = "";
		this.classCodeHidden="";
		this.asc="";
		this.message="";
		// TODO Auto-generated constructor stub
	}

	/** */
	
	public String getClassCode() {
		return classCode;
	}
	
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	
	public String getClassName() {
		return className;
	}
	
	public void setClassName(String className) {
		this.className = className;
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
	

	
	
	public String getSeqNo() {
		return seqNo;
	}

	
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
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

	

	
	
	public String getClassCodeHidden() {
		return classCodeHidden;
	}

	
	public void setClassCodeHidden(String classCodeHidden) {
		this.classCodeHidden = classCodeHidden;
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
}
