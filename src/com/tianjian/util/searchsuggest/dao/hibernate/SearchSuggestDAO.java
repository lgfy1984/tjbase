package com.tianjian.util.searchsuggest.dao.hibernate;

import java.util.*;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.util.searchsuggest.dao.ISearchSuggestDAO;

public class SearchSuggestDAO extends HibernateDaoSupport implements
		ISearchSuggestDAO {

	public List<Object[]> getDataBySql(String param, String sql, int pageSize) {
		try {
			int count = subCount(sql, "?");
			sql = "select * from (" + sql + ") where rownum <=" + pageSize;
			Query q = this.getSession().createSQLQuery(sql);
			for(int i=0; i< count; i++){
				q.setString(i, "%"+param+"%");
			}
			List<Object[]> list = q.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getSession().close();
		}
		return null;
	}

	public List<Object[]> getDataByHql(String param, String hql, int pageSize) {
		try {
			int count = subCount(hql, "?");
			Query q = this.getSession().createQuery(hql);
			for(int i=0; i< count; i++){
				q.setString(i, "%"+param+"%");
			}
			if (pageSize > 0) {
				q.setFirstResult(0);
				q.setMaxResults(pageSize);
			}
			List<Object[]> list = q.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getSession().close();
		}
		return null;
	}
	
	public static int subCount(String string, String subs) {
		int count = 0;
		String temp = string;
		int index;
		while ((index = temp.indexOf(subs)) != -1) {
			count++;
			temp = temp.substring(index + subs.length());
		}
		return count;
	}

}
