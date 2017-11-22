package com.tianjian.comm.bean;

/**
 * CommCongihMidwifery entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class CommCongihMidwifery implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6771663091743150691L;
	private CommCongihMidwiferyId id;
	private Long seqNo;

	// Constructors

	/** default constructor */
	public CommCongihMidwifery() {
	}

	/** minimal constructor */
	public CommCongihMidwifery(CommCongihMidwiferyId id) {
		this.id = id;
	}

	/** full constructor */
	public CommCongihMidwifery(CommCongihMidwiferyId id, Long seqNo) {
		this.id = id;
		this.seqNo = seqNo;
	}

	// Property accessors

	public CommCongihMidwiferyId getId() {
		return this.id;
	}

	public void setId(CommCongihMidwiferyId id) {
		this.id = id;
	}

	public Long getSeqNo() {
		return this.seqNo;
	}

	public void setSeqNo(Long seqNo) {
		this.seqNo = seqNo;
	}

}