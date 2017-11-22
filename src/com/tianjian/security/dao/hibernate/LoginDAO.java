package com.tianjian.security.dao.hibernate;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SQLQuery;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.security.bean.SecurityConfigPublicClass;
import com.tianjian.security.bean.SecurityStaffBaseinfo;
import com.tianjian.security.bean.SecuritySystemUsers;
import com.tianjian.security.bean.SecurityVersionLicense;
import com.tianjian.security.dao.ILoginDAO;
import com.tianjian.security.struts.servlet.BaseSecurityInit;
import com.tianjian.tenant.util.TenantIdHolder;
import com.tianjian.util.comm.TJInit;

public class LoginDAO extends HibernateDaoSupport implements ILoginDAO{

	private static final Log log = LogFactory.getLog(LoginDAO.class);

	
	//查询是否有多个分院
	public List<?> getByTenantId(String tenantId,String paramCode) throws Exception {
		List<?> l=null;
		SQLQuery query = getSession().createSQLQuery("select a.PARAM_CODE,a.PARAM_VALUE,a.COMMENTS from TENANT.TENANT_PARAMETERS a where a.TENANT_ID=? and a.PARAM_CODE=?");
		query.setString(0, tenantId);
		query.setString(1, paramCode);
		l = query.list();
		return l;
		
	}
	//用户角色对照表 通过用户ID查询角色ID
	public List  getHspList(String tenantId) throws Exception {
		String sql = "";
    	sql += " from HspConfigBaseinfo a ";
    	sql += " where a.tenantId = '" + tenantId + "'";        	
		return getHibernateTemplate().find(sql);
	}
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
	
	
    //查询模块类别  //应用系统标志
	public List getPublicClass(String roles,HttpServletRequest request) throws Exception {
		String tenantId = TenantIdHolder.get();
		if(tenantId == null){
			return null;
		}
		StringBuilder sql = new StringBuilder();
    	sql.append(" select distinct d")
    		.append(" from SecurityRoleVsMenus a, SecurityConfigMenus b, SecurityConfigPublic c, SecurityConfigPublicClass d")
    		.append(" where a.securityConfigMenusId = b.id ")  
    		.append(" and b.securityConfigPublicId = c.id  ")
    		.append(" and c.scpcId = d.id  ")
    		.append(" and b.tenantId = '").append(tenantId).append("'")
	    	.append(" and c.tenantId = '").append(tenantId).append("'")
	    	.append(" and d.tenantId = '").append(tenantId).append("'")
	    	.append(" and (b.isFlatMenu is null or b.isFlatMenu = '')");
    	ServletContext application = request.getSession().getServletContext();
    	String message = (String)application.getAttribute("security.APP_SYS_FLAG");
    	//if(BaseSecurityInit.getProperty("APP_SYS_FLAG")!=null&&BaseSecurityInit.getProperty("APP_SYS_FLAG").trim().length()>0){
    	if(message!=null&&message.trim().length()>0){
    		sql.append(" and d.appSysFlag=").append(message.trim());
    	}
    	//取本系统的模块类别
    	if(TJInit.getProperty("classFlag").trim().length()>0){
    		sql.append(" and d.classFlag ='").append(TJInit.getProperty("classFlag").trim()).append("'");
    	}
    	
    	//in?
    	sql.append(" and a.securityConfigRolesId in(").append(roles).append(") ");
    	sql.append(" order by d.serialNo ");
		return getHibernateTemplate().find(sql.toString());
	}
	
	/**根据ID查找*/
	
	public SecurityConfigPublicClass getPublicClassById(String id) throws Exception{
		return (SecurityConfigPublicClass)getHibernateTemplate().get(SecurityConfigPublicClass.class, id);
	}
	/**查询第n级模块类别*/
	public List getPublicClass(int n) throws Exception{
		String sql = "";
		sql += " select d from SecurityConfigPublicClass d" +
				" where 1=1" +
				" and d.levelFlag = " + n +
				" order by d.serialNo "; 
		return getHibernateTemplate().find(sql);
	}
	
	/**根据模块类别查找上一级模块类别*/
	public SecurityConfigPublicClass getParentPublicClassById(String id) throws Exception{
		String sql = "";
		sql += " select d from SecurityConfigPublicClass d,SecurityConfigPublicClass d1" +
				" where 1=1" +
				" and d.id = d1.parentId"+
				" and d1.id = '"+id+"'"; 
		List ls = getHibernateTemplate().find(sql);
		if(ls!=null&&ls.size()>0){
			return (SecurityConfigPublicClass)ls.get(0);
		}else{
			return null;
		}
		
	}
	/**根据模块类别查找下一级模块类别*/
	public List<SecurityConfigPublicClass> getPublicClassByParentId(String parentId) throws Exception{
		String sql = "";
		sql += " select d from SecurityConfigPublicClass d " +
				" where 1=1 " +
				" and d.parentId='"+parentId+"'" +
				" order by d.serialNo"; 
		List<SecurityConfigPublicClass> ls = getHibernateTemplate().find(sql);
		return ls;
	}
	
	public String getRegistCode() throws Exception {
		SecurityVersionLicense temp = null;
		List l = getHibernateTemplate().find(" from SecurityVersionLicense a ");
		if(l != null && l.size() >0){
			temp = (SecurityVersionLicense) l.get(0);
		}
		return temp.getRegistCode();		
	}
	
}