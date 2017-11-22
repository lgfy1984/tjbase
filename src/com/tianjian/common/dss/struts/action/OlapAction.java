package com.tianjian.common.dss.struts.action;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class OlapAction extends Action {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(OlapAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String verbId = request.getParameter("verbId");
		try {
			if (verbId != null & "olap".equals(verbId.trim())) {
				
				request.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=UTF-8");
				response.setHeader("progma","no-cache");   
				response.setHeader("Cache-Control","no-cache");   
				response.setDateHeader("Expires",0);

				
				String path = mapping.findForward("olap").getPath();
				logger.info("path=" + path);

				// 传参时设置编码
				ActionForward forward = new ActionForward(path + "?"
						+ setParams(request), true);// true是Redirect
				logger.info("ooo=" + path + "?" + setParams(request));

				return forward;
			}

			// path=/dss/olap.jsp
			// ooo=/dss/olap.jsp?query=facthspimportdaily&storage=dfgg
		} catch (Exception e) {
			logger.error(this, e);
			return mapping.findForward("fail");
		}
		return mapping.findForward("index");
	}

	public String setParams(HttpServletRequest request) {
		Map<String, Object> paramMap = request.getParameterMap();
		StringBuilder sb = new StringBuilder();
		if (paramMap != null) {
			for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
				try {
					if (entry.getValue() instanceof String) {
						String value = java.net.URLEncoder.encode(
								(String) entry.getValue(), "UTF-8");
						sb.append(entry.getKey()).append("=").append(value).append("&");
					}else if(entry.getValue() instanceof String[]){
						for(String value : (String[])entry.getValue()){
							value = java.net.URLEncoder.encode(value, "UTF-8");
							sb.append(entry.getKey()).append("=").append(value).append("&");
						}
					}
				} catch (UnsupportedEncodingException e) {
					System.err.println(e);
				}
			}
		}
		String params = sb.toString();
		if (params.endsWith("&")) {
			params = params.substring(0, params.length() - 1);
		}
		return params;
	}
}
