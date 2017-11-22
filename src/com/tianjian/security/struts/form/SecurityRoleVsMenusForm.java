package com.tianjian.security.struts.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

import com.tianjian.security.bean.MenuTreeNode;

/**
 * SecurityUserVsRolesForm
 */
public class SecurityRoleVsMenusForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4536254450187182764L;
	private String modId;
	private String modClassId;
	private String roleId;
	private String modClassName;

	private List<String> roleIdsList;
	private List<String> roleNamesList;
	private List<?> publicClassNodes;
	private List<?> publicNodes;
	private List<?> menuNodes;
	
	
	public List<String> getRoleIdsList() {
		return roleIdsList;
	}

	public void setRoleIdsList(List<String> roleIdsList) {
		this.roleIdsList = roleIdsList;
	}

	public List<String> getRoleNamesList() {
		return roleNamesList;
	}

	public void setRoleNamesList(List<String> roleNamesList) {
		this.roleNamesList = roleNamesList;
	}

	private String[] modIds;
	private String[] modNames;
	private String[] modClassIds;
	private String[] modClassNames;
	private String[] roleIds;
	private String[] roleNames;
	private String[] menuId;
	private String[] parentMenuId;
	private String[] menuDetail;
	private String[] endLevelFlag;
	private String[] selectedMenuId;
	private String verbId;
	
	/**2011-09-09添加*/
	private String[] parentModClassIds;  //包括模块的模块类别和模块类别的父类别
	private String[] parentModClassNames; //同上
	private	String[] checkAbles;//表示页面上的模块树是否出现单选框,此处所有的模块为true,模块类别为false.


	public SecurityRoleVsMenusForm() {
		super();
		verbId = "";
		modId = "";
		roleId = "";
	}
	
	/**
	 * @return Returns the endLevelFlag.
	 */
	public String[] getEndLevelFlag() {
		return endLevelFlag;
	}

	/**
	 * @param endLevelFlag The endLevelFlag to set.
	 */
	public void setEndLevelFlag(String[] endLevelFlag) {
		this.endLevelFlag = endLevelFlag;
	}

	/**
	 * @return Returns the menuDetail.
	 */
	public String[] getMenuDetail() {
		return menuDetail;
	}

	/**
	 * @param menuDetail The menuDetail to set.
	 */
	public void setMenuDetail(String[] menuDetail) {
		this.menuDetail = menuDetail;
	}

	/**
	 * @return Returns the menuId.
	 */
	public String[] getMenuId() {
		return menuId;
	}

	/**
	 * @param menuId The menuId to set.
	 */
	public void setMenuId(String[] menuId) {
		this.menuId = menuId;
	}

	/**
	 * @return Returns the modId.
	 */
	public String getModId() {
		return modId;
	}

	/**
	 * @param modId The modId to set.
	 */
	public void setModId(String modId) {
		this.modId = modId;
	}

	/**
	 * @return Returns the modIds.
	 */
	public String[] getModIds() {
		return modIds;
	}

	/**
	 * @param modIds The modIds to set.
	 */
	public void setModIds(String[] modIds) {
		this.modIds = modIds;
	}

	/**
	 * @return Returns the modNames.
	 */
	public String[] getModNames() {
		return modNames;
	}

	/**
	 * @param modNames The modNames to set.
	 */
	public void setModNames(String[] modNames) {
		this.modNames = modNames;
	}

	/**
	 * @return Returns the parentMenuId.
	 */
	public String[] getParentMenuId() {
		return parentMenuId;
	}

	/**
	 * @param parentMenuId The parentMenuId to set.
	 */
	public void setParentMenuId(String[] parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	/**
	 * @return Returns the roleId.
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId The roleId to set.
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return Returns the roleIds.
	 */
	public String[] getRoleIds() {
		return roleIds;
	}

	/**
	 * @param roleIds The roleIds to set.
	 */
	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}

	/**
	 * @return Returns the roleNames.
	 */
	public String[] getRoleNames() {
		return roleNames;
	}

	/**
	 * @param roleNames The roleNames to set.
	 */
	public void setRoleNames(String[] roleNames) {
		this.roleNames = roleNames;
	}

	/**
	 * @return Returns the selectedMenuId.
	 */
	public String[] getSelectedMenuId() {
		return selectedMenuId;
	}

	/**
	 * @param selectedMenuId The selectedMenuId to set.
	 */
	public void setSelectedMenuId(String[] selectedMenuId) {
		this.selectedMenuId = selectedMenuId;
	}

	/**
	 * @return Returns the verbId.
	 */
	public String getVerbId() {
		return verbId;
	}

	/**
	 * @param verbId The verbId to set.
	 */
	public void setVerbId(String verbId) {
		this.verbId = verbId;
	}

	
	public String getModClassId() {
		return modClassId;
	}

	
	public void setModClassId(String modClassId) {
		this.modClassId = modClassId;
	}

	
	public String[] getModClassIds() {
		return modClassIds;
	}

	
	public void setModClassIds(String[] modClassIds) {
		this.modClassIds = modClassIds;
	}

	
	public String[] getModClassNames() {
		return modClassNames;
	}

	
	public void setModClassNames(String[] modClassNames) {
		this.modClassNames = modClassNames;
	}

	
	public String[] getParentModClassIds() {
		return parentModClassIds;
	}

	public void setParentModClassIds(String[] parentModClassIds) {
		this.parentModClassIds = parentModClassIds;
	}

	public String[] getParentModClassNames() {
		return parentModClassNames;
	}

	public void setParentModClassNames(String[] parentModClassNames) {
		this.parentModClassNames = parentModClassNames;
	}

	public String[] getCheckAbles() {
		return checkAbles;
	}

	public void setCheckAbles(String[] checkAbles) {
		this.checkAbles = checkAbles;
	}
	
	public String getModClassName() {
		return modClassName;
	}

	public void setModClassName(String modClassName) {
		this.modClassName = modClassName;
	}

	public List<?> getPublicClassNodes() {
		return publicClassNodes;
	}

	public void setPublicClassNodes(List<?> publicClassNodes) {
		this.publicClassNodes = publicClassNodes;
	}

	public List<?> getPublicNodes() {
		return publicNodes;
	}

	public void setPublicNodes(List<?> publicNodes) {
		this.publicNodes = publicNodes;
	}

	public List<?> getMenuNodes() {
		return menuNodes;
	}

	public void setMenuNodes(List<?> menuNodes) {
		this.menuNodes = menuNodes;
	}
}

