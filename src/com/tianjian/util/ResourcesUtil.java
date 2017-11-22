package com.tianjian.util;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.Globals;
import org.apache.struts.util.MessageResources;

public class ResourcesUtil {
	public static String getValue(String file,String key, HttpServletRequest request){
		MessageResources mr = MessageResources.getMessageResources(file);
		Locale locale = (Locale)request.getSession().getAttribute(Globals.LOCALE_KEY);
		return mr.getMessage(locale,key);
	}
}
