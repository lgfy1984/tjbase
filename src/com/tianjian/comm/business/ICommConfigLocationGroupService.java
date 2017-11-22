package com.tianjian.comm.business;

import javax.servlet.http.HttpServletRequest;

import com.tianjian.comm.bean.CommConfigLocationGroup;
import com.tianjian.comm.struts.form.CommConfigLocationGroupForm;
/**
 * COMM_CONFIG_LOCATION_GROUP居民组字典Service接口
 * @author Dzenall
 * @since 2008-9-18
 */
public interface ICommConfigLocationGroupService {

	void addInit(CommConfigLocationGroupForm form);

	/**
	 * 填充基本字段到form中
	 * @param form
	 * @param data
	 */
	void setForm(CommConfigLocationGroupForm form, CommConfigLocationGroup data);

	/**
	 * 根据itemCode在CommConfigLocationGroup表中查找记录
	 * @param itemCode
	 * @return CommConfigLocationGroup/null
	 */
	CommConfigLocationGroup checkData(String itemCode);

	void add(CommConfigLocationGroupForm form);

	int getCount(String itemCode, String itemName, String inputCode);

	void getSearch(CommConfigLocationGroupForm form, int count, int pageSize);

	/**
	 * 删除（依据字段itemCode，系统中该字段唯一）
	 * @param form
	 */
	void delete(CommConfigLocationGroupForm form, HttpServletRequest request);

	/**
	 * 更新前的初始化工作（依据字段itemCode查找要更新的记录，系统中该字段唯一）
	 * @param form
	 */
	void updateInit(CommConfigLocationGroupForm form);

	/**
	 * 根据form中的字段内容尽量完善其中的字段，主要是在现实详细信息时使用该方法
	 * @param form
	 */
	void getDetail(CommConfigLocationGroupForm form);

	void update(CommConfigLocationGroupForm form, HttpServletRequest request);

	/**
	 * AJAX
	 * @param form
	 */
	void getCitys(CommConfigLocationGroupForm form);
	
	/**
	 * AJAX
	 * @param form
	 */
	void getCounties(CommConfigLocationGroupForm form);
	
	/**
	 * AJAX
	 * @param form
	 */
	void getTowns(CommConfigLocationGroupForm form);

	/**
	 * AJAX
	 * @param form
	 */
	void getVillages(CommConfigLocationGroupForm form);
	
	//定义行政组编号
	public String getItemCode(CommConfigLocationGroupForm form);
	//得到行政村编号
	public String getVillageItemCode(CommConfigLocationGroupForm form);
}
