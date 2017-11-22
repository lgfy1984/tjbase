package com.tianjian.comm.dao;

import java.util.List;

import com.tianjian.comm.bean.CommCongihMidwifery;
import com.tianjian.comm.struts.form.CommCongihMidwiferyForm;

public interface ICommCongihMidwiferyDAO {
	
	/**获得总的个数*/
	public int getCount(CommCongihMidwiferyForm form);
	/**
	 * 获得需求的数据
	 * @param form CommCongihMidwiferyForm
	 * @param count 读取数据的起始位置
	 * @param pageSize 一次读取数据的个数
	 * @return List
	 */
	public List<?> getSearch(CommCongihMidwiferyForm form,int count,int pageSize);
	/**保存*/
	public void add(CommCongihMidwifery com);
	/**删除*/
	public void delete(CommCongihMidwifery com);
	/**
	 * 获得CommCongihMidwifery
	 * @param hspConfigBaseinfoId 机构ID
	 * @param hspCalssCode 机构类别代码
	 * @return
	 */
	public CommCongihMidwifery findByIds(String hspConfigBaseinfoId,String hspCalssCode);
	/**
	 * 获得某一字段的最大值
	 * @param tableName 表名
	 * @param columnName 字段名
	 * @return
	 */
	public int getMaxNumber(String tableName,String columnName);
}
