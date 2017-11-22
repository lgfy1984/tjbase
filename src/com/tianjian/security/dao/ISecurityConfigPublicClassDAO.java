package com.tianjian.security.dao;

import java.util.List;

import com.tianjian.security.bean.SecurityConfigPublicClass;

/**
 * DAO接口
 * @author ch_f001
 * @since 2008-3-24 15:39
 */
public interface ISecurityConfigPublicClassDAO {

	public SecurityConfigPublicClass findById(String Id);
	
	public SecurityConfigPublicClass findByCode(String Id);

	public List<?> findAll();
	
	/**
	 * 查询可以作为父模块类别的一级模块类别
	 * @return
	 */
	public List<?> findParentPublicClass();
	
	public String getClassName(String id);

	public void save(SecurityConfigPublicClass securityConfigPublicClass);

	public void delete(SecurityConfigPublicClass securityConfigPublicClass);

	public void update(SecurityConfigPublicClass securityConfigPublicClass);

	public List<?> getDataList(String id, String classCode, String className, String inputCode, String serialNo,String levelFlag,String panterId, String orderNo, int curCount, int pageSize);

	public int getCount(String id, String classCode, String className, String inputCode, String serialNo,String levelFlag,String panterId);
	
	public long getMaxSeqNo();
	
	public List<?> findAllPublic();

	public int getPublicCountByPublicClassId(String publicClassId);

	public int getPublicClass2CountByPublicClassId(String publicClassId);
}
