package com.tianjian.security.business.service;

import java.util.List;

import com.tianjian.security.bean.SecurityConfigRoles;
import com.tianjian.security.bean.SecurityUserVsRoles;
import com.tianjian.security.business.ISecurityUserVsRolesService;
import com.tianjian.security.dao.ISecurityUserVsRolesDAO;
import com.tianjian.security.struts.form.SecurityUserVsRolesForm;

public class SecurityUserVsRolesServiceImpl implements ISecurityUserVsRolesService {
	
	private ISecurityUserVsRolesDAO dao;
	
	public ISecurityUserVsRolesDAO getDao() {
		return dao;
	}

	public void setDao(ISecurityUserVsRolesDAO dao) {
		this.dao = dao;
	}
	

	public int count(SecurityUserVsRolesForm form,String staffType,String hspConfigBaseinfoId) {
		return dao.count(form.getUserId(), form.getUserName(), form.getHspConfigBaseinfoId(),form.getUserInput(),staffType,hspConfigBaseinfoId);
	}
	
	public void getInitData(SecurityUserVsRolesForm form,String staffType,String hspConfigBaseinfoId,int count, int pageSize){
		List<?> userList = dao.getUsers(form.getUserId(), form.getUserName(), form.getHspConfigBaseinfoId(),form.getUserInput(),staffType,hspConfigBaseinfoId,count,pageSize);
		if(userList != null){
			String[]  userIds  = new String[userList.size()];
			String[] userNames = new String[userList.size()];
			String[] users = new String[userList.size()];
			String[] hspConfigs = new String[userList.size()];
			for(int i = 0; i < userList.size(); i++){
				userIds[i] = this.transNullToString(((Object[]) userList.get(i))[0]);
				userNames[i] = this.transNullToString(((Object[]) userList.get(i))[1]);
				users[i] = this.transNullToString(((Object[]) userList.get(i))[2]);
				hspConfigs[i] = this.transNullToString(((Object[]) userList.get(i))[3]);
			}
			form.setUserIds(userIds);
			form.setUserNames(userNames);
			form.setUsers(users);
			form.setHspConfigs(hspConfigs);
		}
		
		List<?> roleList = dao.getRoles(staffType);
		if(roleList != null){
			String[] roleId = new String[roleList.size()];
			String[] roleDetail = new String[roleList.size()];
			for(int i = 0; i < roleList.size(); i++){
				SecurityConfigRoles securityConfigRoles = (SecurityConfigRoles)roleList.get(i);
				roleId[i] = securityConfigRoles.getId();
				roleDetail[i] = this.transNullToString(securityConfigRoles.getRoleDetail());
			}
			form.setRoleId(roleId);
			form.setRoleDetail(roleDetail);
		}		
		
	}
	
	public String[] getUserRoles(String userId){
		
		String[] roleId = null;
		List<?> roleList = dao.getUserRoles(userId);
		if(roleList != null && roleList.size() > 0){
			roleId = new String[roleList.size()];
			for(int i = 0; i < roleList.size(); i++){
				SecurityUserVsRoles securityUserVsRoles = (SecurityUserVsRoles)roleList.get(i);
				roleId[i] = securityUserVsRoles.getSecurityConfigRolesId();
			}
		}
		return roleId;
	}
		   
	public void saveUserVsRoles(String userId, String[] roleId){
		
		List<?> roleList = dao.getUserRoles(userId);
		if(roleList != null && roleList.size() > 0){
			for(int i = 0; i < roleList.size(); i++){
				SecurityUserVsRoles securityUserVsRoles = (SecurityUserVsRoles)roleList.get(i);
				dao.delete(securityUserVsRoles);
			}
		}
		
		if(roleId != null && roleId.length > 0){
			for(int i = 0; i < roleId.length; i++){
				SecurityUserVsRoles securityUserVsRoles = new SecurityUserVsRoles();
				securityUserVsRoles.setSecurityStaffBaseinfoId(userId);
				securityUserVsRoles.setComments("");
				securityUserVsRoles.setSecurityConfigRolesId(roleId[i]);
				dao.save(securityUserVsRoles);
			}
		}
		
	}
	
	private String transNullToString(Object obj){
		String temp = "";
		if (obj != null){
			temp = String.valueOf(obj).trim();			
		}
		return temp;
	}
	
}
