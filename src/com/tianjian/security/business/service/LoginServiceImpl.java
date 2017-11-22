package com.tianjian.security.business.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

import com.tianjian.hsp.bean.HspConfigBaseinfo;
import com.tianjian.security.bean.SecurityConfigPublicClass;
import com.tianjian.security.bean.SecurityLicense;
import com.tianjian.security.bean.SecurityStaffBaseinfo;
import com.tianjian.security.bean.SecuritySystemUsers;
import com.tianjian.security.bean.SecurityUserVsRoles;
import com.tianjian.security.business.ILoginService;
import com.tianjian.security.dao.ILoginDAO;
import com.tianjian.security.dao.ISecurityLicenseDAO;
import com.tianjian.security.struts.form.LoginForm;
import com.tianjian.security.struts.form.LoginSecondForm;
import com.tianjian.util.ResourcesUtil;
import com.tianjian.util.comm.DateUtil;
import com.tianjian.util.comm.MD5;
import com.tianjian.util.comm.UtilTrans;
import com.tianjian.util.security.VersionDecrypt;

public class LoginServiceImpl implements ILoginService {

	private ILoginDAO loginDAO;
	private ISecurityLicenseDAO SecurityLicenseDAO;

	public ILoginDAO getLoginDAO() {
		return loginDAO;
	}

	public void setLoginDAO(ILoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}
	
	/**
	 * 检查版本注册信息.
	 */
	public void checkVersionRegistCode(LoginForm form,HttpServletRequest request) {
		String versionRegistCode;// 软件版本注册码
		String versionTemp;// 
		String versionStopDate ="1900-01-01";// 软件授权到期时间
		String versionUserName = ResourcesUtil.getValue("conf.security.securityInit", "security.java.business.service.LoginServiceImpl.item", request);//"北京天健源达科技有限公司";// 软件授权显示客户名称
		
		try {
			versionRegistCode = loginDAO.getRegistCode();
			if (versionRegistCode != null) {
				// -----分解注册码，为各参数赋值-----------------------------------------------
				versionTemp = VersionDecrypt.decData(versionRegistCode);
				StringReader reader = new StringReader(versionTemp);
				InputSource source = new InputSource(reader);
				SAXBuilder builder = new SAXBuilder();
				Document doc = null;
				try {
					doc = builder.build(source);
					Element root = doc.getRootElement();
					versionUserName = root.getChildText("userName");
					versionStopDate = root.getChildText("stopDate");
				} catch (Exception e) {
					//form.setMessage("软件未授权，请检查授权码码是否安装！");
					form.setMessage(ResourcesUtil.getValue("conf.security.securityInit", "security.java.business.service.LoginServiceImpl.warn", request));
					e.printStackTrace();
					 
			    }
				if (versionStopDate == null || versionStopDate.trim().equals("")) {
					versionStopDate = "1900-01-01";
				}
				boolean flag = DateUtil.isDateBefore(versionStopDate);
				if  (flag == false) {
					//form.setMessage("软件授权已经到期，请重新获取授权！");
					form.setMessage(ResourcesUtil.getValue("conf.security.securityInit", "security.java.business.service.LoginServiceImpl.warn1", request));
				}
				form.setVersionUserName(this.transNullToString(versionUserName));
				form.setVersionStopDate(this.transNullToString(versionStopDate));
			 
			} else {
				//form.setMessage("软件未授权，请检查授权码码是否安装！");
				form.setMessage(ResourcesUtil.getValue("conf.security.securityInit", "security.java.business.service.LoginServiceImpl.warn", request));
			}
		}
		catch (Exception e) {
			//form.setMessage("软件未授权，请检查授权码码是否安装！");
			form.setMessage(ResourcesUtil.getValue("conf.security.securityInit", "security.java.business.service.LoginServiceImpl.warn", request));
			e.printStackTrace();
		}
	}

	/**
	 * @return Returns the LoginDAO.
	 */
	public void checkUser(LoginForm form,HttpServletRequest request) {
		try {
			SecurityStaffBaseinfo user = loginDAO.getByCode(form.getStaffCode());
			if (user != null) {

	            //判断是否有分院
				List list = loginDAO.getByTenantId(user.getTenantId(),"IS_BRANCH_EXIST");
				if(list!=null&&list.size()>0){
					Object[] obj = (Object[])list.get(0);
					String objs1= String.valueOf(obj[1]);
					//是否存在分院， 0：没有分院  1：有分院
					if(objs1.equals("1")){
						List  hspl = loginDAO.getHspList(user.getTenantId());
						if(hspl!=null && hspl.size()>0){
							Map<String,String> maps = new HashMap();
							for(int i=0;i<hspl.size();i++){
								HspConfigBaseinfo hspbean = (HspConfigBaseinfo) hspl.get(i);
								String hspname = hspbean.getItemName();
								String hspid = hspbean.getId();
								maps.put(hspid, hspname);								
							}
							form.setMaps(maps);
						}
					}

				}
				
				ServletContext application = request.getSession().getServletContext();
				String registered_state = (String)application.getAttribute("security.REGISTER_FLAG");
				System.out.println(registered_state);
				if(registered_state != null && ("1").equals(registered_state)){
					String id = user.getId();// ----staff_id			
					Date dateNow = new Date();
					//检查用户的使用期限
					SecurityLicense securityLicense = SecurityLicenseDAO.findBySecurityStaffBaseinfoId(id);
					if(securityLicense!=null){
						if(securityLicense.getStopDate()!=null&&securityLicense.getStopDate().before(dateNow)){
							form.setMessage("该用户的使用期限已到，请联系管理员！");
							return;
						}
						/*else if(securityLicense.getStopDate()!=null&&DateUtils.addDays(securityLicense.getStopDate(),30).after(dateNow)){
							long t = securityLicense.getStopDate().getTime() - dateNow.getTime();
							long day = t / 1000 / 60 / 60 / 24;
							form.setMessage("你的帐号的授权仅剩下"+day+"天,为免影响使用,请及时与系统管理员联系!");
						}*/
						else{
							if(securityLicense.getStopDate()!=null){
								form.setStaffLicenseStopDate(UtilTrans.transDateToString(securityLicense.getStopDate(), "yyyy-MM-dd"));
							}
						}
					}else{
						form.setMessage("该用户没有注册信息，请联系管理员！");
						return;
					}
					
					
					SecuritySystemUsers securitySystemUsers = loginDAO.getById(id);
					if(securitySystemUsers != null && securitySystemUsers.getLicenseTag()!= null){	
						form.setStaffId(id);
						form.setName(this.transNullToString(user.getName()));// ----获取操作员姓名
						if (securitySystemUsers != null && securitySystemUsers.getPasswd() != null) {
							String password = securitySystemUsers.getPasswd();
							MD5 md5 = new MD5();
							String passwordMD5 = md5.getMD5ofStr(form.getPassword());
							if (!password.equals(passwordMD5)) {
								//form.setMessage("密码不正确，请重新输入！");
								form.setMessage(ResourcesUtil.getValue("conf.security.securityInit", "security.java.business.service.LoginServiceImpl.warn2", request));
								return;
							} else {
								String str_limited = this.transNullToZero(securitySystemUsers.getLimitedFlag());
								form.setSystemUserLimitedFlag(str_limited);// --獲取限制標示
								// -----------此處判斷限制標誌是否為空-------------------------------------------------------------------
								if (str_limited != null && str_limited == "1") {
									//form.setMessage("该用户已经被限制登录，请与管理单位联系！");
									form.setMessage(ResourcesUtil.getValue("conf.security.securityInit", "security.java.business.service.LoginServiceImpl.warn3", request));
									return;
								}
								// -----判断该用户是否在session存在，如果存在，不能登录-,如果不存在，set到session 登陆------------
								//
								//
								//
								//
								// ------------------------------------------------------------------------------------
							}
						} else {
							//form.setMessage("密码不正确，请重新输入！");
							form.setMessage(ResourcesUtil.getValue("conf.security.securityInit", "security.java.business.service.LoginServiceImpl.warn2", request));
							return;
						}
					}else{
						//form.setMessage("对不起，你的帐号未完成注册！");
						form.setMessage(ResourcesUtil.getValue("conf.security.securityInit", "security.java.business.service.LoginServiceImpl.warn5", request));
						return;
					}
				}else{
					String id = user.getId();// ----staff_id
					form.setStaffId(id);
					form.setName(this.transNullToString(user.getName()));// ----获取操作员姓名
					SecuritySystemUsers securitySystemUsers = loginDAO.getById(id);
					if (securitySystemUsers != null && securitySystemUsers.getPasswd() != null) {
						String password = securitySystemUsers.getPasswd();
						MD5 md5 = new MD5();
						String passwordMD5 = md5.getMD5ofStr(form.getPassword());
						if (!password.equals(passwordMD5)) {
							//form.setMessage("密码不正确，请重新输入！");
							form.setMessage(ResourcesUtil.getValue("conf.security.securityInit", "security.java.business.service.LoginServiceImpl.warn2", request));
							return;
						} else {
							String str_limited = this.transNullToZero(securitySystemUsers.getLimitedFlag());
							form.setSystemUserLimitedFlag(str_limited);// --獲取限制標示
							// -----------此處判斷限制標誌是否為空-------------------------------------------------------------------
							if (str_limited != null && str_limited == "1") {
								//form.setMessage("该用户已经被限制登录，请与管理单位联系！");
								form.setMessage(ResourcesUtil.getValue("conf.security.securityInit", "security.java.business.service.LoginServiceImpl.warn3", request));
								return;
							}
							// -----判断该用户是否在session存在，如果存在，不能登录-,如果不存在，set到session 登陆------------
							//
							//
							//
							//
							// ------------------------------------------------------------------------------------
						}
					} else {
						//form.setMessage("密码不正确，请重新输入！");
						form.setMessage(ResourcesUtil.getValue("conf.security.securityInit", "security.java.business.service.LoginServiceImpl.warn2", request));
						return;
					}
				}
				
				
				
				
				// -------------------------原系统附加的内容----------------------------------------------------------------
				form.setHospitalId(this.transNullToString(user.getHspConfigBaseinfoId()));// --获取操作员医疗机构ID
				form.setHomePageType(this.transNullToString(user.getHomePageType()));
				form.setStaffType(this.transNullToString(user.getStaffType()));
				form.setTenantId(this.transNullToString(user.getTenantId()));
				
				// HspConfigBaseinfo hspConfigBaseinfo = loginDAO.getHospitalById(form.getHospitalId());
				// if(hspConfigBaseinfo != null){
				// form.setCommConfigLocationId1(this.transNullToString(hspConfigBaseinfo.getCommConfigLocationId1()));
				// form.setCommConfigLocationId2(this.transNullToString(hspConfigBaseinfo.getCommConfigLocationId2()));
				// form.setCommConfigLocationId3(this.transNullToString(hspConfigBaseinfo.getCommConfigLocationId3()));
				// form.setCommCltId(this.transNullToString(hspConfigBaseinfo.getCommConfigLocationTownId()));
				// form.setCommClvId(this.transNullToString(hspConfigBaseinfo.getCommClvId()));
				// }
				// -------------------------原系统附加的内容----------------------------------------------------------------
			} else {
				//form.setMessage("用户名不存在，请重新输入！");
				form.setMessage(ResourcesUtil.getValue("conf.security.securityInit", "security.java.business.service.LoginServiceImpl.warn4", request));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**检查用户是否跟程序类型一致，如市级不能登录县级平台*/
	private void checkUserVsAppType(LoginForm form,SecurityStaffBaseinfo user,String AppType){
		 
	}
	
	
	public void getPublicClass(String UserId, LoginSecondForm loginSecondForm, HttpServletRequest request) {
		// TODO Auto-generated method stub
		StringBuffer str = new StringBuffer();
		try {
			// 取得角色ID
			List<?> roles = loginDAO.getBysecurityStaffBaseinfoId(UserId);
			if (roles != null && roles.size() > 0) {
				String[] rolesId = new String[roles.size()];
				for (int i = 0; i < roles.size(); i++) {
					SecurityUserVsRoles ro = (SecurityUserVsRoles) roles.get(i);
					rolesId[i] = ro.getSecurityConfigRolesId();
					// --------将获得的角色数组暂存在loginSecondForm中-------
					loginSecondForm.setRolesId(rolesId);
				}
				// 取得有权限的最低级模块类别
				if (rolesId != null && rolesId.length > 0) {
					for (int i = 0; i < rolesId.length; i++) {
						if (i == rolesId.length - 1) {
							str.append("'" + rolesId[i] + "'");
						} else {
							str.append("'" + rolesId[i] + "',");
						}
					}
					List<?> pub = loginDAO.getPublicClass(str.toString(),request);
					List<SecurityConfigPublicClass> pcList = new ArrayList<SecurityConfigPublicClass>();
					Map<String, List<SecurityConfigPublicClass>> childMap = new HashMap<String, List<SecurityConfigPublicClass>>();
					if(pub != null){
						for(Iterator<?> it = pub.iterator(); it.hasNext(); ){
							SecurityConfigPublicClass pc = (SecurityConfigPublicClass) it.next();
							final String parentId = pc.getParentId();
							if(StringUtils.isNotBlank(parentId)){
								List<SecurityConfigPublicClass> childList = childMap.get(parentId);
								if(childList == null){
									SecurityConfigPublicClass parent = this.loginDAO.getPublicClassById(parentId);
									pcList.add(parent);
									childList = new ArrayList<SecurityConfigPublicClass>();
									childMap.put(parentId, childList);
								}
								childList.add(pc);
							}else{
								pcList.add(pc);
							}
						}
					}
					Comparator<SecurityConfigPublicClass> pcCompare = new Comparator<SecurityConfigPublicClass>() {
						@Override
						public int compare(SecurityConfigPublicClass o1,
								SecurityConfigPublicClass o2) {
							if(o1.getSerialNo() != null){
								if(o2.getSerialNo() != null){
									return (int)(o1.getSerialNo() - o2.getSerialNo());
								}else{
									return 1;
								}
							}else{
								if(o2.getSerialNo() != null){
									return -1;
								}else{
									return 0;
								}
							}
						}
					};
					//按序号排序一级模块类别
					Collections.sort(pcList, pcCompare);
					
					loginSecondForm.setPcList(pcList);
					loginSecondForm.setChildMap(childMap);
				}
			}
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**查找某一模块类别的上一级模块类别*/
	private SecurityConfigPublicClass getParentPublicClassById(String id) throws Exception{
		return loginDAO.getParentPublicClassById(id);
	}
	
	/**查找某一模块类别的下一级模块类别*/
	public List<SecurityConfigPublicClass> getPublicClassByParentId(String parentId) throws Exception{
		return loginDAO.getPublicClassByParentId(parentId);
	}
	

	/**查找某一模块类别的最高级模块类别*/
	private SecurityConfigPublicClass getStartPublicClassById(SecurityConfigPublicClass scpc) throws Exception{
		if(scpc.getLevelFlag()==1){
			return scpc;
		}else{
			scpc = this.getParentPublicClassById(scpc.getId());
			return this.getStartPublicClassById(scpc);
		}
	}
	
	/**
	 * 查找有权限的某一模块类别的所有最低级模块类别
	 * */
	private List<SecurityConfigPublicClass>  getEndPublicClassById(String publicClassId,String userId, HttpServletRequest request)  throws Exception{
		List<SecurityConfigPublicClass> ls1 = new ArrayList<SecurityConfigPublicClass>();
		this.getEndPublicClassByParentId2(publicClassId, ls1);
		
		List<SecurityConfigPublicClass> ls2 = null;
		// 取得角色ID
		StringBuffer str = new StringBuffer();
		List<?> roles = loginDAO.getBysecurityStaffBaseinfoId(userId);
		if (roles != null && roles.size() > 0) {
			for (int i = 0; i < roles.size(); i++) {
				SecurityUserVsRoles ro = (SecurityUserVsRoles) roles.get(i);
				String rolesIdTemp = ro.getSecurityConfigRolesId();
				if(i == roles.size() - 1){
					str.append("'" + rolesIdTemp + "'");
				}else{
					str.append("'" + rolesIdTemp + "',");
				}
			}
			ls2 = (List<SecurityConfigPublicClass>) this.loginDAO.getPublicClass(str.toString(),request);
		}	
			
		
		List<SecurityConfigPublicClass> ls3 = new ArrayList<SecurityConfigPublicClass>();
		if(ls1!=null&&ls1.size()>0&&ls2!=null&&ls2.size()>0){
			for(int i=0;i<ls1.size();i++){
				for(int j=0;j<ls2.size();j++){
					if(ls1.get(i).getId().equals(ls2.get(j).getId())){
						ls3.add(ls1.get(i));
					}
				}
			}
		}
		
		return ls3;
	}
	
	/**查找某一模块类别的所有最低级模块类别2*/
	private void getEndPublicClassByParentId2(String parentId,List<SecurityConfigPublicClass> endls) throws Exception{
		List<SecurityConfigPublicClass> ls = this.getPublicClassByParentId(parentId);
		if(ls!=null&&ls.size()>0){
			for(int i=0;i<ls.size();i++){
				this.getEndPublicClassByParentId2(ls.get(i).getId(), endls);
			}
		}else{
			endls.add(this.loginDAO.getPublicClassById(parentId));
		}
	}
	
	
	
	
	// 把null值转化为空
	private String transNullToString(Object obj) {
		String temp = "";
		if (obj != null) {
			temp = String.valueOf(obj);
		}
		return temp.trim();
	}

	public String transNullToZero(Object obj) {
		String temp = "0";
		if (obj != null) {
			temp = String.valueOf(obj).trim();
		}
		return temp;
	}

	@Override
	public String getEndPublicClass(String publicClassId,String staffId,HttpServletRequest request) throws Exception {
		List<SecurityConfigPublicClass> ls = this.getEndPublicClassById(publicClassId, staffId, request);
		StringBuffer str = new StringBuffer();
		if(ls!=null&&ls.size()>0){
			str.append("<info>");
			
			for(int i=0;i<ls.size();i++){
				str.append("<publicClass>");
				SecurityConfigPublicClass temp = ls.get(i);
				str.append("<publicClassId>"+this.transNullToString(temp.getId())+"</publicClassId>");
				str.append("<publicClassName>"+this.transNullToString(temp.getClassName())+"</publicClassName>");
				str.append("<publicClassPicPath>"+this.transNullToString(temp.getPicPath())+"</publicClassPicPath>");
				str.append("<publicClassComments>"+this.transNullToString(temp.getComments())+"</publicClassComments>");
				str.append("<publicClassSysFlag>"+this.transNullToString(temp.getSysFlag())+"</publicClassSysFlag>");
				str.append("<publicClassRedirectUrl>"+this.transNullToString(temp.getRedirectUrl())+"</publicClassRedirectUrl>");
				str.append("</publicClass>");
			}
			str.append("</info>");
		}
		return str.toString();
	}
	
	private HttpServletRequest request;
	
	
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public ISecurityLicenseDAO getSecurityLicenseDAO() {
		return SecurityLicenseDAO;
	}

	public void setSecurityLicenseDAO(ISecurityLicenseDAO securityLicenseDAO) {
		SecurityLicenseDAO = securityLicenseDAO;
	}


}
