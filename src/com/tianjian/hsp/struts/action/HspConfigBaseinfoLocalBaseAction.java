package com.tianjian.hsp.struts.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.core.struts.action.Action;
import com.tianjian.hsp.business.IHspConfigBaseinfoLocalBaseService;
import com.tianjian.security.struts.form.SessionForm;

public class HspConfigBaseinfoLocalBaseAction extends Action{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(HspConfigBaseinfoLocalBaseAction.class);

	private IHspConfigBaseinfoLocalBaseService hspConfigBaseinfoBaseService;
	
	public IHspConfigBaseinfoLocalBaseService getHspConfigBaseinfoBaseService() {
		return hspConfigBaseinfoBaseService;
	}

	public void setHspConfigBaseinfoBaseService(
			IHspConfigBaseinfoLocalBaseService hspConfigBaseinfoBaseService) {
		this.hspConfigBaseinfoBaseService = hspConfigBaseinfoBaseService;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {	
		String verbId = request.getParameter("verbId");
		logger.info("verbId=" + verbId);
		if(verbId.equals("getHsp")){
			return this.getHsp(mapping, form, request, response);
		}else{
			return mapping.findForward("fail");
		}
	}

	/**弹出层*/
	private ActionForward getHsp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		//			/*限定所在地区医疗*/
		HttpSession session = request.getSession (true); 
		SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
		String inputCode = request.getParameter("inputCode"); 
		//------------根据标示符获取结果list-----1拼音 2代码 3汉字---------------------------------------------------
		String flag = request.getParameter("flag");//
		//设置请求以及返回数据的编码类型
	
		//classFlag为判定系统为市级系统还是县级系统
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();
			String xmlString = hspConfigBaseinfoBaseService.getXml(flag, inputCode, sessionForm.getStaffHospitalId(), request);
			out.println(xmlString);    	
			out.close();
			return null;
		} catch (Exception e) {
			logger.error(e);
			return mapping.findForward("fail");
		}
	}
	
}
