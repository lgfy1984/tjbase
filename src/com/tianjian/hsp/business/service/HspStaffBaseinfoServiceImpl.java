package com.tianjian.hsp.business.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

import com.tianjian.comm.business.ICommConfigInputDictService;
import com.tianjian.comm.dao.ICommonDAO;
import com.tianjian.empi.bean.SecurityUserBaseinfo;
import com.tianjian.hsp.bean.HspConfigBaseinfo;
import com.tianjian.hsp.bean.HspStaffBaseinfo;
import com.tianjian.hsp.business.IHspStaffBaseinfoService;
import com.tianjian.hsp.dao.IHspConfigBaseinfoDAO;
import com.tianjian.hsp.dao.IHspStaffBaseinfoDAO;
import com.tianjian.hsp.struts.form.HspStaffBaseinfoForm;
import com.tianjian.hsp.struts.servlet.HspInit;
import com.tianjian.security.bean.SecurityLicense;
import com.tianjian.security.bean.SecurityStaffBaseinfo;
import com.tianjian.security.bean.SecuritySystemUsers;
import com.tianjian.security.dao.ISecurityLicenseDAO;
import com.tianjian.security.dao.ISecurityStaffBaseinfoDAO;
import com.tianjian.security.dao.ISecuritySystemUsersDAO;
import com.tianjian.security.struts.servlet.SecurityInit;
import com.tianjian.util.Converter;
import com.tianjian.util.ResourcesUtil;
import com.tianjian.util.comm.MD5;
import com.tianjian.util.comm.TJInit;
import com.tianjian.util.comm.UtilTrans;

public class HspStaffBaseinfoServiceImpl implements IHspStaffBaseinfoService {
	private static final Logger log = LogManager.getLogger(HspStaffBaseinfoServiceImpl.class);
	public IHspStaffBaseinfoDAO hspStaffBaseinfoDAO;
	public ISecurityStaffBaseinfoDAO securityStaffBaseinfoDAO;
	public IHspConfigBaseinfoDAO hspConfigBaseinfoDAO;
	private ICommConfigInputDictService commConfigInputDictService;
	private ISecurityLicenseDAO securityLicenseDAO;	 
	private ICommonDAO commonDAO;
	private ISecuritySystemUsersDAO securitySystemUsersDAO;
	
	public IHspStaffBaseinfoDAO getHspStaffBaseinfoDAO() {
		return hspStaffBaseinfoDAO;
	}

	public void setHspStaffBaseinfoDAO(IHspStaffBaseinfoDAO hspStaffBaseinfoDAO) {
		this.hspStaffBaseinfoDAO = hspStaffBaseinfoDAO;
	}


	public ISecurityStaffBaseinfoDAO getSecurityStaffBaseinfoDAO() {
		return securityStaffBaseinfoDAO;
	}

	public void setSecurityStaffBaseinfoDAO(
			ISecurityStaffBaseinfoDAO securityStaffBaseinfoDAO) {
		this.securityStaffBaseinfoDAO = securityStaffBaseinfoDAO;
	}

	public IHspConfigBaseinfoDAO getHspConfigBaseinfoDAO() {
		return hspConfigBaseinfoDAO;
	}

	public void setHspConfigBaseinfoDAO(
			IHspConfigBaseinfoDAO hspConfigBaseinfoDAO) {
		this.hspConfigBaseinfoDAO = hspConfigBaseinfoDAO;
	}

	public ICommConfigInputDictService getCommConfigInputDictService() {
		return commConfigInputDictService;
	}

	public void setCommConfigInputDictService(
			ICommConfigInputDictService commConfigInputDictService) {
		this.commConfigInputDictService = commConfigInputDictService;
	}

	public ISecurityLicenseDAO getSecurityLicenseDAO() {
		return securityLicenseDAO;
	}

	public void setSecurityLicenseDAO(ISecurityLicenseDAO securityLicenseDAO) {
		this.securityLicenseDAO = securityLicenseDAO;
	}
    
	public ICommonDAO getCommonDAO() {
		return commonDAO;
	}
	public void setCommonDAO(ICommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}
	public void save(HspStaffBaseinfoForm form) {
//		System.out.println("有 " + form.getHspConfigBaseinfoIdHidden() + " 有了");
		HspStaffBaseinfo data1 = new HspStaffBaseinfo();
		this.setData(form, data1);
		
		this.hspStaffBaseinfoDAO.save(data1);
		String securityStaffBaseinfoAdd=HspInit.getProperty("SECURITY_STAFF_BASEINFO_ADD");
		if(securityStaffBaseinfoAdd.equals("true")){
		SecurityStaffBaseinfo data2 = new SecurityStaffBaseinfo();
		this.setDateToSecurity(data1, data2);
		this.securityStaffBaseinfoDAO.save(data2);
		
		//保存了注册码,未对其进行操作
		String ssbId = data2.getId();
		SecurityLicense sl = new SecurityLicense();
		sl.setSecurityStaffBaseinfoId(ssbId);
		sl.setRegTime(new Date());
		String regCode = this.generateRegCode(5, 5);
		sl.setRegistCode(regCode);
		this.securityLicenseDAO.save(sl);
		}
	}

	
	//save security
	
	public void saveHspToSecurity(HspStaffBaseinfoForm form) {
//		System.out.println("有 " + form.getHspConfigBaseinfoIdHidden() + " 有了");
		HspStaffBaseinfo data1 = new HspStaffBaseinfo();
		
		data1=this.getHspStaffBaseinfoDAO().findById(form.getIdHidden());
	//    List<?>	list= this.commonDAO.findListByHql("from HspStaffBaseinfo h where h.hspConfigBaseinfoId='"+form.getIdHidden()+"'");
	//    data1=(HspStaffBaseinfo)list.get(0);
	    
	    //this.setData(form, data1);
		String securityStaffBaseinfoAdd=HspInit.getProperty("SECURITY_STAFF_BASEINFO_ADD");
		if(securityStaffBaseinfoAdd.equals("false")){
		SecurityStaffBaseinfo data2 = new SecurityStaffBaseinfo();
		
		this.setDateToSecurity(data1, data2);
		this.securityStaffBaseinfoDAO.save(data2);
		
		//保存了注册码,未对其进行操作
		String ssbId = data2.getId();
		SecurityLicense sl = new SecurityLicense();
		sl.setSecurityStaffBaseinfoId(ssbId);
		sl.setRegTime(new Date());
		String regCode = this.generateRegCode(5, 5);
		sl.setRegistCode(regCode);
		this.securityLicenseDAO.save(sl);
		SecuritySystemUsers  ssu = new SecuritySystemUsers();
        ssu.setSecurityStaffBaseinfoId(ssbId);
        ssu.setPasswd(MD5.toMD5(SecurityInit.getProperty("STAFF_PASSWORD")));
        securitySystemUsersDAO.save(ssu);
		}
	}
	
	
	
	public int getCount(String id, String hspId, String name, String idNo,String empNo,String commConfigNationalityId,String workCertificateNo,String commDictPublicCharId1,String commDictPublicCharId2,String commConfigPositiontitleId,String commConfigEmptitleId,String commDictPublicCharId3,String commConfigDegreeId,String commConfigDegreeLevelId,String commConfigProfessionId,String commConfigLocationId3,
			   String commConfigLocationTownId,String commClvId,String staffId,String townCode,String commConfigSexId,String staffHspId,String ageS,String ageE, Long isLocation) {
		
		return this.getHspStaffBaseinfoDAO().getCount(id, hspId, name, idNo, empNo, commConfigNationalityId, 
				workCertificateNo, commDictPublicCharId1, commDictPublicCharId2, commConfigPositiontitleId, 
				commConfigEmptitleId, commDictPublicCharId3, commConfigDegreeId, commConfigDegreeLevelId, 
				commConfigProfessionId,commConfigLocationId3,commConfigLocationTownId,commClvId, staffId, null,townCode,
				commConfigSexId,staffHspId,ageS,ageE, isLocation);
	}
	public int getCountAll(String id, String hspId, String name, String idNo,String empNo,String commConfigNationalityId,String workCertificateNo,String commDictPublicCharId1,String commDictPublicCharId2,String commConfigPositiontitleId,String commConfigEmptitleId,String commDictPublicCharId3,String commConfigDegreeId,String commConfigDegreeLevelId,String commConfigProfessionId,String staffId) {
		return hspStaffBaseinfoDAO.getCountAll(id, hspId, name, idNo, empNo, commConfigNationalityId, workCertificateNo, commDictPublicCharId1, commDictPublicCharId2, commConfigPositiontitleId, commConfigEmptitleId, commDictPublicCharId3, commConfigDegreeId, commConfigDegreeLevelId, commConfigProfessionId);
		
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

	public void getDetail(HspStaffBaseinfoForm hspStaffBaseinfoForm) {
		// 空
		//hspStaffBaseinfoForm.setHspConfigBaseinfoIdList((List<HspStaffBaseinfo>) this.hspStaffBaseinfoDAO.hspConfigBaseinfoIdList());
		hspStaffBaseinfoForm.setCommConfigSexIdList(this.hspStaffBaseinfoDAO.sexList());
		hspStaffBaseinfoForm.setCommDictPublicCharId1List(this.hspStaffBaseinfoDAO.commDictPublicCharId1List());
		hspStaffBaseinfoForm.setCommDictPublicCharId2List(this.hspStaffBaseinfoDAO.commDictPublicCharId2List());
		hspStaffBaseinfoForm.setCommConfigPositiontitleIdList(this.hspStaffBaseinfoDAO.commConfigPositiontitleIdList());
		hspStaffBaseinfoForm.setCommConfigEmptitleIdList(this.hspStaffBaseinfoDAO.commConfigEmptitleIdList());
		hspStaffBaseinfoForm.setCommDictPublicCharId3List(this.hspStaffBaseinfoDAO.commDictPublicCharId3List());
		hspStaffBaseinfoForm.setCommConfigDegreeIdList(this.hspStaffBaseinfoDAO.commConfigDegreeIdList());
		hspStaffBaseinfoForm.setCommConfigDegreeLevelIdList(this.hspStaffBaseinfoDAO.commConfigDegreeLevelIdList());
		hspStaffBaseinfoForm.setCommConfigProfessionIdList(this.hspStaffBaseinfoDAO.commConfigProfessionIdList());
		hspStaffBaseinfoForm.setCommConfigNationalityIdL(this.hspStaffBaseinfoDAO.commConfigNationalityIdList());
		
		String personnelCode = HspInit.getProperty("personnelCode");
		hspStaffBaseinfoForm.setPersonnelCode(personnelCode);
	}

	
	public void searchInit(HspStaffBaseinfoForm hspStaffBaseinfoForm) {
		this.getDetail(hspStaffBaseinfoForm);
		this.getDict(hspStaffBaseinfoForm);
	}
	
	private void getDict(HspStaffBaseinfoForm form) {
		
	}

	
	public void getSearch(HspStaffBaseinfoForm hForm, int curCount, int pageSize, HttpServletRequest request,String townCode,String ageS,String ageE) {

		SimpleDateFormat sdf  =   new  SimpleDateFormat( "yyyyMMdd" );
		String order = "";
		if(hForm.getOrderNo().equals("1")){
			order = " a.empNo";
		}else if(hForm.getOrderNo().equals("2")){
			order = " a.name";
		}else if(hForm.getOrderNo().equals("3")){
			order = " a.commConfigSexId";
		}else if(hForm.getOrderNo().equals("4")){
			order = " a.hspConfigBaseinfoId";
		}else if(hForm.getOrderNo().equals("5")){
			order = " a.idNo";
		}else{
			order = " a.id";
		}
		if(hForm.getAsc().equals("1")){
			order += " desc";
		}else{
			order += " asc";
		}
		
		List<?> list=this.hspStaffBaseinfoDAO.getData(hForm
				.getHspConfigBaseinfoId(), hForm.getName(), hForm.getIdNo(),
				hForm.getEmpNo(),hForm.getCommConfigNationalityId(),
				hForm.getWorkCertificateNo(),hForm.getCommDictPublicCharId1(),
				hForm.getCommDictPublicCharId2(),hForm.getCommConfigPositiontitleId(),
				hForm.getCommConfigEmptitleId(),hForm.getCommDictPublicCharId3(),
				hForm.getCommConfigDegreeId(),hForm.getCommConfigDegreeLevelId(),
				hForm.getCommConfigProfessionId(),hForm.getCommConfigLocationId3(),hForm.getCommConfigLocationTownId(),hForm.getCommClvId(),hForm.getStaffId(),
				order,curCount, pageSize,null,townCode,hForm.getCommConfigSexId(),hForm.getStaffHspId(),ageS,ageE, hForm.getIslocation());
		//准备校验
		//此方法获取所有操作员 操作员数量多时可能会出问题
//		 List<?>  secList = this.securityStaffBaseinfoDAO.getSecData();
		// List<?> hspList = this.hspStaffBaseinfoDAO.getHspData();
	 	
		if (list != null && list.size() > 0) {
			int listSize = list.size();
			String idList[] = new String[listSize];
			String isidNull[] = new String[listSize];
			String hspNameList[] = new String[listSize];
			String nameList[] = new String[listSize];
			String sexList[] = new String[listSize];
			String idNoList[] = new String[listSize];
			String empNoList []=  new String[listSize];
			String hspIdList [] = new String[listSize];
			String commSexIdList [] = new String[listSize];
			String dateOfBrith[] = new String[listSize];
//			String securityStaffBaseinfoId[] = new String[secList.size()];
//			
//			  for(int j=0;j<secList.size();j++){
//				  SecurityStaffBaseinfo sec= (SecurityStaffBaseinfo) secList.get(j);
//				  securityStaffBaseinfoId[j]=sec.getHspStaffBaseinfoId();
//			  }
			for (int i = 0; i < listSize; i++) {
				HspStaffBaseinfo hsb = (HspStaffBaseinfo) list.get(i);
				idList[i] = hsb.getId();
			//	SecurityStaffBaseinfo data=this.securityStaffBaseinfoDAO.findByStaffBsId(idList[i]);
				hspIdList[i] = hsb.getHspConfigBaseinfoId();
				commSexIdList[i] = hsb.getCommConfigSexId();
				Date dateString = hsb.getBirthday();
				if(hsb.getBirthday()!=null){
					dateOfBrith[i] = sdf.format(dateString);
				}else{
					dateOfBrith[i] ="";
				}
				
				String hspId = hsb.getHspConfigBaseinfoId();
				if (hspId != null) {
					hspNameList[i] = this.hspConfigBaseinfoDAO
							.getItemName(hspId);
				} else {
					hspNameList[i] = "";
				}


				if (hsb.getName() != null) {
					nameList[i] = hsb.getName();
				} else {
					nameList[i] = "";
				}

				if (hsb.getCommConfigSexId() != null && hsb.getCommConfigSexId().equals("1")) {
					String str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.common.man", request);
					sexList[i] = str;
				} else if (hsb.getCommConfigSexId() != null && hsb.getCommConfigSexId().equals("2")) {
					String str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.common.women", request);
					sexList[i] = str;
				}else if (hsb.getCommConfigSexId() != null && hsb.getCommConfigSexId().equals("0")) {
					String str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.common.unknownSex", request);
					sexList[i] = str;
				}else if (hsb.getCommConfigSexId() != null && hsb.getCommConfigSexId().equals("9")) {
					String str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.common.unDeclareSex", request);
					sexList[i] = str;
				} else {
					sexList[i] = "";
				}

				if (hsb.getIdNo() != null) {
					idNoList[i] = hsb.getIdNo();
				} else {
					idNoList[i] = "";
				}
				if (hsb.getEmpNo() != null) {
					empNoList[i] = hsb.getEmpNo();
				} else {
					empNoList[i] = "";
				}
				
			}
//			hForm.setSecurityStaffBaseinfoIdArray(securityStaffBaseinfoId);
			hForm.setIdsHiddenArray(idList);
			hForm.setIsIdNull(isidNull);
			hForm.setHspNameArray(hspNameList);
			hForm.setHspIdArray(hspIdList);
			hForm.setNameArray(nameList);
			hForm.setIdNoArray(idNoList);
			hForm.setSexArray(sexList);
			hForm.setEmpNoArray(empNoList);
			hForm.setCommSexIdArray(commSexIdList);
			hForm.setDateOfBirthArray(dateOfBrith);
//			hForm.setSecurityStaffBaseinfoIdL(securityStaffBaseinfoId);
//			hForm.setSecurityBaseInfoData((List<SecurityStaffBaseinfo>) secList);
 
		//	hForm.setHspConfigBaseinfoIdList((List<HspStaffBaseinfo>) hspList);
 			System.out.println("len is +++"+idList.length+"da");
 
		//	hForm.setHspConfigBaseinfoIdList((List<HspStaffBaseinfo>) hspList);
			
 
		}
		this.init(hForm);
	}
	public void getSearchAll(HspStaffBaseinfoForm hForm, int curCount, int pageSize,String staffId, HttpServletRequest request) {
		List<?> list=new ArrayList();
		list=hspStaffBaseinfoDAO.getDataAll(hForm.getId(), 
				hForm.getName(), hForm.getIdNo(), hForm.getEmpNo(), hForm.getCommConfigNationalityId(), hForm.getWorkCertificateNo(),
				hForm.getCommDictPublicCharId1(), hForm.getCommDictPublicCharId2(),
				hForm.getCommConfigPositiontitleId(), hForm.getCommConfigEmptitleId(),
				hForm.getCommDictPublicCharId3(), hForm.getCommConfigDegreeId(), 
				hForm.getCommConfigDegreeLevelId(), hForm.getCommConfigProfessionId(), curCount, pageSize);
				//准备校验
		
		 List<?>  secList = this.securityStaffBaseinfoDAO.getSecData();
		// List<?> hspList = this.hspStaffBaseinfoDAO.getHspData();
	 	
		if (list != null && list.size() > 0) {
			int listSize = list.size();
			String idList[] = new String[listSize];
			String isidNull[] = new String[listSize];
			String hspNameList[] = new String[listSize];
			String nameList[] = new String[listSize];
			String sexList[] = new String[listSize];
			String idNoList[] = new String[listSize];
			String empNoList []=  new String[listSize];
			String securityStaffBaseinfoId[] = new String[secList.size()];
			  for(int j=0;j<secList.size();j++){
				  SecurityStaffBaseinfo sec= (SecurityStaffBaseinfo) secList.get(j);
				  securityStaffBaseinfoId[j]=sec.getHspStaffBaseinfoId();
			  }
			for (int i = 0; i < listSize; i++) {
				HspStaffBaseinfo hsb = (HspStaffBaseinfo) list.get(i);
                
				idList[i] = hsb.getId();
			//	SecurityStaffBaseinfo data=this.securityStaffBaseinfoDAO.findByStaffBsId(idList[i]);
				 
				String hspId = hsb.getHspConfigBaseinfoId();
				if (hspId != null) {
					hspNameList[i] = this.hspConfigBaseinfoDAO
							.getItemName(hspId);
			//		System.out.print("第一条" + (i + 1) + ": ID 是 " + hspId
		//					+ " NAME 是 " + hspNameList[i]);
				} else {
					hspNameList[i] = "";
				}


				if (hsb.getName() != null) {
					nameList[i] = hsb.getName();
				} else {
					nameList[i] = "";
				}

				if (hsb.getCommConfigSexId() != null && hsb.getCommConfigSexId().equals("1")) {
					String str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.common.man", request);
					sexList[i] = str;
				} else if (hsb.getCommConfigSexId() != null && hsb.getCommConfigSexId().equals("2")) {
					String str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.common.women", request);
					sexList[i] = str;
				}else if (hsb.getCommConfigSexId() != null && hsb.getCommConfigSexId().equals("0")) {
					String str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.common.unknownSex", request);
					sexList[i] = str;
				}else if (hsb.getCommConfigSexId() != null && hsb.getCommConfigSexId().equals("9")) {
					String str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.common.unDeclareSex", request);
					sexList[i] = str;
				} else {
					sexList[i] = "";
				}

				if (hsb.getIdNo() != null) {
					idNoList[i] = hsb.getIdNo();
				} else {
					idNoList[i] = "";
				}
				if (hsb.getEmpNo() != null) {
					empNoList[i] = hsb.getEmpNo();
				} else {
					empNoList[i] = "";
				}
			}
			hForm.setSecurityStaffBaseinfoIdArray(securityStaffBaseinfoId);
			hForm.setIdsHiddenArray(idList);
			hForm.setIsIdNull(isidNull);
			hForm.setHspNameArray(hspNameList);
			hForm.setNameArray(nameList);
			hForm.setIdNoArray(idNoList);
			hForm.setSexArray(sexList);
			hForm.setEmpNoArray(empNoList);
//			hForm.setSecurityStaffBaseinfoIdL(securityStaffBaseinfoId);
//			hForm.setSecurityBaseInfoData((List<SecurityStaffBaseinfo>) secList);
 
		//	hForm.setHspConfigBaseinfoIdList((List<HspStaffBaseinfo>) hspList);
 			System.out.println("len is +++"+idList.length+"da");
 
		//	hForm.setHspConfigBaseinfoIdList((List<HspStaffBaseinfo>) hspList);
			
 
		}
		this.init(hForm);
	}
	
	public void showInit(HspStaffBaseinfoForm hspStaffBaseinfoForm) {
		init(hspStaffBaseinfoForm);
	}

	
	public void update(HspStaffBaseinfoForm hsbForm) {
		try{
//			System.out.println("123----"+hsbForm.getId()+"dddd");
			HspStaffBaseinfo hsb = this.hspStaffBaseinfoDAO.findById(hsbForm.getId());
			hsbForm.setCreateDate(UtilTrans.transDateToStringFull(hsb.getCreateDate()));
			this.setData(hsbForm, hsb);
			
//			System.out.println("123----");
			this.hspStaffBaseinfoDAO.update(hsb);
			String securityStaffBaseinfoAdd=HspInit.getProperty("SECURITY_STAFF_BASEINFO_ADD");
			if(securityStaffBaseinfoAdd.equals("true")){
			SecurityStaffBaseinfo ssb = this.hspStaffBaseinfoDAO.getSecurityStaffBaseinfoById(hsb.getId());
			this.setDateToSecurity(hsb, ssb);
			this.securityStaffBaseinfoDAO.update(ssb);
			}
			log.debug("Service public void update(HspStaffBaseinfoForm hsbForm) OK");
		}catch(Exception e){
			log.error("Service public void update(HspStaffBaseinfoForm hsbForm) error", e);
			e.printStackTrace();
		}
	}

	
	public void updateInit(HspStaffBaseinfoForm hsbForm) {
		HspStaffBaseinfo hsb = this.hspStaffBaseinfoDAO.findById(hsbForm
				.getId());
		
		this.setForm(hsb, hsbForm);
		
		init(hsbForm);
	}
	
	public void detail(HspStaffBaseinfoForm hsbForm) {
		HspStaffBaseinfo hsb = this.hspStaffBaseinfoDAO.findById(hsbForm
				.getId());
		
		this.setForm(hsb, hsbForm);
	}
	

	public void addInit(HspStaffBaseinfoForm hsbForm) {
		String hspId = hsbForm.getHspConfigBaseinfoId();
		String deptCode = hsbForm.getDeptCode();
		if(isNotNull(hspId)){
			hsbForm.setHspConfigBaseinfoName(this.getHspNameById(hspId));
			if(isNotNull(deptCode)){
				hsbForm.setDeptName(this.commonDAO.getValue("HspDeptBaseinfo", "deptName", new String[]{"id.deptCode", "id.hspConfigBaseinfoId"}, new String[]{deptCode, hspId}));
			}
		}
		
		String personnelCode = HspInit.getProperty("personnelCode");
		hsbForm.setPersonnelCode(personnelCode);
		if(personnelCode.equals("false")){
			List<?> empNo=commonDAO.findListBySql("select max(to_number((translate(SUBSTR(t.emp_no,length(t.emp_no) - 5,length(t.emp_no)),'0123456789' || SUBSTR(t.emp_no,length(t.emp_no) - 5,length(t.emp_no)),'0123456789')))) from hsp.hsp_staff_baseinfo t");
			try {
				for(int i=0;i<empNo.size();i++){
					Object obj=(Object)empNo.get(0);
					if(obj != null && obj.toString().trim().length() > 0){
						long value=Long.parseLong(obj.toString());
						hsbForm.setEmpNo((value+1)+"");
					}else{
						hsbForm.setEmpNo("1");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.init(hsbForm);
	}

	public void init(HspStaffBaseinfoForm hspStaffBaseinfoForm) {
		// 空
		this.getDetail(hspStaffBaseinfoForm);
	}

	public int checkData(String empNo) {
		HspStaffBaseinfo bean1=this.hspStaffBaseinfoDAO.getByEmpNo(empNo);
//		SecurityStaffBaseinfo bean2=this.hspStaffBaseinfoDAO.getByStaffCode(empNo);
		if(bean1==null){
			return 1;
		}else{
			return 0;
		}
	}

	public void setData(HspStaffBaseinfoForm form, HspStaffBaseinfo data) {
		/**ID*/
	//	data.setId(transNullToString(form.getId()));
		/**组织机构ID*/
		if(form.getHspConfigBaseinfoId() != null)
			data.setHspConfigBaseinfoId(transNullToString(form.getHspConfigBaseinfoId()));
		/**人员编码(卫生局统一)*/
		
		data.setEmpNo(transNullToString(form.getEmpNo()));

		/**姓名*/
		data.setName(transNullToString(form.getName()));
		/**证件号码码*/
		data.setIdNo(transNullToString(form.getIdNo()));
		/**出生日期*/
		data.setBirthday(this.setStringAsDate3(form.getBirthdayYear(), "", ""));
		/**性别*/
		data.setCommConfigSexId(transNullToString(form.getCommConfigSexId()));
		/**民族*/
		data.setCommConfigNationalityId(transNullToString(form.getCommConfigNationalityId()));
		/**参加工作日期*/
		data.setStartWorkDate(this.setStringAsDate2(
				form.getStartWorkDateYear(), ""));
		/**办公室电话*/
		data.setOfficeTel(transNullToString(form.getOfficeTel()));
		/**手机号码*/
		data.setMobileTel(transNullToString(form.getMobileTel()));
		/**从事专业类别*/
		data.setCommDictPublicCharId1(transNullToString(form.getCommDictPublicCharId1()));
		/**（ 医师/ 卫生监督员）执业证书编码*/
		data.setWorkCertificateNo(transNullToString(form.getWorkCertificateNo()));
		/**医师执业类别*/
		data.setCommDictPublicCharId2(transNullToString(form.getCommDictPublicCharId2()));
		/**行政职务*/
		data.setCommConfigPositiontitleId(transNullToString(form.getCommConfigPositiontitleId()));
		/**专业技术资格（评）*/
		data.setCommConfigEmptitleId(transNullToString(form.getCommConfigEmptitleId()));
		/**专业技术职务（聘）*/
		data.setCommDictPublicCharId3(transNullToString(form.getCommDictPublicCharId3()));
		/**学历*/
		data.setCommConfigDegreeId(transNullToString(form.getCommConfigDegreeId()));
		/**学位*/
		data.setCommConfigDegreeLevelId(transNullToString(form.getCommConfigDegreeLevelId()));
		/**所学专业*/
		data.setCommConfigProfessionId(transNullToString(form.getCommConfigProfessionId()));
		/**在位标志*/
		data.setIslocation(form.getIslocation());
		/**记录日期*/
//		private Date createDate;
		/**记录人员ID*/
		data.setCreateUserId(transNullToString(form.getCreateUserId()));
		/**记录人员名称*/
		data.setCreateUserName(transNullToString(form.getCreateUserName()));
		/**所属科室*/
		data.setRelatedDepartment(transNullToString(form.getDeptCode()));
		Date d=UtilTrans.transStringToDate(form.getCreateDate(),"yyyy-MM-dd HH:mm:ss");
	
		data.setCreateDate(UtilTrans.transStringToDate(form.getCreateDate(),"yyyy-MM-dd HH:mm:ss"));
	}

	public void setDateToSecurity(HspStaffBaseinfo data1,
			SecurityStaffBaseinfo data2) {
       
		// 医务人员编码
		if(data1.getId() != null)
			data2.setId(data1.getId());
		// 医疗机构
		if(data1.getHspConfigBaseinfoId() != null)
			data2.setHspConfigBaseinfoId(data1.getHspConfigBaseinfoId());
		// 姓名
		if(data1.getName() != null){
			data2.setName(data1.getName());
			String inputCodeStr = this.commConfigInputDictService.getInputCode(data1.getName());
			//输入码
			data2.setInputCode(inputCodeStr);
		}
		if(data1.getEmpNo()!=null){
			data2.setStaffCode(data1.getEmpNo());
		}
		
		// 性别
		if(data1.getCommConfigSexId() != null)
			data2.setCommConfigSexId(data1.getCommConfigSexId());
		// 出生日期
		if(data1.getBirthday() != null)
			data2.setDateOfBirth(data1.getBirthday());
		// 人员编码
		if(data1.getEmpNo()!=null){
		data2.setStaffCode(data1.getEmpNo());
		}
		// 身份证件号码
		if(data1.getIdNo() != null)
			data2.setIdNo(data1.getIdNo());
		//创造时间
		//if(){}
		
		
		// 所学专业
		// 专业技术职称
		// 职务
		// 文化程度
		// 联系电话
		String phone1 = data1.getMobileTel();
		String phone2 = data1.getOfficeTel();
		if (phone1 != null) {
			data2.setPhone(phone1);
		} else if (phone2 != null) {
			data2.setPhone(phone2);
		}
		// 在位标志
		if(data1.getIslocation() != null)
			data2.setIslocation(data1.getIslocation());
		// data2.setHspDeptBaseinfoId(data1.getDeptCode());
        if(data1.getCreateDate() != null){
        	data2.setCreateDate(data1.getCreateDate());
        }
        if(data1.getCreateUserId()!=null){
        	data2.setCreateUserId(data1.getCreateUserId());
        }
        if(data1.getCreateUserName()!=null){
        	data2.setCreateUserName(data1.getCreateUserName());
        }
        if(data1.getId()!=null){
        	data2.setHspStaffBaseinfoId(data1.getId());
        }
        
	}

	public void setForm(HspStaffBaseinfo data, HspStaffBaseinfoForm form) {
		SimpleDateFormat sdf1 = new SimpleDateFormat( "yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat( "yyyy-MM");
		// 取ID设置医院的名称
		if (data.getHspConfigBaseinfoId() != null) {
			String id = data.getHspConfigBaseinfoId();
			String name = this.hspConfigBaseinfoDAO.getItemName(id);
			form.setHspConfigBaseinfoName(name);
			form.setHspConfigBaseinfoId(id);
			String deptCode = data.getRelatedDepartment();
			if(isNotNull(deptCode)){
				String deptName = this.commonDAO.getValue("HspDeptBaseinfo", "deptName", new String[]{"id.deptCode", "id.hspConfigBaseinfoId"}, new String[]{deptCode, id});
				form.setDeptCode(deptCode);
				form.setDeptName(deptName);
			}
		}
		
		/**人员编码(卫生局统一)*/
		String personnelCode = HspInit.getProperty("personnelCode");
		form.setPersonnelCode(personnelCode);
		form.setEmpNo(transNullToString(data.getEmpNo()));	
		
		form.setName(transNullToString(data.getName()));
		form.setIdNo(transNullToString(data.getIdNo()));
		if (data.getBirthday() != null) {
			String date = sdf1.format(data.getBirthday());
			form.setBirthdayYear(date);
		}
		String sexId = transNullToString(data.getCommConfigSexId());
		form.setCommConfigSexId(sexId);
		if(sexId != null){
			String sexName = this.commonDAO.getNameById("CommConfigSex", "itemCode", "itemName", sexId);
			form.setCommConfigSexName(sexName);
		}
		String commConfigNationalityId = transNullToString(data.getCommConfigNationalityId());
		form.setCommConfigNationalityId(data.getCommConfigNationalityId());
		if (isNotNull(commConfigNationalityId)){
			form.setCommConfigNationalityName(this.commonDAO.getNameById("CommConfigNationality", "itemCode", "itemName", commConfigNationalityId));
		}
		if (data.getStartWorkDate() != null) {
			String date = sdf2.format(data.getStartWorkDate());
			form.setStartWorkDateYear(date);
		}
			form.setOfficeTel(transNullToString(data.getOfficeTel()));
			form.setMobileTel(transNullToString(data.getMobileTel()));
		/**从事专业类别*/
		String commDictPublicCharId1 = transNullToString(data.getCommDictPublicCharId1());
		form.setCommDictPublicCharId1(commDictPublicCharId1);
		if(isNotNull(commDictPublicCharId1)){
			form.setCommDictPublicCharName1(this.commonDAO.getNameById("CommDictPublicChar", "id", "dictValue", commDictPublicCharId1));
		}
		/**（ 医师/ 卫生监督员）执业证书编码*/
		form.setWorkCertificateNo(transNullToString(data.getWorkCertificateNo()));
		String commDictPublicCharId2 = transNullToString(data.getCommDictPublicCharId2());
		form.setCommDictPublicCharId2(commDictPublicCharId2);
		if(isNotNull(commDictPublicCharId2)){
			form.setCommDictPublicCharName2(this.commonDAO.getNameById("CommDictPublicChar", "id", "dictValue", commDictPublicCharId2));
		}
		form.setWorkCertificateNo(transNullToString(data.getWorkCertificateNo()));
		/**行政职务*/
		String commConfigPositiontitleId = transNullToString(data.getCommConfigPositiontitleId());
		form.setCommConfigPositiontitleId(commConfigPositiontitleId);
		if(isNotNull(commConfigPositiontitleId)){
			form.setCommConfigPositiontitleName(this.commonDAO.getNameById("CommConfigPositiontitle", "itemCode", "itemName", commConfigPositiontitleId));
		}
		/**专业技术资格（评）*/
		String commConfigEmptitleId = transNullToString(data.getCommConfigEmptitleId());
		form.setCommConfigEmptitleId(commConfigEmptitleId);
		if(isNotNull(commConfigEmptitleId)){
			form.setCommConfigEmptitleName(this.commonDAO.getNameById("CommConfigEmptitle", "itemCode", "itemName", commConfigEmptitleId));
		}
		/**专业技术职务（聘）*/
		String commDictPublicCharId3 = transNullToString(data.getCommDictPublicCharId3());
		form.setCommDictPublicCharId3(commDictPublicCharId3);
		if(isNotNull(commDictPublicCharId3)){
			form.setCommDictPublicCharName3(this.commonDAO.getNameById("CommDictPublicChar", "id", "dictValue", commDictPublicCharId3));
		}
		/**学历*/
		String commConfigDegreeId = transNullToString(data.getCommConfigDegreeId());
		form.setCommConfigDegreeId(commConfigDegreeId);
		if(isNotNull(commConfigDegreeId)){
			form.setCommConfigDegreeName(this.commonDAO.getNameById("CommConfigDegree", "itemCode", "itemName", commConfigDegreeId));
		}
		/**学位*/
		String commConfigDegreeLevelId = transNullToString(data.getCommConfigDegreeLevelId());
		form.setCommConfigDegreeLevelId(commConfigDegreeLevelId);
		if(isNotNull(commConfigDegreeLevelId)){
			form.setCommConfigDegreeLevelName(this.commonDAO.getNameById("CommConfigDegreeLevel", "itemCode", "itemName", commConfigDegreeLevelId));
		}
		/**所学专业*/
		String commConfigProfessionId = transNullToString(data.getCommConfigProfessionId());
		form.setCommConfigProfessionId(commConfigProfessionId);
		if(isNotNull(commConfigProfessionId)){
			form.setCommConfigProfessionName(this.commonDAO.getNameById("CommConfigProfession", "itemCode", "itemName", commConfigProfessionId));
		}
		/**在位标志*/
		form.setIslocation(data.getIslocation());
		/**记录日期*/
//		private Date createDate;
		/**记录人员ID*/
		form.setCreateUserId(transNullToString(data.getCreateUserId()));
		/**记录人员名称*/
		form.setCreateUserName(transNullToString(data.getCreateUserName()));
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			Date createDate = data.getCreateDate();
			if(createDate != null){
				String dateStr = sdf.format(createDate);
				form.setCreateDate(dateStr);
				form.setYear(dateStr.substring(0, 4));
			    form.setMonth(dateStr.substring(5,7));
				form.setDay(dateStr.substring(8,10));
			} 
		} catch (RuntimeException e) { 
			e.printStackTrace();
		} 
	}

	private final static DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
	private final static DateFormat df2 = new SimpleDateFormat("yyyy-MM");

	/** 将字符串的年月日转化为日期类型 */
	public Date setStringAsDate3(String year, String month, String day) {
//		if (year.trim().equals("") || year == null || month.trim().equals("")
//				|| month == null || day.trim().equals("") || day == null)
			if (year.trim().equals("") || year == null)
			return new Date();
		String dateStr = year.trim() + "-" + month.trim() + "-" + day.trim();
		Date returnDate = new Date();
		df1.setLenient(false);// 这个的功能是不自动增加月到年，日到月，如不把1996-13-3 转换为1997-1-3
		try {
			returnDate = df1.parse(dateStr);
			return returnDate;
		} catch (Exception e) {
			e.printStackTrace();
			return returnDate;
		}
	}

	/** 将字符串的年月转化为日期类型 */
	public Date setStringAsDate2(String year, String month) {
		if (year.trim().equals("") || year == null )
			return new Date();
		String dateStr = year.trim() + "-" + month.trim();
		Date returnDate = new Date();
		df2.setLenient(false);// 这个的功能是不自动增加月到年，日到月，如不把1936-15 转换为1937-3
		try {
			returnDate = df2.parse(dateStr);
			return returnDate;
		} catch (Exception e) {
			e.printStackTrace();
			return returnDate;
		}
	}

	
	
	/*生成医务人员许可证注册码方法*/
	private String generateRegCode(int zushu, int weishu){
		String temp_l="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuffer reg_code = new StringBuffer();
		reg_code.append("");
		for(int i=0; i<zushu; i++){
			String temp = "";
			Random random = new Random();
			for(int j=0; j<weishu; j++)
				temp += temp_l.charAt(Math.abs(random.nextInt())%(26+26+10-1));			
			reg_code.append(temp);
			if(i != zushu-1)
				reg_code.append("-");
		}		
		return reg_code.toString().toUpperCase();
	}
	
	public String transNullToString(Object obj) {
		String temp = "";
		if (obj != null) {
			temp = String.valueOf(obj).trim();
		}
		return temp;
	}
	
	
	
	
	public List<?> getAjaxDept(String hspId, String flag, String input) {
		return this.hspStaffBaseinfoDAO.getAjaxDept(hspId, flag, input);
	}
	
	// 民族
	public List<?> getAjaxNationality(String flag, String input) {
		return this.hspStaffBaseinfoDAO.getAjaxNationality(flag, input);
	}

	/**处理弹出层子画面*/
	@SuppressWarnings("unchecked")
	public String getXml(String flag,String inputCode,String hspType,String staffId,HttpServletRequest request){
//		List<?> hspId=hspStaffBaseinfoDAO.getHsp(staffId);
//		List<?> list=new ArrayList();
//		if(hspId!=null&&hspId.size()>0){
//			String hspConfigBaseInfo1="",hspConfigBaseInfo2="",hspConfigBaseInfo3="",hspConfigBaseInfo4="",hspConfigBaseInfo5="";
//			for (int i = 0; i < hspId.size(); i++) {
//				Object[] obj = (Object[]) hspId.get(0);
//				HspConfigBaseinfo hsp = (HspConfigBaseinfo) obj[0];
//				hspConfigBaseInfo1 = transNullToString(hsp.getHspConfigBaseinfoId1());
//				hspConfigBaseInfo2 =transNullToString( hsp.getHspConfigBaseinfoId2());
//				hspConfigBaseInfo3 = transNullToString(hsp.getHspConfigBaseinfoId3());
//				hspConfigBaseInfo4 = transNullToString(hsp.getHspConfigBaseinfoId4());
//				hspConfigBaseInfo5 = transNullToString(hsp.getHspConfigBaseinfoId5());
//				if (!hspConfigBaseInfo1.equals("")) {
//					list = hspStaffBaseinfoDAO.findHspList(flag, inputCode,
//							hspType,"", "hspConfigBaseinfoId1",
//							Integer.valueOf(CommInit
//									.getProperty("CURRECORD_TANCHUCENG")),
//							CommInit.getPageSize("PAGE_SIZE_TANCHUCENG"));
//				}
//				else if (!hspConfigBaseInfo2.equals("")) {
//					list = hspStaffBaseinfoDAO.findHspList(flag, inputCode,
//							hspType, hsp.getId(),"hspConfigBaseinfoId1",
//							Integer.valueOf(CommInit
//									.getProperty("CURRECORD_TANCHUCENG")),
//							CommInit.getPageSize("PAGE_SIZE_TANCHUCENG"));
//				}
//				else if (!hspConfigBaseInfo3.equals("")) {
//					list = hspStaffBaseinfoDAO.findHspList(flag, inputCode,
//							hspType, hsp.getId(), "hspConfigBaseinfoId2", 
//							Integer.valueOf(CommInit
//									.getProperty("CURRECORD_TANCHUCENG")),
//							CommInit.getPageSize("PAGE_SIZE_TANCHUCENG"));
//				}
//				else if (!hspConfigBaseInfo4.equals("")) {
//					list = hspStaffBaseinfoDAO.findHspList(flag, inputCode,
//							hspType, hsp.getId(),"hspConfigBaseinfoId3",  
//							Integer.valueOf(CommInit
//									.getProperty("CURRECORD_TANCHUCENG")),
//							CommInit.getPageSize("PAGE_SIZE_TANCHUCENG"));
//				}
//				else if (!hspConfigBaseInfo5.equals("")) {
//					list = hspStaffBaseinfoDAO.findHspList(flag, inputCode,
//							hspType,  hsp.getId(),"hspConfigBaseinfoId4",
//							Integer.valueOf(CommInit
//									.getProperty("CURRECORD_TANCHUCENG")),
//							CommInit.getPageSize("PAGE_SIZE_TANCHUCENG"));
//				}
//			}
//		}
		ServletContext application = request.getSession().getServletContext();
		List<?> list=hspStaffBaseinfoDAO.getHsp(flag, inputCode, Integer.valueOf((String)application.getAttribute("hsp.CURRECORD_TANCHUCENG")), Integer.valueOf((String)application.getAttribute("hsp.PAGE_SIZE_TANCHUCENG")));
		StringBuffer buffer = new StringBuffer();
		
//		list = hspStaffBaseinfoDAO.findHspList(flag, inputCode, hspType,id1,id2,id3,id4,id5,
//				Integer.valueOf(CommInit.getProperty("CURRECORD_TANCHUCENG")),
//				CommInit.getPageSize("PAGE_SIZE_TANCHUCENG"));
		buffer.append("<response>");
		for(int i =0;i<list.size();i++){
			HspConfigBaseinfo charbean = (HspConfigBaseinfo)list.get(i);
			 //-----以下xml标签不要改变--------------------------------------------------------------
			buffer.append("<ress>");
			buffer.append("<resInputCode>" + charbean.getInputCode() + "</resInputCode>");
			buffer.append("<resItemCode>" + Converter.toBlank(charbean.getItemCode()) + "</resItemCode>");
			buffer.append("<resItemName>" + charbean.getItemName() + "</resItemName>");
			buffer.append("<resItemId>" + charbean.getId() + "</resItemId>");
			
			
			buffer.append("</ress>");
		}
		buffer.append("</response>");
		
		return buffer.toString();
	}

	
	public HspStaffBaseinfo getStaffByUserId(String userId) {
		return this.hspStaffBaseinfoDAO.getStaffByUserId(userId);
	}
	
	public String elsImport(InputStream inputstream, HttpServletRequest request) {
		StringBuffer msg = new StringBuffer();
		HSSFWorkbook workbook=null;
		try{
			workbook = new HSSFWorkbook(inputstream);
			HSSFSheet sheet = null;//工作表变量
			//遍历该表格中所有的工作表,i表示工作表的数量,getNumberOfSheets表示工作表的总数
			for(int i=0; i<workbook.getNumberOfSheets(); i++){
				sheet = workbook.getSheetAt(i);
				//遍历该行所有的行,j表示行数,getPhysicalNumberOfRows行的总数
				for(int j=3; j<sheet.getPhysicalNumberOfRows(); j++){
					HSSFRow sheetrows = sheet.getRow(j);
					String hspConfigBaseinfoName = "";//组织机构--原始值1
					String hspConfigBaseinfoId = "";//组织机构ID--过渡值
					String empNo = "";//人员编码--原始值2
					String name = "";//姓名--过渡值3
					String commConfigSexName = "";//性别--原始值4
					String commConfigSexId = "";//性别--过渡值
					//String commConfigPositiontitleName = "";//行政职务--原始值5
					//String commConfigPositiontitleId = "";//行政职务--过渡值
					if(sheetrows!=null){
						empNo = Converter.toBlank(Converter.getValue(sheetrows.getCell((short)0)));
						name = Converter.toBlank(Converter.getValue(sheetrows.getCell((short)1)));
						commConfigSexName = Converter.toBlank(Converter.getValue(sheetrows.getCell((short)2)));
						hspConfigBaseinfoName = Converter.toBlank(Converter.getValue(sheetrows.getCell((short)3)));
					    //commConfigPositiontitleName = Converter.toBlank(Converter.getValue(sheetrows.getCell((short)4)));

					    if(name!=null && name.trim().length()<=0){
					    	String str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.hspStaffBaseinfoServiceImpl.warn8", request);
								str = MessageFormat.format(str, (j-2));
							msg.append(str);
						}else if(this.getCommonDAO().findById("HspStaffBaseinfo", "empNo", Converter.toBlank(empNo).trim())!=null){
							String str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.common.log2", request);
							str = MessageFormat.format(str, (j-2));
							msg.append(str);
						}else if(hspConfigBaseinfoName==null || hspConfigBaseinfoName.trim().length()<=0){
							String str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.hspConfigBaseinfoServiceImpl.log5", request);
							str = MessageFormat.format(str, (j-2));
							msg.append(str);
						}else{
							hspConfigBaseinfoId = Converter.toBlank(this.getCommonDAO().getNameById("HspConfigBaseinfo", "itemName", "id", hspConfigBaseinfoName));							
							commConfigSexId = Converter.toBlank(this.getCommonDAO().getNameById("CommConfigSex", "itemName", "itemCode", commConfigSexName));
							//commConfigPositiontitleId = Converter.toBlank(this.getCommonDAO().getNameById("CommConfigPositiontitle", "itemName", "itemCode", commConfigPositiontitleName));
							if(hspConfigBaseinfoId==null || hspConfigBaseinfoId.trim().length()<=0){
								String str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.common.log3", request);
								str = MessageFormat.format(str, (j-2));
								msg.append(str);
							}else{
								HspStaffBaseinfo data = new HspStaffBaseinfo();
								data.setEmpNo(empNo);
								data.setCommConfigSexId(commConfigSexId);
								data.setName(name);
								data.setHspConfigBaseinfoId(hspConfigBaseinfoId);
								//data.setCommConfigPositiontitleId(commConfigPositiontitleId);
								try{
									this.getCommonDAO().save(data);
								}catch(Exception e){
									String str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.common.log1", request);
									str = MessageFormat.format(str, (j-2));
									msg.append(str);
								}
							}
						}
					}
				}
			}
		}catch (IOException e) {
			HspInit.println(e);
			String str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.importFileReadFail", request);
			msg.append(str);
		}  
		return msg.toString();
	}
	private List<?> getList(HspStaffBaseinfoForm hForm){
		List<?> hspId1=hspStaffBaseinfoDAO.getHsp(hForm.getStaffId());
		return hspId1;
	}
	
	public HSSFWorkbook getExcel(HspStaffBaseinfoForm form, String townCode, String ageS, String ageE, String fileName, HttpServletRequest request) {
		/*取出导出到EXCEL的实际数据*/
		String order = "";
		if(form.getOrderNo().equals("1")){
			order = " a.empNo";
		}else if(form.getOrderNo().equals("2")){
			order = " a.name";
		}else if(form.getOrderNo().equals("3")){
			order = " a.commConfigSexId";
		}else if(form.getOrderNo().equals("4")){
			order = " a.hspConfigBaseinfoId";
		}else if(form.getOrderNo().equals("5")){
			order = " a.idNo";
		}else{
			order = " a.id";
		}
		if(form.getAsc().equals("1")){
			order += " desc";
		}else{
			order += " asc";
		}
		List<?> list = this.hspStaffBaseinfoDAO.getData(form
				.getHspConfigBaseinfoId(), form.getName(), form.getIdNo(),
				form.getEmpNo(),form.getCommConfigNationalityId(),
				form.getWorkCertificateNo(),form.getCommDictPublicCharId1(),
				form.getCommDictPublicCharId2(),form.getCommConfigPositiontitleId(),
				form.getCommConfigEmptitleId(),form.getCommDictPublicCharId3(),
				form.getCommConfigDegreeId(),form.getCommConfigDegreeLevelId(),
				form.getCommConfigProfessionId(),form.getCommConfigLocationId3(),form.getCommConfigLocationTownId(),form.getCommClvId(),form.getStaffId(),
				order,0, Integer.MAX_VALUE,null,townCode,form.getCommConfigSexId(),form.getStaffHspId(),ageS,ageE, form.getIslocation());

		/*导出到EXCEL*/
		//申明工作簿的第一张工作表的名字(目前不能中文)
		String table_name = "sheet1";
		table_name = Converter.getUnicode(table_name,"gb2312");

		//创建对工作表的引用。		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(table_name);

		int Header_number = 0;  //表头开始行
		int Title_number  = 1;   //标题开始行
		int Overall_Number= 4;  //总列数

		HSSFRow row  =null;
		HSSFCell cell=null;
		String text = ""; //写入单元格的值


		//定义表格样式
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFFont.COLOR_NORMAL); 
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		HSSFCellStyle cellStyle= workbook.createCellStyle();
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
		cellStyle.setBorderBottom((short)1);
		cellStyle.setBorderLeft((short)1);
		cellStyle.setBorderRight((short)1);
		cellStyle.setBorderTop((short)1);
		HSSFCellStyle HeaderStyle= workbook.createCellStyle();
		HeaderStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HeaderStyle.setFont(font);  
		HeaderStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
		HeaderStyle.setBorderBottom((short)1);
		HeaderStyle.setBorderLeft((short)1);
		HeaderStyle.setBorderRight((short)1);
		HeaderStyle.setBorderTop((short)1);

		//插入标题
		row = sheet.createRow((short)Header_number);
		cell = row.createCell((short)0);
		row.setHeight((short)100);
		row.setHeightInPoints((float)30);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(HeaderStyle);
		String str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.hspStaffBaseinfoServiceImpl.userInfo", request);
		text = Converter.getUnicode(str,"gb2312");
		cell.setCellValue(text);  
		sheet.addMergedRegion(new Region(Header_number,(short)(0), Header_number,(short)(Overall_Number-1)));
		//插入列标题
		row = sheet.createRow((short)Title_number);
		row.setHeight((short)585);
		cell = row.createCell((short)0);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(cellStyle);
  	str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.common.createTime", request);
		text = str + ":" +new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		text = Converter.getUnicode(text,"gb2312");
		cell.setCellValue(text);

		for(int i=1; i<Overall_Number; i++){
			cell = row.createCell((short)i);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellStyle(cellStyle);
			text = "";
			text = Converter.getUnicode(text,"gb2312");
			cell.setCellValue(text);
		}
		sheet.addMergedRegion(new Region(Title_number,(short)(0), Title_number,(short)(Overall_Number-1)));

		row = sheet.createRow((short)2);
		row.setHeight((short)585);
		
  	str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.hspStaffBaseinfoServiceImpl.userId", request);
		Converter.setCellText(row, cell, 0, str, cellStyle);
		str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.hspStaffBaseinfoServiceImpl.name", request);
		Converter.setCellText(row, cell, 1, str, cellStyle);
		str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.hspStaffBaseinfoServiceImpl.sex", request);
		Converter.setCellText(row, cell, 2, str, cellStyle);
		str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.common.itemName", request);
		Converter.setCellText(row, cell, 3, str, cellStyle);		

		if(list!=null && list.size()>0){
			for(int i=0; i<list.size(); i++){
				row = sheet.createRow((short)(i+3));
				HspStaffBaseinfo bean =(HspStaffBaseinfo) list.get(i);			
				Converter.setCellText(row, cell, 0, Converter.toBlank(bean.getEmpNo()).trim(), cellStyle);				
				Converter.setCellText(row, cell, 1, Converter.toBlank(bean.getName()).trim(), cellStyle);
				Converter.setCellText(row, cell, 2, Converter.toBlank(this.getCommonDAO().getNameById("CommConfigSex", "itemCode", "itemName", Converter.toBlank(bean.getCommConfigSexId()))).trim(), cellStyle);
				Converter.setCellText(row, cell, 3, Converter.toBlank(this.getCommonDAO().getNameById("HspConfigBaseinfo", "id", "itemName", Converter.toBlank(bean.getHspConfigBaseinfoId()))).trim(), cellStyle);
			}
		}
		return workbook;
	}
	//国籍
	
	public List<?> getAjaxCountry(String flag, String input) {
		return this.hspStaffBaseinfoDAO.getAjaxCountry(flag, input);
	}

	
	public List<?> getAjaxRelationship(String flag, String input) {
		return this.hspStaffBaseinfoDAO.getAjaxRelationship(flag, input);
	}

	
	public List<?> getPeoples(String hspConfigBaseInfoId, String flag,
			String input) {
		HspConfigBaseinfo data = this.getHspConfigBaseinfoDAO().getById(hspConfigBaseInfoId);
		String hspConfigId = "";
		if(TJInit.getProperty("classFlag").trim().equals("1")){
			hspConfigId =data.getHspConfigBaseinfoId3();
    	}else{
    		hspConfigId =data.getHspConfigBaseinfoId2();
    	}
		
		return this.hspStaffBaseinfoDAO.getPeoples(hspConfigId, flag, input);
	}

	
	public HspStaffBaseinfo findById(String id) {
		return this.hspStaffBaseinfoDAO.findById(id);
	}

	
	public List<?> getAllPeoples(String flag, String input) {
		return this.hspStaffBaseinfoDAO.getAllPeoples(flag, input);
	}


	
	public void dataToForm(SecurityUserBaseinfo securityUserBaseinfo,
			HspStaffBaseinfoForm form) {
		form.setName(this.transNullToString(securityUserBaseinfo.getName()));
		if(securityUserBaseinfo.getCommConfigIdTypeId().equals("01")){
			form.setIdNo(this.transNullToString(securityUserBaseinfo.getIdNo()));
		}
		if(securityUserBaseinfo.getDateOfBirth().toString()!=null){
			String birthday=securityUserBaseinfo.getDateOfBirth().toString();
			form.setBirthdayYear(birthday.substring(0,4));
			form.setBirthdayMonth(birthday.substring(5,7));
			form.setBirthdayDay(birthday.substring(8,10));
		}
		form.setCommConfigSexId(this.transNullToString(securityUserBaseinfo.getCommConfigSexId()));
		form.setCommConfigNationalityId(this.transNullToString(securityUserBaseinfo.getCommConfigNationalityId()));
		form.setCommConfigNationalityName(this.transNullToString(securityUserBaseinfo.getCommConfigNationalityName()));
		form.setMobileTel(this.transNullToString(securityUserBaseinfo.getMobileTel()));
	}

	
	public SecurityUserBaseinfo findOneById(String id) {
		return (SecurityUserBaseinfo)commonDAO.findById("SecurityUserBaseinfo", "id", id);
	}

	public ISecuritySystemUsersDAO getSecuritySystemUsersDAO() {
		return securitySystemUsersDAO;
	}

	public void setSecuritySystemUsersDAO(
			ISecuritySystemUsersDAO securitySystemUsersDAO) {
		this.securitySystemUsersDAO = securitySystemUsersDAO;
	}

	
	public String getBaseInfo(String flag, String input,String saffid, HttpServletRequest request) {
		ServletContext application = request.getSession().getServletContext();
		String classFlag = request.getParameter("classFlag");
		List<?> list=this.hspConfigBaseinfoDAO.findHspList(flag, input, "", saffid, Integer.valueOf((String)application.getAttribute("hsp.CURRECORD_TANCHUCENG")).intValue(), Integer.valueOf((String)application.getAttribute("hsp.PAGE_SIZE_TANCHUCENG")).intValue());
		StringBuffer buffer=new StringBuffer();
		buffer.append("<response>");
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				HspConfigBaseinfo info=(HspConfigBaseinfo)list.get(i);
				buffer.append("<ress>");
				buffer.append("<resInputCode>" +info.getInputCode()+ "</resInputCode>");
				buffer.append("<resItemCode>" + info.getItemCode()+ "</resItemCode>");
				buffer.append("<resItemName>"+ info.getItemName()+ "</resItemName>");
				buffer.append("<resItemId>" + info.getId() + "</resItemId>");
				buffer.append("</ress>");
			}
		}
		buffer.append("</response>");
		return buffer.toString();
	}

	public String getHspNameById(String hspId) {
		return  this.hspConfigBaseinfoDAO.getItemName(hspId);
	}

	private boolean isNull(String temp){
		return temp == null || temp.trim().length() <= 0;
	}
	
	private boolean isNotNull(String temp){
		return temp != null && temp.trim().length() > 0;
	}

}
