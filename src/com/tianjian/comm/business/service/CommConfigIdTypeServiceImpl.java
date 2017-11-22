package com.tianjian.comm.business.service;


import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import com.tianjian.comm.bean.CommConfigIdType;
import com.tianjian.comm.business.ICommConfigIdTypeService;
import com.tianjian.comm.dao.ICommConfigIdTypeDAO;
import com.tianjian.comm.struts.form.CommConfigIdTypeForm;

public class CommConfigIdTypeServiceImpl implements ICommConfigIdTypeService {
	private static final Logger log = LogManager.getLogger(CommConfigIdTypeServiceImpl.class);
	private ICommConfigIdTypeDAO commConfigIdTypeDAO;

	//------------------------DAO-- 声明结束---------------------------------------------------
	
	
	public ICommConfigIdTypeDAO getCommConfigIdTypeDAO() {
		return commConfigIdTypeDAO;
	}

	
	public void setCommConfigIdTypeDAO(ICommConfigIdTypeDAO commConfigIdTypeDAO) {
		this.commConfigIdTypeDAO = commConfigIdTypeDAO;
	}

	public void addInit(CommConfigIdTypeForm form){
		init(form);
	}
	
	public void serchInit(CommConfigIdTypeForm form){
		searchInit(form);
	}
	/**获取记录对象*/
	public CommConfigIdType checkData(String id){
		CommConfigIdType data = commConfigIdTypeDAO.findById(id);
		return data;
	}
	/**提交保存*/
	public void save(CommConfigIdTypeForm form){
		CommConfigIdType data = new CommConfigIdType();
		this.setData(form, data);
		commConfigIdTypeDAO.save(data);
	}
	/**修改之前初始化*/
	public void updateInit(CommConfigIdTypeForm form){
		CommConfigIdType data = commConfigIdTypeDAO.findById(form.getItemCodeHidden());
		this.setForm(form, data);
		init(form);
	}
	/**提交修改*/
	public void update(CommConfigIdTypeForm form){
		CommConfigIdType data = commConfigIdTypeDAO.findById(form.getItemCodeHidden());
		this.setData(form, data);
		commConfigIdTypeDAO.update(data);
	}
	/**提交察看详细*/
	public void showInit(CommConfigIdTypeForm form){
		CommConfigIdType data = commConfigIdTypeDAO.findById(form.getItemCodeHidden());
		this.setForm(form, data);
		init(form);
	}
	/**提交删除*/
	public void delete(CommConfigIdTypeForm form){
		CommConfigIdType data = commConfigIdTypeDAO.findById(form.getItemCodeHidden());
		commConfigIdTypeDAO.delete(data);
	}
	/**获取总记录数*/
	public int getCount(String itemCode, String itemName, String inputCode, String seqNo){
		return commConfigIdTypeDAO.getCount(itemCode, itemName, inputCode, seqNo);
	}
	/**根据查询条件获取当前显示页的数据*/
	public void getSearch(CommConfigIdTypeForm form, int curCount, int pageSize){
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
	 
		List<?> list = commConfigIdTypeDAO.getData(form.getItemCode()
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
	/**构造data*/
	private void setData(CommConfigIdTypeForm form, CommConfigIdType data){
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
	/**在传递数据到页面之前构造form*/
	private void setForm(CommConfigIdTypeForm form, CommConfigIdType data){
		try{
			form.setItemCodeHidden (transNullToString(data.getItemCode()));
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
	/**在初始化阶段将字典库传入form中*/
	private void init(CommConfigIdTypeForm form){
		if (form.getSeqNo() == null || form.getSeqNo().equals(""))
			form.setSeqNo(commConfigIdTypeDAO.getMaxSeqNo() + "");

	}
	/***/
	public void getDetail(CommConfigIdTypeForm form){
	
	}
	/**获取查询结果之前初始化*/
	/**在进行查找之前将字典库传入form中*/
	private void searchInit(CommConfigIdTypeForm form){
	}
	
}
