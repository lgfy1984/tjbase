package com.tianjian.security.struts.form;

import org.apache.struts.action.ActionForm;


/**
 * 
 * @author ch_f001
 *
 */

public final class SecurityXtSysLogForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3694002123962919890L;
	// ---------------QueryForm部分内容-------------------------------------------------------
	private String id;
	private String logTime;
	private String logLevel;
    private String logAction;
    private String logMessage;
    
    
    private String startTime;
    private String endTime;
	// ---------实体类处理--------------------------------------------------
	// -------------公共处理部分--------------------------------------------
	private String verbId  = "";
	private String message = "";
	private String idHidden=""; //供修改.删除使用
	private String orderNo="";
	private String asc="";
	
	private String[] idList;
	private String[] logTimeList;
	private String[] logLevelList;
	private String[] logActionList;
	private String[] logMessageList;

	public SecurityXtSysLogForm() {
		super();
		this.id="";
		this.logTime="";
		this.logLevel="";
	    this.logAction="";
	    this.logMessage="";
		this.orderNo = "";
		this.startTime="";
		this.endTime="";
		this.asc="";
		this.message="";
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogTime() {
		return logTime;
	}

	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}

	public String getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}

	public String getLogAction() {
		return logAction;
	}

	public void setLogAction(String logAction) {
		this.logAction = logAction;
	}

	public String getLogMessage() {
		return logMessage;
	}

	public void setLogMessage(String logMessage) {
		this.logMessage = logMessage;
	}

	public String getVerbId() {
		return verbId;
	}

	public void setVerbId(String verbId) {
		this.verbId = verbId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getIdHidden() {
		return idHidden;
	}

	public void setIdHidden(String idHidden) {
		this.idHidden = idHidden;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getAsc() {
		return asc;
	}

	public void setAsc(String asc) {
		this.asc = asc;
	}

	public String[] getIdList() {
		return idList;
	}

	public void setIdList(String[] idList) {
		this.idList = idList;
	}

	public String[] getLogTimeList() {
		return logTimeList;
	}

	public void setLogTimeList(String[] logTimeList) {
		this.logTimeList = logTimeList;
	}

	public String[] getLogLevelList() {
		return logLevelList;
	}

	public void setLogLevelList(String[] logLevelList) {
		this.logLevelList = logLevelList;
	}

	public String[] getLogActionList() {
		return logActionList;
	}

	public void setLogActionList(String[] logActionList) {
		this.logActionList = logActionList;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String[] getLogMessageList() {
		return logMessageList;
	}

	public void setLogMessageList(String[] logMessageList) {
		this.logMessageList = logMessageList;
	}
	
}
