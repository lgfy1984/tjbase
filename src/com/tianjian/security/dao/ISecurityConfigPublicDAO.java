package com.tianjian.security.dao;

import java.util.List;

import com.tianjian.security.bean.SecurityConfigPublic;

/**
 * DAO接口
 * @author ch_f001
 * @since 2008-3-24 15:39
 */
public interface ISecurityConfigPublicDAO {
	
	public List<?> getMod(String modId,String staffType);
	
	public List<?> getMods();
	
	public List<?> getModClass();
	
	public SecurityConfigPublic findById(String Id);
	
	public SecurityConfigPublic findByCode(String Id);

	public List<?> findAll();

	public String getItemName(String id);

	public void save(SecurityConfigPublic securityConfigPublic);

	public void delete(SecurityConfigPublic securityConfigPublic);

	public void update(SecurityConfigPublic securityConfigPublic);

	public List<?> getDataList(String id,String modCode, String reason, String inputCode, String serialNo, String parentId, String orderNo, int curCount, int pageSize);

	public int getCount(String id,String modCode, String reasonValue, String inputCode, String serialNo,String parentId);
	
	public long getMaxSeqNo();

	public int getMenusCountByPublicId(String publicId);
}
