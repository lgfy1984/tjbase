package com.tianjian.comm.business.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.tianjian.comm.bean.CommConfigTrue;
import com.tianjian.comm.business.ICommConfigTrueService;
import com.tianjian.comm.dao.ICommConfigTrueDAO;
import com.tianjian.comm.struts.form.CommConfigTrueForm;


public class CommConfigTrueServiceImpl implements
		ICommConfigTrueService {

	private static final Logger log= LogManager.getLogger(CommConfigTrueServiceImpl.class);
	private ICommConfigTrueDAO commConfigTrueDAO;
	public ICommConfigTrueDAO getCommConfigTrueDAO() {
		return commConfigTrueDAO;
	}

	public void setCommConfigTrueDAO(
			ICommConfigTrueDAO commConfigTrueDAO) {
		this.commConfigTrueDAO = commConfigTrueDAO;
	}

	public void addInit(CommConfigTrueForm form) {
		init(form);
		form.setSeqNo(String.valueOf(this.commConfigTrueDAO.seqNoMaker("CommConfigTrue")));
	}

	public void serchInit(CommConfigTrueForm form) {
		searchInit(form);
	}
	/**获取记录对象*/
	public CommConfigTrue checkData(String id) {
		return this.commConfigTrueDAO.findById(id);
	}
	/**提交保存*/
	public void save(CommConfigTrueForm form) {
		CommConfigTrue data=new CommConfigTrue();
		this.setData(form, data);
		this.commConfigTrueDAO.save(data);
	}

	/**修改之前初始化*/
	public void updateInit(CommConfigTrueForm form) {
		CommConfigTrue data=this.commConfigTrueDAO.findById(form.getItemCode());
		this.setForm(form,data);
		init(form);
	}
	/**提交删除*/
	public void delete(CommConfigTrueForm form) {
		CommConfigTrue data=this.commConfigTrueDAO.findById(form.getItemCodeHidden());
		this.commConfigTrueDAO.delete(data);
	}
	/**提交修改*/
	public void update(CommConfigTrueForm form) {
		try {
			CommConfigTrue data = this.commConfigTrueDAO
					.findById(form.getItemCode());
			this.setData(form, data);
			log.debug("update success!");
			this.commConfigTrueDAO.update(data);
		} catch (Exception e) {
			log.error("update error!",e);
			e.printStackTrace();
		}
	}
	/**获取总记录数*/
	public int getCount(String itemCode, String itemName, String inputCode,
			String seqNo) {
		return this.commConfigTrueDAO.getCount(itemCode, itemName, inputCode, seqNo);
	}
	/**提交察看详细*/
	public void showInit(CommConfigTrueForm form) {
		CommConfigTrue data = commConfigTrueDAO.findById(form.getItemCode());
		form=new CommConfigTrueForm();
		this.setForm(form, data);
		this.init(form);
	}
	/**根据查询条件获取当前显示页的数据*/
	public void getSearch(CommConfigTrueForm form, int curCount,int pageSize) {
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
		 
			List<?> list = this.commConfigTrueDAO.getData(form.getItemCode()
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
	public void getDetail(CommConfigTrueForm form) {}

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
	private void init(CommConfigTrueForm form){}
	/**在进行查找之前将字典库传入form中*/
	private void searchInit(CommConfigTrueForm form){}
	/**构造data*/
	private void setData(CommConfigTrueForm form,CommConfigTrue data){
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
	private void setForm(CommConfigTrueForm form,CommConfigTrue data){
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
