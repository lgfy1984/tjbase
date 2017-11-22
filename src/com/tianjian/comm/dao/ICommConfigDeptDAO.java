package com.tianjian.comm.dao;

import java.util.List;
import com.tianjian.comm.bean.CommConfigDept;
 

public interface ICommConfigDeptDAO {
   
    public CommConfigDept findById(String id);
    
    public List<?> findAll();
    
	public String getItemName(String id);
	
    public void save(CommConfigDept commConfigDept);
    
    public void update(CommConfigDept commConfigDept);
    
    public void delete(CommConfigDept commConfigDept);
    
    public List<?> getData(String itemCode, String itemName, String inputCode, String seqNo, String orderNo, int curCount,int pageSize);
    
    public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
    
    public int getMaxSeqNo();    
}