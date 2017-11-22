package com.tianjian.comm.business.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.tianjian.comm.bean.CommConfigVocation;
import com.tianjian.comm.business.ICommConfigVocationService;
import com.tianjian.comm.dao.ICommConfigVocationDAO;
import com.tianjian.comm.struts.form.CommConfigVocationForm;

public class CommConfigVocationServiceImpl implements
		ICommConfigVocationService {

	private static final Logger log = LogManager.getLogger(CommConfigVocationServiceImpl.class);
	//------------------------DAO-- 声明开---------------------------------------------------
	private ICommConfigVocationDAO commConfigVocationDAO;
	
	public ICommConfigVocationDAO getCommConfigVocationDAO() {
		return commConfigVocationDAO;
	}

	public void setCommConfigVocationDAO(
			ICommConfigVocationDAO commConfigVocationDAO) {
		this.commConfigVocationDAO = commConfigVocationDAO;
	}
	//------------------------DAO-- 声明结束---------------------------------------------------
	
	public void addInit(CommConfigVocationForm form) {
		init(form);
		form.setSeqNo(String.valueOf(this.commConfigVocationDAO.seqNoMaker("CommConfigVocation")));
	}
	
	public void serchInit(CommConfigVocationForm form) {
		searchInit(form);
	}
	/**获取记录对象*/
	public CommConfigVocation checkData(String id) {
		return this.commConfigVocationDAO.findById(id);
	}
	/**提交保存*/
	public void save(CommConfigVocationForm form) {
		CommConfigVocation data =new CommConfigVocation();
		this.setData(form,data);
		this.commConfigVocationDAO.save(data);
	}
	/**修改之前初始化*/
	public void updateInit(CommConfigVocationForm form) {
		CommConfigVocation data=this.commConfigVocationDAO.findById(form.getItemCode());
		this.setForm(form,data);
		init(form);
	}
	/**提交修改*/
	public void update(CommConfigVocationForm form) {
		try {
			CommConfigVocation data = this.commConfigVocationDAO.findById(form
					.getItemCode());

			this.setData(form, data);
			log.debug("update success");
			this.commConfigVocationDAO.update(data);
		} catch (Exception e) {
			log.error("updata fail",e);
			e.printStackTrace();
			
		}
	}
	/**提交察看详细*/
	public void showInit(CommConfigVocationForm form) {
		CommConfigVocation data =this.commConfigVocationDAO.findById(form.getItemCode());
		form=new CommConfigVocationForm();
		this.setForm(form, data);
		init(form);
	}
	/**提交删除*/
	public void delete(CommConfigVocationForm form) {
		CommConfigVocation data =this.commConfigVocationDAO.findById(form.getItemCodeHidden());
		this.commConfigVocationDAO.delete(data);
	}
	/**获取总记录数*/
	public int getCount(String itemCode, String itemName, String inputCode,
			String seqNo) {
		return this.commConfigVocationDAO.getCount(itemCode, itemName, inputCode, seqNo);
	}

	/**根据查询条件获取当前显示页的数据*/
	public void getSearch(CommConfigVocationForm form, int curCount,
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
		 
			List<?> list = this.commConfigVocationDAO.getData(form.getItemCode()
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
	public void getDetail(CommConfigVocationForm form) {

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
	private void init(CommConfigVocationForm form){
	}
	
	/**在进行查找之前将字典库传入form中*/
	private void searchInit(CommConfigVocationForm form){
		
	}
	/**构造data*/
	private void setData(CommConfigVocationForm form,CommConfigVocation data){
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
	private void setForm(CommConfigVocationForm form,CommConfigVocation data){
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
}
