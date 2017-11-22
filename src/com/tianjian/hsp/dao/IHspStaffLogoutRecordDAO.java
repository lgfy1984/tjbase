package com.tianjian.hsp.dao;

import java.util.List;

import com.tianjian.hsp.bean.HspStaffLogoutRecord;

public interface IHspStaffLogoutRecordDAO {
    public HspStaffLogoutRecord findById(String id);
    
    public List<?> findAll();
    
    public void save(HspStaffLogoutRecord hspStaffLogoutRecord);
    
    public void update(HspStaffLogoutRecord hspStaffLogoutRecord);
    
    public void delete(HspStaffLogoutRecord hspStaffLogoutRecord);
    
    public List<?> getData(String hspStaffBaseinfoId, String empNo, String name, String idNo,String order, int curCount,int pageSize);
    
    public int getCount(String hspStaffBaseinfoId, String empNo, String name, String idNo);

}
