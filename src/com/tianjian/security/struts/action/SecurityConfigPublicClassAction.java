package com.tianjian.security.struts.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.comm.business.ICommConfigInputDictService;
import com.tianjian.security.bean.SecurityConfigPublicClass;
import com.tianjian.security.business.ISecurityConfigPublicClassService;
import com.tianjian.security.struts.form.SecurityConfigPublicClassForm;
import com.tianjian.util.ResourcesUtil;
import com.tianjian.util.comm.PageBean;

public class SecurityConfigPublicClassAction extends BaseAction {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(SecurityConfigPublicClassAction.class);

	private ISecurityConfigPublicClassService securityConfigPublicClassService;
	private ICommConfigInputDictService commConfigInputDictService;

	public ICommConfigInputDictService getCommConfigInputDictService() {
		return commConfigInputDictService;
	}

	public void setCommConfigInputDictService(ICommConfigInputDictService commConfigInputDictService) {
		this.commConfigInputDictService = commConfigInputDictService;
	}
	public ISecurityConfigPublicClassService getSecurityConfigPublicClassService() {
		return securityConfigPublicClassService;
	}

	public void setSecurityConfigPublicClassService(ISecurityConfigPublicClassService securityConfigPublicClassService) {
		this.securityConfigPublicClassService = securityConfigPublicClassService;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		if (checkUser(request, response) == null) {
			return mapping.findForward("noLogin");
		}
		String verbId = request.getParameter("verbId");
		if (verbId.equals("add")) {
			return this.add(mapping, form, request, response);
		} else if (verbId.equals("addInit")) {
			return this.addInit(mapping, form, request, response);
		} else if (verbId.equals("query")) {
			return this.query(mapping, form, request, response);
		} else if (verbId.equals("queryDetail")) {
			return this.query(mapping, form, request, response);
		} else if (verbId.equals("update")) {
			return this.update(mapping, form, request, response);
		} else if (verbId.equals("updateInit")) {
			return this.updateInit(mapping, form, request, response);
		} else if (verbId.equals("detail")) {
			return this.detail(mapping, form, request, response);
		} else if (verbId.equals("delete")) {
			return this.delete(mapping, form, request, response);
		} else if (verbId.equals("init")){
			return this.init(mapping, form, request, response);
		}else if(verbId.equals("initDetail")){
			return this.init(mapping, form, request, response);	
		} else {
			return mapping.findForward("fail");
		}
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String useForTree = request.getParameter("useForTree");
		try {
			SecurityConfigPublicClassForm hosform = (SecurityConfigPublicClassForm) form;
			SecurityConfigPublicClass checkData = securityConfigPublicClassService.checkData(hosform.getData().getClassCode().trim());
			if (checkData != null) {
				//ServletContext application = request.getSession().getServletContext();
				//String message = (String)application.getAttribute("EHRPProject_basesecurity.1-000001");
				//String message = BaseSecurityMessage.getMsg("1-000001");
				//hosform.setMessage(message);
				hosform.setMessage(ResourcesUtil.getValue("conf.security.securityInit", "security.java.commom.warn10", request));
				if(useForTree != null && "1".equals(useForTree)){
					this.write2Response(response, "[{flag:'0', msg:'"+hosform.getMessage()+"'}]");
					return null;
				}else{
					securityConfigPublicClassService.addInit(hosform);
					request.setAttribute("dataForm", hosform);
					return mapping.findForward("add");
				}
			}
			hosform.getData().setInputCode(commConfigInputDictService.getInputCode(hosform.getData().getClassName()));
			securityConfigPublicClassService.save(hosform);
			if(useForTree != null && "1".equals(useForTree)){
				this.write2Response(response, "[{flag:'1', msg:'保存成功!'}]");
				return null;
			}else{
				SecurityConfigPublicClassForm hosformNew = new SecurityConfigPublicClassForm();
				return this.query(mapping, hosformNew, request, response);
			}
		}
		catch (Exception e) {
			logger.error(e.getStackTrace());
			if(useForTree != null && "1".equals(useForTree)){
				this.write2Response(response, "[{flag:'0', msg:'保存失败!'}]");
				return null;
			}
			return mapping.findForward("fail");
		}
	}

	public ActionForward query(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String verbId = request.getParameter("verbId");
			SecurityConfigPublicClassForm hosform = (SecurityConfigPublicClassForm) form;
			// ////// page start ////////////////////////
			PageBean pb = new PageBean();
			int count;
			int page = 0;
			int recordCount = securityConfigPublicClassService.getCount(hosform.getId(),hosform.getClassCode(), hosform.getClassName(), hosform.getInputCode(), hosform.getSerialNo(), hosform.getLevelFlag(),hosform.getParentId());
			if(recordCount == 0){
				//查询条件有误，未查询到结果
				String str = ResourcesUtil.getValue("conf.security.securityInit", "comm.java.security.warn1", request);
				hosform.setMessage(str);
			}
			pb.setCount(recordCount);
			String pageString = request.getParameter("page");
			//ServletContext application = request.getSession().getServletContext();
			//int pageSize = Integer.parseInt((String)application.getAttribute("EHRPProject_basesecurity.PAGE_SIZE"));
			//int pageSize = BaseSecurityInit.getPageSize("PAGE_SIZE");
			int pageSize = 10;
			if(request.getSession().getAttribute("page_282881f5346450a201346450a2ad0000")!=null){
				pageSize = Integer.parseInt((String)request.getSession().getAttribute("page_282881f5346450a201346450a2ad0000"));
			}else{
				ServletContext application = request.getSession().getServletContext();
				pageSize = Integer.parseInt((String)application.getAttribute("security.PAGE_SIZE"));
			}
			
			pb.setPageSize(pageSize);
			if (pageString == null || pageString.equals("") || pageString.equals("0")) {
				page = 1;
				pb.setPage(1);
				count = (page - 1) * pageSize;
			} else {
				page = Integer.parseInt(pageString);
				pb.setPage(page);
				count = (page - 1) * pageSize;
			}
			request.setAttribute("pb", pb);
			// ////// page end ////////////////////////
			securityConfigPublicClassService.getSearch(hosform, count, pageSize);
			securityConfigPublicClassService.serchInit(hosform);
			request.setAttribute("dataForm", hosform);
			if(verbId.equals("queryDetail")){
				return mapping.findForward("queryDetail");
			}else{
				return mapping.findForward("query");
			}
			
		}
		catch (Exception e) {
			logger.error(e.getStackTrace());
			return mapping.findForward("fail");
		}
	}

	public ActionForward updateInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			SecurityConfigPublicClassForm hosform = (SecurityConfigPublicClassForm) form;
			securityConfigPublicClassService.updateInit(hosform);
			request.setAttribute("dataForm", hosform);
			return mapping.findForward("update");
		}
		catch (Exception e) {
			logger.error(e.getStackTrace());
			return mapping.findForward("fail");
		}
	}

	public ActionForward addInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			SecurityConfigPublicClassForm hosform = new SecurityConfigPublicClassForm();
			String parentId = request.getParameter("parentId");
			hosform.setParentId(parentId);
			securityConfigPublicClassService.addInit(hosform);
			request.setAttribute("dataForm", hosform);
			return mapping.findForward("add");
		}
		catch (Exception e) {
			logger.error(e.getStackTrace());
			return mapping.findForward("fail");
		}
	}

	public ActionForward detail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			SecurityConfigPublicClassForm hosform = (SecurityConfigPublicClassForm) form;
			securityConfigPublicClassService.showInit(hosform);
			request.setAttribute("dataForm", hosform);
			return mapping.findForward("detail");
		}
		catch (Exception e) {
			logger.error(e.getStackTrace());
			return mapping.findForward("fail");
		}
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			SecurityConfigPublicClassForm hosform = (SecurityConfigPublicClassForm) form;
			SecurityConfigPublicClass checkData = securityConfigPublicClassService.checkData(hosform.getData().getClassCode().trim());
			if ((checkData!=null )&&(!hosform.getData().getClassCode().equals(hosform.getClassCodeHidden()))) {
				//ServletContext application = request.getSession().getServletContext();
				//String message = (String)application.getAttribute("EHRPProject_basesecurity.PAGE_SIZE");
				//String message = BaseSecurityMessage.getMsg("1-000001");
				hosform.setMessage(ResourcesUtil.getValue("conf.security.securityInit", "security.java.commom.warn10", request));
				//hosform.setMessage(message);
				this.write2Response(response, "[{flag:'0', msg:'"+hosform.getMessage()+"'}]");
				return null;
			}
			hosform.getData().setInputCode(commConfigInputDictService.getInputCode(hosform.getData().getClassName()));
			securityConfigPublicClassService.update(hosform);
			this.write2Response(response, "[{flag:'1', msg:'保存成功！'}]");
			return null;
			
		}
		catch (Exception e) {
			e.getStackTrace();
			this.write2Response(response, "[{flag:'0', msg:'保存失败！'}]");
			return null;
		}
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String useForTree = request.getParameter("useForTree");
		try {
			SecurityConfigPublicClassForm hosform = (SecurityConfigPublicClassForm) form;
			int publicCount = this.securityConfigPublicClassService.getPublicCountByPublicClassId(hosform.getIdHidden());
			int publicClass2Count = this.securityConfigPublicClassService.getPublicClass2CountByPublicClassId(hosform.getIdHidden());
			if(publicCount > 0){
				if(useForTree != null && "1".equals(useForTree.trim())){
					this.write2Response(response, "[{flag:'0', msg:'该模块类别下面有"+publicCount+"个模块，请先删除这些模块!'}]");
					return null;
				}else{
					hosform.setMessage("该模块类别下面有"+publicCount+"个模块，请先删除这些模块!");
					return this.query(mapping, hosform, request, response);
				}
			}else if(publicClass2Count > 0){
				if(useForTree != null && "1".equals(useForTree.trim())){
					this.write2Response(response, "[{flag:'0', msg:'该模块类别下面有"+publicClass2Count+"个二级模块类别，请先删除这些二级模块类别!'}]");
					return null;
				}else{
					hosform.setMessage("该模块类别下面有"+publicClass2Count+"个二级模块类别，请先删除这些二级模块类别!");
					return this.query(mapping, hosform, request, response);
				}
			}else{
				securityConfigPublicClassService.delete(hosform);
				if(useForTree != null && "1".equals(useForTree.trim())){
					this.write2Response(response, "[{flag:'1', msg:'删除成功!'}]");
					return null;
				}else{
					return this.query(mapping, hosform, request, response);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			if(useForTree != null && "1".equals(useForTree.trim())){
				this.write2Response(response, "[{flag:'0', msg:'删除失败!'}]");
				return null;
			}else{
				return mapping.findForward("fail");
			}
		}
	}
	
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String verbId = request.getParameter("verbId");
			SecurityConfigPublicClassForm ssform = (SecurityConfigPublicClassForm)form;
			securityConfigPublicClassService.serchInit(ssform);
			request.setAttribute("dataForm", ssform);
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
