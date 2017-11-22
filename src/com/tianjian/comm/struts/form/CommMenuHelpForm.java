package com.tianjian.comm.struts.form;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;

import com.tianjian.comm.bean.CommMenuHelp;


public class CommMenuHelpForm  extends ActionForm implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3114346780385030192L;
	// Fields
	
	
	/**代码*/
	private String id;
	private String idHidden;
	private String menuName;
	/**名称*/
	private String menuHelpTitle;
	
	private String menuHelpContent;
	/**输入码*/
	private String createDate;
	/**备注*/
	private String menuHelpComment;
	private String menuFlag;
	/**序号*/
	private String seqNo;
	
    private String verbId;
    
    private String message;
	/**排序*/
	private String orderNo;
     /**升序*/
	private String asc;
	
	
	private String createUserId;
	private String createUserName;
	
	private CommMenuHelp help;
	
	private String[] idList;
	private String[] menuNameList;
	private String[] menuHelpTitleList;
	private String[] menuFlagList;
	private String[] createDateList;
	private String[] menuHelpCommentList;
	private String[] seqNoList;

	public CommMenuHelpForm(String id,String idHidden,String menuName, String menuHelpTitle,
			String menuHelpContent,String createDate, String menuHelpComment,
			String seqNo,String createUserId,String createUserName,String menuFlag) {
		super();
		this.id = id;
		this.idHidden = idHidden;
		this.menuName = menuName;
		this.menuHelpTitle = menuHelpTitle;
		this.menuHelpContent = menuHelpContent;
		this.menuFlag = menuFlag;
		this.createDate = createDate;
		this.menuHelpComment = menuHelpComment;
		this.seqNo = seqNo;
		this.createUserId = createUserId;
		this.createUserName = createUserName;
	}

	public CommMenuHelpForm() {
		super();
		this.id  = "";
		this.idHidden = "";
		this.menuName ="";
		this.menuHelpTitle = "";
		this.createDate = "";
		this.menuFlag = "";
		this.menuHelpContent = "";
		this.menuHelpComment = "";
		this.seqNo = "";
		this.orderNo = "";
		this.asc="";
		this.message="";
		this.createUserId = "";
		this.createUserName = "";
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuHelpTitle() {
		return menuHelpTitle;
	}

	public void setMenuHelpTitle(String menuHelpTitle) {
		this.menuHelpTitle = menuHelpTitle;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getMenuHelpComment() {
		return menuHelpComment;
	}

	public void setMenuHelpComment(String menuHelpComment) {
		this.menuHelpComment = menuHelpComment;
	}

	public String getSeqNo() {
		return seqNo;
	}

	
	public String getMenuHelpContent() {
		return menuHelpContent;
	}

	public void setMenuHelpContent(String menuHelpContent) {
		this.menuHelpContent = menuHelpContent;
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

	
	public String[] getMenuNameList() {
		return menuNameList;
	}

	public void setMenuNameList(String[] menuNameList) {
		this.menuNameList = menuNameList;
	}

	public String[] getMenuHelpTitleList() {
		return menuHelpTitleList;
	}

	public void setMenuHelpTitleList(String[] menuHelpTitleList) {
		this.menuHelpTitleList = menuHelpTitleList;
	}

	public String[] getCreateDateList() {
		return createDateList;
	}

	public void setCreateDateList(String[] createDateList) {
		this.createDateList = createDateList;
	}

	public String[] getMenuHelpCommentList() {
		return menuHelpCommentList;
	}

	public void setMenuHelpCommentList(String[] menuHelpCommentList) {
		this.menuHelpCommentList = menuHelpCommentList;
	}

	public String[] getSeqNoList() {
		return seqNoList;
	}

	
	public void setSeqNoList(String[] seqNoList) {
		this.seqNoList = seqNoList;
	}

	public String[] getIdList() {
		return idList;
	}

	public void setIdList(String[] idList) {
		this.idList = idList;
	}

	public String getIdHidden() {
		return idHidden;
	}

	public void setIdHidden(String idHidden) {
		this.idHidden = idHidden;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String[] getMenuFlagList() {
		return menuFlagList;
	}

	public void setMenuFlagList(String[] menuFlagList) {
		this.menuFlagList = menuFlagList;
	}

	public String getMenuFlag() {
		return menuFlag;
	}

	public void setMenuFlag(String menuFlag) {
		this.menuFlag = menuFlag;
	}

	public CommMenuHelp getHelp() {
		return help;
	}

	public void setHelp(CommMenuHelp help) {
		this.help = help;
	}

	
}
