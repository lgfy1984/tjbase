package com.tianjian.comm.dao;

import java.util.List;
import com.tianjian.comm.bean.CommDictPublicClass;
 

public interface ICommDictPublicClassDAO {
   
    public CommDictPublicClass findById(String id);
    
    public List<?> findAll();
    
	public String getClassName(String id);
	
    public void save(CommDictPublicClass commDictPublicClass);
    
    public void update(CommDictPublicClass commDictPublicClass);
    
    public void delete(CommDictPublicClass commDictPublicClass);
    
    public List<?> getData(String classCode, String className, String inputCode, String seqNo, String orderNo, int curCount,int pageSize);
    
    public int getCount(String classCode, String className, String inputCode, String seqNo);
    
    public int getMaxSeqNo();   
    
    public List<?> getClassComm(String falg, String input, int count, int pageSize);
}