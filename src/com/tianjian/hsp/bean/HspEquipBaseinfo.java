package com.tianjian.hsp.bean;

import java.util.Date;

/**
 * HspEquipBaseinfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class HspEquipBaseinfo implements java.io.Serializable {

	// Fields

	private String id;
	private String hspCode;
	private String deptCode;
	private String equipName;
	private String equipType;
	private String unit;
	private String equipCode;
	private String supplier;
	private String supplierTel;
	private String productor;
	private String productorTel;
	private Date productDate;
	private Date purchaseDate;
	private Double price;
	private String purchaseState;
	private Long designLifetime;
	private String usage;
	private String source;
	private String application;
	private String comments;
	private String createUserId;
	private String createUserName;
	private Date instrumentUseStartDate;
	private Date instrumentUseEndDate;
	private String bdeptCode;
	private Date createDate;
	//租户id
	private String tenantId;
	// Constructors

	/** default constructor */
	public HspEquipBaseinfo() {
	}

	/** full constructor */
	public HspEquipBaseinfo(String hspCode, String deptCode, String equipName, String equipType, String unit, String equipCode,
			String supplier, String supplierTel, String productor, String productorTel, Date productDate, Date purchaseDate,
			Double price, String purchaseState, Long designLifetime, String usage, String source, String application,
			String comments, String createUserId, String createUserName, Date instrumentUseStartDate, Date instrumentUseEndDate,
			String bdeptCode, Date createDate) {
		this.hspCode = hspCode;
		this.deptCode = deptCode;
		this.equipName = equipName;
		this.equipType = equipType;
		this.unit = unit;
		this.equipCode = equipCode;
		this.supplier = supplier;
		this.supplierTel = supplierTel;
		this.productor = productor;
		this.productorTel = productorTel;
		this.productDate = productDate;
		this.purchaseDate = purchaseDate;
		this.price = price;
		this.purchaseState = purchaseState;
		this.designLifetime = designLifetime;
		this.usage = usage;
		this.source = source;
		this.application = application;
		this.comments = comments;
		this.createUserId = createUserId;
		this.createUserName = createUserName;
		this.instrumentUseStartDate = instrumentUseStartDate;
		this.instrumentUseEndDate = instrumentUseEndDate;
		this.bdeptCode = bdeptCode;
		this.createDate = createDate;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHspCode() {
		return this.hspCode;
	}

	public void setHspCode(String hspCode) {
		this.hspCode = hspCode;
	}

	public String getDeptCode() {
		return this.deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getEquipName() {
		return this.equipName;
	}

	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}

	public String getEquipType() {
		return this.equipType;
	}

	public void setEquipType(String equipType) {
		this.equipType = equipType;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getEquipCode() {
		return this.equipCode;
	}

	public void setEquipCode(String equipCode) {
		this.equipCode = equipCode;
	}

	public String getSupplier() {
		return this.supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getSupplierTel() {
		return this.supplierTel;
	}

	public void setSupplierTel(String supplierTel) {
		this.supplierTel = supplierTel;
	}

	public String getProductor() {
		return this.productor;
	}

	public void setProductor(String productor) {
		this.productor = productor;
	}

	public String getProductorTel() {
		return this.productorTel;
	}

	public void setProductorTel(String productorTel) {
		this.productorTel = productorTel;
	}

	public Date getProductDate() {
		return this.productDate;
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}

	public Date getPurchaseDate() {
		return this.purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getPurchaseState() {
		return this.purchaseState;
	}

	public void setPurchaseState(String purchaseState) {
		this.purchaseState = purchaseState;
	}

	public Long getDesignLifetime() {
		return this.designLifetime;
	}

	public void setDesignLifetime(Long designLifetime) {
		this.designLifetime = designLifetime;
	}

	public String getUsage() {
		return this.usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getApplication() {
		return this.application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserName() {
		return this.createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public Date getInstrumentUseStartDate() {
		return this.instrumentUseStartDate;
	}

	public void setInstrumentUseStartDate(Date instrumentUseStartDate) {
		this.instrumentUseStartDate = instrumentUseStartDate;
	}

	public Date getInstrumentUseEndDate() {
		return this.instrumentUseEndDate;
	}

	public void setInstrumentUseEndDate(Date instrumentUseEndDate) {
		this.instrumentUseEndDate = instrumentUseEndDate;
	}

	public String getBdeptCode() {
		return this.bdeptCode;
	}

	public void setBdeptCode(String bdeptCode) {
		this.bdeptCode = bdeptCode;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

}