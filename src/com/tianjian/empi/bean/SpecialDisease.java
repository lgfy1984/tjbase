package com.tianjian.empi.bean;

public class SpecialDisease implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String countInfo;  //存放数量
	private String diseaseInfo; //存放特殊疾病名称
	private String ccltName;  //存放所在乡镇名
	
	
	private String lat;
	private String lng;
	
	public String getCountInfo() {
		return countInfo;
	}
	public void setCountInfo(String countInfo) {
		this.countInfo = countInfo;
	}
	public String getDiseaseInfo() {
		return diseaseInfo;
	}
	public void setDiseaseInfo(String diseaseInfo) {
		this.diseaseInfo = diseaseInfo;
	}
	public String getCcltName() {
		return ccltName;
	}
	public void setCcltName(String ccltName) {
		this.ccltName = ccltName;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	
}
