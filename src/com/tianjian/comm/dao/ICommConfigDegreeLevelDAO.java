package com.tianjian.comm.dao;

import java.util.List;
import com.tianjian.comm.bean.CommConfigDegreeLevel;

import com.tianjian.comm.dao.ICommConfigDegreeLevelDAO;

/**
 * Created by TemplateDaoInterface
 * @author atEcd
 * @since 2009-4-1 17:55:44
 */
public interface ICommConfigDegreeLevelDAO{

	public void save(CommConfigDegreeLevel data);

	public void update(CommConfigDegreeLevel data);

	public void delete(CommConfigDegreeLevel data);

	@SuppressWarnings("unchecked")
	public List<CommConfigDegreeLevel> findAll();

	@SuppressWarnings("unchecked")
	/**Not suggested to use this method*/
	public List<CommConfigDegreeLevel> findList(String idName, String idValue);

	@SuppressWarnings("unchecked")
	public List<CommConfigDegreeLevel> findListByItemCode(String itemCodeValue);

	@SuppressWarnings("unchecked")
	public List<CommConfigDegreeLevel> findListByItemName(String itemNameValue);

	@SuppressWarnings("unchecked")
	public List<CommConfigDegreeLevel> findListByInputCode(String inputCodeValue);

	@SuppressWarnings("unchecked")
	public List<CommConfigDegreeLevel> findListByLevelFlag(String levelFlagValue);

	@SuppressWarnings("unchecked")
	public List<CommConfigDegreeLevel> findListByParentItemCode(String parentItemCodeValue);

	@SuppressWarnings("unchecked")
	public List<CommConfigDegreeLevel> findListByComments(String commentsValue);

	@SuppressWarnings("unchecked")
	public List<CommConfigDegreeLevel> findListBySeqNo(String seqNoValue);

	/**Not suggested to use this method*/
	public CommConfigDegreeLevel findCommConfigDegreeLevel(String idName, String idValue);

	public CommConfigDegreeLevel findByItemCode(String itemCodeValue);

	public CommConfigDegreeLevel findByItemName(String itemNameValue);

	public CommConfigDegreeLevel findByInputCode(String inputCodeValue);

	public CommConfigDegreeLevel findByLevelFlag(String levelFlagValue);

	public CommConfigDegreeLevel findByParentItemCode(String parentItemCodeValue);

	public CommConfigDegreeLevel findByComments(String commentsValue);

	public CommConfigDegreeLevel findBySeqNo(String seqNoValue);

	public int getCount(String itemCode, String itemName, String inputCode);

	public List<?> getData(String itemCode, String itemName, String inputCode, String ascNo, String orderNo, int currentPageIndex, int pageSize);

	public CommConfigDegreeLevel checkData(String itemCode);

}
