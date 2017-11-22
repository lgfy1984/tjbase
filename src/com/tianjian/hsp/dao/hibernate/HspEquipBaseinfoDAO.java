package com.tianjian.hsp.dao.hibernate;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.comm.bean.CommConfigCountry;
import com.tianjian.comm.bean.CommEquipState;
import com.tianjian.comm.bean.CommEquipUnit;
import com.tianjian.comm.bean.CommEquipUseage;
import com.tianjian.hsp.bean.HspConfigBaseinfo;
import com.tianjian.hsp.bean.HspEquipBaseinfo;
import com.tianjian.hsp.dao.IHspEquipBaseinfoDAO;
import com.tianjian.hsp.struts.form.HspEquipBaseinfoForm;
import com.tianjian.tenant.util.TenantIdHolder;
import com.tianjian.util.Converter;

public class HspEquipBaseinfoDAO extends HibernateDaoSupport implements IHspEquipBaseinfoDAO{
	
	private static final Logger log = LogManager.getLogger(HspEquipBaseinfoDAO.class);

	private boolean isNull(String temp){
		return temp == null || temp.trim().length() <= 0;
	}
	private boolean isNotNull(String temp){
		return temp != null && temp.trim().length() > 0;
	}
	
	private String createQueryStr(HspEquipBaseinfoForm form, int queryType){
		String tenantId = TenantIdHolder.get();
		StringBuilder sql = new StringBuilder();
		if(queryType == 0){
			sql.append("select count(*) ");
		}else{
			sql.append("select e.id, h.item_name, d.dept_name, e.equip_name, e.equip_type, e.equip_code, e.supplier, e.productor, u.item_name as usageName");
		}
		sql.append(" from hsp.hsp_equip_baseinfo e inner join hsp.hsp_config_baseinfo h on e.hsp_code = h.item_code" )
			.append(" left join hsp.hsp_dept_baseinfo d on e.dept_code=d.dept_code and h.id = d.hsp_config_baseinfo_id ")
			.append(" left join comm.comm_equip_useage u on e.usage = u.item_code")
			.append(" where e.tenant_id = '"+tenantId+"' ");
		if(isNotNull(form.getHspCode())){
			sql.append(" and e.hsp_code = '").append(form.getHspCode().trim()).append("' ");
		}
		//如果机构代码为空，则根据机构名称模糊查询
		if(isNull(form.getHspCode()) && isNotNull(form.getHspName())){
			sql.append(" and h.item_name like '%").append(form.getHspName().trim()).append("%'");
		}
		if(isNotNull(form.getDeptCode())){
			sql.append(" and e.dept_code = '").append(form.getDeptCode().trim()).append("' ");
		}
		if(isNotNull(form.getEquipCode())){
			sql.append(" and e.equip_code = '").append(form.getEquipCode().trim()).append("' ");
		}
		if(isNotNull(form.getEquipName())){
			sql.append(" and e.equip_name like '%").append(form.getEquipName().trim()).append("%' ");
		}
		if(isNotNull(form.getUsage())){
			sql.append(" and e.usage = '").append(form.getUsage().trim()).append("'");
		}
		if(queryType == 1){
			sql.append(" order by ");
			if(form.getOrderNo().equals("1")){
				sql.append(" h.item_name ");
			}else if(form.getOrderNo().equals("2")){
				sql.append(" d.dept_name");
			}else if(form.getOrderNo().equals("3")){
				sql.append(" e.equip_name");
			}else if(form.getOrderNo().equals("4")){
				sql.append(" e.equip_type");
			}else if(form.getOrderNo().equals("5")){
				sql.append(" e.equip_code");
			}else if(form.getOrderNo().equals("6")){
				sql.append(" u.item_name");
			}else{
				sql.append(" e.product_date");
			}
			sql.append(", e.id ");//加上主键确保排序结果唯一
			if(form.getAsc().equals("1")){
				sql.append(" desc");
			}else{
				sql.append(" asc");
			}
		}
		return sql.toString();
	}
	@Override
	public List<?> findEquipList(HspEquipBaseinfoForm form,int curCount,int count) {
		
		try {
			String sql = this.createQueryStr(form, 1);
			Query q = getSession().createSQLQuery(sql);
			q.setFirstResult(curCount); 
			q.setMaxResults(count); 
			List<?> l=q.list();
			return l;
		}
		catch (Exception re) {
			log.error("getData error!", re);
			re.printStackTrace();
			return null;
		}
	}

	@Override
	public int getCount(HspEquipBaseinfoForm form) {
		int count = 0;
		try {
			String sql = this.createQueryStr(form, 0);
			List<?> list =  getSession().createSQLQuery(sql).list();
			if (list != null && list.size() > 0) {
				count = Integer.valueOf(String.valueOf(list.get(0)));
			}
		}
		catch (Exception re) {
			log.error("getCount error!", re);
			re.printStackTrace();
		}
		return count;
	}
	
	public HspEquipBaseinfo getById(String id) {
		try {
			String tenantId = TenantIdHolder.get();
			HspEquipBaseinfo temp = null;
			List<?> ls = getHibernateTemplate().find(" from HspEquipBaseinfo a where a.id = ? and a.tenantId = ?", new Object[]{id, tenantId});
			if (ls != null && ls.size() > 0) {
				temp = (HspEquipBaseinfo) ls.get(0);
			}
			return temp;
		}
		catch (Exception re) {
			log.error("getById error!", re);
			re.printStackTrace();
			return null;
		}
	}
	
	public String save(HspEquipBaseinfo heb) {
		try {
			String tenantId = TenantIdHolder.get();
			heb.setTenantId(tenantId);
			this.getHibernateTemplate().save(heb);
			return heb.getId();
		}
		catch (RuntimeException re) {
			log.error("save error!", re);
			throw re;
		}
	}
	
	public void update(HspEquipBaseinfo heb) {
		try {
			String tenantId = TenantIdHolder.get();
			heb.setTenantId(tenantId);
			this.getHibernateTemplate().update(heb);
		}
		catch (RuntimeException re) {
			log.error("update error!", re);
			throw re;
		}
	}
	
	public void delete(HspEquipBaseinfo heb) {
		try {
			this.getHibernateTemplate().delete(heb);
		}
		catch (RuntimeException re) {
			log.error("delete error!", re);
			throw re;
		}
	}
	
	@Override
	public String getHspNameByCode(String hspCode) {
		String tenantId = TenantIdHolder.get();
		String hspName = null;
		try{
			List<?> ls = getHibernateTemplate().find("select a.itemName from HspConfigBaseinfo a where a.itemCode = ? and a.tenantId = ?", new Object[]{hspCode, tenantId});
			if(ls != null && ls.size() >0){
				hspName = Converter.toBlank(ls.get(0));
			}
			log.debug("getHspNameByCode success!");
		}catch(Exception re){
			log.error("getHspNameByCode fail!",re);
			re.printStackTrace();
		}
		return hspName;
	}
	
	@Override
	public String[] getHspIdNameByCode(String hspCode) {
		String tenantId = TenantIdHolder.get();
		String[] id_name = new String[2];
		try{
			List<?> ls = getHibernateTemplate().find("select a.id, a.itemName from HspConfigBaseinfo a where a.itemCode = ? and a.tenantId = ?", new Object[]{hspCode, tenantId});
			if(ls != null && ls.size() >0){
				Object[] obj = (Object[])ls.get(0);
				id_name[0] = Converter.toBlank(obj[0]);
				id_name[1] = Converter.toBlank(obj[1]);
			}
			log.debug("getHspIdNameByCode success!");
		}catch(Exception re){
			log.error("getHspIdNameByCode fail!",re);
			re.printStackTrace();
		}
		return id_name;
	}
	
	@Override
	public String[] getHspCodeAndNameById(String hspId) {
		String tenantId = TenantIdHolder.get();
		String[] code_name = new String[2];
		try{
			List<?> ls = getHibernateTemplate().find("select a.itemCode, a.itemName from HspConfigBaseinfo a where a.id = ?  and a.tenantId = ?", new Object[]{hspId, tenantId});
			if(ls != null && ls.size() >0){
				Object[] obj = (Object[])ls.get(0);
				code_name[0] = Converter.toBlank(obj[0]);
				code_name[1] = Converter.toBlank(obj[1]);
			}
			log.debug("getHspCodeAndNameById success!");
		}catch(Exception re){
			log.error("getHspCodeAndNameById fail!",re);
			re.printStackTrace();
		}
		return code_name;
	}
	
	@Override
	public String getUnitName(String unitCode) {
		try{
			CommEquipUnit fac = null;
			List<?> ls = getHibernateTemplate().find(" from CommEquipUnit a where a.itemCode = ? ", unitCode);
			if(ls != null && ls.size() >0){
				fac = (CommEquipUnit)ls.get(0);
			}
			log.debug("getUnitName success!");
			return fac.getItemName();
		}catch(Exception re){
			log.error("getUnitName fail!",re);
			re.printStackTrace();
			return null; 
		}
	}


	@Override
	public String getUseageName(String useageCode) {
		try{
			CommEquipUseage fac = null;
			List<?> ls = getHibernateTemplate().find(" from CommEquipUseage a where a.itemCode = ? ", useageCode);
			if(ls != null && ls.size() >0){
				fac = (CommEquipUseage)ls.get(0);
			}
			log.debug("getUseageName success!");
			return fac.getItemName();
		}catch(Exception re){
			log.error("getUseageName fail!",re);
			re.printStackTrace();
			return null; 
		}
	}
	
	@Override
	public String getStateName(String stateName) {
		try{
			CommEquipState fac = null;
			List<?> ls = getHibernateTemplate().find(" from CommEquipState a where a.itemCode = ? ", stateName);
			if(ls != null && ls.size() >0){
				fac = (CommEquipState)ls.get(0);
			}
			log.debug("getStateName success!");
			return fac.getItemName();
		}catch(Exception re){
			log.error("getStateName fail!",re);
			re.printStackTrace();
			return null; 
		}
	}
	
	@Override
	public String getCountryName(String source) {
		try{
			CommConfigCountry fac = null;
			List<?> ls = getHibernateTemplate().find(" from CommConfigCountry a where a.itemCode = ? ", source);
			if(ls != null && ls.size() >0){
				fac = (CommConfigCountry)ls.get(0);
			}
			log.debug("getCountryName success!");
			return fac.getItemName();
		}catch(Exception re){
			log.error("getCountryName fail!",re);
			re.printStackTrace();
			return null; 
		}
	}
	
	public List<?> getUnitList() {
		try {
    		String sql = " from CommEquipUnit a  ";
	       
	    	Query q = getSession().createQuery(sql); 
			List<?> l=q.list();
			log.debug("getUnitList success!");
			return l;
    	} catch (Exception re) {
        	log.error("getUnitList fail",re);
        	re.printStackTrace(); 
        	return null;
        }
	}


	@Override
	public List<?> getUseageList() {
		try {
    		String sql = " from CommEquipUseage a  ";
	       
	    	Query q = getSession().createQuery(sql); 
			List<?> l=q.list();
			log.debug("getUseageList success!");
			return l;
    	} catch (Exception re) {
        	log.error("getUseageList fail",re);
        	re.printStackTrace(); 
        	return null;
        }
	}

	
	public List<?> getStateList(){
		
		try {
    		String sql = " from CommEquipState a  ";
	       
	    	Query q = getSession().createQuery(sql); 
			List<?> l=q.list();
			log.debug("getStateList success!");
			return l;
    	} catch (Exception re) {
        	log.error("getStateList fail",re);
        	re.printStackTrace(); 
        	return null;
        }
	}

}
