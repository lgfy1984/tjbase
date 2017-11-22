package com.tianjian.security.struts.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tianjian.security.bean.SecurityConfigParameter;
import com.tianjian.security.business.service.SecurityConfigParameterServiceImpl;

public class SercurityConfigParameterServlet extends HttpServlet{
	/**
	 * 
	 */
	
	
	private static final long serialVersionUID = 2430088739138248476L;

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(SercurityConfigParameterServlet.class);

	/**
	 * 
	 */

	public void init(ServletConfig config){
	System.out.println("-------------initAll start----------");
	
	SecurityConfigParameterServiceImpl SecurityConfigParameterServiceImpl = (SecurityConfigParameterServiceImpl)WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext()).getBean("securityConfigParameterService"); 

	List<?> list = SecurityConfigParameterServiceImpl.getData();
	for(int i=0;i<list.size();i++){
		//SecurityConfigParamclass securityConfigParamclass = (SecurityConfigParamclass)list.get(i);
		String classCode = String.valueOf(transNullToString(((Object[])list.get(i))[0]));
		String className = String.valueOf(transNullToString(((Object[])list.get(i))[1]));
		List<?> l = SecurityConfigParameterServiceImpl.findByClassCode(classCode);
		for(int a=0;a<l.size();a++){
			SecurityConfigParameter securityConfigParameter = (SecurityConfigParameter)l.get(a);
			String itemName = securityConfigParameter.getItemName();
			ServletContext application = config.getServletContext();
			String applicationName = className + "." + itemName;
			System.out.println(applicationName + ":" +securityConfigParameter.getItemValue());
			application.setAttribute(applicationName, securityConfigParameter.getItemValue());
		}
	}
	System.out.println("-------------initAll end------------");
}


	/**
	 * 去掉字串中的空格
	 * */
	private String transNullToString(Object obj){
		String temp = "";
		if (obj != null){
			temp = ((String)obj).trim();			
		}
		return temp;
	}
	
	
	/**修改session的值*/
	public String changeSessionPageValue(HttpServletRequest req, HttpServletResponse resp){
		
		String pageSizeKey = req.getParameter("pageId");
		String pageSizeValue= req.getParameter("pageSize");
		
		if(!pageSizeKey.startsWith("page")){
			logger.error("页面标识需要以page开头");
			return null;
		}
		
		HttpSession session = req.getSession(true);
		session.setAttribute(pageSizeKey, pageSizeValue);
		try {
			req.setCharacterEncoding("UTF-8");
  			resp.setContentType("text/xml; charset=UTF-8");
  			resp.setHeader("Cache-Control", "no-cache");
  			PrintWriter out = resp.getWriter();
			out.write("<result>success</result>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(req.getParameter("method").equals("changePageSize")){
			this.changeSessionPageValue(req, resp);
		}
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}  
	
}
	



