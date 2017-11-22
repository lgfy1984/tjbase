package com.tianjian.util.searchsuggest.business.service;

import java.util.*;


import com.tianjian.util.searchsuggest.business.ISearchSuggestService;
import com.tianjian.util.searchsuggest.dao.ISearchSuggestDAO;
import com.tianjian.util.searchsuggest.struts.servlet.SearchSuggestInit;

public class SearchSuggestServiceImpl implements ISearchSuggestService {
	private ISearchSuggestDAO ssDao;

	public ISearchSuggestDAO getSsDao() {
		return ssDao;
	}

	public void setSsDao(ISearchSuggestDAO ssDao) {
		this.ssDao = ssDao;
	}

	public String getData(String param, String ssid) {
		try {
			List<Object[]> datalist = null;
			String queryStr = SearchSuggestInit.getProperty(ssid);
			String pageSizeStr = SearchSuggestInit.getProperty("PAGE_SIZE");
			int pageSize = 10;
			if (pageSizeStr != null) {
				pageSize = Integer.valueOf(pageSizeStr);
			}
			String type = queryStr.substring(0, 4);
			if ("sql:".equals(type.toLowerCase())) {
				String sql = queryStr.substring(4);
				datalist = this.ssDao.getDataBySql(param, sql, pageSize);
			} else if ("hql:".equals(type.toLowerCase())) {
				String hql = queryStr.substring(4);
				datalist = this.ssDao.getDataByHql(param, hql, pageSize);
			}
			if (datalist != null) {
				StringBuilder xml = new StringBuilder(
						"<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				xml.append("<message>");
				try {
					for (Object[] objs : datalist) {
						xml.append("<info>");
						xml.append("<result>").append(objs[0]).append(
								"</result>");
						if (objs.length > 1) {
							for (int i = 1; i < objs.length; i++) {
								xml.append("<hidden>").append(objs[i]).append(
										"</hidden>");
							}
						}
						xml.append("</info>");
					}
				} catch (ClassCastException e) {
					for (Object obj : datalist) {
						xml.append("<info>");
						xml.append("<result>").append(obj).append("</result>");
						xml.append("</info>");
					}
				}
				xml.append("</message>");
				return xml.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
