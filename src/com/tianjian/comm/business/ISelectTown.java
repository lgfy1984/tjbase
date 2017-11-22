package com.tianjian.comm.business;

import java.util.List;


public interface ISelectTown {
	public List<?> getLocation(String itemCode,String staffid);
	public List<?> findVillageListByHql(String itemCode,String staffid);
	

}
