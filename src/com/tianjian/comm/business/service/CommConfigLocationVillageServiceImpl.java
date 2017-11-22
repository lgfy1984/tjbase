package com.tianjian.comm.business.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tianjian.comm.bean.CommConfigLocation;
import com.tianjian.comm.bean.CommConfigLocationTown;
import com.tianjian.comm.bean.CommConfigLocationVillage;
import com.tianjian.comm.business.ICommConfigLocationVillageService;
import com.tianjian.comm.dao.ICommConfigLocationVillageDAO;
import com.tianjian.comm.struts.form.CommConfigLocationVillageForm;
import com.tianjian.comm.struts.servlet.CommConfigLocationTownInit;
import com.tianjian.util.ResourcesUtil;
/**
 * COMM_CONFIG_LOCATION_VILLAGE村字典用Service
 * @author Dzenall
 * @since 2008-9-18
 */
public class CommConfigLocationVillageServiceImpl implements ICommConfigLocationVillageService {

	private String flag = CommConfigLocationTownInit.getProperty("PRINT_FLAG");
	private String comm_itemCode = CommConfigLocationTownInit.getProperty("COMM_ITEMCODE");
	private ICommConfigLocationVillageDAO commConfigLocationVillageDAO;
	public ICommConfigLocationVillageDAO getCommConfigLocationVillageDAO() {
		return commConfigLocationVillageDAO;
	}

	public void setCommConfigLocationVillageDAO(
			ICommConfigLocationVillageDAO commConfigLocationVillageDAO) {
		this.commConfigLocationVillageDAO = commConfigLocationVillageDAO;
	}
	//------------------------------------------------------------------------------

	
	public void add(CommConfigLocationVillageForm form, HttpServletRequest request) {
		System.out.println(form.getContactPersonName());
		CommConfigLocationVillage data = new CommConfigLocationVillage();
		this.setData(form, data, "add");
		this.getCommConfigLocationVillageDAO().save(data);
		String message = ResourcesUtil.getValue("conf.comm.CommLocale", "comm.java.commom.newAddSuccess", request) + "!";
		form.setMessage(message);
	}

	
	public void addInit(CommConfigLocationVillageForm form) {
		form.setCommProvinceId(CommConfigLocationTownInit.getProperty("INIT_PROVINCE_IC"));
		form.setCommCityId(CommConfigLocationTownInit.getProperty("INIT_CITY_IC"));
		form.setCommCountyId(CommConfigLocationTownInit.getProperty("INIT_COUNTY_IC"));
		form.setSeqNo(this.transNullToString(this.getCommConfigLocationVillageDAO().seqNoMaker()));
		this.init(form);
	}


	
	public CommConfigLocationVillage checkData(String itemCode) {
		CommConfigLocationVillage data = this.getCommConfigLocationVillageDAO().findByItemCode(itemCode);
		return data;
	}

	
	public void delete(CommConfigLocationVillageForm form, HttpServletRequest request) {
		CommConfigLocationVillage data = this.getCommConfigLocationVillageDAO().findById(form.getIdHidden());
		this.getCommConfigLocationVillageDAO().delete(data);
		String message = ResourcesUtil.getValue("conf.comm.CommLocale", "comm.java.commom.deleteSuccess", request) + "!";
		form.setMessage(message);
	}

	
	public void getCitys(CommConfigLocationVillageForm form) {
		String[] tempIds = null;
		String[] tempNames = null;
		List<?> list = null;
//		commCityIds & commCityNames;
		if(form.getCommProvinceId() != null && form.getCommProvinceId().trim().length() > 0){
			list = this.getCommConfigLocationVillageDAO().getByParent(form.getCommProvinceId().trim(), "2");
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
		return this.getCommConfigLocationVillageDAO().getCount(itemCode, itemName, inputCode);
	}

	
	public void getCounties(CommConfigLocationVillageForm form) {
		String[] tempIds = null;
		String[] tempNames = null;
		List<?> list = null;	
//		commCountyIds & commCountyNames;
		if(form.getCommCityId() != null && form.getCommCityId().trim().length() > 0){
			list = this.getCommConfigLocationVillageDAO().getByParent(form.getCommCityId().trim(), "3");
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
	//定义行政村编号
	public String getItemCode(CommConfigLocationVillageForm form){
		String cmi=null;
		if(form.getCommCltId() != null && form.getCommCltId().trim().length() > 0){
			String temp = this.commConfigLocationVillageDAO.getItemCode(form.getCommCltId().trim());
			if(Long.valueOf(temp)>0){				
				cmi=comm_itemCode.substring(0, 1)+String.valueOf(Long.valueOf(temp));
			}
			if(Long.valueOf(temp)>10){				
				cmi=String.valueOf(Long.valueOf(temp));
			}

		}	
		return cmi;
	}
	//得到乡镇编号
	public String getTownItemCode(CommConfigLocationVillageForm form){
		String temp=null;
		if(form.getCommCltId() != null && form.getCommCltId().trim().length() > 0){
			temp = this.commConfigLocationVillageDAO.getTownItemCode(form.getCommCltId().trim());			
		}	
		return temp;
	}
	
	public void getDetail(CommConfigLocationVillageForm form) {
//		commCltId -> commCountyId;//������Id
		form.setCommCountyId(this.transNullToString(this.getCommConfigLocationVillageDAO().findValueByProp("CommConfigLocationTown", "commConfigLocationId", "id", form.getCommCltId())));
//		commCountyId -> commCityId//������Id
		form.setCommCityId(this.transNullToString(this.getCommConfigLocationVillageDAO().findValueByProp("CommConfigLocation", "parentId", "id", form.getCommCountyId())));
//		commCityId -> commProvinceId//����ʡId
		form.setCommProvinceId(this.transNullToString(this.getCommConfigLocationVillageDAO().findValueByProp("CommConfigLocation", "parentId", "id", form.getCommCityId())));

//		commCltId -> commCltName;//�����������
		form.setCommCltName(this.transNullToString(this.getCommConfigLocationVillageDAO().findValueByProp("CommConfigLocationTown", "itemName", "id", form.getCommCltId())));
//		commCountyId -> commCountyName;//���������
		form.setCommCountyName(this.transNullToString(this.getCommConfigLocationVillageDAO().findValueByProp("CommConfigLocation", "itemName", "id", form.getCommCountyId())));
//		commCityId -> commCityName//���������
		form.setCommCityName(this.transNullToString(this.getCommConfigLocationVillageDAO().findValueByProp("CommConfigLocation", "itemName", "id", form.getCommCityId())));
//		commProvinceId -> commProvinceName;//����ʡ���
		form.setCommProvinceName(this.transNullToString(this.getCommConfigLocationVillageDAO().findValueByProp("CommConfigLocation", "itemName", "id", form.getCommProvinceId())));
	    //增加字段
		/*String zongrenshu=this.transNullToString(this.getCommConfigLocationVillageDAO().findValueByProp("CommConfigLocationVillage", "villagerNum", "id", form.getIdHidden()));
		form.setVillagerNum(zongrenshu.equals(0.0)?"":zongrenshu);
		String jiatingshu=this.transNullToString(this.getCommConfigLocationVillageDAO().findValueByProp("CommConfigLocationVillage", "familyNum", "id", form.getIdHidden()));
		form.setFamilyNum(jiatingshu==null?"":jiatingshu);
	    String liangxiren=this.transNullToString(this.getCommConfigLocationVillageDAO().findValueByProp("CommConfigLocationVillage", "contactPersonName", "id", form.getIdHidden()));
		form.setContactPersonName(liangxiren==null ?"":liangxiren);
	    String phone=this.transNullToString(this.getCommConfigLocationVillageDAO().findValueByProp("CommConfigLocationVillage", "phohe", "id", form.getIdHidden()));
	    form.setPhohe(phone.equals(null)?"":phone);*/
	    //form.setPhohe(this.transNullToString(this.getCommConfigLocationVillageDAO().findValueByProp("CommConfigLocationVillage", "phohe", "id", form.getIdHidden())));
	}

	
	public void getSearch(CommConfigLocationVillageForm form, int curCount, int pageSize) {
		// �б������itemCode�����itemName��������inputCode�������commCltName
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
		List<?> list = this.getCommConfigLocationVillageDAO().getData(form.getItemCode(), form.getItemName(), form.getInputCode(), order, curCount, pageSize);
		if (list != null && list.size() > 0) {
			String[] idList = new String[list.size()];
			String[] seqNoList = new String[list.size()];
			String[] itemCodeList = new String[list.size()];
			String[] itemNameList = new String[list.size()];
			String[] inputCodeList = new String[list.size()];
			String[] commentsList = new String[list.size()];
			String[] commCltIdList = new String[list.size()];
			String[] commCltNameList = new String[list.size()];

			String[] commProvinceIdList = new String[list.size()];
			String[] commCityIdList = new String[list.size()];
			String[] commCountyIdList = new String[list.size()];
			String[] commProvinceNameList = new String[list.size()];
			String[] commCityNameList = new String[list.size()];
			String[] commCountyNameList = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				idList[i] 					= transNullToString(((Object[]) list.get(i))[0]);
				seqNoList[i] 				= transNullToString(((Object[]) list.get(i))[1]);
				itemCodeList[i] 			= transNullToString(((Object[]) list.get(i))[2]);
				itemNameList[i] 			= transNullToString(((Object[]) list.get(i))[3]);
				inputCodeList[i] 			= transNullToString(((Object[]) list.get(i))[4]);
				commentsList[i] 			= transNullToString(((Object[]) list.get(i))[5]);
				commCltIdList[i] 			= transNullToString(((Object[]) list.get(i))[6]);
				commCltNameList[i] 			= transNullToString(((Object[]) list.get(i))[7]);

				commCountyIdList[i] 		= transNullToString(this.getCommConfigLocationVillageDAO().findValueByProp("CommConfigLocationTown", "commConfigLocationId", "itemCode", commCltIdList[i]));
				commCityIdList[i] 			= transNullToString(this.getCommConfigLocationVillageDAO().findValueByProp("CommConfigLocation", "parentId", "id", commCountyIdList[i]));
				commProvinceIdList[i] 		= transNullToString(this.getCommConfigLocationVillageDAO().findValueByProp("CommConfigLocation", "parentId", "id", commCityIdList[i]));				

				commProvinceNameList[i] 	= transNullToString(this.getCommConfigLocationVillageDAO().findValueByProp("CommConfigLocation", "itemName", "id", commProvinceIdList[i]));
				commCityNameList[i] 		= transNullToString(this.getCommConfigLocationVillageDAO().findValueByProp("CommConfigLocation", "itemName", "id", commCityIdList[i]));
				commCountyNameList[i] 		= transNullToString(this.getCommConfigLocationVillageDAO().findValueByProp("CommConfigLocation", "itemName", "id", commCountyIdList[i]));	
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
		}
	}

	
	public void getTowns(CommConfigLocationVillageForm form) {
		String[] tempIds = null;
		String[] tempNames = null;
		List<?> list = null;
//		commCltIds & commCltNames;
		if(form.getCommCountyId() != null && form.getCommCountyId().trim().length() > 0){
			list = this.getCommConfigLocationVillageDAO().getByParent(form.getCommCountyId().trim());
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

	
	public void setForm(CommConfigLocationVillageForm form, CommConfigLocationVillage data) {
		form.setCommCltId(this.transNullToString(data.getCommCltId()));
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

	
	public void setForm2(CommConfigLocationVillageForm form) {
		// TODO Auto-generated method stub
		
		try {
			CommConfigLocationVillage data=this.getCommConfigLocationVillageDAO().findById(form.getIdHidden());
			if(data.getVillagerNum()!=null){
			form.setVillagerNum((data.getVillagerNum()==0L)?"":this.transNullToString(data.getVillagerNum()));
			form.setFamilyNum((data.getFamilyNum()==0L)?"":this.transNullToString(data.getFamilyNum()));
			
			form.setPhohe(data.getPhohe().equals(null)?"":this.transNullToString(data.getPhohe()));
            form.setContactPersonName(data.getContactPersonName().equals(null)?"":this.transNullToString(data.getContactPersonName()));
			}
			} catch (RuntimeException e) { 
			if(flag.equalsIgnoreCase("true"))
			{e.printStackTrace();}
		}
	}
	
	
	
	
	public void update(CommConfigLocationVillageForm form) {
		CommConfigLocationVillage data = this.getCommConfigLocationVillageDAO().findById(form.getId());
		this.setData(form, data, "update");
		this.getCommConfigLocationVillageDAO().update(data);
	}

	
	public void updateInit(CommConfigLocationVillageForm form) {
		CommConfigLocationVillage data = this.getCommConfigLocationVillageDAO().findById(form.getIdHidden());
		this.setForm(form, data);
		this.getDetail(form);
		this.init(form);		
	}

	//-------------------------------------------------------------------------------------------
	private void setData(CommConfigLocationVillageForm form, CommConfigLocationVillage data, String verbId) {
		data.setCommCltId(this.transNullToString(form.getCommCltId()));
		
		data.setComments(this.transNullToString(form.getComments()));
 		data.setId(this.transNullToString(form.getId()));
		data.setComments(this.transNullToString(form.getComments()));	
		if(verbId=="add"){
		data.setItemCode(this.transNullToString(form.getItemCode()));
		}
		data.setItemName(this.transNullToString(form.getItemName()));
		data.setInputCode(this.transNullToString(form.getInputCode()));
		//添加字段 
		if(verbId=="add" && form.getVillagerNum()!=null && !form.getVillagerNum().equals("") ){
		data.setVillagerNum(Long.valueOf(form.getVillagerNum()));
		data.setFamilyNum(Long.valueOf(form.getFamilyNum()));
		}
		
		if(verbId=="update" && form.getVillagerNum()!=null && !form.getVillagerNum().equals("") ){
			data.setVillagerNum(Long.valueOf(form.getVillagerNum()));
			data.setFamilyNum(Long.valueOf(form.getFamilyNum())); 
			}
		data.setContactPersonName(this.transNullToString(form.getContactPersonName()));
		data.setPhohe(this.transNullToString(form.getPhohe()));		
		try{
			data.setSeqNo(Long.valueOf(this.transNullToString(form.getSeqNo())));
		}catch(Exception e){
			if(flag.equalsIgnoreCase("true")){
				e.printStackTrace();
			}
			//data.setSeqNo(0L);
		}
		if(verbId.equalsIgnoreCase("add")){

			data.setInputCode(this.transNullToString(form.getInputCode()));
		}
	}
	private String transNullToString(Object obj) {
		String temp = "";
		if (obj != null) {
			temp = String.valueOf(obj).trim();
		}
		return temp;
	}

	private void init(CommConfigLocationVillageForm form) {
		this.getDict(form);
	}

	private void getDict(CommConfigLocationVillageForm form) {
		String[] tempIds = null;
		String[] tempNames = null;
		List<?> list = null;
//		commProvinceIds & commProvinceNames;
		list = this.getCommConfigLocationVillageDAO().getByParent("", "1");
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
			list = this.getCommConfigLocationVillageDAO().getByParent(form.getCommProvinceId().trim(), "2");
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
			list = this.getCommConfigLocationVillageDAO().getByParent(form.getCommCityId().trim(), "3");
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
			list = this.getCommConfigLocationVillageDAO().getByParent(form.getCommCountyId().trim());
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

	
}
