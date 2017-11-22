package com.tianjian.comm.business;

import com.tianjian.comm.bean.CommConfigDrugclass;
import com.tianjian.comm.struts.form.CommConfigDrugclassForm;

public interface ICommConfigDrugclassService {
	
	//++++++++++++++++++++++++++++++++++++++++++++
	public void updateInit(CommConfigDrugclassForm form);
	//++++++++++++++++++++++++++++++++++++++++++++
	public void save(CommConfigDrugclassForm form);
	public void update(CommConfigDrugclassForm form);
	public void delete(CommConfigDrugclassForm form);
	
	public CommConfigDrugclass checkData(CommConfigDrugclassForm form);
	//取得符合查询条件的记录条数
	public int getCount(String itemCode, String itemName, String inputCode);
	//里面有form.setList方法
	public void getSearch(CommConfigDrugclassForm form, int curCount,
			int quChuCount);
	public void getData(CommConfigDrugclassForm form) ;

}
