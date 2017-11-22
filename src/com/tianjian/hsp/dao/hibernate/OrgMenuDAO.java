package com.tianjian.hsp.dao.hibernate;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.comm.bean.CommConfigDeptAttr;
import com.tianjian.hsp.bean.HspConfigBaseinfo;
import com.tianjian.hsp.bean.HspDeptBaseinfo;
import com.tianjian.hsp.bean.HspStaffBaseinfo;
import com.tianjian.hsp.dao.IOrgMenuDAO;
import com.tianjian.tenant.util.TenantIdHolder;

public class OrgMenuDAO extends HibernateDaoSupport implements IOrgMenuDAO {
	
	private static final Logger log = LogManager.getLogger(OrgMenuDAO.class);

	@Override
	public List<?> findRoot() {
		try {
			String tenantId = TenantIdHolder.get();
			List<?> ls = getHibernateTemplate().find("select a.id, a.itemCode, a.itemName from HspConfigBaseinfo a where a.tenantId = '"+tenantId+"' and a.parentItemCode is null ");
			return ls;
		}
		catch (Exception re) {
			log.error("findRoot error!", re);
			re.printStackTrace();
			return null;
		}
	}

	@Override
	public List<?> findChild(String parentId) {
		try {
			String tenantId = TenantIdHolder.get();
			List<?> ls = getHibernateTemplate().find("select a.id, a.itemCode, a.itemName from HspConfigBaseinfo a where a.tenantId = '"+tenantId+"' and a.parentItemCode = ?",parentId);
			return ls;
		}
		catch (Exception re) {
			log.error("findChild error!", re);
			re.printStackTrace();
			return null;
		}
	}

	@Override
	public List<?> findDept(String orgId) {
		try {
			String tenantId = TenantIdHolder.get();
			List<?> ls = getHibernateTemplate().find(" from HspDeptBaseinfo a where a.id.hspConfigBaseinfoId = ? and a.tenantId = ?", new Object[]{orgId, tenantId});
			return ls;
		}
		catch (Exception re) {
			log.error("findDept error!", re);
			re.printStackTrace();
			return null;
		}
	}
	
	/**取得所有的科室*/
	public List<?> findAllDept(){
		try {
			String tenantId = TenantIdHolder.get();
			String sql = " from HspDeptBaseinfo a where a.tenantId = ? ";

			List<?> l= this.getHibernateTemplate().find(sql, tenantId);
			log.debug("findAllDept success!");
			return l;
		} catch (Exception re) {
			log.error("findAllDept fail",re);
			re.printStackTrace(); 
			return null;
		}
	}

	@Override
	public void saveDept(HspDeptBaseinfo dept) throws Exception {
		try{
			String tenantId = TenantIdHolder.get();
			dept.setTenantId(tenantId);
			this.getHibernateTemplate().save(dept);
			log.debug("DAO public void saveDept(HspDeptBaseinfo dept) success!");
		}catch(Exception e){
			log.error("DAO public void saveDept(HspDeptBaseinfo dept) error!",e);
			e.printStackTrace();
		}
	}

	@Override
	public void updateDept(HspDeptBaseinfo dept) throws Exception {
		try {
			String tenantId = TenantIdHolder.get();
			dept.setTenantId(tenantId);
			getHibernateTemplate().merge(dept);
		}
		catch (Exception re) {
			log.error("updateDept error!", re);
			re.printStackTrace();
		}
	}

	@Override
	public void deleteDept(HspDeptBaseinfo dept) throws Exception {
		try {
			getHibernateTemplate().delete(dept);
		}
		catch (Exception re) {
			log.error("deleteDept error!", re);
			re.printStackTrace();
		}
	}

	@Override
	public List<?> findAllDeptAttr() {
		try {
			List<?> ls = getHibernateTemplate().find(" from CommConfigDeptAttr a ");
			return ls;
		}
		catch (Exception re) {
			log.error("findAllDeptAttr error!", re);
			re.printStackTrace();
			return null;
		}
	}

	@Override
	public HspDeptBaseinfo getDeptById(String orgId, String deptCode) {
		try {
			String tenantId = TenantIdHolder.get();
			HspDeptBaseinfo temp = null;
			List<?> ls = getHibernateTemplate().find(" " +
					" from HspDeptBaseinfo a " +
					" where a.tenantId = '"+tenantId+"' and  a.id.deptCode='"+deptCode+"' and a.id.hspConfigBaseinfoId='"+orgId+"' ");
			if (ls != null && ls.size() > 0) {
				temp = (HspDeptBaseinfo) ls.get(0);
			}
			return temp;
		}
		catch (Exception re) {
			log.error("findRoot error!", re);
			re.printStackTrace();
			return null;
		}
	}

	@Override
	public String getNameByCode(String deptAttrCode) {
		try {
			CommConfigDeptAttr temp = null;
			List<?> ls = getHibernateTemplate().find(" from CommConfigDeptAttr a where a.itemCode='"+deptAttrCode+"' ");
			if (ls != null && ls.size() > 0) {
				temp = (CommConfigDeptAttr) ls.get(0);
			}
			
			return temp.getItemName();
		}
		catch (Exception re) {
			log.error("getNameByCode error!", re);
			re.printStackTrace();
			return null;
		}
	}

	@Override
	public List<?> findStaff(String orgId, String deptCode) {
		try {
			String tenantId = TenantIdHolder.get();
			String sql = " from HspStaffBaseinfo a where a.tenantId = '"+tenantId+"' and a.hspConfigBaseinfoId = '"+orgId+"' ";
			if(deptCode!=null&&deptCode.trim().length()>0){
				sql += " and a.relatedDepartment = '"+deptCode.trim()+"'";
			}else{
				sql += " and a.relatedDepartment is null ";
			}
			sql += " order by a.name";
			List<?> ls = getHibernateTemplate().find(sql);
			return ls;
		}
		catch (Exception re) {
			log.error("findDept error!", re);
			re.printStackTrace();
			return null;
		}
	}

	@Override
	public HspStaffBaseinfo findStaffById(String hspStaffId) {
		try {
			String tenantId = TenantIdHolder.get();
			HspStaffBaseinfo temp = null;
			List<?> ls = getHibernateTemplate().find(" from HspStaffBaseinfo a where a.id=?  and a.tenantId = ?", new Object[]{hspStaffId, tenantId});
			if (ls != null && ls.size() > 0) {
				temp = (HspStaffBaseinfo) ls.get(0);
			}
			return temp;
		}
		catch (Exception re) {
			log.error("findStaffById error!", re);
			re.printStackTrace();
			return null;
		}
	}

	@Override
	public String findStaffId(String empNo) {
		try {
			String tenantId = TenantIdHolder.get();
			HspStaffBaseinfo temp = null;
			List<?> ls = getHibernateTemplate().find(" from HspStaffBaseinfo a where a.empNo=? and a.tenantId = ?", new Object[]{empNo, tenantId});
			if (ls != null && ls.size() > 0) {
				temp = (HspStaffBaseinfo) ls.get(0);
			}
			return temp.getId();
		}
		catch (Exception re) {
			log.error("findStaffById error!", re);
			re.printStackTrace();
			return null;
		}
	}

	@Override
	public void updateStaff(HspStaffBaseinfo hsp) {
		try {
			String tenantId = TenantIdHolder.get();
			hsp.setTenantId(tenantId);
			getHibernateTemplate().merge(hsp);
		}
		catch (Exception re) {
			log.error("updateStaff error!", re);
			re.printStackTrace();
		}
	}

	@Override
	public List<?> findDeptByInputCode(String queryValue) {
		try {
			String tenantId = TenantIdHolder.get();
			String sql = " from HspDeptBaseinfo a where a.inputCode = ? and a.tenantId = ?";

			List<?> ls = getHibernateTemplate().find(sql, new Object[]{queryValue, tenantId});
			return ls;
		}
		catch (Exception re) {
			log.error("findStaffByName error!", re);
			re.printStackTrace();
			return null;
		}
	}

	@Override
	public List<?> findDeptByName(String itemCode,String queryKey,String queryValue) {
		try {
			String tenantId = TenantIdHolder.get();
			String sql = "SELECT T.DEPT_CODE,T.DEPT_NAME, " +
					"A.ID AS ORG_ID3,A.ITEM_CODE AS ORG_CODE3,A.ITEM_NAME AS ORG_NAME3, " +
					"B.ID AS ORG_ID2,B.ITEM_CODE AS ORG_CODE2,B.ITEM_NAME AS ORG_NAME2, " +
					"C.ID AS ORG_ID1,C.ITEM_CODE AS ORG_CODE1,C.ITEM_NAME AS ORG_NAME1  " +
					"FROM HSP.HSP_DEPT_BASEINFO T " +
					"LEFT JOIN HSP.HSP_CONFIG_BASEINFO A ON T.HSP_CONFIG_BASEINFO_ID = A.ID " +
					"LEFT JOIN HSP.HSP_CONFIG_BASEINFO B ON A.PARENT_ITEM_CODE = B.ITEM_CODE " +
					"LEFT JOIN HSP.HSP_CONFIG_BASEINFO C ON B.PARENT_ITEM_CODE = C.ITEM_CODE " +
					"WHERE T.TENANT_ID = '" + tenantId + "' AND A.ID IS NOT NULL ";

			if(queryKey.equals("0")){
				sql += " AND T.DEPT_NAME LIKE '%"+queryValue+"%'";
			}else if(queryKey.equals("1")){
				sql += " AND T.INPUT_CODE = '"+queryValue+"'";
			}else{
				sql += " AND '1' = '2' ";
			}
			if(itemCode!=null&&itemCode.trim().length()>0){
				sql += " AND A.ID = '"+itemCode+"' ";
			}
			Query query = this.getSession().createSQLQuery(sql);
			
			return  query.list();
		}
		catch (Exception re) {
			log.error("findStaffByName error!", re);
			re.printStackTrace();
			return null;
		}
	}

	@Override
	public List<?> findStaffByInputCode(String queryValue) {
		try {
			String tenantId = TenantIdHolder.get();
			String sql = " from HspStaffBaseinfo a where a.inputCode = ? and a.tenantId = ? ";

			List<?> ls = getHibernateTemplate().find(sql, new Object[]{queryValue, tenantId});
			return ls;
		}
		catch (Exception re) {
			log.error("findStaffByName error!", re);
			re.printStackTrace();
			return null;
		}
	}

	@Override
	public List<?> findStaffByName(String queryValue) {
		try {
			String tenantId = TenantIdHolder.get();
			String sql = " from HspStaffBaseinfo a where a.tenantId = '"+tenantId+"' and  a.name like '%"+queryValue+"%' ";

			List<?> ls = getHibernateTemplate().find(sql);
			return ls;
		}
		catch (Exception re) {
			log.error("findStaffByName error!", re);
			re.printStackTrace();
			return null;
		}
	}
	
	public List<?> findStaffByName(String itemCode,String queryKey,String queryValue){
		String tenantId = TenantIdHolder.get();
		String sql = "SELECT T.ID,T.NAME,A.DEPT_CODE,A.DEPT_NAME,B.ID AS HSP_CONFIG_BASEINFO_ID," +
				"B.ITEM_CODE AS HSP_CODE,B.ITEM_NAME AS HSP_NAME, C.ID AS HSP_CONFIG_BASEINFO_ID2," +
				"C.ITEM_CODE AS HSP_CODE2,C.ITEM_NAME AS HSP_NAME2, D.ID AS HSP_CONFIG_BASEINFO_ID1," +
				"D.ITEM_CODE AS HSP_CODE1,D.ITEM_NAME AS HSP_NAME1 " +
				"FROM HSP.HSP_STAFF_BASEINFO T LEFT JOIN HSP.HSP_DEPT_BASEINFO A " +
				"ON T.RELATED_DEPARTMENT = A.DEPT_CODE " +
				"LEFT JOIN HSP.HSP_CONFIG_BASEINFO B ON T.HSP_CONFIG_BASEINFO_ID = B.ID " +
				"LEFT JOIN HSP.HSP_CONFIG_BASEINFO C ON B.PARENT_ITEM_CODE = C.ITEM_CODE " +
				"LEFT JOIN HSP.HSP_CONFIG_BASEINFO D ON C.PARENT_ITEM_CODE = D.ITEM_CODE " +
				"WHERE T.TENANT_ID = '" + tenantId + "' AND B.ID IS NOT NULL ";
		if(queryKey.equals("0")){
			sql += " AND T.NAME LIKE '%"+queryValue+"%'";
		}else if(queryKey.equals("1")){
			sql += " AND T.INPUT_CODE = '"+queryValue+"'";
		}else{
			sql += " AND '1' = '2' ";
		}
		if(itemCode!=null&&itemCode.trim().length()>0){
			sql += " AND B.ID = '"+itemCode+"' ";
		}
		Query query = this.getSession().createSQLQuery(sql);
		
		return  query.list();
	}

}
