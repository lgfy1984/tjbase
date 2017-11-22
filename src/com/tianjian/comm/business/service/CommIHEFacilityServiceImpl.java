package com.tianjian.comm.business.service;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import com.tianjian.comm.bean.CommConfigAbo;
import com.tianjian.comm.bean.CommIHEFacility;
import com.tianjian.comm.business.ICommConfigAboService;
import com.tianjian.comm.business.ICommIHEFacilityService;
import com.tianjian.comm.dao.ICommConfigAboDAO;
import com.tianjian.comm.dao.ICommIHEFacilityDAO;
import com.tianjian.comm.struts.form.CommConfigAboForm;
import com.tianjian.comm.struts.form.CommIHEFacilityForm;

public class CommIHEFacilityServiceImpl implements ICommIHEFacilityService {
	private static final Logger log = LogManager.getLogger(CommIHEFacilityServiceImpl.class);
	private ICommIHEFacilityDAO commIHEFacilityDAO;

	//------------------------DAO-- 声明结束---------------------------------------------------
	
	
	
	public void addInit(CommIHEFacilityForm form){
		init(form);
		form.setSeqNo(String.valueOf(this.commIHEFacilityDAO.seqNoMaker("CommIHEFacility")));
	}
	
	public void serchInit(CommIHEFacilityForm form){
		searchInit(form);
	}
	/**获取记录对象*/
	public CommIHEFacility checkData(String id){
		CommIHEFacility data = commIHEFacilityDAO.findById(id);
		return data;
	}
	/**提交保存*/
	public void save(CommIHEFacilityForm form){
		CommIHEFacility data = new CommIHEFacility();
		this.setData(form, data);
		commIHEFacilityDAO.save(data);
	}
	/**修改之前初始化*/
	public void updateInit(CommIHEFacilityForm form){
		CommIHEFacility data = commIHEFacilityDAO.findById(form.getIdHidden());
		this.setForm(form, data);
		init(form);
	}
	/**提交修改*/
	public void update(CommIHEFacilityForm form){
		CommIHEFacility data = commIHEFacilityDAO.findById(form.getIdHidden());
		this.setData(form, data);
		commIHEFacilityDAO.update(data);
	}
	/**提交察看详细*/
	public void showInit(CommIHEFacilityForm form){
		CommIHEFacility data = commIHEFacilityDAO.findById(form.getIdHidden());
		form = new CommIHEFacilityForm();
		this.setForm(form, data);
		init(form);
	}
	/**提交删除*/
	public void delete(CommIHEFacilityForm form){
		CommIHEFacility data = commIHEFacilityDAO.findById(form.getIdHidden());
		commIHEFacilityDAO.delete(data);
	}
	/**获取总记录数*/
	public int getCount(String facilityUniversalId, String facilityUniversalIdType, String facilityNamespaceId, String seqNo){
		return commIHEFacilityDAO.getCount(facilityUniversalId, facilityUniversalIdType, facilityNamespaceId, seqNo);
	}
	/**根据查询条件获取当前显示页的数据*/
	public void getSearch(CommIHEFacilityForm form, int curCount, int pageSize){
		try{
			 
		String order = "";
		if(form.getOrderNo().equals("1")){
			order = " a.facilityUniversalId";
		} else if(form.getOrderNo().equals("2")){
			order = " a.facilityUniversalIdType";
		} else if(form.getOrderNo().equals("3")){
			order = " a.seqNo";
		} else if(form.getOrderNo().equals("4")){
			order = " a.facilityNamespaceId";
	
		} else {
			order = " a.seqNo";
		}
		if(form.getAsc().equals("1")){
			order += " desc";
		} else {
			order += " asc";
		}
		List<?> list = commIHEFacilityDAO.getData(form.getFacilityUniversalId()
								, form.getFacilityUniversalIdType()
								, form.getFacilityNamespaceId()
								, form.getSeqNo()
								, order
								, curCount
								, pageSize);
		 
		if(list != null && list.size() > 0){			
			String[] ids = new String[list.size()];
			String[] facilityUniversalIds = new String[list.size()];
			String[] facilityUniversalIdTypes = new String[list.size()];
			String[] facilityNamespaceIds = new String[list.size()];
			String[] dateCreateds = new String[list.size()];
			String[] creatorIds = new String[list.size()];
			String[] seqNos = new String[list.size()];
			for(int i = 0; i < list.size(); i++){
				CommIHEFacility data = (CommIHEFacility)list.get(i);
				ids[i] = this.transNullToString(data.getId());
				facilityUniversalIds[i] = this.transNullToString(data.getFacilityUniversalId());
				facilityUniversalIdTypes[i] = this.transNullToString(data.getFacilityUniversalIdType());
				facilityNamespaceIds[i] = this.transNullToString(data.getFacilityNamespaceId());
				Date date = data.getDateCreated();
				String dateString = "";
				if(date != null){
					dateString = DateFormatUtils.format(date, "yyyy-MM-dd");
				}
				dateCreateds[i] = this.transNullToString(dateString);
				creatorIds[i] = this.transNullToString(data.getCreatorId());
				seqNos[i] = this.transNullToString(String.valueOf(data.getSeqNo()));
//				ids[i] = String.valueOf(transNullToString(((Object[])list.get(i))[0]));;
//				facilityUniversalIds[i] = String.valueOf(transNullToString(((Object[])list.get(i))[1]));
//				facilityUniversalIdTypes[i] = String.valueOf(transNullToString(((Object[])list.get(i))[2]));
//				facilityNamespaceIds[i] = String.valueOf(transNullToString(((Object[])list.get(i))[3]));
//				dateCreateds[i] = String.valueOf(transNullToString(((Object[])list.get(i))[4]));
//				creatorIds[i] = String.valueOf(transNullToString(((Object[])list.get(i))[5]));
//				seqNos[i] = ((Object[])list.get(i))[6] == null ? "0" : String.valueOf(((Object[])list.get(i))[6]);
				 
			}
			form.setIdList(ids);
			form.setFacilityUniversalIdList(facilityUniversalIds);
			form.setFacilityUniversalIdTypeList(facilityUniversalIdTypes);
			form.setFacilityNamespaceIdList(facilityNamespaceIds);
			form.setDateCreatedList(dateCreateds);
			form.setCreatorIdList(creatorIds);
			form.setSeqNoList(seqNos);
		 
		}
		 } catch (Exception e) { 
			 log.error("getSearch fail!",e);
			 e.printStackTrace();
	} 
	}
	/**构造data*/
	private void setData(CommIHEFacilityForm form, CommIHEFacility data){
		try{
			data.setFacilityUniversalId(transNullToString(form.getFacilityUniversalId()));
			data.setFacilityUniversalIdType(transNullToString(form.getFacilityUniversalIdType()));
			data.setFacilityNamespaceId(transNullToString(form.getFacilityNamespaceId()));
			data.setDateCreated(new Date(System.currentTimeMillis()));
			data.setCreatorId(transNullToString(form.getCreatorId()));
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
	private void setForm(CommIHEFacilityForm form, CommIHEFacility data){
		
		
		try{
			Date date = data.getDateCreated();
			String dateString = "";
			if(date != null){
				dateString = DateFormatUtils.format(date, "yyyy-MM-dd");
			}
			form.setId(this.transNullToString(data.getId()));
			form.setFacilityUniversalId(transNullToString(data.getFacilityUniversalId()));
			form.setFacilityUniversalIdType(transNullToString(data.getFacilityUniversalIdType()));
			form.setFacilityNamespaceId(transNullToString(data.getFacilityNamespaceId()));
			form.setDateCreated(transNullToString(dateString));
			form.setCreatorId(transNullToString(data.getCreatorId())); 
			form.setSeqNo(data.getSeqNo() == 0 ? "0" : String.valueOf(data.getSeqNo()));
		 } catch (Exception e) { 
			 log.error("setForm fail!",e);
			 e.printStackTrace();
		} 
	}
	/**在初始化阶段将字典库传入form中*/
	private void init(CommIHEFacilityForm form){
		
	}
	/***/
	public void getDetail(CommIHEFacilityForm form){
	
	}
	/**获取查询结果之前初始化*/
	/**在进行查找之前将字典库传入form中*/
	private void searchInit(CommIHEFacilityForm form){
	}

	public ICommIHEFacilityDAO getCommIHEFacilityDAO() {
		return commIHEFacilityDAO;
	}

	public void setCommIHEFacilityDAO(ICommIHEFacilityDAO commIHEFacilityDAO) {
		this.commIHEFacilityDAO = commIHEFacilityDAO;
	}

	public static void main(String args[]){
		SimpleDateFormat sp =	new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = new Date();
		System.out.println(sp.format(date));
	}
	
}
