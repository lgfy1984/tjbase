package com.tianjian.security.struts.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.security.business.ISecurityStaffPasswordInitService;
import com.tianjian.security.struts.form.SecurityStaffPasswordInitForm;
import com.tianjian.security.struts.form.SessionForm;
import com.tianjian.util.ResourcesUtil;
import com.tianjian.util.comm.PageBean;

public class SecurityStaffPasswordInitAction extends BaseAction {

	public SecurityStaffPasswordInitAction() {}

	private ISecurityStaffPasswordInitService service;

	public ISecurityStaffPasswordInitService getService() {
		return service;
	}

	public void setService(ISecurityStaffPasswordInitService service) {
		this.service = service;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		if (checkUser(request, response) == null) {
			return mapping.findForward("noLogin");
		}
		String verbId = request.getParameter("verbId");
		if (verbId.equals("init")) {
			return init(mapping, form, request, response);
		} else if (verbId.equals("query")) {
			return query(mapping, form, request, response);
		} else if (verbId.equals("update")) {
			return update(mapping, form, request, response);
		} else if(verbId.equals("getHsp")){
			return getHsp(mapping, form, request, response);
		} else {
			return mapping.findForward("fail");
		}
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			SecurityStaffPasswordInitForm spiform = (SecurityStaffPasswordInitForm) form;
			ServletContext application = request.getSession().getServletContext();
			spiform.setPasswd((String)application.getAttribute("security.STAFF_PASSWORD"));
			//spiform.setPasswd(BaseSecurityInit.getProperty("STAFF_PASSWORD"));
			service.update(spiform);
			//String xmlString = this.getXMLMessage("密码初始化成功！");
			String xmlString = this.getXMLMessage(ResourcesUtil.getValue("conf.security.securityInit", "security.java.struts.action.SecurityStaffPasswordInitAction.warn", request));
			writeResponse(response, xmlString);
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
		return null;
	}

	// //////Ajax-start
	private String getXMLMessage(String message) {
		String xmlString = "";
		xmlString = xmlString + "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		xmlString = xmlString + "<root>";
		xmlString = xmlString + "<message>" + message + "</message>";
		xmlString = xmlString + "</root>";
		return xmlString;
	}

	public void writeResponse(HttpServletResponse response, String xmlString) throws IOException {
		response.setContentType("text/xml");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(xmlString);
	}

	// //////Ajax-stop
	public ActionForward query(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			SecurityStaffPasswordInitForm ssform = (SecurityStaffPasswordInitForm) form;
			
			HttpSession session = request.getSession(true);
			SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
			ssform.setHspConfigId(sessionForm.getStaffHospitalId());
			// ////// page start ////////////////////////
			ServletContext application = request.getSession().getServletContext();
			PageBean pb = new PageBean();
			int count;
			int page = 0;
			int recordCount = service.getStaffSelectCount(ssform.getStaffId(), ssform.getName(), ssform.getInputCode(), ssform.getItemCode(),ssform.getStaffHspId(),ssform.getHspConfigId());
			pb.setCount(recordCount);
			String pageString = request.getParameter("page");
			//ServletContext application = request.getSession().getServletContext();
			//int pageSize = Integer.parseInt((String)application.getAttribute("EHRPProject_basesecurity.PAGE_SIZE"));
			//int pageSize = 10;
			int pageSize = 10;
			if(request.getSession().getAttribute("page_282881f53464507001346450701f0000")!=null){
				pageSize = Integer.parseInt((String)request.getSession().getAttribute("page_282881f53464507001346450701f0000"));
			}else{
				
				pageSize = Integer.parseInt((String)application.getAttribute("security.PAGE_SIZE"));
			}
			pb.setPageSize(pageSize);
			if (pageString == null || pageString.equals("") || pageString.equals("0")) {
				page = 1;
				pb.setPage(1);
				count = (page - 1) * pageSize;
			} else {
				page = Integer.parseInt(pageString);
				pb.setPage(page);
				count = (page - 1) * pageSize;
			}
			request.setAttribute("pb", pb);
			// ////// page end ////////////////////////
			service.getSearch(ssform, count, pageSize);
			if (recordCount == 0) {
				//ssform.setMessage("没有查询到数据，请重新输入查询条件！");
				ssform.setMessage(ResourcesUtil.getValue("conf.security.securityInit", "security.java.struts.action.SecurityStaffPasswordInitAction.warn1", request));
			}
			service.searchInit(ssform);
			request.setAttribute("securityStaffPasswordInit", ssform);
			return mapping.findForward("query");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			SecurityStaffPasswordInitForm ssform = (SecurityStaffPasswordInitForm) form;
			ssform.setStaffId("");
			HttpSession session = request.getSession(true);
			SessionForm sessionForm = (SessionForm) session.getAttribute("sessionForm");
			if (sessionForm != null) {
				ssform.setStaffHspId(sessionForm.getStaffHospitalId());
			}
			service.searchInit(ssform);
			request.setAttribute("securityStaffPasswordInit", ssform);
			return mapping.findForward("query");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
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
		//设置请求以及返回数据的编码类型
		//hspType 1为除去卫生服务站2为只包括服务站和服务中心3为所有
    	String hspType = request.getParameter("hspType");
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();
			String xmlString = service.getXml(flag, inputCode,hspType,request);
			out.println(xmlString);    	
			out.close();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}
}