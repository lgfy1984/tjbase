package com.tianjian.comm.dao;

import java.util.List;

import com.tianjian.comm.bean.CommIHEFacility;
 

public interface ICommIHEFacilityDAO {
   
    public CommIHEFacility findById(String id);
    
    public List<?> findAll();
    
//	public String getItemName(String id);
	
    public void save(CommIHEFacility commConfigAbo);
    
    public void update(CommIHEFacility commConfigAbo);
    
    public void delete(CommIHEFacility commConfigAbo);
    
    public List<?> getData(String itemCode, String itemName, String inputCode, String seqNo, String orderNo, int curCount,int pageSize);
    
    public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
    
    
    public Long seqNoMaker(String nameOfTable);
	  
}