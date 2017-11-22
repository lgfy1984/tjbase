package com.tianjian.hsp.business.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.time.DateFormatUtils;

import com.tianjian.hsp.bean.HspConfigBaseinfo;
import com.tianjian.hsp.bean.HspLogoutRecord;
import com.tianjian.hsp.bean.HspStaffLogoutRecord;
import com.tianjian.hsp.business.IHspLogoutRecordService;
import com.tianjian.hsp.dao.IHspConfigBaseinfoDAO;
import com.tianjian.hsp.dao.IHspLogoutRecordDAO;
import com.tianjian.hsp.struts.form.HspConfigBaseinfoForm;
import com.tianjian.hsp.struts.form.HspStaffBaseinfoForm;
import com.tianjian.security.struts.servlet.BaseSecurityInit;
import com.tianjian.util.Converter;

public class HspLogoutRecordServiceImpl implements IHspLogoutRecordService {
	
	public IHspLogoutRecordDAO hspLogoutRecordDAO;
	public IHspConfigBaseinfoDAO hspConfigBaseinfoDAO;	

	public IHspLogoutRecordDAO getHspLogoutRecordDAO() {
		return hspLogoutRecordDAO;
	}

	public void setHspLogoutRecordDAO(IHspLogoutRecordDAO hspLogoutRecordDAO) {
		this.hspLogoutRecordDAO = hspLogoutRecordDAO;
	}

	public IHspConfigBaseinfoDAO getHspConfigBaseinfoDAO() {
		return hspConfigBaseinfoDAO;
	}

	public void setHspConfigBaseinfoDAO(IHspConfigBaseinfoDAO hspConfigBaseinfoDAO) {
		this.hspConfigBaseinfoDAO = hspConfigBaseinfoDAO;
	}
	
    private void init(HspConfigBaseinfoForm hcbForm){
		
	}

	public void delete(HspConfigBaseinfoForm hcbForm) {
		HspLogoutRecord data = this.hspLogoutRecordDAO.findById(hcbForm.getId());
		this.hspLogoutRecordDAO.delete(data);
	}

	public int getCount(String hspConfigBaseinfoId, String itemCode,String itemName) {
		
		return hspLogoutRecordDAO.getCount(hspConfigBaseinfoId, itemCode, itemName);
	}

	public void getDetail(HspConfigBaseinfoForm hcbForm) {
		
	}

	public void getSearch(HspConfigBaseinfoForm hcbForm, int curCount,
			int pageSize) {
		String order = "";
		if(hcbForm.getOrderNo().equals("1")){
			order = " a.itemCode";
		}else if(hcbForm.getOrderNo().equals("2")){
			order = " a.itemName";
		}else{
			order = " a.id";
		}
		if(hcbForm.getAsc().equals("1")){
			order += " desc";
		}else{
			order += " asc";
		}
		
		List<?> list = this.hspLogoutRecordDAO.getData(hcbForm.getHspConfigBaseinfoId(), hcbForm.getItemCode(), hcbForm.getItemName(), order, curCount, pageSize);
		
		if(list != null && list.size() > 0){
			String[] ids = new String[list.size()];
			String[] itemCodes = new String[list.size()];
			String[] itemNames = new String[list.size()];
			String [] addresses = new String[list.size()];
			String[] seqNos = new String[list.size()];
			String[] phones = new String[list.size()];
			String[] contactPersonNames = new String[list.size()];
			for(int i = 0; i < list.size(); i++){
				HspLogoutRecord bean =(HspLogoutRecord) list.get(i);
				ids[i] = Converter.toBlank(bean.getId());
				itemCodes[i] = Converter.toBlank(bean.getItemCode());
				itemNames[i] = Converter.toBlank(bean.getItemName());
				addresses[i] = Converter.toBlank("");
				phones[i] = Converter.toBlank("");
				contactPersonNames[i] = Converter.toBlank("");
				seqNos[i] = Converter.toBlank("");
			}
			hcbForm.setIdList(ids);
			hcbForm.setItemCodeList(itemCodes);
			hcbForm.setItemNameList(itemNames);
			hcbForm.setPhoneList(phones);
			hcbForm.setContactPersonNameList(contactPersonNames);
			hcbForm.setSeqNoList(seqNos);
			hcbForm.setAddressList(addresses);
		}
		hcbForm.setCommConfigLocationId1(BaseSecurityInit.getProperty("INIT_PROVINCE"));
		hcbForm.setCommConfigLocationId2(BaseSecurityInit.getProperty("INIT_CITY"));
		hcbForm.setCommConfigLocationId3(BaseSecurityInit.getProperty("INIT_COUNTY"));
		
	}

	@Override
	public void save(HspConfigBaseinfoForm hcbForm) {
		HspConfigBaseinfo oldData = this.hspConfigBaseinfoDAO.getById(hcbForm.getId());
		HspLogoutRecord data = new HspLogoutRecord();
		data.setId(UUID.randomUUID().toString().replace("-", ""));
		data.setHspConfigBaseinfoId(oldData.getId());
		data.setItemCode(oldData.getItemCode());
		data.setItemName(oldData.getItemName());
		data.setDateCreated(oldData.getCreateDate());
		data.setCreatorName(oldData.getCreateUserName());
		
		data.setLogoutTime(new Date());
		data.setLogoutReason(hcbForm.getLogoutReason());
		data.setCreateUserId(hcbForm.getCreateUserId1());
		data.setCreateUserName(hcbForm.getCreateUserName1());
		
		this.hspLogoutRecordDAO.save(data);
	}

	public void serchInit(HspConfigBaseinfoForm hcbForm) {
		
	}

	private void setForm(HspConfigBaseinfoForm hcbForm, HspLogoutRecord data) {
		hcbForm.setId(data.getId());
		hcbForm.setHspConfigBaseinfoId(data.getHspConfigBaseinfoId());
		hcbForm.setItemCode(data.getItemCode());
		hcbForm.setItemName(data.getItemName());
		
		hcbForm.setCreateDate(DateFormatUtils.format(data.getDateCreated(), "yyyy-MM-dd"));
		hcbForm.setCreateUserName(data.getCreatorName());
		
		hcbForm.setLogoutReason(data.getLogoutReason());
		hcbForm.setLogoutTime(DateFormatUtils.format(data.getLogoutTime(), "yyyy-MM-dd"));
		hcbForm.setCreateUserId1(data.getCreateUserId());
		hcbForm.setCreateUserName1(data.getCreateUserName());
	}

	@Override
	public void showInit(HspConfigBaseinfoForm hcbForm) {
		HspLogoutRecord data = this.hspLogoutRecordDAO.findById(this.transNullToString(hcbForm.getIdHidden()));
		hcbForm = new HspConfigBaseinfoForm();
		this.setForm(hcbForm, data);
		init(hcbForm);
	}

	private void setData(HspConfigBaseinfoForm hcbForm, HspLogoutRecord data) {
		data.setLogoutReason(transNullToString(hcbForm.getLogoutReason()));
	}

	public void update(HspConfigBaseinfoForm hcbForm) {
		HspLogoutRecord data = this.hspLogoutRecordDAO.findById(hcbForm.getIdHidden());
		this.setData(hcbForm, data);
		this.hspLogoutRecordDAO.update(data);
	}

	public void updateInit(HspConfigBaseinfoForm hcbForm) {
		HspLogoutRecord data = this.hspLogoutRecordDAO.findById(hcbForm.getId());
		this.setForm(hcbForm, data);
		init(hcbForm);
	}
	
	public String transNullToString(Object obj){
		String temp = "";
		if (obj != null){
			temp = ((String)obj).trim();			
		}
		return temp;
	}

}
