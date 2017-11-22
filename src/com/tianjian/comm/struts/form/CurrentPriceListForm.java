package com.tianjian.comm.struts.form;
import org.apache.struts.action.ActionForm;

public class CurrentPriceListForm extends ActionForm{
	private static final long serialVersionUID = -6215668043334125733L;
	private String id;
	private String drugDictId="";
	private String eanCode="";
	private String drugCode="";
	private String drugName="";
	private String minSpec="";
	private String minUnits="";
	private String drugSpec="";
	private String units="";
	private String amountPerPackage="";
	private String firmId="";
	private String tradePrice="";
	private String retailPrice="";
	private String startDate="";
	private String stopDate="";
	private String hlimitPrice="";
	private String priceClass="";
	private String passNo="";
	private String gmp="";
	private String memos="";
	private Long[] frimidlist;
	private String[] frimnamelist;
	private String Message="";
	private String Asc="";
	
	private String VerbId="";
	private String OrderNo="";
	private String IdHidden="";
	private String[] idlist;
	private String[]  eanCodelist ;
	private String[]  itemnamelist ;
	private String[] drugSpeclist ;
	private String[] unitslist ;
	private String[] retailPricelist ;
	private String[] startDatelist ;
	private String[] stopDatelist;
	
	public String getVerbId() {
		return VerbId;
	}
	public void setVerbId(String verbId) {
		VerbId = verbId;
	}
	public String getOrderNo() {
		return OrderNo;
	}
	public void setOrderNo(String orderNo) {
		OrderNo = orderNo;
	}
	public String getIdHidden() {
		return IdHidden;
	}
	public void setIdHidden(String idHidden) {
		IdHidden = idHidden;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String[] getEanCodelist() {
		return eanCodelist;
	}
	public void setEanCodelist(String[] eanCodelist) {
		this.eanCodelist = eanCodelist;
	}
	public String[] getItemnamelist() {
		return itemnamelist;
	}
	public void setItemnamelist(String[] itemnamelist) {
		this.itemnamelist = itemnamelist;
	}
	public String[] getDrugSpeclist() {
		return drugSpeclist;
	}
	public void setDrugSpeclist(String[] drugSpeclist) {
		this.drugSpeclist = drugSpeclist;
	}
	public String[] getUnitslist() {
		return unitslist;
	}
	public void setUnitslist(String[] unitslist) {
		this.unitslist = unitslist;
	}
	public String[] getRetailPricelist() {
		return retailPricelist;
	}
	public void setRetailPricelist(String[] retailPricelist) {
		this.retailPricelist = retailPricelist;
	}
	public String[] getStartDatelist() {
		return startDatelist;
	}
	public void setStartDatelist(String[] startDatelist) {
		this.startDatelist = startDatelist;
	}
	public String[] getStopDatelist() {
		return stopDatelist;
	}
	public void setStopDatelist(String[] stopDatelist) {
		this.stopDatelist = stopDatelist;
	}
	public String getAsc() {
		return Asc;
	}
	public void setAsc(String asc) {
		Asc = asc;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public Long[] getFrimidlist() {
		return frimidlist;
	}
	public void setFrimidlist(Long[] frimidlist) {
		this.frimidlist = frimidlist;
	}
	public String[] getFrimnamelist() {
		return frimnamelist;
	}
	public void setFrimnamelist(String[] frimnamelist) {
		this.frimnamelist = frimnamelist;
	}
	
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public String getDrugDictId() {
		return drugDictId;
	}
	public void setDrugDictId(String drugDictId) {
		this.drugDictId = drugDictId;
	}
	public String getEanCode() {
		return eanCode;
	}
	public void setEanCode(String eanCode) {
		this.eanCode = eanCode;
	}
	public String getDrugCode() {
		return drugCode;
	}
	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}
	public String getMinSpec() {
		return minSpec;
	}
	public void setMinSpec(String minSpec) {
		this.minSpec = minSpec;
	}
	public String getMinUnits() {
		return minUnits;
	}
	public void setMinUnits(String minUnits) {
		this.minUnits = minUnits;
	}
	public String getDrugSpec() {
		return drugSpec;
	}
	public void setDrugSpec(String drugSpec) {
		this.drugSpec = drugSpec;
	}
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	
	public String getFirmId() {
		return firmId;
	}
	public void setFirmId(String firmId) {
		this.firmId = firmId;
	}
	
	
	public String getPriceClass() {
		return priceClass;
	}
	public void setPriceClass(String priceClass) {
		this.priceClass = priceClass;
	}
	public String getPassNo() {
		return passNo;
	}
	public void setPassNo(String passNo) {
		this.passNo = passNo;
	}

	public void setMemos(String memos) {
		this.memos = memos;
	}
	public String getAmountPerPackage() {
		return amountPerPackage;
	}
	public void setAmountPerPackage(String amountPerPackage) {
		this.amountPerPackage = amountPerPackage;
	}
	public String getTradePrice() {
		return tradePrice;
	}
	public void setTradePrice(String tradePrice) {
		this.tradePrice = tradePrice;
	}
	public String getRetailPrice() {
		return retailPrice;
	}
	public void setRetailPrice(String retailPrice) {
		this.retailPrice = retailPrice;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getStopDate() {
		return stopDate;
	}
	public void setStopDate(String stopDate) {
		this.stopDate = stopDate;
	}
	public String getHlimitPrice() {
		return hlimitPrice;
	}
	public void setHlimitPrice(String hlimitPrice) {
		this.hlimitPrice = hlimitPrice;
	}
	public String getGmp() {
		return gmp;
	}
	public void setGmp(String gmp) {
		this.gmp = gmp;
	}
	public String getMemos() {
		return memos;
	}
	public String[] getIdlist() {
		return idlist;
	}
	public void setIdlist(String[] idlist) {
		this.idlist = idlist;
	}
	
}
