package com.tianjian.security.business.service;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.tianjian.comm.struts.servlet.CommInit;
import com.tianjian.security.bean.SecurityConfigParamProject;
import com.tianjian.security.bean.SecurityConfigParamclass;
import com.tianjian.security.business.ISecurityConfigParamProjectService;
import com.tianjian.security.dao.ISecurityConfigParamProjectDAO;
import com.tianjian.security.struts.form.SecurityConfigParamProjectForm;
import com.tianjian.util.Converter;

public class SecurityConfigParamProjectServiceImpl implements
		ISecurityConfigParamProjectService {
	private ISecurityConfigParamProjectDAO securityConfigParamProjectDAO;


	public ISecurityConfigParamProjectDAO getSecurityConfigParamProjectDAO() {
		return securityConfigParamProjectDAO;
	}

	public void setSecurityConfigParamProjectDAO(
			ISecurityConfigParamProjectDAO securityConfigParamProjectDAO) {
		this.securityConfigParamProjectDAO = securityConfigParamProjectDAO;
	}

	public int count(SecurityConfigParamProjectForm form) {
		return this.getSecurityConfigParamProjectDAO().count(form.getProjectCode(), form
				.getProjectName(), form.getInputCode(), form.getSeqNo());
	}

	public void seqNoMaker(SecurityConfigParamProjectForm form) {
		form.setSeqNo(Converter.toBlank(this.getSecurityConfigParamProjectDAO().maxFind()));
	}
	
	public void deleteData(String projectCode) {
		// System.out.println(classCode);	
		SecurityConfigParamProject scp = this.getSecurityConfigParamProjectDAO().queryByClassCode(projectCode);
		this.getSecurityConfigParamProjectDAO().deleteData(scp);

	}

	
	public void getByClassCode(SecurityConfigParamProjectForm form) {
		SecurityConfigParamProject scp = this.getSecurityConfigParamProjectDAO()
				.queryByClassCode(form.getProjectCodeHidden());
		// System.out.println(form.getClassCode1());
		form.setProjectCode(Converter.toBlank(scp.getProjectCode()));
		form.setProjectName(Converter.toBlank(scp.getProjectName()));
		form.setInputCode(Converter.toBlank(scp.getInputCode()));
		form.setComments(Converter.toBlank(scp.getComments()));
		form.setSeqNo(Converter.toBlank(scp.getSeqNo()));
		//form.setProjectCode(Converter.toBlank(scp.getProjectCode()));

	}

	
	public void getData(SecurityConfigParamProjectForm form, int cur,
			int pageSize) {
		String order = "";
		if (form.getOrderNo().equals("1")) {
			order = " s.projectCode";
		} else if (form.getOrderNo().equals("2")) {
			order = " s.projectName ";
		} else if (form.getOrderNo().equals("3")) {
			order = " s.inputCode ";
		} else if (form.getOrderNo().equals("4")) {
			order = " s.seqNo ";
		} else {
			order = " s.projectCode";
		}
		if (form.getAsc().equals("1")) {
			order += " desc";
		} else {
			order += " asc";
		}
		List<?> list = this.getSecurityConfigParamProjectDAO().queryList(form.getProjectCode(), 
				form.getProjectName(), form.getInputCode(), form.getSeqNo(), order, cur, pageSize);
		if (list != null && list.size() > 0) {
			String[] projectCodeList = new String[list.size()];
			String[] projectNameList = new String[list.size()];
			String[] inputCodeList = new String[list.size()];		
			String[] commentsList = new String[list.size()];
			String[] seqNoList = new String[list.size()];
			//String[] projectCodeList = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				Object obj = (Object) list.get(i);
				SecurityConfigParamProject scp = (SecurityConfigParamProject) obj;
				projectCodeList[i] = com.tianjian.util.Converter.toBlank(scp.getProjectCode());
				projectNameList[i] = Converter.toBlank(scp.getProjectName());
				inputCodeList[i] = Converter.toBlank(scp.getInputCode());
				commentsList[i] = Converter.toBlank(scp.getComments());
				seqNoList[i] = Converter.toBlank(scp.getSeqNo());
				//projectCodeList[i] = Converter.toBlank(scp.getProjectCode());
			}
			form.setProjectCodeList(projectCodeList);
			form.setProjectNameList(projectNameList);
			form.setInputCodeList(inputCodeList);
			form.setCommentsList(commentsList);
			form.setSeqNoList(seqNoList);
			//form.setProjectCodeList(projectCodeList);
		}
	}

	
	public void saveData(SecurityConfigParamProjectForm form) {
		try {
			SecurityConfigParamProject scp = new SecurityConfigParamProject();
			this.setAdd(form, scp);
			this.getSecurityConfigParamProjectDAO().saveData(scp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public SecurityConfigParamProject checkData(String projectCode){
		return this.getSecurityConfigParamProjectDAO().getByProjectCode(projectCode);
	}
	
	
	public void updateData(SecurityConfigParamProjectForm form) {
		SecurityConfigParamProject scp = this.securityConfigParamProjectDAO.queryByClassCode(form.getProjectCode());
		this.setData(form, scp);
		this.getSecurityConfigParamProjectDAO().updateData(scp);

	}

	
	public int check(String projectCode) {
		return this.getSecurityConfigParamProjectDAO().checkClassCode(projectCode);
	}

	private void setData(SecurityConfigParamProjectForm form,
			SecurityConfigParamProject scp) {
		scp.setProjectCode(Converter.toBlank(form.getProjectCode()));
		scp.setProjectName(Converter.toBlank(form.getProjectName()));
		scp.setInputCode(Converter.toBlank(form.getInputCode()));
		scp.setSeqNo(Long.valueOf((form.getSeqNo() == null || form.getSeqNo() == "") ?"0" : form.getSeqNo()));
		scp.setComments(Converter.toBlank(form.getComments()));
		//scp.setProjectCode(Converter.toBlank(form.getProjectCode()));
	}
	
	private void setAdd(SecurityConfigParamProjectForm form,
			SecurityConfigParamProject scp) {
		scp.setProjectCode(Converter.toBlank(form.getProjectCode()));
		scp.setProjectName(Converter.toBlank(form.getProjectName()));
		scp.setInputCode(Converter.toBlank(form.getInputCode()));
		scp.setSeqNo(Long.valueOf( (form.getSeqNo() == null || form.getSeqNo() == "") ?"0" : form.getSeqNo()));
		scp.setComments(Converter.toBlank(form.getComments()));
		//scp.setProjectCode(Converter.toBlank(form.getProjectCode()));
	}
	
	
	/**处理弹出层子画面*/
	@SuppressWarnings("unchecked")
	public String getXml(String flag,String inputCode,HttpServletRequest request){
		ServletContext application = request.getSession().getServletContext(); 
		List<?> list=this.getSecurityConfigParamProjectDAO().getProject(flag, inputCode, Integer.valueOf((String)application.getAttribute("security.CURRECORD_TANCHUCENG")), Integer.valueOf((String)application.getAttribute("security.PAGE_SIZE_TANCHUCENG")));
		StringBuffer buffer = new StringBuffer();
		buffer.append("<response>");
		for(int i =0;i<list.size();i++){
			SecurityConfigParamProject charbean = (SecurityConfigParamProject)list.get(i);
			 //-----以下xml标签不要改变--------------------------------------------------------------
			buffer.append("<ress>");
			buffer.append("<resInputCode>" + charbean.getInputCode() + "</resInputCode>");
			buffer.append("<resItemCode>" + Converter.toBlank(charbean.getProjectCode()) + "</resItemCode>");
			buffer.append("<resItemName>" + charbean.getProjectName() + "</resItemName>");	
			buffer.append("</ress>");
		}
		buffer.append("</response>");
		
		return buffer.toString();
	}
}
