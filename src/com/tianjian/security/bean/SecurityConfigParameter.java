package com.tianjian.security.bean;

/**
 * SecurityConfigParameter entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SecurityConfigParameter implements java.io.Serializable {

	// Fields

	private String id;
	private String classCode;
	private String hspConfigBaseinfoId;
	private String itemName;
	private String itemValue;
	private String usageDescription;
	private String initValue;

	// Constructors

	/** default constructor */
	public SecurityConfigParameter() {
	}

	/** minimal constructor */
	public SecurityConfigParameter(String classCode) {
		this.classCode = classCode;
	}

	/** full constructor */
	public SecurityConfigParameter(String classCode,
			String hspConfigBaseinfoId, String itemName, String itemValue,
			String usageDescription, String initValue) {
		this.classCode = classCode;
		this.hspConfigBaseinfoId = hspConfigBaseinfoId;
		this.itemName = itemName;
		this.itemValue = itemValue;
		this.usageDescription = usageDescription;
		this.initValue = initValue;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClassCode() {
		return this.classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getHspConfigBaseinfoId() {
		return this.hspConfigBaseinfoId;
	}

	public void setHspConfigBaseinfoId(String hspConfigBaseinfoId) {
		this.hspConfigBaseinfoId = hspConfigBaseinfoId;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemValue() {
		return this.itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	public String getUsageDescription() {
		return this.usageDescription;
	}

	public void setUsageDescription(String usageDescription) {
		this.usageDescription = usageDescription;
	}

	public String getInitValue() {
		return this.initValue;
	}

	public void setInitValue(String initValue) {
		this.initValue = initValue;
	}

}