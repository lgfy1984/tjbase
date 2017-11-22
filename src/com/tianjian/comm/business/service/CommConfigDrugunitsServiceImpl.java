package com.tianjian.comm.business.service;

import java.util.List;

import com.tianjian.comm.bean.CommConfigDrugunits;
import com.tianjian.comm.business.ICommConfigDrugunitsService;
import com.tianjian.comm.dao.ICommConfigDrugunitsDAO;
import com.tianjian.comm.dao.ICommonDAO;
import com.tianjian.comm.struts.form.CommConfigDrugunitsForm;

public class CommConfigDrugunitsServiceImpl implements
		ICommConfigDrugunitsService {

	private ICommConfigDrugunitsDAO commConfigDrugunitsDAO;
	private ICommonDAO commonDAO;
	public ICommonDAO getCommonDAO() {
		return commonDAO;
	}
	public void setCommonDAO(ICommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}
	
	public ICommConfigDrugunitsDAO getCommConfigDrugunitsDAO() {
		return commConfigDrugunitsDAO;
	}
	public void setCommConfigDrugunitsDAO(
			ICommConfigDrugunitsDAO commConfigDrugunitsDAO) {
		this.commConfigDrugunitsDAO = commConfigDrugunitsDAO;
	}
	public void updateInit(CommConfigDrugunitsForm form) {
		CommConfigDrugunits data = new CommConfigDrugunits();
		data = commConfigDrugunitsDAO.getByItemCode(form.getItemCodeHidden());
		this.setForm(form, data);
	}
	
	public void save(CommConfigDrugunitsForm form) {
		CommConfigDrugunits data = new CommConfigDrugunits();
		this.setData(form, data);
		commConfigDrugunitsDAO.save(data);
	}
	
	
	public void delete(CommConfigDrugunitsForm form) {
		CommConfigDrugunits data = new CommConfigDrugunits();
		data = commConfigDrugunitsDAO.getByItemCode(form.getItemCode());
		System.out.println("12311111111111111222"+form.getItemCode());
		//this.setData(form, data);
		commConfigDrugunitsDAO.delete(data);
		
	}
	
	public void update(CommConfigDrugunitsForm form) {
		
		CommConfigDrugunits data = new CommConfigDrugunits();
		//data = commConfigDrugunitsDAO.getByItemName(form.getItemName());
		
		data = commConfigDrugunitsDAO.getByItemCode(form.getItemCode());
		this.setData(form, data);
		
		commConfigDrugunitsDAO.update(data);
		
	}
	
	public CommConfigDrugunits checkData(CommConfigDrugunitsForm form) {
		return this.commConfigDrugunitsDAO.getByItemCode(form.getItemCode());
	}
	
	private void setData(CommConfigDrugunitsForm form, CommConfigDrugunits pojo) {
		if(pojo == null){
			pojo = new CommConfigDrugunits();
		}
		pojo.setItemCode(this.transNullToString(form.getItemCode()));
		
		
		pojo.setInputCode(this.transNullToString(form.getInputCode()));
		pojo.setItemName(this.transNullToString(form.getItemName()));
		String seqNoTemp =this.transNullToString(form.getSeqNo());
		if(seqNoTemp.equals("")){
			pojo.setSeqNo(0L);
		}
		else{
			pojo.setSeqNo(Long.valueOf(seqNoTemp));
		}
		pojo.setComments(this.transNullToString(form.getComments()));
		
}
	private void setForm(CommConfigDrugunitsForm form, CommConfigDrugunits pojo) {
		if(form == null)
	    form = new CommConfigDrugunitsForm();
		form.setInputCode(this.transNullToString(pojo.getInputCode()));
		form.setItemCode(this.transNullToString(pojo.getItemCode()));
		form.setItemName(this.transNullToString(pojo.getItemName()));
		form.setSeqNo(this.transNullToString(pojo.getSeqNo()));
		form.setComments(this.transNullToString(pojo.getComments()));
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
	public int getCount(String itemCode, String itemName, String inputCode) {
		return this.commConfigDrugunitsDAO.getCount(itemCode, itemName, inputCode);
	}
	public void getSearch(CommConfigDrugunitsForm form, int curCount,
			int quChuCount) {
		String order = "";
		if (form.getOrderNo().equals("1")) {
			order = " a.itemCode ";
		} else if (form.getOrderNo().equals("2")) {
			order = " a.itemName ";
		} else if (form.getOrderNo().equals("3")) {
			order = " a.inputCode ";
		} else if (form.getOrderNo().equals("4")) {
				order = " a.comments ";
		} else if (form.getOrderNo().equals("5")) {
				order = " a.seqNo ";
		} else{
			order = "a.itemCode";
		}
		if (form.getAsc().equals("1")) {
			order += " desc";
		} else {
			order += " asc";
			
		}
		List<?> list = this.commConfigDrugunitsDAO.getData(form.getItemCode().trim(), form.getItemName().trim(), form.getInputCode().trim(),order, curCount, quChuCount);
		if (list != null && list.size() > 0) {
			// TODO +++Setting the view list fields here +++
			String[] itemCodeList = new String[list.size()];
			String[] seqNoList = new String[list.size()];
			String[] itemNameList = new String[list.size()];
			String[] inputCodeList = new String[list.size()];
			String[] commentsList = new String[list.size()];
			//--------------------------------------------

			for (int i = 0; i < list.size(); i++) {
				itemCodeList[i] = this.transNullToString(String.valueOf(((Object[]) list.get(i))[0]));
				seqNoList[i] = this.transNullToString(String.valueOf(((Object[]) list.get(i))[1]));
				itemNameList[i] = this.transNullToString(String.valueOf(((Object[]) list.get(i))[2]));
				inputCodeList[i] 	= this.transNullToString(String.valueOf(((Object[]) list.get(i))[3]));
				commentsList[i] 	= this.transNullToString(String.valueOf(((Object[]) list.get(i))[4]));
			}			
			form.setItemCodeList(itemCodeList);
			form.setSeqNoList(seqNoList);
			form.setItemNameList(itemNameList);
			form.setInputCodeList(inputCodeList);
			form.setCommentsList(commentsList);
		}
	}
	
	
}
