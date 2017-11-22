package com.tianjian.comm.dao;

import java.util.List;

import com.tianjian.comm.bean.CommConfigYes;


public interface ICommConfigYesDAO {
	/**
	 * 通过主键获取纪录
	 * @param id
	 * @return
	*/
	public CommConfigYes findById(String id);
	/**
     * 查找所有记录
     * @return
    */
	public List<?> findAll();
	 /**
     * 通过ID获取职称名称
     * @param id
     * @return
     */
	public String getNameById(String id);
	/**
	 * 保存记录
	 * @param commConfigYes
	 */
	public void save(CommConfigYes commConfigYes);
	 /**
     * 修改记录
     * @param commConfigYes
     */
	public void update(CommConfigYes commConfigYes);
	/**
     * 删除记录
     * @param commConfigYes
     */
	public void delete(CommConfigYes commConfigYes);
	/**
     * 根据条件获取当前页记录
     * @param itemCode
     * @param itemName
     * @param inputCode
     * @param seqNo
     * @param orderNo
     * @param curCount
     * @param pageSize
     * @return l
     */
	 public List<?> getData(String itemCode, String itemName, String inputCode, String seqNo, String orderNo, int curCount,int pageSize);
	 /**
	     * 根据查询条件获取记录总数
	     * @param itemCode
	     * @param itemName
	     * @param inputCode
	     * @param seqNo
	     * @return
	     */
	 public int getCount(String itemCode, String itemName, String inputCode, String seqNo);
	 
	 
	 public Long seqNoMaker(String nameOfTable);
}
