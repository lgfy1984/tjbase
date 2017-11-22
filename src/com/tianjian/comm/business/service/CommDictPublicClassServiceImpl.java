package com.tianjian.comm.business.service;


import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import com.tianjian.comm.bean.CommDictPublicClass;
import com.tianjian.comm.business.ICommDictPublicClassService;
import com.tianjian.comm.dao.ICommDictPublicClassDAO;
import com.tianjian.comm.struts.form.CommDictPublicClassForm;

public class CommDictPublicClassServiceImpl implements ICommDictPublicClassService {
	private static final Logger log = LogManager.getLogger(CommDictPublicClassServiceImpl.class);
	private ICommDictPublicClassDAO commDictPublicClassDAO;

	//------------------------DAO-- 声明结束---------------------------------------------------
	
	
	public ICommDictPublicClassDAO getCommDictPublicClassDAO() {
		return commDictPublicClassDAO;
	}

	
	public void setCommDictPublicClassDAO(ICommDictPublicClassDAO commDictPublicClassDAO) {
		this.commDictPublicClassDAO = commDictPublicClassDAO;
	}

	public void addInit(CommDictPublicClassForm form){
		init(form);
	}
	
	public void serchInit(CommDictPublicClassForm form){
		searchInit(form);
	}
	/**获取记录对象*/
	public CommDictPublicClass checkData(String id){
		CommDictPublicClass data = commDictPublicClassDAO.findById(id);
		return data;
	}
	/**提交保存*/
	public void save(CommDictPublicClassForm form){
		CommDictPublicClass data = new CommDictPublicClass();
		this.setData(form, data);
		commDictPublicClassDAO.save(data);
	}
	/**修改之前初始化*/
	public void updateInit(CommDictPublicClassForm form){
		CommDictPublicClass data = commDictPublicClassDAO.findById(form.getClassCodeHidden());
		this.setForm(form, data);
		init(form);
	}
	/**提交修改*/
	public void update(CommDictPublicClassForm form){
		CommDictPublicClass data = commDictPublicClassDAO.findById(form.getClassCodeHidden());
		this.setData(form, data);
		commDictPublicClassDAO.update(data);
	}
	/**提交察看详细*/
	public void showInit(CommDictPublicClassForm form){
		CommDictPublicClass data = commDictPublicClassDAO.findById(form.getClassCodeHidden());
		this.setForm(form, data);
		init(form);
	}
	/**提交删除*/
	public void delete(CommDictPublicClassForm form){
		CommDictPublicClass data = commDictPublicClassDAO.findById(form.getClassCodeHidden());
		commDictPublicClassDAO.delete(data);
	}
	/**获取总记录数*/
	public int getCount(String classCode, String className, String inputCode, String seqNo){
		return commDictPublicClassDAO.getCount(classCode, className, inputCode, seqNo);
	}
	/**根据查询条件获取当前显示页的数据*/
	public void getSearch(CommDictPublicClassForm form, int curCount, int pageSize){
		try{
			 
		String order = "";
		if(form.getOrderNo().equals("1")){
			order = " a.classCode";
		} else if(form.getOrderNo().equals("2")){
			order = " a.className";
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
	 
		List<?> list = commDictPublicClassDAO.getData(form.getClassCode()
								, form.getClassName()
								, form.getInputCode()
								, form.getSeqNo()
								, order
								, curCount
								, pageSize);
		 
		if(list != null && list.size() > 0){
		 
			String[] classCodes = new String[list.size()];
			String[] classNames = new String[list.size()];
			String[] inputCodes = new String[list.size()];
			String[] comments = new String[list.size()];
			String[] seqNos = new String[list.size()];
			for(int i = 0; i < list.size(); i++){
				classCodes[i] = String.valueOf(transNullToString(((Object[])list.get(i))[0]));
				classNames[i] = String.valueOf(transNullToString(((Object[])list.get(i))[1]));
				inputCodes[i] = String.valueOf(transNullToString(((Object[])list.get(i))[2]));
				comments[i] = String.valueOf(transNullToString(((Object[])list.get(i))[3]));
				seqNos[i] = ((Object[])list.get(i))[4] == null ? "0" : String.valueOf(((Object[])list.get(i))[4]);
				 
			}
			form.setClassCodeList(classCodes);
			form.setClassNameList(classNames);
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
	private void setData(CommDictPublicClassForm form, CommDictPublicClass data){
		try{
			data.setClassCode (transNullToString(form.getClassCode()));
			data.setClassName (transNullToString(form.getClassName()));
			data.setInputCode (transNullToString(form.getInputCode()));
			data.setComments  (transNullToString(form.getComments      ()));
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
	private void setForm(CommDictPublicClassForm form, CommDictPublicClass data){
		try{
			form.setClassCodeHidden (transNullToString(data.getClassCode()));
			form.setClassCode (transNullToString(data.getClassCode()));
			form.setClassName (transNullToString(data.getClassName()));
			form.setInputCode(transNullToString(data.getInputCode()));
			form.setComments(transNullToString(data.getComments())); 
			form.setSeqNo(data.getSeqNo() == null ? "0" : String.valueOf(data.getSeqNo()));
		 } catch (Exception e) { 
			 log.error("setForm fail!",e);
			 e.printStackTrace();
		} 
	}
	/**在初始化阶段将字典库传入form中*/
	private void init(CommDictPublicClassForm form){
		if (form.getSeqNo() == null || form.getSeqNo().equals(""))
			form.setSeqNo(commDictPublicClassDAO.getMaxSeqNo() + "");

	}
	/***/
	public void getDetail(CommDictPublicClassForm form){
	
	}
	/**获取查询结果之前初始化*/
	/**在进行查找之前将字典库传入form中*/
	private void searchInit(CommDictPublicClassForm form){
	}
	
}
