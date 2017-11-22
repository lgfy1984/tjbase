package com.tianjian.comm.dao;

import java.util.List;
import com.tianjian.comm.bean.CommConfigCardtype;
 

public interface ICommConfigCardtypeDAO {
   
    public CommConfigCardtype findById(String id);
    
    public List<?> findAll();
    
	public String getItemName(String id);
	
    public void save(CommConfigCardtype commConfigCardtype);
    
    public void update(CommConfigCardtype commConfigCardtype);
    
    public void delete(CommConfigCardtype commConfigCardtype);
    
    public List<?> getData(String itemCode, String itemName, String inputCode, String seqNo, String orderNo, int curCount,int pageSize);
    
    public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
    
    public int getMaxSeqNo();    
}