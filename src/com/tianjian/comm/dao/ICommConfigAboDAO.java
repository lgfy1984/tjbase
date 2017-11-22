package com.tianjian.comm.dao;

import java.util.List;
import com.tianjian.comm.bean.CommConfigAbo;
 

public interface ICommConfigAboDAO {
   
    public CommConfigAbo findById(String id);
    
    public List<?> findAll();
    
	public String getItemName(String id);
	
    public void save(CommConfigAbo commConfigAbo);
    
    public void update(CommConfigAbo commConfigAbo);
    
    public void delete(CommConfigAbo commConfigAbo);
    
    public List<?> getData(String itemCode, String itemName, String inputCode, String seqNo, String orderNo, int curCount,int pageSize);
    
    public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
    
    public Long seqNoMaker(String nameOfTable);
    
	  
}