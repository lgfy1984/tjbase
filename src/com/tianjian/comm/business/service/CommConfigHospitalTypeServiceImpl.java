package com.tianjian.comm.business.service;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.tianjian.comm.bean.CommConfigHospitalType;
import com.tianjian.comm.business.ICommConfigHospitalTypeService;
import com.tianjian.comm.dao.ICommConfigHospitalTypeDAO;
import com.tianjian.comm.struts.form.CommConfigHospitalTypeForm;
import com.tianjian.comm.struts.servlet.CommInit;

public class CommConfigHospitalTypeServiceImpl implements
		ICommConfigHospitalTypeService {
	private ICommConfigHospitalTypeDAO commConfigHospitalTypeDAO;

	public ICommConfigHospitalTypeDAO getCommConfigHospitalTypeDAO() {
		return commConfigHospitalTypeDAO;
	}

	public void setCommConfigHospitalTypeDAO(
			ICommConfigHospitalTypeDAO commConfigHospitalTypeDAO) {
		this.commConfigHospitalTypeDAO = commConfigHospitalTypeDAO;
	}
	
	//private String COMMCONFIGHOSPITALTYPELEVEL_1 = CommInit.getProperty("COMMCONFIGHOSPITALTYPELEVEL_1");
	//private String COMMCONFIGHOSPITALTYPELEVEL_2 = CommInit.getProperty("COMMCONFIGHOSPITALTYPELEVEL_2");
	//private String COMMCONFIGHOSPITALTYPELEVEL_3 = CommInit.getProperty("COMMCONFIGHOSPITALTYPELEVEL_3");
	//private String COMMCONFIGHOSPITALTYPELEVEL_ALL = CommInit.getProperty("COMMCONFIGHOSPITALTYPELEVEL_ALL");
	/***/
	public void setData(CommConfigHospitalTypeForm form,CommConfigHospitalType data,HttpServletRequest request){
		ServletContext application = request.getSession().getServletContext();
		data.setItemCode(this.transNullToString(form.getItemCode()));
		data.setItemName(this.transNullToString(form.getItemName()));
		data.setInputCode(this.transNullToString(form.getInputCode()));
		data.setComments(this.transNullToString(form.getComments()));
		if(form.getLevelCode_1()!=null&&form.getLevelCode_2()!=null
				&&form.getLevelCode_1().trim().length()>0&&form.getLevelCode_2().trim().length()>0){
			//data.setLevelFlag(Long.valueOf(COMMCONFIGHOSPITALTYPELEVEL_3));
			data.setLevelFlag(Long.valueOf((String)application.getAttribute("comm.COMMCONFIGHOSPITALTYPELEVEL_3")));
			data.setParentItemCode(form.getLevelCode_2());
		}else if(form.getLevelCode_1()!=null&&form.getLevelCode_1().trim().length()>0){
			//data.setLevelFlag(Long.valueOf(COMMCONFIGHOSPITALTYPELEVEL_2));
			data.setLevelFlag(Long.valueOf((String)application.getAttribute("comm.COMMCONFIGHOSPITALTYPELEVEL_2")));
			data.setParentItemCode(form.getLevelCode_1());
		}else{
			data.setLevelFlag(Long.valueOf((String)application.getAttribute("comm.COMMCONFIGHOSPITALTYPELEVEL_3")));
			data.setParentItemCode("");
		}
		
		try {
			data.setSeqNo(Long.valueOf(this.transNullToString(form.getSeqNo())));
		} catch (Exception e) {
			data.setSeqNo(this.commConfigHospitalTypeDAO.seqNoMaker("CommConfigHospitalType"));
		}
	}
	/***/
	public void setForm(CommConfigHospitalTypeForm form,CommConfigHospitalType data,HttpServletRequest request){
		ServletContext application = request.getSession().getServletContext();
		form.setItemCode(this.transNullToString(data.getItemCode()));
		form.setItemName(this.transNullToString(data.getItemName()));
		form.setInputCode(this.transNullToString(data.getInputCode()));
		form.setComments(this.transNullToString(data.getComments()));
		form.setLevelFlag(this.transNullToString(data.getLevelFlag()));
		form.setParentItemCode(this.transNullToString(data.getParentItemCode()));
		
		//if(data.getLevelFlag().equals(Long.valueOf(COMMCONFIGHOSPITALTYPELEVEL_1))){
		if(data.getLevelFlag().equals(Long.valueOf((String)application.getAttribute("comm.COMMCONFIGHOSPITALTYPELEVEL_1")))){
			form.setLevelCode_1("");
			form.setLevelCode_2("");
			form.setParentItemName("");
		//}else if(data.getLevelFlag().equals(Long.valueOf(COMMCONFIGHOSPITALTYPELEVEL_2))){
		}else if(data.getLevelFlag().equals(Long.valueOf((String)application.getAttribute("comm.COMMCONFIGHOSPITALTYPELEVEL_2")))){
			form.setLevelCode_1(data.getParentItemCode());
			form.setLevelCode_2("");
			form.setParentItemName(this.transNullToString(this.commConfigHospitalTypeDAO.findP2ValueByP1("CommConfigHospitalType", "itemCode", data.getParentItemCode(), "itemName")));
		//}else if(data.getLevelFlag().equals(Long.valueOf(COMMCONFIGHOSPITALTYPELEVEL_3))){
		}else if(data.getLevelFlag().equals(Long.valueOf((String)application.getAttribute("comm.COMMCONFIGHOSPITALTYPELEVEL_3")))){
			form.setLevelCode_1(this.commConfigHospitalTypeDAO.findP2ValueByP1("CommConfigHospitalType", "itemCode",data.getParentItemCode() , "parentItemCode"));
			form.setLevelCode_2(data.getParentItemCode());
			form.setParentItemName(this.transNullToString(this.commConfigHospitalTypeDAO.findP2ValueByP1("CommConfigHospitalType", "itemCode", data.getParentItemCode(), "itemName")));
		}
		
		form.setSeqNo(this.transNullToZero(data.getSeqNo()));
	}
	/**查找itemcode是否已经存在*/
	public  CommConfigHospitalType findByItemCode(String itemCode){
		return this.commConfigHospitalTypeDAO.findByItemCode(itemCode);
	}
	/**set 中类*/
	public void setLevel2(CommConfigHospitalTypeForm form,HttpServletRequest request){
		List<?> level2 = null;
		String[] levelCode_2List = null;
		String[] levelName_2List = null;
		if(form.getLevelCode_1()!=null&&form.getLevelCode_1().trim().length()>0){
			level2 = this.commConfigHospitalTypeDAO.getByParentItemCode(form.getLevelCode_1().trim(),request);
			if(level2!=null&&level2.size()>0){
				levelCode_2List = new String[level2.size()+1];
				levelName_2List = new String[level2.size()+1];
				levelCode_2List[0] = "";
				levelName_2List[0] = "";
				for(int i = 0;i<level2.size();i++){
					levelCode_2List[i+1] = ((CommConfigHospitalType)level2.get(i)).getItemCode();
					levelName_2List[i+1] = ((CommConfigHospitalType)level2.get(i)).getItemName();
				}
			}else{
				levelCode_2List = new String[1];
				levelName_2List = new String[1];
				levelCode_2List[0] = "";
				levelName_2List[0] = "";
			}
		}else{
			levelCode_2List = new String[1];
			levelName_2List = new String[1];
			levelCode_2List[0] = "";
			levelName_2List[0] = "";
		}
		form.setLevelCode_2List(levelCode_2List);
		form.setLevelName_2List(levelName_2List);
	}
	/**初始化*/
	public void init(CommConfigHospitalTypeForm form,HttpServletRequest request){
		List<?> level1 = this.commConfigHospitalTypeDAO.getByParentItemCode("",request);
		List<?> level2 = null;
		String[] levelCode_1List = null;
		String[] levelName_1List = null;
		String[] levelCode_2List = null;
		String[] levelName_2List = null;
		
		if(level1!=null&&level1.size()>0){
			levelCode_1List = new String[level1.size()+1];
			levelName_1List = new String[level1.size()+1];
			levelCode_1List[0] = "";
			levelName_1List[0] = "";
			for(int i = 0;i<level1.size();i++){
				levelCode_1List[i+1] = ((CommConfigHospitalType)level1.get(i)).getItemCode();
				levelName_1List[i+1] = ((CommConfigHospitalType)level1.get(i)).getItemName();
			}
		}else{
			levelCode_1List = new String[1];
			levelName_1List = new String[1];
			levelCode_1List[0] = "";
			levelName_1List[0] = "";
		}
		
		if(form.getLevelCode_1()!=null&&form.getLevelCode_1().trim().length()>0){
			level2 = this.commConfigHospitalTypeDAO.getByParentItemCode(form.getLevelCode_1().trim(),request);
			if(level2!=null&&level2.size()>0){
				levelCode_2List = new String[level2.size()+1];
				levelName_2List = new String[level2.size()+1];
				levelCode_2List[0] = "";
				levelName_2List[0] = "";
				for(int i = 0;i<level2.size();i++){
					levelCode_2List[i+1] = ((CommConfigHospitalType)level2.get(i)).getItemCode();
					levelName_2List[i+1] = ((CommConfigHospitalType)level2.get(i)).getItemName();
				}
			}else{
				levelCode_2List = new String[1];
				levelName_2List = new String[1];
				levelCode_2List[0] = "";
				levelName_2List[0] = "";
			}
		}else{
			levelCode_2List = new String[1];
			levelName_2List = new String[1];
			levelCode_2List[0] = "";
			levelName_2List[0] = "";
		}
		
		form.setLevelCode_1List(levelCode_1List);
		form.setLevelName_1List(levelName_1List);
		form.setLevelCode_2List(levelCode_2List);
		form.setLevelName_2List(levelName_2List);
	}
	
	/**添加初始化*/
	public void addInit(CommConfigHospitalTypeForm form,HttpServletRequest request){
		this.init(form,request);
		form.setSeqNo(String.valueOf(this.commConfigHospitalTypeDAO.seqNoMaker("CommConfigHospitalType")));
	}
	/**更新初始化*/
	public void updateInit(CommConfigHospitalTypeForm form,HttpServletRequest request){
		this.init(form,request);
	}
	/**保存*/
	public void save(CommConfigHospitalTypeForm form,HttpServletRequest request){
		CommConfigHospitalType data = new CommConfigHospitalType();
		this.setData(form, data,request);
		this.commConfigHospitalTypeDAO.save(data);
	}
	/**更新*/
	public void update(CommConfigHospitalTypeForm form,HttpServletRequest request){
		CommConfigHospitalType data = this.findByItemCode(form.getItemCode().trim());
		this.setData(form, data,request);
		this.commConfigHospitalTypeDAO.update(data);
	}
	/**删除*/
	public void delete(CommConfigHospitalTypeForm form){
		CommConfigHospitalType data = this.findByItemCode(form.getItemCodeHidden().trim());
		this.commConfigHospitalTypeDAO.delete(data);
	}
	/**根据条件查找记录*/
	public void getSearch(CommConfigHospitalTypeForm form,int curCount,
			int pageSize){
		String order = "";
		if(form.getOrderNo().equals("1")){
			order = " a.itemCode";
		} else if(form.getOrderNo().equals("2")){
			order = " a.itemName";
		} else if(form.getOrderNo().equals("3")){
			order = " a.seqNo";
		} else if(form.getOrderNo().equals("4")){
			order = " a.inputCode";
		} else if(form.getOrderNo().equals("5")){
			order = " a.levelFlag";
		} else if(form.getOrderNo().equals("6")){
			order = " a.parentItemCode";
		} else {
			order = " a.seqNo";
		}
		
		if(form.getAsc().equals("1")){
			order += " desc";
		} else {
			order += " asc";
		}
		
		List<?> list = this.commConfigHospitalTypeDAO.getData(form.getItemCodeQuery(), form.getItemNameQuery(), form.getInputCodeQuery(), form.getSeqNoQuery(), order, curCount, pageSize);
		if(list!=null&&list.size()>0){
			String[] itemCodes = new String[list.size()];
			String[] itemNames = new String[list.size()];
			String[] inputCodes = new String[list.size()];
			String[] levelFlags = new String[list.size()];
			String[] parentItemCodes = new String[list.size()];
			String[] commentss = new String[list.size()];
			String[] seqNos = new String[list.size()];
			
			for(int i = 0; i < list.size(); i++){
				itemCodes[i] = String.valueOf(transNullToString(((Object[])list.get(i))[0]));
				itemNames[i] = String.valueOf(transNullToString(((Object[])list.get(i))[1]));
				inputCodes[i] = String.valueOf(transNullToString(((Object[])list.get(i))[2]));
				levelFlags[i] = String.valueOf(transNullToString(((Object[])list.get(i))[3]));
				parentItemCodes[i] = String.valueOf(transNullToString(((Object[])list.get(i))[4]));           
				commentss[i] = String.valueOf(transNullToString(((Object[])list.get(i))[5]));
				seqNos[i] = ((Object[])list.get(i))[6] == null ? "0" : String.valueOf(((Object[])list.get(i))[6]);
			}
			
			form.setItemCodeList(itemCodes);
			form.setItemNameList(itemNames);
			form.setInputCodeList(inputCodes);
			form.setLevelFlagList(levelFlags);
			form.setParentItemCodeList(parentItemCodes);
			form.setCommentsList(commentss);
			form.setSeqNoList(seqNos);
		}
		
		
	}
	/**根据条件得到的记录条数*/
	public int getCount(CommConfigHospitalTypeForm form){
		return this.commConfigHospitalTypeDAO.getCount(form.getItemCodeQuery(), form.getItemNameQuery(), form.getInputCodeQuery(), form.getSeqNoQuery());
	}
	/**弹出层所用*/
	public String getXml(String flag,String input,String hspTypeLevel, HttpServletRequest request){
		StringBuffer buffer = new StringBuffer();
		ServletContext application = request.getSession().getServletContext();
		//List<?> list = this.commConfigHospitalTypeDAO.findHspTypeList(flag, input, hspTypeLevel, Integer.valueOf(CommInit.getProperty("CURRECORD_TANCHUCENG")),
		//		CommInit.getPageSize("PAGE_SIZE_TANCHUCENG"));
		List<?> list = this.commConfigHospitalTypeDAO.findHspTypeList(flag, input, hspTypeLevel, Integer.valueOf((String)application.getAttribute("comm.CURRECORD_TANCHUCENG")),
				Integer.valueOf((String)application.getAttribute("comm.PAGE_SIZE_TANCHUCENG")),request);
		buffer.append("<response>");
		for(int i =0;i<list.size();i++){
			CommConfigHospitalType charbean = (CommConfigHospitalType)list.get(i);
			 //-----以下xml标签不要改变--------------------------------------------------------------
			buffer.append("<ress>");
			buffer.append("<resInputCode>" + charbean.getInputCode() + "</resInputCode>");
			buffer.append("<resItemCode>" + charbean.getItemCode() + "</resItemCode>");
			buffer.append("<resItemName>" + charbean.getItemName() + "</resItemName>");
			buffer.append("<resItemId>" + charbean.getItemCode() + "</resItemId>");
			
			
			buffer.append("</ress>");
		}
		buffer.append("</response>");
		
		return buffer.toString();
	}
	/***/
	public String transNullToString(Object obj) {
		String temp = "";
		if (obj != null) {
			temp = String.valueOf(obj).trim();
		}
		return temp;
	}
	/***/
	public String transNullToZero(Object obj) {
		String temp = "0";
		if (obj != null) {
			temp = String.valueOf(obj).trim();
		}
		return temp;
	}

	
}
