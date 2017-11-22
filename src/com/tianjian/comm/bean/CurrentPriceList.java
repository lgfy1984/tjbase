package com.tianjian.comm.bean;

import java.util.Date;

/**
 * DrugPriceList entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class CurrentPriceList implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6706450100505172823L;
	private String id;
	private String drugDictId;
	private String drugName;
	private String eanCode;
	private String drugCode;
	private String minSpec;
	private String minUnits;
	private String drugSpec;
	private String units;
	private Long amountPerPackage;
	private String firmId;
	private Double tradePrice;
	private Double retailPrice;
	private Date startDate;
	private Date stopDate;
	private Double hlimitPrice;
	private String priceClass;
	private String passNo;
	private Long gmp;
	private String memos;

	// Constructors

	/** default constructor */
	public CurrentPriceList() {
	}

	/** minimal constructor */
	public CurrentPriceList(String id) {
		this.id = id;
	}

	/** full constructor */
	public CurrentPriceList(String id, String drugDictId, String eanCode,
			String drugCode,String drugName, String minSpec, String minUnits, String drugSpec,
			String units, Long amountPerPackage, String firmId,
			Double tradePrice, Double retailPrice, Date startDate,
			Date stopDate, Double hlimitPrice, String priceClass,
			String passNo, Long gmp, String memos) {
		this.id = id;
		this.drugDictId = drugDictId;
		this.eanCode = eanCode;
		this.drugCode = drugCode;
		this.drugName = drugName;
		this.minSpec = minSpec;
		this.minUnits = minUnits;
		this.drugSpec = drugSpec;
		this.units = units;
		this.amountPerPackage = amountPerPackage;
		this.firmId = firmId;
		this.tradePrice = tradePrice;
		this.retailPrice = retailPrice;
		this.startDate = startDate;
		this.stopDate = stopDate;
		this.hlimitPrice = hlimitPrice;
		this.priceClass = priceClass;
		this.passNo = passNo;
		this.gmp = gmp;
		this.memos = memos;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDrugDictId() {
		return this.drugDictId;
	}

	public void setDrugDictId(String drugDictId) {
		this.drugDictId = drugDictId;
	}

	public String getEanCode() {
		return this.eanCode;
	}

	public void setEanCode(String eanCode) {
		this.eanCode = eanCode;
	}

	public String getDrugCode() {
		return this.drugCode;
	}

	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}

	public String getMinSpec() {
		return this.minSpec;
	}

	public void setMinSpec(String minSpec) {
		this.minSpec = minSpec;
	}

	public String getMinUnits() {
		return this.minUnits;
	}

	public void setMinUnits(String minUnits) {
		this.minUnits = minUnits;
	}

	public String getDrugSpec() {
		return this.drugSpec;
	}

	public void setDrugSpec(String drugSpec) {
		this.drugSpec = drugSpec;
	}

	public String getUnits() {
		return this.units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public Long getAmountPerPackage() {
		return this.amountPerPackage;
	}

	public void setAmountPerPackage(Long amountPerPackage) {
		this.amountPerPackage = amountPerPackage;
	}

	public String getFirmId() {
		return this.firmId;
	}

	public void setFirmId(String firmId) {
		this.firmId = firmId;
	}

	public Double getTradePrice() {
		return this.tradePrice;
	}

	public void setTradePrice(Double tradePrice) {
		this.tradePrice = tradePrice;
	}

	public Double getRetailPrice() {
		return this.retailPrice;
	}

	public void setRetailPrice(Double retailPrice) {
		this.retailPrice = retailPrice;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getStopDate() {
		return this.stopDate;
	}

	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}

	public Double getHlimitPrice() {
		return this.hlimitPrice;
	}

	public void setHlimitPrice(Double hlimitPrice) {
		this.hlimitPrice = hlimitPrice;
	}

	public String getPriceClass() {
		return this.priceClass;
	}

	public void setPriceClass(String priceClass) {
		this.priceClass = priceClass;
	}

	public String getPassNo() {
		return this.passNo;
	}

	public void setPassNo(String passNo) {
		this.passNo = passNo;
	}

	public Long getGmp() {
		return this.gmp;
	}

	public void setGmp(Long gmp) {
		this.gmp = gmp;
	}

	public String getMemos() {
		return this.memos;
	}

	public void setMemos(String memos) {
		this.memos = memos;
	}

	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

}