package com.tianjian.comm.business;

import com.tianjian.comm.bean.CommConfigFtManage;
import com.tianjian.comm.struts.form.CommConfigFtManageForm;

/**
 * 机构分类管理字典
 * @author DZENALL
 */
public interface ICommConfigFtManageService {

	/**
	 * 
	 * @param form
	 */
	void addInit(CommConfigFtManageForm form);

	/**
	 * 
	 * @param form
	 * @return
	 */
	CommConfigFtManage checkData(CommConfigFtManageForm form);

	/**
	 * 
	 * @param form
	 */
	void save(CommConfigFtManageForm form);

	/**
	 * 
	 * @param form
	 * @param currentPageIndex
	 * @param pageSize
	 */
	void getSearch(CommConfigFtManageForm form, int currentPageIndex, int pageSize);

	/**
	 * 
	 * @param form
	 */
	void searchInit(CommConfigFtManageForm form);

	/**
	 * 
	 * @param form
	 * @return
	 */
	int getCount(CommConfigFtManageForm form);

	/**
	 * 
	 * @param form
	 */
	void updateInit(CommConfigFtManageForm form);

	/**
	 * 
	 * @param form
	 */
	void update(CommConfigFtManageForm form);

	/**
	 * 
	 * @param form
	 */
	void delete(CommConfigFtManageForm form);

}