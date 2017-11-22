package com.tianjian.hsp.bean;

/**
 * HspDeptBaseinfoId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class HspDeptBaseinfoId implements java.io.Serializable {

	// Fields

	private String deptCode;
	private String hspConfigBaseinfoId;

	// Constructors

	/** default constructor */
	public HspDeptBaseinfoId() {
	}

	/** full constructor */
	public HspDeptBaseinfoId(String deptCode, String hspConfigBaseinfoId) {
		this.deptCode = deptCode;
		this.hspConfigBaseinfoId = hspConfigBaseinfoId;
	}

	// Property accessors

	public String getDeptCode() {
		return this.deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getHspConfigBaseinfoId() {
		return this.hspConfigBaseinfoId;
	}

	public void setHspConfigBaseinfoId(String hspConfigBaseinfoId) {
		this.hspConfigBaseinfoId = hspConfigBaseinfoId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof HspDeptBaseinfoId))
			return false;
		HspDeptBaseinfoId castOther = (HspDeptBaseinfoId) other;

		return ((this.getDeptCode() == castOther.getDeptCode()) || (this.getDeptCode() != null && castOther.getDeptCode() != null && this
				.getDeptCode().equals(castOther.getDeptCode())))
				&& ((this.getHspConfigBaseinfoId() == castOther.getHspConfigBaseinfoId()) || (this.getHspConfigBaseinfoId() != null
						&& castOther.getHspConfigBaseinfoId() != null && this.getHspConfigBaseinfoId().equals(
						castOther.getHspConfigBaseinfoId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getDeptCode() == null ? 0 : this.getDeptCode().hashCode());
		result = 37 * result + (getHspConfigBaseinfoId() == null ? 0 : this.getHspConfigBaseinfoId().hashCode());
		return result;
	}

}