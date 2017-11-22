package com.tianjian.hsp.business;

import javax.servlet.http.HttpServletRequest;

public interface IHspConfigBaseinfoLocalBaseService {
	/** 处理弹出层子画面 */
	public String getXml( String flag, String inputCode, String staffHspId, HttpServletRequest request);
}
