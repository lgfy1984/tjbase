package com.tianjian.comm.dao;

import java.util.List;

import com.tianjian.comm.bean.CommConfigLocation;
 

public interface ICommConfigLocationDAO {
	
	public List<?> getByParent(String parentId, String levelFlag);
		
	  public List<?> getLocation(int level,String parentId);
		
	   public CommConfigLocation findById(String id);
	   
	   public List<?> findAll();
	   
	   public String getItemName(String id);
	    
	    public void save(CommConfigLocation commConfigLocation);
	    
	    public void update(CommConfigLocation commConfigLocation);
	    
	    public void delete(CommConfigLocation commConfigLocation);
	    
	    public List<?> getData(String id,String itemCode, String itemName, String levelFlag,String parentId, String inputCode, String seqNo, String orderNo, int curCount,int pageSize);
	    
	    public int getCount(String id,String itemCode, String itemName, String levelFlag,String parentId, String inputCode, String seqNo);
	    
	    public int getMaxSeqNo();  
	    public List<?> getCommcongfiglocation(String staffid, String l);
}