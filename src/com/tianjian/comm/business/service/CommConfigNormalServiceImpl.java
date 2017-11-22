package com.tianjian.comm.business.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.tianjian.comm.bean.CommConfigNormal;
import com.tianjian.comm.business.ICommConfigNormalService;
import com.tianjian.comm.dao.ICommConfigNormalDAO;
import com.tianjian.comm.struts.form.CommConfigNormalForm;


public class CommConfigNormalServiceImpl implements
		ICommConfigNormalService {

	private static final Logger log= LogManager.getLogger(CommConfigNormalServiceImpl.class);
	private ICommConfigNormalDAO commConfigNormalDAO;
	public ICommConfigNormalDAO getCommConfigNormalDAO() {
		return commConfigNormalDAO;
	}

	public void setCommConfigNormalDAO(
			ICommConfigNormalDAO commConfigNormalDAO) {
		this.commConfigNormalDAO = commConfigNormalDAO;
	}

	public void addInit(CommConfigNormalForm form) {
		init(form);
		form.setSeqNo(String.valueOf(this.commConfigNormalDAO.seqNoMaker("CommConfigNormal")));
	}

	public void serchInit(CommConfigNormalForm form) {
		searchInit(form);
	}
	/**获取记录对象*/
	public CommConfigNormal checkData(String id) {
		return this.commConfigNormalDAO.findById(id);
	}
	/**提交保存*/
	public void save(CommConfigNormalForm form) {
		CommConfigNormal data=new CommConfigNormal();
		this.setData(form, data);
		this.commConfigNormalDAO.save(data);
	}

	/**修改之前初始化*/
	public void updateInit(CommConfigNormalForm form) {
		CommConfigNormal data=this.commConfigNormalDAO.findById(form.getItemCode());
		this.setForm(form,data);
		init(form);
	}
	/**提交删除*/
	public void delete(CommConfigNormalForm form) {
		CommConfigNormal data=this.commConfigNormalDAO.findById(form.getItemCodeHidden());
		this.commConfigNormalDAO.delete(data);
	}
	/**提交修改*/
	public void update(CommConfigNormalForm form) {
		try {
			CommConfigNormal data = this.commConfigNormalDAO
					.findById(form.getItemCode());
			this.setData(form, data);
			log.debug("update success!");
			this.commConfigNormalDAO.update(data);
		} catch (Exception e) {
			log.error("update error!",e);
			e.printStackTrace();
		}
	}
	/**获取总记录数*/
	public int getCount(String itemCode, String itemName, String inputCode,
			String seqNo) {
		return this.commConfigNormalDAO.getCount(itemCode, itemName, inputCode, seqNo);
	}
	/**提交察看详细*/
	public void showInit(CommConfigNormalForm form) {
		CommConfigNormal data = commConfigNormalDAO.findById(form.getItemCode());
		form=new CommConfigNormalForm();
		this.setForm(form, data);
		this.init(form);
	}
	/**根据查询条件获取当前显示页的数据*/
	public void getSearch(CommConfigNormalForm form, int curCount,int pageSize) {
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
		 
			List<?> list = this.commConfigNormalDAO.getData(form.getItemCode()
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
					itemCodes[i] = transNullToString(((Object[])list.get(i))[0]);
					itemNames[i] = transNullToString(((Object[])list.get(i))[1]);
					inputCodes[i] = transNullToString(((Object[])list.get(i))[2]);
					comments[i] = transNullToString(((Object[])list.get(i))[3]);
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
	public void getDetail(CommConfigNormalForm form) {}

	//内部私有方法
	/**
	 * 去掉字串中的空格
	 * */
	public String transNullToString(Object obj){
		String temp = "";
		if (obj != null){
			temp = String.valueOf(obj).trim();
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
	private void init(CommConfigNormalForm form){}
	/**在进行查找之前将字典库传入form中*/
	private void searchInit(CommConfigNormalForm form){}
	/**构造data*/
	private void setData(CommConfigNormalForm form,CommConfigNormal data){
		try{
			data.setItemCode(this.transNullToString(form.getItemCode()));
			data.setItemName(this.transNullToString(form.getItemName()));
			data.setInputCode(this.transNullToString(form.getInputCode()));
			data.setComments(this.transNullToString(form.getComments()));
			data.setSeqNo(Long.valueOf((form.getSeqNo()==null||form.getSeqNo()=="")?"0":form.getSeqNo()));
		}catch(Exception e){
			log.error("setData error",e);
			e.printStackTrace();
		}
	}
	private void setForm(CommConfigNormalForm form,CommConfigNormal data){
		try{
			form.setItemCode(transNullToString(data.getItemCode()));
			form.setItemName(transNullToString(data.getItemName()));
			form.setComments(transNullToString(data.getComments()));
			form.setInputCode(transNullToString(data.getInputCode()));
			form.setSeqNo(data.getSeqNo()==null?"0":String.valueOf(data.getSeqNo()));
		}catch(Exception e){
			log.error("setForm error!",e);
			e.printStackTrace();
		}
	}
}
