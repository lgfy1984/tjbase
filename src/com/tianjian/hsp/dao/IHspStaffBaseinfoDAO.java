package com.tianjian.hsp.dao;

import java.util.List;

import com.tianjian.comm.bean.CommConfigNationality;
import com.tianjian.hsp.bean.HspConfigBaseinfo;
import com.tianjian.hsp.bean.HspStaffBaseinfo;
import com.tianjian.security.bean.SecurityStaffBaseinfo;

public interface IHspStaffBaseinfoDAO {
	/**根据主键id查找对象HspStaffBaseinfo*/
	public HspStaffBaseinfo findById(String id);
	/**根据empNo查找对象HspStaffBaseinfo*/
	public HspStaffBaseinfo getByEmpNo(String empNo);
	public SecurityStaffBaseinfo getByStaffCode(String staffCode);
	/**取得所有对象列表*/
	public List<?> findAll();
	/**根据主键id获取对应的名称*/
	public String getNameById(String id);
	public HspConfigBaseinfo getById(String id);
	/**保存对象HspStaffBaseinfo*/
	public void save(HspStaffBaseinfo hspStaffBaseinfo);
	/**更新修改对象HspStaffBaseinfo*/
	public void update(HspStaffBaseinfo hspStaffBaseinfo);
	public List<?> hspConfigBaseinfoIdList();
	/**删除对象HspStaffBaseinfo:只修改在位标识*/
	public void delete(HspStaffBaseinfo hspStaffBaseinfo);
	/**提取数据*/
	public List<?> getData(String hspId, String name, String idNo,String empNo,String commConfigNationalityId,String workCertificateNo,String commDictPublicCharId1,String commDictPublicCharId2,String commConfigPositiontitleId,String commConfigEmptitleId,String commDictPublicCharId3,String commConfigDegreeId,String commConfigDegreeLevelId,String commConfigProfessionId,String commConfigLocationId3,
			   String commConfigLocationTownId,String commClvId,String staffId, String order,int count, int pageSize,List listId,String townCode,String commConfigSexId,String staffHspId,String ageS,String ageE, Long isLocation) ;
	public List<?> getDataAll(String hspId, String name, String idNo,String empNo,String commConfigNationalityId,String workCertificateNo,String commDictPublicCharId1,String commDictPublicCharId2,String commConfigPositiontitleId,String commConfigEmptitleId,String commDictPublicCharId3,String commConfigDegreeId,String commConfigDegreeLevelId,String commConfigProfessionId, int count, int pageSize);
	/**取得记录总数
	 * @param isLocation */
	public int getCount(String id, String hspId, String name, String idNo,String empNo,String commConfigNationalityId,String workCertificateNo,String commDictPublicCharId1,String commDictPublicCharId2,String commConfigPositiontitleId,String commConfigEmptitleId,String commDictPublicCharId3,String commConfigDegreeId,String commConfigDegreeLevelId,String commConfigProfessionId,String commConfigLocationId3,
			   String commConfigLocationTownId,String commClvId,String staffId,List listId,String townCode,String commConfigSexId,String hspConfigId,String ageS,String ageE, Long isLocation);
	public int getCountAll(String id, String hspId, String name, String idNo,String empNo,String commConfigNationalityId,String workCertificateNo,String commDictPublicCharId1,String commDictPublicCharId2,String commConfigPositiontitleId,String commConfigEmptitleId,String commDictPublicCharId3,String commConfigDegreeId,String commConfigDegreeLevelId,String commConfigProfessionId);
	public List<?> getAjaxNationality(String flag,String input);
	public List<?> getAjaxCountry(String flag,String input);
	public List<?> getAjaxDept(String hspId, String flag,String input);
	public List<?> getAjaxRelationship(String flag,String input); 
	public List<?> findHspList(String flag,String inputCode,String hspType,String hspConficBaseinfo1,String colnum,int curCount, int pageSize);
	/**表中存ID,要取NAME, 用的方法..*/
//	public String getNameByid(String tableName, String colName, String name, String idValue);
	
	/**由hsp staff baseinfo ID 取 security staff baseinfo*/
	public SecurityStaffBaseinfo getSecurityStaffBaseinfoById(String id);	
	
	/**取民族的ID对应的NAME 取出一个对像出来*/
	public CommConfigNationality getNationalName(String id);
	
	/**L2 取"所学专业"字典*/
	public List<?> commConfigProfessionIdList();
	/**L3 取"学位"字典*/
	public List<?> commConfigDegreeLevelIdList();
	/**L4 取"学历"字典*/
	public List<?> commConfigDegreeIdList();
	/**L5 取"专业技术职务（聘）"字典*/
	public List<?> commDictPublicCharId3List();
	/**L6 取"专业技术资格（评）"字典*/
	public List<?> commConfigEmptitleIdList();
	/**L7 取"行政/业务管理职务"字典*/
	public List<?> commConfigPositiontitleIdList();
	/**L8 取"医师管理专业类别"字典*/
	public List<?> commDictPublicCharId2List();
	/**L9 取"从事专业类别"字典*/
	public List<?> commDictPublicCharId1List();
	/**L10 取"民族"字典*/
	public List<?> commConfigNationalityIdList();
	/**L11 取"性别"字典*/
	public List<?> sexList();
	
	public List<?> getPeoples(String hspConfigBaseInfoId,String flag,String input);
	
	public List<?> getAllPeoples(String flag,String input);
	
	public List<?> getHspData();
	public HspStaffBaseinfo getStaffByUserId(String userId);
	public List<?> getData(String hspId, String name, String idNo,String empNo,String commConfigNationalityId,String workCertificateNo,String commDictPublicCharId1,String commDictPublicCharId2,String commConfigPositiontitleId,String commConfigEmptitleId,String commDictPublicCharId3,String commConfigDegreeId,String commConfigDegreeLevelId,String commConfigProfessionId,String staffId,List listId);
	
	public List<?> getHsp(String staffId);
	
	public List<?> getHsp(String comlun,String whereComlun,String value);
	
	public List<?> getBaseInfo(String colmun,String sttaf,String flag,String input );
	
	public List<?> getHsp(String falg ,String input,int count, int pageSize);
	
}
