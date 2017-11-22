package com.tianjian.hsp.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.hsp.bean.BaseMessage;
import com.tianjian.hsp.bean.HspConfigBaseinfo;
import com.tianjian.hsp.bean.HspLogoutRecord;
import com.tianjian.hsp.bean.HspMergeLog;
import com.tianjian.hsp.dao.IHspConfigBaseinfoCXFDAO;

public class HspConfigBaseinfoCXFDAO extends HibernateDaoSupport implements IHspConfigBaseinfoCXFDAO{

	public String findItemCode(String itemCode) {
		try{
			List<?> ls = getHibernateTemplate().find("select a.id from HspConfigBaseinfo a where a.itemCode = ? ", itemCode);
			if (ls != null && ls.size() > 0) {
				return (String) ls.get(0);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean save(Object obj) {
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
	
	public void merge(HspConfigBaseinfo hcb) {
		try{
			getHibernateTemplate().merge(hcb);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}


	public List<?> queryByCondition(BaseMessage baseMessage, HspConfigBaseinfo user) {
		try {
				String sql = " select a.id,a.itemCode,a.itemName,a.address,a.seqNo from HspConfigBaseinfo a where 1=1  ";
				if(user.getItemCode()!=null && user.getItemCode().trim().length() > 0){
					sql += " and a.itemCode like '%" + user.getItemCode().trim() + "%' ";
				}
				if(user.getItemName()!=null && user.getItemName().trim().length() > 0){
					sql += " and a.itemName like '%" + user.getItemName().trim() + "%' ";
				}
				if(user.getInputCode()!=null && user.getInputCode().trim().length() > 0){
					sql += " and a.inputCode like '" + user.getInputCode().trim().toUpperCase() + "%' ";
				}
				String orderBy = " order by ";
				String sortNumber = baseMessage.getSequenceNumber();
				if("1".equals(sortNumber)){
					orderBy += " a.itemCode ";
				}else if("2".equals(sortNumber)){
					orderBy += " a.itemName ";
				}else if("3".equals(sortNumber)){
					orderBy += " a.inputCode ";
				}else{
					orderBy += " a.itemCode ";
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

	public HspConfigBaseinfo findById(String id) {
		try{
			List<?> ls = getHibernateTemplate().find("select a from HspConfigBaseinfo a where a.id = ? ", id);
			if (ls != null && ls.size() > 0) {
				return (HspConfigBaseinfo)ls.get(0);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void delete(HspConfigBaseinfo hcb) {
		try{
			getHibernateTemplate().delete(hcb);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
