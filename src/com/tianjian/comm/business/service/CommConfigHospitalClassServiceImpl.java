package com.tianjian.comm.business.service;

import java.util.ArrayList;
import java.util.List;

import com.tianjian.comm.bean.CommConfigHospitalClass;
import com.tianjian.comm.business.ICommConfigHospitalClassService;
import com.tianjian.comm.dao.ICommConfigHospitalClassDAO;
import com.tianjian.comm.struts.form.CommConfigHospitalClassForm;
import com.tianjian.util.Converter;

public class CommConfigHospitalClassServiceImpl implements
		ICommConfigHospitalClassService {
	
	private ICommConfigHospitalClassDAO classDao;

	public void delete(String classCode) {
		// TODO Auto-generated method stub
		CommConfigHospitalClass comm=this.getClassDao().getByItemCode(classCode);
		
		this.getClassDao().delete(comm);
	}

	public CommConfigHospitalClass getByItemCode(String classCode) {
		// TODO Auto-generated method stub
		return this.getClassDao().getByItemCode(classCode);
	}

	public int getCount(CommConfigHospitalClassForm form) {
		// TODO Auto-generated method stub
		return this.getClassDao().getCount(form);
	}

	public void getSearch(CommConfigHospitalClassForm form, int count,
			int pageSize) {
		// TODO Auto-generated method stub
		String order="";
		if(form.getOrderNo().equals("1")){
			order ="classCode";
		}else if(form.getOrderNo().equals("2")){
			order ="className";
		}else if(form.getOrderNo().equals("3")){
			order ="seqNo";
		}else if(form.getOrderNo().equals("4")){
			order ="inputCode";
		}else{
			order ="classCode";
		}
		if(form.getAsc().equals("1")){
			order +=" desc";
		}else{
			order +=" asc";
		}
		form.setOrderNo(order);
		List<?> list=this.getClassDao().getSearch(form, count, pageSize);
		if(list!=null&&list.size()>0){
			List<String> seqNos=new ArrayList<String>();
			List<String> classCodes=new ArrayList<String>();
			List<String> classNames=new ArrayList<String>();
			List<String> inputCodes=new ArrayList<String>();
			
			for(int i=0;i<list.size();i++){
				CommConfigHospitalClass comm=(CommConfigHospitalClass) list.get(i);
				
				seqNos.add(Converter.toBlank(comm.getSeqNo()));
				classCodes.add(Converter.toBlank(comm.getClassCode()));
				classNames.add(Converter.toBlank(comm.getClassName()));
				inputCodes.add(Converter.toBlank(comm.getInputCode()));
			}
			form.setSeqNoList(seqNos);
			form.setClassCodeList(classCodes);
			form.setClassNameList(classNames);
			form.setInputCodeList(inputCodes);
		}
	}

	public void save(CommConfigHospitalClassForm form) {
		// TODO Auto-generated method stub
		CommConfigHospitalClass comm=new CommConfigHospitalClass();
		
		this.formToBean(form, comm);
		this.getClassDao().save(comm);
	}

	public void update(CommConfigHospitalClassForm form) {
		// TODO Auto-generated method stub

	}
	protected void formToBean(CommConfigHospitalClassForm form,CommConfigHospitalClass bean){
		bean.setClassCode(Converter.toBlank(form.getClassCode()));
		bean.setClassName(Converter.toBlank(form.getClassName()));
		bean.setComments(Converter.toBlank(form.getComments()));
		bean.setInputCode(Converter.toBlank(form.getInputCode()));
		bean.setSeqNo(Converter.toLong(form.getSeqNo()));
	}
	public ICommConfigHospitalClassDAO getClassDao() {
		return classDao;
	}

	public void setClassDao(ICommConfigHospitalClassDAO classDao) {
		this.classDao = classDao;
	}

}
