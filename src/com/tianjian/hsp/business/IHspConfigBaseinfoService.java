package com.tianjian.hsp.business;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.KeyPair;
import java.security.cert.X509Certificate;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.tianjian.hsp.bean.HspConfigBaseinfo;
import com.tianjian.hsp.struts.form.HspConfigBaseinfoForm;

public interface IHspConfigBaseinfoService {
	
	 /**新增之前初始化*/
	public void addInit(HspConfigBaseinfoForm form, HttpServletRequest request);
	 /**新增之前初始化*/
	public void addInits(HspConfigBaseinfoForm form,HttpServletRequest request,String staffId);
	public void init(HspConfigBaseinfoForm form, HttpServletRequest request);
	/**检查itemCode的唯一性*/
	public HspConfigBaseinfo checkData(String itemCode);
	/**save*/
	public void save(HspConfigBaseinfoForm form);
	/**修改之前初始化*/
	public void updateInit(HspConfigBaseinfoForm form,HttpServletRequest request,String staffId);
	/**update*/
	public void update(HspConfigBaseinfoForm form);
	/**显示之前初始化*/
	public void showInit(HspConfigBaseinfoForm form, HttpServletRequest request);
	/**delete*/
	public void delete(HspConfigBaseinfoForm form);	
	public int getCountAll(String id, String itemCode, String itemName, String inputCode,String seqNo,String commConfigHospitalTypeId);
	public void getSearchAll(HspConfigBaseinfoForm form, HttpServletRequest request ,int curCount, int quChuCount);
	/**获取省*/
	public void getProvinces(HspConfigBaseinfoForm form);
	/**市*/
	public void getCitys(HspConfigBaseinfoForm form);
	/**获取县*/
	public void getDistricts(HspConfigBaseinfoForm form);
    /**获取详细信息*/
	public void getDetail(HspConfigBaseinfoForm form);
	/**用于弹出层显示*/
	public String getXml(String flag,String inputCode,String hspType,String sattfId,HttpServletRequest request);
	public String getP2ValueByP1(String tableName,String p1,String p1Value,String p2);
	public HSSFWorkbook getExcel(HspConfigBaseinfoForm form, String fileName,String sattfId, HttpServletRequest request);
	public String elsImport(InputStream inputstream, HttpServletRequest request);
	/**根据id找医疗机构基本信息**/
	public HspConfigBaseinfo findById(String id);

	public void detailInit(HspConfigBaseinfoForm form);
	/**获取详细信息*/
	
	/**获取pdf格式的医疗机构信息 */
	public void getPdf(HspConfigBaseinfoForm form, ByteArrayOutputStream ba, String sattfId, HttpServletRequest request);
	
	/**获取word格式的医疗机构的信息 */
	public void getWord(HspConfigBaseinfoForm form, String newFilePath, String sattfId, HttpServletRequest request);
	public String getNewFilePath(HttpServletRequest request) throws Exception;
	public void getSearch(HspConfigBaseinfoForm hosform, HttpServletRequest request, int count, int pageSize);
	public int getCount(HspConfigBaseinfoForm hosform);
}
