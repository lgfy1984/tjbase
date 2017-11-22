package com.tianjian.hsp.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * HspConfigBaseinfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class HspConfigBaseinfoLocalBase implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -930918981107689930L;
	

	private String id;
	private String itemCode;
	private String itemName;
	private String parentItemCode;
	private String commConfigLocationId1;
	private String commConfigLocationId2;
	private String commConfigLocationId3;
	private Long seqNo;
	private String inputCode;
	private String levelDesc;
	private String parentItemName;
	private String commConfigLocationName1;
	private String commConfigLocationName2;
	private String commConfigLocationName3;
	private String hspConfigBaseinfoId1;
	private String hspConfigBaseinfoId2;
	private String hspConfigBaseinfoId3;
	private String hspConfigBaseinfoId4;
	private String hspConfigBaseinfoId5;
	private Long hspType;
	//租户id
	private String tenantId;
	private Set hspStaffBaseinfos = new HashSet(0);

	// Constructors

	public String getHspConfigBaseinfoId1() {
		return hspConfigBaseinfoId1;
	}

	public void setHspConfigBaseinfoId1(String hspConfigBaseinfoId1) {
		this.hspConfigBaseinfoId1 = hspConfigBaseinfoId1;
	}

	public String getHspConfigBaseinfoId2() {
		return hspConfigBaseinfoId2;
	}

	public void setHspConfigBaseinfoId2(String hspConfigBaseinfoId2) {
		this.hspConfigBaseinfoId2 = hspConfigBaseinfoId2;
	}

	public String getHspConfigBaseinfoId3() {
		return hspConfigBaseinfoId3;
	}

	public void setHspConfigBaseinfoId3(String hspConfigBaseinfoId3) {
		this.hspConfigBaseinfoId3 = hspConfigBaseinfoId3;
	}

	public String getHspConfigBaseinfoId4() {
		return hspConfigBaseinfoId4;
	}

	public void setHspConfigBaseinfoId4(String hspConfigBaseinfoId4) {
		this.hspConfigBaseinfoId4 = hspConfigBaseinfoId4;
	}

	public String getHspConfigBaseinfoId5() {
		return hspConfigBaseinfoId5;
	}

	public void setHspConfigBaseinfoId5(String hspConfigBaseinfoId5) {
		this.hspConfigBaseinfoId5 = hspConfigBaseinfoId5;
	}

	/** default constructor */
	public HspConfigBaseinfoLocalBase() {
	}

	/** full constructor */
	public HspConfigBaseinfoLocalBase(String itemCode, String itemName,
			String parentItemCode, 
			String commConfigLocationId1, String commConfigLocationId2,
			String commConfigLocationId3, Long seqNo, String inputCode, String levelDesc,
			String parentItemName,
			String commConfigLocationName1,
			String commConfigLocationName2, String commConfigLocationName3,
			String hspConfigBaseinfoId1, String hspConfigBaseinfoId2,
			String hspConfigBaseinfoId3, String hspConfigBaseinfoId4,
			String hspConfigBaseinfoId5,
			Long hspType,Set hspStaffBaseinfos) {
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.parentItemCode = parentItemCode;
		this.commConfigLocationId1 = commConfigLocationId1;
		this.commConfigLocationId2 = commConfigLocationId2;
		this.commConfigLocationId3 = commConfigLocationId3;
		this.seqNo = seqNo;
		this.inputCode = inputCode;
		this.levelDesc = levelDesc;
		this.parentItemName = parentItemName;
		this.commConfigLocationName1 = commConfigLocationName1;
		this.commConfigLocationName2 = commConfigLocationName2;
		this.commConfigLocationName3 = commConfigLocationName3;
		this.hspConfigBaseinfoId1 = hspConfigBaseinfoId1;
		this.hspConfigBaseinfoId2 = hspConfigBaseinfoId2;
		this.hspConfigBaseinfoId3 = hspConfigBaseinfoId3;
		this.hspConfigBaseinfoId4 = hspConfigBaseinfoId4;
		this.hspConfigBaseinfoId5 = hspConfigBaseinfoId5;
		this.hspType = hspType;
		this.hspStaffBaseinfos = hspStaffBaseinfos;
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

	public String getParentItemCode() {
		return this.parentItemCode;
	}

	public void setParentItemCode(String parentItemCode) {
		this.parentItemCode = parentItemCode;
	}

	public String getCommConfigLocationId1() {
		return this.commConfigLocationId1;
	}

	public void setCommConfigLocationId1(String commConfigLocationId1) {
		this.commConfigLocationId1 = commConfigLocationId1;
	}

	public String getCommConfigLocationId2() {
		return this.commConfigLocationId2;
	}

	public void setCommConfigLocationId2(String commConfigLocationId2) {
		this.commConfigLocationId2 = commConfigLocationId2;
	}

	public String getCommConfigLocationId3() {
		return this.commConfigLocationId3;
	}

	public void setCommConfigLocationId3(String commConfigLocationId3) {
		this.commConfigLocationId3 = commConfigLocationId3;
	}

	public Long getSeqNo() {
		return this.seqNo;
	}

	public void setSeqNo(Long seqNo) {
		this.seqNo = seqNo;
	}

	public String getInputCode() {
		return this.inputCode;
	}

	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}

	public String getLevelDesc() {
		return this.levelDesc;
	}

	public void setLevelDesc(String levelDesc) {
		this.levelDesc = levelDesc;
	}

	public String getParentItemName() {
		return this.parentItemName;
	}

	public void setParentItemName(String parentItemName) {
		this.parentItemName = parentItemName;
	}

	public String getCommConfigLocationName1() {
		return this.commConfigLocationName1;
	}

	public void setCommConfigLocationName1(String commConfigLocationName1) {
		this.commConfigLocationName1 = commConfigLocationName1;
	}

	public String getCommConfigLocationName2() {
		return this.commConfigLocationName2;
	}

	public void setCommConfigLocationName2(String commConfigLocationName2) {
		this.commConfigLocationName2 = commConfigLocationName2;
	}

	public String getCommConfigLocationName3() {
		return this.commConfigLocationName3;
	}

	public void setCommConfigLocationName3(String commConfigLocationName3) {
		this.commConfigLocationName3 = commConfigLocationName3;
	}

	public Set getHspStaffBaseinfos() {
		return this.hspStaffBaseinfos;
	}

	public void setHspStaffBaseinfos(Set hspStaffBaseinfos) {
		this.hspStaffBaseinfos = hspStaffBaseinfos;
	}
	
	public Long getHspType() {
		return hspType;
	}

	public void setHspType(Long hspType) {
		this.hspType = hspType;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}


}