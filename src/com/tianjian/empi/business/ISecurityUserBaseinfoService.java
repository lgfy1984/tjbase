package com.tianjian.empi.business;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.tianjian.empi.struts.form.SecurityUserBaseinfoForm;
import com.tianjian.empi.bean.SecurityUserBaseinfo;
import com.tianjian.security.struts.form.SessionForm;

public interface ISecurityUserBaseinfoService {
	
	public HSSFWorkbook saveExport(SecurityUserBaseinfoForm hosform,String staffId,String staffIdName);
    /**新增之前初始化*/
	public void addInit(SecurityUserBaseinfoForm form,String id);
	/**检查pmi的唯一性*/
	public SecurityUserBaseinfo checkData(String pmi);
	/**save*/
	public void save(SecurityUserBaseinfoForm form,SessionForm sessionform);
	
	public List selectBaseinfobycodee(String code, int start, int everypage);
	
	public int getrowsnum(String sqlpage);
	
	public SecurityUserBaseinfo getByPmi(String pmi);
	
	public SecurityUserBaseinfo checkByIdNo(String idNo);
	
	/**修改之前初始化*/
	public void updateInit(SecurityUserBaseinfoForm form,String id);
	/**update*/
	public void update(SecurityUserBaseinfoForm form,SessionForm sessionform);
	/**显示之前初始化*/
	public void showInit(SecurityUserBaseinfoForm form,String id);
	/**delete*/
	public void delete(SecurityUserBaseinfoForm form);	
	/**修改之前初始化*/
	public int getCountUser(String id, String name, String inputCode,String sexId,String idNo,String cardType,String cardNo , String nameEn,String staffId,String xflag);
	
	public void getSearch(SecurityUserBaseinfoForm form, int curCount, int quChuCount,String staffId);
	
	/**查找之前初始化*/
	public void serchInit(HttpServletRequest request,SecurityUserBaseinfoForm form);
	/**获取省*/
	public void getProvinces(SecurityUserBaseinfoForm form);
	/**市*/
	public void getCitys(SecurityUserBaseinfoForm form);
	/**获取县*/
	public void getDistricts(SecurityUserBaseinfoForm form);
    /**获取详细信息*/
	public void getDetail(SecurityUserBaseinfoForm form);
	
	/**获得部分详细信息*/
	
	public void getSomeDetail(SecurityUserBaseinfoForm form);
	/**更新照片路径*/
	public void picUpById(String id, String photoPath);
	
	/**通过Id获取客户个人信息*/
	public SecurityUserBaseinfo getById(String id);
	/**
	 * 根据用户pmi，获得用户基本信息
	 * @param userid
	 * @return
	 */
	public SecurityUserBaseinfo findUserInfoById(String userid);
	/**取性别*/
	public String getcommConfigSex(String commConfigSexId);
	/**取国籍*/
	public String getcommConfigCountry(String commConfigCountryId);
	/**取名族*/
	public String getcommConfigNationality(String commConfigNationalityId);
	/**取证件*/
	public String getcommConfigIdType(String commConfigIdTypeId);
	/**取学历*/
	public String getcommConfigDegree(String commConfigDegreeId);
	/**取婚姻*/
	public String getcommConfigMaritalStatus(String commConfigMaritalStatusId);
	/**取关系*/
	public String getcommConfigRelationship(String commConfigRelationshipId);
/** 取表总的记录数*/
	public int getCount();
	//导入excel表
	public List saveImport(InputStream inputstream);

	/**获得满足条件的用户个数**/
	public int getcountbymore(SecurityUserBaseinfoForm securityUserBaseinfoForm,String staffId);
	/**获得满足条件的用户**/
	public void getUsers(SecurityUserBaseinfoForm securityUserBaseinfoForm,int count,int pageSize,String staffId);
	
	public void savePassword(SecurityUserBaseinfoForm form,SecurityUserBaseinfo data);
	
	public void getFullForm(SecurityUserBaseinfoForm form, SecurityUserBaseinfo data);
	
	public void setForm(SecurityUserBaseinfoForm form, SecurityUserBaseinfo data);
	
	public void getTowns(SecurityUserBaseinfoForm form);
	
	public void getVillages(SecurityUserBaseinfoForm form);
	
	public void getGroup(SecurityUserBaseinfoForm form);
	
	public void queryInit(SecurityUserBaseinfoForm form,String id,SessionForm sessionForm);
	
	public List<?> getUserBaseinfo(String flag, String input);
	
	public void getIdAddress(String countyCode,SecurityUserBaseinfoForm hosform);
	
	public void searchInit(SecurityUserBaseinfoForm form);
	
}
