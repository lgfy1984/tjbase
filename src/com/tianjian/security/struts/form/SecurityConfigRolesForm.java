package com.tianjian.security.struts.form;

import java.util.List;

import org.apache.struts.action.ActionForm;
 
import com.tianjian.security.bean.SecurityConfigRoles;


/**
 * 
 * @author ch_f001
 *
 */

public final class SecurityConfigRolesForm extends ActionForm {


	/**
	 * 
	 */
	private static final long serialVersionUID = 8437317518534380700L;
	/**
	 * 
	 */
	// ---------------QueryForm部分内容---------------------------------------
	/**角色ID*/
	private String id;
	/**角色代码*/
	private String roleCode;
	/**角色描述*/
	private String roleDetail;
	/**输入码*/
	private String inputCode;
	/**备注*/
	private String comments;
	/**序号*/
	private String serialNo;
	// Constructors
	// ---------实体类处理--------------------------------------------------
	private SecurityConfigRoles data;
	private List<?> dataList;
	// -------------公共处理部分--------------------------------------------
	private String verbId="";
	private String message="";
	private String idHidden=""; //供修改.删除使用
	private String roleCodeHidden=""; //供修改时检查roleCode唯一性
	private String orderNo="";
	private String asc="";
	

	public SecurityConfigRolesForm() {
		super();
		this.id = "";
		this.roleCode = "";
		this.roleDetail = "";
		this.inputCode = "";
		this.comments = "";
		this.serialNo = "";
		this.orderNo = "";
		this.asc="";
		this.message="";
		this.data = new SecurityConfigRoles();
		// TODO Auto-generated constructor stub
	}


	
	public String getId() {
		return id;
	}


	
	public void setId(String id) {
		this.id = id;
	}


	
	public String getRoleCode() {
		return roleCode;
	}


	
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}


	
	public String getRoleDetail() {
		return roleDetail;
	}


	
	public void setRoleDetail(String roleDetail) {
		this.roleDetail = roleDetail;
	}


	
	public String getInputCode() {
		return inputCode;
	}


	
	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}


	
	public String getComments() {
		return comments;
	}


	
	public void setComments(String comments) {
		this.comments = comments;
	}


	
	public String getSerialNo() {
		return serialNo;
	}


	
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}


	
	public SecurityConfigRoles getData() {
		return data;
	}


	
	public void setData(SecurityConfigRoles data) {
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



	
	public String getRoleCodeHidden() {
		return roleCodeHidden;
	}



	
	public void setRoleCodeHidden(String roleCodeHidden) {
		this.roleCodeHidden = roleCodeHidden;
	}

	 
}
