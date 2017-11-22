package com.tianjian.comm.dao;

import java.util.List;
import com.tianjian.comm.bean.CommConfigStafftype;
 

public interface ICommConfigStafftypeDAO {
	public CommConfigStafftype findByItemName(String itemName);
	
    public CommConfigStafftype findById(String id);
    
    public List<?> findAll();
    
	public String getItemName(String id);
	
    public void save(CommConfigStafftype commConfigStafftype);
    
    public void update(CommConfigStafftype commConfigStafftype);
    
    public void delete(CommConfigStafftype commConfigStafftype);
    
    public List<?> getData(String itemCode, String itemName, String inputCode, String seqNo, String orderNo, int curCount,int pageSize);
    
    public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
    
    public int getMaxSeqNo();    
}