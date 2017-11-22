package com.tianjian.comm.dao;

import java.util.List;
import com.tianjian.comm.bean.CommConfigProfession;
 

public interface ICommConfigProfessionDAO {
   
    public CommConfigProfession findById(String id);
    
    public List<?> findAll();
    
	public String getItemName(String id);
	
    public void save(CommConfigProfession commConfigProfession);
    
    public void update(CommConfigProfession commConfigProfession);
    
    public void delete(CommConfigProfession commConfigProfession);
    
    public List<?> getData(String itemCode, String itemName, String inputCode, String seqNo, String orderNo, int curCount,int pageSize);
    
    public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
    
    public int getMaxSeqNo();    
}