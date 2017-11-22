package com.tianjian.comm.dao.hibernate;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.comm.bean.CommConfigHospitalType;
import com.tianjian.comm.dao.ICommConfigHospitalTypeDAO;

public class CommConfigHospitalTypeDAO extends HibernateDaoSupport implements ICommConfigHospitalTypeDAO {
	private static final Logger log = LogManager.getLogger(CommConfigHospitalTypeDAO.class);
	/**
	 * 通过主键获取纪录
	 * @param id
	 * @return
	 */
	public CommConfigHospitalType findByItemCode(String itemCode){
		CommConfigHospitalType data = null;
		try{
			List<?> l=this.getHibernateTemplate().find("from CommConfigHospitalType a where a.itemCode = ? ", itemCode);
			if(l!=null&&l.size()>0){
				data=(CommConfigHospitalType) l.get(0);
			}
			log.debug("findByItemCode success!");
			return data;
		}catch(Exception re){
			log.error("findByItemCode error!",re);
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
			Query q=getSession().createQuery("from CommConfigHospitalType a");
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
	 * 保存
	 * */
	public void save(CommConfigHospitalType commConfigHospitalType) {
		try{
			this.getHibernateTemplate().save(commConfigHospitalType);
			log.debug("save success!");
		}catch(Exception re){
			log.error("save error!",re);
			re.printStackTrace();
		}
	}
	/**
	 * 修改
	 * */
	public void update(CommConfigHospitalType commConfigHospitalType) {
		try{
			this.getHibernateTemplate().update(commConfigHospitalType);
			log.debug("update success!");
		}catch(Exception re){
			log.error("update error!",re);
			re.printStackTrace();
		}
	}
	/**
	 * 删除
	 * */
	public void delete(CommConfigHospitalType commConfigHospitalType) {
		try{
			this.getHibernateTemplate().delete(commConfigHospitalType);
			log.debug("delete success!");
		}catch(Exception re){
			log.error("delete error!",re);
			re.printStackTrace();
		}
	}
	/**
	 * 根据某个属性查询另一个属性值
	 * */
	public String findP2ValueByP1(String beanName,String p1,String p1Value,String p2){
		String p2Value="";
		try{
			List<?> l= this.getHibernateTemplate().find("select a."+p2+" from "+beanName+" a where a."+p1+"=?",p1Value);
			if(l!=null&&l.size()>0){
				p2Value = String.valueOf((Object)l.get(0));
			}
			log.debug("findP2ValueByP1 success!");
			return p2Value;
		}catch(Exception re){
			log.error("findP2ValueByP1 error!",re);
			re.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 根据查询条件获取记录
	 * */
	public List<?> getData(String itemCode, String itemName, String inputCode,String seqNo, String orderNo, int curCount, int quChuCount){
		String sql="";
		sql = sql + "select a.itemCode," +
				"a.itemName," +
				"a.inputCode," +
				"a.levelFlag," +
				"a.parentItemCode," +
				"a.comments," +
				"a.seqNo" +
				" from CommConfigHospitalType a where 1=1" ;
		if(itemCode!=null&&itemCode.trim().length() > 0){
    		sql += " and a.itemCode like '" + itemCode.trim() + "%' ";
    	}
    	if(itemName!=null&&itemName.trim().length() > 0){
    		sql += " and lower(a.itemName) like '%" + itemName.trim().toLowerCase() + "%' ";
    	}
    	if(inputCode!=null&&inputCode.trim().length() > 0){
    		sql += " and upper(a.inputCode) like '" + inputCode.trim().toUpperCase() + "%' ";
    	}
    	if(seqNo!=null&&seqNo.trim().length() > 0){
    		sql += " and a.seqNo = '" + seqNo.trim() + "' ";
    	}
    	if(orderNo!=null&&orderNo.trim().length() > 0){
    		sql += " order by " + orderNo;
    	} 
    	try {
			Query q = getSession().createQuery(sql);
			q.setFirstResult(curCount); 
			q.setMaxResults(quChuCount); 
			List<?> l=q.list();
			
			return l;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 根据查询条件获取记录条数
	 * */
	public int getCount(String itemCode, String itemName, String inputCode,String seqNo){
		int count = 0;
		String sql="";
		sql = sql + "select count(*) from CommConfigHospitalType a where 1=1" ;
		if(itemCode!=null&&itemCode.trim().length() > 0){
    		sql += " and a.itemCode like '" + itemCode.trim() + "%' ";
    	}
		if(itemName!=null&&itemName.trim().length() > 0){
    		sql += " and a.itemName like '%" + itemName.trim() + "%' ";
    	}
		if(inputCode!=null&&inputCode.trim().length() > 0){
    		sql += " and upper(a.inputCode) like '" + inputCode.trim().toUpperCase() + "%' ";
    	}
		if(seqNo!=null&&seqNo.trim().length() > 0){
    		sql += " and a.seqNo = '" + seqNo.trim() + "' ";
    	}
    	
    	try {
    		List<?> list = getHibernateTemplate().find(sql);
			if (list != null && list.size() > 0) {
				count = Integer.valueOf(String.valueOf(list.get(0))).intValue();
			}
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	
	
	/**
	 * 弹出层所用
	 * */
	public List<?> findHspTypeList(String flag,String input,String hspTypeLevel, int curCount, int pageSize,HttpServletRequest request){
		try {
			String sql = "";
			sql += "from CommConfigHospitalType a where 1=1";
			//---1拼音 2代码 3汉字--
			if(input!=null&&input.trim().length()>0&&flag.equals("1")){
				sql = sql + " and upper(a.inputCode) like '"+input.toUpperCase()+"%'";
			}else if(flag.equals("2")){
				sql = sql + " and upper(a.itemCode) like '"+input.toUpperCase()+"%'";
			}else if(flag.equals("3")){
				sql = sql + " and a.itemName like '%"+input+"%'";
			}else{
				sql = sql + "  ";
			}
			//0为所有1为大类2为中类3为小类
			ServletContext application = request.getSession().getServletContext();
			if(hspTypeLevel.equals("1")){
				//sql = sql + " and a.levelFlag="+CommInit.getProperty("COMMCONFIGHOSPITALTYPELEVEL_1")+" ";
				sql = sql + " and a.levelFlag="+(String)application.getAttribute("comm.COMMCONFIGHOSPITALTYPELEVEL_1")+" ";
			}else if(hspTypeLevel.equals("2")){
				//sql = sql + " and a.levelFlag="+CommInit.getProperty("COMMCONFIGHOSPITALTYPELEVEL_2")+" ";
				sql = sql + " and a.levelFlag="+(String)application.getAttribute("comm.COMMCONFIGHOSPITALTYPELEVEL_2")+" ";
			}else if(hspTypeLevel.equals("3")){
				//sql = sql + " and a.levelFlag="+CommInit.getProperty("COMMCONFIGHOSPITALTYPELEVEL_3")+" ";
				sql = sql + " and a.levelFlag="+(String)application.getAttribute("comm.COMMCONFIGHOSPITALTYPELEVEL_3")+" ";
			}else{
				
			}
			Query q = getSession().createQuery(sql);
			q.setFirstResult(curCount); 
			q.setMaxResults(pageSize); 
			List<?> l=q.list();;
			return l;
		}
		catch (Exception re) {
			log.error("findHspTypeList error!", re);
			re.printStackTrace();
			return null;
		}
	}
	 
	/**
	 * 生成序号
	 * */
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
	/**
	 * 通过上一级分类得到对应的类
	 * */
	public List<?> getByParentItemCode(String parentItemCode,HttpServletRequest request){
		try {
			ServletContext application = request.getSession().getServletContext();
			String sql = "";
			sql += "from CommConfigHospitalType a where 1=1";
			//---1拼音 2代码 3汉字--
			if(parentItemCode.trim().equals("")){
				//sql = sql + " and a.levelFlag="+CommInit.getProperty("COMMCONFIGHOSPITALTYPELEVEL_1")+" ";
				sql = sql + " and a.levelFlag="+(String)application.getAttribute("comm.COMMCONFIGHOSPITALTYPELEVEL_1")+" ";
			}else{
				sql = sql + " and a.parentItemCode = '"+parentItemCode.trim()+"'";
			}
			
			List<?> l = this.getHibernateTemplate().find(sql);
			return l;
		}
		catch (Exception re) {
			log.error("getByParentItemCode error!", re);
			re.printStackTrace();
			return null;
		}
	}
}
