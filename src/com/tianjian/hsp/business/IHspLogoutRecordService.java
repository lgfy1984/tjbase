package com.tianjian.hsp.business;

import com.tianjian.hsp.struts.form.HspConfigBaseinfoForm;

public interface IHspLogoutRecordService {
	/**更新之前初始化  注销表*/
	public void updateInit(HspConfigBaseinfoForm hcbForm);
	/**更新 注销表*/
	public void update(HspConfigBaseinfoForm hcbForm);
	/**显示之前初始化 注销表*/
	public void showInit(HspConfigBaseinfoForm hcbForm);
	/**删除 注销表*/
	public void delete(HspConfigBaseinfoForm hcbForm);	
	/**获取总记录数 注销表*/
	public int getCount(String hspConfigBaseinfoId, String itemCode,String itemName);
	/**获取当页记录 注销表*/
	public void getSearch(HspConfigBaseinfoForm hcbForm, int curCount, int pageSize);
	/**serchInit 注销表*/
	public void serchInit(HspConfigBaseinfoForm hcbForm);
	/**getDetail 注销表*/
	public void getDetail(HspConfigBaseinfoForm hcbForm);
	/**保存 注销表*/
	public void save(HspConfigBaseinfoForm hcbForm);
}
