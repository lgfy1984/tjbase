package com.tianjian.hsp.dao.hibernate;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.hsp.bean.HspStaffLogoutRecord;
import com.tianjian.hsp.dao.IHspStaffLogoutRecordDAO;
import com.tianjian.tenant.util.TenantIdHolder;

public class HspStaffLogoutRecordDAO extends HibernateDaoSupport implements IHspStaffLogoutRecordDAO {
	private static final Log log = LogFactory.getLog(HspStaffLogoutRecord.class);

	public void delete(HspStaffLogoutRecord hspStaffLogoutRecord) {
		try{
			this.getHibernateTemplate().delete(hspStaffLogoutRecord);
			log.debug("DAO public void delete(HspStaffLogoutRecord hsp) OK ");
		}catch(Exception e){
			log.error("DAO public void delete(HspStaffLogoutRecord hsp) error", e);
			e.printStackTrace();
		}
	}

	public List<?> findAll() {
		try{
			String tenantId = TenantIdHolder.get();
			List<?> list = this.getHibernateTemplate().find("from HspStaffLogoutRecord a where a.tenantId = ?", tenantId);
			log.debug("public List<?> findAll() (HspStaffLogoutRecord) success!");
			return list;
		}catch(Exception e){
			log.error("public List<?> findAll() (HspStaffLogoutRecord) error!",e);
			e.printStackTrace();
			return null;
		}
	}

	public HspStaffLogoutRecord findById(String id) {
		HspStaffLogoutRecord hspStaffLogoutRecord = null;
		try{
			String tenantId = TenantIdHolder.get();
			List<?> list = this.getHibernateTemplate().find("from HspStaffLogoutRecord a where a.id=? and a.tenantId = ?", new Object[]{id, tenantId});
			if(list!=null&&list.size()>0){
				hspStaffLogoutRecord=(HspStaffLogoutRecord) list.get(0);
			}
			log.debug("DAO public HspStaffLogoutRecord findById(String id) success!");
			return hspStaffLogoutRecord;
		}catch(Exception e){
			log.error("DAO public HspStaffLogoutRecord findById(String id) error!",e);
			e.printStackTrace();
			return null;
		}
	}

	public int getCount(String hspStaffBaseinfoId, String empNo, String name,
			String idNo) {
		try{
			String tenantId = TenantIdHolder.get();
			int count = 0;
			String sql = "SELECT COUNT(*) FROM HspStaffLogoutRecord a  where a.tenantId = '"+tenantId+"'";
			
			if(hspStaffBaseinfoId != null && hspStaffBaseinfoId.trim().length() > 0){
				sql += " and a.hspStaffBaseinfoId = '"+hspStaffBaseinfoId.trim()+"'";
			}if(empNo != null && empNo.trim().length() > 0){
				sql += " and a.empNo = '"+empNo.trim()+"'";
			}if(name != null && name.trim().length() > 0){
				sql += " and a.name = '"+name.trim()+"'";
			}if(idNo != null && idNo.trim().length() > 0){
				sql += " and a.idNo = '"+idNo.trim()+"'";
			}
			
	        List<?> list = this.getHibernateTemplate().find(sql);
			if(list != null && list.size() > 0){
				count = Integer.valueOf(String.valueOf(list.get(0))).intValue();
			}
			log.debug("DAO public int getCount(String id, String hspId, String name, String idNo) OK");
			return count;
		}catch(Exception e){
			log.error("DAO public int getCount(String id, String hspId, String name, String idNo) error", e);
			e.printStackTrace();
			return new Integer("0");
		}
	}

	public List<?> getData(String hspStaffBaseinfoId, String empNo,
			String name, String idNo,String order, int count, int pageSize) {
		try{
			String tenantId = TenantIdHolder.get();
			String sql = "select a FROM HspStaffLogoutRecord a  where a.tenantId = '"+tenantId+"'";
			
			if(hspStaffBaseinfoId != null && hspStaffBaseinfoId.trim().length() > 0){
				sql += " and a.hspStaffBaseinfoId = '"+hspStaffBaseinfoId.trim()+"'";
			}if(empNo != null && empNo.trim().length() > 0){
				sql += " and a.empNo = '"+empNo.trim()+"'";
			}if(name != null && name.trim().length() > 0){
				sql += " and a.name = '"+name.trim()+"'";
			}if(idNo != null && idNo.trim().length() > 0){
				sql += " and a.idNo = '"+idNo.trim()+"'";
			}
			
			Query query = this.getSession().createQuery(sql);
			query.setFirstResult(count);
			query.setMaxResults(pageSize);
			List<?> list = query.list();
			log.debug("DAO public int getCount(String hspStaffBaseinfoId, String empNo, String name,String idNo) OK ");
			return list;
		}catch(Exception e){
			log.error("DAO public int getCount(String hspStaffBaseinfoId, String empNo, String name,String idNo) error", e);
			e.printStackTrace();
			return null;
		}
	}

	public void save(HspStaffLogoutRecord hspStaffLogoutRecord) {
		try{
			String tenantId = TenantIdHolder.get();
			hspStaffLogoutRecord.setTenantId(tenantId);
			this.getHibernateTemplate().save(hspStaffLogoutRecord);
			log.debug("DAO public void save(HspStaffLogoutRecord hspStaffLogoutRecord) success!");
		}catch(Exception e){
			log.error("DAO public void save(HspStaffLogoutRecord hspStaffLogoutRecord) error!",e);
			e.printStackTrace();
		}
	}

	public void update(HspStaffLogoutRecord hspStaffLogoutRecord) {
		try{
			String tenantId = TenantIdHolder.get();
			hspStaffLogoutRecord.setTenantId(tenantId);
			this.getHibernateTemplate().update(hspStaffLogoutRecord);
			log.debug("DAO public void update(HspStaffLogoutRecord hspStaffLogoutRecord) success!");
		}catch(Exception e){
			log.error("DAO public void update(HspStaffLogoutRecord hspStaffLogoutRecord) error!",e);
			e.printStackTrace();
		}
	}

}
