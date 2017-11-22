package com.tianjian.comm.dao;

import java.util.List;

import com.tianjian.comm.bean.CommDictPublicInt;
 

public interface ICommDictPublicIntDAO {
   
	 public CommDictPublicInt findById(String id);
	    
	    public void save(CommDictPublicInt commDictPublicInt);
	    
	    public void update(CommDictPublicInt commDictPublicInt);
	    
	    public void delete(CommDictPublicInt commDictPublicInt);
	    
	    public List<?> getData(String id,String classCode, String dictCode, String dictValue, String inputCode, String seqNo, String orderNo, int curCount,int pageSize);
	    
	    public int getCount(String id, String classCode,String dictCode, String dictValue, String inputCode, String seqNo);
	    
	    public int getMaxSeqNo();  
}