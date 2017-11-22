package com.tianjian.security.struts.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

import com.tianjian.security.bean.SecurityConfigPublicClass;

/**
 * @author ch_f001
 */
public final class SecurityConfigPublicClassForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// ---------------QueryForm部分内容-------------------------------------------------------
	private String id;
	/** 类别代码 */
	private String classCode;
	/** 类别名称 */
	private String className;
	/** 输入码 */
	private String inputCode;
	/** 序号 */
	private String serialNo;
	private String levelFlag;
	private String parentId;
	
	private String[] idList;
	private String[] classCodeList;
	private String[] classNameList;
	private String[] inputCodeList;
	private String[] serialNoList;
	private String[] levelFlagList;
	private String[] classPartenNameL;
	
	// ---------实体类处理--------------------------------------------------
	private SecurityConfigPublicClass data;
	// -------------公共处理部分--------------------------------------------
	private String verbId = "";
	private String message = "";
	private String idHidden=""; // 供修改.删除使用
	private String classCodeHidden = ""; // 供修改时判断代码的唯一性
	private String orderNo = "";
	private String asc = "";
	// ------------字典处理----------------------------
	private List<?> securityConfigPublicClassList;// 模块类别字典

	public SecurityConfigPublicClassForm() {
		super();
		this.id = "";
		this.classCode = "";
		this.className = "";
		this.inputCode = "";
		this.serialNo = "";
		this.levelFlag = "";
		this.parentId  = "";
		this.orderNo = "";
		this.asc = "";
		this.idHidden = ""; // 供修改.删除使用
		this.message = "";
		this.data = new SecurityConfigPublicClass();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getInputCode() {
		return inputCode;
	}

	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public SecurityConfigPublicClass getData() {
		return data;
	}

	public void setData(SecurityConfigPublicClass data) {
		this.data = data;
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

	public List<?> getSecurityConfigPublicClassList() {
		return securityConfigPublicClassList;
	}

	public void setSecurityConfigPublicClassList(List<?> securityConfigPublicClassList) {
		this.securityConfigPublicClassList = securityConfigPublicClassList;
	}

	
	public String getClassCodeHidden() {
		return classCodeHidden;
	}

	
	public void setClassCodeHidden(String classCodeHidden) {
		this.classCodeHidden = classCodeHidden;
	}

	public String getLevelFlag() {
		return levelFlag;
	}

	public void setLevelFlag(String levelFlag) {
		this.levelFlag = levelFlag;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String[] getIdList() {
		return idList;
	}

	public void setIdList(String[] idList) {
		this.idList = idList;
	}

	public String[] getClassCodeList() {
		return classCodeList;
	}

	public void setClassCodeList(String[] classCodeList) {
		this.classCodeList = classCodeList;
	}

	public String[] getClassNameList() {
		return classNameList;
	}

	public void setClassNameList(String[] classNameList) {
		this.classNameList = classNameList;
	}

	public String[] getInputCodeList() {
		return inputCodeList;
	}

	public void setInputCodeList(String[] inputCodeList) {
		this.inputCodeList = inputCodeList;
	}

	public String[] getSerialNoList() {
		return serialNoList;
	}

	public void setSerialNoList(String[] serialNoList) {
		this.serialNoList = serialNoList;
	}

	public String[] getLevelFlagList() {
		return levelFlagList;
	}

	public void setLevelFlagList(String[] levelFlagList) {
		this.levelFlagList = levelFlagList;
	}

	public String[] getClassPartenNameL() {
		return classPartenNameL;
	}

	public void setClassPartenNameL(String[] classPartenNameL) {
		this.classPartenNameL = classPartenNameL;
	}
}
