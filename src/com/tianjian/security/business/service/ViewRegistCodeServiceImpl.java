package com.tianjian.security.business.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.tianjian.security.business.IViewRegistCodeService;
import com.tianjian.security.dao.ISecuritySystemUsersDAO;
import com.tianjian.security.struts.form.ViewRegistCodeForm;
/**
 * 医疗人员查询注册码用Service实现
 * @author DZENALL
 */
public class ViewRegistCodeServiceImpl implements IViewRegistCodeService {

	/*--------------------------------------
	 * 定义接口并引入接口
	 ---------------------------------------*/
	private ISecuritySystemUsersDAO securitySystemUsersDAO;	

	public ISecuritySystemUsersDAO getSecuritySystemUsersDAO() {
		return securitySystemUsersDAO;
	}
	public void setSecuritySystemUsersDAO(
			ISecuritySystemUsersDAO securitySystemUsersDAO) {
		this.securitySystemUsersDAO = securitySystemUsersDAO;
	}
	/*------------------------------------------
	 * Service实现方法
	 *-----------------------------------------*/
	public void viewQuery(ViewRegistCodeForm form) {
		List list = this.getViewList(form.getQueryType(), form.getQueryValue());
		this.setForm(form, list);
	}

	/*------------------------------------------
	 * Service辅助方法
	 *------------------------------------------*/
	/**
	 * 用特定构成的List中的数据填充ActionForm
	 * @param form ViewRegistCodeForm
	 * @param list 具有特定构成的List
	 */
	private void setForm(ViewRegistCodeForm form, List list) {		
		String[] idList;
		String[] nameList;
		String[] staffCodeList;
		String[] idNoList;
		String[] registCodeList;
		String[] isRegistedList;
		String[] startTimeList;
		if(list != null && list.size() > 0){//如果List中有数据，则取之
			idList = new String[list.size()];
			nameList = new String[list.size()];
			staffCodeList = new String[list.size()];
			idNoList = new String[list.size()];
			registCodeList = new String[list.size()];
			isRegistedList = new String[list.size()];
			startTimeList = new String[list.size()];
			
			for(int i=0; i<list.size(); i++){
				idList[i] = this.transNullToString(((Object[])list.get(i))[0]);
				nameList[i] = this.transNullToString(((Object[])list.get(i))[1]);
				staffCodeList[i] = this.transNullToString(((Object[])list.get(i))[2]);
				idNoList[i] = this.transNullToString(((Object[])list.get(i))[3]);
				registCodeList[i] = this.transNullToString(((Object[])list.get(i))[4]);
				SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy"); 
				Date date = (Date)((Object[])list.get(i))[5];
				String dateString = "";
				if(date != null)
					dateString = format.format(date);
				startTimeList[i] = dateString;
				isRegistedList[i] = (startTimeList[i].equals("") ? "N" : "Y");
			}
		}else{//如果List中没有数据，则生成一条默认数据
			idList = new String[1];
			nameList = new String[1];
			staffCodeList = new String[1];
			idNoList = new String[1];
			registCodeList = new String[1];
			isRegistedList = new String[1];
			startTimeList = new String[1];
			
			idList[0] = "";
			nameList[0] = "";
			staffCodeList[0] = "";
			idNoList[0] = "";
			registCodeList[0] = "";
			startTimeList[0] = "";
			isRegistedList[0] = "";
		}
		//填充ActionForm
		form.setIdList(idList);
		form.setNameList(nameList);
		form.setStaffCodeList(staffCodeList);
		form.setIdNoList(idNoList);
		form.setRegistCodeList(registCodeList);
		form.setStartTimeList(startTimeList);
		form.setIsRegistedList(isRegistedList);
	}

	/**
	 * 根据查询的必要条件，调用DAO方法，获取结果
	 * @param queryType 查询条件的类别
	 * @param queryValue 查询的值
	 * @return 具有特定序列的List
	 */
	private List getViewList(String queryType, String queryValue) {
		String sql = " select ssb.id, "	//0
			+ " ssb.name, "				//1
			+ " ssb.staffCode, "			//2
			+ " ssb.idNo, "				//3
			+ " sl.registCode, "			//4
			+ " sl.startTime"			//5
			+ " from SecurityStaffBaseinfo ssb, "
			+ " SecurityLicense sl "
			+ " where ssb.id=sl.securityStaffBaseinfoId ";

		if("1".equals(queryType))
			sql += " and ssb.name = ?";
		else if("2".equals(queryType))
			sql += " and ssb.staffCode = ?";
		else if("3".equals(queryType))
			sql += " and ssb.idNo = ?";
		return this.getSecuritySystemUsersDAO().viewQuery(sql, queryValue.trim());
	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	private String transNullToString(Object obj){
		String temp = "";
		if (obj != null){
			temp = ((String)obj).trim();			
		}
		return temp;
	}

}
