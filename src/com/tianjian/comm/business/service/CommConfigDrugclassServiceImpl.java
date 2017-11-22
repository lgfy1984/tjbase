package com.tianjian.comm.business.service;

import java.util.List;

import com.tianjian.comm.bean.CommConfigDrugclass;
import com.tianjian.comm.business.ICommConfigDrugclassService;
import com.tianjian.comm.dao.ICommConfigDrugclassDAO;
import com.tianjian.comm.dao.ICommonDAO;
import com.tianjian.comm.struts.form.CommConfigDrugclassForm;

public class CommConfigDrugclassServiceImpl implements ICommConfigDrugclassService {
	
	private ICommConfigDrugclassDAO commConfigDrugclassDAO;
	private ICommonDAO commonDAO;
	public ICommonDAO getCommonDAO() {
		return commonDAO;
	}
	public void setCommonDAO(ICommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}
	
	
    
	public ICommConfigDrugclassDAO getCommConfigDrugclassDAO() {
		return commConfigDrugclassDAO;
	}
	public void setCommConfigDrugclassDAO(
			ICommConfigDrugclassDAO commConfigDrugclassDAO) {
		this.commConfigDrugclassDAO = commConfigDrugclassDAO;
	}
	public void updateInit(CommConfigDrugclassForm form) {
		CommConfigDrugclass data = new CommConfigDrugclass();
		data = commConfigDrugclassDAO.getByItemCode(form.getItemCodeHidden());
		this.setForm(form, data);
	}
	
	public void save(CommConfigDrugclassForm form) {
		CommConfigDrugclass data = new CommConfigDrugclass();
		this.setData(form, data);
		commConfigDrugclassDAO.save(data);
	}
	
	
	public void delete(CommConfigDrugclassForm form) {
		CommConfigDrugclass data = new CommConfigDrugclass();
		data = commConfigDrugclassDAO.getByItemCode(form.getItemCode());
		commConfigDrugclassDAO.delete(data);
		this.delete(form.getItemCode());
		
		
	}
	public void delete(String itemCode){
		
		List<?> ls =  commConfigDrugclassDAO.getAllByParentId(itemCode);
		if(ls!=null && ls.size()>0){
			for(int i=0;i<ls.size();i++){ 
				CommConfigDrugclass data = new CommConfigDrugclass();
				data = (CommConfigDrugclass)ls.get(i);
				String temp = data.getItemCode();
				commConfigDrugclassDAO.delete(data);
				delete(temp);
			}
		}
		
	}
	public void update(CommConfigDrugclassForm form) {
		
		CommConfigDrugclass data = new CommConfigDrugclass();
		data = commConfigDrugclassDAO.getByItemCode(form.getItemCode());
		this.setData(form, data);
		
		commConfigDrugclassDAO.update(data);
		
	}
	public CommConfigDrugclass checkData(CommConfigDrugclassForm form) {
		return this.commConfigDrugclassDAO.getByItemCode(form.getItemCode());
	}	
	private void setData(CommConfigDrugclassForm form, CommConfigDrugclass pojo) {
		if(pojo == null){
			pojo = new CommConfigDrugclass();
		}
		pojo.setItemCode(this.transNullToString(form.getItemCode()));
		pojo.setInputCode(this.transNullToString(form.getInputCode()));
		pojo.setItemName(this.transNullToString(form.getItemName()));
		String levelFlag = this.transNullToString(form.getLevelFlag());
		if(!levelFlag.equals("")){
			pojo.setLevelFlag(Long.parseLong(levelFlag));
		}
		
		pojo.setParentId(this.transNullToString(form.getParentId()));
		String seqNoTemp =this.transNullToString(form.getSeqNo());
		if(seqNoTemp.equals("")){
			pojo.setSeqNo(0L);
		}
		else{
			pojo.setSeqNo(Long.valueOf(seqNoTemp));
		}
		
		pojo.setComments(this.transNullToString(form.getComments()));
}
	private void setForm(CommConfigDrugclassForm form, CommConfigDrugclass pojo) {
		if(form == null)
	    form = new CommConfigDrugclassForm();
		form.setInputCode(this.transNullToString(pojo.getInputCode()));
		form.setItemCode(this.transNullToString(pojo.getItemCode()));
		form.setItemName(this.transNullToString(pojo.getItemName()));
		form.setLevelFlag(this.transNullToString(pojo.getLevelFlag()));
		form.setParentId(this.transNullToString(pojo.getParentId()));
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
		return this.commConfigDrugclassDAO.getCount(itemCode, itemName, inputCode);
	}
	public void getSearch(CommConfigDrugclassForm form, int curCount,
			int quChuCount) {
		String order = "";
		if (form.getOrderNo().equals("1")) {
			order = " a.itemCode ";
		} else if (form.getOrderNo().equals("2")) {
			order = " a.itemName ";
		} else if (form.getOrderNo().equals("3")) {
			order = " a.inputCode ";
		} else if (form.getOrderNo().equals("4")) {
				order = " a.seqNo ";
		} else if (form.getOrderNo().equals("5")) {
				order = " a.levelFlag ";
		}  else if (form.getOrderNo().equals("6")) {
				order = " a.parentId ";
		}  else{
			order = "a.itemCode";
		}
		if (form.getAsc().equals("1")) {
			order += " desc";
		} else {
			order += " asc";
			
		}
		List<?> list = this.commConfigDrugclassDAO.getData(form.getItemCode().trim(), form.getItemName().trim(), form.getInputCode().trim(),order, curCount, quChuCount);
		if (list != null && list.size() > 0) {
			// TODO +++Setting the view list fields here +++
			String[] itemCodeList = new String[list.size()];
			String[] itemNameList = new String[list.size()];
			String[] inputCodeList = new String[list.size()];
			String[] levelFlagList = new String[list.size()];
			String[] parentIdList = new String[list.size()];
			String[] parentNameList = new String[list.size()];
			String[] commentsList = new String[list.size()];
			String[] seqNoList = new String[list.size()];
			//--------------------------------------------

			for (int i = 0; i < list.size(); i++) {
				itemCodeList[i] = this.transNullToString(String.valueOf(((Object[]) list.get(i))[0]));
				itemNameList[i] = this.transNullToString(String.valueOf(((Object[]) list.get(i))[1]));
				inputCodeList[i] 	= this.transNullToString(String.valueOf(((Object[]) list.get(i))[2]));
				levelFlagList[i] 	= this.transNullToString(String.valueOf(((Object[]) list.get(i))[3]));
				parentIdList[i] 	= this.transNullToString(String.valueOf(((Object[]) list.get(i))[4]));
				parentNameList[i] =  this.transNullToString(commConfigDrugclassDAO.findValueByProp("CommConfigDrugclass","itemName","itemCode",parentIdList[i]));
				commentsList[i] 	= this.transNullToString(String.valueOf(((Object[]) list.get(i))[5]));
				seqNoList[i] = this.transNullToString(String.valueOf(((Object[]) list.get(i))[6]));
			}			
			form.setItemCodeList(itemCodeList);
			form.setItemNameList(itemNameList);
			form.setInputCodeList(inputCodeList);
			form.setLevelFlagList(levelFlagList);
			form.setParentIdList(parentIdList);
			form.setParentNameList(parentNameList);
			form.setCommentsList(commentsList);
			form.setSeqNoList(seqNoList);
			
		}
	}
	
	public void getData(CommConfigDrugclassForm form) {
		
		List<?> list = this.commConfigDrugclassDAO.findAllOrder();
		if (list != null && list.size() > 0) {
			// TODO +++Setting the view list fields here +++
			String[] itemCodeList = new String[list.size()];
			String[] itemNameList = new String[list.size()];
			String[] inputCodeList = new String[list.size()];
			String[] levelFlagList = new String[list.size()];
			String[] parentIdList = new String[list.size()];
			String[] parentNameList = new String[list.size()];
			String[] commentsList = new String[list.size()];
			String[] seqNoList = new String[list.size()];
			//--------------------------------------------

			for (int i = 0; i < list.size(); i++) {
				itemCodeList[i] = this.transNullToString(String.valueOf(((Object[]) list.get(i))[0]));
				itemNameList[i] = this.transNullToString(String.valueOf(((Object[]) list.get(i))[1]));
				inputCodeList[i] 	= this.transNullToString(String.valueOf(((Object[]) list.get(i))[2]));
				levelFlagList[i] 	= this.transNullToString(String.valueOf(((Object[]) list.get(i))[3]));
				parentIdList[i] 	= this.transNullToString(String.valueOf(((Object[]) list.get(i))[4]));
				parentNameList[i] =  this.transNullToString(commConfigDrugclassDAO.findValueByProp("CommConfigDrugclass","itemName","itemCode",parentIdList[i]));
				commentsList[i] 	= this.transNullToString(String.valueOf(((Object[]) list.get(i))[5]));
				seqNoList[i] = this.transNullToString(String.valueOf(((Object[]) list.get(i))[6]));
			}			
			form.setItemCodeList(itemCodeList);
			form.setItemNameList(itemNameList);
			form.setInputCodeList(inputCodeList);
			form.setLevelFlagList(levelFlagList);
			form.setParentIdList(parentIdList);
			form.setParentNameList(parentNameList);
			form.setCommentsList(commentsList);
			form.setSeqNoList(seqNoList);
			
		}
	}
}
