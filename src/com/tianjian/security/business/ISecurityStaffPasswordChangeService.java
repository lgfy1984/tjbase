package com.tianjian.security.business;

import com.tianjian.security.struts.form.SecurityStaffPasswordChangeForm;

public interface ISecurityStaffPasswordChangeService{

    public boolean checkData(String staffId, String password);
    
    public void update(SecurityStaffPasswordChangeForm form);

}