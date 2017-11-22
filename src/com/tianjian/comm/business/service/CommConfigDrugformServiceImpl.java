package com.tianjian.comm.business.service;

import java.util.List;

import com.tianjian.comm.bean.CommConfigDrugform;
import com.tianjian.comm.business.ICommConfigDrugformService;
import com.tianjian.comm.dao.ICommConfigDrugformDAO;
import com.tianjian.comm.dao.ICommonDAO;
import com.tianjian.comm.struts.form.CommConfigDrugformForm;


public class CommConfigDrugformServiceImpl implements ICommConfigDrugformService{

	private ICommConfigDrugformDAO commConfigDrugformDAO;
	private ICommonDAO commonDAO;
	public ICommonDAO getCommonDAO() {
		return commonDAO;
	}
	public void setCommonDAO(ICommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}
	
	
	public ICommConfigDrugformDAO getCommConfigDrugformDAO() {
		return commConfigDrugformDAO;
	}
	public void setCommConfigDrugformDAO(
			ICommConfigDrugformDAO commConfigDrugformDAO) {
		this.commConfigDrugformDAO = commConfigDrugformDAO;
	}
	public void updateInit(CommConfigDrugformForm form) {
		CommConfigDrugform data = new CommConfigDrugform();
		data = commConfigDrugformDAO.getByItemCode(form.getItemCodeHidden());
		this.setForm(form, data);
	}
	
	public void save(CommConfigDrugformForm form) {
		CommConfigDrugform data = new CommConfigDrugform();
		this.setData(form, data);
		commConfigDrugformDAO.save(data);
	}
	
	
	public void delete(CommConfigDrugformForm form) {
		CommConfigDrugform data = new CommConfigDrugform();
		data = commConfigDrugformDAO.getByItemCode(form.getItemCode());
		this.setData(form, data);
		commConfigDrugformDAO.delete(data);
		
	}
	
	public void update(CommConfigDrugformForm form) {
		
		CommConfigDrugform data = new CommConfigDrugform();
		//data = commConfigDrugunitsDAO.getByItemName(form.getItemName());
		
		data = commConfigDrugformDAO.getByItemCode(form.getItemCode());
		this.setData(form, data);
		
		commConfigDrugformDAO.update(data);
		
	}
	
	public CommConfigDrugform checkData(CommConfigDrugformForm form) {
		return this.commConfigDrugformDAO.getByItemCode(form.getItemCode());
	}
	
	private void setData(CommConfigDrugformForm form, CommConfigDrugform pojo) {
		if(pojo == null){
			pojo = new CommConfigDrugform();
		}
		pojo.setItemCode(this.transNullToString(form.getItemCode()));
		System.out.println("----------pojo------"+pojo.getItemCode());
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
	private void setForm(CommConfigDrugformForm form, CommConfigDrugform pojo) {
		if(form == null)
	    form = new CommConfigDrugformForm();
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
		return this.commConfigDrugformDAO.getCount(itemCode, itemName, inputCode);
	}
	public void getSearch(CommConfigDrugformForm form, int curCount,
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
		}else{
			order = "a.itemCode";
		}
		if (form.getAsc().equals("1")) {
			order += " desc";
		} else {
			order += " asc";
			
		}
		List<?> list = this.commConfigDrugformDAO.getData(form.getItemCode().trim(), form.getItemName().trim(), form.getInputCode().trim(),order, curCount, quChuCount);
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
