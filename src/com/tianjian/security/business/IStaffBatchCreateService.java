package com.tianjian.security.business;

import java.util.List;

import com.tianjian.security.struts.form.StaffBatchCreateForm;

public interface IStaffBatchCreateService {

	public List<?> list(StaffBatchCreateForm sbcForm);

	public int getCount(StaffBatchCreateForm sbcForm);
	
	public void createBySelectedHspIds(StaffBatchCreateForm sbcForm, String defaultPassword);

	public boolean createAll(StaffBatchCreateForm sbcForm, String defaultPassword);
}
