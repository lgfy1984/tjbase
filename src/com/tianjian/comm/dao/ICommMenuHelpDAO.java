package com.tianjian.comm.dao;

import java.util.List;
import com.tianjian.comm.bean.CommMenuHelp;
 

public interface ICommMenuHelpDAO {
	
	public CommMenuHelp findByMenuFlag(String menuFlag);
   
    public CommMenuHelp findById(String id);
    
    public List<?> findAll();
    
	public String getItemName(String id);
	
    public void save(Object obj);
    
    public void update(Object obj);
    
    public void delete(Object obj);
    
    public List<?> getData(String menuName, String menuHelpTitle, String createDate,String seqNo, String orderNo, int curCount,int pageSize);
    
    public int getCount(String menuName, String menuHelpTitle, String createDate,String seqNo);
    
    public Long seqNoMaker(String nameOfTable);
    
	  
}