package com.tianjian.hsp.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tianjian.hsp.struts.form.HspStaffBaseinfoLocalBaseForm;

public interface IHspStaffBaseinfoLocalBaseDAO {
	
	public int getCount(String hspConfigBaseinfoIdQuery, String nameQuery,
			String idNoQuery, String staffId, String hspConfigBaseinfoId);
	public List<?> getSearch(HspStaffBaseinfoLocalBaseForm hosform, int count,
			int pageSize, HttpServletRequest request);
	
}
