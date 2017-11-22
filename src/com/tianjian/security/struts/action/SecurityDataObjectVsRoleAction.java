package com.tianjian.security.struts.action;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.tianjian.comm.bean.CommConfigLocation;
import com.tianjian.comm.bean.CommConfigLocationTown;
import com.tianjian.comm.bean.CommConfigLocationVillage;
import com.tianjian.security.bean.SecurityDataObjectVsRoles;
import com.tianjian.security.business.ISecurityDataObjectVsRoleService;
import com.tianjian.security.struts.form.SecurityDataObjectVsRoleForm;
import com.tianjian.util.ResourcesUtil;
import com.tianjian.util.comm.PageBean;

public class SecurityDataObjectVsRoleAction extends DispatchAction {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(SecurityDataObjectVsRoleAction.class);
	
	
	private ISecurityDataObjectVsRoleService securityDataObjectVsRoleService;
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String verbId=request.getParameter("verbId");
		System.out.println(verbId);
		if(verbId.equals("addinit")){
			return this.addinit(mapping, form, request, response);
		}else if(verbId.equals("add")){
			return add(mapping, form, request, response);
		}else if(verbId.equals("addinits")){
			return addinits(mapping, form, request, response);
		}else if(verbId.equals("query")){
			return query(mapping, form, request, response);
		}else if(verbId.equals("queryDetail")){
			return query(mapping, form, request, response);
		}else if(verbId.equals("detail")){
			return detail(mapping, form, request, response);
		}else if(verbId.equals("delete")){
			return delete(mapping, form, request, response);
		}else if(verbId.equals("updateInit")){
			return updateInit(mapping, form, request, response);
		}else if(verbId.equals("update")){
			return update(mapping, form, request, response);
		}else if(verbId.equals("getSecurityStaff")){
			return this.getSecurityStaff(mapping, form, request, response);
		}else if(verbId.equals("getLocation")){
			return this.getLocation(mapping, form, request, response);
		}else if(verbId.equals("checkItemCode")){
			return this.checkItemCode(mapping, form, request, response);
		}else if(verbId.equals("zhankaiData")){
			return this.zhankaiData(mapping, form, request, response);
		}else if(verbId.equals("zhanKai")){
			return this.zhanKai(mapping, form, request, response);
		}else if(verbId.equals("dealThing")){
			return this.dealThing(mapping, form, request, response);
		}else if(verbId.equals("init")){
			return this.init(mapping, form, request, response);
		}else if(verbId.equals("initDetail")){
			return this.init(mapping, form, request, response);	
		}else{
			return null;
		}
	}
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String verbId=request.getParameter("verbId");
		SecurityDataObjectVsRoleForm adovrform=(SecurityDataObjectVsRoleForm)form;
		PageBean pb=new PageBean();
    	int count;
    	int page = 0;
    	int recordCount=securityDataObjectVsRoleService.getCount(adovrform,adovrform.getId(), adovrform.getSecurityStaffBaseInfo(), adovrform.getSdoValue());   	
    	pb.setCount(recordCount);
        String pageString = request.getParameter("page");
        //ServletContext application = request.getSession().getServletContext();
        //int pageSize = Integer.parseInt((String)application.getAttribute("security.PAGE_SIZE"));
        int pageSize = 10;
        if(request.getSession().getAttribute("page_2828810b39b36be40139b36be44e0000")!=null){
			pageSize = Integer.parseInt((String)request.getSession().getAttribute("page_2828810b39b36be40139b36be44e0000"));
		}else{
			ServletContext application = request.getSession().getServletContext();
			pageSize = Integer.parseInt((String)application.getAttribute("comm.PAGE_SIZE"));
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
        securityDataObjectVsRoleService.getSearch(adovrform, count, pageSize);
        request.setAttribute("pb",pb );
        if(adovrform.getVerbId().trim().equals("update")){
        	adovrform.setMessage(ResourcesUtil.getValue("conf.security.securityInit", "security.java.struts.SecurityDataObjectVsRoleAction.warn", request));//"修改成功"
        }
        request.setAttribute("data", adovrform);	
        if(verbId.equals("queryDetail")){
			return mapping.findForward("queryDetail");
		}else{
			return mapping.findForward("query");
		}
		
	}
	public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id=request.getParameter("itemCodeHidden");
		SecurityDataObjectVsRoleForm adovrform=(SecurityDataObjectVsRoleForm)form;
		adovrform.setId(id);
		securityDataObjectVsRoleService.detail(adovrform);
		request.setAttribute("data", adovrform);
		return mapping.findForward("detail");
	}
	public ActionForward updateInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id=request.getParameter("itemCodeHidden");
		SecurityDataObjectVsRoleForm adovrform=(SecurityDataObjectVsRoleForm)form;
		adovrform.setId(id);
		securityDataObjectVsRoleService.updateInit(adovrform);
		request.setAttribute("data", adovrform);
		return mapping.findForward("update");
	}
	public ActionForward getSecurityStaff(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String inputCode = request.getParameter("inputCode"); 
		//------------根据标示符获取结果list-----1拼音 2代码 3汉字---------------------------------------------------
		//String flag = "1";
		String flag = request.getParameter("flag");//
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();
			String xmlString = securityDataObjectVsRoleService.getXml(flag, inputCode,request);
			out.println(xmlString);    	
			out.close();
			return null;
		} catch (Exception e) {
			logger.warn(e);
			return mapping.findForward("fail");
		}
	}
	public ActionForward getLocation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String inputCode = request.getParameter("inputCode"); 
		String id=request.getParameter("id");
		//------------根据标示符获取结果list-----1拼音 2代码 3汉字---------------------------------------------------
		//String flag = "1";
		String flag = request.getParameter("flag");//
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();
			String xmlString = securityDataObjectVsRoleService.getPlaceXml(flag, inputCode,id,request);
			out.println(xmlString);    	
			out.close();
			return null;
		} catch (Exception e) {
			logger.warn(e);
			return mapping.findForward("fail");
		}
	}
	public ActionForward checkItemCode(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id=request.getParameter("id");
	 	response.setHeader("Cache-Control","no-store");
		response.setHeader("Pragrma","no-cache");
		response.setDateHeader("Expires",0);
		response.setContentType("text/html;charset=utf-8");
		int count=securityDataObjectVsRoleService.checkId(id);
		if(count==1){
			PrintWriter out;
			try {
				out = response.getWriter();
				//out.print("数据对象类型ID已经存在!");
				out.print(ResourcesUtil.getValue("conf.security.securityInit", "security.java.commom.warn7", request));
			} catch (IOException e) {
				logger.warn(e);
				e.printStackTrace();
			}
		}
		return null;
	}
	public ActionForward addinit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SecurityDataObjectVsRoleForm adovrform=(SecurityDataObjectVsRoleForm)form;
		securityDataObjectVsRoleService.addInit(adovrform);
		request.setAttribute("data", adovrform);
		return mapping.findForward("add");
	}
	public ActionForward zhankaiData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String parentId=request.getParameter("parentId");
		String number=request.getParameter("number");
		String id=request.getParameter("name");
		String sdtoId=request.getParameter("sdtoId");
		List<?> list=securityDataObjectVsRoleService.getNeedSecurityDataObjectVsRoles(id, sdtoId);
		List<?> list1=null;
		String xml="";
		if(number.trim().equals("1")){
			list1=securityDataObjectVsRoleService.getLocation(1, "");
			xml= this.getXMLData(number, parentId, list, list1);
		}
		this.writeResponse(response, xml);
		return null;
	}
	public void writeResponse(HttpServletResponse response, String xmlString) throws IOException {
    	response.setContentType("text/xml");
    	response.setCharacterEncoding("UTF-8");
    	response.setHeader("Cache-Control", "no-cache");
    	response.getWriter().write(xmlString);
	}
	private String getXMLData(String number,String parentId,List<?> list,List<?> list1) {
		String xmlString = "";
		
		xmlString = xmlString + "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		xmlString = xmlString + "<root>";
		if(!number.trim().equals("5")&&list1 != null){
			xmlString = xmlString + "<index>" + list1.size() + "</index>";
			if(parentId!=null&&parentId.trim().length()>0){
				List<?> li=securityDataObjectVsRoleService.getNameByObject("CommConfigLocation", "itemCode", parentId);
				if(li!=null&&li.size()>0){
					CommConfigLocation c=(CommConfigLocation) li.get(0);
					xmlString = xmlString + "<parent>" + c.getItemCode() + "</parent>";
					xmlString = xmlString + "<parent>" + c.getItemName() + "</parent>";
				}else{
					xmlString = xmlString + "<parent></parent>";
					xmlString = xmlString + "<parent></parent>";
				}
			}
			else{
				xmlString = xmlString + "<parent></parent>";
				xmlString = xmlString + "<parent></parent>";
			}
			for (int i = 0; i < list1.size(); i++) {
				CommConfigLocation c=(CommConfigLocation) list1.get(i);
				xmlString = xmlString + "<key>" + c.getItemCode() + "</key>";
				xmlString = xmlString + "<value>" + c.getItemName() + "</value>";
				if(list!=null&&list.size()>0){
					int m=0;
					for(int j=0;j<list.size();j++){
						SecurityDataObjectVsRoles s=(SecurityDataObjectVsRoles) list.get(j);
						if(c.getItemCode().equals(s.getSdoValue())){
							m++;
							xmlString = xmlString + "<check>1</check>";
						}
					}
					if(m==0){
						xmlString = xmlString + "<check>0</check>";
					}
				}else{
					xmlString = xmlString + "<check>0</check>";
				}
			}
		} else {
			xmlString = xmlString + "<index>0</index>";
		}
		xmlString = xmlString + "</root>";
		
		return xmlString;
	}
	public ActionForward zhanKai(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SecurityDataObjectVsRoleForm adovrform=(SecurityDataObjectVsRoleForm)form;
		String staffName=request.getParameter("securityStaffBaseInfo");
		String staffId=request.getParameter("securityStaffBaseInfoes");
		String stoId=request.getParameter("sdtoId");
		securityDataObjectVsRoleService.addInits(adovrform);
		adovrform.setSdotId(stoId);
		adovrform.setSecurityStaffBaseInfo(staffId);
		adovrform.setSecurityStaffBaseInfoName(staffName);
		String parentId="";
		List<?> list=securityDataObjectVsRoleService.getNeedSecurityDataObjectVsRoles(staffId, stoId);
		List<?> list1=null;
		if(stoId.trim().equals("1")){
			adovrform.setParentId("100");
			//adovrform.setParentName("所有省");
			adovrform.setParentName(ResourcesUtil.getValue("conf.security.securityInit", "security.java.struts.SecurityDataObjectVsRoleAction.item", request));
			list1=securityDataObjectVsRoleService.getLocation(1, "");
		}else if(stoId.trim().equals("2")||stoId.trim().equals("3")||stoId.trim().equals("4")){
			if(stoId.trim().equals("2")){
				parentId=adovrform.getCommConfigLocationId1();
				list1=securityDataObjectVsRoleService.getLocation(2, parentId);
			}
			if(stoId.trim().equals("3")){
				parentId=adovrform.getCommConfigLocationId2();
				list1=securityDataObjectVsRoleService.getLocation(3, parentId);
			}
			if(stoId.trim().equals("4")){
				parentId=adovrform.getCommConfigLocationId3();
				list1=securityDataObjectVsRoleService.getLocationOther("CommConfigLocationTown","commConfigLocationId" ,parentId);
			}
			List<?> li=securityDataObjectVsRoleService.getNameByObject("CommConfigLocation", "itemCode", parentId);
			if(li!=null&&li.size()>0){
				CommConfigLocation c=(CommConfigLocation) li.get(0);
				adovrform.setParentId(c.getItemCode());
				adovrform.setParentName(c.getItemName());
			}
		}else if(stoId.trim().equals("5")){
			parentId=adovrform.getCommConfigLocationTownId();
			list1=securityDataObjectVsRoleService.getLocationOther("CommConfigLocationVillage","commCltId" ,parentId);
			List<?> li=securityDataObjectVsRoleService.getNameByObject("CommConfigLocationTown", "itemCode", parentId);
			if(li!=null&&li.size()>0){
				CommConfigLocationTown c=(CommConfigLocationTown) li.get(0);
				adovrform.setParentId(c.getItemCode());
				adovrform.setParentName(c.getItemName());
			}
		}
		String[] ids=null;
		String[] names=null;
		String[] checks=null;
		if(list1!=null&&list1.size()>0){
			ids=new String[list1.size()];
			names=new String[list1.size()];
			checks=new String[list1.size()];
			for(int i=0;i<list1.size();i++){
				int m=0;
				String itemcode="";
				String itemname="";
				if(stoId.trim().equals("1")||stoId.trim().equals("2")||stoId.trim().equals("3")){
					CommConfigLocation  c=(CommConfigLocation) list1.get(i);
					itemcode=c.getItemCode();
					itemname=c.getItemName();
				}
				if(stoId.trim().equals("4")){
					CommConfigLocationTown c=(CommConfigLocationTown) list1.get(i);
					itemcode=c.getItemCode();
					itemname=c.getItemName();
				}	
				if(stoId.trim().equals("5")){
					CommConfigLocationVillage c=(CommConfigLocationVillage) list1.get(i);
					itemcode=c.getItemCode();
					itemname=c.getItemName();
				}	
				ids[i]=itemcode;
				names[i]=itemname;
				if(list!=null&&list.size()>0){
					for(int j=0;j<list.size();j++){
						SecurityDataObjectVsRoles s=(SecurityDataObjectVsRoles) list.get(j);
						if(itemcode.equals(s.getSdoValue())){
							m++;
							checks[i]="1";
							break;
						}
					}
					if(m==0){
						checks[i]="0";
					}
				}else{
					checks[i]="0";
				}
			}
		}
		adovrform.setSdotIds(ids);
		adovrform.setSdoValues(names);
		adovrform.setChecks(checks);
		
		request.setAttribute("data", adovrform);
		return mapping.findForward("adds");
	}
	public ActionForward dealThing(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SecurityDataObjectVsRoleForm adovrform=(SecurityDataObjectVsRoleForm)form;
		String id=request.getParameter("id");
		String stdoId=request.getParameter("stdoId");
		String[] selectIds=request.getParameterValues("selectId");
		adovrform.setSdotId(stdoId);
		adovrform.setSecurityStaffBaseInfo(id);
		List<?> list=securityDataObjectVsRoleService.getNeedSecurityDataObjectVsRoles(id, stdoId);
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				SecurityDataObjectVsRoles s=(SecurityDataObjectVsRoles) list.get(i);
				securityDataObjectVsRoleService.deleteObject(s);
			}
		}
		if(selectIds!=null&&selectIds.length>0){
			for(int i=0;i<selectIds.length;i++){
				adovrform.setSdoValue(selectIds[i]);
				securityDataObjectVsRoleService.save(adovrform);
			}
		}
		
		String xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?><root><message>"+ResourcesUtil.getValue("conf.security.securityInit", "security.java.struts.SecurityDataObjectVsRoleAction.warn1", request)+"</message></root>";//数据处理成功!
		this.writeResponse(response, xml);
		return null;
	}
	
	public ActionForward addinits(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SecurityDataObjectVsRoleForm adovrform=(SecurityDataObjectVsRoleForm)form;
		securityDataObjectVsRoleService.addInits(adovrform);
		request.setAttribute("data", adovrform);
		return mapping.findForward("adds");
	}
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String staffId=request.getParameter("securityStaffBaseInfoes");
		String stoVlaue=request.getParameter("sdtoValueis");
		String stoId=request.getParameter("sdtoId");
		SecurityDataObjectVsRoleForm adovrform=(SecurityDataObjectVsRoleForm)form;
		adovrform.setSecurityStaffBaseInfo(staffId);
		adovrform.setSdoValue(stoVlaue);
		adovrform.setSdotId(stoId);
		int count=securityDataObjectVsRoleService.checkStaffBaseinfoId(staffId);
		if(count==1){
			//request.setAttribute("meaage", "登陆人员已经存在!");
			request.setAttribute("meaage", ResourcesUtil.getValue("conf.security.securityInit", "security.java.struts.SecurityDataObjectVsRoleAction.warn2", request));
			securityDataObjectVsRoleService.addInit(adovrform);
			request.setAttribute("data", adovrform);
			return mapping.findForward("add");
		}else{
			boolean b=securityDataObjectVsRoleService.save(adovrform);
			if(b){
				//request.setAttribute("meaage", "添加成功!");
				SecurityDataObjectVsRoleForm adovrform1=new SecurityDataObjectVsRoleForm();
				//adovrform1.setMessage("添加成功!");
				adovrform1.setMessage(ResourcesUtil.getValue("conf.security.securityInit", "security.java.commom.warn8", request));
				return this.query(mapping, adovrform1, request, response);
			}else{
				request.setAttribute("data", adovrform);
				return this.addinit(mapping, form, request, response);
			}
		}
	}
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SecurityDataObjectVsRoleForm adovrform=(SecurityDataObjectVsRoleForm)form;
		securityDataObjectVsRoleService.delete(adovrform);
		adovrform.setId("");
		//adovrform.setMessage("删除成功!");
		adovrform.setMessage(ResourcesUtil.getValue("conf.security.securityInit", "security.java.struts.SecurityDataObjectVsRoleAction.warn4", request));
		return this.query(mapping, adovrform, request, response);
	}
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String staffId=request.getParameter("securityStaffBaseInfoes");
		String stoVlaue=request.getParameter("sdtoValueis");
		String stoId=request.getParameter("sdtoId");
		SecurityDataObjectVsRoleForm adovrform=(SecurityDataObjectVsRoleForm)form;
		adovrform.setSecurityStaffBaseInfo(staffId);
		adovrform.setSdoValue(stoVlaue);
		adovrform.setSdotId(stoId);
		securityDataObjectVsRoleService.update(adovrform);
		adovrform.setId("");
		adovrform.setSdotId("");
		adovrform.setSecurityStaffBaseInfo("");
		adovrform.setSdoValue("");
		return this.query(mapping, form, request, response);
	}
	
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String verbId = request.getParameter("verbId");
			SecurityDataObjectVsRoleForm ssform = (SecurityDataObjectVsRoleForm) form;
			this.getSecurityDataObjectVsRoleService().init(ssform);
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
	
	public ISecurityDataObjectVsRoleService getSecurityDataObjectVsRoleService() {
		return securityDataObjectVsRoleService;
	}

	public void setSecurityDataObjectVsRoleService(
			ISecurityDataObjectVsRoleService securityDataObjectVsRoleService) {
		this.securityDataObjectVsRoleService = securityDataObjectVsRoleService;
	}
}
