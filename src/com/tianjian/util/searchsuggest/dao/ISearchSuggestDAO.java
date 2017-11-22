package com.tianjian.util.searchsuggest.dao;

import java.util.List;

public interface ISearchSuggestDAO {
	public List<Object[]> getDataBySql(String param, String sql, int pageSize);
	public List<Object[]> getDataByHql(String param, String hql, int pageSize);
}
