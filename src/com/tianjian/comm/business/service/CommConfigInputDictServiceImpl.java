package com.tianjian.comm.business.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.tianjian.comm.business.ICommConfigInputDictService;
import com.tianjian.comm.dao.ICommConfigInputDictDAO;

public class CommConfigInputDictServiceImpl implements ICommConfigInputDictService {

	private static final Logger log = LogManager.getLogger(CommConfigInputDictServiceImpl.class);

	private ICommConfigInputDictDAO dao;

	public ICommConfigInputDictDAO getDao() {
		return dao;
	}

	public void setDao(ICommConfigInputDictDAO dao) {
		this.dao = dao;
	}

	public String getInputCode(String name){
		try{
		String temp = "";
		if(name != null && name.trim().length() > 0){
			String subStr = name.trim();
			while(subStr.length() > 0){
				String first = subStr.substring(0, 1);
				temp += dao.getInputCode(first);
				subStr = subStr.substring(1);
			}
		}
		
		temp = temp.toUpperCase();
        if(temp.length() > 8){
        	temp = temp.substring(0, 8);
        }
       
		return temp;
		}catch(Exception e){
			log.error("getInputCode error",e);
			return "";
		}
	}
}