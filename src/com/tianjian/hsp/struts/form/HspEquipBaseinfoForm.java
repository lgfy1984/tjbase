package com.tianjian.hsp.struts.form;

import java.util.List;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.tianjian.hsp.bean.ZTreeNode;

public class HspEquipBaseinfoForm extends ActionForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 123435652342121L;
	
	private String verbId="";
	private String message="";
	private String idHidden; //供修改.删除使用
	private String orderNo="";
	private String asc="";
	
	private String orgId;
	
	private List<?> dataList;
	
	//卫生设备明细
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
	private String productDate;
	private String purchaseDate;
	private String price;
	private String purchaseState;
	private String designLifetime;
	private String usage;
	private String source;
	private String application;
	private String comments;
	private String createUserId;
	private String createUserName;
	private String instrumentUseStartDate;
	private String instrumentUseEndDate;
	private String bdeptCode;
	
	private String hspName;
	private String deptName="";
	private String bdeptName="";
	private String sourceName ="";
	private String useageName ="";
	private String stateName ="";
	private String unitName ="";
	
	private String productDateUp="";
	private String productDateDown="";
	
	private String purchaseDateUp="";
	private String purchaseDateDown="";
	
	private String priceUp="";
	private String priceDown="";
	
	/***字典List***/
	private List<?> deptList;
	private List<?> stateList;
	private List<?> useageList;
	private List<?> unitList;
	
	//XJ新增
	private String staffId;
	private FormFile file;
	private int curCount;
	private int pageSize;
	
	public int getCurCount() {
		return curCount;
	}
	public void setCurCount(int curCount) {
		this.curCount = curCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getBdeptName() {
		return bdeptName;
	}
	public void setBdeptName(String bdeptName) {
		this.bdeptName = bdeptName;
	}
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	public String getUseageName() {
		return useageName;
	}
	public void setUseageName(String useageName) {
		this.useageName = useageName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getHspName() {
		return hspName;
	}
	public void setHspName(String hspName) {
		this.hspName = hspName;
	}
	public String getVerbId() {
		return verbId;
	}
	public void setVerbId(String verbId) {
		this.verbId = verbId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getIdHidden() {
		return idHidden;
	}
	public void setIdHidden(String idHidden) {
		this.idHidden = idHidden;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getAsc() {
		return asc;
	}
	public void setAsc(String asc) {
		this.asc = asc;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHspCode() {
		return hspCode;
	}
	public void setHspCode(String hspCode) {
		this.hspCode = hspCode;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getEquipName() {
		return equipName;
	}
	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}
	public String getEquipType() {
		return equipType;
	}
	public void setEquipType(String equipType) {
		this.equipType = equipType;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getEquipCode() {
		return equipCode;
	}
	public void setEquipCode(String equipCode) {
		this.equipCode = equipCode;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getSupplierTel() {
		return supplierTel;
	}
	public void setSupplierTel(String supplierTel) {
		this.supplierTel = supplierTel;
	}
	public String getProductor() {
		return productor;
	}
	public void setProductor(String productor) {
		this.productor = productor;
	}
	public String getProductorTel() {
		return productorTel;
	}
	public void setProductorTel(String productorTel) {
		this.productorTel = productorTel;
	}
	
	public String getPurchaseState() {
		return purchaseState;
	}
	public void setPurchaseState(String purchaseState) {
		this.purchaseState = purchaseState;
	}
	
	public String getUsage() {
		return usage;
	}
	public void setUsage(String usage) {
		this.usage = usage;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public String getProductDate() {
		return productDate;
	}
	public void setProductDate(String productDate) {
		this.productDate = productDate;
	}
	public String getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDesignLifetime() {
		return designLifetime;
	}
	public void setDesignLifetime(String designLifetime) {
		this.designLifetime = designLifetime;
	}
	public String getInstrumentUseStartDate() {
		return instrumentUseStartDate;
	}
	public void setInstrumentUseStartDate(String instrumentUseStartDate) {
		this.instrumentUseStartDate = instrumentUseStartDate;
	}
	public String getInstrumentUseEndDate() {
		return instrumentUseEndDate;
	}
	public void setInstrumentUseEndDate(String instrumentUseEndDate) {
		this.instrumentUseEndDate = instrumentUseEndDate;
	}
	public String getBdeptCode() {
		return bdeptCode;
	}
	public void setBdeptCode(String bdeptCode) {
		this.bdeptCode = bdeptCode;
	}
	public List<?> getDeptList() {
		return deptList;
	}
	public void setDeptList(List<?> deptList) {
		this.deptList = deptList;
	}
	public List<?> getStateList() {
		return stateList;
	}
	public void setStateList(List<?> stateList) {
		this.stateList = stateList;
	}
	public List<?> getUseageList() {
		return useageList;
	}
	public void setUseageList(List<?> useageList) {
		this.useageList = useageList;
	}
	public List<?> getUnitList() {
		return unitList;
	}
	public void setUnitList(List<?> unitList) {
		this.unitList = unitList;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public FormFile getFile() {
		return file;
	}
	public void setFile(FormFile file) {
		this.file = file;
	}
	public String getProductDateUp() {
		return productDateUp;
	}
	public void setProductDateUp(String productDateUp) {
		this.productDateUp = productDateUp;
	}
	public String getProductDateDown() {
		return productDateDown;
	}
	public void setProductDateDown(String productDateDown) {
		this.productDateDown = productDateDown;
	}
	public String getPurchaseDateUp() {
		return purchaseDateUp;
	}
	public void setPurchaseDateUp(String purchaseDateUp) {
		this.purchaseDateUp = purchaseDateUp;
	}
	public String getPurchaseDateDown() {
		return purchaseDateDown;
	}
	public void setPurchaseDateDown(String purchaseDateDown) {
		this.purchaseDateDown = purchaseDateDown;
	}
	public String getPriceUp() {
		return priceUp;
	}
	public void setPriceUp(String priceUp) {
		this.priceUp = priceUp;
	}
	public String getPriceDown() {
		return priceDown;
	}
	public void setPriceDown(String priceDown) {
		this.priceDown = priceDown;
	}
	public List<?> getDataList() {
		return dataList;
	}
	public void setDataList(List<?> dataList) {
		this.dataList = dataList;
	}
}
