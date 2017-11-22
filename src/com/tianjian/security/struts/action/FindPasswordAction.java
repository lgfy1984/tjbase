package com.tianjian.security.struts.action;

import java.util.List;

import javax.mail.internet.MimeUtility;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.security.bean.SecurityStaffBaseinfo;
import com.tianjian.security.business.IFindPasswordService;
import com.tianjian.security.struts.form.FindPasswordForm;
import com.tianjian.util.ResourcesUtil;
import com.tianjian.util.comm.GenPasswd;
import com.tianjian.util.comm.MD5;
import com.tianjian.util.comm.Mail;

public class FindPasswordAction extends BaseAction {
	private IFindPasswordService findPasswordService;
	public IFindPasswordService getFindPasswordService() {
		return findPasswordService;
	}

	public void setFindPasswordService(IFindPasswordService findPasswordService) {
		this.findPasswordService = findPasswordService;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)   {
		String verbId = request.getParameter("verbId");
		if (verbId.equals("modify")) {
			return this.modify(mapping, form, request, response);
		}else {
			return mapping.findForward("fail");
		}
	}
	
	public ActionForward modify(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)    {
		FindPasswordForm mainForm = (FindPasswordForm) form;
		String e_mail = mainForm.getEmail();
		List<?> list = this.getFindPasswordService().getInformation(mainForm);
		String checkData =  this.getFindPasswordService().checkData(mainForm, request);
		SecurityStaffBaseinfo info = null;
		ServletContext application = request.getSession().getServletContext();
		String email = (String)application.getAttribute("security.E_MAIL");
		String email_address = (String)application.getAttribute("security.EMAIL_ADDRESS");
		String email_userName = (String)application.getAttribute("security.EMAIL_NAME");
		String email_password = (String)application.getAttribute("security.EMAIL_PASSWORD");
		if(checkData != null && checkData.trim().length()>0){
			info = (SecurityStaffBaseinfo)list.get(0);
			if(info.getEmail()!= null && e_mail.equals(info.getEmail())){
				String securityStaffBaseinfoId = info.getId();
				//String password = "tj";
				String password = GenPasswd.genPassword();
				MD5 md5 = new MD5();
				String passwordMD5 = md5.getMD5ofStr(password);
				this.getFindPasswordService().updatePassword(securityStaffBaseinfoId, passwordMD5);		
				boolean foo = true;		
				String mailbody = ResourcesUtil.getValue("conf.security.securityInit", "security.java.findPassword.password", request)+ ":" + password;
				Mail themail = new Mail(email);
				themail.setNeedAuth(true);
				String mailTitle = "";				
				try{
					mailTitle = MimeUtility.encodeText(ResourcesUtil.getValue("conf.security.securityInit", "security.java.findPassword.title", request), "UTF-8", "B");
				}catch(Exception e){
					e.printStackTrace();
				}
				if (themail.setSubject(mailTitle) == false){
					foo = false;
				}
				//邮件内容 支持html 如 <font color=red>欢迎光临</font> <a href=\"http://www.laabc.com\">啦ABC</a>
				if (themail.setBody(mailbody) == false){
					foo = false;
				}
				  //收件人邮箱
				if (themail.setTo(mainForm.getEmail()) == false){
					  foo = false;
				}
				  //发件人邮箱
				if (themail.setFrom(email_address) == false){
					foo = false;
				}
				  //设置附件
				  //if (themail.addFileAffix("#######") == false)
				   //return; // 附件在本地机子上的绝对路径
				themail.setNamePass(email_userName, email_password); // 用户名与密码
				if (themail.sendout() == false){
					foo = false;
				}		
				if(foo){
					return mapping.findForward("success");
				}else{
					return mapping.findForward("fail");
				}
			}else{
				mainForm.setMessage(ResourcesUtil.getValue("conf.security.securityInit", "security.java.findpassword.warn", request));
				request.setAttribute("findPasswordForm", mainForm);
				return mapping.findForward("init");
			}
		}else{
			mainForm.setMessage(ResourcesUtil.getValue("conf.security.securityInit", "security.java.findpassword.warn1", request));
			request.setAttribute("findPasswordForm", mainForm);
			return mapping.findForward("init");
		}
	}
}

