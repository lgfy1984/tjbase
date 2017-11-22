//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl
 
package com.tianjian.empi.struts.action; 

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.tianjian.comm.business.ICommConfigInputDictService;
import com.tianjian.comm.struts.action.BaseAction;
import com.tianjian.empi.bean.SecurityUserBaseinfo;
import com.tianjian.empi.business.ISecurityUserBaseinfoService;
import com.tianjian.empi.struts.form.SecurityUserBaseinfoForm;
import com.tianjian.empi.struts.servlet.EmpiInit;
import com.tianjian.security.struts.form.SessionForm;
import com.tianjian.util.ChineseSpelling;
import com.tianjian.util.comm.PageBean;


public class SecurityUserBaseinfoAction extends BaseAction {

	private ISecurityUserBaseinfoService service;
	private ICommConfigInputDictService commConfigInputDictService;
	public ISecurityUserBaseinfoService getService() {
		return service;
	}

	public void setService(ISecurityUserBaseinfoService service) {
		this.service = service;
	}
    
	public ICommConfigInputDictService getCommConfigInputDictService() {
		return commConfigInputDictService;
	}

	public void setCommConfigInputDictService(
			ICommConfigInputDictService commConfigInputDictService) {
		this.commConfigInputDictService = commConfigInputDictService;
	}
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		String verbId = request.getParameter("verbId");
		System.out.println("-----------verbId==" + verbId);
		if (verbId.equals("addInit")){
			return this.addInit(mapping, form, request, response);
		} else if (verbId.equals("query")){
			return this.query(mapping, form, request, response);
		} else if (verbId.equals("queryDetail")){
			return this.query(mapping, form, request, response);
		} else if (verbId.equals("queryPasswordInit")){
			return this.query(mapping, form, request, response);
		} else if (verbId.equals("queryCompare")){
			return this.query(mapping, form, request, response);
		} else if (verbId.equals("deprecated")){
			return this.deprecated(mapping, form, request, response);
		} else if (verbId.equals("change")){
			return this.change(mapping, form, request, response);
		} else if (verbId.equals("update")){
			return this.update(mapping, form, request, response);
		} else if (verbId.equals("updateInit")){
			return this.updateInit(mapping, form, request, response);
		} else if (verbId.equals("detail")){
			return this.detail(mapping, form, request, response);
		} else if (verbId.equals("casedetail")){
			return this.casedetail(mapping, form, request, response);
		} else if (verbId.equals("delete")){
			return this.delete(mapping, form, request, response);
		} else if (verbId.equals("setCity")){
			return this.setCity(mapping, form, request, response);
		} else if (verbId.equals("setCounty")){
			return this.setCounty(mapping, form, request, response);
		}else if (verbId.equals("setOutput")){
			return this.setOutput(mapping, form, request, response);
		}else if(verbId.equals("setTown")){
			return this.setTown(mapping, form, request, response);
		}else if(verbId.equals("setVillage")){
			return this.setVillage(mapping, form, request, response);
		}else if(verbId.equals("userBaseinfo")){
			return this.userBaseinfo(mapping, form, request, response);
		}else if(verbId.equals("setGroup")){
			return this.setGroup(mapping, form, request, response);
		}else if(verbId.equals("inport")){
			return this.inport(mapping, form, request, response);
		}else if(verbId.equals("save")){
			//----------------保存居民信息时调用-----------------
			return this.save(mapping, form, request, response);
		}else if(verbId.equals("back")){
			//------------------从浏览页面返回居民信息填写页面时调用-----------------------
			return this.back(mapping, form, request, response);
		}else if(verbId.equals("changePhoto")){
			//-------------------当在信息填写页面该变图片时调用----------------------
			return this.changePhoto(mapping, form, request, response);
		}else if(verbId.equals("sight")){
			return this.sight(mapping, form, request, response);
		}else if(verbId.equals("uploadFile")){
			saveUploadFile(mapping, form, request, response);
			return null;
		}else if(verbId.equals("goback")){
			return this.goback(mapping, form, request, response);
		}else if(verbId.equals("getPY")){
			return this.getPY(mapping, form, request, response);
		}else if(verbId.equals("init")){
			return this.init(mapping, form, request, response);
		}else if(verbId.equals("initDetail")){
			return this.init(mapping, form, request, response);	
		}else if(verbId.equals("initPasswordInit")){
			return this.init(mapping, form, request, response);	
		}else if(verbId.equals("initCompare")){
			return this.init(mapping, form, request, response);	
		}else if(verbId.equals("export")){
		 	return this.export(mapping, form, request, response);
		}else if(verbId.equals("getIdAddress")){
			return this.getIdAddress(mapping, form, request, response);
			
//		}else if(verbId.equals("check")){
//			return this.check(mapping, form, request, response);
		}else{
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
	
	
	public ActionForward getIdAddress(ActionMapping mapping
			, ActionForm form
			, HttpServletRequest request
			, HttpServletResponse response){
		try{
			SecurityUserBaseinfoForm hosform = (SecurityUserBaseinfoForm)form;
			String countyCode = new String(request.getParameter("county"));
			System.out.println(countyCode);
			this.getService().getIdAddress(countyCode,hosform);
			String xmlString = this.getXMLInformation(hosform);
			writeResponse(response, xmlString);
	    }catch(Exception e) {
	    	e.printStackTrace();
			return mapping.findForward("fail");
		}
	    return null;
	}
	private ActionForward setTown(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		try{
		SecurityUserBaseinfoForm hosform = (SecurityUserBaseinfoForm)form;
		String town = request.getParameter("town");
		hosform.setCommConfigLocationTownId(town);
		this.getService().getTowns(hosform);
		String xmlString = getXMLData(hosform.getCommConfigLocationTownIds(), hosform.getCommConfigLocationTownId_names());
		writeResponse(response, xmlString);		
		return null;
		}catch(Exception e){
			return mapping.findForward("fail");
		}
	}
	private ActionForward setVillage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		try{
		SecurityUserBaseinfoForm hosform = (SecurityUserBaseinfoForm)form;
		String village = request.getParameter("village");
		hosform.setCommConfigLocationVillageId(village);
		this.getService().getVillages(hosform);
		String xmlString = getXMLData(hosform.getCommConfigLocationVillageIds(), hosform.getCommConfigLocationVillageId_names());
		writeResponse(response, xmlString);		
		return null;
		}catch(Exception e){
			return mapping.findForward("fail");
		}
	}
	private ActionForward setGroup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		try{
		SecurityUserBaseinfoForm hosform = (SecurityUserBaseinfoForm)form;
		String group = request.getParameter("group");
		hosform.setCommConfigLocationGroupId(group);
		this.getService().getGroup(hosform);
		String xmlString = getXMLData(hosform.getCommConfigLocationGroupIds(), hosform.getCommConfigLocationGroupId_names());
		writeResponse(response, xmlString);		
		return null;
		}catch(Exception e){
			return mapping.findForward("fail");
		}
	}
	public ActionForward sight(ActionMapping mapping
			, ActionForm form
			, HttpServletRequest request,
			HttpServletResponse response){
		try {
			//String number=request.getParameter("number");
			SecurityUserBaseinfoForm hosform = (SecurityUserBaseinfoForm)form;
			String pmi=hosform.getIdNo();
			SecurityUserBaseinfo checkData = service.checkData(hosform.getIdNo());
			SessionForm sessionform=(SessionForm) request.getSession().getAttribute("sessionForm");
			if(checkData != null  ){
				String message = "该居民信息已经存在!!";
				hosform.setMessage(message);
				service.addInit(hosform,sessionform.getStaffId());
				request.setAttribute("data", hosform);		        
				return  mapping.findForward("add");
			}
			//SecurityUserBaseinfoForm hosform1=(SecurityUserBaseinfoForm) request.getSession().getAttribute("hosform");
			hosform.setInputCode(commConfigInputDictService.getInputCode(hosform.getName()));
			service.getDetail(hosform);
			hosform.setPmi(pmi);
			request.setAttribute("data", hosform);
			request.setAttribute("display", "yes");
			request.getSession().setAttribute("hosform", hosform);	
			return mapping.findForward("detail");
		} catch(Exception e) {
			e.printStackTrace();
			return mapping.findForward("fail");
		}
		
	}
	public ActionForward addInit(ActionMapping mapping
			, ActionForm form
			, HttpServletRequest request
			, HttpServletResponse response){
		try {
			//String        pmi = request.getParameter("pmi");
			SessionForm sessionform=(SessionForm) request.getSession().getAttribute("sessionForm");
			String photoPath = request.getParameter("photoPath");
			if(photoPath!=null){
			String picpath = this.getPicpath();
			String path = picpath + photoPath; //+ "\\"+photoPath;
			System.out.println("------------------------path="+path);
			this.deleteFile(path);
			}
			SecurityUserBaseinfoForm hosform = new SecurityUserBaseinfoForm();
			hosform.setVerbId("addInit");
			service.addInit(hosform,sessionform.getStaffId());
			
			GregorianCalendar cal = new GregorianCalendar(); 
			cal.setTime(new Date()); 
			String year = String.valueOf(cal.get(Calendar.YEAR));
			String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
			if(month.length() < 2){
				month = "0" + month;
			}
			String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
			if(day.length() < 2){
				day = "0" + day;
			}
			
			hosform.setBirth_date_year(year);
			hosform.setBirth_date_month(month);
			hosform.setBirth_date_day(day);
			//hosform.setPhotoPath(request.getContextPath()+"/empi/include/images/noavatar.gif");
			request.setAttribute("data", hosform);		        
			return  mapping.findForward("add");
			
		} catch(Exception e) {
			return mapping.findForward("fail");
		}		
	}
	
	/**设置市（地区）*/
	public ActionForward setCity(ActionMapping mapping
			, ActionForm form
			, HttpServletRequest request,
			HttpServletResponse response){
		try {
			SecurityUserBaseinfoForm hosform = new SecurityUserBaseinfoForm();
			//----------------获取页面传递的省-------------------
			String province = request.getParameter("commConfigLocationId1");
			hosform.setCommConfigLocationId1(province);
			//----------------根据省设定市----------------------
			service.getCitys(hosform);
			//----------------构造市的xml字串-------------------
			String xmlString = getXMLData(hosform.getCommConfigLocationId2s(), hosform.getCommConfigLocationId2_names());
			writeResponse(response, xmlString);
			
		} catch(Exception e) {
			return mapping.findForward("fail");
		}
		return null;
	}
/**设置县（区）*/
	public ActionForward setCounty(ActionMapping mapping
			, ActionForm form
			, HttpServletRequest request,
			HttpServletResponse response){
		try {
			SecurityUserBaseinfoForm hosform = new SecurityUserBaseinfoForm();
			//----------------获取页面传递的市-------------------
			String city = request.getParameter("commConfigLocationId2");
			hosform.setCommConfigLocationId2(city);
			//----------------根据市设定县----------------------
			service.getDistricts(hosform);
			//----------------构造县的xml字串-------------------
			String xmlString = getXMLData(hosform.getCommConfigLocationId3s(), hosform.getCommConfigLocationId3_names());
				writeResponse(response, xmlString);
			
		} catch(Exception e) {
			return mapping.findForward("fail");
		}
		return null;
	}
	
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String verbId = request.getParameter("verbId");
			SessionForm sessionform=(SessionForm) request.getSession().getAttribute("sessionForm");
			SecurityUserBaseinfoForm ssform = (SecurityUserBaseinfoForm) form;
			service.queryInit(ssform,sessionform.getStaffId(),sessionform);
			service.serchInit(request, ssform);
			request.setAttribute("data", ssform);
			if(verbId.equals("initDetail")){
				return mapping.findForward("queryDetail");
			}else if(verbId.equals("initCompare")){
				return mapping.findForward("queryCompare");
			}else if(verbId.equals("initPasswordInit")){
				return mapping.findForward("queryPasswordInit");
			}else{
				return mapping.findForward("query");
			}
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}
	
	public ActionForward goback(ActionMapping mapping
			, ActionForm form
			, HttpServletRequest request
			, HttpServletResponse response){
		try {
			SessionForm sessionform=(SessionForm) request.getSession().getAttribute("sessionForm");
			SecurityUserBaseinfoForm hosform1 = (SecurityUserBaseinfoForm)form;
			SecurityUserBaseinfoForm hosform = (SecurityUserBaseinfoForm) request.getSession().getAttribute("myneeduserbackform");	
			hosform.setMessage("");
////////	page start   ////////////////////////
	    	PageBean pb=new PageBean();
	    	int count;
	    	int page = 0;
	    	int recordCount = service.getcountbymore(hosform,sessionform.getStaffId());
	        pb.setCount(recordCount);
	        String pageString = (String) request.getSession().getAttribute("myneedpagestring");
	        ServletContext application = request.getSession().getServletContext();
			int pageSize = Integer.parseInt((String) application.getAttribute("empi.PAGE_SIZE"));
			//int pageSize = UserinfoInit.getPageSize("PAGE_SIZE");
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
	////////page end   ////////////////////////
	   
	        service.getUsers(hosform, count, pageSize,sessionform.getStaffId());
	        service.queryInit(hosform,sessionform.getStaffId(),sessionform);
	        hosform.setMessage(hosform1.getMessage());
	        //service.serchInit(hosform);
	        request.setAttribute("data", hosform);		        
			
			return mapping.findForward("query");
		} catch(Exception e) {
			return mapping.findForward("fail");
		}
		 
	}
	public ActionForward query(ActionMapping mapping
			, ActionForm form
			, HttpServletRequest request
			, HttpServletResponse response){
		try {
			String verbId = request.getParameter("verbId");
			SessionForm sessionform = (SessionForm) request.getSession().getAttribute("sessionForm");
			
			SecurityUserBaseinfoForm hosform = (SecurityUserBaseinfoForm)form;
			
			hosform.setXflag(String.valueOf((new Date()).hashCode()));
			System.out.println(hosform.getId());		
////////	page start   ////////////////////////
	    	PageBean pb=new PageBean();
	    	int count;
	    	int page = 0;
	    	int recordCount = service.getcountbymore(hosform,sessionform.getStaffId());
	        pb.setCount(recordCount);
	        String pageString = request.getParameter("page");
			int pageSize = 10;;
			//如果session中有pagesize，则使用session的。否则使用参数表的。
			ServletContext application = request.getSession().getServletContext();
			String locationLevel = (String)application.getAttribute("empi.LOCATION_LEVEL");
			hosform.setLocationLevel(locationLevel);
			if(request.getSession().getAttribute("page_2828822639c274960139c27497200000")!=null){
				pageSize = Integer.parseInt((String)request.getSession().getAttribute("page_2828822639c274960139c27497200000"));
			}else{
				pageSize = Integer.parseInt((String)application.getAttribute("empi.PAGE_SIZE"));
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
	////////page end   ////////////////////////
	   
	        service.getUsers(hosform, count, pageSize,sessionform.getStaffId());
	        service.queryInit(hosform,sessionform.getStaffId(),sessionform);
	        
	        //service.serchInit(hosform);
	        request.setAttribute("data", hosform);		        
			
	        if(verbId.equals("queryDetail")){
				return mapping.findForward("queryDetail");
	        }else if(verbId.equals("queryCompare")){
	        	return mapping.findForward("queryCompare");
	        }else if(verbId.equals("queryPasswordInit")){
	        	return mapping.findForward("queryPasswordInit");
			}else{
				return mapping.findForward("query");
			}
		} catch(Exception e) {
			e.printStackTrace();
			return mapping.findForward("fail");
		}
		 
	}
	public ActionForward change(ActionMapping mapping
			, ActionForm form
			, HttpServletRequest request
			, HttpServletResponse response){
		try {
			SessionForm sessionform=(SessionForm) request.getSession().getAttribute("sessionForm");
			SecurityUserBaseinfoForm hosform = (SecurityUserBaseinfoForm)form;
						
////////	page start   ////////////////////////
	    	PageBean pb=new PageBean();
	    	int count;
	    	int page = 0;
	    	int recordCount = service.getCountUser(hosform.getId()
					, hosform.getName()
					, hosform.getInputCode()
					, hosform.getCommConfigSexId()
					, hosform.getIdNo()
					, hosform.getCardType()
					, hosform.getCardNo()
					, hosform.getNameEn()
					, sessionform.getStaffId(),hosform.getXflag());
			
	        pb.setCount(recordCount);
	        String pageString = request.getParameter("page");
	        ServletContext application = request.getSession().getServletContext();
			int pageSize = Integer.parseInt((String) application.getAttribute("empi.PAGE_SIZE"));
	        //int pageSize = UserinfoInit.getPageSize("PAGE_SIZE");
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
	   
	        service.getSearch(hosform, count, pageSize,sessionform.getStaffId());
	        service.serchInit(request,hosform);
	        request.setAttribute("data", hosform);		        
			return mapping.findForward("change");
		} catch(Exception e) {
			return mapping.findForward("fail");
		}
		 
	}
		
	public ActionForward updateInit(ActionMapping mapping
			, ActionForm form
			, HttpServletRequest request
			, HttpServletResponse response){
		try {
			SessionForm sessionform=(SessionForm) request.getSession().getAttribute("sessionForm");
			SecurityUserBaseinfoForm hosform = (SecurityUserBaseinfoForm)form;
			hosform.setId(hosform.getIdHidden());
			service.updateInit(hosform,sessionform.getStaffId());
			
			request.setAttribute("data", hosform);	
			request.getSession().setAttribute("myneedcardlistform", hosform);
			return  mapping.findForward("update");
			
		} catch(Exception e) {
			return mapping.findForward("fail");
		}		
	}
	
	public ActionForward detail(ActionMapping mapping
			, ActionForm form
			, HttpServletRequest request
			, HttpServletResponse response){
		try {
			SessionForm sessionform=(SessionForm) request.getSession().getAttribute("sessionForm");
			SecurityUserBaseinfoForm hosform = (SecurityUserBaseinfoForm)form;
			hosform.setId(hosform.getIdHidden());
			service.showInit(hosform,sessionform!=null?sessionform.getStaffId():"");
			service.getDetail(hosform);
			request.setAttribute("data", hosform);		        
			return  mapping.findForward("detail");
		} catch(Exception e) {
			return mapping.findForward("fail");
		}		
	}
	public ActionForward casedetail(ActionMapping mapping
			, ActionForm form
			, HttpServletRequest request
			, HttpServletResponse response){
		try {
//			SessionForm sessionform=(SessionForm) request.getSession().getAttribute("sessionForm");
//			String staffId = "";
//			if(sessionform!=null){
//				staffId = sessionform.getStaffId();
//			}
			SecurityUserBaseinfoForm hosform = (SecurityUserBaseinfoForm)form;
			hosform.setId(hosform.getIdHidden());
			service.updateInit(hosform,hosform.getIdHidden());
			service.getDetail(hosform);
			request.setAttribute("data", hosform);		        
			return  mapping.findForward("casedetail");
		} catch(Exception e) {
			return mapping.findForward("fail");
		}		
	}
	
	public ActionForward update(ActionMapping mapping
			, ActionForm form
			, HttpServletRequest request
			, HttpServletResponse response){
		try {
			SessionForm sessionform=(SessionForm) request.getSession().getAttribute("sessionForm");
			SecurityUserBaseinfoForm hosform  = (SecurityUserBaseinfoForm)form;
			SecurityUserBaseinfoForm hosform1 = (SecurityUserBaseinfoForm) request.getSession().getAttribute("myneedcardlistform");
			System.out.println(hosform.getId());
			//service.addInit(hosform);
			//this.changePhoto(mapping, form, request, response);
			this.updatePhoto(hosform);
			service.getSomeDetail(hosform);
			hosform.setInputCode(commConfigInputDictService.getInputCode(hosform.getName()));
			hosform.setPmi(hosform.getIdNo());
			//hosform.setCreateUserId(sessionform.getStaffId());
			service.update(hosform,sessionform);
			String message="信息修改成功!";
			hosform.setMessage(message);
			return this.goback(mapping, hosform, request, response);
			//return null;
		} catch(Exception e) {
			return mapping.findForward("fail");
		}		
	}
	
	public ActionForward deprecated(ActionMapping mapping
			, ActionForm form
			, HttpServletRequest request
			, HttpServletResponse response){
		try{
			SecurityUserBaseinfoForm hosform = (SecurityUserBaseinfoForm)form;
			service.delete(hosform);
			hosform.setMessage("废除成功!");
			return  this.query(mapping, hosform,request, response);
	    }catch(Exception e) {
			return mapping.findForward("fail");
		}
	}
	
	public ActionForward delete(ActionMapping mapping
			, ActionForm form
			, HttpServletRequest request
			, HttpServletResponse response){
		try{
			SecurityUserBaseinfoForm hosform = (SecurityUserBaseinfoForm)form;
			//String photoPath=request.getParameter("photoPath");
			//String pmi=hosform.getPmi();
			//String pmi=request.getParameter("pmi");
			String id = hosform.getIdHidden();
			SecurityUserBaseinfo hos=service.getById(id);
			String picture=hos.getIdNo().trim();
			File file=new File(this.getPicpath());
			File[] files;
			String photoPath="";
			if(file.isDirectory()){
				files= file.listFiles();
				if(files.length!=0){
					for(int i=0;i<files.length;i++){
						String fileName=files[i].getName();
						System.out.println("----------------fileName="+fileName);
						String name="";
						if(fileName.indexOf(".")>0){
							name = fileName.substring(0, fileName.indexOf("."));
						}
						System.out.println("----------------name="+name);
						if(name.equals(picture)){
							photoPath=fileName;
							break;
						}
					}
				}
			}
			System.out.println(this.getPicpath()+photoPath);
			this.deleteFile(this.getPicpath()+photoPath);
			service.delete(hosform);
			hosform.setMessage("删除成功!");
			//System.out.println(this.getPicpath()+photoPath);
			//this.deleteFile(this.getPicpath()+photoPath);
			return  this.query(mapping, hosform,request, response);
	    }catch(Exception e) {
			return mapping.findForward("fail");
		}
	}
	public ActionForward inport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		SecurityUserBaseinfoForm hosform = (SecurityUserBaseinfoForm)form;
		FormFile formfile = hosform.getFile();
		  try {
			InputStream inputstream = formfile.getInputStream();
			List message=service.saveImport(inputstream);
			for(int i=0;i<message.size();i++){
				request.getSession().setAttribute("messagelExcel", message.get(i));
			}
			return mapping.findForward("message");
		} catch (Exception e) {
			request.setAttribute("message", "导出失败请检查格式");
			return mapping.findForward("message");
		}
		
	}
	public ActionForward setOutput(ActionMapping mapping
			, ActionForm form
			, HttpServletRequest request
			, HttpServletResponse response) {
		SessionForm sessionform=(SessionForm) request.getSession().getAttribute("sessionForm");
		response.setContentType("text/xml");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Cache-Control", "no-cache,must-revalidate"); 
		SecurityUserBaseinfoForm hosform = (SecurityUserBaseinfoForm)form;
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		String inputCode=request.getParameter("inputCode");
		hosform.setPmi(id);
		hosform.setName(name);
		hosform.setInputCode(inputCode);
		//数据库总数；
		int recordCount = service.getCount();
		 
		int recordCount1 = service.getCountUser(hosform.getId()
				, hosform.getName()
				, hosform.getInputCode()
				, hosform.getCommConfigSexId()
				, hosform.getIdNo()
				, hosform.getCardType()
				, hosform.getCardNo()
				, hosform.getNameEn()
				, sessionform.getStaffId(),hosform.getXflag());
		if(recordCount==recordCount1){
			response.setContentType("text/html");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.print("不建议全部导出");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			response.setContentType("text/html");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.print("确定要导出吗?");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return mapping.findForward("");
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
	
	public void writeResponse(HttpServletResponse response, String xmlString) throws IOException {
    	response.setContentType("text/xml");
    	response.setCharacterEncoding("UTF-8");
    	response.setHeader("Cache-Control", "no-cache");
    	response.getWriter().write(xmlString);
	}
	
	protected void saveUploadFile(ActionMapping mapping
			, ActionForm form
			, HttpServletRequest request,
			HttpServletResponse response){
		
		SecurityUserBaseinfoForm hosform = (SecurityUserBaseinfoForm)request.getSession().getAttribute("hosform");
		System.out.println("saveUploadFile");
		
		String resultStr = "";
		
	    // 设置数据传输单元大小为1KB
		int unit_size = 1024;
		    // 初始化xml文件大小（以字节为单位）
		 int xmlfilesize = 0;
		    // 初始化上传文件名称（完整文件名）
		 String xmlfilename = "";
		    // 初始化上传文件保存路径（绝对物理路径）
		 String xmlfilepath = this.getPicpath();
		    // 声明文件存储字节数组
		 byte[] xmlfilebytes = null;
		 try {
		  // 初始化 SAX 串行xml文件解析器
		  SAXBuilder builder = new SAXBuilder();
		  Document doc = builder.build(request.getInputStream());
		  Element eroot = doc.getRootElement();
		  // 获取上传文件的完整名称
		  Iterator it_name = eroot.getChildren("uploadfilename").iterator();
		  if (it_name.hasNext()) {
		   xmlfilename = ((Element) it_name.next()).getText();
		  }
		  xmlfilename = hosform.getIdNo()+xmlfilename.substring(xmlfilename.lastIndexOf("."));
		  // 获取上传文件保存的绝对物理路径
		  /*Iterator it_path = eroot.getChildren("uploadfilepath").iterator();
		  if (it_path.hasNext()) {
		   xmlfilepath = ((Element) it_path.next()).getText();
		  }*/
		  // 获取上传文件的大小
		  Iterator it_size = eroot.getChildren("uploadfilesize").iterator();
		  if (it_size.hasNext()) {
		   xmlfilesize = Integer.parseInt(((Element) it_size.next()).getText());
		   if (xmlfilesize>0) {
		    int unit_count = 0;
		    // 为存储文件内容的字节数组分配存储空间
		    xmlfilebytes = new byte[xmlfilesize];
		    // 循环读取文件内容，并保存到字节数组中
		    Iterator it_content = eroot.getChildren("uploadcontent").iterator();
		    while (it_content.hasNext()) {
		     // 初始化Base64编码解码器
		    	Base64 base64 = new Base64();byte[] xmlnodebytearray = base64.decode(((Element) it_content.next()).getText().getBytes());
		     if (xmlnodebytearray.length>=unit_size) {
		      // 读取一个完整数据单元的数据
		         System.arraycopy(xmlnodebytearray,0,xmlfilebytes,unit_count*unit_size,unit_size);
		     }
		     else {
		      // 读取小于一个数据单元的所有数据
		         System.arraycopy(xmlnodebytearray,0,xmlfilebytes,unit_count*unit_size,xmlfilesize%unit_size);
		     }
		     // 继续向下读取文件内容
		     unit_count++;
		    }
		   }
		  }
		  System.out.println(xmlfilepath+xmlfilename);
		  // 创建文件输入输出流
		  FileOutputStream fos = new FileOutputStream(xmlfilepath+xmlfilename);
		  // 写入文件内容
		  fos.write(xmlfilebytes);
		  fos.flush();
		  // 关闭文件输入输出流
		  fos.close();
		  resultStr = "yes";
		 } catch (Exception e) {
			 resultStr = "no";
		     e.printStackTrace();
		 }
		 System.out.println("uploadResult:"+resultStr);
		 //打印返回信息
		 try {
			 String xmlString = getXMLData(new String[]{"result"}, new String[]{resultStr});
			 writeResponse(response, xmlString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ActionForward save(ActionMapping mapping
			, ActionForm form
			, HttpServletRequest request
			, HttpServletResponse response){
		SessionForm sessionform=(SessionForm) request.getSession().getAttribute("sessionForm");
		SecurityUserBaseinfoForm hosform=(SecurityUserBaseinfoForm) request.getSession().getAttribute("hosform");
		service.getSomeDetail(hosform);
		hosform.setCreateUserId(sessionform.getStaffId());
		System.out.println("++++++++++"+hosform.getPhotoPath());
		service.save(hosform,sessionform);
		SecurityUserBaseinfoForm hosform1=new SecurityUserBaseinfoForm();
		service.addInit(hosform1,sessionform.getStaffId());
		//hosform1.set
		hosform1.setMessage("保存居民信息成功!!!");
		request.setAttribute("data", hosform1);
		return mapping.findForward("add");
	}
	public ActionForward back(ActionMapping mapping
			, ActionForm form
			, HttpServletRequest request,
			HttpServletResponse response){
		try {
			SessionForm sessionform=(SessionForm) request.getSession().getAttribute("sessionForm");
			SecurityUserBaseinfoForm hosform=(SecurityUserBaseinfoForm) request.getSession().getAttribute("hosform");
			service.addInit(hosform,sessionform.getStaffId());
			request.setAttribute("data", hosform);
			return mapping.findForward("add");
			
		} catch(Exception e) {
			return mapping.findForward("fail");
		}
	}
	//---------------用于删除文件--------------------------
	public boolean deleteFile(String filePath){
		File file=new File(filePath);
		if(file.exists()){
		if(file.isFile()){
			file.delete();
			return true;
		}
		}
		return false;
	}
	public ActionForward changePhoto(ActionMapping mapping
			, ActionForm form
			, HttpServletRequest request,
			HttpServletResponse response){
		try {
			SessionForm sessionform=(SessionForm) request.getSession().getAttribute("sessionForm");
			SecurityUserBaseinfoForm hosform = (SecurityUserBaseinfoForm)form;
			String pmi=hosform.getCommConfigIdTypeId()+hosform.getIdNo();
			FormFile myFile=hosform.getFile();
			String photoPath=hosform.getPhotoPath();
			String picStatus=hosform.getPicStatus();
			if(picStatus.equals("change")){
				SecurityUserBaseinfo checkData = service.checkData(pmi);			
				if(checkData != null  ){
					String message = "该PMI已经存在!!";
					hosform.setMessage(message);
					service.addInit(hosform,sessionform.getStaffId());
					request.setAttribute("data", hosform);		        
					return  mapping.findForward("add");
				}
				if(photoPath!=null&&photoPath!=""){
					this.deleteFile(this.getPicpath()+photoPath);
				}
				photoPath=this.saveFile(pmi, myFile);
				//service.addInit(hosform,sessionform.getStaffId());
				hosform.setPhotoPath(photoPath);
				request.setAttribute("data", hosform);
				//request.getSession().setAttribute("hosform", hosform);
				return mapping.findForward("add");
			}
			if(picStatus.equals("update")){
				if(myFile.getFileName()!=null&&myFile.getFileName()!=""){
					this.deleteFile(this.getPicpath()+photoPath);
				}
				photoPath=this.saveFile(pmi, myFile);
				service.addInit(hosform,sessionform.getStaffId());
				hosform.setPhotoPath(photoPath);
				request.setAttribute("data", hosform);
				return mapping.findForward("update");
			}
			return null;
				//System.out.println(id+","+commConfigInputDictService.getInputCode(hosform.getName()));
		} catch(Exception e) {
			return mapping.findForward("fail");
		}
	}
	private void updatePhoto(SecurityUserBaseinfoForm hosform){
		//图片名称
		String pmi=hosform.getIdNo();
		FormFile myFile=hosform.getFile();
		String photoPath=hosform.getPhotoPath();
		String picStatus=hosform.getPicStatus();
		if(picStatus.equals("update")){
			if(myFile.getFileName()!=null&&myFile.getFileSize()>0){
				this.deleteFile(this.getPicpath()+photoPath);
				photoPath=this.saveFile(pmi, myFile);
				hosform.setPhotoPath(photoPath);
			}
		}
	}
	//-------------------获取conf/userinfo/UserinfoInit。的picpath值----------------------
	public String getPicpath(){
		String picpath="";
		
		
		picpath = EmpiInit.getProperty("picpath");
		return picpath;
	}
	//-------------------文件的上传,传入D盘的picture文件包里----------------------
	public String saveFile(String pmi,FormFile myFile){
		try {
			String picpath = this.getPicpath();
			//-------封装到id的文件包中-------------------------
			String path = picpath + "/";

			File filepath = new File(path);
			if(!filepath.exists()){
				filepath.mkdirs();
			}
			InputStream stream = myFile.getInputStream();
			String ext = this.getExt(myFile);
			OutputStream bos = new FileOutputStream(filepath + "/" + pmi + "." + ext);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ( (bytesRead = stream.read(buffer, 0, 8192)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}
			bos.close();
			stream.close();
			String photoPath=pmi + "." + ext;
			return photoPath;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	public SecurityUserBaseinfoForm pageStart(SecurityUserBaseinfoForm hosform,HttpServletRequest request){
		SessionForm sessionform=(SessionForm) request.getSession().getAttribute("sessionForm");
    	PageBean pb=new PageBean();
    	int count;
    	int page = 0;
    	int recordCount = service.getCountUser(hosform.getId()
				, hosform.getName()
				, hosform.getInputCode()
				, hosform.getCommConfigSexId()
				, hosform.getIdNo()
				, hosform.getCardType()
				, hosform.getCardNo()
				, hosform.getNameEn()
				, sessionform.getStaffId(),hosform.getXflag());
		
        pb.setCount(recordCount);
        String pageString = request.getParameter("page");
        ServletContext application = request.getSession().getServletContext();
		int pageSize = Integer.parseInt((String) application.getAttribute("empi.PAGE_SIZE"));
        //int pageSize = UserinfoInit.getPageSize("PAGE_SIZE");
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
////////page end   ////////////////////////
   
        service.getSearch(hosform, count, pageSize,sessionform.getStaffId());
        service.queryInit(hosform,sessionform.getStaffId(),sessionform);
        service.serchInit(request,hosform);
        hosform.setXflag(String.valueOf((new Date()).hashCode()));
        request.getSession().setAttribute("myneeduserbackform", hosform);
        request.getSession().setAttribute("myneedpagestring", pageString);
        if(recordCount%pageSize==0&&recordCount/pageSize<page){
        	request.getSession().setAttribute("myneedpagestring", "1");
        }
        return hosform;
	}
	public String getExt(FormFile myFile) {
		String ext="";
		if(myFile.getFileName().indexOf(".") > 0){
			ext = myFile.getFileName().substring(myFile.getFileName().indexOf(".") + 1);
		}else{
			ext = "bmp";
		}
		return ext;
	}



	private String getXMLMessage(String message) {
		String xmlString = "";
		
		xmlString = xmlString + "<?xml version=\"1.0\" encoding=\"gb2312\"?>";
		xmlString = xmlString + "<root>";
		xmlString = xmlString + "<message>" + message + "</message>";
		xmlString = xmlString + "</root>";
		
		return xmlString;
	}
	
	private String getXMLInformation(SecurityUserBaseinfoForm hosform) {
		String xmlString = "";
		
		xmlString = xmlString + "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		xmlString = xmlString + "<root>";
		xmlString = xmlString + "<message>" + hosform.getCommConfigLocationId1() + "</message>";
		xmlString = xmlString + "<message>" + hosform.getCommConfigLocationId1_name() + "</message>";
		
		xmlString = xmlString + "<message>" + hosform.getCommConfigLocationId2() + "</message>";
		xmlString = xmlString + "<message>" + hosform.getCommConfigLocationId2_name() + "</message>";
		
		xmlString = xmlString + "<message>" + hosform.getCommConfigLocationId3() + "</message>";
		xmlString = xmlString + "<message>" + hosform.getCommConfigLocationId3_name() + "</message>";
		xmlString = xmlString + "</root>";
		System.out.println(xmlString);
		return xmlString;
	}
	/**个人信息*/
	public ActionForward userBaseinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try{
			String inputCode = request.getParameter("inputCode");
			String flag = request.getParameter("flag");
			List<?> userList = this.getService().getUserBaseinfo(flag, inputCode);
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out;
			try{
				out = response.getWriter();
				out.println("<response>");
				for (int i = 0; i < userList.size(); i++) {
					SecurityUserBaseinfo userBaseinfo = (SecurityUserBaseinfo) userList.get(i);
					out.println("<ress>");
					out.println("<resInputCode>" + userBaseinfo.getInputCode()+ "</resInputCode>");
					out.println("<resItemCode>" + userBaseinfo.getId()+ "</resItemCode>");
					out.println("<resItemName>"+ userBaseinfo.getName()+ "</resItemName>");
					out.println("</ress>");
				}
				out.println("</response>");
				out.close();				
			}catch(Exception e){
				e.printStackTrace();
			}
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	public ActionForward export(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		try{
			String name="SecurityUserBaseInfo";
			SecurityUserBaseinfoForm hosform = (SecurityUserBaseinfoForm)form;
			
			int count=0;
			SessionForm sessionform=(SessionForm) request.getSession().getAttribute("sessionForm");
			 count =this.service.getcountbymore(hosform,sessionform.getStaffId( ));
			
			System.out.println("---------count=" + count);
			if(count==0){
				request.setAttribute("message", "没有数据导出!");
				return this.query(mapping, form, request, response);
			}
			HSSFWorkbook workbook=null;
			
			workbook=this.service.saveExport(hosform, sessionform.getStaffId(), sessionform.getStaffName());
			 
			String fileName = name+".xls";
			fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
			response.setContentType("application/application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename="+fileName);
			if(workbook==null){
				request.setAttribute("message", "用户基本信息目录Excel生成失败，请联系管理人员!");
			}else{
				ServletOutputStream fOut = response.getOutputStream();
				workbook.write(fOut);
				fOut.flush();
				fOut.close();
			}
		}catch(Exception re){
			re.printStackTrace();
		}
		return null;
	}
	
//	public ActionForward check(ActionMapping mapping
//			, ActionForm form
//			, HttpServletRequest request
//			, HttpServletResponse response){
//		try {
//			String userId=request.getParameter("userId");
//			System.out.println("userid++++"+userId);
//			SecurityUserVsCard securityUserVsCard= this.service.findCurrentByUserId(userId);
//			if(securityUserVsCard!=null){
//				this.writeResponse(response, "have");
//			}
//			//response.getWriter().println("");
//			return null;
//	  
//		} catch(Exception e) {
//			return mapping.findForward("fail");
//		}
//		 
//	}
}
 
 

