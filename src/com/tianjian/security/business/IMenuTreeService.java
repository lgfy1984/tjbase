package com.tianjian.security.business;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tianjian.security.bean.MenuTreeNode;

public interface IMenuTreeService {

	/**
	 * 获取一级模块类别节点
	 */
	public List<MenuTreeNode> getRootNodes();

	/**
	 * 获取一级模块类别的子节点，可能是二级模块类别节点，也可能是模块节点
	 * @param publicClassId 模块类别id
	 * @param pid 父节点id
	 * @return 节点对象列表
	 */
	public List<MenuTreeNode> getPublicClass1ChildNodes(String publicClassId, String pid);

	/**
	 * 获取二级模块类别的子节点，即模块节点
	 * @param publicClassId 模块类别id
	 * @param pid 父节点id
	 * @return 节点对象列表
	 */
	public List<MenuTreeNode> getPublicClass2ChildNodes(String publicClassId, String pid);

	/**
	 * 获取模块的子节点，即菜单节点
	 * @param publicClassId 模块id
	 * @param pid 父节点id
	 * @return 节点对象列表
	 */
	public List<MenuTreeNode> getPublicChildNodes(String publicId, String pid);

	/**
	 * 获取菜单的子节点，即子菜单节点
	 * @param publicClassId 菜单id
	 * @param pid 父节点id
	 * @return 节点对象列表
	 */
	public List<MenuTreeNode> getMenuChildNodes(String menuId, String pid);

	
	/**
	 * 根据请求查询匹配的节点
	 * @param request
	 * @return json字符串，包含匹配的总数 ，当前的记录数，当前匹配节点信息
	 */
	public String getQueryData(HttpServletRequest request);
	
}