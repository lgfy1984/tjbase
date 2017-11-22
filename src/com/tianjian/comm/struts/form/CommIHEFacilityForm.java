package com.tianjian.comm.struts.form;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;


public class CommIHEFacilityForm  extends ActionForm implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3114346780385030192L;
	// Fields
	
	private String id;
	private String facilityUniversalId;//分配机构通用ID
	private String facilityUniversalIdType;//分配机构通用ID类型
	private String facilityNamespaceId;//分配机构命名空间ID
	private String dateCreated;//创建时间
	private String creatorId;//创建人id
	private String seqNo;//序号
	
    private String idHidden;
    
    private String verbId;
    
    private String message;
	/**排序*/
	private String orderNo;
     /**升序*/
	private String asc;
	
	private String[] facilityUniversalIdList;
	private String[] facilityUniversalIdTypeList;
	private String[] facilityNamespaceIdList;
	private String[] dateCreatedList;
	private String[] creatorIdList;
	private String[] seqNoList;
	private String[] idList;

	public CommIHEFacilityForm(String facilityUniversalId, String facilityUniversalIdType,
			String facilityNamespaceId, String dateCreated,
			String  creatorId,String seqNo) {
		super();
		this.facilityUniversalId = facilityUniversalId;
		this.facilityUniversalIdType = facilityUniversalIdType;
		this.facilityNamespaceId = facilityNamespaceId;
		this.dateCreated = dateCreated;
		this.creatorId = creatorId;
		this.seqNo = seqNo;
	}

	public CommIHEFacilityForm() {
		super();
		this.facilityUniversalId ="";
		this.facilityUniversalIdType = "";
		this.facilityNamespaceId = "";
		this.dateCreated = "";
		this.creatorId = "";
		this.seqNo = "";
		this.orderNo = "";
		this.idHidden="";
		this.asc="";
		this.message="";
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFacilityUniversalId() {
		return facilityUniversalId;
	}

	public void setFacilityUniversalId(String facilityUniversalId) {
		this.facilityUniversalId = facilityUniversalId;
	}

	public String getFacilityUniversalIdType() {
		return facilityUniversalIdType;
	}

	public void setFacilityUniversalIdType(String facilityUniversalIdType) {
		this.facilityUniversalIdType = facilityUniversalIdType;
	}

	public String getFacilityNamespaceId() {
		return facilityNamespaceId;
	}

	public void setFacilityNamespaceId(String facilityNamespaceId) {
		this.facilityNamespaceId = facilityNamespaceId;
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

	public String[] getFacilityUniversalIdList() {
		return facilityUniversalIdList;
	}

	public void setFacilityUniversalIdList(String[] facilityUniversalIdList) {
		this.facilityUniversalIdList = facilityUniversalIdList;
	}

	public String[] getFacilityUniversalIdTypeList() {
		return facilityUniversalIdTypeList;
	}

	public void setFacilityUniversalIdTypeList(String[] facilityUniversalIdTypeList) {
		this.facilityUniversalIdTypeList = facilityUniversalIdTypeList;
	}

	public String[] getFacilityNamespaceIdList() {
		return facilityNamespaceIdList;
	}

	public void setFacilityNamespaceIdList(String[] facilityNamespaceIdList) {
		this.facilityNamespaceIdList = facilityNamespaceIdList;
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

	public String[] getIdList() {
		return idList;
	}

	public void setIdList(String[] idList) {
		this.idList = idList;
	}



	
	

	
}
