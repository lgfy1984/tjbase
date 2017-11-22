package com.tianjian.security.struts.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

import com.tianjian.security.bean.SecurityConfigMenus; 

/**
 * 
 * @author ch_f001
 *
 */

public final class SecurityConfigMenusForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3694002123962919890L;
	// ---------------QueryForm部分内容-------------------------------------------------------
	private String id;
	/**菜单ID*/
	private String menuCode;
	/**菜单内容*/
	private String menuDetail;
    /**序号*/
    private String serialNo;
    /**输入码*/
    private String inputCode;
    
    private String parentId;
	// Constructors
 // Constructors
	// ---------实体类处理--------------------------------------------------
	private SecurityConfigMenus data;
	private List<?> dataList;
	// -------------公共处理部分--------------------------------------------
	private String verbId  = "";
	private String message = "";
	private String idHidden=""; //供修改.删除使用
	private String menuCodeHidden=""; //供修改时判断代码的唯一性
	private String orderNo="";
	private String asc="";
	
	private String[] modIds;
	private String[] modNames;
	private String[] modTitle;
	private String[] parentModClassIds;
	private String[] checkAbles;
	
	
	private String modId;
	private String modClassId;
	private String[] menuIds;
	private String[] parentMenuIds;
	private String[] menuDetails;
	private String[] endLevelFlag;
	private List<?> securityConfigPublicList;
	private List<?> securityConfigMenusList;
	
	
	private String[] securityConfigPublicIds;
	private String[] securityConfigPublicNames;

	public SecurityConfigMenusForm() {
		super();
		this.id="";
		this.menuCode="";
		this.menuDetail="";
	    this.serialNo="";
		this.orderNo = "";
		this.asc="";
		this.message="";
		this.inputCode="";
		this.data = new SecurityConfigMenus();
		// TODO Auto-generated constructor stub
	}

	
	public String getId() {
		return id;
	}

	
	public void setId(String id) {
		this.id = id;
	}

	
	public String getMenuCode() {
		return menuCode;
	}

	
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	
	public String getMenuDetail() {
		return menuDetail;
	}

	
	public void setMenuDetail(String menuDetail) {
		this.menuDetail = menuDetail;
	}

	
	public String getSerialNo() {
		return serialNo;
	}

	
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	
	public String getInputCode() {
		return inputCode;
	}

	
	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}

	
	public SecurityConfigMenus getData() {
		return data;
	}

	
	public void setData(SecurityConfigMenus data) {
		this.data = data;
	}

	
	public List<?> getDataList() {
		return dataList;
	}

	
	public void setDataList(List<?> dataList) {
		this.dataList = dataList;
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

	
	public List<?> getSecurityConfigPublicList() {
		return securityConfigPublicList;
	}

	
	public void setSecurityConfigPublicList(List<?> securityConfigPublicList) {
		this.securityConfigPublicList = securityConfigPublicList;
	}

	
	public List<?> getSecurityConfigMenusList() {
		return securityConfigMenusList;
	}

	
	public void setSecurityConfigMenusList(List<?> securityConfigMenusList) {
		this.securityConfigMenusList = securityConfigMenusList;
	}


	
	public String getMenuCodeHidden() {
		return menuCodeHidden;
	}


	
	public void setMenuCodeHidden(String menuCodeHidden) {
		this.menuCodeHidden = menuCodeHidden;
	}

	public String[] getModIds() {
		return modIds;
	}


	public void setModIds(String[] modIds) {
		this.modIds = modIds;
	}


	public String[] getModNames() {
		return modNames;
	}


	public void setModNames(String[] modNames) {
		this.modNames = modNames;
	}


	public String[] getModTitle() {
		return modTitle;
	}


	public void setModTitle(String[] modTitle) {
		this.modTitle = modTitle;
	}


	public String[] getParentModClassIds() {
		return parentModClassIds;
	}


	public void setParentModClassIds(String[] parentModClassIds) {
		this.parentModClassIds = parentModClassIds;
	}


	public String[] getCheckAbles() {
		return checkAbles;
	}


	public void setCheckAbles(String[] checkAbles) {
		this.checkAbles = checkAbles;
	}


	public String getModId() {
		return modId;
	}


	public void setModId(String modId) {
		this.modId = modId;
	}


	public String getModClassId() {
		return modClassId;
	}


	public void setModClassId(String modClassId) {
		this.modClassId = modClassId;
	}


	public String[] getMenuIds() {
		return menuIds;
	}


	public void setMenuIds(String[] menuIds) {
		this.menuIds = menuIds;
	}


	public String[] getParentMenuIds() {
		return parentMenuIds;
	}


	public void setParentMenuIds(String[] parentMenuIds) {
		this.parentMenuIds = parentMenuIds;
	}


	public String[] getMenuDetails() {
		return menuDetails;
	}


	public void setMenuDetails(String[] menuDetails) {
		this.menuDetails = menuDetails;
	}


	public String[] getEndLevelFlag() {
		return endLevelFlag;
	}


	public void setEndLevelFlag(String[] endLevelFlag) {
		this.endLevelFlag = endLevelFlag;
	}


	public String getParentId() {
		return parentId;
	}


	public void setParentId(String parentId) {
		this.parentId = parentId;
	}


	public String[] getSecurityConfigPublicIds() {
		return securityConfigPublicIds;
	}


	public void setSecurityConfigPublicIds(String[] securityConfigPublicIds) {
		this.securityConfigPublicIds = securityConfigPublicIds;
	}


	public String[] getSecurityConfigPublicNames() {
		return securityConfigPublicNames;
	}


	public void setSecurityConfigPublicNames(String[] securityConfigPublicNames) {
		this.securityConfigPublicNames = securityConfigPublicNames;
	}



	
		
}
