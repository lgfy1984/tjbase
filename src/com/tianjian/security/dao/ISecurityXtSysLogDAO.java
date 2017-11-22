package com.tianjian.security.dao;

import java.util.List;

import com.tianjian.security.bean.SecurityXtSysLog;

/**
 * DAO接口
 * @author ch_f001
 * @since 2008-3-24 15:39
 */
public interface ISecurityXtSysLogDAO {
	
	public SecurityXtSysLog findById(String Id);
	

	public List<?> getData(String logLevel,String logMessage,String startTime, String endTime, String orderNo, int curCount, int pageSize);

	public int getCount(String logLevel,String logMessage,String startTime, String endTime);
	
}
