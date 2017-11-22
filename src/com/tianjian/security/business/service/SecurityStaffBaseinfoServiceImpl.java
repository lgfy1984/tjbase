package com.tianjian.security.business.service;

import java.text.SimpleDateFormat;
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

import com.tianjian.security.bean.SecurityLicense;
import com.tianjian.security.bean.SecurityStaffBaseinfo;
import com.tianjian.security.bean.SecuritySystemUsers;
import com.tianjian.security.business.ISecurityStaffBaseinfoService;
import com.tianjian.security.dao.ISecurityLicenseDAO;
import com.tianjian.security.dao.ISecurityStaffBaseinfoDAO;
import com.tianjian.security.struts.form.SecurityStaffBaseinfoForm;
import com.tianjian.util.Converter;
import com.tianjian.util.ResourcesUtil;
import com.tianjian.util.comm.MD5;
import com.tianjian.util.comm.UtilTrans;

public class SecurityStaffBaseinfoServiceImpl implements ISecurityStaffBaseinfoService {

	private static final Logger log = LogManager.getLogger(SecurityStaffBaseinfoServiceImpl.class);
	/**/
	private ISecurityStaffBaseinfoDAO securityStaffBaseinfoDAO;
	private ISecurityLicenseDAO securityLicenseDAO;

	public ISecurityStaffBaseinfoDAO getSecurityStaffBaseinfoDAO() {
		return securityStaffBaseinfoDAO;
	}

	public void setSecurityStaffBaseinfoDAO(ISecurityStaffBaseinfoDAO securityStaffBaseinfoDAO) {
		this.securityStaffBaseinfoDAO = securityStaffBaseinfoDAO;
	}

	public ISecurityLicenseDAO getSecurityLicenseDAO() {
		return securityLicenseDAO;
	}

	public void setSecurityLicenseDAO(ISecurityLicenseDAO securityLicenseDAO) {
		this.securityLicenseDAO = securityLicenseDAO;
	}

	/**/
	public SecurityStaffBaseinfoServiceImpl() {}

	/**/
	private String[] ids;
	private String[] names;

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String[] getNames() {
		return names;
	}

	public void setNames(String[] names) {
		this.names = names;
	}
	
	/* Service主方法 */
	public void addInit(SecurityStaffBaseinfoForm form,HttpServletRequest request) {
		init(form,request);
	}

	public void searchInit(SecurityStaffBaseinfoForm form,HttpServletRequest request) {
		init(form,request);
	}
	public void getLogin(SecurityStaffBaseinfoForm form,HttpServletRequest request){
		try{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd"); 
			SecurityStaffBaseinfo info = this.getSecurityStaffBaseinfoDAO().findById(form.getStaffId());
			if(info != null && info.getStaffCode().length()>0){
				SecurityLicense data = this.getSecurityLicenseDAO().findBySecurityStaffBaseinfoId(info.getId());
				this.setForm(form, info, request);
				form.setRegistCode(data.getRegistCode());
				Date date = data.getRegTime();
					String dateString = "";
					if(date != null){
						dateString = format.format(date);
					}
					form.setRegTime(dateString);
				Date date1 = data.getStartTime();
					String dateString1 = "";
					if(date1 != null){
						dateString1 = format.format(date1);
					}
					form.setStartTime(dateString1);
				Date date2 = data.getStopDate();
					String dateString2 = "";
					if(date2 != null){
						dateString2 = format1.format(date2);
					}
					form.setStopDate(dateString2);
			}
		}catch(Exception e){
			log.error("getLogin fail!", e);
			e.printStackTrace();
		}
		
	}
	public String checkData(String code,HttpServletRequest request) {
		String temp = "";
		if (code != null && code.trim().length() > 0) {
			SecurityStaffBaseinfo data = securityStaffBaseinfoDAO.findByCode(code);
			if (data != null && data.getId() != null) {
				temp = data.getId();
			}
		} else {
			temp = ResourcesUtil.getValue("conf.security.securityInit", "security.java.commom.warn6", request);//"数据不存在";
		}
		return temp;
	}

	public String checkSecurityLicense(String securityStaffBaseinfoId) {
		String temp = "";
		SecurityLicense data = this.securityLicenseDAO.findBySecurityStaffBaseinfoId(securityStaffBaseinfoId);
		if (data != null && data.getSecurityStaffBaseinfoId() != null) {
			temp = data.getSecurityStaffBaseinfoId();
		}
		return temp;
	}

	public void save(SecurityStaffBaseinfoForm form,HttpServletRequest request) {
		ServletContext application = request.getSession().getServletContext();
		SecurityStaffBaseinfo data = new SecurityStaffBaseinfo();
		setData(form, data);
		securityStaffBaseinfoDAO.save(data);
		SecuritySystemUsers sysUser = new SecuritySystemUsers();
		//String passwd = BaseSecurityInit.getProperty("STAFF_PASSWORD");
		String passwd = (String)application.getAttribute("security.STAFF_PASSWORD");
		String passwdMD5 = MD5.toMD5(passwd);
		sysUser.setSecurityStaffBaseinfoId(data.getId());
		sysUser.setPasswd(passwdMD5);
		securityStaffBaseinfoDAO.save(sysUser);
	}

	public void saveSecurityLicense(String securityStaffBaseinfoStaffCode,String regCode) {
		SecurityStaffBaseinfo data = this.securityStaffBaseinfoDAO.findByCode(securityStaffBaseinfoStaffCode);
		SecurityLicense securityLicense = new SecurityLicense();
		securityLicense.setSecurityStaffBaseinfoId(data.getId());
		securityLicense.setRegTime(new Date());
		//String regCode = this.generateRegCode(5, 5);		
		securityLicense.setRegistCode(regCode);
		this.securityLicenseDAO.save(securityLicense);
	}

	public void updateInit(SecurityStaffBaseinfoForm form,HttpServletRequest request) {
		SecurityStaffBaseinfo data = securityStaffBaseinfoDAO.findById(form.getId());
		setForm(form, data,request);
		init(form,request);
		getDetail(form,request);
	}

	public void update(SecurityStaffBaseinfoForm form) {
		SecurityStaffBaseinfo data = securityStaffBaseinfoDAO.findById(form.getId());
		form.setCreateDate(UtilTrans.transDateToStringFull(data.getCreateDate()));
		setData(form, data);
		securityStaffBaseinfoDAO.update(data);
		//更新license信息
		SecurityLicense securityLicense = this.securityLicenseDAO.findBySecurityStaffBaseinfoId(form.getId());
		if(securityLicense!=null){
			securityLicense.setStopDate(UtilTrans.transStringToDate(form.getStopDate(),"yyyy-MM-dd"));
		}
	}

	public void showInit(SecurityStaffBaseinfoForm form,HttpServletRequest request) {
		SecurityStaffBaseinfo data = securityStaffBaseinfoDAO.findById(form.getId());
		form = new SecurityStaffBaseinfoForm();
		setForm(form, data,request);
	}

	public void delete(SecurityStaffBaseinfoForm form) {
		SecuritySystemUsers sysUser = securityStaffBaseinfoDAO.findByStaffId(form.getId());
		securityStaffBaseinfoDAO.delete(sysUser);
		SecurityStaffBaseinfo data = securityStaffBaseinfoDAO.findById(form.getId());
		securityStaffBaseinfoDAO.delete(data);
	}

	public void getDetail(SecurityStaffBaseinfoForm form,HttpServletRequest request) {
		SecurityStaffBaseinfo data = securityStaffBaseinfoDAO.findById(form.getId());
		setForm(form, data,request);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		if(data != null && data.getId().length()>0){
			SecurityLicense info = this.getSecurityLicenseDAO().findBySecurityStaffBaseinfoId(data.getId());
			form.setRegistCode(info.getRegistCode());
				Date date = info.getRegTime();
				String dateString = "";
				if(date != null){
					dateString = format.format(date);
				}
				form.setRegTime(dateString);
				Date date1 = info.getStartTime();
				String dateString1 = "";
				if(date1 != null){
					dateString1 = format.format(date1);
				}
				form.setStartTime(dateString1);
				Date date2 = info.getStopDate();
				String dateString2 = "";
				if(date2 != null){
					dateString2 = format1.format(date2);
				}
				form.setStopDate(dateString2);
		}
		
		
	}

	public void deleteSecurityLicense(String securityStaffBaseinfoId) {
		SecurityLicense securityLicense = this.securityLicenseDAO.findBySecurityStaffBaseinfoId(securityStaffBaseinfoId);
		this.securityLicenseDAO.delete(securityLicense);
	}

	public int getCount(String staffCode, String hspConfigBaseinfoId, String name, String commConfigSexId, String itemName, String inputCode, String staffId) {
		
		return securityStaffBaseinfoDAO.getCount(staffCode, hspConfigBaseinfoId, name, commConfigSexId, itemName, inputCode, staffId);
	}

	public void getSearch(SecurityStaffBaseinfoForm form, int curCount, int pageSize) {
		try {
			String order = "";
			if (form.getOrderNo().equals("1")) {
				order = " a.staffCode";
			} else if (form.getOrderNo().equals("2")) {
				order = " a.name";
			} else if (form.getOrderNo().equals("3")) {
				order = " b.itemName";
			} else if (form.getOrderNo().equals("4")) {
				order = " a.commConfigSexId";
			} else if (form.getOrderNo().equals("5")) {
				order = " c.startTime";
			} else if (form.getOrderNo().equals("6")) {
				order = " c.stopDate";
			} else {
				order = " a.seqNo";
			}
			if (form.getAsc().equals("1")) {
				order += " desc";
			} else {
				order += " asc";
			}
			
			
			List<?> list = securityStaffBaseinfoDAO.getData(form.getStaffCode(), form.getHspConfigBaseinfoId(), form.getName(), 
					form.getCommConfigSexId(), form.getHspConfigBaseinfoName(), form.getInputCode(), form.getStaffId(), order,curCount, pageSize);
			if (list != null && list.size() > 0) {
				String[] idList = new String[list.size()];
				String[] staffCodeList = new String[list.size()];
				String[] hspConfigBaseinfoNameList = new String[list.size()];
				String[] nameList = new String[list.size()];
				String[] commConfigSexIdList = new String[list.size()];
				String[] commConfigSexNameList = new String[list.size()];
				String[] regTimeList = new String[list.size()];
				String[] stopTimeList = new String[list.size()];
				for (int i = 0; i < list.size(); i++) {
					idList[i] = this.transNullToString(((Object[]) list.get(i))[0]);
					staffCodeList[i] = this.transNullToString(((Object[]) list.get(i))[1]);
					hspConfigBaseinfoNameList[i] = this.transNullToString(((Object[]) list.get(i))[2]);
					nameList[i] = this.transNullToString(((Object[]) list.get(i))[3]);
					commConfigSexIdList[i] = this.transNullToString(((Object[]) list.get(i))[4]);
					Date date = (Date) ((Object[]) list.get(i))[5];
					Date date1 = (Date) ((Object[]) list.get(i))[6];
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
					SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd"); 
					String dateString = "";
					String dateString1 = "";
					if (date != null) {
						dateString = format.format(date);
					}
					regTimeList[i] = this.transNullToString(dateString);
					if(date1 != null){
						dateString1 = format1.format(date1);
					}
//					dateOfBirthList[i] = this.transNullToString(dateString);
					stopTimeList[i] = this.transNullToString(dateString1);
					commConfigSexNameList[i] = this.transNullToString(this.getNameById("CommConfigSex", "itemCode", "itemName", commConfigSexIdList[i]));
				}
				form.setIdList(idList);
				form.setStaffCodeList(staffCodeList);
				form.setHspConfigBaseinfoNameList(hspConfigBaseinfoNameList);
				form.setNameList(nameList);
				form.setCommConfigSexIdList(commConfigSexIdList);
				form.setCommConfigSexNameList(commConfigSexNameList);
				form.setRegTimeList(regTimeList);
				form.setStopTimeList(stopTimeList);
			}
		}
		catch (Exception e) {
			log.error("getSearch fail!", e);
			e.printStackTrace();
		}
	}
	
	public void getSearchRegisterCode(SecurityStaffBaseinfoForm form, int curCount, int pageSize) {
		try {
			String order = "";
			if (form.getOrderNo().equals("1")) {
				order = " a.staffCode";
			} else if (form.getOrderNo().equals("2")) {
				order = " a.name";
			} else if (form.getOrderNo().equals("3")) {
				order = " b.itemName";
			} else if (form.getOrderNo().equals("4")) {
				order = " a.commConfigSexId";
			} else if (form.getOrderNo().equals("5")) {
				order = " c.startTime";
			} else if (form.getOrderNo().equals("6")) {
				order = " c.stopDate";
			} else {
				order = " a.seqNo";
			}
			if (form.getAsc().equals("1")) {
				order += " desc";
			} else {
				order += " asc";
			}
			List<?> list = securityStaffBaseinfoDAO.getDataRegisterCode(form.getStaffCode(), form.getHspConfigBaseinfoId(), form.getName(), form.getCommConfigSexId(), form.getHspConfigBaseinfoName(), form.getInputCode(), form.getStaffId(), order, curCount, pageSize);
			if (list != null && list.size() > 0) {
				String[] idList = new String[list.size()];
				String[] staffCodeList = new String[list.size()];
				String[] hspConfigBaseinfoNameList = new String[list.size()];
				String[] nameList = new String[list.size()];
				String[] registCodeList = new String[list.size()];
				for (int i = 0; i < list.size(); i++) {
					idList[i] = this.transNullToString(((Object[]) list.get(i))[0]);
					staffCodeList[i] = this.transNullToString(((Object[]) list.get(i))[1]);
					hspConfigBaseinfoNameList[i] = this.transNullToString(((Object[]) list.get(i))[2]);
					nameList[i] = this.transNullToString(((Object[]) list.get(i))[3]);
					registCodeList[i] = this.transNullToString(((Object[]) list.get(i))[4]);
				}
				form.setIdList(idList);
				form.setStaffCodeList(staffCodeList);
				form.setHspConfigBaseinfoNameList(hspConfigBaseinfoNameList);
				form.setNameList(nameList);
				form.setRegistCodeList(registCodeList);
			}
		}
		catch (Exception e) {
			log.error("getSearch fail!", e);
			e.printStackTrace();
		}
	}
	/* Service */
	private String getNameById(String table, String id, String name, String idValue) {
		String temp = "";
		if (idValue != null && idValue.trim().length() > 0) {
			temp = securityStaffBaseinfoDAO.getNameById(table, id, name, idValue);
		}
		return temp;
	}

	private void setData(SecurityStaffBaseinfoForm form, SecurityStaffBaseinfo data) {
		try {
			//data.setStaffCode(this.transNullToString(form.getStaffCode()));
			data.setHspStaffBaseinfoId(this.transNullToString(form.getHspStaffBaseinfoId()));
			data.setHspConfigBaseinfoId(this.transNullToString(form.getHspConfigBaseinfoId()));
			data.setName(this.transNullToString(form.getName()));
			data.setEmail(this.transNullToString(form.getEmail()));
			data.setNameEn(this.transNullToString(form.getNameEn()));
			data.setInputCode(this.transNullToString(form.getInputCode()));
			data.setPinyinInputCode(this.transNullToString(form.getPinyinInputCode()));
			data.setCommConfigSexId(this.transNullToString(form.getCommConfigSexId()));
			// 新增部分
			if (form.getYear() != null && !form.getYear().equals("") && form.getMonth() != null && !form.getMonth().equals("") && form.getDay() != null && !form.getDay().equals("")) {
				try {
					String today = form.getYear().trim()+"-"+form.getMonth().trim()+"-"+form.getDay().trim();
					SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
//					int year = Integer.valueOf(form.getYear());
//					int month = Integer.valueOf(form.getMonth());
//					int day = Integer.valueOf(form.getDay());
//					Date date = new Date(year - 1900, month - 1, day);
					Date date = dateformat.parse(today);
					data.setDateOfBirth(date);
				}
				catch (Exception e) {
					e.printStackTrace();
					data.setDateOfBirth(null);
				}
			}else{
				data.setDateOfBirth(null);
			}
			data.setCommConfigStafftypeId(this.transNullToString(form.getCommConfigStafftypeId()));
			data.setIdNo(this.transNullToString(form.getIdNo()));
			data.setPhone(this.transNullToString(form.getPhone()));
			String temp = form.getIslocation();
			if (temp != null && temp.trim().length() > 0) {
				data.setIslocation(Long.valueOf(temp));
			} else {
				data.setIslocation(Long.valueOf("0"));
			}
			String temp1 = form.getSeqNo();
			if (temp1 != null && temp1.trim().length() > 0) {
				data.setSeqNo(Long.valueOf(temp1));
			} else {
				data.setSeqNo(Long.valueOf("0"));
			}
			data.setComments(this.transNullToString(form.getComments()));
			data.setCreateDate(UtilTrans.transStringToDate(form.getCreateDate(), "yyyy-MM-dd HH:mm:ss"));
			data.setCreateUserId(this.transNullToString(form.getCreateUserId()));
			data.setCreateUserName(this.transNullToString(form.getCreateUserName()));
			data.setHomePageType(this.transNullToString(form.getHomePageType()));
			String staff = form.getStaffType();
			if(staff != null && staff.trim().length() > 0){
				data.setStaffType(Long.valueOf(staff));
			}else{
				data.setStaffType(Long.valueOf("0"));
			}
		}
		catch (Exception e) {
			log.error("setData fail!", e);
			e.printStackTrace();
		}
		}

	public String transNullToString(Object obj) {
		String temp = "";
		if (obj != null)
			temp = String.valueOf(obj).trim();
		return temp;
	}

	public String transNullToZero(Object obj) {
		String temp = "0";
		if (obj != null)
			temp = ((String) obj).trim();
		return temp;
	}

	/* 在ActionForm中应放置名称Name，有Id的将之换成Name */
	private void setForm(SecurityStaffBaseinfoForm form, SecurityStaffBaseinfo data,HttpServletRequest request) {
		try {
			form.setId(this.transNullToString(data.getId()));
			form.setStaffCode(this.transNullToString(data.getStaffCode()));
			form.setHspConfigBaseinfoId(this.transNullToString(data.getHspConfigBaseinfoId()));
			form.setHspStaffBaseinfoId(this.transNullToString(data.getHspStaffBaseinfoId()));
			form.setEmail(this.transNullToString(data.getEmail()));
			form.setHspConfigBaseinfoName(this.transNullToString(this.getNameById("HspConfigBaseinfoLocalBase", "id", "itemName", form.getHspConfigBaseinfoId())));
			form.setName(this.transNullToString(data.getName()));
			form.setNameEn(this.transNullToString(data.getNameEn()));
			form.setInputCode(this.transNullToString(data.getInputCode()));
			form.setPinyinInputCode(this.transNullToString(data.getPinyinInputCode()));
			form.setCommConfigSexId(this.transNullToString(data.getCommConfigSexId()));
			form.setCommConfigSexName(this.transNullToString(this.getNameById("CommConfigSex", "itemCode", "itemName", form.getCommConfigSexId())));
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date date = data.getDateOfBirth();
			if (date == null) {
				form.setYear(this.transNullToString(""));
				form.setMonth(this.transNullToString(""));
				form.setDay(this.transNullToString(""));
			} else {
				String dateNew = format.format(date);
				String[] aa = dateNew.split("/");
				form.setYear(aa[2].substring(0, 4));
				form.setMonth(aa[1]);
				form.setDay(aa[0]);
			}
			form.setCommConfigStafftypeId(this.transNullToString(data.getCommConfigStafftypeId()));
			form.setCommConfigStafftypeName(this.transNullToString(this.getNameById("CommConfigStafftype", "itemCode", "itemName", form.getCommConfigStafftypeId())));
			form.setIdNo(this.transNullToString(data.getIdNo()));
			form.setPhone(this.transNullToString(data.getPhone()));
			form.setIslocation(this.transNullToString(data.getIslocation()));
			form.setIslocationName(this.transNullToString(this.getIslocations(form.getIslocation(),request)));
			form.setSeqNo(this.transNullToString(data.getSeqNo()));
			form.setComments(this.transNullToString(data.getComments()));
			System.out.println(data.getCreateDate());
			try {
				if (data.getCreateDate() != null) {
					form.setCreateDateYear(String.valueOf(data.getCreateDate().getYear() + 1900));
					form.setCreateDateMonth(String.valueOf(data.getCreateDate().getMonth() + 1));
					form.setCreateDateDay(String.valueOf(data.getCreateDate().getDay() + 12));
				}
			}
			catch (RuntimeException e) {
				e.printStackTrace();
			}
			form.setCreateUserId(transNullToString(data.getCreateUserId()));
			form.setCreateUserName(transNullToString(data.getCreateUserName()));
			form.setCreateDate(UtilTrans.transDateToStringFull(data.getCreateDate()));
			form.setHomePageType(transNullToString(data.getHomePageType()));
			form.setStaffType(this.transNullToString(String.valueOf(data.getStaffType())));
			
			
			this.searchInit(form, request);
		}
		catch (Exception e) {
			log.error("setForm fail!", e);
			e.printStackTrace();
		}
	}

	private void init(SecurityStaffBaseinfoForm form,HttpServletRequest request) {
		this.setIdNames("CommConfigSex", "itemCode", "itemName");
		form.setCommConfigSexIds(this.getIds());
		form.setCommConfigSexNames(this.getNames());
		this.setIdNames("CommConfigStafftype", "itemCode", "itemName");
		form.setCommConfigStafftypeIds(this.getIds());
		form.setCommConfigStafftypeNames(this.getNames());
		this.setIslocations(request);
		form.setIslocationIds(this.getIds());
		form.setIslocationNames(this.getNames());
		if (form.getSeqNo() == null || form.getSeqNo().equals(""))
			form.setSeqNo(securityStaffBaseinfoDAO.getMaxSeqNo() + "");

	}

	private void setIslocations(HttpServletRequest request) {
		String[] ids = new String[3];
		String[] names = new String[3];
		ids[0] = "2";
		names[0] = "";
		ids[1] = "1";
		names[1] = ResourcesUtil.getValue("conf.security.securityInit", "security.java.commom.item", request);//"在";
		ids[2] = "0";
		names[2] = ResourcesUtil.getValue("conf.security.securityInit", "security.java.commom.item1", request);
		this.setIds(ids);
		this.setNames(names);
	}

	private String getIslocations(String id,HttpServletRequest request) {
		String temp = "";
		if(id.equals("2")){
			temp = "";
		}else if (id.equals("1")) {
			temp = ResourcesUtil.getValue("conf.security.securityInit", "security.java.commom.item", request);//"在";
		} else if (id.equals("0")) {
			temp = ResourcesUtil.getValue("conf.security.securityInit", "security.java.commom.item1", request);//"不在";
		}
		return temp;
	}

	private void setIdNames(String table, String id, String name) {
		List<?> l = securityStaffBaseinfoDAO.getIdNames(table, id, name);
		if (l != null && l.size() > 0) {
			String[] ids = new String[l.size() + 1];
			String[] names = new String[l.size() + 1];
			ids[0] = "";
			names[0] = "";
			for (int i = 0; i < l.size(); i++) {
				ids[i + 1] = this.transNullToString(((Object[]) l.get(i))[0]);
				names[i + 1] = this.transNullToString(((Object[]) l.get(i))[1]);
			}
			this.setIds(ids);
			this.setNames(names);
		} else {
			String[] ids = new String[1];
			String[] names = new String[1];
			ids[0] = "";
			names[0] = "";
			this.setIds(ids);
			this.setNames(names);
		}
	}

	/* 生成医务人员许可证注册码方法 */
	public String generateRegCode(int zushu, int weishu) {
		String temp_l = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuffer reg_code = new StringBuffer();
		reg_code.append("");
		for (int i = 0; i < zushu; i++) {
			String temp = "";
			Random random = new Random();
			for (int j = 0; j < weishu; j++)
				temp += temp_l.charAt(Math.abs(random.nextInt()) % (26 + 26 + 10 - 1));
			reg_code.append(temp);
			if (i != zushu - 1)
				reg_code.append("-");
		}
		return reg_code.toString().toUpperCase();
	}

	public SecurityStaffBaseinfo findbyId(String id) {
		return this.securityStaffBaseinfoDAO.findById(id);
	}

	/** 处理弹出层子画面 */
	public String getXml(String classFlag, String flag, String inputCode, String hspType,HttpServletRequest request) {
		ServletContext application = request.getSession().getServletContext();
		StringBuffer buffer = new StringBuffer();
		List<?> list = securityStaffBaseinfoDAO.findHspList(classFlag,flag, inputCode, hspType, Integer.valueOf((String)application.getAttribute("security.CURRECORD_TANCHUCENG")),
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

	@Override
	public void save(SecurityStaffBaseinfoForm form,
			HttpServletRequest request, String regCode) {
		ServletContext application = request.getSession().getServletContext();
		SecurityStaffBaseinfo data = new SecurityStaffBaseinfo();
		setData(form, data);
		//data.setStaffCode(staffCode)
		securityStaffBaseinfoDAO.save(data);
		
		SecuritySystemUsers sysUser = new SecuritySystemUsers();
		//String passwd = BaseSecurityInit.getProperty("STAFF_PASSWORD");
		String passwd = (String)application.getAttribute("security.STAFF_PASSWORD");
		String passwdMD5 = MD5.toMD5(passwd);
		sysUser.setSecurityStaffBaseinfoId(data.getId());
		sysUser.setPasswd(passwdMD5);
		securityStaffBaseinfoDAO.save(sysUser);
		
		SecurityLicense securityLicense = new SecurityLicense();
		securityLicense.setSecurityStaffBaseinfoId(data.getId());
		securityLicense.setRegTime(new Date());
		//String regCode = this.generateRegCode(5, 5);		
		securityLicense.setRegistCode(regCode);
		securityLicense.setStopDate(UtilTrans.transStringToDate(form.getStopDate(),"yyyy-MM-dd"));
		this.securityLicenseDAO.save(securityLicense);
	}

	public HSSFWorkbook getExcel(SecurityStaffBaseinfoForm form, String fileName, HttpServletRequest request) {
		/*取出导出到EXCEL的实际数据*/
		List<?> list = securityStaffBaseinfoDAO.getDataRegisterCode(form.getStaffCode(), form.getHspConfigBaseinfoId(), form.getName(), form.getCommConfigSexId(), form.getHspConfigBaseinfoName(), form.getInputCode(), form.getStaffId(), "", 0, Integer.MAX_VALUE);

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
		String str = "操作人员注册码信息";
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
		text = str + ":" + new SimpleDateFormat("yyyy-MM-dd").format(new Date());
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
		
		
		str = "卫生机构";
		Converter.setCellText(row, cell, 0, str, cellStyle);
		str = "姓名";
		Converter.setCellText(row, cell, 1, str, cellStyle);
		str = "用户名";
		Converter.setCellText(row, cell, 2, str, cellStyle);
		str = "注册码";
		Converter.setCellText(row, cell, 3, str, cellStyle);		

		if(list!=null && list.size()>0){
			for(int i=0; i<list.size(); i++){
				row = sheet.createRow((short)(i+3));
				Converter.setCellText(row, cell, 0, Converter.toBlank(this.transNullToString(((Object[]) list.get(i))[2])).trim(), cellStyle);				
				Converter.setCellText(row, cell, 1, Converter.toBlank(this.transNullToString(((Object[]) list.get(i))[3])).trim(), cellStyle);
				Converter.setCellText(row, cell, 2, Converter.toBlank(this.transNullToString(((Object[]) list.get(i))[1])).trim(), cellStyle);
				Converter.setCellText(row, cell, 3, Converter.toBlank(this.transNullToString(((Object[]) list.get(i))[4])).trim(), cellStyle);
				sheet.setColumnWidth((short)0, (short)5000);
				sheet.setColumnWidth((short)1, (short)5000);
				sheet.setColumnWidth((short)2, (short)5000);
				sheet.setColumnWidth((short)3, (short)10000);
			}
		}
		return workbook;
	}
	
}