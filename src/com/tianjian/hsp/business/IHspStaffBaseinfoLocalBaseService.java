package com.tianjian.hsp.business;

import javax.servlet.http.HttpServletRequest;

import com.tianjian.hsp.struts.form.HspStaffBaseinfoLocalBaseForm;

public interface IHspStaffBaseinfoLocalBaseService {

	public int getCount(String hspConfigBaseinfoIdQuery, String nameQuery,
			String idNoQuery, String staffId, String hspConfigBaseinfoId);

	public void getSearch(HspStaffBaseinfoLocalBaseForm hosform, int count,
			int pageSize, HttpServletRequest request);

}
