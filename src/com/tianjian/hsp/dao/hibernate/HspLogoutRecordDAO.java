package com.tianjian.hsp.dao.hibernate;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.hsp.bean.HspLogoutRecord;
import com.tianjian.hsp.dao.IHspLogoutRecordDAO;
import com.tianjian.tenant.util.TenantIdHolder;

public class HspLogoutRecordDAO  extends HibernateDaoSupport implements IHspLogoutRecordDAO {
	private static final Log log = LogFactory.getLog(HspLogoutRecord.class);

	public void delete(HspLogoutRecord hspLogoutRecord) {
		try{
			this.getHibernateTemplate().delete(hspLogoutRecord);
			log.debug("DAO public void delete(HspLogoutRecord hsp) OK ");
		}catch(Exception e){
			log.error("DAO public void delete(HspLogoutRecord hsp) error", e);
			e.printStackTrace();
		}
	}

	public List<?> findAll() {
		try{
			String tenantId = TenantIdHolder.get();
			List<?> list = this.getHibernateTemplate().find("from HspLogoutRecord a where a.tenantId = ?", tenantId);
			log.debug("public List<?> findAll() (HspLogoutRecord) success!");
			return list;
		}catch(Exception e){
			log.error("public List<?> findAll() (HspLogoutRecord) error!",e);
			e.printStackTrace();
			return null;
		}
	}

	public HspLogoutRecord findById(String id) {
		String tenantId = TenantIdHolder.get();
		HspLogoutRecord hspLogoutRecord = null;
		try{
			List<?> list = this.getHibernateTemplate().find("from HspLogoutRecord a where a.id=? and a.tenantId = ?", new Object[]{id, tenantId});
			if(list!=null&&list.size()>0){
				hspLogoutRecord = (HspLogoutRecord) list.get(0);
			}
			log.debug("DAO public HspLogoutRecord findById(String id) success!");
			return hspLogoutRecord;
		}catch(Exception e){
			log.error("DAO public HspLogoutRecord findById(String id) error!",e);
			e.printStackTrace();
			return null;
		}
	}

	public void save(HspLogoutRecord hspLogoutRecord) {
		try{
			String tenantId = TenantIdHolder.get();
			hspLogoutRecord.setTenantId(tenantId);
			this.getHibernateTemplate().save(hspLogoutRecord);
			log.debug("DAO public void save(HspLogoutRecord hspLogoutRecord) success!");
		}catch(Exception e){
			log.error("DAO public void save(HspLogoutRecord hspLogoutRecord) error!",e);
			e.printStackTrace();
		}
	}

	public void update(HspLogoutRecord hspLogoutRecord) {
		try{
			String tenantId = TenantIdHolder.get();
			hspLogoutRecord.setTenantId(tenantId);
			this.getHibernateTemplate().update(hspLogoutRecord);
			log.debug("DAO public void update(HspLogoutRecord hspLogoutRecord) success!");
		}catch(Exception e){
			log.error("DAO public void update(HspLogoutRecord hspLogoutRecord) error!",e);
			e.printStackTrace();
		}
	}

	@Override
	public int getCount(String hspConfigBaseinfoId, String itemCode,
			String itemName) {
		try{
			String tenantId = TenantIdHolder.get();
			int count = 0;
			String sql = "SELECT COUNT(*) FROM HspLogoutRecord a  where a.tenantId = '"+tenantId+"'";
			
			if(hspConfigBaseinfoId != null && hspConfigBaseinfoId.trim().length() > 0){
				sql += " and a.hspConfigBaseinfoId = '"+hspConfigBaseinfoId.trim()+"'";
			}if(itemCode != null && itemCode.trim().length() > 0){
				sql += " and a.itemCode = '"+itemCode.trim()+"'";
			}if(itemName != null && itemName.trim().length() > 0){
				sql += " and a.itemName = '"+itemName.trim()+"'";
			}
			
	        List<?> list = this.getHibernateTemplate().find(sql);
			if(list != null && list.size() > 0){
				count = Integer.valueOf(String.valueOf(list.get(0))).intValue();
			}
			log.debug("DAO public int getCount(String hspConfigBaseinfoId, String itemCode,String itemName) OK");
			return count;
		}catch(Exception e){
			log.error("DAO public int getCount(String hspConfigBaseinfoId, String itemCode,String itemName) error", e);
			e.printStackTrace();
			return new Integer("0");
		}
	}

	@Override
	public List<?> getData(String hspConfigBaseinfoId, String itemCode,
			String itemName, String order, int curCount, int pageSize) {
		try{
			String tenantId = TenantIdHolder.get();
			String sql = "select a FROM HspLogoutRecord a  where a.tenantId = '"+tenantId+"'";
			
			if(hspConfigBaseinfoId != null && hspConfigBaseinfoId.trim().length() > 0){
				sql += " and a.hspConfigBaseinfoId = '"+hspConfigBaseinfoId.trim()+"'";
			}if(itemCode != null && itemCode.trim().length() > 0){
				sql += " and a.itemCode = '"+itemCode.trim()+"'";
			}if(itemName != null && itemName.trim().length() > 0){
				sql += " and a.itemName = '"+itemName.trim()+"'";
			}
			
			Query query = this.getSession().createQuery(sql);
			query.setFirstResult(curCount);
			query.setMaxResults(pageSize);
			List<?> list = query.list();
			log.debug("DAO public int getCount(String hspConfigBaseinfoId, String itemCode,String itemName, String order, int curCount, int pageSize) OK ");
			return list;
		}catch(Exception e){
			log.error("DAO public int getCount(String hspConfigBaseinfoId, String itemCode,String itemName, String order, int curCount, int pageSize) error", e);
			e.printStackTrace();
			return null;
		}
	}

}
