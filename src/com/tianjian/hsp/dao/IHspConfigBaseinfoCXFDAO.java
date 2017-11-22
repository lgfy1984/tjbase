package com.tianjian.hsp.dao;

import java.util.List;

import com.tianjian.hsp.bean.BaseMessage;
import com.tianjian.hsp.bean.HspConfigBaseinfo;
import com.tianjian.hsp.bean.HspLogoutRecord;
import com.tianjian.hsp.bean.HspMergeLog;

public interface IHspConfigBaseinfoCXFDAO {
	public String findItemCode(String itemCode);
	public boolean save(Object obj);
	public void merge(HspConfigBaseinfo hcb) ;
	public List<?> queryByCondition(BaseMessage baseMessage, HspConfigBaseinfo user);
	public HspConfigBaseinfo findById(String id);
	public void delete(HspConfigBaseinfo hcb);
}
