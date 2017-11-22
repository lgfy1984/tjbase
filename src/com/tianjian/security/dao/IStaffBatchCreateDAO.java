package com.tianjian.security.dao;

import java.util.List;

import com.tianjian.security.struts.form.StaffBatchCreateForm;

public interface IStaffBatchCreateDAO {

	public List<?> list(StaffBatchCreateForm sbcForm);

	public int getCount(StaffBatchCreateForm sbcForm);

	public int createBySelectedHspIds(String[] selectedHspIds, String defaultPassword);

	public int createAll(StaffBatchCreateForm sbcForm, String defaultPassword);

}
