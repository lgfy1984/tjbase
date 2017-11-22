package com.tianjian.comm.dao;

import java.util.List;
import com.tianjian.comm.bean.CommConfigDegree;
 

public interface ICommConfigDegreeDAO {
   
    public CommConfigDegree findById(String id);
    
    public List<?> findAll();
    
	public String getItemName(String id);
	
    public void save(CommConfigDegree commConfigDegree);
    
    public void update(CommConfigDegree commConfigDegree);
    
    public void delete(CommConfigDegree commConfigDegree);
    
    public List<?> getData(String itemCode, String itemName, String inputCode, String seqNo, String orderNo, int curCount,int pageSize);
    
    public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
    
    public int getMaxSeqNo();   
}