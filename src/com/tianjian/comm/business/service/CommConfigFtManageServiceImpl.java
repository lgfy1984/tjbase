package com.tianjian.comm.business.service;

import java.util.List;

import com.tianjian.comm.bean.CommConfigFtManage;
import com.tianjian.comm.business.ICommConfigFtManageService;
import com.tianjian.comm.dao.ICommConfigFtManageDAO;
import com.tianjian.comm.dao.ICommonDAO;
import com.tianjian.comm.struts.form.CommConfigFtManageForm;
import com.tianjian.comm.struts.servlet.CommInit;
/**
 * 机构分类管理字典
 * @author DZENALL
 */
public class CommConfigFtManageServiceImpl implements ICommConfigFtManageService {

	private ICommonDAO commonDAO;
	private ICommConfigFtManageDAO commConfigFtManageDAO;	
	public ICommonDAO getCommonDAO() {
		return commonDAO;
	}
	public void setCommonDAO(ICommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}
	public ICommConfigFtManageDAO getCommConfigFtManageDAO() {
		return commConfigFtManageDAO;
	}
	public void setCommConfigFtManageDAO(ICommConfigFtManageDAO commConfigFtManageDAO) {
		this.commConfigFtManageDAO = commConfigFtManageDAO;
	}


	private void setData(CommConfigFtManage data, CommConfigFtManageForm form){
//		private String itemCode;
		data.setItemCode(CommInit.transNullToString(form.getItemCode()));
//		private Long seqNo;
		data.setSeqNo(CommInit.transNullToLong(form.getSeqNo()));
//		private String itemName;
		data.setItemName(CommInit.transNullToString(form.getItemName()));
//		private String inputCode;
		data.setInputCode(CommInit.transNullToString(form.getInputCode()));
//		private String comments;
		data.setComments(CommInit.transNullToString(form.getComments()));
	}
	private void setForm(CommConfigFtManage data, CommConfigFtManageForm form){
//		private String itemCode;
		form.setItemCode(CommInit.transNullToString(data.getItemCode()));
//		private Long seqNo;
		form.setSeqNo(CommInit.transNullToString(data.getSeqNo()));
//		private String itemName;
		form.setItemName(CommInit.transNullToString(data.getItemName()));
//		private String inputCode;
		form.setInputCode(CommInit.transNullToString(data.getInputCode()));
//		private String comments;
		form.setComments(CommInit.transNullToString(data.getComments()));
	}
	private String getSql(String way, CommConfigFtManageForm form){
		String countString = "select count(*) ";
		String selectString = " select a.itemCode, a.seqNo, a.itemName, a.inputCode, a.comments ";
		String fromString = " from CommConfigFtManage a ";
		String whereString = " where 1=1 ";
		if(form.getItemCode()!=null && form.getItemCode().trim().length()>0)
			whereString += " and a.itemCode = '" + form.getItemCode().trim()+"' ";
		if(form.getInputCode()!=null && form.getInputCode().trim().length()>0)
			whereString += " and a.inputCode like '" + form.getInputCode().trim().toUpperCase() + "%' ";
		if(form.getItemName()!=null && form.getItemName().trim().length()>0)
			whereString += " and lower(a.itemName) like '" + form.getItemName().trim().toLowerCase() + "%' ";
		String orderString = " order by ";
		if(form.getOrderNo()!=null && form.getOrderNo().trim().length()>0){
			if(form.getOrderNo().equals("1"))
				orderString += " a.itemCode ";
			else if(form.getOrderNo().equals("2"))
				orderString += " a.itemName ";
			else if(form.getOrderNo().equals("3"))
				orderString += " a.seqNo ";
			else if(form.getOrderNo().equals("4"))
				orderString += " a.inputCode ";
			else
				orderString += " a.itemCode ";
		}
		if(form.getAsc()!=null && form.getAsc().trim().length()>0){
			if(form.getAsc().equals("1"))
				orderString += " asc ";
			else
				orderString += " desc ";
		}
		if(way.equalsIgnoreCase("count")){
			return countString+fromString+whereString;
		}else if(way.equalsIgnoreCase("data")){
			return selectString+fromString+whereString+orderString;
		}else{
			return null;
		}
	}
	private void getDetail(CommConfigFtManageForm form){}
	private void getDict(CommConfigFtManageForm form) {}

	
	public void addInit(CommConfigFtManageForm form) {
		form.setSeqNo(String.valueOf(this.commConfigFtManageDAO.seqNoMaker("CommConfigFtManage")));
	}

	
	public CommConfigFtManage checkData(CommConfigFtManageForm form) {
		return this.getCommConfigFtManageDAO().findByItemCode(form.getItemCode().trim());
	}

	
	public void delete(CommConfigFtManageForm form) {
		this.getCommonDAO().delete(this.getCommConfigFtManageDAO().findByItemCode(form.getItemCodeHidden().trim()));		
	}

	
	public int getCount(CommConfigFtManageForm form) {
		return this.getCommConfigFtManageDAO().getCount(this.getSql("count", form));
	}
	
	public void getSearch(CommConfigFtManageForm form, int currentPageIndex, int pageSize) {
		List<?> list = this.getCommConfigFtManageDAO().getData(this.getSql("data", form), currentPageIndex, pageSize);
		if(list!=null && list.size()>0){
			String[] itemCodeList = new String[list.size()];
			String[] seqNoList = new String[list.size()];
			String[] itemNameList = new String[list.size()];
			String[] inputCodeList = new String[list.size()];
			String[] commentsList = new String[list.size()];
			for(int i=0; i<list.size(); i++){
				itemCodeList[i] = CommInit.transNullToString(((Object[]) list.get(i))[0]);
				seqNoList[i] = CommInit.transNullToString(((Object[]) list.get(i))[1]);
				itemNameList[i] = CommInit.transNullToString(((Object[]) list.get(i))[2]);
				inputCodeList[i] = CommInit.transNullToString(((Object[]) list.get(i))[3]);
				commentsList[i] = CommInit.transNullToString(((Object[]) list.get(i))[4]);
			}
			form.setItemCodeList(itemCodeList);
			form.setSeqNoList(seqNoList);
			form.setItemNameList(itemNameList);
			form.setInputCodeList(inputCodeList);
			form.setCommentsList(commentsList);
		}
	}

	
	public void save(CommConfigFtManageForm form) {
		CommConfigFtManage data = new CommConfigFtManage();
		this.setData(data, form);
		this.getCommonDAO().save(data);
	}

	
	public void searchInit(CommConfigFtManageForm form) {
		this.getDetail(form);
		this.getDict(form);
	}

	
	public void update(CommConfigFtManageForm form) {
		CommConfigFtManage data = this.getCommConfigFtManageDAO().findByItemCode(form.getItemCodeHidden().trim());
		this.setData(data, form);
		this.getCommonDAO().update(data);
	}

	
	public void updateInit(CommConfigFtManageForm form) {
		CommConfigFtManage data = this.getCommConfigFtManageDAO().findByItemCode(form.getItemCodeHidden().trim());
		this.setForm(data, form);
		this.getDetail(form);	
		this.getDict(form);
	}
}