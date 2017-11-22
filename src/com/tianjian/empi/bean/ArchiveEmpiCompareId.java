package com.tianjian.empi.bean;

/**
 * ArchiveEmpiCompareId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ArchiveEmpiCompareId implements java.io.Serializable {

	// Fields

	private String oldUserId;
	private String newUserId;

	// Constructors

	/** default constructor */
	public ArchiveEmpiCompareId() {
	}

	/** full constructor */
	public ArchiveEmpiCompareId(String oldUserId, String newUserId) {
		this.oldUserId = oldUserId;
		this.newUserId = newUserId;
	}

	// Property accessors

	public String getOldUserId() {
		return this.oldUserId;
	}

	public void setOldUserId(String oldUserId) {
		this.oldUserId = oldUserId;
	}

	public String getNewUserId() {
		return this.newUserId;
	}

	public void setNewUserId(String newUserId) {
		this.newUserId = newUserId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ArchiveEmpiCompareId))
			return false;
		ArchiveEmpiCompareId castOther = (ArchiveEmpiCompareId) other;

		return ((this.getOldUserId() == castOther.getOldUserId()) || (this
				.getOldUserId() != null
				&& castOther.getOldUserId() != null && this.getOldUserId()
				.equals(castOther.getOldUserId())))
				&& ((this.getNewUserId() == castOther.getNewUserId()) || (this
						.getNewUserId() != null
						&& castOther.getNewUserId() != null && this
						.getNewUserId().equals(castOther.getNewUserId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getOldUserId() == null ? 0 : this.getOldUserId().hashCode());
		result = 37 * result
				+ (getNewUserId() == null ? 0 : this.getNewUserId().hashCode());
		return result;
	}

}