package com.tianjian.security.dao.hibernate;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.security.bean.SecurityConfigMenus;
import com.tianjian.security.bean.SecurityConfigPublicClass;
import com.tianjian.security.bean.SecurityStaffBaseinfo;
import com.tianjian.security.bean.SecuritySystemUsers;
import com.tianjian.security.dao.ILoginSecondDAO;
import com.tianjian.tenant.util.TenantIdHolder;

public class LoginSecondDAO extends HibernateDaoSupport implements ILoginSecondDAO{

	private static final Log log = LogFactory.getLog(LoginSecondDAO.class);
	
	//医务人员基本信息表 通过医务人员号查询密码
	public SecurityStaffBaseinfo getByCode(String code) throws Exception {
		SecurityStaffBaseinfo temp = null;
		List l = getHibernateTemplate().find(" from SecurityStaffBaseinfo a where a.staffCode = ? ", code);
		if(l != null && l.size() >0){
			temp = (SecurityStaffBaseinfo) l.get(0);
		}
		return temp;		
	}

	public SecuritySystemUsers getById(String id) throws Exception {
		SecuritySystemUsers temp = null;
		List l = getHibernateTemplate().find(" from SecuritySystemUsers a where a.securityStaffBaseinfoId = ? ", id);
		if(l != null && l.size() >0){
			temp = (SecuritySystemUsers) l.get(0);
		}
		return temp;		
	}

    //用户角色对照表 通过用户ID查询角色ID
	public List  getBysecurityStaffBaseinfoId(String securityStaffBaseinfoId) throws Exception {
		String sql = "";
    	sql += " from SecurityUserVsRoles a ";
    	sql += " where a.securityStaffBaseinfoId = '" + securityStaffBaseinfoId + "'";        	
    	
		return getHibernateTemplate().find(sql);
	}
	
	 //查询模块
	public List getPublic(String publicClassId,String roles) throws Exception {
		String sql = " select distinct c";
    	sql += " from SecurityRoleVsMenus a, SecurityConfigMenus b, SecurityConfigPublic c";
    	sql += " where a.securityConfigMenusId = b.id ";  
    	sql += " and b.securityConfigPublicId = c.id  "; 
    	sql += " and c.scpcId=  '"+ publicClassId + "' "; 
    	sql += " and a.securityConfigRolesId in(" + roles + ") ";
    	sql += " order by c.serialNo ";
		return getHibernateTemplate().find(sql);
	}
	
	 //查询模块
	public List getPublicAll(String roles) throws Exception {
		String sql = "";
    	sql += " select distinct c ";
    	sql += " from SecurityRoleVsMenus a, SecurityConfigMenus b, SecurityConfigPublic c";
    	sql += " where a.securityConfigMenusId = b.id ";  
    	sql += " and b.securityConfigPublicId = c.id  "; 
    	sql += " and a.securityConfigRolesId in(" + roles + ") ";
    	sql += " order by c.serialNo ";
		return getHibernateTemplate().find(sql);
	}
	
    //通过模块来查询菜单
	public List getMenu( String roles,String pubId) throws Exception {
		String tenantId = TenantIdHolder.get();
		if(tenantId == null) return null;
		StringBuilder sql = new StringBuilder();
    	sql.append(" select distinct a ")
	    	.append(" from  SecurityConfigMenus a, SecurityRoleVsMenus b")
	    	.append(" where a.securityConfigPublicId = '").append(pubId).append("' ") 
	    	.append(" and   b.securityConfigRolesId in(").append(roles).append(") ")
	    	.append(" and   b.securityConfigMenusId = a.id ")
	    	.append(" and ((a.displayType<>1) or (a.displayType is null)) ")
	    	.append(" and (a.isFlatMenu is null or a.isFlatMenu = '')")
	    	.append(" order by a.serialNo ");
    	//sql += " order by a.menuSeq ";  //
		return getHibernateTemplate().find(sql.toString());
	}
	
	 //通过模块来查询菜单
	public List getMenuAll(String roles) throws Exception {
		String sql = "";
    	sql += " select distinct a ";
    	sql += " from  SecurityConfigMenus a, SecurityRoleVsMenus b";
    	sql += " where b.securityConfigRolesId in(" + roles + ") "; 
    	sql += " and b.securityConfigMenusId = a.id " ;
    	sql += " and ((a.displayType<>1) or (a.displayType is null)) ";
    	sql += " order by a.serialNo ";
    	//sql += " order by a.menuSeq ";  //
		return getHibernateTemplate().find(sql);
	}
	
	/**
	 * 根据父级菜单ID查找下一级的子菜单
	 * */
	public List<?> findByParentId(String id, String[] rolesId) {
		StringBuffer str = new StringBuffer();
		if (rolesId != null && rolesId.length > 0) {
			for (int i = 0; i < rolesId.length; i++) {
				if (i == rolesId.length - 1) {
					str.append("'" + rolesId[i] + "'");
				} else {
					str.append("'" + rolesId[i] + "',");
				}
			}
		}
		String sql = "";
    	sql += " select distinct a ";
    	sql += " from  SecurityConfigMenus a, SecurityRoleVsMenus b";
    	sql += " where a.parentId = '" + id + "' "; 
    	sql += " and   b.securityConfigRolesId in(" + str.toString() + ") ";
    	sql += " and   b.securityConfigMenusId = a.id ";
    	sql += " order by a.menuSeq ";
		return getHibernateTemplate().find(sql);
	}
	
	public SecurityConfigMenus findById(String Id) {
		try {
			SecurityConfigMenus temp = null;
			List<?> ls = getHibernateTemplate().find(" from SecurityConfigMenus a where a.id = ? ", Id);
			if (ls != null && ls.size() > 0) {
				temp = (SecurityConfigMenus) ls.get(0);
			}
			return temp;
		}
		catch (Exception re) {
			log.error("findById error!", re);
			re.printStackTrace();
			return null;
		}
	}
	/**根据模块类别查找下一级模块类别*/
	public List<SecurityConfigPublicClass> getPublicClassByParentId(String parentId) {
		try{
			String sql = "";
			sql += " select d from SecurityConfigPublicClass d " +
					" where 1=1 " +
					" and d.parentId='"+parentId+"'" +
					" order by d.serialNo"; 
			List<SecurityConfigPublicClass> ls = getHibernateTemplate().find(sql);
			return ls;
		}catch(Exception re){
			log.error("getPublicClassByParentId error!", re);
			re.printStackTrace();
			return null;
		}
		
	}
	
	public List<?> findByPublicClassId(String publicClassId){
		try{
			SecurityConfigPublicClass temp = null;
			List<?> ls = this.getHibernateTemplate().find(" from SecurityConfigPublicClass a where a.id = ? ", publicClassId);
			return ls;
		}catch(Exception re){
			log.error("findByPublicClassId error!", re);
			re.printStackTrace();
			return null;
		}
	}
	
	
/**根据ID查找*/
	
	public SecurityConfigPublicClass getPublicClassById(String id){
		try{
			String sql = "";
			sql += " select d from SecurityConfigPublicClass d" +
					" where 1=1" +
					" and d.id = '"+id+"'"; 
			List ls = getHibernateTemplate().find(sql);
			if(ls!=null&&ls.size()>0){
				return (SecurityConfigPublicClass)ls.get(0);
			}else{
				return null;
			}
		}catch(Exception re){
			log.error("getPublicClassById error!", re);
			re.printStackTrace();
			return null;
		}
		
		
	}

	//查询模块类别  //应用系统标志
	public List getPublicClass(String roles,HttpServletRequest request) throws Exception {
		String sql = "";
    	sql += " select distinct d";
    	sql += " from SecurityRoleVsMenus a, SecurityConfigMenus b, SecurityConfigPublic c, SecurityConfigPublicClass d";
    	sql += " where a.securityConfigMenusId = b.id ";  
    	sql += " and b.securityConfigPublicId = c.id  ";
    	sql += " and c.scpcId = d.id  " ;
    	ServletContext application = request.getSession().getServletContext();
    	String message = (String)application.getAttribute("security.APP_SYS_FLAG");
    	//if(BaseSecurityInit.getProperty("APP_SYS_FLAG")!=null&&BaseSecurityInit.getProperty("APP_SYS_FLAG").trim().length()>0){
    	if(message!=null&&message.trim().length()>0){
    		sql += " and d.appSysFlag="+message.trim();
    	}
    	
    	sql += " and a.securityConfigRolesId in(" + roles + ") ";
    	sql += " order by d.serialNo ";
		return getHibernateTemplate().find(sql);
	}


	
}