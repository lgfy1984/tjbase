package com.tianjian.comm.business.service;

import java.util.ArrayList;
import java.util.List;

import com.tianjian.comm.bean.CommConfigPopulationNature;
import com.tianjian.comm.business.ICommConfigPopulationNatureService;
import com.tianjian.comm.dao.ICommConfigPopulationNatureDAO;
import com.tianjian.comm.struts.form.CommConfigPopulationNatureForm;
import com.tianjian.util.Converter;

public class CommConfigPopulationNatureServiceImpl implements
		ICommConfigPopulationNatureService {
	
	private ICommConfigPopulationNatureDAO natureDao;

	public int getCount(CommConfigPopulationNatureForm form) {
		// TODO Auto-generated method stub
		return this.getNatureDao().getCount(form);
	}

	public void getSearch(CommConfigPopulationNatureForm form, int count,
			int pageSize) {
		// TODO Auto-generated method stub
		String order="";
		if(form.getOrderNo().equals("1")){
			order ="itemCode";
		}else if(form.getOrderNo().equals("2")){
			order ="itemName";
		}else if(form.getOrderNo().equals("3")){
			order ="seqNo";
		}else if(form.getOrderNo().equals("4")){
			order ="inputCode";
		}else{
			order ="itemCode";
		}
		if(form.getAsc().equals("1")){
			order +=" desc";
		}else{
			order +=" asc";
		}
		form.setOrderNo(order);
		List<?> list=this.getNatureDao().getSearch(form, count, pageSize);
		if(list!=null&&list.size()>0){
			List<String> seqNos=new ArrayList<String>();
			List<String> itemCodes=new ArrayList<String>();
			List<String> itemNames=new ArrayList<String>();
			List<String> inputCodes=new ArrayList<String>();
			
			for(int i=0;i<list.size();i++){
				CommConfigPopulationNature comm=(CommConfigPopulationNature) list.get(i);
				
				seqNos.add(Converter.toBlank(comm.getSeqNo()));
				itemCodes.add(comm.getItemCode());
				itemNames.add(comm.getItemName());
				inputCodes.add(comm.getInputCode());
			}
			form.setSeqNoList(seqNos);
			form.setItemCodeList(itemCodes);
			form.setItemNameList(itemNames);
			form.setInputCodeList(inputCodes);
		}
	}

	public ICommConfigPopulationNatureDAO getNatureDao() {
		return natureDao;
	}

	public void setNatureDao(ICommConfigPopulationNatureDAO natureDao) {
		this.natureDao = natureDao;
	}

	public void delete(String itemCode) {
		// TODO Auto-generated method stub
		CommConfigPopulationNature comm=this.getNatureDao().getByItemCode(itemCode);
		
		this.getNatureDao().delete(comm);
	}

	public CommConfigPopulationNature getByItemCode(String itemCode) {
		// TODO Auto-generated method stub
		return this.getNatureDao().getByItemCode(itemCode);
	}

	public void save(CommConfigPopulationNatureForm form) {
		// TODO Auto-generated method stub
		CommConfigPopulationNature comm=new CommConfigPopulationNature();
		this.formtobean(form, comm);
		this.getNatureDao().save(comm);
	}
	protected void formtobean(CommConfigPopulationNatureForm form,CommConfigPopulationNature bean ){
		bean.setSeqNo(Converter.toLong(form.getSeqNo()));
		bean.setItemCode(Converter.toBlank(form.getItemCode()));
		bean.setItemName(Converter.toBlank(form.getItemName()));
		bean.setInputCode(Converter.toBlank(form.getInputCode()));
		bean.setComments(Converter.toBlank(form.getComments()));
	}
	public void update(CommConfigPopulationNatureForm form) {
		// TODO Auto-generated method stub
		
	}

}
