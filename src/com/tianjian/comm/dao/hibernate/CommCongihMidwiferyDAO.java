package com.tianjian.comm.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.comm.bean.CommCongihMidwifery;
import com.tianjian.comm.dao.ICommCongihMidwiferyDAO;
import com.tianjian.comm.struts.form.CommCongihMidwiferyForm;

public class CommCongihMidwiferyDAO extends HibernateDaoSupport implements ICommCongihMidwiferyDAO {

	public int getCount(CommCongihMidwiferyForm form) {
		// TODO Auto-generated method stub
		
		try{
			int temp=0;
			String hql="select count(*) from CommCongihMidwifery c where 1=1";
			if(form.getHspClassCode()!=null&&form.getHspClassCode().trim().length()>0){
				hql +=" and c.hspClassCode='"+form.getHspClassCode().trim()+"'";
			}
			if(form.getHspConfigBaseinfoId()!=null&&form.getHspConfigBaseinfoId().trim().length()>0){
				hql +=" and c.hspConfigBaseinfoId='"+form.getHspConfigBaseinfoId().trim()+"'";
			}
			
			List<?> list=this.getHibernateTemplate().find(hql);
			if(list!=null&&list.size()>0){
				temp =Integer.parseInt(list.get(0).toString());
			}
			return temp;
		}catch(Exception e){
			return 0;
		}
	}

	public List<?> getSearch(CommCongihMidwiferyForm form, int count,
			int pageSize) {
		// TODO Auto-generated method stub
		try{
			
			String hql="from CommCongihMidwifery c where 1=1";
			if(form.getHspClassCode()!=null&&form.getHspClassCode().trim().length()>0){
				hql +=" and c.id.hspClassCode='"+form.getHspClassCode().trim()+"'";
			}
			if(form.getHspConfigBaseinfoId()!=null&&form.getHspConfigBaseinfoId().trim().length()>0){
				hql +=" and c.id.hspConfigBaseinfoId='"+form.getHspConfigBaseinfoId().trim()+"'";
			}
			if(form.getOrderNo()!=null&&form.getOrderNo().trim().length()>0){
				hql += " order by c."+form.getOrderNo().trim();
			}
			System.out.println(hql);
			
			Query query=this.getSession().createQuery(hql)
			.setMaxResults(pageSize)
			.setFirstResult(count);
			return query.list();
		}catch(Exception e){
			return null;
		}
	}

	public void add(CommCongihMidwifery com) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(com);
	}

	public void delete(CommCongihMidwifery com) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(com);
	}

	public CommCongihMidwifery findByIds(String hspConfigBaseinfoId, String hspCalssCode) {
		// TODO Auto-generated method stub
		CommCongihMidwifery comm=null;
		String hql="from CommCongihMidwifery c where c.id.hspConfigBaseinfoId=? and c.id.hspCalssCode=?";
		List<?> list=this.getHibernateTemplate().find(hql,new String[]{hspConfigBaseinfoId,hspCalssCode});
		if(list!=null&&list.size()>0){
			comm=(CommCongihMidwifery) list.get(0);
		}
		return comm;
	}

	public int getMaxNumber(String tableName, String columnName) {
		// TODO Auto-generated method stub
		int temp=0;
		if(this.getNumber(tableName)==0){
			temp=1;
		}else{
			String hql="select max(a."+columnName+") from "+tableName+" a where 1=1";
			List<?> list=this.getHibernateTemplate().find(hql);
			if(list!=null&&list.size()>0){
				temp=Integer.parseInt(list.get(0).toString())+1;
			}
		}
		return temp;
	}
	protected int getNumber(String tableName){
		String hql="select count(*) from "+tableName+" where 1=1";
		int temp=0;
		List<?> list=this.getHibernateTemplate().find(hql);
		if(list!=null&&list.size()>0){
			temp=Integer.parseInt(list.get(0).toString());
		}
		return temp;
	}
}
