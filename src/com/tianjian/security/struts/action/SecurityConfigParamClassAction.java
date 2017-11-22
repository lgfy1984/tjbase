package com.tianjian.security.struts.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.comm.business.ICommConfigInputDictService;
import com.tianjian.security.bean.SecurityConfigParamclass;
import com.tianjian.security.business.ISecurityConfigParamClassService;
import com.tianjian.security.struts.form.SecurityConfigParamClassForm;
import com.tianjian.util.ResourcesUtil;
import com.tianjian.util.comm.PageBean;

public class SecurityConfigParamClassAction extends Action {
	private ISecurityConfigParamClassService securityConfigParamClassService;
	private ICommConfigInputDictService commConfigInputDictService;
	
	public ICommConfigInputDictService getCommConfigInputDictService() {
		return commConfigInputDictService;
	}
	public void setCommConfigInputDictService(
			ICommConfigInputDictService commConfigInputDictService) {
		this.commConfigInputDictService = commConfigInputDictService;
	}
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String verbId=request.getParameter("verbId");
		if(verbId.equals("initTables")){
			return this.initTables(mapping, actionForm, request, response);
		}else if(verbId.equals("query")){
			return this.query(mapping, actionForm, request, response);
		}else if(verbId.equals("queryDetail")){
			return this.query(mapping, actionForm, request, response);
		}else if(verbId.equals("queryTables")){
			return this.queryTables(mapping, actionForm, request, response);
		}else if(verbId.equals("backupForExcel")){
			return this.backupForExcel(mapping, actionForm, request, response);
		}else if(verbId.equals("queryDetail")){
			return this.query(mapping, actionForm, request, response);
		}else if (verbId.equals("detail")) {
			return this.detail(mapping, actionForm, request, response);
		} else if (verbId.equals("delete")) {
			return this.delete(mapping, actionForm, request, response);
		} else if (verbId.equals("updateInit")) {
			return this.updateInit(mapping, actionForm, request, response);
		} else if (verbId.equals("update")) {
			return this.update(mapping, actionForm, request, response);
		} else if (verbId.equals("add")) {
			return this.add(mapping, actionForm, request, response);
		} else if (verbId.equals("addInit")) {
			return this.addInit(mapping, actionForm, request, response);
		} else if (verbId.equals("checkClassCode")) {
			return this.checkClassCode(mapping, actionForm, request, response);
		} else if (verbId.equals("init")){
			return this.init(mapping, actionForm, request, response);
		} else if (verbId.equals("initDetail")){
			return this.init(mapping, actionForm, request, response);
		} 
		
		return null;
	}
	public ActionForward backupForExcel(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String tableName[]=request.getParameterValues("checkTable");

		String backPath="";
		File fil=new File(backPath);
		if(!fil.exists()){
			fil.mkdir();
		}
		String filename=securityConfigParamClassService.getExcel(tableName,backPath);
		filename = new String(filename.getBytes("iso-8859-1"), "utf-8"); 
		  //得到要下载文件的具体路径 
	    String path = backPath + filename;
	    //实例化出要下载文件 
	    File fileB = new File(path); 
	    //得到要下载文件的文件读取流 
	    InputStream is = new FileInputStream(fileB); 
	    //定义文件输入流，用于下载文件 
	    OutputStream os = response.getOutputStream(); 
	    //设置响应体属性，文件下载操作的关键就在这儿 
	    response.addHeader("Content-Disposition", 
	    	      "attachment;filename="+ new String(fileB.getName().getBytes("iso-8859-1"),"utf-8")); 
	    	    //文件头属性设置 
	    response.addHeader("Content-length", fileB.length() + ""); 
	    	    //响应体内容设置 
	    response.setContentType("application/octet-stream"); 
	    	    //下载文件大小记数器 
	    int count = 0; 
	    	    //实例化一个byte数组用于写入一次写入文件的大小 
	    byte[] buffer = new byte[1024 * 1024]; 
	    	    //如果读取文件成功 
	    while ((count = is.read(buffer)) != -1) { 
	    	     //下载文件 
	    	os.write(buffer, 0, count);
	    }
	//    os.close();
		return mapping.findForward(null);
	}
	public ActionForward queryTables(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setHeader("Cache-Control","no-store");
		response.setHeader("Pragrma","no-cache");
		response.setDateHeader("Expires",0);
		response.setContentType("text/xml;charset=utf-8");
		String tableName=request.getParameter("tableName");
		String url=securityConfigParamClassService.queryTable(tableName);
		if(url.equals("0")){
			PrintWriter out = response.getWriter();
			out.println("0");    	
			out.close();
			return null;
		}else{
			PrintWriter out = response.getWriter();
			out.println(url);    	
			out.close();
			return null;
		}
		
	}
	public ActionForward initTables(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		this.securityConfigParamClassService.setRequest(request);
		
		String tableName[]=request.getParameterValues("checkTable");
		String basePath = this.servlet.getServletConfig().getServletContext().getRealPath("/");
		basePath=basePath+"security/securityConfigParamClass/sql/";
		List<?> b=securityConfigParamClassService.addTable(tableName, basePath);
		for(int i=0;i<b.size();i++){
			if(b.get(i).toString().equals("success")){
				request.setAttribute("message", b.get(i).toString());
			}else{
				request.setAttribute("message", b.get(i).toString());
			}
		}
		return this.query(mapping, actionForm, request, response);
	}
	
	
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			String verbId = request.getParameter("verbId");
			SecurityConfigParamClassForm scpcForm = (SecurityConfigParamClassForm) form;
			PageBean pb = new PageBean();
			int count;
			int page = 0;
			int recordCount = securityConfigParamClassService.count(scpcForm);
			if(recordCount == 0){
				//查询条件有误，未查询到结果
				String str = ResourcesUtil.getValue("conf.security.securityInit", "comm.java.security.warn1", request);
				scpcForm.setMessage(str);
			}
			pb.setCount(recordCount);
			String pageString = "";
			if(scpcForm.getPageNow() != null&&scpcForm.getPageNow() != ""){
				pageString = scpcForm.getPageNow();
			}else{
				pageString = request.getParameter("page");
			}
			//int pageSize = 10;
			int pageSize = 10;
			if(request.getSession().getAttribute("page_282881f534645a2c0134645a2cd00000")!=null){
				pageSize = Integer.parseInt((String)request.getSession().getAttribute("page_282881f534645a2c0134645a2cd00000"));
			}else{
				ServletContext application = request.getSession().getServletContext();
				pageSize = Integer.parseInt((String)application.getAttribute("security.PAGE_SIZE"));
			}
			pb.setPageSize(pageSize);
			if (pageString == null || pageString.equals("")
					|| pageString.equals("0")) {
				page = 1;
				pb.setPage(1);
				count = (page - 1) * pageSize;
			} else {
				page = Integer.parseInt(pageString);
				pb.setPage(page);
				count = (page - 1) * pageSize;
			}

			request.setAttribute("pb", pb);
			securityConfigParamClassService.getData(scpcForm, count, pageSize);
			request.setAttribute("data", scpcForm);
			if(verbId.equals("queryDetail")){
				return mapping.findForward("queryDetail");
			}else{
				return mapping.findForward("query");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("fail");
	}

	
	public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SecurityConfigParamClassForm scpcForm = (SecurityConfigParamClassForm) form;
		securityConfigParamClassService.getByClassCode(scpcForm);
		request.setAttribute("data", scpcForm);
		return mapping.findForward("detail");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SecurityConfigParamClassForm scpcForm = (SecurityConfigParamClassForm) form;
		securityConfigParamClassService.deleteData(scpcForm.getClassCode1());
		SecurityConfigParamClassForm sf=new SecurityConfigParamClassForm();
		return this.query(mapping, sf, request, response);
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pageString = request.getParameter("page");
		SecurityConfigParamClassForm scpcForm = (SecurityConfigParamClassForm) form;
		scpcForm.setPageNow(pageString);
		scpcForm.setInputCode(this.getCommConfigInputDictService().getInputCode(scpcForm.getClassName()));
		securityConfigParamClassService.updateData(scpcForm);
		SecurityConfigParamClassForm sf=new SecurityConfigParamClassForm();
		return this.query(mapping, sf, request, response);
	}

	public ActionForward updateInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pageString = request.getParameter("page");
		SecurityConfigParamClassForm scpcForm = (SecurityConfigParamClassForm) form;
		scpcForm.setPageNow(pageString);
		securityConfigParamClassService.getByClassCode(scpcForm);
		request.setAttribute("data", scpcForm);
		return mapping.findForward("update");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		SecurityConfigParamClassForm scpcForm = (SecurityConfigParamClassForm) form;
		SecurityConfigParamclass checkData = this.getSecurityConfigParamClassService().checkData(scpcForm.getClassCode());
		if(checkData != null){
			String message = ResourcesUtil.getValue("conf.security.securityInit", "security.jsp.securityConfigParamClassM.add.warn1", request);
			scpcForm.setMessage(message);	
			request.setAttribute("data", scpcForm);		
			return mapping.findForward("addInit");
		}
		scpcForm.setInputCode(this.getCommConfigInputDictService().getInputCode(scpcForm.getClassName()));
		securityConfigParamClassService.saveData(scpcForm);
		SecurityConfigParamClassForm sf = new SecurityConfigParamClassForm();
		return this.query(mapping, sf, request, response);
	}

	public ActionForward addInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
//		SecurityConfigParamClassForm scpcForm = (SecurityConfigParamClassForm)form;
//		this.getsecurityConfigParamClassService().seqNoMaker(scpcForm);
//		request.setAttribute("data", scpcForm);
		return mapping.findForward("addInit");
	}
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String verbId = request.getParameter("verbId");
			SecurityConfigParamClassForm ssform = (SecurityConfigParamClassForm) form;
			request.setAttribute("data", ssform);
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
	
	public ActionForward checkClassCode(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String classCode = request.getParameter("classCode");
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragrma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html;charset=utf-8");
		if (classCode.trim().length() > 0) {
			int count = securityConfigParamClassService.check(classCode);
			if (count == 1) {
				PrintWriter out;
				try {
					out = response.getWriter();
					out.print("数据对象类型classCode已经存在!");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return mapping.findForward("");
	}
	
	
	public ISecurityConfigParamClassService getSecurityConfigParamClassService() {
		return securityConfigParamClassService;
	}
	public void setSecurityConfigParamClassService(
			ISecurityConfigParamClassService securityConfigParamClassService) {
		this.securityConfigParamClassService = securityConfigParamClassService;
	}
}
