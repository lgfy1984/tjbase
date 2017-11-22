package com.tianjian.security.business.service;


import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import com.tianjian.comm.bean.CommConfigSex;
import com.tianjian.security.bean.SecurityConfigMenus;
import com.tianjian.security.bean.SecurityConfigPublic;
import com.tianjian.security.bean.SecurityConfigPublicClass;
import com.tianjian.security.bean.SecurityConfigRoles;
import com.tianjian.security.bean.SecurityRoleVsMenus;
import com.tianjian.security.business.ISecurityConfigMenusService;
import com.tianjian.security.dao.ISecurityConfigMenusDAO;
import com.tianjian.security.dao.ISecurityConfigPublicDAO;
import com.tianjian.security.struts.form.SecurityConfigMenusForm;
import com.tianjian.security.struts.form.SecurityRoleVsMenusForm;

public class SecurityConfigMenusServiceImpl implements ISecurityConfigMenusService {
	private static final Logger log = LogManager.getLogger(SecurityConfigMenusServiceImpl.class);
	private ISecurityConfigMenusDAO securityConfigMenusDAO;
	private ISecurityConfigPublicDAO securityConfigPublicDAO;

	//------------------------DAO-- 声明结束---------------------------------------------------
	
	
	
	
	public ISecurityConfigPublicDAO getSecurityConfigPublicDAO() {
		return securityConfigPublicDAO;
	}


	
	public void setSecurityConfigPublicDAO(ISecurityConfigPublicDAO securityConfigPublicDAO) {
		this.securityConfigPublicDAO = securityConfigPublicDAO;
	}


	public ISecurityConfigMenusDAO getSecurityConfigMenusDAO() {
		return securityConfigMenusDAO;
	}

	
	public void setSecurityConfigMenusDAO(ISecurityConfigMenusDAO securityConfigMenusDAO) {
		this.securityConfigMenusDAO = securityConfigMenusDAO;
	}

	public void addInit(SecurityConfigMenusForm form){
		init(form);
	}
	public void addClassId(SecurityConfigMenusForm form){
		initClassId(form);
	}
	public void serchInit(SecurityConfigMenusForm form){
		searchInit(form);
	}
	
		public void getInit(SecurityConfigMenusForm form){		
		
		//------------------------------初始化模块列表---------------------------------------
		this.getClassAndMods(form);
		
		}
		/**
		 * 取带有模块类别的模块列表 2011-09-09修改 
		 * */
		public void getClassAndMods(SecurityConfigMenusForm form){
			//
			List<?> modClassList = this.getSecurityConfigMenusDAO().getModClass();
			List<?> modList =  this.getSecurityConfigMenusDAO().getMods();
			
			if(modClassList != null && modClassList.size() > 0 && modList!=null &&modList.size()>0){
				int size = modClassList.size() + modList.size();
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
					
					
					parentModClassIds[i] = this.transNullToString(securityConfigPublicClass.getParentId());
						if(parentModClassIds[i].trim().length()<=0){
							parentModClassIds[i] = "-1";
						}
					checkAbles[i] = "false";	
				}
				for(int i = modClassList.size(); i < size; i++){
					SecurityConfigPublic securityConfigPublic = (SecurityConfigPublic)modList.get(i-modClassList.size());
					modIds[i] = securityConfigPublic.getId();
					modNames[i] = this.transNullToString(securityConfigPublic.getReasonValue());
					modTitle[i] = this.transNullToString(securityConfigPublic.getReason());//--
					
//					modNames[i] = modNames[i] + ";hint:"  + modTitle[i];
					
					parentModClassIds[i] = this.transNullToString(securityConfigPublic.getScpcId());
					checkAbles[i] = "true";	
				}
				
				form.setModIds(modIds);
				form.setModNames(modNames);
				form.setParentModClassIds(parentModClassIds);
				form.setCheckAbles(checkAbles);
			}
		}
		public SecurityConfigMenusForm getMenu(String modId,String staffType){
			SecurityConfigMenusForm form = new SecurityConfigMenusForm();
			List<?> menuList = this.getSecurityConfigMenusDAO().getModMenu(modId,staffType);
			if(menuList != null && menuList.size() > 0){
				System.out.println("length:"+menuList.size());
				String[] menuIds = new String[menuList.size()];
				String[] parentMenuIds = new String[menuList.size()];
				String[] menuDetail = new String[menuList.size()];
				String[] endLevelFlag = new String[menuList.size()];
				for(int i = 0; i < menuList.size(); i++){
					SecurityConfigMenus menu = (SecurityConfigMenus)menuList.get(i);
					menuIds[i] = menu.getId();
					String parentId = menu.getParentId();
					if(parentId == null || parentId.trim().length() <= 0){
						parentId = "-1";
					}
					parentMenuIds[i] = parentId;
//					String temp = "";
//					temp = temp + "text:" + this.transNullToString(menu.getMenuDetail()) + ";";
//					temp = temp + "hint:" + this.transNullToString(menu.getMenuNotice()) + ";";
//					temp = temp + "url:" + "security/securityConfigMenus.do?verbId=updateMenu&menuId="+ menu.getId() +";";

					menuDetail[i] = menu.getMenuDetail();
					endLevelFlag[i] = String.valueOf(menu.getEndLevelFlag());
				}
				
				form.setMenuIds(menuIds);
				form.setParentMenuIds(parentMenuIds);
				form.setMenuDetails(menuDetail);
				form.setEndLevelFlag(endLevelFlag);
			}
			
			return form;
		}
	/**获取记录对象*/
	public SecurityConfigMenus checkData(String id){
		SecurityConfigMenus data = securityConfigMenusDAO.findById(id);
		return data;
	}
	
	/**查询该ID的菜单是否存在子菜单*/
	public List<?> checkDataParent(String id){
		List<?> l = this.getSecurityConfigMenusDAO().findByParentId(id);
		return l;
		
	}
	
	/**提交保存*/
	public void save(SecurityConfigMenusForm form){
		this.setData(form);
		securityConfigMenusDAO.save(form.getData());
	}
	/**修改之前初始化*/
	public void updateInit(SecurityConfigMenusForm form){
		SecurityConfigMenus data = securityConfigMenusDAO.findById(form.getIdHidden());
		form.setData(data);
		init(form);
	}
	/**提交修改*/
	public void update(SecurityConfigMenusForm form){
		SecurityConfigMenus data = securityConfigMenusDAO.findById(form.getData().getId());
		this.setMenusData(data,form);
		securityConfigMenusDAO.update(data);
	}
	/**提交察看详细*/
	public void showInit(SecurityConfigMenusForm form){
		SecurityConfigMenus data = securityConfigMenusDAO.findById(form.getIdHidden());
		form.setData(data);
	}
	/**提交删除*/
	public void delete(SecurityConfigMenusForm form){
		SecurityConfigMenus data = securityConfigMenusDAO.findById(form.getIdHidden());
		securityConfigMenusDAO.delete(data);
	}
	/**获取总记录数*/
	public int getCount(String id,String menuCode, String menuDetail, String inputCode, String serialNo){
		return securityConfigMenusDAO.getCount(id,menuCode, menuDetail, inputCode, serialNo);
	}
	/**根据查询条件获取当前显示页的数据*/
	public void getSearch(SecurityConfigMenusForm form, int curCount, int pageSize){
		try{
			 
		String order = "";
		if(form.getOrderNo().equals("1")){
			order = " a.menuCode";
		} else if(form.getOrderNo().equals("2")){
			order = " a.menuDetail";
		} else if(form.getOrderNo().equals("3")){
			order = " a.serialNo";
		} else if(form.getOrderNo().equals("4")){
			order = " a.inputCode";
	
		} else {
			order = " a.serialNo";
		}
		
		if(form.getAsc().equals("1")){
			order += " desc";
		} else {
			order += " asc";
		}
	 
		List<?> list = securityConfigMenusDAO.getDataList(form.getId()
								, form.getMenuCode()
								, form.getMenuDetail()
								, form.getInputCode()
								, form.getSerialNo()
								, order
								, curCount
								, pageSize);
		 
		//if(list != null && list.size() > 0){ 
			form.setDataList(list);	 
		//}
		 } catch (Exception e) { 
			 log.error("getSearch fail!",e);
			 e.printStackTrace();
	} 
	}
	/**构造data*/
	private void setMenusData(SecurityConfigMenus data,SecurityConfigMenusForm form){
		try{
			data.setId (transNullToString(form.getData().getId()));			
			data.setMenuCode (transNullToString(form.getData().getId()));
			data.setMenuDetail (transNullToString(form.getData().getMenuDetail()));
			data.setInputCode (transNullToString(form.getData().getInputCode()));
			data.setComments  (transNullToString(form.getData().getComments()));
			data.setSerialNo(form.getData().getSerialNo() == null ||form.getData().getSerialNo().equals("") ? 0 : form.getData().getSerialNo());
			data.setMenuNotice  (transNullToString(form.getData().getMenuNotice()));
			data.setMenuUrl  (transNullToString(form.getData().getMenuUrl()));
			data.setEndLevelFlag(form.getData().getEndLevelFlag() == null ||form.getData().getEndLevelFlag().equals("") ? 0 : form.getData().getEndLevelFlag());
			data.setMenuLevel(form.getData().getMenuLevel() == null ||form.getData().getMenuLevel().equals("") ? 0 : form.getData().getMenuLevel());
			data.setMenuSeq(form.getData().getMenuSeq() == null ||form.getData().getMenuSeq().equals("") ? 0 : form.getData().getMenuSeq());
			data.setMenuIcon  (transNullToString(form.getData().getMenuIcon()));
			data.setMenuData  (transNullToString(form.getData().getMenuData()));
			data.setMenuTarget  (transNullToString(form.getData().getMenuTarget()));
			data.setMenuMethod  (transNullToString(form.getData().getMenuMethod()));
			data.setSecurityConfigPublicId  (transNullToString(form.getData().getSecurityConfigPublicId()));
			data.setParentId  (transNullToString(form.getData().getParentId()));
			data.setDisplayType(form.getData().getDisplayType() == null||form.getData().getDisplayType().equals("") ? 0:form.getData().getDisplayType());
			data.setMenuType(form.getData().getMenuType() ==null||form.getData().getMenuType().equals("") ? 0:form.getData().getMenuType());
		} catch (Exception e) { 
				 log.error("setData fail!",e);
				 e.printStackTrace();
		} 
	}
	/**构造data*/
	private void setData(SecurityConfigMenusForm form){
		try{
			form.getData().setId (transNullToString(form.getData().getId()));			
			form.getData().setMenuCode (transNullToString(form.getData().getId()));
			form.getData().setMenuDetail (transNullToString(form.getData().getMenuDetail()));
			form.getData().setInputCode (transNullToString(form.getData().getInputCode()));
			form.getData().setComments  (transNullToString(form.getData().getComments()));
			form.getData().setSerialNo(form.getData().getSerialNo() == null ||form.getData().getSerialNo().equals("") ? 0 : form.getData().getSerialNo());
			form.getData().setMenuNotice  (transNullToString(form.getData().getMenuNotice()));
			form.getData().setMenuUrl  (transNullToString(form.getData().getMenuUrl()));
			form.getData().setEndLevelFlag(form.getData().getEndLevelFlag() == null ||form.getData().getEndLevelFlag().equals("") ? 0 : form.getData().getEndLevelFlag());
			form.getData().setMenuLevel(form.getData().getMenuLevel() == null ||form.getData().getMenuLevel().equals("") ? 0 : form.getData().getMenuLevel());
			form.getData().setMenuSeq(form.getData().getMenuSeq() == null ||form.getData().getMenuSeq().equals("") ? 0 : form.getData().getMenuSeq());
			form.getData().setMenuIcon  (transNullToString(form.getData().getMenuIcon()));
			form.getData().setMenuData  (transNullToString(form.getData().getMenuData()));
			form.getData().setMenuTarget  (transNullToString(form.getData().getMenuTarget()));
			form.getData().setMenuMethod  (transNullToString(form.getData().getMenuMethod()));
			form.getData().setSecurityConfigPublicId  (transNullToString(form.getData().getSecurityConfigPublicId()));
			form.getData().setParentId  (transNullToString(form.getData().getParentId()));
			form.getData().setDisplayType(form.getData().getDisplayType() == null||form.getData().getDisplayType().equals("") ? 0:form.getData().getDisplayType());
			form.getData().setMenuType(form.getData().getMenuType() ==null||form.getData().getMenuType().equals("") ? 0:form.getData().getMenuType());
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
	private void initClassId(SecurityConfigMenusForm form){
		this.getDict(form);
//		SecurityConfigMenus data = securityConfigMenusDAO.findById(form.getIdHidden());
		form.getData().setSecurityConfigPublicId(form.getModClassId());
		form.getData().setMenuDetail("");
		
		if (form.getData().getSerialNo() == null || form.getData().getSerialNo().equals(""))
			form.getData().setSerialNo(Long.valueOf(securityConfigMenusDAO.getMaxSeqNo()+""));
	
	}
	/**在初始化阶段将字典库传入form中*/
	private void init(SecurityConfigMenusForm form){
		this.getDict(form);
		SecurityConfigMenus data = securityConfigMenusDAO.findById(form.getIdHidden());
		form.getData().setSecurityConfigPublicId(data.getSecurityConfigPublicId());
		form.getData().setMenuDetail(data.getMenuDetail());
		form.getData().setIsFlatMenu(data.getIsFlatMenu());
		
		if (form.getData().getSerialNo() == null || form.getData().getSerialNo().equals(""))
			form.getData().setSerialNo(Long.valueOf(securityConfigMenusDAO.getMaxSeqNo()+""));
	
	}
	/***/
	public void getDetail(SecurityConfigMenusForm form){
	
	}
	/**获取查询结果之前初始化*/
	/**在进行查找之前将字典库传入form中*/
	private void searchInit(SecurityConfigMenusForm form){
	}
	 //--------------------------------以下部分开始处理字典库的内容-----------------------------------------
	/** 根据字典类别获取字典详细信息列表 */
	public void getDict(SecurityConfigMenusForm form) {
		List<?> securityConfigPublic_data = securityConfigPublicDAO.findAll();
		//------------------------模块类别----------------------------------------------------
		String[]tempId = null;
		String[] tempName = null;
		if (securityConfigPublic_data != null) {
			tempId = new String[securityConfigPublic_data.size() + 1];
			tempName = new String[securityConfigPublic_data.size() + 1];
			tempId[0] = "";
			tempName[0] = "";
			for (int i = 0; i < securityConfigPublic_data.size(); i++) {
				SecurityConfigPublic configpublic = (SecurityConfigPublic)securityConfigPublic_data.get(i);
				tempId[i + 1] = this.transNullToString(configpublic.getId());
				tempName[i + 1] =this.transNullToString(configpublic.getReason());
			}
		}
		form.setSecurityConfigPublicIds(tempId);
		form.setSecurityConfigPublicNames(tempName);
		
		List<?> securityConfigMenus_data = securityConfigMenusDAO.findAll();
		form.setSecurityConfigMenusList(securityConfigMenus_data);
		 
	}
	//--------------------------------以下部分开始处理字典库的内容------结束-----------------------------------

	public List<?> getAjaxDict(String iputFlag, String inputValue) {
		return 	 this.getSecurityConfigMenusDAO().getAjaxDict( iputFlag, inputValue);
		
	}



	@Override
	public int getChildrenCount(String id) {
		return this.getSecurityConfigMenusDAO().getChildrenCount(id);
	}
	
	
}
