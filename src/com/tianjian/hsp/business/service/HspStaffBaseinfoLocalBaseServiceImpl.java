package com.tianjian.hsp.business.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tianjian.comm.dao.ICommonDAO;
import com.tianjian.hsp.business.IHspStaffBaseinfoLocalBaseService;
import com.tianjian.hsp.dao.IHspStaffBaseinfoLocalBaseDAO;
import com.tianjian.hsp.struts.form.HspStaffBaseinfoLocalBaseForm;
import com.tianjian.util.comm.UtilTrans;

public class HspStaffBaseinfoLocalBaseServiceImpl implements IHspStaffBaseinfoLocalBaseService{
	private IHspStaffBaseinfoLocalBaseDAO hspStaffBaseinfoLocalBaseDAO;
	private ICommonDAO commonDAO;
	
	public IHspStaffBaseinfoLocalBaseDAO getHspStaffBaseinfoLocalBaseDAO() {
		return hspStaffBaseinfoLocalBaseDAO;
	}

	public void setHspStaffBaseinfoLocalBaseDAO(
			IHspStaffBaseinfoLocalBaseDAO hspStaffBaseinfoLocalBaseDAO) {
		this.hspStaffBaseinfoLocalBaseDAO = hspStaffBaseinfoLocalBaseDAO;
	}

	@Override
	public int getCount(String hspConfigBaseinfoIdQuery, String nameQuery,
			String idNoQuery, String staffId, String hspConfigBaseinfoId) {
		// TODO Auto-generated method stub
		return this.hspStaffBaseinfoLocalBaseDAO.getCount(hspConfigBaseinfoIdQuery, nameQuery, idNoQuery, staffId, hspConfigBaseinfoId);
	}

	@Override
	public void getSearch(HspStaffBaseinfoLocalBaseForm hosform, int count,
			int pageSize, HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<?> list = this.hspStaffBaseinfoLocalBaseDAO.getSearch(hosform, count, pageSize, request);
		if (list != null && list.size() > 0) {
			int listSize = list.size();
			
			String[] empNoArray =  new String[listSize];
			String[] hspIdArray =  new String[listSize];
			String[] hspNameArray =  new String[listSize];
			String[] nameArray =  new String[listSize];
			String[] commSexIdArray =  new String[listSize];
			String[] commSexNameArray =  new String[listSize]; 
			String[] dateOfBirthArray =  new String[listSize];
			String[] idNoArray =  new String[listSize];
			String[] idsHiddenArray =  new String[listSize];
			String[] mobileTelArray =  new String[listSize];
		
			for (int i = 0; i < listSize; i++) {
				Object[] obj = (Object[])list.get(i);
				idsHiddenArray[i] = UtilTrans.transNullToString(obj[0]);
				hspIdArray[i] = UtilTrans.transNullToString(obj[1]);
				empNoArray[i] = UtilTrans.transNullToString(obj[2]);
				nameArray[i] = UtilTrans.transNullToString(obj[3]);
				idNoArray[i] = UtilTrans.transNullToString(obj[4]);
				dateOfBirthArray[i] = UtilTrans.transNullToString(obj[5]);
				commSexIdArray[i] = UtilTrans.transNullToString(obj[6]);
				mobileTelArray[i] = UtilTrans.transNullToString(obj[7]);
				hspNameArray[i] = UtilTrans.transNullToString(obj[8]);
				commSexNameArray[i] = UtilTrans.transNullToString(commonDAO.getNameById("CommConfigSex", "itemCode", "itemName", commSexIdArray[i]));
			}
			hosform.setIdsHiddenArray(idsHiddenArray);
			hosform.setHspIdArray(hspIdArray);
			hosform.setEmpNoArray(empNoArray);
			hosform.setNameArray(nameArray);
			hosform.setIdNoArray(idNoArray);
			hosform.setDateOfBirthArray(dateOfBirthArray);
			hosform.setCommSexIdArray(commSexIdArray);
			hosform.setMobileTelArray(mobileTelArray);
			hosform.setHspNameArray(hspNameArray);
			hosform.setCommSexNameArray(commSexNameArray);
		}
		
	}

	public ICommonDAO getCommonDAO() {
		return commonDAO;
	}

	public void setCommonDAO(ICommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}
}
