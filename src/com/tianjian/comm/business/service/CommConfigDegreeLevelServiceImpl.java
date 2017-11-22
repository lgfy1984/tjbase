package com.tianjian.comm.business.service;

import java.util.Iterator;
import java.util.List;

import com.tianjian.comm.bean.CommConfigDegreeLevel;
import com.tianjian.comm.business.ICommConfigDegreeLevelService;
import com.tianjian.comm.dao.ICommConfigDegreeLevelDAO;
import com.tianjian.comm.dao.ICommonDAO;
import com.tianjian.comm.struts.form.CommConfigDegreeLevelForm;

public class CommConfigDegreeLevelServiceImpl implements ICommConfigDegreeLevelService{

	private ICommonDAO commonDAO;
	private ICommConfigDegreeLevelDAO commConfigDegreeLevelDAO;

	public ICommonDAO getCommonDAO() {
		return commonDAO;
	}
	public void setCommonDAO(ICommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}
	public ICommConfigDegreeLevelDAO getCommConfigDegreeLevelDAO(){
		return this.commConfigDegreeLevelDAO;
	}
	public void setCommConfigDegreeLevelDAO(ICommConfigDegreeLevelDAO commConfigDegreeLevelDAO){
		this.commConfigDegreeLevelDAO = commConfigDegreeLevelDAO;
	}

	private void getDict(CommConfigDegreeLevelForm form) {
		List<CommConfigDegreeLevel> parentItemList = this.commConfigDegreeLevelDAO.findListByLevelFlag("1");
		form.setParentItemList(parentItemList);
	}

	private String transNullToString(Object obj){
		try{
			return obj==null ? "" : String.valueOf(obj);
		}catch(Exception e){
			return "";
		}
	}

	private Long transNullToLong(Object obj){
		try{
			return obj==null ? null : Long.valueOf(String.valueOf(obj));
		}catch(Exception e){
			return null;
		}
	}
	private void setData(CommConfigDegreeLevel data, CommConfigDegreeLevelForm form){
		data.setItemCode(this.transNullToString(form.getItemCode()));
		data.setItemName(this.transNullToString(form.getItemName()));
		data.setInputCode(this.transNullToString(form.getInputCode()));
		data.setLevelFlag(this.transNullToLong(form.getLevelFlag()));
		data.setParentItemCode(this.transNullToString(form.getParentItemCode()));
		data.setComments(this.transNullToString(form.getComments()));
		data.setSeqNo(this.transNullToLong(form.getSeqNo()));
	}

	private void setForm(CommConfigDegreeLevel data, CommConfigDegreeLevelForm form){
		form.setItemCode(this.transNullToString(data.getItemCode()));
		form.setItemName(this.transNullToString(data.getItemName()));
		form.setInputCode(this.transNullToString(data.getInputCode()));
		form.setLevelFlag(this.transNullToString(data.getLevelFlag()));
		form.setParentItemCode(this.transNullToString(data.getParentItemCode()));
		form.setComments(this.transNullToString(data.getComments()));
		form.setSeqNo(this.transNullToString(data.getSeqNo()));
	}

	private void getDetail(CommConfigDegreeLevelForm form){
		form.setParentItemCodeName(this.transNullToString(this.getCommonDAO().getNameById("CommConfigDegreeLevel", "itemCode", "itemName", form.getParentItemCode())));
	}

	public void addInit(CommConfigDegreeLevelForm form) {
		this.getDict(form);
	}

	public CommConfigDegreeLevel checkData(CommConfigDegreeLevelForm form) {
		return this.getCommConfigDegreeLevelDAO().checkData(form.getItemCode());
	}

	public void save(CommConfigDegreeLevelForm form) {
		CommConfigDegreeLevel data = new CommConfigDegreeLevel();
		this.setData(data, form);
		CommConfigDegreeLevel data_ = (CommConfigDegreeLevel) this.getCommonDAO().findById("CommConfigDegreeLevel", "itemCode", data.getParentItemCode());
		if(data_!=null){
			data.setLevelFlag(data_.getLevelFlag()+1);
		}else{
			data.setLevelFlag(0L);
		}
		if(data.getSeqNo()==null){
			try{
				data.setSeqNo(Long.valueOf(this.getCommonDAO().getSequenceNo("CommConfigDegreeLevel", "seqNo")));
			}catch(Exception e){
				data.setSeqNo(0L);
			}
		}
		this.getCommonDAO().save(data);
	}

	public int getCount(CommConfigDegreeLevelForm form){
		return this.getCommConfigDegreeLevelDAO().getCount(form.getItemCode(), form.getItemName(), form.getInputCode());
	}

	public void getSearch(CommConfigDegreeLevelForm form, int currentPageIndex, int pageSize) {
		List<?> list = this.getCommConfigDegreeLevelDAO().getData(form.getItemCode(), form.getItemName(), form.getInputCode(), form.getAsc(), form.getOrderNo(), currentPageIndex, pageSize);
		if(list!=null){
			Iterator<?> iterator = list.iterator();
			String[] itemCodeList = new String[list.size()];
			String[] itemNameList = new String[list.size()];
			String[] inputCodeList = new String[list.size()];
			String[] parentItemCodeList = new String[list.size()];
			String[] parentItemCodeNameList = new String[list.size()];
			String[] levelFlagList = new String[list.size()];
			String[] seqNoList = new String[list.size()];
			int i = 0;
			while(iterator.hasNext()){
				CommConfigDegreeLevel ccdl = (CommConfigDegreeLevel)iterator.next();
				itemCodeList[i] = this.transNullToString(ccdl.getItemCode());
				itemNameList[i] = this.transNullToString(ccdl.getItemName());
				inputCodeList[i] = this.transNullToString(ccdl.getInputCode());
				parentItemCodeList[i] = this.transNullToString(ccdl.getParentItemCode());
				parentItemCodeNameList[i] = "";
				if(ccdl.getParentItemCode() != null && ccdl.getParentItemCode().trim().length() > 0){
					CommConfigDegreeLevel parent = this.getCommConfigDegreeLevelDAO().findByItemCode(ccdl.getParentItemCode());
					if(parent != null){
						parentItemCodeNameList[i] = this.transNullToString(parent.getItemName());
					}
				}
				levelFlagList[i] = this.transNullToString(ccdl.getLevelFlag());
				seqNoList[i] = this.transNullToString(ccdl.getSeqNo());
				i++;
			}
			
			form.setItemCodeList(itemCodeList);
			form.setItemNameList(itemNameList);
			form.setInputCodeList(inputCodeList);
			form.setParentItemCodeNameList(parentItemCodeNameList);
			form.setParentItemCodeList(parentItemCodeList);
			form.setLevelFlagList(levelFlagList);
			form.setSeqNoList(seqNoList);
		}
	}

	public void searchInit(CommConfigDegreeLevelForm form) {
		this.getDetail(form);
		this.getDict(form);
	}

	public void updateInit(CommConfigDegreeLevelForm form) {
		CommConfigDegreeLevel data = (CommConfigDegreeLevel)this.getCommonDAO().findById("CommConfigDegreeLevel", "itemCode", form.getIdHidden().trim());
		this.setForm(data, form);
		this.getDetail(form);
		this.getDict(form);
	}

	public void update(CommConfigDegreeLevelForm form) {
		CommConfigDegreeLevel data = (CommConfigDegreeLevel)this.getCommonDAO().findById("CommConfigDegreeLevel", "itemCode", form.getItemCode().trim());
		this.setData(data, form);
		CommConfigDegreeLevel data_ = (CommConfigDegreeLevel) this.getCommonDAO().findById("CommConfigDegreeLevel", "itemCode", data.getParentItemCode());
		if(data_!=null){
			data.setLevelFlag(2L);
		}else{
			data.setLevelFlag(1L);
		}
		this.getCommonDAO().update(data);
	}

	public void delete(CommConfigDegreeLevelForm form) {
		this.getCommonDAO().delete(this.getCommonDAO().findById("CommConfigDegreeLevel", "itemCode", form.getIdHidden().trim()));
	}
}
