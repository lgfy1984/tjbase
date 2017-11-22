package com.tianjian.hsp.struts.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.comm.business.ICommConfigInputDictService;
import com.tianjian.hsp.bean.ZTreeNode;
import com.tianjian.hsp.business.IOrgMenuService;
import com.tianjian.hsp.struts.form.OrgMenuForm;
import com.tianjian.security.struts.form.SessionForm;
import com.tianjian.util.ResourcesUtil;
import com.tianjian.util.comm.UtilTrans;

public class OrgMenuAction extends Action{
	
	private IOrgMenuService orgMenuService;
	private ICommConfigInputDictService commConfigInputDictService;
	
	public ICommConfigInputDictService getCommConfigInputDictService() {
		return commConfigInputDictService;
	}

	public void setCommConfigInputDictService(
			ICommConfigInputDictService commConfigInputDictService) {
		this.commConfigInputDictService = commConfigInputDictService;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try{
			String verbId = "";
			verbId = request.getParameter("verbId");
			System.out.println(verbId);
			if (verbId.equals("init")) {
				return this.init(mapping, form, request, response);
			}else if (verbId.equals("init1")) {
				return this.init1(mapping, form, request, response);
			}else if (verbId.equals("init2")) {
				return this.init2(mapping, form, request, response);
			}else if (verbId.equals("childNode")) {
				return this.childNode(mapping, form, request, response);
			}else if (verbId.equals("detail")) {
				return this.detail(mapping, form, request, response);
			}else if (verbId.equals("delDept")) {
				return this.delDept(mapping, form, request, response);
			}else if (verbId.equals("modifyDept")) {
				return this.modifyDept(mapping, form, request, response);
			}else if (verbId.equals("modifyinit")) {
				return this.modifyinit(mapping, form, request, response);
			}else if (verbId.equals("addDept")) {
				return this.addDept(mapping, form, request, response);
			}else if (verbId.equals("addInit")) {
				return this.addinit(mapping, form, request, response);
			}else if (verbId.equals("staffAddInit")) {
				return this.staffAddInit(mapping, form, request, response);
			}else if (verbId.equals("staffUpdateInit")) {
				return this.staffUpdateInit(mapping, form, request, response);
			}else if (verbId.equals("staffAdd")) {
				return this.staffAdd(mapping, form, request, response);
			}else if (verbId.equals("staffUpdate")) {
				return this.staffUpdate(mapping, form, request, response);
			}else if (verbId.equals("staffDetail")) {
				return this.staffDetail(mapping, form, request, response);
			}else if (verbId.equals("staffDel")) {
				return this.staffDel(mapping, form, request, response);
			}else if (verbId.equals("moveStaff")) {
				return this.moveStaff(mapping, form, request, response);
			}else if (verbId.equals("query")) {
				return this.query(mapping, form, request, response);
			}else if (verbId.equals("childNodeDept")) {
				return this.childNodeDept(mapping, form, request, response);
			}else{
				return mapping.findForward("fail");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return mapping.findForward("fail");
	}

	private ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		OrgMenuForm orgForm = (OrgMenuForm) form;
		String queryType = request.getParameter("queryType");
		String queryKey = request.getParameter("queryKey");
		String queryValue = request.getParameter("queryValue");
		if(queryKey!=null&&queryKey.trim().length()>0)
			orgForm.setQueryKey(queryKey);
		if(queryType!=null&&queryType.trim().length()>0)
			orgForm.setQueryType(queryType);
		if(queryValue!=null&&queryValue.trim().length()>0)
			orgForm.setQueryValue(queryValue);
		this.orgMenuService.query(orgForm);
		
		request.setAttribute("dataForm", orgForm);
		if(orgForm.getQueryType().equals("2"))
			return mapping.findForward("querystaff");
		return mapping.findForward("query");
	}

	private ActionForward moveStaff(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		OrgMenuForm orgForm = (OrgMenuForm) form;
		String hspStaffId = request.getParameter("hspStaffId");
		String orgId = request.getParameter("orgId");
		String deptCode = request.getParameter("deptCode");
		this.orgMenuService.moveStaff(orgForm,hspStaffId,orgId,deptCode);
		return null;
	}

	private ActionForward staffDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		OrgMenuForm orgForm = (OrgMenuForm) form;
		String hspStaffId = request.getParameter("hspStaffId");
		this.orgMenuService.delStaff(orgForm,hspStaffId);
		return null;
	}

	private ActionForward staffDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		OrgMenuForm orgForm = (OrgMenuForm) form;
		String hspStaffId = request.getParameter("hspStaffId");
		
		this.orgMenuService.findStaffById(hspStaffId, orgForm);
		
		request.setAttribute("dataForm", orgForm);
		return mapping.findForward("staffDetail");
	}

	private ActionForward staffUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		OrgMenuForm orgForm = (OrgMenuForm) form;
		HttpSession session = request.getSession (true); 
		SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
		orgForm.setCreateUserId(sessionForm.getStaffId());
		orgForm.setCreateUserName(sessionForm.getStaffName());
		orgForm.setId(orgForm.getIdHidden());
//		System.out.println("HIDE ID is " + hsbForm.getIdHidden());
		this.orgMenuService.updateStaff(orgForm);
		String message = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.common.msg1", request);
		request.setAttribute("message", message);
		this.orgMenuService.updateStaff(orgForm,orgForm.getId());
		request.setAttribute("data", orgForm);
		return mapping.findForward("staffUpdateInit");
	}

	private ActionForward staffAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		OrgMenuForm orgForm = (OrgMenuForm) form;
		String hspStaffId = request.getParameter("hspStaffId");
		String orgId = orgForm.getHspConfigBaseinfoId1();
		String deptCode = orgForm.getRelatedDepartment();
		String menuId = request.getParameter("menuId");
		HttpSession session = request.getSession (true); 
		SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
		orgForm.setCreateUserId(sessionForm.getStaffId());
		orgForm.setCreateUserName(sessionForm.getStaffName());
		orgForm.setCreateDate(UtilTrans.transDateToStringFull(new Date()));
		int checkData=this.orgMenuService.checkData(orgForm.getEmpNo());
		if(checkData!=1){
			String message = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.hspStaffBaseinfoAction.warn2", request);
			orgForm.setMessage(message);
			this.orgMenuService.initStaff(orgForm,hspStaffId,orgId,deptCode,menuId);
			request.setAttribute("data", orgForm);
			return mapping.findForward("staffUpdateInit");
			
		}
		this.orgMenuService.saveStaff(orgForm);
		String message = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.hspStaffBaseinfoAction.warn5", request);
		request.setAttribute("message", message);
		this.orgMenuService.initStaff(orgForm,hspStaffId,orgId,deptCode,menuId);
		request.setAttribute("data", orgForm);
		return mapping.findForward("staffAddInit");
	}

	private ActionForward staffUpdateInit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		OrgMenuForm orgForm = (OrgMenuForm) form;
		String hspStaffId = request.getParameter("hspStaffId");
		this.orgMenuService.updateStaff(orgForm,hspStaffId);
		
		request.setAttribute("data", orgForm);
		return mapping.findForward("staffUpdateInit");
	}

	private ActionForward staffAddInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		OrgMenuForm orgForm = (OrgMenuForm) form;
		String hspStaffId = request.getParameter("hspStaffId");
		String orgId = request.getParameter("orgId");
		String deptCode = request.getParameter("deptCode");
		String menuId = request.getParameter("menuId");
		this.orgMenuService.initStaff(orgForm,hspStaffId,orgId,deptCode,menuId);
		
		request.setAttribute("data", orgForm);
		return mapping.findForward("staffAddInit");
	}

	private ActionForward addinit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		OrgMenuForm orgForm = (OrgMenuForm) form;
		String orgId = request.getParameter("orgId");
		String orgName = request.getParameter("orgName");
		String menuId = request.getParameter("menuId");
		this.orgMenuService.init(orgForm);
		orgForm.setHspConfigBaseinfoId(orgId);
		orgForm.setHspConfigBaseinfoName(orgName);
		orgForm.setMenuId(menuId);
		orgForm.setStatus("0");
		request.setAttribute("dataForm", orgForm);
		return mapping.findForward("addInit");
	}

	private ActionForward modifyinit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		OrgMenuForm orgForm = (OrgMenuForm) form;
		String orgId = request.getParameter("orgId");
		String deptCode = request.getParameter("deptCode");
		if(orgId==null||orgId.trim().length()==0)
			orgId = orgForm.getHspConfigBaseinfoId();
		if(deptCode==null||deptCode.trim().length()==0)
			deptCode = orgForm.getDeptCode();
		this.orgMenuService.init(orgForm);
		String msg = this.orgMenuService.findDept(orgForm,orgId,deptCode);
		orgForm.setMessage(msg);
		orgForm.setStatus("0");
		request.setAttribute("dataForm", orgForm);	
		
		return mapping.findForward("updataInit");
	}

	private ActionForward childNode(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		OrgMenuForm orgForm = (OrgMenuForm) form;
//		String treeId = (String)request.getAttribute("id");
		String treeId = request.getParameter("id");
		String treeName = request.getParameter("name");
		String orgId = request.getParameter("orgId");
		String itemCode = request.getParameter("itemCode");
		String deptCode = request.getParameter("deptCode");
		String rMenuFlag = request.getParameter("rMenuFlag");
		request.setAttribute("data", orgForm);		
		this.orgMenuService.createChildNode(orgForm,treeId,treeName,orgId,itemCode,deptCode,rMenuFlag);
		List<ZTreeNode> childNodeList = orgForm.getChildNodeList();
		if(childNodeList!=null&&childNodeList.size()>0){
			try {
				response.setContentType("text/html");
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				String josn = this.trantJosn(childNodeList);
				out.println("["+josn+"]");
				out.flush();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}		
		return null;
	}
	
	private ActionForward childNodeDept(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		OrgMenuForm orgForm = (OrgMenuForm) form;
//		String treeId = (String)request.getAttribute("id");
		String treeId = request.getParameter("id");
		String treeName = request.getParameter("name");
		String orgId = request.getParameter("orgId");
		String itemCode = request.getParameter("itemCode");
		String deptCode = request.getParameter("deptCode");
		String rMenuFlag = request.getParameter("rMenuFlag");
		request.setAttribute("data", orgForm);		
		this.orgMenuService.createChildNodeDept(orgForm,treeId,treeName,orgId,itemCode,deptCode,rMenuFlag);
		List<ZTreeNode> childNodeList = orgForm.getChildNodeList();
		if(childNodeList!=null&&childNodeList.size()>0){
			try {
				response.setContentType("text/html");
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				String josn = this.trantJosn1(childNodeList);
				out.println("["+josn+"]");
				out.flush();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}		
		return null;
	}

//    private String trantJosn(ZTreeNode node) {
//    	String josn = "";
//    	String id = node.getId();
//		String name = node.getName();
//		if(node.isParent()){
//			String orgId = node.getOrgId();
//			String itemCode = node.getItemCode();
//			josn += "{id:\""+id+"\",name:\""+name+"\",isParent:true,times:1,orgId:\""+orgId+"\",itemCode:\""+itemCode+"\"}";
//		}else{
//			String url = node.getUrl();
//			String orgId = node.getOrgId();
//			String deptCode = node.getDeptCode();
//			josn += "{id:\""+id+"\",name:\""+name+"\",url:\""+url+"\",orgId:\""+orgId+"\",deptCode:\""+deptCode+"\"}";
//		}
//		return josn;
//	}

	private String trantJosn(List<ZTreeNode> childNodeList) {
		StringBuilder josn = new StringBuilder();
		int size = childNodeList.size();
		for(int i=1;i<=size;i++){
			ZTreeNode node = childNodeList.get(i-1);
			String id = node.getId();
			String name = node.getName();
			if(node.isParent()){
				String orgId = node.getOrgId();
				String itemCode = node.getItemCode();
				String pid = node.getPId();
				String rMenuFlag = node.getRMenuFlag();
				josn.append("{id:\"").append(id).append("\",pId:\"").append(pid).append("\",name:\"").append(name).append("\",rMenuFlag:\"").append(rMenuFlag).append("\",isParent:true,orgId:\"").append(orgId).append("\",itemCode:\"").append(itemCode).append("\",drag:false,dropInner:false}");
			}else{
			    if(node.getRMenuFlag().equals("3")){
			    	String url = node.getUrl();
					String pid = node.getPId();
					String hspStaffId = node.getHspStaffId();
					String target = node.getTarget();
					String rMenuFlag = node.getRMenuFlag();
					josn.append("{id:\"").append(id).append("\",pId:\"").append(pid).append("\",name:\"").append(name).append("\",rMenuFlag:\"").append(rMenuFlag).append("\",target:\"").append(target).append("\",uri:\"").append(url).append("\",isParent:false,hspStaffId:\"").append(hspStaffId).append("\",dropInner:false}");
			    }else{
			    	String url = node.getUrl();
					String orgId = node.getOrgId();
					String deptCode = node.getDeptCode();
					String pid = node.getPId();
					String target = node.getTarget();
					String rMenuFlag = node.getRMenuFlag();
					josn.append("{id:\"").append(id).append("\",pId:\"").append(pid).append("\",name:\"").append(name).append("\",rMenuFlag:\"").append(rMenuFlag).append("\",target:\"").append(target).append("\",uri:\"").append(url).append("\",isParent:true,orgId:\"").append(orgId).append("\",deptCode:\"").append(deptCode).append("\",childOrder:false,drag:false}");
			    }
				
			}
			if(i<size){
				josn.append(",\n");
			}
		}
		System.out.println(josn);
		return josn.toString();
	}
	
	private String trantJosn1(List<ZTreeNode> childNodeList) {
		StringBuilder josn = new StringBuilder();
		int size = childNodeList.size();
		for(int i=1;i<=size;i++){
			ZTreeNode node = childNodeList.get(i-1);
			String id = node.getId();
			String name = node.getName();
			if(node.isParent()){
				String orgId = node.getOrgId();
				String itemCode = node.getItemCode();
				String pid = node.getPId();
				String rMenuFlag = node.getRMenuFlag();
				josn.append("{id:\"").append(id).append("\",pId:\"").append(pid).append("\",name:\"").append(name).append("\",rMenuFlag:\"").append(rMenuFlag).append("\",isParent:true,orgId:\"").append(orgId).append("\",itemCode:\"").append(itemCode).append("\",drag:false,dropInner:false}");
			}else{
				String url = node.getUrl();
				String orgId = node.getOrgId();
				String deptCode = node.getDeptCode();
				String pid = node.getPId();
				String target = node.getTarget();
				String rMenuFlag = node.getRMenuFlag();
				josn.append("{id:\"").append(id).append("\",pId:\"").append(pid).append("\",name:\"").append(name).append("\",rMenuFlag:\"").append(rMenuFlag).append("\",target:\"").append(target).append("\",uri:\"").append(url).append("\",isParent:false,orgId:\"").append(orgId).append("\",deptCode:\"").append(deptCode).append("\",childOrder:false,drag:false}");
				
			}
			if(i<size){
				josn.append(",\n");
			}
		}
		System.out.println(josn);
		return josn.toString();
	}

	private ActionForward init1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		OrgMenuForm orgForm = (OrgMenuForm) form;
		request.setAttribute("data", orgForm);
		return mapping.findForward("init1");
	}
	
	private ActionForward init2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		OrgMenuForm orgForm = (OrgMenuForm) form;
		request.setAttribute("data", orgForm);
		return mapping.findForward("init2");
	}

	private ActionForward addDept(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		OrgMenuForm orgForm = (OrgMenuForm) form;			
		
		orgForm.setInputCode(this.commConfigInputDictService.getInputCode(orgForm.getDeptName()));
		String msg = this.orgMenuService.addDept(orgForm);
		orgForm.setMessage(msg);
		this.orgMenuService.init(orgForm);
		request.setAttribute("dataForm", orgForm);	
		return mapping.findForward("addInit");
	}

	private ActionForward modifyDept(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		OrgMenuForm orgForm = (OrgMenuForm) form;
		String msg = this.orgMenuService.modifyDept(orgForm);
		orgForm.setMessage(msg);
		this.orgMenuService.init(orgForm);
		request.setAttribute("dataForm", orgForm);	
		return mapping.findForward("updataInit");
	}

	private ActionForward delDept(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		OrgMenuForm orgForm = (OrgMenuForm) form;
		String orgId = request.getParameter("orgId");
		String deptCode = request.getParameter("deptCode");
		orgForm.setHspConfigBaseinfoId(orgId);
		orgForm.setDeptCode(deptCode);
		String msg = this.orgMenuService.delDept(orgForm);
		orgForm.setMessage(msg);
		request.setAttribute("data", orgForm);	

		return null;
	}

	private ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		OrgMenuForm orgForm = (OrgMenuForm) form;
		String orgId = request.getParameter("orgId");
		String deptCode = request.getParameter("deptCode");
		String msg = this.orgMenuService.findDept(orgForm,orgId,deptCode);
		orgForm.setMessage(msg);
		request.setAttribute("dataForm", orgForm);	
		return mapping.findForward("detail");
	}

	private ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		OrgMenuForm orgForm = (OrgMenuForm) form;
		HttpSession session = request.getSession(true);
		SessionForm sessionForm = (SessionForm) session.getAttribute("sessionForm");
		String staffId = sessionForm.getStaffId();
		this.orgMenuService.getOrgMenu(orgForm,staffId);
		
		request.setAttribute("data", orgForm);	
		return mapping.findForward("init");
	}

	public IOrgMenuService getOrgMenuService() {
		return orgMenuService;
	}

	public void setOrgMenuService(IOrgMenuService orgMenuService) {
		this.orgMenuService = orgMenuService;
	}

}
