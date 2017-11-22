package com.tianjian.security.business.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.tianjian.security.bean.SecurityConfigMenus;
import com.tianjian.security.bean.SecurityConfigParamProject;
import com.tianjian.security.bean.SecurityConfigParamclass;
import com.tianjian.security.bean.SecurityConfigParameter;
import com.tianjian.security.business.ISecurityConfigParamClassService;
import com.tianjian.security.dao.ISecurityConfigParamClassDAO;
import com.tianjian.security.struts.form.SecurityConfigParamClassForm;
import com.tianjian.util.Converter;
import com.tianjian.util.ResourcesUtil;
import com.tianjian.util.db.DBTools;

public class SecurityConfigParamClassServiceImpl implements
		ISecurityConfigParamClassService {
	private ISecurityConfigParamClassDAO securityConfigParamClassDAO;
	
	public ISecurityConfigParamClassDAO getSecurityConfigParamClassDAO() {
		return securityConfigParamClassDAO;
	}
	public void setSecurityConfigParamClassDAO(
			ISecurityConfigParamClassDAO securityConfigParamClassDAO) {
		this.securityConfigParamClassDAO = securityConfigParamClassDAO;
	}
	
	
	private HttpServletRequest request;
	
	
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	
	
	@SuppressWarnings("unchecked")
	
	public List<?> addTable(String[] tableName, String fileName) {
		Connection conn=DBTools.getConnection();
		StringBuffer sb=new StringBuffer();
		List list=new ArrayList();
		BufferedReader reader=null;
		String t="";
		try {
			for(int k=0;k<tableName.length;k++){
				File sqlFile=new File(fileName);
				t=tableName[k];
				File files=new File(sqlFile+"/"+tableName[k]+".SQL");
				if(files.exists()){
					securityConfigParamClassDAO.deleteTable(conn, "delete from "+tableName[k]);
					String file=sqlFile+"/"+tableName[k]+".sql";
					reader=new BufferedReader(new FileReader(file));
					String tempReader=null;
					int line=1;
					while((tempReader=reader.readLine())!=null){
						line++;
						sb.append(tempReader);
					}
					reader.close();
					String[] sql=sb.toString().split(";");
					boolean b=false;
					List ls=new ArrayList();
					for(int i=0;i<sql.length;i++){
						ls.add(sql[i].trim().toString());
					}
					b=securityConfigParamClassDAO.initTables(conn, ls);
					if(b){
						
						list.add("success");
					}else{
						list.add(tableName[k]+"fail");
					}
				}else{
					list.add(tableName[k]+"file not exsit");
				}
			}
			return list;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			list.add("fail");
		} catch (IOException e) {
			e.printStackTrace();
			list.add("fail");
		}finally{
			if(reader!=null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		return list;
	}
	
	public String queryTable(String tableName) {
			SecurityConfigParameter p=securityConfigParamClassDAO.getTableName(tableName);
			SecurityConfigMenus m=securityConfigParamClassDAO.getUrl(p.getItemName());
			if(m.getMenuUrl()==null){
				return "0";
			}else{
				return m.getMenuUrl();
			}
	}
	
	public String getExcel(String[] tableName,String path) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName="";
		try {
			Connection conn=DBTools.getConnection();
			List list=new ArrayList();
			List tableValue=new ArrayList();
			for(int a=0;a<tableName.length;a++){
				list=securityConfigParamClassDAO.getColmun(conn, tableName[a]);
				tableValue=securityConfigParamClassDAO.getTables(conn,tableName[a]);
				
				String worksheet = tableName[a];
				
				WritableWorkbook workbook=null;
				fileName=tableName[a]+sdf.format(new Date())+".xls";
				OutputStream os = new FileOutputStream(path+fileName);
				workbook = Workbook.createWorkbook(os);
				WritableSheet sheet = workbook.createSheet(worksheet, 0); 
				sheet.setColumnView(0, 5);
				jxl.write.Label label;
				for(int j=0;j<list.size();j++){
					
					jxl.write.WritableFont wfont = new jxl.write.WritableFont(WritableFont.createFont(ResourcesUtil.getValue("conf.security.securityInit", "security.java.business.service.SecurityConfigParamClassServiceImpl.item", request)),14); //黑体  
					WritableCellFormat font = new WritableCellFormat(wfont);   
					//Label(列号,行号 ,内容 )
					label = new Label(j+1, 0, list.get(j).toString(),font);   
					sheet.addCell(label); 
				}
				int n=1;
				for(int i=0;i<tableValue.size();i++){
					Object[] obj=(Object[])tableValue.get(i);
					label = new jxl.write.Label(0, i+1, n++ +""); 
					sheet.addCell(label);
					for(int k=0;k<obj.length;k++){
						WritableCellFormat wc = new WritableCellFormat();   
						wc.setAlignment(Alignment.CENTRE);
						label = new Label(k+1,i+1,Converter.toBlank(obj[k]),wc);   
						sheet.addCell(label); 
					}
				}
				workbook.write(); 
				workbook.close();
				os.close();
			}
			return zipFile(path,fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	private String zipFile(String pathDir,String fileName){
		File file=new File(pathDir);
		if(!file.isDirectory()){
			System.out.println("not directory!");
		}
		try {
			String[] entries =file.list();
			List list=new ArrayList();
			for(int j=0;j<entries.length;j++){
				int p=entries[j].lastIndexOf(".");
				String c=entries[j].substring(p+1);
				if(c.equals("xls")){
					list.add(entries[j]);
				}
			}
			byte[] buffer = new byte[4096];
			int bytesRead; 
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
			String path="backupExcel"+sdf.format(new Date())+".zip";
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(pathDir+path));
			for (int i = 0; i < list.size(); i++) { 
				File f = new File(file, list.get(i).toString()); 
				if (f.isDirectory()){
					continue; 
				}
				FileInputStream in = new FileInputStream(f); 
				ZipEntry entry = new ZipEntry(f.getPath()); 
				out.putNextEntry(entry); 
				while ((bytesRead = in.read(buffer)) != -1) 
				out.write(buffer, 0, bytesRead); 
				in.close(); 
			} 
			out.close();
			deleteAllxls(pathDir);
			return path;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	private boolean deleteAllxls(String fullPath) {
		boolean ret = false;
		File file = new File(fullPath);
		if (file.exists()) {
			if (file.isDirectory()) {
				File[] fileList = file.listFiles();
				for (int i = 0; i < fileList.length; i++) {
					String filePath = fileList[i].getPath();
					deleteAllxls(filePath);
				}
			}
			if (file.isFile()) {
				String d=file.getName();
				int d1=d.lastIndexOf(".");
				String n=d.substring(d1+1);
				if(n.equals("xls")){
					file.delete();
				}
			}
		}
		return ret;
	}
	
	//***************************************
	
	public int count(SecurityConfigParamClassForm form) {

		return securityConfigParamClassDAO.count(form.getClassCode(), form
				.getClassName(), form.getInputCode(), form
				.getProjectName());
	}

	public void seqNoMaker(SecurityConfigParamClassForm form) {
		form.setProjectCode(Converter.toBlank(this.securityConfigParamClassDAO.maxFind()));
	}
	
	public void deleteData(String classCode) {
		// System.out.println(classCode);	
		SecurityConfigParamclass scp = securityConfigParamClassDAO.queryByClassCode(classCode);
		securityConfigParamClassDAO.deleteData(scp);

	}

	
	public void getByClassCode(SecurityConfigParamClassForm form) {
		SecurityConfigParamclass scp = securityConfigParamClassDAO.queryByClassCode(form.getClassCode1());
	
		// System.out.println(form.getClassCode1());
		form.setClassCode(Converter.toBlank(scp.getClassCode()));
		form.setClassName(Converter.toBlank(scp.getClassName()));
		form.setInputCode(Converter.toBlank(scp.getInputCode()));
		form.setFunctionDescription(Converter.toBlank(scp.getFunctionDescription()));
		form.setComments(Converter.toBlank(scp.getComments()));
		//form.setProjectCode(Converter.toBlank(scp.getProjectCode()));
		form.setProjectCode(Converter.toBlank(scp.getProjectCode()));
		if(scp.getProjectCode()!= null && scp.getProjectCode() != ""){
			SecurityConfigParamProject data = this.getSecurityConfigParamClassDAO().queryByProjectCode(scp.getProjectCode());
			form.setProjectName(data.getProjectName());
			
		}
		
	}

	
	public void getData(SecurityConfigParamClassForm form, int cur,
			int pageSize) {
		String order = "";
		if(form.getClassCode().equals("1")){
			order = "s.classCode ";
		}else if (form.getOrderNo().equals("2")) {
			order = " s.className ";
		} else if (form.getOrderNo().equals("3")) {
			order = " s.inputCode ";
		} else if (form.getOrderNo().equals("4")) {
			order = " s.functionDescription ";
		} else if (form.getOrderNo().equals("5")) {
			order = " a.projectName ";
		} else {
			order = " s.classCode";
		}
		if (form.getAsc().equals("1")) {
			order += " desc";
		} else {
			order += " asc";
		}
		List<?> list = securityConfigParamClassDAO.queryList(form.getProjectName(), form.getClassName(), form.getInputCode(), form.getClassCode(), order, cur, pageSize);
		if (list != null && list.size() > 0) {
			String[] classCodeList = new String[list.size()];
			String[] classNameList = new String[list.size()];
			String[] inputCodeList = new String[list.size()];
			String[] functionDescriptionList = new String[list.size()];
			String[] commentsList = new String[list.size()];
			String[] projectCodeList = new String[list.size()];
			String[] projectNameList = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				classCodeList[i] = transNullToString(((Object[]) list.get(i))[0]);
				classNameList[i] = transNullToString(((Object[]) list.get(i))[1]);
				inputCodeList[i] = transNullToString(((Object[]) list.get(i))[2]);
				functionDescriptionList[i] = transNullToString(((Object[]) list.get(i))[3]);
				commentsList[i] = transNullToString(((Object[]) list.get(i))[4]);
				projectCodeList[i] = transNullToString(((Object[]) list.get(i))[5]);
				projectNameList[i] = transNullToString(((Object[]) list.get(i))[6]);	
			}
			form.setClassCodeList(classCodeList);
			form.setClassNameList(classNameList);
			form.setInputCodeList(inputCodeList);
			form.setFunctionDescriptionList(functionDescriptionList);
			form.setCommentsList(commentsList);
			form.setProjectCodeList(projectCodeList);
			form.setProjectNameList(projectNameList);
		}
	}
	public List<?> getDataAll(){
		return securityConfigParamClassDAO.findAll();
	}
	
	public void saveData(SecurityConfigParamClassForm form) {
		try {
			SecurityConfigParamclass scp = new SecurityConfigParamclass();
			this.setAdd(form, scp);
			securityConfigParamClassDAO.saveData(scp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public SecurityConfigParamclass checkData(String classCode){
		return this.getSecurityConfigParamClassDAO().getByProjectCode(classCode);
	}
	
	
	public void updateData(SecurityConfigParamClassForm form) {
		SecurityConfigParamclass scp = new SecurityConfigParamclass();
		this.setData(form, scp);
		securityConfigParamClassDAO.updateData(scp);

	}

	
	public int check(String classCode) {
		return securityConfigParamClassDAO.checkClassCode(classCode);
	}
	
	// 把null值转化为空
	private String transNullToString(Object obj) {
		String temp = "";
		if (obj != null) {
			temp = String.valueOf(obj);
		}
		return temp.trim();
	}
	
	private void setData(SecurityConfigParamClassForm form,
			SecurityConfigParamclass scp) {
		scp.setClassCode(Converter.toBlank(form.getClassCode()));
		scp.setClassName(Converter.toBlank(form.getClassName()));
		scp.setInputCode(Converter.toBlank(form.getInputCode()));
		scp.setFunctionDescription(Converter.toBlank(form.getFunctionDescription()));
		scp.setComments(Converter.toBlank(form.getComments()));
		scp.setProjectCode(Converter.toBlank(form.getProjectCode()));
	}
	
	private void setAdd(SecurityConfigParamClassForm form,
			SecurityConfigParamclass scp) {
		scp.setClassCode(Converter.toBlank(form.getClassCode()));
		scp.setClassName(Converter.toBlank(form.getClassName()));
		scp.setInputCode(Converter.toBlank(form.getInputCode()));
		scp.setFunctionDescription(Converter.toBlank(form.getFunctionDescription()));
		scp.setComments(Converter.toBlank(form.getComments()));
		scp.setProjectCode(Converter.toBlank(form.getProjectCode()));
	}
	
	
	
	
}
