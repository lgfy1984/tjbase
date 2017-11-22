package com.tianjian.comm.business;

import javax.servlet.http.HttpServletRequest;

import com.tianjian.comm.bean.CommCongihMidwifery;
import com.tianjian.comm.struts.form.CommCongihMidwiferyForm;

public interface ICommCongihMidwiferyService {
	/**获得xml，用于弹出层*/
	public String getXml(String flag, String inputCode,HttpServletRequest request);
	/**获得总的个数*/
	public int getCount(CommCongihMidwiferyForm form);
	/**
	 * 获得需求的数据
	 * @param form CommCongihMidwiferyForm
	 * @param count 读取数据的起始位置
	 * @param pageSize 一次读取数据的个数
	 * @return
	 */
	public void getSearch(CommCongihMidwiferyForm form,int count,int pageSize);
	/**保存*/
	public void save(CommCongihMidwiferyForm form);
	/**删除*/
	public void delete(CommCongihMidwiferyForm form);
	/**通过条件获得CommCongihMidwifery*/
	public CommCongihMidwifery getByIds(CommCongihMidwiferyForm form);
	/**
	 * 获得某一字段的最大值
	 * @param tableName 表名
	 * @param columnName 字段名
	 * @return
	 */
	public int getMaxNumber(String tableName,String columnName);
}
