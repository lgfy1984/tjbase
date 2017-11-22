package com.tianjian.hsp.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.hsp.bean.BaseMessage;
import com.tianjian.hsp.bean.HspDeptBaseinfo;
import com.tianjian.hsp.dao.IHspDeptBaseinfoCXFDAO;

public class HspDeptBaseinfoCXFDAO extends HibernateDaoSupport implements IHspDeptBaseinfoCXFDAO{

	public void delete(HspDeptBaseinfo hdb) {
		try{
			getHibernateTemplate().delete(hdb);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public HspDeptBaseinfo findById(String deptCode, String hspConfigBaseinfoId) {
		try{
			List<?> ls = getHibernateTemplate().find("select a from HspDeptBaseinfo a where a.id.deptCode=? and a.id.hspConfigBaseinfoId=?", new Object[]{deptCode, hspConfigBaseinfoId});
			if(ls != null && ls.size() > 0){
				return (HspDeptBaseinfo)ls.get(0);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void merge(HspDeptBaseinfo hdb) {
		try{
			getHibernateTemplate().merge(hdb);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<?> queryByCondition(BaseMessage baseMessage, String seqNo, String hspCode, String hspName,
			String healthBureauCode, String socialSecurityBureauCode, String deptAttrCode, String deptAttrName, String deptCode,
			String deptName, String inputCode) {
		try {
			String sql = " select a from HspDeptBaseinfo a, HspConfigBaseinfo b where a.id.hspConfigBaseinfoId = b.id ";
			if(seqNo!=null && seqNo.trim().length() > 0){
				sql += " and a.seqNo = '" + seqNo.trim() + "' ";
			}
			if(hspCode!=null && hspCode.trim().length() > 0){
				sql += " and b.itemCode like '" + hspCode.trim() + "%' ";
			}
			if(hspName!=null && hspName.trim().length() > 0){
				sql += " and a.hspConfigBaseinfoName like '" + hspName.trim() + "%' ";
			}
			if(healthBureauCode!=null && healthBureauCode.trim().length() > 0){
				sql += " and a.healthBureauCode like '" + healthBureauCode.trim().toUpperCase() + "%' ";
			}
			if(socialSecurityBureauCode!=null && socialSecurityBureauCode.trim().length() > 0){
				sql += " and a.socialSecurityBureauCode like '" + socialSecurityBureauCode.trim().toUpperCase() + "%' ";
			}
			if(deptAttrCode!=null && deptAttrCode.trim().length() > 0){
				sql += " and a.deptAttrCode like '" + deptAttrCode.trim().toUpperCase() + "%' ";
			}
			if(deptAttrName!=null && deptAttrName.trim().length() > 0){
				sql += " and a.deptAttrName like '" + deptAttrName.trim().toUpperCase() + "%' ";
			}
			if(deptCode!=null && deptCode.trim().length() > 0){
				sql += " and a.deptCode like '" + deptCode.trim().toUpperCase() + "%' ";
			}
			if(deptName!=null && deptName.trim().length() > 0){
				sql += " and a.deptName like '" + deptName.trim().toUpperCase() + "%' ";
			}
			if(inputCode!=null && inputCode.trim().length() > 0){
				sql += " and a.inputCode like '" + inputCode.trim().toUpperCase() + "%' ";
			}
			String orderBy = " order by ";
			String sortNumber = baseMessage.getSequenceNumber();
			if("1".equals(sortNumber)){
				orderBy += " a.seqNo ";
			}else if("2".equals(sortNumber)){
				orderBy += " b.itemCode ";
			}else if("3".equals(sortNumber)){
				orderBy += " a.hspConfigBaseinfoName ";
			}else if("4".equals(sortNumber)){
				orderBy += " a.healthBureauCode ";
			}else if("5".equals(sortNumber)){
				orderBy += " a.socialSecurityBureauCode ";
			}else if("6".equals(sortNumber)){
				orderBy += " a.deptAttrCode ";
			}else if("7".equals(sortNumber)){
				orderBy += " a.deptAttrName ";
			}else if("8".equals(sortNumber)){
				orderBy += " a.deptCode ";
			}else if("9".equals(sortNumber)){
				orderBy += " a.deptName ";
			}else if("10".equals(sortNumber)){
				orderBy += " a.inputCode ";
			}else{
				orderBy += " a.seqNo ";
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

}
