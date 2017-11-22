package com.tianjian.comm.business.service;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import com.tianjian.comm.bean.CommMenuHelp;
import com.tianjian.comm.business.ICommMenuHelpService;
import com.tianjian.comm.dao.ICommMenuHelpDAO;
import com.tianjian.comm.struts.form.CommMenuHelpForm;

public class CommMenuHelpServiceImpl implements ICommMenuHelpService {
	private static final Logger log = LogManager.getLogger(CommMenuHelpServiceImpl.class);
	private ICommMenuHelpDAO commMenuHelpDAO;

	//------------------------DAO-- 声明结束---------------------------------------------------
	public ICommMenuHelpDAO getCommMenuHelpDAO() {
		return commMenuHelpDAO;
	}

	public void setCommMenuHelpDAO(ICommMenuHelpDAO commMenuHelpDAO) {
		this.commMenuHelpDAO = commMenuHelpDAO;
	}
	
	public void addInit(CommMenuHelpForm form){
		init(form);
		form.setSeqNo(String.valueOf(this.commMenuHelpDAO.seqNoMaker("CommMenuHelp")));
	}
	
	public void serchInit(CommMenuHelpForm form){
		searchInit(form);
	}
	/**获取记录对象*/
	public CommMenuHelp checkData(String id){
		CommMenuHelp data = commMenuHelpDAO.findById(id);
		return data;
	}
	/**提交保存*/
	public void save(CommMenuHelpForm form){
		CommMenuHelp data = new CommMenuHelp();
		this.setData(form, data);
		commMenuHelpDAO.save(data);
	}
	/**修改之前初始化*/
	public void updateInit(CommMenuHelpForm form){
		CommMenuHelp data = commMenuHelpDAO.findById(form.getId());
		this.setForm(form, data);
		init(form);
	}
	
	public void help(CommMenuHelpForm form){
		CommMenuHelp data = commMenuHelpDAO.findByMenuFlag(form.getMenuFlag());
		if(data!=null&&data.getId().length()>0){
			form.setHelp(data);
		}
	}
	
	/**提交修改*/
	public void update(CommMenuHelpForm form){
		CommMenuHelp data = commMenuHelpDAO.findById(form.getIdHidden());
		this.setData(form, data);
		commMenuHelpDAO.update(data);
	}
	/**提交察看详细*/
	public void showInit(CommMenuHelpForm form){
		CommMenuHelp data = commMenuHelpDAO.findById(form.getId());
		form = new CommMenuHelpForm();
		this.setForm(form, data);
		init(form);
	}
	/**提交删除*/
	public void delete(CommMenuHelpForm form){
		CommMenuHelp data = commMenuHelpDAO.findById(form.getIdHidden());
		commMenuHelpDAO.delete(data);
	}
	/**获取总记录数*/
	public int getCount(String itemCode, String itemName, String inputCode, String seqNo){
		return commMenuHelpDAO.getCount(itemCode, itemName, inputCode, seqNo);
	}
	/**根据查询条件获取当前显示页的数据*/
	public void getSearch(CommMenuHelpForm form, int curCount, int pageSize){
		try{
			String order = "";
			if(form.getOrderNo().equals("1")){
				order = " a.seqNo";
			} else if(form.getOrderNo().equals("2")){
				order = " a.menuName";
			} else if(form.getOrderNo().equals("3")){
				order = " a.menuHelpTitle";
			} else if(form.getOrderNo().equals("4")){
				order = " a.menuFlag";
			} else if(form.getOrderNo().equals("5")){
				order = " a.createDate";
			} else {
				order = " a.seqNo";
			}
			
			if(form.getAsc().equals("1")){
				order += " desc";
			} else {
				order += " asc";
			}
		 
			List<?> list = commMenuHelpDAO.getData(form.getMenuName()
									, form.getMenuHelpTitle()
									, form.getCreateDate()
									, form.getSeqNo()
									, order
									, curCount
									, pageSize);
			 
			if(list != null && list.size() > 0){
				String[] idList = new String[list.size()];
				String[] menuNameList = new String[list.size()];
				String[] menuHelpTitleList = new String[list.size()];
				String[] createDateList = new String[list.size()];
				String[] menuHelpCommentList = new String[list.size()];
				String[] seqNoList = new String[list.size()];
				String[] menuFlagList = new String[list.size()];
				for(int i = 0; i < list.size(); i++){
					CommMenuHelp bean = (CommMenuHelp)list.get(i);
					idList[i] = this.transNullToString(bean.getId());
					menuNameList[i] = this.transNullToString(bean.getMenuName());
					menuHelpTitleList[i] = this.transNullToString(bean.getMenuHelpTitle());
					if(bean.getCreateDate()!=null){
						SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
						createDateList[i] = format.format(bean.getCreateDate());
					}
					menuHelpCommentList[i] = this.transNullToString(bean.getMenuHelpComment());
					seqNoList[i] = (bean.getSeqNo() == null ? "0" : String.valueOf(bean.getSeqNo()));
					menuFlagList[i] = this.transNullToString(bean.getMenuFlag());
				}
				form.setIdList(idList);
				form.setMenuNameList(menuNameList);
				form.setMenuHelpTitleList(menuHelpTitleList);
				form.setCreateDateList(createDateList);
				form.setMenuHelpCommentList(menuHelpCommentList);
				form.setSeqNoList(seqNoList);
			    form.setMenuFlagList(menuFlagList);
			}
		 } catch (Exception e) { 
			 log.error("getSearch fail!",e);
			 e.printStackTrace();
	} 
	}
	/**构造data*/
	private void setData(CommMenuHelpForm form, CommMenuHelp data){
		try{
			data.setMenuName (transNullToString(form.getMenuName()));
			data.setMenuHelpTitle (transNullToString(form.getMenuHelpTitle()));
			data.setMenuHelpContent (transNullToString(form.getMenuHelpContent()));
			data.setMenuHelpComment (transNullToString(form.getMenuHelpComment()));
			data.setMenuFlag(this.transNullToString(form.getMenuFlag()));
			data.setSeqNo     (Long.valueOf((form.getSeqNo() == null || form.getSeqNo().trim() == "") ? "0" : form.getSeqNo()));
			data.setCreateDate(new Date());
			data.setCreateUserId(this.transNullToString(form.getCreateUserId()));
			data.setCreateUserName(this.transNullToString(form.getCreateUserName()));
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
	private void setForm(CommMenuHelpForm form, CommMenuHelp data){
		try{
			form.setMenuName (transNullToString(data.getMenuName()));
			form.setMenuHelpTitle (transNullToString(data.getMenuHelpTitle()));
			form.setMenuHelpContent(transNullToString(data.getMenuHelpContent()));
			form.setMenuHelpComment(transNullToString(data.getMenuHelpComment())); 
			form.setMenuFlag(this.transNullToString(data.getMenuFlag()));
			form.setSeqNo(data.getSeqNo() == null ? "0" : String.valueOf(data.getSeqNo()));
		 } catch (Exception e) { 
			 log.error("setForm fail!",e);
			 e.printStackTrace();
		} 
	}
	/**在初始化阶段将字典库传入form中*/
	private void init(CommMenuHelpForm form){
		
	}
	/***/
	public void getDetail(CommMenuHelpForm form){
	
	}
	/**获取查询结果之前初始化*/
	/**在进行查找之前将字典库传入form中*/
	private void searchInit(CommMenuHelpForm form){
	}

	
}
