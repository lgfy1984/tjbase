package com.tianjian.comm.dao;

import java.util.List;
import com.tianjian.comm.bean.CommConfigCountry;
 

public interface ICommConfigCountryDAO {
   
    public CommConfigCountry findById(String id);
    
    public List<?> findAll();
    
	public String getItemName(String id);
	
    public void save(CommConfigCountry commConfigCountry);
    
    public void update(CommConfigCountry commConfigCountry);
    
    public void delete(CommConfigCountry commConfigCountry);
    
    public List<?> getData(String itemCode, String itemName, String inputCode, String seqNo, String orderNo, int curCount,int pageSize);
    
    public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
    
    public int getMaxSeqNo();    
}