package com.tianjian.util.searchsuggest.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.util.searchsuggest.business.ISearchSuggestService;

public class SearchSuggestAction extends Action {
	private ISearchSuggestService ssService;

	public ISearchSuggestService getSsService() {
		return ssService;
	}

	public void setSsService(ISearchSuggestService ssService) {
		this.ssService = ssService;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/xml;charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			String param = (String) request.getParameter("param");
			String ssid = (String) request.getParameter("ssid");
			String backXml = this.ssService.getData(param, ssid);
			if (backXml != null && !"".equals(backXml)) {
				response.getWriter().write(backXml);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
