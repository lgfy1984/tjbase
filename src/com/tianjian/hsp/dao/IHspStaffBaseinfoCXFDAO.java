package com.tianjian.hsp.dao;

import java.util.List;

import com.tianjian.hsp.bean.BaseMessage;
import com.tianjian.hsp.bean.HspStaffBaseinfo;
import com.tianjian.hsp.bean.HspStaffLogoutRecord;

public interface IHspStaffBaseinfoCXFDAO {
	public String findIdNo(String idNo) throws Exception;
	public boolean save(Object obj) throws Exception;
	public List<?> queryByCondition(BaseMessage baseMessage, HspStaffBaseinfo hsb);
	public HspStaffBaseinfo findById(String id);
	public void merge(HspStaffBaseinfo hsb);
	public void delete(HspStaffBaseinfo hsb);
}
