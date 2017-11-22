package com.tianjian.security.business.service;

import java.util.List;

import com.tianjian.security.business.IStaffBatchCreateService;
import com.tianjian.security.dao.IStaffBatchCreateDAO;
import com.tianjian.security.struts.form.StaffBatchCreateForm;

public class StaffBatchCreateServiceImpl implements IStaffBatchCreateService{
	private IStaffBatchCreateDAO staffBatchCreateDAO;

	public IStaffBatchCreateDAO getStaffBatchCreateDAO() {
		return staffBatchCreateDAO;
	}

	public void setStaffBatchCreateDAO(IStaffBatchCreateDAO staffBatchCreateDAO) {
		this.staffBatchCreateDAO = staffBatchCreateDAO;
	}

	@Override
	public List<?> list(StaffBatchCreateForm sbcForm) {
		return this.staffBatchCreateDAO.list(sbcForm);
	}

	@Override
	public int getCount(StaffBatchCreateForm sbcForm) {
		return this.staffBatchCreateDAO.getCount(sbcForm);
	}

	@Override
	public void createBySelectedHspIds(StaffBatchCreateForm sbcForm, String defaultPassword) {
		String[] selectedHspIds = sbcForm.getSelectedHspIds();
		if(selectedHspIds == null){
			sbcForm.setMessage("共为0所机构创建了操作员!");
			return;
		}
		int createCount = this.staffBatchCreateDAO.createBySelectedHspIds(selectedHspIds, defaultPassword);
		StringBuilder sb = new StringBuilder("共为").append(createCount).append("所机构创建了操作员!");
		if(selectedHspIds.length > createCount){
			sb.append("有").append(selectedHspIds.length - createCount).append("所机构的机构代码被其他操作员使用!");
		}
		sbcForm.setMessage(sb.toString());
	}

	@Override
	public boolean createAll(StaffBatchCreateForm sbcForm, String defaultPassword) {
		boolean isAllCreate = false;
		int allCount = this.getCount(sbcForm);
		int createCount = this.staffBatchCreateDAO.createAll(sbcForm, defaultPassword);
		StringBuilder sb = new StringBuilder("共为").append(createCount).append("所机构创建了操作员!");
		if(allCount > createCount){
			sb.append("有").append(allCount - createCount).append("所机构的代码被其他操作员占用");
			isAllCreate = false;
		}else{
			isAllCreate = true;
		}
		sbcForm.setMessage(sb.toString());
		return isAllCreate;
	}
	

}
