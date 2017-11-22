package com.tianjian.hsp.dao.hibernate;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.hsp.bean.HspStaffBaseinfo;
import com.tianjian.hsp.bean.HspStaffIdList;
import com.tianjian.hsp.bean.HspStaffMergeLog;
import com.tianjian.hsp.dao.IHspStaffBaseinfoMergeDAO;
import com.tianjian.tenant.util.TenantIdHolder;

public class HspStaffBaseinfoMergeDAO extends HibernateDaoSupport implements IHspStaffBaseinfoMergeDAO{
	
	private static final Log log = LogFactory.getLog(HspStaffBaseinfoMergeDAO.class);

	@Override
	public int getCount(String id,String name, String commConfigSexId, String birthday,
			String idNo, String checkValue) {
		try{
			String tenantId = TenantIdHolder.get();
			int count = 0;
			String sql = "SELECT COUNT(*) FROM HspStaffBaseinfo a where a.tenantId = '"+tenantId+"' ";
			if(checkValue!=null&&checkValue.trim().length()>0&&id!=null&&id.trim().length()>0){
				String[] checkValues = checkValue.split(",");
				for(String value : checkValues){
					if(value.equals("name")){
						sql += " AND a.name = (SELECT b.name FROM HspStaffBaseinfo b WHERE b.id = '"+id.trim()+"') ";
					}if(value.equals("commConfigSexId")){
						sql += " AND a.commConfigSexId = (SELECT b.commConfigSexId FROM HspStaffBaseinfo b WHERE b.id = '"+id.trim()+"') ";
					}if(value.equals("dateOfBirth")){
						sql += " AND a.birthday = (SELECT b.birthday FROM HspStaffBaseinfo b WHERE b.id = '"+id.trim()+"') ";
					}if(value.equals("idNo")){
						sql += " AND a.idNo = (SELECT b.idNo FROM HspStaffBaseinfo b WHERE b.id = '"+id.trim()+"') ";
					}
					sql += " AND a.id not in '"+id+"' ";
					
				}
			}else{
				if(name!=null&&name.trim().length()>0){
					sql += " AND a.name = '"+name.trim()+"' ";
				}
				if(commConfigSexId!=null&&commConfigSexId.trim().length()>0){
					sql += " AND a.commConfigSexId = '"+commConfigSexId.trim()+"' ";
				}
				if(birthday!=null&&birthday.trim().length()>0){
					sql += " AND a.birthday = '"+birthday.trim()+"' ";
				}
				if(idNo!=null&&idNo.trim().length()>0){
					sql += " AND a.idNo = '"+idNo.trim()+"' ";
				}
			}
			List<?> list = this.getHibernateTemplate().find(sql);
			if(list != null && list.size() > 0){
				count = Integer.valueOf(String.valueOf(list.get(0))).intValue();
			}
			log.debug("DAO public int getCount(String id,String name, String commConfigSexId, String birthday,String idNo, String checkValue) OK");
			return count;
		}catch(Exception e){
			log.error("DAO public int getCount(String id,String name, String commConfigSexId, String birthday,String idNo, String checkValue) error", e);
			e.printStackTrace();
			return new Integer("0");
		}
	}

	@Override
	public String querySexNameById(String commConfigSexId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> search(String id,String name, String commConfigSexId, String birthday,
			String idNo, String checkValue, String order, int curCount,
			int pageSize) {
		try{
			String tenantId = TenantIdHolder.get();
			String sql = "SELECT a FROM HspStaffBaseinfo a where a.tenantId = '"+tenantId+"' ";
			if(checkValue!=null&&checkValue.trim().length()>0&&id!=null&&id.trim().length()>0){
				String[] checkValues = checkValue.split(",");
				for(String value : checkValues){
					if(value.equals("name")){
						sql += " AND a.name = (SELECT b.name FROM HspStaffBaseinfo b WHERE b.id = '"+id.trim()+"') ";
					}if(value.equals("commConfigSexId")){
						sql += " AND a.commConfigSexId = (SELECT b.commConfigSexId FROM HspStaffBaseinfo b WHERE b.id = '"+id.trim()+"') ";
					}if(value.equals("dateOfBirth")){
						sql += " AND a.birthday = (SELECT b.birthday FROM HspStaffBaseinfo b WHERE b.id = '"+id.trim()+"') ";
					}if(value.equals("idNo")){
						sql += " AND a.idNo = (SELECT b.idNo FROM HspStaffBaseinfo b WHERE b.id = '"+id.trim()+"') ";
					}
					sql += " AND a.id not in '"+id+"' ";
					
				}
			}else{
				if(name!=null&&name.trim().length()>0){
					sql += " AND a.name = '"+name.trim()+"' ";
				}
				if(commConfigSexId!=null&&commConfigSexId.trim().length()>0){
					sql += " AND a.commConfigSexId = '"+commConfigSexId.trim()+"' ";
				}
				if(birthday!=null&&birthday.trim().length()>0){
					sql += " AND a.birthday = '"+birthday.trim()+"' ";
				}
				if(idNo!=null&&idNo.trim().length()>0){
					sql += " AND a.idNo = '"+idNo.trim()+"' ";
				}
			}
			if(order!=null && order.trim().length()>0){
				sql += " order by "+order.trim();
			}
			Query query = this.getSession().createQuery(sql);
			query.setFirstResult(curCount);
			query.setMaxResults(pageSize);
			List<?> list = query.list();
			log.debug("DAO public List<?> search(String id,String name, String commConfigSexId, String birthday,String idNo, String checkValue, String order, int curCount,int pageSize) OK ");
			return list;
		}catch(Exception e){
			log.error("DAO public List<?> search(String id,String name, String commConfigSexId, String birthday,String idNo, String checkValue, String order, int curCount,int pageSize) error", e);
			e.printStackTrace();
			return null;
		}
	}
	
	public List<?> sexList() {
		return this.getHibernateTemplate().find("select a from CommConfigSex a");
	}
	
	public void saveLog(HspStaffMergeLog vo) {
		try{
			String tenantId = TenantIdHolder.get();
			vo.setTenantId(tenantId);
			this.getHibernateTemplate().save(vo);
			log.debug("DAO public void saveLog(HspStaffMergeLog vo) success!");
		}catch(Exception e){
			log.error("DAO public void saveLog(HspStaffMergeLog vo) error!",e);
			e.printStackTrace();
		}
	}
	
	public HspStaffBaseinfo getVoById(String id) {
		try{
			String tenantId = TenantIdHolder.get();
			String sql = "select a from HspStaffBaseinfo a where a.id = ? and a.tenantId = ? ";
			List<?> list = this.getHibernateTemplate().find(sql, new Object[]{id, tenantId});
			HspStaffBaseinfo hspStaffBaseinfo = null;
			if(list!=null&&list.size()>0){
				hspStaffBaseinfo = (HspStaffBaseinfo) list.get(0);
			}
			log.debug("DAO public String getVoById(String id) success!");
			return hspStaffBaseinfo;
		}catch(Exception e){
			log.error("DAO public String getVoById(String id) error!",e);
			e.printStackTrace();
			return null;			
		}
	}
	
	public HspStaffIdList getHspStaffIdListById(String id) {
		try{
			String tenantId = TenantIdHolder.get();
			List<?> list = this.getHibernateTemplate().find("from HspStaffIdList a where a.hspStaffBaseinfoId=? and a.tenantId = ?", new Object[]{id, tenantId});
			HspStaffIdList hspStaffIdList = null;
			if(list!=null&&list.size()>0){
				hspStaffIdList = (HspStaffIdList) list.get(0);
			}
			log.debug("DAO public String getHspStaffIdListById(String id) success!");
			return hspStaffIdList;
		}catch(Exception e){
			log.error("DAO public String getHspStaffIdListById(String id) error!",e);
			e.printStackTrace();
			return null;			
		}
	}
	
	public void updataHspStaffIdList(HspStaffIdList vo){
		try {
			String tenantId = TenantIdHolder.get();
			vo.setTenantId(tenantId);
			getHibernateTemplate().merge(vo);
		}
		catch (Exception re) {
			log.error("update error!", re);
			re.printStackTrace();
		}
	}
}
