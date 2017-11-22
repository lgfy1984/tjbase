package com.tianjian.hsp.business;

import javax.servlet.http.HttpServletRequest;

import com.tianjian.hsp.struts.form.HspConfigBaseinfoMergeForm;

public interface IHspConfigBaseinfoMergeService {

	public void mgInit(HspConfigBaseinfoMergeForm hsbmForm);

	public int getCount(HspConfigBaseinfoMergeForm hsbmForm);

	public void search(HspConfigBaseinfoMergeForm hsbmForm, int count,
			int pageSize);

	public void init(HspConfigBaseinfoMergeForm hcbmForm,
			HttpServletRequest request, String staffId);

}
