package com.tianjian.comm.dao.hibernate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.comm.bean.CommConfigLocation;
import com.tianjian.comm.dao.ICommConfigLocationDAO;
import com.tianjian.util.db.DBTools;


public class CommConfigLocationDAO extends HibernateDaoSupport implements  ICommConfigLocationDAO{

	private static final Logger log = LogManager.getLogger(CommConfigLocationDAO.class);
	
	public List<?> getLocation(int level,String parentId) {
		// TODO Auto-generated method stub
		try{
			String hql="select c from CommConfigLocation c where c.levelFlag = "+level;
			if(parentId!=null&&parentId.trim().length()>0){
				hql += " and c.parentId='"+parentId.trim()+"'";
			}
			List<?> list=this.getHibernateTemplate().find(hql);
			return list;
		}catch(Exception e){
			return null;
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
	
	 public List<?> findAll(){
	    	try {
	    		String sql = " from CommConfigLocation a  ";
		       
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
	
	 public String getItemName(String id){
			String itemName = "";
			try {
				List<?> l = getHibernateTemplate().find(" from CommConfigLocation aa where aa.id = ? ", id);
				if(l != null && l.size() > 0){
					CommConfigLocation pub = (CommConfigLocation)l.get(0);
					itemName = pub.getItemName();
				}
				log.debug("getItemName success!");
				return itemName;
	        } catch (Exception re) {
	        	log.error("getItemName fail!",re);
	        	re.printStackTrace();
				return null; 
	        }
		} 
	    
	 
	public CommConfigLocation findById(String id){
		try{
			CommConfigLocation fac = null;
			List<?> ls = getHibernateTemplate().find(" from CommConfigLocation a where a.id = ? ", id);
			if(ls != null && ls.size() >0){
				fac = (CommConfigLocation) ls.get(0);
			}
			log.debug("findById success!");
			return fac;
		}catch(Exception re){
			log.error("findById error!",re);
			re.printStackTrace();
			return null; 
		}
	}
    
    public void save(CommConfigLocation commConfigLocation){
    	try {
        	getHibernateTemplate().save(commConfigLocation);
        	log.debug("save success!");
        } catch (Exception re) {
        	log.error("save error!",re);
        	re.printStackTrace();
        }
    }
    
    public void update(CommConfigLocation commConfigLocation){
    	try {
        	getHibernateTemplate().update(commConfigLocation);
        	log.debug("update success!");
        } catch (Exception re) {
        	log.error("update error!",re);
        	re.printStackTrace();
        }
    }
    
    public void delete(CommConfigLocation commConfigLocation){
    	try {
        	getHibernateTemplate().delete(commConfigLocation);
        	log.debug("delete success!");
        } catch (Exception re) {
        	log.error("delete error!",re); 
        	re.printStackTrace();
        }
    } 
    
    public List<?> getData(String id,String itemCode, String itemName, String levelFlag,String parentId, String inputCode, String seqNo, String orderNo, int curCount,int pageSize){
    	try {
    		String sql = " select a.id,a.itemCode, a.itemName, a.levelFlag,a.parentId,a.inputCode,a.comments,a.seqNo  ";
	    	sql += " from CommConfigLocation a  where 1=1 ";
	       
	    	if(id.trim().length() > 0){
	    		sql += " and a.id = '" + id.trim() + "' ";
	        }
	    	if(itemCode.trim().length() > 0){
	    		sql += " and a.itemCode = '" + itemCode.trim() + "' ";
	    	}
	    	if(itemName.trim().length() > 0){
	    		sql += " and a.itemName = '" + itemName.trim() + "' ";
	    	}
	    	if(levelFlag.trim().length() > 0){
	    		sql += " and a.levelFlag = '" + levelFlag.trim() + "' ";
	    	}
	    	if(parentId.trim().length() > 0){
	    		sql += " and a.parentId = '" + parentId.trim() + "' ";
	    	}
	    	if(inputCode.trim().length() > 0){
	    		sql += " and a.inputCode like '" + inputCode.trim().toUpperCase() + "%' ";
	    	}
	    	if(seqNo.trim().length() > 0){
	    		sql += " and a.seqNo = '" + seqNo.trim() + "' ";
	    	}
	    	if(orderNo.trim().length() > 0){
	    		sql += " order by " + orderNo;
	    	}
	    	
	    	Query q = getSession().createQuery(sql);
			q.setFirstResult(curCount); 
			q.setMaxResults(pageSize); 
			List<?> l=q.list();
			log.debug("getData success!");
			return l;
    	} catch (Exception re) {
        	log.error("getData error!",re);
        	re.printStackTrace();
        	return null;
        }
    }
    
    public int getCount(String id,String itemCode, String itemName, String levelFlag,String parentId, String inputCode, String seqNo){
    	try {
    		int count = 0;
	    	String sql = "select count(*) ";
	    	sql += " from CommConfigLocation a  where 1=1  ";
	    	if(id.trim().length() > 0){
	    		sql += " and a.id = '" + id.trim() + "' ";
	        }
	    	if(itemCode.trim().length() > 0){
	    		sql += " and a.itemCode = '" + itemCode.trim() + "' ";
	    	}
	    	if(itemName.trim().length() > 0){
	    		sql += " and a.itemName = '" + itemName.trim() + "' ";
	    	}
	    	if(levelFlag.trim().length() > 0){
	    		sql += " and a.levelFlag = '" + levelFlag.trim() + "' ";
	    	}
	    	if(parentId.trim().length() > 0){
	    		sql += " and a.parentId = '" + parentId.trim() + "' ";
	    	}
	    	if(inputCode.trim().length() > 0){
	    		sql += " and a.inputCode like '" + inputCode.trim().toUpperCase() + "%' ";
	    	}
	    	if(seqNo.trim().length() > 0){
	    		sql += " and a.seqNo = '" + seqNo.trim() + "' ";
	    	}
	    
	    	List<?> list = getHibernateTemplate().find(sql);
	    	if(list != null && list.size() > 0){
	    		count = Integer.valueOf(String.valueOf(list.get(0))).intValue();
	    	}
	    	log.debug("getCount success!");
	    	return count;
	    } catch (Exception re) {
	    	log.error("getCount error!",re);
	    	re.printStackTrace();
	    	return new Integer("0");
	    }
    }
    public int getMaxSeqNo(){
		int temp = 1;
		try {
			List<?> l = getHibernateTemplate().find(" select max(aa.seqNo) from CommConfigLocation aa ");
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
    
    public List<?> getCommcongfiglocation(String staffid, String l) {
		List countrylocation = new ArrayList();
		String sql = null;
		String sql1 = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		CommConfigLocation commconfiglocation = new CommConfigLocation();
		commconfiglocation.setItemCode("");
		commconfiglocation.setItemName("");
		countrylocation.add(commconfiglocation);
		sql1 = "SELECT vr.sdot_id,vr.sdo_value FROM security.security_data_object_vs_roles VR WHERE VR.SECURITY_STAFF_BASEINFO_ID='"
				+ staffid + "'";
		try {
			conn = DBTools.getConnection();
			pstmt1 = conn.prepareStatement(sql1);
			rs1 = pstmt1.executeQuery();
			boolean flag = false;
			int a = 0;
			Set set = new HashSet();

			while (rs1.next()) {
				set.add(rs1.getString(2).substring(0, 6));
				if ("1".equals(rs1.getString(1))
						|| "2".equals(rs1.getString(1))) {

					sql = "SELECT ITEM_CODE,ITEM_NAME FROM COMM.COMM_CONFIG_LOCATION WHERE PARENT_ID = '"
							+ rs1.getString(2) + "'ORDER BY ID";
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					while (rs.next()) {
						commconfiglocation = new CommConfigLocation();
						commconfiglocation.setItemCode(rs.getString(1));
						commconfiglocation.setItemName(rs.getString(2));
						countrylocation.add(commconfiglocation);
					}
				} else if ("3".equals(rs1.getString(1))) {
					sql = "SELECT ITEM_CODE,ITEM_NAME FROM COMM.COMM_CONFIG_LOCATION WHERE ID = '"
							+ rs1.getString(2) + "'ORDER BY ID";
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					while (rs.next()) {
						commconfiglocation = new CommConfigLocation();
						commconfiglocation.setItemCode(rs.getString(1));
						commconfiglocation.setItemName(rs.getString(2));
						countrylocation.add(commconfiglocation);
					}
				} else if ("4".equals(rs1.getString(1))) {
					a = 1;

				} else {
					flag = true;
					sql = "SELECT ITEM_CODE,ITEM_NAME FROM COMM.COMM_CONFIG_LOCATION WHERE ID = '"
							+ rs1.getString(2).substring(0, 6) + "'ORDER BY ID";
					while (rs.next()) {
						commconfiglocation = new CommConfigLocation();
						commconfiglocation.setItemCode(rs.getString(1));
						commconfiglocation.setItemName(rs.getString(2));
						countrylocation.add(commconfiglocation);
					}
				}

			}

			if (flag) {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					commconfiglocation = new CommConfigLocation();
					commconfiglocation.setItemCode(rs.getString(1));
					commconfiglocation.setItemName(rs.getString(2));
					countrylocation.add(commconfiglocation);
				}
			}
			if (a == 1) {
				Iterator ite = set.iterator();
				while (ite.hasNext()) {

					sql = "SELECT ITEM_CODE,ITEM_NAME FROM COMM.COMM_CONFIG_LOCATION WHERE ID = '"
							+ ite.next() + "'ORDER BY ID";
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					while (rs.next()) {
						commconfiglocation = new CommConfigLocation();
						commconfiglocation.setItemCode(rs.getString(1));
						commconfiglocation.setItemName(rs.getString(2));
						countrylocation.add(commconfiglocation);
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBTools.close(rs);
			DBTools.close(pstmt);
			DBTools.close(conn);
		}
		return countrylocation;
	}
    
    
    
}