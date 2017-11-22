package com.tianjian.hsp.bean;

import java.util.List;



public class BaseMessage {
	private String receiverId;//发送系统的域ID
	private String senderId;//接受系统的域ID
	private String idRoot;//xml消息的唯一标识符
	
	private String localID;//系统localID
	private String idDomain;//
	private String sendIdDomain;//发送系统域ID
	private String queryId;//查询唯一ID
	private String queryIdDomain;//指定查询的域ID
	
	private List<String> idDomainList;//要查询的域ID，可以为多个
	
	private String sequenceNumber;//顺序号
	private String elementName;//元素名称
	private String directionCode;//方向代码
	private String initialQuantity;//请求返回最大的记录数
	private String pageSize;
	
	private String mergeId; //合并的主ID
	private String mergeIdDomain;//合并的主ID域
	
	private String commTypeId;
	private String commTypeName;
	
	private List<SecurityIHEPatientIdList> idLists;//合并的时候其他的一些id和id域
	
	public BaseMessage(){
		
	}
	
	public BaseMessage(String receiverId,String senderId,
			String idRoot,String localID,String idDomain
			,String sendIdDomain, List<String> idDomainList
			,String queryId,String queryIdDomain,String sequenceNumber,
			String elementName,String directionCode,String mergeId, 
			String mergeIdDomain,String pageSize,String commTypeId, String commTypeName){
		this.receiverId = receiverId;
		this.senderId = senderId;
		this.idRoot = idRoot;
		this.localID = localID;
		this.idDomain = idDomain;
		this.sendIdDomain = sendIdDomain;
		this.idDomainList = idDomainList;
		this.queryId = queryId;
		this.queryIdDomain = queryIdDomain;
		this.sequenceNumber = sequenceNumber;
		this.elementName = elementName;
		this.directionCode = directionCode;
		this.mergeId = mergeId;
		this.mergeIdDomain = mergeIdDomain;
		this.pageSize = pageSize;
		this.commTypeId = commTypeId;
		this.commTypeName = commTypeName;
	}
	
	public String getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getIdRoot() {
		return idRoot;
	}
	public void setIdRoot(String idRoot) {
		this.idRoot = idRoot;
	}

	public String getLocalID() {
		return localID;
	}

	public void setLocalID(String localID) {
		this.localID = localID;
	}
	
	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getIdDomain() {
		return idDomain;
	}

	public void setIdDomain(String idDomain) {
		this.idDomain = idDomain;
	}

	public String getSendIdDomain() {
		return sendIdDomain;
	}

	public void setSendIdDomain(String sendIdDomain) {
		this.sendIdDomain = sendIdDomain;
	}

	public List<String> getIdDomainList() {
		return idDomainList;
	}

	public void setIdDomainList(List<String> idDomainList) {
		this.idDomainList = idDomainList;
	}

	public String getQueryId() {
		return queryId;
	}

	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}

	public String getQueryIdDomain() {
		return queryIdDomain;
	}

	public void setQueryIdDomain(String queryIdDomain) {
		this.queryIdDomain = queryIdDomain;
	}

	public String getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(String sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public String getDirectionCode() {
		return directionCode;
	}

	public void setDirectionCode(String directionCode) {
		this.directionCode = directionCode;
	}

	public String getInitialQuantity() {
		return initialQuantity;
	}

	public void setInitialQuantity(String initialQuantity) {
		this.initialQuantity = initialQuantity;
	}

	public String getMergeId() {
		return mergeId;
	}

	public void setMergeId(String mergeId) {
		this.mergeId = mergeId;
	}

	public String getMergeIdDomain() {
		return mergeIdDomain;
	}

	public void setMergeIdDomain(String mergeIdDomain) {
		this.mergeIdDomain = mergeIdDomain;
	}

	public List<SecurityIHEPatientIdList> getIdLists() {
		return idLists;
	}

	public void setIdLists(List<SecurityIHEPatientIdList> idLists) {
		this.idLists = idLists;
	}

	public String getCommTypeId() {
		return commTypeId;
	}

	public void setCommTypeId(String commTypeId) {
		this.commTypeId = commTypeId;
	}

	public String getCommTypeName() {
		return commTypeName;
	}

	public void setCommTypeName(String commTypeName) {
		this.commTypeName = commTypeName;
	}

	
	
	
	
	
}
