package com.tianjian.security.business.service;


import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import com.tianjian.security.bean.SecurityConfigRoles;
import com.tianjian.security.business.ISecurityConfigRolesService;
import com.tianjian.security.dao.ISecurityConfigRolesDAO;
import com.tianjian.security.struts.form.SecurityConfigRolesForm;
import com.tianjian.util.comm.TJInit;

public class SecurityConfigRolesServiceImpl implements ISecurityConfigRolesService {
	private static final Logger log = LogManager.getLogger(SecurityConfigRolesServiceImpl.class);
	private ISecurityConfigRolesDAO securityConfigRolesDAO;

	//------------------------DAO-- 声明结束---------------------------------------------------
	
	
	public ISecurityConfigRolesDAO getSecurityConfigRolesDAO() {
		return securityConfigRolesDAO;
	}

	
	public void setSecurityConfigRolesDAO(ISecurityConfigRolesDAO securityConfigRolesDAO) {
		this.securityConfigRolesDAO = securityConfigRolesDAO;
	}

	public void addInit(SecurityConfigRolesForm form){
		init(form);
	}
	
	public void serchInit(SecurityConfigRolesForm form){
		searchInit(form);
	}
	/**获取记录对象*/
	public SecurityConfigRoles checkData(String itemCode){
		SecurityConfigRoles data = securityConfigRolesDAO.findByCode(itemCode);
		return data;
	}
	
	/**提交保存*/
	public void save(SecurityConfigRolesForm form){
		this.setData(form);
		securityConfigRolesDAO.save(form.getData());
	}
	/**修改之前初始化*/
	public void updateInit(SecurityConfigRolesForm form){
		SecurityConfigRoles data = securityConfigRolesDAO.findById(form.getIdHidden());
		form.setData(data);
		this.setForm(form);
		init(form);
	}
	/**提交修改*/
	public void update(SecurityConfigRolesForm form){
		this.setData(form);
		securityConfigRolesDAO.update(form.getData());
	}
	/**提交察看详细*/
	public void showInit(SecurityConfigRolesForm form){
		SecurityConfigRoles data = securityConfigRolesDAO.findById(form.getIdHidden());
		form.setData(data);
		this.setForm(form);
	}
	/**提交删除*/
	public void delete(SecurityConfigRolesForm form){
		SecurityConfigRoles data = securityConfigRolesDAO.findById(form.getIdHidden());
		securityConfigRolesDAO.delete(data);
	}
	/**获取总记录数*/
	public int getCount(String id,String itemCode, String itemName, String inputCode, String seqNo){
		return securityConfigRolesDAO.getCount(id,itemCode, itemName, inputCode, seqNo);
	}
	/**根据查询条件获取当前显示页的数据*/
	public void getSearch(SecurityConfigRolesForm form, int curCount, int pageSize){
		try{
			 
		String order = "";
		if(form.getOrderNo().equals("1")){
			order = " a.roleCode";
		} else if(form.getOrderNo().equals("2")){
			order = " a.roleDetail";
		} else if(form.getOrderNo().equals("3")){
			order = " a.serialNo";
		} else if(form.getOrderNo().equals("4")){
			order = " a.inputCode";
	
		} else {
			order = " a.serialNo";
		}
		
		if(form.getAsc().equals("1")){
			order += " desc";
		} else {
			order += " asc";
		}
		
	 
		List<?> list = securityConfigRolesDAO.getDataList(form.getId()
								, form.getRoleCode()
								, form.getRoleDetail()
								, form.getInputCode()
								, form.getSerialNo()
								, order
								, curCount
								, pageSize);
		 
		//if(list != null && list.size() > 0){
		 
			form.setDataList(list);
			 
	//	}
		 } catch (Exception e) { 
			 log.error("getSearch fail!",e);
			 e.printStackTrace();
	} 
	}
	/**构造data*/
	private void setData(SecurityConfigRolesForm form){
		try{
			form.getData().setId (transNullToString(form.getData().getId()));
			form.getData().setRoleCode (transNullToString(form.getData().getRoleCode()));
			form.getData().setRoleDetail (transNullToString(form.getData().getRoleDetail()));
			form.getData().setInputCode (transNullToString(form.getData().getInputCode()));
			form.getData().setComments  (transNullToString(form.getData().getComments()));
			form.getData().setSerialNo(form.getData().getSerialNo() == null||form.getData().getSerialNo().equals("") ? 0 : form.getData().getSerialNo());
			form.getData().setRoleType(form.getData().getRoleType() == null||form.getData().getRoleType().equals("")?0:form.getData().getRoleType());
			String roleFlag = TJInit.getProperty("classFlag");
			if(roleFlag != null && roleFlag.length()>0){
				form.getData().setRoleFlag(Long.valueOf(roleFlag));
			}else{
				form.getData().setRoleFlag(Long.valueOf("1"));
			}
			 } catch (Exception e) { 
				 log.error("setData fail!",e);
				 e.printStackTrace();
		} 
	}
	/**
	 * 去掉字串中的空格
	 * */
	public String transNullToString(Object obj){
		String temp = "";
		if (obj != null){
			temp = ((String)obj).trim();			
		}
		return temp;
	}
	
	public String transNullToZero(Object obj){
		String temp = "0";
		if (obj != null){
			temp = ((String)obj).trim();			
		}
		return temp;
	}
	/**在传递数据到页面之前构造form*/
	private void setForm(SecurityConfigRolesForm form){
		try{
			form.setIdHidden (transNullToString(form.getData().getId()));
			form.getData().setId (transNullToString(form.getData().getId()));
			form.getData().setRoleCode (transNullToString(form.getData().getRoleCode()));
			form.setRoleCodeHidden (transNullToString(form.getData().getRoleCode()));
			form.getData().setRoleDetail (transNullToString(form.getData().getRoleDetail()));
			form.getData().setInputCode (transNullToString(form.getData().getInputCode()));
			form.getData().setComments  (transNullToString(form.getData().getComments()));
			form.getData().setSerialNo(form.getData().getSerialNo() == null ||form.getData().getSerialNo().equals("") ? 0 : form.getData().getSerialNo());
			form.getData().setRoleType(form.getData().getRoleType() == null ||form.getData().getRoleType().equals("")?0:form.getData().getRoleType());
		 } catch (Exception e) { 
			 log.error("setForm fail!",e);
			 e.printStackTrace();
		} 
	}
	/**在初始化阶段将字典库传入form中*/
	private void init(SecurityConfigRolesForm form){
		if (form.getData().getSerialNo() == null || form.getData().getSerialNo().equals(""))
			form.getData().setSerialNo(Long.valueOf(securityConfigRolesDAO.getMaxSeqNo()+""));

	}
	/***/
	public void getDetail(SecurityConfigRolesForm form){
	
	}
	/**获取查询结果之前初始化*/
	/**在进行查找之前将字典库传入form中*/
	private void searchInit(SecurityConfigRolesForm form){
	}
	
}
