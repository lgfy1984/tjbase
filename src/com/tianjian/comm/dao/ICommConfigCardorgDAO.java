package com.tianjian.comm.dao;

import java.util.List;
import com.tianjian.comm.bean.CommConfigCardorg;
 

public interface ICommConfigCardorgDAO {
   
    public CommConfigCardorg findById(String id);
    
    public List<?> findAll();
    
	public String getItemName(String id);
	
    public void save(CommConfigCardorg commConfigCardorg);
    
    public void update(CommConfigCardorg commConfigCardorg);
    
    public void delete(CommConfigCardorg commConfigCardorg);
    
    public List<?> getData(String itemCode, String itemName, String inputCode, String seqNo, String orderNo, int curCount,int pageSize);
    
    public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
    
    public int getMaxSeqNo();  
}