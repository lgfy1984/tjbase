package com.tianjian.hsp.business;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tianjian.hsp.bean.HRTreeNode;
import com.tianjian.hsp.struts.form.HealthRegisterTreeForm;

public interface IHealthRegisterTreeService {

	public List<HRTreeNode> getHspChildNodes(HealthRegisterTreeForm hrtForm);
	
	public List<HRTreeNode> getDeptListChildNodes(HealthRegisterTreeForm hrtForm);
	
	public List<HRTreeNode> getDeptChildNodes(HealthRegisterTreeForm hrtForm);
	
	public List<HRTreeNode> getNulldeptChildNodes(HealthRegisterTreeForm hrtForm);

	public List<HRTreeNode> getRootChildNodes(HealthRegisterTreeForm hrtForm, String staffHspId);

	public void getDeptDetail(HealthRegisterTreeForm hrtForm);

	public void deleteHsp(HealthRegisterTreeForm hrtForm);

	public void deleteDept(HealthRegisterTreeForm hrtForm);

	public void deleteStaff(HealthRegisterTreeForm hrtForm);

	public void deleteEquip(HealthRegisterTreeForm hrtForm);

	public String createResponseJson(boolean success, String msg);

	public void addDeptInit(HealthRegisterTreeForm hrtForm);

	public void addDept(HealthRegisterTreeForm hrtForm);

	public void updateDeptInit(HealthRegisterTreeForm hrtForm);

	public void updateDept(HealthRegisterTreeForm hrtForm);

	public String checkDeptCode(String hspId, String deptCode);

	public String getQueryData(HttpServletRequest request);

	public String getDeptXml(HttpServletRequest request);

	public String getDeptXmlByHspCode(HttpServletRequest request);

}
