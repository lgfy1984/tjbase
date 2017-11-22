package com.tianjian.hsp.dao;

import java.util.List;

import com.tianjian.hsp.bean.HspConfigBaseinfo;
import com.tianjian.hsp.bean.HspDeptBaseinfo;
import com.tianjian.hsp.bean.HspStaffBaseinfo;

public interface IOrgMenuDAO {
	
	public List<?> findRoot();
	
	public List<?> findChild(String parentId);
	
	public List<?> findDept(String orgId);
	
	public void saveDept(HspDeptBaseinfo dept) throws Exception;
	
	public void updateDept(HspDeptBaseinfo dept) throws Exception;
	
	public void deleteDept(HspDeptBaseinfo dept) throws Exception;
	
	public List<?> findAllDept();

	public List<?> findAllDeptAttr();

	public HspDeptBaseinfo getDeptById(String orgId, String deptCode);

	public String getNameByCode(String deptAttrCode);

	public List<?> findStaff(String orgId, String deptCode);

	public HspStaffBaseinfo findStaffById(String hspStaffId);

	public String findStaffId(String empNo);

	public void updateStaff(HspStaffBaseinfo hsp);

	public List<?> findDeptByName(String itemCode,String queryKey,String queryValue);

	public List<?> findDeptByInputCode(String queryValue);

	public List<?> findStaffByName(String queryValue);

	public List<?> findStaffByInputCode(String queryValue);
	
	public List<?> findStaffByName(String itemCode,String queryKey,String queryValue);

}
