package com.tianjian.comm.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.comm.bean.CommConfigPopulationNature;
import com.tianjian.comm.dao.ICommConfigPopulationNatureDAO;
import com.tianjian.comm.struts.form.CommConfigPopulationNatureForm;

public class CommConfigPopulationNatureDAO extends HibernateDaoSupport implements
		ICommConfigPopulationNatureDAO {

	public int getCount(CommConfigPopulationNatureForm form) {
		// TODO Auto-generated method stub
		try{			
			int temp=0;
			String hql="select count(*) from CommConfigPopulationNature c where 1=1";
			if(form.getItemCode()!=null&&form.getItemCode().trim().length()>0){
				hql +=" and c.itemCode='"+form.getItemCode().trim()+"'";
			}
			if(form.getItemName()!=null&&form.getItemName().trim().length()>0){
				hql +=" and c.itemName like '%"+form.getItemName().trim()+"%'";
			}
			if(form.getInputCode()!=null&&form.getInputCode().trim().length()>0){
				hql +=" and c.inputCode like '%"+form.getInputCode().trim().toUpperCase()+"%'";
			}
			List<?> list=this.getHibernateTemplate().find(hql);
			if(list!=null&&list.size()>0){
				temp=Integer.parseInt(list.get(0).toString());
			}
			return temp;
		}catch(Exception e){
			return 0;
		}
	}

	public List<?> getSearch(CommConfigPopulationNatureForm form, int count,
			int pageSize) {
		// TODO Auto-generated method stub
		try{			
			String hql="from CommConfigPopulationNature c where 1=1";
			if(form.getItemCode()!=null&&form.getItemCode().trim().length()>0){
				hql +=" and c.itemCode='"+form.getItemCode().trim()+"'";
			}
			if(form.getItemName()!=null&&form.getItemName().trim().length()>0){
				hql +=" and c.itemName like '%"+form.getItemName().trim()+"%'";
			}
			if(form.getInputCode()!=null&&form.getInputCode().trim().length()>0){
				hql +=" and c.inputCode like '%"+form.getInputCode().trim().toUpperCase()+"%'";
			}
			if(form.getOrderNo()!=null&&form.getOrderNo().trim().length()>0){
				hql +=" order by c."+form.getOrderNo().trim();
			}
			Query query=this.getSession().createQuery(hql)
			.setMaxResults(pageSize)
			.setFirstResult(count);
			List<?> list=query.list();
			return list;
		}catch(Exception e){
			return null;
		}
	}

	public void delete(CommConfigPopulationNature bean) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(bean);
	}

	public CommConfigPopulationNature getByItemCode(String itemCode) {
		// TODO Auto-generated method stub
		CommConfigPopulationNature comm=null;
		List<?> list=this.getHibernateTemplate().find("from CommConfigPopulationNature c where c.itemCode=?",new String[]{itemCode});
		if(list!=null&&list.size()>0){
			comm=(CommConfigPopulationNature) list.get(0);
		}
		return comm;
	}

	public void save(CommConfigPopulationNature bean) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(bean);
	}

	public void update(CommConfigPopulationNature bean) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().merge(bean);
	}

}
