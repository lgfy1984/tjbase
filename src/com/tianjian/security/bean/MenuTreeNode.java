package com.tianjian.security.bean;

public class MenuTreeNode {
	public static final String NODE_TYPE_ROOT = "0";//根节点
	public static final String NODE_TYPE_PUBLIC_CLASS_1 = "1";// 一级模块类别
	public static final String NODE_TYPE_PUBLIC_CLASS_2 = "2";// 二级模块类别
	public static final String NODE_TYPE_PUBLIC = "3";// 模块
	public static final String NODE_TYPE_MENU = "4";// 菜单

	private String id;
	private String pId;
	private String name;
	private String type;

	private Boolean isParent;
	
	private String class1ChildType;//一级模块类别的子级类型， 可能是二级模块类别或者模块，两者不能共存

	public MenuTreeNode(String id, String pId, String name, String type) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

	public Boolean getIsParent() {
		return isParent;
	}
	public String getClass1ChildType() {
		return class1ChildType;
	}

	public void setClass1ChildType(String class1ChildType) {
		this.class1ChildType = class1ChildType;
	}

}
