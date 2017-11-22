package com.tianjian.security.struts.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class StaffBatchCreateForm extends ActionForm{

	private static final long serialVersionUID = 4892504068866393744L;
	private String filter;
	private String[] selectedHspIds;
	private int count;
	private List<?> resultList;
	
	private String verbId="";
	private String message="";
	private String idHidden; //供修改.删除使用
	private String orderNo="";
	private String asc="";
	public String getFilter() {
		return filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
	}
	public String[] getSelectedHspIds() {
		return selectedHspIds;
	}
	public void setSelectedHspIds(String[] selectedHspIds) {
		this.selectedHspIds = selectedHspIds;
	}
	public List<?> getResultList() {
		return resultList;
	}
	public void setResultList(List<?> resultList) {
		this.resultList = resultList;
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
	public String getIdHidden() {
		return idHidden;
	}
	public void setIdHidden(String idHidden) {
		this.idHidden = idHidden;
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
