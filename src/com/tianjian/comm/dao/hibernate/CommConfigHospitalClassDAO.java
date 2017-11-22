package com.tianjian.comm.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.comm.bean.CommConfigHospitalClass;
import com.tianjian.comm.dao.ICommConfigHospitalClassDAO;
import com.tianjian.comm.struts.form.CommConfigHospitalClassForm;

public class CommConfigHospitalClassDAO extends HibernateDaoSupport implements ICommConfigHospitalClassDAO {

	public void delete(CommConfigHospitalClass bean) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(bean);
	}

	public CommConfigHospitalClass getByItemCode(String classCode) {
		// TODO Auto-generated method stub
		CommConfigHospitalClass comm=null;
		List<?> list=this.getHibernateTemplate().find("from CommConfigHospitalClass c where c.classCode=?",new String[]{classCode});
		if(list!=null&&list.size()>0){
			comm=(CommConfigHospitalClass) list.get(0);
		}
		return comm;
	}

	public int getCount(CommConfigHospitalClassForm form) {
		// TODO Auto-generated method stub
		try{			
			int temp=0;
			String hql="select count(*) from CommConfigHospitalClass c where 1=1";
			if(form.getClassCode()!=null&&form.getClassCode().trim().length()>0){
				hql +=" and c.classCode='"+form.getClassCode().trim()+"'";
			}
			if(form.getClassName()!=null&&form.getClassName().trim().length()>0){
				hql +=" and c.className like '%"+form.getClassName().trim()+"%'";
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

	public List<?> getSearch(CommConfigHospitalClassForm form, int count,
			int pageSize) {
		// TODO Auto-generated method stub
		try{			
			String hql="from CommConfigHospitalClass c where 1=1";
			if(form.getClassCode()!=null&&form.getClassCode().trim().length()>0){
				hql +=" and c.classCode='"+form.getClassCode().trim()+"'";
			}
			if(form.getClassName()!=null&&form.getClassName().trim().length()>0){
				hql +=" and lower(c.className) like '%"+form.getClassName().trim().toLowerCase()+"%'";
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

	public void save(CommConfigHospitalClass bean) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(bean);
	}

	public void update(CommConfigHospitalClass bean) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().merge(bean);
	}

	public List<?> findHspClassList(String flag,String input,int start,int pageSize) {
		// TODO Auto-generated method stub
		String hql="from CommConfigHospitalClass c where 1=1";
		if(input!=null&&input.trim().length()>0&&flag.equals("1")){
			hql += " and upper(c.inputCode) like '%"+input.toUpperCase()+"%'";
		}
		if(input!=null&&input.trim().length()>0&&flag.equals("2")){
			hql += " and upper(c.classCode) like '%"+input.toUpperCase()+"%'";
		}
		if(input!=null&&input.trim().length()>0&&flag.equals("3")){
			hql += " and c.className like '%"+input+"%'";
		}
		
		Query q = getSession().createQuery(hql);
		q.setFirstResult(start); 
		q.setMaxResults(pageSize); 
		List<?> list=q.list();;
		return list;
	}

}
