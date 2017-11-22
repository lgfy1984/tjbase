package com.tianjian.hsp.struts.action;

import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.comm.business.ICommConfigInputDictService;
import com.tianjian.hsp.bean.HRTreeNode;
import com.tianjian.hsp.business.IHealthRegisterTreeService;
import com.tianjian.hsp.struts.form.HealthRegisterTreeForm;
import com.tianjian.hsp.struts.form.HealthRegisterTreeForm.DeptBean;
import com.tianjian.security.struts.form.SessionForm;
import com.tianjian.util.Converter;

public class HealthRegisterTreeAction extends Action{
	
	private IHealthRegisterTreeService healthRegisterTreeService;
	private ICommConfigInputDictService commConfigInputDictService;
	
	public IHealthRegisterTreeService getHealthRegisterTreeService() {
		return healthRegisterTreeService;
	}

	public void setHealthRegisterTreeService(
			IHealthRegisterTreeService healthRegisterTreeService) {
		this.healthRegisterTreeService = healthRegisterTreeService;
	}
	
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
			String verbId = request.getParameter("verbId");
			if("getChildNodes".equals(verbId)){
				return this.getChildNodes(mapping, form, request, response);
			}else if("detail".equals(verbId)){
				return this.detail(mapping, form, request, response);
			}else if("delete".equals(verbId)){
				return this.delete(mapping, form, request, response);
			}else if("updateInit".equals(verbId)){//修改初始化页面
				return this.updateInit(mapping, form, request, response);
			}else if("update".equals(verbId)){//更新数据
				return this.update(mapping, form, request, response);
			}else if("addInit".equals(verbId)){//添加初始化页面
				return this.addInit(mapping, form, request, response);
			}else if("add".equals(verbId)){//插入数据
				return this.add(mapping, form, request, response);
			}else if("move".equals(verbId)){
				
			}else if("query".equals(verbId)){
				return this.query(mapping, form, request, response);
			}else if("checkDeptCode".equals(verbId)){
				return this.checkDeptCode(mapping, form, request, response);
			}else if("getDept".equals(verbId)){
				return this.getDept(mapping, form, request, response);
			}else if("getDeptByHspCode".equals(verbId)){
				return this.getDeptByHspCode(mapping, form, request, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return mapping.findForward("fail");
	}

	private ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String json = this.healthRegisterTreeService.getQueryData(request);
		if(json != null && json.trim().length() > 0){
			this.write2Response(response, json);
		}
		return null;
	}

	private ActionForward getChildNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HealthRegisterTreeForm hrtForm = (HealthRegisterTreeForm)form;
		List<HRTreeNode> nodeList = null;
		if(HRTreeNode.NODE_TYPE_ROOT.equals(hrtForm.getType())){
			HttpSession session = request.getSession(true);
			SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
			String staffHspId = sessionForm.getStaffHospitalId();
			nodeList = this.getHealthRegisterTreeService().getRootChildNodes(hrtForm, staffHspId);
		}else if(HRTreeNode.NODE_TYPE_HSP.equals(hrtForm.getType())){
			nodeList = this.getHealthRegisterTreeService().getHspChildNodes(hrtForm);
		}else if(HRTreeNode.NODE_TYPE_DEPTLIST.equals(hrtForm.getType())){
			nodeList = this.getHealthRegisterTreeService().getDeptListChildNodes(hrtForm);
		}else if(HRTreeNode.NODE_TYPE_DEPT.equals(hrtForm.getType())){
			nodeList = this.getHealthRegisterTreeService().getDeptChildNodes(hrtForm);
		}else if(HRTreeNode.NODE_TYPE_OTHER_DEPT.equals(hrtForm.getType())){
			nodeList = this.getHealthRegisterTreeService().getNulldeptChildNodes(hrtForm);
		}
		String json = this.node2Json(nodeList);
		this.write2Response(response, json);
		return null;
	}
	
	private ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HealthRegisterTreeForm hrtForm = (HealthRegisterTreeForm)form;
		request.setAttribute("data", hrtForm);
		if(HRTreeNode.NODE_TYPE_DEPT.equals(hrtForm.getType())){
			if(hrtForm.getHspId() != null && hrtForm.getHspId().trim().length() >0
					&& hrtForm.getDeptCode() != null && hrtForm.getDeptCode().trim().length() > 0){
				this.getHealthRegisterTreeService().getDeptDetail(hrtForm);
				return mapping.findForward("deptDetail");
			}
		}else if(HRTreeNode.NODE_TYPE_EQUIP.equals(hrtForm.getType())){
			if(hrtForm.getEquipId() != null && hrtForm.getEquipId().trim().length() > 0){
				return mapping.findForward("equipDetail");
			}
		}
		return mapping.findForward("fail");
	}
	
	private ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HealthRegisterTreeForm hrtForm = (HealthRegisterTreeForm)form;
		if(HRTreeNode.NODE_TYPE_HSP.equals(hrtForm.getType())){
			if(hrtForm.getHspId() != null && hrtForm.getHspId().trim().length() > 0){
				this.getHealthRegisterTreeService().deleteHsp(hrtForm);
			}
		}else if(HRTreeNode.NODE_TYPE_DEPT.equals(hrtForm.getType())){
			if(hrtForm.getHspId() != null && hrtForm.getHspId().trim().length() >0
					&& hrtForm.getDeptCode() != null && hrtForm.getDeptCode().trim().length() > 0){
				this.getHealthRegisterTreeService().deleteDept(hrtForm);
			}
		}else if(HRTreeNode.NODE_TYPE_STAFF.equals(hrtForm.getType())){
			if(hrtForm.getStaffId() != null && hrtForm.getStaffId().trim().length() > 0){
				this.getHealthRegisterTreeService().deleteStaff(hrtForm);
			}
		}else if(HRTreeNode.NODE_TYPE_EQUIP.equals(hrtForm.getType())){
			if(hrtForm.getEquipId() != null && hrtForm.getEquipId().trim().length() > 0){
				this.getHealthRegisterTreeService().deleteEquip(hrtForm);
			}
		}else{
			hrtForm.setMessage(this.getHealthRegisterTreeService().createResponseJson(false, "错误的操作！"));
		}
		this.write2Response(response, hrtForm.getMessage());
		return null;
	}
	
	private ActionForward addInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HealthRegisterTreeForm hrtForm = (HealthRegisterTreeForm)form;
		request.setAttribute("data", hrtForm);
		if(HRTreeNode.NODE_TYPE_DEPT.equals(hrtForm.getType())){
			if(hrtForm.getHspId() != null && hrtForm.getHspId().trim().length() > 0){
				this.getHealthRegisterTreeService().addDeptInit(hrtForm);
				return mapping.findForward("addDept");
			}
		}
		return mapping.findForward("fail");
	}
	
	private ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HealthRegisterTreeForm hrtForm = (HealthRegisterTreeForm)form;
		request.setAttribute("data", hrtForm);
		if(HRTreeNode.NODE_TYPE_DEPT.equals(hrtForm.getType())){
			DeptBean deptBean = hrtForm.getDeptBean();
			deptBean.setInputCode(this.getCommConfigInputDictService().getInputCode(deptBean.getDeptName()));
			this.getHealthRegisterTreeService().addDept(hrtForm);
			return mapping.findForward("addDept");
		}
		return mapping.findForward("fail");
	}
	
	private ActionForward updateInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HealthRegisterTreeForm hrtForm = (HealthRegisterTreeForm)form;
		request.setAttribute("data", hrtForm);
		if(HRTreeNode.NODE_TYPE_DEPT.equals(hrtForm.getType())){
			if(hrtForm.getHspId() != null && hrtForm.getHspId().trim().length() > 0
					&& hrtForm.getDeptCode() != null && hrtForm.getDeptCode().trim().length() > 0){
				this.getHealthRegisterTreeService().updateDeptInit(hrtForm);
				return mapping.findForward("modifyDept");
			}
		}else if(HRTreeNode.NODE_TYPE_EQUIP.equals(hrtForm.getType())){
			if(hrtForm.getHspId() != null && hrtForm.getHspId().trim().length() >0
					&& hrtForm.getDeptCode() != null && hrtForm.getDeptCode().trim().length() > 0){
			}
		}
		return mapping.findForward("fail");
	}
	
	private ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HealthRegisterTreeForm hrtForm = (HealthRegisterTreeForm)form;
		request.setAttribute("data", hrtForm);
		if(HRTreeNode.NODE_TYPE_HSP.equals(hrtForm.getType())){
		}else if(HRTreeNode.NODE_TYPE_DEPT.equals(hrtForm.getType())){
			DeptBean deptBean = hrtForm.getDeptBean();
			deptBean.setInputCode(this.commConfigInputDictService.getInputCode(deptBean.getDeptName()));
			this.getHealthRegisterTreeService().updateDept(hrtForm);
			return mapping.findForward("modifyDept");
		}
		return mapping.findForward("fail");
	}
	
	private ActionForward checkDeptCode(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String hspId = (String)request.getParameter("hspId");
		String deptCode = (String)request.getParameter("deptCode");
		String back = this.getHealthRegisterTreeService().checkDeptCode(hspId, deptCode);
		this.write2Response(response, back);
		return null;
	}
	
	
	private ActionForward getDept(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();
			String xmlString = this.healthRegisterTreeService.getDeptXml(request);
			out.println(xmlString);    	
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private ActionForward getDeptByHspCode(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();
			String xmlString = this.healthRegisterTreeService.getDeptXmlByHspCode(request);
			out.println(xmlString);    	
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void write2Response(HttpServletResponse response, String str){
		try {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print(str);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	private String node2Json(List<HRTreeNode> nodeList) throws IllegalArgumentException, IllegalAccessException {
		StringBuilder json = new StringBuilder("[");
		if(nodeList != null){
			for(HRTreeNode node : nodeList){
				json.append("{");
				Field[] fields = HRTreeNode.class.getDeclaredFields();
				for(Field f : fields){
					if(f.getModifiers() == Modifier.PUBLIC + Modifier.STATIC + Modifier.FINAL){
						continue;
					}
					f.setAccessible(true);
					Object value = f.get(node);
					String temp = Converter.toBlank(value);
					if(value != null && temp.length() > 0){
						if(value instanceof Boolean || value instanceof Number){
							json.append(f.getName()).append(":").append(temp).append(",");
						}else{
							json.append(f.getName()).append(":'").append(temp).append("',");
						}
					}
				}
				if(json.charAt(json.length() - 1) == ','){
					json.deleteCharAt(json.length() - 1);
				}
				json.append("},");
			}
			if(json.charAt(json.length() - 1) == ','){
				json.deleteCharAt(json.length() - 1);
			}
		}
		json.append("]");
		return json.toString();
	}
	
}
