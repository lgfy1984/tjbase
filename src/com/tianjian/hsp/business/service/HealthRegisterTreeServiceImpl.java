package com.tianjian.hsp.business.service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.tianjian.comm.bean.CommConfigEconkind;
import com.tianjian.comm.bean.CommConfigFtManage;
import com.tianjian.comm.bean.CommConfigLocation;
import com.tianjian.comm.bean.CommConfigLocationTown;
import com.tianjian.comm.bean.CommConfigLocationVillage;
import com.tianjian.comm.bean.CommConfigSettype;
import com.tianjian.comm.bean.CommConfigUnitgrade;
import com.tianjian.comm.bean.CommConfigUnittype;
import com.tianjian.comm.bean.CommDictPublicChar;
import com.tianjian.comm.business.ICommConfigInputDictService;
import com.tianjian.comm.dao.ICommonDAO;
import com.tianjian.hsp.bean.HRTreeNode;
import com.tianjian.hsp.bean.HspConfigBaseinfo;
import com.tianjian.hsp.bean.HspDeptBaseinfo;
import com.tianjian.hsp.bean.HspDeptBaseinfoId;
import com.tianjian.hsp.bean.HspEquipBaseinfo;
import com.tianjian.hsp.bean.HspStaffBaseinfo;
import com.tianjian.hsp.business.IHealthRegisterTreeService;
import com.tianjian.hsp.dao.IHealthRegisterTreeDAO;
import com.tianjian.hsp.struts.form.HealthRegisterTreeForm;
import com.tianjian.hsp.struts.form.HspConfigBaseinfoForm;
import com.tianjian.hsp.struts.form.OrgMenuForm;
import com.tianjian.hsp.struts.form.HealthRegisterTreeForm.DeptBean;
import com.tianjian.hsp.struts.form.HealthRegisterTreeForm.HspBean;
import com.tianjian.hsp.struts.form.HealthRegisterTreeForm.StaffBean;
import com.tianjian.hsp.struts.servlet.HspInit;
import com.tianjian.security.bean.SecurityStaffBaseinfo;
import com.tianjian.security.dao.ISecurityLicenseDAO;
import com.tianjian.security.dao.ISecurityStaffBaseinfoDAO;
import com.tianjian.security.struts.form.SessionForm;
import com.tianjian.security.struts.servlet.BaseSecurityInit;
import com.tianjian.tenant.util.TenantIdHolder;
import com.tianjian.util.Converter;
import com.tianjian.util.comm.UtilTrans;

public class HealthRegisterTreeServiceImpl implements IHealthRegisterTreeService{
	
	private IHealthRegisterTreeDAO healthRegisterTreeDAO;
	private ICommonDAO commonDAO;
	private ISecurityLicenseDAO securityLicenseDAO;	 
	private ICommConfigInputDictService commConfigInputDictService;
	public ISecurityStaffBaseinfoDAO securityStaffBaseinfoDAO;

	public ICommonDAO getCommonDAO() {
		return commonDAO;
	}

	public void setCommonDAO(ICommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}

	public IHealthRegisterTreeDAO getHealthRegisterTreeDAO() {
		return healthRegisterTreeDAO;
	}

	public void setHealthRegisterTreeDAO(
			IHealthRegisterTreeDAO healthRegisterTreeDAO) {
		this.healthRegisterTreeDAO = healthRegisterTreeDAO;
	}

	public String transNullToString(Object obj) {
		String temp = "";
		if (obj != null) {
			temp = String.valueOf(obj).trim();
		}
		return temp;
	}

	/*生成医务人员许可证注册码方法*/
	private String generateRegCode(int zushu, int weishu){
		String temp_l="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuffer reg_code = new StringBuffer();
		reg_code.append("");
		for(int i=0; i<zushu; i++){
			String temp = "";
			Random random = new Random();
			for(int j=0; j<weishu; j++)
				temp += temp_l.charAt(Math.abs(random.nextInt())%(26+26+10-1));			
			reg_code.append(temp);
			if(i != zushu-1)
				reg_code.append("-");
		}		
		return reg_code.toString().toUpperCase();
	}
	
	public void setDateToSecurity(HspStaffBaseinfo data1,
			SecurityStaffBaseinfo data2) {
       
		// 医务人员编码
		if(data1.getId() != null)
			data2.setId(data1.getId());
		// 医疗机构
		if(data1.getHspConfigBaseinfoId() != null)
			data2.setHspConfigBaseinfoId(data1.getHspConfigBaseinfoId());
		// 姓名
		if(data1.getName() != null){
			data2.setName(data1.getName());
			String inputCodeStr = this.commConfigInputDictService.getInputCode(data1.getName());
			//输入码
			data2.setInputCode(inputCodeStr);
		}
		if(data1.getEmpNo()!=null){
			data2.setStaffCode(data1.getEmpNo());
		}
		
		// 性别
		if(data1.getCommConfigSexId() != null)
			data2.setCommConfigSexId(data1.getCommConfigSexId());
		// 出生日期
		if(data1.getBirthday() != null)
			data2.setDateOfBirth(data1.getBirthday());
		// 人员编码
		if(data1.getEmpNo()!=null){
		data2.setStaffCode(data1.getEmpNo());
		}
		// 身份证件号码
		if(data1.getIdNo() != null)
			data2.setIdNo(data1.getIdNo());
		//创造时间
		//if(){}
		
		
		// 所学专业
		// 专业技术职称
		// 职务
		// 文化程度
		// 联系电话
		String phone1 = data1.getMobileTel();
		String phone2 = data1.getOfficeTel();
		if (phone1 != null) {
			data2.setPhone(phone1);
		} else if (phone2 != null) {
			data2.setPhone(phone2);
		}
		// 在位标志
		if(data1.getIslocation() != null)
			data2.setIslocation(data1.getIslocation());
		// data2.setHspDeptBaseinfoId(data1.getDeptCode());
        if(data1.getCreateDate() != null){
        	data2.setCreateDate(data1.getCreateDate());
        }
        if(data1.getCreateUserId()!=null){
        	data2.setCreateUserId(data1.getCreateUserId());
        }
        if(data1.getCreateUserName()!=null){
        	data2.setCreateUserName(data1.getCreateUserName());
        }
        if(data1.getId()!=null){
        	data2.setHspStaffBaseinfoId(data1.getId());
        }
        
	}


	DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
	DateFormat df2 = new SimpleDateFormat("yyyy-MM");
	
	/** 将字符串的年月日转化为日期类型 */
	public Date setStringAsDate3(String year, String month, String day) {
//		if (year.trim().equals("") || year == null || month.trim().equals("")
//				|| month == null || day.trim().equals("") || day == null)
			if (year.trim().equals("") || year == null)
			return new Date();
		String dateStr = year.trim() + "-" + month.trim() + "-" + day.trim();
		Date returnDate = new Date();
		df1.setLenient(false);// 这个的功能是不自动增加月到年，日到月，如不把1996-13-3 转换为1997-1-3
		try {
			returnDate = df1.parse(dateStr);
			return returnDate;
		} catch (Exception e) {
			e.printStackTrace();
			return returnDate;
		}
	}

	/** 将字符串的年月转化为日期类型 */
	public Date setStringAsDate2(String year, String month) {
		if (year.trim().equals("") || year == null )
			return new Date();
		String dateStr = year.trim() + "-" + month.trim();
		Date returnDate = new Date();
		df2.setLenient(false);// 这个的功能是不自动增加月到年，日到月，如不把1936-15 转换为1937-3
		try {
			returnDate = df2.parse(dateStr);
			return returnDate;
		} catch (Exception e) {
			e.printStackTrace();
			return returnDate;
		}
	}

	public ISecurityLicenseDAO getSecurityLicenseDAO() {
		return securityLicenseDAO;
	}

	public void setSecurityLicenseDAO(ISecurityLicenseDAO securityLicenseDAO) {
		this.securityLicenseDAO = securityLicenseDAO;
	}

	public ICommConfigInputDictService getCommConfigInputDictService() {
		return commConfigInputDictService;
	}

	public void setCommConfigInputDictService(
			ICommConfigInputDictService commConfigInputDictService) {
		this.commConfigInputDictService = commConfigInputDictService;
	}

	public ISecurityStaffBaseinfoDAO getSecurityStaffBaseinfoDAO() {
		return securityStaffBaseinfoDAO;
	}

	public void setSecurityStaffBaseinfoDAO(
			ISecurityStaffBaseinfoDAO securityStaffBaseinfoDAO) {
		this.securityStaffBaseinfoDAO = securityStaffBaseinfoDAO;
	}

	@Override
	public List<HRTreeNode> getRootChildNodes(HealthRegisterTreeForm hrtForm, String staffHspId) {
		final String pId = hrtForm.getPid();
		List<Object[]> list = null;
		String tenantId = TenantIdHolder.get();
		String hql = "select a.id, a.itemCode, a.itemName from HspConfigBaseinfo a where a.tenantId = ? and a.id = ?";
		list = (List<Object[]>)this.commonDAO.findListByHql(hql, new String[]{tenantId, staffHspId});
		return this.getHspNodes(list, pId);
	}

	@Override
	public List<HRTreeNode> getHspChildNodes(HealthRegisterTreeForm hrtForm) {
		String tenantId = TenantIdHolder.get();
		final String hspId = hrtForm.getHspId();
		final String pid = hrtForm.getPid();
		final String parentItemCode = hrtForm.getParentItemCode();
		if(parentItemCode == null || parentItemCode.trim().length() == 0
				|| hspId == null || hspId.trim().length() == 0
					|| pid == null || pid.trim().length() == 0 ){
			return null;
		}
		List<HRTreeNode> nodeList = new ArrayList<HRTreeNode>();
		nodeList.add(this.createDeptListNode(hrtForm.getPid(), hspId));
		
		String hql = "select a.id, a.itemCode, a.itemName from HspConfigBaseinfo a where a.parentItemCode = ? and a.tenantId = ? order by a.itemName";
		List<Object[]> list = (List<Object[]>)this.commonDAO.findListByHql(hql, new String[]{parentItemCode, tenantId});
		nodeList.addAll(this.getHspNodes(list, pid));
		return nodeList;
	}
	
	
	@Override
	public List<HRTreeNode> getDeptListChildNodes(HealthRegisterTreeForm hrtForm) {
		String tenantId = TenantIdHolder.get();
		final String hspId = hrtForm.getHspId();
		final String pid = hrtForm.getPid();
		if(hspId == null || hspId.trim().length() == 0
					|| pid == null || pid.trim().length() == 0 ){
			return null;
		}
		String hql = "select a.id.deptCode, a.deptName from HspDeptBaseinfo a where a.id.hspConfigBaseinfoId = ? and a.tenantId = ? order by a.deptName";
		List<Object[]> list = (List<Object[]>)this.commonDAO.findListByHql(hql, new String[]{hspId, tenantId});
		return this.getDeptNodes(list, pid, hspId);
	}
	
	
	@Override
	public List<HRTreeNode> getDeptChildNodes(HealthRegisterTreeForm hrtForm) {
		String tenantId = TenantIdHolder.get();
		final String pid = hrtForm.getPid();
		final String hspId = hrtForm.getHspId();
		final String deptCode = hrtForm.getDeptCode();
		if(isNull(hspId) || isNull(deptCode) || isNull(pid)){
			return null;
		}
		if("equip".equals(hrtForm.getStaffOrEquip())){
			String hql = "select a.id, a.equipName from HspEquipBaseinfo a, HspConfigBaseinfo b where a.hspCode = b.itemCode and b.id = ? and a.deptCode = ? and a.tenantId = ? order by a.equipName";
			List<Object[]> list = (List<Object[]>)this.commonDAO.findListByHql(hql, new String[]{hspId, deptCode, tenantId});
			return this.getEquipNodes(list, pid);
		}else{
			String hql = "select a.id, a.name from HspStaffBaseinfo a where a.hspConfigBaseinfoId = ? and a.relatedDepartment = ? and a.tenantId = ? order by a.name";
			List<Object[]> list = (List<Object[]>)this.commonDAO.findListByHql(hql, new String[]{hspId, deptCode, tenantId});
			return this.getStaffNodes(list, pid);
		}
	}

	@Override
	public List<HRTreeNode> getNulldeptChildNodes(HealthRegisterTreeForm hrtForm) {
		String tenantId = TenantIdHolder.get();
		final String pid = hrtForm.getPid();
		final String hspId = hrtForm.getHspId();
		if(isNull(hspId) || isNull(pid)){
			return null;
		}
		if("equip".equals(hrtForm.getStaffOrEquip())){
			String sql = "select a.id, a.equip_name from hsp.hsp_equip_baseinfo a, hsp.hsp_config_baseinfo b where a.tenant_id = '"+tenantId+"' and a.hsp_code = b.item_code and b.id = '"+hspId+"' and not exists (select 1 from hsp.hsp_dept_baseinfo d where d.hsp_config_baseinfo_id = b.id and d.dept_code = a.dept_code) order by a.equip_name";
			List<Object[]> list = (List<Object[]>)this.commonDAO.findListBySql(sql);
			return this.getEquipNodes(list, pid);
		}else{
			String sql = "select a.id, a.name from hsp.hsp_staff_baseinfo a where a.tenant_id = '"+tenantId+"' and a.hsp_config_baseinfo_id = '"+hspId+"' and not exists (select 1 from hsp.hsp_dept_baseinfo d where d.hsp_config_baseinfo_id = a.hsp_config_baseinfo_id and d.dept_code = a.related_department) order by a.name";
			List<Object[]> list = (List<Object[]>)this.commonDAO.findListBySql(sql);
			return this.getStaffNodes(list, pid);
		}
	}
	

	/**
	 * List<Object[]>
	 * obj[0] ----id
	 * obj[1] ----itemCode
	 * obj[2] ----itemName
	 */
	private List<HRTreeNode> getHspNodes(List<Object[]> list, String pid){
		if(list != null){
			List<HRTreeNode> nodeList = new ArrayList<HRTreeNode>();
			int i = 0;
			for(Object[] obj :  list){
				i++;
				String id = String.valueOf(i);
				if(pid != null && pid.trim().length() > 0){
					id = pid + "_" + id;
				}else{
					pid = "0";
				}
				HRTreeNode node = new HRTreeNode(id, pid, Converter.toBlank(obj[2]), HRTreeNode.NODE_TYPE_HSP, Converter.toBlank(obj[0]));
				node.setHspCode(Converter.toBlank(obj[1]));
				nodeList.add(node);
			}
			return nodeList;
		}
		return null;
	}
	
	private HRTreeNode createDeptListNode(String pId, String hspId){
		HRTreeNode deptListNode = new HRTreeNode(pId + "_0", pId, "科室列表", HRTreeNode.NODE_TYPE_DEPTLIST, hspId);
		return deptListNode;
	}
	
	private List<HRTreeNode> getDeptNodes(List<Object[]> list, String pid, String hspId){
		if(list != null){
			List<HRTreeNode> nodeList = new ArrayList<HRTreeNode>();
			int i = 0;
			for(Object[] obj : list){
				i ++;
				String id = pid+"_"+ String.valueOf(i);
				HRTreeNode node = new HRTreeNode(id, pid, Converter.toBlank(obj[1]), HRTreeNode.NODE_TYPE_DEPT, hspId);
				node.setDeptCode(Converter.toBlank(obj[0]));
				nodeList.add(node);
			}
			i++;
			String id = pid + "_" + String.valueOf(i);
			//科室代码为空的节点
			HRTreeNode node = new HRTreeNode(id, pid, "未登记的科室", HRTreeNode.NODE_TYPE_OTHER_DEPT, hspId);
			nodeList.add(node);
			return nodeList;
		}
		return null;
	}

	
	private List<HRTreeNode> getStaffNodes(List<Object[]> list, String pid) {
		if(list != null){
			List<HRTreeNode> nodeList = new ArrayList<HRTreeNode>();
			int i = 0;
			for(Object[] obj : list){
				i ++;
				String id = String.valueOf(i);
				id = pid+"_"+id;
				HRTreeNode node = new HRTreeNode(id, pid, Converter.toBlank(obj[1]), HRTreeNode.NODE_TYPE_STAFF);
				node.setStaffId(Converter.toBlank(obj[0]));
				nodeList.add(node);
			}
			return nodeList;
		}
		return null;
	}

	private List<HRTreeNode> getEquipNodes(List<Object[]> list, String pid) {
		if(list != null){
			List<HRTreeNode> nodeList = new ArrayList<HRTreeNode>();
			int i = 0;
			for(Object[] obj : list){
				i ++;
				String id = String.valueOf(i);
				id = pid+"_"+id;
				HRTreeNode node = new HRTreeNode(id, pid, Converter.toBlank(obj[1]), HRTreeNode.NODE_TYPE_EQUIP);
				node.setEquipId(Converter.toBlank(obj[0]));
				nodeList.add(node);
			}
			return nodeList;
		}
		return null;
	}
	
	@Override
	public void getDeptDetail(HealthRegisterTreeForm hrtForm) {
		String tenantId = TenantIdHolder.get();
		String hql = "from HspDeptBaseinfo a where a.id.hspConfigBaseinfoId = ? and a.id.deptCode = ? and a.tenantId = ?";
		List<HspDeptBaseinfo> list = (List<HspDeptBaseinfo>)this.commonDAO.findListByHql(hql, new String[]{hrtForm.getHspId(), hrtForm.getDeptCode(), tenantId});
		if(list != null && list.size() > 0){
			HspDeptBaseinfo hdb = list.get(0);
			DeptBean deptBean = hrtForm.new DeptBean();
			deptBean.setHspId(hdb.getId().getHspConfigBaseinfoId());
			deptBean.setHspConfigBaseinfoName(this.commonDAO.getNameById("HspConfigBaseinfo", "id", "itemName", hdb.getId().getHspConfigBaseinfoId()));
			deptBean.setDeptCode(hdb.getId().getDeptCode());
			deptBean.setDeptName(Converter.toBlank(hdb.getDeptName()));
			deptBean.setSeqNo(hdb.getSeqNo());
			deptBean.setHealthBureauCode(Converter.toBlank(hdb.getHealthBureauCode()));
			deptBean.setSocialSecurityBureauCode(Converter.toBlank(hdb.getSocialSecurityBureauCode()));
			deptBean.setDeptAttrCode(Converter.toBlank(hdb.getDeptAttrCode()));
			deptBean.setDeptAttrName(Converter.toBlank(hdb.getDeptAttrName()));
			deptBean.setInputCode(Converter.toBlank(hdb.getInputCode()));
			deptBean.setComments(Converter.toBlank(hdb.getComments()));
			hrtForm.setDeptBean(deptBean);
		}
	}


	@Override
	public void deleteDept(HealthRegisterTreeForm hrtForm) {
		String sql = "select 1 from hsp.hsp_dept_baseinfo d, hsp.hsp_staff_baseinfo s where d.hsp_config_baseinfo_id = s.hsp_config_baseinfo_id and d.dept_code = s.related_department and d.hsp_config_baseinfo_id='"+hrtForm.getHspId()+"' and d.dept_code='"+hrtForm.getDeptCode()+"'";
		List<?> list = this.commonDAO.findListBySql(sql);
		if(list != null){
			if(list.size() > 0){
				hrtForm.setMessage(this.createResponseJson(false, "该科室下存在卫生人员，请先删除后再删除该科室！"));
			}else{
				HspDeptBaseinfo hdb = (HspDeptBaseinfo)this.commonDAO.getObject("HspDeptBaseinfo", new String[]{"id.hspConfigBaseinfoId", "id.deptCode"}, new String[]{hrtForm.getHspId(), hrtForm.getDeptCode()});
				if(hdb != null && this.healthRegisterTreeDAO.delete(hdb)){
					hrtForm.setMessage(this.createResponseJson(true, "删除成功!"));
				}else{
					hrtForm.setMessage(this.createResponseJson(false, "删除失败!"));
				}
			}
		}else{//查询时出现异常的情况
			hrtForm.setMessage(this.createResponseJson(false, "删除失败!"));
		}
		
	}

	@Override
	public void deleteEquip(HealthRegisterTreeForm hrtForm) {
		String tenantId = TenantIdHolder.get();
		HspEquipBaseinfo heb = (HspEquipBaseinfo)this.commonDAO.getObject("HspEquipBaseinfo", new String[]{"id", "tenantId"}, new String[]{hrtForm.getEquipId(), tenantId});
		if(heb != null && this.healthRegisterTreeDAO.delete(heb)){
			hrtForm.setMessage(this.createResponseJson(true, "删除成功!"));
		}else{
			hrtForm.setMessage(this.createResponseJson(false, "删除失败!"));
		}
	}

	@Override
	public void deleteHsp(HealthRegisterTreeForm hrtForm) {
		String tenantId = TenantIdHolder.get();
		String temp = " and h.tenant_id = '" + tenantId + "'  and h.id = '"+hrtForm.getHspId()+"' ";
		String sql = "select nvl(sum(flag), 0) from (select distinct(1) as flag from hsp.hsp_config_baseinfo h, hsp.hsp_dept_baseinfo d where h.id = d.hsp_config_baseinfo_id " + temp +
				" union all select distinct(2) as flag from hsp.hsp_config_baseinfo h, hsp.hsp_staff_baseinfo s where h.id = s.hsp_config_baseinfo_id " + temp +
				" union all select distinct(4) as flag from hsp.hsp_config_baseinfo h, hsp.hsp_equip_baseinfo e where h.item_code = e.hsp_code " + temp + ")";
		List<?> list = this.commonDAO.findListBySql(sql);
		if(list != null){
			if(list.size() > 0){
				BigDecimal sum = (BigDecimal)list.get(0);
				if(new BigDecimal(0).equals(sum)){
					HspConfigBaseinfo hcb = (HspConfigBaseinfo)this.commonDAO.getObject("HspConfigBaseinfo", new String[]{"id"}, new String[]{hrtForm.getHspId()});
					if(hcb != null && this.healthRegisterTreeDAO.delete(hcb)){
						hrtForm.setMessage(this.createResponseJson(true, "删除成功!"));
					}else{
						hrtForm.setMessage(this.createResponseJson(false, "删除失败!"));
					}
				}else{
					String msg = "该机构下存在";
					if(new BigDecimal(1).equals(sum)){
						msg += "科室";
					}else if(new BigDecimal(2).equals(sum)){
						msg += "卫生人员";
					}else if(new BigDecimal(3).equals(sum)){
						msg += "科室、卫生人员";
					}else if(new BigDecimal(4).equals(sum)){
						msg += "设备";
					}else if(new BigDecimal(5).equals(sum)){
						msg += "科室、设备";
					}else if(new BigDecimal(6).equals(sum)){
						msg += "卫生人员、设备";
					}else if(new BigDecimal(7).equals(sum)){
						msg += "科室、卫生人员、设备";
					}
					msg += "，请先删除后再删除该机构！";
					hrtForm.setMessage(this.createResponseJson(false, msg));
				}
			}
		}else{//查询时出现异常的情况
			hrtForm.setMessage(this.createResponseJson(false, "删除失败!"));
		}
	}

	@Override
	public void deleteStaff(HealthRegisterTreeForm hrtForm) {
		String tenantId = TenantIdHolder.get();
		HspStaffBaseinfo hsb = (HspStaffBaseinfo)this.commonDAO.getObject("HspStaffBaseinfo", new String[]{"id", "tenantId"}, new String[]{hrtForm.getStaffId(), tenantId});
		if(hsb != null && this.healthRegisterTreeDAO.delete(hsb)){
			hrtForm.setMessage(this.createResponseJson(true, "删除成功!"));
		}else{
			hrtForm.setMessage(this.createResponseJson(false, "删除失败!"));
		}
	}
	
	@Override
	public void addDeptInit(HealthRegisterTreeForm hrtForm) {
		DeptBean deptBean = hrtForm.new DeptBean();
		String maxSeqNo = this.healthRegisterTreeDAO.getMaxSeqNo("hsp.hsp_dept_baseinfo", "seq_no");
		Long seqNo = Long.valueOf((maxSeqNo != null && maxSeqNo.trim().length() > 0) ? maxSeqNo : "0") + 1;
		deptBean.setSeqNo(seqNo);
		deptBean.setHspId(hrtForm.getHspId());
		String hspName = this.commonDAO.getNameById("HspConfigBaseinfo", "id", "itemName", hrtForm.getHspId());
		deptBean.setHspConfigBaseinfoName(hspName);
		this.initDept(deptBean);
		hrtForm.setDeptBean(deptBean);
	}
	
	private void initDept(DeptBean deptBean){
		String hql = "select a.itemCode, a.itemName from CommConfigDeptAttr a";
		List<Object[]> list = (List<Object[]>)this.commonDAO.findListByHql(hql);
		LinkedHashMap<String, String> deptAttrMap = new LinkedHashMap<String, String>();
		if(list != null){
			for(Object[] obj : list){
				deptAttrMap.put(Converter.toBlank(obj[0]), Converter.toBlank(obj[1]));
			}
		}
		deptBean.setDeptAttrMap(deptAttrMap);
	}
	//用于弹出消息
	public String createResponseJson(boolean success, String msg){
		return "[{flag:"+(success?"1":"0")+", msg:'"+msg+"'}]";
	}
	//用于弹出消息，并刷新父节点
	public String createResponseJson(boolean success, String msg, String parentTId){
		return "[{flag:"+(success?"1":"0")+", msg:'"+msg+"', parentTId:'"+parentTId+"'}]";
	}

	@Override
	public void addDept(HealthRegisterTreeForm hrtForm) {
		DeptBean deptBean =  hrtForm.getDeptBean();
		if(this.checkDeptCode(deptBean.getHspId(), deptBean.getDeptCode()) != null){
			hrtForm.setMessage(this.createResponseJson(false, "科室代码已被"+deptBean.getHspConfigBaseinfoName()+"的其他科室使用，请修改！"));
		}else{
			if(isNotNull(deptBean.getHspId()) && isNotNull(deptBean.getDeptCode())){
				HspDeptBaseinfo hdb = new HspDeptBaseinfo();
				HspDeptBaseinfoId id = new HspDeptBaseinfoId();
				id.setHspConfigBaseinfoId(deptBean.getHspId());
				id.setDeptCode(deptBean.getDeptCode());
				hdb.setId(id);
				hdb.setSeqNo(deptBean.getSeqNo());
				hdb.setDeptAttrCode(deptBean.getDeptAttrCode());
				hdb.setDeptAttrName(deptBean.getDeptAttrName());
				hdb.setInputCode(deptBean.getInputCode());
				hdb.setComments(deptBean.getComments());
				hdb.setHspConfigBaseinfoName(deptBean.getHspConfigBaseinfoName());
				hdb.setDeptName(deptBean.getDeptName());
				hdb.setHealthBureauCode(deptBean.getHealthBureauCode());
				hdb.setSocialSecurityBureauCode(deptBean.getSocialSecurityBureauCode());
				String tenantId = TenantIdHolder.get();
				hdb.setTenantId(tenantId);
				if(this.healthRegisterTreeDAO.save(hdb)){
					hrtForm.setMessage(this.createResponseJson(true, "添加成功！", hrtForm.getParentTId()));
					//继续添加
					DeptBean newDeptBean = hrtForm.new DeptBean();
					newDeptBean.setHspId(deptBean.getHspId());
					newDeptBean.setHspConfigBaseinfoName(deptBean.getHspConfigBaseinfoName());
					newDeptBean.setSeqNo(deptBean.getSeqNo() + 1);
					this.initDept(newDeptBean);
					hrtForm.setDeptBean(newDeptBean);
					return;
				}else{
					hrtForm.setMessage(this.createResponseJson(false, "添加失败！"));
				}
			}else{
				hrtForm.setMessage(this.createResponseJson(false, "添加失败！"));
			}
		}
		this.initDept(deptBean);
	}

	@Override
	public void updateDeptInit(HealthRegisterTreeForm hrtForm) {
		String tenantId = TenantIdHolder.get();
		HspDeptBaseinfo hdb = (HspDeptBaseinfo)this.commonDAO.getObject("HspDeptBaseinfo", new String[]{"id.hspConfigBaseinfoId", "id.deptCode", "tenantId"}, new String[]{hrtForm.getHspId(), hrtForm.getDeptCode(), tenantId});
		DeptBean deptBean = hrtForm.new DeptBean();
		deptBean.setHspId(hdb.getId().getHspConfigBaseinfoId());
		deptBean.setHspConfigBaseinfoName(hdb.getHspConfigBaseinfoName());
		deptBean.setSeqNo(hdb.getSeqNo());
		deptBean.setDeptAttrCode(hdb.getDeptAttrCode());
		deptBean.setDeptAttrName(hdb.getDeptAttrName());
		deptBean.setHealthBureauCode(hdb.getHealthBureauCode());
		deptBean.setSocialSecurityBureauCode(hdb.getSocialSecurityBureauCode());
		deptBean.setDeptCode(hdb.getId().getDeptCode());
		deptBean.setDeptName(hdb.getDeptName());
		deptBean.setComments(hdb.getComments());
		this.initDept(deptBean);
		hrtForm.setDeptBean(deptBean);
	}

	@Override
	public void updateDept(HealthRegisterTreeForm hrtForm) {
		String tenantId = TenantIdHolder.get();
		DeptBean deptBean =  hrtForm.getDeptBean();
		String message = null;
		if(isNotNull(deptBean.getHspId()) && isNotNull(deptBean.getDeptCode())){
			HspDeptBaseinfo hdb = (HspDeptBaseinfo)this.commonDAO.getObject("HspDeptBaseinfo", new String[]{"id.hspConfigBaseinfoId", "id.deptCode", "tenantId"}, new String[]{deptBean.getHspId(), deptBean.getDeptCode(), tenantId});
			if(hdb == null){
				hdb = new HspDeptBaseinfo();
			}
			HspDeptBaseinfoId id = new HspDeptBaseinfoId();
			id.setHspConfigBaseinfoId(deptBean.getHspId());
			id.setDeptCode(deptBean.getDeptCode());
			hdb.setId(id);
			hdb.setSeqNo(deptBean.getSeqNo());
			hdb.setDeptAttrCode(deptBean.getDeptAttrCode());
			hdb.setDeptAttrName(deptBean.getDeptAttrName());
			hdb.setInputCode(deptBean.getInputCode());
			hdb.setComments(deptBean.getComments());
			hdb.setHspConfigBaseinfoName(deptBean.getHspConfigBaseinfoName());
			hdb.setDeptName(deptBean.getDeptName());
			hdb.setHealthBureauCode(deptBean.getHealthBureauCode());
			hdb.setSocialSecurityBureauCode(deptBean.getSocialSecurityBureauCode());
			if(this.healthRegisterTreeDAO.merge(hdb)){
				message = this.createResponseJson(true, "修改成功！", hrtForm.getParentTId());
			}else{
				message = this.createResponseJson(false, "修改失败！");
			}
		}else{
			message = this.createResponseJson(false, "修改失败！");
		}
		this.initDept(deptBean);
		hrtForm.setMessage(message);
	}

	@Override
	public String checkDeptCode(String hspId, String deptCode) {
		String tenantId = TenantIdHolder.get();
		String hql = "select a.id.deptCode from HspDeptBaseinfo a where a.id.hspConfigBaseinfoId=? and a.id.deptCode=? and a.tenantId = ?";
		List<?> list = this.commonDAO.findListByHql(hql, new String[]{hspId, deptCode, tenantId});
		if(list != null && list.size() > 0){
			return "1";
		}
		return null;
	}

	
	private final static int GET_COUNT = 0;
	private final static int GET_DATA = 1;
	/**
	 * 根据查询条件构造SQL
	 * @param request
	 * @param sqlType 0:查询总结果数量  1：查询数据
	 * @return
	 */
	private String createConditionSql(HttpServletRequest request, int sqlType){
		String tenantId = TenantIdHolder.get();
		String hspName = request.getParameter("hspName");
		String hspCode = request.getParameter("hspCode");
		String deptCode = request.getParameter("deptCode");
		String deptName = request.getParameter("deptName");
		String empNo = request.getParameter("empNo");
		String empName = request.getParameter("empName");
		String equipCode = request.getParameter("equipCode");
		String equipName = request.getParameter("equipName");
		String recordNum = request.getParameter("recordNum");
		
		SessionForm sessionForm = (SessionForm)request.getSession(true).getAttribute("sessionForm");
		String staffHspId = sessionForm.getStaffHospitalId();
		//机构过滤条件
		StringBuilder hspFitler = new StringBuilder();
		//操作员所在机构或者下属机构
		hspFitler.append(" and (h.id = '").append(staffHspId).append("'");
		List<String> subHspIdList = this.getSubHspIds(staffHspId);
		if(subHspIdList != null && subHspIdList.size() > 0){
			for(String subHspId : subHspIdList){
				hspFitler.append(" or h.id = '").append(subHspId).append("'");
			}
		}
		hspFitler.append(")");
		if(isNotNull(hspName)){
			hspFitler.append(" and h.item_name like '%").append(hspName.trim()).append("%' ");
	    }
	    if(isNotNull(hspCode)){
	    	hspFitler.append(" and h.item_code = '").append(hspCode.trim()).append("' ");
	    }
	    //科室过滤条件
	    StringBuilder deptFilter = new StringBuilder();
	    if(isNotNull(deptCode)){
	    	deptFilter.append(" and d.dept_code = '").append(deptCode.trim()).append("' ");
	    }
	    if(isNotNull(deptName)){
	    	deptFilter.append(" and d.dept_name like '%").append(deptName.trim()).append("%' ");
	    }
	    //人员过滤条件
	    StringBuilder staffFitler = new StringBuilder();
	    if(isNotNull(empNo)){
	    	staffFitler.append(" and s.emp_no = '").append(empNo.trim()).append("' ");
	    }
	    if(isNotNull(empName)){
	    	staffFitler.append(" and s.name like '%").append(empName.trim()).append("%'");
	    }
	    //人员过滤条件
	    StringBuilder equipFitler = new StringBuilder();
	    if(isNotNull(equipCode)){
	    	staffFitler.append(" and e.equip_code = '").append(equipCode.trim()).append("' ");
	    }
	    if(isNotNull(equipName)){
	    	staffFitler.append(" and e.equip_name like '%").append(equipName.trim()).append("%'");
	    }
		
		StringBuilder sql = new StringBuilder();
		//如果设备查询条件不为空 则查询设备， 否则判断科室，最后是机构
		if(isNotNull(equipCode) || isNotNull(equipName)){
			if(sqlType == GET_COUNT){
				sql.append("select count(*) ");
			}else if(sqlType == GET_DATA){
				sql.append("select equipId, deptCode, hspId, hspCode, parentItemCode from ")
					.append("(select t.*, rownum as rn from ")
					.append("(select e.id as equipId, d.dept_code as deptCode, h.id as hspId, h.item_code as hspCode, h.parent_item_code as parentItemCode");
			}else return null;
		    sql.append(" from hsp.hsp_equip_baseinfo e ")
				.append(" inner join hsp.hsp_config_baseinfo h on e.hsp_code = h.item_code ")
				.append(" left join hsp.hsp_dept_baseinfo d on e.id = d.hsp_config_baseinfo_id and e.dept_code = d.dept_code ")
		    	.append(" where e.tenant_id = '"+tenantId+"' " );
		    //加上过滤条件
		    sql.append(hspFitler)
		    	.append(deptFilter)
		    	.append(equipFitler);
		    if(sqlType == GET_DATA){
		    	sql.append(" order by h.item_name, d.dept_name, e.equip_name, e.id ) t")//加上s.id确保排列顺序唯一
					.append(")where rn = ").append(recordNum);
		    }
		} //如果人员查询条件不为空 则查询人员， 否则判断科室，最后是机构
		else if(isNotNull(empNo)  || isNotNull(empName)){
			if(sqlType == GET_COUNT){
				sql.append("select count(*) ");
			}else if(sqlType == GET_DATA){
				sql.append("select staffId, deptCode, hspId, hspCode, parentItemCode from ")
					.append("(select t.*, rownum as rn from ")
					.append("(select s.id as staffId, d.dept_code as deptCode, h.id as hspId, h.item_code as hspCode, h.parent_item_code as parentItemCode");
			}else return null;
		    sql.append(" from hsp.hsp_staff_baseinfo s ")
				.append(" inner join hsp.hsp_config_baseinfo h on s.hsp_config_baseinfo_id = h.id ")
				.append(" left join hsp.hsp_dept_baseinfo d on s.hsp_config_baseinfo_id = d.hsp_config_baseinfo_id and s.RELATED_DEPARTMENT = d.dept_code ")
		    	.append(" where s.tenant_id = '"+tenantId+"'" );
		    //加上过滤条件
		    sql.append(hspFitler)
		    	.append(deptFilter)
		    	.append(staffFitler);
		    if(sqlType == GET_DATA){
		    	sql.append(" order by h.item_name, d.dept_name, s.name, s.id ) t")//加上s.id确保排列顺序唯一
					.append(")where rn = ").append(recordNum);
		    }
		}else if(isNotNull(deptCode) || isNotNull(deptName)){
			if(sqlType == GET_COUNT){
				sql.append("select count(*) ");
			}else if(sqlType == GET_DATA){
				sql.append("select null, deptCode, hspId, hspCode, parentItemCode from ")
					.append("(select t.*, rownum as rn from ")
					.append("(select d.dept_code as deptCode, h.id as hspId, h.item_code as hspCode, h.parent_item_code as parentItemCode");
			}else return null;
			sql.append(" from hsp.hsp_dept_baseinfo d ")
				.append(" inner join hsp.hsp_config_baseinfo h on d.hsp_config_baseinfo_id = h.id ")
		    	.append(" where d.tenant_id = '"+tenantId+"'" );
			 //加上过滤条件
			sql.append(hspFitler)
			    .append(deptFilter);
		    if(sqlType == GET_DATA){
		    	sql.append(" order by h.item_name, d.dept_name, d.dept_code, d.hsp_config_baseinfo_id ) t")//加上主键确保排列顺序唯一
					.append(")where rn = ").append(recordNum);
		    }
		}else if(isNotNull(hspCode) || isNotNull(hspName)){
			if(sqlType == GET_COUNT){
				sql.append("select count(*) ");
			}else if(sqlType == GET_DATA){
				sql.append("select null, null, hspId, hspCode, parentItemCode from ")
					.append("(select t.*, rownum as rn from ")
					.append("(select h.id as hspId, h.item_code as hspCode, h.parent_item_code as parentItemCode");
			}else return null;
			sql.append(" from hsp.hsp_config_baseinfo h ")
		    	.append(" where h.tenant_id = '"+tenantId+"'" );
			 //加上过滤条件
			sql.append(hspFitler);
		    if(sqlType == GET_DATA){
		    	sql.append(" order by h.item_name, h.id ) t")//加上主键确保排列顺序唯一
					.append(")where rn = ").append(recordNum);
		    }
		}
		
		return sql.toString();
	}
	
	public String getQueryData(HttpServletRequest request){
		String recordNum = request.getParameter("recordNum");
		int recordCount = this.getCount(request);
		if(recordCount != 0){
			String ids = this.getData(request);
			StringBuilder json = new StringBuilder("{\"count\":").append(recordCount).append(",")
									.append("\"recordNum\":").append(recordNum).append(",")
									.append("\"ids\":").append(ids).append("}");
			return json.toString();
		}
		return null;
	}
	
	public int getCount(HttpServletRequest request) {
		String sql = this.createConditionSql(request, GET_COUNT);
		if(isNull(sql)) return 0;
		List<?> list = this.commonDAO.findListBySql(sql);
		if(list != null && list.size() > 0){
			BigDecimal count = (BigDecimal)list.get(0);
			return count.intValue();
		}
		return 0;
	}
	
	public String getData(HttpServletRequest request) {
		String sql = this.createConditionSql(request, GET_DATA);
		if(isNull(sql)) return null;
		List<?> list = this.commonDAO.findListBySql(sql);
		List<Object> idList = new ArrayList<Object>(10);
		if(list != null && list.size() > 0){
			Object[] obj = (Object[])list.get(0);
			idList.addAll(Arrays.asList(obj));
			if(obj[obj.length - 1] != null){
				List<String> parentItemCodeList = this.getAllParentItemCode((String)obj[obj.length - 1]);
				if(parentItemCodeList != null){
					idList.addAll(parentItemCodeList);
				}
			}
		}
		StringBuilder json = new StringBuilder("[");
		for(Object id : idList){
			if(id != null){
				json.append("\"").append((String)id).append("\"").append(",");
			}else{
				json.append(id).append(",");
			}
		}
		if(json.charAt(json.length() - 1) == ','){
			json.deleteCharAt(json.length() - 1);
		}
		json.append("]");
		return json.toString();
	}

	//迭代获取上级机构代码列表，直到获取最顶层的机构为止
	private List<String> getAllParentItemCode(String string) {
		List<String> itemCodeList = new ArrayList<String>(4);
		String parentItemCode= this.commonDAO.getValueByAnotherSQL("hsp.hsp_config_baseinfo", "item_code", string, "parent_item_code");
		if(parentItemCode != null){
			itemCodeList.add(parentItemCode);
			List<String> parentItemCodeList = this.getAllParentItemCode(parentItemCode);
			if(parentItemCodeList != null){
				itemCodeList.addAll(parentItemCodeList);
			}
			return itemCodeList;
		}
		return null;
	}

	private boolean isNull(String temp){
		return temp == null || temp.trim().length() <= 0;
	}
	
	private boolean isNotNull(String temp){
		return temp != null && temp.trim().length() > 0;
	}

	@Override
	public String getDeptXml(HttpServletRequest request) {
		String inputCode = request.getParameter("inputCode"); 
		String flag = request.getParameter("flag");
		String hspId = request.getParameter("hspId");
		ServletContext application = request.getSession().getServletContext();
		List<?> list= this.healthRegisterTreeDAO.getDeptByHspId(flag, inputCode, hspId, Integer.valueOf((String)application.getAttribute("hsp.CURRECORD_TANCHUCENG")), Integer.valueOf((String)application.getAttribute("hsp.PAGE_SIZE_TANCHUCENG")));
		StringBuffer buffer = new StringBuffer();
		
//		list = hspStaffBaseinfoDAO.findHspList(flag, inputCode, hspType,id1,id2,id3,id4,id5,
//				Integer.valueOf(CommInit.getProperty("CURRECORD_TANCHUCENG")),
//				CommInit.getPageSize("PAGE_SIZE_TANCHUCENG"));
		buffer.append("<response>");
		if(list != null){
			Iterator<?> iterator = list.iterator();
			while(iterator.hasNext()){
				HspDeptBaseinfo dept = (HspDeptBaseinfo) iterator.next();
				buffer.append("<ress>");
				buffer.append("<resInputCode>" + Converter.toBlank(dept.getInputCode()) + "</resInputCode>");
				buffer.append("<resItemCode>" + Converter.toBlank(dept.getId().getDeptCode()) + "</resItemCode>");
				buffer.append("<resItemName>" + Converter.toBlank(dept.getDeptName()) + "</resItemName>");
				buffer.append("<resItemId>" + Converter.toBlank(dept.getId().getDeptCode()) + "</resItemId>");
				buffer.append("</ress>");
			}
			
		}
		buffer.append("</response>");
		
		return buffer.toString();
	}

	@Override
	public String getDeptXmlByHspCode(HttpServletRequest request) {
		String inputCode = request.getParameter("inputCode"); 
		String flag = request.getParameter("flag");
		String hspCode = request.getParameter("hspCode");
		ServletContext application = request.getSession().getServletContext();
		List<?> list= this.healthRegisterTreeDAO.getDeptByHspCode(flag, inputCode, hspCode, Integer.valueOf((String)application.getAttribute("hsp.CURRECORD_TANCHUCENG")), Integer.valueOf((String)application.getAttribute("hsp.PAGE_SIZE_TANCHUCENG")));
		StringBuffer buffer = new StringBuffer();
		
//		list = hspStaffBaseinfoDAO.findHspList(flag, inputCode, hspType,id1,id2,id3,id4,id5,
//				Integer.valueOf(CommInit.getProperty("CURRECORD_TANCHUCENG")),
//				CommInit.getPageSize("PAGE_SIZE_TANCHUCENG"));
		buffer.append("<response>");
		if(list != null){
			Iterator<?> iterator = list.iterator();
			while(iterator.hasNext()){
				HspDeptBaseinfo dept = (HspDeptBaseinfo) iterator.next();
				buffer.append("<ress>");
				buffer.append("<resInputCode>" + Converter.toBlank(dept.getInputCode()) + "</resInputCode>");
				buffer.append("<resItemCode>" + Converter.toBlank(dept.getId().getDeptCode()) + "</resItemCode>");
				buffer.append("<resItemName>" + Converter.toBlank(dept.getDeptName()) + "</resItemName>");
				buffer.append("<resItemId>" + Converter.toBlank(dept.getId().getDeptCode()) + "</resItemId>");
				buffer.append("</ress>");
			}
			
		}
		buffer.append("</response>");
		
		return buffer.toString();
	}
	
	
	/**
	 * 按机构id获取下属机构id集合
	 * @param hspId
	 * @return
	 */
	private List<String> getSubHspIds(String id){
		//查询该id的下属机构id
		String hql = "select sub.id from HspConfigBaseinfoLocalBase super, HspConfigBaseinfoLocalBase sub where super.itemCode = sub.parentItemCode and super.id = ?";
		List<?> list = this.commonDAO.findListByHql(hql, new String[]{id});
		if(list != null && list.size() > 0){
			List<String> hspIdList = new ArrayList<String>();
			for(Iterator<?> it = list.iterator(); it.hasNext(); ){
				String subId = (String)it.next();
				hspIdList.add(subId);
				//根据id迭代获取下属机构id列表
				List<String> subIdList = getSubHspIds(subId);
				if(subIdList != null && subIdList.size() > 0){
					hspIdList.addAll(subIdList);
				}
			}
			return hspIdList;
		}
		return null;
	}
}

