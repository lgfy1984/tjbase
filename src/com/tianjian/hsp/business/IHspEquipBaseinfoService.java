package com.tianjian.hsp.business;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.tianjian.hsp.struts.form.HspEquipBaseinfoForm;

public interface IHspEquipBaseinfoService {

	void findEquipList(HspEquipBaseinfoForm hebForm, int curCount,int count);
	
	int getCount(HspEquipBaseinfoForm hebForm);
	
	/**初始化字典*/
	public void init(HspEquipBaseinfoForm form);
	/**增加之前初始化*/
	public void saveInit(HspEquipBaseinfoForm form);
	/**保存*/
	public void save(HspEquipBaseinfoForm form);
	/**更新之前初始化*/
	public void updateInit(HspEquipBaseinfoForm form);
	/**更新*/
	public void update(HspEquipBaseinfoForm form);
	/**删除*/
	public void delete(HspEquipBaseinfoForm form);

	void getDept(HspEquipBaseinfoForm hosform, String orgId);

	String elsImport(InputStream inputstream, HttpServletRequest request);

	HSSFWorkbook getExcel(HspEquipBaseinfoForm hosform, String fileName,
			HttpServletRequest request);

	public void getDetail(HspEquipBaseinfoForm hosform);	

}
