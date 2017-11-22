package com.tianjian.comm.business.service;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.tianjian.comm.bean.CommConfigIcd10;
import com.tianjian.comm.business.ICommConfigIcd10Service;
import com.tianjian.comm.dao.ICommConfigIcd10DAO;
import com.tianjian.comm.struts.form.CommConfigIcd10Form;
import com.tianjian.comm.struts.servlet.CommInit;

public class CommConfigIcd10ServiceImpl implements
		ICommConfigIcd10Service {

	private static final Logger log = LogManager.getLogger(CommConfigIcd10ServiceImpl.class);
	//------------------------DAO-- 声明开---------------------------------------------------
	private ICommConfigIcd10DAO commConfigIcd10DAO;
	
	public ICommConfigIcd10DAO getCommConfigIcd10DAO() {
		return commConfigIcd10DAO;
	}

	public void setCommConfigIcd10DAO(
			ICommConfigIcd10DAO commConfigIcd10DAO) {
		this.commConfigIcd10DAO = commConfigIcd10DAO;
	}
	//------------------------DAO-- 声明结束---------------------------------------------------
	
	public void addInit(CommConfigIcd10Form form) {
		init(form);
		form.setSeqNo(String.valueOf(this.commConfigIcd10DAO.seqNoMaker("CommConfigIcd10")));
	}
	
	public void serchInit(CommConfigIcd10Form form) {
		searchInit(form);
	}
	/**获取记录对象*/
	public CommConfigIcd10 checkData(String id) {
		return this.commConfigIcd10DAO.findById(id);
	}
	/**提交保存*/
	public void save(CommConfigIcd10Form form) {
		CommConfigIcd10 data =new CommConfigIcd10();
		this.setData(form,data);
		this.commConfigIcd10DAO.save(data);
	}
	/**修改之前初始化*/
	public void updateInit(CommConfigIcd10Form form) {
		CommConfigIcd10 data=this.commConfigIcd10DAO.findById(form.getItemCode());
		this.setForm(form,data);
		init(form);
	}
	/**提交修改*/
	public void update(CommConfigIcd10Form form) {
		try {
			CommConfigIcd10 data = this.commConfigIcd10DAO.findById(form
					.getItemCode());

			this.setData(form, data);
			log.debug("update success");
			this.commConfigIcd10DAO.update(data);
		} catch (Exception e) {
			log.error("updata fail",e);
			e.printStackTrace();
			
		}
	}
	/**提交察看详细*/
	public void showInit(CommConfigIcd10Form form) {
		CommConfigIcd10 data =this.commConfigIcd10DAO.findById(form.getItemCode());
		form=new CommConfigIcd10Form();
		this.setForm(form, data);
		init(form);
	}
	/**提交删除*/
	public void delete(CommConfigIcd10Form form) {
		CommConfigIcd10 data =this.commConfigIcd10DAO.findById(form.getItemCodeHidden());
		this.commConfigIcd10DAO.delete(data);
	}
	/**获取总记录数*/
	public int getCount(String itemCode, String itemName, String inputCode,
			String seqNo) {
		return this.commConfigIcd10DAO.getCount(itemCode, itemName, inputCode, seqNo);
	}

	/**根据查询条件获取当前显示页的数据*/
	public void getSearch(CommConfigIcd10Form form, int curCount,
			int pageSize) {
		try{
			 
			String order = "";
			if(form.getOrderNo().equals("1")){
				order = " a.itemCode";
			} else if(form.getOrderNo().equals("2")){
				order = " a.itemName";
			} else if(form.getOrderNo().equals("3")){
				order = " a.seqNo";
			} else if(form.getOrderNo().equals("4")){
				order = " a.inputCode";
		
			} else {
				order = " a.seqNo";
			}
			
			if(form.getAsc().equals("1")){
				order += " desc";
			} else {
				order += " asc";
			}
		 
			List<?> list = this.commConfigIcd10DAO.getData(form.getItemCode()
									, form.getItemName()
									, form.getInputCode()
									, form.getSeqNo()
									, order
									, curCount
									, pageSize);
			 
			if(list != null && list.size() > 0){
			 
				String[] itemCodes = new String[list.size()];
				String[] itemNames = new String[list.size()];
				String[] inputCodes = new String[list.size()];
				String[] comments = new String[list.size()];
				String[] seqNos = new String[list.size()];
				for(int i = 0; i < list.size(); i++){
					itemCodes[i] = String.valueOf(transNullToString(((Object[])list.get(i))[0]));
					itemNames[i] = String.valueOf(transNullToString(((Object[])list.get(i))[1]));
					inputCodes[i] = String.valueOf(transNullToString(((Object[])list.get(i))[2]));
					comments[i] = String.valueOf(transNullToString(((Object[])list.get(i))[3]));
					seqNos[i] = ((Object[])list.get(i))[4] == null ? "0" : String.valueOf(((Object[])list.get(i))[4]);
					 
				}
				form.setItemCodeList(itemCodes);
				form.setItemNameList(itemNames);
				form.setInputCodeList(inputCodes);
				form.setCommentsList(comments);
				form.setSeqNoList(seqNos);
			 
			}
			 } catch (Exception e) { 
				 log.error("getSearch fail!",e);
				 e.printStackTrace();
		} 
		
	}
	/**获取查询结果之前初始化*/
	public void getDetail(CommConfigIcd10Form form) {

	}

	//内部方法
	/**
	 * 去掉字串中的空格
	 * */
	public String transNullToString(Object obj){
		String temp = "";
		if (obj != null){
			temp = ((String)obj).trim();			
		}
		return temp;
	}
	
	public String transNullToZero(Object obj){
		String temp = "0";
		if (obj != null){
			temp = ((String)obj).trim();			
		}
		return temp;
	}
	/**在初始化阶段将字典库传入form中*/
	private void init(CommConfigIcd10Form form){
	}
	
	/**在进行查找之前将字典库传入form中*/
	private void searchInit(CommConfigIcd10Form form){
		
	}
	/**构造data*/
	private void setData(CommConfigIcd10Form form,CommConfigIcd10 data){
		try{
			data.setItemCode (transNullToString(form.getItemCode()));
			
			data.setItemName (transNullToString(form.getItemName()));
			data.setInputCode (transNullToString(form.getInputCode()));
			data.setComments  (transNullToString(form.getComments      ()));
			data.setSeqNo     (Long.valueOf((form.getSeqNo() == null || form.getSeqNo().trim() == "") ? "0" : form.getSeqNo()));
			 } catch (Exception e) { 
				 log.error("setData fail!",e);
				 e.printStackTrace();
		} 
	}
	/**在传递数据到页面之前构造form*/
	private void setForm(CommConfigIcd10Form form,CommConfigIcd10 data){
		try{
			form.setItemCode (transNullToString(data.getItemCode()));
			form.setItemName (transNullToString(data.getItemName()));
			form.setInputCode(transNullToString(data.getInputCode()));
			form.setComments(transNullToString(data.getComments())); 
			form.setSeqNo(data.getSeqNo() == null ? "0" : String.valueOf(data.getSeqNo()));
		 } catch (Exception e) { 
			 log.error("setForm fail!",e);
			 e.printStackTrace();
		} 
	}
	
	/**构造xml,用于一些页面弹出层显示*/
	public String getXml(String flag,String inputCode,HttpServletRequest request){
		ServletContext application = request.getSession().getServletContext();
		StringBuffer buffer = new StringBuffer();		
		//List<?> list = commConfigIcd10DAO.findIcd10List(flag, inputCode, Integer.valueOf(CommInit.getProperty("CURRECORD_TANCHUCENG")),
				//CommInit.getPageSize("PAGE_SIZE_TANCHUCENG"));
		List<?> list = commConfigIcd10DAO.findIcd10List(flag, inputCode, Integer.valueOf((String)application.getAttribute("comm.CURRECORD_TANCHUCENG")),
				Integer.valueOf((String)application.getAttribute("comm.PAGE_SIZE_TANCHUCENG")));
		buffer.append("<response>");
		for(int i =0;i<list.size();i++){
			CommConfigIcd10 charbean = (CommConfigIcd10)list.get(i);
			 //-----以下xml标签不要改变--------------------------------------------------------------
			buffer.append("<ress>");
			buffer.append("<resInputCode>" + charbean.getInputCode() + "</resInputCode>");
			buffer.append("<resItemCode>" + charbean.getItemCode() + "</resItemCode>");
			buffer.append("<resItemName>" + charbean.getItemName() + "</resItemName>");
			buffer.append("<resItemId>" + charbean.getItemCode() + "</resItemId>");
			buffer.append("</ress>");
		}
		buffer.append("</response>");
		
		return buffer.toString();
	}
}
