package com.tianjian.comm.dao.hibernate;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.comm.dao.ICommonDAO;
import com.tianjian.util.ResourcesUtil;

public class CommonDAO extends HibernateDaoSupport implements ICommonDAO{

	public CommonDAO(){}

	private static final Log log = LogFactory.getLog(CommonDAO.class);
	//从指定table中得到id和name的列表   
	public List<?> getIdNames(String table, String id, String name){
		try{
			String sql = "select a." + id + ", a." + name + " ";
			sql += " from " + table + " a ";
			sql += " order by a." + id + " ";

			List<?> ls = getHibernateTemplate().find(sql);
			log.debug("getIdNames success!");
			return ls;
		} catch(Exception re){
			log.error("getIdNames fail!["+re.getMessage()+"]");
//			re.printStackTrace();
			return null;
		}
	}

	//从指定table中得到id和name的列表   
	public List<?> getIdNames(String table, String id, String name,String other){
		try{
			String sql = "select a." + id + ", a." + name + " ";
			sql += " from " + table + " a ";
			if(other.trim().length()>0){
				sql += " where a."+other+" =0 ";
			}
			sql += " order by a." + id + " ";
			System.out.println(sql);
			List<?> ls = getHibernateTemplate().find(sql);
			log.debug("getIdNames success!");
			return ls;
		} catch(Exception re){
			log.error("getIdNames fail!["+re.getMessage()+"]");
//			re.printStackTrace();
			return null;
		}
	}

	//从指定table中得到与指定value相等的name列表       
	public String getNameById(String table, String id, String name, String idValue){
		try{
			String temp = "";
			String sql = "select a." + name + " ";
			sql += " from " + table + " a ";
			sql += " where a." + id + " = '" + idValue + "' ";
			//System.out.println(sql);
			List<?> ls = getHibernateTemplate().find(sql);
			if(ls != null && ls.size() > 0){
				temp = String.valueOf(ls.get(0));
			}
			if(temp.equals("null")){
				temp = "";
			}
			log.debug("getNameById success!");
			return temp;
		} catch(Exception re){
			log.error("getNameById fail! ["+re.getMessage()+"]");
//			re.printStackTrace();
			return "";
		}
	}

	public String getSequenceNo(String tableName, String filedName) {
		try{
			String sql = "select a." + filedName + " ";
			sql += " from " + tableName + " a ";
			sql += " order by a." + filedName + " ";

			List<?> ls = getHibernateTemplate().find(sql);
			log.debug("Get data from database success! And the HQL=" + sql);

			if(ls.size() == 0)
				return "1";
			else{	        	
				Iterator<?> itr = null;
				if(ls.get(0) != null){
					String targetType = ls.get(0).getClass().getSimpleName();
					if(targetType.equalsIgnoreCase("Integer")){
						Integer i_target = 0;
						Integer i_targetTemp;
						for(itr = ls.iterator(); itr.hasNext();){
							Object temp = itr.next() ;
							if(temp != null){
								i_targetTemp = (Integer)temp;
								if(i_targetTemp >= i_target)
									i_target = i_targetTemp;
							}
						}
						i_target = i_target + 1;
						return i_target.toString();
					}	        	
					if(targetType.equalsIgnoreCase("Long")){
						Long l_target = 0L;		        	
						Long l_targetTemp;
						for(itr = ls.iterator(); itr.hasNext();){
							Object temp = itr.next() ;
							if(temp != null){
								l_targetTemp = (Long)temp;
								if(l_targetTemp >= l_target)
									l_target = l_targetTemp;
							}
						}
						l_target = l_target + 1;
						return l_target.toString();
					}
					if(targetType.equalsIgnoreCase("String")){
						Long s_target = 0L;		        	
						Long s_targetTemp;
						for(itr = ls.iterator(); itr.hasNext();){
							Object temp = itr.next() ;
							if(temp != null){
								s_targetTemp = Long.valueOf((String)temp);
								if(s_targetTemp >= s_target)
									s_target = s_targetTemp;
							}
						}
						s_target = s_target + 1;
						return s_target.toString();
					}
				}else{
					return "1";
				}
				log.debug("Wrong filed type excepted, getSequenceNo fail!");
				return null;
			}

		} catch(Exception re){
			log.error("getSequenceNo fail! ["+re.getMessage()+"]");
			return "";
		}
	}

	public String getSequenceNo(String tableName, String filedName, String[] fieldCondition, String[] fieldConditionValue){
		try{
			String sql = "select a." + filedName + " ";
			sql += " from " + tableName + " a ";
			if(fieldCondition != null && fieldCondition.length > 0){
				for(int i = 0; i < fieldCondition.length ; i++){
					if(i == 0){
						sql += " where a." + fieldCondition[i] + " = '" + fieldConditionValue[i] + "' ";
					} else {
						sql += " and a." + fieldCondition[i] + " = '" + fieldConditionValue[i] + "' ";
					}
				}
			}
			sql += " order by a." + filedName + " ";

			List<?> ls = getHibernateTemplate().find(sql);
			log.debug("Get data from database success! And the HQL=" + sql);

			if(ls.size() == 0)
				return "1";
			else{	        	
				Iterator<?> itr = null;
				String targetType = ls.get(0).getClass().getSimpleName();
				if(targetType.equalsIgnoreCase("Integer")){
					Integer i_target = 0;
					Integer i_targetTemp;
					for(itr = ls.iterator(); itr.hasNext();){
						i_targetTemp = (Integer)itr.next();
						if(i_targetTemp >= i_target)
							i_target = i_targetTemp;
					}
					i_target = i_target + 1;
					return i_target.toString();
				}	        	
				if(targetType.equalsIgnoreCase("Long")){
					Long l_target = 0L;		        	
					Long l_targetTemp;
					for(itr = ls.iterator(); itr.hasNext();){
						l_targetTemp = (Long)itr.next();
						if(l_targetTemp >= l_target)
							l_target = l_targetTemp;
					}
					l_target = l_target + 1;
					return l_target.toString();
				}
				if(targetType.equalsIgnoreCase("String")){
					Long s_target = 0L;		        	
					Long s_targetTemp;
					for(itr = ls.iterator(); itr.hasNext();){
						s_targetTemp = Long.valueOf((String)itr.next());
						if(s_targetTemp >= s_target)
							s_target = s_targetTemp;
					}
					s_target = s_target + 1;
					return s_target.toString();
				}
				log.debug("Wrong filed type excepted, getSequenceNo fail!");
				return null;
			}	        
		} catch(Exception re){
			log.error("getSequenceNo fail! ["+re.getMessage()+"]");
			return "";
		}
	}

	public List<?> getIdNamesNew(String table, String id, String name, String order){
		try{
			String sql = "select a." + id + ", a." + name + " ";
			sql += " from " + table + " a ";
			if(order != null && order.trim().length() > 0){
				sql += " order by a." + order + " asc " + " ";
			}

			List<?> ls = getHibernateTemplate().find(sql);
			log.debug("getIdNamesNew success!");
			return ls;
		} catch(Exception re){
			log.error("getIdNamesNew fail! ["+re.getMessage()+"]");
//			re.printStackTrace();
			return null;
		}
	}


	public void delete(Object data) {
		try{
			this.getHibernateTemplate().delete(data);
			log.debug("delete success!");
		}catch(Exception e){
			log.error("delete fail!["+e.getMessage()+"]");
//			e.printStackTrace();
		}
	}


	public void save(Object data) {
		try{
			this.getHibernateTemplate().save(data);
			log.debug("save success!");
		}catch(Exception e){
			log.error("save fail!["+e.getMessage()+"]");
//			e.printStackTrace();
		}
	}

	public void saveOrUpdate(Object data) {
		try{
			this.getHibernateTemplate().saveOrUpdate(data);
			log.debug("saveOrUpdate success!");
		}catch(Exception e){
			log.error("saveOrUpdate fail!["+e.getMessage()+"]");
			e.printStackTrace();
		}
	}


	public void update(Object data) {
		try{
			this.getHibernateTemplate().update(data);
			log.debug("update success!");
		}catch(Exception e){
			log.error("update fail!["+e.getMessage()+"]");
//			e.printStackTrace();
		}
	}


	public List<?> findListInTable(String nameOfTable, String nameOfProp, String valueOfProp) {
		try {
			String sql = " from "+nameOfTable+" a where a."+nameOfProp+" = ? order by a."+nameOfProp+" ";
			return this.getHibernateTemplate().find(sql, valueOfProp);
		} catch (Exception re) {
			log.error("findListInTable fail!["+re.getMessage()+"]");
//			re.printStackTrace(); 
			return null;
		}
	}

	public List<?> findIdNameInTable(String nameOfTable, String nameOfId, String nameOfName) {
		try{
			String sql = "select a." + nameOfId + ", a." + nameOfName + " ";
			sql += " from " + nameOfTable + " a ";
			sql += " order by a." + nameOfId + ", a."+nameOfName+" ";

			List<?> ls = getHibernateTemplate().find(sql);
			return ls;
		} catch(Exception re){
			log.error("findIdNameInTable fail! ["+re.getMessage()+"]");
//			re.printStackTrace();
			return null;
		}
	}

	public Object findById(String nameOfTable, String nameOfId, String valueOfId) {
		try{
			String sql = " from " + nameOfTable + " a ";
			sql += " where a." + nameOfId + "=?";
			sql += " order by a." + nameOfId + " ";

			List<?> ls = getHibernateTemplate().find(sql, new String[]{valueOfId});
			if(ls.size()>0&&ls!=null){
				return ls.get(0);
			}
			return null;
		} catch(Exception re){
			log.error("findById fail! ["+re.getMessage()+"]");
			re.printStackTrace();
			return null;
		}
	}


	public List<?> findIdNameInChar(String classCode) {
		try{
			String sql = "select a.id, a.dictValue from CommDictPublicChar a where a.classCode='"+classCode+"'order by a.id, a.dictValue ";
			List<?> ls = getHibernateTemplate().find(sql);
			return ls;
		} catch(Exception re){
			log.error("findIdNameInChar fail! ["+re.getMessage()+"]");
//			re.printStackTrace();
			return null;
		}
	}


	public List<?> findListInTable(String nameOfTable, String nameOfProp,
			String valueOfProp, String nameOfOrder, String descOrAsc) {
		try {
			String sql = " from "+nameOfTable+" a where a."+nameOfProp+" = ? order by a."+nameOfOrder+" "+descOrAsc;
			return this.getHibernateTemplate().find(sql, valueOfProp);
		} catch (Exception re) {
			log.error("findListInTable fail!["+re.getMessage()+"]");
//			re.printStackTrace(); 
			return null;
		}
	}


	public List<?> findListInTable(String nameOfTable, String[] nameOfFields, String[] valueOfFields) {
		if(nameOfTable==null || nameOfTable.trim().length()<=0){
			throw new RuntimeException("table name not null!");
		}
		if(nameOfFields==null || valueOfFields==null || nameOfFields.length<=0 || valueOfFields.length<=0){
			throw new RuntimeException("Query or query the value of an array of arrays not null!");
		}
		int lenN = nameOfFields.length;
		int lenV = valueOfFields.length;
		if(lenN!=lenV){
			throw new RuntimeException("Query the value of the array and query the length of the array does not match!");
		}
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("from "+nameOfTable+" a where");
			for(int i=0; i<lenN; i++){
				if(i==0){
					sb.append(" a."+nameOfFields[i]+"=?");					
				}else{
					sb.append(" or a."+nameOfFields[i]+"=?");
				}
			}
			return this.getHibernateTemplate().find(sb.toString(), valueOfFields);
		} catch (Exception re) {
			log.error("findListInTable fail!["+re.getMessage()+"]");
			re.printStackTrace(); 
			return null;
		}
	}


	public int getCount(String nameOfTable, String[] nameOfFields, String[] operators, String[] valueOfFields) {
		if(nameOfTable==null || nameOfTable.trim().length()<=0){
			throw new RuntimeException("table name not null!");
		}
		if(nameOfFields==null || valueOfFields==null || operators==null || nameOfFields.length<=0 || valueOfFields.length<=0 || operators.length<=0){
			throw new RuntimeException("Query operator array array or array of values ​​or query not null!");
		}
		int lenN = nameOfFields.length;
		int lenO = operators.length;
		int lenV = valueOfFields.length;
		if(lenN!=lenO || lenN!=lenV || lenO!=lenV){
			throw new RuntimeException("Query array operator array and query the length of the array of values ​​does not match!");
		}
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("from "+nameOfTable+" a where 1=1 ");
			String temp = "";
			for(int i=0; i<lenN; i++){
				if(valueOfFields[i]!=null && valueOfFields[i].trim().length()>0){
					if(operators[i].equals(ICommonDAO.OPERATION_LIKE)){
						temp = "%";
					}
					sb.append(" and a."+nameOfFields[i]+" "+operators[i]+" '"+valueOfFields[i]+temp+"'");
				}
			}
			List<?> list = this.getHibernateTemplate().find(sb.toString());
			if(list==null || list.size()<=0){
				return 0;
			}else{
				return list.size();
			}
		} catch (Exception re) {
			log.error("getCount fail!["+re.getMessage()+"]");
			re.printStackTrace(); 
			return 0;
		}
	}


	public List<?> getObjectData(String nameOfTable, String[] nameOfFields, String[] operators, String[] valueOfFields, String order, int startIndex, int pageSize) {
		if(nameOfTable==null || nameOfTable.trim().length()<=0){
			throw new RuntimeException("table name not null!");
		}
		if(nameOfFields==null || valueOfFields==null || operators==null || nameOfFields.length<=0 || valueOfFields.length<=0 || operators.length<=0){
			throw new RuntimeException("Query operator array array or array of values ​​or query not null!");
		}
		int lenN = nameOfFields.length;
		int lenO = operators.length;
		int lenV = valueOfFields.length;
		if(lenN!=lenO || lenN!=lenV || lenO!=lenV){
			throw new RuntimeException("Query array operator array and query the length of the array of values ​​does not match!");
		}
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("from "+nameOfTable+" a where 1=1 ");
			String temp = "";
			for(int i=0; i<lenN; i++){
				if(valueOfFields[i]!=null && valueOfFields[i].trim().length()>0){
					if(operators[i].equals(ICommonDAO.OPERATION_LIKE)){
						temp = "%";
					}
					sb.append(" and a."+nameOfFields[i]+" "+operators[i]+" '"+valueOfFields[i]+temp+"'");
				}
			}
			if(order.trim().length()>0){
				sb.append(" order by "+order);
			}
			Query query = getSession().createQuery(sb.toString());
			query.setFirstResult(startIndex); 
			query.setMaxResults(pageSize);
			return query.list();
		} catch (Exception re) {
			log.error("getObjectData fail!["+re.getMessage()+"]");
			re.printStackTrace(); 
			return null;
		}
	}


	public List<?> findAllInTable(String nameOfTable) {
		if(nameOfTable==null || nameOfTable.trim().length()<=0){
			throw new RuntimeException("table name not null!");
		}
		try{
			return this.getHibernateTemplate().find("from "+nameOfTable+" a where 1=1");
		}catch(Exception e){
			log.error("findAllInTable fail!");
			return null;
		}
	}


	public List<?> findListByHql(String hql) {
		if(hql==null || hql.trim().length()<=0){
			throw new RuntimeException("hql not null!");
		}
		try{
			return this.getHibernateTemplate().find(hql);
		}catch(Exception e){
			log.error("findListByHql fail!");
			e.printStackTrace();
			return null;
		}
	}

	public List<?> findListBySql(String sql) {
		if(sql==null || sql.trim().length()<=0){
			throw new RuntimeException("sql not null!");
		}
		try{
			return this.getSession().createSQLQuery(sql).list();
		}catch(Exception e){
			log.error("findListBySql fail!");
			e.printStackTrace();
			return null;
		}
	}
	public List<?> findListByHql(String hql, String[] args) {
		if(hql==null || hql.trim().length()<=0){
			throw new RuntimeException("hql not null!");
		}
		try{
			if(args==null && args.length<=0){
				return this.getHibernateTemplate().find(hql);
			}else{
				return this.getHibernateTemplate().find(hql, args);
			}
		}catch(Exception e){
			log.error("findListByHql fail!");
			e.printStackTrace();
			return null;
		}
	}

	public List<?> findPageListByHql(String hql, int startIndex, int pageSize) {
		if(hql==null || hql.trim().length()<=0){
			throw new RuntimeException("hql not null!");
		}
		try{
			Query query = this.getSession().createQuery(hql);
			query.setFirstResult(startIndex);
			query.setMaxResults(pageSize);
			return query.list();
		}catch(Exception e){
			log.error("findPageListByHql fail!");
			e.printStackTrace();
			return null;
		}
	}

	public List<?> findAllInTable(String nameOfTable, String[] nameOfOrderFields, String[] orders) {
		if(nameOfTable==null || nameOfTable.trim().length()<=0){
			throw new RuntimeException("table name not null!");
		}
		if(nameOfOrderFields==null || orders==null || nameOfOrderFields.length<=0 || orders.length<=0){
			throw new RuntimeException("Query or queries sorted array sorted array not null!");
		}
		int lenN = nameOfOrderFields.length;
		int lenO = orders.length;
		if(lenN!=lenO){
			throw new RuntimeException("Query sort array sorted array or query does not match the length!");
		}
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("from "+nameOfTable+" a where 1=1 order by ");
			for(int i=0; i<lenN; i++){
				sb.append(" a."+nameOfOrderFields[i]+" "+orders[i]+",");
			}
			String sql = sb.toString();
			int len = sql.length();
			return this.getHibernateTemplate().find(sql.substring(0, len-1));
		}catch(Exception e){
			log.error("findAllInTable fail!");
			return null;
		}
	}


	public void deleteBatch(List<?> list) {
		try{
			this.getHibernateTemplate().deleteAll(list);
		}catch(Exception e){
			log.error("deleteBatch fail!");
			e.printStackTrace();
		}
	}


	public void findIdNameList(String nameOfTable, String nameOfId, String nameOfName, List<String> idList, List<String> nameList, String mode, HttpServletRequest request) {
		try {
			if(idList==null)
				idList = new ArrayList<String>();
			if(nameList==null)
				nameList = new ArrayList<String>();
			if(mode.toLowerCase().equals("all")){
				idList.add("");
				String str = ResourcesUtil.getValue("conf.comm.CommLocale", "comm.java.commom.all", request);
				nameList.add("---"+str+"---");
			}else if(mode.toLowerCase().equals("blank")){
				idList.add("");
				nameList.add("");
			}
			String sql = "select a."+nameOfId+",a."+nameOfName+" from "+nameOfTable+" a";
			List<?> list = this.getHibernateTemplate().find(sql);
			if(list!=null && list.size()>0){
				for(int i=0; i<list.size(); i++){
					Object[] objs = (Object[]) list.get(i);
					idList.add(this.transNullToString(objs[0]));
					nameList.add(this.transNullToString(objs[1]));
				}
			}
		} catch (Exception re) {
			log.error("findIdNameList fail!["+re.getMessage()+"]");
			re.printStackTrace(); 
		}
	}	
	public String transNullToString(Object obj){
		return obj==null?"":String.valueOf(obj);
	}


	public void findIdNameList(String nameOfTable, String nameOfId,
			String nameOfName, String[] nameOfFields, String[] valueOfFields,
			List<String> idList, List<String> nameList, String mode, HttpServletRequest request) {
		try {
			if(nameOfFields!=null && valueOfFields!=null && valueOfFields.length!=nameOfFields.length){
				throw new RuntimeException("Matching the length of the parameters do not match!");
			}
			if(idList==null)
				idList = new ArrayList<String>();
			if(nameList==null)
				nameList = new ArrayList<String>();
			if(mode.toLowerCase().equals("all")){
				idList.add("");
				String str = ResourcesUtil.getValue("conf.comm.CommLocale", "comm.java.commom.all", request);
				nameList.add("---"+str+"---");
			}else if(mode.toLowerCase().equals("blank")){
				idList.add("");
				nameList.add("");
			}
			List<?> list = null;
			String sql = "select a."+nameOfId+",a."+nameOfName+" from "+nameOfTable+" a";
			if(nameOfFields!=null){
				sql += " where 1=1";
				for(int i=0; i<nameOfFields.length; i++){
					sql += " and a."+nameOfFields[i]+"=?";
				}
				list = this.getHibernateTemplate().find(sql, valueOfFields);
			}else{
				list = this.getHibernateTemplate().find(sql);
			}		
			if(list!=null && list.size()>0){
				for(int i=0; i<list.size(); i++){
					Object[] objs = (Object[]) list.get(i);
					idList.add(this.transNullToString(objs[0]));
					nameList.add(this.transNullToString(objs[1]));
				}
			}
		} catch (Exception re) {
			log.error("findIdNameList fail!["+re.getMessage()+"]");
			re.printStackTrace(); 
		}
	}


	public Object getObject(String nameOfTable, String[] nameOfConditionFields,	String[] valueOfConditionFields){
		try{
			if(nameOfTable==null || nameOfTable.trim().length()<=0){
				throw new NullPointerException("nameOfTable not Null");
			}
			if(nameOfConditionFields!=null && valueOfConditionFields!=null && nameOfConditionFields.length!=valueOfConditionFields.length){
				throw new RuntimeException("Parameters on the length of the match match!");
			}
			StringBuffer sb = new StringBuffer();
			List<?> list = null;
			sb.append("from "+nameOfTable+" a");
			if(nameOfConditionFields!=null){
				sb.append(" where 1=1");
				for(int i=0; i<nameOfConditionFields.length; i++){
					sb.append(" and a."+nameOfConditionFields[i]+"=?");
				}
				list = this.getHibernateTemplate().find(sb.toString(), valueOfConditionFields);
			}else{
				list = this.getHibernateTemplate().find(sb.toString());
			}
			if(list!=null && list.size()>0){
				return list.get(0);
			}else{
				return null;
			}
		}catch(Exception e){
			log.error("getObject fail!");
			return null;
		}
	}


	public String getValue(String nameOfTable, String nameOfTargetField, String[] nameOfConditionFields, String[] valueOfConditionFields) {
		try{
			if(nameOfTable==null || nameOfTable.trim().length()<=0){
				throw new NullPointerException("nameOfTable not null");
			}
			if(nameOfConditionFields!=null && valueOfConditionFields!=null && nameOfConditionFields.length!=valueOfConditionFields.length){
				throw new RuntimeException("Parameters on the length of the match match!");
			}
			StringBuffer sb = new StringBuffer();
			List<?> list = null;
			sb.append("select a."+nameOfTargetField+" from "+nameOfTable+" a");
			if(nameOfConditionFields!=null){
				sb.append(" where 1=1");
				for(int i=0; i<nameOfConditionFields.length; i++){
					sb.append(" and a."+nameOfConditionFields[i]+"=?");
				}
				list = this.getHibernateTemplate().find(sb.toString(), valueOfConditionFields);
			}else{
				list = this.getHibernateTemplate().find(sb.toString());
			}
			if(list!=null && list.size()>0){
				return String.valueOf(list.get(0));
			}else{
				return null;
			}
		}catch(Exception e){
			log.error("getValue fail!");
			return null;
		}
	}

	@Override
	public List<?> findPageListBySql(String sql, int start, int max) {
		if(sql==null || sql.trim().length()<=0){
			throw new RuntimeException("sql not null!");
		}
		try{
			Query query = this.getSession().createSQLQuery(sql);
			query.setFirstResult(start);
			query.setMaxResults(max);
			return query.list();
		}catch(Exception e){
			log.error("findPageListBySql fail!");
			e.printStackTrace();
			return null;
		}
	}
	


	@Override
	public Object findObjectByHql(String hql) {
		List<?> list = this.getHibernateTemplate().find(hql);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int findPageCountByHql(String hql) {
		List<?> list = null;
		list = this.getHibernateTemplate().find(hql);
		if(list!=null){
			return Integer.valueOf(String.valueOf(list.get(0)));
		}
		return 0;
	}
	
	/**
	 * 适用于根据ID或者代码取名称等情况 只适用于字符型  SQL语句非HQL
	 * */
	public String getValueByAnotherSQL(String table,String inColumn,String inColumnValue,String outColumn) {
		String temp="";
		String sql="select a."+outColumn+" from "+table+" a where a."+inColumn+"='"+inColumnValue+"'";
		SQLQuery query=this.getSession().createSQLQuery(sql);
		List<?> ls = query.list();
		if(ls.size()>0&&ls!=null){
			for(int i=0;i<ls.size();i++){
				temp = (String)ls.get(0);
			}
		}
		return temp;
	}

}