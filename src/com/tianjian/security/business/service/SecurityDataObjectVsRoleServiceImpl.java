package com.tianjian.security.business.service;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.tianjian.comm.bean.CommConfigLocation;
import com.tianjian.comm.bean.CommConfigLocationTown;
import com.tianjian.comm.bean.CommConfigLocationVillage;
import com.tianjian.comm.dao.ICommConfigLocationDAO;
import com.tianjian.comm.dao.ICommonDAO;
import com.tianjian.comm.struts.servlet.CommInit;
import com.tianjian.security.bean.SecurityDataObjectType;
import com.tianjian.security.bean.SecurityDataObjectVsRoles;
import com.tianjian.security.bean.SecurityStaffBaseinfo;
import com.tianjian.security.business.ISecurityDataObjectVsRoleService;
import com.tianjian.security.dao.ISecurityDataObjectVsRoleServiceDAO;
import com.tianjian.security.struts.form.SecurityDataObjectVsRoleForm;
import com.tianjian.util.Converter;

public class SecurityDataObjectVsRoleServiceImpl implements
		ISecurityDataObjectVsRoleService {
	private ISecurityDataObjectVsRoleServiceDAO  securityDataObjectVsRoleServiceDAO;
	private ICommonDAO commondao;

	public ISecurityDataObjectVsRoleServiceDAO getSecurityDataObjectVsRoleServiceDAO() {
		return securityDataObjectVsRoleServiceDAO;
	}

	public void setSecurityDataObjectVsRoleServiceDAO(
			ISecurityDataObjectVsRoleServiceDAO securityDataObjectVsRoleServiceDAO) {
		this.securityDataObjectVsRoleServiceDAO = securityDataObjectVsRoleServiceDAO;
	}

	
	public int getCount(SecurityDataObjectVsRoleForm form,String id, String securityStaffBaseInfo, String sdotId) {
//		SecurityDataObjectType type=securityDataObjectVsRoleServiceDAO.getRoles(sdotId);
		form.setSdoIdName(sdotId);
		return securityDataObjectVsRoleServiceDAO.getCount(transNullToString(form.getStaffCode()),transNullToString(form.getHspConfigBaseinfoId()),
				transNullToString(id), transNullToString(form.getSecurityStaffBaseInfo()), transNullToString(form.getSdoValue()),transNullToString(form.getInputCode()));
	}

	
	public void getSearch(SecurityDataObjectVsRoleForm form, int count,
			int pageSize) {
		String order = "";
		if (form.getOrderNo().equals("1")) {
			order = " s.staff_Code ";
		} else if (form.getOrderNo().equals("2")) {
			order = " s.name ";
		} else if (form.getOrderNo().equals("3")) {
			order = " m.item_Name ";
		} else if (form.getOrderNo().equals("4")) {
				order = " t.sdot_id ";
		} else if (form.getOrderNo().equals("5")){
			order = " t.sdo_value ";
		}else{
			order = "t.id";
		}
		if (form.getAsc().equals("1")) {
			order += " desc";
		} else {
			order += " asc";
		}
//		SecurityDataObjectType type=securityDataObjectVsRoleServiceDAO.getRoles(form.getSdoIdName());
		List<?> list=securityDataObjectVsRoleServiceDAO.getDate(transNullToString(form.getStaffCode()),transNullToString(form.getHspConfigBaseinfoId()),
									transNullToString(form.getId()), transNullToString(form.getSecurityStaffBaseInfo()), transNullToString(form.getSdoValue()),
									transNullToString(form.getInputCode()), count, pageSize, order);
		if(list!=null&&list.size()>0){
			String[] idList=new String[list.size()];
			String[] sList=new String[list.size()];
			String[] stoList=new String[list.size()];
			String[] stoValueList=new String[list.size()];
			String[] staffCodes = new String[list.size()];
			String[] hspConfigs = new String[list.size()];
			for(int i=0;i<list.size();i++){
				Object[] obj=(Object[])list.get(i);
				idList[i]=transNullToString(obj[0]);
				SecurityStaffBaseinfo infos = securityDataObjectVsRoleServiceDAO.getId(transNullToString(obj[1]));
				sList[i]=transNullToString(infos!=null?infos.getName():"");
				staffCodes[i]=this.transNullToString(infos!=null?infos.getStaffCode():"");
				SecurityDataObjectType types=securityDataObjectVsRoleServiceDAO.getRolesName(transNullToString(obj[2]));
				stoList[i]=transNullToString(types!=null?types.getDataObjectTypeName():"");
				CommConfigLocation location=securityDataObjectVsRoleServiceDAO.getLocationName(transNullToString(obj[3]));
				if(location!=null){
					stoValueList[i]=transNullToString(transNullToString(location.getItemName()));
				}else{
					CommConfigLocationTown locationTown=securityDataObjectVsRoleServiceDAO.getLocationTown(transNullToString(obj[3]));
					if(locationTown!=null){
						stoValueList[i]=transNullToString(transNullToString(locationTown.getItemName()));
					}else{
						CommConfigLocationVillage locationVillage=securityDataObjectVsRoleServiceDAO.getVillage(transNullToString(obj[3]));
						if(locationVillage!=null){
							stoValueList[i]=transNullToString(transNullToString(locationVillage.getItemName()));
						}else{
							stoValueList[i]=transNullToString(transNullToString(""));
						}
					}
				}
				hspConfigs[i] = this.transNullToString(transNullToString(obj[4]));
			}
			form.setIds(idList);
			form.setSecurityStaffBaseInfos(sList);
			form.setStaffCodes(staffCodes);
			form.setSdotIds(stoList);
			form.setSdoValues(stoValueList);
			form.setHspConfigs(hspConfigs);
		}
		List<?> sdotId=securityDataObjectVsRoleServiceDAO.getObjectTbales("s.id", "s.dataObjectTypeName", "SecurityDataObjectType s");
		form.setSdotIdList(sdotId);
	}
	private String transNullToString(Object valueOf) {
		String temp = "";
		try{
			if(valueOf != null)
				temp = String.valueOf(valueOf).trim();
			if(valueOf != null && valueOf.equals("null"))
				temp = "";
		}catch(Exception e){
			e.printStackTrace();
			temp = "";
		}
		return temp;
	}

	
	public void detail(SecurityDataObjectVsRoleForm form) {
		SecurityDataObjectVsRoles vsRoles=this.getSecurityDataObjectVsRoleServiceDAO().getDetail(form.getId());
		setForm(form,vsRoles);
	}
	
	public void setForm(SecurityDataObjectVsRoleForm form,SecurityDataObjectVsRoles vsRoles){
		form.setIdid(vsRoles!=null?vsRoles.getId():"");
		form.setSdotId(transNullToString(vsRoles!=null?vsRoles.getSdotId():""));
		form.setSecurityStaffBaseInfo(transNullToString(vsRoles!=null?vsRoles.getSecurityStaffBaseinfoId():""));
		SecurityStaffBaseinfo info=securityDataObjectVsRoleServiceDAO.getId(vsRoles!=null?vsRoles.getSecurityStaffBaseinfoId():"");
		form.setSecurityStaffBaseInfoName(info==null?"":transNullToString(info.getName()));
		SecurityDataObjectType type=securityDataObjectVsRoleServiceDAO.getRolesName(vsRoles!=null?vsRoles.getSdotId():"");
		form.setSdoIdName(type!=null?transNullToString(type.getDataObjectTypeName()):"");
		form.setSdoValue(transNullToString(vsRoles!=null?vsRoles.getSdoValue():""));
		CommConfigLocation location=securityDataObjectVsRoleServiceDAO.getLocationName(transNullToString(vsRoles!=null?vsRoles.getSdoValue():""));
		if(location!=null){
			form.setSdoValueName(transNullToString(transNullToString(location.getItemName())));
		}else{
			CommConfigLocationTown locationTown=securityDataObjectVsRoleServiceDAO.getLocationTown(transNullToString(vsRoles!=null?vsRoles.getSdoValue():""));
			if(locationTown!=null){
				form.setSdoValueName(transNullToString(transNullToString(locationTown.getItemName())));
			}else{
				CommConfigLocationVillage locationVillage=securityDataObjectVsRoleServiceDAO.getVillage(transNullToString(vsRoles!=null?vsRoles.getSdoValue():""));
				if(locationVillage!=null){
					form.setSdoValueName(transNullToString(transNullToString(locationVillage.getItemName())));
				}else{
					form.setSdoValueName(transNullToString(transNullToString("")));
				}
			}
		}
	}

	
	public void delete(SecurityDataObjectVsRoleForm form) {
		SecurityDataObjectVsRoles vsRoles=this.getSecurityDataObjectVsRoleServiceDAO().getDetail(form.getItemCodeHidden());
		securityDataObjectVsRoleServiceDAO.delete(vsRoles);
	}

	
	public void updateInit(SecurityDataObjectVsRoleForm form) {
		detail(form);
		List<?> sdotId=securityDataObjectVsRoleServiceDAO.getObjectTbales("s.id", "s.dataObjectTypeName", "SecurityDataObjectType s");
		form.setSdotIdList(sdotId);
	}

	
	public int checkId(String id) {
		return securityDataObjectVsRoleServiceDAO.getCheckId(id);
		
	}

	
	public String getXml(String flag, String inputCode,HttpServletRequest request) {
		ServletContext application = request.getSession().getServletContext();
		//List<?> list=securityDataObjectVsRoleServiceDAO.getFindList(flag, inputCode, Integer.valueOf(CommInit.getProperty("CURRECORD_TANCHUCENG")), 
			//	CommInit.getPageSize("PAGE_SIZE_TANCHUCENG"));
		List<?> list=securityDataObjectVsRoleServiceDAO.getFindList(flag, inputCode, Integer.valueOf((String)application.getAttribute("security.CURRECORD_TANCHUCENG")), 
				Integer.valueOf((String)application.getAttribute("security.PAGE_SIZE_TANCHUCENG")));
		StringBuffer buffer = new StringBuffer();
		buffer.append("<response>");
		for(int i=0; i<list.size(); i++){
			SecurityStaffBaseinfo charbean =(SecurityStaffBaseinfo) list.get(i);
			// -----����xml��ǩ��Ҫ�ı�--------------------------------------------------------------
			buffer.append("<ress>");
			buffer.append("<resInputCode>"+Converter.toBlank(charbean.getInputCode())+"</resInputCode>");
			buffer.append("<resItemCode>"+Converter.toBlank(charbean.getStaffCode())+"</resItemCode>");
			buffer.append("<resItemName>"+Converter.toBlank(charbean.getName())+"</resItemName>");
			buffer.append("<resItemId>"+Converter.toBlank(charbean.getId())+"</resItemId>");
			buffer.append("</ress>");
		}
		buffer.append("</response>");
		return buffer.toString();
	}

	
	public String getLocationXml(String flag, String inputCode) {
		return "";
	}
	private String getLocationTownXml(String flag, String inputCode,HttpServletRequest request) {
		ServletContext application = request.getSession().getServletContext();
//		List<?> list = securityDataObjectVsRoleServiceDAO.getFindLocationTown(
//				flag, inputCode, Integer.valueOf(CommInit
//						.getProperty("CURRECORD_TANCHUCENG")), CommInit
//						.getPageSize("PAGE_SIZE_TANCHUCENG"));
		List<?> list = securityDataObjectVsRoleServiceDAO.getFindLocationTown(
				flag, inputCode, Integer.valueOf((String)application.getAttribute("security.CURRECORD_TANCHUCENG")), 
					Integer.valueOf((String)application.getAttribute("security.PAGE_SIZE_TANCHUCENG")));
		String village="";
		if (list != null && list.size() > 0) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("<response>");
			for (int i = 0; i < list.size(); i++) {
				CommConfigLocationTown towns = (CommConfigLocationTown) list
						.get(i);
				buffer.append("<ress>");
				buffer.append("<resInputCode>"
						+ Converter.toBlank(towns.getInputCode())
						+ "</resInputCode>");
				buffer.append("<resItemCode>"
						+ Converter.toBlank(towns.getItemCode())
						+ "</resItemCode>");
				buffer.append("<resItemName>"
						+ Converter.toBlank(towns.getItemName())
						+ "</resItemName>");
				buffer.append("<resItemId>" + Converter.toBlank(towns.getId())
						+ "</resItemId>");
				buffer.append("</ress>");

			}
			buffer.append("</response>");
			return buffer.toString();
		} else {
			village=getLocationVillageXml(flag, inputCode,request);
			return village;
		}
	}
	private String getLocationVillageXml(String flag, String inputCode,HttpServletRequest request) {
		ServletContext application = request.getSession().getServletContext();
//		List<?> list = securityDataObjectVsRoleServiceDAO.getFindLocationVilge(
//				flag, inputCode, Integer.valueOf(CommInit
//						.getProperty("CURRECORD_TANCHUCENG")), CommInit
//						.getPageSize("PAGE_SIZE_TANCHUCENG"));
		List<?> list = securityDataObjectVsRoleServiceDAO.getFindLocationVilge(			
				flag, inputCode, Integer.valueOf((String)application.getAttribute("security.CURRECORD_TANCHUCENG")), 
					Integer.valueOf((String)application.getAttribute("security.PAGE_SIZE_TANCHUCENG")));
		if (list != null && list.size() > 0) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("<response>");
			for (int i = 0; i < list.size(); i++) {
				CommConfigLocationVillage towns = (CommConfigLocationVillage) list
						.get(i);
					buffer.append("<ress>");
					buffer.append("<resInputCode>"
							+ Converter.toBlank(towns.getInputCode())
							+ "</resInputCode>");
					buffer.append("<resItemCode>"
							+ Converter.toBlank(towns.getItemCode())
							+ "</resItemCode>");
					buffer.append("<resItemName>"
							+ Converter.toBlank(towns.getItemName())
							+ "</resItemName>");
					buffer
							.append("<resItemId>"
									+ Converter.toBlank(towns.getId())
									+ "</resItemId>");
					buffer.append("</ress>");
			}
			buffer.append("</response>");
			return buffer.toString();
		} else {
			StringBuffer buffer = new StringBuffer();
			buffer.append("<ress>");
			buffer.append("<resInputCode></resInputCode>");
			buffer.append("<resItemCode></resItemCode>");
			buffer.append("<resItemName></resItemName>");
			buffer.append("<resItemId></resItemId>");
			buffer.append("</ress>");
			return buffer.toString();
		}
	}

	
	public void update(SecurityDataObjectVsRoleForm form) {
		SecurityDataObjectVsRoles roles=securityDataObjectVsRoleServiceDAO.getDetail(form.getId());
		setData(form,roles);
		securityDataObjectVsRoleServiceDAO.update(roles);
	}
	
	private void setData(SecurityDataObjectVsRoleForm form,SecurityDataObjectVsRoles roles){
		//roles.setId(form.getId());
		roles.setSdotId(form.getSdotId());
		roles.setSecurityStaffBaseinfoId(form.getSecurityStaffBaseInfo());
		roles.setSdoValue(form.getSdoValue());
	}

	
	public void addInit(SecurityDataObjectVsRoleForm form) {
		this.detail(form);
		List<?> sdotId=securityDataObjectVsRoleServiceDAO.getObjectTbales("s.id", "s.dataObjectTypeName", "SecurityDataObjectType s");
		form.setSdotIdList(sdotId);
	}
	
	public boolean save(SecurityDataObjectVsRoleForm form) {
		try {
			SecurityDataObjectVsRoles roles=new SecurityDataObjectVsRoles();
			setData(form,roles);
			securityDataObjectVsRoleServiceDAO.save(roles);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	
	public String getPlaceXml(String flag, String inputCode, String id,HttpServletRequest request) {
		ServletContext application = request.getSession().getServletContext();
		// TODO Auto-generated method stub
		List<?> list=null;
		if(id.trim().equals("1")){
			//list=securityDataObjectVsRoleServiceDAO.getFindLocation(flag, inputCode, Integer.valueOf(CommInit.getProperty("CURRECORD_TANCHUCENG")), CommInit.getPageSize("PAGE_SIZE_TANCHUCENG"),1);
			list=securityDataObjectVsRoleServiceDAO.getFindLocation(flag, inputCode, Integer.valueOf((String)application.getAttribute("security.CURRECORD_TANCHUCENG")), Integer.valueOf((String)application.getAttribute("security.PAGE_SIZE_TANCHUCENG")),1);
		}else if(id.trim().equals("2")){
			//list=securityDataObjectVsRoleServiceDAO.getFindLocation(flag, inputCode, Integer.valueOf(CommInit.getProperty("CURRECORD_TANCHUCENG")), CommInit.getPageSize("PAGE_SIZE_TANCHUCENG"),2);
			list=securityDataObjectVsRoleServiceDAO.getFindLocation(flag, inputCode, Integer.valueOf((String)application.getAttribute("security.CURRECORD_TANCHUCENG")), Integer.valueOf((String)application.getAttribute("security.PAGE_SIZE_TANCHUCENG")),2);
		}else if(id.trim().equals("3")){
			//list=securityDataObjectVsRoleServiceDAO.list = securityDataObjectVsRoleServiceDAO.getFindLocationTown(
//					flag, inputCode, Integer.valueOf(CommInit
//							.getProperty("CURRECORD_TANCHUCENG")), CommInit
//							.getPageSize("PAGE_SIZE_TANCHUCENG"));getFindLocation(flag, inputCode, Integer.valueOf(CommInit.getProperty("CURRECORD_TANCHUCENG")), CommInit.getPageSize("PAGE_SIZE_TANCHUCENG"),3);
			list=securityDataObjectVsRoleServiceDAO.getFindLocation(flag, inputCode, Integer.valueOf((String)application.getAttribute("security.CURRECORD_TANCHUCENG")), Integer.valueOf((String)application.getAttribute("security.PAGE_SIZE_TANCHUCENG")),3);
		}else if(id.trim().equals("4")){
//			
			list = securityDataObjectVsRoleServiceDAO.getFindLocationTown(
					flag, inputCode, Integer.valueOf((String)application.getAttribute("security.CURRECORD_TANCHUCENG")),Integer.valueOf((String)application.getAttribute("security.PAGE_SIZE_TANCHUCENG")));
		}else if(id.trim().equals("5")){
//			list = securityDataObjectVsRoleServiceDAO.getFindLocationVilge(
//					flag, inputCode, Integer.valueOf(CommInit
//							.getProperty("CURRECORD_TANCHUCENG")), CommInit
//							.getPageSize("PAGE_SIZE_TANCHUCENG"));
			list = securityDataObjectVsRoleServiceDAO.getFindLocationVilge(
					flag, inputCode, Integer.valueOf((String)application.getAttribute("security.CURRECORD_TANCHUCENG")), Integer.valueOf((String)application.getAttribute("security.PAGE_SIZE_TANCHUCENG")));
		}
		if(list!=null&&list.size()>0){
			StringBuffer buffer = new StringBuffer();
			buffer.append("<response>");
			for(int i=0;i<list.size();i++){
				if(id.trim().equals("4")){
					CommConfigLocationTown location=(CommConfigLocationTown) list.get(i);
					buffer.append("<ress>");
					buffer.append("<resInputCode>"+Converter.toBlank(location.getInputCode())+"</resInputCode>");
					buffer.append("<resItemCode>"+Converter.toBlank(location.getItemCode())+"</resItemCode>");
					buffer.append("<resItemName>"+Converter.toBlank(location.getItemName())+"</resItemName>");
					buffer.append("</ress>");
				}
				else if(id.trim().equals("5")){
					CommConfigLocationVillage location=(CommConfigLocationVillage) list.get(i);
					buffer.append("<ress>");
					buffer.append("<resInputCode>"+Converter.toBlank(location.getInputCode())+"</resInputCode>");
					buffer.append("<resItemCode>"+Converter.toBlank(location.getItemCode())+"</resItemCode>");
					buffer.append("<resItemName>"+Converter.toBlank(location.getItemName())+"</resItemName>");
					buffer.append("</ress>");
				}else{
					CommConfigLocation location=(CommConfigLocation) list.get(i);
					buffer.append("<ress>");
					buffer.append("<resInputCode>"+Converter.toBlank(location.getInputCode())+"</resInputCode>");
					buffer.append("<resItemCode>"+Converter.toBlank(location.getItemCode())+"</resItemCode>");
					buffer.append("<resItemName>"+Converter.toBlank(location.getItemName())+"</resItemName>");
					buffer.append("</ress>");
				}
			}
			buffer.append("</response>");
			return buffer.toString();
		}else{
			StringBuffer buffer = new StringBuffer();
			buffer.append("<ress>");
			buffer.append("<resInputCode></resInputCode>");
			buffer.append("<resItemCode></resItemCode>");
			buffer.append("<resItemName></resItemName>");
			buffer.append("</ress>");
			return buffer.toString();
		}
	}

	
	public int checkStaffBaseinfoId(String id) {
		// TODO Auto-generated method stub
		return securityDataObjectVsRoleServiceDAO.getCheckId(id);
	}

	
	public void addInits(SecurityDataObjectVsRoleForm form) {
		// TODO Auto-generated method stub
		this.detail(form);
		List<?> sdotId=securityDataObjectVsRoleServiceDAO.getObjectTbales("s.id", "s.dataObjectTypeName", "SecurityDataObjectType s");
		form.setSdotIdList(sdotId);
		this.setLocation1s(form);
	}
	private void setLocation1s(SecurityDataObjectVsRoleForm form){
		List<?> commConfigLocationId1_data = null;		
		List<?> commConfigLocationId2_data = null;
		List<?> commConfigLocationId3_data = null;
		List<?> commConfigLocationTownId_data = null;
		commConfigLocationId1_data = securityDataObjectVsRoleServiceDAO.getLocation(1,"");
		if(form.getCommConfigLocationId1()!=null&&form.getCommConfigLocationId1().trim().length()>0){
			commConfigLocationId2_data=securityDataObjectVsRoleServiceDAO.getLocation(2, form.getCommConfigLocationId1());
		}
		if(form.getCommConfigLocationId2()!=null&&form.getCommConfigLocationId2().trim().length()>0){
			commConfigLocationId3_data=securityDataObjectVsRoleServiceDAO.getLocation(3, form.getCommConfigLocationId2());
		}
		if(form.getCommConfigLocationId3()!=null&&form.getCommConfigLocationId3().trim().length()>0){
			commConfigLocationTownId_data=securityDataObjectVsRoleServiceDAO.getLocationOther("CommConfigLocationTown", "commConfigLocationId", form.getCommConfigLocationId3());
		}
		//--------------------------省---------------------------------------
		 String[] tempId = null;
		 String[] tempName = null;
		if (commConfigLocationId1_data != null) {
			if(commConfigLocationId1_data.size()==1){
				tempId = new String[commConfigLocationId1_data.size() ];
				tempName = new String[commConfigLocationId1_data.size() ];
				for (int i = 0; i < commConfigLocationId1_data.size(); i++) {
					CommConfigLocation data = (CommConfigLocation)commConfigLocationId1_data.get(i);
					tempId[i ] = this.transNullToString(data.getItemCode());
					tempName[i ] =this.transNullToString(data.getItemName());
				}
			}else{
				tempId = new String[commConfigLocationId1_data.size()+1 ];
				tempName = new String[commConfigLocationId1_data.size()+1 ];
				tempId[0] = "";
				tempName[0] = "";
				for (int i = 0; i < commConfigLocationId1_data.size(); i++) {
					CommConfigLocation data = (CommConfigLocation)commConfigLocationId1_data.get(i);
					tempId[i+1 ] = this.transNullToString(data.getItemCode());
					tempName[i+1 ] =this.transNullToString(data.getItemName());
				}
			}
		}		
		form.setCommConfigLocationId1s(tempId);
		form.setCommConfigLocationId1_names(tempName);
		//--------------------------市---------------------------------------
		tempId = null;
		tempName = null;
		if (commConfigLocationId2_data != null) {
			if(commConfigLocationId2_data.size()==1){
				tempId = new String[commConfigLocationId2_data.size() ];
				tempName = new String[commConfigLocationId2_data.size() ];
				for (int i = 0; i < commConfigLocationId2_data.size(); i++) {
					CommConfigLocation data = (CommConfigLocation)commConfigLocationId2_data.get(i);
					tempId[i] = this.transNullToString(data.getItemCode());
					tempName[i] =this.transNullToString(data.getItemName());
				}
			}
			else{
				tempId = new String[commConfigLocationId2_data.size()+1 ];
				tempName = new String[commConfigLocationId2_data.size()+1 ];
				tempId[0] = "";
				tempName[0] = "";
				for (int i = 0; i < commConfigLocationId2_data.size(); i++) {
					CommConfigLocation data = (CommConfigLocation)commConfigLocationId2_data.get(i);
					tempId[i+1] = this.transNullToString(data.getItemCode());
					tempName[i+1] =this.transNullToString(data.getItemName());
				}
			}
		} else {
			//--------------如果为空，插入空格-----------
			tempId = new String[1];
			tempName = new String[1];
			tempId[0] = "";
			tempName[0] = "";
		}
		form.setCommConfigLocationId2s(tempId);
		form.setCommConfigLocationId2_names(tempName);
		//--------------------------县---------------------------------------
		tempId = null;
		tempName = null;
		if (commConfigLocationId3_data != null) {
			if(commConfigLocationId3_data.size()==1){
				tempId = new String[commConfigLocationId3_data.size() ];
				tempName = new String[commConfigLocationId3_data.size() ];
				for (int i = 0; i < commConfigLocationId3_data.size(); i++) {
					CommConfigLocation data = (CommConfigLocation)commConfigLocationId3_data.get(i);
					tempId[i ] = this.transNullToString(data.getItemCode());
					tempName[i ] =this.transNullToString(data.getItemName());
				}
			}else{
				tempId = new String[commConfigLocationId3_data.size()+1 ];
				tempName = new String[commConfigLocationId3_data.size()+1 ];
				tempId[0] = "";
				tempName[0] = "";
				for (int i = 0; i < commConfigLocationId3_data.size(); i++) {
					CommConfigLocation data = (CommConfigLocation)commConfigLocationId3_data.get(i);
					tempId[i +1] = this.transNullToString(data.getItemCode());
					tempName[i +1] =this.transNullToString(data.getItemName());
				}
			}
		} else {
			//--------------如果为空，插入空格-----------
			tempId = new String[1];
			tempName = new String[1];
			tempId[0] = "";
			tempName[0] = "";
		}
		form.setCommConfigLocationId3s(tempId);
		form.setCommConfigLocationId3_names(tempName);
		//--------------------------乡镇---------------------------------------
		 tempId = null;
		 tempName = null;
		if (commConfigLocationTownId_data != null) {
			if(commConfigLocationTownId_data.size()==1){
				tempId = new String[commConfigLocationTownId_data.size() ];
				tempName = new String[commConfigLocationTownId_data.size() ];
				for (int i = 0; i < commConfigLocationTownId_data.size(); i++) {
					CommConfigLocationTown data = (CommConfigLocationTown)commConfigLocationTownId_data.get(i);
					tempId[i ] = this.transNullToString(data.getItemCode());
					tempName[i ] =this.transNullToString(data.getItemName());
				}
			}else{
				tempId = new String[commConfigLocationTownId_data.size()+1 ];
				tempName = new String[commConfigLocationTownId_data.size()+1 ];
				tempId[0] = "";
				tempName[0] = "";
				for (int i = 0; i < commConfigLocationTownId_data.size(); i++) {
					CommConfigLocationTown data = (CommConfigLocationTown)commConfigLocationTownId_data.get(i);
					tempId[i+1 ] = this.transNullToString(data.getItemCode());
					tempName[i+1 ] =this.transNullToString(data.getItemName());
				}
			}
		} else {
			//--------------如果为空，插入空格-----------
			tempId = new String[1];
			tempName = new String[1];
			tempId[0] = "";
			tempName[0] = "";
		}
		form.setCommConfigLocationTownIds(tempId);
		form.setCommConfigLocationTownId_names(tempName);
	}

	
	public List<?> getNeedSecurityDataObjectVsRoles(String id, String sdotId) {
		// TODO Auto-generated method stub
		return securityDataObjectVsRoleServiceDAO.getNeedSecurityDataObjectVsRoles(id, sdotId);
	}

	
	public List<?> getLocation(int level, String parentId) {
		// TODO Auto-generated method stub
		return securityDataObjectVsRoleServiceDAO.getLocation(level, parentId);
	}

	public ICommonDAO getCommondao() {
		return commondao;
	}

	public void setCommondao(ICommonDAO commondao) {
		this.commondao = commondao;
	}

	
	public List<?> getNameByObject(String tableName,String name,String value) {
		// TODO Auto-generated method stub
		List<?> list=commondao.findListInTable(tableName, name, value);
		return list;
	}

	
	public List<?> getLocationOther(String tableName,String columnName ,String parentId) {
		// TODO Auto-generated method stub
		return securityDataObjectVsRoleServiceDAO.getLocationOther(tableName,columnName, parentId);
	}

	
	public void deleteObject(SecurityDataObjectVsRoles s) {
		// TODO Auto-generated method stub
		securityDataObjectVsRoleServiceDAO.delete(s);
	}
	
	public void init(SecurityDataObjectVsRoleForm form){
		List<?> sdotId=securityDataObjectVsRoleServiceDAO.getObjectTbales("s.id", "s.dataObjectTypeName", "SecurityDataObjectType s");
		form.setSdotIdList(sdotId);
	}

}
