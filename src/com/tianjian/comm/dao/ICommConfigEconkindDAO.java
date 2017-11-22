package com.tianjian.comm.dao;

import java.util.List;
import com.tianjian.comm.bean.CommConfigEconkind;
 

public interface ICommConfigEconkindDAO {
   
    public CommConfigEconkind findById(String id);
    
    public List<?> findAll();
    
	public String getItemName(String id);
	
    public void save(CommConfigEconkind commConfigEconkind);
    
    public void update(CommConfigEconkind commConfigEconkind);
    
    public void delete(CommConfigEconkind commConfigEconkind);
    
    public List<?> getData(String itemCode, String itemName, String inputCode, String seqNo, String orderNo, int curCount,int pageSize);
    
    public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
    
    public int getMaxSeqNo();    
}