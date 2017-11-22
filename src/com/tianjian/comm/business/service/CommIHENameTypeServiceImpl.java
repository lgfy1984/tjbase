package com.tianjian.comm.business.service;


import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import com.tianjian.comm.bean.CommConfigAbo;
import com.tianjian.comm.bean.CommIHENameType;
import com.tianjian.comm.business.ICommConfigAboService;
import com.tianjian.comm.business.ICommIHENameTypeService;
import com.tianjian.comm.dao.ICommConfigAboDAO;
import com.tianjian.comm.dao.ICommIHENameTypeDAO;
import com.tianjian.comm.struts.form.CommConfigAboForm;
import com.tianjian.comm.struts.form.CommIHENameTypeForm;

public class CommIHENameTypeServiceImpl implements ICommIHENameTypeService {
	private static final Logger log = LogManager.getLogger(CommIHENameTypeServiceImpl.class);
	private ICommIHENameTypeDAO commIHENameTypeDAO;

	//------------------------DAO-- 声明结束---------------------------------------------------
	
	public void addInit(CommIHENameTypeForm form){
		init(form);
		form.setSeqNo(String.valueOf(this.commIHENameTypeDAO.seqNoMaker("CommIHENameType")));
	}
	
	public void serchInit(CommIHENameTypeForm form){
		searchInit(form);
	}
	/**获取记录对象*/
	public CommIHENameType checkData(String id){
		CommIHENameType data = commIHENameTypeDAO.findById(id);
		return data;
	}
	/**提交保存*/
	public void save(CommIHENameTypeForm form){
		CommIHENameType data = new CommIHENameType();
		this.setData(form, data);
		commIHENameTypeDAO.save(data);
	}
	/**修改之前初始化*/
	public void updateInit(CommIHENameTypeForm form){
		CommIHENameType data = commIHENameTypeDAO.findById(form.getIdHidden());
		this.setForm(form, data);
		init(form);
	}
	/**提交修改*/
	public void update(CommIHENameTypeForm form){
		CommIHENameType data = commIHENameTypeDAO.findById(form.getIdHidden());
		this.setData(form, data);
		commIHENameTypeDAO.update(data);
	}
	/**提交察看详细*/
	public void showInit(CommIHENameTypeForm form){
		CommIHENameType data = commIHENameTypeDAO.findById(form.getIdHidden());
		form = new CommIHENameTypeForm();
		this.setForm(form, data);
		init(form);
	}
	/**提交删除*/
	public void delete(CommIHENameTypeForm form){
		CommIHENameType data = commIHENameTypeDAO.findById(form.getIdHidden());
		commIHENameTypeDAO.delete(data);
	}
	/**获取总记录数*/
	public int getCount(String nameTypeCode, String nameTypeName, String inputCode, String seqNo){
		return commIHENameTypeDAO.getCount(nameTypeCode, nameTypeName, inputCode, seqNo);
	}
	/**根据查询条件获取当前显示页的数据*/
	public void getSearch(CommIHENameTypeForm form, int curCount, int pageSize){
		try{
			 
		String order = "";
		if(form.getOrderNo().equals("1")){
			order = " a.nameTypeCode";
		} else if(form.getOrderNo().equals("2")){
			order = " a.nameTypeName";
		} else if(form.getOrderNo().equals("3")){
			order = " a.seqNo";
		} else if(form.getOrderNo().equals("4")){
			order = " a.inputCode";
	
		} else {
			order = " a.seqNo";
		}
		
		if(form.getAsc().equals("1")){
			order += " desc";
		} else {
			order += " asc";
		}
	 
		List<?> list = commIHENameTypeDAO.getData(form.getNameTypeCode()
								, form.getNameTypeName()
								, form.getInputCode()
								, form.getSeqNo()
								, order
								, curCount
								, pageSize);
		 
		if(list != null && list.size() > 0){
			String[] ids = new String[list.size()];
			String[] nameTypeCodes = new String[list.size()];
			String[] nameTypeNames = new String[list.size()];
			String[] inputCodes = new String[list.size()];
			String[] comments = new String[list.size()];
			String[] seqNos = new String[list.size()];
			for(int i = 0; i < list.size(); i++){
				ids[i] = String.valueOf(transNullToString(((Object[])list.get(i))[0]));
				nameTypeCodes[i] = String.valueOf(transNullToString(((Object[])list.get(i))[1]));
				nameTypeNames[i] = String.valueOf(transNullToString(((Object[])list.get(i))[2]));
				inputCodes[i] = String.valueOf(transNullToString(((Object[])list.get(i))[3]));
				comments[i] = String.valueOf(transNullToString(((Object[])list.get(i))[4]));
				seqNos[i] = ((Object[])list.get(i))[5] == null ? "0" : String.valueOf(((Object[])list.get(i))[5]);
				 
			}
			form.setIdList(ids);
			form.setNameTypeCodeList(nameTypeCodes);
			form.setNameTypeNameList(nameTypeNames);
			form.setInputCodeList(inputCodes);
			form.setCommentsList(comments);
			form.setSeqNoList(seqNos);
		 
		}
		 } catch (Exception e) { 
			 log.error("getSearch fail!",e);
			 e.printStackTrace();
	} 
	}
	/**构造data*/
	private void setData(CommIHENameTypeForm form, CommIHENameType data){
		try{
			data.setId(transNullToString(form.getId()));
			data.setNameTypeCode(transNullToString(form.getNameTypeCode()));
			data.setNameTypeName(transNullToString(form.getNameTypeName()));
			data.setInputCode(transNullToString(form.getInputCode()));
			data.setComments(transNullToString(form.getComments      ()));
			data.setSeqNo(Long.valueOf((form.getSeqNo() == null || form.getSeqNo().trim() == "") ? "0" : form.getSeqNo()));
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
	private void setForm(CommIHENameTypeForm form, CommIHENameType data){
		try{
			form.setId(transNullToString(data.getId()));
			form.setNameTypeCode(transNullToString(data.getNameTypeCode()));
			form.setNameTypeName(transNullToString(data.getNameTypeName()));
			form.setInputCode(transNullToString(data.getInputCode()));
			form.setComments(transNullToString(data.getComments())); 
			form.setSeqNo(data.getSeqNo() == 0 ? "0" : String.valueOf(data.getSeqNo()));
		 } catch (Exception e) { 
			 log.error("setForm fail!",e);
			 e.printStackTrace();
		} 
	}
	/**在初始化阶段将字典库传入form中*/
	private void init(CommIHENameTypeForm form){
		
	}
	/***/
	public void getDetail(CommIHENameTypeForm form){
	
	}
	/**获取查询结果之前初始化*/
	/**在进行查找之前将字典库传入form中*/
	private void searchInit(CommIHENameTypeForm form){
	}

	public ICommIHENameTypeDAO getCommIHENameTypeDAO() {
		return commIHENameTypeDAO;
	}

	public void setCommIHENameTypeDAO(ICommIHENameTypeDAO commIHENameTypeDAO) {
		this.commIHENameTypeDAO = commIHENameTypeDAO;
	}
	
}
