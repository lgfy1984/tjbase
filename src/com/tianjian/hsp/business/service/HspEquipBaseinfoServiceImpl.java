package com.tianjian.hsp.business.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

import com.tianjian.comm.dao.ICommonDAO;
import com.tianjian.hsp.bean.HspDeptBaseinfo;
import com.tianjian.hsp.bean.HspEquipBaseinfo;
import com.tianjian.hsp.bean.ZTreeNode;
import com.tianjian.hsp.business.IHspEquipBaseinfoService;
import com.tianjian.hsp.dao.IHspEquipBaseinfoDAO;
import com.tianjian.hsp.dao.IOrgMenuDAO;
import com.tianjian.hsp.struts.form.HspEquipBaseinfoForm;
import com.tianjian.hsp.struts.servlet.HspInit;
import com.tianjian.util.Converter;
import com.tianjian.util.ResourcesUtil;

public class HspEquipBaseinfoServiceImpl implements IHspEquipBaseinfoService{
	
	private IHspEquipBaseinfoDAO hspEquipBaseinfoDAO;
	private IOrgMenuDAO orgMenuDao;
	private ICommonDAO commonDAO;

	public ICommonDAO getCommonDAO() {
		return commonDAO;
	}

	public void setCommonDAO(ICommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}

	public IOrgMenuDAO getOrgMenuDao() {
		return orgMenuDao;
	}

	public void setOrgMenuDao(IOrgMenuDAO orgMenuDao) {
		this.orgMenuDao = orgMenuDao;
	}

	public IHspEquipBaseinfoDAO getHspEquipBaseinfoDAO() {
		return hspEquipBaseinfoDAO;
	}

	public void setHspEquipBaseinfoDAO(IHspEquipBaseinfoDAO hspEquipBaseinfoDAO) {
		this.hspEquipBaseinfoDAO = hspEquipBaseinfoDAO;
	}
	private boolean isNull(String temp){
		return temp == null || temp.trim().length() <= 0;
	}
	
	private boolean isNotNull(String temp){
		return temp != null && temp.trim().length() > 0;
	}

	public int getCount(HspEquipBaseinfoForm hebForm){
		return this.hspEquipBaseinfoDAO.getCount(hebForm);
	}

	@Override
	public void findEquipList(HspEquipBaseinfoForm hebForm, int curCount,int count) {
		
		List<?> equipList = this.hspEquipBaseinfoDAO.findEquipList(hebForm,curCount,count);
		hebForm.setDataList(equipList);
	}
	
	@Override
	public void init(HspEquipBaseinfoForm form) {
		form.setStateList(hspEquipBaseinfoDAO.getStateList());
		form.setUnitList(hspEquipBaseinfoDAO.getUnitList());
		form.setUseageList(hspEquipBaseinfoDAO.getUseageList());
	}

	@Override
	public void save(HspEquipBaseinfoForm form) {
		HspEquipBaseinfo data = new HspEquipBaseinfo();
		setData(data, form);
		hspEquipBaseinfoDAO.save(data);
	}

	@Override
	public void saveInit(HspEquipBaseinfoForm form) {
		if(isNotNull(form.getOrgId())){
			String[] code_name = this.hspEquipBaseinfoDAO.getHspCodeAndNameById(form.getOrgId());
			form.setHspCode(code_name[0]);
			form.setHspName(code_name[1]);
			if(isNotNull(form.getDeptCode())){
				HspDeptBaseinfo dept = this.orgMenuDao.getDeptById(form.getOrgId(), form.getDeptCode());
				if(dept != null){
					form.setDeptName(dept.getDeptName());
					form.setBdeptCode(dept.getDeptAttrCode());
					form.setBdeptName(dept.getDeptAttrName());
				}else{
					form.setDeptCode("");
					form.setDeptName("");
					form.setBdeptCode("");
					form.setBdeptName("");
				}
			}
		}		
		this.init(form);
	}

	@Override
	public void update(HspEquipBaseinfoForm form) {
		HspEquipBaseinfo data = hspEquipBaseinfoDAO.getById(form.getIdHidden());
		setData(data, form);
		hspEquipBaseinfoDAO.update(data);
	}

	@Override
	public void updateInit(HspEquipBaseinfoForm form) {
		this.getDetail(form);
		this.init(form);
	}

	
	private void setForm(HspEquipBaseinfo data,HspEquipBaseinfoForm form){
		
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			form.setId(Converter.toBlank(data.getId()));
			form.setHspCode(Converter.toBlank(data.getHspCode()));
			String[] id_name = this.hspEquipBaseinfoDAO.getHspIdNameByCode(data.getHspCode());
			form.setOrgId(Converter.toBlank(id_name[0]));
			form.setHspName(Converter.toBlank(id_name[1]));
			if(data.getDeptCode()!=null&&data.getDeptCode().trim().length()>0){
				HspDeptBaseinfo dept = this.orgMenuDao.getDeptById(data.getHspCode(),data.getDeptCode());
				form.setDeptCode(dept.getId().getDeptCode());
				form.setDeptName(dept.getDeptName());
				form.setBdeptCode(dept.getDeptAttrCode());
				form.setBdeptName(dept.getDeptAttrName());
			}else{
				form.setDeptCode("");
				form.setDeptName("");
				form.setBdeptCode("");
				form.setBdeptName("");
			}
			
			form.setEquipName(Converter.toBlank(data.getEquipName()));
			form.setEquipType(Converter.toBlank(data.getEquipType()));
			form.setUnit(Converter.toBlank(data.getUnit()));
			if(data.getUnit()!=null && data.getUnit().length()>0){
				form.setUnitName(Converter.toBlank(hspEquipBaseinfoDAO.getUnitName(data.getUnit())));
			}
			form.setEquipCode(Converter.toBlank(data.getEquipCode()));
			form.setSupplier(Converter.toBlank(data.getSupplier()));
			form.setSupplierTel(Converter.toBlank(data.getSupplierTel()));
			form.setProductor(Converter.toBlank(data.getProductor()));
			form.setProductorTel(Converter.toBlank(data.getProductorTel()));
			form.setProductDate(Converter.toBlank(format.format(data.getProductDate())));
			form.setPurchaseDate(Converter.toBlank(format.format(data.getPurchaseDate())));
			form.setPrice(Converter.toBlank(data.getPrice()));
			form.setPurchaseState(Converter.toBlank(data.getPurchaseState()));
			if(data.getPurchaseState()!=null && data.getPurchaseState().length()>0){
				form.setStateName(Converter.toBlank(hspEquipBaseinfoDAO.getStateName(data.getPurchaseState())));
			}
			form.setDesignLifetime(Converter.toBlank(data.getDesignLifetime()));
			form.setUsage(Converter.toBlank(data.getUsage()));
			if(data.getUsage()!=null && data.getUsage().length()>0){
				form.setUseageName(Converter.toBlank(hspEquipBaseinfoDAO.getUseageName(data.getUsage())));
			}
			form.setSource(Converter.toBlank(data.getSource()));
			form.setSourceName(Converter.toBlank(hspEquipBaseinfoDAO.getCountryName(data.getSource())));
			form.setApplication(Converter.toBlank(data.getApplication()));
			form.setComments(Converter.toBlank(data.getComments()));
			form.setCreateUserId(Converter.toBlank(data.getCreateUserId()));
			form.setCreateUserName(Converter.toBlank(data.getCreateUserName()));
			if(data.getInstrumentUseStartDate()!=null){
				form.setInstrumentUseStartDate(Converter.toBlank(format.format(data.getInstrumentUseStartDate())));
			}
			if(data.getInstrumentUseEndDate()!=null){
				form.setInstrumentUseEndDate(Converter.toBlank(format.format(data.getInstrumentUseEndDate())));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void setData(HspEquipBaseinfo data,HspEquipBaseinfoForm form){
		try {
			data.setHspCode(Converter.toBlank(form.getHspCode()));
			if(isNotNull(form.getOrgId())){
				HspDeptBaseinfo dept = this.orgMenuDao.getDeptById(form.getOrgId(), form.getDeptCode());
				if(dept != null){
					data.setDeptCode(dept.getId().getDeptCode());
					data.setBdeptCode(dept.getDeptAttrCode());
				}else{
					data.setDeptCode("");
					data.setBdeptCode("");
				}
			}
			data.setEquipName(Converter.toBlank(form.getEquipName()));
			data.setEquipType(Converter.toBlank(form.getEquipType()));
			data.setUnit(Converter.toBlank(form.getUnit()));
			data.setEquipCode(Converter.toBlank(form.getEquipCode()));
			data.setSupplier(Converter.toBlank(form.getSupplier()));
			data.setSupplierTel(Converter.toBlank(form.getSupplierTel()));
			data.setProductor(Converter.toBlank(form.getProductor()));
			data.setProductorTel(Converter.toBlank(form.getProductorTel()));
			data.setProductDate(Converter.toDate(form.getProductDate()));
			data.setPurchaseDate(Converter.toDate(form.getPurchaseDate()));
			data.setPrice(Converter.toDouble(form.getPrice()));
			data.setPurchaseState(Converter.toBlank(form.getPurchaseState()));
			data.setDesignLifetime(Converter.toLong(form.getDesignLifetime()));
			data.setUsage(Converter.toBlank(form.getUsage()));
			data.setSource(Converter.toBlank(form.getSource()));
			data.setApplication(Converter.toBlank(form.getApplication()));
			data.setComments(Converter.toBlank(form.getComments()));
			data.setCreateUserId(Converter.toBlank(form.getCreateUserId()));
			data.setCreateUserName(Converter.toBlank(form.getCreateUserName()));
			data.setInstrumentUseStartDate(Converter.toDate(form.getInstrumentUseStartDate()));
			data.setInstrumentUseEndDate(Converter.toDate(form.getInstrumentUseEndDate()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(HspEquipBaseinfoForm form) {
		HspEquipBaseinfo data = hspEquipBaseinfoDAO.getById(form.getIdHidden());
		
		this.hspEquipBaseinfoDAO.delete(data);
	}

	@Override
	public void getDept(HspEquipBaseinfoForm hosform, String orgId) {
		if(orgId!=null&&orgId.trim().length()>0){
			List <?> deptList = this.orgMenuDao.findDept(orgId);
			hosform.setDeptList(deptList);
		}
	}

	@Override
	public String elsImport(InputStream inputstream, HttpServletRequest request) {
		StringBuffer msg = new StringBuffer();
		HSSFWorkbook workbook=null;
		try{
			workbook = new HSSFWorkbook(inputstream);
			HSSFSheet sheet = null;//工作表变量
			//遍历该表格中所有的工作表,i表示工作表的数量,getNumberOfSheets表示工作表的总数
			for(int i=0; i<workbook.getNumberOfSheets(); i++){
				sheet = workbook.getSheetAt(i);
				//遍历该行所有的行,j表示行数,getPhysicalNumberOfRows行的总数
				for(int j=3; j<sheet.getPhysicalNumberOfRows(); j++){
					HSSFRow sheetrows = sheet.getRow(j);
					String hspConfigBaseinfoName = "";//组织机构--原始值1
					String deptName="";
					String commConfigDeptId="";
					String hspConfigBaseinfoId = "";//组织机构ID--过渡值
					String equipName = "";//人员编码--原始值2
					String equipType="";
					String equipCode="";
					String supplier="";
					String productor="";
					String useageName="";
					String usageId="";
					
					if(sheetrows!=null){
						hspConfigBaseinfoName = Converter.toBlank(Converter.getValue(sheetrows.getCell((short)0)));
						deptName = Converter.toBlank(Converter.getValue(sheetrows.getCell((short)1)));
						equipName = Converter.toBlank(Converter.getValue(sheetrows.getCell((short)2)));
						equipType = Converter.toBlank(Converter.getValue(sheetrows.getCell((short)3)));
						equipCode=Converter.toBlank(Converter.getValue(sheetrows.getCell((short)4)));
						supplier=Converter.toBlank(Converter.getValue(sheetrows.getCell((short)5)));
						productor=Converter.toBlank(Converter.getValue(sheetrows.getCell((short)6)));
						useageName=Converter.toBlank(Converter.getValue(sheetrows.getCell((short)7)));
						
						
					    if(hspConfigBaseinfoName==null || hspConfigBaseinfoName.trim().length()<=0){
							String str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.hspConfigBaseinfoServiceImpl.warn6", request);
							str = MessageFormat.format(str, (j-2));
							msg.append(str);
						}else if(deptName==null || deptName.trim().length()<=0){
							String str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.hspConfigBaseinfoServiceImpl.warn7", request);
							str = MessageFormat.format(str, (j-2));
							msg.append(str);
						}else if(equipName==null || equipName.trim().length()<=0){
					    	String str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.hspConfigBaseinfoServiceImpl.warn10", request);
							str = MessageFormat.format(str, (j-2));
							msg.append(str);
						}else if(equipType==null||equipType.trim().length()<=0){
							String str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.hspConfigBaseinfoServiceImpl.log5", request);
							str = MessageFormat.format(str, (j-2));
							msg.append(str);
						}else if(this.getCommonDAO().findById("HspEquipBaseinfo", "equipType", Converter.toBlank(equipType).trim())!=null){
							String str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.common.log2", request);
							str = MessageFormat.format(str, (j-2));
							msg.append(str);
						}else if(equipCode==null || equipCode.trim().length()<=0){
							String str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.hspConfigBaseinfoServiceImpl.warn13", request);
							str = MessageFormat.format(str, (j-2));
							msg.append(str);
						}else if(supplier==null || supplier.trim().length()<=0){
							String str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.hspConfigBaseinfoServiceImpl.warn14", request);
							str = MessageFormat.format(str, (j-2));
							msg.append(str);
						}else if(productor==null || productor.trim().length()<=0){
							String str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.common.warn15", request);
							str = MessageFormat.format(str, (j-2));
							msg.append(str);
						}else if(useageName==null || useageName.trim().length()<=0){
							String str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.hspConfigBaseinfoServiceImpl.warn16", request);
							str = MessageFormat.format(str, (j-2));
							msg.append(str);
						}else {
							hspConfigBaseinfoId = Converter.toBlank(this.getCommonDAO().getNameById("HspConfigBaseinfo", "itemName", "id", hspConfigBaseinfoName));							
							commConfigDeptId = Converter.toBlank(this.getCommonDAO().getNameById("CommConfigDept", "itemName", "itemCode", deptName));
							usageId=Converter.toBlank(this.getCommonDAO().getNameById("CommEquipUseage", "itemName", "itemCode", useageName));
							
							//commConfigPositiontitleId = Converter.toBlank(this.getCommonDAO().getNameById("CommConfigPositiontitle", "itemName", "itemCode", commConfigPositiontitleName));
							if(hspConfigBaseinfoId==null || hspConfigBaseinfoId.trim().length()<=0){
								String str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.common.log4", request);
								str = MessageFormat.format(str, (j-2));
								msg.append(str);
							}else if(commConfigDeptId==null || commConfigDeptId.trim().length()<=0){
								String str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.common.log5", request);
								str = MessageFormat.format(str, (j-2));
								msg.append(str);
							}else if(usageId==null || usageId.trim().length()<=0){
								String str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.common.log6", request);
								str = MessageFormat.format(str, (j-2));
								msg.append(str);
							}else if(usageId==null || usageId.trim().length()<=0){
								String str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.common.log6", request);
								str = MessageFormat.format(str, (j-2));
								msg.append(str);
							}else{
							
								HspEquipBaseinfo data = new HspEquipBaseinfo();
								data.setHspCode(hspConfigBaseinfoId);
								data.setDeptCode(commConfigDeptId);
								data.setEquipName(equipName);
								data.setEquipCode(equipCode);
								data.setEquipType(equipType);
								data.setSupplier(supplier);
								data.setProductor(productor);
								data.setUsage(usageId);
								
								//data.setCommConfigPositiontitleId(commConfigPositiontitleId);
								try{
									this.getCommonDAO().save(data);
								}catch(Exception e){
									String str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.common.log1", request);
									str = MessageFormat.format(str, (j-2));
									msg.append(str);
								}
							}
						}
					}
				}
			}
		}catch (IOException e) {
			HspInit.println(e);
			String str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.importFileReadFail", request);
			msg.append(str);
		}  
		return msg.toString();
	}

	@Override
	public HSSFWorkbook getExcel(HspEquipBaseinfoForm form, String fileName,
			HttpServletRequest request) {
		/*取出导出到EXCEL的实际数据*/
		List<?> list = this.hspEquipBaseinfoDAO.findEquipList(form, 0, Integer.MAX_VALUE);

		/*导出到EXCEL*/
		//申明工作簿的第一张工作表的名字(目前不能中文)
		String table_name = "sheet1";
		table_name = Converter.getUnicode(table_name,"gb2312");

		//创建对工作表的引用。		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(table_name);

		int Header_number = 0;  //表头开始行
		int Title_number  = 1;   //标题开始行
		int Overall_Number= 8;  //总列数

		HSSFRow row  =null;
		HSSFCell cell=null;
		String text = ""; //写入单元格的值


		//定义表格样式
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFFont.COLOR_NORMAL); 
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		HSSFCellStyle cellStyle= workbook.createCellStyle();
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
		cellStyle.setBorderBottom((short)1);
		cellStyle.setBorderLeft((short)1);
		cellStyle.setBorderRight((short)1);
		cellStyle.setBorderTop((short)1);
		HSSFCellStyle HeaderStyle= workbook.createCellStyle();
		HeaderStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HeaderStyle.setFont(font);  
		HeaderStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
		HeaderStyle.setBorderBottom((short)1);
		HeaderStyle.setBorderLeft((short)1);
		HeaderStyle.setBorderRight((short)1);
		HeaderStyle.setBorderTop((short)1);

		//插入标题
		row = sheet.createRow((short)Header_number);
		cell = row.createCell((short)0);
		row.setHeight((short)100);
		row.setHeightInPoints((float)30);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(HeaderStyle);
		String str = "卫生机构设备信息";
		text = Converter.getUnicode(str,"gb2312");
		cell.setCellValue(text);  
		sheet.addMergedRegion(new Region(Header_number,(short)(0), Header_number,(short)(Overall_Number-1)));
		//插入列标题
		row = sheet.createRow((short)Title_number);
		row.setHeight((short)585);
		cell = row.createCell((short)0);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(cellStyle);
  	str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.common.createTime", request);
		text = str + ":" +new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		text = Converter.getUnicode(text,"gb2312");
		cell.setCellValue(text);

		for(int i=1; i<Overall_Number; i++){
			cell = row.createCell((short)i);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellStyle(cellStyle);
			text = "";
			text = Converter.getUnicode(text,"gb2312");
			cell.setCellValue(text);
		}
		sheet.addMergedRegion(new Region(Title_number,(short)(0), Title_number,(short)(Overall_Number-1)));

		row = sheet.createRow((short)2);
		row.setHeight((short)585);
		
		str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.common.itemName", request);
		Converter.setCellText(row, cell, 0, str, cellStyle);
		str = "科室";
		Converter.setCellText(row, cell, 1, str, cellStyle);
		str = "设备名称";
		Converter.setCellText(row, cell, 2, str, cellStyle);
	  	str = "设备型号";
		Converter.setCellText(row, cell, 3, str, cellStyle);		
		str="出厂编号";
		Converter.setCellText(row, cell, 4, str, cellStyle);
		str="供货单位";
		Converter.setCellText(row, cell, 5, str, cellStyle);
		str="生产厂家";
		Converter.setCellText(row, cell, 6, str, cellStyle);
		str="使用情况";
		Converter.setCellText(row, cell, 7, str, cellStyle);
		if(list!=null){
			int i = 0;
			for(Iterator<?> it = list.iterator(); it.hasNext(); i++){
				row = sheet.createRow((short)(i+3));
				Object[] obj = (Object[])it.next();
				Converter.setCellText(row, cell, 0, Converter.toBlank(obj[1]), cellStyle);
				Converter.setCellText(row, cell, 1, Converter.toBlank(obj[2]), cellStyle);
				
				Converter.setCellText(row, cell, 2, Converter.toBlank(obj[3]), cellStyle);
				Converter.setCellText(row, cell, 3, Converter.toBlank(obj[4]), cellStyle);
				Converter.setCellText(row, cell, 4, Converter.toBlank(obj[5]), cellStyle);				
				Converter.setCellText(row, cell, 5, Converter.toBlank(obj[6]), cellStyle);
				Converter.setCellText(row, cell, 6, Converter.toBlank(obj[7]), cellStyle);
				Converter.setCellText(row, cell, 7, Converter.toBlank(obj[8]), cellStyle);
				
				
			}
		}
		return workbook;
	}

	@Override
	public void getDetail(HspEquipBaseinfoForm hosform) {
		HspEquipBaseinfo data = hspEquipBaseinfoDAO.getById(hosform.getIdHidden());
		if(data != null){
			setForm(data, hosform);
		}
	}

}
