package com.tianjian.hsp.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.hsp.bean.BaseMessage;
import com.tianjian.hsp.bean.HspStaffBaseinfo;
import com.tianjian.hsp.bean.HspStaffLogoutRecord;
import com.tianjian.hsp.dao.IHspStaffBaseinfoCXFDAO;

public class HspStaffBaseinfoCXFDAO extends HibernateDaoSupport implements IHspStaffBaseinfoCXFDAO {

	public String findIdNo(String idNo) throws Exception {
		List<?> ls = getHibernateTemplate().find("select a.id from HspStaffBaseinfo a where a.idNo = ? ", idNo);
		if (ls != null && ls.size() > 0) {
			return (String)ls.get(0);
		}
		return null;
	}

	public List<?> queryByCondition(BaseMessage baseMessage, HspStaffBaseinfo hsb) {
		try{
			String sql = "select a.id,a.empNo,a.name,a.commConfigSexId,b.itemName,a.idNo,b.itemCode from HspStaffBaseinfo a ,HspConfigBaseinfo b where 1=1 and a.hspConfigBaseinfoId = b.id  ";
			
			if(hsb.getHspConfigBaseinfoId() != null && hsb.getHspConfigBaseinfoId().trim().length() > 0){
				sql += " and a.hspConfigBaseinfoId = '" + hsb.getHspConfigBaseinfoId().trim() +"'";
			}
			if(hsb.getName() != null && hsb.getName().trim().length() > 0){
				sql += " and a.name like '%" + hsb.getName().trim() +"%'";
			}
			if(hsb.getIdNo() != null && hsb.getIdNo().trim().length() > 0){
				sql += " and a.idNo = '" + hsb.getIdNo().trim() +"'";
			}
			if(hsb.getIdNo() != null && hsb.getIdNo().trim().length() > 0){
				sql += "and a.empNo = '" + hsb.getEmpNo().trim() + "'";
			}
			String orderBy = " order by ";
			String sortNumber = baseMessage.getSequenceNumber();
			if("1".equals(sortNumber)){
				orderBy += " a.hspConfigBaseinfoId ";
			}else if("2".equals(sortNumber)){
				orderBy += " a.name ";
			}else if("3".equals(sortNumber)){
				orderBy += " a.idNo ";
			}else if("4".equals(sortNumber)){
				orderBy += " a.empNo ";
			}else{
				orderBy += " a.hspConfigBaseinfoId ";
			}
			if("1".equals(baseMessage.getDirectionCode())){
				orderBy += " desc";
			}else{
				orderBy += " asc";
			}
			Query query = this.getSession().createQuery(sql + orderBy);
			if(baseMessage.getInitialQuantity()!=null&&baseMessage.getInitialQuantity().length()>0){
				query.setFirstResult(Integer.valueOf(baseMessage.getInitialQuantity())); 
			}
			if(baseMessage.getPageSize()!=null&&baseMessage.getPageSize().length()>0){
				query.setMaxResults(Integer.valueOf(baseMessage.getPageSize()));
			}
			List<?> list = query.list();
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public boolean save(Object obj) throws Exception {
		try{
			Serializable id = getHibernateTemplate().save(obj);
			if(id != null){
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public HspStaffBaseinfo findById(String id) {
		try{
			List<?> ls = getHibernateTemplate().find("select a from HspStaffBaseinfo a where a.id=?", id);
			if(ls != null && ls.size() > 0){
				return (HspStaffBaseinfo)ls.get(0);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void merge(HspStaffBaseinfo hsb) {
		try{
			getHibernateTemplate().merge(hsb);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(HspStaffBaseinfo hsb) {
		try{
			getHibernateTemplate().delete(hsb);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
