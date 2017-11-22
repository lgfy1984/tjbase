package com.tianjian.hsp.business.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.tianjian.comm.bean.CommConfigSex;
import com.tianjian.comm.dao.ICommConfigSexDAO;
import com.tianjian.hsp.bean.HspStaffBaseinfo;
import com.tianjian.hsp.bean.HspStaffIdList;
import com.tianjian.hsp.bean.HspStaffMergeLog;
import com.tianjian.hsp.business.IHspStaffBaseinfoMergeService;
import com.tianjian.hsp.dao.IHspStaffBaseinfoDAO;
import com.tianjian.hsp.dao.IHspStaffBaseinfoMergeDAO;
import com.tianjian.hsp.struts.form.HspStaffBaseinfoForm;
import com.tianjian.hsp.struts.form.HspStaffBaseinfoMergeForm;
import com.tianjian.hsp.struts.servlet.HspInit;
import com.tianjian.security.bean.SecurityLicense;
import com.tianjian.security.bean.SecurityStaffBaseinfo;
import com.tianjian.security.dao.ISecurityLicenseDAO;
import com.tianjian.security.dao.ISecurityStaffBaseinfoDAO;

public class HspStaffBaseinfoMergeServiceImpl implements IHspStaffBaseinfoMergeService {
	
	private IHspStaffBaseinfoMergeDAO hspStaffBaseinfoMergeDAO;
	public IHspStaffBaseinfoDAO hspStaffBaseinfoDAO;
	private ICommConfigSexDAO commConfigSexDAO;
	public ISecurityStaffBaseinfoDAO securityStaffBaseinfoDAO;
	private ISecurityLicenseDAO securityLicenseDAO;

	public ISecurityStaffBaseinfoDAO getSecurityStaffBaseinfoDAO() {
		return securityStaffBaseinfoDAO;
	}

	public void setSecurityStaffBaseinfoDAO(
			ISecurityStaffBaseinfoDAO securityStaffBaseinfoDAO) {
		this.securityStaffBaseinfoDAO = securityStaffBaseinfoDAO;
	}

	public ISecurityLicenseDAO getSecurityLicenseDAO() {
		return securityLicenseDAO;
	}

	public void setSecurityLicenseDAO(ISecurityLicenseDAO securityLicenseDAO) {
		this.securityLicenseDAO = securityLicenseDAO;
	}

	public ICommConfigSexDAO getCommConfigSexDAO() {
		return commConfigSexDAO;
	}

	public void setCommConfigSexDAO(ICommConfigSexDAO commConfigSexDAO) {
		this.commConfigSexDAO = commConfigSexDAO;
	}

	public IHspStaffBaseinfoDAO getHspStaffBaseinfoDAO() {
		return hspStaffBaseinfoDAO;
	}

	public void setHspStaffBaseinfoDAO(IHspStaffBaseinfoDAO hspStaffBaseinfoDAO) {
		this.hspStaffBaseinfoDAO = hspStaffBaseinfoDAO;
	}

	public IHspStaffBaseinfoMergeDAO getHspStaffBaseinfoMergeDAO() {
		return hspStaffBaseinfoMergeDAO;
	}

	public void setHspStaffBaseinfoMergeDAO(
			IHspStaffBaseinfoMergeDAO hspStaffBaseinfoMergeDAO) {
		this.hspStaffBaseinfoMergeDAO = hspStaffBaseinfoMergeDAO;
	}

	public int getCount(HspStaffBaseinfoMergeForm hsbmForm) {
		return this.hspStaffBaseinfoMergeDAO.getCount(hsbmForm.getIdHidden(),hsbmForm.getName(),hsbmForm.getCommConfigSexId(),
				hsbmForm.getBirthday(),hsbmForm.getIdNo(),hsbmForm.getCheckValue());
	}

	public void search(
			HspStaffBaseinfoMergeForm hsbmForm, int curCount, int pageSize) {
		String order = "";
		SimpleDateFormat sdf = new  SimpleDateFormat( "yyyy-MM-dd" );
		if(hsbmForm.getOrderNo().equals("1")){
			order = " a.empNo";
		}else if(hsbmForm.getOrderNo().equals("2")){
			order = " a.name";
		}else if(hsbmForm.getOrderNo().equals("3")){
			order = " a.commConfigSexId";
		}else if(hsbmForm.getOrderNo().equals("4")){
			order = " a.hspConfigBaseinfoId";
		}else if(hsbmForm.getOrderNo().equals("5")){
			order = " a.idNo";
		}else{
			order = " a.id";
		}
		if(hsbmForm.getAsc().equals("1")){
			order += " desc";
		}else{
			order += " asc";
		}
		List<?> voList = this.hspStaffBaseinfoMergeDAO.search(hsbmForm.getIdHidden(),hsbmForm.getName(),hsbmForm.getCommConfigSexId(),
				hsbmForm.getBirthday(),hsbmForm.getIdNo(),hsbmForm.getCheckValue(),order,curCount,pageSize);
		if(voList!=null&&voList.size()>0){
			int size = voList.size();
			String[] idArray = new String[size];
			String[] hspConfigBaseinfoIdArray = new String[size];
			String[] empNoArray = new String[size];
			String[] nameArray = new String[size];
			String[] idNoArray = new String[size];
			String[] birthdayArray = new String[size];
			String[] commConfigSexIdArray = new String[size];
			String[] commConfigSexNameArray = new String[size];
			for(int i = 0;i<size;i++){
				HspStaffBaseinfo vo = (HspStaffBaseinfo)voList.get(i);
				idArray[i] = trantNull(vo.getId());
				hspConfigBaseinfoIdArray[i] = trantNull(vo.getHspConfigBaseinfoId());
				empNoArray[i] = trantNull(vo.getEmpNo());
				nameArray[i] = trantNull(vo.getName());
				idNoArray[i] = trantNull(vo.getIdNo());
				if(vo.getBirthday()!=null){
					birthdayArray[i] = trantNull(sdf.format(vo.getBirthday()));
				}else{
					birthdayArray[i] = "";
				}
				
				commConfigSexIdArray[i] = trantNull(vo.getCommConfigSexId());
				String commConfigSexName = null;
				if(vo.getCommConfigSexId()!=null&&vo.getCommConfigSexId().trim().length()>0)
					commConfigSexName = this.commConfigSexDAO.getItemName(vo.getCommConfigSexId());
				commConfigSexNameArray[i] = trantNull(commConfigSexName);
			}
			hsbmForm.setIdArray(idArray);
			hsbmForm.setHspConfigBaseinfoIdArray(hspConfigBaseinfoIdArray);
			hsbmForm.setEmpNoArray(empNoArray);
			hsbmForm.setNameArray(nameArray);
			hsbmForm.setIdNoArray(idNoArray);
			hsbmForm.setBirthdayArray(birthdayArray);
			hsbmForm.setCommConfigSexIdArray(commConfigSexIdArray);
			hsbmForm.setCommConfigSexNameArray(commConfigSexNameArray);
		}
	}

	private String trantNull(String value) {
		if(value!=null&&value.trim().length()>0){
			return value;
		}
		return "";
	}

	public void mgInit(HspStaffBaseinfoMergeForm hsbmForm) {
		String[] checkValues = hsbmForm.getCheckValue().split(",");

		List<String>  cpcl = new ArrayList<String>();
		for(int i=0; i<checkValues.length;i++){
		  if(!checkValues[i].equals("")){
				cpcl.add(checkValues[i]);
		   }
		 }
      
		cpcl = this.removedup(cpcl);
		this.mgBegin(cpcl,hsbmForm);	
	}
    
	/**合并开始*/
	private void mgBegin(List<String> cpcl, HspStaffBaseinfoMergeForm hsbmForm) {
		List<String> list = new ArrayList<String>();
		for(int k=0;k<cpcl.size();k++){
			String id=String.valueOf(cpcl.get(k));
			String[] ids=id.split(":");
			String oldId=ids[0];
			String newId=ids[1];
			HspStaffBaseinfo oldVo = this.hspStaffBaseinfoMergeDAO.getVoById(oldId);
			if(list.size()==0){
				this.dealResult(newId,oldId);
			}	
			else{
				int m=0;
				for(int i=0;i<list.size();i++){
					if(oldId.equals(list.get(i))){
						break;
					}else{
						m++;
					}
				}
				if(m>0){
					this.dealResult(newId,oldId);
				}
			}
			list.add(oldId);
			this.saveMerge(newId, oldId, hsbmForm,oldVo);
		}
	}

	private void saveMerge(String newId, String oldId,
			HspStaffBaseinfoMergeForm hsbmForm,HspStaffBaseinfo oldVo) {
		HspStaffMergeLog vo = new HspStaffMergeLog();
		
		vo.setId(UUID.randomUUID().toString().replace("-", "")); 
		vo.setNewHspStaffBaseinfoId(newId);
		if(oldVo.getCommConfigNationalityId()!=null&&oldVo.getCommConfigNationalityId().trim().length()>0){
			vo.setUniversalId(oldVo.getCommConfigNationalityId());
		}
		
//		HspStaffBaseinfo newVo = this.hspStaffBaseinfoMergeDAO.getVoById(newId);
		vo.setOldHspStaffBaseinfoId(oldId);
		
		vo.setCreateDate(new Date());
		vo.setCreateUserId(hsbmForm.getCreateUserId());
		vo.setCreateUserName(hsbmForm.getCreateUserName());
		
		this.hspStaffBaseinfoMergeDAO.saveLog(vo);
		
	}

	private void dealResult(String newId,String oldId) {
		HspStaffBaseinfoForm hForm = new HspStaffBaseinfoForm();
		hForm.setId(oldId);
		this.delete(hForm);
		HspStaffIdList vo = this.hspStaffBaseinfoMergeDAO.getHspStaffIdListById(oldId);
		if(vo!=null&&vo.getId()!=null){
			vo.setHspStaffBaseinfoId(newId);
			this.hspStaffBaseinfoMergeDAO.updataHspStaffIdList(vo);
		}
	}

	/**去除重复ID*/
	private List<String> removedup(List<String>  cpcl){
		
	    for  (int  i = 0 ; i<cpcl.size()  - 1 ; i ++ )  {
          for  ( int  j  =  cpcl.size() - 1 ; j  >  i; j -- )  {
               if  (cpcl.get(j).equals(cpcl.get(i)))  {
            	   cpcl.remove(j);
                } 
            } 
         } 
         return cpcl;
	}

	public void init(HspStaffBaseinfoMergeForm hsbmForm) {
		List<?> sexList = this.commConfigSexDAO.findAll();
		int l = sexList.size();
		String[] sexIdArray = new String[l];
		String[] sexNameArray = new String[l];
		for(int i = 0;i<l;i++){
			CommConfigSex vo = (CommConfigSex) sexList.get(i);
			sexIdArray[i] = vo.getItemCode();
			sexNameArray[i] = vo.getItemName();
		}
		hsbmForm.setCommConfigSexIds(sexIdArray);
		hsbmForm.setCommConfigSexNames(sexNameArray);
	}
	
	public void delete(HspStaffBaseinfoForm hForm) {
		//删除注册码 SECURITY STAFF BASEINFO HSP STAFF BASEINFO 
		//
		HspStaffBaseinfo hsp = this.hspStaffBaseinfoDAO.findById(hForm.getId());
		String securityStaffBaseinfoAdd=HspInit.getProperty("SECURITY_STAFF_BASEINFO_ADD");
		if(securityStaffBaseinfoAdd.equals("true")){
		SecurityStaffBaseinfo	SecurityStaffBaseinfobean=this.hspStaffBaseinfoDAO.getSecurityStaffBaseinfoById(hForm.getId());
		if(SecurityStaffBaseinfobean!=null){
		String hspId = hsp.getId();
		SecurityStaffBaseinfo ssb = this.hspStaffBaseinfoDAO.getSecurityStaffBaseinfoById(hspId);
		String ssbId = ssb.getId();
		SecurityLicense sl = this.securityLicenseDAO.findBySecurityStaffBaseinfoId(ssbId);
		this.securityLicenseDAO.delete(sl);
		this.securityStaffBaseinfoDAO.delete(ssb);
		}
		}
		this.hspStaffBaseinfoDAO.delete(hsp);
	}
}
