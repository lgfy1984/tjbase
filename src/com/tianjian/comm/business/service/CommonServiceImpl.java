package com.tianjian.comm.business.service;

import com.tianjian.comm.business.ICommonService;
import com.tianjian.comm.dao.ICommonDAO;

public class CommonServiceImpl implements ICommonService{
	private ICommonDAO commonDAO;

	public ICommonDAO getCommonDAO() {
		return commonDAO;
	}

	public void setCommonDAO(ICommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}
}
