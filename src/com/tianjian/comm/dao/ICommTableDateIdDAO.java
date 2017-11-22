package com.tianjian.comm.dao;

import java.util.Date;
import java.util.List;
import com.tianjian.comm.bean.CommTableDateId;


public interface ICommTableDateIdDAO {
    
	public CommTableDateId getByColumn(String tableName, String columnName, Date date);
    
	public List<?> getTableColumn(String tableName, String columnName);
    
    public void save(CommTableDateId commTableDateId);
    
    public void update(CommTableDateId commTableDateId);
    
    public void delete(CommTableDateId commTableDateId);
    
    public long getMaxId();
    
}