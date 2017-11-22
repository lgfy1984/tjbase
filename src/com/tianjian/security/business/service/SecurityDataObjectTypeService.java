package com.tianjian.security.business.service;

import java.util.List;

import com.tianjian.security.bean.SecurityDataObjectType;
import com.tianjian.security.business.ISecurityDataObjectTypeService;
import com.tianjian.security.dao.ISecurityDataObjectTypeDAO;
import com.tianjian.security.struts.form.SecurityDataObjectTypeForm;

public class SecurityDataObjectTypeService implements ISecurityDataObjectTypeService {
	private ISecurityDataObjectTypeDAO securityDataObjectTypeDAO;
	
	
	public boolean save(String id, String dataObjectTypeName, String comulnName) {
		try {
			SecurityDataObjectType type=new SecurityDataObjectType();
			type.setId(id);
			type.setDataObjectTypeName(dataObjectTypeName);
			type.setColumnName(comulnName);
			securityDataObjectTypeDAO.save(type);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public ISecurityDataObjectTypeDAO getSecurityDataObjectTypeDAO() {
		return securityDataObjectTypeDAO;
	}
	public void setSecurityDataObjectTypeDAO(
			ISecurityDataObjectTypeDAO securityDataObjectTypeDAO) {
		this.securityDataObjectTypeDAO = securityDataObjectTypeDAO;
	}


	
	public int check(String id) {
		return securityDataObjectTypeDAO.checkId(id);
	}


	
	public int getCount(String id, String dataObjectTypeName, String comulnName) {
		return securityDataObjectTypeDAO.getCount(id, dataObjectTypeName, comulnName);
	}


	
	public void getSearch(SecurityDataObjectTypeForm form,int count,int pageSize) {
		String order = "";
		if (form.getOrderNo().equals("1")) {
			order = " s.id ";
		} else if (form.getOrderNo().equals("2")) {
			order = " s.dataObjectTypeName ";
		} else if (form.getOrderNo().equals("3")) {
				order = " s.columnName ";
		} else{
			order = "s.id";
		}
		if (form.getAsc().equals("1")) {
			order += " desc";
		} else {
			order += " asc";
		}
		List<?> list=securityDataObjectTypeDAO.getDate(form.getId(), form.getDataObjectTypeName(), form.getComulnName(), count, pageSize,order);
		if(list!=null&&list.size()>0){
			String[] idList=new String[list.size()];
			String[] dataObjectTypeNameList=new String[list.size()];
			String[] comulnNameList=new String[list.size()];
			for(int i=0;i<list.size();i++){
				SecurityDataObjectType type=(SecurityDataObjectType)list.get(i);
				idList[i]=transNullToString(type.getId());
				dataObjectTypeNameList[i]=transNullToString(type.getDataObjectTypeName());
				comulnNameList[i]=transNullToString(type.getColumnName());
			}
			form.setIds(idList);
			form.setDataObjectTypeNames(dataObjectTypeNameList);
			form.setComulnNames(comulnNameList);
		}
	}
	private String transNullToString(Object valueOf) {
		String temp = "";
		try{
			if(valueOf != null)
				temp = String.valueOf(valueOf).trim();
			if(valueOf != null && valueOf.equals("null"))
				temp = "";
		}catch(Exception e){
			e.printStackTrace();
			temp = "";
		}
		return temp;
	}


	
	public void detail(SecurityDataObjectTypeForm form) {
		SecurityDataObjectType list=securityDataObjectTypeDAO.detail(form.getItemCodeHidden());
		setForm(list,form);
		
	}
	public void setForm(SecurityDataObjectType type,SecurityDataObjectTypeForm form){
		form.setId(type.getId());
		form.setComulnName(type.getColumnName());
		form.setDataObjectTypeName(type.getDataObjectTypeName());
	}


	
	public void delete(SecurityDataObjectTypeForm form) {
		SecurityDataObjectType list=securityDataObjectTypeDAO.detail(form.getId());
		
		securityDataObjectTypeDAO.delete(list);
	}


	
	public SecurityDataObjectType detail(String form) {
		SecurityDataObjectType list=securityDataObjectTypeDAO.detail(form);
		return list;
	}


	
	public void update(SecurityDataObjectTypeForm form) {
		SecurityDataObjectType type=new SecurityDataObjectType();
		setData(form,type);
		securityDataObjectTypeDAO.update(type);
	}
	public void setData(SecurityDataObjectTypeForm form,SecurityDataObjectType type){
		type.setId(form.getId());
		type.setDataObjectTypeName(form.getDataObjectTypeName());
		type.setColumnName(form.getComulnName());
	}

}
