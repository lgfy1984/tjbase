package com.tianjian.comm.business.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tianjian.comm.bean.CommConfigLocation;
import com.tianjian.comm.bean.CommConfigLocationGroup;
import com.tianjian.comm.bean.CommConfigLocationTown;
import com.tianjian.comm.bean.CommConfigLocationVillage;
import com.tianjian.comm.business.ICommConfigLocationGroupService;
import com.tianjian.comm.dao.ICommConfigLocationGroupDAO;
import com.tianjian.comm.struts.form.CommConfigLocationGroupForm;
import com.tianjian.comm.struts.servlet.CommConfigLocationTownInit;
import com.tianjian.util.ResourcesUtil;
/**
 * COMM_CONFIG_LOCATION_GROUP居民组字典Service接口实现
 * @author Dzenall
 * @since 2008-9-18
 */
public class CommConfigLocationGroupServiceImpl implements ICommConfigLocationGroupService {

	private String flag = CommConfigLocationTownInit.getProperty("PRINT_FLAG");
	private String comm_itemCode = CommConfigLocationTownInit.getProperty("COMM_ITEMCODE");
	private ICommConfigLocationGroupDAO commConfigLocationGroupDAO;
	public ICommConfigLocationGroupDAO getCommConfigLocationGroupDAO() {
		return commConfigLocationGroupDAO;
	}
	public void setCommConfigLocationGroupDAO(
			ICommConfigLocationGroupDAO commConfigLocationGroupDAO) {
		this.commConfigLocationGroupDAO = commConfigLocationGroupDAO;
	}
	//---------------------------------------------------------------------
	
	public void add(CommConfigLocationGroupForm form) {
		CommConfigLocationGroup data = new CommConfigLocationGroup();
		this.setData(form, data);
		this.getCommConfigLocationGroupDAO().save(data);
	}


	
	public void addInit(CommConfigLocationGroupForm form) {
		form.setCommProvinceId(CommConfigLocationTownInit.getProperty("INIT_PROVINCE_IC"));
		form.setCommCityId(CommConfigLocationTownInit.getProperty("INIT_CITY_IC"));
		form.setCommCountyId(CommConfigLocationTownInit.getProperty("INIT_COUNTY_IC"));
		form.setSeqNo(this.transNullToString(this.getCommConfigLocationGroupDAO().seqNoMaker()));
		this.init(form);
	}


	
	public CommConfigLocationGroup checkData(String itemCode) {
		CommConfigLocationGroup data = this.getCommConfigLocationGroupDAO().findByItemCode(itemCode);
		return data;
	}

	
	public void delete(CommConfigLocationGroupForm form ,HttpServletRequest request) {
		CommConfigLocationGroup data = this.getCommConfigLocationGroupDAO().findById(form.getIdHidden());
		this.getCommConfigLocationGroupDAO().delete(data);
		String message = ResourcesUtil.getValue("conf.comm.CommLocale", "comm.java.commom.deleteSuccess", request) + "!";
		form.setMessage(message);
	}

	
	public void getCitys(CommConfigLocationGroupForm form) {
		String[] tempIds = null;
		String[] tempNames = null;
		List<?> list = null;
//		commCityIds & commCityNames;
		if(form.getCommProvinceId() != null && form.getCommProvinceId().trim().length() > 0){
			list = this.getCommConfigLocationGroupDAO().getByParent(form.getCommProvinceId().trim(), "2");
			if(list != null && list.size() > 0){
				tempIds = new String[list.size()+1];
				tempNames = new String[list.size()+1];
				tempIds[0] = "";
				tempNames[0] = "";
				for(int i=0; i<list.size(); i++){
					CommConfigLocation location = (CommConfigLocation)list.get(i);
					tempIds[i+1] = this.transNullToString(location.getId());
					tempNames[i+1] = this.transNullToString(location.getItemName());
				}
				form.setCommCityIds(tempIds);
				form.setCommCityNames(tempNames);
			}
		}	
	}

	
	public int getCount(String itemCode, String itemName, String inputCode) {
		return this.getCommConfigLocationGroupDAO().getCount(itemCode, itemName, inputCode);
	}

	
	public void getCounties(CommConfigLocationGroupForm form) {
		String[] tempIds = null;
		String[] tempNames = null;
		List<?> list = null;	
//		commCountyIds & commCountyNames;
		if(form.getCommCityId() != null && form.getCommCityId().trim().length() > 0){
			list = this.getCommConfigLocationGroupDAO().getByParent(form.getCommCityId().trim(), "3");
			if(list != null && list.size() > 0){
				tempIds = new String[list.size()+1];
				tempNames = new String[list.size()+1];
				tempIds[0] = "";
				tempNames[0] = "";
				for(int i=0; i<list.size(); i++){
					CommConfigLocation location = (CommConfigLocation)list.get(i);
					tempIds[i+1] = this.transNullToString(location.getId());
					tempNames[i+1] = this.transNullToString(location.getItemName());
				}
				form.setCommCountyIds(tempIds);
				form.setCommCountyNames(tempNames);
			}
		}
	}
	//定义行政组编号
	public String getItemCode(CommConfigLocationGroupForm form){
		String cmi=null;
		if(form.getCommClvId() != null && form.getCommClvId().trim().length() > 0){
			String temp = this.getCommConfigLocationGroupDAO().getItemCode(form.getCommClvId().trim());
			if(Long.valueOf(temp)>0){				
				cmi=comm_itemCode.substring(0, 1)+String.valueOf(Long.valueOf(temp));
			}
			if(Long.valueOf(temp)>10){				
				cmi=String.valueOf(Long.valueOf(temp));
			}

		}	
		return cmi;
	}
	//得到行政村编号
	public String getVillageItemCode(CommConfigLocationGroupForm form){
		String temp=null;
		if(form.getCommClvId() != null && form.getCommClvId().trim().length() > 0){
			temp = this.getCommConfigLocationGroupDAO().getVillageItemCode(form.getCommClvId().trim());			
		}	
		return temp;
	}
	
	public void getDetail(CommConfigLocationGroupForm form) {
//		commClvId -> commCltId
		form.setCommCltId(this.transNullToString(this.getCommConfigLocationGroupDAO().findValueByProp("CommConfigLocationVillage", "commCltId", "id", form.getCommClvId())));
//		commCltId -> commCountyId;
		form.setCommCountyId(this.transNullToString(this.getCommConfigLocationGroupDAO().findValueByProp("CommConfigLocationTown", "commConfigLocationId", "id", form.getCommCltId())));
//		commCountyId -> commCityId
		form.setCommCityId(this.transNullToString(this.getCommConfigLocationGroupDAO().findValueByProp("CommConfigLocation", "parentId", "id", form.getCommCountyId())));
//		commCityId -> commProvinceId
		form.setCommProvinceId(this.transNullToString(this.getCommConfigLocationGroupDAO().findValueByProp("CommConfigLocation", "parentId", "id", form.getCommCityId())));

//		commClvId -> commClvName
		form.setCommClvName(this.transNullToString(this.getCommConfigLocationGroupDAO().findValueByProp("CommConfigLocationVillage", "itemName", "id", form.getCommClvId())));
//		commCltId -> commCltName;
		form.setCommCltName(this.transNullToString(this.getCommConfigLocationGroupDAO().findValueByProp("CommConfigLocationTown", "itemName", "id", form.getCommCltId())));
//		commCountyId -> commCountyName;
		form.setCommCountyName(this.transNullToString(this.getCommConfigLocationGroupDAO().findValueByProp("CommConfigLocation", "itemName", "id", form.getCommCountyId())));
//		commCityId -> commCityName
		form.setCommCityName(this.transNullToString(this.getCommConfigLocationGroupDAO().findValueByProp("CommConfigLocation", "itemName", "id", form.getCommCityId())));
//		commProvinceId -> commProvinceName;
		form.setCommProvinceName(this.transNullToString(this.getCommConfigLocationGroupDAO().findValueByProp("CommConfigLocation", "itemName", "id", form.getCommProvinceId())));
	}

	
	public void getSearch(CommConfigLocationGroupForm form, int curCount, int pageSize) {
		//itemCode itemName inputCode commClvName
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
		List<?> list = this.getCommConfigLocationGroupDAO().getData(form.getItemCode(), form.getItemName(), form.getInputCode(), order, curCount, pageSize);
		if (list != null && list.size() > 0) {
			String[] idList = new String[list.size()];
			String[] seqNoList = new String[list.size()];
			String[] itemCodeList = new String[list.size()];
			String[] itemNameList = new String[list.size()];
			String[] inputCodeList = new String[list.size()];
			String[] commentsList = new String[list.size()];
			String[] commClvIdList = new String[list.size()];
			String[] commClvNameList = new String[list.size()];

			String[] commProvinceIdList = new String[list.size()];
			String[] commProvinceNameList = new String[list.size()];
			String[] commCityIdList = new String[list.size()];
			String[] commCityNameList = new String[list.size()];
			String[] commCountyIdList = new String[list.size()];
			String[] commCountyNameList = new String[list.size()];
			String[] commCltIdList = new String[list.size()];
			String[] commCltNameList = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				idList[i] 					= transNullToString(((Object[]) list.get(i))[0]);
				seqNoList[i] 				= transNullToString(((Object[]) list.get(i))[1]);
				itemCodeList[i] 			= transNullToString(((Object[]) list.get(i))[2]);
				itemNameList[i] 			= transNullToString(((Object[]) list.get(i))[3]);
				inputCodeList[i] 			= transNullToString(((Object[]) list.get(i))[4]);
				commentsList[i] 			= transNullToString(((Object[]) list.get(i))[5]);
				commClvIdList[i] 			= transNullToString(((Object[]) list.get(i))[6]);
				commClvNameList[i] 			= transNullToString(((Object[]) list.get(i))[7]);

				commCltIdList[i] 			= transNullToString(this.getCommConfigLocationGroupDAO().findValueByProp("CommConfigLocationVillage", "commCltId", "itemCode", commClvIdList[i]));
				commCountyIdList[i] 		= transNullToString(this.getCommConfigLocationGroupDAO().findValueByProp("CommConfigLocationTown", "commConfigLocationId", "itemCode", commCltIdList[i]));
				commCityIdList[i] 			= transNullToString(this.getCommConfigLocationGroupDAO().findValueByProp("CommConfigLocation", "parentId", "id", commCountyIdList[i]));
				commProvinceIdList[i] 		= transNullToString(this.getCommConfigLocationGroupDAO().findValueByProp("CommConfigLocation", "parentId", "id", commCityIdList[i]));				

				commCltNameList[i] 			= transNullToString(this.getCommConfigLocationGroupDAO().findValueByProp("CommConfigLocationVillage", "itemName", "itemCode", commClvIdList[i]));
				commProvinceNameList[i] 	= transNullToString(this.getCommConfigLocationGroupDAO().findValueByProp("CommConfigLocation", "itemName", "id", commProvinceIdList[i]));
				commCityNameList[i] 		= transNullToString(this.getCommConfigLocationGroupDAO().findValueByProp("CommConfigLocation", "itemName", "id", commCityIdList[i]));
				commCountyNameList[i] 		= transNullToString(this.getCommConfigLocationGroupDAO().findValueByProp("CommConfigLocation", "itemName", "id", commCountyIdList[i]));	
			}
			form.setIdList(idList);
			form.setSeqNoList(seqNoList);
			form.setItemCodeList(itemCodeList);			
			form.setItemNameList(itemNameList);
			form.setInputCodeList(inputCodeList);
			form.setCommentsList(commentsList);
			form.setCommCltIdList(commCltIdList);
			form.setCommCltNameList(commCltNameList);
			form.setCommCountyIdList(commCountyIdList);
			form.setCommCountyNameList(commCountyNameList);
			form.setCommCityIdList(commCityIdList);
			form.setCommCityNameList(commCityNameList);
			form.setCommProvinceIdList(commProvinceIdList);
			form.setCommProvinceNameList(commProvinceNameList);
			form.setCommClvIdList(commClvIdList);
			form.setCommClvNameList(commClvNameList);
		}
	}

	
	public void getTowns(CommConfigLocationGroupForm form) {
		String[] tempIds = null;
		String[] tempNames = null;
		List<?> list = null;
//		commCltIds & commCltNames;
		if(form.getCommCountyId() != null && form.getCommCountyId().trim().length() > 0){
			list = this.getCommConfigLocationGroupDAO().getTownsByParent(form.getCommCountyId().trim());
			if(list != null && list.size() > 0){
				tempIds = new String[list.size()+1];
				tempNames = new String[list.size()+1];
				tempIds[0] = "";
				tempNames[0] = "";
				for(int i=0; i<list.size(); i++){
					CommConfigLocationTown town = (CommConfigLocationTown)list.get(i);
					tempIds[i+1] = this.transNullToString(town.getId());
					tempNames[i+1] = this.transNullToString(town.getItemName());
				}
				form.setCommCltIds(tempIds);
				form.setCommCltNames(tempNames);
			}
		}
	}

	
	public void getVillages(CommConfigLocationGroupForm form) {
		String[] tempIds = null;
		String[] tempNames = null;
		List<?> list = null;
//		commClvIds & commClvNames;
		if(form.getCommCltId() != null && form.getCommCltId().trim().length() > 0){
			list = this.getCommConfigLocationGroupDAO().getVillagesByParent(form.getCommCltId().trim());
			if(list != null && list.size() > 0){
				tempIds = new String[list.size()+1];
				tempNames = new String[list.size()+1];
				tempIds[0] = "";
				tempNames[0] = "";
				for(int i=0; i<list.size(); i++){
					CommConfigLocationVillage town = (CommConfigLocationVillage)list.get(i);
					tempIds[i+1] = this.transNullToString(town.getId());
					tempNames[i+1] = this.transNullToString(town.getItemName());
				}
				form.setCommClvIds(tempIds);
				form.setCommClvNames(tempNames);
			}
		}
	}

	
	public void setForm(CommConfigLocationGroupForm form, CommConfigLocationGroup data) {
		form.setCommClvId(this.transNullToString(data.getCommClvId()));
		form.setComments(this.transNullToString(data.getComments()));
		form.setId(this.transNullToString(data.getId()));
		form.setInputCode(this.transNullToString(data.getInputCode()));
		form.setItemCode(this.transNullToString(data.getItemCode()));
		form.setItemName(this.transNullToString(data.getItemName()));
		if(data.getSeqNo()==null)
			form.setSeqNo("");
		else
			form.setSeqNo(this.transNullToString(data.getSeqNo()));
	}

	
	public void update(CommConfigLocationGroupForm form, HttpServletRequest request) {
		CommConfigLocationGroup data = this.getCommConfigLocationGroupDAO().findByItemCode(form.getItemCode());
		this.setData(form, data);
		this.getCommConfigLocationGroupDAO().update(data);
		String message = ResourcesUtil.getValue("conf.comm.CommLocale", "comm.java.commom.updateSuccess", request) + "!";
		form.setMessage(message);
	}

	
	public void updateInit(CommConfigLocationGroupForm form) {
		CommConfigLocationGroup data = this.getCommConfigLocationGroupDAO().findById(form.getIdHidden());
		this.setForm(form, data);
		this.getDetail(form);
		this.init(form);		
	}
	//--------------------------------------------------------------
	private String transNullToString(Object obj) {
		String temp = "";
		if (obj != null) {
			temp = String.valueOf(obj).trim();
		}
		return temp;
	}
	private void setData(CommConfigLocationGroupForm form, CommConfigLocationGroup data) {
		data.setCommClvId(this.transNullToString(form.getCommClvId()));
		data.setComments(this.transNullToString(form.getComments()));
//		data.setId(this.transNullToString(form.getId()));
		data.setInputCode(this.transNullToString(form.getInputCode()));
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
	}
	private void init(CommConfigLocationGroupForm form) {
		this.getDict(form);
	}
	private void getDict(CommConfigLocationGroupForm form) {
		String[] tempIds = null;
		String[] tempNames = null;
		List<?> list = null;
//		commProvinceIds & commProvinceNames;
		list = this.getCommConfigLocationGroupDAO().getByParent("", "1");
		if(list != null && list.size() > 0){
			tempIds = new String[list.size()+1];
			tempNames = new String[list.size()+1];
			tempIds[0] = "";
			tempNames[0] = "";
			for(int i=0; i<list.size(); i++){
				CommConfigLocation location = (CommConfigLocation)list.get(i);
				tempIds[i+1] = this.transNullToString(location.getId());
				tempNames[i+1] = this.transNullToString(location.getItemName());
			}
			form.setCommProvinceIds(tempIds);
			form.setCommProvinceNames(tempNames);
		}
//		commCityIds & commCityNames;
		tempIds = null;
		tempNames = null;
		list = null;
		if(form.getCommProvinceId() != null && form.getCommProvinceId().trim().length() > 0){
			list = this.getCommConfigLocationGroupDAO().getByParent(form.getCommProvinceId().trim(), "2");
			if(list != null && list.size() > 0){
				tempIds = new String[list.size()+1];
				tempNames = new String[list.size()+1];
				tempIds[0] = "";
				tempNames[0] = "";
				for(int i=0; i<list.size(); i++){
					CommConfigLocation location = (CommConfigLocation)list.get(i);
					tempIds[i+1] = this.transNullToString(location.getId());
					tempNames[i+1] = this.transNullToString(location.getItemName());
				}
				form.setCommCityIds(tempIds);
				form.setCommCityNames(tempNames);
			}
		}		
//		commCountyIds & commCountyNames;
		tempIds = null;
		tempNames = null;
		list = null;
		if(form.getCommCityId() != null && form.getCommCityId().trim().length() > 0){
			list = this.getCommConfigLocationGroupDAO().getByParent(form.getCommCityId().trim(), "3");
			if(list != null && list.size() > 0){
				tempIds = new String[list.size()+1];
				tempNames = new String[list.size()+1];
				tempIds[0] = "";
				tempNames[0] = "";
				for(int i=0; i<list.size(); i++){
					CommConfigLocation location = (CommConfigLocation)list.get(i);
					tempIds[i+1] = this.transNullToString(location.getId());
					tempNames[i+1] = this.transNullToString(location.getItemName());
				}
				form.setCommCountyIds(tempIds);
				form.setCommCountyNames(tempNames);
			}
		}
//		commCltIds & commCltNames;
		tempIds = null;
		tempNames = null;
		list = null;
		if(form.getCommCountyId() != null && form.getCommCountyId().trim().length() > 0){
			list = this.getCommConfigLocationGroupDAO().getTownsByParent(form.getCommCountyId().trim());
			if(list != null && list.size() > 0){
				tempIds = new String[list.size()+1];
				tempNames = new String[list.size()+1];
				tempIds[0] = "";
				tempNames[0] = "";
				for(int i=0; i<list.size(); i++){
					CommConfigLocationTown town = (CommConfigLocationTown)list.get(i);
					tempIds[i+1] = this.transNullToString(town.getId());
					tempNames[i+1] = this.transNullToString(town.getItemName());
				}
				form.setCommCltIds(tempIds);
				form.setCommCltNames(tempNames);
			}
		}
//		commClvIds & commClvNames;
		tempIds = null;
		tempNames = null;
		list = null;
		if(form.getCommCltId() != null && form.getCommCltId().trim().length() > 0){
			list = this.getCommConfigLocationGroupDAO().getVillagesByParent(form.getCommCltId().trim());
			if(list != null && list.size() > 0){
				tempIds = new String[list.size()+1];
				tempNames = new String[list.size()+1];
				tempIds[0] = "";
				tempNames[0] = "";
				for(int i=0; i<list.size(); i++){
					CommConfigLocationVillage town = (CommConfigLocationVillage)list.get(i);
					tempIds[i+1] = this.transNullToString(town.getId());
					tempNames[i+1] = this.transNullToString(town.getItemName());
				}
				form.setCommClvIds(tempIds);
				form.setCommClvNames(tempNames);
			}
		}
	}

}
