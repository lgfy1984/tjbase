package com.tianjian.comm.business.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.tianjian.comm.bean.CommConfigHospitalClass;
import com.tianjian.comm.bean.CommCongihMidwifery;
import com.tianjian.comm.bean.CommCongihMidwiferyId;
import com.tianjian.comm.business.ICommCongihMidwiferyService;
import com.tianjian.comm.dao.ICommConfigHospitalClassDAO;
import com.tianjian.comm.dao.ICommCongihMidwiferyDAO;
import com.tianjian.comm.dao.ICommonDAO;
import com.tianjian.comm.struts.form.CommCongihMidwiferyForm;
import com.tianjian.comm.struts.servlet.CommInit;
import com.tianjian.util.Converter;

public class CommCongihMidwiferyServiceImpl implements
		ICommCongihMidwiferyService {
	private ICommConfigHospitalClassDAO classDao;
	private ICommCongihMidwiferyDAO midDao;
	private ICommonDAO comm;
	
	public String getXml(String flag, String inputCode,HttpServletRequest request) {
		ServletContext application = request.getSession().getServletContext();
		// TODO Auto-generated method stub
		//List<?> list=this.getClassDao().findHspClassList(flag, inputCode,Integer.valueOf(CommInit.getProperty("CURRECORD_TANCHUCENG")), 
				//CommInit.getPageSize("PAGE_SIZE_TANCHUCENG"));
		List<?> list=this.getClassDao().findHspClassList(flag, inputCode,Integer.valueOf((String)application.getAttribute("comm.CURRECORD_TANCHUCENG")), 
				Integer.valueOf((String)application.getAttribute("comm.PAGE_SIZE_TANCHUCENG")));
		StringBuffer buffer = new StringBuffer();
		buffer.append("<response>");
		for(int i =0;i<list.size();i++){
			CommConfigHospitalClass com=(CommConfigHospitalClass) list.get(i);
			buffer.append("<ress>");
			buffer.append("<resInputCode>" + Converter.toBlank(com.getInputCode()) + "</resInputCode>");
			buffer.append("<resItemCode>" + Converter.toBlank(com.getClassCode()) + "</resItemCode>");
			buffer.append("<resItemName>" + Converter.toBlank(com.getClassName()) + "</resItemName>");
			buffer.append("</ress>");
		}
		buffer.append("</response>");
		
		return buffer.toString();
	}

	public int getCount(CommCongihMidwiferyForm form) {
		// TODO Auto-generated method stub
		return this.getMidDao().getCount(form);
	}

	public void getSearch(CommCongihMidwiferyForm form, int count, int pageSize) {
		// TODO Auto-generated method stub
		String order="seqNo";
		if(form.getAsc().trim().equals("1")){
			order +=" desc";
		}else{
			order +=" asc";
		}
		form.setOrderNo(order);
		List<?> list=this.getMidDao().getSearch(form, count, pageSize);
		if(list!=null&&list.size()>0){
			List<String> hspConfigBaseinfoNameList=new ArrayList<String>();
			List<String> hspCalssNameList=new ArrayList<String>();
			List<String> seqNoList=new ArrayList<String>();
			List<String> hspConfigBaseinfoIdList=new ArrayList<String>();
			List<String> hspClassCodeList=new ArrayList<String>();
			for(int i=0;i<list.size();i++){
				CommCongihMidwifery com=(CommCongihMidwifery) list.get(i);
				hspConfigBaseinfoIdList.add(Converter.toBlank(com.getId().getHspConfigBaseinfoId()));
				hspClassCodeList.add(Converter.toBlank(com.getId().getHspClassCode()));
				hspConfigBaseinfoNameList.add(this.getComm().getNameById("HspConfigBaseinfo", "id", "itemName", Converter.toBlank(com.getId().getHspConfigBaseinfoId())));
				hspCalssNameList.add(this.getComm().getNameById("CommConfigHospitalClass", "classCode", "className", Converter.toBlank(com.getId().getHspClassCode())));
				seqNoList.add(Converter.toBlank(com.getSeqNo()));
			}
			
			form.setHspConfigBaseinfoNameList(hspConfigBaseinfoNameList);
			form.setHspCalssNameList(hspCalssNameList);
			form.setSeqNoList(seqNoList);
			form.setHspClassCodeList(hspClassCodeList);
			form.setHspConfigBaseinfoIdList(hspConfigBaseinfoIdList);
		}
	}

	public ICommonDAO getComm() {
		return comm;
	}

	public void setComm(ICommonDAO comm) {
		this.comm = comm;
	}

	public ICommCongihMidwiferyDAO getMidDao() {
		return midDao;
	}

	public void setMidDao(ICommCongihMidwiferyDAO midDao) {
		this.midDao = midDao;
	}

	public void delete(CommCongihMidwiferyForm form) {
		// TODO Auto-generated method stub
		CommCongihMidwifery com=this.getMidDao().findByIds(form.getHspConfigBaseinfoIdHidden(), form.getHspCalssCodeHidden());
		this.getMidDao().delete(com);
	}

	public void save(CommCongihMidwiferyForm form) {
		// TODO Auto-generated method stub
		CommCongihMidwifery com=new CommCongihMidwifery();
		CommCongihMidwiferyId comm=new CommCongihMidwiferyId();
		comm.setHspConfigBaseinfoId(Converter.toBlank(form.getHspConfigBaseinfoId().trim()));
		comm.setHspClassCode(Converter.toBlank(form.getHspClassCode().trim()));
		com.setId(comm);
		com.setSeqNo(Converter.toLong(form.getSeqNo().trim()));
		this.getMidDao().add(com);
	}

	public CommCongihMidwifery getByIds(CommCongihMidwiferyForm form) {
		// TODO Auto-generated method stub
		return this.getMidDao().findByIds(form.getHspConfigBaseinfoId(), form.getHspClassCode());
	}

	public ICommConfigHospitalClassDAO getClassDao() {
		return classDao;
	}

	public void setClassDao(ICommConfigHospitalClassDAO classDao) {
		this.classDao = classDao;
	}

	public int getMaxNumber(String tableName, String columnName) {
		// TODO Auto-generated method stub
		return this.getMidDao().getMaxNumber(tableName, columnName);
	}
}
