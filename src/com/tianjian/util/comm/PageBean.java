package com.tianjian.util.comm;

/** 
 * MyEclipse Struts
 * Author:邱娇娇
 * Creation date: 04-05-2007
 */

public class PageBean {
	
	private int count = 0; // 记录总数

	private int pageSize = 10; // 每页显示记录�?

	private int pageCount = 0; // 总页�?

	private int page = 1; // 当前页数

	private String totalCountSQL;// 得到总记录数sql语句

	private String listSQL;// 得到查询记录sql语句

	public int getCount() {
		return count;
	}

	public PageBean(int count, int pageSize, int pageCount, int page, String totalCountSQL, String listSQL) {
		super();
		// TODO Auto-generated constructor stub
		this.count = count;
		this.pageSize = pageSize;
		this.pageCount = pageCount;
		this.page = page;
		this.totalCountSQL = totalCountSQL;
		this.listSQL = listSQL;
	}

	public PageBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public void setCount(int count) {
		if (pageSize != 0) {
			pageCount = count / pageSize;
			if (count % pageSize != 0) {
				pageCount++;
			}
		}
		this.count = count;
	}

	public String getListSQL() {
		return listSQL;
	}

	public void setListSQL(String listSQL) {
		this.listSQL = listSQL;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getTotalCountSQL() {
		return totalCountSQL;
	}

	public void setTotalCountSQL(String totalCountSQL) {
		this.totalCountSQL = totalCountSQL;
	}
}