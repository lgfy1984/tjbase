package com.tianjian.comm.struts.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class CommCongihMidwiferyForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3811321414964449633L;
	private String hspConfigBaseinfoId="";
	private String hspClassCode="";
	private String seqNo="";
	
	private String orderNo="";
	private String asc="";
	private String message="";
	private String verbId="";
	private String hspConfigBaseinfoIdHidden="";
	private String hspCalssCodeHidden="";
	
	private List<?> hspConfigBaseinfoIdList=null;
	private List<?> hspClassCodeList=null;
	private List<?> hspConfigBaseinfoNameList=null;
	private List<?> hspCalssNameList=null;
	private List<?> seqNoList=null;
	
	public String getHspConfigBaseinfoId() {
		return hspConfigBaseinfoId;
	}
	public void setHspConfigBaseinfoId(String hspConfigBaseinfoId) {
		this.hspConfigBaseinfoId = hspConfigBaseinfoId;
	}
	
	public String getHspClassCode() {
		return hspClassCode;
	}
	public void setHspClassCode(String hspClassCode) {
		this.hspClassCode = hspClassCode;
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<?> getHspConfigBaseinfoNameList() {
		return hspConfigBaseinfoNameList;
	}
	public void setHspConfigBaseinfoNameList(List<?> hspConfigBaseinfoNameList) {
		this.hspConfigBaseinfoNameList = hspConfigBaseinfoNameList;
	}
	public List<?> getHspCalssNameList() {
		return hspCalssNameList;
	}
	public void setHspCalssNameList(List<?> hspCalssNameList) {
		this.hspCalssNameList = hspCalssNameList;
	}
	public List<?> getSeqNoList() {
		return seqNoList;
	}
	public void setSeqNoList(List<?> seqNoList) {
		this.seqNoList = seqNoList;
	}
	public String getVerbId() {
		return verbId;
	}
	public void setVerbId(String verbId) {
		this.verbId = verbId;
	}
	public String getHspConfigBaseinfoIdHidden() {
		return hspConfigBaseinfoIdHidden;
	}
	public void setHspConfigBaseinfoIdHidden(String hspConfigBaseinfoIdHidden) {
		this.hspConfigBaseinfoIdHidden = hspConfigBaseinfoIdHidden;
	}
	public String getHspCalssCodeHidden() {
		return hspCalssCodeHidden;
	}
	public void setHspCalssCodeHidden(String hspCalssCodeHidden) {
		this.hspCalssCodeHidden = hspCalssCodeHidden;
	}
	public List<?> getHspConfigBaseinfoIdList() {
		return hspConfigBaseinfoIdList;
	}
	public void setHspConfigBaseinfoIdList(List<?> hspConfigBaseinfoIdList) {
		this.hspConfigBaseinfoIdList = hspConfigBaseinfoIdList;
	}
	public List<?> getHspClassCodeList() {
		return hspClassCodeList;
	}
	public void setHspClassCodeList(List<?> hspClassCodeList) {
		this.hspClassCodeList = hspClassCodeList;
	}
	
}
