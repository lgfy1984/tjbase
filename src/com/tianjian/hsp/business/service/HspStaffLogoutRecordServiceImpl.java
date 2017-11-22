package com.tianjian.hsp.business.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.time.DateFormatUtils;

import com.tianjian.hsp.bean.HspStaffBaseinfo;
import com.tianjian.hsp.bean.HspStaffLogoutRecord;
import com.tianjian.hsp.business.IHspStaffLogoutRecordService;
import com.tianjian.hsp.dao.IHspStaffBaseinfoDAO;
import com.tianjian.hsp.dao.IHspStaffLogoutRecordDAO;
import com.tianjian.hsp.struts.form.HspStaffBaseinfoForm;

public class HspStaffLogoutRecordServiceImpl implements IHspStaffLogoutRecordService{
	public IHspStaffLogoutRecordDAO hspStaffLogoutRecordDAO;
	public IHspStaffBaseinfoDAO hspStaffBaseinfoDAO;

	public void delete(HspStaffBaseinfoForm hForm) {
		HspStaffLogoutRecord data = hspStaffLogoutRecordDAO.findById(hForm.getId());
		hspStaffLogoutRecordDAO.delete(data);
	}

	public int getCount(String hspStaffBaseinfoId, String empNo, String name,
			String idNo) {
		return hspStaffLogoutRecordDAO.getCount(hspStaffBaseinfoId, empNo, name, idNo);
	}
	
    private void init(HspStaffBaseinfoForm hForm){
		
	}

	public void getDetail(HspStaffBaseinfoForm hspStaffBaseinfoForm) {
		
	}

	public void getSearch(HspStaffBaseinfoForm hForm, int curCount, int pageSize) {
		String order = "";
		if(hForm.getOrderNo().equals("1")){
			order = " a.empNo";
		}else if(hForm.getOrderNo().equals("2")){
			order = " a.name";
		}else if(hForm.getOrderNo().equals("4")){
			order = " a.hspConfigBaseinfoId";
		}else if(hForm.getOrderNo().equals("5")){
			order = " a.idNo";
		}else{
			order = " a.id";
		}
		if(hForm.getAsc().equals("1")){
			order += " desc";
		}else{
			order += " asc";
		}
		
		List<?> list=this.hspStaffLogoutRecordDAO.getData(hForm.getId(), hForm.getEmpNo(), hForm.getName(), hForm.getIdNo(),order, curCount, pageSize);
		
		if (list != null && list.size() > 0) {
			int listSize = list.size();
			String idList[] = new String[listSize];
			String isidNull[] = new String[listSize];
			String hspNameList[] = new String[listSize];
			String nameList[] = new String[listSize];
			String sexList[] = new String[listSize];
			String idNoList[] = new String[listSize];
			String empNoList []=  new String[listSize];
			String hspIdList [] = new String[listSize];
			String commSexIdList [] = new String[listSize];
			String dateOfBrith[] = new String[listSize];
			
			for(int i = 0;i < listSize;i++){
				HspStaffLogoutRecord hslr = (HspStaffLogoutRecord) list.get(i);
				idList[i] = hslr.getId();
				hspIdList[i] = "";
				commSexIdList[i] = "";
				dateOfBrith[i] = "";
				hspNameList[i] = "";
				if (hslr.getName() != null) {
					nameList[i] = hslr.getName();
				} else {
					nameList[i] = "";
				}
				sexList[i] = "";
				if (hslr.getIdNo() != null) {
					idNoList[i] = hslr.getIdNo();
				} else {
					idNoList[i] = "";
				}
				if (hslr.getEmpNo() != null) {
					empNoList[i] = hslr.getEmpNo();
				} else {
					empNoList[i] = "";
				}
			}
			hForm.setIdsHiddenArray(idList);
			hForm.setIsIdNull(isidNull);
			hForm.setHspNameArray(hspNameList);
			hForm.setHspIdArray(hspIdList);
			hForm.setNameArray(nameList);
			hForm.setIdNoArray(idNoList);
			hForm.setSexArray(sexList);
			hForm.setEmpNoArray(empNoList);
			hForm.setCommSexIdArray(commSexIdList);
			hForm.setDateOfBirthArray(dateOfBrith);
		}
	}

	public void save(HspStaffBaseinfoForm hForm) {
		HspStaffBaseinfo oldData = hspStaffBaseinfoDAO.findById(this.transNullToString(hForm.getIdHidden()));
		HspStaffLogoutRecord data = new HspStaffLogoutRecord();
		data.setId(UUID.randomUUID().toString().replace("-", ""));
		data.setHspStaffBaseinfoId(oldData.getId());
		data.setEmpNo(oldData.getEmpNo());
		data.setIdNo(oldData.getIdNo());
		data.setName(oldData.getName());
		data.setDateCreated(oldData.getCreateDate());
		data.setCreatorName(oldData.getCreateUserName());
		
		data.setLogoutTime(new Date());
		data.setLogoutReason(hForm.getLogoutReason());
		data.setCreateUserId(hForm.getCreateUserId1());
		data.setCreateUserName(hForm.getCreateUserName1());
		
		hspStaffLogoutRecordDAO.save(data);
	}

	public void serchInit(HspStaffBaseinfoForm hspStaffBaseinfoForm) {
		
	}

	public void showInit(HspStaffBaseinfoForm hForm) {
		HspStaffLogoutRecord data = hspStaffLogoutRecordDAO.findById(this.transNullToString(hForm.getIdHidden()));
		hForm = new HspStaffBaseinfoForm();
		this.setForm(hForm, data);
		init(hForm);
	}

	private void setForm(HspStaffBaseinfoForm hForm, HspStaffLogoutRecord data) {
		hForm.setId(transNullToString(data.getId()));
		hForm.setHspStaffBaseinfoId(data.getHspStaffBaseinfoId());
		hForm.setEmpNo(data.getEmpNo());
		hForm.setIdNo(data.getIdNo());
		hForm.setName(data.getName());
		hForm.setCreateDate(DateFormatUtils.format(data.getDateCreated(), "yyyy-MM-dd"));
		hForm.setCreateUserName(data.getCreatorName());
		
		hForm.setLogoutReason(data.getLogoutReason());
		hForm.setLogoutTime(DateFormatUtils.format(data.getLogoutTime(), "yyyy-MM-dd"));
		hForm.setCreateUserId1(data.getCreateUserId());
		hForm.setCreateUserName1(data.getCreateUserName());
	}

	public void update(HspStaffBaseinfoForm hForm) {
		HspStaffLogoutRecord data = hspStaffLogoutRecordDAO.findById(hForm.getIdHidden());
		this.setData(hForm, data);
		hspStaffLogoutRecordDAO.update(data);
	}

	private void setData(HspStaffBaseinfoForm hForm, HspStaffLogoutRecord data) {
		data.setLogoutReason(transNullToString(hForm.getLogoutReason()));
	}

	public void updateInit(HspStaffBaseinfoForm hForm) {
		HspStaffLogoutRecord data = hspStaffLogoutRecordDAO.findById(hForm.getId());
		this.setForm(hForm, data);
		init(hForm);
	}

	public IHspStaffLogoutRecordDAO getHspStaffLogoutRecordDAO() {
		return hspStaffLogoutRecordDAO;
	}

	public void setHspStaffLogoutRecordDAO(
			IHspStaffLogoutRecordDAO hspStaffLogoutRecordDAO) {
		this.hspStaffLogoutRecordDAO = hspStaffLogoutRecordDAO;
	}

	public IHspStaffBaseinfoDAO getHspStaffBaseinfoDAO() {
		return hspStaffBaseinfoDAO;
	}

	public void setHspStaffBaseinfoDAO(IHspStaffBaseinfoDAO hspStaffBaseinfoDAO) {
		this.hspStaffBaseinfoDAO = hspStaffBaseinfoDAO;
	}
	
	public String transNullToString(Object obj){
		String temp = "";
		if (obj != null){
			temp = ((String)obj).trim();			
		}
		return temp;
	}

}
