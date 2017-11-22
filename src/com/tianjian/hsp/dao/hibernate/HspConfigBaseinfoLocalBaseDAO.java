package com.tianjian.hsp.dao.hibernate;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.hsp.dao.IHspConfigBaseinfoLocalBaseDAO;
import com.tianjian.tenant.util.TenantIdHolder;
import com.tianjian.util.comm.TJInit;

public class HspConfigBaseinfoLocalBaseDAO extends HibernateDaoSupport implements IHspConfigBaseinfoLocalBaseDAO {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(HspConfigBaseinfoLocalBaseDAO.class);

	//医疗机构弹出层
	public List<?> findHspList(String flag,String inputCode,String staffHspId, int curCount, int pageSize){
		try {
			String tenantId = TenantIdHolder.get();
			StringBuilder sql = new StringBuilder("select a from  HspConfigBaseinfoLocalBase a")
							.append(" where a.tenantId = :tenantId");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tenantId", tenantId);
			sql.append(" and (a.id = :staffHspId");
			map.put("staffHspId", staffHspId);
			List<String> subHspIdList = this.getSubHspIds(staffHspId);
			if(subHspIdList != null && subHspIdList.size() > 0){
				for(String subHspId : subHspIdList){
					sql.append(" or a.id = '").append(subHspId).append("'");
				}
			}
			sql.append(")");
			//---1拼音 2代码 3汉字--
			if(flag.equals("1")){
				sql.append(" and upper(a.inputCode) like :inputCode");
				map.put("inputCode", inputCode.toUpperCase()+"%");
			}else if(flag.equals("2")){
				sql.append(" and a.itemCode like :itemCode");
				map.put("itemCode", inputCode+"%");
			}else if(flag.equals("3")){
				sql.append(" and a.itemName like :itemName");
				map.put("itemName", "%"+inputCode.trim()+"%");
			}

			Query q = getSession().createQuery(sql.toString());
			for(Map.Entry<String, Object> entry : map.entrySet()){
	        	q.setParameter(entry.getKey(), entry.getValue());
	        }
			q.setFirstResult(curCount); 
			q.setMaxResults(pageSize); 
			List<?> l=q.list();
			return l;
		}
		catch (Exception re) {
			logger.error("findHspList error!", re);
			re.printStackTrace();
			return null;
		}
		
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
