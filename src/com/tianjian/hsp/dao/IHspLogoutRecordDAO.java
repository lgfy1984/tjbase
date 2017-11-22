package com.tianjian.hsp.dao;

import java.util.List;

import com.tianjian.hsp.bean.HspLogoutRecord;

public interface IHspLogoutRecordDAO {
	
	 public HspLogoutRecord findById(String id);
	    
	 public List<?> findAll();
	    
	 public void save(HspLogoutRecord hspLogoutRecord);
	    
	 public void update(HspLogoutRecord hspLogoutRecord);
	    
	 public void delete(HspLogoutRecord hspLogoutRecord);
	    
	 public List<?> getData(String hspConfigBaseinfoId, String itemCode, String itemName,String order, int curCount,int pageSize);
	    
	 public int getCount(String hspConfigBaseinfoId, String itemCode, String itemName);
}
