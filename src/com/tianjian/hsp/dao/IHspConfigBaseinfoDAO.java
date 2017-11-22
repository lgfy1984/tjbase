package com.tianjian.hsp.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tianjian.hsp.bean.HspConfigBaseinfo;
import com.tianjian.hsp.struts.form.HspConfigBaseinfoForm;

public interface IHspConfigBaseinfoDAO {
	
	/**通过Id获取医疗机构信息*/
	public HspConfigBaseinfo getById(String id);
	/**通过ItemCode获取医疗机构信息*/
	public HspConfigBaseinfo getByItemCode(String itemCode);
    /**保存医疗机构名称信息*/
    public String save(HspConfigBaseinfo hspConfigBaseinfo);
    /**更新医疗机构名称信息*/
    public void update(HspConfigBaseinfo hspConfigBaseinfo);
    /**删除医疗机构信息*/
    public void delete(HspConfigBaseinfo hspConfigBaseinfo);
    /**用来查找乡镇村名字的公用方法*/
    public String getP2ValueByP1(String tableName,String p1,String p1Value,String p2);
    /**获取当前页面医疗机构记录列表*/
    public List<?> getData(String colmun,String sttaf,String id, String itemCode, String parentItemCode, String itemName, String inputCode, String seqNo, String commConfigHospitalTypeId, String commConfigUnitgradeId, String commConfigUnittypeId, String commConfigFtManageId, String commConfigEconkindId, String commConfigLocationTownId, String commClvId, String order, int curCount, int quChuCount);
    public List<?> getDataAll(String id, String itemCode, String itemName, String inputCode, String seqNo, String commConfigHospitalTypeId, String order,  String hspConfig,int curCount, int quChuCount);
//    public List<?> getData(String colmun,String sttaf,String id, String itemCode, String parentItemCode, String itemName, String inputCode, String seqNo, String commConfigHospitalTypeId, String commConfigUnitgradeId, String commConfigUnittypeId, String commConfigFtManageId, String commConfigEconkindId, String commConfigLocationTownId, String commClvId, String order);
//    /**获取医疗机构记录总数*/
//	public int getCount(String colmun,String sattffid,String id,String itemCode,String parentItemCode, 
//			String itemName, String inputCode,String seqNo,String commConfigHospitalTypeId,String commConfigUnitgradeId,
//			String commConfigUnittypeId,String commConfigFtManageId,String commConfigEconkindId,
//			String commConfigLocationTownId,String commClvId,String hspConfig);
    public int getCountAll(String id,String itemCode, String itemName, String inputCode,String seqNo,String commConfigHospitalTypeId);
    /**id获取医疗机构名称*/
    public String getItemName(String id);
    /**itemCode获取医疗机构名称*/
    public String getNameByCode(String itemCode);
    public List<?> getByParent(String parentId, String levelFlag);
    public List<?> getTownsByParent(String parentId);
    public List<?> getVillagesByParent(String parentId);
    public List<?> findAll();
    
    public List<?> findCommConfigUnittypeList();
    public List<?> findCommConfigUnitgradeList();
    public List<?> findConfigHospitalTypeList();
    /**取得除去社区卫生服务站所有的医疗机构*/
    public List<?> findAllRemoveStation();
    /**
     * 返回nameOfTable表的有效的下一个seqNo值
     * @param nameOfTable
     * @return Long(>=1)
     */
	public Long seqNoMaker(String nameOfTable);
	/**用于弹出子画面层显示*/
	public List<?> findHspList(String flag,String inputCode,String hspType,String sattId ,int curCount, int pageSize);
	
	//读取字典值 机构分类管理
	public List<?> getPublicChar(String class_code);
	//机构分类管理-修改
	public List<?> getCommConfigFtManageIds();
	public String getPublicChar_name(String id);
	//卫生机构类别
	public String getCommConfigHospitalType_name(String itemCode);
	
//医院的等级
	
	/**
	 * @return CommDictPublicCharList
	 */
	public List<?> findAllc010501aList();
	/**
	 * @return CommDictPublicCharList
	 */
	public List<?> findAllc010501bList();
	
	String findValueByProp(String nameOfTab, String nameOfField, String nameOfProp, String valueOfProp);
	
	public HspConfigBaseinfo getHsp(String id);
	
	public List<?> getHspBaseInfoId(String id);	
	
	public List<?> init(String staffId);
	
	public int getCount(HspConfigBaseinfoForm form);
	
	public List<?> getData(HspConfigBaseinfoForm form, int curCount, int pageSize);
	/**itemCode获取机构类型名称*/
	public String getHspTypeName(String itemCode);
	
	public List<?> getByHospitalId(String hspConfigHospitalTypeId);
}


