package com.tianjian.comm.dao;

import java.util.List;
import com.tianjian.comm.bean.CommConfigUnitgrade;
 

public interface ICommConfigUnitgradeDAO {
   
    public CommConfigUnitgrade findById(String id);
    
    public List<?> findAll();
    
	public String getItemName(String id);
	
    public void save(CommConfigUnitgrade commConfigUnitgrade);
    
    public void update(CommConfigUnitgrade commConfigUnitgrade);
    
    public void delete(CommConfigUnitgrade commConfigUnitgrade);
    
    public List<?> getData(String itemCode, String itemName, String inputCode, String seqNo, String orderNo, int curCount,int pageSize);
    
    public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
    
    public int getMaxSeqNo();   
}