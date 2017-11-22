package com.tianjian.comm.business;


import com.tianjian.comm.struts.form.UserSelectForm;


public interface ICommConfigLocationSearchService {
	/**
	 * AJAX
	 * @param form
	 */
	public void getCitys(UserSelectForm form, String staffId);
	/**
	 * AJAX
	 * @param form
	 */
	public void getCounties(UserSelectForm form, String staffId);
	
	/**
	 * AJAX
	 * @param form
	 */
	public void getTowns(UserSelectForm form, String staffId);
	
	/**
	 * AJAX
	 * @param form
	 */
	public void getVillages(UserSelectForm form, String staffId);
	/**
	 * AJAX
	 * @param form
	 */
	public void getGroups(UserSelectForm form);
	
	public void getDict(UserSelectForm form, String staffId);
}
