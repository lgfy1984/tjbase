package com.tianjian.security.business.service;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.tianjian.comm.business.service.CommonServiceImpl;
import com.tianjian.security.bean.SecurityConfigParameter;
import com.tianjian.security.business.ISecurityConfigParameterService;
import com.tianjian.security.dao.ISecurityConfigParameterDAO;
import com.tianjian.security.struts.form.SecurityConfigParameterForm;
import com.tianjian.security.struts.servlet.SecurityConfigParam;
import com.tianjian.util.Converter;

public class SecurityConfigParameterServiceImpl extends CommonServiceImpl implements ISecurityConfigParameterService {
	private ISecurityConfigParameterDAO securityConfigParameterDAO;
	

	public ISecurityConfigParameterDAO getSecurityConfigParameterDAO() {
		return securityConfigParameterDAO;
	}


	public void setSecurityConfigParameterDAO(
			ISecurityConfigParameterDAO securityConfigParameterDAO) {
		this.securityConfigParameterDAO = securityConfigParameterDAO;
	}


	public int getCount(SecurityConfigParameterForm form) {

		return securityConfigParameterDAO.count(form
				.getClassName(), form.getProjectName(), form.getItemName(), form.getItemValue());
	}

	
	public void deleteData(String id) {	
		try{
			SecurityConfigParameter scp = this.securityConfigParameterDAO.findById(id);
			this.securityConfigParameterDAO.deleteData(scp);	
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	
	public void getByClassCode(SecurityConfigParameterForm form) {
		List<?> list = securityConfigParameterDAO.queryClassByParamId(form.getHiddenId());
		if (list != null && list.size() > 0) {
			String hspConfigBaseinfoName = "";
			String classCode = "";
			String className = "";
			String inputCode =  "";
			String functionDescription =  "";
			String comments =  "";
			String id =  "";
			String hspConfigBaseinfoId = "";
			String itemName =  ""; 
			String itemValue =  "";
			String usageDescription =  "";
			String initValue =  "";
			
			Object obj = (Object) list.get(0);
			classCode = this.transNullToString(((Object[])list.get(0))[0]);
			className = this.transNullToString(((Object[])list.get(0))[1]);
			inputCode = this.transNullToString(((Object[])list.get(0))[2]);
			functionDescription = this.transNullToString(((Object[])list.get(0))[3]);
			comments = this.transNullToString(((Object[])list.get(0))[4]);
			id = this.transNullToString(((Object[])list.get(0))[5]);
			hspConfigBaseinfoId = this.transNullToString(((Object[])list.get(0))[6]);
			itemName = this.transNullToString(((Object[])list.get(0))[7]); 
			itemValue = this.transNullToString(((Object[])list.get(0))[8]);
			usageDescription = this.transNullToString(((Object[])list.get(0))[9]);
			initValue = this.transNullToString(((Object[])list.get(0))[10]);
			
			
			hspConfigBaseinfoName = this.transNullToString(this.getCommonDAO().getValueByAnotherSQL("HSP_CONFIG_BASEINFO", "ID", hspConfigBaseinfoId, "ITEM_NAME"));
			
			form.setClassCode(classCode);
			form.setClassName(className);
			form.setInputCode(inputCode);
			form.setFunctionDescription(functionDescription);
			form.setComments(comments);
			form.setId(id);
			form.setHspConfigBaseinfoId(hspConfigBaseinfoId);
			form.setItemName(itemName);
			form.setHspConfigBaseinfoName(hspConfigBaseinfoName);
			form.setItemValue(itemValue);
			form.setUsageDescription(usageDescription);
			form.setInitValue(initValue);
			
		}
	}

	
	public void getData(SecurityConfigParameterForm form, int cur,
			int pageSize) {
		String order = "";
		if (form.getOrderNo().equals("1")) {
			order = " c.projectName ";
		} else if (form.getOrderNo().equals("2")) {
			order = " s.className ";
		} else if (form.getOrderNo().equals("3")) {
			order = " b.itemName ";
		} else if (form.getOrderNo().equals("4")) {
			order = " b.itemValue ";
		} else {
			order = " s.classCode";
		}
		if (form.getAsc().equals("1")) {
			order += " desc";
		} else {
			order += " asc";
		}
		List<?> list = securityConfigParameterDAO.queryList(form.getClassName(),form.getProjectName(),form.getItemName(), form.getItemValue(), order, cur, pageSize);
		if (list != null && list.size() > 0) {
			String[] classCodeList = new String[list.size()];
			String[] classNameList = new String[list.size()];
			String[] inputCodeList = new String[list.size()];
			String[] functionDescriptionList = new String[list.size()];
			String[] commentsList = new String[list.size()];
			String[] idList = new String[list.size()];
			String[] itemNameList = new String[list.size()];
			String[] itemValueList = new String[list.size()];
			String[] hspConfigBaseinfoIdList = new String[list.size()];
			String[] usageDescriptionList = new String[list.size()];
			String[] initValueList = new String[list.size()];
			String[] projectNameList = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				Object obj = (Object) list.get(i);
				String classCode = this.transNullToString(((Object[])list.get(i))[0]);
				classCodeList[i] = this.transNullToString(((Object[])list.get(i))[0]);
				if(classCode != null && classCode != ""){
					List<?> l= this.securityConfigParameterDAO.queryProjectName(classCode);
					projectNameList[i] = this.transNullToString((String)l.get(0));
				}
				classNameList[i] = this.transNullToString(((Object[])list.get(i))[1]);
				inputCodeList[i] = this.transNullToString(((Object[])list.get(i))[2]);
				functionDescriptionList[i] = this.transNullToString(((Object[])list.get(i))[3]);
				commentsList[i] = this.transNullToString(((Object[])list.get(i))[4]);			
				idList[i] = this.transNullToString(((Object[])list.get(i))[5]);
				hspConfigBaseinfoIdList[i] = this.transNullToString(((Object[])list.get(i))[6]);
				itemNameList[i] = this.transNullToString(((Object[])list.get(i))[7]); 
				itemValueList[i] = this.transNullToString(((Object[])list.get(i))[8]);
				usageDescriptionList[i] = this.transNullToString(((Object[])list.get(i))[9]);
				initValueList[i] = this.transNullToString(((Object[])list.get(i))[10]);
			}
			form.setClassCodeList(classCodeList);
			form.setClassNameList(classNameList);
			form.setInputCodeList(inputCodeList);
			form.setFunctionDescriptionList(functionDescriptionList);
			form.setCommentsList(commentsList);
			form.setIdList(idList);
			form.setItemNameList(itemNameList);
			form.setItemValueList(itemValueList);
			form.setHspConfigBaseinfoIdList(hspConfigBaseinfoIdList);
			form.setUsageDescriptionList(usageDescriptionList);
			form.setInitValueList(initValueList);
			form.setProjectNameList(projectNameList);
		}
	}
	public List<?> getData(){
		return securityConfigParameterDAO.findAll();
	}
	
	public boolean saveData(SecurityConfigParameterForm form) {
	try {
			SecurityConfigParameter scpc = new SecurityConfigParameter();
			this.setScpc(form, scpc);
			this.securityConfigParameterDAO.saveData(scpc);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean saveList(SecurityConfigParameterForm form) {
		try {
				
				//拿到前台的list总体数据
				List<SecurityConfigParam> moniList = form.getList();
				if(moniList!=null&&moniList.size()>0){
					for(int i =0;i<moniList.size();i++){
						SecurityConfigParam param = moniList.get(i);
						if(param.getId()!= null && param.getId().length()>0){//判断是否已经存在了ID,如果存在ID就执行更新的操作,不然就保存
							SecurityConfigParameter bean = this.securityConfigParameterDAO.findById(param.getId());
							bean.setClassCode(Converter.toBlank(param.getClassCode()));
							bean.setItemName(Converter.toBlank(param.getItemName()));
							bean.setItemValue(Converter.toBlank(param.getItemValue()));
							bean.setInitValue(Converter.toBlank(param.getInitValue()));
							bean.setUsageDescription(Converter.toBlank(param.getUsageDescription()));
							this.securityConfigParameterDAO.updateData(bean);
						}else{
							SecurityConfigParameter scpc = new SecurityConfigParameter();
							scpc.setClassCode(Converter.toBlank(param.getClassCode()));
							scpc.setItemName(Converter.toBlank(param.getItemName()));
							scpc.setItemValue(Converter.toBlank(param.getItemValue()));
							scpc.setInitValue(Converter.toBlank(param.getInitValue()));
							scpc.setUsageDescription(Converter.toBlank(param.getUsageDescription()));
							this.securityConfigParameterDAO.saveData(scpc);
						}
						
					}
					return true;
				}else{
					return false;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}
	public void updateData(SecurityConfigParameterForm form) {
		SecurityConfigParameter scpc = new SecurityConfigParameter();
		this.setScpc(form, scpc);
		this.securityConfigParameterDAO.updateData(scpc);

	}

	
	public int check(String classCode) {
		return securityConfigParameterDAO.checkClassCode(classCode);
	}
	
	private void setScpc(SecurityConfigParameterForm form,SecurityConfigParameter scpc){
		scpc.setId(Converter.toBlank(form.getHiddenId()));
		scpc.setHspConfigBaseinfoId(Converter.toBlank(form.getHspConfigBaseinfoId()));
		scpc.setClassCode(Converter.toBlank(form.getClassCode()));
		scpc.setItemName(Converter.toBlank(form.getItemName()));
		scpc.setItemValue(Converter.toBlank(form.getItemValue()));
		scpc.setUsageDescription(Converter.toBlank(form.getUsageDescription()));
		scpc.setInitValue(Converter.toBlank(form.getInitValue()));
	}
	
	private String transNullToString(Object valueOf) {
		String temp = "";
		try{
			if(valueOf != null)
				temp = String.valueOf(valueOf).trim();
			if(valueOf != null && valueOf.equals("null"))
				temp = "";
		}catch(Exception e){
			e.printStackTrace();
			temp = "";
		}
		return temp;
	}
	
	
	/**处理弹出层子画面*/
	@SuppressWarnings("unchecked")
	public String getXml(String flag,String inputCode,HttpServletRequest request){
		ServletContext application = request.getSession().getServletContext();
		List<?> list=this.securityConfigParameterDAO.getHsp(flag, inputCode, Integer.valueOf((String)application.getAttribute("security.CURRECORD_TANCHUCENG")), Integer.valueOf((String)application.getAttribute("security.PAGE_SIZE_TANCHUCENG")));
		StringBuffer buffer = new StringBuffer(); 
		buffer.append("<response>");
		for(int i =0;i<list.size();i++){
			Object obj = (Object) list.get(i);
			 //-----以下xml标签不要改变--------------------------------------------------------------
			buffer.append("<ress>");
			buffer.append("<resInputCode>" + this.transNullToString(((Object[])list.get(i))[2]) + "</resInputCode>");
			buffer.append("<resItemCode>" + this.transNullToString(((Object[])list.get(i))[0]) + "</resItemCode>");
			buffer.append("<resItemName>" + this.transNullToString(((Object[])list.get(i))[1]) + "</resItemName>");	
			buffer.append("</ress>");
		}
		buffer.append("</response>");
		
		return buffer.toString();
	}
	
	/**处理弹出层子画面*/
	@SuppressWarnings("unchecked")
	public String getHsp(String flag,String inputCode,HttpServletRequest request){
		ServletContext application = request.getSession().getServletContext();
		List<?> list=this.securityConfigParameterDAO.findHspList(flag, inputCode, "",Integer.valueOf((String)application.getAttribute("security.CURRECORD_TANCHUCENG")).intValue(), Integer.valueOf((String)application.getAttribute("security.PAGE_SIZE_TANCHUCENG")).intValue() );
		StringBuffer buffer = new StringBuffer();
		buffer.append("<response>");
		for(int i =0;i<list.size();i++){
			Object[] obj = (Object[]) list.get(i);
			// -----以下xml标签不要改变--------------------------------------------------------------
			buffer.append("<ress>");
			buffer.append("<resInputCode>" + this.transNullToString(obj[3]) + "</resInputCode>");
			buffer.append("<resItemCode>" + this.transNullToString(obj[1]) + "</resItemCode>");
			buffer.append("<resItemName>" + this.transNullToString(obj[2]) + "</resItemName>");
			buffer.append("<resItemId>" + this.transNullToString(obj[0]) + "</resItemId>");
			buffer.append("</ress>");
		}
		buffer.append("</response>");
		
		return buffer.toString();
	}
	
	public List<?> findByClassCode(String classCode){
		return securityConfigParameterDAO.queryByClassCode(classCode);
		
	}
	

}
