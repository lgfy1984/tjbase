package com.tianjian.security.business.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.tianjian.security.bean.SecuritySystemUsers;
import com.tianjian.security.business.ISecurityStaffPasswordInitService;
import com.tianjian.security.dao.ISecurityStaffPasswordInitDAO;
import com.tianjian.security.struts.form.SecurityStaffPasswordInitForm;
import com.tianjian.util.comm.MD5;

public class SecurityStaffPasswordInitServiceImpl implements ISecurityStaffPasswordInitService {

	private ISecurityStaffPasswordInitDAO dao;

	public ISecurityStaffPasswordInitDAO getDao() {
		return dao;
	}

	public void setDao(ISecurityStaffPasswordInitDAO dao) {
		this.dao = dao;
	}


	public void update(SecurityStaffPasswordInitForm form) {
		SecuritySystemUsers data = dao.findById(form.getStaffId());
		if (data == null) {
			data = new SecuritySystemUsers();
			data.setSecurityStaffBaseinfoId(form.getStaffId());
			String md5Password = MD5.toMD5(form.getPasswd().trim());
			data.setPasswd(md5Password);
			dao.save(data);
		} else {
			String md5Password = MD5.toMD5(form.getPasswd().trim());
			data.setPasswd(md5Password);
			dao.update(data);
		}
	}

	public void searchInit(SecurityStaffPasswordInitForm form) {
		this.init(form);
	}

	public int getStaffSelectCount(String staffId, String name, String inputCode, String itemCode,String staffHspId,String hspConfigId) {
		return dao.getStaffsCount(staffId, name, inputCode, itemCode,staffHspId,hspConfigId);
		
	}

	public void getSearch(SecurityStaffPasswordInitForm form, int from, int length) {
		String order = "";
		if (form.getOrderNo().equals("1")) {
			order = " a.hspConfigBaseinfoId";
		} else if (form.getOrderNo().equals("2")) {
			order = " a.staffCode";
		} else if (form.getOrderNo().equals("3")) {
			order = " a.name";
		} else if (form.getOrderNo().equals("4")) {
			order = " a.commConfigSexId";
		} else if (form.getOrderNo().equals("5")) {
			order = " c.startTime";
		} else if (form.getOrderNo().equals("5")) {
			order = " c.stopDate";
		} else {
			order = " a.staffCode ";
		}
		if (form.getAsc().equals("1")) {
			order += " desc";
		} else {
			order += " asc";
		}
		List<?> list = dao.getStaffs(form.getStaffId(), form.getName(), form.getInputCode(), form.getItemCode(), 
				form.getStaffHspId(),form.getHspConfigId(), order, from, length);
		
		if (list != null && list.size() > 0) {
			String[] ids = new String[list.size()];
			String[] staffIds = new String[list.size()];
			String[] itemNames = new String[list.size()];
			String[] names = new String[list.size()];
			String[] sexIds = new String[list.size()];
			String[] sexs = new String[list.size()];
			String[] regTimes = new String[list.size()];
			String[] stopTimes = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				ids[i] = this.transNullToString(((Object[]) list.get(i))[0]);
				staffIds[i] = this.transNullToString(((Object[]) list.get(i))[1]);
				itemNames[i] = this.transNullToString(((Object[]) list.get(i))[2]);
				names[i] = this.transNullToString(((Object[]) list.get(i))[3]);
				sexIds[i] = this.transNullToString(((Object[]) list.get(i))[4]);
				Date date = (Date) ((Object[]) list.get(i))[5];
				Date date1 = (Date) ((Object[]) list.get(i))[6];
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd mm:ss"); 
				String dateString = "";
				String dateString1 = "";
				if (date != null) {
					dateString = format.format(date);
				}
				regTimes[i] = this.transNullToString(dateString);
				if(date1 != null){
					dateString1 = format.format(date1);
				}
				stopTimes[i] = this.transNullToString(dateString1);
				sexs[i] = this.transNullToString(this.getNameById("CommConfigSex", "itemCode", "itemName", sexIds[i]));
			
			}
			form.setIdList(ids);
			form.setStaffIdList(staffIds);
			form.setItemNameList(itemNames);
			form.setNameList(names);
			form.setSexList(sexs);
			form.setRegTimes(regTimes);
			form.setStopTimes(stopTimes);
		}
	}

	private String getNameById(String table, String id, String name, String idValue) {
		String temp = "";
		if (idValue != null && idValue.trim().length() > 0) {
			temp = dao.getNameById(table, id, name, idValue);
		}
		return temp;
	}

	private void init(SecurityStaffPasswordInitForm form) {
		 
	}

	private String transNullToString(Object obj) {
		String temp = "";
		if (obj != null) {
			temp = ((String) obj).trim();
		}
		return temp;
	}
	
	/** 处理弹出层子画面 */
	public String getXml(String flag, String inputCode, String hspType,HttpServletRequest request) {
		ServletContext application = request.getSession().getServletContext();
		StringBuffer buffer = new StringBuffer();
		//List<?> list = dao.findHspList(flag, inputCode, hspType, Integer.valueOf(BaseCommInit.getProperty("CURRECORD_TANCHUCENG")), 
		//		BaseCommInit.getPageSize("PAGE_SIZE_TANCHUCENG"));
		List<?> list = dao.findHspList(flag, inputCode, hspType, Integer.valueOf((String)application.getAttribute("security.CURRECORD_TANCHUCENG")),
				Integer.valueOf((String)application.getAttribute("security.PAGE_SIZE_TANCHUCENG")));
		buffer.append("<response>");
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			// -----以下xml标签不要改变--------------------------------------------------------------
			buffer.append("<ress>");
			buffer.append("<resInputCode>" + this.transNullToString(obj[3]) + "</resInputCode>");
			buffer.append("<resItemCode>" + this.transNullToString(obj[1]) + "</resItemCode>");
			buffer.append("<resItemName>" + this.transNullToString(obj[2]) + "</resItemName>");
			buffer.append("<resItemId>" + this.transNullToString(obj[0]) + "</resItemId>");
			buffer.append("</ress>");
		}
		buffer.append("</response>");
		return buffer.toString();
	}

	
}
