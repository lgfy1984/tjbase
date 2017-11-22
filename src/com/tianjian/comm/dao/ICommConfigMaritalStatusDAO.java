package com.tianjian.comm.dao;

import java.util.List;
import com.tianjian.comm.bean.CommConfigMaritalStatus;
 

public interface ICommConfigMaritalStatusDAO {
   
    public CommConfigMaritalStatus findById(String id);
    
    public List<?> findAll();
    
	public String getItemName(String id);
	
    public void save(CommConfigMaritalStatus commConfigMaritalStatus);
    
    public void update(CommConfigMaritalStatus commConfigMaritalStatus);
    
    public void delete(CommConfigMaritalStatus commConfigMaritalStatus);
    
    public List<?> getData(String itemCode, String itemName, String inputCode, String seqNo, String orderNo, int curCount,int pageSize);
    
    public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
    
    public int getMaxSeqNo();    
}