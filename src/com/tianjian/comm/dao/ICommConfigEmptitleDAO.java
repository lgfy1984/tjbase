package com.tianjian.comm.dao;

import java.util.List;
import com.tianjian.comm.bean.CommConfigEmptitle;
 

public interface ICommConfigEmptitleDAO {
   
    public CommConfigEmptitle findById(String id);
    
    public List<?> findAll();
    
	public String getItemName(String id);
	
    public void save(CommConfigEmptitle commConfigEmptitle);
    
    public void update(CommConfigEmptitle commConfigEmptitle);
    
    public void delete(CommConfigEmptitle commConfigEmptitle);
    
    public List<?> getData(String itemCode, String itemName, String inputCode, String seqNo, String orderNo, int curCount,int pageSize);
    
    public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
    
    public int getMaxSeqNo();    
}