package com.tianjian.comm.business.service;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.tianjian.comm.bean.CommGbCoptp;
import com.tianjian.comm.business.ICommGbCoptpService;
import com.tianjian.comm.dao.ICommGbCoptpDAO;
import com.tianjian.comm.struts.form.CommGbCoptpForm;

public class CommGbCoptpServiceImpl implements
		ICommGbCoptpService {

	private static final Logger log = LogManager.getLogger(CommGbCoptpServiceImpl.class);
	//------------------------DAO-- 声明开---------------------------------------------------
	private ICommGbCoptpDAO commGbCoptpDAO;
	
	public ICommGbCoptpDAO getCommGbCoptpDAO() {
		return commGbCoptpDAO;
	}
	public void setCommGbCoptpDAO(
			ICommGbCoptpDAO commGbCoptpDAO) {
		this.commGbCoptpDAO = commGbCoptpDAO;
	}

	//------------------------DAO-- 声明结束---------------------------------------------------
	
	public void addInit(CommGbCoptpForm form) {
		init(form);
	}
	
	public void serchInit(CommGbCoptpForm form) {
		searchInit(form);
	}
	/**获取记录对象*/
	public CommGbCoptp checkData(String id) {
		return this.commGbCoptpDAO.findById(id);
	}
	/**提交保存*/
	public void save(CommGbCoptpForm form) {
		CommGbCoptp data =new CommGbCoptp();
		this.setData(form,data);
		this.commGbCoptpDAO.save(data);
	}
	/**修改之前初始化*/
	public void updateInit(CommGbCoptpForm form) {
		CommGbCoptp data=this.commGbCoptpDAO.findById(form.getItemCodeHidden());
		this.setForm(form,data);
		init(form);
	}
	/**提交修改*/
	public void update(CommGbCoptpForm form) {
		try {
			CommGbCoptp data = this.commGbCoptpDAO.findById(form
					.getItemCodeHidden());

			this.setData(form, data);
			log.debug("update success");
			this.commGbCoptpDAO.update(data);
		} catch (Exception e) {
			log.error("updata fail",e);
			e.printStackTrace();
			
		}
	}
	/**提交察看详细*/
	public void showInit(CommGbCoptpForm form) {
		CommGbCoptp data =this.commGbCoptpDAO.findById(form.getItemCodeHidden());
		this.setForm(form, data);
		init(form);
	}
	/**提交删除*/
	public void delete(CommGbCoptpForm form) {
		CommGbCoptp data =this.commGbCoptpDAO.findById(form.getItemCodeHidden());
		this.commGbCoptpDAO.delete(data);
	}
	/**获取总记录数*/
	public int getCount(String itemCode, String itemName, String inputCode,
			String seqNo) {
		return this.commGbCoptpDAO.getCount(itemCode, itemName, inputCode, seqNo);
	}

	/**根据查询条件获取当前显示页的数据*/
	public void getSearch(CommGbCoptpForm form, int curCount,
			int pageSize) {
		try{
			 
			String order = "";
			if(form.getOrderNo() != null ){
				if(form.getOrderNo().equals("1")){
					order = " a.itemCode";
				} else if(form.getOrderNo().equals("2")){
					order = " a.itemName";
				} else if(form.getOrderNo().equals("3")){
					order = " a.seqNo";
				} else if(form.getOrderNo().equals("4")){
					order = " a.levelFlag";
				} else if(form.getOrderNo().equals("5")){
					order = " a.parentItemCode";
				} else if(form.getOrderNo().equals("6")){
					order = " a.inputCode";
			
				} else {
					order = " a.seqNo";
				}
				
				if(form.getAsc().equals("1")){
					order += " desc";
				} else {
					order += " asc";
				}
			}
			List<?> list = this.commGbCoptpDAO.getData(form.getItemCode()
									, form.getItemName()
									, form.getInputCode()
									, form.getSeqNo()
									, order
									, curCount
									, pageSize);
			 
			if(list != null && list.size() > 0){
				String[] itemCodes = new String[list.size()];
				String[] itemNames = new String[list.size()];
				String[] inputCodes = new String[list.size()];
				String[] levelFlags = new String[list.size()];
				String[] parentItemCodes = new String[list.size()];
				String[] parentItemCodeNameList = new String[list.size()];
				String[] comments = new String[list.size()];
				String[] seqNos = new String[list.size()];
				for(int i = 0; i < list.size(); i++){
					if(((Object[])list.get(i))[0]!=null){
						itemCodes[i] = String.valueOf(transNullToString(((Object[])list.get(i))[0]));
					}else{
						itemCodes[i] = "";
					}
					
					if(((Object[])list.get(i))[1]!=null){
						itemNames[i] = String.valueOf(transNullToString(((Object[])list.get(i))[1]));
					}else{
						itemNames[i] = "";
					}
					
					if(((Object[])list.get(i))[2]!=null){
						inputCodes[i] = String.valueOf(transNullToString(((Object[])list.get(i))[2]));
					}else{
						inputCodes[i] = "";
					}
					
					if(((Object[])list.get(i))[3]!=null){
						levelFlags[i] = ((Object[])list.get(i))[3] == null ? "0" : String.valueOf(((Object[])list.get(i))[3]);
					}else{
						levelFlags[i] = "";
					}
					
					if(((Object[])list.get(i))[4]!=null){
						parentItemCodes[i] = String.valueOf(transNullToString(((Object[])list.get(i))[4]));
						parentItemCodeNameList[i] = this.commGbCoptpDAO.findNameByParentId(parentItemCodes[i]);
					}else{
						parentItemCodes[i] = "";
						parentItemCodeNameList[i] ="";
					}
					
					if(((Object[])list.get(i))[5]!=null){
						comments[i] = String.valueOf(transNullToString(((Object[])list.get(i))[5]));
					}else{
						comments[i] = "";
					}
					
					if(((Object[])list.get(i))[6]!=null){
						seqNos[i] = ((Object[])list.get(i))[6] == null ? "0" : String.valueOf(((Object[])list.get(i))[6]);
					}else{
						seqNos[i] = "";
					}
				}
				form.setItemCodeList(itemCodes);
				form.setItemNameList(itemNames);
				form.setInputCodeList(inputCodes);
				form.setLevelFlagList(levelFlags);
				form.setParentItemCodeNameList(parentItemCodeNameList);
				form.setCommentsList(comments);
				form.setSeqNoList(seqNos);
			}
			 } catch (Exception e) { 
				 log.error("getSearch fail!",e);
				 e.printStackTrace();
		}
		
	}
	/**获取查询结果之前初始化*/
	public void getDetail(CommGbCoptpForm form) {

	}

	//内部方法
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
	/**在初始化阶段将字典库传入form中*/
	private void init(CommGbCoptpForm form){
		//取得"所属代码的名称
		List<?> list = this.commGbCoptpDAO.getCodeAndName();
		
		if(list != null && list.size() > 0){
			int size = list.size();
			String[] parentItemCodes = new String[size+1];
			String[] parentItemNames = new String[size+1];
			parentItemCodes[0] = "";
			parentItemNames[0] = "";
			for(int i = 0; i < size; i++){
				CommGbCoptp data = (CommGbCoptp)list.get(i);
				if(data.getItemCode() != null)
					parentItemCodes[i+1] = data.getItemCode().toString();
				else{
					parentItemCodes[i+1] = "";
				}
				parentItemNames[i+1] = data.getItemName().toString();
			}
			form.setParentItemCodes(parentItemCodes);
			form.setParentItemNames(parentItemNames);
		}
		if (form.getSeqNo() == null || form.getSeqNo().equals(""))
			form.setSeqNo(commGbCoptpDAO.getMaxSeqNo() + "");

	}
	
	/**在进行查找之前将字典库传入form中*/
	private void searchInit(CommGbCoptpForm form){
		
	}
	/**构造data*/
	private void setData(CommGbCoptpForm form,CommGbCoptp data){
		try{
			data.setItemCode (transNullToString(form.getItemCode()));
			data.setItemName (transNullToString(form.getItemName()));
			data.setInputCode (transNullToString(form.getInputCode()));
			data.setComments  (transNullToString(form.getComments      ()));
			data.setSeqNo     (Long.valueOf((form.getSeqNo() == null || form.getSeqNo().trim() == "") ? "0" : form.getSeqNo()));
			//判断是否选中了所属代码,不选则为1,否则在原有的值上加1.注意可能存在非数字类型的值
			if(form.getParentItemCode() != null){
				data.setParentItemCode(form.getParentItemCode());
				if(data.getParentItemCode().trim().equals("")){
					data.setLevelFlag(2L);
				}else{
					String levelFlag = this.commGbCoptpDAO.findLevelFlagByParentId(data.getParentItemCode());
					System.out.println("levelFlag "+levelFlag);
					data.setLevelFlag(Long.valueOf(levelFlag)+1L);
				}
			}else{
				data.setParentItemCode("");
				data.setLevelFlag(1L);
			}
		} catch (Exception e) { 
			 log.error("setData fail!",e);
			 e.printStackTrace();
		} 
	}
	/**在传递数据到页面之前构造form*/
	private void setForm(CommGbCoptpForm form,CommGbCoptp data){
		try{
			form.setItemCodeHidden (transNullToString(data.getItemCode()));
			form.setItemCode (transNullToString(data.getItemCode()));
			form.setItemName (transNullToString(data.getItemName()));
			form.setInputCode(transNullToString(data.getInputCode()));
			form.setLevelFlag(data.getLevelFlag().toString());
			form.setParentItemCode(transNullToString(data.getParentItemCode()));
			form.setParentItemCodeName(this.commGbCoptpDAO.findNameByParentId(form.getParentItemCode()));
			form.setComments(transNullToString(data.getComments())); 
			form.setSeqNo(data.getSeqNo() == null ? "0" : String.valueOf(data.getSeqNo()));
		 } catch (Exception e) { 
			 log.error("setForm fail!",e);
			 e.printStackTrace();
		} 
	}
	
}
