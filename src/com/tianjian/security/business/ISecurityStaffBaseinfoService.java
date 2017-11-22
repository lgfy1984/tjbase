package com.tianjian.security.business;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.tianjian.security.bean.SecurityStaffBaseinfo;
import com.tianjian.security.struts.form.SecurityStaffBaseinfoForm;

public interface ISecurityStaffBaseinfoService
{
	public void getLogin(SecurityStaffBaseinfoForm form,HttpServletRequest request);
	
    public void addInit(SecurityStaffBaseinfoForm form,HttpServletRequest request);

    public String checkData(String s,HttpServletRequest request);
    
    public String checkSecurityLicense(String securityStaffBaseinfoId);

    public void save(SecurityStaffBaseinfoForm form,HttpServletRequest request,String regCode);
    
    public void saveSecurityLicense(String securityStaffBaseinfoId,String regCode);

    public void updateInit(SecurityStaffBaseinfoForm form,HttpServletRequest request);

    public void update(SecurityStaffBaseinfoForm form);

    public void showInit(SecurityStaffBaseinfoForm form,HttpServletRequest request);

    public void delete(SecurityStaffBaseinfoForm form);
    
    public void deleteSecurityLicense(String securityStaffBaseinfoId);

    public int getCount(String staffCode, String hspConfigBaseinfoId, String name, 
    		String commConfigSexId, String dateOfBirth, String inputCode,String staffId);

    public void getSearch(SecurityStaffBaseinfoForm form, int i, int j);

    public void searchInit(SecurityStaffBaseinfoForm form,HttpServletRequest request);

    public void getDetail(SecurityStaffBaseinfoForm form,HttpServletRequest request);
    
    public String generateRegCode(int zushu, int weishu);
	
	public SecurityStaffBaseinfo findbyId(String id);
	/**用于弹出层显示*/
	public String getXml(String classFlag, String flag,String inputCode,String hspType,HttpServletRequest request);
	
	public void getSearchRegisterCode(SecurityStaffBaseinfoForm form, int curCount, int pageSize);
	
	/**导出注册码信息*/
	public HSSFWorkbook getExcel(SecurityStaffBaseinfoForm form, String fileName, HttpServletRequest request);
}