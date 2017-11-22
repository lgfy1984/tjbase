package com.tianjian.hsp.dao;

import java.util.List;

import com.tianjian.hsp.bean.HspStaffBaseinfo;
import com.tianjian.hsp.bean.HspStaffIdList;
import com.tianjian.hsp.bean.HspStaffMergeLog;

public interface IHspStaffBaseinfoMergeDAO {

	public int getCount(String id,String name, String commConfigSexId, String birthday,
			String idNo, String checkValue);

	public List<?> search(String id,String name, String commConfigSexId, String birthday,
			String idNo, String checkValue, String order, int curCount,
			int pageSize);

	public String querySexNameById(String commConfigSexId);
	
	public List<?> sexList();
	
	public void saveLog(HspStaffMergeLog vo);
	
	public HspStaffBaseinfo getVoById(String id);
	
	public HspStaffIdList getHspStaffIdListById(String id);
	
	public void updataHspStaffIdList(HspStaffIdList vo);

}
