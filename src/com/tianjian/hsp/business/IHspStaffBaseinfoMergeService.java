package com.tianjian.hsp.business;

import com.tianjian.hsp.struts.form.HspStaffBaseinfoMergeForm;

public interface IHspStaffBaseinfoMergeService {
	
	public void search(HspStaffBaseinfoMergeForm hsbmForm,int curCount, int pageSize);
	
	public int getCount(HspStaffBaseinfoMergeForm hsbmForm);

	public void mgInit(HspStaffBaseinfoMergeForm hsbmForm);

	public void init(HspStaffBaseinfoMergeForm hsbmForm);

}
