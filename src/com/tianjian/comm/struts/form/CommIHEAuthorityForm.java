package com.tianjian.comm.struts.form;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;


public class CommIHEAuthorityForm  extends ActionForm implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3114346780385030192L;
	// Fields
	
	private String id;
	private String universalId;
	private String universalIdType;
	private String namespaceId;
	private String dateCreated;
	private String creatorId;
	private String seqNo;
	
    private String idHidden;
    
    private String verbId;
    
    private String message;
	/**排序*/
	private String orderNo;
     /**升序*/
	private String asc;
	
	private String[] universalIdList;
	private String[] universalIdTypeList;
	private String[] namespaceIdList;
	private String[] dateCreatedList;
	private String[] creatorIdList;
	private String[] seqNoList;
	private String[] idList;

	public CommIHEAuthorityForm(String id,String universalId, String universalIdType, String namespaceId, String dateCreated,String  creatorId,String seqNo) {
		this.universalId = universalId;
		this.universalIdType = universalIdType;
		this.namespaceId = namespaceId;
		this.dateCreated = dateCreated;
		this.creatorId = creatorId;
		this.seqNo = seqNo;
		this.id = id;
	}

	public CommIHEAuthorityForm() {
		this.id = "";
		this.universalId ="";
		this.universalIdType = "";
		this.namespaceId = "";
		this.dateCreated = "";
		this.seqNo = "";
		this.creatorId = "";
		this.orderNo = "";
		this.idHidden="";
		this.asc="";
		this.message="";
		// TODO Auto-generated constructor stub
	}

	public String getUniversalId() {
		return universalId;
	}

	public void setUniversalId(String universalId) {
		this.universalId = universalId;
	}

	public String getUniversalIdType() {
		return universalIdType;
	}

	public void setUniversalIdType(String universalIdType) {
		this.universalIdType = universalIdType;
	}

	public String getNamespaceId() {
		return namespaceId;
	}

	public void setNamespaceId(String namespaceId) {
		this.namespaceId = namespaceId;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getIdHidden() {
		return idHidden;
	}

	public void setIdHidden(String idHidden) {
		this.idHidden = idHidden;
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

	public String[] getUniversalIdList() {
		return universalIdList;
	}

	public void setUniversalIdList(String[] universalIdList) {
		this.universalIdList = universalIdList;
	}

	public String[] getUniversalIdTypeList() {
		return universalIdTypeList;
	}

	public void setUniversalIdTypeList(String[] universalIdTypeList) {
		this.universalIdTypeList = universalIdTypeList;
	}

	public String[] getNamespaceIdList() {
		return namespaceIdList;
	}

	public void setNamespaceIdList(String[] namespaceIdList) {
		this.namespaceIdList = namespaceIdList;
	}


	public String[] getDateCreatedList() {
		return dateCreatedList;
	}

	public void setDateCreatedList(String[] dateCreatedList) {
		this.dateCreatedList = dateCreatedList;
	}

	public String[] getCreatorIdList() {
		return creatorIdList;
	}

	public void setCreatorIdList(String[] creatorIdList) {
		this.creatorIdList = creatorIdList;
	}

	public String[] getSeqNoList() {
		return seqNoList;
	}

	public void setSeqNoList(String[] seqNoList) {
		this.seqNoList = seqNoList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getIdList() {
		return idList;
	}

	public void setIdList(String[] idList) {
		this.idList = idList;
	}



	
}
