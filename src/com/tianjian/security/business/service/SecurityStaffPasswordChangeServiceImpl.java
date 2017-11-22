package com.tianjian.security.business.service;

import com.tianjian.security.bean.SecuritySystemUsers;
import com.tianjian.security.business.ISecurityStaffPasswordChangeService;

import com.tianjian.security.dao.ISecurityStaffPasswordChangeDAO;
import com.tianjian.security.struts.form.SecurityStaffPasswordChangeForm;
import com.tianjian.util.comm.MD5;


public class SecurityStaffPasswordChangeServiceImpl implements ISecurityStaffPasswordChangeService {

	
	
    private ISecurityStaffPasswordChangeDAO dao;

	public ISecurityStaffPasswordChangeDAO getDao() {
		return dao;
	}

	public void setDao(ISecurityStaffPasswordChangeDAO dao) {
		this.dao = dao;
	}

	public boolean checkData(String staffId, String password){
		boolean temp = true;
    	SecuritySystemUsers data = dao.findById(staffId);
    	
        if(data != null && data.getSecurityStaffBaseinfoId() != null){
        	String oldPassword = this.transNullToString(data.getPasswd());
        	String md5Password = MD5.toMD5(password);
        	if (oldPassword.equals(md5Password)){
        		temp = true;
        		
        	}else{
        		temp = false;
        	}
        }
        
        return temp;
    }

    public void update(SecurityStaffPasswordChangeForm form) {
    	SecuritySystemUsers data = dao.findById(form.getStaffId());
    	String md5Password = MD5.toMD5(form.getNewPasswd().trim());
    	data.setPasswd(md5Password);
        dao.update(data);
    }

    public String transNullToString(Object obj) {
        String temp = "";
        if(obj != null)
            temp = ((String)obj).trim();
        return temp;
    }
   
}