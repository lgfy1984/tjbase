package com.tianjian.comm.bean;

/**
 * CommConfigLnglat entity. @author MyEclipse Persistence Tools
 */

public class CommConfigLnglat implements java.io.Serializable {

	// Fields

	private String id;
	private String itemCode;
	private String itemName;
	private String lat;
	private String lng;
	private String comments;

	// Constructors

	/** default constructor */
	public CommConfigLnglat() {
	}

	/** minimal constructor */
	public CommConfigLnglat(String id) {
		this.id = id;
	}

	/** full constructor */
	public CommConfigLnglat(String id, String itemCode, String itemName,
			String lat, String lng, String comments) {
		this.id = id;
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.lat = lat;
		this.lng = lng;
		this.comments = comments;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItemCode() {
		return this.itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getLat() {
		return this.lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return this.lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}