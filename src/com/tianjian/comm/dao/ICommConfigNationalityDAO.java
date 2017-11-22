package com.tianjian.comm.dao;

import java.util.List;
import com.tianjian.comm.bean.CommConfigNationality;
 

public interface ICommConfigNationalityDAO {
   
    public CommConfigNationality findById(String id);
    
    public List<?> findAll();
    
	public String getItemName(String id);
	
    public void save(CommConfigNationality commConfigNationality);
    
    public void update(CommConfigNationality commConfigNationality);
    
    public void delete(CommConfigNationality commConfigNationality);
    
    public List<?> getData(String itemCode, String itemName, String inputCode, String seqNo, String orderNo, int curCount,int pageSize);
    
    public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
    
    public int getMaxSeqNo();   
}