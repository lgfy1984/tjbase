package com.tianjian.hsp.business.service;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.tianjian.hsp.bean.HspConfigBaseinfoLocalBase;
import com.tianjian.hsp.business.IHspConfigBaseinfoLocalBaseService;
import com.tianjian.hsp.dao.IHspConfigBaseinfoLocalBaseDAO;
import com.tianjian.util.Converter;

public class HspConfigBaseinfoLocalBaseServiceImpl implements IHspConfigBaseinfoLocalBaseService {
	private IHspConfigBaseinfoLocalBaseDAO hspConfigBaseinfoBaseDAO;
	
	public IHspConfigBaseinfoLocalBaseDAO getHspConfigBaseinfoBaseDAO() {
		return hspConfigBaseinfoBaseDAO;
	}

	public void setHspConfigBaseinfoBaseDAO(
			IHspConfigBaseinfoLocalBaseDAO hspConfigBaseinfoBaseDAO) {
		this.hspConfigBaseinfoBaseDAO = hspConfigBaseinfoBaseDAO;
	}

	/** 处理弹出层子画面 */
	public String getXml( String flag, String inputCode, String staffHspId, HttpServletRequest request){
		ServletContext application = request.getSession().getServletContext();
		StringBuffer buffer = new StringBuffer();
		List<?> list= null;
		list = hspConfigBaseinfoBaseDAO.findHspList(flag, inputCode, staffHspId, Integer.valueOf((String)application.getAttribute("hsp.CURRECORD_TANCHUCENG")), Integer.valueOf((String)application.getAttribute("hsp.PAGE_SIZE_TANCHUCENG")));;
		buffer.append("<response>");
		for(int i=0; i<list.size(); i++){
			
			HspConfigBaseinfoLocalBase charbean =(HspConfigBaseinfoLocalBase) list.get(i);
		
			// -----以下xml标签不要改变--------------------------------------------------------------
			buffer.append("<ress>");
			buffer.append("<resInputCode>"+charbean.getInputCode()+"</resInputCode>");
			buffer.append("<resItemCode>"+Converter.toBlank(charbean.getItemCode())+"</resItemCode>");
			buffer.append("<resItemName>"+Converter.toBlank(charbean.getItemName())+"</resItemName>");
			buffer.append("<resItemId>"+Converter.toBlank(charbean.getId())+"</resItemId>");
			buffer.append("</ress>");
			
		}
		buffer.append("</response>");
		return buffer.toString();
	}
}
