package com.tianjian.security.business.service;

import java.util.Iterator;
import java.util.List;

import com.tianjian.security.bean.SecurityRoleVsMenus;
import com.tianjian.security.business.ISecurityRoleVsMenusService;
import com.tianjian.security.dao.ISecurityRoleVsMenusDAO;
import com.tianjian.util.Converter;

public class SecurityRoleVsMenusServiceImpl implements ISecurityRoleVsMenusService {
	
	private ISecurityRoleVsMenusDAO dao;
	
	public ISecurityRoleVsMenusDAO getDao() {
		return dao;
	}

	public void setDao(ISecurityRoleVsMenusDAO dao) {
		this.dao = dao;
	}
	
	public String getRoleTreeNodes(){		
		//--------------------------------初始化角色列表------------------------------------------
		List<?> roleList = dao.getRolesIdAndName();
		StringBuilder sb = new StringBuilder("[");
		if(roleList != null && roleList.size() > 0){
			Iterator<?> ite = roleList.iterator();
			while(ite.hasNext()){
				Object[] obj = (Object[])ite.next();
				sb.append("{id:'").append(Converter.toBlank(obj[0]))
					.append("', pid:'").append("0")
					.append("', name:'").append(Converter.toBlank(obj[1]))
					.append("'}");
				if(ite.hasNext()){
					sb.append(",");
				}
			}
		}
		sb.append("]");
		return sb.toString();
	}
	
	public String getMenuTreeNodes() {
		List<?> publicClassNodes = this.dao.getPublicClassNodes();
		List<?> publicNodes = this.dao.getPublicNodes();
		List<?> menuNodes = this.dao.getMenuNodes();
		StringBuilder sb = new StringBuilder("[");
		if(publicClassNodes != null){
			Iterator<?> ite = publicClassNodes.iterator();
			while(ite.hasNext()){
				Object[] node = (Object[])ite.next();
				sb.append(obj2json(node));
				sb.append(",");
			}
		}
		if(publicNodes != null){
			Iterator<?> ite = publicNodes.iterator();
			while(ite.hasNext()){
				Object[] node = (Object[])ite.next();
				sb.append(obj2json(node));
				sb.append(",");
			}
		}
		if(menuNodes != null){
			Iterator<?> ite = menuNodes.iterator();
			while(ite.hasNext()){
				Object[] node = (Object[])ite.next();
				sb.append(obj2json(node));
				sb.append(",");
			}
		}
		if(sb.charAt(sb.length() - 1) == ','){
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("]");
		return sb.toString();
	}
	
	private StringBuilder obj2json(Object[] node){
		if(node != null && node.length > 3){
			StringBuilder sb = new StringBuilder("{id:'").append(Converter.toBlank(node[0]))
				.append("', pId:'").append(Converter.toBlank(node[1]))
				.append("', name:'").append(Converter.toBlank(node[2]))
				.append("', type:'").append(Converter.toBlank(node[3])).append("'}");
			return sb;
		}
		return null;
	}

	private String transNullToString(Object obj){
		String temp = "";
		if (obj != null){
			temp = String.valueOf(obj).trim();			
		}
		return temp;
	}

	@Override
	public List<String> getMenuIdsByRoleId(String roleId) {
		return this.dao.getMenuIdsByRoleId(roleId);
	}

	@Override
	public void saveSecurityRoleVsMenus(String roleId, String menuIds) {
		this.dao.deleteOldRoleVsMenus(roleId);
		if(menuIds != null){
			menuIds = menuIds.replaceAll("m_", "");
			String[] menuIdArr = menuIds.split(",");
			for(String menuId :menuIdArr){
				if(menuId != null && menuId.trim().length() > 0){
					SecurityRoleVsMenus srvm = new SecurityRoleVsMenus();
					srvm.setSecurityConfigRolesId(roleId);
					srvm.setSecurityConfigMenusId(menuId);
					this.dao.save(srvm);
				}
			}
		}
	}
}
