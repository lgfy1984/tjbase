package com.tianjian.security.business.service;


import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import com.tianjian.security.bean.SecurityConfigPublicClass;
import com.tianjian.security.business.ISecurityConfigPublicClassService;
import com.tianjian.security.dao.ISecurityConfigPublicClassDAO;
import com.tianjian.security.struts.form.SecurityConfigPublicClassForm;
import com.tianjian.util.comm.TJInit;

public class SecurityConfigPublicClassServiceImpl implements ISecurityConfigPublicClassService {
	private static final Logger log = LogManager.getLogger(SecurityConfigPublicClassServiceImpl.class);
	private ISecurityConfigPublicClassDAO securityConfigPublicClassDAO;

	//------------------------DAO-- 声明结束---------------------------------------------------
	
	
	public ISecurityConfigPublicClassDAO getSecurityConfigPublicClassDAO() {
		return securityConfigPublicClassDAO;
	}

	
	public void setSecurityConfigPublicClassDAO(ISecurityConfigPublicClassDAO securityConfigPublicClassDAO) {
		this.securityConfigPublicClassDAO = securityConfigPublicClassDAO;
	}

	public void addInit(SecurityConfigPublicClassForm form){
		List<?> securityConfigPublicClass_data = securityConfigPublicClassDAO.findParentPublicClass();
		form.setSecurityConfigPublicClassList(securityConfigPublicClass_data);
		if (form.getData().getSerialNo() == null || form.getData().getSerialNo().equals(""))
			form.getData().setSerialNo(Long.valueOf(securityConfigPublicClassDAO.getMaxSeqNo()+""));
	}
	
	public void serchInit(SecurityConfigPublicClassForm form){
		init(form);
	}
	/**获取记录对象*/
	public SecurityConfigPublicClass checkData(String id){
		SecurityConfigPublicClass data = securityConfigPublicClassDAO.findByCode(id);
		return data;
	}
	/**提交保存*/
	public void save(SecurityConfigPublicClassForm form){
		this.setData(form);
		securityConfigPublicClassDAO.save(form.getData());
	}
	/**修改之前初始化*/
	public void updateInit(SecurityConfigPublicClassForm form){
		SecurityConfigPublicClass data = securityConfigPublicClassDAO.findById(form.getIdHidden());
		form.setData(data);
		List<?> securityConfigPublicClass_data = securityConfigPublicClassDAO.findParentPublicClass();
		form.setSecurityConfigPublicClassList(securityConfigPublicClass_data);
	}
	/**提交修改*/
	public void update(SecurityConfigPublicClassForm form){
		this.setData(form);
		securityConfigPublicClassDAO.update(form.getData());
	}
	/**提交察看详细*/
	public void showInit(SecurityConfigPublicClassForm form){
		SecurityConfigPublicClass data = securityConfigPublicClassDAO.findById(form.getIdHidden());
		if(data.getParentId() != null && data.getParentId().trim().length() > 0){
			data.setSecurityConfigPublicClassObject(securityConfigPublicClassDAO.findById(data.getParentId()));
		}
		form.setData(data);
	}
	/**提交删除*/
	public void delete(SecurityConfigPublicClassForm form){
		SecurityConfigPublicClass data = securityConfigPublicClassDAO.findById(form.getIdHidden());
		securityConfigPublicClassDAO.delete(data);
	}
	/**获取总记录数*/
	public int getCount(String id,String classCode, String className, String inputCode, String serialNo,String levelFlag,String panterId){
		return securityConfigPublicClassDAO.getCount(id,classCode, className, inputCode, serialNo, levelFlag, panterId);
	}
	/**根据查询条件获取当前显示页的数据*/
	public void getSearch(SecurityConfigPublicClassForm form, int curCount, int pageSize){
		try{
			String order = "";
			if(form.getOrderNo().equals("1")){
				order = " a.classCode";
			} else if(form.getOrderNo().equals("2")){
				order = " a.className";
			} else if(form.getOrderNo().equals("3")){
				order = " a.parentId";
			} else if(form.getOrderNo().equals("4")){
				order = " a.levelFlag";
			} else if(form.getOrderNo().equals("5")){
				order = " a.serialNo";
			} else if(form.getOrderNo().equals("6")){
				order = " a.inputCode";
			} else {
				order = " a.serialNo";
			}
			
			if(form.getAsc().equals("1")){
				order += " desc";
			} else {
				order += " asc";
			}
			List<?> list = securityConfigPublicClassDAO.getDataList(form.getId()
									, form.getClassCode()
									, form.getClassName()
									, form.getInputCode()
									, form.getSerialNo()
									, form.getLevelFlag()
									, form.getParentId()
									, order
									, curCount
									, pageSize);
			if(list != null && list.size() > 0){
				String[] idList = new String[list.size()];
				String[] classCodeList = new String[list.size()];
				String[] classNameList = new String[list.size()];
				String[] inputCodeList = new String[list.size()];
				String[] serialNoList = new String[list.size()];
				String[] levelFlagList = new String[list.size()];
				String[] classPartenNameL = new String[list.size()];
				for(int i = 0;i < list.size(); i++){
					SecurityConfigPublicClass data = (SecurityConfigPublicClass)list.get(i);
					idList[i] = this.transNullToString(data.getId());
					classCodeList[i] = this.transNullToString(data.getClassCode());
					classNameList[i] = this.transNullToString(data.getClassName());
					inputCodeList[i] = this.transNullToString(data.getInputCode());
					serialNoList[i] = this.transNullToString(String.valueOf(data.getSerialNo()));
					levelFlagList[i] = this.transNullToString(String.valueOf(data.getLevelFlag()));
					if(data.getParentId()!=null && data.getParentId().length()>0){
						classPartenNameL[i] = this.transNullToString(this.getSecurityConfigPublicClassDAO().getClassName(data.getParentId()));
					}else{
						classPartenNameL[i] = "";
					}
				}
				form.setIdList(idList);
				form.setClassCodeList(classCodeList);
				form.setClassNameList(classNameList);
				form.setInputCodeList(inputCodeList);
				form.setSerialNoList(serialNoList);
				form.setLevelFlagList(levelFlagList);
				form.setClassPartenNameL(classPartenNameL);
			}
		 } catch (Exception e) { 
			 log.error("getSearch fail!",e);
			 e.printStackTrace();
	} 
	}
	/**setData方法用来构造form.data对应实体类的属性*/
	private void setData(SecurityConfigPublicClassForm form){
		try{
			form.getData().setId(transNullToString(form.getData().getId()));
			form.getData().setClassCode(transNullToString(form.getData().getClassCode()));
			form.getData().setClassName(transNullToString(form.getData().getClassName()));
			form.getData().setInputCode(transNullToString(form.getData().getInputCode()));
			form.getData().setComments(transNullToString(form.getData().getComments()));
			form.getData().setParentId(transNullToString(form.getData().getParentId()));
			form.getData().setPicPath(transNullToString(form.getData().getPicPath()));
			form.getData().setSerialNo(form.getData().getSerialNo() == null ||form.getData().getSerialNo().equals("") ? Long.valueOf("0") : form.getData().getSerialNo());
			form.getData().setLevelFlag(form.getData().getLevelFlag() == null ||form.getData().getLevelFlag().equals("") ? Long.valueOf("0") : form.getData().getLevelFlag());
			String classFlag = TJInit.getProperty("classFlag");
			if(classFlag != null && classFlag.trim().length() > 0){
				form.getData().setClassFlag(Long.valueOf(classFlag));
			}else{
				form.getData().setClassFlag(Long.valueOf("1"));
			}
		} catch (Exception e) { 
				 log.error("setData fail!",e);
				 e.printStackTrace();
		} 
	}
	/**
	 * 去掉字串中的空格
	 * */
	public String transNullToString(Object obj){
		String temp = "";
		if (obj != null){
			temp = ((String)obj).trim();			
		}
		return temp;
	}
	
	public String transNullToZero(Object obj){
		String temp = "0";
		if (obj != null){
			temp = ((String)obj).trim();			
		}
		return temp;
	}
	/**在初始化阶段将字典库传入form中*/
	private void init(SecurityConfigPublicClassForm form){
		this.getDict(form);
		if (form.getData().getSerialNo() == null || form.getData().getSerialNo().equals(""))
			form.getData().setSerialNo(Long.valueOf(securityConfigPublicClassDAO.getMaxSeqNo()+""));

	}
	
	/**获取查询结果之前初始化*/
	/**在进行查找之前将字典库传入form中*/
 
    //--------------------------------以下部分开始处理字典库的内容-----------------------------------------
	/** 根据字典类别获取字典详细信息列表 */
	public void getDict(SecurityConfigPublicClassForm form) {
		List<?> securityConfigPublicClass_data = securityConfigPublicClassDAO.findAll();
		form.setSecurityConfigPublicClassList(securityConfigPublicClass_data);
		 
	}


	@Override
	public int getPublicCountByPublicClassId(String publicClassId) {
		return this.securityConfigPublicClassDAO.getPublicCountByPublicClassId(publicClassId);
	}


	@Override
	public int getPublicClass2CountByPublicClassId(String publicClassId) {
		return this.securityConfigPublicClassDAO.getPublicClass2CountByPublicClassId(publicClassId);
	}
 
	//--------------------------------以下部分开始处理字典库的内容------结束-----------------------------------
}
