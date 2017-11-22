package com.tianjian.hsp.business;

import com.tianjian.hsp.struts.form.HspStaffBaseinfoForm;

public interface IHspStaffLogoutRecordService {
	/**更新之前初始化  注销表*/
	public void updateInit(HspStaffBaseinfoForm hspStaffBaseinfoForm);
	/**更新 注销表*/
	public void update(HspStaffBaseinfoForm hspStaffBaseinfoForm);
	/**显示之前初始化 注销表*/
	public void showInit(HspStaffBaseinfoForm hspStaffBaseinfoForm);
	/**删除 注销表*/
	public void delete(HspStaffBaseinfoForm hspStaffBaseinfoForm);	
	/**获取总记录数 注销表*/
	public int getCount(String hspStaffBaseinfoId, String empNo, String name,String idNo);
	/**获取当页记录 注销表*/
	public void getSearch(HspStaffBaseinfoForm hForm, int curCount, int pageSize);
	/**serchInit 注销表*/
	public void serchInit(HspStaffBaseinfoForm hspStaffBaseinfoForm);
	/**getDetail 注销表*/
	public void getDetail(HspStaffBaseinfoForm hspStaffBaseinfoForm);
	/**保存 注销表*/
	public void save(HspStaffBaseinfoForm hspStaffBaseinfoForm);
}
