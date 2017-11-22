package com.tianjian.comm.business.service;


import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import com.tianjian.comm.bean.CommDictPublicLevel;
import com.tianjian.comm.bean.CommDictPublicClass;
import com.tianjian.comm.business.ICommDictPublicLevelService;
import com.tianjian.comm.dao.ICommDictPublicLevelDAO;
import com.tianjian.comm.dao.ICommDictPublicClassDAO;
import com.tianjian.comm.struts.form.CommDictPublicLevelForm;

public class CommDictPublicLevelServiceImpl implements ICommDictPublicLevelService {
	private static final Logger log = LogManager.getLogger(CommDictPublicLevelServiceImpl.class);
	private ICommDictPublicLevelDAO commDictPublicLevelDAO;
	private ICommDictPublicClassDAO commDictPublicClassDAO;

	//------------------------DAO-- 声明结束---------------------------------------------------
	
	
	public ICommDictPublicLevelDAO getCommDictPublicLevelDAO() {
		return commDictPublicLevelDAO;
	}

	
	public void setCommDictPublicLevelDAO(ICommDictPublicLevelDAO commDictPublicLevelDAO) {
		this.commDictPublicLevelDAO = commDictPublicLevelDAO;
	}

	public ICommDictPublicClassDAO getCommDictPublicClassDAO() {
		return commDictPublicClassDAO;
	}

	
	public void setCommDictPublicClassDAO(ICommDictPublicClassDAO commDictPublicClassDAO) {
		this.commDictPublicClassDAO = commDictPublicClassDAO;
	}
   /**新增之前的初始化，这里需要初始化字典库内容*/
	public void addInit(CommDictPublicLevelForm form){
		init(form);
	}
	/**查找之前的初始化，这里需要初始化字典库内容*/
	public void serchInit(CommDictPublicLevelForm form){
		searchInit(form);
	}
	/**获取记录对象*/
	public CommDictPublicLevel checkData(String id){
		CommDictPublicLevel data = commDictPublicLevelDAO.findById(id);
		return data;
	}
	/**提交保存*/
	public void save(CommDictPublicLevelForm form){
		CommDictPublicLevel data = new CommDictPublicLevel();
		this.setData(form, data);
		commDictPublicLevelDAO.save(data);
	}
	/**修改之前初始化*/
	public void updateInit(CommDictPublicLevelForm form){
		CommDictPublicLevel data = commDictPublicLevelDAO.findById(form.getIdHidden());
		this.setForm(form, data);
		init(form);
	}
	/**提交修改*/
	public void update(CommDictPublicLevelForm form){
		CommDictPublicLevel data = commDictPublicLevelDAO.findById(form.getIdHidden());
		this.setData(form, data);
		commDictPublicLevelDAO.update(data);
	}
	/**提交察看详细*/
	public void showInit(CommDictPublicLevelForm form){
		CommDictPublicLevel data = commDictPublicLevelDAO.findById(form.getIdHidden());
		this.setForm(form, data);
		init(form);
	}
	/**提交删除*/
	public void delete(CommDictPublicLevelForm form){
		CommDictPublicLevel data = commDictPublicLevelDAO.findById(form.getIdHidden());
		commDictPublicLevelDAO.delete(data);
	}
	/**获取总记录数*/
	public int getCount(String itemCode,String itemName, String inputCode,String parentItemCode, String classLevel,  String seqInLevel, String tableCode){
		return commDictPublicLevelDAO.getCount( itemCode, itemName,  inputCode, parentItemCode,  classLevel,   seqInLevel,  tableCode);
	}
	/**根据查询条件获取当前显示页的数据*/
	public void getSearch(CommDictPublicLevelForm form, int curCount, int pageSize){
		
		String order = "";
		if(form.getOrderNo().equals("1")){
			order = " a.classLevel";
		} else if(form.getOrderNo().equals("2")){
			order = " a.itemName";
		} else if(form.getOrderNo().equals("3")){
			order = " a.seqInLevel";
		} else if(form.getOrderNo().equals("4")){
			order = " a.parentItemCode";
		} else if(form.getOrderNo().equals("5")){
			order = " a.tableCode";
		} else {
			order = " a.seqInLevel";
		}
		
		if(form.getAsc().equals("1")){
			order += " desc";
		} else {
			order += " asc";
		}
		
		List<?> list = commDictPublicLevelDAO.getData(form.getItemCode()
								, form.getItemName()
								, form.getInputCode()
								, form.getParentItemCode()
								, form.getClassLevel()
								, form.getSeqInLevel()
								, form.getTableCode()
								, order
								, curCount
								, pageSize);
		if(list != null && list.size() > 0){
			String[] itemCodeList = new String[list.size()];
			String[] itemNameList = new String[list.size()];
			String[] inputCodeList = new String[list.size()];
			String[] parentItemCodeList = new String[list.size()];
			String[] parentItemNameList = new String[list.size()];
			String[] classLevelList = new String[list.size()];
			String[] seqInLevelList = new String[list.size()];
			String[] tableCodeList = new String[list.size()];
			String[] tableNameList = new String[list.size()];
			String[] commentsList = new String[list.size()];
			for(int i = 0; i < list.size(); i++){
				itemCodeList[i] = String.valueOf(transNullToString(((Object[])list.get(i))[0]));
				itemNameList[i] = String.valueOf(transNullToString(((Object[])list.get(i))[1]));
				inputCodeList[i] = String.valueOf(transNullToString(((Object[])list.get(i))[2]));
				parentItemCodeList[i] = String.valueOf(transNullToString(((Object[])list.get(i))[3]));
				parentItemNameList[i] = transNullToString(commDictPublicLevelDAO.getItemName(parentItemCodeList[i]));
				classLevelList[i] = ((Object[])list.get(i))[4] == null ? "0" : String.valueOf(((Object[])list.get(i))[4]);
				seqInLevelList[i] = ((Object[])list.get(i))[5] == null ? "0" : String.valueOf(((Object[])list.get(i))[5]);
				tableCodeList[i] = String.valueOf(transNullToString(((Object[])list.get(i))[6]));
				tableNameList[i] = transNullToString(commDictPublicClassDAO.getClassName(tableCodeList[i]));
				commentsList[i] = String.valueOf(transNullToString(((Object[])list.get(i))[7]));
			}
			
			form.setItemCodeList(itemCodeList);
			form.setItemNameList(itemNameList);
		    form.setInputCodeList(inputCodeList); 
		    form.setParentItemCodeList(parentItemCodeList);
		    form.setParentItemNameList(parentItemNameList);
		    form.setClassLevelList(classLevelList);
		    form.setSeqInLevelList(seqInLevelList);
		    form.setTableCodeList(tableCodeList);
		    form.setTableNameList(tableNameList);
		    form.setCommentsList(commentsList);
		}
	}
	/**构造data*/
	private void setData(CommDictPublicLevelForm form, CommDictPublicLevel data){
		try{
			data.setItemCode        (transNullToString(form.getItemCode      ()));
			data.setItemName(transNullToString(form.getItemName())); 
			data.setInputCode (transNullToString(form.getInputCode()));
			data.setParentItemCode (transNullToString(form.getParentItemCode()));
			data.setClassLevel   (Long.valueOf((form.getClassLevel() == null || form.getClassLevel().trim() == "") ? "0" : form.getClassLevel()));
			data.setSeqInLevel   (Long.valueOf((form.getSeqInLevel() == null || form.getSeqInLevel().trim() == "") ? "0" : form.getSeqInLevel()));
			data.setTableCode   (transNullToString(form.getTableCode()));	 
			data.setComments  (transNullToString(form.getComments()));	
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
	private void setForm(CommDictPublicLevelForm form, CommDictPublicLevel data){
		try{
			form.setIdHidden(transNullToString(data.getItemCode()));
			form.setItemCode(transNullToString(data.getItemCode()));
			form.setItemName(transNullToString(data.getItemName()));
			form.setInputCode(transNullToString(data.getInputCode()));
			form.setParentItemCode(transNullToString(data.getParentItemCode()));
			form.setParentItemName(transNullToString(commDictPublicLevelDAO.getItemName(data.getParentItemCode())));
			form.setClassLevel(data.getClassLevel() == null ? "0" : String.valueOf(data.getClassLevel()));
			form.setSeqInLevel(data.getSeqInLevel() == null ? "0" : String.valueOf(data.getSeqInLevel()));
			form.setTableCode(transNullToString(data.getTableCode()));
			form.setTableName(transNullToString(commDictPublicClassDAO.getClassName(data.getTableCode())));
			form.setComments(transNullToString(data.getComments())); 
		 } catch (Exception e) { 
			 log.error("setForm fail!",e);
				e.printStackTrace();
		} 
	}
	
	/**在初始化阶段将字典库传入form中*/
	private void init(CommDictPublicLevelForm form){
		this.getCommDictPulicClass();
		form.setTableCodes(this.getTableCodes());
		form.setTableNames(this.getTableNames());
		this.getCommDictPulicLevel();
		form.setParentItemCodes(this.getParentItemCodes());
		form.setParentItemNames(this.getParentItemNames());
		if (form.getSeqInLevel() == null || form.getSeqInLevel().equals(""))
			form.setSeqInLevel(commDictPublicLevelDAO.getMaxSeqNo() + "");

	}

	/**获取查询结果之前初始化*/
	private void searchInit(CommDictPublicLevelForm form){
		this.getCommDictPulicClass();
		form.setTableCodes(this.getTableCodes());
		form.setTableNames(this.getTableNames());
		this.getCommDictPulicLevel();
		form.setParentItemCodes(this.getParentItemCodes());
		form.setParentItemNames(this.getParentItemNames());
	}
	/**获取单条记录详细信息时去的字典库描述*/
	public void getDetail(CommDictPublicLevelForm form){
		form.setTableName(commDictPublicClassDAO.getClassName( form.getTableCode()));
		form.setParentItemName(commDictPublicLevelDAO.getItemName( form.getParentItemCode()));
	 }
     //--------------------------------以下部分开始处理字典库的内容-----------------------------------------
	/**字典库的处理*/
	private String[] tableCodes;
	private String[] tableNames;

	
	/**
	 * @return Returns the dictId.
	 */
	public String[] getTableCodes() {
		return this.tableCodes;
	}
	/**
	 * @param dictId The dictId to set.
	 */
	public void setTableCodes(String[] tableCodes) {
		this.tableCodes = tableCodes;
	}
	/**
	 * @return Returns the dictName.
	 */
	public String[] getTableNames() {
		return this.tableNames;
	}
	/**
	 * @param dictName The dictName to set.
	 */
	public void setTableNames(String[] tableNames) {
		this.tableNames = tableNames;
	}
	/**根据字典类别获取字典详细信息列表*/
	public void getCommDictPulicClass(){
		List<?> data = commDictPublicClassDAO.findAll();
		String[] tempId = null;
		String[] tempName = null;
		if(data != null){
			tempId = new String[data.size() + 1];
			tempName = new String[data.size() + 1];
			tempId[0] = "";
			tempName[0] = "";
			for(int i = 0; i < data.size(); i++){
				CommDictPublicClass dict = (CommDictPublicClass)data.get(i);
				tempId[i + 1] = dict.getClassCode();
				tempName[i + 1] = dict.getClassName();
			}
		}
		this.setTableCodes(tempId);
		this.setTableNames(tempName);
	}
	
	//-------------------------------------------------------------------------------
	private String[] parentItemCodes;
	private String[] parentItemNames;

	
	public String[] getParentItemCodes() {
		return parentItemCodes;
	}


	
	public void setParentItemCodes(String[] parentItemCodes) {
		this.parentItemCodes = parentItemCodes;
	}


	
	public String[] getParentItemNames() {
		return parentItemNames;
	}


	
	public void setParentItemNames(String[] parentItemNames) {
		this.parentItemNames = parentItemNames;
	}
	
	/**根据字典类别获取字典详细信息列表*/
	public void getCommDictPulicLevel(){
		List<?> data = commDictPublicLevelDAO.findAll();
		String[] tempId = null;
		String[] tempName = null;
		if(data != null){
			tempId = new String[data.size() + 1];
			tempName = new String[data.size() + 1];
			tempId[0] = "";
			tempName[0] = "";
			for(int i = 0; i < data.size(); i++){
				CommDictPublicLevel dict = (CommDictPublicLevel)data.get(i);
				tempId[i + 1] = dict.getItemCode();
				tempName[i + 1] = dict.getItemName();
			}
		}
		this.setParentItemCodes(tempId);
		this.setParentItemNames(tempName);
	}
	//--------------------------------以下部分开始处理字典库的内容------结束-----------------------------------
}
