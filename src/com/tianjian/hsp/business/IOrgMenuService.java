package com.tianjian.hsp.business;

import com.tianjian.hsp.struts.form.OrgMenuForm;

public interface IOrgMenuService {

	public void getOrgMenu(OrgMenuForm orgForm, String staffId);

	public String addDept(OrgMenuForm orgForm);

	public String modifyDept(OrgMenuForm orgForm);

	public String delDept(OrgMenuForm orgForm);

	public String findDept(OrgMenuForm orgForm,String orgId,String deptCode);

	public void createChildNode(OrgMenuForm orgForm, String treeId,String treeName, 
			String orgId,String itemCode,String deptCode,String rMenuFlag);

	public void init(OrgMenuForm orgForm);

	public void findStaffById(String hspStaffId, OrgMenuForm orgForm);

	public void initStaff(OrgMenuForm orgForm, String hspStaffId, String orgId,
			String deptCode,String menuId);

	public void updateStaff(OrgMenuForm orgForm, String hspStaffId);

	public int checkData(String empNo);

	public void saveStaff(OrgMenuForm orgForm);

	public void updateStaff(OrgMenuForm orgForm);

	public void delStaff(OrgMenuForm orgForm, String hspStaffId);

	public void moveStaff(OrgMenuForm orgForm, String hspStaffId, String orgId,
			String deptCode);

	public void query(OrgMenuForm orgForm);

	public void createChildNodeDept(OrgMenuForm orgForm, String treeId,
			String treeName, String orgId, String itemCode, String deptCode,
			String menuFlag);

}
