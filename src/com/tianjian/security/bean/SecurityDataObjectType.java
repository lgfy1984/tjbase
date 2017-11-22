package com.tianjian.security.bean;

/**
 * SecurityDataObjectType entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SecurityDataObjectType implements java.io.Serializable {

	// Fields

	private String id;
	private String dataObjectTypeName;
	private String columnName;

	// Constructors

	/** default constructor */
	public SecurityDataObjectType() {
	}

	/** full constructor */
	public SecurityDataObjectType(String dataObjectTypeName, String columnName) {
		this.dataObjectTypeName = dataObjectTypeName;
		this.columnName = columnName;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDataObjectTypeName() {
		return this.dataObjectTypeName;
	}

	public void setDataObjectTypeName(String dataObjectTypeName) {
		this.dataObjectTypeName = dataObjectTypeName;
	}

	public String getColumnName() {
		return this.columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

}