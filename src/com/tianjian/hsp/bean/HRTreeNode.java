package com.tianjian.hsp.bean;

public class HRTreeNode {
	public static final String NODE_TYPE_ROOT = "root";
	public static final String NODE_TYPE_HSP = "hsp";
	public static final String NODE_TYPE_DEPTLIST = "deptlist";
	public static final String NODE_TYPE_DEPT = "dept";
	public static final String NODE_TYPE_OTHER_DEPT = "otherdept";
	public static final String NODE_TYPE_STAFF = "staff";
	public static final String NODE_TYPE_EQUIP = "equip";
	
	
	private String id;
	private String pId;
	private String name;
	private String type = "";
	
	//
	private String hspId;
	private String hspCode;
	private String deptCode;
	private String staffId;
	private String equipId;
	

	public HRTreeNode(String id, String pId, String name, String type) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.type = type;
	}
	
	public HRTreeNode(String id, String pId, String name, String type, 
			String hspId) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.type = type;
		this.hspId = hspId;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHspId() {
		return hspId;
	}

	public void setHspId(String hspId) {
		this.hspId = hspId;
	}

	public String getHspCode() {
		return hspCode;
	}

	public void setHspCode(String hspCode) {
		this.hspCode = hspCode;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getEquipId() {
		return equipId;
	}

	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}

}
