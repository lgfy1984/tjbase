package com.tianjian.security.dao;

import java.util.List;

import com.tianjian.security.bean.SecurityConfigRoles;

/**
 * DAO接口
 * @author ch_f001
 * @since 2008-3-24 15:39
 */
public interface ISecurityConfigRolesDAO {

	public SecurityConfigRoles findById(String Id);
	
	public SecurityConfigRoles findByCode(String itemCode);

	public String getItemName(String id);

	public List<?> findAll();

	public void save(SecurityConfigRoles securityConfigRoles);

	public void delete(SecurityConfigRoles securityConfigRoles);

	public void update(SecurityConfigRoles securityConfigRoles);

	public List<?> getDataList(String id, String itemCode, String itemName, String inputCode, String seqNo, String orderNo, int curCount, int pageSize);

	public int getCount(String id, String itemCode, String itemName, String inputCode, String seqNo);
	
	public int getMaxSeqNo();
	
}
