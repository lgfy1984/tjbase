package com.tianjian.comm.dao;

import java.util.List;

import com.tianjian.comm.bean.CommDictPublicDouble;
 

public interface ICommDictPublicDoubleDAO {
   
	 public CommDictPublicDouble findById(String id);
	    
	    public void save(CommDictPublicDouble commDictPublicDouble);
	    
	    public void update(CommDictPublicDouble commDictPublicDouble);
	    
	    public void delete(CommDictPublicDouble commDictPublicDouble);
	    
	    public List<?> getData(String id,String classCode, String dictCode, String dictValue, String inputCode, String seqNo, String orderNo, int curCount,int pageSize);
	    
	    public int getCount(String id, String classCode,String dictCode, String dictValue, String inputCode, String seqNo);
	    
	    public int getMaxSeqNo();  
}