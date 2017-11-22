package com.tianjian.empi.dao.hibernate;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.comm.bean.CommConfigLocation;
import com.tianjian.comm.bean.CommConfigLocationTown;
import com.tianjian.comm.bean.CommConfigLocationVillage;
import com.tianjian.empi.bean.SecurityConfigUsers;
import com.tianjian.empi.bean.SecurityUserBaseinfo;
import com.tianjian.empi.dao.ISecurityUserBaseinfoDAO;
import com.tianjian.empi.struts.form.SecurityUserBaseinfoForm;
import com.tianjian.hsp.bean.HspConfigBaseinfo;
import com.tianjian.security.bean.SecurityDataObjectType;
import com.tianjian.security.bean.SecurityDataObjectVsRoles;
import com.tianjian.security.bean.SecurityStaffBaseinfo;

public class SecurityUserBaseinfoDAO extends HibernateDaoSupport implements ISecurityUserBaseinfoDAO {

	private static final Logger log = LogManager.getLogger(SecurityUserBaseinfoDAO.class);
	
	
	/**
	 * 根据sql语句得到符合查询条件的记录个数
	 * 
	 * @param sqlpage
	 * @return
	 */
	public int getrowsnum(String sqlpage) {
		try {
			Query query = this.getSession().createQuery(sqlpage);
			return Integer.parseInt(query.list().get(0).toString());
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}
	
	/** 保存客户个人密码 */
	public void save(Object obj) {
        try{
            getHibernateTemplate().save(obj);
            log.debug("save success!");
        } catch(Exception re) {
            log.error("save fail!", re);
            re.printStackTrace();
        }
    }

	/** 通过Id获取客户个人信息 */
	public SecurityUserBaseinfo getById(String id) {
		try {
			SecurityUserBaseinfo temp = null;
			//System.out.println(id);
			List<?> ls = getHibernateTemplate().find(" from SecurityUserBaseinfo a where a.id = ? ", id);
			if (ls != null && ls.size() > 0) {
				temp = (SecurityUserBaseinfo) ls.get(0);
			}
			return temp;
		}
		catch (Exception re) {
			log.error("getById error!", re);
			re.printStackTrace();
			return null;
		}
	}
	public SecurityUserBaseinfo getByName(String name) {
		try {
			SecurityUserBaseinfo temp = null;
			List<?> ls = getHibernateTemplate().find(" from SecurityUserBaseinfo a where a.name = ? ", name);
			if (ls != null && ls.size() > 0) {
				temp = (SecurityUserBaseinfo) ls.get(0);
			}
			return temp;
		}
		catch (Exception re) {
			log.error("getById error!", re);
			re.printStackTrace();
			return null;
		}
	}
	/** 通过pmi获取客户个人信息 */
	public SecurityUserBaseinfo getByItemCode(String itemCode) {
		try {
			SecurityUserBaseinfo temp = null;
			List<?> ls = getHibernateTemplate().find(" from SecurityUserBaseinfo a where a.pmi = ? ", itemCode);
			if (ls != null && ls.size() > 0) {
				temp = (SecurityUserBaseinfo) ls.get(0);
			}
			return temp;
		}
		catch (Exception re) {
			log.error("getByItemCode error!", re);
			re.printStackTrace();
			return null;
		}
	}
	
	public int getMaxSeqNo(){
		int temp = 1;
		try {
			List<?> l = getHibernateTemplate().find(" select max(aa.seqNo) from SecurityIHEPatientIdList aa ");
			if(l != null && l.size() > 0){
				Object o = l.get(0);
				if(o==null){
					return temp;
				}else{
					temp = Integer.valueOf(String.valueOf(l.get(0))).intValue()+1;
				}
				
			}
			log.debug("getMaxSeqNo success!");
			return temp;
        } catch (Exception re) {
        	log.error("getMaxSeqNo fail!",re);
        	re.printStackTrace();
			return new Integer("1"); 
        }
	} 
	
	/** 更新客户个人信息 */
	public void update(Object obj) {
		try {
			getHibernateTemplate().update(obj);
		}catch (Exception re) {
			log.error("update error!", re);
			re.printStackTrace();
		}
	}

	/** 删除客户个人信息 */
	public void delete(Object obj) {
		try {
			getHibernateTemplate().delete(obj);
		}catch (Exception re) {
			log.error("delete error!", re);
			re.printStackTrace();
		}
	}

	/** 获取当前页面客户记录列表 */
	public List<?> getUser(String id, String name, String inputCode,String sexId,String idNo,String cardType,String cardNo , String nameEn, String orderNo, int curCount, int quChuCount,List<String[]> many) {
		try {
			String sql = " select a.id,"+
			" a.pmi, "+
			" a.name, "+
			" a.nameEn, "+
			" a.inputCode, "+
			" a.commConfigSexId, "+
			" a.birthPlace, "+
			" a.dateOfBirth, "+
			" a.commConfigCountryId, "+
			" a.commConfigNationalityId, "+
			" a.commConfigIdTypeId, "+
			" a.idNo, "+
			" a.sscid, "+
			" a.commConfigAboId, "+
			" a.commConfigRhId, "+
			" a.commConfigDegreeId, "+
			" a.commConfigMaritalStatusId, "+
			" a.commConfigLocationId1, "+
			" a.commConfigLocationId2, "+
			" a.commConfigLocationId3, "+
			" a.commConfigLocationTownId, "+
			" a.commConfigLocationVillageid, "+
			" a.mailingAddress, "+
			" a.zipcode,"+ 
			" a.phone,"+ 
			" a.EMail,"+
			" a.photoPath,"+
			" a.commConfigRelationshipId,"+
			" a.contactPersonName,"+
			" a.contactPersonPhone,"+
			" a.comments ";
			if((cardType!=null&&cardType.trim().length()>0)||(cardNo!=null&&cardNo.trim().length()>0)){
				sql += " from SecurityUserBaseinfo a,SecurityUserVsCard b where a.id=b.securityUserBaseinfoId and b.cardStatus<>2";
			}else{
				sql += " from SecurityUserBaseinfo a where 1=1 ";
			}	       
			if (id.trim().length() > 0) {
				sql += " and a.id = '" + id.trim() + "' ";
			}
			if (name.trim().length() > 0) {
				sql += " and a.name like '%" + name.trim() + "%' ";
			}
			if (inputCode.trim().length() > 0) {
				sql += " and a.inputCode like '" + inputCode.trim().toUpperCase() + "%' ";
			}
			if (sexId.trim().length() > 0) {
				sql += " and a.commConfigSexId = '" + sexId.trim() + "' ";
			}
			if(idNo.trim().length()>0){
				sql +=" and a.idNo='"+idNo.trim()+"'";
			}
			if (cardType.trim().length() > 0) {
				sql += " and b.commConfigCardtypeId = '" + cardType.trim() + "' ";
			}
			if (cardNo.trim().length() > 0) {
				sql += " and b.cardNo like '%" + cardNo.trim() + "%' ";
			}
			if (nameEn.trim().length() > 0) {
				sql += " and a.nameEn = '" + nameEn.trim() + "' ";
			}
			if(many!=null&&many.size()>0){
				sql += " and (";
				for(int i=0;i<many.size();i++){
					String[] s=many.get(i);
					if(i==0){
						sql += s[0].trim()+"='"+s[1].trim()+"'";
					}else{
						sql += " or "+s[0].trim()+"='"+s[1].trim()+"'";
					}
				}
				sql += ")";
			}
	    	if(orderNo.trim().length() > 0){
	    		sql += " order by " + orderNo;
	    	} 
	    	Query q = getSession().createQuery(sql);
	    	q.setFirstResult(curCount); 
			q.setMaxResults(quChuCount); 
			List<?> l=q.list();
		    
			return l;
		}
		catch (Exception re) {
			log.error("getUser error!", re);
			re.printStackTrace();
			return null;
		}
	}

	 public List<?> getUser(String id, String name, String inputCode,String sexId,String idNo,String cardType,String cardNo , String nameEn, String orderNo, 
	    		String columnName,String place){
		 try {
				String sql = " select a.id,"+
				" a.pmi, "+
				" a.name, "+
				" a.nameEn, "+
				" a.inputCode, "+
				" a.commConfigSexId, "+
				" a.birthPlace, "+
				" a.dateOfBirth, "+
				" a.commConfigCountryId, "+
				" a.commConfigNationalityId, "+
				" a.commConfigIdTypeId, "+
				" a.idNo, "+
				" a.sscid, "+
				" a.commConfigAboId, "+
				" a.commConfigRhId, "+
				" a.commConfigDegreeId, "+
				" a.commConfigMaritalStatusId, "+
				" a.commConfigLocationId1, "+
				" a.commConfigLocationId2, "+
				" a.commConfigLocationId3, "+
				" a.commConfigLocationTownId, "+
				" a.commConfigLocationVillageid, "+
				" a.mailingAddress, "+
				" a.zipcode,"+ 
				" a.phone,"+ 
				" a.EMail,"+
				" a.photoPath,"+
				" a.commConfigRelationshipId,"+
				" a.contactPersonName,"+
				" a.contactPersonPhone,"+
				" a.comments ";
				if((cardType!=null&&cardType.trim().length()>0)||(cardNo!=null&&cardNo.trim().length()>0)){
					sql += " from SecurityUserBaseinfo a,SecurityUserVsCard b where a.id=b.securityUserBaseinfoId and b.cardStatus<>2 ";
				}else{
					sql += " from SecurityUserBaseinfo a where 1=1 ";
				}	       
				if (id.trim().length() > 0) {
					sql += " and a.id = '" + id.trim() + "' ";
				}
				if (name.trim().length() > 0) {
					sql += " and a.name like '%" + name.trim() + "%' ";
				}
				if (inputCode.trim().length() > 0) {
					sql += " and a.inputCode like '" + inputCode.trim().toUpperCase() + "%' ";
				}
				if (sexId.trim().length() > 0) {
					sql += " and a.commConfigSexId = '" + sexId.trim() + "' ";
				}
				if(idNo.trim().length()>0){
					sql +=" and a.idNo='"+idNo.trim()+"'";
				}
				if (cardType.trim().length() > 0) {
					sql += " and b.commConfigCardtypeId = '" + cardType.trim() + "' ";
				}
				if (cardNo.trim().length() > 0) {
					sql += " and b.cardNo like '%" + cardNo.trim() + "%' ";
				}
				if (nameEn.trim().length() > 0) {
					sql += " and a.nameEn = '" + nameEn.trim() + "' ";
				}
		    	if(columnName!=null&&columnName.trim().length()>0&&place.trim().length()>0){
		    		sql += " and "+columnName.trim()+" ='"+place.trim()+"'";
		    	}
		    	if(orderNo.trim().length() > 0){
		    		sql += " order by " + orderNo;
		    	} 
		    	Query q = getSession().createQuery(sql);
				List<?> l=q.list();
			    
				return l;
			}
			catch (Exception re) {
				log.error("getUser error!", re);
				re.printStackTrace();
				return null;
			}
	 }
	/** 获取客户记录总数 */
	public int getCountUser(String id, String name, String inputCode,String sexId,String idNo,String cardType,String cardNo , String nameEn
			,List<String[]> many) {
		try {
			int count = 0;
			String sql="";
			if((cardType!=null&&cardType.trim().length()>0)||(cardNo!=null&&cardNo.trim().length()>0)){
				sql = "select count(*) from SecurityUserBaseinfo a,SecurityUserVsCard b where a.id=b.securityUserBaseinfoId and b.cardStatus<>2";
			}
			else{
				sql="select count(*) from SecurityUserBaseinfo a where 1=1 ";
			}
			if (id.trim().length() > 0) {
				sql += " and a.id = '" + id.trim() + "' ";
			}
			if (name.trim().length() > 0) {
				sql += " and a.name like '%" + name.trim() + "%' ";
			}
			if (inputCode.trim().length() > 0) {
				sql += " and a.inputCode like '" + inputCode.trim().toUpperCase() + "%' ";
			}
			if (sexId.trim().length() > 0) {
				sql += " and a.commConfigSexId = '" + sexId.trim() + "' ";
			}
			if(idNo.trim().length()>0){
				sql +=" and a.idNo='"+idNo.trim()+"'";
			}
			if (cardType.trim().length() > 0) {
				sql += " and b.commConfigCardtypeId = '" + cardType.trim() + "' ";
			}
			if (cardNo.trim().length() > 0) {
				sql += " and b.cardNo like '%" + cardNo.trim() + "%' ";
			}
			if (nameEn.trim().length() > 0) {
				sql += " and a.nameEn = '" + nameEn.trim() + "' ";
			}
			if(many!=null&&many.size()>0){
				sql += " and (";
				for(int i=0;i<many.size();i++){
					String[] s=many.get(i);
					if(i==0){
						sql += s[0].trim()+"='"+s[1].trim()+"'";
					}else{
						sql += " or "+s[0].trim()+"='"+s[1].trim()+"'";
					}
				}
				sql += ")";
			}
			List<?> li = getHibernateTemplate().find(sql);
			if (li != null && li.size() > 0) {
				count = Integer.valueOf(String.valueOf(li.get(0))).intValue();
			}
			return count;
		}
		catch (Exception re) {
			log.error("getCountUser error!", re);
			re.printStackTrace();
			return 0;
		}
	}

	/** 获取客户姓名 */
	public String getName(String id) {
		try {
			return null;
		}
		catch (Exception re) {
			log.error("getName error!", re);
			re.printStackTrace();
			return null;
		}
	}

	public List<?> getUsers(String userId, String userName, String inputCode, String orderNo, int from, int length,List<SecurityStaffBaseinfo> list) {
		try {
			String sql = "";
			sql += "from SecurityUserBaseinfo a ";
			if (userId.trim().length() > 0) {
				sql += " where a.pmi like '%" + userId + "%' ";
			}
			if (userName.trim().length() > 0) {
				if (userId.trim().length() > 0) {
					sql += " and a.name like '%" + userName + "%' ";
				} else {
					sql += " where a.name like '%" + userName + "%' ";
				}
			}
			if (inputCode.trim().length() > 0) {
				if (sql.indexOf(" where ") > -1) {
					sql += " and a.inputCode like '%" + inputCode.trim().toUpperCase() + "%' ";
				} else {
					sql += " where a.inputcode like '%" + inputCode.trim().toUpperCase() + "%' ";
				}
			}
	    	if(list!=null&&list.size()>0){
	    		sql+=" and a.createUserId in(";
	    		for(int i=0;i<list.size();i++){
	    			sql+="'"+list.get(i).getId().trim()+"',";
	    		}
	    		sql=sql.substring(0,sql.length()-1);
	    		sql+=")";
	    	}
			if (orderNo.trim().length() > 0) {
				sql += " order by " + orderNo;
			}
			Query q = getSession().createQuery(sql);
			q.setFirstResult(from);
			q.setMaxResults(length);
			List<?> l = q.list();
			return l;
		}
		catch (RuntimeException re) {
			log.error(re.toString());
			throw re;
		}
	}

	public int getUsersCount(String userId, String userName, String inputCode,List<SecurityStaffBaseinfo> list) {
		try {
			int count = 0;
			String sql = "select count(*) ";
			sql += "from SecurityUserBaseinfo a ";
			if (userId.trim().length() > 0) {
				sql += " where a.pmi like '%" + userId + "%' ";
			}
			if (userName.trim().length() > 0) {
				if (userId.trim().length() > 0) {
					sql += " and a.name like '%" + userName + "%' ";
				} else {
					sql += " where a.name like '%" + userName + "%' ";
				}
			}
			if (inputCode.trim().length() > 0) {
				if (sql.indexOf(" where ") > -1) {
					sql += " and a.inputCode like '%" + inputCode.trim().toUpperCase() + "%' ";
				} else {
					sql += " where a.inputcode like '%" + inputCode.trim().toUpperCase()+ "%' ";
				}
			}
	    	if(list!=null&&list.size()>0){
	    		sql+=" and a.createUserId in(";
	    		for(int i=0;i<list.size();i++){
	    			sql+="'"+list.get(i).getId().trim()+"',";
	    		}
	    		sql=sql.substring(0,sql.length()-1);
	    		sql+=")";
	    	}
			List<?> li = getHibernateTemplate().find(sql);
			if (li != null && li.size() > 0) {
				count = Integer.valueOf(String.valueOf(li.get(0))).intValue();
			}
			return count;
		}
		catch (RuntimeException re) {
			log.error(re.toString());
			throw re;
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
//	 //---------------------------fanjf添加----------------------------------------------------
//    public SecurityConfigUsers selcectbeanbyuserid(String userid){
//		try{
//		List<?> list = this.getHibernateTemplate().find("from SecurityConfigUsers s where s.securityUserBaseinfoId='" + userid + "'");
//		
//		if(list.isEmpty() || list == null){
//			return null;
//		}
//		
//		return (SecurityConfigUsers) list.get(0);
//		}catch(Exception re){
//			log.error("selcectbeanbyuserid error!",re);
//			re.printStackTrace();
//			return null; 
//		}
//	}
//	
//	public void insertconfigbean(SecurityConfigUsers bean){	
//		try{
//		this.getHibernateTemplate().saveOrUpdate(bean);
//		}catch(Exception re){
//			log.error("getByCode error!",re);
//			re.printStackTrace();
//		}
//	}
	
	public SecurityUserBaseinfo findUserInfoById(String userid){
		try{
			List<?> list = this.getHibernateTemplate().find("from SecurityUserBaseinfo s where s.pmi='" + userid + "'");
			
			if(list.isEmpty() || list == null || list.size() == 0){
				return null;
			}
			
			return (SecurityUserBaseinfo) list.get(0);
		}catch(Exception re){
			log.error("findUserInfoById error!",re);
			re.printStackTrace();
			return null; 
		}
		}
	/**取性别*/
	public String getcommConfigSex(String commConfigSexId){
		
		List<?> list = this.getHibernateTemplate().find("select s.itemName from CommConfigSex s where s.itemCode = '"+commConfigSexId+"'");
		if(list.isEmpty() || list == null || list.size() == 0){
			return null;
		}
		return (String) list.get(0);
	}
	/**取国籍*/
	public String getcommConfigCountry(String commConfigCountryId){
		
		List<?> list = this.getHibernateTemplate().find("select s.itemName from CommConfigCountry s where s.itemCode = '"+commConfigCountryId+"'");
		if(list.isEmpty() || list == null || list.size() == 0){
			return null;
		}
		return (String) list.get(0);
	}
	/**取名族*/
	public String getcommConfigNationality(String commConfigNationalityId){
		
		List<?> list = this.getHibernateTemplate().find("select s.itemName from CommConfigNationality s where s.itemCode = '"+commConfigNationalityId+"'");
		if(list.isEmpty() || list == null || list.size() == 0){
			return null;
		}
		return (String) list.get(0);
	}
	/**取证件*/
	public String getcommConfigIdType(String commConfigIdTypeId){
		
		List<?> list = this.getHibernateTemplate().find("select s.itemName from CommConfigIdType s where s.itemCode = '"+commConfigIdTypeId+"'");
		if(list.isEmpty() || list == null || list.size() == 0){
			return null;
		}
		return (String) list.get(0);
	}
	/**取学历*/
	public String getcommConfigDegree(String commConfigDegreeId){
		
		List<?> list = this.getHibernateTemplate().find("select s.itemName from CommConfigDegree s where s.itemCode= '"+commConfigDegreeId+"'");
		if(list.isEmpty() || list == null || list.size() == 0){
			return null;
		}
		return (String) list.get(0);
	}
	/**取婚姻*/
	public String getcommConfigMaritalStatus(String commConfigMaritalStatusId){
		
		List<?> list = this.getHibernateTemplate().find("select s.itemName from CommConfigMaritalStatus s where s.itemCode = '"+commConfigMaritalStatusId+"'");
		if(list.isEmpty() || list == null || list.size() == 0){
			return null;
		}
		return (String) list.get(0);
	}
	/**取关系*/
	public String getcommConfigRelationship(String commConfigRelationshipId){
		
		List<?> list = this.getHibernateTemplate().find("select s.itemName from CommConfigRelationship s where s.itemCode = '"+commConfigRelationshipId+"'");
		if(list.isEmpty() || list == null || list.size() == 0){
			return null;
		}
		return (String) list.get(0);
	}
	
	public int getCount() {
		try {
			int count = 0;
			String sql = "select count(*) ";
			sql += "from SecurityUserBaseinfo a ";
			List<?> list = getHibernateTemplate().find(sql);
			if (list != null && list.size() > 0) {
				count = Integer.valueOf(String.valueOf(list.get(0))).intValue();
			}
			return count;
		}
		catch (RuntimeException re) {
			log.error(re.toString());
			throw re;
		}
	}
	
	public List<?> getUser(String id, String pmi, String name, String nameEn,
			String inputCode,String columnName,String place) {
		try {
			//String sql = "select * ";
			String sql = " from SecurityUserBaseinfo a where 1=1 ";
			if (id.trim().length() > 0) {
				sql += " and a.id = '" + id.trim() + "' ";
			}
			if (pmi.trim().length() > 0) {
				sql += " and a.pmi like '%" + pmi.trim() + "%' ";
			}
			if (name.trim().length() > 0) {
				sql += " and a.name like '%" + name.trim() + "%' ";
			}
			if (nameEn.trim().length() > 0) {
				sql += " and a.nameEn = '" + nameEn.trim() + "' ";
			}
			if (inputCode.trim().length() > 0) {
				sql += " and a.inputCode like '" + inputCode.trim().toUpperCase() + "%' ";
			}
			if(columnName!=null&&columnName.trim().length()>0&&place.trim().length()>0){
	    		sql += " and "+columnName.trim()+" ='"+place.trim()+"'";
	    	}
			sql+=" order by pmi asc";
			List<?> list = getHibernateTemplate().find(sql);
			
			return list;
		}
		catch (Exception re) {
			log.error("getCountUser error!", re);
			re.printStackTrace();
			return null;
		}
	}
	
	public String getById(Object obj,String value) {
		String hql="select s.itemCode from "+obj+" s where s.itemName='"+value+"'";
		List<?> ls=getHibernateTemplate().find(hql);
		if(ls==null||ls.size()==0){
			return null;
		}else{
			return ls.get(0).toString();
		}
	}
	
	public int getCheckPmi(String pmi) {
		return getHibernateTemplate().find("from SecurityUserBaseinfo s where s.idNo=?",pmi).size();
	}
	
	 //---------------------------fanjf添加----------------------------------------------------
    public SecurityConfigUsers selcectbeanbyuserid(String userid){
		try{
		List<?> list = this.getHibernateTemplate().find("from SecurityConfigUsers s where s.securityUserBaseinfoId='" + userid + "'");
		
		if(list.isEmpty() || list == null){
			return null;
		}
		
		return (SecurityConfigUsers) list.get(0);
		}catch(Exception re){
			log.error("selcectbeanbyuserid error!",re);
			re.printStackTrace();
			return null; 
		}
	}
	
	public void insertconfigbean(SecurityConfigUsers bean){	
		try{
		this.getHibernateTemplate().saveOrUpdate(bean);
		}catch(Exception re){
			log.error("getByCode error!",re);
			re.printStackTrace();
		}
	}
	
	public List selectBaseinfobycodee(final String sql, final int start,
			final int everypage) {
		List list = (List) (this.getHibernateTemplate()
				.execute(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query q = session.createQuery(sql);
						q.setFirstResult(start);
						q.setMaxResults(everypage);
						List l = (List) q.list();

						return l;
					}
				}));
		if (list.isEmpty() || list == null || list.size() == 0) {
			return null;
		}

		return list;
	}
	
	public SecurityUserBaseinfo getByIdNo(String idNo) {
		// TODO Auto-generated method stub
		try {
			SecurityUserBaseinfo temp = null;
			List<?> ls = getHibernateTemplate().find(" from SecurityUserBaseinfo a where a.idNo = ? ", idNo);
			if (ls != null && ls.size() > 0) {
				temp = (SecurityUserBaseinfo) ls.get(0);
			}
			return temp;
		}
		catch (Exception re) {
			log.error("getByIdNo error!", re);
			re.printStackTrace();
			return null;
		}
	}
	public int getCountUser(String id, String pmi,String hspConfigBaseinfoId, String name,
			String commConfigIdTypeId, String idNo, String inputCode,
			SecurityUserBaseinfoForm hosform) {
		// TODO Auto-generated method stub
		try {
			int count = 0;
			String sql= "select count(*) from SecurityUserBaseinfo cu where 1=1";
			if(id.trim().length() > 0){
				sql += " and cu.id = '" + id.trim() + "' ";
		    }
			if(commConfigIdTypeId.trim().length() > 0){
				sql += " and cu.commConfigIdTypeId = '" + commConfigIdTypeId.trim() + "' ";
			}
			if(pmi.trim().length() > 0){
				sql += " and cu.pmi = '" + pmi.trim() + "' ";
			}
			if(name.trim().length() > 0){
				sql += " and cu.name like '%" + name.trim() + "%' ";
			}
			if(hosform.getCommConfigSexId() != null && hosform.getCommConfigSexId().trim().length() > 0){
				sql += " and cu.commConfigSexId = '" + hosform.getCommConfigSexId().trim() + "'";
			}
			if(hosform.getBirthPlace() != null && hosform.getBirthPlace().trim().length() > 0){
				sql += " and cu.birthPlace like '%" + hosform.getBirthPlace().trim() + "%'";
			}
			if(idNo.trim().length() > 0){
				sql += " and cu.idNo = '" + idNo.trim() + "' ";
			}
			if(inputCode.trim().length() > 0){
				sql += " and cu.inputCode like '" + inputCode.trim().toUpperCase() + "%' ";
			}
			if(hosform.getPmi()!=null&&hosform.getPmi().trim().length()>0){
				sql += " and cu.pmi ='" +hosform.getPmi().trim() +"'";
			}
			if(hosform.getNameEn() != null && hosform.getNameEn().trim().length() > 0){
				sql += " and cu.nameEn like '%" + hosform.getNameEn().trim() + "%'";
			}
			if(hosform.getBirth_date_year() != null && hosform.getBirth_date_year().trim().length() > 0){
				String date=hosform.getBirth_date_year().trim()+"-"+
				hosform.getBirth_date_month().trim()+"-"+
				hosform.getBirth_date_day().trim();
				//sql += "and a.dateOfBirth = to_date('" + date+"','yyyy/MM/dd')";
				sql += " and to_char(trunc(cu.dateOfBirth),'yyyy-MM-dd') = '"+date+"'";
			}
			if(hosform.getSscid() != null && hosform.getSscid().trim().length() > 0){
				sql += " and cu.sscid = '" + hosform.getSscid().trim() + "'";
			}
			if(hosform.getCommConfigCountryId() != null && hosform.getCommConfigCountryId().trim().length() > 0){
				sql += " and cu.commConfigCountryId = '" + hosform.getCommConfigCountryId().trim() + "'";
			}
			if(hosform.getCommConfigNationalityId() != null && hosform.getCommConfigNationalityId().trim().length() > 0){
				sql += " and cu.commConfigNationalityId = '" + hosform.getCommConfigNationalityId().trim() + "'";
			}
			if(hosform.getCommConfigDegreeId() != null && hosform.getCommConfigDegreeId().trim().length() > 0){
				sql += " and cu.commConfigDegreeId = '" + hosform.getCommConfigDegreeId().trim() + "'";
			}
			if(hosform.getCommConfigMaritalStatusId() != null && hosform.getCommConfigMaritalStatusId().trim().length() > 0){
				sql += " and cu.commConfigMaritalStatusId = '" + hosform.getCommConfigMaritalStatusId().trim() + "'";
			}
			if(hosform.getCommConfigLocationId1() != null && hosform.getCommConfigLocationId1().trim().length() > 0){
				sql += " and cu.commConfigLocationId1 = '" + hosform.getCommConfigLocationId1().trim() + "'";
			}
			if(hosform.getCommConfigLocationId2() != null && hosform.getCommConfigLocationId2().trim().length() > 0){
				sql += " and cu.commConfigLocationId2 = '" + hosform.getCommConfigLocationId2().trim() + "'";
			}
			if(hosform.getCommConfigLocationId3() != null && hosform.getCommConfigLocationId3().trim().length() > 0){
				sql += " and cu.commConfigLocationId3 = '" + hosform.getCommConfigLocationId3().trim() + "'";
			}
			if(hosform.getCommConfigLocationTownId()!=null&&hosform.getCommConfigLocationTownId().trim().length()>0){
				sql += "and cu.commConfigLocationTownId ='" +hosform.getCommConfigLocationTownId().trim() +"'";
			}
			if(hosform.getCommConfigLocationVillageId()!=null&&hosform.getCommConfigLocationVillageId().trim().length()>0){
				sql += "and cu.commConfigLocationVillageid ='" +hosform.getCommConfigLocationVillageId().trim() +"'";
			}
			if(hosform.getCommConfigLocationGroupId()!=null&&hosform.getCommConfigLocationGroupId().trim().length()>0){
				sql += "and cu.commConfigLocationGroupId ='" +hosform.getCommConfigLocationGroupId().trim() +"'";
			}
			if(hosform.getMailingAddress() != null && hosform.getMailingAddress().trim().length() > 0){
				sql += " and cu.mailingAddress like '%" + hosform.getMailingAddress().trim() + "%'";
			}
			if(hosform.getZipcode() != null && hosform.getZipcode().trim().length() > 0){
				sql += " and cu.zipcode = '" + hosform.getZipcode().trim() + "'";
			}
			if(hosform.getPhone() != null && hosform.getPhone().trim().length() > 0){
				sql += " and cu.phone = '" + hosform.getPhone().trim() + "'";
			}
			if(hosform.getEMail() != null && hosform.getEMail().trim().length() > 0){
				sql += " and cu.EMail = '" + hosform.getEMail().trim() + "'";
			}
			if(hosform.getMobileTel() != null && hosform.getMobileTel().trim().length() > 0){
				sql += " and cu.mobileTel = '" + hosform.getMobileTel().trim() + "'";
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
	
	/**获得满足条件的用户个数**/
	
	public int getcountbymore(SecurityUserBaseinfoForm securityUserBaseinfoForm,List<String[]> many) {
		// TODO Auto-generated method stub
		try{
			int count = 0;
			String sql = "SELECT COUNT(*) FROM SecurityUserBaseinfo a ";
			if((securityUserBaseinfoForm.getCardType()!=null&&securityUserBaseinfoForm.getCardType().trim().length()>0)||
					(securityUserBaseinfoForm.getCardNo()!=null&&securityUserBaseinfoForm.getCardNo().trim().length()>0)){
				sql += " ,SecurityUserVsCard b where a.id=b.securityUserBaseinfoId and b.cardStatus<>2";
			}else{
				sql += " where 1=1 ";
			}
			if(securityUserBaseinfoForm.getPmi() != null && securityUserBaseinfoForm.getPmi().trim().length() > 0){
				sql += " and a.pmi = ' "+securityUserBaseinfoForm.getPmi().trim()+"'";
			}
			if(securityUserBaseinfoForm.getCardType()!=null&&securityUserBaseinfoForm.getCardType().trim().length()>0){
				sql += " and b.commConfigCardtypeId = '"+securityUserBaseinfoForm.getCardType().trim()+"' ";
			}
			if(securityUserBaseinfoForm.getCardNo()!=null&&securityUserBaseinfoForm.getCardNo().trim().length()>0){
				sql += " and b.cardNo like '%"+securityUserBaseinfoForm.getCardNo().trim()+"%' ";
			}
			if(securityUserBaseinfoForm.getName() != null && securityUserBaseinfoForm.getName().trim().length() > 0){
				sql += " and a.name like '%"+securityUserBaseinfoForm.getName().trim()+"%'";
			}
			if(securityUserBaseinfoForm.getNameEn() != null && securityUserBaseinfoForm.getNameEn().trim().length() > 0){
				sql += " and a.nameEn like '%" + securityUserBaseinfoForm.getNameEn().trim() + "%'";
			}
			if(securityUserBaseinfoForm.getCommConfigSexId() != null && securityUserBaseinfoForm.getCommConfigSexId().trim().length() > 0){
				sql += " and a.commConfigSexId = '" + securityUserBaseinfoForm.getCommConfigSexId().trim() + "'";
			}
			if(securityUserBaseinfoForm.getBirthPlace() != null && securityUserBaseinfoForm.getBirthPlace().trim().length() > 0){
				sql += " and a.birthPlace like '%" + securityUserBaseinfoForm.getBirthPlace().trim() + "%'";
			}
			if(securityUserBaseinfoForm.getBirth_date_year() != null && securityUserBaseinfoForm.getBirth_date_year().trim().length() > 0){
				String date=securityUserBaseinfoForm.getBirth_date_year().trim()+"-"+
				securityUserBaseinfoForm.getBirth_date_month().trim()+"-"+
				securityUserBaseinfoForm.getBirth_date_day().trim();
				sql += " and a.dateOfBirth = to_date('" + date+"','yyyy/MM/dd')";
			}
			if(securityUserBaseinfoForm.getCommConfigCountryId() != null && securityUserBaseinfoForm.getCommConfigCountryId().trim().length() > 0){
				sql += " and a.commConfigCountryId = '" + securityUserBaseinfoForm.getCommConfigCountryId().trim() + "'";
			}
			if(securityUserBaseinfoForm.getCommConfigNationalityId() != null && securityUserBaseinfoForm.getCommConfigNationalityId().trim().length() > 0){
				sql += " and a.commConfigNationalityId = '" + securityUserBaseinfoForm.getCommConfigNationalityId().trim() + "'";
			}
			if(securityUserBaseinfoForm.getCommConfigIdTypeId() != null && securityUserBaseinfoForm.getCommConfigIdTypeId().trim().length() > 0){
				sql += " and a.commConfigIdTypeId = '" + securityUserBaseinfoForm.getCommConfigIdTypeId().trim() + "'";
			}
			if(securityUserBaseinfoForm.getIdNo() != null && securityUserBaseinfoForm.getIdNo().trim().length() > 0){
				sql += " and a.idNo = '" + securityUserBaseinfoForm.getIdNo().trim() + "'";
			}
			if(securityUserBaseinfoForm.getSscid() != null && securityUserBaseinfoForm.getSscid().trim().length() > 0){
				sql += " and a.sscid = '" + securityUserBaseinfoForm.getSscid().trim() + "'";
			}
			if(securityUserBaseinfoForm.getCommConfigDegreeId() != null && securityUserBaseinfoForm.getCommConfigDegreeId().trim().length() > 0){
				sql += " and a.commConfigDegreeId = '" + securityUserBaseinfoForm.getCommConfigDegreeId().trim() + "'";
			}
			if(securityUserBaseinfoForm.getCommConfigMaritalStatusId() != null && securityUserBaseinfoForm.getCommConfigMaritalStatusId().trim().length() > 0){
				sql += " and a.commConfigMaritalStatusId = '" + securityUserBaseinfoForm.getCommConfigMaritalStatusId().trim() + "'";
			}
			if(securityUserBaseinfoForm.getCommConfigLocationId1() != null && securityUserBaseinfoForm.getCommConfigLocationId1().trim().length() > 0){
				sql += " and a.commConfigLocationId1 = '" + securityUserBaseinfoForm.getCommConfigLocationId1().trim() + "'";
			}
			if(securityUserBaseinfoForm.getCommConfigLocationId2() != null && securityUserBaseinfoForm.getCommConfigLocationId2().trim().length() > 0){
				sql += " and a.commConfigLocationId2 = '" + securityUserBaseinfoForm.getCommConfigLocationId2().trim() + "'";
			}
			if(securityUserBaseinfoForm.getCommConfigLocationId3() != null && securityUserBaseinfoForm.getCommConfigLocationId3().trim().length() > 0){
				sql += " and a.commConfigLocationId3 = '" + securityUserBaseinfoForm.getCommConfigLocationId3().trim() + "'";
			}
			if(securityUserBaseinfoForm.getCommConfigLocationTownId()!=null&&securityUserBaseinfoForm.getCommConfigLocationTownId().trim().length()>0){
				sql += " and a.commConfigLocationTownId ='" +securityUserBaseinfoForm.getCommConfigLocationTownId().trim() +"'";
			}
			if(securityUserBaseinfoForm.getCommConfigLocationVillageId()!=null&&securityUserBaseinfoForm.getCommConfigLocationVillageId().trim().length()>0){
				sql += " and a.commConfigLocationVillageid ='" +securityUserBaseinfoForm.getCommConfigLocationVillageId().trim() +"'";
			}
			if(securityUserBaseinfoForm.getCommConfigLocationGroupId()!=null&&securityUserBaseinfoForm.getCommConfigLocationGroupId().trim().length()>0){
				sql += " and a.commConfigLocationGroupId ='" +securityUserBaseinfoForm.getCommConfigLocationGroupId().trim() +"'";
			}
			if(securityUserBaseinfoForm.getMailingAddress() != null && securityUserBaseinfoForm.getMailingAddress().trim().length() > 0){
				sql += " and a.mailingAddress like '%" + securityUserBaseinfoForm.getMailingAddress().trim() + "%'";
			}
			if(securityUserBaseinfoForm.getZipcode() != null && securityUserBaseinfoForm.getZipcode().trim().length() > 0){
				sql += " and a.zipcode = '" + securityUserBaseinfoForm.getZipcode().trim() + "'";
			}
			if(securityUserBaseinfoForm.getPhone() != null && securityUserBaseinfoForm.getPhone().trim().length() > 0){
				sql += " and a.phone = '" + securityUserBaseinfoForm.getPhone().trim() + "'";
			}
			if(securityUserBaseinfoForm.getEMail() != null && securityUserBaseinfoForm.getEMail().trim().length() > 0){
				sql += " and a.EMail = '" + securityUserBaseinfoForm.getEMail().trim() + "'";
			}
			if(securityUserBaseinfoForm.getMobileTel() != null && securityUserBaseinfoForm.getMobileTel().trim().length() > 0){
				sql += " and a.mobileTel = '" + securityUserBaseinfoForm.getMobileTel().trim() + "'";
			}
			if(securityUserBaseinfoForm.getInputCode() != null && securityUserBaseinfoForm.getInputCode().trim().length() > 0){
				sql += " and a.inputCode like '%" + securityUserBaseinfoForm.getInputCode().trim().toUpperCase() + "%'";
			}
			if(many!=null&&many.size()>0){
				sql += " and (";
				for(int i=0;i<many.size();i++){
					String[] s=many.get(i);
					if(i==0){
						sql += s[0].trim()+"='"+s[1].trim()+"'";
					}else{
						sql += " or "+s[0].trim()+"='"+s[1].trim()+"'";
					}
				}
				sql += ")";
			}
			List<?> li = this.getHibernateTemplate().find(sql);
			if(li != null && li.size() > 0){
				count = Integer.valueOf(String.valueOf(li.get(0))).intValue();
			}
			log.debug("DAO public int getcountbymore OK");
			return count;
		}catch(Exception e){
			log.error("DAO public int getcountbymore error", e);
			e.printStackTrace();
			return new Integer("0");
		}
	}
	/**获得满足条件的用户**/
	
	public List<?> getUsers(SecurityUserBaseinfoForm securityUserBaseinfoForm,
			int count, int pageSize,List<String[]> many) {
		// TODO Auto-generated method stub
		try{
			String sql = " select a.id,"+
			" a.pmi, "+
			" a.name, "+
			" a.nameEn, "+
			" a.inputCode, "+
			" a.commConfigSexId, "+
			" a.birthPlace, "+
			" a.dateOfBirth, "+
			" a.commConfigCountryId, "+
			" a.commConfigNationalityId, "+
			" a.commConfigIdTypeId, "+
			" a.idNo, "+
			" a.sscid, "+
			" a.commConfigAboId, "+
			" a.commConfigRhId, "+
			" a.commConfigDegreeId, "+
			" a.commConfigMaritalStatusId, "+
			" a.commConfigLocationId1, "+
			" a.commConfigLocationId2, "+
			" a.commConfigLocationId3, "+
			" a.commConfigLocationTownId, "+
			" a.commConfigLocationVillageid, "+
			" a.mailingAddress, "+
			" a.zipcode,"+ 
			" a.phone,"+ 
			" a.EMail,"+
			" a.photoPath,"+
			" a.commConfigRelationshipId,"+
			" a.contactPersonName,"+
			" a.contactPersonPhone,"+
			" a.comments "
			;
			if((securityUserBaseinfoForm.getCardType()!=null&&securityUserBaseinfoForm.getCardType().trim().length()>0)||
					(securityUserBaseinfoForm.getCardNo()!=null&&securityUserBaseinfoForm.getCardNo().trim().length()>0)){
				sql += " from SecurityUserBaseinfo a,SecurityUserVsCard b where a.id=b.securityUserBaseinfoId and b.cardStatus<>2";
			}else{
	    		sql += " from SecurityUserBaseinfo a where 1=1 ";
			}
			if(securityUserBaseinfoForm.getPmi() != null && securityUserBaseinfoForm.getPmi().trim().length() > 0){
				sql += " and a.pmi = ' "+securityUserBaseinfoForm.getPmi().trim()+"'";
			}
			if(securityUserBaseinfoForm.getCardType()!=null&&securityUserBaseinfoForm.getCardType().trim().length()>0){
				sql += " and b.commConfigCardtypeId = '"+securityUserBaseinfoForm.getCardType().trim()+"' ";
			}
			if(securityUserBaseinfoForm.getCardNo()!=null&&securityUserBaseinfoForm.getCardNo().trim().length()>0){
				sql += " and b.cardNo like '%"+securityUserBaseinfoForm.getCardNo().trim()+"%' ";
			}
			if(securityUserBaseinfoForm.getName() != null && securityUserBaseinfoForm.getName().trim().length() > 0){
				sql += " and a.name like '%"+securityUserBaseinfoForm.getName().trim()+"%'";
			}
			if(securityUserBaseinfoForm.getNameEn() != null && securityUserBaseinfoForm.getNameEn().trim().length() > 0){
				sql += " and a.nameEn like '%" + securityUserBaseinfoForm.getNameEn().trim() + "%'";
			}
			if(securityUserBaseinfoForm.getCommConfigSexId() != null && securityUserBaseinfoForm.getCommConfigSexId().trim().length() > 0){
				sql += " and a.commConfigSexId = '" + securityUserBaseinfoForm.getCommConfigSexId().trim() + "'";
			}
			if(securityUserBaseinfoForm.getBirthPlace() != null && securityUserBaseinfoForm.getBirthPlace().trim().length() > 0){
				sql += " and a.birthPlace like '%" + securityUserBaseinfoForm.getBirthPlace().trim() + "%'";
			}
			if(securityUserBaseinfoForm.getBirth_date_year() != null && securityUserBaseinfoForm.getBirth_date_year().trim().length() > 0){
				String date=securityUserBaseinfoForm.getBirth_date_year().trim()+"-"+
				securityUserBaseinfoForm.getBirth_date_month().trim()+"-"+
				securityUserBaseinfoForm.getBirth_date_day().trim();
				//sql += "and a.dateOfBirth = to_date('" + date+"','yyyy/MM/dd')";
				sql += " and to_char(trunc(a.dateOfBirth),'yyyy-MM-dd') = '"+date+"'";
			}
			if(securityUserBaseinfoForm.getCommConfigCountryId() != null && securityUserBaseinfoForm.getCommConfigCountryId().trim().length() > 0){
				sql += " and a.commConfigCountryId = '" + securityUserBaseinfoForm.getCommConfigCountryId().trim() + "'";
			}
			if(securityUserBaseinfoForm.getCommConfigNationalityId() != null && securityUserBaseinfoForm.getCommConfigNationalityId().trim().length() > 0){
				sql += " and a.commConfigNationalityId = '" + securityUserBaseinfoForm.getCommConfigNationalityId().trim() + "'";
			}
			if(securityUserBaseinfoForm.getCommConfigIdTypeId() != null && securityUserBaseinfoForm.getCommConfigIdTypeId().trim().length() > 0){
				sql += " and a.commConfigIdTypeId = '" + securityUserBaseinfoForm.getCommConfigIdTypeId().trim() + "'";
			}
			if(securityUserBaseinfoForm.getIdNo() != null && securityUserBaseinfoForm.getIdNo().trim().length() > 0){
				sql += " and a.idNo = '" + securityUserBaseinfoForm.getIdNo().trim() + "'";
			}
			if(securityUserBaseinfoForm.getSscid() != null && securityUserBaseinfoForm.getSscid().trim().length() > 0){
				sql += " and a.sscid = '" + securityUserBaseinfoForm.getSscid().trim() + "'";
			}
			if(securityUserBaseinfoForm.getCommConfigDegreeId() != null && securityUserBaseinfoForm.getCommConfigDegreeId().trim().length() > 0){
				sql += " and a.commConfigDegreeId = '" + securityUserBaseinfoForm.getCommConfigDegreeId().trim() + "'";
			}
			if(securityUserBaseinfoForm.getCommConfigMaritalStatusId() != null && securityUserBaseinfoForm.getCommConfigMaritalStatusId().trim().length() > 0){
				sql += " and a.commConfigMaritalStatusId = '" + securityUserBaseinfoForm.getCommConfigMaritalStatusId().trim() + "'";
			}
			if(securityUserBaseinfoForm.getCommConfigLocationId1() != null && securityUserBaseinfoForm.getCommConfigLocationId1().trim().length() > 0){
				sql += " and a.commConfigLocationId1 = '" + securityUserBaseinfoForm.getCommConfigLocationId1().trim() + "'";
			}
			if(securityUserBaseinfoForm.getCommConfigLocationId2() != null && securityUserBaseinfoForm.getCommConfigLocationId2().trim().length() > 0){
				sql += " and a.commConfigLocationId2 = '" + securityUserBaseinfoForm.getCommConfigLocationId2().trim() + "'";
			}
			if(securityUserBaseinfoForm.getCommConfigLocationId3() != null && securityUserBaseinfoForm.getCommConfigLocationId3().trim().length() > 0){
				sql += " and a.commConfigLocationId3 = '" + securityUserBaseinfoForm.getCommConfigLocationId3().trim() + "'";
			}
			if(securityUserBaseinfoForm.getCommConfigLocationTownId()!=null&&securityUserBaseinfoForm.getCommConfigLocationTownId().trim().length()>0){
				sql += " and a.commConfigLocationTownId ='" +securityUserBaseinfoForm.getCommConfigLocationTownId().trim() +"'";
			}
			if(securityUserBaseinfoForm.getCommConfigLocationVillageId()!=null&&securityUserBaseinfoForm.getCommConfigLocationVillageId().trim().length()>0){
				sql += " and a.commConfigLocationVillageid ='" +securityUserBaseinfoForm.getCommConfigLocationVillageId().trim() +"'";
			}
			if(securityUserBaseinfoForm.getCommConfigLocationGroupId()!=null&&securityUserBaseinfoForm.getCommConfigLocationGroupId().trim().length()>0){
				sql += " and a.commConfigLocationGroupId ='" +securityUserBaseinfoForm.getCommConfigLocationGroupId().trim() +"'";
			}
			if(securityUserBaseinfoForm.getMailingAddress() != null && securityUserBaseinfoForm.getMailingAddress().trim().length() > 0){
				sql += " and a.mailingAddress like '%" + securityUserBaseinfoForm.getMailingAddress().trim() + "%'";
			}
			if(securityUserBaseinfoForm.getZipcode() != null && securityUserBaseinfoForm.getZipcode().trim().length() > 0){
				sql += " and a.zipcode = '" + securityUserBaseinfoForm.getZipcode().trim() + "'";
			}
			if(securityUserBaseinfoForm.getPhone() != null && securityUserBaseinfoForm.getPhone().trim().length() > 0){
				sql += " and a.phone = '" + securityUserBaseinfoForm.getPhone().trim() + "'";
			}
			if(securityUserBaseinfoForm.getEMail() != null && securityUserBaseinfoForm.getEMail().trim().length() > 0){
				sql += " and a.EMail = '" + securityUserBaseinfoForm.getEMail().trim() + "'";
			}
			if(securityUserBaseinfoForm.getMobileTel() != null && securityUserBaseinfoForm.getMobileTel().trim().length() > 0){
				sql += " and a.mobileTel = '" + securityUserBaseinfoForm.getMobileTel().trim() + "'";
			}
			if(securityUserBaseinfoForm.getInputCode() != null && securityUserBaseinfoForm.getInputCode().trim().length() > 0){
				sql += " and a.inputCode like '%" + securityUserBaseinfoForm.getInputCode().trim().toUpperCase() + "%'";
			}
			if(many!=null&&many.size()>0){
				sql += " and (";
				for(int i=0;i<many.size();i++){
					String[] s=many.get(i);
					if(i==0){
						sql += s[0].trim()+"='"+s[1].trim()+"'";
					}else{
						sql += " or "+s[0].trim()+"='"+s[1].trim()+"'";
					}
				}
				sql += ")";
			}
			if (securityUserBaseinfoForm.getOrderNo().trim().length() > 0) {
				sql += " order by " + securityUserBaseinfoForm.getOrderNo().trim();
			}
			System.out.println(sql);
			Query query = this.getSession().createQuery(sql);
			query.setFirstResult(count);
			query.setMaxResults(pageSize);
			List<?> li = query.list();
			log.debug("DAO public int getusers OK");
			return li;
		}catch(Exception e){
			log.error("DAO public int getusers error", e);
			e.printStackTrace();
			return null;
		}
	}
	
	/**获得满足条件的用户**/
	
	public List<?> getUsers(SecurityUserBaseinfoForm securityUserBaseinfoForm) {
		// TODO Auto-generated method stub
		try{
			String sql = " select a.id,"+
			" a.pmi, "+
			" a.name, "+
			" a.nameEn, "+
			" a.inputCode, "+
			" a.commConfigSexId, "+
			" a.birthPlace, "+
			" a.dateOfBirth, "+
			" a.commConfigCountryId, "+
			" a.commConfigNationalityId, "+
			" a.commConfigIdTypeId, "+
			" a.idNo, "+
			" a.sscid, "+
			" a.commConfigAboId, "+
			" a.commConfigRhId, "+
			" a.commConfigDegreeId, "+
			" a.commConfigMaritalStatusId, "+
			" a.commConfigLocationId1, "+
			" a.commConfigLocationId2, "+
			" a.commConfigLocationId3, "+
			" a.commConfigLocationTownId, "+
			" a.commConfigLocationVillageid, "+
			" a.mailingAddress, "+
			" a.zipcode,"+ 
			" a.phone,"+ 
			" a.EMail,"+
			" a.photoPath,"+
			" a.commConfigRelationshipId,"+
			" a.contactPersonName,"+
			" a.contactPersonPhone,"+
			" a.comments ";

			sql += " from SecurityUserBaseinfo a where 1=1 ";

			if(securityUserBaseinfoForm.getPmi() != null && securityUserBaseinfoForm.getPmi().trim().length() > 0){
				sql += " and a.pmi = ' "+securityUserBaseinfoForm.getPmi().trim()+"'";
			}
			if(securityUserBaseinfoForm.getName() != null && securityUserBaseinfoForm.getName().trim().length() > 0){
				sql += " and a.name like '%"+securityUserBaseinfoForm.getName().trim()+"%'";
			}
			if(securityUserBaseinfoForm.getNameEn() != null && securityUserBaseinfoForm.getNameEn().trim().length() > 0){
				sql += " and a.nameEn like '%" + securityUserBaseinfoForm.getNameEn().trim() + "%'";
			}
			if(securityUserBaseinfoForm.getCommConfigSexId() != null && securityUserBaseinfoForm.getCommConfigSexId().trim().length() > 0){
				sql += " and a.commConfigSexId = '" + securityUserBaseinfoForm.getCommConfigSexId().trim() + "'";
			}
			if(securityUserBaseinfoForm.getBirthPlace() != null && securityUserBaseinfoForm.getBirthPlace().trim().length() > 0){
				sql += " and a.birthPlace like '%" + securityUserBaseinfoForm.getBirthPlace().trim() + "%'";
			}
			if(securityUserBaseinfoForm.getBirth_date_year() != null && securityUserBaseinfoForm.getBirth_date_year().trim().length() > 0){
				String date=securityUserBaseinfoForm.getBirth_date_year().trim()+"-"+
				securityUserBaseinfoForm.getBirth_date_month().trim()+"-"+
				securityUserBaseinfoForm.getBirth_date_day().trim();
				//sql += "and a.dateOfBirth = to_date('" + date+"','yyyy/MM/dd')";
				sql += " and to_char(trunc(a.dateOfBirth),'yyyy-MM-dd') = '"+date+"'";
			}
			if(securityUserBaseinfoForm.getCommConfigCountryId() != null && securityUserBaseinfoForm.getCommConfigCountryId().trim().length() > 0){
				sql += " and a.commConfigCountryId = '" + securityUserBaseinfoForm.getCommConfigCountryId().trim() + "'";
			}
			if(securityUserBaseinfoForm.getCommConfigNationalityId() != null && securityUserBaseinfoForm.getCommConfigNationalityId().trim().length() > 0){
				sql += " and a.commConfigNationalityId = '" + securityUserBaseinfoForm.getCommConfigNationalityId().trim() + "'";
			}
			if(securityUserBaseinfoForm.getCommConfigIdTypeId() != null && securityUserBaseinfoForm.getCommConfigIdTypeId().trim().length() > 0){
				sql += " and a.commConfigIdTypeId = '" + securityUserBaseinfoForm.getCommConfigIdTypeId().trim() + "'";
			}
			if(securityUserBaseinfoForm.getIdNo() != null && securityUserBaseinfoForm.getIdNo().trim().length() > 0){
				sql += " and a.idNo = '" + securityUserBaseinfoForm.getIdNo().trim() + "'";
			}
			if(securityUserBaseinfoForm.getSscid() != null && securityUserBaseinfoForm.getSscid().trim().length() > 0){
				sql += " and a.sscid = '" + securityUserBaseinfoForm.getSscid().trim() + "'";
			}
			if(securityUserBaseinfoForm.getCommConfigDegreeId() != null && securityUserBaseinfoForm.getCommConfigDegreeId().trim().length() > 0){
				sql += " and a.commConfigDegreeId = '" + securityUserBaseinfoForm.getCommConfigDegreeId().trim() + "'";
			}
			if(securityUserBaseinfoForm.getCommConfigMaritalStatusId() != null && securityUserBaseinfoForm.getCommConfigMaritalStatusId().trim().length() > 0){
				sql += " and a.commConfigMaritalStatusId = '" + securityUserBaseinfoForm.getCommConfigMaritalStatusId().trim() + "'";
			}
			if(securityUserBaseinfoForm.getCommConfigLocationId1() != null && securityUserBaseinfoForm.getCommConfigLocationId1().trim().length() > 0){
				sql += " and a.commConfigLocationId1 = '" + securityUserBaseinfoForm.getCommConfigLocationId1().trim() + "'";
			}
			if(securityUserBaseinfoForm.getCommConfigLocationId2() != null && securityUserBaseinfoForm.getCommConfigLocationId2().trim().length() > 0){
				sql += " and a.commConfigLocationId2 = '" + securityUserBaseinfoForm.getCommConfigLocationId2().trim() + "'";
			}
			if(securityUserBaseinfoForm.getCommConfigLocationId3() != null && securityUserBaseinfoForm.getCommConfigLocationId3().trim().length() > 0){
				sql += " and a.commConfigLocationId3 = '" + securityUserBaseinfoForm.getCommConfigLocationId3().trim() + "'";
			}
			if(securityUserBaseinfoForm.getCommConfigLocationTownId()!=null&&securityUserBaseinfoForm.getCommConfigLocationTownId().trim().length()>0){
				sql += "and a.commConfigLocationTownId ='" +securityUserBaseinfoForm.getCommConfigLocationTownId().trim() +"'";
			}
			if(securityUserBaseinfoForm.getCommConfigLocationVillageId()!=null&&securityUserBaseinfoForm.getCommConfigLocationVillageId().trim().length()>0){
				sql += "and a.commConfigLocationVillageid ='" +securityUserBaseinfoForm.getCommConfigLocationVillageId().trim() +"'";
			}
			if(securityUserBaseinfoForm.getMailingAddress() != null && securityUserBaseinfoForm.getMailingAddress().trim().length() > 0){
				sql += " and a.mailingAddress like '%" + securityUserBaseinfoForm.getMailingAddress().trim() + "%'";
			}
			if(securityUserBaseinfoForm.getZipcode() != null && securityUserBaseinfoForm.getZipcode().trim().length() > 0){
				sql += " and a.zipcode = '" + securityUserBaseinfoForm.getZipcode().trim() + "'";
			}
			if(securityUserBaseinfoForm.getPhone() != null && securityUserBaseinfoForm.getPhone().trim().length() > 0){
				sql += " and a.phone = '" + securityUserBaseinfoForm.getPhone().trim() + "'";
			}
			if(securityUserBaseinfoForm.getEMail() != null && securityUserBaseinfoForm.getEMail().trim().length() > 0){
				sql += " and a.EMail = '" + securityUserBaseinfoForm.getEMail().trim() + "'";
			}
			if(securityUserBaseinfoForm.getMobileTel() != null && securityUserBaseinfoForm.getMobileTel().trim().length() > 0){
				sql += " and a.mobileTel = '" + securityUserBaseinfoForm.getMobileTel().trim() + "'";
			}
			if(securityUserBaseinfoForm.getInputCode() != null && securityUserBaseinfoForm.getInputCode().trim().length() > 0){
				sql += " and a.inputCode like '%" + securityUserBaseinfoForm.getInputCode().trim().toUpperCase() + "%'";
			}
			if (securityUserBaseinfoForm.getOrderNo().trim().length() > 0) {
				sql += " order by " + securityUserBaseinfoForm.getOrderNo().trim();
			}
			System.out.println(sql);
			Query query = this.getSession().createQuery(sql);
			List<?> li = query.list();
			log.debug("DAO public int getusers OK");
			return li;
		}catch(Exception e){
			log.error("DAO public int getusers error", e);
			e.printStackTrace();
			return null;
		}
	}
	/**通过id获得SecurityDataObjectVsRoles*/
	
	public SecurityDataObjectVsRoles findById(String id) {
		// TODO Auto-generated method stub

		SecurityDataObjectVsRoles se=null;
		String sql="select a from SecurityDataObjectVsRoles a where a.securityStaffBaseinfoId='"+id+"'";
			
		List<?> list= this.getHibernateTemplate().find(sql);
		if(list!=null&&list.size()>0){
			se=(SecurityDataObjectVsRoles) list.get(0);
		}
		return se;
	}
	/**通过itemCode获得地理位置*/
	
	public List<CommConfigLocation> findByItemCode(String itemCode) {
		// TODO Auto-generated method stub
		CommConfigLocation com=null;
		String sql="select a from CommConfigLocation a where a.itemCode= '"+itemCode+"'";
		List<CommConfigLocation> list=this.getHibernateTemplate().find(sql);
		
		return list;
	}
	/**通过id获得SecurityDataObjectType*/
	
	public SecurityDataObjectType getSecurityDataObjectTypeById(String id){
		SecurityDataObjectType sec=null;
		String sql="select a from SecurityDataObjectType a where a.id='"+id+"'";
		
		List<?> list=this.getHibernateTemplate().find(sql);
		
		if(list!=null&&list.size()>0){
			sec=(SecurityDataObjectType) list.get(0);
		}
		return sec;
	}
	/**获得满足条件的医疗机构**/
	
	public List<HspConfigBaseinfo> findHosiptal(String columnName, String id) {
		// TODO Auto-generated method stub
		
		String sql="select a from HspConfigBaseinfo a where a."+columnName+"='"+id+"'";
		List<HspConfigBaseinfo> list=this.getHibernateTemplate().find(sql);
		return list;
	}
	/**获得满足条件的操作人员**/
	
	public List<SecurityStaffBaseinfo> findSecurityStaffBaseinfo(List<HspConfigBaseinfo> list) {
		// TODO Auto-generated method stub
		
		String sql="select  a from SecurityStaffBaseinfo a where 1=1 ";
		if(list!=null&&list.size()>0){
			sql+=" and a.hspConfigBaseinfoId in (";
			for(int i=0;i<list.size();i++){
				sql+="'"+list.get(i).getId().trim()+"',";
			}
			System.out.println(sql);
			sql=sql.substring(0,sql.length()-1);
			sql+=")";
		}
		
		List<SecurityStaffBaseinfo> li=this.getHibernateTemplate().find(sql);
		return li;
	}
	/**通过id获得操作人员*/
	
	public SecurityStaffBaseinfo findSecurityStaffBaseinfoById(String id) {
		// TODO Auto-generated method stub
		try {
			SecurityStaffBaseinfo sec=null;
			String sql="select a from SecurityStaffBaseinfo a where a.id='"+id+"'";
			List<?> list=this.getHibernateTemplate().find(sql);
			if(list!=null&&list.size()>0){
				sec=(SecurityStaffBaseinfo) list.get(0);
			}
			return sec;
		}
		catch (Exception re) {
			log.error("findSecurityStaffBaseinfoById error!", re);
			re.printStackTrace();
			return null;
		}
	}
	/**获取客户记录总数*/
	
	public int getCountUser(String id, String pmi, String name, String nameEn,
			String inputCode, String commConfigIdTypeId,List<HspConfigBaseinfo> list) {
		// TODO Auto-generated method stub
		try {
			int count = 0;
			String sql = "select count(*) ";
			sql += " from SecurityUserBaseinfo a where 1=1  and nvl(a.recordFlag,0)=0 and a.id not in (select c.securityUserBaseinfoId from CardSecurityBaseinfo c) ";
			if (id.trim().length() > 0) {
				sql += " and a.id = '" + id.trim() + "' ";
			}
			if (pmi.trim().length() > 0) {
				sql += " and a.pmi like '%" + pmi.trim() + "%' ";
			}
			if (name.trim().length() > 0) {
				sql += " and a.name like '%" + name.trim() + "%' ";
			}
			if (nameEn.trim().length() > 0) {
				sql += " and a.nameEn = '" + nameEn.trim() + "' ";
			}
			if (inputCode.trim().length() > 0) {
				sql += " and a.inputCode like '" + inputCode.trim().toUpperCase() + "%' ";
			}
			if (commConfigIdTypeId!=null && commConfigIdTypeId.trim().length() > 0) {
				sql += " and a.commConfigIdTypeId = '" + commConfigIdTypeId.trim() + "' ";
			}
//	    	if(list!=null&&list.size()>0){
//	    		sql+=" and b.hspConfigBaseinfoId in(";
//	    		for(int i=0;i<list.size();i++){
//	    			sql+="'"+list.get(i).getId().trim()+"',";
//	    		}
//	    		sql=sql.substring(0,sql.length()-1);
//	    		sql+=")";
//	    	}
			List<?> li = getHibernateTemplate().find(sql);
			if (li != null && li.size() > 0) {
				count = Integer.valueOf(String.valueOf(li.get(0))).intValue();
			}
			return count;
		}
		catch (Exception re) {
			log.error("getCountUser error!", re);
			re.printStackTrace();
			return 0;
		}
	}
	/**获取当前页面客户记录列表*/
	
	public List<?> getUser(String id, String pmi, String name, String nameEn,
			String inputCode, String commConfigIdTypeId,String orderNo, int curCount, int quChuCount,
			List<HspConfigBaseinfo> list) {
		// TODO Auto-generated method stub
		try {
			String sql = " select a.id,"+
			" a.pmi, "+
			" a.name, "+
			" a.nameEn, "+
			" a.inputCode, "+
			" a.commConfigSexId, "+
			" a.birthPlace, "+
			" a.dateOfBirth, "+
			" a.commConfigCountryId, "+
			" a.commConfigNationalityId, "+
			" a.commConfigIdTypeId, "+
			" a.idNo, "+
			" a.sscid, "+
			" a.commConfigAboId, "+
			" a.commConfigRhId, "+
			" a.commConfigDegreeId, "+
			" a.commConfigMaritalStatusId, "+
			" a.commConfigLocationId1, "+
			" a.commConfigLocationId2, "+
			" a.commConfigLocationId3, "+
			" a.commConfigLocationTownId, "+
			" a.commConfigLocationVillageid, "+
			" a.mailingAddress, "+
			" a.zipcode,"+ 
			" a.phone,"+ 
			" a.EMail,"+
			" a.photoPath,"+
			" a.commConfigRelationshipId,"+
			" a.contactPersonName,"+
			" a.contactPersonPhone,"+
			" a.comments ";
	    	sql += " from SecurityUserBaseinfo a where 1=1  and nvl(a.recordFlag,0)=0 and a.id not in (select c.securityUserBaseinfoId from CardSecurityBaseinfo c)";
	       
	    	if(id.trim().length() > 0){
	    		sql += " and a.id = '" + id.trim() + "' ";
	        }
	    	if(pmi.trim().length() > 0){
	    		sql += " and a.pmi like '%" + pmi.trim() + "%' ";
	    	}
	    	if(name.trim().length() > 0){
	    		sql += " and a.name like '%" + name.trim() + "%' ";
	    	}
	    	if(nameEn.trim().length() > 0){
	    		sql += " and a.nameEn = '" + nameEn.trim() + "' ";
	    	}
	    	if(inputCode.trim().length() > 0){
	    		sql += " and a.inputCode like '" + inputCode.trim().toUpperCase() + "%' ";
	    	}
	    	if (commConfigIdTypeId!=null && commConfigIdTypeId.trim().length() > 0) {
				sql += " and a.commConfigIdTypeId = '" + commConfigIdTypeId.trim() + "' ";
			}
//	    	if(list!=null&&list.size()>0){
//	    		sql+=" and b.hspConfigBaseinfoId in(";
//	    		for(int i=0;i<list.size();i++){
//	    			sql+="'"+list.get(i).getId().trim()+"',";
//	    		}
//	    		sql=sql.substring(0,sql.length()-1);
//	    		sql+=")";
//	    	}
	    	if(orderNo.trim().length() > 0){
	    		sql += " order by " + orderNo;
	    	} 
	    	Query q = getSession().createQuery(sql);
			q.setFirstResult(curCount); 
			q.setMaxResults(quChuCount); 
			List<?> l=q.list();
		    
			return l;
		}
		catch (Exception re) {
			log.error("getUser error!", re);
			re.printStackTrace();
			return null;
		}
	}
	
	
	
	public List<?> getUserExcel(String id, String pmi, String name, String nameEn,
			String inputCode, String commConfigIdTypeId,String orderNo, List<HspConfigBaseinfo> list) {
		// TODO Auto-generated method stub
		try {
			
	    	String sql = "select a from SecurityUserBaseinfo a , CardSecurityBaseinfo b where 1=1  and nvl(a.recordFlag,0)=0 and a.id = b.securityUserBaseinfoId and b.stuFlag='0'";
	       
	    	if(id.trim().length() > 0){
	    		sql += " and a.id = '" + id.trim() + "' ";
	        }
	    	if(pmi.trim().length() > 0){
	    		sql += " and a.pmi like '%" + pmi.trim() + "%' ";
	    	}
	    	if(name.trim().length() > 0){
	    		sql += " and a.name like '%" + name.trim() + "%' ";
	    	}
	    	if(nameEn.trim().length() > 0){
	    		sql += " and a.nameEn = '" + nameEn.trim() + "' ";
	    	}
	    	if(inputCode.trim().length() > 0){
	    		sql += " and a.inputCode like '" + inputCode.trim().toUpperCase() + "%' ";
	    	}
	    	if (commConfigIdTypeId!=null && commConfigIdTypeId.trim().length() > 0) {
				sql += " and a.commConfigIdTypeId = '" + commConfigIdTypeId.trim() + "' ";
			}
	    	if(orderNo.trim().length() > 0){
	    		sql += " order by " + orderNo;
	    	} 
	    	Query q = getSession().createQuery(sql);
			List<?> l=q.list();
		    
			return l;
		}
		catch (Exception re) {
			log.error("getUser error!", re);
			re.printStackTrace();
			return null;
		}
	}
	
	
	
	
	
	@Override
	public List<?> getUserExcel2(String id, String pmi, String name,
			String nameEn, String inputCode, String commConfigIdTypeId,
			String orderNo, List<HspConfigBaseinfo> list) {
			try {
			
	    	String sql = "select a from SecurityUserBaseinfo a , CardSecurityBaseinfo b where 1=1  and nvl(a.recordFlag,0)=0 and a.id = b.securityUserBaseinfoId";
	       
	    	if(id.trim().length() > 0){
	    		sql += " and a.id = '" + id.trim() + "' ";
	        }
	    	if(pmi.trim().length() > 0){
	    		sql += " and a.pmi like '%" + pmi.trim() + "%' ";
	    	}
	    	if(name.trim().length() > 0){
	    		sql += " and a.name like '%" + name.trim() + "%' ";
	    	}
	    	if(nameEn.trim().length() > 0){
	    		sql += " and a.nameEn = '" + nameEn.trim() + "' ";
	    	}
	    	if(inputCode.trim().length() > 0){
	    		sql += " and a.inputCode like '" + inputCode.trim().toUpperCase() + "%' ";
	    	}
	    	if (commConfigIdTypeId!=null && commConfigIdTypeId.trim().length() > 0) {
				sql += " and a.commConfigIdTypeId = '" + commConfigIdTypeId.trim() + "' ";
			}
	    	if(orderNo.trim().length() > 0){
	    		sql += " order by " + orderNo;
	    	} 
	    	Query q = getSession().createQuery(sql);
			List<?> l=q.list();
		    
			return l;
		   }
		     catch (Exception re) {
		     	log.error("getUser error!", re);
			    re.printStackTrace();
			    return null;
		   }
	  }
	
	
	
	
	@Override
	public int getUserExcelCount(String id, String pmi, String name,
			String nameEn, String inputCode, String commConfigIdTypeId) {
		try {
			int count =0;
	    	String sql = "select count(a) from SecurityUserBaseinfo a , CardSecurityBaseinfo b where 1=1  and nvl(a.recordFlag,0)=0 and a.id = b.securityUserBaseinfoId and b.stuFlag='0'";
	       
	    	if(id.trim().length() > 0){
	    		sql += " and a.id = '" + id.trim() + "' ";
	        }
	    	if(pmi.trim().length() > 0){
	    		sql += " and a.pmi like '%" + pmi.trim() + "%' ";
	    	}
	    	if(name.trim().length() > 0){
	    		sql += " and a.name like '%" + name.trim() + "%' ";
	    	}
	    	if(nameEn.trim().length() > 0){
	    		sql += " and a.nameEn = '" + nameEn.trim() + "' ";
	    	}
	    	if(inputCode.trim().length() > 0){
	    		sql += " and a.inputCode like '" + inputCode.trim().toUpperCase() + "%' ";
	    	}
	    	if (commConfigIdTypeId!=null && commConfigIdTypeId.trim().length() > 0) {
				sql += " and a.commConfigIdTypeId = '" + commConfigIdTypeId.trim() + "' ";
			}
	    	Query q = getSession().createQuery(sql);
			List<?> l=q.list();
			if (l != null && l.size() > 0) {
				count = Integer.valueOf(String.valueOf(l.get(0))).intValue();
			}
			return count;
		}catch (Exception re) {
			log.error("getUserExcelCount error!", re);
			re.printStackTrace();
            return 0;
		}
	}
	
	
	
	public int getUserExcelCount2(String id, String pmi, String name,
			String nameEn, String inputCode, String commConfigIdTypeId) {
		try {
			int count =0;
	    	String sql = "select count(a) from SecurityUserBaseinfo a , CardSecurityBaseinfo b where 1=1  and nvl(a.recordFlag,0)=0 and a.id = b.securityUserBaseinfoId ";
	       
	    	if(id.trim().length() > 0){
	    		sql += " and a.id = '" + id.trim() + "' ";
	        }
	    	if(pmi.trim().length() > 0){
	    		sql += " and a.pmi like '%" + pmi.trim() + "%' ";
	    	}
	    	if(name.trim().length() > 0){
	    		sql += " and a.name like '%" + name.trim() + "%' ";
	    	}
	    	if(nameEn.trim().length() > 0){
	    		sql += " and a.nameEn = '" + nameEn.trim() + "' ";
	    	}
	    	if(inputCode.trim().length() > 0){
	    		sql += " and a.inputCode like '" + inputCode.trim().toUpperCase() + "%' ";
	    	}
	    	if (commConfigIdTypeId!=null && commConfigIdTypeId.trim().length() > 0) {
				sql += " and a.commConfigIdTypeId = '" + commConfigIdTypeId.trim() + "' ";
			}
	    	Query q = getSession().createQuery(sql);
			List<?> l=q.list();
			if (l != null && l.size() > 0) {
				count = Integer.valueOf(String.valueOf(l.get(0))).intValue();
			}
			return count;
		}catch (Exception re) {
			log.error("getUserExcelCount error!", re);
			re.printStackTrace();
            return 0;
		}
	}
	
	
	@Override
	public List<?> getUserExcelData(String id, String pmi, String name,
			String nameEn, String inputCode, String commConfigIdTypeId,
			String orderNo, int curCount, int quChuCount) {
			
		try {
			
	    	String sql = "select a.id," +
	    			"a.pmi," +
	    			"a.name," +
	    			"a.nameEn," +
	    			"a.dateOfBirth," +
	    			"a.commConfigSexId," +
	    			"a.inputCode," +
	    			"a.commConfigIdTypeId," +
	    			"a.idNo," +
	    			"b.stuFlag " +
	    			"from SecurityUserBaseinfo a , CardSecurityBaseinfo b where 1=1  and nvl(a.recordFlag,0)=0 and a.id = b.securityUserBaseinfoId and b.stuFlag='0'";
	       
	    	if(id.trim().length() > 0){
	    		sql += " and a.id = '" + id.trim() + "' ";
	        }
	    	if(pmi.trim().length() > 0){
	    		sql += " and a.pmi like '%" + pmi.trim() + "%' ";
	    	}
	    	if(name.trim().length() > 0){
	    		sql += " and a.name like '%" + name.trim() + "%' ";
	    	}
	    	if(nameEn.trim().length() > 0){
	    		sql += " and a.nameEn = '" + nameEn.trim() + "' ";
	    	}
	    	if(inputCode.trim().length() > 0){
	    		sql += " and a.inputCode like '" + inputCode.trim().toUpperCase() + "%' ";
	    	}
	    	if (commConfigIdTypeId!=null && commConfigIdTypeId.trim().length() > 0) {
				sql += " and a.commConfigIdTypeId = '" + commConfigIdTypeId.trim() + "' ";
			}
	    	if(orderNo.trim().length() > 0){
	    		sql += " order by " + orderNo;
	    	} 
	    	Query q = getSession().createQuery(sql);
	    	q.setFirstResult(curCount);
			q.setMaxResults(quChuCount);
			List<?> l=q.list();
		    
			return l;
		}
		catch (Exception re) {
			log.error("getUserExcelData error!", re);
			re.printStackTrace();
			return null;
		}
	}
	
	
	public List<?> getUserExcelData2(String id, String pmi, String name,
			String nameEn, String inputCode, String commConfigIdTypeId,
			String orderNo, int curCount, int quChuCount) {
			
		try {
			
	    	String sql = "select a.id," +
	    			"a.pmi," +
	    			"a.name," +
	    			"a.nameEn," +
	    			"a.dateOfBirth," +
	    			"a.commConfigSexId," +
	    			"a.inputCode," +
	    			"a.commConfigIdTypeId," +
	    			"a.idNo," +
	    			"b.stuFlag " +
	    			"from SecurityUserBaseinfo a , CardSecurityBaseinfo b where 1=1  and nvl(a.recordFlag,0)=0 and a.id = b.securityUserBaseinfoId ";
	       
	    	if(id.trim().length() > 0){
	    		sql += " and a.id = '" + id.trim() + "' ";
	        }
	    	if(pmi.trim().length() > 0){
	    		sql += " and a.pmi like '%" + pmi.trim() + "%' ";
	    	}
	    	if(name.trim().length() > 0){
	    		sql += " and a.name like '%" + name.trim() + "%' ";
	    	}
	    	if(nameEn.trim().length() > 0){
	    		sql += " and a.nameEn = '" + nameEn.trim() + "' ";
	    	}
	    	if(inputCode.trim().length() > 0){
	    		sql += " and a.inputCode like '" + inputCode.trim().toUpperCase() + "%' ";
	    	}
	    	if (commConfigIdTypeId!=null && commConfigIdTypeId.trim().length() > 0) {
				sql += " and a.commConfigIdTypeId = '" + commConfigIdTypeId.trim() + "' ";
			}
	    	if(orderNo.trim().length() > 0){
	    		sql += " order by " + orderNo;
	    	} 
	    	Query q = getSession().createQuery(sql);
	    	q.setFirstResult(curCount);
			q.setMaxResults(quChuCount);
			List<?> l=q.list();
		    
			return l;
		}
		catch (Exception re) {
			log.error("getUserExcelData error!", re);
			re.printStackTrace();
			return null;
		}
	}
	
	
	
	public List<?> getTownsByParent(String parentId) {
		// TODO Auto-generated method stub
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
	
	public List<?> getVillagesByParent(String parentId) {
		// TODO Auto-generated method stub
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
	
	public List<CommConfigLocationTown> findByItemCode1(String itemCode) {
		// TODO Auto-generated method stub
		try {
			List<CommConfigLocationTown> ls = this.getHibernateTemplate().find(" from CommConfigLocationTown a where a.itemCode = ? ", itemCode);
			return ls;
		}
		catch (Exception re) {
			log.error("findByItemCode1 error!", re);
			return null;
		}
	}
	
	public List<CommConfigLocationVillage> findByItemCode2(String itemCode) {
		// TODO Auto-generated method stub
		try {
			List<CommConfigLocationVillage> ls = this.getHibernateTemplate().find(" from CommConfigLocationVillage a where a.itemCode = ? ", itemCode);
			return ls;
		}
		catch (Exception re) {
			log.error("findByItemCode2 error!", re);
			return null;
		}
	}
	
	public List<?> getGroupsByParent(String parentId) {
		// TODO Auto-generated method stub
		try {
			String sql = "";
			sql += " from CommConfigLocationGroup a where 1=1 ";
			if (parentId.trim().length() > 0) {
				sql += " and a.commClvId = '" + parentId.trim() + "' ";
			}
			sql += " order by a.seqNo ";
			return getHibernateTemplate().find(sql);
		}
		catch (RuntimeException re) {
			log.error(re.toString());
			throw re;			
		}
	}
	
	public List<?> getItemName(String tableName, String colmon, String value) {
		List<?> ls=getHibernateTemplate().find("from " + tableName +" s where s."+ colmon + " = '"+value+"'");
		return ls;
	}
	
	public List<?> findManyById(String id) {
		// TODO Auto-generated method stub
		String sql="select a from SecurityDataObjectVsRoles a where a.securityStaffBaseinfoId='"+id+"'";		
		List<?> list= this.getHibernateTemplate().find(sql);		
		return list;
	}
	public int getcountbymore(SecurityUserBaseinfoForm securityUserBaseinfoForm) {
		// TODO Auto-generated method stub
		try{
			int count = 0;
			String sql = "select count(*) from security.security_user_baseinfo a left outer join card.security_user_vs_card t on a.id = t.SECURITY_USER_BASEINFO_ID " +
					" where 1=1 ";
			
			if(securityUserBaseinfoForm.getCardType() != null && securityUserBaseinfoForm.getCardType().length() > 0){
				sql += " and t.COMM_CONFIG_CARDTYPE_ID = '"+securityUserBaseinfoForm.getCardType().trim()+"'";
			}
			if(securityUserBaseinfoForm.getCardNo() != null && securityUserBaseinfoForm.getCardNo().length() > 0){
				sql += " and t.CARD_NO = '"+securityUserBaseinfoForm.getCardNo().trim()+"'";
			}
			if(securityUserBaseinfoForm.getPmi() != null && securityUserBaseinfoForm.getPmi().trim().length() > 0){
				sql += " and a.pmi = '"+securityUserBaseinfoForm.getPmi().trim()+"'";
			}
			if(securityUserBaseinfoForm.getName() != null && securityUserBaseinfoForm.getName().trim().length() > 0){
				sql += " and a.name like '%"+securityUserBaseinfoForm.getName().trim()+"%'";
			}
			if(securityUserBaseinfoForm.getNameEn() != null && securityUserBaseinfoForm.getNameEn().trim().length() > 0){
				sql += " and a.NAME_EN like '%" + securityUserBaseinfoForm.getNameEn().trim() + "%'";
			}
			if(securityUserBaseinfoForm.getCommConfigSexId() != null && securityUserBaseinfoForm.getCommConfigSexId().trim().length() > 0){
				sql += " and a.COMM_CONFIG_SEX_ID = '" + securityUserBaseinfoForm.getCommConfigSexId().trim() + "'";
			}
//			if(securityUserBaseinfoForm.getBirthPlace() != null && securityUserBaseinfoForm.getBirthPlace().trim().length() > 0){
//				sql += " and a.BIRTH_PLACE like '%" + securityUserBaseinfoForm.getBirthPlace().trim() + "%'";
//			}
			if(securityUserBaseinfoForm.getBirth_date_year() != null && securityUserBaseinfoForm.getBirth_date_year().trim().length() > 0){
				String date=securityUserBaseinfoForm.getBirth_date_year().trim()+"-"+
				securityUserBaseinfoForm.getBirth_date_month().trim()+"-"+
				securityUserBaseinfoForm.getBirth_date_day().trim();
				sql += " and a.DATE_OF_BIRTH = to_date('" + date+"','yyyy/MM/dd')";
			}
			if(securityUserBaseinfoForm.getCommConfigCountryId() != null && securityUserBaseinfoForm.getCommConfigCountryId().trim().length() > 0){
				sql += " and a.COMM_CONFIG_COUNTRY_ID = '" + securityUserBaseinfoForm.getCommConfigCountryId().trim() + "'";
			}
			if(securityUserBaseinfoForm.getCommConfigNationalityId() != null && securityUserBaseinfoForm.getCommConfigNationalityId().trim().length() > 0){
				sql += " and a.COMM_CONFIG_NATIONALITY_ID = '" + securityUserBaseinfoForm.getCommConfigNationalityId().trim() + "'";
			}
			if(securityUserBaseinfoForm.getCommConfigIdTypeId() != null && securityUserBaseinfoForm.getCommConfigIdTypeId().trim().length() > 0){
				sql += " and a.COMM_CONFIG_ID_TYPE_ID = '" + securityUserBaseinfoForm.getCommConfigIdTypeId().trim() + "'";
			}
			if(securityUserBaseinfoForm.getIdNo() != null && securityUserBaseinfoForm.getIdNo().trim().length() > 0){
				sql += " and a.ID_NO = '" + securityUserBaseinfoForm.getIdNo().trim() + "'";
			}
			if(securityUserBaseinfoForm.getDoorNo() != null && securityUserBaseinfoForm.getDoorNo().trim().length() > 0){
				sql += " and a.DOOR_NO like '%" + securityUserBaseinfoForm.getDoorNo().trim() + "%'";
			}
			if(securityUserBaseinfoForm.getSscid() != null && securityUserBaseinfoForm.getSscid().trim().length() > 0){
				sql += " and a.SSCID = '" + securityUserBaseinfoForm.getSscid().trim() + "'";
			}
			if(securityUserBaseinfoForm.getCommConfigDegreeId() != null && securityUserBaseinfoForm.getCommConfigDegreeId().trim().length() > 0){
				sql += " and a.COMM_CONFIG_DEGREE_ID = '" + securityUserBaseinfoForm.getCommConfigDegreeId().trim() + "'";
			}
			if(securityUserBaseinfoForm.getCommConfigMaritalStatusId() != null && securityUserBaseinfoForm.getCommConfigMaritalStatusId().trim().length() > 0){
				sql += " and a.COMM_CONFIG_MARITAL_STATUS_ID = '" + securityUserBaseinfoForm.getCommConfigMaritalStatusId().trim() + "'";
			}
			if(securityUserBaseinfoForm.getCommConfigLocationId1() != null && securityUserBaseinfoForm.getCommConfigLocationId1().trim().length() > 0){
				sql += " and a.COMM_CONFIG_LOCATION_ID_1 = '" + securityUserBaseinfoForm.getCommConfigLocationId1().trim() + "'";
			}
			if(securityUserBaseinfoForm.getCommConfigLocationId2() != null && securityUserBaseinfoForm.getCommConfigLocationId2().trim().length() > 0){
				sql += " and a.COMM_CONFIG_LOCATION_ID_2 = '" + securityUserBaseinfoForm.getCommConfigLocationId2().trim() + "'";
			}
			if(securityUserBaseinfoForm.getCommConfigLocationId3() != null && securityUserBaseinfoForm.getCommConfigLocationId3().trim().length() > 0){
				sql += " and a.COMM_CONFIG_LOCATION_ID_3 = '" + securityUserBaseinfoForm.getCommConfigLocationId3().trim() + "'";
			}
			if(securityUserBaseinfoForm.getCommConfigLocationTownId()!=null&&securityUserBaseinfoForm.getCommConfigLocationTownId().trim().length()>0){
				sql += " and a.COMM_CONFIG_LOCATION_TOWN_ID ='" +securityUserBaseinfoForm.getCommConfigLocationTownId().trim() +"'";
			}
			if(securityUserBaseinfoForm.getCommConfigLocationVillageId()!=null&&securityUserBaseinfoForm.getCommConfigLocationVillageId().trim().length()>0){
				sql += " and a.COMM_CONFIG_LOCATION_VILLAGEID ='" +securityUserBaseinfoForm.getCommConfigLocationVillageId().trim() +"'";
			}
			if(securityUserBaseinfoForm.getCommConfigLocationGroupId()!=null&&securityUserBaseinfoForm.getCommConfigLocationGroupId().trim().length()>0){
				sql += " and a.COMM_CONFIG_LOCATION_GROUP_ID ='" +securityUserBaseinfoForm.getCommConfigLocationGroupId().trim() +"'";
			}
//			if(securityUserBaseinfoForm.getMailingAddress() != null && securityUserBaseinfoForm.getMailingAddress().trim().length() > 0){
//				sql += " and a.MAILING_ADDRESS like '%" + securityUserBaseinfoForm.getMailingAddress().trim() + "%'";
//			}
			if(securityUserBaseinfoForm.getZipcode() != null && securityUserBaseinfoForm.getZipcode().trim().length() > 0){
				sql += " and a.ZIPCODE = '" + securityUserBaseinfoForm.getZipcode().trim() + "'";
			}
			if(securityUserBaseinfoForm.getPhone() != null && securityUserBaseinfoForm.getPhone().trim().length() > 0){
				sql += " and a.PHONE = '" + securityUserBaseinfoForm.getPhone().trim() + "'";
			}
			if(securityUserBaseinfoForm.getEMail() != null && securityUserBaseinfoForm.getEMail().trim().length() > 0){
				sql += " and a.E_MAIL = '" + securityUserBaseinfoForm.getEMail().trim() + "'";
			}
			if(securityUserBaseinfoForm.getMobileTel() != null && securityUserBaseinfoForm.getMobileTel().trim().length() > 0){
				sql += " and a.MOBILE_TEL = '" + securityUserBaseinfoForm.getMobileTel().trim() + "'";
			}
			if(securityUserBaseinfoForm.getInputCode() != null && securityUserBaseinfoForm.getInputCode().trim().length() > 0){
				sql += " and a.INPUT_CODE like '%" + securityUserBaseinfoForm.getInputCode().trim().toUpperCase() + "%'";
			}
			
			List<?> li = this.getSession().createSQLQuery(sql).list();
			if(li != null && li.size() > 0){
				count = Integer.valueOf(String.valueOf(li.get(0))).intValue();
			}
			log.debug("DAO public int getcountbymore OK");
			return count;
		}catch(Exception e){
			log.error("DAO public int getcountbymore error", e);
			e.printStackTrace();
			return new Integer("0");
		}
	}
	
	public List<?> getUsers(SecurityUserBaseinfoForm securityUserBaseinfoForm,
			int count, int pageSize) {
		// TODO Auto-generated method stub
		try{
			String sql = " select a.id,"+
			" a.pmi, "+
			" a.name, "+
			" a.NAME_EN, "+
			" a.INPUT_CODE, "+
			" a.COMM_CONFIG_SEX_ID, "+
			" a.DATE_OF_BIRTH, "+
			" a.COMM_CONFIG_COUNTRY_ID, "+
			" a.COMM_CONFIG_NATIONALITY_ID, "+
			" a.COMM_CONFIG_ID_TYPE_ID, "+
			" a.ID_NO, "+
			" a.SSCID, "+
			" a.COMM_CONFIG_ABO_ID, "+
			" a.COMM_CONFIG_RH_ID, "+
			" a.COMM_CONFIG_DEGREE_ID, "+
			" a.COMM_CONFIG_MARITAL_STATUS_ID, "+
			" a.COMM_CONFIG_LOCATION_ID_1, "+
			" a.COMM_CONFIG_LOCATION_ID_2, "+
			" a.COMM_CONFIG_LOCATION_ID_3, "+
			" a.COMM_CONFIG_LOCATION_TOWN_ID, "+
			" a.COMM_CONFIG_LOCATION_VILLAGEID, "+
			" a.ZIPCODE, "+
			" a.PHONE,"+ 
			" a.E_MAIL,"+
			" a.PHOTO_PATH,"+
			" a.COMM_CONFIG_RELATIONSHIP_ID,"+
			" a.CONTACT_PERSON_NAME,"+
			" a.CONTACT_PERSON_PHONE,"+
			" a.COMMENTS, " +
			" a.MOBILE_TEL, "+
			" a.BIRTH_LOCATION_NAME_1, "+
			" a.BIRTH_LOCATION_NAME_2, "+
			" a.BIRTH_LOCATION_NAME_3, "+
			" a.BIRTH_LOCATION_TOWN_NAME, "+
			" a.BIRTH_LOCATION_VILLAGE_NAME, "+
			" a.BIRTH_LOCATION_GROUP_NAME, "+
			" a.COMM_CONFIG_LOCATION_GROUP_ID, "+
			" a.CENSUS_LOCATION_NAME_1, "+
			" a.CENSUS_LOCATION_NAME_2, "+
			" a.CENSUS_LOCATION_NAME_3, "+
			" a.CENSUS_LOCATION_TOWN_NAME, "+
			" a.CENSUS_LOCATION_VILLAGE_NAME, "+
			" a.CENSUS_LOCATION_GROUP_NAME";
	    	sql += " from security.security_user_baseinfo a left outer join card.security_user_vs_card t on a.id = t.SECURITY_USER_BASEINFO_ID " +
			" where  1=1";
	    	
	    	if(securityUserBaseinfoForm.getCardType() != null && securityUserBaseinfoForm.getCardType().length() > 0){
				sql += " and t.COMM_CONFIG_CARDTYPE_ID = '"+securityUserBaseinfoForm.getCardType().trim()+"'";
			}
			if(securityUserBaseinfoForm.getCardNo() != null && securityUserBaseinfoForm.getCardNo().length() > 0){
				sql += " and t.CARD_NO = '"+securityUserBaseinfoForm.getCardNo().trim()+"'";
			}
			if(securityUserBaseinfoForm.getPmi() != null && securityUserBaseinfoForm.getPmi().trim().length() > 0){
				sql += " and a.pmi = '"+securityUserBaseinfoForm.getPmi().trim()+"'";
			}
			if(securityUserBaseinfoForm.getName() != null && securityUserBaseinfoForm.getName().trim().length() > 0){
				sql += " and a.name like '%"+securityUserBaseinfoForm.getName().trim()+"%'";
			}
			if(securityUserBaseinfoForm.getNameEn() != null && securityUserBaseinfoForm.getNameEn().trim().length() > 0){
				sql += " and a.NAME_EN like '%" + securityUserBaseinfoForm.getNameEn().trim() + "%'";
			}
			if(securityUserBaseinfoForm.getCommConfigSexId() != null && securityUserBaseinfoForm.getCommConfigSexId().trim().length() > 0){
				sql += " and a.COMM_CONFIG_SEX_ID = '" + securityUserBaseinfoForm.getCommConfigSexId().trim() + "'";
			}
//			if(securityUserBaseinfoForm.getBirthPlace() != null && securityUserBaseinfoForm.getBirthPlace().trim().length() > 0){
//				sql += " and a.BIRTH_PLACE like '%" + securityUserBaseinfoForm.getBirthPlace().trim() + "%'";
//			}
			if(securityUserBaseinfoForm.getBirth_date_year() != null && securityUserBaseinfoForm.getBirth_date_year().trim().length() > 0){
				String date=securityUserBaseinfoForm.getBirth_date_year().trim()+"-"+
				securityUserBaseinfoForm.getBirth_date_month().trim()+"-"+
				securityUserBaseinfoForm.getBirth_date_day().trim();
				sql += " and a.DATE_OF_BIRTH = to_date('" + date+"','yyyy/MM/dd')";
			}
			if(securityUserBaseinfoForm.getCommConfigCountryId() != null && securityUserBaseinfoForm.getCommConfigCountryId().trim().length() > 0){
				sql += " and a.COMM_CONFIG_COUNTRY_ID = '" + securityUserBaseinfoForm.getCommConfigCountryId().trim() + "'";
			}
			if(securityUserBaseinfoForm.getCommConfigNationalityId() != null && securityUserBaseinfoForm.getCommConfigNationalityId().trim().length() > 0){
				sql += " and a.COMM_CONFIG_NATIONALITY_ID = '" + securityUserBaseinfoForm.getCommConfigNationalityId().trim() + "'";
			}
			if(securityUserBaseinfoForm.getCommConfigIdTypeId() != null && securityUserBaseinfoForm.getCommConfigIdTypeId().trim().length() > 0){
				sql += " and a.COMM_CONFIG_ID_TYPE_ID = '" + securityUserBaseinfoForm.getCommConfigIdTypeId().trim() + "'";
			}
			if(securityUserBaseinfoForm.getIdNo() != null && securityUserBaseinfoForm.getIdNo().trim().length() > 0){
				sql += " and a.ID_NO = '" + securityUserBaseinfoForm.getIdNo().trim() + "'";
			}
			if(securityUserBaseinfoForm.getDoorNo() != null && securityUserBaseinfoForm.getDoorNo().trim().length() > 0){
				sql += " and a.DOOR_NO like '%" + securityUserBaseinfoForm.getDoorNo().trim() + "%'";
			}
			if(securityUserBaseinfoForm.getSscid() != null && securityUserBaseinfoForm.getSscid().trim().length() > 0){
				sql += " and a.SSCID = '" + securityUserBaseinfoForm.getSscid().trim() + "'";
			}
			if(securityUserBaseinfoForm.getCommConfigDegreeId() != null && securityUserBaseinfoForm.getCommConfigDegreeId().trim().length() > 0){
				sql += " and a.COMM_CONFIG_DEGREE_ID = '" + securityUserBaseinfoForm.getCommConfigDegreeId().trim() + "'";
			}
			if(securityUserBaseinfoForm.getCommConfigMaritalStatusId() != null && securityUserBaseinfoForm.getCommConfigMaritalStatusId().trim().length() > 0){
				sql += " and a.COMM_CONFIG_MARITAL_STATUS_ID = '" + securityUserBaseinfoForm.getCommConfigMaritalStatusId().trim() + "'";
			}
			if(securityUserBaseinfoForm.getCommConfigLocationId1() != null && securityUserBaseinfoForm.getCommConfigLocationId1().trim().length() > 0){
				sql += " and a.COMM_CONFIG_LOCATION_ID_1 = '" + securityUserBaseinfoForm.getCommConfigLocationId1().trim() + "'";
			}
			if(securityUserBaseinfoForm.getCommConfigLocationId2() != null && securityUserBaseinfoForm.getCommConfigLocationId2().trim().length() > 0){
				sql += " and a.COMM_CONFIG_LOCATION_ID_2 = '" + securityUserBaseinfoForm.getCommConfigLocationId2().trim() + "'";
			}
			if(securityUserBaseinfoForm.getCommConfigLocationId3() != null && securityUserBaseinfoForm.getCommConfigLocationId3().trim().length() > 0){
				sql += " and a.COMM_CONFIG_LOCATION_ID_3 = '" + securityUserBaseinfoForm.getCommConfigLocationId3().trim() + "'";
			}
			if(securityUserBaseinfoForm.getCommConfigLocationTownId()!=null&&securityUserBaseinfoForm.getCommConfigLocationTownId().trim().length()>0){
				sql += " and a.COMM_CONFIG_LOCATION_TOWN_ID ='" +securityUserBaseinfoForm.getCommConfigLocationTownId().trim() +"'";
			}
			if(securityUserBaseinfoForm.getCommConfigLocationVillageId()!=null&&securityUserBaseinfoForm.getCommConfigLocationVillageId().trim().length()>0){
				sql += " and a.COMM_CONFIG_LOCATION_VILLAGEID ='" +securityUserBaseinfoForm.getCommConfigLocationVillageId().trim() +"'";
			}
			if(securityUserBaseinfoForm.getCommConfigLocationGroupId()!=null&&securityUserBaseinfoForm.getCommConfigLocationGroupId().trim().length()>0){
				sql += " and a.COMM_CONFIG_LOCATION_GROUP_ID ='" +securityUserBaseinfoForm.getCommConfigLocationGroupId().trim() +"'";
			}
//			if(securityUserBaseinfoForm.getMailingAddress() != null && securityUserBaseinfoForm.getMailingAddress().trim().length() > 0){
//				sql += " and a.MAILING_ADDRESS like '%" + securityUserBaseinfoForm.getMailingAddress().trim() + "%'";
//			}
			if(securityUserBaseinfoForm.getZipcode() != null && securityUserBaseinfoForm.getZipcode().trim().length() > 0){
				sql += " and a.ZIPCODE = '" + securityUserBaseinfoForm.getZipcode().trim() + "'";
			}
			if(securityUserBaseinfoForm.getPhone() != null && securityUserBaseinfoForm.getPhone().trim().length() > 0){
				sql += " and a.PHONE = '" + securityUserBaseinfoForm.getPhone().trim() + "'";
			}
			if(securityUserBaseinfoForm.getEMail() != null && securityUserBaseinfoForm.getEMail().trim().length() > 0){
				sql += " and a.E_MAIL = '" + securityUserBaseinfoForm.getEMail().trim() + "'";
			}
			if(securityUserBaseinfoForm.getMobileTel() != null && securityUserBaseinfoForm.getMobileTel().trim().length() > 0){
				sql += " and a.MOBILE_TEL = '" + securityUserBaseinfoForm.getMobileTel().trim() + "'";
			}
			if(securityUserBaseinfoForm.getInputCode() != null && securityUserBaseinfoForm.getInputCode().trim().length() > 0){
				sql += " and a.INPUT_CODE like '%" + securityUserBaseinfoForm.getInputCode().trim().toUpperCase() + "%'";
			}
			if (securityUserBaseinfoForm.getOrderNo().trim().length() > 0) {
				sql += " order by " + securityUserBaseinfoForm.getOrderNo().trim();
			}
			System.out.println(sql);
			Query query = this.getSession().createSQLQuery(sql);
			query.setFirstResult(count);
			query.setMaxResults(pageSize);
			List<?> li = query.list();
			log.debug("DAO public int getusers OK");
			return li;
		}catch(Exception e){
			log.error("DAO public int getusers error", e);
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public List<?> getUserExcel3(String id, String pmi, String name,
			String nameEn, String inputCode, String commConfigIdTypeId,
			String orderNo, List<HspConfigBaseinfo> list) {
				try {
				
		    	String sql = "select a " +
		    	 "from SecurityUserBaseinfo a , CardSecurityBaseinfo b ,CardBaseinfo c" +
			     " where 1=1  and nvl(a.recordFlag,0)=0 and b.stuFlag ='5' and c.cardStu ='0'" +
			     "and a.id = b.securityUserBaseinfoId and b.securityUserBaseinfoId = c.securityUserBaseinfoId ";
		       
		    	if(id.trim().length() > 0){
		    		sql += " and a.id = '" + id.trim() + "' ";
		        }
		    	if(pmi.trim().length() > 0){
		    		sql += " and a.pmi like '%" + pmi.trim() + "%' ";
		    	}
		    	if(name.trim().length() > 0){
		    		sql += " and a.name like '%" + name.trim() + "%' ";
		    	}
		    	if(nameEn.trim().length() > 0){
		    		sql += " and a.nameEn = '" + nameEn.trim() + "' ";
		    	}
		    	if(inputCode.trim().length() > 0){
		    		sql += " and a.inputCode like '" + inputCode.trim().toUpperCase() + "%' ";
		    	}
		    	if (commConfigIdTypeId!=null && commConfigIdTypeId.trim().length() > 0) {
					sql += " and a.commConfigIdTypeId = '" + commConfigIdTypeId.trim() + "' ";
				}
		    	if(orderNo.trim().length() > 0){
		    		sql += " order by " + orderNo;
		    	} 
		    	Query q = getSession().createQuery(sql);
				List<?> l=q.list();
			    
				return l;
			   }
			     catch (Exception re) {
			     	log.error("getUser error!", re);
				    re.printStackTrace();
				    return null;
			   }
		  }
	
	@Override
	public int getUserExcelCount3(String id, String pmi, String name,
			String nameEn, String inputCode, String commConfigIdTypeId) {
		try {
			int count =0;
	    	String sql = "select count(a)" +
	    	 "from SecurityUserBaseinfo a , CardSecurityBaseinfo b ,CardBaseinfo c" +
		     " where 1=1  and nvl(a.recordFlag,0)=0 and b.stuFlag ='5' and c.cardStu ='0'" +
		     "and a.id = b.securityUserBaseinfoId and b.securityUserBaseinfoId = c.securityUserBaseinfoId ";
	       
	    	if(id.trim().length() > 0){
	    		sql += " and a.id = '" + id.trim() + "' ";
	        }
	    	if(pmi.trim().length() > 0){
	    		sql += " and a.pmi like '%" + pmi.trim() + "%' ";
	    	}
	    	if(name.trim().length() > 0){
	    		sql += " and a.name like '%" + name.trim() + "%' ";
	    	}
	    	if(nameEn.trim().length() > 0){
	    		sql += " and a.nameEn = '" + nameEn.trim() + "' ";
	    	}
	    	if(inputCode.trim().length() > 0){
	    		sql += " and a.inputCode like '" + inputCode.trim().toUpperCase() + "%' ";
	    	}
	    	if (commConfigIdTypeId!=null && commConfigIdTypeId.trim().length() > 0) {
				sql += " and a.commConfigIdTypeId = '" + commConfigIdTypeId.trim() + "' ";
			}
	    	Query q = getSession().createQuery(sql);
			List<?> l=q.list();
			if (l != null && l.size() > 0) {
				count = Integer.valueOf(String.valueOf(l.get(0))).intValue();
			}
			return count;
		}catch (Exception re) {
			log.error("getUserExcelCount error!", re);
			re.printStackTrace();
            return 0;
		}
	}
	@Override
	public List<?> getUserExcelData3(String id, String pmi, String name,
			String nameEn, String inputCode, String commConfigIdTypeId,
			String orderNo, int curCount, int quChuCount) {
try {
			
	    	String sql = "select a.id," +
	    			"a.pmi," +
	    			"a.name," +
	    			"a.nameEn," +
	    			"a.dateOfBirth," +
	    			"a.commConfigSexId," +
	    			"a.inputCode," +
	    			"a.commConfigIdTypeId, " +  
	    			"a.idNo " +  
	    			     "from SecurityUserBaseinfo a , CardSecurityBaseinfo b ,CardBaseinfo c" +
	    			     " where 1=1  and nvl(a.recordFlag,0)=0 and b.stuFlag ='5' and c.cardStu ='0'" +
	    			     "and a.id = b.securityUserBaseinfoId and b.securityUserBaseinfoId = c.securityUserBaseinfoId ";
	       
	    	if(id.trim().length() > 0){
	    		sql += " and a.id = '" + id.trim() + "' ";
	        }
	    	if(pmi.trim().length() > 0){
	    		sql += " and a.pmi like '%" + pmi.trim() + "%' ";
	    	}
	    	if(name.trim().length() > 0){
	    		sql += " and a.name like '%" + name.trim() + "%' ";
	    	}
	    	if(nameEn.trim().length() > 0){
	    		sql += " and a.nameEn = '" + nameEn.trim() + "' ";
	    	}
	    	if(inputCode.trim().length() > 0){
	    		sql += " and a.inputCode like '" + inputCode.trim().toUpperCase() + "%' ";
	    	}
	    	if (commConfigIdTypeId!=null && commConfigIdTypeId.trim().length() > 0) {
				sql += " and a.commConfigIdTypeId = '" + commConfigIdTypeId.trim() + "' ";
			}
	    	if(orderNo.trim().length() > 0){
	    		sql += " order by " + orderNo;
	    	} 
	    	Query q = getSession().createQuery(sql);
	    	q.setFirstResult(curCount);
			q.setMaxResults(quChuCount);
			List<?> l=q.list();
		    
			return l;
		}
		catch (Exception re) {
			log.error("getUserExcelData error!", re);
			re.printStackTrace();
			return null;
		}
	}
	
	public List<?> getUserBaseinfo(String flag, String input) {
		// TODO Auto-generated method stub
		String newName = null;
		String sql = "select a from SecurityUserBaseinfo a where 1=1 ";
		if ((flag != null && flag.equals("1")) && (input != null && !input.equals(""))) {
			sql += " and upper(a.inputCode) like '%" + input.trim().toUpperCase() + "%'";
		}
		if ((flag != null && flag.equals("2")) && (input != null && !input.equals(""))) {
			sql += " and a.id like '%" + input.trim() + "%'";
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

	public List<?> getDataUser(String id, String pmi,String hspConfigBaseinfoId, String name,
			String commConfigIdTypeId, String idNo, String inputCode,
			String orderNo, int curCount, int quChuCount,
			SecurityUserBaseinfoForm hosform) {
		// TODO Auto-generated method stub
		try {
			String sql="select cu.id,cu.name,cu.commConfigSexId,cu.commConfigIdTypeId," +
					"cu.dateOfBirth,cu.idNo,cu.commConfigLocationId3,cu.commConfigLocationTownId,cu.commConfigLocationVillageid ,cu.pmi from SecurityUserBaseinfo cu where 1=1";

			if(id.trim().length() > 0){
				sql += " and cu.id = '" + id.trim() + "' ";
		    }
			if(commConfigIdTypeId.trim().length() > 0){
				sql += " and cu.commConfigIdTypeId = '" + commConfigIdTypeId.trim() + "' ";
			}
			if(pmi.trim().length() > 0){
				sql += " and cu.pmi = '" + pmi.trim() + "' ";
			}
			if(name.trim().length() > 0){
				sql += " and cu.name like '%" + name.trim() + "%' ";
			}
			if(hosform.getCommConfigSexId() != null && hosform.getCommConfigSexId().trim().length() > 0){
				sql += " and cu.commConfigSexId = '" + hosform.getCommConfigSexId().trim() + "'";
			}
			if(hosform.getBirthPlace() != null && hosform.getBirthPlace().trim().length() > 0){
				sql += " and cu.birthPlace like '%" + hosform.getBirthPlace().trim() + "%'";
			}
			if(idNo.trim().length() > 0){
				sql += " and cu.idNo = '" + idNo.trim() + "' ";
			}
			if(inputCode.trim().length() > 0){
				sql += " and cu.inputCode like '" + inputCode.trim().toUpperCase() + "%' ";
			}
			if(hosform.getPmi()!=null&&hosform.getPmi().trim().length()>0){
				sql += " and cu.pmi ='" +hosform.getPmi().trim() +"'";
			}
			if(hosform.getNameEn() != null && hosform.getNameEn().trim().length() > 0){
				sql += " and cu.nameEn like '%" + hosform.getNameEn().trim() + "%'";
			}
			if(hosform.getBirth_date_year() != null && hosform.getBirth_date_year().trim().length() > 0){
				String date=hosform.getBirth_date_year().trim()+"-"+
				hosform.getBirth_date_month().trim()+"-"+
				hosform.getBirth_date_day().trim();
				//sql += "and a.dateOfBirth = to_date('" + date+"','yyyy/MM/dd')";
				sql += " and to_char(trunc(cu.dateOfBirth),'yyyy-MM-dd') = '"+date+"'";
			}
			if(hosform.getSscid() != null && hosform.getSscid().trim().length() > 0){
				sql += " and cu.sscid = '" + hosform.getSscid().trim() + "'";
			}
			if(hosform.getCommConfigCountryId() != null && hosform.getCommConfigCountryId().trim().length() > 0){
				sql += " and cu.commConfigCountryId = '" + hosform.getCommConfigCountryId().trim() + "'";
			}
			if(hosform.getCommConfigNationalityId() != null && hosform.getCommConfigNationalityId().trim().length() > 0){
				sql += " and cu.commConfigNationalityId = '" + hosform.getCommConfigNationalityId().trim() + "'";
			}
			if(hosform.getCommConfigDegreeId() != null && hosform.getCommConfigDegreeId().trim().length() > 0){
				sql += " and cu.commConfigDegreeId = '" + hosform.getCommConfigDegreeId().trim() + "'";
			}
			if(hosform.getCommConfigMaritalStatusId() != null && hosform.getCommConfigMaritalStatusId().trim().length() > 0){
				sql += " and cu.commConfigMaritalStatusId = '" + hosform.getCommConfigMaritalStatusId().trim() + "'";
			}
			if(hosform.getCommConfigLocationId1() != null && hosform.getCommConfigLocationId1().trim().length() > 0){
				sql += " and cu.commConfigLocationId1 = '" + hosform.getCommConfigLocationId1().trim() + "'";
			}
			if(hosform.getCommConfigLocationId2() != null && hosform.getCommConfigLocationId2().trim().length() > 0){
				sql += " and cu.commConfigLocationId2 = '" + hosform.getCommConfigLocationId2().trim() + "'";
			}
			if(hosform.getCommConfigLocationId3() != null && hosform.getCommConfigLocationId3().trim().length() > 0){
				sql += " and cu.commConfigLocationId3 = '" + hosform.getCommConfigLocationId3().trim() + "'";
			}
			if(hosform.getCommConfigLocationTownId()!=null&&hosform.getCommConfigLocationTownId().trim().length()>0){
				sql += "and cu.commConfigLocationTownId ='" +hosform.getCommConfigLocationTownId().trim() +"'";
			}
			if(hosform.getCommConfigLocationVillageId()!=null&&hosform.getCommConfigLocationVillageId().trim().length()>0){
				sql += "and cu.commConfigLocationVillageid ='" +hosform.getCommConfigLocationVillageId().trim() +"'";
			}
			if(hosform.getCommConfigLocationGroupId()!=null&&hosform.getCommConfigLocationGroupId().trim().length()>0){
				sql += "and cu.commConfigLocationGroupId ='" +hosform.getCommConfigLocationGroupId().trim() +"'";
			}
			if(hosform.getMailingAddress() != null && hosform.getMailingAddress().trim().length() > 0){
				sql += " and cu.mailingAddress like '%" + hosform.getMailingAddress().trim() + "%'";
			}
			if(hosform.getZipcode() != null && hosform.getZipcode().trim().length() > 0){
				sql += " and cu.zipcode = '" + hosform.getZipcode().trim() + "'";
			}
			if(hosform.getPhone() != null && hosform.getPhone().trim().length() > 0){
				sql += " and cu.phone = '" + hosform.getPhone().trim() + "'";
			}
			if(hosform.getEMail() != null && hosform.getEMail().trim().length() > 0){
				sql += " and cu.EMail = '" + hosform.getEMail().trim() + "'";
			}
			if(hosform.getMobileTel() != null && hosform.getMobileTel().trim().length() > 0){
				sql += " and cu.mobileTel = '" + hosform.getMobileTel().trim() + "'";
			}
			
	    	if(orderNo.trim().length() > 0){
	    		sql += " order by " + orderNo;
	    	} 
	    	Query q = getSession().createQuery(sql);
			q.setFirstResult(curCount); 
			q.setMaxResults(quChuCount); 
			List<?> l = q.list();
		    
			return l;
		}catch (Exception re) {
			log.error("getData error!", re);
			re.printStackTrace();
			return null;
		}
	}
}