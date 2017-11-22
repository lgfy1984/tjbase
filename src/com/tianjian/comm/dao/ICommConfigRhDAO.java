package com.tianjian.comm.dao;

import java.util.List;
import com.tianjian.comm.bean.CommConfigRh;
 

public interface ICommConfigRhDAO {
   
    public CommConfigRh findById(String id);
    
    public List<?> findAll();
    
	public String getItemName(String id);
	
    public void save(CommConfigRh commConfigRh);
    
    public void update(CommConfigRh commConfigRh);
    
    public void delete(CommConfigRh commConfigRh);
    
    public List<?> getData(String itemCode, String itemName, String inputCode, String seqNo, String orderNo, int curCount,int pageSize);
    
    public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
    
    public int getMaxSeqNo();    
}