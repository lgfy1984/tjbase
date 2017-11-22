package com.tianjian.comm.business.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tianjian.comm.bean.CommConfigLocation;
import com.tianjian.comm.bean.CommConfigLocationTown;
import com.tianjian.comm.business.ICommConfigLocationTownService;
import com.tianjian.comm.dao.ICommConfigLocationTownDAO;
import com.tianjian.comm.struts.form.CommConfigLocationTownForm;
import com.tianjian.comm.struts.servlet.CommConfigLocationTownInit;
import com.tianjian.util.ResourcesUtil;
/**
 * COMM_CONFIG_LOCATION_TOWN SERVICE
 * @AUTHOR DZENALL
 * @SINCE 2008-9-17
 */
public class ICommConfigLocationTownServiceImpl implements ICommConfigLocationTownService {
	private String flag = CommConfigLocationTownInit.getProperty("PRINT_FLAG");
	private String comm_itemCode = CommConfigLocationTownInit.getProperty("COMM_ITEMCODE");
	private ICommConfigLocationTownDAO commConfigLocationTownDAO;
	public ICommConfigLocationTownDAO getCommConfigLocationTownDAO() {
		return commConfigLocationTownDAO;
	}
	public void setCommConfigLocationTownDAO(
			ICommConfigLocationTownDAO commConfigLocationTownDAO) {
		this.commConfigLocationTownDAO = commConfigLocationTownDAO;
	}
	//-------------------------------------------------------

	public void add(CommConfigLocationTownForm form, HttpServletRequest request) {
		CommConfigLocationTown data = new CommConfigLocationTown();
		this.setData(form, data, "add");
		this.getCommConfigLocationTownDAO().save(data);
		String message = ResourcesUtil.getValue("conf.comm.CommLocale", "comm.java.commom.newAddSuccess", request) + "!";
		form.setMessage(message);
	}

	public void addInit(CommConfigLocationTownForm form) {
		form.setCommConfigLocationId1(CommConfigLocationTownInit.getProperty("INIT_PROVINCE_IC"));
		form.setCommConfigLocationId2(CommConfigLocationTownInit.getProperty("INIT_CITY_IC"));
		form.setCommConfigLocationId3(CommConfigLocationTownInit.getProperty("INIT_COUNTY_IC"));
		form.setSeqNo(this.transNullToString(this.getCommConfigLocationTownDAO().seqNoMaker(form.getSeqNo())));
		
		CommConfigLocationTown data = this.getCommConfigLocationTownDAO().findById(form.getId());
		if(data!=null)
			this.setForm(form, data);
		this.init(form);
	}


	public CommConfigLocationTown checkData(String itemCode) {
		CommConfigLocationTown data = this.getCommConfigLocationTownDAO().findByItemCode(itemCode);
		return data;
	}
	
	public CommConfigLocationTown checkDataById(String id) {
		CommConfigLocationTown data = this.getCommConfigLocationTownDAO().findById(id);
		return data;
	}
	public void delete(CommConfigLocationTownForm form, HttpServletRequest request) {
		try{
			CommConfigLocationTown data = this.getCommConfigLocationTownDAO().findById(form.getIdHidden());
			this.getCommConfigLocationTownDAO().delete(data);
			String message = ResourcesUtil.getValue("conf.comm.CommLocale", "comm.java.commom.deleteSuccess", request) + "!";
			form.setMessage(message);
		}catch(Exception e){
			e.printStackTrace();
			String message = ResourcesUtil.getValue("conf.comm.CommLocale", "comm.java.ICommConfigLocationTownServiceImpl.deleteFail", request) + "!";
			form.setMessage(message);
		}
	}

	public void getCitys(CommConfigLocationTownForm form) {
		String[] tempIds = null;
		String[] tempNames = null;
		List<?> list = null;
		if(form.getCommConfigLocationId1() != null && form.getCommConfigLocationId1().trim().length() > 0){
			list = this.getCommConfigLocationTownDAO().getByParent(form.getCommConfigLocationId1().trim(), "2");
			if(list != null && list.size() > 0){
				tempIds = new String[list.size()+1];
				tempNames = new String[list.size()+1];
				tempIds[0] = "";
				tempNames[0] = "";
				for(int i=0; i<list.size(); i++){
					CommConfigLocation location = (CommConfigLocation)list.get(i);
					tempIds[i+1] = location.getId();
					tempNames[i+1] = location.getItemName();
				}
				form.setCommConfigLocationIds2(tempIds);
				form.setCommConfigLocationNames2(tempNames);
			}
		}	
	}

	public int getCount(String itemCode, String itemName, String inputCode) {
		return this.getCommConfigLocationTownDAO().getCount(itemCode, itemName, inputCode);
	}

	public void getCounties(CommConfigLocationTownForm form) {
		String[] tempIds = null;
		String[] tempNames = null;
		List<?> list = null;
		if(form.getCommConfigLocationId2() != null && form.getCommConfigLocationId2().trim().length() > 0){
			list = this.getCommConfigLocationTownDAO().getByParent(form.getCommConfigLocationId2().trim(), "3");
			if(list != null && list.size() > 0){
				tempIds = new String[list.size()+1];
				tempNames = new String[list.size()+1];
				tempIds[0] = "";
				tempNames[0] = "";
				for(int i=0; i<list.size(); i++){
					CommConfigLocation location = (CommConfigLocation)list.get(i);
					tempIds[i+1] = location.getId();
					tempNames[i+1] = location.getItemName();
				}
				form.setCommConfigLocationIds3(tempIds);
				form.setCommConfigLocationNames3(tempNames);
			}
		}	
	}
	//得到乡镇编号
	public String getItemCode(CommConfigLocationTownForm form){

		String cmi=null;
		if(form.getCommConfigLocationId3() != null && form.getCommConfigLocationId3().trim().length() > 0){
			String temp = this.getCommConfigLocationTownDAO().getItemCode(form.getCommConfigLocationId3().trim());
			if(Long.valueOf(temp)>0){				
				cmi=comm_itemCode.substring(0, 1)+String.valueOf(Long.valueOf(temp));
			}
			if(Long.valueOf(temp)>10){				
				cmi=String.valueOf(Long.valueOf(temp));
			}

		}	
		return cmi;	
	}
	public void getDetail(CommConfigLocationTownForm form) {
		form.setCommConfigLocationId2(this.transNullToString(this.getCommConfigLocationTownDAO().findParentIdById(form.getCommConfigLocationId3())));
		form.setCommConfigLocationId1(this.transNullToString(this.getCommConfigLocationTownDAO().findParentIdById(form.getCommConfigLocationId2())));
		form.setCommConfigLocationName1(this.transNullToString(this.getCommConfigLocationTownDAO().findItemNameById(form.getCommConfigLocationId1())));
		form.setCommConfigLocationName2(this.transNullToString(this.getCommConfigLocationTownDAO().findItemNameById(form.getCommConfigLocationId2())));
		form.setCommConfigLocationName3(this.transNullToString(this.getCommConfigLocationTownDAO().findItemNameById(form.getCommConfigLocationId3())));
	}

	public void getSearch(CommConfigLocationTownForm form, int curCount, int pageSize) {
		String order = "";
		if (form.getOrderNo().equals("1")) {
			order = " a.itemCode ";
		} else if (form.getOrderNo().equals("2")) {
			order = " a.itemName ";
		} else if (form.getOrderNo().equals("3")) {
			order = " a.inputCode ";
		} else if (form.getOrderNo().equals("4")) {
			order = " b.itemName ";
		} else {
			order = " a.itemCode";
		}
		if (form.getAsc().equals("1")) {
			order += " desc";
		} else {
			order += " asc";
		}
		List<?> list = this.getCommConfigLocationTownDAO().getData(form.getItemCode(), form.getItemName(), form.getInputCode(), order, curCount, pageSize);
		if (list != null && list.size() > 0) {
			String[] idList = new String[list.size()];
			String[] itemCodeList = new String[list.size()];
			String[] seqNoList = new String[list.size()];
			String[] itemNameList = new String[list.size()];
			String[] inputCodeList = new String[list.size()];
			String[] commConfigLocationIdList1 = new String[list.size()];
			String[] commConfigLocationNameList1 = new String[list.size()];
			String[] commConfigLocationIdList2 = new String[list.size()];
			String[] commConfigLocationNameList2 = new String[list.size()];
			String[] commConfigLocationIdList3 = new String[list.size()];
			String[] commConfigLocationNameList3 = new String[list.size()];
			String[] commentsList = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				idList[i]			= transNullToString(((Object[]) list.get(i))[7]);
				itemCodeList[i] 	= transNullToString(((Object[]) list.get(i))[0]);
				seqNoList[i] 		= transNullToString(((Object[]) list.get(i))[6]);
				itemNameList[i] 	= transNullToString(((Object[]) list.get(i))[1]);
				inputCodeList[i] 	= transNullToString(((Object[]) list.get(i))[2]);
				commentsList[i] 	= transNullToString(((Object[]) list.get(i))[3]);
				commConfigLocationIdList3[i] = transNullToString(String.valueOf(((Object[]) list.get(i))[4]));
				commConfigLocationNameList3[i] = transNullToString(String.valueOf(((Object[]) list.get(i))[5]));

				commConfigLocationIdList2[i] = transNullToString(this.getCommConfigLocationTownDAO().findParentIdById(commConfigLocationIdList3[i]));
				commConfigLocationNameList2[i] = transNullToString(this.getCommConfigLocationTownDAO().findItemNameById(commConfigLocationIdList2[i]));

				commConfigLocationIdList1[i] = transNullToString(this.getCommConfigLocationTownDAO().findParentIdById(commConfigLocationIdList2[i]));
				commConfigLocationNameList1[i] = transNullToString(this.getCommConfigLocationTownDAO().findItemNameById(commConfigLocationIdList1[i]));	
			}
			form.setIdList(idList);
			form.setItemCodeList(itemCodeList);
			form.setSeqNoList(seqNoList);
			form.setItemNameList(itemNameList);
			form.setCommentsList(commentsList);
			form.setInputCodeList(inputCodeList);
			form.setCommConfigLocationIdList1(commConfigLocationIdList1);
			form.setCommConfigLocationIdList2(commConfigLocationIdList2);
			form.setCommConfigLocationIdList3(commConfigLocationIdList3);
			form.setCommConfigLocationNameList1(commConfigLocationNameList1);
			form.setCommConfigLocationNameList2(commConfigLocationNameList2);
			form.setCommConfigLocationNameList3(commConfigLocationNameList3);
		}
	}

	public void setForm(CommConfigLocationTownForm form, CommConfigLocationTown data) {
		form.setCommConfigLocationId3(this.transNullToString(data.getCommConfigLocationId()));
		form.setComments(this.transNullToString(data.getComments()));
		form.setInputCode(this.transNullToString(data.getInputCode()));
		form.setItemCode(this.transNullToString(data.getItemCode()));
		form.setItemName(this.transNullToString(data.getItemName()));
		if(data.getSeqNo()==null)
			form.setSeqNo("");
		else
			form.setSeqNo(this.transNullToString(data.getSeqNo()));
	}

	public void update(CommConfigLocationTownForm form, HttpServletRequest request) {
		CommConfigLocationTown data = this.getCommConfigLocationTownDAO().findById(form.getId());
		this.setData(form, data, "update");
		this.getCommConfigLocationTownDAO().update(data);
		String message = ResourcesUtil.getValue("conf.comm.CommLocale", "comm.java.commom.updateSuccess", request) + "!";
		form.setMessage(message);
	}

	public void updateInit(CommConfigLocationTownForm form) {
		CommConfigLocationTown data = this.getCommConfigLocationTownDAO().findById(form.getId());
		this.setForm(form, data);
		this.getDetail(form);
		this.init(form);		
	}

	//------------------------------------------------------------------
	private void setData(CommConfigLocationTownForm form, CommConfigLocationTown data, String verbId) {
		data.setCommConfigLocationId(this.transNullToString(form.getCommConfigLocationId3()));
		data.setComments(this.transNullToString(form.getComments()));	
		data.setItemCode(this.transNullToString(form.getItemCode()));
		data.setItemName(this.transNullToString(form.getItemName()));
		try{
			data.setSeqNo(Long.valueOf(this.transNullToString(form.getSeqNo())));
		}catch(Exception e){
			if(flag.equalsIgnoreCase("true")){
				e.printStackTrace();
			}
			data.setSeqNo(0L);
		}
		if(verbId.equalsIgnoreCase("add")){			
			data.setInputCode(this.transNullToString(form.getInputCode()));
		}
	}
	private String transNullToString(Object obj) {
		String temp = "";
		if(obj==null){
			temp = "";
		}else if(obj.equals("null")){
			temp = "";
		}else{
			temp = String.valueOf(obj).trim();
		}
		return temp;
	}
	/** �ڳ�ʼ���׶ν��ֵ�⴫��form�� */
	private void init(CommConfigLocationTownForm form) {
		this.getDict(form);
		form.setCtrlNo("0");
	}
	private void getDict(CommConfigLocationTownForm form) {
		String[] tempIds = null;
		String[] tempNames = null;
		List<?> list = null;
//		commConfigLocationIds1 & commConfigLocationNames1;
		list = this.getCommConfigLocationTownDAO().getByParent("", "1");
		if(list != null && list.size() > 0){
			tempIds = new String[list.size()+1];
			tempNames = new String[list.size()+1];
			tempIds[0] = "";
			tempNames[0] = "";
			for(int i=0; i<list.size(); i++){
				CommConfigLocation location = (CommConfigLocation)list.get(i);
				tempIds[i+1] = location.getId();
				tempNames[i+1] = location.getItemName();
			}
			form.setCommConfigLocationIds1(tempIds);
			form.setCommConfigLocationNames1(tempNames);
		}
//		commConfigLocationIds2 & commConfigLocationNames2;
		tempIds = null;
		tempNames = null;
		list = null;
		if(form.getCommConfigLocationId1() != null && form.getCommConfigLocationId1().trim().length() > 0){
			list = this.getCommConfigLocationTownDAO().getByParent(form.getCommConfigLocationId1().trim(), "2");
			if(list != null && list.size() > 0){
				tempIds = new String[list.size()+1];
				tempNames = new String[list.size()+1];
				tempIds[0] = "";
				tempNames[0] = "";
				for(int i=0; i<list.size(); i++){
					CommConfigLocation location = (CommConfigLocation)list.get(i);
					tempIds[i+1] = location.getId();
					tempNames[i+1] = location.getItemName();
				}
				form.setCommConfigLocationIds2(tempIds);
				form.setCommConfigLocationNames2(tempNames);
			}
		}		
//		commConfigLocationIds3 & commConfigLocationNames3;	
		tempIds = null;
		tempNames = null;
		list = null;
		if(form.getCommConfigLocationId2() != null && form.getCommConfigLocationId2().trim().length() > 0){
			list = this.getCommConfigLocationTownDAO().getByParent(form.getCommConfigLocationId2().trim(), "3");
			if(list != null && list.size() > 0){
				tempIds = new String[list.size()+1];
				tempNames = new String[list.size()+1];
				tempIds[0] = "";
				tempNames[0] = "";
				for(int i=0; i<list.size(); i++){
					CommConfigLocation location = (CommConfigLocation)list.get(i);
					tempIds[i+1] = location.getId();
					tempNames[i+1] = location.getItemName();
				}
				form.setCommConfigLocationIds3(tempIds);
				form.setCommConfigLocationNames3(tempNames);
			}
		}	
	}
	

}
