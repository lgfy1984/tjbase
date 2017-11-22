package com.tianjian.hsp.dao.hibernate;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.comm.bean.CommConfigNationality;
import com.tianjian.hsp.bean.HspConfigBaseinfo;
import com.tianjian.hsp.bean.HspStaffBaseinfo;
import com.tianjian.hsp.dao.IHspStaffBaseinfoDAO;
import com.tianjian.hsp.struts.servlet.HspInit;
import com.tianjian.security.bean.SecurityStaffBaseinfo;
import com.tianjian.tenant.util.TenantIdHolder;
import com.tianjian.util.comm.TJInit;

public class HspStaffBaseinfoDAO extends HibernateDaoSupport implements IHspStaffBaseinfoDAO {
	
	private static final Log log = LogFactory.getLog(HspStaffBaseinfo.class);

	
	
	//取医院相应的科室
	public List<?> getAjaxDept(String hspId, String flag, String input) {
		try{
			String tenantId = TenantIdHolder.get();
			String newName = null;
			String sql = "select dict from HspDeptBaseinfo dict where dict.tenantId = '"+tenantId+"' and dict.hspConfigBaseinfoId = '"+hspId.trim()+"'";
			if ((flag != null && flag.equals("1")) && (input != null && !input.equals(""))) {
				sql += " and upper(dict.inputCode) like '%" + input.trim().toUpperCase() + "%'";
			}
			if ((flag != null && flag.equals("2")) && (input != null && !input.equals(""))) {
				sql += " and dict.deptCode like '%" + input.trim() + "%'";
			}
			if ((flag != null && flag.equals("3")) && (input != null && !input.equals(""))) {
				try {
					newName = new String(input.trim().getBytes("ISO8859_1"), "gb2312");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				sql += " and dict.deptName like '%" + newName + "%'";
			}
//			System.out.println("SQL查询是"+sql+"结束");
			Query query = this.getSession().createQuery(sql);
			query.setMaxResults(Integer.valueOf("10"));
			List<?> list = query.list();
			log.debug("DAO public List<?> getAjaxDept(String hspId, String flag, String input) OK");
			return list;
		}catch(Exception e){
			log.error("DAO public List<?> getAjaxDept(String hspId, String flag, String input) error");
			e.printStackTrace();
			return null;
		}
	}
	//医疗机构弹出层
	public List<?> findHspList(String flag,String inputCode,String hspType,String hspConficBaseinfo1,String colnum, int curCount, int pageSize){
		try {
			String tenantId = TenantIdHolder.get();
			String sql = "";
			sql += "select a from HspConfigBaseinfo a ";
	        sql += " where a.tenantId = '"+tenantId+"' and a."+colnum+"='"+hspConficBaseinfo1+"' ";
			//---1拼音 2代码 3汉字--
			if(flag.equals("1")){
				sql = sql + " and upper(a.inputCode) like '"+inputCode.toUpperCase()+"%'";
			}else if(flag.equals("2")){
				sql = sql + " and a.itemCode like '"+inputCode+"%'";
			}else if(flag.equals("3")){
				sql = sql + " and a.itemName like '"+inputCode+"%'";
			}else{
				sql = sql + "  ";
			}
			
			//1为除去卫生服务站2为只包括服务站和服务中心3为所有
			if(hspType.equals("1")){
				sql = sql + " and a.commConfigUnittypeId!='"+HspInit.getProperty("COMM_CONFIG_UNITTYPE_ITEM_CODE3")+"' ";
			}else if(hspType.equals("2")){
				sql = sql + " and (a.commConfigUnittypeId='"+HspInit.getProperty("COMM_CONFIG_UNITTYPE_ITEM_CODE3")+"' or a.commConfigUnittypeId='"+HspInit.getProperty("COMM_CONFIG_UNITTYPE_ITEM_CODE2")+"')";
			}else if(hspType.equals("3")){
				sql = sql + " ";
			}else{
				
			}
     
			
		//	System.out.println(sql+"%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
	
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
	//带参数的民族字典
	public List<?> getAjaxNationality(String flag, String input) {
		String newName = null;
		String sql = "select dict from CommConfigNationality dict where 1=1 ";
		if ((flag != null && flag.equals("1")) && (input != null && !input.equals(""))) {
			sql += " and upper(dict.inputCode) like '%" + input.trim().toUpperCase() + "%'";
		}
		if ((flag != null && flag.equals("2")) && (input != null && !input.equals(""))) {
			sql += " and dict.itemCode like '%" + input.trim() + "%'";
		}
		if ((flag != null && flag.equals("3")) && (input != null && !input.equals(""))) {
			try {
				newName = new String(input.trim().getBytes("ISO8859_1"), "gb2312");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			sql += " and dict.itemName like '%" + input.trim() + "%'";
		}
		Query q = getSession().createQuery(sql);
		q.setMaxResults(Integer.valueOf("10"));
		List<?> list = q.list();
		return list;
	}

	
	
	public SecurityStaffBaseinfo getSecurityStaffBaseinfoById(String id) {
		SecurityStaffBaseinfo ssb = null;
		try{
			String tenantId = TenantIdHolder.get();
			List<?> list = this.getHibernateTemplate().find("FROM SecurityStaffBaseinfo a WHERE a.hspStaffBaseinfoId=? and a.tenantId = ?", new Object[]{id, tenantId});
			if(list!=null&&list.size()>0){
				ssb = (SecurityStaffBaseinfo)list.get(0);
			}
			log.debug("public SecurityStaffBaseinfo getSecurityStaffBaseinfoById(String id) OK");
			return ssb;
		}catch(Exception e){
			log.error("DAO public SecurityStaffBaseinfo getSecurityStaffBaseinfoById(String id)error", e);
			e.printStackTrace();
			return ssb;
		}
	}
	
	public CommConfigNationality getNationalName(String id) {
		CommConfigNationality ccn = null;
		try{
			List<?> list = this.getHibernateTemplate().find("FROM CommConfigNationality a WHERE a.itemCode=?",id);
			if(list != null && list.size() > 0){
				ccn = (CommConfigNationality)list.get(0);
			}
			log.debug("DAO public String getNationalName(String id) OK ");
			return ccn;
		}catch(Exception e){
			log.error("DAO public String getNationalName(String id) error", e);
			e.printStackTrace();
			return ccn;
		}
	}
	/**L2 取"所学专业"字典*/
	public List<?> commConfigProfessionIdList(){
		return this.getHibernateTemplate().find("select a from CommConfigProfession a order by a.seqNo asc");
	}
	/**L3 取"学位"字典*/
	public List<?> commConfigDegreeLevelIdList(){
		return this.getHibernateTemplate().find("select a from CommConfigDegreeLevel a order by a.seqNo asc");
	}
	/**L4 取"学历"字典*/
	public List<?> commConfigDegreeIdList(){
		return this.getHibernateTemplate().find("select a from CommConfigDegree a order by a.seqNo asc");
	}
	/**L5 取"专业技术职务（聘）"字典*/
	public List<?> commDictPublicCharId3List(){
		return this.getHibernateTemplate().find("select a from CommDictPublicChar a where a.classCode='N200711_T2_3.2' order by a.seqNo asc");
	}
	/**L6 取"专业技术资格（评）"字典*/
	public List<?> commConfigEmptitleIdList(){
		return this.getHibernateTemplate().find("select a from CommConfigEmptitle a order by a.seqNo asc ");
	}
	/**L7 取"行政/业务管理职务"字典*/
	public List<?> commConfigPositiontitleIdList(){
		return this.getHibernateTemplate().find("select a from CommConfigPositiontitle a order by a.seqNo asc ");
	}
	/**L8 取"医师管理专业类别"字典*/
	public List<?> commDictPublicCharId2List(){
		return this.getHibernateTemplate().find("select a from CommDictPublicChar a where a.classCode='N200711_T2_2.4' order by a.seqNo asc");
	}
	/**L9 取"从事专业类别"字典*/
	public List<?> commDictPublicCharId1List(){
		return this.getHibernateTemplate().find("select a from CommDictPublicChar a where a.classCode='N200711_T2_2.2' order by a.seqNo asc");
	}
	
	
	
	
	
	
	public List<?> commConfigNationalityIdList() {
		return this.getHibernateTemplate().find("select a from CommConfigNationality a order by a.seqNo asc");
	}
	
	
	public List<?> sexList() {
		return this.getHibernateTemplate().find("select a from CommConfigSex a order by a.itemCode asc");
	}
	
	
	public List<?> hspConfigBaseinfoIdList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public void delete(HspStaffBaseinfo hsp) {
		try{
			this.getHibernateTemplate().delete(hsp);
			log.debug("DAO public void delete(HspStaffBaseinfo hsp) OK ");
		}catch(Exception e){
			log.error("DAO public void delete(HspStaffBaseinfo hsp) error", e);
			e.printStackTrace();
		}
	}

	public List<?> findAll() {
		try{
			String tenantId = TenantIdHolder.get();
			List<?> list = getHibernateTemplate().find("from HspStaffBaseinfo a where a.tenantId = ?" , tenantId);
			log.debug("public List<?> findAll() (HspStaffBaseinfo) success!");
			return list;
		}catch(Exception e){
			log.error("public List<?> findAll() (HspStaffBaseinfo) error!",e);
			e.printStackTrace();
			return null;
		}
	}

	public HspStaffBaseinfo findById(String id) {
		HspStaffBaseinfo hspStaffBaseinfo = null;
		try{
			String tenantId = TenantIdHolder.get();
			List<?> list = this.getHibernateTemplate().find("from HspStaffBaseinfo a where a.id=? and a.tenantId = ?", new Object[]{id, tenantId});
			if(list!=null&&list.size()>0){
				hspStaffBaseinfo=(HspStaffBaseinfo) list.get(0);
			}
			log.debug("DAO public HspStaffBaseinfo findById(String id) success!");
			return hspStaffBaseinfo;
		}catch(Exception e){
			log.error("DAO public HspStaffBaseinfo findById(String id) error!",e);
			e.printStackTrace();
			return null;
		}
	}

	/**通过Id获取医疗机构信息*/
	public HspConfigBaseinfo getById(String id) {
		try {
			String tenantId = TenantIdHolder.get();
			HspConfigBaseinfo temp = null;
			List<?> ls = getHibernateTemplate().find(" from HspConfigBaseinfo a where a.id = ? and a.tenantId = ?", new Object[]{id, tenantId});
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
	
	public int getCount(String id, String hspId, String name, String idNo,String empNo,String commConfigNationalityId,String workCertificateNo,String commDictPublicCharId1,String commDictPublicCharId2,String commConfigPositiontitleId,String commConfigEmptitleId,String commDictPublicCharId3,String commConfigDegreeId,String commConfigDegreeLevelId,String commConfigProfessionId,String commConfigLocationId3,
	   String commConfigLocationTownId,String commClvId,String staffId, List listId,String townCode,String commConfigSexId,String staffHspId,String ageS,String ageE, Long isLocation) {
		try{
			int count = 0;
			String sql = this.createQueryStr(hspId, name, idNo, empNo, commConfigNationalityId, workCertificateNo, commDictPublicCharId1, commDictPublicCharId2, commConfigPositiontitleId, commConfigEmptitleId, commDictPublicCharId3, commConfigDegreeId, commConfigDegreeLevelId, commConfigProfessionId, commConfigLocationId3, commConfigLocationTownId, commClvId, staffId, listId, townCode, commConfigSexId, staffHspId, ageS, ageE, isLocation, 0); 
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
	public int getCountAll(String id, String hspId, String name, String idNo,String empNo,String commConfigNationalityId,String workCertificateNo,String commDictPublicCharId1,String commDictPublicCharId2,String commConfigPositiontitleId,String commConfigEmptitleId,String commDictPublicCharId3,String commConfigDegreeId,String commConfigDegreeLevelId,String commConfigProfessionId) {
		try{
			String tenantId = TenantIdHolder.get();
			int count = 0;
			String sql = "SELECT COUNT(*) FROM HspStaffBaseinfo a ";
	        sql += " where a.tenantId = '"+tenantId+"' ";
			if(id != null && id.trim().length() > 0){
				sql += " and a.id = ' "+id.trim()+"'";
			}
			if(hspId != null && hspId.trim().length() > 0){
				sql += " and a.hspConfigBaseinfoId = '"+hspId.trim()+"'";
			}
			if(name != null && name.trim().length() > 0){
				sql += " and a.name like '%" + name.trim() + "%'";
			}
			if(idNo != null && idNo.trim().length() > 0){
				sql += " and a.idNo = '" + idNo.trim() + "'";
			}
			if(empNo != null && empNo.trim().length() > 0){
				sql += " and a.empNo = '" + empNo.trim() + "'";
			}
			if(commConfigNationalityId != null && commConfigNationalityId.trim().length() > 0){
				sql += " and a.commConfigNationalityId = '" + commConfigNationalityId.trim() + "'";
			}
			if(workCertificateNo != null && workCertificateNo.trim().length() > 0){
				sql += " and a.workCertificateNo = '" + workCertificateNo.trim() + "'";
			}
			if(commDictPublicCharId1 != null && commDictPublicCharId1.trim().length() > 0){
				sql += " and a.commDictPublicCharId1 = '" + commDictPublicCharId1.trim() + "'";
			}
			if(commDictPublicCharId2 != null && commDictPublicCharId2.trim().length() > 0){
				sql += " and a.commDictPublicCharId2 = '" + commDictPublicCharId2.trim() + "'";
			}
			if(commConfigPositiontitleId != null && commConfigPositiontitleId.trim().length() > 0){
				sql += " and a.commConfigPositiontitleId = '" + commConfigPositiontitleId.trim() + "'";
			}
			if(commConfigEmptitleId != null && commConfigEmptitleId.trim().length() > 0){
				sql += " and a.commConfigEmptitleId = '" + commConfigEmptitleId.trim() + "'";
			}
			if(commDictPublicCharId3 != null && commDictPublicCharId3.trim().length() > 0){
				sql += " and a.commDictPublicCharId3 = '" + commDictPublicCharId3.trim() + "'";
			}
			if(commConfigDegreeId != null && commConfigDegreeId.trim().length() > 0){
				sql += " and a.commConfigDegreeId = '" + commConfigDegreeId.trim() + "'";
			}
			if(commConfigDegreeLevelId != null && commConfigDegreeLevelId.trim().length() > 0){
				sql += " and a.commConfigDegreeLevelId = '" + commConfigDegreeLevelId.trim() + "'";
			}
			if(commConfigProfessionId != null && commConfigProfessionId.trim().length() > 0){
				sql += " and a.commConfigProfessionId = '" + commConfigProfessionId.trim() + "'";
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

	
	//为卫生人员维护查询数量和查询数据创建sql
	private String createQueryStr(String hspId, String name, String idNo,String empNo,String commConfigNationalityId,String workCertificateNo,String commDictPublicCharId1,String commDictPublicCharId2,String commConfigPositiontitleId,String commConfigEmptitleId,String commDictPublicCharId3,String commConfigDegreeId,String commConfigDegreeLevelId,String commConfigProfessionId,String commConfigLocationId3,
			   String commConfigLocationTownId,String commClvId,String staffId, List listId,String townCode,String commConfigSexId,String staffHspId,String ageS,String ageE, Long isLocation, int queryType){
		String tenantId = TenantIdHolder.get();
		StringBuilder sql = new StringBuilder();
		if(queryType == 0){
			sql.append("select count(*) ");
		}else{
			sql.append("select a ");
		}
		sql.append(" from HspStaffBaseinfo a ,HspConfigBaseinfo b ")
			.append("where a.tenantId = '"+tenantId+"' and a.hspConfigBaseinfoId = b.id");
		sql.append(" and (b.id = '").append(staffHspId).append("'");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("staffHspId", staffHspId);
		List<String> subHspIdList = this.getSubHspIds(staffHspId);
		if(subHspIdList != null && subHspIdList.size() > 0){
			for(String subHspId : subHspIdList){
				sql.append(" or b.id = '").append(subHspId).append("'");
			}
		}
		sql.append(")");
		if(hspId != null && hspId.trim().length() > 0){
			sql.append(" and a.hspConfigBaseinfoId = '").append( hspId.trim() ).append("'");
		}
		if(name != null && name.trim().length() > 0){
			sql.append(" and a.name like '%").append( name.trim() ).append("%'");
		}
		if(idNo != null && idNo.trim().length() > 0){
			sql.append(" and a.idNo = '").append( idNo.trim() ).append("'");
		}
		if(empNo != null && empNo.trim().length() > 0){
			sql.append(" and a.empNo = '").append( empNo.trim() ).append("'");
		}
		if(commConfigNationalityId != null && commConfigNationalityId.trim().length() > 0){
			sql.append(" and a.commConfigNationalityId = '").append( commConfigNationalityId.trim() ).append("'");
		}
		if(workCertificateNo != null && workCertificateNo.trim().length() > 0){
			sql.append(" and a.workCertificateNo = '").append( workCertificateNo.trim() ).append("'");
		}
		if(commDictPublicCharId1 != null && commDictPublicCharId1.trim().length() > 0){
			if(commDictPublicCharId1.trim().equals("yjry")){
				sql.append(" and a.commDictPublicCharId1 = '1124' or a.commDictPublicCharId1 = '11112' ");
			}else
				sql.append(" and a.commDictPublicCharId1 = '").append( commDictPublicCharId1.trim() ).append("'");
		}
		if(commDictPublicCharId2 != null && commDictPublicCharId2.trim().length() > 0){
			sql.append(" and a.commDictPublicCharId2 = '").append( commDictPublicCharId2.trim() ).append("'");
		}
		if(commConfigPositiontitleId != null && commConfigPositiontitleId.trim().length() > 0){
			sql.append(" and a.commConfigPositiontitleId = '").append( commConfigPositiontitleId.trim() ).append("'");
		}
		if(commConfigEmptitleId != null && commConfigEmptitleId.trim().length() > 0){
			sql.append(" and a.commConfigEmptitleId = '").append( commConfigEmptitleId.trim() ).append("'");
		}
		if(commDictPublicCharId3 != null && commDictPublicCharId3.trim().length() > 0){
			if(commDictPublicCharId3.trim().equals("fgys")){
				sql.append(" and a.commDictPublicCharId3 = '1140' or ")
					.append(" a.commDictPublicCharId3 = '1141'");
			}else{
				sql.append(" and a.commDictPublicCharId3 = '").append( commDictPublicCharId3.trim() ).append("'");
			}
		}
		if(commConfigDegreeId != null && commConfigDegreeId.trim().length() > 0){
			sql.append(" and a.commConfigDegreeId = '").append( commConfigDegreeId.trim() ).append("'");
		}
		if(commConfigDegreeLevelId != null && commConfigDegreeLevelId.trim().length() > 0){
			sql.append(" and a.commConfigDegreeLevelId = '").append( commConfigDegreeLevelId.trim() ).append("'");
		}
		if(commConfigProfessionId != null && commConfigProfessionId.trim().length() > 0){
			sql.append(" and a.commConfigProfessionId = '").append( commConfigProfessionId.trim() ).append("'");
		}
		if(commConfigLocationId3 != null && commConfigLocationId3.trim().length() > 0){
			sql.append(" and b.commConfigLocationId3 = '").append( commConfigLocationId3.trim() ).append("'");
		}
		if(commConfigLocationTownId != null && commConfigLocationTownId.trim().length() > 0){
			sql.append(" and b.commConfigLocationTownId = '").append( commConfigLocationTownId.trim() ).append("'");
		}
		if(commClvId != null && commClvId.trim().length() > 0){
			sql.append(" and b.commClvId like '").append( commClvId.trim() ).append("%'");
		}
		if(townCode != null && townCode.trim().length() > 0){
			sql.append(" and b.commConfigLocationTownId = '").append( townCode.trim() ).append("'");
		}
		if(commConfigSexId != null && commConfigSexId.trim().length() > 0){
			sql.append(" and a.commConfigSexId = '").append( commConfigSexId.trim() ).append("'");
		}
		if(isLocation != null){
			sql.append(" and a.islocation =  '").append(isLocation).append("'");
		}
		if(ageS != null && ageS.trim().length() > 0&&ageE != null && ageE.trim().length() > 0){
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			String nowDate = sdf.format(d);
			Integer min = (Integer.valueOf(nowDate) - Integer.valueOf(ageE));
			Integer max = (Integer.valueOf(nowDate) - Integer.valueOf(ageS));
			sql.append(" and a.birthday between to_date('").append( min.toString() ).append("','yyyy') and to_date('").append( max.toString() ).append("','yyyy') ");
		}
		return sql.toString();
	}
	
	public List<?> getData(String hspId, String name, String idNo,String empNo,String commConfigNationalityId,String workCertificateNo,String commDictPublicCharId1,String commDictPublicCharId2,String commConfigPositiontitleId,String commConfigEmptitleId,String commDictPublicCharId3,String commConfigDegreeId,String commConfigDegreeLevelId,String commConfigProfessionId,String commConfigLocationId3,
			   String commConfigLocationTownId,String commClvId,String staffId,String orderNo, int count, int pageSize,List listId,String townCode,String commConfigSexId,String staffHspId,String ageS,String ageE, Long isLocation) {
		try{
			String sql = this.createQueryStr(hspId, name, idNo, empNo, commConfigNationalityId, workCertificateNo, commDictPublicCharId1, commDictPublicCharId2, commConfigPositiontitleId, commConfigEmptitleId, commDictPublicCharId3, commConfigDegreeId, commConfigDegreeLevelId, commConfigProfessionId, commConfigLocationId3, commConfigLocationTownId, commClvId, staffId, listId, townCode, commConfigSexId, staffHspId, ageS, ageE,isLocation, 1);
			if(orderNo!=null && orderNo.trim().length()>0){
				sql += " order by "+orderNo.trim();
			}
			Query query = this.getSession().createQuery(sql);
			query.setFirstResult(count);
			query.setMaxResults(pageSize);
			List<?> list = query.list();
			log.debug("DAO public List<?> getData(String hspId, String name, String idNo, int count, int pageSize) OK ");
			return list;
		}catch(Exception e){
			log.error("DAO public List<?> getData(String hspId, String name, String idNo, int count, int pageSize) error", e);
			e.printStackTrace();
			return null;
		}
	}
	public List<?> getDataAll(String hspId, String name, String idNo,String empNo,String commConfigNationalityId,String workCertificateNo,String commDictPublicCharId1,String commDictPublicCharId2,String commConfigPositiontitleId,String commConfigEmptitleId,String commDictPublicCharId3,String commConfigDegreeId,String commConfigDegreeLevelId,String commConfigProfessionId, int count, int pageSize) {
		try{
			String tenantId = TenantIdHolder.get();
			String sql = "select a from HspStaffBaseinfo a ";
	        sql += " where a.tenantId = '"+tenantId+"' ";
			if(hspId != null && hspId.trim().length() > 0){
				sql += " and a.hspConfigBaseinfoId = '" + hspId.trim() +"'";
			}
			if(name != null && name.trim().length() > 0){
				sql += " and a.name like '%" + name.trim() +"%'";
			}
			if(idNo != null && idNo.trim().length() > 0){
				sql += " and a.idNo = '" + idNo.trim() +"'";
			}
			if(empNo != null && empNo.trim().length() > 0){
				sql += "and a.empNo = '" + empNo.trim() + "'";
			}
			if(commConfigNationalityId != null && commConfigNationalityId.trim().length() > 0){
				sql += "and a.commConfigNationalityId = '" + commConfigNationalityId.trim() + "'";
			}
			if(workCertificateNo != null && workCertificateNo.trim().length() > 0){
				sql += "and a.workCertificateNo = '" + workCertificateNo.trim() + "'";
			}
			if(commDictPublicCharId1 != null && commDictPublicCharId1.trim().length() > 0){
				sql += "and a.commDictPublicCharId1 = '" + commDictPublicCharId1.trim() + "'";
			}
			if(commDictPublicCharId2 != null && commDictPublicCharId2.trim().length() > 0){
				sql += "and a.commDictPublicCharId2 = '" + commDictPublicCharId2.trim() + "'";
			}
			if(commConfigPositiontitleId != null && commConfigPositiontitleId.trim().length() > 0){
				sql += "and a.commConfigPositiontitleId = '" + commConfigPositiontitleId.trim() + "'";
			}
			if(commConfigEmptitleId != null && commConfigEmptitleId.trim().length() > 0){
				sql += "and a.commConfigEmptitleId = '" + commConfigEmptitleId.trim() + "'";
			}
			if(commDictPublicCharId3 != null && commDictPublicCharId3.trim().length() > 0){
				sql += "and a.commDictPublicCharId3 = '" + commDictPublicCharId3.trim() + "'";
			}
			if(commConfigDegreeId != null && commConfigDegreeId.trim().length() > 0){
				sql += "and a.commConfigDegreeId = '" + commConfigDegreeId.trim() + "'";
			}
			if(commConfigDegreeLevelId != null && commConfigDegreeLevelId.trim().length() > 0){
				sql += "and a.commConfigDegreeLevelId = '" + commConfigDegreeLevelId.trim() + "'";
			}
			if(commConfigProfessionId != null && commConfigProfessionId.trim().length() > 0){
				sql += "and a.commConfigProfessionId = '" + commConfigProfessionId.trim() + "'";
			}
			Query query = this.getSession().createQuery(sql);
			query.setFirstResult(count);
			query.setMaxResults(pageSize);
			List<?> list = query.list();
			log.debug("DAO public List<?> getData(String hspId, String name, String idNo, int count, int pageSize) OK ");
			return list;
		}catch(Exception e){
			log.error("DAO public List<?> getData(String hspId, String name, String idNo, int count, int pageSize) error", e);
			e.printStackTrace();
			return null;
		}
	}
	
	
	public List<?> getHspData() {
		try{
			String tenantId = TenantIdHolder.get();
			String sql = "FROM HspStaffBaseinfo a WHERE a.tenantId = '"+tenantId+"'";
			
			Query query = this.getSession().createQuery(sql);
			query.setFirstResult(0);
			query.setMaxResults(10);
			List<?> list = query.list();
			log.debug("DAO public List<?> getData(String hspId, String name, String idNo, int count, int pageSize) OK ");
			return list;
		}catch(Exception e){
			log.error("DAO public List<?> getData(String hspId, String name, String idNo, int count, int pageSize) error", e);
			e.printStackTrace();
			return null;
		}
	}

	public String getNameById(String id) {
		try{
			String tenantId = TenantIdHolder.get();
			String name = "";
			List<?> list = this.getHibernateTemplate().find("from HspStaffBaseinfo a where a.id=? and a.tenantId = ?", new Object[]{id, tenantId});
			if(list!=null&&list.size()>0){
				HspStaffBaseinfo hspStaffBaseinfo = (HspStaffBaseinfo) list.get(0);
				name = hspStaffBaseinfo.getName();
			}
			log.debug("DAO public String getNameById(String id) success!");
			return name;
		}catch(Exception e){
			log.error("DAO public String getNameById(String id) error!",e);
			e.printStackTrace();
			return null;			
		}
	}

	public void save(HspStaffBaseinfo hspStaffBaseinfo) {
		try{
			String tenantId = TenantIdHolder.get();
			hspStaffBaseinfo.setTenantId(tenantId);
			this.getHibernateTemplate().save(hspStaffBaseinfo);
			log.debug("DAO public void save(HspStaffBaseinfo hspStaffBaseinfo) success!");
		}catch(Exception e){
			log.error("DAO public void save(HspStaffBaseinfo hspStaffBaseinfo) error!",e);
			e.printStackTrace();
		}
	}

	public void update(HspStaffBaseinfo hspStaffBaseinfo) {
		try{
			String tenantId = TenantIdHolder.get();
			hspStaffBaseinfo.setTenantId(tenantId);
			this.getHibernateTemplate().update(hspStaffBaseinfo);
			log.debug("DAO public void update(HspStaffBaseinfo hspStaffBaseinfo) success!");
		}catch(Exception e){
			log.error("DAO public void update(HspStaffBaseinfo hspStaffBaseinfo) error!",e);
			e.printStackTrace();
		}
	}
	public HspStaffBaseinfo getByEmpNo(String empNo){
		HspStaffBaseinfo hspStaffBaseinfo = null;
		try{
			String tenantId = TenantIdHolder.get();
			List<?> list = this.getHibernateTemplate().find("from HspStaffBaseinfo a where a.empNo=? and a.tenantId = ?", new Object[]{empNo, tenantId});
			if(list!=null&&list.size()>0){
				hspStaffBaseinfo=(HspStaffBaseinfo) list.get(0);
			}
			log.debug("getByEmpNo success!");
			return hspStaffBaseinfo;
		}catch(Exception e){
			log.error("getByEmpNo error!",e);
			e.printStackTrace();
			return null;
		}
	}
	public SecurityStaffBaseinfo getByStaffCode(String staffCode){
		SecurityStaffBaseinfo securityStaffBaseinfo = null;
		try{
			String tenantId = TenantIdHolder.get();
			List<?> list = this.getHibernateTemplate().find("from SecurityStaffBaseinfo a where a.staffCode=? and a.tenantId = ?", new Object[]{staffCode, tenantId});
			if(list!=null&&list.size()>0){
				securityStaffBaseinfo=(SecurityStaffBaseinfo) list.get(0);
			}
			log.debug("getByStaffCode success!");
			return securityStaffBaseinfo;
		}catch(Exception e){
			log.error("getByStaffCode error!",e);
			e.printStackTrace();
			return null;
		}
	}
	
	public HspStaffBaseinfo getStaffByUserId(String userId) {
		
		HspStaffBaseinfo data = new HspStaffBaseinfo();
		try{
			String tenantId = TenantIdHolder.get();
			String sql = " select a from HspStaffBaseinfo a,SecurityStaffBaseinfo b where a.tenantId = '"+tenantId+"' and a.id=b.hspStaffBaseinfoId and b.id='"+userId+"'";
			List list = this.getHibernateTemplate().find(sql);
			if(list!=null && list.size()>0){
				data = (HspStaffBaseinfo) list.get(0);
			}else{
				data = null;
			}
			return data;
		}catch(Exception e){
			return null;
		}
	}
	
	
	public List<?> getData(String hspId, String name, String idNo,String empNo,String commConfigNationalityId,String workCertificateNo,String commDictPublicCharId1,String commDictPublicCharId2,String commConfigPositiontitleId,String commConfigEmptitleId,String commDictPublicCharId3,String commConfigDegreeId,String commConfigDegreeLevelId,String commConfigProfessionId,String staffId,List listId) {
		try{
			String tenantId = TenantIdHolder.get();
			String sql = "select a from HspStaffBaseinfo a  where a.tenantId = '"+tenantId+"' ";
			if(listId!=null&&listId.size()>0){
				sql += " and a.hspConfigBaseinfoId in (";
				for(int i=0;i<listId.size();i++){
					sql+="'"+listId.get(i)+"',";
				}
				sql=sql.substring(0,sql.length()-1);
				sql+=")   ";
			}
			if(hspId != null && hspId.trim().length() > 0){
				sql += " and a.hspConfigBaseinfoId = '" + hspId.trim() +"'";
			}
			if(name != null && name.trim().length() > 0){
				sql += " and a.name like '%" + name.trim() +"%'";
			}
			if(idNo != null && idNo.trim().length() > 0){
				sql += " and a.idNo = '" + idNo.trim() +"'";
			}
			if(empNo != null && empNo.trim().length() > 0){
				sql += "and a.empNo = '" + empNo.trim() + "'";
			}
			if(commConfigNationalityId != null && commConfigNationalityId.trim().length() > 0){
				sql += "and a.commConfigNationalityId = '" + commConfigNationalityId.trim() + "'";
			}
			if(workCertificateNo != null && workCertificateNo.trim().length() > 0){
				sql += "and a.workCertificateNo = '" + workCertificateNo.trim() + "'";
			}
			if(commDictPublicCharId1 != null && commDictPublicCharId1.trim().length() > 0){
				sql += "and a.commDictPublicCharId1 = '" + commDictPublicCharId1.trim() + "'";
			}
			if(commDictPublicCharId2 != null && commDictPublicCharId2.trim().length() > 0){
				sql += "and a.commDictPublicCharId2 = '" + commDictPublicCharId2.trim() + "'";
			}
			if(commConfigPositiontitleId != null && commConfigPositiontitleId.trim().length() > 0){
				sql += "and a.commConfigPositiontitleId = '" + commConfigPositiontitleId.trim() + "'";
			}
			if(commConfigEmptitleId != null && commConfigEmptitleId.trim().length() > 0){
				sql += "and a.commConfigEmptitleId = '" + commConfigEmptitleId.trim() + "'";
			}
			if(commDictPublicCharId3 != null && commDictPublicCharId3.trim().length() > 0){
				sql += "and a.commDictPublicCharId3 = '" + commDictPublicCharId3.trim() + "'";
			}
			if(commConfigDegreeId != null && commConfigDegreeId.trim().length() > 0){
				sql += "and a.commConfigDegreeId = '" + commConfigDegreeId.trim() + "'";
			}
			if(commConfigDegreeLevelId != null && commConfigDegreeLevelId.trim().length() > 0){
				sql += "and a.commConfigDegreeLevelId = '" + commConfigDegreeLevelId.trim() + "'";
			}
			if(commConfigProfessionId != null && commConfigProfessionId.trim().length() > 0){
				sql += "and a.commConfigProfessionId = '" + commConfigProfessionId.trim() + "'";
			}
			List<?> list = getHibernateTemplate().find(sql);
			log.debug("DAO public List<?> getData(String hspId, String name, String idNo, int count, int pageSize) OK ");
			return list;
		}catch(Exception e){
			log.error("DAO public List<?> getData(String hspId, String name, String idNo, int count, int pageSize) error", e);
			e.printStackTrace();
			return null;
		}
	}
	
	public List<?> getAjaxCountry(String flag, String input) {
		// TODO Auto-generated method stub
		String newName = null;
		String sql = "select a from CommConfigCountry a where 1=1 ";
		if ((flag != null && flag.equals("1")) && (input != null && !input.equals(""))) {
			sql += " and upper(a.inputCode) like '%" + input.trim().toUpperCase() + "%'";
		}
		if ((flag != null && flag.equals("2")) && (input != null && !input.equals(""))) {
			sql += " and a.itemCode like '%" + input.trim() + "%'";
		}
		if ((flag != null && flag.equals("3")) && (input != null && !input.equals(""))) {
			try {
				newName = new String(input.trim().getBytes("ISO8859_1"), "gb2312");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			sql += " and a.itemName like '%" + input.trim() + "%'";
		}
		Query q = getSession().createQuery(sql);
		q.setFirstResult(0); 
		q.setMaxResults(Integer.valueOf("10"));
		List<?> list = q.list();
		return list;
	}
	
	public List<?> getAjaxRelationship(String flag, String input) {
		// TODO Auto-generated method stub
		String newName = null;
		String sql = "select a from CommConfigRelationship a where 1=1 ";
		if ((flag != null && flag.equals("1")) && (input != null && !input.equals(""))) {
			sql += " and upper(a.inputCode) like '%" + input.trim().toUpperCase() + "%'";
		}
		if ((flag != null && flag.equals("2")) && (input != null && !input.equals(""))) {
			sql += " and a.itemCode like '%" + input.trim() + "%'";
		}
		if ((flag != null && flag.equals("3")) && (input != null && !input.equals(""))) {
			try {
				newName = new String(input.trim().getBytes("ISO8859_1"), "gb2312");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			sql += " and a.itemName like '%" + input.trim() + "%'";
		}
		Query q = getSession().createQuery(sql);
		q.setFirstResult(0); 
		q.setMaxResults(Integer.valueOf("10"));
		List<?> list = q.list();
		return list;
	}
	
	public List<?> getPeoples(String hspConfigBaseInfoId, String flag,
			String input) {
		// TODO Auto-generated method stub
		String tenantId = TenantIdHolder.get();
		String newName = null;
		String sql = "select a from HspStaffBaseinfo a ,HspConfigBaseinfo c  where a.tenantId = '"+tenantId+"' and a.hspConfigBaseinfoId = c.id " +
		"and c.hspType = "+ TJInit.getProperty("classFlag").trim() ;
		
		//市级  //县级在第一个sql中已经区分  还需要区分每个县各自的人
    	if(TJInit.getProperty("classFlag").trim().equals("1")){
    		sql +=" and c.hspConfigBaseinfoId3 =  '"+ hspConfigBaseInfoId +"'";
    	}else{
    		sql +=" and c.hspConfigBaseinfoId2 =  '"+ hspConfigBaseInfoId +"'";
    	}
		if ((flag != null && flag.equals("1")) && (input != null && !input.equals(""))) {
			sql += " and upper(a.inputCode) like '%" + input.trim().toUpperCase() + "%'";
		}
		if ((flag != null && flag.equals("2")) && (input != null && !input.equals(""))) {
			sql += " and a.empNo like '%" + input.trim() + "%'";
		}
		if ((flag != null && flag.equals("3")) && (input != null && !input.equals(""))) {
			try {
				newName = new String(input.trim().getBytes("ISO8859_1"), "gb2312");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			sql += " and a.name like '%" + input.trim() + "%'";
		}
		Query q = getSession().createQuery(sql);
		q.setFirstResult(0); 
		q.setMaxResults(Integer.valueOf("10"));
		List<?> list = q.list();
		return list;
	}
	
	public List<?> getAllPeoples(String flag, String input) {
		// TODO Auto-generated method stub
		String newName = null;
		String sql = "select a from SecurityUserBaseinfo a where 1=1";
		if ((flag != null && flag.equals("1")) && (input != null && !input.equals(""))) {
			sql += " and upper(a.inputCode) like '%" + input.trim().toUpperCase() + "%'";
		}
		if ((flag != null && flag.equals("2")) && (input != null && !input.equals(""))) {
			sql += " and a.pmi like '%" + input.trim() + "%'";
		}
		if ((flag != null && flag.equals("3")) && (input != null && !input.equals(""))) {
			try {
				newName = new String(input.trim().getBytes("ISO8859_1"), "gb2312");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			sql += " and a.name like '%" + newName.trim() + "%'";
		}
		Query q = getSession().createQuery(sql);
		q.setFirstResult(0); 
		q.setMaxResults(Integer.valueOf("10"));
		List<?> list = q.list();
		return list;
	}
	
	public List<?> getHsp(String staffId) {
		String tenantId = TenantIdHolder.get();
		String hql="from HspConfigBaseinfo h,SecurityStaffBaseinfo s where h.id=s.hspConfigBaseinfoId and s.id='"+staffId+"' and s.tenantId = '"+tenantId+"'";
		return getHibernateTemplate().find(hql);
	}
	
	
	
	public List<?> getHsp(String comlun,String whereComlun,String value) {
		String tenantId = TenantIdHolder.get();
		String hql="select distinct h."+comlun+" from HspConfigBaseinfo h where h.tenantId = '"+tenantId+"' and h."+whereComlun+"='"+value+"'";
		List<?> ls=getHibernateTemplate().find(hql);
		if(ls!=null&&ls.size()>0){
			return ls;
		}
		return null;
	}
	
	public List<?> getBaseInfo(String colmun,String sttaf,String flag, String input) {
		String tenantId = TenantIdHolder.get();
		String hql="from HspConfigBaseinfo h where h.tenantId = '"+tenantId+"' and h."+colmun+"='"+sttaf+"'";
		if(flag.equals("1")&&input.equals("")){
			
		}else{
			hql+=" and h.inputCode='"+input.trim().toUpperCase()+"'";
		}
		if(flag.equals("2")){
			hql+=" and h.itemCode='"+input.trim()+"'";
		}
		if(flag.equals("3")){
			hql+=" and h.name='"+input.trim()+"'";
		}
		hql+=" order by h.seqNo";
		Query q=this.getSession().createQuery(hql);
		q.setMaxResults(10);
		return q.list();
	}
	
	public List<?> getHsp(String falg, String input, int count, int pageSize) {
		String tenantId = TenantIdHolder.get();
		String hql="from HspConfigBaseinfo h where h.tenantId = '"+tenantId+"'";
		if(input!=null&&input.trim().length()>0&&falg.equals("1")){
			hql+=" and h.inputCode like '%"+input.trim().toUpperCase()+"%'";
		}
		if(falg.equals("2")){
			hql+=" and h.itemCode='"+input.trim()+"'";
		}
		if(falg.equals("3")){
			hql+=" and h.itemName like '%"+input.trim()+"%'";
		}
		Query q=this.getSession().createQuery(hql);
		q.setFirstResult(count);
		q.setMaxResults(pageSize);
		return q.list();
	}
	
	/**
	 * 按机构id获取下属机构id集合
	 * @param hspId
	 * @return
	 */
	private List<String> getSubHspIds(String id){
		//查询该id的下属机构id
		String hql = "select sub.id from HspConfigBaseinfoLocalBase super, HspConfigBaseinfoLocalBase sub where super.itemCode = sub.parentItemCode and super.id = ?";
		List<?> list = this.getHibernateTemplate().find(hql, id);
		if(list != null && list.size() > 0){
			List<String> hspIdList = new ArrayList<String>();
			for(Iterator<?> it = list.iterator(); it.hasNext(); ){
				String subId = (String)it.next();
				hspIdList.add(subId);
				//根据id迭代获取下属机构id列表
				List<String> subIdList = getSubHspIds(subId);
				if(subIdList != null && subIdList.size() > 0){
					hspIdList.addAll(subIdList);
				}
			}
			return hspIdList;
		}
		return null;
	}		
	
	
	
	
}
















