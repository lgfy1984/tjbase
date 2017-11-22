package com.tianjian.comm.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tianjian.comm.bean.CommConfigHospitalType;

public interface ICommConfigHospitalTypeDAO {
	/**
	 * 通过主键获取纪录
	 * @param id
	 * @return
	 */
	public CommConfigHospitalType findByItemCode(String itemCode);
	/**
	 * 查找所有记录
	 * @return
	 */
	public List<?> findAll() ;
	/**
	 * 保存
	 * */
	public void save(CommConfigHospitalType commConfigHospitalType) ;
	/**
	 * 修改
	 * */
	public void update(CommConfigHospitalType commConfigHospitalType) ;
	/**
	 * 删除
	 * */
	public void delete(CommConfigHospitalType commConfigHospitalType);
	/**
	 * 根据某个属性查询另一个属性值
	 * */
	public String findP2ValueByP1(String beanName,String p1,String p1Value,String p2);
	/**
	 * 根据查询条件获取记录
	 * */
	public List<?> getData(String itemCode, String itemName, String inputCode,String seqNo, String orderNo, int curCount, int quChuCount);
	/**
	 * 根据查询条件获取记录条数
	 * */
	public int getCount(String itemCode, String itemName, String inputCode,String seqNo);
	/**
	 * 弹出层所用
	 * */
	public List<?> findHspTypeList(String flag,String input,String hspTypeLevel, int curCount, int pageSize,HttpServletRequest request);
	/**
	 * 生成序号
	 * */
	public Long seqNoMaker(String nameOfTable) ;
	/**
	 * 通过上一级分类得到对应的类
	 * */
	public List<?> getByParentItemCode(String parentItemCode,HttpServletRequest request);
}
