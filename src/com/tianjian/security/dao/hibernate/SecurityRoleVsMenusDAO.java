package com.tianjian.security.dao.hibernate;

import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.security.bean.MenuTreeNode;
import com.tianjian.security.bean.SecurityRoleVsMenus;
import com.tianjian.security.dao.ISecurityRoleVsMenusDAO;
import com.tianjian.tenant.util.TenantIdHolder;
import com.tianjian.util.comm.TJInit;

public class SecurityRoleVsMenusDAO extends HibernateDaoSupport implements
		ISecurityRoleVsMenusDAO {

	private static final Logger log = LogManager
			.getLogger(SecurityRoleVsMenusDAO.class);

	@Override
	public List<?> getRolesIdAndName() {
		try {
			String tenantId = TenantIdHolder.get();
			if(tenantId == null){
				return null;
			}
			String sql = "select a.id, a.roleDetail from SecurityConfigRoles a where 1=1 ";
			if (TJInit.getProperty("classFlag").trim().length() > 0) {
				sql += " and a.roleFlag="
						+ TJInit.getProperty("classFlag").trim();
			}
			sql += " and a.tenantId = '"+ tenantId +"'";
			sql += " order by a.inputCode ";// 改为InputCode按拼音排序
			return getHibernateTemplate().find(sql);
		} catch (Exception re) {
			log.error("getRoles error!", re);
			re.printStackTrace();
			return null;
		}
	}

	@Override
	public List<?> getPublicClassNodes() {
		try {
			String tenantId = TenantIdHolder.get();
			if(tenantId == null) return null;
			StringBuilder sql = new StringBuilder(
					"select 'pc_'||a.id as id, case when a.parent_id is null and a.parent_id = '' then '0' else 'pc_'||a.parent_id end as pId, a.class_name as name,")
					.append(" case when a.level_flag='1' then '")
					.append(MenuTreeNode.NODE_TYPE_PUBLIC_CLASS_1)
					.append("' ")
					.append(" else '")
					.append(MenuTreeNode.NODE_TYPE_PUBLIC_CLASS_2)
					.append("' end as type")
					.append(" from security.security_config_public_class a where 1=1 ");
			// 取本系统的模块类别
			if (TJInit.getProperty("classFlag").trim().length() > 0) {
				sql.append(" and a.class_flag ='")
						.append(TJInit.getProperty("classFlag").trim())
						.append("'");
			}
			sql.append(" and a.tenant_id = '").append(tenantId).append("' ");
			sql.append(" order by a.class_code ");
			Query q = this.getSession().createSQLQuery(sql.toString());
			return q.list();
		} catch (Exception re) {
			log.error("getModClass error!", re);
			re.printStackTrace();
			return null;
		}
	}

	@Override
	public List<?> getPublicNodes() {
		try {
			String tenantId = TenantIdHolder.get();
			if(tenantId == null) return null;
			StringBuilder sql = new StringBuilder(
					"select 'p_'||a.id as id, 'pc_'||a.scpc_id as pId, a.reason as name, ")
					.append("'")
					.append(MenuTreeNode.NODE_TYPE_PUBLIC)
					.append("' as type from security.security_config_public a ")
					.append(" inner join security.security_config_public_class c on a.scpc_id = c.id where 1=1");
			// 取本系统的模块
			if (TJInit.getProperty("classFlag").trim().length() > 0) {
				sql.append(" and c.class_flag ='")
						.append(TJInit.getProperty("classFlag").trim())
						.append("'");
			}
			sql.append(" and a.tenant_id = '").append(tenantId).append("' ");
			sql.append(" order by a.mod_code ");
			Query q = this.getSession().createSQLQuery(sql.toString());
			return q.list();
		} catch (Exception re) {
			log.error("getModClass error!", re);
			re.printStackTrace();
			return null;
		}
	}

	@Override
	public List<?> getMenuNodes() {
		try {
			String tenantId = TenantIdHolder.get();
			if(tenantId == null) return null;
			StringBuilder sql = new StringBuilder(
					"select 'm_'||a.id, case when a.parent_id is null or a.parent_id = '' then 'p_' ||a.security_config_public_id else 'm_'||a.parent_id  end as pId, a.menu_detail as name, ")
					.append("'")
					.append(MenuTreeNode.NODE_TYPE_MENU)
					.append("' as type from security.security_config_menus a ")
					.append(" inner join security.security_config_public p on a.security_config_public_id = p.id ")
					.append(" inner join security.security_config_public_class c on p.scpc_id = c.id where 1=1");
			// 取本系统的模块
			if (TJInit.getProperty("classFlag").trim().length() > 0) {
				sql.append(" and c.class_flag ='")
						.append(TJInit.getProperty("classFlag").trim())
						.append("'");
			}
			sql.append(" and a.tenant_id = '").append(tenantId).append("' ");
			sql.append(" order by a.menu_code ");
			Query q = this.getSession().createSQLQuery(sql.toString());
			return q.list();
		} catch (Exception re) {
			log.error("getModClass error!", re);
			re.printStackTrace();
			return null;
		}
	}

	@Override
	public List<String> getMenuIdsByRoleId(String roleId) {
		try {
			String sql = "select 'm_'||a.security_config_menus_id from security.security_role_vs_menus a where a.security_config_roles_id = ?";
			Query q = this.getSession().createSQLQuery(sql);
			q.setString(0, roleId);
			return q.list();
		} catch (Exception re) {
			log.error("getMenuIdsByRoleId error!", re);
			re.printStackTrace();
			return null;
		}
	}

	@Override
	public void deleteOldRoleVsMenus(String roleId) {
		try {
			String sql = "delete from security.security_role_vs_menus where security_config_roles_id = ?";
			Query q = this.getSession().createSQLQuery(sql);
			q.setString(0, roleId);
			q.executeUpdate();
		} catch (RuntimeException re) {
			log.error("deleteOldRoleVsMenus error!", re);
			throw re;//让事务回滚
		}
	}
	
	public void save(SecurityRoleVsMenus securityRoleVsMenus){
		try {
			getHibernateTemplate().save(securityRoleVsMenus);
		} catch (RuntimeException re) {
			log.error("save error!", re);
			throw re;//让事务回滚
		}
	}

}