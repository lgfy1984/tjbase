package com.tianjian.security.business.service;


import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import com.tianjian.security.bean.SecurityConfigMenus;
import com.tianjian.security.bean.SecurityConfigPublic;
import com.tianjian.security.bean.SecurityConfigPublicClass;
import com.tianjian.security.business.ISecurityConfigPublicService;
import com.tianjian.security.dao.ISecurityConfigPublicClassDAO;
import com.tianjian.security.dao.ISecurityConfigPublicDAO;
import com.tianjian.security.struts.form.SecurityConfigMenusForm;
import com.tianjian.security.struts.form.SecurityConfigPublicForm;

public class SecurityConfigPublicServiceImpl implements ISecurityConfigPublicService {
	private static final Logger log = LogManager.getLogger(SecurityConfigPublicServiceImpl.class);
	private ISecurityConfigPublicDAO securityConfigPublicDAO;
	private ISecurityConfigPublicClassDAO securityConfigPublicClassDAO;

	//------------------------DAO-- 声明结束---------------------------------------------------
	
	
	public ISecurityConfigPublicDAO getSecurityConfigPublicDAO() {
		return securityConfigPublicDAO;
	}

	
	public void setSecurityConfigPublicDAO(ISecurityConfigPublicDAO securityConfigPublicDAO) {
		this.securityConfigPublicDAO = securityConfigPublicDAO;
	}

	public ISecurityConfigPublicClassDAO getSecurityConfigPublicClassDAO() {
		return securityConfigPublicClassDAO;
	}


	
	public void setSecurityConfigPublicClassDAO(ISecurityConfigPublicClassDAO securityConfigPublicClassDAO) {
		this.securityConfigPublicClassDAO = securityConfigPublicClassDAO;
	}
 
	public void addInit(SecurityConfigPublicForm form){
		init(form);
	}
	
	public void serchInit(SecurityConfigPublicForm form){
		init(form);
	}
	/**获取记录对象*/
	public SecurityConfigPublic checkData(String id){
		SecurityConfigPublic data = securityConfigPublicDAO.findByCode(id);
		return data;
	}
	/**提交保存*/
	public void save(SecurityConfigPublicForm form){
		this.setData(form);
		securityConfigPublicDAO.save(form.getData());
	}
	/**修改之前初始化*/
	public void updateInit(SecurityConfigPublicForm form){
		SecurityConfigPublic data = securityConfigPublicDAO.findById(form.getIdHidden());
		form.setData(data);
		init(form);
	}
	/**提交修改*/
	public void update(SecurityConfigPublicForm form){
		SecurityConfigPublic data = securityConfigPublicDAO.findById(form.getData().getId());
		this.setData1(form,data);
		securityConfigPublicDAO.update(data);
	}
	/**提交察看详细*/
	public void showInit(SecurityConfigPublicForm form){
		SecurityConfigPublic data = securityConfigPublicDAO.findById(form.getIdHidden());
		form.setData(data);
	}
	public void getInit(SecurityConfigPublicForm form){		
		
		//------------------------------初始化模块列表---------------------------------------
		this.getClassAndMods(form);
		
	}
	/**
	 * 取带有模块类别的模块列表 2011-09-09修改 
	 * */
	public void getClassAndMods(SecurityConfigPublicForm form){
		//
		List<?> modClassList = this.getSecurityConfigPublicDAO().getModClass();
		
		if(modClassList != null && modClassList.size() > 0){
			int size = modClassList.size();
			String[] modIds = new String[size];
			String[] modNames = new String[size];
			String[] modTitle = new String[size];
			String[] parentModClassIds = new String[size];
			String[] checkAbles = new String[size];
			for(int i = 0; i < modClassList.size(); i++){
				SecurityConfigPublicClass securityConfigPublicClass = (SecurityConfigPublicClass)modClassList.get(i);
				modIds[i] = securityConfigPublicClass.getId();
				modNames[i] = this.transNullToString(securityConfigPublicClass.getClassName());
				modTitle[i] = this.transNullToString(securityConfigPublicClass.getComments()); //--
				modNames[i] = modNames[i];//+ ";hint:"  + modTitle[i]
				parentModClassIds[i] = this.transNullToString(securityConfigPublicClass.getParentId());
					if(parentModClassIds[i].trim().length()<=0){
						parentModClassIds[i] = "-1";
					}
				checkAbles[i] = "false";	
			}
			
			form.setModIds(modIds);
			form.setModNames(modNames);
			form.setParentModClassIds(parentModClassIds);
			form.setCheckAbles(checkAbles);
		}
	}
	
	public void getPublic(SecurityConfigPublicForm form,String modClassId,String staffType){
		List<?> publicList = this.getSecurityConfigPublicDAO().getMod(modClassId,staffType);
		if(publicList != null && publicList.size() > 0){
			String[] publicIds = new String[publicList.size()];
			String[] publicDetails = new String[publicList.size()];
			for(int i = 0; i < publicList.size(); i++){
				SecurityConfigPublic publicData = (SecurityConfigPublic)publicList.get(i);
				publicIds[i] = publicData.getId();
				publicDetails[i] = publicData.getReason();
			}
			form.setIdList(publicIds);
			form.setReasonList(publicDetails);
		}
	}
	/**提交删除*/
	public void delete(SecurityConfigPublicForm form){
		SecurityConfigPublic data = securityConfigPublicDAO.findById(form.getIdHidden());
		securityConfigPublicDAO.delete(data);
	}
	/**获取总记录数*/
	public int getCount(String id,String modCode, String reasonValue, String inputCode, String serialNo,String parentId){
		return securityConfigPublicDAO.getCount(id, modCode, reasonValue, inputCode, serialNo, parentId);
	}
	/**根据查询条件获取当前显示页的数据*/
	public void getSearch(SecurityConfigPublicForm form, int curCount, int pageSize){
		try{
			 
		String order = "";
		if(form.getOrderNo().equals("1")){
			order = " a.modCode";
		} else if(form.getOrderNo().equals("2")){
			order = " a.reason";
		} else if(form.getOrderNo().equals("3")){
			order = " a.scpcId";
		} else if(form.getOrderNo().equals("4")){
			order = " a.serialNo";
		} else if(form.getOrderNo().equals("5")){
			order = " a.inputCode";
	
		} else {
			order = " a.serialNo";
		}
		
		if(form.getAsc().equals("1")){
			order += " desc";
		} else {
			order += " asc";
		}
	 
		List<?> list = securityConfigPublicDAO.getDataList(form.getId()
								, form.getModCode()
								, form.getReason()
								, form.getInputCode()
								, form.getSerialNo()
								, form.getParentId()
								, order
								, curCount
								, pageSize);
			if(list != null && list.size()>0){
				String[] idList = new String[list.size()];
				String[] modCodeList = new String[list.size()];
				String[] reasonList = new String[list.size()];
				String[] inputCodeList = new String[list.size()];
				String[] seriaNoList = new String[list.size()];
				String[] classNameList = new String[list.size()];
				for(int i = 0;i<list.size();i++){
					idList[i] = transNullToString(((Object[]) list.get(i))[0]);
					modCodeList[i] = transNullToString(((Object[]) list.get(i))[1]);
					reasonList[i] = transNullToString(((Object[]) list.get(i))[2]);
					seriaNoList[i] = transNullToString(String.valueOf(((Object[]) list.get(i))[3]));
					inputCodeList[i] = transNullToString(((Object[]) list.get(i))[4]);
					classNameList[i] = transNullToString(((Object[]) list.get(i))[5]);
				}
				form.setIdList(idList);
				form.setModCodeList(modCodeList);
				form.setReasonList(reasonList);
				form.setInputCodeList(inputCodeList);
				form.setSeriaNoList(seriaNoList);
				form.setClassNameList(classNameList);
			}
		 } catch (RuntimeException e) { 
			 log.error("getSearch fail!",e);
			 throw e;
		 } 
	}
	/**构造data*/
	private void setData(SecurityConfigPublicForm form){
		try{
			form.getData().setId(transNullToString(form.getData().getId()));
			form.getData().setModCode(transNullToString(form.getData().getModCode()));
			form.getData().setReason(transNullToString(form.getData().getReason()));
			form.getData().setScpcId(transNullToString(form.getData().getScpcId()));
			form.getData().setReasonValue(transNullToString(form.getData().getReasonValue()));
			form.getData().setInputCode(transNullToString(form.getData().getInputCode()));
			form.getData().setComments (transNullToString(form.getData().getComments()));
			form.getData().setSerialNo(form.getData().getSerialNo() == null ||form.getData().getSerialNo().equals("") ? 0 : form.getData().getSerialNo());
			form.getData().setPicPath(transNullToString(form.getData().getPicPath()));	
		} catch (RuntimeException e) { 
			log.error("setData fail!",e);
			throw e;
		} 
	}
	/**构造data*/
	private void setData1(SecurityConfigPublicForm form,SecurityConfigPublic data){
		try{
//			data.setId(transNullToString(form.getData().getId()));
			data.setModCode(transNullToString(form.getData().getModCode()));
			data.setReason(transNullToString(form.getData().getReason()));
			data.setScpcId(transNullToString(form.getData().getScpcId()));
			data.setReasonValue(transNullToString(form.getData().getReasonValue()));
			data.setInputCode(transNullToString(form.getData().getInputCode()));
			data.setComments (transNullToString(form.getData().getComments()));
			data.setSerialNo(form.getData().getSerialNo() == null ||form.getData().getSerialNo().equals("") ? 0 : form.getData().getSerialNo());
			data.setPicPath(transNullToString(form.getData().getPicPath()));
			data.setPublicUrl(transNullToString(form.getData().getPublicUrl()));
			data.setPublicTarget(transNullToString(form.getData().getPublicTarget()));
		} catch (RuntimeException e) { 
			log.error("setData fail!",e);
			throw e;
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
	private void init(SecurityConfigPublicForm form){
		this.getDict(form);
		if (form.getData().getSerialNo() == null || form.getData().getSerialNo().equals(""))
			form.getData().setSerialNo(Long.valueOf(securityConfigPublicDAO.getMaxSeqNo()+""));

	}
	 //--------------------------------以下部分开始处理字典库的内容-----------------------------------------
	/** 根据字典类别获取字典详细信息列表 */
	public void getDict(SecurityConfigPublicForm form) {
		List<?> securityConfigPublicClass_data = securityConfigPublicClassDAO.findAllPublic();
		form.setSecurityConfigPublicClassList(securityConfigPublicClass_data);
		 
	}
	//--------------------------------以下部分开始处理字典库的内容------结束-----------------------------------


	@Override
	public int getMenusCountByPublicId(String publicId) {
		return this.securityConfigPublicDAO.getMenusCountByPublicId(publicId);
	}
}
