package com.tianjian.security.struts.form;

import org.apache.struts.action.ActionForm;

public class SecurityUserVsRolesForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6101004132873744686L;
	private String userId = "";
	private String userName = "";
	private String userInput = "";
	private String pageNow = "";
	private String message = "";
	private String orderNo = "";
	private String asc = "";
	private String hspConfigBaseinfoName = "";
	private String hspConfigBaseinfoId = "";
	
	
	private String[] roleId;	
	private String[] roleDetail;	
	private String[] userIds;	
	public String getHspConfigBaseinfoName() {
		return hspConfigBaseinfoName;
	}

	public void setHspConfigBaseinfoName(String hspConfigBaseinfoName) {
		this.hspConfigBaseinfoName = hspConfigBaseinfoName;
	}

	public String getHspConfigBaseinfoId() {
		return hspConfigBaseinfoId;
	}

	public void setHspConfigBaseinfoId(String hspConfigBaseinfoId) {
		this.hspConfigBaseinfoId = hspConfigBaseinfoId;
	}

	private String[] userNames;
	private String[] users;
	private String[] hspConfigs;
	private String[] roleIdSelected;
	
	private String userIdSelected;
	private String verbId;	
	
	public 	SecurityUserVsRolesForm(){
		userId = "";
		userName = "";
		userInput = "";
		userIdSelected = "";
		verbId = "";
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserInput() {
		return userInput;
	}

	public void setUserInput(String userInput) {
		this.userInput = userInput;
	}

	public String[] getRoleId() {
		return roleId;
	}

	public void setRoleId(String[] roleId) {
		this.roleId = roleId;
	}

	public String[] getRoleDetail() {
		return roleDetail;
	}

	public void setRoleDetail(String[] roleDetail) {
		this.roleDetail = roleDetail;
	}

	public String[] getUserIds() {
		return userIds;
	}

	public void setUserIds(String[] userIds) {
		this.userIds = userIds;
	}

	public String[] getUserNames() {
		return userNames;
	}

	public void setUserNames(String[] userNames) {
		this.userNames = userNames;
	}

	public String[] getRoleIdSelected() {
		return roleIdSelected;
	}

	public void setRoleIdSelected(String[] roleIdSelected) {
		this.roleIdSelected = roleIdSelected;
	}

	public String getUserIdSelected() {
		return userIdSelected;
	}

	public void setUserIdSelected(String userIdSelected) {
		this.userIdSelected = userIdSelected;
	}

	public String getVerbId() {
		return verbId;
	}

	public void setVerbId(String verbId) {
		this.verbId = verbId;
	}

	public String[] getUsers() {
		return users;
	}

	public void setUsers(String[] users) {
		this.users = users;
	}

	public String[] getHspConfigs() {
		return hspConfigs;
	}

	public void setHspConfigs(String[] hspConfigs) {
		this.hspConfigs = hspConfigs;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPageNow() {
		return pageNow;
	}

	public void setPageNow(String pageNow) {
		this.pageNow = pageNow;
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
	
	

}

