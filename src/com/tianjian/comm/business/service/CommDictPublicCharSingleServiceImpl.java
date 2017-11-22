package com.tianjian.comm.business.service;


import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import com.tianjian.comm.bean.CommDictPublicChar;
import com.tianjian.comm.business.ICommDictPublicCharSingleService;
import com.tianjian.comm.dao.ICommDictPublicCharDAO;
import com.tianjian.comm.dao.ICommDictPublicClassDAO;
import com.tianjian.comm.struts.form.CommDictPublicCharForm;

public class CommDictPublicCharSingleServiceImpl implements ICommDictPublicCharSingleService {
	private static final Logger log = LogManager.getLogger(CommDictPublicCharSingleServiceImpl.class);
	private ICommDictPublicCharDAO commDictPublicCharDAO;
	private ICommDictPublicClassDAO commDictPublicClassDAO;

	//------------------------DAO-- 声明结束---------------------------------------------------
	
	
	public ICommDictPublicCharDAO getCommDictPublicCharDAO() {
		return commDictPublicCharDAO;
	}

	
	public void setCommDictPublicCharDAO(ICommDictPublicCharDAO commDictPublicCharDAO) {
		this.commDictPublicCharDAO = commDictPublicCharDAO;
	}

	public ICommDictPublicClassDAO getCommDictPublicClassDAO() {
		return commDictPublicClassDAO;
	}

	
	public void setCommDictPublicClassDAO(ICommDictPublicClassDAO commDictPublicClassDAO) {
		this.commDictPublicClassDAO = commDictPublicClassDAO;
	}
   /**新增之前的初始化，这里需要初始化字典库内容*/
	public void addInit(CommDictPublicCharForm form){
		init(form);
	}
	/**查找之前的初始化，这里需要初始化字典库内容*/
	public void serchInit(CommDictPublicCharForm form){
		searchInit(form);
	}
	/**获取记录对象*/
	public CommDictPublicChar checkData(String id){
		CommDictPublicChar data = commDictPublicCharDAO.findById(id);
		return data;
	}
	/**提交保存*/
	public void save(CommDictPublicCharForm form){
		CommDictPublicChar data = new CommDictPublicChar();
		this.setData(form, data);
		commDictPublicCharDAO.save(data);
	}
	/**修改之前初始化*/
	public void updateInit(CommDictPublicCharForm form){
		CommDictPublicChar data = commDictPublicCharDAO.findById(form.getIdHidden());
		this.setForm(form, data);
		init(form);
	}
	/**提交修改*/
	public void update(CommDictPublicCharForm form){
		CommDictPublicChar data = commDictPublicCharDAO.findById(form.getIdHidden());
		this.setData(form, data);
		commDictPublicCharDAO.update(data);
	}
	/**提交察看详细*/
	public void showInit(CommDictPublicCharForm form){
		CommDictPublicChar data = commDictPublicCharDAO.findById(form.getIdHidden());
		this.setForm(form, data);
		init(form);
	}
	/**提交删除*/
	public void delete(CommDictPublicCharForm form){
		CommDictPublicChar data = commDictPublicCharDAO.findById(form.getIdHidden());
		commDictPublicCharDAO.delete(data);
	}
	/**获取总记录数*/
	public int getCount(String id, String classCode,String dictCode, String dictValue, String inputCode, String seqNo){
		return commDictPublicCharDAO.getCount(id,classCode, dictCode, dictValue, inputCode, seqNo);
	}
	/**根据查询条件获取当前显示页的数据*/
	public void getSearch(CommDictPublicCharForm form, int curCount, int pageSize){
		try{
		//----------get order column--------------------------------
		String order = "";
		if(form.getOrderNo().equals("1")){
			order = " a.dictCode";
		} else if(form.getOrderNo().equals("2")){
			order = " a.dictValue";
		} else if(form.getOrderNo().equals("3")){
			order = " a.seqNo";
		} else if(form.getOrderNo().equals("4")){
			order = " a.inputCode";
		} else {
			order = " a.seqNo";
		}
		//----------get asc -----------------------------
		if(form.getAsc().equals("1")){
			order += " desc";
		} else {
			order += " asc";
		}
		
		List<?> list = commDictPublicCharDAO.getData(form.getId()
								, form.getClassCode()
								, form.getDictCode()
								, form.getDictValue()
								, form.getInputCode()
								, form.getSeqNo()
								, order
								, curCount
								, pageSize);
		if(list != null && list.size() > 0){
			String[] idList = new String[list.size()];
			String[] classCodeList = new String[list.size()];
			String[] classNameList = new String[list.size()];
			String[] dictCodeList = new String[list.size()];
			String[] dictValueList = new String[list.size()];
			String[] inputCodeList = new String[list.size()];
			String[] commentsList = new String[list.size()];
			String[] seqNoList = new String[list.size()];
			for(int i = 0; i < list.size(); i++){
				idList[i] = String.valueOf(transNullToString(((Object[])list.get(i))[0]));
				classCodeList[i] = String.valueOf(transNullToString(((Object[])list.get(i))[1]));
				classNameList[i] = transNullToString(commDictPublicClassDAO.getClassName(classCodeList[i]));
				dictCodeList[i] = String.valueOf(transNullToString(((Object[])list.get(i))[2]));
				dictValueList[i] = String.valueOf(transNullToString(((Object[])list.get(i))[3]));
				inputCodeList[i] = String.valueOf(transNullToString(((Object[])list.get(i))[4]));
				commentsList[i] = String.valueOf(transNullToString(((Object[])list.get(i))[5]));
				seqNoList[i] = ((Object[])list.get(i))[6] == null ? "0" : String.valueOf(((Object[])list.get(i))[6]);
			}
			
			form.setIdList(idList);
			form.setClassCodeList(classCodeList);
			form.setClassNameList(classNameList);
			form.setDictCodeList(dictCodeList);
			form.setDictValueList(dictValueList);
			form.setInputCodeList(inputCodeList);
			form.setCommentsList(commentsList);
			form.setSeqNoList(seqNoList);
		}
	 } catch (Exception e) { 
		 log.error("getSearch fail!",e);
		 e.printStackTrace();
} 
}
	/**构造data*/
	private void setData(CommDictPublicCharForm form, CommDictPublicChar data){
		try{
			data.setId        (transNullToString(form.getId      ()));
			data.setClassCode(transNullToString(form.getClassCode())); 
			data.setDictCode (transNullToString(form.getDictCode()));
			data.setDictValue (transNullToString(form.getDictValue()));
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
	private void setForm(CommDictPublicCharForm form, CommDictPublicChar data){
		try{
			form.setIdHidden(transNullToString(data.getId()));
			form.setId(transNullToString(data.getId()));
			form.setClassCode(transNullToString(data.getClassCode()));
			form.setClassName(transNullToString(commDictPublicClassDAO.getClassName(data.getClassCode())));
			form.setDictCode (transNullToString(data.getDictCode()));
			form.setDictValue (transNullToString(data.getDictValue()));
			form.setInputCode(transNullToString(data.getInputCode()));
			form.setComments(transNullToString(data.getComments())); 
			form.setSeqNo(data.getSeqNo() == null ? "0" : String.valueOf(data.getSeqNo()));
		 } catch (Exception e) { 
			 log.error("setForm fail!",e);
			 e.printStackTrace();
		} 
	}
	
	/**在初始化阶段将字典库传入form中*/
	private void init(CommDictPublicCharForm form){
	 form.setClassName(transNullToString(commDictPublicClassDAO.getClassName(form.getClassCode())));
	 if (form.getSeqNo() == null || form.getSeqNo().equals(""))
			form.setSeqNo(commDictPublicCharDAO.getMaxSeqNo() + "");

	}

	/**获取查询结果之前初始化*/
	private void searchInit(CommDictPublicCharForm form){
		 form.setClassName(transNullToString(commDictPublicClassDAO.getClassName(form.getClassCode())));
	}
	/**获取单条记录详细信息时去的字典库描述*/
	public void getDetail(CommDictPublicCharForm form){
		 form.setClassName(transNullToString(commDictPublicClassDAO.getClassName(form.getClassCode())));
	 }
     //--------------------------------以下部分开始处理字典库的内容-----------------------------------------

	
	//--------------------------------以下部分开始处理字典库的内容------结束-----------------------------------
}
