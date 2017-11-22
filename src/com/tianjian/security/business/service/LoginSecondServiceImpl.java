package com.tianjian.security.business.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tianjian.security.bean.SecurityConfigMenus;
import com.tianjian.security.bean.SecurityConfigPublicClass;
import com.tianjian.security.bean.SecurityUserVsRoles;
import com.tianjian.security.business.ILoginSecondService;
import com.tianjian.security.dao.ILoginSecondDAO;
import com.tianjian.security.struts.form.LoginMenuForm;
import com.tianjian.security.struts.form.SessionForm;
import com.tianjian.util.comm.Encrypt;

public class LoginSecondServiceImpl implements ILoginSecondService {

	private ILoginSecondDAO loginSecondDAO;

	public ILoginSecondDAO getLoginSecondDAO() {
		return loginSecondDAO;
	}

	public void setLoginSecondDAO(ILoginSecondDAO loginSecondDAO) {
		this.loginSecondDAO = loginSecondDAO;
	}
	
	/**
	 * @return Returns the LoginDAO.
	 */
	public List<?> getPublic(LoginMenuForm loginMenuForm) {
		// TODO Auto-generated method stub
		StringBuffer str = new StringBuffer();
		try {
			// 取得模块
			if (loginMenuForm.getRolesId() != null && loginMenuForm.getRolesId().length > 0) {
				for (int i = 0; i < loginMenuForm.getRolesId().length; i++) {
					if (i == loginMenuForm.getRolesId().length - 1) {
						str.append("'" + loginMenuForm.getRolesId()[i] + "'");
					} else {
						str.append("'" + loginMenuForm.getRolesId()[i] + "',");
					}
				}
				List<?> pub = loginSecondDAO.getPublic(loginMenuForm.getSelectedPublicClassId(), str.toString());
				return pub;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;

	}
	
	
	public List<?> getPublicAll(SessionForm sessionForm) {
		// TODO Auto-generated method stub
		StringBuffer str = new StringBuffer();
		try {
			// 取得模块
			if (sessionForm.getStaffRoles() != null && sessionForm.getStaffRoles().length > 0) {
				for (int i = 0; i < sessionForm.getStaffRoles().length; i++) {
					if (i == sessionForm.getStaffRoles().length - 1) {
						str.append("'" + sessionForm.getStaffRoles()[i] + "'");
					} else {
						str.append("'" + sessionForm.getStaffRoles()[i] + "',");
					}
				}
				List<?> pub = loginSecondDAO.getPublicAll(str.toString());
				return pub;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;

	}
	
	
	public List<?> getMenusByPubId(SessionForm sessionForm,String pubId){
		StringBuffer str = new StringBuffer();
		try {
			// 取得模块
			if (sessionForm.getStaffRoles() != null && sessionForm.getStaffRoles().length > 0) {
				for (int i = 0; i < sessionForm.getStaffRoles().length; i++) {
					if (i == sessionForm.getStaffRoles().length - 1) {
						str.append("'" + sessionForm.getStaffRoles()[i] + "'");
					} else {
						str.append("'" + sessionForm.getStaffRoles()[i] + "',");
					}
				}
				List<?> menus = loginSecondDAO.getMenu(str.toString(),pubId);
				return menus;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	
	public List<?> getMenuAll(SessionForm sessionForm) {
		// TODO Auto-generated method stub
		StringBuffer str = new StringBuffer();
		try {
			// 取得模块
			if (sessionForm.getStaffRoles() != null && sessionForm.getStaffRoles().length > 0) {
				for (int i = 0; i < sessionForm.getStaffRoles().length; i++) {
					if (i == sessionForm.getStaffRoles().length - 1) {
						str.append("'" + sessionForm.getStaffRoles()[i] + "'");
					} else {
						str.append("'" + sessionForm.getStaffRoles()[i] + "',");
					}
				}
				List<?> menus = loginSecondDAO.getMenuAll(str.toString());
				return menus;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;

	}
	/**
	 * @return Returns the LoginDAO.
	 */
	public void getPublicAndMenu(LoginMenuForm loginMenuForm) {
		// TODO Auto-generated method stub
		StringBuffer str = new StringBuffer();
		try {
			// 取得模块
			if (loginMenuForm.getRolesId() != null && loginMenuForm.getRolesId().length > 0) {
				for (int i = 0; i < loginMenuForm.getRolesId().length; i++) {
					if (i == loginMenuForm.getRolesId().length - 1) {
						str.append("'" + loginMenuForm.getRolesId()[i] + "'");
					} else {
						str.append("'" + loginMenuForm.getRolesId()[i] + "',");
					}
				}
				List<?> pub = loginSecondDAO.getPublic(loginMenuForm.getSelectedPublicClassId(), str.toString());
				if (pub != null && pub.size() > 0) {
					String[] publicId = new String[pub.size()];
					String[] publicReasonValue = new String[pub.size()];
					String[] picPath = new String[pub.size()];
					String[] publicReasonName = new String[pub.size()];
					for (int i = 0; i < pub.size(); i++) {
						publicId[i] = this.transNullToString(((Object[]) pub.get(i))[0]);
						publicReasonValue[i] = this.transNullToString(((Object[]) pub.get(i))[1]);
						picPath[i] = this.transNullToString(((Object[]) pub.get(i))[2]);
						publicReasonName[i] = this.transNullToString(((Object[]) pub.get(i))[4]); 
					}
					loginMenuForm.setPublicId(publicId);
					loginMenuForm.setPublicName(publicReasonValue);
					loginMenuForm.setPicPath(picPath);
					loginMenuForm.setPublicReasonName(publicReasonName);
				}
				// 取得菜单
				if (loginMenuForm.getPublicId() != null && loginMenuForm.getPublicId().length > 0) {
					this.getMenuDetail(loginMenuForm.getPublicId()[0], loginMenuForm.getRolesId(), loginMenuForm);
				}
			}
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**查找某一模块类别的下一级模块类别*/
	public List<SecurityConfigPublicClass> getPublicClassByParentId(String parentId) throws Exception{
		return this.getLoginSecondDAO().getPublicClassByParentId(parentId);
	}
	/**查找某一模块类别的所有最低级模块类别2*/
	private void getEndPublicClassByParentId2(String parentId,List<SecurityConfigPublicClass> endls) throws Exception{
		List<SecurityConfigPublicClass> ls = this.getPublicClassByParentId(parentId);
		if(ls!=null&&ls.size()>0){
			for(int i=0;i<ls.size();i++){
				this.getEndPublicClassByParentId2(ls.get(i).getId(), endls);
			}
		}else{
			endls.add(this.loginSecondDAO.getPublicClassById(parentId));
		}
	}
	/**
	 * 查找有权限的某一模块类别的所有最低级模块类别
	 * */
	private List<SecurityConfigPublicClass>  getEndPublicClassById(String publicClassId,String userId, HttpServletRequest request)  throws Exception{
		List<SecurityConfigPublicClass> ls1 = new ArrayList<SecurityConfigPublicClass>();
		this.getEndPublicClassByParentId2(publicClassId, ls1);
		
		List<SecurityConfigPublicClass> ls2 = null;
		// 取得角色ID
		StringBuffer str = new StringBuffer();
		List<?> roles = this.loginSecondDAO.getBysecurityStaffBaseinfoId(userId);
		if (roles != null && roles.size() > 0) {
			for (int i = 0; i < roles.size(); i++) {
				SecurityUserVsRoles ro = (SecurityUserVsRoles) roles.get(i);
				String rolesIdTemp = ro.getSecurityConfigRolesId();
				if(i == roles.size() - 1){
					str.append("'" + rolesIdTemp + "'");
				}else{
					str.append("'" + rolesIdTemp + "',");
				}
			}
			ls2 = (List<SecurityConfigPublicClass>) this.loginSecondDAO.getPublicClass(str.toString(),request);
		}	
			
		
		List<SecurityConfigPublicClass> ls3 = new ArrayList<SecurityConfigPublicClass>();
		if(ls1!=null&&ls1.size()>0&&ls2!=null&&ls2.size()>0){
			for(int i=0;i<ls1.size();i++){
				for(int j=0;j<ls2.size();j++){
					if(ls1.get(i).getId().equals(ls2.get(j).getId())){
						ls3.add(ls1.get(i));
					}
				}
			}
		}
		
		return ls3;
	}
	
	public void getMenuDetail(String pubId, String[] rolesId, LoginMenuForm loginMenuForm) throws Exception {
		StringBuffer str = new StringBuffer();
		Encrypt encrypt = new Encrypt();
		if (rolesId != null && rolesId.length > 0) {
			for (int i = 0; i < rolesId.length; i++) {
				if (i == rolesId.length - 1) {
					str.append("'" + rolesId[i] + "'");
				} else {
					str.append("'" + rolesId[i] + "',");
				}
			}
		}
		List<?> menu = loginSecondDAO.getMenu( str.toString(),pubId);
		if (menu != null && menu.size() > 0) {
			String[] menuId = new String[menu.size()];
			String[] parentMenuId = new String[menu.size()];
			String[] menuName = new String[menu.size()];
			for (int i = 0; i < menu.size(); i++) {
				SecurityConfigMenus me = (SecurityConfigMenus) menu.get(i);
				menuId[i] = this.transNullToString(me.getId());
				String parent = this.transNullToString(me.getParentId());
				if (parent.trim().length() <= 0) {
					parent = "-1";
				}
				parentMenuId[i] = parent;
				String temp = "";
				temp = temp + "text:" + this.transNullToString(me.getMenuDetail()) + ";";
				temp = temp + "hint:" + this.transNullToString(me.getMenuNotice()) + ";";
				if (this.transNullToString(me.getEndLevelFlag()).equals("1")) {
					if (this.transNullToString(me.getMenuUrl()).trim().length() > 0) {
						temp = temp + "url:" + this.transNullToString(me.getMenuUrl());
					} else {
						temp = temp + "url:" + "main/default.jsp";
					}
					if (this.transNullToString(me.getMenuData()).trim().length() > 0) {
						temp = temp + "?" + this.transNullToString(me.getMenuData());
					}
					temp = this.replaceUrl(temp, "?", encrypt.encryptString(loginMenuForm.getStaffId())) + ";";
				}
				if (this.transNullToString(me.getEndLevelFlag()).equals("1")) {
					temp = temp + "target:main;";
				}
				menuName[i] = temp;
				// System.out.println(menuName[i]);
			}
			loginMenuForm.setMenuId(menuId);
			loginMenuForm.setParentMenuId(parentMenuId);
			loginMenuForm.setMenuName(menuName);
			loginMenuForm.setSelectedPublicId(pubId);
		}
	}

	/**
	 * 处理在loginSecond阶段 sessionForm需要装载的内容
	 * @param staffId
	 * @param sessionForm
	 */
	public void getLoginSecondSessionForm(SessionForm sessionForm) {
		// TODO Auto-generated method stub
		try {
			// if (loginSecondForm.getPublicClassId()=='3'){
			//HspConfigBaseinfo hspConfigBaseinfo = loginSecondDAO.getHospitalById(sessionForm.getStaffHospitalId());
			/*sessionForm.setStaffHospitalId(this.transNullToString(hspConfigBaseinfo.getId()));// ---目前取到
			sessionForm.setCommConfigLocationId1(this.transNullToString(hspConfigBaseinfo.getCommConfigLocationId1()));
			sessionForm.setCommConfigLocationId2(this.transNullToString(hspConfigBaseinfo.getCommConfigLocationId2()));
			sessionForm.setCommConfigLocationId3(this.transNullToString(hspConfigBaseinfo.getCommConfigLocationId3()));*/
			
			
			//sessionForm.setCommCltId(this.transNullToString(hspConfigBaseinfo.getCommConfigLocationTownId()));
			//sessionForm.setCommClvId(this.transNullToString(hspConfigBaseinfo.getCommClvId()));
			// }
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 把null值转化为空
	private String transNullToString(Object obj) {
		String temp = "";
		if (obj != null) {
			temp = String.valueOf(obj);
		}
		return temp.trim();
	}

	public String transNullToZero(Object obj) {
		String temp = "0";
		if (obj != null) {
			temp = String.valueOf(obj).trim();
		}
		return temp;
	}

	public String replaceUrl(String line, String oldString, String staffId) {
		if (line == null) {
			return null;
		}
		int i = 0;
		// --------开始查找lcOldString---------------------------------
		if ((i = line.indexOf(oldString, i)) >= 0) {
			char[] line2 = line.toCharArray();
			char[] newString2 = ("?staffId=" + staffId + "&").toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j = i;
			while ((i = line.indexOf(oldString, i)) > 0) {
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
				j = i;
			}
			buf.append(line2, j, line2.length - j);
			return buf.toString();
		} else {
			line = line + "?staffId=" + staffId;
		}
		return line;
	}
	
	/**
	 * 根据父级菜单ID查找下一级的子菜单
	 * */
	public List<?> findByParentId(String id, String[] rolesId){
		return this.loginSecondDAO.findByParentId(id,rolesId);
	}
	
	public SecurityConfigMenus findById(String Id) {
		return this.loginSecondDAO.findById(Id);
	}
	/**
	 * 根据第二类菜单ID寻找它的父类菜单（就是第一类菜单ID）
	 * */
	
	public String findByPublicClassId(String publicClassId){
		SecurityConfigPublicClass temp = new SecurityConfigPublicClass();
		List<?> ls = this.loginSecondDAO.findByPublicClassId(publicClassId);
		if(ls != null && ls.size() > 0){
			temp = (SecurityConfigPublicClass)ls.get(0);
		}
		return temp.getParentId();
	}
	
	public void findByPublicClass(SessionForm sessionForm ,String publicClassId){
		List<?> ls = this.loginSecondDAO.findByPublicClassId(publicClassId);
		String[] publicClassIds = new String[ls.size()]; 
		String[] publicClassNames = new String[ls.size()]; 
		String[] publicClassSysFlags = new String[ls.size()]; 
		String[] publicClassRedirectUrls = new String[ls.size()]; 		
		if(ls != null && ls.size() > 0){	
			for(int i=0;i<ls.size();i++){
				SecurityConfigPublicClass temp = (SecurityConfigPublicClass) ls.get(i);
				publicClassIds[i] = this.transNullToString(temp.getId());
				publicClassNames[i] = this.transNullToString(temp.getClassName());
				publicClassSysFlags[i] = this.transNullToString(temp.getSysFlag());
				publicClassRedirectUrls[i] = this.transNullToString(temp.getRedirectUrl());
			}
		}
		sessionForm.setPublicClassIds(publicClassIds);
		sessionForm.setPublicClassNames(publicClassNames);
		sessionForm.setPublicClassSysFlags(publicClassSysFlags);
		sessionForm.setPublicClassRedirectUrls(publicClassRedirectUrls);
		
	}
	
	@Override
	public void getEndPublicClass(SessionForm sessionForm,String publicClassId,String staffId,HttpServletRequest request) {
		try{
			List<SecurityConfigPublicClass> ls = this.getEndPublicClassById(publicClassId, staffId, request);
			String[] publicClassIds = new String[ls.size()]; 
			String[] publicClassNames = new String[ls.size()]; 
			String[] publicClassSysFlags = new String[ls.size()]; 
			String[] publicClassRedirectUrls = new String[ls.size()]; 		
			if(ls != null && ls.size() > 0){	
				for(int i=0;i<ls.size();i++){
					SecurityConfigPublicClass temp = (SecurityConfigPublicClass)ls.get(i);
					publicClassIds[i] = this.transNullToString(temp.getId());
					publicClassNames[i] = this.transNullToString(temp.getClassName());
					publicClassSysFlags[i] = this.transNullToString(temp.getSysFlag());
					publicClassRedirectUrls[i] = this.transNullToString(temp.getRedirectUrl());
				}
			}
			sessionForm.setPublicClassIds(publicClassIds);
			sessionForm.setPublicClassNames(publicClassNames);
			sessionForm.setPublicClassSysFlags(publicClassSysFlags);
			sessionForm.setPublicClassRedirectUrls(publicClassRedirectUrls);
		}catch(Exception re){
			re.printStackTrace();
	
		}	
	}
	

}
