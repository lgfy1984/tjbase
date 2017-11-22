package com.tianjian.security.business.service;


import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import com.tianjian.comm.bean.CommConfigSex;
import com.tianjian.security.bean.SecurityConfigMenus;
import com.tianjian.security.bean.SecurityConfigPublic;
import com.tianjian.security.bean.SecurityConfigPublicClass;
import com.tianjian.security.bean.SecurityXtSysLog;
import com.tianjian.security.business.ISecurityXtSysLogService;
import com.tianjian.security.dao.ISecurityConfigMenusDAO;
import com.tianjian.security.dao.ISecurityConfigPublicDAO;
import com.tianjian.security.dao.ISecurityXtSysLogDAO;
import com.tianjian.security.struts.form.SecurityConfigMenusForm;
import com.tianjian.security.struts.form.SecurityXtSysLogForm;

public class SecurityXtSysLogServiceImpl implements ISecurityXtSysLogService {
	private static final Logger log = LogManager.getLogger(SecurityXtSysLogServiceImpl.class);
	private ISecurityXtSysLogDAO xtsyslogDAO;

	//------------------------DAO-- 声明结束---------------------------------------------------

	public ISecurityXtSysLogDAO getXtsyslogDAO() {
		return xtsyslogDAO;
	}
	public void setXtsyslogDAO(ISecurityXtSysLogDAO xtsyslogDAO) {
		this.xtsyslogDAO = xtsyslogDAO;
	}
	
	/**提交察看详细*/
	public void showInit(SecurityXtSysLogForm form){
		SecurityXtSysLog data = this.getXtsyslogDAO().findById(form.getIdHidden());
		this.setForm(form,data);
	}
	/**获取总记录数*/
	public int getCount(SecurityXtSysLogForm form){
		return this.getXtsyslogDAO().getCount(form.getLogLevel(),form.getLogMessage(),form.getStartTime(),form.getEndTime());
	}
	/**根据查询条件获取当前显示页的数据*/
	public void getSearch(SecurityXtSysLogForm form, int curCount, int pageSize){
		try{
			String order = "";
			if(form.getOrderNo().equals("1")){
				order = " a.logTime";
			} else if(form.getOrderNo().equals("2")){
				order = " a.logLevel";
			} else if(form.getOrderNo().equals("3")){
				order = " a.logAction";
			} else if(form.getOrderNo().equals("4")){
				order = " a.logMessage";
			} else {
				order = " a.logTime";
			}
			
			if(form.getAsc().equals("1")){
				order += " desc";
			} else {
				order += " asc";
			}
		 
			List<?> list = this.getXtsyslogDAO().getData(form.getLogLevel(),form.getLogMessage(),form.getStartTime(),form.getEndTime()
									, order
									, curCount
									, pageSize);
			 
			if(list != null && list.size() > 0){
				String[] idList = new String[list.size()];
				String[] logTimeList = new String[list.size()];
				String[] logLevelList = new String[list.size()];
				String[] logActionList = new String[list.size()];
				String[] logMessageList = new String[list.size()];
				for(int i = 0; i < list.size(); i++){
					SecurityXtSysLog log = (SecurityXtSysLog)list.get(i);
					idList[i] = transNullToString(log.getId());
					if(log.getLogTime() != null){
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						logTimeList[i] = transNullToString(format.format(log.getLogTime()));
					}
					
					logLevelList[i] = transNullToString(log.getLogLevel());
					logActionList[i] = transNullToString(log.getLogAction());
					if(log.getLogMessage()!=null && log.getLogMessage().length()>30){
						logMessageList[i] = this.transNullToString(log.getLogMessage().substring(0, 30)+"....");
					}else{
						logMessageList[i] = this.transNullToString(log.getLogMessage());
					}
					
					 
				}
				form.setIdList(idList);
				form.setLogTimeList(logTimeList);
				form.setLogLevelList(logLevelList);
				form.setLogActionList(logActionList);
				form.setLogMessageList(logMessageList);
			}
		 } catch (Exception e) { 
			 log.error("getSearch fail!",e);
			 e.printStackTrace();
	} 
	
	}
	/**
	 * 去掉字串中的空格
	 * */
	public String transNullToString(Object obj){
		String temp = "";
		if (obj != null){
			temp = ((String)obj).trim();			
		}
		return temp;
	}
	
	public String transNullToZero(Object obj){
		String temp = "0";
		if (obj != null){
			temp = ((String)obj).trim();			
		}
		return temp;
	}
	/**在传递数据到页面之前构造form*/
	private void setForm(SecurityXtSysLogForm form,SecurityXtSysLog data){
		try{
			form.setId(transNullToString(data.getId()));
			if(data.getLogTime() != null){
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				form.setLogTime(format.format(data.getLogTime()));
			}
			form.setLogLevel(transNullToString(data.getLogLevel()));			
			form.setLogAction(transNullToString(data.getLogAction()));
			form.setLogMessage(transNullToString(data.getLogMessage()));
		 } catch (Exception e) { 
			 log.error("setForm fail!",e);
			 e.printStackTrace();
		} 
	}
}
