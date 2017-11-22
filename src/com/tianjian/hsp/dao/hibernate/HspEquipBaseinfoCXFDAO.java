package com.tianjian.hsp.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.hsp.bean.BaseMessage;
import com.tianjian.hsp.bean.HspEquipBaseinfo;
import com.tianjian.hsp.dao.IHspEquipBaseinfoCXFDAO;

public class HspEquipBaseinfoCXFDAO extends HibernateDaoSupport implements IHspEquipBaseinfoCXFDAO{

	public void delete(HspEquipBaseinfo heb) {
		try{
			getHibernateTemplate().delete(heb);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public HspEquipBaseinfo findById(String id) {
		try{
			List<?> ls = getHibernateTemplate().find("select a from HspEquipBaseinfo a where a.id=?", id);
			if(ls != null && ls.size() > 0){
				return (HspEquipBaseinfo)ls.get(0);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void merge(HspEquipBaseinfo heb) {
		try{
			getHibernateTemplate().merge(heb);
		}catch (Exception e) {
			e.printStackTrace();
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

	@Override
	public List<?> queryByCondition(BaseMessage baseMessage, String hspCode, String hspName, String deptCode, String deptName,
			String equipName, String usage) {
		try {
			String sql = " select a.id,a.hspCode,b.itemName,a.deptCode,c.deptName, a.equipName, a.equipType, a.equipCode, a.supplier, a.usage from HspEquipBaseinfo a, HspConfigBaseinfo b, HspDeptBaseinfo c where a.hspCode = b.itemCode and a.deptCode = c.id.deptCode and b.id=c.id.hspConfigBaseinfoId  ";
			if(hspCode!=null && hspCode.trim().length() > 0){
				sql += " and a.hspCode like '" + hspCode.trim() + "%' ";
			}
			if(hspName!=null && hspName.trim().length() > 0){
				sql += " and b.itemName like '%" + hspName.trim() + "%' ";
			}
			if(deptCode!=null && deptCode.trim().length() > 0){
				sql += " and a.deptCode like '" + deptCode.trim().toUpperCase() + "%' ";
			}
			if(deptName!=null && deptName.trim().length() > 0){
				sql += " and c.deptName like '" + deptName.trim().toUpperCase() + "%' ";
			}
			if(equipName!=null && equipName.trim().length() > 0){
				sql += " and a.equipName like '" + equipName.trim().toUpperCase() + "%' ";
			}
			if(usage!=null && usage.trim().length() > 0){
				sql += " and a.usage = '" + equipName.trim()+ "' ";
			}
			String orderBy = " order by ";
			String sortNumber = baseMessage.getSequenceNumber();
			if("1".equals(sortNumber)){
				orderBy += " a.hspCode ";
			}else if("2".equals(sortNumber)){
				orderBy += " b.itemName ";
			}else if("3".equals(sortNumber)){
				orderBy += " a.deptCode ";
			}else if("4".equals(sortNumber)){
				orderBy += " c.deptName ";
			}else if("5".equals(sortNumber)){
				orderBy += " a.equipName ";
			}else if("6".equals(sortNumber)){
				orderBy += " a.usage ";
			}else{
				orderBy += " a.hspCode ";
			}
			if("1".equals(baseMessage.getDirectionCode())){
				orderBy += " desc";
			}else{
				orderBy += " asc";
			} 
			Query q = getSession().createQuery(sql + orderBy);
			if(baseMessage.getInitialQuantity()!=null&&baseMessage.getInitialQuantity().length()>0){
				q.setFirstResult(Integer.valueOf(baseMessage.getInitialQuantity())); 
			}
			if(baseMessage.getPageSize()!=null&&baseMessage.getPageSize().length()>0){
				q.setMaxResults(Integer.valueOf(baseMessage.getPageSize()));
			}
			List<?> l=q.list();

			return l;
		}
		catch (Exception re) {
			re.printStackTrace();
			return null;
		}
	}

}
