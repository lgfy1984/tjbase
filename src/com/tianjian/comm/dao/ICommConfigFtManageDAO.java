package com.tianjian.comm.dao;

import java.util.List;

import com.tianjian.comm.bean.CommConfigFtManage;
/**
 * 机构分类管理字典
 * @author DZENALL
 * @since 2009-03-17 16:41
 */
public interface ICommConfigFtManageDAO {

	/**
	 * 查询数据库中所有的CommConfigFtManage记录
	 * @return List[CommConfigFtManage]
	 */
	public List<CommConfigFtManage> findAll();

	/**
	 * 根据itemCode的属性值得到对应的itemName属性值
	 * @param itemCode
	 * @return String/""
	 */
	public String getItemNameByItemCode(String itemCode);

	/**
	 * 根据itemCode的属性值得到对应的CommConfigFtManage对象
	 * @param itemCode
	 * @return CommConfigFtManage/null
	 */
	public CommConfigFtManage findByItemCode(String itemCode);

	/**
	 * 根据传入的SQL查询规范的数据记录条数
	 * @param countSql
	 * @return int(>=0)
	 */
	public int getCount(String countSql);

	/**
	 * 根据传入的SQL查询规范的数据记录
	 * @param dataSql
	 * @param currentPageIndex
	 * @param pageSize
	 * @return List[?]/null
	 */
	public List<?> getData(String dataSql, int currentPageIndex, int pageSize);
	
	
	public Long seqNoMaker(String nameOfTable);

}
