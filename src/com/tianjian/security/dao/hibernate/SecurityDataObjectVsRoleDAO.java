package com.tianjian.security.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.comm.bean.CommConfigLocation;
import com.tianjian.comm.bean.CommConfigLocationTown;
import com.tianjian.comm.bean.CommConfigLocationVillage;
import com.tianjian.security.bean.SecurityDataObjectType;
import com.tianjian.security.bean.SecurityDataObjectVsRoles;
import com.tianjian.security.bean.SecurityStaffBaseinfo;
import com.tianjian.security.dao.ISecurityDataObjectVsRoleServiceDAO;

public class SecurityDataObjectVsRoleDAO extends HibernateDaoSupport implements
		ISecurityDataObjectVsRoleServiceDAO {

	
	public int getCount(String staffCode, String hspConfigBaseinfoId,String id, String securityStaffBaseInfo, String sdotId,String inputCode) {
		try {
			String hql="select count(*) from security.security_data_object_vs_roles t " +
					"left join security.security_staff_baseinfo s on s.id=t.security_staff_baseinfo_id " +
					" left join hsp.hsp_config_baseinfo m on m.id = s.hsp_Config_Baseinfo_Id where 1=1";
			if(id.trim().length()>0){
				hql+=" and t.id='"+id.trim()+"'";
			}
			if(securityStaffBaseInfo.trim().length()>0){
				hql+=" and t.security_staff_baseinfo_id = '"+securityStaffBaseInfo.trim()+"'";
			}
			if(sdotId.trim().length()>0){
				hql+=" and t.sdot_id = '"+sdotId.trim()+"'";
			}
			if(inputCode.trim().length()>0){
				hql+=" and s.input_code = '"+inputCode.trim().toUpperCase()+"'";
			}
			if(staffCode.trim().length()>0){
				hql+=" and s.staff_code = '" + staffCode.trim().toUpperCase() + "'";
			}
			if(hspConfigBaseinfoId.trim().length()>0){
				hql+=" and s.HSP_CONFIG_BASEINFO_ID = '" + hspConfigBaseinfoId.trim().toUpperCase() + "'";
			}
			Query q=getSession().createSQLQuery(hql);
			
			return Integer.parseInt(q.list().get(0).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	
	public List<?> getDate(String staffCode,String hspConfigBaseinfoId,String id, String securityStaffBaseInfo,
			String sdotId,String inputCode,int first, int pageSize, String orderNo) {
		try {
		String hql="select t.id,t.security_staff_baseinfo_id,t.sdot_id,t.sdo_value,m.item_Name from security.security_data_object_vs_roles t " +
				" left join security.security_staff_baseinfo s on s.id = t.security_staff_baseinfo_id " +
				" left join hsp.hsp_config_baseinfo m on m.id = s.hsp_Config_Baseinfo_Id where 1=1";
		if(id.trim().length()>0){
			hql+=" and t.id='"+id.trim()+"'";
		}
		if(securityStaffBaseInfo.trim().length()>0){
			hql+=" and s.name = '" + securityStaffBaseInfo.trim()+ "'";
		}
		if(sdotId.trim().length()>0){
			hql+=" and t.sdot_id = '" +sdotId.trim()+ "'";
		}
		if(inputCode.trim().length()>0){
			hql+=" and s.input_code = '" +inputCode.trim().toUpperCase()+ "'";
		}
		if(staffCode.trim().length()>0){
			hql+=" and s.staff_code = '" + staffCode.trim() + "'";
		}
		if(hspConfigBaseinfoId.trim().length()>0){
			hql+=" and s.HSP_CONFIG_BASEINFO_ID = '" + hspConfigBaseinfoId.trim() + "'";
		}
		hql+=" order by "+orderNo;
		Query query=getSession().createSQLQuery(hql);
		query.setFirstResult(first);
		query.setMaxResults(pageSize);
		return query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public SecurityStaffBaseinfo getName(String name) {
		SecurityStaffBaseinfo info=null;
		List<SecurityStaffBaseinfo> list = getHibernateTemplate().find("from SecurityStaffBaseinfo s where s.name = '"+name.trim()+"'");
		if(list!=null&&list.size()>0){
			info=(SecurityStaffBaseinfo)list.get(0);
		}
		return info;
	}

	
	public SecurityDataObjectType getRoles(String name) {
		SecurityDataObjectType info=null;
		List<SecurityDataObjectType> list=getHibernateTemplate().find("from SecurityDataObjectType s where s.dataObjectTypeName = '"+name.trim()+"'");
		if(list!=null&&list.size()>0){
			info=(SecurityDataObjectType)list.get(0);
		}
		return info;
	}

	
	public SecurityStaffBaseinfo getId(String id) {
		SecurityStaffBaseinfo info=null;
		List<SecurityStaffBaseinfo> list=getHibernateTemplate().find("from SecurityStaffBaseinfo s where s.id=?",new String[]{id});
		if(list!=null&&list.size()>0){
			info=(SecurityStaffBaseinfo)list.get(0);
		}
		return info;
	}

	
	public CommConfigLocation getLocationName(String id) {
		CommConfigLocation info=null;
		List<CommConfigLocation> list=getHibernateTemplate().find("from CommConfigLocation s where s.itemCode=?",new String[]{id});
		if(list!=null&&list.size()>0){
			info=(CommConfigLocation)list.get(0);
		}
		return info;
	}

	
	public SecurityDataObjectType getRolesName(String id) {
		SecurityDataObjectType info=null;
		List<SecurityDataObjectType> list=getHibernateTemplate().find("from SecurityDataObjectType s where s.id=?",new String[]{id});
		if(list!=null&&list.size()>0){
			info=(SecurityDataObjectType)list.get(0);
		}
		return info;
	}

	
	public CommConfigLocationTown getLocationTown(String id) {
		CommConfigLocationTown info=null;
		List<CommConfigLocationTown> list=getHibernateTemplate().find("from CommConfigLocationTown s where s.itemCode=?",new String[]{id});
		if(list!=null&&list.size()>0){
			info=(CommConfigLocationTown)list.get(0);
		}
		return info;
	}

	
	public CommConfigLocationVillage getVillage(String id) {
		CommConfigLocationVillage info=null;
		List<CommConfigLocationVillage> list=getHibernateTemplate().find("from CommConfigLocationVillage s where s.itemCode=?",new String[]{id});
		if(list!=null&&list.size()>0){
			info=(CommConfigLocationVillage)list.get(0);
		}
		return info;
	}

	
	public SecurityDataObjectVsRoles getDetail(String id) {
		SecurityDataObjectVsRoles info=null;
		List<SecurityDataObjectVsRoles> list=getHibernateTemplate().find("from SecurityDataObjectVsRoles s where s.id=?",new String[]{id});
		if(list!=null&&list.size()>0){
			info=(SecurityDataObjectVsRoles)list.get(0);
		}
		return info;
	}

	
	public void delete(Object obj) {
		getHibernateTemplate().delete(obj);
		
	}

	
	public void save(Object obj) {
		getHibernateTemplate().save(obj);
	}

	
	public void update(Object obj) {
		getHibernateTemplate().update(obj);
	}

	
	public int getCheckId(String id) {
		return getHibernateTemplate().find("from SecurityDataObjectVsRoles s where s.securityStaffBaseinfoId='"+id+"'").size();
	}

	
	public List getObjectTbales(String itemCode, String itemName, String object) {
		return getHibernateTemplate().find("select "+itemCode+","+itemName+" from "+object);
	}

	
	public List<?> getFindList(String flag, String inputCode, int cur,
			int pageSaie) {
		String hql="select a from SecurityStaffBaseinfo a where 1=1 ";
		if(inputCode!=null&&inputCode.trim().length()>0&&flag.equals("1")){
			hql+=" and upper(a.inputCode) like '"+inputCode.toUpperCase()+"%'";
		}
		if(flag.equals("2")){
			hql+=" and upper(a.staffCode) like '"+inputCode.toUpperCase()+"%'";
		}
		if(flag.equals("3")){
			hql+=" and upper(a.name) like '%"+inputCode+"%'";
		}
		Query q = getSession().createQuery(hql);
		q.setFirstResult(cur); 
		q.setMaxResults(pageSaie); 
		List<?> l=q.list();
		return l;
	}

	
	public List<?> getFindLocation(String flag, String inputCode, int cur,
			int pageSaie,int level) {
		try {
			String hql="select c from CommConfigLocation c where 1=1";
			if(inputCode!=null&&inputCode.trim().length()>0&&flag.equals("1")){
				hql+=" and upper(c.inputCode) like '"+inputCode.toUpperCase()+"%'";
			}
			if(flag.equals("2")){
				hql+=" and upper(c.itemCode) like '"+inputCode+"%'";
			}
			if(flag.equals("3")){
				hql+=" and upper(c.itemName) like '%"+inputCode+"%'";
			}
			hql += " and c.levelFlag=" +level;
			Query q = getSession().createQuery(hql);
			q.setFirstResult(cur); 
			q.setMaxResults(pageSaie); 
			List<?> l=q.list();
			return l;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	
	public List<?> getFindLocationTown(String flag, String inputCode, int cur,
			int pageSaie) {
		String hql="from CommConfigLocationTown c where 1=1";
		if(inputCode!=null&&inputCode.trim().length()>0&&flag.equals("1")){
			hql+=" and upper(c.inputCode) like '"+inputCode.toUpperCase()+"%'";
		}
		if(flag.equals("2")){
			hql+=" and upper(c.itemCode) like '"+inputCode+"%'";
		}
		if(flag.equals("3")){
			hql+=" and upper(c.itemName) like '%"+inputCode+"%'";
		}
		Query q = getSession().createQuery(hql);
		q.setFirstResult(cur); 
		q.setMaxResults(pageSaie); 
		List<?> l=q.list();
		return l;
	}

	
	public List<?> getFindLocationVilge(String flag, String inputCode, int cur,
			int pageSaie) {
		String hql="from CommConfigLocationVillage c where 1=1";
		if(inputCode!=null&&inputCode.trim().length()>0&&flag.equals("1")){
			hql+=" and upper(c.inputCode) like '"+inputCode.toUpperCase()+"%'";
		}
		if(flag.equals("2")){
			hql+=" and upper(c.itemCode) like '"+inputCode+"%'";
		}
		if(flag.equals("3")){
			hql+=" and upper(c.itemName) like '%"+inputCode+"%'";
		}
		Query q = getSession().createQuery(hql);
		q.setFirstResult(cur); 
		q.setMaxResults(pageSaie); 
		List<?> l=q.list();
		return l;
	}

	
	public List<?> getLocation(int level,String parentId) {
		// TODO Auto-generated method stub
		try{
			String hql="select c from CommConfigLocation c where c.levelFlag = "+level;
			if(parentId!=null&&parentId.trim().length()>0){
				hql += " and c.parentId='"+parentId.trim()+"'";
			}
			List<?> list=this.getHibernateTemplate().find(hql);
			return list;
		}catch(Exception e){
			return null;
		}
	}

	
	public List<?> getNeedSecurityDataObjectVsRoles(String id, String sdotId) {
		// TODO Auto-generated method stub
		try{
			String sql="from SecurityDataObjectVsRoles s where s.securityStaffBaseinfoId = '"+id.trim()+"' and s.sdotId = '" +sdotId.trim()+"'";
			List<?> list=this.getHibernateTemplate().find(sql);
			return list;
		}catch(Exception e){
			return null;
		}
	}

	
	public List<?> getLocationOther(String tableName,String columnName,String parentId) {
		// TODO Auto-generated method stub
		try{
			String hql="select c from "+tableName+" c where 1=1";
			if(columnName!=null&&columnName.trim().length()>0&&parentId!=null&&parentId.trim().length()>0){
				hql += " and c."+columnName+"='"+parentId.trim()+"'";
			}
			List<?> list=this.getHibernateTemplate().find(hql);
			return list;
		}catch(Exception e){
			return null;
		}
	}

}
