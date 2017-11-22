package com.tianjian.comm.dao;

import java.util.List;
import com.tianjian.comm.bean.CommConfigSex;
 

public interface ICommConfigSexDAO {
   
    public CommConfigSex findById(String id);
    
    public List<?> findAll();
    
	public String getItemName(String id);
	
    public void save(CommConfigSex commConfigSex);
    
    public void update(CommConfigSex commConfigSex);
    
    public void delete(CommConfigSex commConfigSex);
    
    public List<?> getData(String itemCode, String itemName, String inputCode, String seqNo, String orderNo, int curCount,int pageSize);
    
    public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
    
    public int getMaxSeqNo();   
}