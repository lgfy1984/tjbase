package com.tianjian.empi.dao;

import java.util.List;

import com.tianjian.comm.bean.CommConfigLocation;
import com.tianjian.comm.bean.CommConfigLocationTown;
import com.tianjian.comm.bean.CommConfigLocationVillage;
import com.tianjian.empi.bean.SecurityConfigUsers;
import com.tianjian.empi.bean.SecurityUserBaseinfo;
import com.tianjian.empi.struts.form.SecurityUserBaseinfoForm;
import com.tianjian.hsp.bean.HspConfigBaseinfo;
import com.tianjian.security.bean.SecurityDataObjectType;
import com.tianjian.security.bean.SecurityDataObjectVsRoles;
import com.tianjian.security.bean.SecurityStaffBaseinfo;

public interface ISecurityUserBaseinfoDAO {
	
	public void save(Object obj);
	/**通过Id获取客户个人信息*/
	public SecurityUserBaseinfo getById(String id);
	
	public int getMaxSeqNo();
	
	public int getrowsnum(String sqlpage);
	
	public List selectBaseinfobycodee(final String sql, final int start,
			final int everypage);
	
	public SecurityUserBaseinfo getByIdNo(String idNo);
	
	public int getCountUser(String id, String pmi,String hspConfigBaseinfoId, String name,
			String commConfigIdTypeId, String idNo, String inputCode,
			SecurityUserBaseinfoForm hosform);
	
	public List<?> getDataUser(String id, String pmi,String hspConfigBaseinfoId, String name,
			String commConfigIdTypeId, String idNo, String inputCode,
			String orderNo, int curCount, int quChuCount,
			SecurityUserBaseinfoForm hosform);
	
	public SecurityUserBaseinfo getByName(String name);
	/**通过pmi获取客户个人信息*/
	public SecurityUserBaseinfo getByItemCode(String itemCode);
    /**更新客户个人信息*/
    public void update(Object obj);
    /**删除客户个人信息*/
    public void delete(Object obj);
    /**获取当前页面客户记录列表*/
    public List<?> getUser(String id, String name, String inputCode,String sexId,String idNo,String cardType,String cardNo , String nameEn, String orderNo, int curCount,int quChuCount
    		,List<String[]> many);
    /**获取当前页面客户记录列表*/
    public List<?> getUser(String id, String name, String inputCode,String sexId,String idNo,String cardType,String cardNo , String nameEn, String orderNo, 
    		String columnName,String place);
    /**获取当前页面客户记录列表*/
    public List<?> getUser(String id,String pmi, String name, String nameEn, String inputCode, String commConfigIdTypeId, String orderNo, int curCount,int quChuCount
    		,List<HspConfigBaseinfo> list);
    /**获取客户记录总数*/
    public int getCountUser(String id, String name, String inputCode,String sexId,String idNo,String cardType,String cardNo , String nameEn
			,List<String[]> many);
    /**获取客户记录总数*/
    public int getCountUser(String id,String pmi, String name, String nameEn, String inputCode, String commConfigIdTypeId
    		,List<HspConfigBaseinfo> list);
    
    /**获取客户姓名*/
    public String getName(String id);
    public List<?> getUser(String id,String pmi, String name, String nameEn, String inputCode,String columnName,String place);
    public List<?> getUsers(String userId, String userName, String inputCode, String orderNo, int from, int length,List<SecurityStaffBaseinfo> list);
    public int getUsersCount(String userId, String userName, String inputCode,List<SecurityStaffBaseinfo> list);
   //-----------根据父类Id获取区县---------------------
    public List<?> getByParent(String parentId, String levelFlag);
//    //------------------fanjf添加-------------------
//    public SecurityConfigUsers selcectbeanbyuserid(String userid);
//	
//	public void insertconfigbean(SecurityConfigUsers bean);
//	/**
//	 * 根据用户userid，获得用户基本信息
//	 * @param userid
//	 * @return
//	 */
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
	/**取Item_Code**/
	public String getById(Object obj,String value);
	/**检查pmi是否重复**/
	public int getCheckPmi(String pmi);
	  //------------------fanjf添加-------------------
    public SecurityConfigUsers selcectbeanbyuserid(String userid);
	
	public void insertconfigbean(SecurityConfigUsers bean);
	/**获得满足条件的用户个数（有权限）**/
	public int getcountbymore(SecurityUserBaseinfoForm securityUserBaseinfoForm,List<String[]> many);
	/**获得满足条件的用户（有权限）**/
	public List<?> getUsers(SecurityUserBaseinfoForm securityUserBaseinfoForm,int count,int pageSize,List<String[]> many);
	public List<?> getUsers(SecurityUserBaseinfoForm securityUserBaseinfoForm);
	/**通过id获得SecurityDataObjectVsRoles*/
	public SecurityDataObjectVsRoles findById(String id);
	
	public List<?> findManyById(String id);
	/**通过itemCode获得地理位置*/
	public List<CommConfigLocation> findByItemCode(String itemCode);
	
	/**通过id获得SecurityDataObjectType*/
	public SecurityDataObjectType getSecurityDataObjectTypeById(String id);
	
	/**获得满足条件的医疗机构**/
	public List<HspConfigBaseinfo> findHosiptal(String columnName,String id);
	
	/**获得满足条件的操作人员**/
	public List<SecurityStaffBaseinfo> findSecurityStaffBaseinfo(List<HspConfigBaseinfo> list);
	
	/**通过id获得操作人员*/
	public SecurityStaffBaseinfo findSecurityStaffBaseinfoById(String id);
	
	public List<?> getTownsByParent(String parentId);
	
	public List<?> getVillagesByParent(String parentId);
	
	public List<?> getGroupsByParent(String parentId);
	
	public List<CommConfigLocationTown> findByItemCode1(String itemCode);
	
	public List<CommConfigLocationVillage> findByItemCode2(String itemCode);
	
	public List<?> getItemName(String tableName,String colmon,String value);
	
	/**获得满足条件的用户个数（无权限）**/
	public int getcountbymore(SecurityUserBaseinfoForm securityUserBaseinfoForm);
	
	/**获得满足条件的用户（无权限）**/
	public List<?> getUsers(SecurityUserBaseinfoForm securityUserBaseinfoForm,
			int count, int pageSize);
	
	public List<?> getUserExcel(String id, String pmi, String name, String nameEn,
			String inputCode, String commConfigIdTypeId,String orderNo, List<HspConfigBaseinfo> list);
	
	
	public List<?> getUserExcel2(String id, String pmi, String name, String nameEn,
			String inputCode, String commConfigIdTypeId,String orderNo, List<HspConfigBaseinfo> list);
	public List<?> getUserExcel3(String id, String pmi, String name, String nameEn,
			String inputCode, String commConfigIdTypeId,String orderNo, List<HspConfigBaseinfo> list);
	
	public List<?> getUserExcelData(String id, String pmi, String name, String nameEn,
			String inputCode, String commConfigIdTypeId,String orderNo,int curCount,int quChuCount );
	public List<?> getUserExcelData2(String id, String pmi, String name, String nameEn,
			String inputCode, String commConfigIdTypeId,String orderNo,int curCount,int quChuCount );
	public List<?> getUserExcelData3(String id, String pmi, String name, String nameEn,
			String inputCode, String commConfigIdTypeId,String orderNo,int curCount,int quChuCount );
	
	
	public int getUserExcelCount(String id, String pmi, String name, String nameEn,
			String inputCode, String commConfigIdTypeId);
	public int getUserExcelCount2(String id, String pmi, String name, String nameEn,
			String inputCode, String commConfigIdTypeId);
	public int getUserExcelCount3(String id, String pmi, String name, String nameEn,
			String inputCode, String commConfigIdTypeId);
	
	public List<?> getUserBaseinfo(String flag, String input) ;
}