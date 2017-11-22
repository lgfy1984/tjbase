package com.tianjian.hsp.struts.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
	
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.tianjian.hsp.bean.HspDeptBaseinfo;
import com.tianjian.hsp.bean.ZTreeNode;
import com.tianjian.hsp.business.IHspEquipBaseinfoService;
import com.tianjian.hsp.struts.form.HspEquipBaseinfoForm;
import com.tianjian.hsp.struts.servlet.HspInit;
import com.tianjian.security.struts.form.SessionForm;
import com.tianjian.util.ResourcesUtil;
import com.tianjian.util.comm.PageBean;

public class HspEquipBaseinfoAction extends Action {
	
	private IHspEquipBaseinfoService hspEquipBaseinfoService;
	
	public IHspEquipBaseinfoService getHspEquipBaseinfoService() {
		return hspEquipBaseinfoService;
	}

	public void setHspEquipBaseinfoService(
			IHspEquipBaseinfoService hspEquipBaseinfoService) {
		this.hspEquipBaseinfoService = hspEquipBaseinfoService;
	}

	public ActionForward execute(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) {
		String verbId = request.getParameter("verbId");
		System.out.println("-----------verbId==" + verbId);
		if(verbId.equals("init")){
			return this.init(mapping, form, request, response);
		}else if(verbId.equals("init2")){
				return this.init(mapping, form, request, response);
		}else if(verbId.equals("query")){
			return this.query(mapping, form, request, response);
		}else if(verbId.equals("query2")){
			return this.query(mapping, form, request, response);
		}else if(verbId.equals("addInit")){
			return this.addInit(mapping, form, request, response);
		}else if(verbId.equals("add")){
			return this.add(mapping, form, request, response);
		}else if(verbId.equals("updateInit")){
			return this.updateInit(mapping, form, request, response);
		}else if(verbId.equals("update")){
			return this.update(mapping, form, request, response);
		}else if(verbId.equals("detail")){
			return this.detail(mapping, form, request, response);
		}else if (verbId.equals("delete")) {
			return this.delete(mapping, form, request, response);
		}else if (verbId.equals("setDept")) {
			return this.setDept(mapping, form, request, response);
		}else if(verbId.equals("elsExport")){
			return this.elsExport(mapping, form, request, response);
		}else if(verbId.equals("elsImport")){
			return this.elsImport(mapping, form, request, response);
		}else{
			return mapping.findForward("fail");
		}
	 }

	private ActionForward elsImport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		HspEquipBaseinfoForm hosform = (HspEquipBaseinfoForm)form;
		FormFile formfile = hosform.getFile();
		try{
			InputStream inputstream = formfile.getInputStream();
			String message  = this.hspEquipBaseinfoService.elsImport(inputstream, request);
			if(message==null || message.trim().length()<=0){
				message = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.common.importSuccess", request);
				request.setAttribute("message", message);
			}
			request.setAttribute("message", message);
			return mapping.findForward("elsImport");
		}catch(Exception e){
			String message = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.common.importFail", request);
			request.setAttribute("message", message);
			HspInit.println(e);
			return mapping.findForward("elsImport");
		}	
	}

	private ActionForward elsExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			HspEquipBaseinfoForm hosform = (HspEquipBaseinfoForm) form;
			HttpSession session = request.getSession(true);
			SessionForm sessionForm = (SessionForm) session.getAttribute("sessionForm");
			if(sessionForm!=null && !"".equals(sessionForm)){
				if(sessionForm.getStaffId()!=null && !"".equals(sessionForm.getStaffId())){
					hosform.setStaffId(sessionForm.getStaffId());
				}
			}
			String fileName = "Hospital staff infomations.xls";
			fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");

			//response.setContentType("application/vnd.ms-excel");	
			response.setContentType("application/application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename="+fileName);

			HSSFWorkbook workbook = this.hspEquipBaseinfoService.getExcel(hosform, fileName, request);
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
			HspInit.println(e);
			return mapping.findForward("fail");
		}
	}

	private ActionForward setDept(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try{
			HspEquipBaseinfoForm hosform = new HspEquipBaseinfoForm();
			String orgId = request.getParameter("orgId");
			this.hspEquipBaseinfoService.getDept(hosform,orgId);
			String[] key = this.trantArray1(hosform.getDeptList());
			String[] value = this.trantArray2(hosform.getDeptList());
			String xmlString = this.getXMLData(key, value);
			System.out.println(xmlString);
			this.writeResponse(response, xmlString);
		}catch(Exception e) {
			HspInit.println(e);
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

	private String[] trantArray2(List<?> deptList) {
		if(deptList!=null&&deptList.size()>0){
			String[] value = new String[deptList.size()+1]; 
			for(int i=0;i<deptList.size();i++){
				HspDeptBaseinfo dept = (HspDeptBaseinfo) deptList.get(i);
				value[i] = dept.getDeptName()!=null?dept.getDeptName():"";
			}
			value[deptList.size()] = "其他科室";
			return value;
		}
		return null;
	}

	private String[] trantArray1(List<?> deptList) {
		if(deptList!=null&&deptList.size()>0){
			String[] key = new String[deptList.size()+1]; 
			for(int i=0;i<deptList.size();i++){
				HspDeptBaseinfo dept = (HspDeptBaseinfo) deptList.get(i);
				key[i] = dept.getId().getDeptCode()!=null?dept.getId().getDeptCode():"";
			}
			key[deptList.size()] = "";
			return key;
		}
		return null;
	}

	private ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String useForTree = request.getParameter("useForTree");
		try {
			HspEquipBaseinfoForm hosform = (HspEquipBaseinfoForm) form;
			this.hspEquipBaseinfoService.delete(hosform);
			if("1".equals(useForTree)){
				this.write2Response(response, "[{flag:'1', msg:'删除成功！'}]");
				return null;
			}else{
				return this.query(mapping, hosform, request, response);
			}
		}
		catch (Exception e) {
			if("1".equals(useForTree)){
				this.write2Response(response, "[{flag:'0', msg:'删除失败！'}]");
				return null;
			}else{
				return mapping.findForward("fail");
			}
		}
	}

	private ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		HspEquipBaseinfoForm hebForm = (HspEquipBaseinfoForm) form;
		
		PageBean pb=new PageBean();
		int count;
		int page = 0;
		int recordCount = this.hspEquipBaseinfoService.getCount(hebForm);
		pb.setCount(recordCount);
		String pageString = request.getParameter("page");
		//int pageSize = HspInit.getPageSize("PAGE_SIZE");
		int pageSize = 10;
		if(request.getSession().getAttribute("page_282881153ad4909b013ad4909b8c0000")!=null){
			pageSize = Integer.parseInt((String)request.getSession().getAttribute("page_282881153ad4909b013ad4909b8c0000"));
		}else{
			ServletContext application = request.getSession().getServletContext();
			pageSize = Integer.parseInt((String)application.getAttribute("hsp.PAGE_SIZE"));
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
		this.hspEquipBaseinfoService.findEquipList(hebForm,count,pageSize);
		this.hspEquipBaseinfoService.init(hebForm);
		request.setAttribute("data", hebForm);	
		if("query2".equals(request.getParameter("verbId"))){
			return mapping.findForward("query2");
		}else{
			return mapping.findForward("query");
		}
	}

	private ActionForward addInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			HspEquipBaseinfoForm hosform = (HspEquipBaseinfoForm)form;
			this.hspEquipBaseinfoService.saveInit(hosform);
			request.setAttribute("data", hosform);
			return mapping.findForward("add");
		}
		catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}

	private ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		boolean useForTree = "1".equals(request.getParameter("useForTree"));
		try {
			HspEquipBaseinfoForm hosform = (HspEquipBaseinfoForm)form;
			this.hspEquipBaseinfoService.save(hosform);
			if(useForTree){
				this.write2Response(response, "[{flag:'1', msg:'保存成功！'}]");
				return null;
			}else{
				HspEquipBaseinfoForm hosformNew = new HspEquipBaseinfoForm();
				hosformNew.setMessage("保存成功！");
				request.setAttribute("orgId", hosform.getOrgId());
				request.setAttribute("deptCode", hosform.getDeptCode());
				return this.query(mapping, hosformNew, request, response);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			if(useForTree){
				this.write2Response(response, "[{flag:'0', msg:'保存失败！'}]");
				return null;
			}else{
				return mapping.findForward("fail");
			}
		}
	}

	private ActionForward updateInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			HspEquipBaseinfoForm hosform = (HspEquipBaseinfoForm) form;
			this.hspEquipBaseinfoService.updateInit(hosform);
			request.setAttribute("data", hosform);
			return mapping.findForward("update");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	private ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		boolean useForTree = "1".equals(request.getParameter("useForTree"));
		try {
			HspEquipBaseinfoForm hosform = (HspEquipBaseinfoForm) form;
			this.hspEquipBaseinfoService.update(hosform);
			if(useForTree){
				this.write2Response(response, "[{flag:'1', msg:'保存成功！'}]");
				return null;
			}else{
				HspEquipBaseinfoForm hosformNew = new HspEquipBaseinfoForm();
				hosformNew.setMessage("修改成功！");
				request.setAttribute("orgId", hosform.getOrgId());
				request.setAttribute("deptCode", hosform.getDeptCode());
				return this.query(mapping, hosformNew, request, response);
			}
		}
		catch (Exception e) {
			if(useForTree){
				this.write2Response(response, "[{flag:'0', msg:'保存失败！'}]");
				return null;
			}else{
				return mapping.findForward("fail");
			}
		}
	}

	private ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			HspEquipBaseinfoForm hosform = (HspEquipBaseinfoForm) form;
			this.hspEquipBaseinfoService.getDetail(hosform);
			request.setAttribute("data", hosform);
			return mapping.findForward("detail");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}


	private ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		HspEquipBaseinfoForm hebForm = new HspEquipBaseinfoForm();
		this.hspEquipBaseinfoService.init(hebForm);
		request.setAttribute("data", hebForm);
		if("init2".equals(request.getParameter("verbId"))){
			return mapping.findForward("query2");
		}else{
			return mapping.findForward("query");
		}
	}
	
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
	
	private void write2Response(HttpServletResponse response, String str){
		try {
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print(str);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
