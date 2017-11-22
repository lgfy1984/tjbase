package com.tianjian.comm.dao;

import java.util.List;
import com.tianjian.comm.bean.CommConfigClinicattr;
 

public interface ICommConfigClinicattrDAO {
   
    public CommConfigClinicattr findById(String id);
    
    public List<?> findAll();
    
	public String getItemName(String id);
	
    public void save(CommConfigClinicattr commConfigClinicattr);
    
    public void update(CommConfigClinicattr commConfigClinicattr);
    
    public void delete(CommConfigClinicattr commConfigClinicattr);
    
    public List<?> getData(String itemCode, String itemName, String inputCode, String seqNo, String orderNo, int curCount,int pageSize);
    
    public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
    
    public int getMaxSeqNo();    
}