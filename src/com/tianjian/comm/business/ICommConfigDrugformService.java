package com.tianjian.comm.business;

import com.tianjian.comm.bean.CommConfigDrugform;
import com.tianjian.comm.struts.form.CommConfigDrugformForm;

public interface ICommConfigDrugformService {
	//++++++++++++++++++++++++++++++++++++++++++++
	public void updateInit(CommConfigDrugformForm form);
	//++++++++++++++++++++++++++++++++++++++++++++
	public void save(CommConfigDrugformForm form);
	public void update(CommConfigDrugformForm form);
	public void delete(CommConfigDrugformForm form);
	
	public CommConfigDrugform checkData(CommConfigDrugformForm form);
	//取得符合查询条件的记录条数
	public int getCount(String itemCode, String itemName, String inputCode);
	//里面有form.setList方法
	public void getSearch(CommConfigDrugformForm form, int curCount,
			int quChuCount);
}
