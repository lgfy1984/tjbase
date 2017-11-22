package com.tianjian.comm.dao;

import java.util.List;

import com.tianjian.comm.bean.CommConfigSystemtype;
 

public interface ICommConfigSystemtypeDAO {
   
    public CommConfigSystemtype findById(String id);
    
    public List<?> findAll();
    
	public String getItemName(String id);
	
    public void save(CommConfigSystemtype CommConfigSystemtype);
    
    public void update(CommConfigSystemtype CommConfigSystemtype);
    
    public void delete(CommConfigSystemtype CommConfigSystemtype);
    
    public List<?> getData(String itemCode, String itemName, String inputCode, String seqNo, String orderNo, int curCount,int pageSize);
    
    public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
    
    public int getMaxSeqNo();
    
	  
}