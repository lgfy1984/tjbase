package com.tianjian.comm.dao;

import java.util.List;
import com.tianjian.comm.bean.CommConfigIdType;
 

public interface ICommConfigIdTypeDAO {
   
    public CommConfigIdType findById(String id);
    
    public List<?> findAll();
    
	public String getItemName(String id);
	
    public void save(CommConfigIdType commConfigIdType);
    
    public void update(CommConfigIdType commConfigIdType);
    
    public void delete(CommConfigIdType commConfigIdType);
    
    public List<?> getData(String itemCode, String itemName, String inputCode, String seqNo, String orderNo, int curCount,int pageSize);
    
    public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
    
    public int getMaxSeqNo();    
}