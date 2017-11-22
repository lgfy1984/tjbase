package com.tianjian.comm.dao;

import java.util.List;


public interface ISelectTownDAO {
	 
	
	public List<?> findListByHql(String itemCode,String staffid);
	public List<?> findVillageListByHql(String itemCode,String staffid);
	
	 

}
