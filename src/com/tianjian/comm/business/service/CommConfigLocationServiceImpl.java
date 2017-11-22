package com.tianjian.comm.business.service;


import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import com.tianjian.comm.bean.CommConfigLocation;
import com.tianjian.comm.business.ICommConfigLocationService;
import com.tianjian.comm.dao.ICommConfigLocationDAO;
import com.tianjian.comm.struts.form.CommConfigLocationForm;

public class CommConfigLocationServiceImpl implements ICommConfigLocationService {
	private static final Logger log = LogManager.getLogger(CommConfigLocationServiceImpl.class);
	private ICommConfigLocationDAO commConfigLocationDAO;
	//------------------------DAO-- 声明结束---------------------------------------------------
	
	
	public ICommConfigLocationDAO getCommConfigLocationDAO() {
		return commConfigLocationDAO;
	}

	
	public void setCommConfigLocationDAO(ICommConfigLocationDAO commConfigLocationDAO) {
		this.commConfigLocationDAO = commConfigLocationDAO;
	}

   /**新增之前的初始化，这里需要初始化字典库内容*/
	public void addInit(CommConfigLocationForm form){
		init(form);
	}
	/**查找之前的初始化，这里需要初始化字典库内容*/
	public void serchInit(CommConfigLocationForm form){
		searchInit(form);
	}
	/**获取记录对象*/
	public CommConfigLocation checkData(String id){
		CommConfigLocation data = commConfigLocationDAO.findById(id);
		return data;
	}
	/**提交保存*/
	public void save(CommConfigLocationForm form){
		CommConfigLocation data = new CommConfigLocation();
		this.setData(form, data);
		commConfigLocationDAO.save(data);
	}
	/**修改之前初始化*/
	public void updateInit(CommConfigLocationForm form){
		CommConfigLocation data = commConfigLocationDAO.findById(form.getIdHidden());
		this.setForm(form, data);
		init(form);
	}
	/**提交修改*/
	public void update(CommConfigLocationForm form){
		CommConfigLocation data = commConfigLocationDAO.findById(form.getIdHidden());
		this.setData(form, data);
		commConfigLocationDAO.update(data);
	}
	/**提交察看详细*/
	public void showInit(CommConfigLocationForm form){
		CommConfigLocation data = commConfigLocationDAO.findById(form.getIdHidden());
		this.setForm(form, data);
		init(form);
	}
	/**提交删除*/
	public void delete(CommConfigLocationForm form){
		CommConfigLocation data = commConfigLocationDAO.findById(form.getIdHidden());
		commConfigLocationDAO.delete(data);
	}
	/**获取总记录数*/
	public int getCount(String id,String itemCode, String itemName, String levelFlag,String parentId, String inputCode, String seqNo){
		return commConfigLocationDAO.getCount( id, itemCode,  itemName,  levelFlag, parentId,  inputCode,  seqNo);
	}
	/**根据查询条件获取当前显示页的数据*/
	public void getSearch(CommConfigLocationForm form, int curCount, int pageSize){
		
		String order = "";
		if(form.getOrderNo().equals("1")){
			order = " a.seqNo";
		} else if(form.getOrderNo().equals("2")){
			order = " a.itemCode";
		} else if(form.getOrderNo().equals("3")){
			order = " a.itemName";
		} else if(form.getOrderNo().equals("4")){
			order = " a.levelFlag";
		} else if(form.getOrderNo().equals("5")){
			order = " a.inputCode";
		} else {
			order = " a.seqNo";
		}
		
		if(form.getAsc().equals("1")){
			order += " desc";
		} else {
			order += " asc";
		}
		
		List<?> list = commConfigLocationDAO.getData(form.getId()
								, form.getItemCode()
								, form.getItemName()
								, form.getLevelFlag()
								, form.getParentId()
								, form.getInputCode()
								, form.getSeqNo()
								, order
								, curCount
								, pageSize);
		if(list != null && list.size() > 0){
			String[] idList = new String[list.size()];
			String[] itemCodeList = new String[list.size()];
			String[] itemNameList = new String[list.size()];
			String[] levelFlagList = new String[list.size()];
			String[] parentIdList = new String[list.size()];
			String[] parentNameList = new String[list.size()];
			String[] inputCodeList = new String[list.size()];
			String[] commentsList = new String[list.size()];
			String[] seqNoList = new String[list.size()];
			for(int i = 0; i < list.size(); i++){
				idList[i] = String.valueOf(transNullToString(((Object[])list.get(i))[0]));
				itemCodeList[i] = String.valueOf(transNullToString(((Object[])list.get(i))[1]));
				itemNameList[i] = String.valueOf(transNullToString(((Object[])list.get(i))[2]));
				levelFlagList[i] = ((Object[])list.get(i))[3] == null ? "0" : String.valueOf(((Object[])list.get(i))[3]);
				parentIdList[i] = String.valueOf(transNullToString(((Object[])list.get(i))[4]));
				parentNameList[i] = transNullToString(commConfigLocationDAO.getItemName(parentIdList[i]));
				inputCodeList[i] = String.valueOf(transNullToString(((Object[])list.get(i))[5]));
				commentsList[i] = String.valueOf(transNullToString(((Object[])list.get(i))[6]));
				seqNoList[i] = ((Object[])list.get(i))[7] == null ? "0" : String.valueOf(((Object[])list.get(i))[7]);
			}
			
			form.setIdList(idList);
			form.setItemCodeList(itemCodeList);
			form.setItemNameList(itemNameList);
			form.setLevelFlagList(levelFlagList);
			form.setParentIdList(parentIdList);
			form.setParentNameList(parentNameList);
			form.setInputCodeList(inputCodeList);
			form.setCommentsList(commentsList);
			form.setSeqNoList(seqNoList);
		}
	}
	/**构造data*/
	private void setData(CommConfigLocationForm form, CommConfigLocation data){
		try{
			data.setId        (transNullToString(form.getId      ()));
			data.setItemCode (transNullToString(form.getItemCode()));
			data.setItemName (transNullToString(form.getItemName()));
			data.setLevelFlag   (Long.valueOf((form.getLevelFlag() == null || form.getLevelFlag().trim() == "") ? "0" : form.getLevelFlag()));
			data.setParentId (transNullToString(form.getParentId()));
			data.setInputCode (transNullToString(form.getInputCode()));
			data.setComments  (transNullToString(form.getComments()));
			data.setSeqNo     (Long.valueOf((form.getSeqNo() == null || form.getSeqNo().trim() == "") ? "0" : form.getSeqNo()));
			 } catch (Exception e) { 
			log.error("setData fail!",e);
			e.printStackTrace();
		} 
	}
	/**
	 * 去掉字串中的空格
	 * */
	public String transNullToString(Object obj){
		String temp = "";
		if (obj != null){
			temp = ((String)obj).trim();			
		}
		return temp;
	}
	
	public String transNullToZero(Object obj){
		String temp = "0";
		if (obj != null){
			temp = ((String)obj).trim();			
		}
		return temp;
	}
	/**在传递数据到页面之前构造form*/
	private void setForm(CommConfigLocationForm form, CommConfigLocation data){
		try{
			form.setIdHidden(transNullToString(data.getId()));
			form.setId(transNullToString(data.getId()));
			form.setItemCode (transNullToString(data.getItemCode()));
			form.setItemName (transNullToString(data.getItemName()));
			form.setLevelFlag(data.getLevelFlag() == null ? "0" : String.valueOf(data.getLevelFlag()));
			form.setParentId (transNullToString(data.getParentId()));
			form.setParentName(transNullToString(commConfigLocationDAO.getItemName(data.getParentId())));
			form.setInputCode(transNullToString(data.getInputCode()));
			form.setComments(transNullToString(data.getComments())); 
			form.setSeqNo(data.getSeqNo() == null ? "0" : String.valueOf(data.getSeqNo()));
		 } catch (Exception e) { 
			 log.error("setForm fail!",e);
				e.printStackTrace();
		} 
	}
	
	/**在初始化阶段将字典库传入form中*/
	private void init(CommConfigLocationForm form){
		this.getCommConfigLocation();
		form.setParentIds(this.getParentIds());
		form.setParentNames(this.getParentNames());
		if (form.getSeqNo() == null || form.getSeqNo().equals(""))
			form.setSeqNo(commConfigLocationDAO.getMaxSeqNo() + "");
		
		
		List<?> commConfigLocationId1_data = null;		
		List<?> commConfigLocationId2_data = null;
		
		commConfigLocationId1_data = this.commConfigLocationDAO.getLocation(1,"");
		if(form.getCommConfigLocationId1()!=null&&form.getCommConfigLocationId1().trim().length()>0){
			commConfigLocationId2_data = this.commConfigLocationDAO.getLocation(2, form.getCommConfigLocationId1());
		}

		
		//--------------------------省---------------------------------------
		 String[] tempId = null;
		 String[] tempName = null;
		if (commConfigLocationId1_data != null) {
			if(commConfigLocationId1_data.size()==1){
				tempId = new String[commConfigLocationId1_data.size() ];
				tempName = new String[commConfigLocationId1_data.size() ];
				for (int i = 0; i < commConfigLocationId1_data.size(); i++) {
					CommConfigLocation data = (CommConfigLocation)commConfigLocationId1_data.get(i);
					tempId[i ] = this.transNullToString(data.getItemCode());
					tempName[i ] =this.transNullToString(data.getItemName());
				}
			}else{
				tempId = new String[commConfigLocationId1_data.size()+1 ];
				tempName = new String[commConfigLocationId1_data.size()+1 ];
				tempId[0] = "";
				tempName[0] = "";
				for (int i = 0; i < commConfigLocationId1_data.size(); i++) {
					CommConfigLocation data = (CommConfigLocation)commConfigLocationId1_data.get(i);
					tempId[i+1 ] = this.transNullToString(data.getItemCode());
					tempName[i+1 ] =this.transNullToString(data.getItemName());
				}
			}
		}		
		form.setCommConfigLocationId1s(tempId);
		form.setCommConfigLocationId1_names(tempName);
		//--------------------------市---------------------------------------
		tempId = null;
		tempName = null;
		if (commConfigLocationId2_data != null) {
			if(commConfigLocationId2_data.size()==1){
				tempId = new String[commConfigLocationId2_data.size() ];
				tempName = new String[commConfigLocationId2_data.size() ];
				for (int i = 0; i < commConfigLocationId2_data.size(); i++) {
					CommConfigLocation data = (CommConfigLocation)commConfigLocationId2_data.get(i);
					tempId[i] = this.transNullToString(data.getItemCode());
					tempName[i] =this.transNullToString(data.getItemName());
				}
			}
			else{
				tempId = new String[commConfigLocationId2_data.size()+1 ];
				tempName = new String[commConfigLocationId2_data.size()+1 ];
				tempId[0] = "";
				tempName[0] = "";
				for (int i = 0; i < commConfigLocationId2_data.size(); i++) {
					CommConfigLocation data = (CommConfigLocation)commConfigLocationId2_data.get(i);
					tempId[i+1] = this.transNullToString(data.getItemCode());
					tempName[i+1] =this.transNullToString(data.getItemName());
				}
			}
		} else {
			//--------------如果为空，插入空格-----------
			tempId = new String[1];
			tempName = new String[1];
			tempId[0] = "";
			tempName[0] = "";
		}
		form.setCommConfigLocationId2s(tempId);
		form.setCommConfigLocationId2_names(tempName);

	}

	/**获取查询结果之前初始化*/
	private void searchInit(CommConfigLocationForm form){
		this.getCommConfigLocation();
		
		/**所属省*/
		form.setCommConfigLocationId1s(this.getCommConfigLocationId1());
		form.setCommConfigLocationId1_names(this.getCommConfigLocationName1());
		
		form.setParentIds(this.getParentIds());
		form.setParentNames(this.getParentNames());
	}
	/**获取单条记录详细信息时去的字典库描述*/
	public void getDetail(CommConfigLocationForm form){
	//	form.setParentName(commConfigLocationDAO.getItemName( form.getParentId()));
	 }
     //--------------------------------以下部分开始处理字典库的内容-----------------------------------------
	/**字典库的处理*/
	private String[] parentIds;
	private String[] parentNames;
	
	private String[] commConfigLocationId1;
	private String[] commConfigLocationName1;
	/**
	 * @return Returns the dictId.
	 */
	public String[] getParentIds() {
		return this.parentIds;
	}
	/**
	 * @param dictId The dictId to set.
	 */
	public void setParentIds(String[] parentIds) {
		this.parentIds = parentIds;
	}
	/**
	 * @return Returns the dictName.
	 */
	public String[] getParentNames() {
		return this.parentNames;
	}
	/**
	 * @param dictName The dictName to set.
	 */
	public void setParentNames(String[] parentNames) {
		this.parentNames = parentNames;
	}
	/**根据字典类别获取字典详细信息列表*/
	public void getCommConfigLocation(){
		
		List<?> commConfigLocationId1_data = null;
		commConfigLocationId1_data = this.commConfigLocationDAO.getByParent("","1");
		
		String[] tempId = null;
		String[] tempName = null;
		//----------------------省----------------------------------------------------
		 tempId = null;
		 tempName = null;
		if (commConfigLocationId1_data != null) {
			if(commConfigLocationId1_data.size()==1){
				tempId = new String[commConfigLocationId1_data.size() ];
				tempName = new String[commConfigLocationId1_data.size() ];
				for (int i = 0; i < commConfigLocationId1_data.size(); i++) {
					CommConfigLocation data = (CommConfigLocation)commConfigLocationId1_data.get(i);
					tempId[i ] = this.transNullToString(data.getItemCode());
					tempName[i ] =this.transNullToString(data.getItemName());
				}
			}else{
				tempId = new String[commConfigLocationId1_data.size()+1 ];
				tempName = new String[commConfigLocationId1_data.size()+1 ];
				tempId[0] = "";
				tempName[0] = "";
				for (int i = 0; i < commConfigLocationId1_data.size(); i++) {
					CommConfigLocation data = (CommConfigLocation)commConfigLocationId1_data.get(i);
					tempId[i+1 ] = this.transNullToString(data.getItemCode());
					tempName[i+1 ] =this.transNullToString(data.getItemName());
				}
			}
		}
		this.setCommConfigLocationId1(tempId);
		this.setCommConfigLocationName1(tempName);
		
		
		List<?> data = commConfigLocationDAO.findAll();
		if(data != null){
			tempId = new String[data.size() + 1];
			tempName = new String[data.size() + 1];
			tempId[0] = "";
			tempName[0] = "";
			for(int i = 0; i < data.size(); i++){
				CommConfigLocation dict = (CommConfigLocation)data.get(i);
				tempId[i + 1] = dict.getId();
				tempName[i + 1] = dict.getItemName();
			}
		}
		this.setParentIds(tempId);
		this.setParentNames(tempName);
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
	
	//--------------------------------以下部分开始处理字典库的内容------结束-----------------------------------
}
