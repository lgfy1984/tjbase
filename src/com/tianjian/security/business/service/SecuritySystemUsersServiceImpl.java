package com.tianjian.security.business.service;

import java.util.Date;

import com.tianjian.security.bean.SecurityLicense;
import com.tianjian.security.bean.SecurityStaffBaseinfo;
import com.tianjian.security.bean.SecuritySystemUsers;
import com.tianjian.security.business.ISecuritySystemUsersService;
import com.tianjian.security.dao.ISecurityLicenseDAO;
import com.tianjian.security.dao.ISecurityStaffBaseinfoDAO;
import com.tianjian.security.dao.ISecuritySystemUsersDAO;
import com.tianjian.security.struts.form.SecuritySystemUsersForm;
import com.tianjian.util.comm.MD5;
/**
 * SECURITY_SYSTEM_USERS用户登陆表用Service
 * @author DZENALL
 * @since 2008-3-24 16:38
 */
public class SecuritySystemUsersServiceImpl implements ISecuritySystemUsersService {

	/*定義接口以及註入接口*/
	private ISecurityLicenseDAO securityLicenseDAO;
	private ISecurityStaffBaseinfoDAO securityStaffBaseinfoDAO;
	private ISecuritySystemUsersDAO securitySystemUsersDAO;

	public ISecuritySystemUsersDAO getSecuritySystemUserDAO() {return securitySystemUsersDAO;}
	public void setSecuritySystemUsersDAO(ISecuritySystemUsersDAO securitySystemUsersDAO) {this.securitySystemUsersDAO = securitySystemUsersDAO;}
	public ISecurityLicenseDAO getSecurityLicenseDAO() {return securityLicenseDAO;}
	public void setSecurityLicenseDAO(ISecurityLicenseDAO securityLicenseDAO) {this.securityLicenseDAO = securityLicenseDAO;}
	public ISecurityStaffBaseinfoDAO getSecurityStaffBaseinfoDAO() {return securityStaffBaseinfoDAO;}
	public void setSecurityStaffBaseinfoDAO(ISecurityStaffBaseinfoDAO securityStaffBaseinfoDAO) {this.securityStaffBaseinfoDAO = securityStaffBaseinfoDAO;}

	/*Service主方法*/
	public SecurityStaffBaseinfo checkData(SecuritySystemUsersForm form){
		try{
			SecurityStaffBaseinfo securityStaffBaseinfo = this.getStaffBaseinfoByStaffCode(form.getStaffCode());
//			String securityStaffBaseinfoId = securityStaffBaseinfo.getId();
//			return this.securitySystemUsersDAO.findBySecurityStaffBaseinfoId(securityStaffBaseinfoId);
			return securityStaffBaseinfo;
		}catch(Exception e){//抛出了异常，说明根据staffCode查不到数据库中对应的用户
			return null;
		}
	}

	
	public SecurityLicense checkRegistCode(String registCode){
		SecurityLicense securityLicense = securityLicenseDAO.findByRegistCode(registCode);
		return securityLicense;
	}
	
	
	public String hasLicense(String staffCode){
		String regCode = "";
		SecurityStaffBaseinfo securityStaffBaseinfo = this.getStaffBaseinfoByStaffCode(staffCode);
		String securityStaffBaseinfoId = securityStaffBaseinfo.getId();
		
		SecurityLicense securityLicense = this.getLicenseByStaffBaseinfoId(securityStaffBaseinfoId);
		if(securityLicense != null)
			regCode = securityLicense.getRegistCode();
		return regCode;
	}

	public boolean isRegisted(String staffCode){
		boolean flag = false;
		//////////////////////////////////////////////////
		try{
			SecurityStaffBaseinfo securityStaffBaseinfo = this.getStaffBaseinfoByStaffCode(staffCode);
			String securityStaffBaseinfoId = securityStaffBaseinfo.getId();
			SecuritySystemUsers securitySystemUsers =this.securitySystemUsersDAO.findBySecurityStaffBaseinfoId(securityStaffBaseinfoId);
			flag = (securitySystemUsers==null || securitySystemUsers.getLicenseTag()==null) ? false :
				(securitySystemUsers.getLicenseTag()==1L ? true : false);
			//////////////////////////////////////////////////
			return flag;
		}catch(Exception e){//抛出异常说明数据库中没有对应的用户或用户License记录，总体上说就是没有注册
			return false;
		}
		
	}

	//保存过程中可能出错。为什么此处不做处理？？？上抛一个Exception，需要Action中对之进行处理
	public void save(SecuritySystemUsersForm form) {
		String securityStaffBaseinfoId = form.getSecurityStaffBaseinfoId();
		SecurityLicense securityLicense = this.getLicenseByStaffBaseinfoId(securityStaffBaseinfoId);
		SecurityStaffBaseinfo staff = this.securityStaffBaseinfoDAO.findById(securityStaffBaseinfoId);
		String registCode = securityLicense.getRegistCode();
		String regCode = "";
		regCode = form.getRegCode_1()
			+ "-" + form.getRegCode_2()
			+ "-" + form.getRegCode_3()
			+ "-" + form.getRegCode_4()
			+ "-" + form.getRegCode_5();
		
		SecuritySystemUsers ssu = this.securitySystemUsersDAO.findBySecurityStaffBaseinfoId(securityStaffBaseinfoId);
		if(ssu == null || ssu.getSecurityStaffBaseinfoId() == null ||ssu.getSecurityStaffBaseinfoId().trim().length() <= 0){
			
			staff.setStaffCode(form.getStaffCode().trim());
			this.securityStaffBaseinfoDAO.update(staff);
			
			ssu = new SecuritySystemUsers();
			securityLicense.setStartTime(new Date());
			this.updateLicense(securityLicense);
			
			ssu.setSecurityStaffBaseinfoId(securityStaffBaseinfoId);
			ssu.setPasswd(MD5.toMD5(form.getPasswd()));
			ssu.setLicenseTag(1L);
			this.securitySystemUsersDAO.save(ssu);
		} else {
			staff.setStaffCode(form.getStaffCode().trim());
			this.securityStaffBaseinfoDAO.update(staff);
			
			securityLicense.setStartTime(new Date());
			this.updateLicense(securityLicense);
			
			ssu.setPasswd(MD5.toMD5(form.getPasswd()));
			ssu.setLicenseTag(1L);
			this.securitySystemUsersDAO.update(ssu);
		}
		
		
	}	

	public void registInit(SecuritySystemUsersForm hosForm) {}

	/*輔助方法*/
	private SecurityStaffBaseinfo getStaffBaseinfoByStaffCode(String staffCode){
		return this.securityStaffBaseinfoDAO.findByCode(staffCode);
	}

	private SecurityLicense getLicenseByStaffBaseinfoId(String securityStaffBaseinfoId){
		return this.securityLicenseDAO.findBySecurityStaffBaseinfoId(securityStaffBaseinfoId);
	}

	private void updateLicense(SecurityLicense securityLicense){
		this.securityLicenseDAO.update(securityLicense);
	}
}
