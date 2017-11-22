package com.tianjian.comm.dao;

import java.util.List;
import com.tianjian.comm.bean.CommConfigDeptAttr;
 

public interface ICommConfigDepttypeDAO {
   
    public CommConfigDeptAttr findById(String id);
    
    public List<?> findAll();
    
	public String getItemName(String id);
	
    public void save(CommConfigDeptAttr commConfigDepttype);
    
    public void update(CommConfigDeptAttr commConfigDepttype);
    
    public void delete(CommConfigDeptAttr commConfigDepttype);
    
    public List<?> getData(String itemCode, String itemName, String inputCode, String seqNo, String orderNo, int curCount,int pageSize);
    
    public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
    
    public int getMaxSeqNo();   
}