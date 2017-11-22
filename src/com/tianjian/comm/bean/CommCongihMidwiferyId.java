package com.tianjian.comm.bean;

/**
 * CommCongihMidwiferyId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class CommCongihMidwiferyId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6781889121083918983L;
	private String hspConfigBaseinfoId;
	private String hspClassCode;

	// Constructors

	/** default constructor */
	public CommCongihMidwiferyId() {
	}

	/** full constructor */
	public CommCongihMidwiferyId(String hspConfigBaseinfoId, String hspClassCode) {
		this.hspConfigBaseinfoId = hspConfigBaseinfoId;
		this.hspClassCode = hspClassCode;
	}

	// Property accessors

	public String getHspConfigBaseinfoId() {
		return this.hspConfigBaseinfoId;
	}

	public void setHspConfigBaseinfoId(String hspConfigBaseinfoId) {
		this.hspConfigBaseinfoId = hspConfigBaseinfoId;
	}

	public String getHspClassCode() {
		return this.hspClassCode;
	}

	public void setHspClassCode(String hspClassCode) {
		this.hspClassCode = hspClassCode;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CommCongihMidwiferyId))
			return false;
		CommCongihMidwiferyId castOther = (CommCongihMidwiferyId) other;

		return ((this.getHspConfigBaseinfoId() == castOther
				.getHspConfigBaseinfoId()) || (this.getHspConfigBaseinfoId() != null
				&& castOther.getHspConfigBaseinfoId() != null && this
				.getHspConfigBaseinfoId().equals(
						castOther.getHspConfigBaseinfoId())))
				&& ((this.getHspClassCode() == castOther.getHspClassCode()) || (this
						.getHspClassCode() != null
						&& castOther.getHspClassCode() != null && this
						.getHspClassCode().equals(castOther.getHspClassCode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getHspConfigBaseinfoId() == null ? 0 : this
						.getHspConfigBaseinfoId().hashCode());
		result = 37
				* result
				+ (getHspClassCode() == null ? 0 : this.getHspClassCode()
						.hashCode());
		return result;
	}

}