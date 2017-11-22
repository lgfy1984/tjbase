package com.tianjian.comm.dao;

import java.util.List;


import com.tianjian.comm.bean.CommConfigSettype;

 

public interface ICommConfigSettypeDAO {
	 
public CommConfigSettype findById(String id);
    
    public List<?> findAll();
    
	public String getItemName(String id);
	
    public void save(CommConfigSettype commConfigSettype);
    
    public void update(CommConfigSettype commConfigSettype);
    
    public void delete(CommConfigSettype commConfigSettype);
    
    public List<?> getData(String itemCode, String itemName, String inputCode, String seqNo, String orderNo, int curCount,int pageSize);
    
    public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
    
    public int getMaxSeqNo();    
	  
}