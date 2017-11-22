package com.tianjian.security.struts.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.comm.business.ICommConfigInputDictService;
import com.tianjian.security.business.ISecurityStaffBaseinfoService;
import com.tianjian.security.struts.form.SecurityStaffBaseinfoForm;
import com.tianjian.security.struts.form.SessionForm;
import com.tianjian.util.ChineseSpelling;
import com.tianjian.util.PinyinUtil;
import com.tianjian.util.ResourcesUtil;
import com.tianjian.util.comm.Mail;
import com.tianjian.util.comm.PageBean;
import com.tianjian.util.comm.UtilTrans;


public class SecurityStaffBaseinfoAction extends BaseAction {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(SecurityStaffBaseinfoAction.class);

	public SecurityStaffBaseinfoAction(){}

	private ISecurityStaffBaseinfoService service;        
	//input
	private ICommConfigInputDictService commConfigInputDictService;

	public ISecurityStaffBaseinfoService getService() {
		return service;
	}

	public void setService(ISecurityStaffBaseinfoService service) {
		this.service = service;
	}

	//input
	public ICommConfigInputDictService getCommConfigInputDictService() {
		return commConfigInputDictService;
	}
	public void setCommConfigInputDictService(ICommConfigInputDictService commConfigInputDictService) {
		this.commConfigInputDictService = commConfigInputDictService;
	}
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){        
		if (checkUser(request, response) == null) {
			return mapping.findForward("noLogin");
		}
		String verbId = request.getParameter("verbId");
		if(verbId.equals("add")){
			return add(mapping, form, request, response);
		} else if(verbId.equals("addInit")){
			return addInit(mapping, form, request, response);
		} else if(verbId.equals("query")){
			return query(mapping, form, request, response);
		} else if(verbId.equals("queryDetail")){
			return query(mapping, form, request, response);
		} else if(verbId.equals("query4Web")){
			return query4Web(mapping, form, request, response);
		} else if(verbId.equals("change")){
			return change(mapping, form, request, response);
		} else if(verbId.equals("update")){
			return update(mapping, form, request, response);
		} else if(verbId.equals("updateInit")){
			return updateInit(mapping, form, request, response);
		} else if(verbId.equals("detail")){
			return detail(mapping, form, request, response);
		} else if(verbId.equals("delete")){
			return delete(mapping, form, request, response);
		} else if(verbId.equals("getHsp")){
			return getHsp(mapping, form, request, response);
		}else if(verbId.equals("getLogin")){
			return getLogin(mapping, form, request, response);
		}else if(verbId.equals("verification")){
			return verification(mapping, form, request, response);
		}else if(verbId.equals("initDetail")){
			return this.init(mapping, form, request, response);
		}else if(verbId.equals("elsExport")){
			return this.elsExport(mapping, form, request, response);
		}else if(verbId.equals("init")){
			return this.init(mapping, form, request, response);
		}else if(verbId.equals("registerCode")){
			return this.queryRegisterCode(mapping, form, request, response);
		}else if(verbId.equals("getPY")){
			return this.getPY(mapping, form, request, response);
		} else {
			return mapping.findForward("fail");
		}
	}

	public ActionForward getLogin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {  
		SecurityStaffBaseinfoForm hosform = (SecurityStaffBaseinfoForm)form;
		HttpSession session = request.getSession(true);
		SessionForm sessionForm = (SessionForm) session.getAttribute("sessionForm");
		if(sessionForm != null && !"".equals(sessionForm)){
			if(sessionForm.getStaffId()!=null && !"".equals(sessionForm.getStaffId())){
				hosform.setStaffId(sessionForm.getStaffId());
				this.getService().getLogin(hosform,request);
			}
		}
		request.setAttribute("securityStaffBaseinfo", hosform);		
		return mapping.findForward("loginDetail");
	}
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {  
		SecurityStaffBaseinfoForm hosform = (SecurityStaffBaseinfoForm)form;
		ServletContext application = request.getSession().getServletContext();
		String  registerFlag =  (String)application.getAttribute("security.REGISTER_FLAG");
		String email = (String)application.getAttribute("security.E_MAIL");
		String email_address = (String)application.getAttribute("security.EMAIL_ADDRESS");
		String email_userName = (String)application.getAttribute("security.EMAIL_NAME");
		String email_password = (String)application.getAttribute("security.EMAIL_PASSWORD");
		if(email != null && email_address != null && email_userName != null && email_password  != null){
			try {
				//检查是否已经存在 用户名了
				//String checkData = service.checkData(hosform.getStaffCode(),request);
				/*String checkData2 = service.checkSecurityLicense(hosform.getStaffCode());
				if((checkData != null && checkData.trim().length() > 0) || (checkData2 != null && checkData2.trim().length() > 0)){
					//hosform.setMessage("代码已经存在或已经被注册，请重新输入！");
					hosform.setMessage(ResourcesUtil.getValue("conf.security.securityInit", "security.java.struts.action.SecuritySecurityStaffBaseinfoAction.warn", request));
					service.addInit(hosform, request);
					request.setAttribute("securityStaffBaseinfo", hosform);		        
					return  mapping.findForward("add");
				}*/
				// inpucode
				hosform.setInputCode(commConfigInputDictService.getInputCode(hosform.getName()));
				hosform.setPinyinInputCode(PinyinUtil.hanziToPinyin(UtilTrans.transNullToString(hosform.getName()),"").toUpperCase());
				HttpSession session = request.getSession (true); 
				SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
				hosform.setCreateUserId(sessionForm.getStaffId());
				hosform.setCreateUserName(sessionForm.getStaffName());
				hosform.setCreateDate(UtilTrans.transDateToStringFull(new Date()));			
				String regCode = this.service.generateRegCode(5, 5);
				
				request.setAttribute("securityStaffBaseinfo", hosform);		
				//如果需要注册才能登录,则发送注册
				if(registerFlag != null && ("1").equals(registerFlag)){
					boolean foo = true;
					String mailbody = "姓名:" + hosform.getName() + "<br>" + "注册码:" + regCode;
					Mail themail = new Mail(email);
					themail.setNeedAuth(true);			
						if (themail.setSubject("系统注册码发放") == false){
							foo = false;
						}
						//邮件内容 支持html 如 <font color=red>欢迎光临</font> <a href=\"http://www.laabc.com\">啦ABC</a>
						if (themail.setBody(mailbody) == false){
							foo = false;
						}
						//收件人邮箱
						if (themail.setTo(hosform.getEmail()) == false){
							  foo = false;
						}
						//发件人邮箱
						if (themail.setFrom(email_address) == false){
							foo = false;
						}
						//设置附件
						//if (themail.addFileAffix("#######") == false)
						//return; // 附件在本地机子上的绝对路径
						themail.setNamePass(email_userName,email_password); // 用户名与密码
						if (themail.sendout() == false){
							foo = false;
						}
						String message = null;
					
					service.save(hosform,request,regCode);
					
					if(foo){
						message = "添加成功！";
					}else{
						message = "添加成功！但注册码发送至邮箱失败！";
					}
					//service.saveSecurityLicense(hosform.getStaffCode(), regCode);
					request.setAttribute("message", message);
					return this.addInit(mapping, form, request, response);
				}else{
					service.save(hosform,request,regCode);
					//service.saveSecurityLicense(hosform.getStaffCode(), regCode);
					String message = "添加成功!";
					request.setAttribute("message", message);
					return this.addInit(mapping, form, request, response);
				}			
			} catch(Exception e) {
				return mapping.findForward("fail");
			}
		}else{
			hosform.setMessage(ResourcesUtil.getValue("conf.security.securityInit", "security.java.findpassword.warn2", request));
			request.setAttribute("securityStaffBaseinfo", hosform);
			return mapping.findForward("add");
		}	
	}

	public ActionForward query(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String verbId = request.getParameter("verbId");
			SecurityStaffBaseinfoForm hosform = (SecurityStaffBaseinfoForm)form;
			HttpSession session = request.getSession(true);
			SessionForm sessionForm = (SessionForm) session.getAttribute("sessionForm");
			if(sessionForm!=null && !"".equals(sessionForm)){
				if(sessionForm.getStaffId()!=null && !"".equals(sessionForm.getStaffId())){
					hosform.setStaffId(sessionForm.getStaffId());
					hosform.setHspConfigBaseinfoId(sessionForm.getStaffHospitalId());
				}
			}
			////////	page start   ////////////////////////
			PageBean pb = new PageBean();
			int count;
			int page = 0;
			int recordCount = service.getCount(hosform.getStaffCode()
					, hosform.getHspConfigBaseinfoId()
					, hosform.getName()
					, hosform.getCommConfigSexId()
					, hosform.getHspConfigBaseinfoName()
					, hosform.getInputCode()
					, hosform.getStaffId()
			);
			if(recordCount == 0){
				//查询条件有误，未查询到结果
				String str = ResourcesUtil.getValue("conf.security.securityInit", "comm.java.security.warn1", request);
				hosform.setMessage(str);
			}
			pb.setCount(recordCount);
			String pageString = request.getParameter("page");
			//ServletContext application = request.getSession().getServletContext();
			//int pageSize = Integer.parseInt((String)application.getAttribute("EHRPProject_basesecurity.PAGE_SIZE"));
			//int pageSize = 10;
			int pageSize = 10;
			if(request.getSession().getAttribute("page_282881f5346450530134645053210000")!=null){
				pageSize = Integer.parseInt((String)request.getSession().getAttribute("page_282881f5346450530134645053210000"));
			}else{
				ServletContext application = request.getSession().getServletContext();
				pageSize = Integer.parseInt((String)application.getAttribute("security.PAGE_SIZE"));
			}
			
			pb.setPageSize(pageSize);
			if(pageString == null || pageString.equals("") || pageString.equals("0")){
				page = 1;
				pb.setPage(1);
				count = (page - 1) * pageSize;
			}else{	        	
				page = Integer.parseInt(pageString);
				pb.setPage(page);
				count = (page - 1) * pageSize  ;
			}

			request.setAttribute("pb",pb );
			////////	page end   ////////////////////////

			service.getSearch(hosform, count, pageSize);
			service.searchInit(hosform, request);
			request.setAttribute("securityStaffBaseinfo", hosform);		        
			if(verbId.equals("queryDetail")){
				return mapping.findForward("queryDetail");
			}else{
				return mapping.findForward("query");
			}
			
		} catch(Exception e) {
			return mapping.findForward("fail");
		}
	}
	
	
	public ActionForward queryRegisterCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			SecurityStaffBaseinfoForm hosform = (SecurityStaffBaseinfoForm)form;
			HttpSession session = request.getSession(true);
			SessionForm sessionForm = (SessionForm) session.getAttribute("sessionForm");
			if(sessionForm!=null && !"".equals(sessionForm)){
				if(sessionForm.getStaffId()!=null && !"".equals(sessionForm.getStaffId())){
					hosform.setStaffId(sessionForm.getStaffId());
					hosform.setHspConfigBaseinfoId(sessionForm.getStaffHospitalId());
				}
			}
			////////	page start   ////////////////////////
			PageBean pb = new PageBean();
			int count;
			int page = 0;
			int recordCount = service.getCount(hosform.getStaffCode()
					, hosform.getHspConfigBaseinfoId()
					, hosform.getName()
					, hosform.getCommConfigSexId()
					, hosform.getHspConfigBaseinfoName()
					, hosform.getInputCode()
					, hosform.getStaffId()
			);
			if(recordCount == 0){
				//查询条件有误，未查询到结果
				String str = ResourcesUtil.getValue("conf.security.securityInit", "comm.java.security.warn1", request);
				hosform.setMessage(str);
			}
			pb.setCount(recordCount);
			String pageString = request.getParameter("page");
			//ServletContext application = request.getSession().getServletContext();
			//int pageSize = Integer.parseInt((String)application.getAttribute("EHRPProject_basesecurity.PAGE_SIZE"));
			//int pageSize = 10;
			int pageSize = 10;
			if(request.getSession().getAttribute("page_282881f5346450530134645053210000")!=null){
				pageSize = Integer.parseInt((String)request.getSession().getAttribute("page_282881f5346450530134645053210000"));
			}else{
				ServletContext application = request.getSession().getServletContext();
				pageSize = Integer.parseInt((String)application.getAttribute("security.PAGE_SIZE"));
			}
			
			pb.setPageSize(pageSize);
			if(pageString == null || pageString.equals("") || pageString.equals("0")){
				page = 1;
				pb.setPage(1);
				count = (page - 1) * pageSize;
			}else{	        	
				page = Integer.parseInt(pageString);
				pb.setPage(page);
				count = (page - 1) * pageSize  ;
			}

			request.setAttribute("pb",pb );
			////////	page end   ////////////////////////

			service.getSearchRegisterCode(hosform, count, pageSize);
			service.searchInit(hosform, request);
			request.setAttribute("securityStaffBaseinfo", hosform);		        
			return mapping.findForward("queryRegisterCode");
		} catch(Exception e) {
			return mapping.findForward("fail");
		}
	}
	private ActionForward elsExport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			SecurityStaffBaseinfoForm hosform = (SecurityStaffBaseinfoForm) form;
			HttpSession session = request.getSession(true);
			SessionForm sessionForm = (SessionForm) session.getAttribute("sessionForm");
			if(sessionForm!=null && !"".equals(sessionForm)){
				if(sessionForm.getStaffId()!=null && !"".equals(sessionForm.getStaffId())){
					hosform.setStaffId(sessionForm.getStaffId());
					hosform.setHspConfigBaseinfoId(sessionForm.getStaffHospitalId());
				}
			}
			String fileName = "Operator registration code infomations.xls";
			fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");

			//response.setContentType("application/vnd.ms-excel");	
			response.setContentType("application/application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename="+fileName);

			HSSFWorkbook workbook = this.getService().getExcel(hosform, fileName, request);
			if(workbook==null){
				String message = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.common.userExcelCreateFail", request);
				hosform.setMessage(message);
			}else{
				// 新建一输出文件流
				ServletOutputStream fOut = response.getOutputStream();
				// 把相应的Excel 工作簿存盘
				workbook.write(fOut);
				fOut.flush();
				fOut.close();
			}
			return null;
		} catch(Exception e) {
			logger.warn(e);
			return mapping.findForward("fail");
		}
	}
	public ActionForward query4Web(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {

			SecurityStaffBaseinfoForm hosform = (SecurityStaffBaseinfoForm)form;
			HttpSession session = request.getSession(true);
			SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
			hosform.setHspConfigBaseinfoId(sessionForm.getStaffHospitalId());
			////////	page start   ////////////////////////
			PageBean pb = new PageBean();
			int count;
			int page = 0;
			int recordCount = service.getCount(hosform.getStaffCode()
					, hosform.getHspConfigBaseinfoId()
					, hosform.getName()
					, hosform.getCommConfigSexId()
					, ""
					, hosform.getInputCode()
					, hosform.getStaffId()
			);

			pb.setCount(recordCount);
			String pageString = request.getParameter("page");
			ServletContext application = request.getSession().getServletContext();
			int pageSize = Integer.parseInt((String)application.getAttribute("EHRPProject_basesecurity.PAGE_SIZE"));
			//int pageSize = 10;
			pb.setPageSize(pageSize);
			if(pageString == null || pageString.equals("") || pageString.equals("0")){
				page = 1;
				pb.setPage(1);
				count = (page - 1) * pageSize;
			}else{	        	
				page = Integer.parseInt(pageString);
				pb.setPage(page);
				count = (page - 1) * pageSize  ;
			}

			request.setAttribute("pb",pb );
			////////	page end   ////////////////////////

			service.getSearch(hosform, count, pageSize);
			service.searchInit(hosform, request);
			request.setAttribute("securityStaffBaseinfo", hosform);		        
			return mapping.findForward("query4Web");
		} catch(Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward change(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {

			SecurityStaffBaseinfoForm hosform = (SecurityStaffBaseinfoForm)form;
			HttpSession session = request.getSession(true);
			SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
			hosform.setHspConfigBaseinfoId(sessionForm.getStaffHospitalId());
			////////	page start   ////////////////////////
			PageBean pb = new PageBean();
			int count;
			int page = 0;
			int recordCount = service.getCount(hosform.getStaffCode()
					, hosform.getHspConfigBaseinfoId()
					, hosform.getName()
					, hosform.getCommConfigSexId()
					, ""
					, hosform.getInputCode()
					, hosform.getStaffId()
			);

			pb.setCount(recordCount);
			String pageString = request.getParameter("page");
			int pageSize = 10;
			pb.setPageSize(pageSize);
			if(pageString == null || pageString.equals("") || pageString.equals("0")){
				page = 1;
				pb.setPage(1);
				count = (page - 1) * pageSize;
			}else{	        	
				page = Integer.parseInt(pageString);
				pb.setPage(page);
				count = (page - 1) * pageSize  ;
			}
			request.setAttribute("pb",pb );
			////////	page end   ////////////////////////

			service.getSearch(hosform, count, pageSize);
			service.searchInit(hosform, request);
			request.setAttribute("securityStaffBaseinfo", hosform);		        

			return mapping.findForward("change");
		} catch(Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward updateInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {

			SecurityStaffBaseinfoForm hosform = (SecurityStaffBaseinfoForm)form;
			service.updateInit(hosform, request);
			request.setAttribute("securityStaffBaseinfo", hosform);		        
			return  mapping.findForward("update");

		} catch(Exception e) {
			e.printStackTrace();
			return mapping.findForward("fail");
		}	
	}

	public ActionForward addInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			SecurityStaffBaseinfoForm hosform = new SecurityStaffBaseinfoForm();
			service.addInit(hosform, request);
			hosform.setStopDate("2099-12-31");//默认设置
			request.setAttribute("securityStaffBaseinfo", hosform);			
			return  mapping.findForward("add");
		} catch(Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward detail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {

			SecurityStaffBaseinfoForm hosform = (SecurityStaffBaseinfoForm)form;
			// inpucode
			hosform.setInputCode(commConfigInputDictService.getInputCode(hosform.getName()));
			service.getDetail(hosform, request);
			hosform.setMessage("");
			request.setAttribute("securityStaffBaseinfo", hosform);		     
			return  mapping.findForward("detail");

		} catch(Exception e) {
			return mapping.findForward("fail");
		}	
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			SecurityStaffBaseinfoForm hosform = (SecurityStaffBaseinfoForm)form;
			hosform.setInputCode(commConfigInputDictService.getInputCode(hosform.getName()));
			hosform.setPinyinInputCode(PinyinUtil.hanziToPinyin(UtilTrans.transNullToString(hosform.getName()),"").toUpperCase());
			HttpSession session = request.getSession (true); 
			SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
			hosform.setCreateUserId(sessionForm.getStaffId());
			hosform.setCreateUserName(sessionForm.getStaffName());
			service.update(hosform);
			SecurityStaffBaseinfoForm formNew = new SecurityStaffBaseinfoForm();
			return  this.query(mapping
					, formNew
					, request
					, response);

		} catch(Exception e) {
			return mapping.findForward("fail");
		}	
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		SecurityStaffBaseinfoForm hosform = (SecurityStaffBaseinfoForm)form;
		try{    		
			   String  idDeleting = hosform.getId();			 	
					hosform.setId(idDeleting);
					service.deleteSecurityLicense(hosform.getId());
					service.delete(hosform);
			 
			
			return  this.query(mapping
					, hosform
					, request
					, response);
		}catch(org.springframework.dao.DataIntegrityViolationException e){
			//e.printStackTrace();
			//不敢提示，前台返回使用history.go(-1),其中的message没有清空，出现提示错乱的情况
			//hosform.setMessage("该数据已经被关联，在关联未消除之前不能被删除！");
			hosform.setId("");
			hosform.setStaffCode("");
			hosform.setHspConfigBaseinfoId("");
			hosform.setName("");
			hosform.setCommConfigSexId("");
			hosform.setInputCode("");
			return this.query(mapping, form, request, response);
		}catch(Exception e) {
			return mapping.findForward("fail");
		}
	}

	/**构造XML的内容格式*/
	public String getXMLData(String[] key, String[] value) {
		String xmlString = "";

		xmlString = xmlString + "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		xmlString = xmlString + "<root>";
		if(key != null){
			xmlString = xmlString + "<index>" + key.length + "</index>";
			for (int i = 0; i < key.length; i++) {
				xmlString = xmlString + "<key>" + key[i] + "</key>";
				xmlString = xmlString + "<value>" + value[i] + "</value>";
			}
		} else {
			xmlString = xmlString + "<index>0</index>";
		}
		xmlString = xmlString + "</root>";

		return xmlString;
	}
	public ActionForward verification(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		try{
			SecurityStaffBaseinfoForm hosForm = (SecurityStaffBaseinfoForm)form;
			 //SecurityStaffBaseinfo securitySystemUsers = service.checkData(hosForm);
			 request.setCharacterEncoding("UTF-8");
			 response.setContentType("text/html;charset=utf-8");
			 PrintWriter out = response.getWriter();
			 String checkData = service.checkData(hosForm.getStaffCode(), request);		 
			 if(checkData != null && checkData.trim().length() > 0){
				   out.println(ResourcesUtil.getValue("conf.security.securityInit", "security.java.struts.action.SecuritySecurityStaffBaseinfoAction.warn1", request));
			 }else{
				   out.println(ResourcesUtil.getValue("conf.security.securityInit", "security.java.struts.action.SecuritySecurityStaffBaseinfoAction.warn2", request));
			 } 
			out.close();
		}catch(Exception e){
			e.printStackTrace();
			return mapping.findForward("fail");
		}
		return null;
	}

	public void writeResponse(HttpServletResponse response, String xmlString) throws IOException {
		response.setContentType("text/xml");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(xmlString);
	}

	/**弹出层*/
	public ActionForward getHsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
//		/*限定所在地区医疗*/
//		HttpSession session = request.getSession (true); 
//		SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
//		String staffId =sessionForm.getStaffId();
//		String staffHospitalId =sessionForm.getStaffHospitalId();
				
		String inputCode = request.getParameter("inputCode"); 
		//------------根据标示符获取结果list-----1拼音 2代码 3汉字---------------------------------------------------
		//String flag = "1";
		String flag = request.getParameter("flag");//
		String classFlag = request.getParameter("classFlag");
		//设置请求以及返回数据的编码类型
		//hspType 1为除去卫生服务站2为只包括服务站和服务中心3为所有
    	String hspType = request.getParameter("hspType");
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();
			String xmlString = service.getXml(classFlag, flag, inputCode, hspType,request);
			out.println(xmlString);    	
			out.close();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}
	
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String verbId = request.getParameter("verbId");
			SecurityStaffBaseinfoForm ssform = (SecurityStaffBaseinfoForm) form;
			request.setAttribute("securityStaffBaseinfo", ssform);
			if(verbId.equals("initDetail")){
				return mapping.findForward("queryDetail");
			}else{
				return mapping.findForward("query");
			}
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}
	
	public ActionForward getPY(ActionMapping mapping
			, ActionForm form
			, HttpServletRequest request
			, HttpServletResponse response){
		try{
			String name = new String(request.getParameter("name"));
			ChineseSpelling chineseSpelling = new ChineseSpelling();
			name = java.net.URLDecoder.decode(name,"UTF-8");
			String enName = chineseSpelling.getSelling(name);
			String xmlString = getXMLMessage(enName);
			writeResponse(response, xmlString);
	    }catch(Exception e) {
	    	e.printStackTrace();
			return mapping.findForward("fail");
		}
	    return null;
	}
	
	private String getXMLMessage(String message) {
		String xmlString = "";
		
		xmlString = xmlString + "<?xml version=\"1.0\" encoding=\"gb2312\"?>";
		xmlString = xmlString + "<root>";
		xmlString = xmlString + "<message>" + message + "</message>";
		xmlString = xmlString + "</root>";
		
		return xmlString;
	}
}