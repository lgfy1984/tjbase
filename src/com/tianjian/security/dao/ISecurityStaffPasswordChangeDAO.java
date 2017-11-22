package com.tianjian.security.dao;

import com.tianjian.security.bean.SecuritySystemUsers;

public interface ISecurityStaffPasswordChangeDAO {

    public SecuritySystemUsers findById(String id);

    public void update(SecuritySystemUsers securitySystemUsers);

}