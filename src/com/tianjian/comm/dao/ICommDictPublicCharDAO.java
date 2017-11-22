package com.tianjian.comm.dao;

import java.util.List;

import com.tianjian.comm.bean.CommDictPublicChar;
 

public interface ICommDictPublicCharDAO {
   
	   public CommDictPublicChar findById(String id);
	    
	    public void save(CommDictPublicChar commDictPublicChar);
	    
	    public void update(CommDictPublicChar commDictPublicChar);
	    
	    public void delete(CommDictPublicChar commDictPublicChar);
	    
	    public List<?> getData(String id,String classCode, String dictCode, String dictValue, String inputCode, String seqNo, String orderNo, int curCount,int pageSize);
	    
	    public int getCount(String id, String classCode,String dictCode, String dictValue, String inputCode, String seqNo);
	    
	    public int getMaxSeqNo();  
	    
	    public List<?> findAllByClassCode(String classCode);
	    public CommDictPublicChar findByDictcode(String classCode, String dictCode);
}