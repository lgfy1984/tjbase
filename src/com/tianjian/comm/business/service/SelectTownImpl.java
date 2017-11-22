package com.tianjian.comm.business.service;
import java.util.List;
import com.tianjian.comm.business.ISelectTown;
import com.tianjian.comm.dao.ISelectTownDAO;

public class SelectTownImpl implements ISelectTown {
	 
	 
	private ISelectTownDAO selectTownDAO;
	
	 
	 
	public List<?> getLocation(String itemCode,String staffid) {
		List<?> location=selectTownDAO.findListByHql(itemCode,staffid);
		return location;
	}
	public List<?> findVillageListByHql(String itemCode,String staffid){
		List<?> villages=selectTownDAO.findVillageListByHql( itemCode, staffid);
		return villages;
	}



	public ISelectTownDAO getSelectTownDAO() {
		return selectTownDAO;
	}



	public void setSelectTownDAO(ISelectTownDAO selectTownDAO) {
		this.selectTownDAO = selectTownDAO;
	}
	 
 
	 

}
