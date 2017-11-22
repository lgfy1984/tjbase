package com.tianjian.comm.business;

import com.tianjian.comm.bean.CommConfigDrugunits;
import com.tianjian.comm.struts.form.CommConfigDrugunitsForm;

public interface ICommConfigDrugunitsService {
	//++++++++++++++++++++++++++++++++++++++++++++
	public void updateInit(CommConfigDrugunitsForm form);
	//++++++++++++++++++++++++++++++++++++++++++++
	public void save(CommConfigDrugunitsForm form);
	//++++++++++++++++++++++++++++++++++++++++++++
	public void update(CommConfigDrugunitsForm form);
	//++++++++++++++++++++++++++++++++++++++++++++
	public void delete(CommConfigDrugunitsForm form);
	
	public CommConfigDrugunits checkData(CommConfigDrugunitsForm form);
	//取得符合查询条件的记录条数
	public int getCount(String itemCode, String itemName, String inputCode);
	//里面有form.setList方法
	public void getSearch(CommConfigDrugunitsForm form, int curCount,
			int quChuCount);
}
