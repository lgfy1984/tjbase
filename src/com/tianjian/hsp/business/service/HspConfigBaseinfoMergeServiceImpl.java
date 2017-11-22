package com.tianjian.hsp.business.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.tianjian.comm.bean.CommConfigEconkind;
import com.tianjian.comm.bean.CommConfigFtManage;
import com.tianjian.comm.bean.CommConfigLocation;
import com.tianjian.comm.bean.CommConfigLocationTown;
import com.tianjian.comm.bean.CommConfigLocationVillage;
import com.tianjian.comm.bean.CommConfigSettype;
import com.tianjian.comm.bean.CommConfigUnitgrade;
import com.tianjian.comm.bean.CommConfigUnittype;
import com.tianjian.comm.bean.CommDictPublicChar;
import com.tianjian.comm.dao.ICommConfigLocationDAO;
import com.tianjian.hsp.bean.HspConfigBaseinfo;
import com.tianjian.hsp.bean.HspIdList;
import com.tianjian.hsp.bean.HspMergeLog;
import com.tianjian.hsp.business.IHspConfigBaseinfoMergeService;
import com.tianjian.hsp.business.IHspConfigBaseinfoService;
import com.tianjian.hsp.dao.IHspConfigBaseinfoDAO;
import com.tianjian.hsp.dao.IHspConfigBaseinfoMergeDAO;
import com.tianjian.hsp.struts.form.HspConfigBaseinfoForm;
import com.tianjian.hsp.struts.form.HspConfigBaseinfoMergeForm;
import com.tianjian.security.bean.SecurityDataObjectType;
import com.tianjian.security.bean.SecurityDataObjectVsRoles;
import com.tianjian.security.struts.servlet.BaseSecurityInit;
import com.tianjian.util.Converter;

public class HspConfigBaseinfoMergeServiceImpl implements IHspConfigBaseinfoMergeService{
	
    private IHspConfigBaseinfoMergeDAO hspConfigBaseinfoMergeDAO;
	
    private IHspConfigBaseinfoDAO hspConfigBaseinfoDAO;
	
	private ICommConfigLocationDAO commConfigLocationDAO;

	public ICommConfigLocationDAO getCommConfigLocationDAO() {
		return commConfigLocationDAO;
	}

	public void setCommConfigLocationDAO(
			ICommConfigLocationDAO commConfigLocationDAO) {
		this.commConfigLocationDAO = commConfigLocationDAO;
	}

	public IHspConfigBaseinfoMergeDAO getHspConfigBaseinfoMergeDAO() {
		return hspConfigBaseinfoMergeDAO;
	}

	public void setHspConfigBaseinfoMergeDAO(
			IHspConfigBaseinfoMergeDAO hspConfigBaseinfoMergeDAO) {
		this.hspConfigBaseinfoMergeDAO = hspConfigBaseinfoMergeDAO;
	}
	
	public IHspConfigBaseinfoDAO getHspConfigBaseinfoDAO() {
		return hspConfigBaseinfoDAO;
	}

	public void setHspConfigBaseinfoDAO(IHspConfigBaseinfoDAO hspConfigBaseinfoDAO) {
		this.hspConfigBaseinfoDAO = hspConfigBaseinfoDAO;
	}

	public int getCount(HspConfigBaseinfoMergeForm hcbmForm) {
		return this.hspConfigBaseinfoMergeDAO.getCount(hcbmForm.getIdHidden(),hcbmForm.getItemName(),hcbmForm.getCommConfigLocationId1(),hcbmForm.getCommConfigLocationId2(),
				hcbmForm.getCommConfigLocationId3(),hcbmForm.getInputCode(),hcbmForm.getCheckValue());
	}

	@Override
	public void mgInit(HspConfigBaseinfoMergeForm hcbmForm) {
		String[] checkValues = hcbmForm.getCheckValue().split(",");

		List<String>  cpcl = new ArrayList<String>();
		for(int i=0; i<checkValues.length;i++){
		  if(!checkValues[i].equals("")){
				cpcl.add(checkValues[i]);
		   }
		 }
      
		cpcl = this.removedup(cpcl);
		this.mgBegin(cpcl,hcbmForm);
	}
	
	/**合并开始*/
	private void mgBegin(List<String> cpcl, HspConfigBaseinfoMergeForm hcbmForm) {
		List<String> list = new ArrayList<String>();
		for(int k=0;k<cpcl.size();k++){
			String id=String.valueOf(cpcl.get(k));
			String[] ids=id.split(":");
			String oldId=ids[0];
			String newId=ids[1];
			HspConfigBaseinfo oldVo = this.hspConfigBaseinfoMergeDAO.getVoById(oldId);
			if(list.size()==0){
				this.dealResult(newId,oldId);
			}	
			else{
				int m=0;
				for(int i=0;i<list.size();i++){
					if(oldId.equals(list.get(i))){
						break;
					}else{
						m++;
					}
				}
				if(m>0){
					this.dealResult(newId,oldId);
				}
			}
			list.add(oldId);
			this.saveMerge(newId, oldId, hcbmForm,oldVo);
		}
	}

	private void saveMerge(String newId, String oldId,
			HspConfigBaseinfoMergeForm hcbmForm,HspConfigBaseinfo oldVo) {
		HspMergeLog vo = new HspMergeLog();
		
		vo.setId(UUID.randomUUID().toString().replace("-", "")); 
		vo.setNewHspConfigBaseinfoId(newId);
		if(oldVo.getDomainName()!=null&&oldVo.getDomainName().trim().length()>0){
			vo.setUniversalId(oldVo.getDomainName());
		}
		
//		HspConfigBaseinfo newVo = this.hspConfigBaseinfoMergeDAO.getVoById(newId);
		vo.setOldHspConfigBaseinfoId(oldId);
		
		vo.setCreateDate(new Date());
		vo.setCreateUserId(hcbmForm.getCreateUserId());
		vo.setCreateUserName(hcbmForm.getCreateUserName());
		
		this.hspConfigBaseinfoMergeDAO.saveLog(vo);
		
	}

	private void dealResult(String newId,String oldId) {
		HspConfigBaseinfoForm hForm = new HspConfigBaseinfoForm();
		hForm.setId(oldId);
		hForm.setIdHidden(oldId);
		this.delete(hForm);
		HspIdList vo = this.hspConfigBaseinfoMergeDAO.getHspIdListById(oldId);
		if(vo!=null&&vo.getId()!=null){
			vo.setHspConfigBaseinfoId(newId);
			this.hspConfigBaseinfoMergeDAO.updataHspIdList(vo);
		}
		
	}

	/**去除重复ID*/
	private List<String> removedup(List<String>  cpcl){
		
	    for  (int  i = 0 ; i<cpcl.size()  - 1 ; i ++ )  {
          for  ( int  j  =  cpcl.size() - 1 ; j  >  i; j -- )  {
               if  (cpcl.get(j).equals(cpcl.get(i)))  {
            	   cpcl.remove(j);
                } 
            } 
         } 
         return cpcl;
	}

	public void search(HspConfigBaseinfoMergeForm hcbmForm, int count,
			int pageSize) {
		String order = "";
		if(hcbmForm.getOrderNo().equals("1")){
			order = " a.itemCode";
		}else if(hcbmForm.getOrderNo().equals("2")){
			order = " a.itemName";
		}else if(hcbmForm.getOrderNo().equals("3")){
			order = " a.commConfigLocationId1,a.commConfigLocationId2,a.commConfigLocationId3";
		}else if(hcbmForm.getOrderNo().equals("4")){
			order = " a.seqNo";
		}else if(hcbmForm.getOrderNo().equals("5")){
			order = " a.inputCode";
		}else{
			order = " a.id";
		}
		if(hcbmForm.getAsc().equals("1")){
			order += " desc";
		}else{
			order += " asc";
		}
		
		List<?> voList = this.hspConfigBaseinfoMergeDAO.search(hcbmForm.getIdHidden(),hcbmForm.getItemName(),hcbmForm.getCommConfigLocationId1(),hcbmForm.getCommConfigLocationId2(),
				hcbmForm.getCommConfigLocationId3(),hcbmForm.getInputCode(),hcbmForm.getCheckValue(),order,count,pageSize);
		int l = voList.size();
		String[] idArray = new String[l];
		String[] itemCodeArray = new String[l];
		String[] itemNameArray = new String[l];
		String[] itemAddressArray = new String[l];
		String[] commConfigLocationId1s = new String[l];
		String[] commConfigLocationId2s = new String[l];
		String[] commConfigLocationId3s = new String[l];
		String[] commConfigLocationId1_names = new String[l];
		String[] commConfigLocationId2_names = new String[l];
		String[] commConfigLocationId3_names = new String[l];
		String[] inputCodeArray = new String[l];
		String[] seqNoArray = new String[l];
		for(int i = 0;i<l;i++){
			HspConfigBaseinfo vo = (HspConfigBaseinfo)voList.get(i);
			idArray[i] = trantNull(vo.getId());
			itemCodeArray[i] = trantNull(vo.getItemCode());
			itemNameArray[i] = trantNull(vo.getItemName());
			
			commConfigLocationId1s[i] = trantNull(vo.getId());
            if(vo.getCommConfigLocationId1()!=null&&vo.getCommConfigLocationId1().trim().length()>0){
            	commConfigLocationId1_names[i] = trantNull(this.commConfigLocationDAO.getItemName(vo.getCommConfigLocationId1()));
			}
            commConfigLocationId2s[i] = trantNull(vo.getId());
            if(vo.getCommConfigLocationId2()!=null&&vo.getCommConfigLocationId2().trim().length()>0){
            	commConfigLocationId2_names[i] = trantNull(this.commConfigLocationDAO.getItemName(vo.getCommConfigLocationId2()));
			}
            commConfigLocationId3s[i] = trantNull(vo.getId());
            if(vo.getCommConfigLocationId3()!=null&&vo.getCommConfigLocationId3().trim().length()>0){
            	commConfigLocationId3_names[i] = trantNull(this.commConfigLocationDAO.getItemName(vo.getCommConfigLocationId3()));
			}
            
            itemAddressArray[i] = commConfigLocationId1_names[i]+commConfigLocationId2_names[i]+commConfigLocationId3_names[i];
            
            inputCodeArray[i] = trantNull(vo.getInputCode());
            if(vo.getSeqNo()!=null){
            	seqNoArray[i] = vo.getSeqNo().toString();
            }else{
            	seqNoArray[i] = "";
            }
        }
		hcbmForm.setIdArray(idArray);
		hcbmForm.setItemCodeArray(itemCodeArray);
		hcbmForm.setItemNameArray(itemNameArray);
		hcbmForm.setItemAddressArray(itemAddressArray);
		hcbmForm.setInputCodeArray(inputCodeArray);
		hcbmForm.setSeqNoArray(seqNoArray);
	}
	
	private String trantNull(String value) {
		if(value!=null&&value.trim().length()>0){
			return value;
		}
		return "";
	}

	public void init(HspConfigBaseinfoMergeForm hcbmForm,
			HttpServletRequest request, String staffId) {
		//增加数据之前设置默认值：默认的省、市、县
		hcbmForm.setCommConfigLocationId1(BaseSecurityInit.getProperty("INIT_PROVINCE"));
		hcbmForm.setCommConfigLocationId2(BaseSecurityInit.getProperty("INIT_CITY"));
		hcbmForm.setCommConfigLocationId3(BaseSecurityInit.getProperty("INIT_COUNTY"));
		
		this.getDict(hcbmForm);
		
		/** 所属省 */
		hcbmForm.setCommConfigLocationId1s(this.getCommConfigLocationId1());
		hcbmForm.setCommConfigLocationId1_names(this.getCommConfigLocationName1());
		/** 所属市 */
		hcbmForm.setCommConfigLocationId2s(this.getCommConfigLocationId2());
		hcbmForm.setCommConfigLocationId2_names(this.getCommConfigLocationName2());
		/** 所属县 */
		hcbmForm.setCommConfigLocationId3s(this.getCommConfigLocationId3());
		hcbmForm.setCommConfigLocationId3_names(this.getCommConfigLocationName3());
	}
	
	public void delete(HspConfigBaseinfoForm form){
		hspConfigBaseinfoDAO.delete(hspConfigBaseinfoDAO.getById(form.getIdHidden()));
	}
	
	public void getDict(HspConfigBaseinfoMergeForm form){
		List<?> commConfigLocationId1_data = hspConfigBaseinfoDAO.getByParent("", "1");
		List<?> commConfigLocationId2_data = null;
		if(form.getCommConfigLocationId1()!= null && form.getCommConfigLocationId1().trim().length()>0){
			commConfigLocationId2_data = hspConfigBaseinfoDAO.getByParent(form.getCommConfigLocationId1(), "2");
		}
		List<?> commConfigLocationId3_data = null;
		if(form.getCommConfigLocationId2()!=null && form.getCommConfigLocationId2().trim().length()>0){
			commConfigLocationId3_data = hspConfigBaseinfoDAO.getByParent(form.getCommConfigLocationId2(), "3");
		}		
		
		String[] tempId = null;
		String[] tempName = null;
		// ----------------------省----------------------------------------------------
		tempId = null;
		tempName = null;
		if(commConfigLocationId1_data != null){
			tempId = new String[commConfigLocationId1_data.size()+1];
			tempName = new String[commConfigLocationId1_data.size()+1];
			tempId[0] = "";
			tempName[0] = "";
			for(int i=0; i<commConfigLocationId1_data.size(); i++){
				CommConfigLocation data =(CommConfigLocation) commConfigLocationId1_data.get(i);
				tempId[i+1] = Converter.toBlank(data.getItemCode());
				tempName[i+1] = Converter.toBlank(data.getItemName());
			}
		}
		this.setCommConfigLocationId1(tempId);
		this.setCommConfigLocationName1(tempName);
		// ----------------------市----------------------------------------------------
		tempId = null;
		tempName = null;
		if(commConfigLocationId2_data!=null){
			tempId = new String[commConfigLocationId2_data.size()+1];
			tempName = new String[commConfigLocationId2_data.size()+1];
			tempId[0] = "";
			tempName[0] = "";
			for(int i=0; i<commConfigLocationId2_data.size(); i++){
				CommConfigLocation data =(CommConfigLocation) commConfigLocationId2_data.get(i);
				tempId[i+1] = Converter.toBlank(data.getItemCode());
				tempName[i+1] = Converter.toBlank(data.getItemName());
			}
		}else{
			// --------------如果为空，插入空格-----------
			tempId = new String[1];
			tempName = new String[1];
			tempId[0] = "";
			tempName[0] = "";
		}
		this.setCommConfigLocationId2(tempId);
		this.setCommConfigLocationName2(tempName);
		// ----------------------县----------------------------------------------------
		tempId = null;
		tempName = null;
		if(commConfigLocationId3_data!=null){
			tempId = new String[commConfigLocationId3_data.size() + 1];
			tempName = new String[commConfigLocationId3_data.size() + 1];
			tempId[0] = "";
			tempName[0] = "";
			for(int i=0; i<commConfigLocationId3_data.size(); i++){
				CommConfigLocation data =(CommConfigLocation) commConfigLocationId3_data.get(i);
				tempId[i+1] = Converter.toBlank(data.getItemCode());
				tempName[i+1] = Converter.toBlank(data.getItemName());
			}
		}else{
			// --------------如果为空，插入空格-----------
			tempId = new String[1];
			tempName = new String[1];
			tempId[0] = "";
			tempName[0] = "";
		} 
		this.setCommConfigLocationId3(tempId);
		this.setCommConfigLocationName3(tempName);
	}
	
	private String[] commConfigEconkindName;
	private String[] commConfigLocationId1;
	private String[] commConfigLocationName1;
	private String[] commConfigLocationId2;
	private String[] commConfigLocationName2;
	private String[] commConfigLocationId3;
	private String[] commConfigLocationName3;

	public String[] getCommConfigEconkindName() {
		return commConfigEconkindName;
	}

	public void setCommConfigEconkindName(String[] commConfigEconkindName) {
		this.commConfigEconkindName = commConfigEconkindName;
	}

	public String[] getCommConfigLocationId1() {
		return commConfigLocationId1;
	}

	public void setCommConfigLocationId1(String[] commConfigLocationId1) {
		this.commConfigLocationId1 = commConfigLocationId1;
	}

	public String[] getCommConfigLocationName1() {
		return commConfigLocationName1;
	}

	public void setCommConfigLocationName1(String[] commConfigLocationName1) {
		this.commConfigLocationName1 = commConfigLocationName1;
	}

	public String[] getCommConfigLocationId2() {
		return commConfigLocationId2;
	}

	public void setCommConfigLocationId2(String[] commConfigLocationId2) {
		this.commConfigLocationId2 = commConfigLocationId2;
	}

	public String[] getCommConfigLocationName2() {
		return commConfigLocationName2;
	}

	public void setCommConfigLocationName2(String[] commConfigLocationName2) {
		this.commConfigLocationName2 = commConfigLocationName2;
	}

	public String[] getCommConfigLocationId3() {
		return commConfigLocationId3;
	}

	public void setCommConfigLocationId3(String[] commConfigLocationId3) {
		this.commConfigLocationId3 = commConfigLocationId3;
	}

	public String[] getCommConfigLocationName3() {
		return commConfigLocationName3;
	}

	public void setCommConfigLocationName3(String[] commConfigLocationName3) {
		this.commConfigLocationName3 = commConfigLocationName3;
	}

}
