package com.tianjian.comm.dao;

import java.util.List;
import com.tianjian.comm.bean.CommIHEAuthority;
 

public interface ICommIHEAuthorityDAO {
   
    public CommIHEAuthority findById(String id);
    
    public List<?> findAll();
    
//	public String getItemName(String id);
	
    public void save(CommIHEAuthority commConfigAbo);
    
    public void update(CommIHEAuthority commConfigAbo);
    
    public void delete(CommIHEAuthority commConfigAbo);
    
    public List<?> getData(String universalId, String universalIdType, String namespaceId, String seqNo, String orderNo, int curCount,int pageSize);
    
    public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
    
    
    public Long seqNoMaker(String nameOfTable);
	  
}