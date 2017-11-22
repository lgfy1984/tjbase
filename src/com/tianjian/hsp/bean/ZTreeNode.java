package com.tianjian.hsp.bean;

public class ZTreeNode {
	
	private String id;
	private String pId;
	private String name;
	private boolean isOpen = false;
	private boolean isParent = false;
	private String iconOpen = "";
	private String iconClose = "";
	private String icon = "";
	private String target = "righFrame";
	private String url;
	
	//
	private String orgId;
	private String itemCode;
	private String deptCode;
	private String rMenuFlag;//右键菜单标识
	private String hspStaffId;
	
	public String getHspStaffId() {
		return hspStaffId;
	}

	public void setHspStaffId(String hspStaffId) {
		this.hspStaffId = hspStaffId;
	}

	public String getRMenuFlag() {
		return rMenuFlag;
	}

	public void setRMenuFlag(String menuFlag) {
		rMenuFlag = menuFlag;
	}

	public ZTreeNode() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ZTreeNode(String id, String pid, String name, boolean isParent){
		this(id, pid, name, isParent, null);
	}
	
	public ZTreeNode(String id, String pid, String name, boolean isParent, String url){
		super();
		this.id = id;
		pId = pid;
		this.name = name;
		this.isParent = isParent;
		this.url = url;
	}

	public ZTreeNode(String id, String id2, String name, boolean isOpen,
			boolean isParent, String iconOpen, String iconClose, String icon,
			String target, String url) {
		super();
		this.id = id;
		pId = id2;
		this.name = name;
		this.isOpen = isOpen;
		this.isParent = isParent;
		this.iconOpen = iconOpen;
		this.iconClose = iconClose;
		this.icon = icon;
		this.target = target;
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPId() {
		return pId;
	}

	public void setPId(String id) {
		pId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	public boolean isParent() {
		return isParent;
	}

	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}

	public String getIconOpen() {
		return iconOpen;
	}

	public void setIconOpen(String iconOpen) {
		this.iconOpen = iconOpen;
	}

	public String getIconClose() {
		return iconClose;
	}

	public void setIconClose(String iconClose) {
		this.iconClose = iconClose;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
}
