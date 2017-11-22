package com.tianjian.hsp.dao;

import java.util.List;

import com.tianjian.hsp.bean.BaseMessage;
import com.tianjian.hsp.bean.HspEquipBaseinfo;

public interface IHspEquipBaseinfoCXFDAO {
	public boolean save(Object obj) throws Exception;
	public HspEquipBaseinfo findById(String id);
	public void merge(HspEquipBaseinfo heb);
	public void delete(HspEquipBaseinfo heb);
	public List<?> queryByCondition(BaseMessage baseMessage, String hspCode, String hspName, String deptCode, String deptName,
			String equipName, String usage);
}
