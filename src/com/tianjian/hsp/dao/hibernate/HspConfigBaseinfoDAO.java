package com.tianjian.hsp.dao.hibernate;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.hsp.bean.HspConfigBaseinfo;
import com.tianjian.hsp.dao.IHspConfigBaseinfoDAO;
import com.tianjian.hsp.struts.form.HspConfigBaseinfoForm;
import com.tianjian.hsp.struts.servlet.HspInit;
import com.tianjian.tenant.util.TenantIdHolder;
import com.tianjian.util.ResourcesUtil;
import com.tianjian.util.comm.TJInit;

public class HspConfigBaseinfoDAO extends HibernateDaoSupport implements IHspConfigBaseinfoDAO {

	private static final Logger log = LogManager.getLogger(HspConfigBaseinfoDAO.class);
	/**通过Id获取医疗机构信息*/
	public HspConfigBaseinfo getById(String id) {
		try {
			HspConfigBaseinfo temp = null;
			List<?> ls = getHibernateTemplate().find(" from HspConfigBaseinfo a where a.id = ? ", id);
			if (ls != null && ls.size() > 0) {
				temp = (HspConfigBaseinfo) ls.get(0);
			}
			return temp;
		}
		catch (Exception re) {
			log.error("getById error!", re);
			re.printStackTrace();
			return null;
		}
	}

	public HspConfigBaseinfo getByItemCode(String itemCode) {
		try {
			String tenantId = TenantIdHolder.get();
			if(tenantId == null) return null;
			HspConfigBaseinfo temp = null;
			Query query = getSession().createQuery(" from HspConfigBaseinfo a where a.itemCode = ? and a.tenantId = ?");
			query.setString(0, itemCode);
			query.setString(1, tenantId);
			List<?> ls = query.list();
			if (ls != null && ls.size() > 0) {
				temp = (HspConfigBaseinfo) ls.get(0);
			}
			return temp;
		}
		catch (Exception re) {
			log.error("getById error!", re);
			re.printStackTrace();
			return null;
		}
	}

	/**id获取医疗机构名称*/
	public String getItemName(String id) {
		try {
			String tenantId = TenantIdHolder.get();
			if(tenantId == null) return null;
			String temp = null;
			Query query = getSession().createQuery("select a.itemName from HspConfigBaseinfo a where a.id = ? and a.tenantId = ?");
			query.setString(0, id);
			query.setString(1, tenantId);
			List<?> ls = query.list();
			if (ls != null && ls.size() > 0) {
				temp = (String) ls.get(0);
			}
			return temp;
		}
		catch (Exception re) {
			log.error("getItemName error!", re);
			return null;
		}
	}  
	
	/**itemCode获取医疗机构名称*/
	public String getNameByCode(String itemCode) {
		try {
			String tenantId = TenantIdHolder.get();
			if(tenantId == null) return null;
			String temp = null;
			Query query = getSession().createQuery("select a.itemName from HspConfigBaseinfo a where a.itemCode = ? and a.tenantId = ?");
			query.setString(0, itemCode);
			query.setString(1, tenantId);
			List<?> ls = query.list();
			if (ls != null && ls.size() > 0) {
				temp = (String) ls.get(0);
			}
			return temp;
		}
		catch (Exception re) {
			log.error("getItemName error!", re);
			return null;
		}
	}  
	
	/**保存医疗机构名称信息*/
	public String save(HspConfigBaseinfo hspConfigBaseinfo) {
		try {
			String tenantId = TenantIdHolder.get();
			hspConfigBaseinfo.setTenantId(tenantId);
			getHibernateTemplate().save(hspConfigBaseinfo);
			return hspConfigBaseinfo.getId();
		}
		catch (RuntimeException re) {
			log.error("save error!", re);
			throw re;
		}
	}

	/**更新医疗机构名称信息*/
	public void update(HspConfigBaseinfo hspConfigBaseinfo) {
		try {
			String tenantId = TenantIdHolder.get();
			hspConfigBaseinfo.setTenantId(tenantId);
			getHibernateTemplate().update(hspConfigBaseinfo);
		}
		catch (RuntimeException re) {
			log.error("update error!", re);
			throw re;
		}
	}

	/**删除医疗机构信息*/
	public void delete(HspConfigBaseinfo hspConfigBaseinfo) {
		try {
			getHibernateTemplate().delete(hspConfigBaseinfo);
		}
		catch (RuntimeException re) {
			log.error("delete error!", re);
			throw re;
		}
	}
	/**取得所有的医疗机构*/
	public List<?> findAll(){
		try {
			String tenantId = TenantIdHolder.get();
			if(tenantId == null) return null;
			String sql = " from HspConfigBaseinfo a where a.tenantId = ?";

			Query q = getSession().createQuery(sql); 
			q.setString(0, tenantId);
			List<?> l=q.list();
			log.debug("findAll success!");
			return l;
		} catch (Exception re) {
			log.error("findAll fail",re);
			re.printStackTrace(); 
			return null;
		}
	}

	/**取得除去社区卫生服务站所有的医疗机构*/
	public List<?> findAllRemoveStation(){
		try {
			String tenantId = TenantIdHolder.get();
			if(tenantId == null) return null;
			String sql = " from HspConfigBaseinfo a where a.commConfigUnittypeId not in("+HspInit.getProperty("COMM_CONFIG_UNITTYPE_ITEM_CODE")+")";
			sql  += " and a.tenantId = '"+tenantId+"'";

			Query q = getSession().createQuery(sql); 
			List<?> l=q.list();
			log.debug("findAllRemoveStation success!");
			return l;
		} catch (Exception re) {
			log.error("findAllRemoveStation fail",re);
			re.printStackTrace(); 
			return null;
		}
	}

	/**获取当前页面医疗机构记录列表*/
	public List<?> getData(String colmun,String sttaf,String id,String itemCode,String parentItemCode, String itemName, String inputCode,String seqNo,String commConfigHospitalTypeId,String commConfigUnitgradeId,String commConfigUnittypeId,String commConfigFtManageId,String commConfigEconkindId,String commConfigLocationTownId,String commClvId, String orderNo, int curCount, int quChuCount) {
		try {
			String tenantId = TenantIdHolder.get();
			if(tenantId == null) return null;
		//	String sql = " select a from HspConfigBaseinfo a where 1=1  and a."+colmun+"='"+sttaf+"' ";
			String sql = " select a from HspConfigBaseinfo a where a.tenantId = '"+tenantId+"'  ";
		//	sql += " where 1=1 ";
			if(id!=null && id.trim().length() > 0){
				sql += " and a.id = '" + id.trim() + "' ";
			}
			if(itemCode!=null && itemCode.trim().length() > 0){
				sql += " and a.itemCode like '%" + itemCode.trim() + "%' ";
			}
			if(parentItemCode!=null && parentItemCode.trim().length() > 0){
				sql += " and a.parentItemCode = '" + parentItemCode.trim() + "' ";
			}
			if(itemName!=null && itemName.trim().length() > 0){
				sql += " and a.itemName like '%" + itemName.trim() + "%' ";
			}
			if(inputCode!=null && inputCode.trim().length() > 0){
				sql += " and a.inputCode like '" + inputCode.trim().toUpperCase() + "%' ";
			}
			if(seqNo!=null && seqNo.trim().length() > 0){
				sql += " and a.seqNo = '" + seqNo.trim() + "' ";
			}
			if(commConfigHospitalTypeId!=null && commConfigHospitalTypeId.trim().length() > 0){
				sql += " and a.commConfigHospitalTypeId = '" + commConfigHospitalTypeId.trim() + "' ";
			}
			if(commConfigUnitgradeId!=null && commConfigUnitgradeId.trim().length() > 0){
				sql += " and a.commConfigUnitgradeId = '" + commConfigUnitgradeId.trim() + "' ";
			}
			if(commConfigUnittypeId!=null && commConfigUnittypeId.trim().length() > 0){
				sql += " and a.commConfigUnittypeId = '" + commConfigUnittypeId.trim() + "' ";
			}
			if(commConfigFtManageId!=null && commConfigFtManageId.trim().length() > 0){
				sql += " and a.commConfigFtManageId = '" + commConfigFtManageId.trim() + "' ";
			}
			if(commConfigEconkindId!=null && commConfigEconkindId.trim().length() > 0){
				sql += " and a.commConfigEconkindId = '" + commConfigEconkindId.trim() + "' ";
			}
			if(commConfigLocationTownId!=null && commConfigLocationTownId.trim().length() > 0){
				sql += " and a.commConfigLocationTownId = '" + commConfigLocationTownId.trim() + "' ";
			}
			if(commClvId!=null && commClvId.trim().length() > 0){
				sql += " and a.commClvId = '" + commClvId.trim() + "' ";
			}
			if(orderNo!=null && orderNo.trim().length()>0){
				sql += " order by "+orderNo.trim();
			}
			Query q = getSession().createQuery(sql);
			q.setFirstResult(curCount); 
			q.setMaxResults(quChuCount); 
			List<?> l=q.list();

			return l;
		}
		catch (Exception re) {
			log.error("getData error!", re);
			re.printStackTrace();
			return null;
		}
	}
	public List<?> getDataAll(String id,String itemCode, String itemName, String inputCode,String seqNo,
			String commConfigHospitalTypeId, String orderNo, String hspConfig,int curCount, int pageSize) {
		try {
			String tenantId = TenantIdHolder.get();
			if(tenantId == null) return null;
			String sql = " select a from HspConfigBaseinfo a  ";
			sql += "where a.tenantId = '"+tenantId+"'  and a.hspType = "+ TJInit.getProperty("classFlag").trim() ;
			
			 if(TJInit.getProperty("classFlag").trim()!=null && TJInit.getProperty("classFlag").trim().equals("1")){
			    sql +=" and a.hspConfigBaseinfoId3 = (select b.hspConfigBaseinfoId3 from HspConfigBaseinfo b where b.id = '"+ hspConfig +"') " ;
			 }else if(TJInit.getProperty("classFlag").trim()!=null && TJInit.getProperty("classFlag").trim().equals("2")){
			    sql +=" and a.hspConfigBaseinfoId2 = (select b.hspConfigBaseinfoId2 from HspConfigBaseinfo b where b.id = '"+ hspConfig +"') " ;
			 }else{
				sql +=" ";
			 }
			if(id!=null && id.trim().length() > 0){
				sql += " and a.id = '" + id.trim() + "' ";
			}
			if(itemCode!=null && itemCode.trim().length() > 0){
				sql += " and a.itemCode like '%" + itemCode.trim() + "%' ";
			}
//			if(parentItemCode!=null && parentItemCode.trim().length() > 0){
//				sql += " and a.parentItemCode = '" + parentItemCode.trim() + "' ";
//			}
			if(itemName!=null && itemName.trim().length() > 0){
				sql += " and lower(a.itemName) like '%" + itemName.trim().toLowerCase() + "%' ";
			}
			if(inputCode!=null && inputCode.trim().length() > 0){
				sql += " and a.inputCode like '" + inputCode.trim().toUpperCase() + "%' ";
			}
			if(seqNo!=null && seqNo.trim().length() > 0){
				sql += " and a.seqNo = '" + seqNo.trim() + "' ";
			}
			if(commConfigHospitalTypeId!=null && commConfigHospitalTypeId.trim().length() > 0){
				sql += " and a.commConfigHospitalTypeId = '" + commConfigHospitalTypeId.trim() + "' ";
			}
//			if(commConfigUnitgradeId!=null && commConfigUnitgradeId.trim().length() > 0){
//				sql += " and a.commConfigUnitgradeId = '" + commConfigUnitgradeId.trim() + "' ";
//			}
//			if(commConfigUnittypeId!=null && commConfigUnittypeId.trim().length() > 0){
//				sql += " and a.commConfigUnittypeId = '" + commConfigUnittypeId.trim() + "' ";
//			}
//			if(commConfigFtManageId!=null && commConfigFtManageId.trim().length() > 0){
//				sql += " and a.commConfigFtManageId = '" + commConfigFtManageId.trim() + "' ";
//			}
//			if(commConfigEconkindId!=null && commConfigEconkindId.trim().length() > 0){
//				sql += " and a.commConfigEconkindId = '" + commConfigEconkindId.trim() + "' ";
//			}
//			if(commConfigLocationTownId!=null && commConfigLocationTownId.trim().length() > 0){
//				sql += " and a.commConfigLocationTownId = '" + commConfigLocationTownId.trim() + "' ";
//			}
//			if(commClvId!=null && commClvId.trim().length() > 0){
//				sql += " and a.commClvId = '" + commClvId.trim() + "' ";
//			}
			if(orderNo!=null && orderNo.trim().length()>0){
				sql += " order by "+orderNo.trim();
			}

			Query q = getSession().createQuery(sql);
			q.setFirstResult(curCount); 
			q.setMaxResults(pageSize);
			List<?> l=q.list();

			return l;
		}
		catch (Exception re) {
			log.error("getData error!", re);
			re.printStackTrace();
			return null;
		}
	}

	
	public List<?> getData(String colmun,String sttaf,String id,String itemCode,String parentItemCode, String itemName, String inputCode,String seqNo,String commConfigHospitalTypeId,String commConfigUnitgradeId,String commConfigUnittypeId,String commConfigFtManageId,String commConfigEconkindId,String commConfigLocationTownId,String commClvId, String orderNo) {
		try{
			String tenantId = TenantIdHolder.get();
			if(tenantId == null) return null;
//			String sql = " select a from HspConfigBaseinfo a where 1=1  and a."+colmun+"='"+sttaf+"' ";
			String sql = " select a from HspConfigBaseinfo a where a.tenantId = '"+tenantId+"'   ";
		//	sql += " where 1=1 ";
				if(id!=null && id.trim().length() > 0){
					sql += " and a.id = '" + id.trim() + "' ";
				}
				if(itemCode!=null && itemCode.trim().length() > 0){
					sql += " and a.itemCode like '%" + itemCode.trim() + "%' ";
				}
				if(parentItemCode!=null && parentItemCode.trim().length() > 0){
					sql += " and a.parentItemCode = '" + parentItemCode.trim() + "' ";
				}
				if(itemName!=null && itemName.trim().length() > 0){
					sql += " and a.itemName like '%" + itemName.trim() + "%' ";
				}
				if(inputCode!=null && inputCode.trim().length() > 0){
					sql += " and a.inputCode like '" + inputCode.trim().toUpperCase() + "%' ";
				}
				if(seqNo!=null && seqNo.trim().length() > 0){
					sql += " and a.seqNo = '" + seqNo.trim() + "' ";
				}
				if(commConfigHospitalTypeId!=null && commConfigHospitalTypeId.trim().length() > 0){
					sql += " and a.commConfigHospitalTypeId = '" + commConfigHospitalTypeId.trim() + "' ";
				}
				if(commConfigUnitgradeId!=null && commConfigUnitgradeId.trim().length() > 0){
					sql += " and a.commConfigUnitgradeId = '" + commConfigUnitgradeId.trim() + "' ";
				}
				if(commConfigUnittypeId!=null && commConfigUnittypeId.trim().length() > 0){
					sql += " and a.commConfigUnittypeId = '" + commConfigUnittypeId.trim() + "' ";
				}
				if(commConfigFtManageId!=null && commConfigFtManageId.trim().length() > 0){
					sql += " and a.commConfigFtManageId = '" + commConfigFtManageId.trim() + "' ";
				}
				if(commConfigEconkindId!=null && commConfigEconkindId.trim().length() > 0){
					sql += " and a.commConfigEconkindId = '" + commConfigEconkindId.trim() + "' ";
				}
				if(commConfigLocationTownId!=null && commConfigLocationTownId.trim().length() > 0){
					sql += " and a.commConfigLocationTownId = '" + commConfigLocationTownId.trim() + "' ";
				}
				if(commClvId!=null && commClvId.trim().length() > 0){
					sql += " and a.commClvId = '" + commClvId.trim() + "' ";
				}
				sql+=" order by a.seqNo asc";
			return this.getHibernateTemplate().find(sql);
		}catch(Exception e){
			log.error("getData error!", e);
			HspInit.println(e);
			return null;
		}
	}
	/** 获取记录总数 */
	public int getCount(String colmun,String sattffid,String id,String itemCode,String parentItemCode, 
			String itemName, String inputCode,String seqNo,String commConfigHospitalTypeId,String commConfigUnitgradeId,
			String commConfigUnittypeId,String commConfigFtManageId,String commConfigEconkindId,
			String commConfigLocationTownId,String commClvId,String hspConfig) {
		try {
			String tenantId = TenantIdHolder.get();
			if(tenantId == null) return 0;
			int count = 0;
			String sql = "select count(*) ";
			sql += " from HspConfigBaseinfo a  ";
	//		sql += " where 1=1 and  a."+colmun+"='"+sattffid+"' ";
			sql += " where a.tenantId = '"+tenantId+"' and a.hspType = "+ TJInit.getProperty("classFlag").trim() ;
			 if(TJInit.getProperty("classFlag").trim()!=null && TJInit.getProperty("classFlag").trim().equals("1")){
		    	sql +=" and a.hspConfigBaseinfoId3 = (select b.hspConfigBaseinfoId3 from HspConfigBaseinfo b where b.id = '"+ hspConfig +"') " ;
		    }else if(TJInit.getProperty("classFlag").trim()!=null && TJInit.getProperty("classFlag").trim().equals("2")){
		    	sql +=" and a.hspConfigBaseinfoId2 = (select b.hspConfigBaseinfoId2 from HspConfigBaseinfo b where b.id = '"+ hspConfig +"') " ;
		    }else{
		    	sql +=" ";
		    }
			
			if(id!=null && id.trim().length() > 0){
				sql += " and a.id = '" + id.trim() + "' ";
			}
			if(itemCode!=null && itemCode.trim().length() > 0){
				sql += " and a.itemCode like '%" + itemCode.trim() + "%' ";
			}
			if(parentItemCode!=null && parentItemCode.trim().length() > 0){
				sql += " and a.parentItemCode = '" + parentItemCode.trim() + "' ";
			}
			if(itemName!=null && itemName.trim().length() > 0){
				sql += " and a.itemName like '%" + itemName.trim() + "%' ";
			}
			if(inputCode!=null && inputCode.trim().length() > 0){
				sql += " and a.inputCode like '%" + inputCode.trim().toUpperCase() + "%' ";
			}
			if(seqNo!=null && seqNo.trim().length() > 0){
				sql += " and a.seqNo = '" + seqNo.trim() + "' ";
			}
			if(commConfigHospitalTypeId!=null && commConfigHospitalTypeId.trim().length() > 0){
				sql += " and a.commConfigHospitalTypeId = '" + commConfigHospitalTypeId.trim() + "' ";
			}
			if(commConfigUnitgradeId!=null && commConfigUnitgradeId.trim().length() > 0){
				sql += " and a.commConfigUnitgradeId = '" + commConfigUnitgradeId.trim() + "' ";
			}
			if(commConfigUnittypeId!=null && commConfigUnittypeId.trim().length() > 0){
				sql += " and a.commConfigUnittypeId = '" + commConfigUnittypeId.trim() + "' ";
			}
			if(commConfigFtManageId!=null && commConfigFtManageId.trim().length() > 0){
				sql += " and a.commConfigFtManageId = '" + commConfigFtManageId.trim() + "' ";
			}
			if(commConfigEconkindId!=null && commConfigEconkindId.trim().length() > 0){
				sql += " and a.commConfigEconkindId = '" + commConfigEconkindId.trim() + "' ";
			}
			if(commConfigLocationTownId!=null && commConfigLocationTownId.trim().length() > 0){
				sql += " and a.commConfigLocationTownId = '" + commConfigLocationTownId.trim() + "' ";
			}
			if(commClvId!=null && commClvId.trim().length() > 0){
				sql += " and a.commClvId = '" + commClvId.trim() + "' ";
			}
			List<?> list = getHibernateTemplate().find(sql);
			if (list != null && list.size() > 0) {
				count = Integer.valueOf(String.valueOf(list.get(0))).intValue();
			}
			return count;
		}
		catch (Exception re) {
			log.error("getCount error!", re);
			re.printStackTrace();
			return 0;
		}
	}
	public int getCountAll(String id,String itemCode,String itemName, String inputCode,String seqNo,String commConfigHospitalTypeId) {
		try {
			String tenantId = TenantIdHolder.get();
			if(tenantId == null) return 0;
			int count = 0;
			String sql = "select count(*) ";
			sql += " from HspConfigBaseinfo a  ";
			sql += " where a.tenantId = '"+tenantId+"' ";
			if(id!=null && id.trim().length() > 0){
				sql += " and a.id = '" + id.trim() + "' ";
			}
			if(itemCode!=null && itemCode.trim().length() > 0){
				sql += " and a.itemCode like '%" + itemCode.trim() + "%' ";
			}
			if(itemName!=null && itemName.trim().length() > 0){
				sql += " and a.itemName like '%" + itemName.trim() + "%' ";
			}
			if(inputCode!=null && inputCode.trim().length() > 0){
				sql += " and a.inputCode like '" + inputCode.trim().toUpperCase() + "%' ";
			}
			if(seqNo!=null && seqNo.trim().length() > 0){
				sql += " and a.seqNo = '" + seqNo.trim() + "' ";
			}
			if(commConfigHospitalTypeId!=null && commConfigHospitalTypeId.trim().length() > 0){
				sql += " and a.commConfigHospitalTypeId = '" + commConfigHospitalTypeId.trim() + "' ";
			}
			List<?> list = getHibernateTemplate().find(sql);
			if (list != null && list.size() > 0) {
				count = Integer.valueOf(String.valueOf(list.get(0))).intValue();
			}
			return count;
		}
		catch (Exception re) {
			log.error("getCount error!", re);
			re.printStackTrace();
			return 0;
		}
	}



	public List<?> getByParent(String parentId, String levelFlag) {
		try {
			String sql = "";
			sql += " from CommConfigLocation a where 1=1 ";
			if (parentId.trim().length() > 0) {
				sql += " and a.parentId = '" + parentId.trim() + "' ";
			}
			if (levelFlag.trim().length() > 0) {
				sql += " and a.levelFlag = '" + levelFlag.trim() + "' ";
			}
			sql += " order by a.seqNo ";
			return getHibernateTemplate().find(sql);
		}
		catch (RuntimeException re) {
			log.error(re.toString());
			throw re;
		}
	}

	public List<?> getTownsByParent(String parentId) {
		try {
			String sql = "";
			sql += " from CommConfigLocationTown a where 1=1 ";
			if (parentId.trim().length() > 0) {
				sql += " and a.commConfigLocationId = '" + parentId.trim() + "' ";
			}
			sql += " order by a.seqNo ";
			return getHibernateTemplate().find(sql);
		}
		catch (RuntimeException re) {
			log.error(re.toString());
			throw re;			
		}
	}

	public String getP2ValueByP1(String tableName,String p1,String p1Value,String p2) {
		try {
			String sql = "";
			sql += " select a."+p2+" from "+tableName
			+" a where 1=1 and a."+p1+"='"+p1Value+"'";
			List<?> list = getHibernateTemplate().find(sql);
			String p2Value = String.valueOf((Object)list.get(0));
			return p2Value;
		}
		catch (Exception re) {
			//log.error(re.toString());
			return "";
			//throw re;			
		}

	}

	public List<?> getVillagesByParent(String parentId) {
		try {
			String sql = "";
			sql += " from CommConfigLocationVillage a where 1=1 ";
			if (parentId.trim().length() > 0) {
				sql += " and a.commCltId = '" + parentId.trim() + "' ";
			}
			sql += " order by a.seqNo ";
			return getHibernateTemplate().find(sql);
		}
		catch (RuntimeException re) {
			log.error(re.toString());
			throw re;			
		}
	}



	
	public Long seqNoMaker(String nameOfTable) {
		try {
			Long temp = (Long)this.getSession().createQuery(" select max(a.seqNo) from " + nameOfTable + " a ").uniqueResult();
			if(temp==null)
				return 1L;
			else
				return temp+1;
		}
		catch (Exception re) {
			log.error("seqNoMaker error!", re);
			return 1L;
		}

	}
	//医疗机构弹出层
	public List<?> findHspList(String flag,String inputCode,String hspType,String sattId, int curCount, int pageSize){
		try {
			String tenantId = TenantIdHolder.get();
			if(tenantId == null) return null;
			String sql = "";
		//	sql += "select a from  HspConfigBaseinfo a where 1=1 and a.hspConfigBaseinfoId1='"+sattId+"'  ";
			sql += "select a from  HspConfigBaseinfo a where a.tenantId = '"+tenantId+"'  and a.hspType = "+ TJInit.getProperty("classFlag").trim() ;
			
			if(TJInit.getProperty("classFlag").trim()!=null && TJInit.getProperty("classFlag").trim().equals("1")){
			    sql +=" and a.hspConfigBaseinfoId3 = (select s.hspConfigBaseinfoId3 from HspConfigBaseinfo s where s.id = '"+ sattId +"') " ;
			 }else if(TJInit.getProperty("classFlag").trim()!=null && TJInit.getProperty("classFlag").trim().equals("2")){
			    sql +=" and a.hspConfigBaseinfoId2 = (select s.hspConfigBaseinfoId2 from HspConfigBaseinfo s where s.id = '"+ sattId +"') " ;
			 }else{
				sql +=" ";
			 }
			//---1拼音 2代码 3汉字--
			if(flag.equals("1")){
				sql = sql + " and upper(a.inputCode) like '"+inputCode.toUpperCase()+"%'";
			}else if(flag.equals("2")){
				sql = sql + " and a.itemCode like '"+inputCode+"%'";
			}else if(flag.equals("3")){
				sql = sql + " and a.itemName like '%"+inputCode.trim()+"%'";
			}else{
				sql = sql + "  ";
			}
//			//1为除去卫生服务站2为只包括服务站和服务中心3为所有
//			if(hspType.equals("1")){
//				sql = sql + " and a.commConfigUnittypeId!='"+HspInit.getProperty("COMM_CONFIG_UNITTYPE_ITEM_CODE3")+"' ";
//			}else if(hspType.equals("2")){
//				sql = sql + " and (a.commConfigUnittypeId='"+HspInit.getProperty("COMM_CONFIG_UNITTYPE_ITEM_CODE3")+"' or a.commConfigUnittypeId='"+HspInit.getProperty("COMM_CONFIG_UNITTYPE_ITEM_CODE2")+"')";
//			}else if(hspType.equals("3")){
//				sql = sql + " ";
//			}else{
//
//			}
			Query q = getSession().createQuery(sql);
			q.setFirstResult(curCount); 
			q.setMaxResults(pageSize); 
			List<?> l=q.list();
			return l;
		}
		catch (Exception re) {
			log.error("findHspList error!", re);
			re.printStackTrace();
			return null;
		}
	}
	
	//读取字典值 机构分类管理
	public List<?> getPublicChar(String class_code)
	{
		String sql ="select pub from CommDictPublicChar pub where pub.classCode = '"+class_code+"' order by pub.seqNo asc";
		return getHibernateTemplate().find(sql);
	}

	//机构分类管理_修改
	public List<?> getCommConfigFtManageIds(){
		String sql = "from CommConfigFtManage a where 1=1 order by a.seqNo asc";
		try {
			return this.getHibernateTemplate().find(sql);
		} catch (DataAccessException e) {
			log.error("getCommConfigFtManageIds error!",e);
			return null;
		}
	}

	public String getPublicChar_name(String id)
	{
		try{
			String sql ="select pub.dictValue from CommDictPublicChar pub where 1=1 and pub.id = '"+id.trim()+"'";

			List<?> list = getHibernateTemplate().find(sql);
			String dictValue = String.valueOf((Object)list.get(0));

			return dictValue;
		}
		catch (Exception re) {
			log.error(re.toString());
			return "";
			//throw re;			
		}
	}
	//卫生机构类别
	public String getCommConfigHospitalType_name(String itemCode)
	{
		try{
			String sql ="select dict.itemName from CommConfigHospitalType dict where dict.itemCode = '"+itemCode.trim()+"'";

			List<?> list = getHibernateTemplate().find(sql);
			String itemName = String.valueOf((Object)list.get(0));

			return itemName;
		}
		catch (Exception re) {
			//log.error(re.toString());
			return "";
			//throw re;			
		}
	}


	//医院的级 
	
	public List<?> findAllc010501aList() {
		try{
			String sql = "from CommDictPublicChar a  where a.classCode='N200711_T1-1_1.5.1-1' order by a.seqNo asc";  

			Query q = getSession().createQuery(sql); 
			List<?> l=q.list();
			log.debug("findAllc010501aList success!");
			return l;
		} catch(Exception re){
			log.error("findAllc010501aList fail!", re);
			return null;
		}
	}
	//医院的等
	
	public List<?> findAllc010501bList() {
		try{
			String sql = "from CommDictPublicChar a  where a.classCode='N200711_T1-1_1.5.1-2' order by a.seqNo asc ";  

			Query q = getSession().createQuery(sql); 
			List<?> l=q.list();
			log.debug("findAllc010501bList success!");
			return l;
		} catch(Exception re){
			log.error("findAllc010501bList fail!", re);
			return null;
		}
	}
	public String findValueByProp(String nameOfTab, String nameOfField, String nameOfProp, String valueOfProp) {
		try {
			String temp = null;
			String sql = " select a." + nameOfField + " "
			+ " from " + nameOfTab + " a "
			+ " where a." + nameOfProp + " = '" + valueOfProp + "' ";
			List<?> ls = getHibernateTemplate().find(sql);
			if (ls != null && ls.size() > 0) {
				temp = (String) ls.get(0);
			}
			return temp;
		}
		catch (Exception re) {
			log.error("findValueByProp error!", re);
			re.printStackTrace();
			return null;
		}
	}


	
	public List<?> findCommConfigUnitgradeList() {
		try {
			String sql = " from CommConfigUnitgrade a   order by a.seqNo asc "; 
			Query q = getSession().createQuery(sql); 
			List<?> l=q.list();
			log.debug("findCommConfigUnitgradeList success!");
			return l;
		} catch (Exception re) {
			log.error("findAll fail",re);
			re.printStackTrace(); 
			return null;
		}
	}

	
	public List<?> findCommConfigUnittypeList() {

		try {
			String sql = " from CommConfigUnittype a order by a.seqNo asc "; 
			Query q = getSession().createQuery(sql); 
			List<?> l=q.list();
			log.debug("findCommConfigUnittypeList success!");
			return l;
		} catch (Exception re) {
			log.error("findAll fail",re);
			re.printStackTrace(); 
			return null;
		}
	}

	
	public List<?> findConfigHospitalTypeList() {

		try {
			String sql = " from CommConfigHospitalType a where 1=1 order by a.seqNo asc"; 
			Query q = getSession().createQuery(sql); 
			List<?> l=q.list();
			log.debug("findAll success!");
			return l;
		} catch (Exception re) {
			log.error("findAll fail",re);
			re.printStackTrace(); 
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	
	public HspConfigBaseinfo getHsp(String id) {
		HspConfigBaseinfo info=null;
		String hql="from HspConfigBaseinfo h where h.id='"+id+"'";
		List list=getHibernateTemplate().find(hql);
		if(list!=null&&list.size()>0){
			info=(HspConfigBaseinfo)list.get(0);
		}
		return info;
	}

	
	public List<?> getHspBaseInfoId(String id) {
		String hql=" from SecurityStaffBaseinfo s,HspConfigBaseinfo h  " +
			" where s.hspConfigBaseinfoId=h.id" +
			" and s.id='"+id+"' ";
		List<?> ls=getHibernateTemplate().find(hql);
		return ls;
	}
	public List<?> init(String staffId){
		String hql=" from SecurityDataObjectType t,SecurityDataObjectVsRoles t1,SecurityStaffBaseinfo s where" +
				" 1=1 and t.id=t1.sdotId and t1.securityStaffBaseinfoId=s.id and s.id=? order by t.id";
		return getHibernateTemplate().find(hql,new String[]{staffId});
	}
	
	
	
	public static void main(String[] args){
		System.out.println(HspInit.getProperty("COMM_CONFIG_UNITTYPE_ITEM_CODE"));
	}


	/** 获取记录总数 */
	public int getCount(HspConfigBaseinfoForm form) {
		int count = 0;
		String sql = this.createQuerySql(form, 0);
		List<?> list = getHibernateTemplate().find(sql);
		if (list != null && list.size() > 0) {
			count = Integer.valueOf(String.valueOf(list.get(0))).intValue();
		}
		return count;
	}
	
	@Override
	public List<?> getData(HspConfigBaseinfoForm form, int curCount,
			int pageSize) {
		String sql = this.createQuerySql(form, 1);
		Query q = getSession().createQuery(sql);
		q.setFirstResult(curCount);
		q.setMaxResults(pageSize);
		return q.list();
	}
	

	private String createQuerySql(HspConfigBaseinfoForm form, int queryType) {
		String tenantId = TenantIdHolder.get();
		StringBuilder sql = new StringBuilder();
		if(queryType == 0){
			sql.append("select count(*)");
		}else{
			sql.append("select a ");
		}
		sql.append(" from HspConfigBaseinfo a where a.tenantId = '"+tenantId+"' ");
		if(isNotNull(form.getHspConfigBaseinfoId())){
			sql.append(" and a.id = '").append(form.getHspConfigBaseinfoId().trim()).append("' ");
		}
		if(isNotNull(form.getItemCode())){
			sql.append(" and a.itemCode like '%").append(form.getItemCode().trim()).append("%' ");
		}
		if(isNotNull(form.getParentItemCode())){
			sql.append(" and a.parentItemCode = '").append(form.getParentItemCode().trim()).append("' ");
		}
		if(isNotNull(form.getItemName())){
			sql.append(" and a.itemName like '%").append(form.getItemName().trim()).append("%' ");
		}
		if(isNotNull(form.getInputCode())){
			sql.append(" and a.inputCode like '").append(form.getInputCode().trim().toUpperCase()).append("%' ");
		}
		if(isNotNull(form.getSeqNo())){
			sql.append(" and a.seqNo = '").append(form.getSeqNo().trim()).append("' ");
		}
		if(isNotNull(form.getCommConfigHospitalTypeId())){
			sql.append(" and a.commConfigHospitalTypeId = '").append(form.getCommConfigHospitalTypeId().trim()).append("' ");
		}
		if(isNotNull(form.getCommConfigUnitgradeId())){
			sql.append(" and a.commConfigUnitgradeId = '").append(form.getCommConfigUnitgradeId().trim()).append("' ");
		}
		if(isNotNull(form.getCommConfigUnittypeId())){
			sql.append(" and a.commConfigUnittypeId = '").append(form.getCommConfigUnittypeId().trim()).append("' ");
		}
		if(isNotNull(form.getCommConfigFtManageId())){
			sql.append(" and a.commConfigFtManageId = '").append(form.getCommConfigFtManageId().trim()).append("' ");
		}
		if(isNotNull(form.getCommConfigEconkindId())){
			sql.append(" and a.commConfigEconkindId = '").append(form.getCommConfigEconkindId().trim()).append("' ");
		}
		if(isNotNull(form.getCommConfigLocationTownId())){
			sql.append(" and a.commConfigLocationTownId = '").append(form.getCommConfigLocationTownId().trim()).append("' ");
		}
		if(isNotNull(form.getCommClvId())){
			sql.append(" and a.commClvId = '").append(form.getCommClvId().trim()).append("' ");
		}
		
		if(queryType == 1){
			sql.append(" order by ");
			if(form.getOrderNo().equals("1")){
				sql.append(" a.itemCode");
			}else if(form.getOrderNo().equals("2")){
				sql.append(" a.itemName");
			}else if(form.getOrderNo().equals("3")){
				sql.append(" a.phone");
			}else if(form.getOrderNo().equals("4")){
				sql.append(" a.contactPersonName");
			}else if(form.getOrderNo().equals("5")){
				sql.append(" a.seqNo");
			}else if(form.getOrderNo().equals("6")){
				sql.append(" a.commConfigLocationName1 || a.commConfigLocationName2 || a.commConfigLocationName3 || a.ccltName || a.commClvName");
			}else{
				sql.append(" a.seqNo");
			}
			sql.append(", a.id");//加上主键确保排序唯一
			if(form.getAsc().equals("1")){
				sql.append(" desc");
			}else{
				sql.append(" asc");
			}
		}
		return sql.toString();
	}
	
	private boolean isNull(String temp){
		return temp == null || temp.trim().length() <= 0;
	}
	
	private boolean isNotNull(String temp){
		return temp != null && temp.trim().length() > 0;
	}

	@Override
	public String getHspTypeName(String itemCode) {
		String temp = null;
		try {
			
			List<?> ls = getHibernateTemplate().find("select a.itemName from CommConfigHospitalType a where a.itemCode = ? ", itemCode);
			if (ls != null && ls.size() > 0) {
				temp = (String) ls.get(0);
			}
		}
		catch (Exception re) {
			log.error("getItemName error!", re);
		}
		return temp;
	}

	@Override
	public List<?> getByHospitalId(String hspConfigHospitalTypeId) {
		try {
			String sql = "from HspConfigBaseinfo a where a.commConfigHospitalTypeId like '%"+hspConfigHospitalTypeId+"%'";
			Query q = getSession().createQuery(sql); 
			List<?> l=q.list();
			return l;
		}
		catch (Exception re) {
			log.error("error!", re);
			re.printStackTrace();
			return null;
		}
	}
}
