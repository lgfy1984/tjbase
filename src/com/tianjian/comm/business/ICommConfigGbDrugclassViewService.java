package com.tianjian.comm.business;

import com.tianjian.comm.bean.CommConfigGbDrugclass;
import com.tianjian.comm.struts.form.CommConfigGbDrugclassForm;

public interface ICommConfigGbDrugclassViewService {
	public void updateInit(CommConfigGbDrugclassForm form);
	//++++++++++++++++++++++++++++++++++++++++++++
	public void save(CommConfigGbDrugclassForm form);
	public void update(CommConfigGbDrugclassForm form);
	public void delete(CommConfigGbDrugclassForm form);

	public CommConfigGbDrugclass checkData(CommConfigGbDrugclassForm form);
	//取得符合查询条件的记录条数
	public int getCount(String itemCode, String itemName, String inputCode);
	//里面有form.setList方法
	public void getSearch(CommConfigGbDrugclassForm form, int curCount,
			int quChuCount);
	public void getData(CommConfigGbDrugclassForm form) ;

}
