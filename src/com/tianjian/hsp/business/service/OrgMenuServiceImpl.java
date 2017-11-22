package com.tianjian.hsp.business.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.tianjian.comm.bean.CommConfigDeptAttr;
import com.tianjian.comm.bean.CommConfigNationality;
import com.tianjian.comm.business.ICommConfigInputDictService;
import com.tianjian.comm.dao.ICommonDAO;
import com.tianjian.hsp.bean.HspConfigBaseinfo;
import com.tianjian.hsp.bean.HspDeptBaseinfo;
import com.tianjian.hsp.bean.HspDeptBaseinfoId;
import com.tianjian.hsp.bean.HspStaffBaseinfo;
import com.tianjian.hsp.bean.ZTreeNode;
import com.tianjian.hsp.business.IOrgMenuService;
import com.tianjian.hsp.dao.IHspConfigBaseinfoDAO;
import com.tianjian.hsp.dao.IHspStaffBaseinfoDAO;
import com.tianjian.hsp.dao.IOrgMenuDAO;
import com.tianjian.hsp.struts.form.HspStaffBaseinfoForm;
import com.tianjian.hsp.struts.form.OrgMenuForm;
import com.tianjian.hsp.struts.servlet.HspInit;
import com.tianjian.security.bean.SecurityLicense;
import com.tianjian.security.bean.SecurityStaffBaseinfo;
import com.tianjian.security.dao.ISecurityLicenseDAO;
import com.tianjian.security.dao.ISecurityStaffBaseinfoDAO;
import com.tianjian.util.Converter;
import com.tianjian.util.comm.UtilTrans;

public class OrgMenuServiceImpl implements IOrgMenuService{
	
	private IHspConfigBaseinfoDAO hspConfigBaseinfoDAO;
	private IOrgMenuDAO orgMenuDao;
	public IHspStaffBaseinfoDAO hspStaffBaseinfoDAO;
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

	public IHspStaffBaseinfoDAO getHspStaffBaseinfoDAO() {
		return hspStaffBaseinfoDAO;
	}

	public void setHspStaffBaseinfoDAO(IHspStaffBaseinfoDAO hspStaffBaseinfoDAO) {
		this.hspStaffBaseinfoDAO = hspStaffBaseinfoDAO;
	}

	@Override
	public void getOrgMenu(OrgMenuForm orgForm, String staffId) {
		List<?> allList = this.hspConfigBaseinfoDAO.findAll();
		List<?> allDeptList = this.orgMenuDao.findAllDept();
//		HspConfigBaseinfo orgRoot = this.orgMenuDao.findRoot();
		//查找第一层数据
		List<HspConfigBaseinfo> rootList = this.aptDate(allList, null);
		HspConfigBaseinfo orgRoot = null;
		if(rootList!=null&&rootList.size()>0){
			orgRoot = (HspConfigBaseinfo)rootList.get(0);
		}
		if(orgRoot!=null&&orgRoot.getId()!=null){
			List<ZTreeNode> firstNodeList = new ArrayList<ZTreeNode>();
			ZTreeNode root = new ZTreeNode(orgRoot.getItemCode(), "0", orgRoot.getItemName(), true);
			firstNodeList.add(root);
			orgForm.setFirstNodeList(firstNodeList);
			//第二层
			List<ZTreeNode> secondNodeList = new ArrayList<ZTreeNode>();			
			//第二层-机构
//			List<?> secondList = this.orgMenuDao.findChild(orgRoot.getItemCode());
			List<HspConfigBaseinfo> secondList = this.aptDate(allList, orgRoot.getItemCode());
			if(secondList!=null&&secondList.size()>0){
				for(int i1=0;i1<secondList.size();i1++){
					HspConfigBaseinfo orgSecond = (HspConfigBaseinfo) secondList.get(i1);
					ZTreeNode secondNode = new ZTreeNode(orgSecond.getItemCode(), orgRoot.getItemCode(), orgSecond.getItemName(), true);
					secondNodeList.add(secondNode);		
					//第三层
					List<ZTreeNode> thirdNodeList = new ArrayList<ZTreeNode>();
					//第三层-机构
//					List<?> thirdList = this.orgMenuDao.findChild(orgSecond.getItemCode());
					List<HspConfigBaseinfo> thirdList = this.aptDate(allList, orgSecond.getItemCode());
					if(thirdList!=null&&thirdList.size()>0){
						for(int i2=0;i2<thirdList.size();i2++){
							HspConfigBaseinfo orgThird = (HspConfigBaseinfo) thirdList.get(i2);
							ZTreeNode thirdNode = new ZTreeNode(orgThird.getItemCode(), orgSecond.getItemCode(), orgThird.getItemName(), true);
							thirdNodeList.add(thirdNode);		
							//第四层
							List<ZTreeNode> fourthNodeList = new ArrayList<ZTreeNode>();
							//第四层-机构
//							List<?> fourthList = this.orgMenuDao.findChild(orgThird.getItemCode());
							List<HspConfigBaseinfo> fourthList = this.aptDate(allList, orgThird.getItemCode());
							if(fourthList!=null&&fourthList.size()>0){
								for(int i3=0;i3<fourthList.size();i3++){
									HspConfigBaseinfo orgFourth = (HspConfigBaseinfo) fourthList.get(i3);
									ZTreeNode fourthNode = new ZTreeNode(orgFourth.getItemCode(), orgThird.getItemCode(), orgFourth.getItemName(), true);
									fourthNodeList.add(fourthNode);		
									//第五层
									List<ZTreeNode> fifthNodeList = new ArrayList<ZTreeNode>();
									//第五层-机构
//									List<?> fifthList = this.orgMenuDao.findChild(orgFourth.getItemCode());
//									if(fifthList!=null&&fifthList.size()>0){
//										for(int i4=0;i4<fifthList.size();i4++){
//											HspConfigBaseinfo orgFifth = (HspConfigBaseinfo) fifthList.get(i4);
//											ZTreeNode fifthNode = new ZTreeNode();
//											fifthNode.setId(orgFifth.getItemCode());
//											fifthNode.setPId(orgFourth.getItemCode());
//											fifthNode.setParent(true);
////											secondNode.setUrl("hsp/orgMenu.do?hspConfigBaseinfoId="+orgSecond.getId());
//											fifthNodeList.add(fifthNode);		
//										}
//									}
									//第五层-科室
//									List<?> fifthDeptList = this.orgMenuDao.findDept(orgFourth.getId());
									List<HspDeptBaseinfo> fifthDeptList = this.aptDeptDate(allDeptList, orgFourth.getId());
									if(fifthDeptList!=null&&fifthDeptList.size()>0){
										for(int j=0;j<secondList.size();j++){
											HspDeptBaseinfo deptFifth= (HspDeptBaseinfo)fifthDeptList.get(j);
											ZTreeNode fifthDeptNode = new ZTreeNode(deptFifth.getId().getDeptCode(), orgFourth.getItemCode(), deptFifth.getDeptName(), false, "hsp/orgMenu.do?verbId=detail&deptCode="+deptFifth.getId().getDeptCode());
											fifthNodeList.add(fifthDeptNode);			
										}
									}
									orgForm.setFifthNodeList(fifthNodeList);
								}
							}
							//第四层-科室
//							List<?> fourthDeptList = this.orgMenuDao.findDept(orgThird.getId());
							List<HspDeptBaseinfo> fourthDeptList = this.aptDeptDate(allDeptList, orgThird.getId());
							if(fourthDeptList!=null&&fourthDeptList.size()>0){
								for(int j=0;j<secondList.size();j++){
									HspDeptBaseinfo deptFourth = (HspDeptBaseinfo)fourthDeptList.get(j);
									ZTreeNode fourthDeptNode = new ZTreeNode(deptFourth.getId().getDeptCode(), orgThird.getItemCode(), deptFourth.getDeptName(), false, "hsp/orgMenu.do?verbId=detail&deptCode="+deptFourth.getId().getDeptCode());
									fourthNodeList.add(fourthDeptNode);			
								}
							}
							orgForm.setFourthNodeList(fourthNodeList);
						}
					}
					//第三层-科室
//					List<?> thirdDeptList = this.orgMenuDao.findDept(orgSecond.getId());
					List<HspDeptBaseinfo> thirdDeptList = this.aptDeptDate(allDeptList, orgSecond.getId());
					if(thirdDeptList!=null&&thirdDeptList.size()>0){
						for(int j=0;j<secondList.size();j++){
							HspDeptBaseinfo deptThird = (HspDeptBaseinfo)thirdDeptList.get(j);
							ZTreeNode thirdDeptNode = new ZTreeNode(deptThird.getId().getDeptCode(), orgSecond.getItemCode(), deptThird.getDeptName(), false, "hsp/orgMenu.do?verbId=detail&deptCode="+deptThird.getId().getDeptCode());
							thirdNodeList.add(thirdDeptNode);			
						}
					}
					orgForm.setThirdNodeList(thirdNodeList);
				}
			}
			//第二层-科室
//			List<?> secondDeptList = this.orgMenuDao.findDept(orgRoot.getId());
			List<HspDeptBaseinfo> secondDeptList = this.aptDeptDate(allDeptList, orgRoot.getId());
			if(secondDeptList!=null&&secondDeptList.size()>0){
				for(int j=0;j<secondList.size();j++){
					HspDeptBaseinfo deptSecond = (HspDeptBaseinfo)secondDeptList.get(j);
					ZTreeNode secondDeptNode = new ZTreeNode(deptSecond.getId().getDeptCode(), orgRoot.getItemCode(), deptSecond.getDeptName(), false, "hsp/orgMenu.do?verbId=detail&deptCode="+deptSecond.getId().getDeptCode());
					secondNodeList.add(secondDeptNode);			
				}
			}
			orgForm.setSecondNodeList(secondNodeList);
		}

//		
//		if(orgForm.getNodeList() == null)
//			orgForm.setNodeList(new ArrayList<ZTreeNode>());
//		orgForm.setNodeList(nodeList);
	}
	
	/**
	 * 查找合适的机构
	 * @param allList
	 * @param parentId
	 * @return
	 */
	public List<HspConfigBaseinfo> aptDate(List<?> allList,String parentId){
		List<HspConfigBaseinfo> orgList = new ArrayList<HspConfigBaseinfo>();
		if(allList!=null&&allList.size()>0){
			for(int i=0;i<allList.size();i++){
				HspConfigBaseinfo org = (HspConfigBaseinfo)allList.get(i);
				if(parentId==null||parentId.equals("")){
					if(org.getParentItemCode()==null||org.getParentItemCode().equals("")){
						orgList.add(org);
					}
				}else{
					if(org.getParentItemCode()!=null&&org.getParentItemCode().equals(parentId)){
						orgList.add(org);
					}
				}
			}
		}
		
		return orgList;
	}
	
	/**
	 * 查找合适的科室
	 * @param allList
	 * @param orgId
	 * @return
	 */
	public List<HspDeptBaseinfo> aptDeptDate(List<?> allList,String orgId){
		List<HspDeptBaseinfo> deptList = new ArrayList<HspDeptBaseinfo>();
		if(allList!=null&&allList.size()>0){
			for(int i=0;i<allList.size();i++){
				HspDeptBaseinfo org = (HspDeptBaseinfo)allList.get(i);
				if(orgId==null||orgId.equals("")){
					if(org.getId().getHspConfigBaseinfoId()==null||org.getId().getHspConfigBaseinfoId().equals("")){
						deptList.add(org);
					}
				}else{
					if(org.getId().getHspConfigBaseinfoId()!=null&&org.getId().getHspConfigBaseinfoId().equals(orgId)){
						deptList.add(org);
					}
				}
			}
		}
		
		return deptList;
	}

	public IHspConfigBaseinfoDAO getHspConfigBaseinfoDAO() {
		return hspConfigBaseinfoDAO;
	}

	public void setHspConfigBaseinfoDAO(IHspConfigBaseinfoDAO hspConfigBaseinfoDAO) {
		this.hspConfigBaseinfoDAO = hspConfigBaseinfoDAO;
	}

	public IOrgMenuDAO getOrgMenuDao() {
		return orgMenuDao;
	}

	public void setOrgMenuDao(IOrgMenuDAO orgMenuDao) {
		this.orgMenuDao = orgMenuDao;
	}

	@Override
	public String addDept(OrgMenuForm orgForm) {
		HspDeptBaseinfo dept = new HspDeptBaseinfo();
		this.setData(orgForm,dept);
		try {
			dept.setDeptAttrName(this.orgMenuDao.getNameByCode(dept.getDeptAttrCode()));
			this.orgMenuDao.saveDept(dept);
			//形成新的节点
//			String orgId = dept.getHspConfigBaseinfoId();
//			String url = "hsp/orgMenu.do?verbId=detail&deptCode="+dept.getDeptCode()+"&orgId="+orgId;			
//			String deptCode = dept.getDeptCode();
//			String target = "righFrame";
//			String rMenuFlag = "2";
//			String name = dept.getDeptName();
//			String newNode = "{name:\""+name+"\",rMenuFlag:\""+rMenuFlag+"\",target:\""+target+"\",url:\""+url+"\",isParent:false,orgId:\""+orgId+"\",deptCode:\""+deptCode+"\"}";
//			orgForm.setNewNode(newNode);
			orgForm.setStatus("1");
		} catch (Exception e) {
			return "保存出错："+e.getMessage();
		}
		return null;
	}

	private void setData(OrgMenuForm orgForm, HspDeptBaseinfo dept) {
		if(dept.getId() == null){
			HspDeptBaseinfoId id = new HspDeptBaseinfoId();
			dept.setId(id);
		}
		if(orgForm.getSeqNo()!=null&&orgForm.getSeqNo().trim().length()>0){
			dept.setSeqNo(Long.valueOf(orgForm.getSeqNo()));
		}
		if(orgForm.getHspConfigBaseinfoId()!=null&&orgForm.getHspConfigBaseinfoId().trim().length()>0){
			dept.getId().setHspConfigBaseinfoId(orgForm.getHspConfigBaseinfoId());
		}
		if(orgForm.getHspConfigBaseinfoName()!=null&&orgForm.getHspConfigBaseinfoName().trim().length()>0){
			dept.setHspConfigBaseinfoName(orgForm.getHspConfigBaseinfoName());
		}
		if(orgForm.getHealthBureauCode()!=null&&orgForm.getHealthBureauCode().trim().length()>0){
			dept.setHealthBureauCode(orgForm.getHealthBureauCode());
		}
		if(orgForm.getSocialSecurityBureauCode()!=null&&orgForm.getSocialSecurityBureauCode().trim().length()>0){
			dept.setSocialSecurityBureauCode(orgForm.getSocialSecurityBureauCode());
		}
		if(orgForm.getDeptAttrCode()!=null&&orgForm.getDeptAttrCode().trim().length()>0){
			dept.setDeptAttrCode(orgForm.getDeptAttrCode());
		}
		if(orgForm.getDeptAttrName()!=null&&orgForm.getDeptAttrName().trim().length()>0){
			dept.setDeptAttrName(orgForm.getDeptAttrName());
		}
		if(orgForm.getDeptCode()!=null&&orgForm.getDeptCode().trim().length()>0){
			dept.getId().setDeptCode(orgForm.getDeptCode());
		}
		if(orgForm.getDeptName()!=null&&orgForm.getDeptName().trim().length()>0){
			dept.setDeptName(orgForm.getDeptName());
		}
		if(orgForm.getInputCode()!=null&&orgForm.getInputCode().trim().length()>0){
			dept.setInputCode(orgForm.getInputCode());
		}
		if(orgForm.getComments()!=null&&orgForm.getComments().trim().length()>0){
			dept.setComments(orgForm.getComments());
		}
	}

	@Override
	public String delDept(OrgMenuForm orgForm) {
		HspDeptBaseinfo dept = this.orgMenuDao.getDeptById(orgForm.getHspConfigBaseinfoId(), orgForm.getDeptCode());
		try {
			this.orgMenuDao.deleteDept(dept);
		} catch (Exception e) {
			return "删除出错："+e.getMessage();
		}
		return null;
	}

	@Override
	public String findDept(OrgMenuForm orgForm,String orgId,String deptCode) {
		HspDeptBaseinfo dept = this.orgMenuDao.getDeptById(orgId,deptCode);
		if(dept!=null){
			orgForm.setSeqNo(((dept.getSeqNo()==null?"":dept.getSeqNo()).toString()));
			orgForm.setHspConfigBaseinfoId((dept.getId().getHspConfigBaseinfoId()==null?"":dept.getId().getHspConfigBaseinfoId()));
			orgForm.setHspConfigBaseinfoName((dept.getHspConfigBaseinfoName()==null?"":dept.getHspConfigBaseinfoName()));
			orgForm.setHealthBureauCode((dept.getHealthBureauCode()==null?"":dept.getHealthBureauCode()));
			orgForm.setSocialSecurityBureauCode((dept.getSocialSecurityBureauCode()==null?"":dept.getSocialSecurityBureauCode()));
			orgForm.setDeptAttrCode((dept.getDeptAttrCode()==null?"":dept.getDeptAttrCode()));
			orgForm.setDeptAttrName((dept.getDeptAttrName()==null?"":dept.getDeptAttrName()));
			orgForm.setDeptCode((dept.getId().getDeptCode()==null?"":dept.getId().getDeptCode()));
			orgForm.setDeptName((dept.getDeptName()==null?"":dept.getDeptName()));
			orgForm.setInputCode((dept.getInputCode()==null?"":dept.getInputCode()));
			orgForm.setComments((dept.getComments()==null?"":dept.getComments()));
		}else{
			return "没有找到匹配的科室数据！";
		}
		return null;
	}

	@Override
	public String modifyDept(OrgMenuForm orgForm) {
		HspDeptBaseinfo dept = this.orgMenuDao.getDeptById(orgForm.getHspConfigBaseinfoId(), orgForm.getDeptCode());
		if(dept!=null){
			if(orgForm.getSeqNo()!=null&&orgForm.getSeqNo().trim().length()>0){
				dept.setSeqNo(Long.valueOf(orgForm.getSeqNo()));
			}
			if(orgForm.getHealthBureauCode()!=null&&orgForm.getHealthBureauCode().trim().length()>0){
				dept.setHealthBureauCode(orgForm.getHealthBureauCode());
			}
			if(orgForm.getSocialSecurityBureauCode()!=null&&orgForm.getSocialSecurityBureauCode().trim().length()>0){
				dept.setSocialSecurityBureauCode(orgForm.getSocialSecurityBureauCode());
			}
			if(orgForm.getDeptAttrCode()!=null&&orgForm.getDeptAttrCode().trim().length()>0){
				dept.setDeptAttrCode(orgForm.getDeptAttrCode());
				dept.setDeptAttrName(this.orgMenuDao.getNameByCode(dept.getDeptAttrCode()));
			}
			if(orgForm.getDeptAttrName()!=null&&orgForm.getDeptAttrName().trim().length()>0){
				dept.setDeptAttrName(orgForm.getDeptAttrName());
			}
			if(orgForm.getDeptName()!=null&&orgForm.getDeptName().trim().length()>0){
				dept.setDeptName(orgForm.getDeptName());
			}
			if(orgForm.getInputCode()!=null&&orgForm.getInputCode().trim().length()>0){
				dept.setInputCode(orgForm.getInputCode());
			}
			if(orgForm.getComments()!=null&&orgForm.getComments().trim().length()>0){
				dept.setComments(orgForm.getComments());
			}
			
			try {
				this.orgMenuDao.updateDept(dept);
				orgForm.setStatus("1");
			} catch (Exception e) {
				return "更新出错："+e.getMessage();
			}
		}else{
			return "错误的科室对象！";
		}
		return null;
	}

	@Override
	public void createChildNode(OrgMenuForm orgForm, String treeId,String treeName,
			String orgId,String itemCode,String deptCode,String rMenuFlag) {
		
		List<ZTreeNode> childNodeList = new ArrayList<ZTreeNode>();		
		
		if(orgId!=null&&orgId.equals("null")&&
				itemCode!=null&&itemCode.equals("null")){
			List<?> orgRoot = this.orgMenuDao.findRoot();
			if(orgRoot != null && orgRoot.size() > 0){
				for(int i = 0; i < orgRoot.size(); i++){
					Object[] org = (Object[])orgRoot.get(i);
					ZTreeNode node = new ZTreeNode(treeId+(i+1), "1", Converter.toBlank(org[2]), true);
					node.setItemCode(Converter.toBlank(org[1]));
					node.setOrgId(Converter.toBlank(org[0]));
					node.setRMenuFlag("0");
					childNodeList.add(node);
				}
			}
		}else{
			if(rMenuFlag!=null&&rMenuFlag.equals("1")){//科室
				List<?> deptList = this.orgMenuDao.findDept(orgId);
				int index = 1;
				if(deptList!=null&&deptList.size()>0){
					for(int i=0;i<deptList.size();i++){
						HspDeptBaseinfo dept = (HspDeptBaseinfo) deptList.get(i);
						ZTreeNode node = new ZTreeNode(treeId+(i+1), treeId, dept.getDeptName(), false,"hsp/orgMenu.do?verbId=detail&deptCode="+dept.getId().getDeptCode()+"&orgId="+orgId);
						node.setOrgId(orgId);
						node.setDeptCode(dept.getId().getDeptCode());
						node.setRMenuFlag("2");
						childNodeList.add(node);
						index++ ;
					}
				}
				ZTreeNode node = new ZTreeNode(treeId+(index+1), treeId, "其他科室", false, "");
				node.setOrgId(orgId);
				node.setDeptCode("");
				node.setRMenuFlag("4");
				childNodeList.add(node);
			}else if(rMenuFlag!=null&&rMenuFlag.trim().equals("0")){//机构
				ZTreeNode currtOrg = new ZTreeNode(treeId+"1", treeId, treeName, true);
				currtOrg.setOrgId(orgId);
				currtOrg.setItemCode("-1");
				currtOrg.setRMenuFlag("1");
				childNodeList.add(currtOrg);	
				List<?> orgList = this.orgMenuDao.findChild(itemCode);
				if(orgList!=null&&orgList.size()>0){
					for(int i=0;i<orgList.size();i++){
						//0:id, 1:itemCode, 2:itemName
						Object[] org = (Object[])orgList.get(i);
						ZTreeNode node = new ZTreeNode(treeId+(i+2), treeId, Converter.toBlank(org[2]), true);
						node.setItemCode(Converter.toBlank(org[1]));
						node.setOrgId(Converter.toBlank(org[0]));
						node.setRMenuFlag("0");
						childNodeList.add(node);
					}
				}							
			}else if(rMenuFlag!=null&&rMenuFlag.trim().equals("2")){
				List<?> staffList = this.orgMenuDao.findStaff(orgId,deptCode);
				if(staffList!=null&&staffList.size()>0){
					for(int i=0;i<staffList.size();i++){
						HspStaffBaseinfo staff = (HspStaffBaseinfo)staffList.get(i);
						ZTreeNode node = new ZTreeNode(treeId+(i+1), treeId, staff.getName(), false, "hsp/orgMenu.do?verbId=staffDetail&hspStaffId="+staff.getId());
						node.setHspStaffId(staff.getId());
						node.setRMenuFlag("3");
						childNodeList.add(node);
					}
				}
			}else if(rMenuFlag!=null&&rMenuFlag.trim().equals("4")){
				List<?> staffList = this.orgMenuDao.findStaff(orgId,null);
				if(staffList!=null&&staffList.size()>0){
					for(int i=0;i<staffList.size();i++){
						HspStaffBaseinfo staff = (HspStaffBaseinfo)staffList.get(i);
						ZTreeNode node = new ZTreeNode(treeId+(i+1), treeId, staff.getName(), false, "hsp/orgMenu.do?verbId=staffDetail&hspStaffId="+staff.getId());
						node.setHspStaffId(staff.getId());
						node.setRMenuFlag("3");
						childNodeList.add(node);
					}
				}
			}				
		}
		
		orgForm.setChildNodeList(childNodeList);
	}

	@Override
	public void init(OrgMenuForm orgForm) {
		List<?> deptAttrList = this.orgMenuDao.findAllDeptAttr();
		if(orgForm.getDeptAttrList()==null)
			orgForm.setDeptAttrList(new ArrayList<CommConfigDeptAttr>());
		if(deptAttrList!=null&&deptAttrList.size()>0){
			for(int i=0;i<deptAttrList.size();i++){
				CommConfigDeptAttr deptAttr = (CommConfigDeptAttr)deptAttrList.get(i);
				orgForm.getDeptAttrList().add(deptAttr);
			}
		}
	}

	@Override
	public void findStaffById(String hspStaffId, OrgMenuForm orgForm) {
		HspStaffBaseinfo staff = this.orgMenuDao.findStaffById(hspStaffId);
		if(staff!=null&&staff.getId()!=null){
			this.setStaffForm(orgForm,staff);
		}else{
			orgForm.setMessage("未找到此人！");
		}
	}

	private void setStaffForm(OrgMenuForm form, HspStaffBaseinfo data) {
		SimpleDateFormat sdf1 = new SimpleDateFormat( "yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat( "yyyy-MM");
		// 取ID设置医院的名称
		if (data.getHspConfigBaseinfoId() != null) {
			String id = data.getHspConfigBaseinfoId();
			String name = this.hspConfigBaseinfoDAO.getItemName(id);
			form.setHspConfigBaseinfoName(name);
			form.setHspConfigBaseinfoId(id);
			//科室
			if(data.getRelatedDepartment()!=null&&data.getRelatedDepartment().trim().length()>0){
				HspDeptBaseinfo dept = this.orgMenuDao.getDeptById(id,data.getRelatedDepartment());
				form.setDeptName(dept != null ? dept.getDeptName() : "");
			}else{
				form.setDeptName("");
			}
		}
		form.setId(data.getId());
		form.setIdHidden(data.getId());
		/**人员编码(卫生局统一)*/
		String personnelCode = HspInit.getProperty("personnelCode");
		form.setPersonnelCode(personnelCode);
//		if(personnelCode.equals("false")){
//		List<?> empNo=commonDAO.findListBySql("select max(to_number(a.emp_no)) from hsp.hsp_staff_baseinfo a");
//		for(int i=0;i<empNo.size();i++){
//			Object obj=(Object)empNo.get(0);
//			int value=Integer.parseInt(obj.toString());
//			form.setEmpNo((value+1)+"");
//			}
//		}else{
			form.setEmpNo(transNullToString(data.getEmpNo()));	
//		}
			
			form.setName(transNullToString(data.getName()));
			form.setIdNo(transNullToString(data.getIdNo()));
		if (data.getBirthday() != null) {
			String date = sdf1.format(data.getBirthday());
			form.setBirthdayYear(date);
//			form.setBirthdayMonth(date.substring(5, 7));
//			form.setBirthdayDay(date.substring(8, 10));
		}
			form.setCommConfigSexId(transNullToString(data.getCommConfigSexId()));
		if (data.getCommConfigNationalityId() != null){
			String id = data.getCommConfigNationalityId();
			CommConfigNationality nationality = this.hspStaffBaseinfoDAO.getNationalName(id);
			String name="";
			if(nationality!=null&&nationality.getItemName().trim().length()>0){
				name=nationality.getItemName();
			}
//			System.out.println("ID是"+id+"民族是"+name);
			form.setCommConfigNationalityId(data.getCommConfigNationalityId());
			form.setTheNationalName(name);
		}
		if (data.getStartWorkDate() != null) {
			String date = sdf2.format(data.getStartWorkDate());
			form.setStartWorkDateYear(date);
			//form.setStartWorkDateMonth(date.substring(5,7));
		}
			form.setOfficeTel(transNullToString(data.getOfficeTel()));
			form.setMobileTel(transNullToString(data.getMobileTel()));
		/**从事专业类别*/
		form.setCommDictPublicCharId1(transNullToString(data.getCommDictPublicCharId1()));
		/**（ 医师/ 卫生监督员）执业证书编码*/
		form.setWorkCertificateNo(transNullToString(data.getWorkCertificateNo()));
		form.setCommDictPublicCharId2(transNullToString(data.getCommDictPublicCharId2()));
		form.setWorkCertificateNo(transNullToString(data.getWorkCertificateNo()));
		/**行政职务*/
		form.setCommConfigPositiontitleId(transNullToString(data.getCommConfigPositiontitleId()));
		/**专业技术资格（评）*/
		form.setCommConfigEmptitleId(transNullToString(data.getCommConfigEmptitleId()));
		/**专业技术职务（聘）*/
		form.setCommDictPublicCharId3(transNullToString(data.getCommDictPublicCharId3()));
		/**学历*/
		form.setCommConfigDegreeId(transNullToString(data.getCommConfigDegreeId()));
		/**学位*/
		form.setCommConfigDegreeLevelId(transNullToString(data.getCommConfigDegreeLevelId()));
		/**所学专业*/
		form.setCommConfigProfessionId(transNullToString(data.getCommConfigProfessionId()));
		/**在位标志*/
		form.setIslocation(data.getIslocation());
		/**记录日期*/
//		private Date createDate;
		/**记录人员ID*/
		form.setCreateUserId(transNullToString(data.getCreateUserId()));
		/**记录人员名称*/
		form.setCreateUserName(transNullToString(data.getCreateUserName()));
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		
		
		
		
		try {
			if(data.getCreateDate()!=null){
			form.setCreateDate(sdf.format(data.getCreateDate()));
			form.setCreateDate( transNullToString(data.getCreateDate()));
			form.setYear( transNullToString(data.getCreateDate()).substring(0,4));
		    form.setMonth( transNullToString(data.getCreateDate()).substring(5,7));
			form.setDay( transNullToString(data.getCreateDate()).substring(8,10));
			} 
		} catch (RuntimeException e) { 
			e.printStackTrace();
		} 
	}
	public String transNullToString(Object obj) {
		String temp = "";
		if (obj != null) {
			temp = String.valueOf(obj).trim();
		}
		return temp;
	}

	@Override
	public void initStaff(OrgMenuForm orgForm, String hspStaffId, String orgId,
			String deptCode,String menuId) {
		String personnelCode = HspInit.getProperty("personnelCode");
		orgForm.setPersonnelCode(personnelCode);
		if(personnelCode.equals("false")){
			List<?> empNo=commonDAO.findListBySql("select max(to_number((translate(SUBSTR(t.emp_no,length(t.emp_no) - 5,length(t.emp_no)),'0123456789' || SUBSTR(t.emp_no,length(t.emp_no) - 5,length(t.emp_no)),'0123456789')))) from hsp.hsp_staff_baseinfo t");
			try {
				for(int i=0;i<empNo.size();i++){
					Object obj=(Object)empNo.get(0);
					long value=Long.parseLong(obj.toString());
					orgForm.setEmpNo((value+1)+"");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		orgForm.setHspConfigBaseinfoId1(orgId);
		HspConfigBaseinfo org = this.hspConfigBaseinfoDAO.getById(orgId);
		orgForm.setHspConfigBaseinfoName(org.getItemName());
		if(deptCode!=null&&deptCode.trim().length()>0){
			orgForm.setRelatedDepartment(deptCode);
			HspDeptBaseinfo dept = this.orgMenuDao.getDeptById(orgId, deptCode);
			orgForm.setDeptName(dept.getDeptName());
		}else{
			orgForm.setRelatedDepartment("");
			orgForm.setDeptName("");
		}
		
		this.init1(orgForm);
	}

	@SuppressWarnings("unchecked")
	private void init1(OrgMenuForm orgForm) {
		orgForm.setHspConfigBaseinfoIdList((List<HspStaffBaseinfo>) this.hspStaffBaseinfoDAO.hspConfigBaseinfoIdList());
		orgForm.setCommConfigSexIdList(this.hspStaffBaseinfoDAO.sexList());
		orgForm.setCommDictPublicCharId1List(this.hspStaffBaseinfoDAO.commDictPublicCharId1List());
		orgForm.setCommDictPublicCharId2List(this.hspStaffBaseinfoDAO.commDictPublicCharId2List());
		orgForm.setCommConfigPositiontitleIdList(this.hspStaffBaseinfoDAO.commConfigPositiontitleIdList());
		//orgForm.setCommConfigEmptitleIdList(this.hspStaffBaseinfoDAO.commConfigEmptitleIdList());
		orgForm.setCommDictPublicCharId3List(this.hspStaffBaseinfoDAO.commDictPublicCharId3List());
		orgForm.setCommConfigDegreeIdList(this.hspStaffBaseinfoDAO.commConfigDegreeIdList());
		orgForm.setCommConfigDegreeLevelIdList(this.hspStaffBaseinfoDAO.commConfigDegreeLevelIdList());
		orgForm.setCommConfigProfessionIdList(this.hspStaffBaseinfoDAO.commConfigProfessionIdList());
		orgForm.setCommConfigNationalityIdL(this.hspStaffBaseinfoDAO.commConfigNationalityIdList());
		
		String personnelCode = HspInit.getProperty("personnelCode");
		orgForm.setPersonnelCode(personnelCode);
	}

	@Override
	public void updateStaff(OrgMenuForm orgForm, String hspStaffId) {
		this.findStaffById(hspStaffId, orgForm);
		this.init1(orgForm);
	}

	@Override
	public int checkData(String empNo) {
		HspStaffBaseinfo bean1=this.hspStaffBaseinfoDAO.getByEmpNo(empNo);
//		SecurityStaffBaseinfo bean2=this.hspStaffBaseinfoDAO.getByStaffCode(empNo);
		if(bean1==null){
			return 1;
		}else{
			return 0;
		}
	}

	@Override
	public void saveStaff(OrgMenuForm orgForm) {
		HspStaffBaseinfo data1 = new HspStaffBaseinfo();
		this.setStaffData(orgForm, data1);
		
		this.hspStaffBaseinfoDAO.save(data1);
		String id = data1.getId();
		orgForm.setId(id);
		orgForm.setStatus("1");
		String securityStaffBaseinfoAdd=HspInit.getProperty("SECURITY_STAFF_BASEINFO_ADD");
		if(securityStaffBaseinfoAdd.equals("true")){
			SecurityStaffBaseinfo data2 = new SecurityStaffBaseinfo();
			this.setDateToSecurity(data1, data2);
			this.securityStaffBaseinfoDAO.save(data2);
			
			//保存了注册码,未对其进行操作
			String ssbId = data2.getId();
			SecurityLicense sl = new SecurityLicense();
			sl.setSecurityStaffBaseinfoId(ssbId);
			sl.setRegTime(new Date());
			String regCode = this.generateRegCode(5, 5);
			sl.setRegistCode(regCode);
			this.securityLicenseDAO.save(sl);		
		}
		orgForm.setStatus("1");
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

	public void setForm(HspStaffBaseinfo data, HspStaffBaseinfoForm form) {
		SimpleDateFormat sdf1 = new SimpleDateFormat( "yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat( "yyyy-MM");
		// 取ID设置医院的名称
		if (data.getHspConfigBaseinfoId() != null) {
			String id = data.getHspConfigBaseinfoId();
			String name = this.hspConfigBaseinfoDAO.getItemName(id);
			form.setTheHspName(name);
			form.setHspConfigBaseinfoId(id);
		}
		/**人员编码(卫生局统一)*/
		String personnelCode = HspInit.getProperty("personnelCode");
		form.setPersonnelCode(personnelCode);
//		if(personnelCode.equals("false")){
//		List<?> empNo=commonDAO.findListBySql("select max(to_number(a.emp_no)) from hsp.hsp_staff_baseinfo a");
//		for(int i=0;i<empNo.size();i++){
//			Object obj=(Object)empNo.get(0);
//			int value=Integer.parseInt(obj.toString());
//			form.setEmpNo((value+1)+"");
//			}
//		}else{
			form.setEmpNo(transNullToString(data.getEmpNo()));	
//		}
			
			form.setName(transNullToString(data.getName()));
			form.setIdNo(transNullToString(data.getIdNo()));
		if (data.getBirthday() != null) {
			String date = sdf1.format(data.getBirthday());
			form.setBirthdayYear(date);
//			form.setBirthdayMonth(date.substring(5, 7));
//			form.setBirthdayDay(date.substring(8, 10));
		}
			form.setCommConfigSexId(transNullToString(data.getCommConfigSexId()));
		if (data.getCommConfigNationalityId() != null){
			String id = data.getCommConfigNationalityId();
			CommConfigNationality nationality = this.hspStaffBaseinfoDAO.getNationalName(id);
			String name="";
			if(nationality!=null&&nationality.getItemName().trim().length()>0){
				name=nationality.getItemName();
			}
//			System.out.println("ID是"+id+"民族是"+name);
			form.setCommConfigNationalityId(data.getCommConfigNationalityId());
			form.setCommConfigNationalityName(name);
		}
		if (data.getStartWorkDate() != null) {
			String date = sdf2.format(data.getStartWorkDate());
			form.setStartWorkDateYear(date);
			//form.setStartWorkDateMonth(date.substring(5,7));
		}
			form.setOfficeTel(transNullToString(data.getOfficeTel()));
			form.setMobileTel(transNullToString(data.getMobileTel()));
		/**从事专业类别*/
		form.setCommDictPublicCharId1(transNullToString(data.getCommDictPublicCharId1()));
		/**（ 医师/ 卫生监督员）执业证书编码*/
		form.setWorkCertificateNo(transNullToString(data.getWorkCertificateNo()));
		form.setCommDictPublicCharId2(transNullToString(data.getCommDictPublicCharId2()));
		form.setWorkCertificateNo(transNullToString(data.getWorkCertificateNo()));
		/**行政职务*/
		form.setCommConfigPositiontitleId(transNullToString(data.getCommConfigPositiontitleId()));
		/**专业技术资格（评）*/
		form.setCommConfigEmptitleId(transNullToString(data.getCommConfigEmptitleId()));
		/**专业技术职务（聘）*/
		form.setCommDictPublicCharId3(transNullToString(data.getCommDictPublicCharId3()));
		/**学历*/
		form.setCommConfigDegreeId(transNullToString(data.getCommConfigDegreeId()));
		/**学位*/
		form.setCommConfigDegreeLevelId(transNullToString(data.getCommConfigDegreeLevelId()));
		/**所学专业*/
		form.setCommConfigProfessionId(transNullToString(data.getCommConfigProfessionId()));
		/**在位标志*/
		form.setIslocation(data.getIslocation());
		/**记录日期*/
//		private Date createDate;
		/**记录人员ID*/
		form.setCreateUserId(transNullToString(data.getCreateUserId()));
		/**记录人员名称*/
		form.setCreateUserName(transNullToString(data.getCreateUserName()));
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			if(data.getCreateDate()!=null){
			form.setCreateDate(sdf.format(data.getCreateDate()));
			form.setCreateDate( transNullToString(data.getCreateDate()));
			form.setYear( transNullToString(data.getCreateDate()).substring(0,4));
		    form.setMonth( transNullToString(data.getCreateDate()).substring(5,7));
			form.setDay( transNullToString(data.getCreateDate()).substring(8,10));
			} 
			} catch (RuntimeException e) { 
			e.printStackTrace();
		} 
	}

	private void setStaffData(OrgMenuForm form, HspStaffBaseinfo data) {
		/**ID*/
		//	data.setId(transNullToString(form.getId()));
		/**组织机构ID*/
		if(form.getHspConfigBaseinfoId() != null)
			data.setHspConfigBaseinfoId(transNullToString(form.getHspConfigBaseinfoId()));
		else
			data.setHspConfigBaseinfoId(transNullToString(form.getHspConfigBaseinfoIdHidden()));
		/**人员编码(卫生局统一)*/
		
		data.setEmpNo(transNullToString(form.getEmpNo()));

		/**姓名*/
		data.setName(transNullToString(form.getName()));
		/**证件号码码*/
		data.setIdNo(transNullToString(form.getIdNo()));
		/**出生日期*/
		data.setBirthday(this.setStringAsDate3(form.getBirthdayYear(), "", ""));
		/**性别*/
		data.setCommConfigSexId(transNullToString(form.getCommConfigSexId()));
		/**民族*/
		data.setCommConfigNationalityId(transNullToString(form.getNationalityIdHidden()));
		/**参加工作日期*/
		data.setStartWorkDate(this.setStringAsDate2(
				form.getStartWorkDateYear(), ""));
		/**办公室电话*/
		data.setOfficeTel(transNullToString(form.getOfficeTel()));
		/**手机号码*/
		data.setMobileTel(transNullToString(form.getMobileTel()));
		/**从事专业类别*/
		data.setCommDictPublicCharId1(transNullToString(form.getCommDictPublicCharId1()));
		/**（ 医师/ 卫生监督员）执业证书编码*/
		data.setWorkCertificateNo(transNullToString(form.getWorkCertificateNo()));
		/**医师执业类别*/
		data.setCommDictPublicCharId2(transNullToString(form.getCommDictPublicCharId2()));
		/**行政职务*/
		data.setCommConfigPositiontitleId(transNullToString(form.getCommConfigPositiontitleId()));
		/**专业技术资格（评）*/
		data.setCommConfigEmptitleId(transNullToString(form.getCommConfigEmptitleId()));
		/**专业技术职务（聘）*/
		data.setCommDictPublicCharId3(transNullToString(form.getCommDictPublicCharId3()));
		/**学历*/
		data.setCommConfigDegreeId(transNullToString(form.getCommConfigDegreeId()));
		/**学位*/
		data.setCommConfigDegreeLevelId(transNullToString(form.getCommConfigDegreeLevelId()));
		/**所学专业*/
		data.setCommConfigProfessionId(transNullToString(form.getCommConfigProfessionId()));
		/**在位标志*/
		data.setIslocation(form.getIslocation());
		/**记录日期*/
//		private Date createDate;
		/**记录人员ID*/
		data.setCreateUserId(transNullToString(form.getCreateUserId()));
		/**记录人员名称*/
		data.setCreateUserName(transNullToString(form.getCreateUserName()));
//		Date d =UtilTrans.transStringToDate(form.getCreateDate(),"yyyy-MM-dd HH:mm:ss");
	
		data.setCreateDate(UtilTrans.transStringToDate(form.getCreateDate(),"yyyy-MM-dd HH:mm:ss"));
		//所属科室
		data.setRelatedDepartment(transNullToString(form.getRelatedDepartment()));
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
	public void updateStaff(OrgMenuForm orgForm) {
		try{
//			System.out.println("123----"+hsbForm.getId()+"dddd");
			HspStaffBaseinfo hsb = this.hspStaffBaseinfoDAO.findById(orgForm.getId());
			orgForm.setCreateDate(UtilTrans.transDateToStringFull(hsb.getCreateDate()));
			this.setStaffData(orgForm, hsb);
			
//			System.out.println("123----");
			this.hspStaffBaseinfoDAO.update(hsb);
			String securityStaffBaseinfoAdd=HspInit.getProperty("SECURITY_STAFF_BASEINFO_ADD");
			if(securityStaffBaseinfoAdd.equals("true")){
				SecurityStaffBaseinfo ssb = this.hspStaffBaseinfoDAO.getSecurityStaffBaseinfoById(hsb.getId());
				this.setDateToSecurity(hsb, ssb);
				this.securityStaffBaseinfoDAO.update(ssb);			
			}
			orgForm.setStatus("1");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void delStaff(OrgMenuForm orgForm, String hspStaffId) {
		//删除注册码 SECURITY STAFF BASEINFO HSP STAFF BASEINFO 
		//
		HspStaffBaseinfo hsp = this.hspStaffBaseinfoDAO.findById(hspStaffId);
		String securityStaffBaseinfoAdd=HspInit.getProperty("SECURITY_STAFF_BASEINFO_ADD");
		if(securityStaffBaseinfoAdd.equals("true")){
			SecurityStaffBaseinfo	SecurityStaffBaseinfobean=this.hspStaffBaseinfoDAO.getSecurityStaffBaseinfoById(hspStaffId);
			if(SecurityStaffBaseinfobean!=null){
				String hspId = hsp.getId();
				SecurityStaffBaseinfo ssb = this.hspStaffBaseinfoDAO.getSecurityStaffBaseinfoById(hspId);
				String ssbId = ssb.getId();
				SecurityLicense sl = this.securityLicenseDAO.findBySecurityStaffBaseinfoId(ssbId);
				this.securityLicenseDAO.delete(sl);
				this.securityStaffBaseinfoDAO.delete(ssb);
			
			}		
		}
		this.hspStaffBaseinfoDAO.delete(hsp);
	}

	@Override
	public void moveStaff(OrgMenuForm orgForm, String hspStaffId, String orgId,
			String deptCode) {
		HspStaffBaseinfo hsp = this.hspStaffBaseinfoDAO.findById(hspStaffId);
		if(orgId!=null&&orgId.trim().length()>0){
			hsp.setHspConfigBaseinfoId(orgId);
		}
		if(deptCode!=null&&deptCode.trim().length()>0){
			hsp.setRelatedDepartment(deptCode);
		}else{
			hsp.setRelatedDepartment(null);
		}
		this.orgMenuDao.updateStaff(hsp);
	}

	@Override
	public void query(OrgMenuForm orgForm) {
		String queryType = orgForm.getQueryType();
		String queryKey = orgForm.getQueryKey();
		String queryValue = orgForm.getQueryValue();
		String itemCode = orgForm.getItemCode();
		if(queryType!=null){
			if(queryType.equals("1")){
				if(queryKey!=null){
					List<?> deptList = this.orgMenuDao.findDeptByName(itemCode,queryKey,queryValue);
					
					if(deptList!=null&&deptList.size()>0){
						for(int i=0;i<deptList.size();i++){
							Object[] temp = (Object[])deptList.get(i);
							String deptCode = (String)temp[0];
							String deptName = (String)temp[1];
							String orgId3 = (String)temp[2];
							String orgCode3 = (String)temp[3];
							String orgName3 = (String)temp[4];
							String orgId2 = (String)temp[5];
							String orgCode2 = (String)temp[6];
							String orgName2 = (String)temp[7];
							String orgId1 = (String)temp[8];
							String orgCode1 = (String)temp[9];
							String orgName1 = (String)temp[10];
							
							if(orgId1!=null&&orgId1.trim().length()>0){
								//
								if(orgForm.getFirstNodeList()==null)
									orgForm.setFirstNodeList(new ArrayList<ZTreeNode>());
								List<ZTreeNode> firstList = orgForm.getFirstNodeList();
								boolean flag1 = true;
								if(firstList!=null&&firstList.size()>0){
									for(ZTreeNode node : firstList){
										if(node.getId().equals(orgId1)){
											flag1 = false;
										}
									}
								}
								if(flag1){
									ZTreeNode node1 = new ZTreeNode(orgId1, "1", orgName1, true);
									node1.setItemCode(orgCode1);
									node1.setRMenuFlag("0");
									node1.setOrgId(orgId1);
									orgForm.getFirstNodeList().add(node1);
								}
								//
								if(orgForm.getSecondNodeList()==null)
									orgForm.setSecondNodeList(new ArrayList<ZTreeNode>());
								List<ZTreeNode> secondList = orgForm.getSecondNodeList();
								boolean flag2 = true;
								if(secondList!=null&&secondList.size()>0){
									for(ZTreeNode node : secondList){
										if(node.getId().equals(orgId2)){
											flag2 = false;
										}
									}
								}
								if(flag2){
									ZTreeNode node2 = new ZTreeNode(orgId2, orgId1, orgName2, true);
									node2.setItemCode(orgCode2);
									node2.setRMenuFlag("0");
									node2.setOrgId(orgId2);
									orgForm.getSecondNodeList().add(node2);
								}
								//
								if(orgForm.getThirdNodeList()==null)
									orgForm.setThirdNodeList(new ArrayList<ZTreeNode>());
								List<ZTreeNode> thirdList = orgForm.getThirdNodeList();
								boolean flag3 = true;
								if(thirdList!=null&&thirdList.size()>0){
									for(ZTreeNode node : thirdList){
										if(node.getId().equals(orgId3)){
											flag3 = false;
										}
									}
								}
								if(flag3){																
									ZTreeNode node3 = new ZTreeNode(orgId3, orgId2, orgName3, true);
									node3.setItemCode(orgCode3);
									node3.setRMenuFlag("1");
									node3.setDeptCode("-1");
									node3.setOrgId(orgId3);
									orgForm.getThirdNodeList().add(node3);
								}
								//
								if(orgForm.getFourthNodeList()==null)
									orgForm.setFourthNodeList(new ArrayList<ZTreeNode>());
								List<ZTreeNode> fourthList = orgForm.getFourthNodeList();
								boolean flag4 = true;
								if(fourthList!=null&&fourthList.size()>0){
									for(ZTreeNode node : fourthList){
										if(node.getId().equals(deptCode)){
											flag4 = false;
										}
									}
								}
								if(flag4){
									ZTreeNode node4 = new ZTreeNode();
									if(deptCode!=null&&deptCode.trim().length()>0){
										node4.setId(deptCode);
										node4.setName(deptName);
										node4.setDeptCode(deptCode);
										node4.setRMenuFlag("2");
										node4.setUrl("hsp/orgMenu.do?verbId=detail&deptCode="+deptCode+"&orgId="+orgId3);
									}else{
										node4.setId(orgId3+"_qitakeshi");
										node4.setName("其他科室");
										node4.setDeptCode(orgId3+"_qitakeshi");
										node4.setRMenuFlag("4");
										node4.setUrl("");
									}
									
									node4.setPId(orgId3);							
									node4.setOrgId(orgId3);
									node4.setParent(false);								
									orgForm.getFourthNodeList().add(node4);
								}							
							}else if(orgId2!=null&&orgId2.trim().length()>0){
								//
								if(orgForm.getFirstNodeList()==null)
									orgForm.setFirstNodeList(new ArrayList<ZTreeNode>());
								List<ZTreeNode> firstList = orgForm.getFirstNodeList();
								boolean flag1 = true;
								if(firstList!=null&&firstList.size()>0){
									for(ZTreeNode node : firstList){
										if(node.getId().equals(orgId2)){
											flag1 = false;
										}
									}
								}
								if(flag1){
									ZTreeNode node1 = new ZTreeNode(orgId2, "1", orgName2, true);
									node1.setItemCode(orgCode2);
									node1.setRMenuFlag("0");
									node1.setOrgId(orgId2);
									orgForm.getFirstNodeList().add(node1);
								}
								//
								if(orgForm.getSecondNodeList()==null)
									orgForm.setSecondNodeList(new ArrayList<ZTreeNode>());
								List<ZTreeNode> secondList = orgForm.getSecondNodeList();
								boolean flag2 = true;
								if(secondList!=null&&secondList.size()>0){
									for(ZTreeNode node : secondList){
										if(node.getId().equals(orgId3)){
											flag2 = false;
										}
									}
								}
								if(flag2){																
									ZTreeNode node2 = new ZTreeNode(orgId3, orgId2, orgName3, true);
									node2.setItemCode(orgCode3);
									node2.setRMenuFlag("1");
									node2.setDeptCode("-1");
									node2.setOrgId(orgId3);
									orgForm.getSecondNodeList().add(node2);
								}
								//
								if(orgForm.getThirdNodeList()==null)
									orgForm.setThirdNodeList(new ArrayList<ZTreeNode>());
								List<ZTreeNode> thirdList = orgForm.getThirdNodeList();
								boolean flag3 = true;
								if(thirdList!=null&&thirdList.size()>0){
									for(ZTreeNode node : thirdList){
										if(node.getId().equals(deptCode)){
											flag3 = false;
										}
									}
								}
								if(flag3){
									ZTreeNode node3 = new ZTreeNode();
									if(deptCode!=null&&deptCode.trim().length()>0){
										node3.setId(deptCode);
										node3.setName(deptName);
										node3.setDeptCode(deptCode);
										node3.setRMenuFlag("2");
										node3.setUrl("hsp/orgMenu.do?verbId=detail&deptCode="+deptCode+"&orgId="+orgId3);
									}else{
										node3.setId(orgId3+"_qitakeshi");
										node3.setName("其他科室");
										node3.setDeptCode(orgId3+"_qitakeshi");
										node3.setRMenuFlag("4");
										node3.setUrl("");
									}
									
									node3.setPId(orgId3);							
									node3.setOrgId(orgId3);
									node3.setParent(false);								
									orgForm.getThirdNodeList().add(node3);
								}								
							}else{
								//
								if(orgForm.getFirstNodeList()==null)
									orgForm.setFirstNodeList(new ArrayList<ZTreeNode>());
								List<ZTreeNode> firstList = orgForm.getFirstNodeList();
								boolean flag1 = true;
								if(firstList!=null&&firstList.size()>0){
									for(ZTreeNode node : firstList){
										if(node.getId().equals(orgId3)){
											flag1 = false;
										}
									}
								}
								if(flag1){																
									ZTreeNode node1 = new ZTreeNode(orgId3, "1", orgName3, true);
									node1.setItemCode(orgCode3);
									node1.setRMenuFlag("1");
									node1.setDeptCode("-1");
									node1.setOrgId(orgId3);
									orgForm.getFirstNodeList().add(node1);
								}
								//
								if(orgForm.getSecondNodeList()==null)
									orgForm.setSecondNodeList(new ArrayList<ZTreeNode>());
								List<ZTreeNode> secondList = orgForm.getSecondNodeList();
								boolean flag2 = true;
								if(secondList!=null&&secondList.size()>0){
									for(ZTreeNode node : secondList){
										if(node.getId().equals(deptCode)){
											flag2 = false;
										}
									}
								}
								if(flag2){
									ZTreeNode node2 = new ZTreeNode();
									if(deptCode!=null&&deptCode.trim().length()>0){
										node2.setId(deptCode);
										node2.setName(deptName);
										node2.setDeptCode(deptCode);
										node2.setRMenuFlag("2");
										node2.setUrl("hsp/orgMenu.do?verbId=detail&deptCode="+deptCode+"&orgId="+orgId3);
									}else{
										node2.setId(orgId3+"_qitakeshi");
										node2.setName("其他科室");
										node2.setDeptCode(orgId3+"_qitakeshi");
										node2.setRMenuFlag("4");
										node2.setUrl("");
									}
									
									node2.setPId(orgId3);							
									node2.setOrgId(orgId3);
									node2.setParent(false);								
									orgForm.getSecondNodeList().add(node2);
								}								
							}
						}
					}
				}
			}else if(queryType.equals("2")){
				if(queryKey!=null){
					List<?> staffList = this.orgMenuDao.findStaffByName(itemCode,queryKey,queryValue);
					if(staffList!=null&&staffList.size()>0){
						for(int i=0;i<staffList.size();i++){
							Object[] temp = (Object[])staffList.get(i);
							String id = (String)temp[0];
							String name = (String)temp[1];
							String deptCode = (String)temp[2];
							String deptName = (String)temp[3];
							String hspId = (String)temp[4];
							String hspCode = (String)temp[5];
							String hspName = (String)temp[6];
							String hspId2 = (String)temp[7];
							String hspCode2 = (String)temp[8];
							String hspName2 = (String)temp[9];
							String hspId1 = (String)temp[10];
							String hspCode1 = (String)temp[11];
							String hspName1 = (String)temp[12];
							
							if(hspCode1!=null&&hspCode1.trim().length()>0){
								//
								if(orgForm.getFirstNodeList()==null)
									orgForm.setFirstNodeList(new ArrayList<ZTreeNode>());
								List<ZTreeNode> firstList = orgForm.getFirstNodeList();
								boolean flag1 = true;
								if(firstList!=null&&firstList.size()>0){
									for(ZTreeNode node : firstList){
										if(node.getId().equals(hspId1)){
											flag1 = false;
										}
									}
								}
								if(flag1){
									ZTreeNode node1 = new ZTreeNode(hspId1, "1", hspName1, true);
									node1.setItemCode(hspCode1);
									node1.setRMenuFlag("0");
									node1.setOrgId(hspId1);
									orgForm.getFirstNodeList().add(node1);
								}
								//
								if(orgForm.getSecondNodeList()==null)
									orgForm.setSecondNodeList(new ArrayList<ZTreeNode>());
								List<ZTreeNode> secondList = orgForm.getSecondNodeList();
								boolean flag2 = true;
								if(secondList!=null&&secondList.size()>0){
									for(ZTreeNode node : secondList){
										if(node.getId().equals(hspId2)){
											flag2 = false;
										}
									}
								}
								if(flag2){
									ZTreeNode node2 = new ZTreeNode(hspId2, hspId1, hspName2, true);
									node2.setItemCode(hspCode2);
									node2.setRMenuFlag("0");
									node2.setOrgId(hspId2);
									orgForm.getSecondNodeList().add(node2);
								}
								//
								if(orgForm.getThirdNodeList()==null)
									orgForm.setThirdNodeList(new ArrayList<ZTreeNode>());
								List<ZTreeNode> thirdList = orgForm.getThirdNodeList();
								boolean flag3 = true;
								if(thirdList!=null&&thirdList.size()>0){
									for(ZTreeNode node : thirdList){
										if(node.getId().equals(hspId)){
											flag3 = false;
										}
									}
								}
								if(flag3){																
									ZTreeNode node3 = new ZTreeNode(hspId, hspId2, hspName, true);
									node3.setItemCode(hspCode);
									node3.setRMenuFlag("1");
									node3.setDeptCode("-1");
									node3.setOrgId(hspId);
									orgForm.getThirdNodeList().add(node3);
								}
								//
								if(orgForm.getFourthNodeList()==null)
									orgForm.setFourthNodeList(new ArrayList<ZTreeNode>());
								List<ZTreeNode> fourthList = orgForm.getFourthNodeList();
								boolean flag4 = true;
								if(fourthList!=null&&fourthList.size()>0){
									for(ZTreeNode node : fourthList){
										if(node.getId().equals(deptCode)){
											flag4 = false;
										}
									}
								}
								if(flag4){
									ZTreeNode node4 = new ZTreeNode();
									if(deptCode!=null&&deptCode.trim().length()>0){
										node4.setId(deptCode);
										node4.setName(deptName);
										node4.setDeptCode(deptCode);
										node4.setRMenuFlag("2");
										node4.setUrl("hsp/orgMenu.do?verbId=detail&deptCode="+deptCode+"&orgId="+hspId);
									}else{
										node4.setId(hspId+"_qitakeshi");
										node4.setName("其他科室");
										node4.setDeptCode(hspId+"_qitakeshi");
										node4.setRMenuFlag("4");
										node4.setUrl("");
									}
									
									node4.setPId(hspId);							
									node4.setOrgId(hspId);
									node4.setParent(true);								
									orgForm.getFourthNodeList().add(node4);
								}
								//
								if(orgForm.getFifthNodeList()==null)
									orgForm.setFifthNodeList(new ArrayList<ZTreeNode>());
								ZTreeNode node5 = new ZTreeNode();
								node5.setId(id);
								node5.setName(name);
								if(deptCode!=null&&deptCode.trim().length()>0){
									node5.setPId(deptCode);
								}else{
									node5.setPId(hspId+"_qitakeshi");
								}							
								node5.setHspStaffId(id);
								node5.setDeptCode(deptCode);
								node5.setOrgId(hspId);
								node5.setParent(false);
								node5.setRMenuFlag("3");
								node5.setUrl("hsp/orgMenu.do?verbId=staffDetail&hspStaffId="+id);
								orgForm.getFifthNodeList().add(node5);
							}else if(hspId2!=null&&hspId2.trim().length()>0){
								//
								if(orgForm.getFirstNodeList()==null)
									orgForm.setFirstNodeList(new ArrayList<ZTreeNode>());
								List<ZTreeNode> firstList = orgForm.getFirstNodeList();
								boolean flag1 = true;
								if(firstList!=null&&firstList.size()>0){
									for(ZTreeNode node : firstList){
										if(node.getId().equals(hspId2)){
											flag1 = false;
										}
									}
								}
								if(flag1){
									ZTreeNode node1 = new ZTreeNode(hspId2, "1", hspName2, true);
									node1.setItemCode(hspCode2);
									node1.setRMenuFlag("0");
									node1.setOrgId(hspId2);
									orgForm.getFirstNodeList().add(node1);
								}
								//
								if(orgForm.getSecondNodeList()==null)
									orgForm.setSecondNodeList(new ArrayList<ZTreeNode>());
								List<ZTreeNode> secondList = orgForm.getSecondNodeList();
								boolean flag2 = true;
								if(secondList!=null&&secondList.size()>0){
									for(ZTreeNode node : secondList){
										if(node.getId().equals(hspId)){
											flag2 = false;
										}
									}
								}
								if(flag2){																
									ZTreeNode node2 = new ZTreeNode(hspId, hspId2, hspName, true);
									node2.setItemCode(hspCode);
									node2.setRMenuFlag("1");
									node2.setDeptCode("-1");
									node2.setOrgId(hspId);
									orgForm.getSecondNodeList().add(node2);
								}
								//
								if(orgForm.getThirdNodeList()==null)
									orgForm.setThirdNodeList(new ArrayList<ZTreeNode>());
								List<ZTreeNode> thirdList = orgForm.getThirdNodeList();
								boolean flag3 = true;
								if(thirdList!=null&&thirdList.size()>0){
									for(ZTreeNode node : thirdList){
										if(node.getId().equals(deptCode)){
											flag3 = false;
										}
									}
								}
								if(flag3){
									ZTreeNode node3 = new ZTreeNode();
									if(deptCode!=null&&deptCode.trim().length()>0){
										node3.setId(deptCode);
										node3.setName(deptName);
										node3.setDeptCode(deptCode);
										node3.setRMenuFlag("2");
										node3.setUrl("hsp/orgMenu.do?verbId=detail&deptCode="+deptCode+"&orgId="+hspId);
									}else{
										node3.setId(hspId+"_qitakeshi");
										node3.setName("其他科室");
										node3.setDeptCode(hspId+"_qitakeshi");
										node3.setRMenuFlag("4");
										node3.setUrl("");
									}
									
									node3.setPId(hspId);							
									node3.setOrgId(hspId);
									node3.setParent(true);								
									orgForm.getThirdNodeList().add(node3);
								}
								//
								if(orgForm.getFourthNodeList()==null)
									orgForm.setFourthNodeList(new ArrayList<ZTreeNode>());
								ZTreeNode node4 = new ZTreeNode();
								node4.setId(id);
								node4.setName(name);
								if(deptCode!=null&&deptCode.trim().length()>0){
									node4.setPId(deptCode);
								}else{
									node4.setPId(hspId+"_qitakeshi");
								}							
								node4.setHspStaffId(id);
								node4.setDeptCode(deptCode);
								node4.setOrgId(hspId);
								node4.setParent(false);
								node4.setRMenuFlag("3");
								node4.setUrl("hsp/orgMenu.do?verbId=staffDetail&hspStaffId="+id);
								orgForm.getFourthNodeList().add(node4);
							}else{
								//
								if(orgForm.getFirstNodeList()==null)
									orgForm.setFirstNodeList(new ArrayList<ZTreeNode>());
								List<ZTreeNode> firstList = orgForm.getFirstNodeList();
								boolean flag1 = true;
								if(firstList!=null&&firstList.size()>0){
									for(ZTreeNode node : firstList){
										if(node.getId().equals(hspId)){
											flag1 = false;
										}
									}
								}
								if(flag1){																
									ZTreeNode node1 = new ZTreeNode(hspId, "1", hspName, true);
									node1.setItemCode(hspCode);
									node1.setRMenuFlag("1");
									node1.setDeptCode("-1");
									node1.setOrgId(hspId);
									orgForm.getFirstNodeList().add(node1);
								}
								//
								if(orgForm.getSecondNodeList()==null)
									orgForm.setSecondNodeList(new ArrayList<ZTreeNode>());
								List<ZTreeNode> secondList = orgForm.getSecondNodeList();
								boolean flag2 = true;
								if(secondList!=null&&secondList.size()>0){
									for(ZTreeNode node : secondList){
										if(node.getId().equals(deptCode)){
											flag2 = false;
										}
									}
								}
								if(flag2){
									ZTreeNode node2 = new ZTreeNode();
									if(deptCode!=null&&deptCode.trim().length()>0){
										node2.setId(deptCode);
										node2.setName(deptName);
										node2.setDeptCode(deptCode);
										node2.setRMenuFlag("2");
										node2.setUrl("hsp/orgMenu.do?verbId=detail&deptCode="+deptCode+"&orgId="+hspId);
									}else{
										node2.setId(hspId+"_qitakeshi");
										node2.setName("其他科室");
										node2.setDeptCode(hspId+"_qitakeshi");
										node2.setRMenuFlag("4");
										node2.setUrl("");
									}
									
									node2.setPId(hspId);							
									node2.setOrgId(hspId);
									node2.setParent(true);								
									orgForm.getSecondNodeList().add(node2);
								}
								//
								if(orgForm.getThirdNodeList()==null)
									orgForm.setThirdNodeList(new ArrayList<ZTreeNode>());
								ZTreeNode node3 = new ZTreeNode();
								node3.setId(id);
								node3.setName(name);
								if(deptCode!=null&&deptCode.trim().length()>0){
									node3.setPId(deptCode);
								}else{
									node3.setPId(hspId+"_qitakeshi");
								}							
								node3.setHspStaffId(id);
								node3.setDeptCode(deptCode);
								node3.setOrgId(hspId);
								node3.setParent(false);
								node3.setRMenuFlag("3");
								node3.setUrl("hsp/orgMenu.do?verbId=staffDetail&hspStaffId="+id);
								orgForm.getThirdNodeList().add(node3);
							}		
						}
					}
				}
			}
		}
		
	}

	@Override
	public void createChildNodeDept(OrgMenuForm orgForm, String treeId,
			String treeName, String orgId, String itemCode, String deptCode,
			String rMenuFlag) {
        List<ZTreeNode> childNodeList = new ArrayList<ZTreeNode>();		
		
		if(orgId!=null&&orgId.equals("null")&&
				itemCode!=null&&itemCode.equals("null")){
			List<?> orgRoot = this.orgMenuDao.findRoot();
			if(orgRoot != null && orgRoot.size() > 0){
				for(int i = 0; i < orgRoot.size(); i++){
					//0:id, 1:itemCode, 2:itemName
					Object[] org = (Object[])orgRoot.get(i);
					ZTreeNode node = new ZTreeNode(treeId+(i+1), "1", Converter.toBlank(org[2]), true);
					node.setItemCode(Converter.toBlank(org[1]));
					node.setOrgId(Converter.toBlank(org[0]));
					node.setRMenuFlag("0");
					childNodeList.add(node);
				}
			}
		}else{
			if(rMenuFlag!=null&&rMenuFlag.equals("1")){//科室
				List<?> deptList = this.orgMenuDao.findDept(orgId);
				int index = 1;
				if(deptList!=null&&deptList.size()>0){
					for(int i=0;i<deptList.size();i++){
						HspDeptBaseinfo dept = (HspDeptBaseinfo) deptList.get(i);
						ZTreeNode node = new ZTreeNode(treeId+(i+1), treeId, dept.getDeptName(), false, "hsp/orgMenu.do?verbId=detail&deptCode="+dept.getId().getDeptCode()+"&orgId="+orgId);
						node.setOrgId(orgId);
						node.setDeptCode(dept.getId().getDeptCode());
						node.setRMenuFlag("2");
						childNodeList.add(node);
						index++ ;
					}
				}
				
			}else if(rMenuFlag!=null&&rMenuFlag.trim().equals("0")){//机构
				ZTreeNode currtOrg = new ZTreeNode(treeId+"1", treeId, treeName, true);
				currtOrg.setOrgId(orgId);
				currtOrg.setItemCode("-1");
				currtOrg.setRMenuFlag("1");
				childNodeList.add(currtOrg);	
				List<?> orgList = this.orgMenuDao.findChild(itemCode);
				if(orgList!=null&&orgList.size()>0){
					for(int i=0;i<orgList.size();i++){
						//0:id, 1:itemCode, 2:itemName
						Object[] org = (Object[]) orgList.get(i);
						ZTreeNode node = new ZTreeNode(treeId+(i+2), treeId, Converter.toBlank(org[2]), true);
						node.setItemCode(Converter.toBlank(org[1]));
						node.setOrgId(Converter.toBlank(org[0]));
						node.setRMenuFlag("0");
						childNodeList.add(node);
					}
				}							
			}				
		}
		
		orgForm.setChildNodeList(childNodeList);
	}
}

