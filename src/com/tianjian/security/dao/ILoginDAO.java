package com.tianjian.security.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tianjian.security.bean.SecurityConfigPublicClass;
import com.tianjian.security.bean.SecurityStaffBaseinfo;
import com.tianjian.security.bean.SecuritySystemUsers;
/**
 * TODO
 * <p>Title: ILoginDAO.java</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: Tianjian</p>
 * <p>team: TianjianTeam</p>
 * @author: LengJiong
 * @date 2015年7月26日下午6:40:08
 * @version 1.0 
 */
public interface ILoginDAO {
	/**
	*TODO
	* @Title: getByTenantId
	* @param tenantId
	* @return
	* @throws Exception
	* @return List
	* @throws
	* @author LengJiong
	*/
	public List<?> getByTenantId(String tenantId,String paramCode) throws Exception;
	/**
	*TODO
	* @Title: getHspList
	* @param tenantId
	* @return
	* @throws Exception
	* @return List
	* @throws
	* @author LengJiong
	*/
	public List  getHspList(String tenantId) throws Exception;
	public SecurityStaffBaseinfo getByCode(String code) throws Exception ;
	
	public SecuritySystemUsers getById(String id) throws Exception;
	
	public List<?> getBysecurityStaffBaseinfoId(String rolesId)throws Exception;
	
	public List<?> getPublicClass(String roles,HttpServletRequest request) throws Exception;
 
	public String getRegistCode() throws Exception;

	/**查询第n级模块类别*/
	public List getPublicClass(int n) throws Exception;
	
	/**根据模块类别查找上一级模块类别*/
	public SecurityConfigPublicClass getParentPublicClassById(String id) throws Exception;
	
	/**根据模块类别查找下一级模块类别*/
	public List<SecurityConfigPublicClass> getPublicClassByParentId(String parentId) throws Exception;
	
	/**根据ID查找*/
	public SecurityConfigPublicClass getPublicClassById(String id) throws Exception;
	
}