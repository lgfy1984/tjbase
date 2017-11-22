package com.tianjian.security.dao;

import java.util.List;

import com.tianjian.security.bean.SecurityConfigMenus;
import com.tianjian.security.bean.SecurityConfigPublic;

/**
 * DAO接口
 * @author ch_f001
 * @since 2008-3-24 15:39
 */
public interface ISecurityConfigMenusDAO {
	
	public List<?> findByParentId(String id);
	
	public SecurityConfigPublic getModPublic(String modId);
	
	public List<?> getModMenu(String modId,String staffType);
	
	public List<?> getMods();
	
	public List<?> getModClass();
	
	public SecurityConfigMenus findById(String Id);
	
	public SecurityConfigMenus findByCode(String Id);

	public String getMenuDetail(String id);

	public List<?> findAll();

	public void save(SecurityConfigMenus SecurityConfigMenus);

	public void delete(SecurityConfigMenus SecurityConfigMenus);

	public void update(SecurityConfigMenus SecurityConfigMenus);

	public List<?> getDataList(String id,String menuCode, String menuDetail, String inputCode, String serialNo, String orderNo, int curCount, int pageSize);

	public int getCount(String id ,String menuCode, String menuDetail, String inputCode, String serialNo);
	
	public List<?> getAjaxDict(String inputFlag, String inputValue) ;
	
	public long getMaxSeqNo();

	public int getChildrenCount(String id);
}
