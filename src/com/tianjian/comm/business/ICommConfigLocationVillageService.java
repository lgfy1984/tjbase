package com.tianjian.comm.business;

import javax.servlet.http.HttpServletRequest;

import com.tianjian.comm.bean.CommConfigLocationVillage;
import com.tianjian.comm.struts.form.CommConfigLocationVillageForm;
/**
 * COMM_CONFIG_LOCATION_VILLAGE村字典用Service
 * @author Dzenall
 * @since 2008-9-18
 */
public interface ICommConfigLocationVillageService {

	void addInit(CommConfigLocationVillageForm form);

	void setForm(CommConfigLocationVillageForm form, CommConfigLocationVillage data);
	void setForm2(CommConfigLocationVillageForm form);
	/**��
	 * 根据itemCode在CommConfigLocationVillage中查找记录
	 * @param itemCode
	 * @return CommConfigLocationVillage/null
	 */
	CommConfigLocationVillage checkData(String itemCode);

	void add(CommConfigLocationVillageForm form, HttpServletRequest request);

	int getCount(String itemCode, String itemName, String inputCode);

	void getSearch(CommConfigLocationVillageForm form, int curCount, int pageSize);

	/**
	 * ɾ��CommConfigLocationVillage���¼���>��ֶ�form.idHidden��
	 * @param form
	 */
	void delete(CommConfigLocationVillageForm form, HttpServletRequest request);

	/**
	 * ����CommConfigLocationVillage���¼���>��ֶ�form.idHidden��
	 * @param form
	 */
	void updateInit(CommConfigLocationVillageForm form);

	/**
	 * ���form�е��ֶΣ�������form�е������ֶΡ���Ҫ��Բ鿴��ϸ����ʱ����
	 * @param form
	 */
	void getDetail(CommConfigLocationVillageForm form);

	/**
	 * ���¼�¼���>��ֶ�Ϊform.idHidden��
	 * @param form
	 */
	void update(CommConfigLocationVillageForm form);

	/**
	 * Ajax������form.commCityIds��form.commCityNames�����form.commProvinceId��
	 * @param form
	 */
	void getCitys(CommConfigLocationVillageForm form);
	/**
	 * Ajax������form.commCountyIds��form.commCountyNames�����form.commCityId��
	 * @param form
	 */
	void getCounties(CommConfigLocationVillageForm form);
	/**
	 * Ajax��������form.commCltIds��form.commCltNames�����form.commCountyId��
	 * @param form
	 */
	void getTowns(CommConfigLocationVillageForm form);
	//定义行政村编号
	public String getItemCode(CommConfigLocationVillageForm form);
	//得到乡镇编号
	public String getTownItemCode(CommConfigLocationVillageForm form);
}
