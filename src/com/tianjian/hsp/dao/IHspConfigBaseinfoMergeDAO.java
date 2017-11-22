package com.tianjian.hsp.dao;

import java.util.List;

import com.tianjian.hsp.bean.HspConfigBaseinfo;
import com.tianjian.hsp.bean.HspIdList;
import com.tianjian.hsp.bean.HspMergeLog;

public interface IHspConfigBaseinfoMergeDAO {

	public HspConfigBaseinfo getVoById(String oldId);

	public void saveLog(HspMergeLog vo);
	
	public List<?> init(String staffId);

	public List<?> search(String id,String itemName, String commConfigLocationId1,
			String commConfigLocationId2, String commConfigLocationId3,
			String inputCode, String checkValue, String order, int count,
			int pageSize);

	public int getCount(String id,String itemName, String commConfigLocationId1,
			String commConfigLocationId2, String commConfigLocationId3,
			String inputCode, String checkValue);
	
    public HspIdList getHspIdListById(String id);
	
	public void updataHspIdList(HspIdList vo);

}
