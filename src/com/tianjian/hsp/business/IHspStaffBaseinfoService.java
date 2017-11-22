package com.tianjian.hsp.business;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.tianjian.hsp.bean.HspStaffBaseinfo;
import com.tianjian.hsp.struts.form.HspStaffBaseinfoForm;
import com.tianjian.empi.bean.SecurityUserBaseinfo;

public interface IHspStaffBaseinfoService {
	/**新增之前初始化
	 * @param request */
	public void addInit(HspStaffBaseinfoForm hspStaffBaseinfoForm);
	/**检查empNo的唯一性*/
	public int checkData(String empNo);
	/**保存对象*/
	public void save(HspStaffBaseinfoForm hspStaffBaseinfoForm);
	/**保存security对象*/
	public void saveHspToSecurity(HspStaffBaseinfoForm form);
	/**修改之前初始化*/
	
	public void updateInit(HspStaffBaseinfoForm hspStaffBaseinfoForm);
	/**更新对象*/
	public void update(HspStaffBaseinfoForm hspStaffBaseinfoForm);
	/**显示之前初始化*/
	public void showInit(HspStaffBaseinfoForm hspStaffBaseinfoForm);
	/**删除对象*/
	public void delete(HspStaffBaseinfoForm hspStaffBaseinfoForm);
	/**根据页面提交的查询条件,查询符合这些条件的总的记录条数
	 * @param isLocation */
	public int getCount(String id, String hspId, String name, String idNo,String empNo,String commConfigNationalityId,String workCertificateNo,String commDictPublicCharId1,String commDictPublicCharId2,String commConfigPositiontitleId,String commConfigEmptitleId,String commDictPublicCharId3,String commConfigDegreeId,String commConfigDegreeLevelId,String commConfigProfessionId,String commConfigLocationId3,
			   String commConfigLocationTownId,String commClvId,String staffId,String townCode,String commConfigSexId,String staffHspId,String ageS,String ageE, Long isLocation);
	public int getCountAll(String id, String hspId, String name, String idNo,String empNo,String commConfigNationalityId,String workCertificateNo,String commDictPublicCharId1,
				String commDictPublicCharId2,String commConfigPositiontitleId,String commConfigEmptitleId,String commDictPublicCharId3,String commConfigDegreeId,String commConfigDegreeLevelId,String commConfigProfessionId,String staffId);
	/**根据传入的两个参数curCount,pageSize从数据库中取得数据,*/
	public void getSearch(HspStaffBaseinfoForm hspStaffBaseinfoForm, int curCount, int pageSize, HttpServletRequest request,String townCode,String ageS,String ageE);
	public void getSearchAll(HspStaffBaseinfoForm hspStaffBaseinfoForm, int curCount, int pageSize,String staffId, HttpServletRequest request);
	/**查询前初始化*/
	public void searchInit(HspStaffBaseinfoForm hspStaffBaseinfoForm);
	/**查询详细信息*/
	public void getDetail(HspStaffBaseinfoForm hspStaffBaseinfoForm);
	
	
	//取字典
	public List<?> getAjaxNationality(String flag,String input);
	//取国籍
	public List<?> getAjaxCountry(String flag,String input);
	//取关系
	public List<?> getAjaxRelationship(String flag,String input);
	//取科室
	public List<?> getAjaxDept(String hspId, String flag,String input);
	/**用于弹出层显示*/
	public String getXml(String flag,String inputCode,String hspType,String staffId,HttpServletRequest request);

	public List<?> getPeoples(String hspConfigBaseInfoId,String flag,String input);
	
	public List<?> getAllPeoples(String flag,String input);
	
	public HspStaffBaseinfo getStaffByUserId(String userId);
	
	public HSSFWorkbook getExcel(HspStaffBaseinfoForm form, String townCode, String ageS, String ageE, String fileName, HttpServletRequest request);

	public String elsImport(InputStream inputstream, HttpServletRequest request);
	
	public HspStaffBaseinfo findById(String id);
	
	public void dataToForm(SecurityUserBaseinfo nSecurityUserBaseinfo,HspStaffBaseinfoForm form);
	
	public SecurityUserBaseinfo findOneById(String id);
	
	public String getBaseInfo(String flag,String input,String sattid, HttpServletRequest request);
	
	public String getHspNameById(String hspId);
	
	public void detail(HspStaffBaseinfoForm hsbForm);
//	/**L2 取"所学专业"字典*/
//	public List<?> commDictPublicCharId7List();
//	/**L3 取"学位"字典*/
//	public List<?> commDictPublicCharId6List();
//	/**L4 取"学历"字典*/
//	public List<?> commDictPublicCharId5List();
//	/**L5 取"专业技术职务（聘）"字典*/
//	public List<?> commDictPublicCharId4List();
//	/**L6 取"专业技术资格（评）"字典*/
//	public List<?> commGbCoptpIdList();
//	/**L7 取"行政/业务管理职务"字典*/
//	public List<?> commDictPublicCharId3List();
//	/**L8 取"从事专业类别"字典*/
//	public List<?> commDictPublicCharId2List();
//	/**L9 取"所在科室代码"字典*/
//	public List<?> deptCodeList();
//	/**L10 取"民族"字典*/
//	public List<?> commConfigNationalityIdList();
//	/**L11 取"性别"字典*/
//	public List<?> sexList();
//	/**L12 取"组织机构ID"字典*/
//	public List<?> hspConfigBaseinfoIdList();
	
}
