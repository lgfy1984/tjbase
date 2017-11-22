package com.tianjian.comm.dao;

import java.util.List;

import com.tianjian.comm.bean.CommConfigLocationTown;
/**
 * COMM_CONFIG_LOCATION_TOWN�����ֵ���Dao�ӿ�
 * @author Dzenall
 * @since 2008-9-17
 */
public interface ICommConfigLocationTownDAO {

	void save(CommConfigLocationTown data);

	/**
	 * ��CommConfigLocationTown���и�������seqNo��ֵ������ݿ��в�ѯseqNo�ֶ�����ֵ�����Ǹ����֣���������ϼ�һ�����û���ҵ������֣�����Ϊһ�󷵻�
	 * @param seqNo
	 * @return Long(>=1)
	 */
	Long seqNoMaker(String seqNo);

	/**
	 * ��CommConfigLocation���в�ѯparentId��levelFlagΪ����ֵʱ������м�¼
	 * @param parentId
	 * @param levelFlag
	 * @return List/RuntimeException
	 */
	List<?> getByParent(String parentId, String levelFlag);

	/**
	 * ���itemCode��CommConfigLocationTown���в�ѯ�Ƿ��Ѵ���
	 * @param itemCode
	 * @return CommConfigLocationTown/null
	 */
	CommConfigLocationTown findByItemCode(String itemCode);
	CommConfigLocationTown findById(String id);

	void delete(CommConfigLocationTown data);

	int getCount(String itemCode, String itemName, String inputCode);

	/**
	 * ��CommConfigLocation���и��Id��ѯ��Ӧ��ItemName�ֶ�ֵ
	 * @param id
	 * @return String/null
	 */
	String findItemNameById(String id);

	List<?> getData(String itemCode, String itemName, String inputCode, String order, int curCount, int pageSize);

	/**
	 * ���CommConfigLocation��id������Ӧ��¼��parentId
	 * @param id
	 * @return String/null
	 */
	String findParentIdById(String id);

	void update(CommConfigLocationTown data);
	public String getItemName(String itemCode);
	
	//定义乡镇编号
	public String getItemCode(String country);

}
