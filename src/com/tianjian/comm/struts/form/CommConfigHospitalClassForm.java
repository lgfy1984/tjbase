package com.tianjian.comm.struts.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class CommConfigHospitalClassForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5580649811796874383L;
	private String classCode="";
	private String className="";
	private String inputCode="";
	private String comments="";
	private String seqNo="";
	
	private String verbId="";
	private String message="";
	private String orderNo="";
	private String asc="";
	private String mypage="";
	private String classCodeHidden="";
	private String classNameHidden="";
	private String inputCodeHidden="";
	
	private List<?> classCodeList=null;
	private List<?> classNameList=null;
	private List<?> inputCodeList=null;
	private List<?> seqNoList=null;
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
	public String getMypage() {
		return mypage;
	}
	public void setMypage(String mypage) {
		this.mypage = mypage;
	}
	public String getClassCodeHidden() {
		return classCodeHidden;
	}
	public void setClassCodeHidden(String classCodeHidden) {
		this.classCodeHidden = classCodeHidden;
	}
	public String getClassNameHidden() {
		return classNameHidden;
	}
	public void setClassNameHidden(String classNameHidden) {
		this.classNameHidden = classNameHidden;
	}
	public String getInputCodeHidden() {
		return inputCodeHidden;
	}
	public void setInputCodeHidden(String inputCodeHidden) {
		this.inputCodeHidden = inputCodeHidden;
	}
	public List<?> getClassCodeList() {
		return classCodeList;
	}
	public void setClassCodeList(List<?> classCodeList) {
		this.classCodeList = classCodeList;
	}
	public List<?> getClassNameList() {
		return classNameList;
	}
	public void setClassNameList(List<?> classNameList) {
		this.classNameList = classNameList;
	}
	public List<?> getInputCodeList() {
		return inputCodeList;
	}
	public void setInputCodeList(List<?> inputCodeList) {
		this.inputCodeList = inputCodeList;
	}
	public List<?> getSeqNoList() {
		return seqNoList;
	}
	public void setSeqNoList(List<?> seqNoList) {
		this.seqNoList = seqNoList;
	}
}
