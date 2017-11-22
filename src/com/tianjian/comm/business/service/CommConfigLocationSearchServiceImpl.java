package com.tianjian.comm.business.service;


import java.util.List;

import com.tianjian.comm.bean.CommConfigLocation;
import com.tianjian.comm.bean.CommConfigLocationGroup;
import com.tianjian.comm.bean.CommConfigLocationTown;
import com.tianjian.comm.bean.CommConfigLocationVillage;
import com.tianjian.comm.struts.servlet.CommConfigLocationTownInit;
import com.tianjian.comm.business.ICommConfigLocationSearchService;
import com.tianjian.comm.dao.ICommConfigLocationSearchDAO;
import com.tianjian.comm.struts.form.UserSelectForm;

public class CommConfigLocationSearchServiceImpl implements ICommConfigLocationSearchService{

	private ICommConfigLocationSearchDAO commConfigLocationSearchDAO;

	public ICommConfigLocationSearchDAO getCommConfigLocationSearchDAO() {
		return commConfigLocationSearchDAO;
	}

	public void setCommConfigLocationSearchDAO(
			ICommConfigLocationSearchDAO commConfigLocationSearchDAO) {
		this.commConfigLocationSearchDAO = commConfigLocationSearchDAO;
	}
	private String flag = CommConfigLocationTownInit.getProperty("PRINT_FLAG");
	
	public void getCitys(UserSelectForm form, String staffId) {
		String[] tempIds = null;
		String[] tempNames = null;
		List<?> list = null;
//		commCityIds & commCityNames;
		if(form.getCommProvinceId() != null && form.getCommProvinceId().trim().length() > 0){
			list = commConfigLocationSearchDAO.getByParent(form.getCommProvinceId().trim(), "2", staffId);
			if(list != null && list.size() > 0){
				tempIds = new String[list.size()+1];
				tempNames = new String[list.size()+1];
				tempIds[0] = "";
				tempNames[0] = "";
				for(int i=0; i<list.size(); i++){
					CommConfigLocation location = (CommConfigLocation)list.get(i);
					tempIds[i+1] = this.transNullToString(location.getId());
					tempNames[i+1] = this.transNullToString(location.getItemName());
				}
				form.setCommCityIds(tempIds);
				form.setCommCityNames(tempNames);
			}
		}	
	}
	
	public void getCounties(UserSelectForm form, String staffId) {
		String[] tempIds = null;
		String[] tempNames = null;
		List<?> list = null;	
//		commCountyIds & commCountyNames;
		if(form.getCommCityId() != null && form.getCommCityId().trim().length() > 0){
			list =commConfigLocationSearchDAO.getByParent(form.getCommCityId().trim(), "3", staffId);
			if(list != null && list.size() > 0){
				tempIds = new String[list.size()+1];
				tempNames = new String[list.size()+1];
				tempIds[0] = "";
				tempNames[0] = "";
				for(int i=0; i<list.size(); i++){
					CommConfigLocation location = (CommConfigLocation)list.get(i);
					tempIds[i+1] = this.transNullToString(location.getId());
					tempNames[i+1] = this.transNullToString(location.getItemName());
				}
				form.setCommCountyIds(tempIds);
				form.setCommCountyNames(tempNames);
			}
		}
	}
	
	public void getTowns(UserSelectForm form, String staffId) {
		String[] tempIds = null;
		String[] tempNames = null;
		List<?> list = null;
//		commCltIds & commCltNames;
		if(form.getCommCountyId() != null && form.getCommCountyId().trim().length() > 0){
			list = commConfigLocationSearchDAO.getTownsByParent(form.getCommCountyId().trim(), staffId);
			if(list != null && list.size() > 0){
				tempIds = new String[list.size()+1];
				tempNames = new String[list.size()+1];
				tempIds[0] = "";
				tempNames[0] = "";
				for(int i=0; i<list.size(); i++){
					CommConfigLocationTown town = (CommConfigLocationTown)list.get(i);
					tempIds[i+1] = this.transNullToString(town.getId());
					tempNames[i+1] = this.transNullToString(town.getItemName());
				}
				form.setCommCltIds(tempIds);
				form.setCommCltNames(tempNames);
			}
		}
	}
	
	public void getVillages(UserSelectForm form, String staffId) {
		String[] tempIds = null;
		String[] tempNames = null;
		List<?> list = null;
//		commClvIds & commClvNames;
		if(form.getCommCltId() != null && form.getCommCltId().trim().length() > 0){
			list = commConfigLocationSearchDAO.getVillagesByParent(form.getCommCltId().trim(), staffId);
			if(list != null && list.size() > 0){
				tempIds = new String[list.size()+1];
				tempNames = new String[list.size()+1];
				tempIds[0] = "";
				tempNames[0] = "";
				for(int i=0; i<list.size(); i++){
					CommConfigLocationVillage town = (CommConfigLocationVillage)list.get(i);
					tempIds[i+1] = this.transNullToString(town.getId());
					tempNames[i+1] = this.transNullToString(town.getItemName());
				}
				form.setCommClvIds(tempIds);
				form.setCommClvNames(tempNames);
			}
		}
	}
	
	public void getGroups(UserSelectForm form) {
		String[] tempIds = null;
		String[] tempNames = null;
		List<?> list = null;
//		commClvIds & commClvNames;
		if(form.getCommClvId() != null && form.getCommClvId().trim().length() > 0){
			list = commConfigLocationSearchDAO.getGroupByParent(form.getCommClvId().trim());
			if(list != null && list.size() > 0){
				tempIds = new String[list.size()+1];
				tempNames = new String[list.size()+1];
				tempIds[0] = "";
				tempNames[0] = "";
				for(int i=0; i<list.size(); i++){
					CommConfigLocationGroup group = (CommConfigLocationGroup)list.get(i);
					tempIds[i+1] = this.transNullToString(group.getId());
					tempNames[i+1] = this.transNullToString(group.getItemName());
				}
				form.setCommGroupIds(tempIds);
				form.setCommGroupNames(tempNames);
			}
		}
	}
	public void getDict(UserSelectForm form, String staffId){
		String[] tempIds = null;
		String[] tempNames = null;
		List<?> list = null;
//		commProvinceIds & commProvinceNames;
		list = commConfigLocationSearchDAO.getByParent("", "1", staffId);
		if(list != null && list.size() > 0){
			tempIds = new String[list.size()+1];
			tempNames = new String[list.size()+1];
			tempIds[0] = "";
			tempNames[0] = "";
			for(int i=0; i<list.size(); i++){
				CommConfigLocation location = (CommConfigLocation)list.get(i);
				tempIds[i+1] = this.transNullToString(location.getId());
				tempNames[i+1] = this.transNullToString(location.getItemName());
			}
			form.setCommProvinceIds(tempIds);
			form.setCommProvinceNames(tempNames);
		} else {
			tempIds = new String[1];
			tempNames = new String[1];
			tempIds[0] = "";
			tempNames[0] = "";
			form.setCommProvinceIds(tempIds);
			form.setCommProvinceNames(tempNames);			
		}
//		commCityIds & commCityNames;
		tempIds = null;
		tempNames = null;
		list = null;
		if(form.getCommProvinceId() != null && form.getCommProvinceId().trim().length() > 0){
			list = commConfigLocationSearchDAO.getByParent(form.getCommProvinceId().trim(), "2", staffId);
			if(list != null && list.size() > 0){
				tempIds = new String[list.size()+1];
				tempNames = new String[list.size()+1];
				tempIds[0] = "";
				tempNames[0] = "";
				for(int i=0; i<list.size(); i++){
					CommConfigLocation location = (CommConfigLocation)list.get(i);
					tempIds[i+1] = this.transNullToString(location.getId());
					tempNames[i+1] = this.transNullToString(location.getItemName());
				}
				form.setCommCityIds(tempIds);
				form.setCommCityNames(tempNames);
			} else {
				tempIds = new String[1];
				tempNames = new String[1];
				tempIds[0] = "";
				tempNames[0] = "";
				form.setCommCityIds(tempIds);
				form.setCommCityNames(tempNames);	
			}
		} else {
			tempIds = new String[1];
			tempNames = new String[1];
			tempIds[0] = "";
			tempNames[0] = "";
			form.setCommCityIds(tempIds);
			form.setCommCityNames(tempNames);	
		}	
//		commCountyIds & commCountyNames;
		tempIds = null;
		tempNames = null;
		list = null;
		if(form.getCommCityId() != null && form.getCommCityId().trim().length() > 0){
			list = commConfigLocationSearchDAO.getByParent(form.getCommCityId().trim(), "3", staffId);
			if(list != null && list.size() > 0){
				tempIds = new String[list.size()+1];
				tempNames = new String[list.size()+1];
				tempIds[0] = "";
				tempNames[0] = "";
				for(int i=0; i<list.size(); i++){
					CommConfigLocation location = (CommConfigLocation)list.get(i);
					tempIds[i+1] = this.transNullToString(location.getId());
					tempNames[i+1] = this.transNullToString(location.getItemName());
				}
				form.setCommCountyIds(tempIds);
				form.setCommCountyNames(tempNames);
			} else {
				tempIds = new String[1];
				tempNames = new String[1];
				tempIds[0] = "";
				tempNames[0] = "";
				form.setCommCountyIds(tempIds);
				form.setCommCountyNames(tempNames);
			}
		} else {
			tempIds = new String[1];
			tempNames = new String[1];
			tempIds[0] = "";
			tempNames[0] = "";
			form.setCommCountyIds(tempIds);
			form.setCommCountyNames(tempNames);
		}	
//		commCltIds & commCltNames;
		tempIds = null;
		tempNames = null;
		list = null;
		if(form.getCommCountyId() != null && form.getCommCountyId().trim().length() > 0){
			list = commConfigLocationSearchDAO.getTownsByParent(form.getCommCountyId().trim(), staffId);
			if(list != null && list.size() > 0){
				tempIds = new String[list.size()+1];
				tempNames = new String[list.size()+1];
				tempIds[0] = "";
				tempNames[0] = "";
				for(int i=0; i<list.size(); i++){
					CommConfigLocationTown town = (CommConfigLocationTown)list.get(i);
					tempIds[i+1] = this.transNullToString(town.getId());
					tempNames[i+1] = this.transNullToString(town.getItemName());
				}
				form.setCommCltIds(tempIds);
				form.setCommCltNames(tempNames);
			} else {
				tempIds = new String[1];
				tempNames = new String[1];
				tempIds[0] = "";
				tempNames[0] = "";
				form.setCommCltIds(tempIds);
				form.setCommCltNames(tempNames);
			}
		} else {
			tempIds = new String[1];
			tempNames = new String[1];
			tempIds[0] = "";
			tempNames[0] = "";
			form.setCommCltIds(tempIds);
			form.setCommCltNames(tempNames);
		}
//		commClvIds & commClvNames;
		tempIds = null;
		tempNames = null;
		list = null;
		if(form.getCommCltId() != null && form.getCommCltId().trim().length() > 0){
			list = commConfigLocationSearchDAO.getVillagesByParent(form.getCommCltId().trim(), staffId);
			if(list != null && list.size() > 0){
				tempIds = new String[list.size()+1];
				tempNames = new String[list.size()+1];
				tempIds[0] = "";
				tempNames[0] = "";
				for(int i=0; i<list.size(); i++){
					CommConfigLocationVillage town = (CommConfigLocationVillage)list.get(i);
					tempIds[i+1] = this.transNullToString(town.getId());
					tempNames[i+1] = this.transNullToString(town.getItemName());
				}
				form.setCommClvIds(tempIds);
				form.setCommClvNames(tempNames);
			} else {
				tempIds = new String[1];
				tempNames = new String[1];
				tempIds[0] = "";
				tempNames[0] = "";
				form.setCommClvIds(tempIds);
				form.setCommClvNames(tempNames);
			}
		} else {
			tempIds = new String[1];
			tempNames = new String[1];
			tempIds[0] = "";
			tempNames[0] = "";
			form.setCommClvIds(tempIds);
			form.setCommClvNames(tempNames);
		}
		
		
		tempIds = null;
		tempNames = null;
		list = null;
		if(form.getCommClvId() != null && form.getCommClvId().trim().length() > 0){
			list = commConfigLocationSearchDAO.getGroupByParent(form.getCommClvId().trim());
			if(list != null && list.size() > 0){
				tempIds = new String[list.size()+1];
				tempNames = new String[list.size()+1];
				tempIds[0] = "";
				tempNames[0] = "";
				for(int i=0; i<list.size(); i++){
					CommConfigLocationGroup group = (CommConfigLocationGroup)list.get(i);
					tempIds[i+1] = this.transNullToString(group.getId());
					tempNames[i+1] = this.transNullToString(group.getItemName());
				}
				form.setCommGroupIds(tempIds);
				form.setCommGroupNames(tempNames);
			} else {
				tempIds = new String[1];
				tempNames = new String[1];
				tempIds[0] = "";
				tempNames[0] = "";
				form.setCommGroupIds(tempIds);
				form.setCommGroupNames(tempNames);
			}
		} else {
			tempIds = new String[1];
			tempNames = new String[1];
			tempIds[0] = "";
			tempNames[0] = "";
			form.setCommGroupIds(tempIds);
			form.setCommGroupNames(tempNames);
		}
	}
	public String transNullToString(Object obj) {
		String temp = "";
		if (obj != null) {
			temp = String.valueOf(obj).trim();
		}
		return temp;
	}

	public String transNullToZero(Object obj) {
		String temp = "0";
		if (obj != null) {
			temp = ((String) obj).trim();
		}
		return temp;
	}
}
