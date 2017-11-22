package com.tianjian.comm.dao;

import java.util.List;

import com.tianjian.comm.bean.CommDictPublicLevel;
 

public interface ICommDictPublicLevelDAO {
   
	    public CommDictPublicLevel findById(String id);
	    public String getItemName(String id);
	    public List<?> findAll();
	    public List<?> findAllByTableCode(String tableCode);
	    public void save(CommDictPublicLevel commDictPublicLevel);
	    
	    public void update(CommDictPublicLevel commDictPublicLevel);
	    
	    public void delete(CommDictPublicLevel commDictPublicLevel);
	    
	    public List<?> getData(String itemCode,String itemName, String inputCode,String parentItemCode, String classLevel,  String seqInLevel, String tableCode,String orderNo, int curCount,int pageSize);
	    
	    public int getCount(String itemCode,String itemName, String inputCode,String parentItemCode, String classLevel,  String seqInLevel, String tableCode);
	    
	    public int getMaxSeqNo();  
}