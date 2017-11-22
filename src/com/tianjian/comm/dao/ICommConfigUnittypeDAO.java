package com.tianjian.comm.dao;

import java.util.List;
import com.tianjian.comm.bean.CommConfigUnittype;
 

public interface ICommConfigUnittypeDAO {
   
    public CommConfigUnittype findById(String id);
    
    public List<?> findAll();
    
	public String getItemName(String id);
	
    public void save(CommConfigUnittype commConfigUnittype);
    
    public void update(CommConfigUnittype commConfigUnittype);
    
    public void delete(CommConfigUnittype commConfigUnittype);
    
    public List<?> getData(String itemCode, String itemName, String inputCode, String seqNo, String orderNo, int curCount,int pageSize);
    
    public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
    
    public int getMaxSeqNo();    
}