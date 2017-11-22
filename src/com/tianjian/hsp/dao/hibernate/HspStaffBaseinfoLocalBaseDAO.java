package com.tianjian.hsp.dao.hibernate;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.hsp.dao.IHspStaffBaseinfoLocalBaseDAO;
import com.tianjian.hsp.struts.form.HspStaffBaseinfoLocalBaseForm;
import com.tianjian.tenant.util.TenantIdHolder;
import com.tianjian.util.comm.TJInit;

public class HspStaffBaseinfoLocalBaseDAO extends HibernateDaoSupport implements IHspStaffBaseinfoLocalBaseDAO {

	@Override
	public int getCount(String hspConfigBaseinfoIdQuery, String nameQuery,
			String idNoQuery, String staffId, String hspConfigBaseinfoId) {
		String tenantId = TenantIdHolder.get();
		// TODO Auto-generated method stub
		int count = 0;
		String sql = "SELECT COUNT(*) FROM HspStaffBaseinfoLocalBase a ,HspConfigBaseinfoLocalBase b";
		
        sql += " where a.tenantId = '"+tenantId+"'  and a.hspConfigBaseinfoId = b.id  and b.hspType = "+ TJInit.getProperty("classFlag").trim() ;
        
        if(TJInit.getProperty("classFlag").trim()!=null && TJInit.getProperty("classFlag").trim().equals("1")){
		    sql +=" and b.hspConfigBaseinfoId3 = (select c.hspConfigBaseinfoId3 from HspConfigBaseinfoLocalBase c where c.id = '"+ hspConfigBaseinfoId +"') " ;
		}else if(TJInit.getProperty("classFlag").trim()!=null && TJInit.getProperty("classFlag").trim().equals("2")){
		    sql +=" and b.hspConfigBaseinfoId2 = (select c.hspConfigBaseinfoId2 from HspConfigBaseinfoLocalBase c where c.id = '"+ hspConfigBaseinfoId +"') " ;
		}else{
			sql +=" ";
		}
        if(hspConfigBaseinfoIdQuery != null && hspConfigBaseinfoIdQuery.trim().length() > 0){
			sql += " and a.hspConfigBaseinfoId = '"+hspConfigBaseinfoIdQuery.trim()+"'";
		}
		if(nameQuery != null && nameQuery.trim().length() > 0){
			sql += " and a.name like '%" + nameQuery.trim() + "%'";
		}
		if(idNoQuery != null && idNoQuery.trim().length() > 0){
			sql += " and a.idNo like '%" + idNoQuery.trim() + "%'";
		}
		
		List<?> list = this.getHibernateTemplate().find(sql);
		if(list != null && list.size() > 0){
			count = Integer.valueOf(String.valueOf(list.get(0))).intValue();
		}
		return count;
	}

	@Override
	public List<?> getSearch(HspStaffBaseinfoLocalBaseForm hosform, int count,
			int pageSize, HttpServletRequest request) {
		String tenantId = TenantIdHolder.get();
		List<?> list = null;
		String sql = "SELECT a.id,a.hspConfigBaseinfoId,a.empNo,a.name,a.idNo,to_char(a.birthday,'yyyyMMdd'),a.commConfigSexId,a.mobileTel,b.itemName " +
				" FROM HspStaffBaseinfoLocalBase a ,HspConfigBaseinfoLocalBase b";
		sql += " where a.tenantId = '"+tenantId+"' and a.hspConfigBaseinfoId = b.id  and b.hspType = "+ TJInit.getProperty("classFlag").trim() ;
        
        if(TJInit.getProperty("classFlag").trim()!=null && TJInit.getProperty("classFlag").trim().equals("1")){
		    sql +=" and b.hspConfigBaseinfoId3 = (select c.hspConfigBaseinfoId3 from HspConfigBaseinfoLocalBase c where c.id = '"+ hosform.getHspConfigBaseinfoId() +"') " ;
		}else if(TJInit.getProperty("classFlag").trim()!=null && TJInit.getProperty("classFlag").trim().equals("2")){
		    sql +=" and b.hspConfigBaseinfoId2 = (select c.hspConfigBaseinfoId2 from HspConfigBaseinfoLocalBase c where c.id = '"+ hosform.getHspConfigBaseinfoId() +"') " ;
		}else{
			sql +=" ";
		}
        if(hosform.getHspConfigBaseinfoIdQuery() != null && hosform.getHspConfigBaseinfoIdQuery().trim().length() > 0){
			sql += " and a.hspConfigBaseinfoId = '"+hosform.getHspConfigBaseinfoIdQuery().trim()+"'";
		}
		if(hosform.getNameQuery() != null && hosform.getNameQuery().trim().length() > 0){
			sql += " and a.name like '%" + hosform.getNameQuery().trim() + "%'";
		}
		if(hosform.getIdNoQuery() != null && hosform.getIdNoQuery().trim().length() > 0){
			sql += " and a.idNo like '%" + hosform.getIdNoQuery().trim() + "%'";
		}
		sql += " order by a.name";
		list = this.getHibernateTemplate().find(sql);
		Query query = this.getSession().createQuery(sql);
		query.setFirstResult(count);
		query.setMaxResults(pageSize);
		list = query.list();
		return list;
	}
}
