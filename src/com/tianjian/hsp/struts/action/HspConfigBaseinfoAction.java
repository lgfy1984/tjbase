package com.tianjian.hsp.struts.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;

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

import com.tianjian.comm.business.ICommConfigInputDictService;
import com.tianjian.hsp.bean.HspConfigBaseinfo;
import com.tianjian.hsp.business.IHspConfigBaseinfoService;
import com.tianjian.hsp.struts.form.HspConfigBaseinfoForm;
import com.tianjian.hsp.struts.servlet.HspInit;
import com.tianjian.security.struts.form.SessionForm;
import com.tianjian.security.struts.servlet.BaseSecurityInit;
import com.tianjian.util.ResourcesUtil;
import com.tianjian.util.comm.PageBean;
import com.tianjian.util.comm.UtilTrans;

public class HspConfigBaseinfoAction extends Action{

	private IHspConfigBaseinfoService service;
	private ICommConfigInputDictService commConfigInputDictService;

	public ICommConfigInputDictService getCommConfigInputDictService() {
		return commConfigInputDictService;
	}
	public void setCommConfigInputDictService(ICommConfigInputDictService commConfigInputDictService) {
		this.commConfigInputDictService = commConfigInputDictService;
	}
	public IHspConfigBaseinfoService getService() {
		return service;
	}
	public void setService(IHspConfigBaseinfoService service) {
		this.service = service;
	}
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {		
		String verbId = request.getParameter("verbId");
		System.out.println("verbId=" + verbId);

		if(verbId.equals("add")){
			return this.add(mapping, form, request, response);
		}else if(verbId.equals("addInit")){
			return this.addInit(mapping, form, request, response);
		}else if(verbId.equals("query")){
			return this.query(mapping, form, request, response);
		}else if(verbId.equals("queryDetail")){
			return this.query(mapping, form, request, response);
		}else if(verbId.equals("init")){
			return this.init(mapping, form, request, response);
		}else if(verbId.equals("initquery")){
			return this.init(mapping, form, request, response);
		}else if(verbId.equals("query4Web")){
			return this.query4Web(mapping, form, request, response);
		}else if(verbId.equals("update")){
			return this.update(mapping, form, request, response);
		}else if(verbId.equals("updateInit")){
			return this.updateInit(mapping, form, request, response);
		}else if(verbId.equals("detail")){
			return this.detail(mapping, form, request, response);
		}else if(verbId.equals("delete")){
			return this.delete(mapping, form, request, response);
		}else if(verbId.equals("setCity")){
			return this.setCity(mapping, form, request, response);
		}else if(verbId.equals("setCounty")){
			return this.setCounty(mapping, form, request, response);
		}else if(verbId.equals("getHsp")){
			return this.getHsp(mapping, form, request, response);
		}else if(verbId.equals("elsPdf")){
			return this.elsPdf(mapping, form, request, response);
		}else if(verbId.equals("elsWord")){
			return this.elsWord(mapping, form, request, response);
		}else if(verbId.equals("elsExport")){
			return this.elsExport(mapping, form, request, response);
		}else if(verbId.equals("elsImport")){
			return this.elsImport(mapping, form, request, response);
		}else if(verbId.equals("checkItemCode")){
			return this.checkItemCode(mapping, form, request, response);
		}else if (verbId.equals("casedetail")){
			return this.casedetail(mapping, form, request, response);
		}else{
			return mapping.findForward("fail");
		}

	}
	/** 
	* @Title: init 
	* @Description: TODO
	* @param @param mapping
	* @param @param form
	* @param @param request
	* @param @param response
	* @param @return    设定文件 
	* @return ActionForward
	* @throws 
	*/
	private ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			
			String verbId = request.getParameter("verbId");
			HttpSession session = request.getSession (true); 
			SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
			HspConfigBaseinfoForm hosform = (HspConfigBaseinfoForm)form;
			hosform.setStaffHspId(sessionForm.getStaffHospitalId());
			hosform.setCommConfigLocationId1(BaseSecurityInit.getProperty("INIT_PROVINCE"));
			hosform.setCommConfigLocationId2(BaseSecurityInit.getProperty("INIT_CITY"));
			hosform.setCommConfigLocationId3(BaseSecurityInit.getProperty("INIT_COUNTY"));
			this.service.init(hosform, request);
			request.setAttribute("data", hosform);	
			if(verbId.equals("init")){
				return mapping.findForward("queryDetail");
			}else{
				return mapping.findForward("query");
			}
			
	     } catch(Exception e) {
			HspInit.println(e);
			return mapping.findForward("fail");
		}
	}
	public ActionForward checkItemCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/text; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		HspConfigBaseinfoForm hosform = (HspConfigBaseinfoForm)form;
		HspConfigBaseinfo checkData = service.checkData(hosform.getItemCode());
		String itemCode=request.getParameter("itemCode");
		if(checkData!=null&&checkData.getItemCode().equals(itemCode)){
			try {
				PrintWriter out = response.getWriter();
				out.println("nocode");
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return mapping.findForward("");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		String useForTree = request.getParameter("useForTree");
		try {
			HttpSession session = request.getSession (true); 
			SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
			HspConfigBaseinfoForm hosform = (HspConfigBaseinfoForm)form;
//			HspConfigBaseinfo checkData = service.checkData(hosform.getItemCode());
			hosform.setStaffHspId(sessionForm.getStaffHospitalId());
//			if(checkData != null){
//				String message = HspMessage.getMsg("600001");
//				hosform.setMessage(message);
//				service.addInit(hosform);
//				request.setAttribute("data", hosform);		        
//				return  mapping.findForward("add");
//			}
			
			/**发证书
			if(hosform.getIsGetCer().equals("1")){
				HcaConfigBaseinfo checkHcaData = hcaConfigBaseinfoService.checkData(hosform.getCaCode());
				if(checkHcaData!=null){
					String message = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.hspConfigBaseinfoAction.warn3", request);
					hosform.setMessage(message);
					service.addInit(hosform, request);
					request.setAttribute("data", hosform);		        
					return  mapping.findForward("add");
				}
			}*/
			
			
			if(hosform.getParentItemCode() == null || hosform.getParentItemCode().trim().length() <= 0){
				int count = service.getCount(new HspConfigBaseinfoForm());
				if(count!=0){
					hosform.setMessage("必须为该机构指定上级机构");
					if("1".equals(useForTree)){
						this.write2Response(response, "[{flag:'0', msg:'"+hosform.getMessage()+"'}]");
						return null;
					}else{
						service.init(hosform,request);
						request.setAttribute("data", hosform);		        
						return  mapping.findForward("add");
					}
				}
			}
			hosform.setCreateUserId(sessionForm.getStaffId());
			hosform.setCreateUserName(sessionForm.getStaffName());
			hosform.setCreateDate(UtilTrans.transDateToStringFull(new Date()));
			hosform.setInputCode(commConfigInputDictService.getInputCode(hosform.getItemName()));
			service.save(hosform);
			if("1".equals(useForTree)){
				this.write2Response(response, "[{flag:'1', msg:'保存成功!'}]");
				return null;
			}else{
				HspConfigBaseinfoForm hosformNew = new HspConfigBaseinfoForm();
				String str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.hspConfigBaseinfoAction.warn4", request);
				hosformNew.setMessage(str);
				return this.addInit(mapping, hosformNew, request, response);
			}
		} catch(Exception e) {
			HspInit.println(e);
			if("1".equals(useForTree)){
				this.write2Response(response, "[{flag:'0', msg:'保存失败!'}]");
				return null;
			}else{
				return mapping.findForward("fail");
			}
		}
	}

	public ActionForward setCity(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		try {
			HspConfigBaseinfoForm hosform = new HspConfigBaseinfoForm();
			String province = request.getParameter("commConfigLocationId1");
			hosform.setCommConfigLocationId1(province);
			service.getCitys(hosform);
			String xmlString = getXMLData(hosform.getCommConfigLocationId2s(), hosform.getCommConfigLocationId2_names());
			writeResponse(response, xmlString);
		} catch(Exception e) {
			HspInit.println(e);
			return mapping.findForward("fail");
		}
		return null;
	}
	public ActionForward setCounty(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		try {
			HspConfigBaseinfoForm hosform = new HspConfigBaseinfoForm();
			String city = request.getParameter("city");
			hosform.setCommConfigLocationId2(city);
			service.getDistricts(hosform);
			String xmlString = getXMLData(hosform.getCommConfigLocationId3s(), hosform.getCommConfigLocationId3_names());
			System.out.println(xmlString);
			writeResponse(response, xmlString);
		} catch(Exception e) {
			HspInit.println(e);
			return mapping.findForward("fail");
		}
		return null;
	}

	public ActionForward query(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		try {
			String verbId = request.getParameter("verbId");
			HttpSession session = request.getSession (true); 
			SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
			HspConfigBaseinfoForm hosform = (HspConfigBaseinfoForm)form;
			hosform.setStaffHspId(sessionForm.getStaffHospitalId());
			hosform.setCommConfigLocationId1(BaseSecurityInit.getProperty("INIT_PROVINCE"));
			hosform.setCommConfigLocationId2(BaseSecurityInit.getProperty("INIT_CITY"));
			hosform.setCommConfigLocationId3(BaseSecurityInit.getProperty("INIT_COUNTY"));
////////		page start   ////////////////////////
			PageBean pb=new PageBean();
			int count;
			int page = 0;
			int recordCount = service.getCount(hosform);

			pb.setCount(recordCount);
			String pageString = request.getParameter("page");
			//int pageSize = HspInit.getPageSize("PAGE_SIZE");
			int pageSize = 10;
			if(request.getSession().getAttribute("page_282881f53463cbef013463cbef2c0000")!=null){
				pageSize = Integer.parseInt((String)request.getSession().getAttribute("page_282881f53463cbef013463cbef2c0000"));
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
////////		page end   ////////////////////////

			service.getSearch(hosform, request, count, pageSize);
			request.setAttribute("data", hosform);		        
			if(verbId.equals("queryDetail")){
				return mapping.findForward("queryDetail");
			}else{
				return mapping.findForward("query");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}
	private ActionForward elsExport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession (true); 
			SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
			HspConfigBaseinfoForm hosform = (HspConfigBaseinfoForm)form;
			hosform.setStaffHspId(sessionForm.getStaffHospitalId());
			String fileName = "Hospital infomations.xls";
			fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");

			response.setContentType("application/vnd.ms-excel");	
			//response.setContentType("application/application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename="+fileName);

			HSSFWorkbook workbook = this.getService().getExcel(hosform, fileName,sessionForm.getStaffId(),request);
			if(workbook == null){
				String message = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.common.excelCreateFail", request);
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
	private ActionForward elsPdf(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession (true); 
			SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
			HspConfigBaseinfoForm hosform = (HspConfigBaseinfoForm)form;

			ByteArrayOutputStream ba = new ByteArrayOutputStream();
	
			this.getService().getPdf(hosform , ba, sessionForm.getStaffId() ,request);
			response.setContentType("application/pdf");	
			//response.setContentType("application/application/octet-stream");
			response.setContentLength(ba.size());
			//response.setHeader("Content-Disposition", "attachment;filename="+fileName);
			ServletOutputStream out = response.getOutputStream();
			ba.writeTo(out);
			out.flush();
			return null;
		} catch(Exception e) {
			HspInit.println(e);
			return mapping.findForward("fail");
		}
	}
	private ActionForward elsWord(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession (true); 
			SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
			HspConfigBaseinfoForm hosform = (HspConfigBaseinfoForm)form;
			
			//String newFilePath = this.getService().getNewFilePath(request);
			String newFilePath = "d:/demo.doc";
			
			this.getService().getWord(hosform, newFilePath, sessionForm.getStaffId(), request);
			
			
			File f = new File(newFilePath);
			InputStream ins = new FileInputStream(f);
			byte[] b = new byte[ins.available()];
			ins.read(b);
			ins.close();
			
			response.setContentType("application/msword");
			response.getOutputStream().write(b);
			//文件删除
//			if (f != null) {
//				f.delete();
//			}		
			return null;
		} catch(Exception e) {
			HspInit.println(e);
			return mapping.findForward("fail");
		}
	}
	
	
	
	private ActionForward elsImport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HspConfigBaseinfoForm hosform = (HspConfigBaseinfoForm)form;
		try{
			FormFile formfile = hosform.getFile();
			InputStream inputstream = formfile.getInputStream();
			String message  = this.getService().elsImport(inputstream, request);
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
	public ActionForward query4Web(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		try {
			
			HspConfigBaseinfoForm hosform = (HspConfigBaseinfoForm)form;

////////		page start   ////////////////////////
			PageBean pb=new PageBean();
			int count;
			int page = 0;
			int recordCount = service.getCountAll(hosform.getId()
					, hosform.getItemCode()
					, hosform.getItemName()
					, hosform.getInputCode()
					, hosform.getSeqNo()
					, hosform.getCommConfigHospitalTypeId()
			);

			pb.setCount(recordCount);
			String pageString = request.getParameter("page");
			//int pageSize = HspInit.getPageSize("PAGE_SIZE");
			ServletContext application = request.getSession().getServletContext();
			int pageSize = Integer.parseInt((String) application.getAttribute("hsp.PAGE_SIZE"));
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
////////		page end   ////////////////////////

			service.getSearchAll(hosform, request,count, pageSize);
			request.setAttribute("data", hosform);		        

			return mapping.findForward("query4Web");
		} catch(Exception e) {
			HspInit.println(e);
			return mapping.findForward("fail");
		}
	}

	public ActionForward updateInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		try {
			HttpSession session = request.getSession (true); 
			SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
			HspConfigBaseinfoForm hosform = (HspConfigBaseinfoForm)form;
			hosform.setId(hosform.getIdHidden());
			service.updateInit(hosform,request,sessionForm.getStaffId());
			request.setAttribute("data", hosform);		        
			return  mapping.findForward("update");
		} catch(Exception e) {
			HspInit.println(e);
			return mapping.findForward("fail");
		}		
	}

	public ActionForward addInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		try {
			HttpSession session = request.getSession (true); 
			SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
			
			HspConfigBaseinfoForm hosform = (HspConfigBaseinfoForm)form;
			service.addInits(hosform,request,sessionForm.getStaffId());
			request.setAttribute("data", hosform);	
			return  mapping.findForward("add");
		} catch(Exception e) {
			HspInit.println(e);
			return mapping.findForward("fail");
		}		
	}

	public ActionForward detail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		try {
			HttpSession session = request.getSession (true); 
			SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
			HspConfigBaseinfoForm hosform = (HspConfigBaseinfoForm)form;
			hosform.setId(hosform.getIdHidden());
			service.updateInit(hosform,request,sessionForm.getStaffId());
			service.getDetail(hosform);
			request.setAttribute("data", hosform);		        
			return  mapping.findForward("detail");
		} catch(Exception e) {
			e.printStackTrace();
			return mapping.findForward("fail");
		}		
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		String useForTree = request.getParameter("useForTree");
		try {
			HspConfigBaseinfoForm hosform = (HspConfigBaseinfoForm)form;
			HttpSession session = request.getSession (true); 
			SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
			hosform.setCreateUserId(sessionForm.getStaffId());
			hosform.setCreateUserName(sessionForm.getStaffName());
			hosform.setInputCode(commConfigInputDictService.getInputCode(hosform.getItemName()));
			service.update(hosform);
			String message = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.common.msg1", request);
			if("1".equals(useForTree)){
				this.write2Response(response, "[{flag:'1', msg:'"+message+"'}]");
				return null;
			}else{
				HspConfigBaseinfoForm hosformNew = new HspConfigBaseinfoForm();
				request.setAttribute("message", message);
				return  this.query(mapping, hosformNew, request, response);
			}
		} catch(Exception e) {
			HspInit.println(e);
			if("1".equals(useForTree)){
				this.write2Response(response, "[{flag:'0', msg:'保存失败!'}]");
				return null;
			}else{
				return mapping.findForward("fail");
			}
		}		
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		String useForTree = request.getParameter("useForTree");
		try{
			HspConfigBaseinfoForm hosform = (HspConfigBaseinfoForm)form;
			String  idDeleting = hosform.getIdHidden();
//			if(idDeleting != null && idDeleting.length > 0){
//			for(int i=0; i<idDeleting.length; i++){			
			hosform.setIdHidden(idDeleting);
			String message = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.common.deleteSuccess", request);
			request.setAttribute("message", message);
			service.delete(hosform);
//			}
//			}
			if("1".equals(useForTree)){
				this.write2Response(response, "[{flag:'1', msg:'删除成功!'}]");
				return null;
			}else{
				return this.query(mapping, hosform, request, response);
			}
		}catch(Exception e) {
			HspInit.println(e);
			if("1".equals(useForTree)){
				this.write2Response(response, "[{flag:'0', msg:'删除失败!'}]");
				return null;
			}else{
				return mapping.findForward("fail");
			}
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

	public void writeResponse(HttpServletResponse response, String xmlString) throws IOException {
		response.setContentType("text/xml");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(xmlString);
	}
	/**弹出层*/
	public ActionForward getHsp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
//		/*限定所在地区医疗*/
//		HttpSession session = request.getSession (true); 
//		SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
//		String staffId =sessionForm.getStaffId();
//		String staffHospitalId =sessionForm.getStaffHospitalId();
		HttpSession session = request.getSession (true); 
		SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
		String inputCode = request.getParameter("inputCode"); 
		//------------根据标示符获取结果list-----1拼音 2代码 3汉字---------------------------------------------------
		//String flag = "1";
		String flag = request.getParameter("flag");//
		//设置请求以及返回数据的编码类型
		//hspType 1为除去卫生服务站2为只包括服务站和服务中心3为所有4为县外机构
		String hspType = request.getParameter("hspType");
		//classFlag为判定系统为市级系统还是县级系统
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();
			String xmlString = service.getXml(flag, inputCode,hspType,sessionForm.getStaffHospitalId(),request);
			out.println(xmlString);    	
			out.close();
			return null;
		} catch (Exception e) {
			HspInit.println(e);
			return mapping.findForward("fail");
		}
	}
	public ActionForward casedetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		try {
			HspConfigBaseinfoForm hosform = (HspConfigBaseinfoForm)form;
			hosform.setId(hosform.getIdHidden());
			service.detailInit(hosform);
			service.getDetail(hosform);
			request.setAttribute("data", hosform);		        
			return  mapping.findForward("casedetail");
		} catch(Exception e) {
//			e.printStackTrace();
			return mapping.findForward("fail");
		}		
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
