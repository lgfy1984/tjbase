package com.tianjian.comm.business.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.tianjian.comm.bean.CommConfigInputDict;
import com.tianjian.comm.business.ICommConfigInputDict2Service;
import com.tianjian.comm.dao.ICommConfigInputDict2DAO;
import com.tianjian.comm.struts.form.CommConfigInputDictForm;

public class CommConfigInputDict2ServiceImpl implements
		ICommConfigInputDict2Service {

	private static final Logger log = LogManager.getLogger(CommConfigInputDict2ServiceImpl.class);
	// ------------------------DAO--声明开始---------------------------------------------------
	private ICommConfigInputDict2DAO commConfigInputDictDAO;

	public ICommConfigInputDict2DAO getCommConfigInputDictDAO() {
		return commConfigInputDictDAO;
	}

	public void setCommConfigInputDictDAO(
			ICommConfigInputDict2DAO commConfigInputDictDAO) {
		this.commConfigInputDictDAO = commConfigInputDictDAO;
	}

	// ------------------------DAO--声明结束---------------------------------------------------

	public void addInit(CommConfigInputDictForm form) {
		init(form);
	}

	public void serchInit(CommConfigInputDictForm form) {
		searchInit(form);
	}

	/** 获取记录对象 */
	public CommConfigInputDict checkData(String id, String name) {
		return this.commConfigInputDictDAO.findByIdAndName(id, name);
	}

	/** 提交保存 */
	public void save(CommConfigInputDictForm form) {
		CommConfigInputDict data = new CommConfigInputDict();
		this.setData(form, data);
		this.commConfigInputDictDAO.save(data);
	}

	/** 修改之前初始化 */
	public void updateInit(CommConfigInputDictForm form) {
		CommConfigInputDict data = this.commConfigInputDictDAO.findById(form.getItemCode());
		this.setForm(form, data);
		init(form);
	}

	/** 提交修改 */
	public void update(CommConfigInputDictForm form) {
		try {
			CommConfigInputDict data = this.commConfigInputDictDAO.findById(form.getItemCode());
			this.setData(form, data);
			log.debug("update success");
			this.commConfigInputDictDAO.update(data);
		} catch (Exception e) {
			log.error("updata fail", e);
			e.printStackTrace();
		}
	}

	/** 提交察看详细 */
	public void showInit(CommConfigInputDictForm form) {
		CommConfigInputDict data = this.commConfigInputDictDAO.findById(form.getItemCode());
		form = new CommConfigInputDictForm();
		this.setForm(form, data);
		init(form);
	}

	/** 提交删除 */
	public void delete(CommConfigInputDictForm form) {
		CommConfigInputDict data = this.commConfigInputDictDAO.findById(form.getItemCodeHidden());
		this.commConfigInputDictDAO.delete(data);
	}

	/** 获取总记录数 */
	public int getCount(String itemCode, String itemName, String inputCode, String inputCodeWb, String inputCode1, String inputCode2) {
		return this.commConfigInputDictDAO.getCount(itemCode,  itemName,  inputCode,  inputCodeWb,  inputCode1,  inputCode2);
	}

	/** 根据查询条件获取当前显示页的数据 */
	public void getSearch(CommConfigInputDictForm form, int curCount, int pageSize) {
		try {
			String order = "";
			if (form.getOrderNo() != null) {
				if (form.getOrderNo().equals("1")) {
					order = " a.itemCode";
				} else if (form.getOrderNo().equals("2")) {
					order = " a.itemName";
				} else if (form.getOrderNo().equals("3")) {
					order = " a.inputCode";
				} else if (form.getOrderNo().equals("4")) {
					order = " a.inputCodeWb";
				} else {
					order = " a.itemCode";
				}
				if (form.getAsc().equals("1")) {
					order += " desc";
				} else {
					order += " asc";
				}
			}
			List<?> list = this.commConfigInputDictDAO.getData(form.getItemCode(),
					form.getItemName(), form.getInputCode(), form.getInputCodeWb(), 
					form.getInputCode1(), form.getInputCode2(), order, curCount, pageSize);
			if (list != null && list.size() > 0) {
				String[] itemCodes = new String[list.size()];
				String[] itemNames = new String[list.size()];
				String[] inputCodes = new String[list.size()];
				String[] inputCodeWbs = new String[list.size()];
				for (int i = 0; i < list.size(); i++) {
					itemCodes[i] = String.valueOf(transNullToString(((Object[]) list.get(i))[0]));
					itemNames[i] = String.valueOf(transNullToString(((Object[]) list.get(i))[1]));
					inputCodes[i] = String.valueOf(transNullToString(((Object[]) list.get(i))[2]));
					inputCodeWbs[i] = String.valueOf(transNullToString(((Object[]) list.get(i))[3]));
				}
				form.setItemCodeList(itemCodes);
				form.setItemNameList(itemNames);
				form.setInputCodeList(inputCodes);
				form.setInputCodeWbList(inputCodeWbs);
			}
		} catch (Exception e) {
			log.error("getSearch fail!", e);
			e.printStackTrace();
		}

	}

	/** 获取查询结果之前初始化 */
	public void getDetail(CommConfigInputDictForm form) {

	}

	// 内部方法
	/**
	 * 去掉字串中的空格
	 */
	public String transNullToString(Object obj) {
		String temp = "";
		if (obj != null) {
			temp = ((String) obj).trim();
		}
		return temp;
	}

	public String transNullToZero(Object obj) {
		String temp = "0";
		if (obj != null) {
			temp = ((String) obj).trim();
		}
		return temp;
	}

	/** 在初始化阶段将字典库传入form中 */
	private void init(CommConfigInputDictForm form) {
	}

	/** 在进行查找之前将字典库传入form中 */
	private void searchInit(CommConfigInputDictForm form) {

	}

	/** 构造data */
	private void setData(CommConfigInputDictForm form, CommConfigInputDict data) {
		try {
			data.setItemCode(transNullToString(form.getItemCode()));
			data.setItemName(transNullToString(form.getItemName()));
			data.setInputCode(transNullToString(form.getInputCode()));
			data.setInputCodeWb(transNullToString(form.getInputCodeWb()));
			data.setInputCode1(transNullToString(form.getInputCode1()));
			data.setInputCode2(transNullToString(form.getInputCode2()));
			data.setComments(transNullToString(form.getComments()));
		} catch (Exception e) {
			log.error("setData fail!", e);
			e.printStackTrace();
		}
	}

	/** 在传递数据到页面之前构造form */
	private void setForm(CommConfigInputDictForm form, CommConfigInputDict data) {
		try {
			form.setItemCode(transNullToString(data.getItemCode().toString()));
			form.setItemName(transNullToString(data.getItemName()));
			form.setInputCode(transNullToString(data.getInputCode()));
			form.setInputCodeWb(transNullToString(data.getInputCodeWb()));
			form.setInputCode1(transNullToString(data.getInputCode1()));
			form.setInputCode2(transNullToString(data.getInputCode2()));
			form.setComments(transNullToString(data.getComments()));
		} catch (Exception e) {
			log.error("setForm fail!", e);
			e.printStackTrace();
		}
	}

}
