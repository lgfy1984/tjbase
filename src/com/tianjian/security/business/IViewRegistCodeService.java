package com.tianjian.security.business;

import com.tianjian.security.struts.form.ViewRegistCodeForm;
/**
 * 医疗人员查询注册码用Service接口
 * @author DZENALL
 */
public interface IViewRegistCodeService {

	/**
	 * 根据参数类中的具体数据在查询出需要的数据库数据后，更新参数类中的数据
	 * @param form
	 */
	public void viewQuery(ViewRegistCodeForm form);
}
