package com.tianjian.comm.dao.hibernate;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.comm.bean.CommConfigIcd10;
import com.tianjian.comm.dao.ICommConfigIcd10DAO;


public class CommConfigIcd10DAO extends HibernateDaoSupport implements ICommConfigIcd10DAO {

	private static final Logger log = LogManager.getLogger(CommConfigIcd10DAO.class);
	/**
	 * 通过主键获取纪录
	 * @param id
	 * @return
	 */
	public CommConfigIcd10 findById(String id) {
		CommConfigIcd10 data=null;
		try{
			List<?> l=this.getHibernateTemplate().find("from CommConfigIcd10 a where a.itemCode = ? ", id);
			if(l!=null&&l.size()>0){
				data=(CommConfigIcd10) l.get(0);
			}
			log.debug("findById success!");
			return data;
		}catch(Exception re){
			log.error("findById error!",re);
			re.printStackTrace();
			return null;
			
		}
		
	}
	/**
	 * 查找所有记录
	 * @return
	 */
	public List<?> findAll() {
		try{
			Query q=getSession().createQuery("from CommConfigIcd10 a");
			List<?> l=q.list();
			log.debug("findAll success!");
			return l;
		}catch(Exception re){
			log.error("findAll error!",re);
			re.printStackTrace();
			return null;
		}
	}
	/**
	 * 通过ID获取职称名称
	 * @param id
	 * @return
	 */
	public String getNameById(String id) {
		String name="";
		try{
			List<?> l= this.getHibernateTemplate().find("from CommConfigIcd10 a where a.itemName=?",id);
			if(l!=null&&l.size()>0){
				CommConfigIcd10 commConfigIcd10= (CommConfigIcd10) l.get(0);
				name=commConfigIcd10.getItemName();
			}
			log.debug("getNameById success!");
			return name;
		}catch(Exception re){
			log.error("getNameById error!",re);
			re.printStackTrace();
			return null;
			
		}
	}
	/**
	 * 保存记录
	 * @param commConfigIcd10
	 */
	public void save(CommConfigIcd10 commConfigIcd10) {
		try{
			this.getHibernateTemplate().save(commConfigIcd10);
			log.debug("save success!");
		}catch(Exception re){
			log.error("save error!",re);
			re.printStackTrace();
		}
	}
	/**
	 * 修改记录
	 * @param commConfigIcd10
	 */
	public void update(CommConfigIcd10 commConfigIcd10) {
		try{
			this.getHibernateTemplate().update(commConfigIcd10);
			log.debug("update success!");
		}catch(Exception re){
			log.error("update error!",re);
			re.printStackTrace();
		}
	}
	/**
     * 删除记录
     * @param commConfigIcd10
     */
	public void delete(CommConfigIcd10 commConfigIcd10) {
		try{
			this.getHibernateTemplate().delete(commConfigIcd10);
			log.debug("delete success!");
		}catch(Exception re){
			log.error("delete error!",re);
			re.printStackTrace();
		}
	}

	/**
	 * 根据条件获取当前页记录
	 * @param itemCode
	 * @param itemName
	 * @param inputCode
	 * @param seqNo
	 * @param orderNo
	 * @param curCount
	 * @param pageSize
	 * @return l
	 */
	public List<?> getData(String itemCode, String itemName, String inputCode,
			String seqNo, String orderNo, int curCount, int pageSize) {
		try {
    		String sql = " select a.itemCode, a.itemName,a.inputCode,a.comments,a.seqNo  ";
	    	sql += " from CommConfigIcd10 a  where 1=1 ";
	       
	    
	    	if(itemCode.trim().length() > 0){
	    		sql += " and a.itemCode like '" + itemCode.trim() + "%' ";
	    	}
	    	if(itemName.trim().length() > 0){
	    		sql += " and lower(a.itemName) like '%" + itemName.trim().toLowerCase() + "%' ";
	    	}
	    	if(inputCode.trim().length() > 0){
	    		sql += " and upper(a.inputCode) like '%" + inputCode.trim().toUpperCase() + "%' ";
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
        	log.error("getData fail!",re);
        	re.printStackTrace(); 
        	 return null;
        }
	}
	
//ajax弹出选择icd10
	
	public List<?> findIcd10List(String flag,String inputCode, int curCount, int pageSize){
		try {
			String sql = "";
			sql += "from CommConfigIcd10 a where 1=1";
			//---1拼音 2代码 3汉字--
			if(flag.equals("1")){
				sql = sql + " and upper(a.inputCode) like '"+inputCode.toUpperCase()+"%'";
			}else if(flag.equals("2")){
				sql = sql + " and a.itemCode like '"+inputCode+"%'";
			}else if(flag.equals("3")){
				sql = sql + " and a.itemName like '%"+inputCode+"%'";
			}else{
				sql = sql + "  ";
			}
			Query q = getSession().createQuery(sql);
			q.setFirstResult(curCount); 
			q.setMaxResults(pageSize); 
			List<?> l=q.list();;
			return l;
		}
		catch (Exception re) {
			log.error("findIcd10List error!", re);
			re.printStackTrace();
			return null;
		}
	}
	
	 /**
     * 根据查询条件获取记录总数
     * @param itemCode
     * @param itemName
     * @param inputCode
     * @param seqNo
     * @return
     */
	public int getCount(String itemCode, String itemName, String inputCode,
			String seqNo) {
		try {
    		int count = 0;
	    	String sql = "select count(*) ";
	    	sql += " from CommConfigIcd10 a  where 1=1  ";
	    	
	    	if(itemCode.trim().length() > 0){
	    		sql += " and a.itemCode like '" + itemCode.trim() + "%' ";
	    	}
	    	if(itemName.trim().length() > 0){
	    		sql += " and a.itemName like '%" + itemName.trim() + "%' ";
	    	}
	    	if(inputCode.trim().length() > 0){
	    		sql += " and upper(a.inputCode) like '%" + inputCode.trim().toUpperCase() + "%' ";
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
	    	log.error("getCount fail!",re);
	    	re.printStackTrace();
	    	return new Integer("0");
	    }
	}

	
	/**
     * 自动获取序号
     * @param commConfigYes
     */
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
}
