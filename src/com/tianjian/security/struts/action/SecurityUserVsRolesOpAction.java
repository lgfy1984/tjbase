package com.tianjian.security.struts.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.security.business.ISecurityUserVsRolesOpService;
import com.tianjian.security.struts.form.SecurityUserVsRolesOpForm;
import com.tianjian.util.ResourcesUtil;
import com.tianjian.util.comm.PageBean;

public class SecurityUserVsRolesOpAction extends Action{

	
	private ISecurityUserVsRolesOpService securityUserVsRolesOpService;

	public ISecurityUserVsRolesOpService getSecurityUserVsRolesOpService() {
		return securityUserVsRolesOpService;
	}

	public void setSecurityUserVsRolesOpService(
			ISecurityUserVsRolesOpService securityUserVsRolesOpService) {
		this.securityUserVsRolesOpService = securityUserVsRolesOpService;
	}
    
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){        
		String verbId = request.getParameter("verbId");
        if(verbId.equals("query")){
            return query(mapping, form, request, response);
        }  else if(verbId.equals("detail")){
            return detail(mapping, form, request, response);
        } else if(verbId.equals("init")){
        	return this.init(mapping, form, request, response);
        }  else {
            return mapping.findForward("fail");
        }
    }
	
	public ActionForward query(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	try {
			
    		SecurityUserVsRolesOpForm hosform = (SecurityUserVsRolesOpForm)form;
						
    		////////	page start   ////////////////////////
	    	PageBean pb = new PageBean();
	    	int count;
	    	int page = 0;
	    	int recordCount = securityUserVsRolesOpService.getCount(hosform.getSecurityConfigRolesId()
					, hosform.getHspConfigBaseinfoId()
					);
	    	if(recordCount == 0){
				//查询条件有误，未查询到结果
				String str = ResourcesUtil.getValue("conf.security.securityInit", "comm.java.security.warn1", request);
				hosform.setMessage(str);
			}
	        pb.setCount(recordCount);
	        String pageString = request.getParameter("page");
	        //ServletContext application = request.getSession().getServletContext();
	        //int pageSize = Integer.parseInt((String)application.getAttribute("EHRPProject_basesecurity.PAGE_SIZE"));
	        //int pageSize = 10;
	        int pageSize = 10;
			if(request.getSession().getAttribute("page_282881f53464510601346451060b0000")!=null){
				pageSize = Integer.parseInt((String)request.getSession().getAttribute("page_282881f53464510601346451060b0000"));
			}else{
				ServletContext application = request.getSession().getServletContext();
				pageSize = Integer.parseInt((String)application.getAttribute("security.PAGE_SIZE"));
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
	        ////////	page end   ////////////////////////
	        securityUserVsRolesOpService.getSearch(hosform, count, pageSize);
	        securityUserVsRolesOpService.searchInit(hosform);
	        request.setAttribute("data", hosform);		        		
			return mapping.findForward("query");
		} catch(Exception e) {
			return mapping.findForward("fail");
		}
    }
	
	public ActionForward detail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	try {
			
    		SecurityUserVsRolesOpForm hosform = (SecurityUserVsRolesOpForm)form;
    		this.securityUserVsRolesOpService.setRequest(request);
    		securityUserVsRolesOpService.getDetail(hosform);
			request.setAttribute("data", hosform);		        
			return  mapping.findForward("detail");
			
		} catch(Exception e) {
			return mapping.findForward("fail");
		}	
    }
	
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			SecurityUserVsRolesOpForm ssform = (SecurityUserVsRolesOpForm) form;
			securityUserVsRolesOpService.searchInit(ssform);
			request.setAttribute("data", ssform);
			return mapping.findForward("query");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}
}
