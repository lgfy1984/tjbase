package com.tianjian.security.struts.form;

import org.apache.struts.action.ActionForm;

public class SecurityDataObjectTypeForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String id="";
	private String dataObjectTypeName="";
	private String comulnName="";
	//-----定义query查询列表-------------------------------
	
	private String[] ids;
	private String[] dataObjectTypeNames;
	private String[] comulnNames;
	
	private String verbId;
	private String message;
	private String orderNo;
	private String asc;
	private String itemCodeHidden;
	
	public SecurityDataObjectTypeForm() {
		verbId="";
		message="";
		orderNo="";
		asc="";
		itemCodeHidden="";
	}

	public SecurityDataObjectTypeForm(String id, String dataObjectTypeName,
			String comulnName, String[] ids, String[] dataObjectTypeNames,
			String[] comulnNames, String verbId, String message,
			String orderNo, String asc, String itemCodeHidden) {
		super();
		this.id = id;
		this.dataObjectTypeName = dataObjectTypeName;
		this.comulnName = comulnName;
		this.ids = ids;
		this.dataObjectTypeNames = dataObjectTypeNames;
		this.comulnNames = comulnNames;
		this.verbId = verbId;
		this.message = message;
		this.orderNo = orderNo;
		this.asc = asc;
		this.itemCodeHidden = itemCodeHidden;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDataObjectTypeName() {
		return dataObjectTypeName;
	}

	public void setDataObjectTypeName(String dataObjectTypeName) {
		this.dataObjectTypeName = dataObjectTypeName;
	}

	public String getComulnName() {
		return comulnName;
	}

	public void setComulnName(String comulnName) {
		this.comulnName = comulnName;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String[] getDataObjectTypeNames() {
		return dataObjectTypeNames;
	}

	public void setDataObjectTypeNames(String[] dataObjectTypeNames) {
		this.dataObjectTypeNames = dataObjectTypeNames;
	}

	public String[] getComulnNames() {
		return comulnNames;
	}

	public void setComulnNames(String[] comulnNames) {
		this.comulnNames = comulnNames;
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

	public String getItemCodeHidden() {
		return itemCodeHidden;
	}

	public void setItemCodeHidden(String itemCodeHidden) {
		this.itemCodeHidden = itemCodeHidden;
	}
}
