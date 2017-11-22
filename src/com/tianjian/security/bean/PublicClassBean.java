package com.tianjian.security.bean;

import java.util.List;

public class PublicClassBean {
	private SecurityConfigPublicClass pc;
	private List<SecurityConfigPublicClass> childList;
	public SecurityConfigPublicClass getPc() {
		return pc;
	}
	public void setPc(SecurityConfigPublicClass pc) {
		this.pc = pc;
	}
	public List<SecurityConfigPublicClass> getChildList() {
		return childList;
	}
	public void setChildList(List<SecurityConfigPublicClass> childList) {
		this.childList = childList;
	}
	
}
