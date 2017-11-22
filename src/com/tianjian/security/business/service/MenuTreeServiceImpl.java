package com.tianjian.security.business.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tianjian.comm.dao.ICommonDAO;
import com.tianjian.security.bean.MenuTreeNode;
import com.tianjian.security.business.IMenuTreeService;
import com.tianjian.tenant.util.TenantIdHolder;
import com.tianjian.util.Converter;
import com.tianjian.util.comm.TJInit;

public class MenuTreeServiceImpl implements IMenuTreeService{
	private ICommonDAO commonDAO;
	
	public ICommonDAO getCommonDAO() {
		return commonDAO;
	}

	public void setCommonDAO(ICommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}

	@Override
	public List<MenuTreeNode> getRootNodes() {
		String tenantId = TenantIdHolder.get();
		StringBuilder hql = new StringBuilder("select a.id, a.class_name, count(b.id), count(c.id) from security.security_config_public_class a ")
							.append(" left join security.security_config_public_class b on a.id = b.parent_id")
							.append(" left join security.security_config_public c on a.id = c.scpc_id")
							.append(" where a.level_flag='1' and a.class_flag = '")
							.append(TJInit.getProperty("classFlag").trim()).append("'")
							.append(" and a.tenant_id = '").append(tenantId).append("'")
							.append(" group by a.id, a.class_name, a.serial_no order by a.serial_no, a.id, a.class_name ") ;
		List<Object[]> list = (List<Object[]>)this.commonDAO.findListBySql(hql.toString());
		return this.getPublicClassNodes(list, String.valueOf(0));
	}
	
	private List<MenuTreeNode> getPublicClassNodes(List<Object[]> list, String pid) {
		if(list != null){
			List<MenuTreeNode> nodeList = new ArrayList<MenuTreeNode>();
			for(Object[] obj : list){
				MenuTreeNode node = new MenuTreeNode(Converter.toBlank(obj[0]), pid, Converter.toBlank(obj[1]), "0".equals(pid) ? MenuTreeNode.NODE_TYPE_PUBLIC_CLASS_1 : MenuTreeNode.NODE_TYPE_PUBLIC_CLASS_2);
				if(obj.length > 2){
					if(Converter.toInteger(obj[2]) > 0){//如果一级模块类别下有二级模块类别
						node.setClass1ChildType(MenuTreeNode.NODE_TYPE_PUBLIC_CLASS_2);
					}else if(Converter.toInteger(obj[3]) > 0){//如果一级模块类别下有模块
						node.setClass1ChildType(MenuTreeNode.NODE_TYPE_PUBLIC);
					}
				}
				node.setIsParent(Boolean.TRUE);
				nodeList.add(node);
			}
			return nodeList;
		}
		return null;
	}

	@Override
	public List<MenuTreeNode> getPublicClass1ChildNodes(String publicClassId, String pid) {
		String tenantId = TenantIdHolder.get();
		StringBuilder class2Hql = new StringBuilder("select a.id, a.className from SecurityConfigPublicClass a ")
							.append(" where a.levelFlag = '2' and a.classFlag = '")
							.append(TJInit.getProperty("classFlag").trim()).append("'")
							.append(" and a.parentId = '").append(publicClassId).append("'")
							.append(" and a.tenantId = '").append(tenantId).append("'")
							.append(" order by a.serialNo") ;
		List<Object[]> class2List = (List<Object[]>)this.commonDAO.findListByHql(class2Hql.toString());
		if(class2List != null && class2List.size() > 0){
			return this.getPublicClassNodes(class2List, pid);
		}else{//如果二级模块类别为空，则查询该模块来类别下的模块是否为空
			return this.getPublicClass2ChildNodes(publicClassId, pid);
		}
	}

	private List<MenuTreeNode> getPublicNodes(List<Object[]> list, String pid) {
		if(list != null){
			List<MenuTreeNode> nodeList = new ArrayList<MenuTreeNode>();
			for(Object[] obj : list){
				MenuTreeNode node = new MenuTreeNode(Converter.toBlank(obj[0]), pid, Converter.toBlank(obj[1]), MenuTreeNode.NODE_TYPE_PUBLIC);
				nodeList.add(node);
			}
			return nodeList;
		}
		return null;
	}

	@Override
	public List<MenuTreeNode> getPublicClass2ChildNodes(String publicClassId, String pid) {
		String tenantId = TenantIdHolder.get();
		StringBuilder hql = new StringBuilder("select a.id, a.reason from SecurityConfigPublic a ")
					.append(" where a.scpcId = '").append(publicClassId).append("'")
					.append(" and a.tenantId = '").append(tenantId).append("'")
					.append(" order by a.serialNo ");
		List<Object[]> list = (List<Object[]>)this.commonDAO.findListByHql(hql.toString());
		if(list != null && list.size() > 0){
			return this.getPublicNodes(list, pid);
		}
		return null;
	}

	@Override
	public List<MenuTreeNode> getPublicChildNodes(String publicId, String pid) {
		String tenantId = TenantIdHolder.get();
		StringBuilder hql = new StringBuilder("select a.id, a.menuDetail from SecurityConfigMenus a ")
					.append(" where a.securityConfigPublicId = '").append(publicId).append("'")
					.append(" and a.tenantId = '").append(tenantId).append("'")
					.append(" and a.parentId is null order by a.serialNo ");
		List<Object[]> list = (List<Object[]>)this.commonDAO.findListByHql(hql.toString());
		if(list != null && list.size() > 0){
			return this.getMenuNodes(list, pid);
		}
		return null;
	}

	private List<MenuTreeNode> getMenuNodes(List<Object[]> list, String pid) {
		if(list != null){
			List<MenuTreeNode> nodeList = new ArrayList<MenuTreeNode>();
			for(Object[] obj : list){
				MenuTreeNode node = new MenuTreeNode(Converter.toBlank(obj[0]), pid, Converter.toBlank(obj[1]), MenuTreeNode.NODE_TYPE_MENU);
				nodeList.add(node);
			}
			return nodeList;
		}
		return null;
	}

	@Override
	public List<MenuTreeNode> getMenuChildNodes(String menuId, String pid) {
		String tenantId = TenantIdHolder.get();
		StringBuilder hql = new StringBuilder("select a.id, a.menuDetail from SecurityConfigMenus a ")
				.append(" where a.parentId = '").append(menuId).append("'")
				.append(" and a.tenantId = '").append(tenantId).append("'")
				.append(" order by a.serialNo ");
		System.out.println(hql);
		List<Object[]> list = (List<Object[]>)this.commonDAO.findListByHql(hql.toString());
		if(list != null && list.size() > 0){
		return this.getMenuNodes(list, pid);
		}
		return null;
	}

	@Override
	public String getQueryData(HttpServletRequest request) {
		String recordNum = request.getParameter("recordNum");
		int recordCount = this.getCount(request);
		if(recordCount != 0){
			String ids = this.getData(request);
			StringBuilder json = new StringBuilder("{\"count\":").append(recordCount).append(",")
									.append("\"recordNum\":").append(recordNum).append(",")
									.append("\"ids\":").append(ids).append("}");
			return json.toString();
		}
		return null;
	}
	private final static int GET_COUNT = 0;
	private final static int GET_DATA = 1;
	/**
	 * 根据查询条件构造SQL
	 * @param request
	 * @param sqlType 0:查询总结果数量  1：查询数据
	 * @return
	 */
	private String createConditionSql(HttpServletRequest request, int sqlType){
		String queryLevel = request.getParameter("queryLevel");
		String queryCode = request.getParameter("queryCode");
		String queryName = request.getParameter("queryName");
		String queryPinyinCode = request.getParameter("queryPinyinCode");
		String recordNum = request.getParameter("recordNum");
		String tenantId = TenantIdHolder.get();
		//通用过滤条件
		StringBuilder filter = new StringBuilder();
		if(isNotNull(queryCode)){
			filter.append(" and code ='").append(queryCode.trim()).append("'");
	    }
	    if(isNotNull(queryName)){
	    	filter.append(" and name like '%").append(queryName.trim()).append("%'");
	    }
	    if(isNotNull(queryPinyinCode)){
	    	filter.append(" and pinyinCode like '%").append(queryPinyinCode.trim().toUpperCase()).append("%'");
	    }
		StringBuilder publicClass1Sql = new StringBuilder("select '")
					.append(MenuTreeNode.NODE_TYPE_PUBLIC_CLASS_1)
					.append("' as type, pc1.id, pc1.class_name as name, pc1.class_code as code, pc1.input_code as pinyinCode, pc1.serial_no as seqNo from security.security_config_public_class pc1 ")
					.append(" where pc1.level_flag = '1' and pc1.class_flag = '")
					.append(TJInit.getProperty("classFlag").trim())
					.append("'")
					.append(" and pc1.tenant_id = '").append(tenantId).append("'");
		StringBuilder publicClass2Sql = new StringBuilder("select '")
					.append(MenuTreeNode.NODE_TYPE_PUBLIC_CLASS_2)
					.append("' as type, pc2.id, pc2.class_name as name, pc2.class_code as code, pc2.input_code as pinyinCode, pc2.serial_no as seqNo from security.security_config_public_class pc2 ")
					.append(" where pc2.level_flag = '2' and pc2.class_flag = '")
					.append(TJInit.getProperty("classFlag").trim())
					.append("'")
					.append(" and pc2.tenant_id = '").append(tenantId).append("'");
		StringBuilder publicSql = new StringBuilder("select '")
					.append(MenuTreeNode.NODE_TYPE_PUBLIC)
					.append("' as type, p.id, p.reason as name, p.mod_code as code, p.input_code as pinyinCode, p.serial_no as seqNo from security.security_config_public p, security.security_config_public_class pc ")
					.append(" where p.scpc_id = pc.id and pc.class_flag = '")
					.append(TJInit.getProperty("classFlag").trim())
					.append("'")
					.append(" and p.tenant_id = '").append(tenantId).append("'");
		StringBuilder menuSql = new StringBuilder("select '")
					.append(MenuTreeNode.NODE_TYPE_MENU)
					.append("' as type, m.id, m.menu_detail as name,  m.menu_code as code, m.input_code as pinyinCode, m.serial_no as seqNo from security.security_config_menus m, security.security_config_public p, security.security_config_public_class pc ")
					.append(" where m.security_config_public_id = p.id and p.scpc_id = pc.id and pc.class_flag = '")
					.append(TJInit.getProperty("classFlag").trim())
					.append("'")
					.append(" and m.tenant_id = '").append(tenantId).append("'");
		//如果人员查询条件不为空 则查询人员， 否则判断科室，最后是机构
		if(isNotNull(queryLevel)){
			StringBuilder sql = new StringBuilder();
			if(sqlType == GET_COUNT){
				sql.append("select count(*) from (");
			}else if(sqlType == GET_DATA){
				sql.append("select type, id from( ")
					.append(" select type, id, rownum as rn from(")
					.append(" select type, id from(");
			}else return null;
			//综合查询
			if("0".equals(queryLevel.trim())){
			    sql.append(publicClass1Sql).append(" union all ")
			    	.append(publicClass2Sql).append(" union all ")
			    	.append(publicSql).append(" union all ")
			    	.append(menuSql);
			}//模块类别查询
			else if("1".equals(queryLevel.trim())){
				 sql.append(publicClass1Sql);
			}//模块查询
			else if("2".equals(queryLevel.trim())){
				sql.append(publicSql);
			}//菜单查询
			else if("3".equals(queryLevel.trim())){
				sql.append(menuSql);
			}else return null;
			
			sql.append(") where 1=1 ");
			sql.append(filter);//过滤条件
			
			if(sqlType == GET_DATA){
				sql.append(" order by type, seqNo, id")//加上s.id确保排列顺序唯一
				.append("))where rn = ").append(recordNum);
			}
			return sql.toString();
		}
		return null;
		
	}
	public int getCount(HttpServletRequest request) {
		String sql = this.createConditionSql(request, GET_COUNT);
		if(isNull(sql)) return 0;
		List<?> list = this.commonDAO.findListBySql(sql);
		if(list != null && list.size() > 0){
			BigDecimal count = (BigDecimal)list.get(0);
			return count.intValue();
		}
		return 0;
	}
	
	public String getData(HttpServletRequest request) {
		String sql = this.createConditionSql(request, GET_DATA);
		if(isNull(sql)) return null;
		List<?> list = this.commonDAO.findListBySql(sql);
		List<Object> idList = new ArrayList<Object>(10);
		if(list != null && list.size() > 0){
			Object[] obj = (Object[])list.get(0);
			idList.addAll(Arrays.asList(obj));
			if(obj[obj.length - 1] != null){
				List<String> parentIdList = this.getAllParentId(String.valueOf(obj[0]), String.valueOf(obj[obj.length - 1]));
				if(parentIdList != null){
					idList.addAll(parentIdList);
				}
			}
		}
		StringBuilder json = new StringBuilder("[");
		for(Object id : idList){
			if(id != null){
				json.append("\"").append(String.valueOf(id)).append("\"").append(",");
			}else{
				json.append(id).append(",");
			}
		}
		if(json.charAt(json.length() - 1) == ','){
			json.deleteCharAt(json.length() - 1);
		}
		json.append("]");
		return json.toString();
	}
	
	//迭代获取上级菜单、模块、模块列别ID，直到获取最顶层为止
	private List<String> getAllParentId(String type, String id) {
		List<String> idList = new ArrayList<String>(10);
		StringBuilder sql = null;
		if(MenuTreeNode.NODE_TYPE_MENU.equals(type)){
			sql = new StringBuilder("select case when parent_id is null then '")
								.append(MenuTreeNode.NODE_TYPE_PUBLIC)
								.append("' else '")
								.append(MenuTreeNode.NODE_TYPE_MENU)
								.append("' end, nvl(parent_id, security_config_public_id) ")
								.append(" from security.security_config_menus ")
								.append(" where id = '").append(id).append("'");
		}else if(MenuTreeNode.NODE_TYPE_PUBLIC.equals(type)){
			sql = new StringBuilder("select case when pc.level_flag='1' then '")
					.append(MenuTreeNode.NODE_TYPE_PUBLIC_CLASS_1)
					.append("' else '")
					.append(MenuTreeNode.NODE_TYPE_PUBLIC_CLASS_2)
					.append("' end, scpc_id ")
					.append(" from security.security_config_public p , security.security_config_public_class pc ")
					.append(" where p.scpc_id = pc.id and p.id = '").append(id).append("'");
		}else if(MenuTreeNode.NODE_TYPE_PUBLIC_CLASS_2.equals(type)){
			sql = new StringBuilder("select '").append(MenuTreeNode.NODE_TYPE_PUBLIC_CLASS_1).append("', parent_id ")
					.append(" from security.security_config_public_class ")
					.append(" where id = '").append(id).append("'");
		}else{
			return new ArrayList<String>(0);
		}
		List<Object[]> list = (List<Object[]>)this.commonDAO.findListBySql(sql.toString());
		if(list != null && list.size() > 0){
			Object[] obj = list.get(0);
			String parentType = String.valueOf(obj[0]);
			String parentId = String.valueOf(obj[1]);
			idList.add(parentId);
			idList.addAll(this.getAllParentId(parentType, parentId));
		}
		return idList;
	}
	private boolean isNull(String temp){
		return temp == null || temp.trim().length() <= 0;
	}
	
	private boolean isNotNull(String temp){
		return temp != null && temp.trim().length() > 0;
	}
}
