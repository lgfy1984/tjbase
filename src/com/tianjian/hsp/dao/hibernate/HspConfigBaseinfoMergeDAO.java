package com.tianjian.hsp.dao.hibernate;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.hsp.bean.HspConfigBaseinfo;
import com.tianjian.hsp.bean.HspIdList;
import com.tianjian.hsp.bean.HspMergeLog;
import com.tianjian.hsp.bean.HspStaffIdList;
import com.tianjian.hsp.dao.IHspConfigBaseinfoMergeDAO;
import com.tianjian.tenant.util.TenantIdHolder;
import com.tianjian.util.comm.TJInit;

public class HspConfigBaseinfoMergeDAO extends HibernateDaoSupport implements IHspConfigBaseinfoMergeDAO{
	private static final Log log = LogFactory.getLog(HspStaffBaseinfoMergeDAO.class);

	public HspConfigBaseinfo getVoById(String id) {
		try{
			List<?> list = this.getHibernateTemplate().find("from HspConfigBaseinfo a where a.id=?",id);
			HspConfigBaseinfo hspConfigBaseinfo = null;
			if(list!=null&&list.size()>0){
				hspConfigBaseinfo = (HspConfigBaseinfo) list.get(0);
			}
			log.debug("DAO public String getVoById(String id) success!");
			return hspConfigBaseinfo;
		}catch(Exception e){
			log.error("DAO public String getVoById(String id) error!",e);
			e.printStackTrace();
			return null;			
		}
	}

	public void saveLog(HspMergeLog vo) {
		try{
			String tenantId = TenantIdHolder.get();
			vo.setTenantId(tenantId);
			this.getHibernateTemplate().save(vo);
			log.debug("DAO public void saveLog(HspMergeLog vo) success!");
		}catch(Exception e){
			log.error("DAO public void saveLog(HspMergeLog vo) error!",e);
			e.printStackTrace();
		}
	}
	
	public List<?> init(String staffId){
		String tenantId = TenantIdHolder.get();
		String hql=" from SecurityDataObjectType t,SecurityDataObjectVsRoles t1,SecurityStaffBaseinfo s where" +
				" s.tenantId = '"+tenantId+"' and t.id=t1.sdotId and t1.securityStaffBaseinfoId=s.id and s.id=? order by t.id";
		return getHibernateTemplate().find(hql,new String[]{staffId});
	}

	public int getCount(String id,String itemName, String commConfigLocationId1,
			String commConfigLocationId2, String commConfigLocationId3,
			String inputCode, String checkValue) {
		try{
			String tenantId = TenantIdHolder.get();
			int count = 0;
			String sql = " select count(*) from HspConfigBaseinfo a  ";
			sql += " where a.tenantId = '"+tenantId+"' and a.hspType = "+ TJInit.getProperty("classFlag").trim() ;
			if(checkValue!=null&&checkValue.trim().length()>0&&id!=null&&id.trim().length()>0){
				String[] checkValues = checkValue.split(",");
				for(String value : checkValues){
					if(value.equals("itemName")){
						sql += " AND a.itemName = (SELECT b.itemName FROM HspConfigBaseinfo b WHERE b.id = '"+id.trim()+"') ";
					}if(value.equals("itemAddress")){
						if(commConfigLocationId1!=null && commConfigLocationId1.trim().length() > 0){
							sql += " AND a.commConfigLocationId1 = (SELECT b.commConfigLocationId1 FROM HspConfigBaseinfo b WHERE b.id = '"+id.trim()+"') ";
						}
						if(commConfigLocationId1!=null && commConfigLocationId1.trim().length() > 0){
							sql += " AND a.commConfigLocationId2 = (SELECT b.commConfigLocationId2 FROM HspConfigBaseinfo b WHERE b.id = '"+id.trim()+"') ";
						}
						if(commConfigLocationId1!=null && commConfigLocationId1.trim().length() > 0){
							sql += " AND a.commConfigLocationId3 = (SELECT b.commConfigLocationId3 FROM HspConfigBaseinfo b WHERE b.id = '"+id.trim()+"') ";
						}
					}
					sql += " AND a.id not in '"+id+"' ";
				}
			}else{
				if(itemName!=null && itemName.trim().length() > 0){
					sql += " and lower(a.itemName) like '%" + itemName.trim().toLowerCase() + "%' ";
				}
				if(inputCode!=null && inputCode.trim().length() > 0){
					sql += " and a.inputCode like '" + inputCode.trim().toUpperCase() + "%' ";
				}
				if(commConfigLocationId1!=null && commConfigLocationId1.trim().length() > 0){
					sql += " and a.commConfigLocationId1 like '" + commConfigLocationId1.trim() + "%' ";
				}
				if(commConfigLocationId2!=null && commConfigLocationId2.trim().length() > 0){
					sql += " and a.commConfigLocationId2 like '" + commConfigLocationId2.trim() + "%' ";
				}
				if(commConfigLocationId3!=null && commConfigLocationId3.trim().length() > 0){
					sql += " and a.commConfigLocationId3 like '" + commConfigLocationId3.trim() + "%' ";
				}
			}
			List<?> list = getHibernateTemplate().find(sql);
			if (list != null && list.size() > 0) {
				count = Integer.valueOf(String.valueOf(list.get(0))).intValue();
			}
			return count;
		}catch (Exception re) {
			log.error("getCount error!", re);
			re.printStackTrace();
			return 0;
		}	
	}

	public List<?> search(String id,String itemName, String commConfigLocationId1,
			String commConfigLocationId2, String commConfigLocationId3,
			String inputCode, String checkValue, String order, int count,
			int pageSize) {
		try{
			String tenantId = TenantIdHolder.get();
			String sql = " select a from HspConfigBaseinfo a  ";
			sql += " where a.tenantId = '"+tenantId+"' and a.hspType = "+ TJInit.getProperty("classFlag").trim() ;
			if(checkValue!=null&&checkValue.trim().length()>0&&id!=null&&id.trim().length()>0){
				String[] checkValues = checkValue.split(",");
				for(String value : checkValues){
					if(value.equals("itemName")){
						sql += " AND a.itemName = (SELECT b.itemName FROM HspConfigBaseinfo b WHERE b.id = '"+id.trim()+"') ";
					}if(value.equals("itemAddress")){
						if(commConfigLocationId1!=null && commConfigLocationId1.trim().length() > 0){
							sql += " AND a.commConfigLocationId1 = (SELECT b.commConfigLocationId1 FROM HspConfigBaseinfo b WHERE b.id = '"+id.trim()+"') ";
						}
						if(commConfigLocationId1!=null && commConfigLocationId1.trim().length() > 0){
							sql += " AND a.commConfigLocationId2 = (SELECT b.commConfigLocationId2 FROM HspConfigBaseinfo b WHERE b.id = '"+id.trim()+"') ";
						}
						if(commConfigLocationId1!=null && commConfigLocationId1.trim().length() > 0){
							sql += " AND a.commConfigLocationId3 = (SELECT b.commConfigLocationId3 FROM HspConfigBaseinfo b WHERE b.id = '"+id.trim()+"') ";
						}
					}
					sql += " AND a.id not in '"+id+"' ";
				}
			}else{
				if(itemName!=null && itemName.trim().length() > 0){
					sql += " and lower(a.itemName) like '%" + itemName.trim().toLowerCase() + "%' ";
				}
				if(inputCode!=null && inputCode.trim().length() > 0){
					sql += " and a.inputCode like '" + inputCode.trim().toUpperCase() + "%' ";
				}
				if(commConfigLocationId1!=null && commConfigLocationId1.trim().length() > 0){
					sql += " and a.commConfigLocationId1 like '" + commConfigLocationId1.trim() + "%' ";
				}
				if(commConfigLocationId2!=null && commConfigLocationId2.trim().length() > 0){
					sql += " and a.commConfigLocationId2 like '" + commConfigLocationId2.trim() + "%' ";
				}
				if(commConfigLocationId3!=null && commConfigLocationId3.trim().length() > 0){
					sql += " and a.commConfigLocationId3 like '" + commConfigLocationId3.trim() + "%' ";
				}
			}
			if(order!=null && order.trim().length()>0){
				sql += " order by "+order.trim();
			}
			Query query = this.getSession().createQuery(sql);
			query.setFirstResult(count);
			query.setMaxResults(pageSize);
			List<?> list = query.list();
			log.debug("DAO public List<?> search(String id,String itemName, String commConfigLocationId1,String commConfigLocationId2, String commConfigLocationId3,String inputCode, String checkValue, String order, int count,int pageSize) OK ");
			return list;
		}catch(Exception e){
			log.error("DAO public List<?> search(String id,String itemName, String commConfigLocationId1,String commConfigLocationId2, String commConfigLocationId3,String inputCode, String checkValue, String order, int count,int pageSize) error", e);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public HspIdList getHspIdListById(String id) {
		try{
			String tenantId = TenantIdHolder.get();
			List<?> list = this.getHibernateTemplate().find("from HspIdList a where a.hspConfigBaseinfoId=? and a.tenantId=?", new Object[]{id, tenantId});
			HspIdList hspIdList = null;
			if(list!=null&&list.size()>0){
				hspIdList = (HspIdList) list.get(0);
			}
			log.debug("DAO public String getHspIdListById(String id) success!");
			return hspIdList;
		}catch(Exception e){
			log.error("DAO public String getHspIdListById(String id) error!",e);
			e.printStackTrace();
			return null;			
		}
	}

	@Override
	public void updataHspIdList(HspIdList vo) {
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
