package com.tianjian.security.dao;

import java.util.List;

import com.tianjian.comm.bean.CommConfigLocation;
import com.tianjian.comm.bean.CommConfigLocationTown;
import com.tianjian.comm.bean.CommConfigLocationVillage;
import com.tianjian.security.bean.SecurityDataObjectType;
import com.tianjian.security.bean.SecurityDataObjectVsRoles;
import com.tianjian.security.bean.SecurityStaffBaseinfo;

public interface ISecurityDataObjectVsRoleServiceDAO {
	/**
	 * 根据ID查看SecurityDataObjectVsRoles
	 * @param id
	 * @return
	 */
	public SecurityDataObjectVsRoles getDetail(String id);
	
	public SecurityStaffBaseinfo getName(String name);
	
	public SecurityDataObjectType getRoles(String name);
	
	public SecurityStaffBaseinfo getId(String id);
	
	public SecurityDataObjectType getRolesName(String id);
	
	public CommConfigLocation getLocationName(String id);
	
	public CommConfigLocationTown getLocationTown(String id);
	
	public CommConfigLocationVillage getVillage(String id);
	
	public int getCount(String staffCode, String hspConfigBaseinfoId,String id,String securityStaffBaseInfo, String sdotId,String inputCode);
	
	public List<?> getDate(String staffCode,String hspConfigBaseinfoId,String id,String securityStaffBaseInfo,String sdotId,String inputCode,int first,int pageSize,String orderNo);

	public void delete(Object obj);
	
	public void update(Object obj);
	
	public void save(Object obj);
	
	public int getCheckId(String id);
	
	public List getObjectTbales(String itemCode,String itemName ,String object);
	
	public List<?> getFindList(String flag,String inputCode,int cur,int pageSaie);
	
	public List<?> getFindLocation(String flag,String inputCode,int cur,int pageSaie,int level);
	
	public List<?> getFindLocationTown(String flag,String inputCode,int cur,int pageSaie);
	public List<?> getFindLocationVilge(String flag,String inputCode,int cur,int pageSaie);
	
	public List<?> getLocation(int level,String parentId);
	
	public List<?> getNeedSecurityDataObjectVsRoles(String id,String sdotId);
	
	public List<?> getLocationOther(String tableName,String columnName,String parentId);
}
