package com.tianjian.security.dao;

import java.util.List;

import com.tianjian.security.bean.SecurityStaffBaseinfo;
import com.tianjian.security.bean.SecuritySystemUsers;

public interface ISecurityStaffBaseinfoDAO {
	/**
	 * 通过ID查找操作员信息
	 */
    public SecurityStaffBaseinfo findById(String id);
    /**
     * 通过操作员ID获取操作员登陆信息
     */
    public SecuritySystemUsers findByStaffId(String id);
    /**
     * 通过操作员登陆用户名staffCode获取操作员信息
     */
    public SecurityStaffBaseinfo findByCode(String code);
    /**
	 * 通过ID查找操作员信息
	 */
    public SecurityStaffBaseinfo findByStaffBsId(String id);
    /**
     * 保存操作员信息
     */ 
    public void save(SecurityStaffBaseinfo securityStaffBaseinfo);
    /**
     * 更新操作员信息
     */
    public void update(SecurityStaffBaseinfo securityStaffBaseinfo);
    /**
     * 删除操作员信息
     */
    public void delete(SecurityStaffBaseinfo securityStaffBaseinfo);
    /**
     * 保存操作员登陆信息
     */
    public void save(SecuritySystemUsers securitySystemUsers);
    /**
     * 更新操作员登陆信息
     */
    public void update(SecuritySystemUsers securitySystemUsers);
    /**
     * 删除操作员登陆信息
     */
    public void delete(SecuritySystemUsers securitySystemUsers);
    /**
     * 根据条件获取指定页面长度的操作员信息列表
     */
    public List<?> getData(String staffCode, String hspConfigBaseinfoId, String name, String commConfigSexId, String dateOfBirth, 
    		String inputCode,String staffId,String order, int curCount, int quChuCount);
    /**
     * 根据条件获取的操作员的总数量
     */
    public int getCount(String staffCode, String hspConfigBaseinfoId, String name, String commConfigSexId, String dateOfBirth,
    		String inputCode, String staffId);
    /**
     *从指定的table表中获取id和name
     */
    public List<?> getIdNames(String table, String id, String name);
    /**
     *从指定的table表中查找满足id=idValue的记录的name
     */
    public String getNameById(String table, String id, String name, String idValue);

    /**
     * 获取所有操作员信息
     */
	public List<?> getSecData();
	/**用于弹出子画面层显示*/
	public List<?> findHspList(String classFlag,String flag,String inputCode,String hspType, int curCount, int pageSize);
	
	public int getMaxSeqNo();
	
	public List<?> getDataRegisterCode(String staffCode, String hspConfigBaseinfoId, String name, String commConfigSexId, String dateOfBirth, String inputCode,String staffId, String order, int curCount, int quChuCount);
	
	 public List<?> getRegisterExport(String staffCode, String hspConfigBaseinfoId, String name);
}