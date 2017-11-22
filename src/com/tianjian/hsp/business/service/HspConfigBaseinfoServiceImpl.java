package com.tianjian.hsp.business.service;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;
import com.tianjian.comm.bean.CommConfigEconkind;
import com.tianjian.comm.bean.CommConfigFtManage;
import com.tianjian.comm.bean.CommConfigHospitalType;
import com.tianjian.comm.bean.CommConfigLocation;
import com.tianjian.comm.bean.CommConfigLocationTown;
import com.tianjian.comm.bean.CommConfigLocationVillage;
import com.tianjian.comm.bean.CommConfigSettype;
import com.tianjian.comm.bean.CommConfigUnitgrade;
import com.tianjian.comm.bean.CommConfigUnittype;
import com.tianjian.comm.bean.CommDictPublicChar;
import com.tianjian.comm.dao.ICommConfigEconkindDAO;
import com.tianjian.comm.dao.ICommConfigLocationDAO;
import com.tianjian.comm.dao.ICommConfigUnitgradeDAO;
import com.tianjian.comm.dao.ICommConfigUnittypeDAO;
import com.tianjian.comm.dao.ICommonDAO;
import com.tianjian.comm.struts.servlet.CommConfigLocationTownInit;
import com.tianjian.hsp.bean.HspConfigBaseinfo;
import com.tianjian.hsp.business.IHspConfigBaseinfoService;
import com.tianjian.hsp.dao.IHspConfigBaseinfoDAO;
import com.tianjian.hsp.dao.IHspStaffBaseinfoDAO;
import com.tianjian.hsp.struts.form.HspConfigBaseinfoForm;
import com.tianjian.hsp.struts.servlet.HspInit;
import com.tianjian.security.struts.servlet.BaseSecurityInit;
import com.tianjian.util.Converter;
import com.tianjian.util.ResourcesUtil;
import com.tianjian.util.comm.TJInit;
import com.tianjian.util.comm.UtilTrans;

public class HspConfigBaseinfoServiceImpl implements IHspConfigBaseinfoService{
	private static final Logger log = LogManager.getLogger(HspConfigBaseinfoServiceImpl.class);

	private IHspConfigBaseinfoDAO hspConfigBaseinfoDAO;
	private ICommConfigUnittypeDAO commConfigUnittypeDAO;
	private ICommConfigUnitgradeDAO commConfigUnitgradeDAO;
	private ICommConfigEconkindDAO commConfigEconkindDAO;
	private ICommConfigLocationDAO commConfigLocationDAO;
	private IHspStaffBaseinfoDAO hspStaffBaseinfoDAO;
	
	private ICommonDAO commonDAO;
	
	// ------------引入dao接口------------------------------start-------------------------------------
	public IHspConfigBaseinfoDAO getHspConfigBaseinfoDAO(){
		return hspConfigBaseinfoDAO;
	}
	public void setHspConfigBaseinfoDAO(IHspConfigBaseinfoDAO hspConfigBaseinfoDAO){
		this.hspConfigBaseinfoDAO = hspConfigBaseinfoDAO;
	}
	public ICommConfigUnittypeDAO getCommConfigUnittypeDAO(){
		return commConfigUnittypeDAO;
	}
	public void setCommConfigUnittypeDAO(ICommConfigUnittypeDAO commConfigUnittypeDAO){
	}
	public ICommConfigUnitgradeDAO getCommConfigUnitgradeDAO(){
		return commConfigUnitgradeDAO;
	}
	public void setCommConfigUnitgradeDAO(ICommConfigUnitgradeDAO commConfigUnitgradeDAO){
		this.commConfigUnitgradeDAO = commConfigUnitgradeDAO;
	}
	public ICommConfigEconkindDAO getCommConfigEconkindDAO(){
		return commConfigEconkindDAO;
	}
	public void setCommConfigEconkindDAO(ICommConfigEconkindDAO commConfigEconkindDAO){
		this.commConfigEconkindDAO = commConfigEconkindDAO;
	}
	public ICommConfigLocationDAO getCommConfigLocationDAO(){
		return commConfigLocationDAO;
	}
	public void setCommConfigLocationDAO(ICommConfigLocationDAO commConfigLocationDAO){
		this.commConfigLocationDAO = commConfigLocationDAO;
	} 	

	public ICommonDAO getCommonDAO(){
		return commonDAO;
	}
	public void setCommonDAO(ICommonDAO commonDAO){
		this.commonDAO = commonDAO;
	}
	// ------------引入dao接口-----------------------------end-------------------------------------
    
	public void addInits(HspConfigBaseinfoForm form,HttpServletRequest request,String id){
		//如果是是用于树状图
		String useForTree = request.getParameter("useForTree");
		if("1".equals(useForTree)){
			String tree_hspId = request.getParameter("tree_hspId");
			String tree_hspCode = request.getParameter("tree_hspCode");
			if(tree_hspId != null && tree_hspCode != null){
				form.setParentItemCode(tree_hspCode);
				String parentItemName = hspConfigBaseinfoDAO.getItemName(tree_hspId);
				form.setParentItemName(parentItemName);
			}
		}
		
    	form.setStaffId(id);
		addInit(form,request);
	}

	public void addInit(HspConfigBaseinfoForm form, HttpServletRequest request){
		//增加数据之前设置默认值：默认的省、市、县
//		form.setCommConfigLocationId1(BaseSecurityInit.getProperty("INIT_PROVINCE"));
//		form.setCommConfigLocationId2(BaseSecurityInit.getProperty("INIT_CITY"));
//		form.setCommConfigLocationId3(BaseSecurityInit.getProperty("INIT_COUNTY"));
		init(form,request);
	}

	public void detailInit(HspConfigBaseinfoForm form) {
		HspConfigBaseinfo data = hspConfigBaseinfoDAO.getById(form.getId());
		this.setForm(form, data);
//		init(form);
	}
	public HspConfigBaseinfo checkData(String itemCode){
		return hspConfigBaseinfoDAO.getByItemCode(itemCode);
	}
    String hspConfigBaseinfoId="";
    
    
    
	public void save(HspConfigBaseinfoForm form){
		HspConfigBaseinfo data = new HspConfigBaseinfo();
		if(form.getCommConfigLocationId1()==null || "".equals(form.getCommConfigLocationId1())){
			if(BaseSecurityInit.getProperty("INIT_PROVINCE")!=null && BaseSecurityInit.getProperty("INIT_PROVINCE").trim().length()>0){
				form.setCommConfigLocationId1(BaseSecurityInit.getProperty("INIT_PROVINCE"));
			}
		}
		if(form.getCommConfigLocationId2()==null || "".equals(form.getCommConfigLocationId2())){
			if(BaseSecurityInit.getProperty("INIT_CITY") !=null && BaseSecurityInit.getProperty("INIT_CITY").trim().length()>0){
				form.setCommConfigLocationId2(BaseSecurityInit.getProperty("INIT_CITY"));
			}
		}
		if(form.getCommConfigLocationId3()==null || "".equals(form.getCommConfigLocationId3())){
			if(BaseSecurityInit.getProperty("INIT_COUNTY")!=null && BaseSecurityInit.getProperty("INIT_COUNTY").trim().length()>0){
				form.setCommConfigLocationId3(BaseSecurityInit.getProperty("INIT_COUNTY"));
			}
		}
		this.setData(form, data);
		
		hspConfigBaseinfoId=hspConfigBaseinfoDAO.save(data);
	}

	public void updateInit(HspConfigBaseinfoForm form,HttpServletRequest request,String staffId){
		HspConfigBaseinfo data = hspConfigBaseinfoDAO.getById(form.getId());
		this.setForm(form, data);

		form.setStaffId(staffId);
		init(form,request);
		this.getDetail(form);
	}

	public void update(HspConfigBaseinfoForm form){
		HspConfigBaseinfo data = null;
        data = hspConfigBaseinfoDAO.getById(form.getId());
		form.setCreateDate(UtilTrans.transDateToStringFull(data.getCreateDate()));
		if(form.getCommConfigLocationId1()==null || "".equals(form.getCommConfigLocationId1())){
			if(BaseSecurityInit.getProperty("INIT_PROVINCE")!=null && BaseSecurityInit.getProperty("INIT_PROVINCE").trim().length()>0){
				form.setCommConfigLocationId1(BaseSecurityInit.getProperty("INIT_PROVINCE"));
			}
		}
		if(form.getCommConfigLocationId2()==null || "".equals(form.getCommConfigLocationId2())){
			if(BaseSecurityInit.getProperty("INIT_CITY")!=null && BaseSecurityInit.getProperty("INIT_CITY").trim().length()>0){
				form.setCommConfigLocationId2(BaseSecurityInit.getProperty("INIT_CITY"));
			}
		}
		if(form.getCommConfigLocationId3()==null || "".equals(form.getCommConfigLocationId3())){
			if(BaseSecurityInit.getProperty("INIT_COUNTY")!=null && BaseSecurityInit.getProperty("INIT_COUNTY").trim().length()>0){
				form.setCommConfigLocationId3(BaseSecurityInit.getProperty("INIT_COUNTY"));
			}
		}
		this.setData(form, data);
		// 机构分类管理
//		 data.setCommDictPublicCharId1(Converter.toBlank(form.getCommDictPublicCharId1()));
//		 data.setCommConfigFtManageId(Converter.toBlank(form.getCommConfigFtManageId()));

		// 卫生机构类别
		data.setCommConfigHospitalTypeId(Converter.toBlank(form.getCommConfigHospitalTypeId()));
		hspConfigBaseinfoDAO.update(data);
	}

	public void showInit(HspConfigBaseinfoForm form, HttpServletRequest request){
		HspConfigBaseinfo data = hspConfigBaseinfoDAO.getById(form.getId());
		form = new HspConfigBaseinfoForm();
		this.setForm(form, data);
		init(form,request);
	}

	public void delete(HspConfigBaseinfoForm form){
		hspConfigBaseinfoDAO.delete(hspConfigBaseinfoDAO.getById(form.getIdHidden()));
	}
	
	private String transNullToString(Object obj) {
		String temp = "";
		if (obj != null) {
			temp = String.valueOf(obj).trim();
		}
		return temp;
	}
	public int getCountAll(String id, String itemCode, String itemName, String inputCode, String seqNo, String commConfigHospitalTypeId){
		
		return hspConfigBaseinfoDAO.getCountAll(id, itemCode, itemName, inputCode, seqNo, commConfigHospitalTypeId);
	}

	
private List<?> getList(HspConfigBaseinfoForm form,String saffid){
	String order = "";
	if(form.getOrderNo().equals("1")){
		order = " a.itemCode";
	}else if(form.getOrderNo().equals("2")){
		order = " a.itemName";
	}else if(form.getOrderNo().equals("3")){
		order = " a.commConfigUnittypeId";
	}else if(form.getOrderNo().equals("4")){
		order = " a.commConfigUnitgradeId";
	}else if(form.getOrderNo().equals("5")){
		order = " a.seqNo";
	}else{
		order = " a.seqNo";
	}
	if(form.getAsc().equals("1")){
		order += " desc";
	}else{
		order += " asc";
	}
	HspConfigBaseinfo hspConfigBaseinfo = this.getHspConfigBaseinfoDAO().getById(form.getStaffHspId());
	String hspConfig = "";
	if(TJInit.getProperty("classFlag").trim().equals("1")){
		hspConfig = hspConfigBaseinfo.getHspConfigBaseinfoId3();
	}else{
		hspConfig = hspConfigBaseinfo.getHspConfigBaseinfoId2();
	}
	
	
	List<?> list=this.getHspConfigBaseinfoDAO().getDataAll(form.getId(), form.getItemCode(), form.getItemName(), 
			form.getInputCode(), form.getSeqNo(), form.getCommConfigHospitalTypeId(), order,hspConfig, 0, Integer.MAX_VALUE);

	return list;
}
	
	public HSSFWorkbook getExcel(HspConfigBaseinfoForm form, String fileName,String sattfId, HttpServletRequest request){
		try {
			
			List<?> list = getList(form,sattfId);

			/*导出到EXCEL*/
			//申明工作簿的第一张工作表的名字(目前不能中文)
			String table_name = "sheet1";
			table_name = Converter.getUnicode(table_name,"gb2312");

			//创建对工作表的引用。		
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet(table_name);

			int Header_number = 0;  //表头开始行
			int Title_number  = 1;   //标题开始行
			int Overall_Number= 10;  //总列数

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
			String str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.hspConfigBaseinfoServiceImpl.itemInfo", request);
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
			text = str + ":"+new SimpleDateFormat("yyyy-MM-dd").format(new Date());
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
			
			str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.imexport.code", request);
			Converter.setCellText(row, cell, 0, str, cellStyle);
			str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.imexport.name", request);
			Converter.setCellText(row, cell, 1, str, cellStyle);
			str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.imexport.type", request);
			Converter.setCellText(row, cell, 2, str, cellStyle);
			str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.imexport.address", request);
			Converter.setCellText(row, cell, 3, str, cellStyle);
			str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.imexport.zipCode", request);
			Converter.setCellText(row, cell, 4, str, cellStyle);
			str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.imexport.homePage", request);
			Converter.setCellText(row, cell, 5, str, cellStyle);
			str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.imexport.contactor", request);
			Converter.setCellText(row, cell, 6, str, cellStyle);
			str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.imexport.telNo", request);
			Converter.setCellText(row, cell, 7, str, cellStyle);
			str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.imexport.email", request);
			Converter.setCellText(row, cell, 8, str, cellStyle);
			str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.imexport.observations", request);
			Converter.setCellText(row, cell, 9, str, cellStyle);	

			if(list!=null && list.size()>0){
				for(int i=0; i<list.size(); i++){
					row = sheet.createRow((short)(i+3));
					HspConfigBaseinfo bean =(HspConfigBaseinfo) list.get(i);				
					Converter.setCellText(row, cell, 0, Converter.toBlank(bean.getItemCode()), cellStyle);
					Converter.setCellText(row, cell, 1, Converter.toBlank(bean.getItemName()), cellStyle);
					Converter.setCellText(row, cell, 2, Converter.toBlank(this.getCommonDAO().getNameById("CommConfigHospitalType", "itemCode", "itemName", bean.getCommConfigHospitalTypeId())), cellStyle);
					Converter.setCellText(row, cell, 3, Converter.toBlank(bean.getAddress()), cellStyle);
					Converter.setCellText(row, cell, 4, Converter.toBlank(bean.getZipcode()), cellStyle);
					Converter.setCellText(row, cell, 5, Converter.toBlank(bean.getDomainName()), cellStyle);
					Converter.setCellText(row, cell, 6, Converter.toBlank(bean.getContactPersonName()), cellStyle);
					Converter.setCellText(row, cell, 7, Converter.toBlank(bean.getPhone()), cellStyle);
					Converter.setCellText(row, cell, 8, Converter.toBlank(bean.getEMail()), cellStyle);
					Converter.setCellText(row, cell, 9, Converter.toBlank(bean.getComments()), cellStyle);
				}
			}
			return workbook;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public void getPdf(HspConfigBaseinfoForm form, ByteArrayOutputStream ba, String sattfId, HttpServletRequest request){		
		List<?> list = getList(form,sattfId);
		Document document = new Document(PageSize.A4); 
		try { 
			//new FileOutputStream("E:\\itext.pdf ")
			//PdfWriter writer = PdfWriter.getInstance(document, ba); 
			document.open(); 
			
			//设置中文字体  
	        BaseFont bfChinese = BaseFont.createFont("STSongStd-Light",  "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);           
	        Font fontChinese = new Font(bfChinese, 9, Font.NORMAL, Color.GREEN);  
			
			String text = "";
			String str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.hspConfigBaseinfoServiceImpl.itemInfo", request);
			text = Converter.getUnicode(str,"gb2312");
			document.add(new Paragraph(text, fontChinese)); 
			
			str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.common.createTime", request);
			text = str + ":" + new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			text = Converter.getUnicode(text,"gb2312");
			document.add(new Paragraph(text, fontChinese));
	        
	
	        // 设置 Table 表格  
	        Table aTable = new Table(7);  
	        int width[] =  { 50, 50, 50, 25, 25, 25, 25};  
	        aTable.setWidths(width);// 设置每列所占比例  
	        aTable.setWidth(90); // 占页面宽度 90%  
	        aTable.setAlignment(Element.ALIGN_CENTER);// 居中显示  
	        aTable.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示  
	        aTable.setAutoFillEmptyCells(true); // 自动填满  
	        aTable.setBorderWidth(1); // 边框宽度  
	        aTable.setBorderColor(new Color(0, 125, 255)); // 边框颜色  
	        aTable.setPadding(2);// 衬距，看效果就知道什么意思了  
	        aTable.setSpacing(0);// 即单元格之间的间距  
	        aTable.setBorder(2);// 边框  
	        
	        // 设置表头  
	        /** 
	         * cell.setHeader(true);是将该单元格作为表头信息显示； 
	         * cell.setColspan(10);指定了该单元格占10列； 为表格添加表头信息时，要注意的是一旦表头信息添加完了之后， 必须调用 
	         * endHeaders()方法，否则当表格跨页后，表头信息不会再显示 
	         */  
	        Cell haderCell = new Cell(new Phrase("卫生机构信息", fontChinese));  
	        haderCell.setHeader(true);  
	        haderCell.setColspan(7);  
	        aTable.addCell(haderCell);  
	        aTable.endHeaders();        
	//      Cell cell = new Cell( new Phrase("这是一个测试的 3*3 Table 数据", fontChinese));  
	//      cell.setVerticalAlignment(Element.ALIGN_TOP);  
	//      cell.setBorderColor(new Color(255, 0, 0));  
	//      cell.setRowspan(2);  
	//      aTable.addCell(cell);
	        aTable.addCell(new Cell(new Phrase("编号", fontChinese)));
	        aTable.addCell(new Cell(new Phrase("名称", fontChinese)));
	        aTable.addCell(new Cell(new Phrase("类别", fontChinese)));
	        aTable.addCell(new Cell(new Phrase("联系人", fontChinese)));
	        aTable.addCell(new Cell(new Phrase("电话", fontChinese)));
	        aTable.addCell(new Cell(new Phrase("邮箱", fontChinese)));
	        aTable.addCell(new Cell(new Phrase("备注", fontChinese)));
	        for(int i = 0;i<list.size();i++){
	          HspConfigBaseinfo bean =(HspConfigBaseinfo) list.get(i);          
	          aTable.addCell(new Cell(new Phrase(Converter.toBlank(bean.getItemCode()), fontChinese)));  
	          aTable.addCell(new Cell(new Phrase(Converter.toBlank(bean.getItemName()), fontChinese)));  
	          aTable.addCell(new Cell(new Phrase(Converter.toBlank(this.getCommonDAO().getNameById("CommConfigHospitalType", "itemCode", "itemName", bean.getCommConfigHospitalTypeId())), fontChinese)));  
	          aTable.addCell(new Cell(new Phrase(Converter.toBlank(bean.getContactPersonName()), fontChinese)));
	          aTable.addCell(new Cell(new Phrase(Converter.toBlank(bean.getPhone()), fontChinese)));
	          aTable.addCell(new Cell(new Phrase(Converter.toBlank(bean.getEMail()), fontChinese)));
	          aTable.addCell(new Cell(new Phrase(Converter.toBlank(bean.getComments()), fontChinese)));
	        }
	//      Cell cell3 = new Cell(new Phrase("一行三列数据", fontChinese));  
	//      cell3.setColspan(3);  
	//      cell3.setVerticalAlignment(Element.ALIGN_CENTER);  
	//      aTable.addCell(cell3);  
	        document.add(aTable);  
	        document.add(new Paragraph("\n")); 
	        
	        
	        // 第五步：关闭文档。  
	        document.close();  
		} catch (Exception e) { 	
			e.printStackTrace(); 
		}
	
	}
	public String getNewFilePath(HttpServletRequest request) throws Exception {
			String filename = System.currentTimeMillis()+".doc";
			InputStream ins = request.getSession().getServletContext().getResourceAsStream("/include/empty.doc");
//			String file = request.getSession().getServletContext().getResource();
			byte[] b = new byte[ins.available()];
			ins.read(b);
			ins.close();
			String newFileName = request.getSession().getServletContext().getRealPath("") + "/" + filename;
			OutputStream ous = new FileOutputStream(newFileName);
			ous.write(b);
			ous.close();
			return newFileName;
		}

	
	public void getWord(HspConfigBaseinfoForm form, String newFilePath,String sattfId, HttpServletRequest request){		
		try{
			// 设置纸张大小  
	        Document document = new Document(PageSize.A4);  
	        // 建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中  
	        RtfWriter2.getInstance(document, new FileOutputStream(newFilePath));  
	        document.open();  
	        // 设置中文字体  
	        BaseFont bfChinese = BaseFont.createFont("STSongStd-Light",  "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);  
	        Font fontChinese = new Font(bfChinese, 9, Font.NORMAL, Color.GREEN);  
	        // 标题字体风格  
	        Font titleFont = new Font(bfChinese, 12, Font.BOLD);  
	        // 正文字体风格  
	        Font contextFont = new Font(bfChinese, 10, Font.NORMAL);  
	        Paragraph title = new Paragraph("标题");  
	        // 设置标题格式对齐方式  
	        title.setAlignment(Element.ALIGN_CENTER);  
	        title.setFont(titleFont);  
	        document.add(title);  
	        String contextString = "iText是一个能够快速产生PDF文件的java类库。"  
	                + " \n"// 换行  
	                + "iText的java类对于那些要产生包含文本，"  
	                + "表格，图形的只读文档是很有用的。它的类库尤其与java Servlet有很好的给合。"  
	                + "使用iText与PDF能够使你正确的控制Servlet的输出。";  
	        Paragraph context = new Paragraph(contextString);  
	        // 正文格式左对齐  
	        context.setAlignment(Element.ALIGN_LEFT);  
	        context.setFont(contextFont);  
	        // 离上一段落（标题）空的行数  
	        context.setSpacingBefore(5);  
	        // 设置第一行空的列数  
	        context.setFirstLineIndent(20);  
	        document.add(context);  
	        // 利用类FontFactory结合Font和Color可以设置各种各样字体样式  
	        /** 
	         * Font.UNDERLINE 下划线，Font.BOLD 粗体 
	         */  
	        Paragraph underline = new Paragraph("下划线的实现", FontFactory.getFont(  
	                FontFactory.HELVETICA_BOLDOBLIQUE, 18, Font.UNDERLINE,  
	                new Color(0, 0, 255)));  
	        document.add(underline);  
	        // 设置 Table 表格  
	        Table aTable = new Table(3);  
	        int width[] =  { 25, 25, 50 };  
	        aTable.setWidths(width);// 设置每列所占比例  
	        aTable.setWidth(90); // 占页面宽度 90%  
	        aTable.setAlignment(Element.ALIGN_CENTER);// 居中显示  
	        aTable.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示  
	        aTable.setAutoFillEmptyCells(true); // 自动填满  
	        aTable.setBorderWidth(1); // 边框宽度  
	        aTable.setBorderColor(new Color(0, 125, 255)); // 边框颜色  
	        aTable.setPadding(2);// 衬距，看效果就知道什么意思了  
	        aTable.setSpacing(0);// 即单元格之间的间距  
	        aTable.setBorder(2);// 边框  
	        // 设置表头  
	        /** 
	         * cell.setHeader(true);是将该单元格作为表头信息显示； cell.setColspan(3);指定了该单元格占3列； 
	         * 为表格添加表头信息时，要注意的是一旦表头信息添加完了之后， 必须调用 endHeaders()方法，否则当表格跨页后，表头信息不会再显示 
	         */  
	        Cell haderCell = new Cell("表格表头");  
	        haderCell.setHeader(true);  
	        haderCell.setColspan(3);  
	        aTable.addCell(haderCell);  
	        aTable.endHeaders();  
	        
	        
	        Cell cell = new Cell(new Phrase("这是一个测试的 3*3 Table 数据", fontChinese));  
	        cell.setVerticalAlignment(Element.ALIGN_TOP);  
	        cell.setBorderColor(new Color(255, 0, 0));  
	        cell.setRowspan(2);  
	        aTable.addCell(cell);  
	        
	        aTable.addCell(new Cell("#1"));  
	        aTable.addCell(new Cell("#2"));  
	        aTable.addCell(new Cell("#3"));  
	        aTable.addCell(new Cell("#4"));  
	        
	        Cell cell3 = new Cell(new Phrase("一行三列数据", fontChinese));  
	        cell3.setColspan(3);  
	        cell3.setVerticalAlignment(Element.ALIGN_CENTER);  
	        aTable.addCell(cell3); 
	        
	        document.add(aTable);  
	        document.add(new Paragraph("\n"));  
	        // 添加图片  
	        Image img = Image.getInstance("d:\\test.jpg");  
	        img.setAbsolutePosition(0, 0);  
	        img.setAlignment(Image.RIGHT);// 设置图片显示位置  
	        img.scaleAbsolute(12, 35);// 直接设定显示尺寸  
	        img.scalePercent(50);// 表示显示的大小为原尺寸的50%  
	        img.scalePercent(25, 12);// 图像高宽的显示比例  
	        img.setRotation(30);// 图像旋转一定角度  
	        document.add(img);  
	        document.close(); 
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public String elsImport(InputStream inputstream, HttpServletRequest request){
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
					String itemCode = "";//机构代码--原始值
					String itemName = "";//机构名称--原始值
					String commConfigUnittypeId = "";//机构等ID--过渡值
					String commConfigUnittypeName = "";//机构等--原始值
					String commConfigUnitgradeId = "";//机构ID--过渡值
					String commConfigUnitgradeName = "";//机构级--原始值
					String seqNo = "";//序号--原始值
					String parentItemCode="";
					String parentItemName="";
					if(sheetrows!=null){
						itemCode = Converter.toBlank(Converter.getValue(sheetrows.getCell((short)0)));
						parentItemCode = Converter.toBlank(Converter.getValue(sheetrows.getCell((short)1)));
						parentItemName = Converter.toBlank(Converter.getValue(sheetrows.getCell((short)2)));
						itemName = Converter.toBlank(Converter.getValue(sheetrows.getCell((short)3)));
						commConfigUnittypeName = Converter.toBlank(Converter.getValue(sheetrows.getCell((short)4)));
						commConfigUnitgradeName = Converter.toBlank(Converter.getValue(sheetrows.getCell((short)5)));
						seqNo = Converter.toBlank(Converter.getValue(sheetrows.getCell((short)6)));

						if(itemCode==null || itemCode.trim().length()<=0){
							String str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.hspConfigBaseinfoServiceImpl.warn6", request);
							str = MessageFormat.format(str, (j-2));
							msg.append(str);
						}else if(parentItemCode==null||parentItemCode.trim().length()<=0){
							String str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.hspConfigBaseinfoServiceImpl.warn7", request);
							str = MessageFormat.format(str, (j-2));
							msg.append(str);
						}else if(parentItemName==null||parentItemName.trim().length()<=0){
							String str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.hspConfigBaseinfoServiceImpl.warn10", request);
							str = MessageFormat.format(str, (j-2));
							msg.append(str);
						}
						else if(itemName==null || itemName.trim().length()<=0){
							String str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.hspConfigBaseinfoServiceImpl.log5", request);
							str = MessageFormat.format(str, (j-2));
							msg.append(str);
						}else if(seqNo!=null && Converter.toLong(seqNo)==null){
							String str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.hspConfigBaseinfoServiceImpl.warn12", request);
							str = MessageFormat.format(str, (j-2));
							msg.append(str);
						}else if(this.getHspConfigBaseinfoDAO().getByItemCode(itemCode)!=null){
							String str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.common.log2", request);
							str = MessageFormat.format(str, (j-2));
							msg.append(str);
						}else{
							commConfigUnitgradeId = Converter.toBlank(this.getCommonDAO().getNameById("CommConfigUnitgrade", "itemName", "itemCode", commConfigUnitgradeName));
							commConfigUnittypeId = Converter.toBlank(this.getCommonDAO().getNameById("CommConfigUnittype", "itemName", "itemCode", commConfigUnittypeName));
//							if(commConfigUnitgradeId==null || commConfigUnitgradeId.trim().length()<=0){
//								String str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.common.log3", request);
//								str = MessageFormat.format(str, (j-2));
//								msg.append(str);
//							}else if(commConfigUnittypeId==null || commConfigUnittypeId.trim().length()<=0){
//								String str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.hspConfigBaseinfoServiceImpl.warn11", request);
//								str = MessageFormat.format(str, (j-2));
//								msg.append(str);
//							}else{
//								HspConfigBaseinfo data = new HspConfigBaseinfo();
//								data.setItemCode(itemCode);
//								data.setItemName(itemName);
//								HspConfigBaseinfo info=this.hspConfigBaseinfoDAO.getHsp(parentItemCode);
//								if(info==null||Converter.toBlank(info.getParentItemCode()).equals("")){
//									String str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.hspConfigBaseinfoServiceImpl.warn9", request);
//									str = MessageFormat.format(str, (j-2));
//									msg.append(str);
//								}else{
//									saveHsp(data,parentItemName,itemName,parentItemCode );
//								}
								//data.setParentItemName(parentItemName);
								//data.setCommConfigUnitgradeName(commConfigUnitgradeName);
								//data.setCommConfigUnittypeName(commConfigUnittypeName);
								//data.setCommConfigUnitgradeId(commConfigUnitgradeId);
								//data.setCommConfigUnittypeId(commConfigUnittypeId);
//								data.setSeqNo(Long.valueOf(seqNo));
//								try{
//									this.getCommonDAO().save(data);
//								}catch(Exception e){
//									String str = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.hspConfigBaseinfoServiceImpl.warn9", request);
//									str = MessageFormat.format(str, (j-2));
//									msg.append(str);
//								}
//							}
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
//	private void saveHsp(HspConfigBaseinfo data,String value,String itemName,String itemCode){
//		int number=1;
//		HspConfigBaseinfo  info1 = null,info2=null,info3=null,info4=null;
//		if(value.equals(itemName)){
//			data.setHspConfigBaseinfoId1(Converter.toBlank(itemCode));
//		}else{
//			info1=this.hspConfigBaseinfoDAO.getHsp(itemCode);number++;
//			if(info1!=null&&info1.getParentItemCode()!=null&&info1.getParentItemCode().trim().length()>0){
//				number++;
//				info2=this.hspConfigBaseinfoDAO.getHsp(info1.getParentItemCode());
//				if(info2!=null&&info2.getParentItemCode()!=null&&info2.getParentItemCode().trim().length()>0){
//					number++;
//					info3=this.hspConfigBaseinfoDAO.getHsp(info2.getParentItemCode());
//					if(info3!=null&&info3.getParentItemCode()!=null&&info3.getParentItemCode().trim().length()>0){
//						number++;
//						info4=this.hspConfigBaseinfoDAO.getHsp(info3.getParentItemCode());
//						if(info4!=null&&info4.getParentItemCode()!=null&&info4.getParentItemCode().trim().length()>0){
//							number++;
//						}
//					}
//				}
//			}
//			switch (number) {
//			case 2:
//				data.setHspConfigBaseinfoId1(Converter.toBlank(info1.getId()));
//				data.setHspConfigBaseinfoId2(Converter.toBlank(hspConfigBaseinfoId));
//				break;
//			case 3:
//				data.setHspConfigBaseinfoId1(Converter.toBlank(info2.getId()));
//				data.setHspConfigBaseinfoId2(Converter.toBlank(info1.getId()));
//				data.setHspConfigBaseinfoId3(Converter.toBlank(hspConfigBaseinfoId));
//				break;
//			case 4:
//				data.setHspConfigBaseinfoId1(Converter.toBlank(info3.getId()));
//				data.setHspConfigBaseinfoId2(Converter.toBlank(info2.getId()));
//				data.setHspConfigBaseinfoId3(Converter.toBlank(info1.getId()));
//				data.setHspConfigBaseinfoId4(Converter.toBlank(hspConfigBaseinfoId));
//				break;
//			case 5:
//				data.setHspConfigBaseinfoId1(Converter.toBlank(info4.getId()));
//				data.setHspConfigBaseinfoId2(Converter.toBlank(info3.getId()));
//				data.setHspConfigBaseinfoId3(Converter.toBlank(info2.getId()));
//				data.setHspConfigBaseinfoId4(Converter.toBlank(info1.getId()));
//				data.setHspConfigBaseinfoId5(Converter.toBlank(hspConfigBaseinfoId));
//				break;
//			default:
//				break;
//			}
//		}
//
//	}
	public void getSearchAll(HspConfigBaseinfoForm form, HttpServletRequest request, int curCount, int quChuCount){
		String order = "";
		if(form.getOrderNo().equals("1")){
			order = " a.itemCode";
		}else if(form.getOrderNo().equals("2")){
			order = " a.itemName";
		}else if(form.getOrderNo().equals("3")){
			order = " a.zipcode";
		}else if(form.getOrderNo().equals("4")){
			order = " a.contactPersonName";
		}else if(form.getOrderNo().equals("5")){
			order = " a.seqNo";
		}else{
			order = " a.seqNo";
		}
		if(form.getAsc().equals("1")){
			order += " desc";
		}else{
			order += " asc";
		}
		
		HspConfigBaseinfo hspConfigBaseinfo = this.getHspConfigBaseinfoDAO().getById(form.getStaffHspId());
		String hspConfig = "";
		if(TJInit.getProperty("classFlag").trim().equals("1")){
			hspConfig = hspConfigBaseinfo.getHspConfigBaseinfoId3();
    	}else{
    		hspConfig = hspConfigBaseinfo.getHspConfigBaseinfoId2();
    	}
		
		
		List<?> list = hspConfigBaseinfoDAO.getDataAll(form.getId(), 
				form.getItemCode(), 
				form.getItemName(),
				form.getInputCode(), 
				form.getSeqNo(), 
				form.getCommConfigHospitalTypeId(), 
				order,hspConfig ,curCount, quChuCount);
//		// 取字典
//		form.setC010501aList(this.hspConfigBaseinfoDAO.findCommConfigUnittypeList());
//		// 医院级
//		form.setC010501bList(this.hspConfigBaseinfoDAO.findCommConfigUnitgradeList());
		List<?> CommConfigHospitalType_data = hspConfigBaseinfoDAO.findConfigHospitalTypeList();
		if(CommConfigHospitalType_data!=null && CommConfigHospitalType_data.size()>0){
			String[] CommConfigHospitalTypeId_names = new String[CommConfigHospitalType_data.size()];
			String[] CommConfigHospitalTypeIds = new String[CommConfigHospitalType_data.size()];
			for(int j = 0; j < CommConfigHospitalType_data.size(); j++){
				CommConfigHospitalTypeIds[j] =((CommConfigHospitalType) CommConfigHospitalType_data.get(j)).getItemCode();
				CommConfigHospitalTypeId_names[j] =((CommConfigHospitalType) CommConfigHospitalType_data.get(j)).getItemName();
			}
			form.setCommConfigHospitalTypeIds(CommConfigHospitalTypeIds);
			form.setCommConfigHospitalTypeId_names(CommConfigHospitalTypeId_names);
		}

		if(list != null && list.size() > 0){
			String[] ids = new String[list.size()];
			String[] itemCodes = new String[list.size()];
			String[] itemNames = new String[list.size()];
			String[] zipcode = new String[list.size()];
			String[] contactPersonName = new String[list.size()];
			//String[] commConfigUnittypeIds = new String[list.size()];
			//String[] commConfigUnittypeId_names = new String[list.size()];
			//String[] commConfigUnitgradeIds = new String[list.size()];
			//String[] commConfigUnitgradeId_names = new String[list.size()];
			String[] seqNos = new String[list.size()];
			for(int i = 0; i < list.size(); i++){
				HspConfigBaseinfo bean =(HspConfigBaseinfo) list.get(i);
				ids[i] = Converter.toBlank(bean.getId());
				itemCodes[i] = Converter.toBlank(bean.getItemCode());
				itemNames[i] = Converter.toBlank(bean.getItemName());
				zipcode[i] = Converter.toBlank(bean.getZipcode());
				contactPersonName[i] = Converter.toBlank(bean.getContactPersonName());
				//commConfigUnittypeIds[i] = Converter.toBlank(bean.getCommConfigUnittypeId());
				//commConfigUnitgradeIds[i] = Converter.toBlank(bean.getCommConfigUnitgradeId());

				//以下两个IF-ELSE的意义是：关联ID有一个NAME的冗余字段，查询时如果该NAME冗余字段值为空，则依据ID去获取，否则直接使用冗余字段值
//				if(bean.getCommConfigUnittypeName()==null || bean.getCommConfigUnittypeName().trim().length()<=0){
//					commConfigUnittypeId_names[i] = Converter.toBlank(this.getCommConfigUnittypeDAO().getItemName(commConfigUnittypeIds[i]));
//				}else{
//					commConfigUnittypeId_names[i] = Converter.toBlank(bean.getCommConfigUnittypeName());
//				}
//				if(bean.getCommConfigUnitgradeName()==null || bean.getCommConfigUnitgradeName().trim().length()<=0){
//					commConfigUnitgradeId_names[i] = Converter.toBlank(this.getCommConfigUnitgradeDAO().getItemName(commConfigUnitgradeIds[i]));
//				}else{
//					commConfigUnitgradeId_names[i] = Converter.toBlank(bean.getCommConfigUnitgradeName());
//				}
				seqNos[i] = Converter.toBlank(bean.getSeqNo());
			}
			form.setIdList(ids);
			form.setItemCodeList(itemCodes);
			form.setItemNameList(itemNames);
			//form.setCommConfigUnittypeIdList(commConfigUnittypeIds);
			//form.setCommConfigUnittypeNameList(commConfigUnittypeId_names);
			//form.setCommConfigUnitgradeIdList(commConfigUnitgradeIds);
			//form.setCommConfigUnitgradeNameList(commConfigUnitgradeId_names);
			form.setSeqNoList(seqNos);
		}
		form.setCommConfigLocationId1(CommConfigLocationTownInit.getProperty("INIT_PROVINCE_IC"));
		form.setCommConfigLocationId2(CommConfigLocationTownInit.getProperty("INIT_CITY_IC"));
		form.setCommConfigLocationId3(CommConfigLocationTownInit.getProperty("INIT_COUNTY_IC"));
		init(form,request);
	}

	private void setData(HspConfigBaseinfoForm form, HspConfigBaseinfo data){
		try{
			//data.setId(Converter.toBlank(form.getId()));
			//此处设置id值作为最后一级行政管理机构id
			//data.setId(UUID.randomUUID().toString().replace("-", ""));
			data.setItemCode(Converter.toBlank(form.getItemCode()));
			data.setItemName(Converter.toBlank(form.getItemName()));
			data.setParentItemCode(Converter.toBlank(form.getParentItemCode()));
			data.setParentItemName(Converter.toBlank(form.getParentItemCode_name()));
			data.setCommConfigUnittypeId(Converter.toBlank(form.getCommConfigUnittypeId()));
			data.setCommConfigUnitgradeId(Converter.toBlank(form.getCommConfigUnitgradeId()));
			data.setCommConfigEconkindId(Converter.toBlank(form.getCommConfigEconkindId()));
			data.setCommConfigLocationId1(Converter.toBlank(form.getCommConfigLocationId1()));
			data.setCommConfigLocationId2(Converter.toBlank(form.getCommConfigLocationId2()));
			data.setCommConfigLocationId3(Converter.toBlank(form.getCommConfigLocationId3()));
			data.setCommConfigLocationTownId(Converter.toBlank(form.getCommConfigLocationTownId()));
			data.setCommClvId(Converter.toBlank(form.getCommClvId()));
			data.setAddress(Converter.toBlank(form.getAddress()));
			data.setZipcode(Converter.toBlank(form.getZipcode()));
			data.setContactPersonName(Converter.toBlank(form.getContactPersonName()));
			data.setPhone(Converter.toBlank(form.getPhone()));
			data.setDateClinicNum(!((Converter.toBlank(form.getDateClinicNum())).trim().length()>0) ? null : Long.valueOf(form.getDateClinicNum()));
			data.setYearOuthospitalNum(!((Converter.toBlank(form.getYearOuthospitalNum())).trim().length()>0) ? null : Long.valueOf(form.getYearOuthospitalNum()));
			data.setAuthorizedBedNum(!((Converter.toBlank(form.getAuthorizedBedNum())).trim().length()>0) ? null : Long.valueOf(form.getAuthorizedBedNum()));
			data.setOutspreadBedNum(!((Converter.toBlank(form.getOutspreadBedNum())).trim().length()>0) ? null : Long.valueOf(form.getOutspreadBedNum()));
			data.setDoctorNum(!((Converter.toBlank(form.getDoctorNum())).trim().length()>0) ? null : Long.valueOf(form.getDoctorNum()));
			data.setNurseNum(!((Converter.toBlank(form.getNurseNum())).trim().length()>0) ? null : Long.valueOf(form.getNurseNum()));
			data.setTechnicPersonNum(!((Converter.toBlank(form.getTechnicPersonNum())).trim().length()>0) ? null : Long.valueOf(form.getTechnicPersonNum()));
			data.setSickroomNum(!((Converter.toBlank(form.getSickroomNum())).trim().length()>0) ? null : Long.valueOf(form.getSickroomNum()));
			data.setOperationroomNum(!((Converter.toBlank(form.getOperationroomNum())).trim().length()>0) ? null : Long.valueOf(form.getOperationroomNum()));
			data.setLevelDesc(Converter.toBlank(form.getLevelDesc()));
			data.setCreateUserId(Converter.toBlank(form.getCreateUserId()));
			data.setCreateUserName(Converter.toBlank(form.getCreateUserName()));
			data.setCreateDate(UtilTrans.transStringToDate(form.getCreateDate(),"yyyy-MM-dd HH:mm:ss"));
			data.setCommConfigFtManageId(Converter.toBlank(form.getCommConfigFtManageId()));
			data.setCommConfigHospitalTypeId(Converter.toBlank(form.getCommConfigHospitalTypeId()));
			try{
				String parentLevelDesc = this.hspConfigBaseinfoDAO.getP2ValueByP1("HspConfigBaseinfo", "id", form.getParentItemCode(), "levelDesc");
				if(parentLevelDesc!=null && parentLevelDesc.trim().length()>0){
					data.setLevelDesc(parentLevelDesc+Converter.toBlank(form.getItemCode()) + "\\");
				}else{
					data.setLevelDesc("\\"+Converter.toBlank(form.getItemCode())+"\\");
				}
			} catch(Exception e){
				HspInit.println(e);
				data.setLevelDesc("\\" + Converter.toBlank(form.getItemCode())+"\\");
			}
			data.setComments(Converter.toBlank(form.getComments()));	
			if(form.getSeqNo()!=null&&form.getSeqNo().trim()!=""){
				try {
					data.setSeqNo(Long.valueOf(form.getSeqNo()));
				} catch (Exception e) {
					data.setSeqNo(0L);
				}
			}else{
				data.setSeqNo(this.getHspConfigBaseinfoDAO().seqNoMaker("HspConfigBaseinfo"));
			}
			
			data.setInputCode(Converter.toBlank(form.getInputCode()));
			data.setEMail(Converter.toBlank(form.getEMail()));
			data.setDomainName(Converter.toBlank(form.getDomainName()));
			String hspType = TJInit.getProperty("classFlag");
			if(hspType != null && hspType.length() > 0){
				data.setHspType(Long.valueOf(hspType));
			}else{
				data.setHspType(Long.valueOf("1"));
			}
			data.setCommConfigSettypeId(Converter.toBlank(form.getCommConfigSetTypes()));
			data.setCommConfigGovaffrsId(Converter.toBlank(form.getCommConfigCovaffrss()));
			//如果上级机构代码为空，将本机构的id作为第一级行政管理机构id
			if(form.getParentItemCode() == null || form.getParentItemCode().trim().length() == 0){
				String classFlag = TJInit.getProperty("classFlag");
				if(classFlag!=null && classFlag.trim().equals("2")){
					data.setHspConfigBaseinfoId2(Converter.toBlank(data.getId()));
				}else{
					data.setHspConfigBaseinfoId3(Converter.toBlank(data.getId()));
				}
			}else{
				HspConfigBaseinfo parentHsp = this.hspConfigBaseinfoDAO.getByItemCode(form.getParentItemCode());
				if(parentHsp == null){
					throw new Exception("上级机构代码错误！");
				}
				String parent_hspId1 = parentHsp.getHspConfigBaseinfoId1();
				String parent_hspId2 = parentHsp.getHspConfigBaseinfoId2();
				String parent_hspId3 = parentHsp.getHspConfigBaseinfoId3();
				String parent_hspId4 = parentHsp.getHspConfigBaseinfoId4();
				String parent_hspId5 = parentHsp.getHspConfigBaseinfoId5();
				//从上级机构的最后一级行政管理机构开始判断，直到有数据为止
				if(parent_hspId5 != null && parent_hspId5.trim().length() > 0){
					data.setHspConfigBaseinfoId1(parent_hspId1);
					data.setHspConfigBaseinfoId2(parent_hspId2);
					data.setHspConfigBaseinfoId3(parent_hspId3);
					data.setHspConfigBaseinfoId4(parent_hspId4);
					data.setHspConfigBaseinfoId5(parent_hspId5);
				}else if(parent_hspId4 != null && parent_hspId4.trim().length() > 0){
					data.setHspConfigBaseinfoId1(parent_hspId1);
					data.setHspConfigBaseinfoId2(parent_hspId2);
					data.setHspConfigBaseinfoId3(parent_hspId3);
					data.setHspConfigBaseinfoId4(parent_hspId4);
					if(parent_hspId4.equals(parentHsp.getId())){
						data.setHspConfigBaseinfoId5(data.getId());
					}else{//如果上级机构的id不等于最后一级的行政管理机构id，则将上一级机构的id作为下一级行政管理机构id，将本机构的id做为最后一级行政管理机构id
						data.setHspConfigBaseinfoId5(parentHsp.getId());
					}
				}else if(parent_hspId3 != null && parent_hspId3.trim().length() > 0){
					data.setHspConfigBaseinfoId1(parent_hspId1);
					data.setHspConfigBaseinfoId2(parent_hspId2);
					data.setHspConfigBaseinfoId3(parent_hspId3);
					if(parent_hspId3.equals(parentHsp.getId())){
						data.setHspConfigBaseinfoId4(data.getId());
					}else{
						data.setHspConfigBaseinfoId4(parentHsp.getId());
						data.setHspConfigBaseinfoId5(data.getId());
					}
				}else if(parent_hspId2 != null && parent_hspId2.trim().length() > 0){
					data.setHspConfigBaseinfoId1(parent_hspId1);
					data.setHspConfigBaseinfoId2(parent_hspId2);
					if(parent_hspId2.equals(parentHsp.getId())){
						data.setHspConfigBaseinfoId3(data.getId());
					}else{
						data.setHspConfigBaseinfoId3(parentHsp.getId());
						data.setHspConfigBaseinfoId4(data.getId());
					}
				}else if(parent_hspId1 != null && parent_hspId1.trim().length() > 0){
					data.setHspConfigBaseinfoId1(parent_hspId1);
					if(parent_hspId1.equals(parentHsp.getId())){
						data.setHspConfigBaseinfoId2(data.getId());
					}else{
						data.setHspConfigBaseinfoId2(parentHsp.getId());
						data.setHspConfigBaseinfoId3(data.getId());
					}
				}
			}
		} catch(Exception e){
			log.error("setData error!", e);
			HspInit.println(e);
		}
	}

	
	public String getHspInputCodeById(String hspConfigBaseinfoId){
		return Converter.toBlank(commonDAO.getNameById("HspConfigBaseinfo", "id", "inputCode", hspConfigBaseinfoId));
	}

	@SuppressWarnings("deprecation")
	private void setForm(HspConfigBaseinfoForm form, HspConfigBaseinfo data){
		try{
			form.setId(Converter.toBlank(data.getId()));
			form.setItemCode(Converter.toBlank(data.getItemCode()));
			form.setItemName(Converter.toBlank(data.getItemName()));
			form.setParentItemCode(Converter.toBlank(data.getParentItemCode()));
			form.setCommConfigUnittypeId(Converter.toBlank(data.getCommConfigUnittypeId()));
			form.setCommConfigUnitgradeId(Converter.toBlank(data.getCommConfigUnitgradeId()));
			form.setCommConfigEconkindId(Converter.toBlank(data.getCommConfigEconkindId()));
			form.setCommConfigLocationId1(Converter.toBlank(data.getCommConfigLocationId1()));
			form.setCommConfigLocationId2(Converter.toBlank(data.getCommConfigLocationId2()));
			form.setCommConfigLocationId3(Converter.toBlank(data.getCommConfigLocationId3()));
			form.setCommConfigLocationTownId(Converter.toBlank(data.getCommConfigLocationTownId()));
			form.setCommClvId(Converter.toBlank(data.getCommClvId()));
			form.setAddress(Converter.toBlank(data.getAddress()));
			form.setZipcode(Converter.toBlank(data.getZipcode()));
			form.setContactPersonName(Converter.toBlank(data.getContactPersonName()));
			form.setPhone(Converter.toBlank(data.getPhone()));
			form.setDateClinicNum(Converter.toBlank(data.getDateClinicNum()));
			form.setYearOuthospitalNum(Converter.toBlank(data.getYearOuthospitalNum()));
			form.setAuthorizedBedNum(Converter.toBlank(data.getAuthorizedBedNum()));
			form.setOutspreadBedNum(Converter.toBlank(data.getOutspreadBedNum()));
			form.setDoctorNum(Converter.toBlank(data.getDoctorNum()));
			form.setNurseNum(Converter.toBlank(data.getNurseNum()));
			form.setTechnicPersonNum(Converter.toBlank(data.getTechnicPersonNum()));
			form.setSickroomNum(Converter.toBlank(data.getSickroomNum()));
			form.setOperationroomNum(Converter.toBlank(data.getOperationroomNum()));
			data.setOperationroomNum(data.getOperationroomNum()==null ? null : Long.valueOf(data.getOperationroomNum()));
			form.setComments(Converter.toBlank(data.getComments()));
			form.setSeqNo(data.getSeqNo()==null ? "0" : String.valueOf(data.getSeqNo()));
			form.setInputCode(Converter.toBlank(data.getInputCode()));
			form.setLevelDesc(Converter.toBlank(data.getLevelDesc()));
			form.setCreateUserId(Converter.toBlank(data.getCreateUserId()));
			form.setCreateDate(UtilTrans.transDateToStringFull(data.getCreateDate()));
			try{
				if(data.getCreateDate()!=null){
					form.setCreateDate(Converter.toBlank(data.getCreateDate()));
					form.setCreateDateYear(Converter.toBlank(data.getCreateDate().getYear() + 1900));
					form.setCreateDateMonth(Converter.toBlank(data.getCreateDate().getMonth() + 1));
					form.setCreateDateDay(Converter.toBlank(data.getCreateDate().getDay() + 12));
				}
			}catch(RuntimeException e){
				HspInit.println(e);
			}

			form.setCommConfigFtManageId(Converter.toBlank(data.getCommConfigFtManageId()));
			form.setCommConfigHospitalTypeId(Converter.toBlank(data.getCommConfigHospitalTypeId()));
			form.setEMail(Converter.toBlank(data.getEMail()));
			form.setDomainName(Converter.toBlank(data.getDomainName()));

			//以下是表结构的冗余字段，如果该字段值为空，则根据关联获取其值，否则直接使用该值
			if(data.getCchtName()==null || data.getCchtName().trim().length()<=0){
				form.setCchtName(Converter.toBlank(this.getCommonDAO().getNameById("CommConfigHospitalType", "itemCode", "itemName", data.getCommConfigHospitalTypeId())));
			}else{
				form.setCchtName(Converter.toBlank(data.getCchtName()));
			}
			if(data.getCommConfigFtManageName()==null || data.getCommConfigFtManageName().trim().length()<=0){
				form.setCommConfigFtManageName(Converter.toBlank(this.getCommonDAO().getNameById("CommConfigFtManage", "itemCode", "itemName", data.getCommConfigFtManageId())));
			}else{
				form.setCommConfigFtManageName(Converter.toBlank(data.getCommConfigFtManageName()));
			}
			if(data.getCommClvName()==null || data.getCommClvName().trim().length()<=0){
				form.setCommClvName(Converter.toBlank(this.getCommonDAO().getNameById("CommConfigLocationVillage", "id", "itemName", data.getCommClvId())));
			}else{
				form.setCommClvName(Converter.toBlank(data.getCommClvName()));
			}
			if(data.getCommConfigEconkindName()==null || data.getCommConfigEconkindName().trim().length()<=0){
				form.setCommConfigEconkindName(Converter.toBlank(this.getCommonDAO().getNameById("CommConfigEconkind", "id", "itemName", data.getCommConfigEconkindId())));
			}else{
				form.setCommConfigEconkindName(Converter.toBlank(data.getCommConfigEconkindName()));
			}
			if(data.getCommConfigLocationName1()==null || data.getCommConfigLocationName1().trim().length()<=0){
				form.setCommConfigLocationName1(Converter.toBlank(this.getCommonDAO().getNameById("CommConfigLocation", "id", "itemName", data.getCommConfigLocationId1())));
			}else{
				form.setCommConfigLocationName1(Converter.toBlank(data.getCommConfigLocationName1()));
			}
			if(data.getCommConfigLocationName2()==null || data.getCommConfigLocationName2().trim().length()<=0){
				form.setCommConfigLocationName2(Converter.toBlank(this.getCommonDAO().getNameById("CommConfigLocation", "id", "itemName", data.getCommConfigLocationId2())));
			}else{
				form.setCommConfigLocationName2(Converter.toBlank(data.getCommConfigLocationName2()));
			}
			if(data.getCommConfigLocationName3()==null || data.getCommConfigLocationName3().trim().length()<=0){
				form.setCommConfigLocationName3(Converter.toBlank(this.getCommonDAO().getNameById("CommConfigLocation", "id", "itemName", data.getCommConfigLocationId3())));
			}else{
				form.setCommConfigLocationName3(Converter.toBlank(data.getCommConfigLocationName3()));
			}
			if(data.getCcltName()==null || data.getCcltName().trim().length()<=0){
				form.setCcltName(Converter.toBlank(this.getCommonDAO().getNameById("CommConfigLocationTown", "id", "itemName", data.getCommConfigLocationTownId())));
			}else{
				form.setCcltName(Converter.toBlank(data.getCcltName()));
			}
			if(data.getCommClvName()==null || data.getCommClvName().trim().length()<=0){
				form.setCommClvName(Converter.toBlank(this.getCommonDAO().getNameById("CommConfigLocationVillage", "id", "itemName", data.getCommClvId())));
			}else{
				form.setCommClvName(Converter.toBlank(data.getCommClvName()));
			}
			if(data.getCommConfigUnitgradeName()==null || data.getCommConfigUnitgradeName().trim().length()<=0){
				form.setCommConfigUnitgradeName(Converter.toBlank(this.getCommonDAO().getNameById("CommConfigUnitgrade", "id", "itemName", data.getCommConfigUnitgradeId())));
			}else{
				form.setCommConfigUnitgradeName(Converter.toBlank(data.getCommConfigUnitgradeName()));
			}
			if(data.getCommConfigUnittypeName()==null || data.getCommConfigUnittypeName().trim().length()<=0){
				form.setCommConfigUnittypeName(Converter.toBlank(this.getCommonDAO().getNameById("CommConfigUnittype", "itemCode", "itemName", data.getCommConfigUnittypeId())));
			}else{
				form.setCommConfigUnittypeName(Converter.toBlank(data.getCommConfigUnittypeName()));
			}
			if(data.getCreateUserName()==null || data.getCreateUserName().trim().length()<=0){
				form.setCreateUserName(Converter.toBlank(this.getCommonDAO().getNameById("SecurityStaffBaseinfo", "id", "name", data.getCreateUserId())));
			}else{
				form.setCreateUserName(Converter.toBlank(data.getCreateUserName()));
			}
			if(data.getParentItemName()==null || data.getParentItemName().trim().length()<=0){
				form.setParentItemName(Converter.toBlank(this.getCommonDAO().getNameById("HspConfigBaseinfo", "itemCode", "itemName", data.getParentItemCode())));
			}else{
				form.setParentItemName(Converter.toBlank(data.getParentItemName()));
			}
			form.setCommConfigCovaffrss(Converter.toBlank(data.getCommConfigGovaffrsId()));
			form.setCommConfigSetTypes(Converter.toBlank(data.getCommConfigSettypeId()));
			HspConfigBaseinfo info=hspConfigBaseinfoDAO.getHsp(data.getHspConfigBaseinfoId1());
			form.setHspConfigBaseInfoId1Name(Converter.toBlank(info==null?"":info.getItemName()));
			HspConfigBaseinfo info1=hspConfigBaseinfoDAO.getHsp(data.getHspConfigBaseinfoId2());
			form.setHspConfigBaseInfoId2Name(Converter.toBlank(info==null?"":info1.getItemName()));
			HspConfigBaseinfo info2=hspConfigBaseinfoDAO.getHsp(data.getHspConfigBaseinfoId3());
			form.setHspConfigBaseInfoId3Name(Converter.toBlank(info2==null?"":info2.getItemName()));
			HspConfigBaseinfo info3=hspConfigBaseinfoDAO.getHsp(data.getHspConfigBaseinfoId4());
			form.setHspConfigBaseInfoId4Name(Converter.toBlank(info==null?"":info3.getItemName()));
			HspConfigBaseinfo info4=hspConfigBaseinfoDAO.getHsp(data.getHspConfigBaseinfoId5());
			form.setHspConfigBaseInfoId5Name(Converter.toBlank(info4==null?"":info4.getItemName()));
		} catch(Exception e){
			HspInit.println(e);
		}
	}

	/**
	 * 获得省列表
	 */
	public void getProvinces(HspConfigBaseinfoForm form){
		List<?> data = hspConfigBaseinfoDAO.getByParent("", "1");
		String[] tempId = null;
		String[] tempName = null;
		if(data!=null){
			tempId = new String[data.size()+1];
			tempName = new String[data.size()+1];
			tempId[0] = "";
			tempName[0] = "";
			for(int i=0; i<data.size(); i++){
				CommConfigLocation district =(CommConfigLocation) data.get(i);
				tempId[i+1] = district.getId();
				tempName[i+1] = district.getItemName();
			}
		}
		form.setCommConfigLocationId1s(tempId);
		form.setCommConfigLocationId1_names(tempName);
	}

	/**
	 * 获得省对应的市列表
	 */
	public void getCitys(HspConfigBaseinfoForm form){
		List<?> data = hspConfigBaseinfoDAO.getByParent(form.getCommConfigLocationId1(), "2");
		String[] tempId = null;
		String[] tempName = null;
		if(data!=null){
			tempId = new String[data.size()+1];
			tempName = new String[data.size()+1];
			tempId[0] = "";
			tempName[0] = "";
			for(int i=0; i<data.size(); i++){
				CommConfigLocation district =(CommConfigLocation)data.get(i);
				tempId[i+1] = district.getId();
				tempName[i+1] = district.getItemName();
			}
		}
		form.setCommConfigLocationId2s(tempId);
		form.setCommConfigLocationId2_names(tempName);
	}
	
	/**
	 * 获取市对应的县列表
	 */
	public void getDistricts(HspConfigBaseinfoForm form){
		List<?> data = hspConfigBaseinfoDAO.getByParent(form.getCommConfigLocationId2(), "3");
		String[] tempId = null;
		String[] tempName = null;
		if(data!=null){
			tempId = new String[data.size()+1];
			tempName = new String[data.size()+1];
			tempId[0] = "";
			tempName[0] = "";
			for(int i=0; i<data.size(); i++){
				CommConfigLocation district =(CommConfigLocation)data.get(i);
				tempId[i+1] = district.getId();
				tempName[i+1] = district.getItemName();
			}
		}
		form.setCommConfigLocationId3s(tempId);
		form.setCommConfigLocationId3_names(tempName);
	}


	/** 在初始化阶段将字典库传入form中 */
	public void init(HspConfigBaseinfoForm form , HttpServletRequest request){
		// -----------------------------------------------------------------------------
		this.getDict(form);
		// --------所有字典都定义成数组-------
		/** 医院等 */
		form.setCommConfigUnittypeIds(this.getCommConfigUnittypeId());
		form.setCommConfigUnittypeId_names(this.getCommConfigUnittypeName());
		/** 医院级*/
		form.setCommConfigUnitgradeIds(this.getCommConfigUnitgradeId());
		form.setCommConfigUnitgradeId_names(this.getCommConfigUnitgradeName());
		/** 经济性质 */
		form.setCommConfigEconkindIds(this.getCommConfigEconkindId());
		form.setCommConfigEconkindId_names(this.getCommConfigEconkindName());
		

//		SecurityDataObjectVsRoles nSecurityDataObjectVsRoles=securityUserBaseinfoDAO.findById(form.getStaffId());
//		SecurityStaffBaseinfo nSecurityStaffBaseinfo=securityUserBaseinfoDAO.findSecurityStaffBaseinfoById(form.getStaffId());
//		if(nSecurityStaffBaseinfo!=null){
//		String hspId=nSecurityStaffBaseinfo.getHspConfigBaseinfoId();
//		HspConfigBaseinfo nHspConfigBaseinfo=hspConfigBaseinfoDAO.getById(hspId);
//		if(nSecurityDataObjectVsRoles!=null){
//		int sdotId=Integer.parseInt(nSecurityDataObjectVsRoles.getSdotId());
//			switch(sdotId){
//				case 1:
//					if(form.getCommConfigLocationId1()==null||form.getCommConfigLocationId1().trim().length()==0){
//						form.setCommConfigLocationId1(nSecurityDataObjectVsRoles.getSdoValue());
//						CommConfigLocation location1=this.commConfigLocationDAO.findById(nHspConfigBaseinfo.getCommConfigLocationId1());
//			    		form.setCommConfigLocationId1s(new String[]{location1.getItemCode()});
//			    		form.setCommConfigLocationId1_names(new String[]{location1.getItemName()});
//					}else{
//						form.setCommConfigLocationId1s(new String[]{form.getCommConfigLocationId1()});
//						form.setCommConfigLocationId1_names(new String[]{form.getCommConfigLocationName1()});
//						form.setCommConfigLocationId2s(this.getCommConfigLocationId2());
//						form.setCommConfigLocationId2_names(this.getCommConfigLocationName2());
//						form.setCommConfigLocationId3s(this.getCommConfigLocationId3());
//						form.setCommConfigLocationId3_names(this.getCommConfigLocationName3());
//					}
//					break;
//				case 2:
//					form.setCommConfigLocationId1(nHspConfigBaseinfo.getCommConfigLocationId1());
//					if(form.getCommConfigLocationId2()==null||form.getCommConfigLocationId2().trim().length()==0){
//						form.setCommConfigLocationId2(nSecurityDataObjectVsRoles.getSdoValue());
//						CommConfigLocation location=this.commConfigLocationDAO.findById(nSecurityDataObjectVsRoles.getSdoValue());
//			    		form.setCommConfigLocationId2s(new String[]{location.getItemCode()});
//			    		form.setCommConfigLocationId2_names(new String[]{location.getItemName()});
//			    		CommConfigLocation location1=this.commConfigLocationDAO.findById(nHspConfigBaseinfo.getCommConfigLocationId1());
//			    		form.setCommConfigLocationId1s(new String[]{location1.getItemCode()});
//			    		form.setCommConfigLocationId1_names(new String[]{location1.getItemName()});
//					}else{
//						form.setCommConfigLocationId1s(new String[]{form.getCommConfigLocationId1()});
//						form.setCommConfigLocationId1_names(new String[]{form.getCommConfigLocationName1()});
//						form.setCommConfigLocationId2s(new String[]{form.getCommConfigLocationId2()});
//						form.setCommConfigLocationId2_names(new String[]{form.getCommConfigLocationName2()});
//						form.setCommConfigLocationId3s(this.getCommConfigLocationId3());
//						form.setCommConfigLocationId3_names(this.getCommConfigLocationName3());
//					}
//					break;
//				case 3:
//					form.setCommConfigLocationId1(nHspConfigBaseinfo.getCommConfigLocationId1());
//					form.setCommConfigLocationId2(nHspConfigBaseinfo.getCommConfigLocationId2());
//					if(form.getCommConfigLocationId3()==null||form.getCommConfigLocationId3().trim().length()==0){
//						form.setCommConfigLocationId3(nSecurityDataObjectVsRoles.getSdoValue());
//						
//						form.setCommConfigLocationId2(nSecurityDataObjectVsRoles.getSdoValue());
//						CommConfigLocation location=this.commConfigLocationDAO.findById(nHspConfigBaseinfo.getCommConfigLocationId2());
//			    		form.setCommConfigLocationId2s(new String[]{location.getItemCode()});
//			    		form.setCommConfigLocationId2_names(new String[]{location.getItemName()});
//			    		
//			    		CommConfigLocation location1=this.commConfigLocationDAO.findById(nHspConfigBaseinfo.getCommConfigLocationId1());
//			    		form.setCommConfigLocationId1s(new String[]{location1.getItemCode()});
//			    		form.setCommConfigLocationId1_names(new String[]{location1.getItemName()});
//			    		
//			    		List<?> location3=this.commonDAO.findListByHql("from CommConfigLocation l where l.parentId='"+location.getItemCode()+"' and l.levelFlag='3'");
//			    		if(location3!=null&&location3.size()>0){
//			    			String[] key=new String[location3.size()];
//			    			String[] value=new String[location3.size()];
//			    			for(int i=0;i<location3.size();i++){
//			    				CommConfigLocation l=(CommConfigLocation)location3.get(i);
//			    				key[i]=l.getItemCode();
//			    				value[i]=l.getItemName();
//			    			}
//			    			form.setCommConfigLocationId3s(key);
//			    			form.setCommConfigLocationId3_names(value);
//			    		}
//					}else{
//						form.setCommConfigLocationId1s(new String[]{form.getCommConfigLocationId1()});
//						form.setCommConfigLocationId1_names(new String[]{form.getCommConfigLocationName1()});
//						form.setCommConfigLocationId2s(new String[]{form.getCommConfigLocationId2()});
//						form.setCommConfigLocationId2_names(new String[]{form.getCommConfigLocationName2()});
//						List<?> ls=commonDAO.findListByHql("from CommConfigLocation c where c.parentId='"+form.getCommConfigLocationId2()+"' and c.levelFlag=3");
//						String[] sid=new String[ls.size()];
//						String[] sName=new String[ls.size()];
//						for(int i=0;i<ls.size();i++){
//							CommConfigLocation location=(CommConfigLocation)ls.get(i);
//								sid[i]=location.getItemCode();
//								sName[i]=location.getItemName();
//						}
//						form.setCommConfigLocationId3(BaseSecurityInit.getProperty("INIT_COUNTY"));
//						form.setCommConfigLocationId3s(sid);
//						form.setCommConfigLocationId3_names(sName);
//						
//						List<?> list=commonDAO.findListByHql("from CommConfigLocationTown c where c.commConfigLocationId='"+ BaseSecurityInit.getProperty("INIT_COUNTY") +"'");
//						String[] ssid=new String[list.size()];
//						String[] ssName=new String[list.size()];
//						for(int i=0;i<list.size();i++){
//							CommConfigLocationTown location=(CommConfigLocationTown)list.get(i);
//							ssid[i]=location.getItemCode();
//							ssName[i]=location.getItemName();
//						}
//						form.setCommConfigLocationTownIds(ssid);
//						form.setCommConfigLocationTownId_names(ssName);
//						
//					}
//					break;
//		}
//		}
//		}
		/** 所属省 */
		form.setCommConfigLocationId1s(this.getCommConfigLocationId1());
		form.setCommConfigLocationId1_names(this.getCommConfigLocationName1());
		/** 所属市 */
		form.setCommConfigLocationId2s(this.getCommConfigLocationId2());
		form.setCommConfigLocationId2_names(this.getCommConfigLocationName2());
		/** 所属县 */
		form.setCommConfigLocationId3s(this.getCommConfigLocationId3());
		form.setCommConfigLocationId3_names(this.getCommConfigLocationName3());
		/** 乡镇 */
		form.setCommConfigLocationTownIds(this.getCommConfigLocationTownId());
		form.setCommConfigLocationTownId_names(this.getCommConfigLocationTownIdname());
		/** 村 */
		form.setCommClvIds(this.getCommClvId());
		form.setCommClvId_names(this.getCommClvIdname());
		/** 上级医疗机构 */
		form.setParentItemCodes(this.getParentItemCodess());
		form.setParentItemCode_names(this.getParentItemNamess());
		/** 机构分类管理 */
		form.setCommConfigFtManageIds(this.getCommConfigFtManageIds());
		form.setCommConfigFtManageId_names(this.getCommConfigFtManageId_names());

	}

	public void getDetail(HspConfigBaseinfoForm form){}


	/** 根据字典类别获取字典详细信息列表 */
	public void getDict(HspConfigBaseinfoForm form){
		form.setC010501aList(this.hspConfigBaseinfoDAO.findCommConfigUnittypeList());
		// 医院级
		form.setC010501bList(this.hspConfigBaseinfoDAO.findCommConfigUnitgradeList());
		List<?> commConfigUnittype_data = hspConfigBaseinfoDAO.findCommConfigUnittypeList();
		List<?> commConfigUnitgrade_data = hspConfigBaseinfoDAO.findCommConfigUnitgradeList();
		List<?> commConfigEconkind_data = commConfigEconkindDAO.findAll();
		List<?> commConfigLocationId1_data = hspConfigBaseinfoDAO.getByParent("", "1");
		List<?> commConfigLocationId2_data = null;
		if(form.getCommConfigLocationId1()!= null && form.getCommConfigLocationId1().trim().length()>0){
			commConfigLocationId2_data = hspConfigBaseinfoDAO.getByParent(form.getCommConfigLocationId1(), "2");
		}
		List<?> commConfigLocationId3_data = null;
		if(form.getCommConfigLocationId2()!=null && form.getCommConfigLocationId2().trim().length()>0){
			commConfigLocationId3_data = hspConfigBaseinfoDAO.getByParent(form.getCommConfigLocationId2(), "3");
		}
		List<?> commConfigLocationTownId_data = null;
		if(form.getCommConfigLocationId3()!=null && form.getCommConfigLocationId3().trim().length()>0){
			commConfigLocationTownId_data = hspConfigBaseinfoDAO.getTownsByParent(form.getCommConfigLocationId3());
		}
		List<?> commClvId_data = null;
		if(form.getCommClvId()!=null && form.getCommClvId().trim().length()>0){
			commClvId_data = hspConfigBaseinfoDAO.getVillagesByParent(form.getCommConfigLocationTownId());
		}

		List<?> getCommConfigFtManageIds_data = hspConfigBaseinfoDAO.getCommConfigFtManageIds();// 机构分类管理-修改
		// ------------------------医院等----------------------------------------------------
		String[] tempId = null;
		String[] tempName = null;
		if(commConfigUnittype_data!=null){
			tempId = new String[commConfigUnittype_data.size()+1];
			tempName = new String[commConfigUnittype_data.size()+1];
			tempId[0] = "";
			tempName[0] = "";
			for(int i=0; i<commConfigUnittype_data.size(); i++){
				CommConfigUnittype data =(CommConfigUnittype) commConfigUnittype_data.get(i);
				tempId[i+1] = Converter.toBlank(data.getSeqNo());
				tempName[i+1] = Converter.toBlank(data.getItemName());
			}
		}
		this.setCommConfigUnittypeId(tempId);
		this.setCommConfigUnittypeName(tempName);
		// ------------------------医院级-----------------------------------------------------
		tempId = null;
		tempName = null;
		if(commConfigUnitgrade_data!=null){
			tempId = new String[commConfigUnitgrade_data.size()+1];
			tempName = new String[commConfigUnitgrade_data.size()+1];
			tempId[0] = "";
			tempName[0] = "";
			for(int i=0; i<commConfigUnitgrade_data.size(); i++){
				CommConfigUnitgrade data =(CommConfigUnitgrade) commConfigUnitgrade_data.get(i);
				tempId[i+1] = Converter.toBlank(data.getSeqNo());
				tempName[i+1] = Converter.toBlank(data.getItemName());
			}
		}
		this.setCommConfigUnitgradeId(tempId);
		this.setCommConfigUnitgradeName(tempName);
		// ------------------------民族-----------------------------------------------------
		tempId = null;
		tempName = null;
		if(commConfigEconkind_data!=null){
			tempId = new String[commConfigEconkind_data.size()+1];
			tempName = new String[commConfigEconkind_data.size()+1];
			tempId[0] = "";
			tempName[0] = "";
			for(int i=0; i<commConfigEconkind_data.size(); i++){
				CommConfigEconkind data =(CommConfigEconkind) commConfigEconkind_data.get(i);
				tempId[i+1] = Converter.toBlank(data.getItemCode());
				tempName[i+1] = Converter.toBlank(data.getItemName());
			}
		}
		this.setCommConfigEconkindId(tempId);
		this.setCommConfigEconkindName(tempName);
		// ----------------------省----------------------------------------------------
		tempId = null;
		tempName = null;
		if(commConfigLocationId1_data != null){
			tempId = new String[commConfigLocationId1_data.size()+1];
			tempName = new String[commConfigLocationId1_data.size()+1];
			tempId[0] = "";
			tempName[0] = "";
			for(int i=0; i<commConfigLocationId1_data.size(); i++){
				CommConfigLocation data =(CommConfigLocation) commConfigLocationId1_data.get(i);
				tempId[i+1] = Converter.toBlank(data.getItemCode());
				tempName[i+1] = Converter.toBlank(data.getItemName());
			}
		}
		this.setCommConfigLocationId1(tempId);
		this.setCommConfigLocationName1(tempName);
		// ----------------------市----------------------------------------------------
		tempId = null;
		tempName = null;
		if(commConfigLocationId2_data!=null){
			tempId = new String[commConfigLocationId2_data.size()+1];
			tempName = new String[commConfigLocationId2_data.size()+1];
			tempId[0] = "";
			tempName[0] = "";
			for(int i=0; i<commConfigLocationId2_data.size(); i++){
				CommConfigLocation data =(CommConfigLocation) commConfigLocationId2_data.get(i);
				tempId[i+1] = Converter.toBlank(data.getItemCode());
				tempName[i+1] = Converter.toBlank(data.getItemName());
			}
		}else{
			// --------------如果为空，插入空格-----------
			tempId = new String[1];
			tempName = new String[1];
			tempId[0] = "";
			tempName[0] = "";
		}
		this.setCommConfigLocationId2(tempId);
		this.setCommConfigLocationName2(tempName);
		// ----------------------县----------------------------------------------------
		tempId = null;
		tempName = null;
		if(commConfigLocationId3_data!=null){
			tempId = new String[commConfigLocationId3_data.size() + 1];
			tempName = new String[commConfigLocationId3_data.size() + 1];
			tempId[0] = "";
			tempName[0] = "";
			for(int i=0; i<commConfigLocationId3_data.size(); i++){
				CommConfigLocation data =(CommConfigLocation) commConfigLocationId3_data.get(i);
				tempId[i+1] = Converter.toBlank(data.getItemCode());
				tempName[i+1] = Converter.toBlank(data.getItemName());
			}
		}else{
			// --------------如果为空，插入空格-----------
			tempId = new String[1];
			tempName = new String[1];
			tempId[0] = "";
			tempName[0] = "";
		} 
		this.setCommConfigLocationId3(tempId);
		this.setCommConfigLocationName3(tempName);

		// ----------------------乡镇----------------------------------------------------
		tempId = null;
		tempName = null;
		if(commConfigLocationTownId_data!=null){
			tempId = new String[commConfigLocationTownId_data.size()+1];
			tempName = new String[commConfigLocationTownId_data.size()+1];
			tempId[0] = "";
			tempName[0] = "";
			for(int i=0; i<commConfigLocationTownId_data.size(); i++){
				CommConfigLocationTown data =(CommConfigLocationTown) commConfigLocationTownId_data.get(i);
				tempId[i+1] = Converter.toBlank(data.getId());
				tempName[i+1] = Converter.toBlank(data.getItemName());
			}
		}else{
			// --------------如果为空，插入空格-----------
			tempId = new String[1];
			tempName = new String[1];
			tempId[0] = "";
			tempName[0] = "";
		}
		this.setCommConfigLocationTownId(tempId);
		this.setCommConfigLocationTownIdname(tempName);
		// ----------------------村----------------------------------------------------
		tempId = null;
		tempName = null;
		if(commClvId_data!=null){
			tempId = new String[commClvId_data.size()+1];
			tempName = new String[commClvId_data.size()+1];
			tempId[0] = "";
			tempName[0] = "";
			for(int i=0; i<commClvId_data.size(); i++){
				CommConfigLocationVillage data =(CommConfigLocationVillage) commClvId_data.get(i);
				tempId[i+1] = Converter.toBlank(data.getId());
				tempName[i+1] = Converter.toBlank(data.getItemName());
			}
		}else{
			// --------------如果为空，插入空格-----------
			tempId = new String[1];
			tempName = new String[1];
			tempId[0] = "";
			tempName[0] = "";
		}
		form.setCommClvIds(tempId);
		form.setCommClvId_names(tempName);
		this.setCommClvId(tempId);
		this.setCommClvIdname(tempName);

		// ----------------------机构分类管理_修改后----------------------------------------------------
		tempId = null;
		tempName = null;
		if(getCommConfigFtManageIds_data != null){
			tempId = new String[getCommConfigFtManageIds_data.size()+1];
			tempName = new String[getCommConfigFtManageIds_data.size()+1];
			tempId[0] = "";
			tempName[0] = "";
			for(int i=0; i<getCommConfigFtManageIds_data.size(); i++){
				CommConfigFtManage data =(CommConfigFtManage) getCommConfigFtManageIds_data.get(i);
				tempId[i+1] = Converter.toBlank(data.getItemCode());
				tempName[i+1] = Converter.toBlank(data.getItemName());
			}
		}else{
			// --------------如果为空，插入空格-----------
			tempId = new String[1];
			tempName = new String[1];
			tempId[0] = "";
			tempName[0] = "";
		}
		List<?> list=this.commonDAO.findAllInTable("CommConfigSettype");
		if(list.size()>0&&list!=null){
			String[] settypeCode=new String[list.size()];
			String[] settypeNmae=new String[list.size()];
			for(int i=0;i<list.size();i++){
				CommConfigSettype ccs=(CommConfigSettype)list.get(i);
				settypeCode[i+0]=Converter.toBlank(ccs.getItemCode());
				settypeNmae[i+0]=Converter.toBlank(ccs.getItemName());
			}
			form.setCommConfigSetTypeId(settypeCode);
			form.setCommConfigSetType(settypeNmae);
		}
		
		List<?> list1=this.commonDAO.findListByHql("from CommDictPublicChar c where c.classCode='N200711_T1-1_1.4.3'");
		if(list.size()>0&&list!=null){
			String[] settypeCode=new String[list1.size()];
			String[] settypeNmae=new String[list1.size()];
			for(int i=0;i<list1.size();i++){
				CommDictPublicChar ccs=(CommDictPublicChar)list1.get(i);
				settypeCode[i+0]=Converter.toBlank(ccs.getId());
				settypeNmae[i+0]=Converter.toBlank(ccs.getDictValue());
			}
			form.setCommConfigCovaffrsId(settypeCode);
			form.setCommConfigCovaffrs(settypeNmae);
		}
		this.setCommConfigFtManageIds(tempId);
		this.setCommConfigFtManageId_names(tempName);
	}

	/** 处理弹出层子画面 */
	public String getXml( String flag, String inputCode, String hspType,String sattfConfigId, HttpServletRequest request){
		
//		List<?> ls=hspConfigBaseinfoDAO.getHspBaseInfoId(sattfId);
//		String hspId="";
//		if(ls!=null&&ls.size()>0){
//			Object[] obj=(Object[])ls.get(0);
//			HspConfigBaseinfo hsp=(HspConfigBaseinfo)obj[1];
//			hspId=hsp.getHspConfigBaseinfoId3();
//		}
		ServletContext application = request.getSession().getServletContext();
		StringBuffer buffer = new StringBuffer();
		List<?> list=new ArrayList();
		//hspType 1为除去卫生服务站、2为只包括服务站和服务中心、3为所有
		list = hspConfigBaseinfoDAO.findHspList(flag, inputCode, "",sattfConfigId, Integer.valueOf((String)application.getAttribute("hsp.CURRECORD_TANCHUCENG")), Integer.valueOf((String)application.getAttribute("hsp.PAGE_SIZE_TANCHUCENG")));
		
		buffer.append("<response>");
		for(int i=0; i<list.size(); i++){
			//HspConfigBaseinfo charbean
			HspConfigBaseinfo charbean =(HspConfigBaseinfo) list.get(i);
		
			// -----以下xml标签不要改变--------------------------------------------------------------
			buffer.append("<ress>");
			buffer.append("<resInputCode>"+charbean.getInputCode()+"</resInputCode>");
			buffer.append("<resItemCode>"+Converter.toBlank(charbean.getItemCode())+"</resItemCode>");
			buffer.append("<resItemName>"+Converter.toBlank(charbean.getItemName())+"</resItemName>");
			buffer.append("<resItemId>"+Converter.toBlank(charbean.getId())+"</resItemId>");
			buffer.append("</ress>");
			
		}
		buffer.append("</response>");
		return buffer.toString();
	}

	// -------------------------------------------------------------------------------------
	/** 字典库的处理 */
	private String[] commConfigUnittypeId;
	private String[] commConfigUnittypeName;
	private String[] commConfigUnitgradeId;
	private String[] commConfigUnitgradeName;
	private String[] commConfigEconkindId;
	private String[] commConfigEconkindName;
	private String[] commConfigLocationId1;
	private String[] commConfigLocationName1;
	private String[] commConfigLocationId2;
	private String[] commConfigLocationName2;
	private String[] commConfigLocationId3;
	private String[] commConfigLocationName3;
	private String[] parentItemCodess;
	private String[] parentItemNamess;
	private String[] commConfigLocationTownId;
	private String[] commConfigLocationTownIdname;
	private String[] commClvId;
	private String[] commClvIdname;



	private String[] CommConfigFtManageIds; // 机构分类管理-修改后
	private String[] CommConfigFtManageId_names;

	public String[] getParentItemCodess(){
		return parentItemCodess;
	}
	public void setParentItemCodess(String[] parentItemCodess){
		this.parentItemCodess = parentItemCodess;
	}
	public String[] getParentItemNamess(){
		return parentItemNamess;
	}
	public void setParentItemNamess(String[] parentItemNamess){
		this.parentItemNamess = parentItemNamess;
	}
	public String[] getCommConfigUnittypeId(){
		return commConfigUnittypeId;
	}
	public void setCommConfigUnittypeId(String[] commConfigUnittypeId){
		this.commConfigUnittypeId = commConfigUnittypeId;
	}
	public String[] getCommConfigUnittypeName(){
		return commConfigUnittypeName;
	}
	public void setCommConfigUnittypeName(String[] commConfigUnittypeName){
		this.commConfigUnittypeName = commConfigUnittypeName;
	}
	public String[] getCommConfigUnitgradeId(){
		return commConfigUnitgradeId;
	}
	public void setCommConfigUnitgradeId(String[] commConfigUnitgradeId){
		this.commConfigUnitgradeId = commConfigUnitgradeId;
	}
	public String[] getCommConfigUnitgradeName(){
		return commConfigUnitgradeName;
	}
	public void setCommConfigUnitgradeName(String[] commConfigUnitgradeName){
		this.commConfigUnitgradeName = commConfigUnitgradeName;
	}
	public String[] getCommConfigEconkindId(){
		return commConfigEconkindId;
	}
	public void setCommConfigEconkindId(String[] commConfigEconkindId){
		this.commConfigEconkindId = commConfigEconkindId;
	}
	public String[] getCommConfigEconkindName(){
		return commConfigEconkindName;
	}
	public void setCommConfigEconkindName(String[] commConfigEconkindName){
		this.commConfigEconkindName = commConfigEconkindName;
	}
	public String[] getCommConfigLocationId1(){
		return commConfigLocationId1;
	}
	public void setCommConfigLocationId1(String[] commConfigLocationId1){
		this.commConfigLocationId1 = commConfigLocationId1;
	}
	public String[] getCommConfigLocationName1(){
		return commConfigLocationName1;
	}
	public void setCommConfigLocationName1(String[] commConfigLocationName1){
		this.commConfigLocationName1 = commConfigLocationName1;
	}
	public String[] getCommConfigLocationId2(){
		return commConfigLocationId2;
	}
	public void setCommConfigLocationId2(String[] commConfigLocationId2){
		this.commConfigLocationId2 = commConfigLocationId2;
	}
	public String[] getCommConfigLocationName2(){
		return commConfigLocationName2;
	}
	public void setCommConfigLocationName2(String[] commConfigLocationName2){
		this.commConfigLocationName2 = commConfigLocationName2;
	}
	public String[] getCommConfigLocationId3(){
		return commConfigLocationId3;
	}
	public void setCommConfigLocationId3(String[] commConfigLocationId3){
		this.commConfigLocationId3 = commConfigLocationId3;
	}
	public String[] getCommConfigLocationName3(){
		return commConfigLocationName3;
	}
	public void setCommConfigLocationName3(String[] commConfigLocationName3){
		this.commConfigLocationName3 = commConfigLocationName3;
	}
	public String[] getCommConfigLocationTownId(){
		return commConfigLocationTownId;
	}
	public void setCommConfigLocationTownId(String[] commConfigLocationTownId){
		this.commConfigLocationTownId = commConfigLocationTownId;
	}
	public String[] getCommConfigLocationTownIdname(){
		return commConfigLocationTownIdname;
	}
	public void setCommConfigLocationTownIdname(String[] commConfigLocationTownIdname){
		this.commConfigLocationTownIdname = commConfigLocationTownIdname;
	}
	public String[] getCommClvId(){
		return commClvId;
	}
	public void setCommClvId(String[] commClvId){
		this.commClvId = commClvId;
	}
	public String[] getCommClvIdname(){
		return commClvIdname;
	}
	public void setCommClvIdname(String[] commClvIdname){
		this.commClvIdname = commClvIdname;
	}
	public String[] getCommConfigFtManageIds(){
		return CommConfigFtManageIds;
	}
	public void setCommConfigFtManageIds(String[] commConfigFtManageIds){
		CommConfigFtManageIds = commConfigFtManageIds;
	}
	public String[] getCommConfigFtManageId_names(){
		return CommConfigFtManageId_names;
	}
	public void setCommConfigFtManageId_names(String[] commConfigFtManageId_names){
		CommConfigFtManageId_names = commConfigFtManageId_names;
	}
	public String getP2ValueByP1(String tableName, String p1, String p1Value, String p2){
		return this.hspConfigBaseinfoDAO.getP2ValueByP1(tableName, p1, p1Value, p2);
	}
	public int getCount(String id, String itemCode,String parentItemCode, String itemName, String inputCode, String seqNo,String commConfigHospitalTypeId,String commConfigUnitgradeId,String commConfigUnittypeId,String commConfigFtManageId,String commConfigEconkindId,String commConfigLocationTownId,String commClvId){
		//return hspConfigBaseinfoDAO.getCount(id, itemCode, parentItemCode, itemName, inputCode, seqNo, commConfigHospitalTypeId, commConfigUnitgradeId, commConfigUnittypeId, commConfigFtManageId, commConfigEconkindId, commConfigLocationTownId, commClvId);
		return 0;
	}
	
	public HspConfigBaseinfo findById(String id) {
		return this.getHspConfigBaseinfoDAO().getById(id);
	}
	public IHspStaffBaseinfoDAO getHspStaffBaseinfoDAO() {
		return hspStaffBaseinfoDAO;
	}
	public void setHspStaffBaseinfoDAO(IHspStaffBaseinfoDAO hspStaffBaseinfoDAO) {
		this.hspStaffBaseinfoDAO = hspStaffBaseinfoDAO;
	}
	@Override
	public int getCount(HspConfigBaseinfoForm hosform) {
		return this.getHspConfigBaseinfoDAO().getCount(hosform);
	}
	
	public void getSearch(HspConfigBaseinfoForm form, HttpServletRequest request,int curCount, int pageSize){
		
		List<?> list = this.getHspConfigBaseinfoDAO().getData(form, curCount, pageSize);
		
		if(list != null){
			String[] ids = new String[list.size()];
			String[] itemCodes = new String[list.size()];
			String[] itemNames = new String[list.size()];
			String[] addresses = new String[list.size()];
			String[] seqNos = new String[list.size()];
			String[] phones = new String[list.size()];
			String[] contactPersonNames = new String[list.size()];
			int i = 0;
			for(Iterator<?> it = list.iterator(); it.hasNext(); i++){
				HspConfigBaseinfo bean =(HspConfigBaseinfo) it.next();
				ids[i] = Converter.toBlank(bean.getId());
				itemCodes[i] = Converter.toBlank(bean.getItemCode());
				itemNames[i] = Converter.toBlank(bean.getItemName());
				addresses[i] = Converter.toBlank(bean.getCommConfigLocationName1())
									+Converter.toBlank(bean.getCommConfigLocationName2())
									+Converter.toBlank(bean.getCommConfigLocationName3())
									+Converter.toBlank(bean.getCcltName())
									+Converter.toBlank(bean.getCommClvName());
				phones[i] = Converter.toBlank(bean.getPhone());
				contactPersonNames[i] = Converter.toBlank(bean.getContactPersonName());
				seqNos[i] = Converter.toBlank(bean.getSeqNo());
			}
			form.setIdList(ids);
			form.setItemCodeList(itemCodes);
			form.setItemNameList(itemNames);
			form.setPhoneList(phones);
			form.setContactPersonNameList(contactPersonNames);
			form.setSeqNoList(seqNos);
			form.setAddressList(addresses);
		}
		
		init(form, request);
		
	}
}
