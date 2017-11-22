package com.tianjian.comm.business;

import javax.servlet.http.HttpServletRequest;

import com.tianjian.comm.bean.CommConfigLocationTown;
import com.tianjian.comm.struts.form.CommConfigLocationTownForm;
/**
 * COMM_CONFIG_LOCATION_TOWN�����ֵ���Service�ӿ�
 * @author Dzenall
 * @since 2008-9-17
 */
public interface ICommConfigLocationTownService {

	/**
	 * ���itemCode��CommConfigLocationTown���в�ѯ�Ƿ��Ѵ���
	 * @param itemCode
	 * @return CommConfigLocationTown/null
	 */
	CommConfigLocationTown checkData(String itemCode);
	CommConfigLocationTown checkDataById(String id);

	/**
	 * ת������ҳ��ʱ���ʼ������
	 * @param form
	 */
	void addInit(CommConfigLocationTownForm form);

	/**
	 * ����
	 * @param form
	 */
	void add(CommConfigLocationTownForm form, HttpServletRequest request);

	/**
	 * ���data�е����ݾ����ܵ����form
	 * @param form
	 * @param data
	 */
	void setForm(CommConfigLocationTownForm form, CommConfigLocationTown data);

	/**
	 * ���form�����ݣ���Ҫ��idHidden�ֶε�ֵ��ɾ��ĳ���¼
	 * @param form
	 */
	void delete(CommConfigLocationTownForm form, HttpServletRequest request);

	/**
	 * ������е����Ԫ�أ���ѯ�����������м�¼������
	 * @param itemCode
	 * @param itemName
	 * @param inputCode
	 * @return
	 */
	int getCount(String itemCode, String itemName, String inputCode);

	/**
	 * ���form���ݣ���Ҫ��itemCode��itemName��inputCode�ֶΣ���ݷ�ҳ���curPage��pageSize����ѡ��ݽ��в�ѯ��������������form��
	 * @param form
	 * @param curCount
	 * @param pageSize
	 */
	void getSearch(CommConfigLocationTownForm form, int curCount, int pageSize);

	/**
	 * ����(�>��ֶ�idHidden)
	 * @param form
	 */
	void update(CommConfigLocationTownForm form, HttpServletRequest request);

	/**
	 * ת������ʱ�ĳ�ʼ������
	 * @param form
	 */
	void updateInit(CommConfigLocationTownForm form);

	/**
	 * ���form�е��ֶΣ�������form�е������ֶΡ���Ҫ��Բ鿴��ϸ����ʱ����
	 * @param form
	 */
	void getDetail(CommConfigLocationTownForm form);

	/**
	 * Ajaxʱ���ʡ��commConfigLocationId1����ѡ�������µ�������
	 * @param form
	 */
	void getCitys(CommConfigLocationTownForm form);

	/**
	 * Ajaxʱ����У�commConfigLocationId2����ѡ�������µ�������
	 * @param form
	 */
	void getCounties(CommConfigLocationTownForm form);

	//定义乡镇编号
	public String getItemCode(CommConfigLocationTownForm form);

}
