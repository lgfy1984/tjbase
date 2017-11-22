package com.tianjian.empi.business.service;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

import com.tianjian.comm.bean.CommConfigAbo;
import com.tianjian.comm.bean.CommConfigCardtype;
import com.tianjian.comm.bean.CommConfigCountry;
import com.tianjian.comm.bean.CommConfigIdType;
import com.tianjian.comm.bean.CommConfigLocation;
import com.tianjian.comm.bean.CommConfigLocationGroup;
import com.tianjian.comm.bean.CommConfigLocationTown;
import com.tianjian.comm.bean.CommConfigLocationVillage;
import com.tianjian.comm.bean.CommConfigMaritalStatus;
import com.tianjian.comm.bean.CommConfigNationality;
import com.tianjian.comm.bean.CommConfigRelationship;
import com.tianjian.comm.bean.CommConfigRh;
import com.tianjian.comm.bean.CommConfigSex;
import com.tianjian.comm.bean.CommConfigVocation;
import com.tianjian.comm.bean.CommConfigYes;
import com.tianjian.comm.bean.CommDictPublicChar;
import com.tianjian.comm.business.ICommConfigInputDictService;
import com.tianjian.comm.dao.ICommConfigAboDAO;
import com.tianjian.comm.dao.ICommConfigCardtypeDAO;
import com.tianjian.comm.dao.ICommConfigCountryDAO;
import com.tianjian.comm.dao.ICommConfigDegreeDAO;
import com.tianjian.comm.dao.ICommConfigIdTypeDAO;
import com.tianjian.comm.dao.ICommConfigLocationDAO;
import com.tianjian.comm.dao.ICommConfigLocationGroupDAO;
import com.tianjian.comm.dao.ICommConfigLocationTownDAO;
import com.tianjian.comm.dao.ICommConfigLocationVillageDAO;
import com.tianjian.comm.dao.ICommConfigMaritalStatusDAO;
import com.tianjian.comm.dao.ICommConfigNationalityDAO;
import com.tianjian.comm.dao.ICommConfigRelationshipDAO;
import com.tianjian.comm.dao.ICommConfigRhDAO;
import com.tianjian.comm.dao.ICommConfigSexDAO;
import com.tianjian.comm.dao.ICommConfigVocationDAO;
import com.tianjian.comm.dao.ICommConfigYesDAO;
import com.tianjian.comm.dao.ICommonDAO;
import com.tianjian.empi.bean.HealthAssiBean;
import com.tianjian.empi.bean.SecurityConfigUsers;
import com.tianjian.empi.bean.SecurityIHEPatientIdList;
import com.tianjian.empi.bean.SecurityUserBaseinfo;
import com.tianjian.empi.bean.SecurityUserDeprecated;
import com.tianjian.empi.business.ISecurityUserBaseinfoService;
import com.tianjian.empi.dao.ISecurityUserBaseinfoDAO;
import com.tianjian.empi.struts.form.SecurityUserBaseinfoForm;
import com.tianjian.hsp.bean.HspConfigBaseinfo;
import com.tianjian.hsp.dao.IHspConfigBaseinfoDAO;
import com.tianjian.security.bean.SecurityDataObjectType;
import com.tianjian.security.bean.SecurityDataObjectVsRoles;
import com.tianjian.security.bean.SecurityStaffBaseinfo;
import com.tianjian.security.struts.form.SessionForm;
import com.tianjian.security.struts.servlet.BaseSecurityInit;
import com.tianjian.util.Converter;
import com.tianjian.util.comm.MD5;

public class SecurityUserBaseinfoServiceImpl implements ISecurityUserBaseinfoService {

	private ISecurityUserBaseinfoDAO securityUserBaseinfoDAO;
	private ICommConfigSexDAO commConfigSexDAO;
	private ICommConfigCountryDAO commConfigCountryDAO;
	private ICommConfigCardtypeDAO commConfigCardtypeDAO;
	private ICommConfigNationalityDAO commConfigNationalityDAO;
	private ICommConfigIdTypeDAO commConfigIdTypeDAO;
	private ICommConfigAboDAO commConfigAboDAO;
	private ICommConfigRhDAO commConfigRhDAO;
	private ICommConfigDegreeDAO commConfigDegreeDAO;
	private ICommConfigMaritalStatusDAO commConfigMaritalStatusDAO;
	private ICommConfigLocationDAO commConfigLocationDAO;
	private ICommConfigRelationshipDAO commConfigRelationshipDAO;
	private ICommConfigInputDictService commConfigInputDictService;

	private IHspConfigBaseinfoDAO hspConfigBaseinfoDAO;
	private ICommConfigLocationTownDAO commConfigTownDao;
	private ICommConfigLocationVillageDAO commConfigVillageDao;
	private ICommConfigLocationGroupDAO commConfigGroupDao;
	private ICommonDAO commonDAO;
	private ICommConfigVocationDAO commConfigVocationDAO;
	private ICommConfigYesDAO commConfigYesDAO;
	// ------------引入dao接口------------------------------start-------------------------------------
	/**
	 * @return Returns the securityUserBaseinfoDAO.
	 */
	public ISecurityUserBaseinfoDAO getSecurityUserBaseinfoDAO() {
		return securityUserBaseinfoDAO;
	}

	public ICommConfigCardtypeDAO getCommConfigCardtypeDAO() {
		return commConfigCardtypeDAO;
	}


	public void setCommConfigCardtypeDAO(
			ICommConfigCardtypeDAO commConfigCardtypeDAO) {
		this.commConfigCardtypeDAO = commConfigCardtypeDAO;
	}


	/**
	 * @param securityUserBaseinfoDAO The securityUserBaseinfoDAO to set.
	 */
	public void setSecurityUserBaseinfoDAO(ISecurityUserBaseinfoDAO securityUserBaseinfoDAO) {
		this.securityUserBaseinfoDAO = securityUserBaseinfoDAO;
	}

	public ICommConfigSexDAO getCommConfigSexDAO() {
		return commConfigSexDAO;
	}

	public void setCommConfigSexDAO(ICommConfigSexDAO commConfigSexDAO) {
		this.commConfigSexDAO = commConfigSexDAO;
	}

	public ICommConfigCountryDAO getCommConfigCountryDAO() {
		return commConfigCountryDAO;
	}

	public void setCommConfigCountryDAO(ICommConfigCountryDAO commConfigCountryDAO) {
		this.commConfigCountryDAO = commConfigCountryDAO;
	}

	public ICommConfigNationalityDAO getCommConfigNationalityDAO() {
		return commConfigNationalityDAO;
	}

	public void setCommConfigNationalityDAO(ICommConfigNationalityDAO commConfigNationalityDAO) {
		this.commConfigNationalityDAO = commConfigNationalityDAO;
	}

	public ICommConfigIdTypeDAO getCommConfigIdTypeDAO() {
		return commConfigIdTypeDAO;
	}

	public void setCommConfigIdTypeDAO(ICommConfigIdTypeDAO commConfigIdTypeDAO) {
		this.commConfigIdTypeDAO = commConfigIdTypeDAO;
	}

	public ICommConfigAboDAO getCommConfigAboDAO() {
		return commConfigAboDAO;
	}

	public void setCommConfigAboDAO(ICommConfigAboDAO commConfigAboDAO) {
		this.commConfigAboDAO = commConfigAboDAO;
	}

	public ICommConfigRhDAO getCommConfigRhDAO() {
		return commConfigRhDAO;
	}

	public void setCommConfigRhDAO(ICommConfigRhDAO commConfigRhDAO) {
		this.commConfigRhDAO = commConfigRhDAO;
	}

	public ICommConfigDegreeDAO getCommConfigDegreeDAO() {
		return commConfigDegreeDAO;
	}

	public void setCommConfigDegreeDAO(ICommConfigDegreeDAO commConfigDegreeDAO) {
		this.commConfigDegreeDAO = commConfigDegreeDAO;
	}

	public ICommConfigMaritalStatusDAO getCommConfigMaritalStatusDAO() {
		return commConfigMaritalStatusDAO;
	}

	public void setCommConfigMaritalStatusDAO(ICommConfigMaritalStatusDAO commConfigMaritalStatusDAO) {
		this.commConfigMaritalStatusDAO = commConfigMaritalStatusDAO;
	}

	public ICommConfigLocationDAO getCommConfigLocationDAO() {
		return commConfigLocationDAO;
	}

	public void setCommConfigLocationDAO(ICommConfigLocationDAO commConfigLocationDAO) {
		this.commConfigLocationDAO = commConfigLocationDAO;
	}

	public ICommConfigRelationshipDAO getCommConfigRelationshipDAO() {
		return commConfigRelationshipDAO;
	}

	public void setCommConfigRelationshipDAO(ICommConfigRelationshipDAO commConfigRelationshipDAO) {
		this.commConfigRelationshipDAO = commConfigRelationshipDAO;
	}
	
	
	public ICommConfigVocationDAO getCommConfigVocationDAO() {
		return commConfigVocationDAO;
	}


	public void setCommConfigVocationDAO(
			ICommConfigVocationDAO commConfigVocationDAO) {
		this.commConfigVocationDAO = commConfigVocationDAO;
	}


	public ICommConfigYesDAO getCommConfigYesDAO() {
		return commConfigYesDAO;
	}


	public void setCommConfigYesDAO(ICommConfigYesDAO commConfigYesDAO) {
		this.commConfigYesDAO = commConfigYesDAO;
	}
	/**
	 * 测试
	 * @param code
	 * @param flag
	 * @return
	 */
	public List selectBaseinfobycodee(String code, int start, int everypage) {
		List list = this.getSecurityUserBaseinfoDAO().selectBaseinfobycodee(code, start, everypage);
		if (list != null) {
			List reslist = new ArrayList();
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				
//				System.out.println(" OBJ   "+obj[0]);
				HealthAssiBean bean = new HealthAssiBean();
				try {
					bean.setId(this.transNullToString((String) (obj[0])));
					bean.setName(this.transNullToString((String) obj[1]));
					bean.setSex(this.transNullToString((String) obj[2]));
//					bean.setUsertype((String) obj[3]);
//					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//					bean.setBrithday(df.format((Date) obj[4]));
//					bean.setCardtype((String) obj[5]);
//					bean.setCardid((String) obj[6]);
//					bean.setMobile((String) obj[7]);
					//bean.setIdTypeName(this.transNullToString((String) obj[8]));
					bean.setIdNo(this.transNullToString((String) obj[6]));
					bean.setMailingAddress(this.transNullToString((String) obj[7]));
					bean.setPhone(this.transNullToString((String) obj[8]));
					bean.setPmi(this.transNullToString((String) obj[9]));
					String idType = this.transNullToString((String) obj[10]);
					if(idType != null && idType.length() > 0){
						bean.setIdTypeName(this.getCommConfigIdTypeDAO().getItemName(idType));
					}else{
						bean.setIdTypeName("");
					}
					
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				reslist.add(bean);
			}
			return reslist;
		} else {
			return null;
		}
	}
	/**
	 * 根据sql语句得到符合查询条件的记录个数
	 * @param sqlpage
	 * @return
	 */
	public int getrowsnum(String sqlpage) {
		return this.getSecurityUserBaseinfoDAO().getrowsnum(sqlpage);
	}

	// ------------引入dao接口-----------------------------end-------------------------------------
	public void addInit(SecurityUserBaseinfoForm form,String id) {
		if(form.getCommConfigCountryId()==null||form.getCommConfigCountryId().trim().length()==0)
			form.setCommConfigCountryId(BaseSecurityInit.getProperty("INIT_REAL_COUNTY"));
		if(form.getCommConfigCountryId_name()==null||form.getCommConfigCountryId_name().trim().length()==0)
			form.setCommConfigCountryId_name(commConfigCountryDAO.getItemName(BaseSecurityInit.getProperty("INIT_REAL_COUNTY")));
		if(form.getCommConfigNationalityId()==null||form.getCommConfigNationalityId().trim().length()==0)
			form.setCommConfigNationalityId(BaseSecurityInit.getProperty("INIT_REAL_NATIONALITY"));
		if(form.getCommConfigNationalityId_name()==null||form.getCommConfigNationalityId_name().trim().length()==0)
			form.setCommConfigNationalityId_name(commConfigNationalityDAO.getItemName(BaseSecurityInit.getProperty("INIT_REAL_NATIONALITY")));
		/**设置居住地址省市县的默认值开始*/
		if(form.getCommConfigLocationId1()==null||form.getCommConfigLocationId2().trim().length()==0){
			form.setCommConfigLocationId1(BaseSecurityInit.getProperty("INIT_PROVINCE"));
		}
		if(form.getCommConfigLocationId2()==null||form.getCommConfigLocationId2().trim().length()==0){
			form.setCommConfigLocationId2(BaseSecurityInit.getProperty("INIT_CITY"));
		}
		if(form.getCommConfigLocationId3()==null||form.getCommConfigLocationId3().trim().length()==0){
			if(BaseSecurityInit.getProperty("INIT_COUNTY").trim().length()>0){
				form.setCommConfigLocationId3(BaseSecurityInit.getProperty("INIT_COUNTY"));
			}
		}
		/**设置省居住地址市县的默认值结束*/
		if(form.getCensusLocationGroupId()==null||form.getCensusLocationGroupId().trim().length()==0)
			form.setCensusLocationGroupId(BaseSecurityInit.getProperty("INIT_REAL_COUNTY"));
		if(form.getCensusLocationGroupName()==null||form.getCensusLocationGroupName().trim().length()==0)
			form.setCensusLocationGroupName(commConfigCountryDAO.getItemName(BaseSecurityInit.getProperty("INIT_REAL_COUNTY")));
		/**设置户籍地址省市县的默认值开始*/
		if(form.getCensusLocationId1()==null||form.getCensusLocationId1().trim().length()==0){
			form.setCensusLocationId1(BaseSecurityInit.getProperty("INIT_PROVINCE"));
		}
		if(form.getCensusLocationId2()==null||form.getCensusLocationId2().trim().length()==0){
			form.setCensusLocationId2(BaseSecurityInit.getProperty("INIT_CITY"));
		}
		if(form.getCensusLocationId3()==null||form.getCensusLocationId3().trim().length()==0){
			if(BaseSecurityInit.getProperty("INIT_COUNTY").trim().length()>0){
				form.setCensusLocationId3(BaseSecurityInit.getProperty("INIT_COUNTY"));
			}
		}
		/**设置户籍地址省市县的默认值结束*/
		if(form.getBirthLocationGroupId()==null||form.getBirthLocationGroupId().trim().length()==0)
			form.setBirthLocationGroupId(BaseSecurityInit.getProperty("INIT_REAL_COUNTY"));
		if(form.getBirthLocationGroupName()==null||form.getBirthLocationGroupName().trim().length()==0)
			form.setBirthLocationGroupName(commConfigCountryDAO.getItemName(BaseSecurityInit.getProperty("INIT_REAL_COUNTY")));
		/**设置出生地址省市县的默认值开始*/
		if(form.getBirthLocationId1()==null||form.getBirthLocationId1().trim().length()==0){
			form.setBirthLocationId1(BaseSecurityInit.getProperty("INIT_PROVINCE"));
		}
		if(form.getBirthLocationId2()==null||form.getBirthLocationId2().trim().length()==0){
			form.setBirthLocationId2(BaseSecurityInit.getProperty("INIT_CITY"));
		}
		if(form.getBirthLocationId3()==null||form.getBirthLocationId3().trim().length()==0){
			if(BaseSecurityInit.getProperty("INIT_COUNTY").trim().length()>0){
				form.setBirthLocationId3(BaseSecurityInit.getProperty("INIT_COUNTY"));
			}
		}
		/**设置出生地址省市县的默认值结束*/
		init(form,id);
	}

	public void serchInit(HttpServletRequest request,SecurityUserBaseinfoForm form) {
		ServletContext application = request.getSession().getServletContext();
		if(application.getAttribute("empi.CARD_TYPE")!=null)
			form.setCardType((String)application.getAttribute("empi.CARD_TYPE"));
		if(application.getAttribute("empi.IDNO_TYPE")!=null)
			form.setCommConfigIdTypeId((String)application.getAttribute("empi.IDNO_TYPE"));
		searchInit(form);
	}

	public SecurityUserBaseinfo checkData(String id) {
		SecurityUserBaseinfo data = securityUserBaseinfoDAO.getByIdNo(id);
		return data;
	}

	public void getIdAddress(String countyCode,SecurityUserBaseinfoForm hosform){
			try {
				CommConfigLocation county = commConfigLocationDAO.findById(countyCode);
				CommConfigLocation city =null;
				CommConfigLocation province =null;
				if(county!=null){
						 hosform.setCommConfigLocationId3(county.getItemCode());//县级信息
						 hosform.setCommConfigLocationId3_name(county.getItemName());
					 
					 city = commConfigLocationDAO.findById(county.getParentId());
						 hosform.setCommConfigLocationId2(city.getItemCode());//市级
						 hosform.setCommConfigLocationId2_name(city.getItemName());
					 province = commConfigLocationDAO.findById(city.getParentId());
						 hosform.setCommConfigLocationId1(province.getItemCode());//省级
						 hosform.setCommConfigLocationId1_name(province.getItemName());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
	public void save(SecurityUserBaseinfoForm form,SessionForm sessionform) {
		SecurityUserBaseinfo data = new SecurityUserBaseinfo();
		this.setData(form, data,sessionform);
		securityUserBaseinfoDAO.save(data);
		//尽管用户信息录入过程中并没有录入密码，但是系統会默认给该用户一个密码，这些密码等信息存放在了除放用户信息的主表SECURITY_USER_BASEINFO外的SECURITY_CONFIG_USERS表中
		this.savePassword(form, data);
//		SecurityUserBaseinfo tempUserBaseinfo = securityUserBaseinfoDAO.getByName(data.getName());
		
		SecurityUserBaseinfo dataNew = securityUserBaseinfoDAO.getByItemCode(form.getPmi());
		if(dataNew != null && dataNew.getId() != null){
			form.setId(dataNew.getId());
		}
		//每次平台上村一条居民信息都存一条关联
		SecurityIHEPatientIdList idList = new SecurityIHEPatientIdList();
		idList.setAuthorityId("1.3.6.1.4.1.21367.2008.2");
		idList.setAuthorityName("平台域");
		idList.setFacilityId("1.3.6.1.4.1.23178.2012.2");
		idList.setFacilityName("平台机构");
		idList.setSecurityUserBaseinfoId(dataNew.getId());
		idList.setPid(dataNew.getId());//重复存的俩遍，平台ID再次的保存
		idList.setSecurityUserBaseinfoId(dataNew.getId());
		idList.setSeqNo(Long.valueOf(this.getSecurityUserBaseinfoDAO().getMaxSeqNo()));
	}
	public void savePassword(SecurityUserBaseinfoForm form,SecurityUserBaseinfo data) {
		// TODO Auto-generated method stub
			String passwd = "";
			if(form.getPassWord().equals("")){
				passwd = BaseSecurityInit.getProperty("USER_PASSWORD");
			}else{
				passwd = form.getPassWord();
			}
			String passwdMD5 = MD5.toMD5(passwd);
			SecurityConfigUsers sysUser = new SecurityConfigUsers();
			sysUser.setSecurityUserBaseinfoId(data.getId());
			sysUser.setPasswd(passwdMD5);
			this.securityUserBaseinfoDAO.save(sysUser);
	}

	public void updateInit(SecurityUserBaseinfoForm form,String id) {
		SecurityUserBaseinfo data = securityUserBaseinfoDAO.getById(form.getId());
		this.setForm(form, data);
		init(form,id);	
	}

	public void update(SecurityUserBaseinfoForm form,SessionForm sessionform) {
		SecurityUserBaseinfo data = securityUserBaseinfoDAO.getById(form.getId());
		//form.setPhotoPath(form.getPhotoPath());
		this.setData(form, data,sessionform);
		securityUserBaseinfoDAO.update(data);
	}

	public void showInit(SecurityUserBaseinfoForm form,String id) {
		SecurityUserBaseinfo data = securityUserBaseinfoDAO.getById(form.getId());
		this.setForm(form, data);
	}

	public void delete(SecurityUserBaseinfoForm form) {
		SecurityUserBaseinfo data = securityUserBaseinfoDAO.getById(form.getIdHidden());
		SecurityUserDeprecated deprecated = new SecurityUserDeprecated();
		this.setDeprcated(data, deprecated);
		this.securityUserBaseinfoDAO.save(deprecated);
		securityUserBaseinfoDAO.delete(data);
	}

	protected List<SecurityStaffBaseinfo> getNeedSecurityStaffBaseinfo(String id){
		String desc="";
		String value="";
		SecurityStaffBaseinfo sec=securityUserBaseinfoDAO.findSecurityStaffBaseinfoById(id);
		HspConfigBaseinfo hsp=null;
		if(sec!=null){
			hsp=this.hspConfigBaseinfoDAO.getById(sec.getHspConfigBaseinfoId());
		}
		if(hsp!=null){
			if(hsp.getHspConfigBaseinfoId1()!=null&&hsp.getHspConfigBaseinfoId1().trim().length()>0){
				if(hsp.getHspConfigBaseinfoId2()==null||hsp.getHspConfigBaseinfoId2().trim().length()==0){
					desc="hspConfigBaseinfoId1";
				}
			}
			if(hsp.getHspConfigBaseinfoId2()!=null&&hsp.getHspConfigBaseinfoId2().trim().length()>0){
				if(hsp.getHspConfigBaseinfoId3()==null||hsp.getHspConfigBaseinfoId3().trim().length()==0){
					desc="hspConfigBaseinfoId2";
				}
			}			
			if(hsp.getHspConfigBaseinfoId3()!=null&&hsp.getHspConfigBaseinfoId3().trim().length()>0){
				if(hsp.getHspConfigBaseinfoId4()==null||hsp.getHspConfigBaseinfoId4().trim().length()==0){
					desc="hspConfigBaseinfoId3";
				}
			}
			value=hsp.getId();
		}
		List<HspConfigBaseinfo> list1=securityUserBaseinfoDAO.findHosiptal(desc, value);
		List<SecurityStaffBaseinfo> lis=securityUserBaseinfoDAO.findSecurityStaffBaseinfo(list1);
		return lis;
	}
	//用于权限管理
	protected List<String> getColumnNameAndPlace(String staffId){
		List<String> li=new ArrayList<String>();
		SecurityDataObjectVsRoles nSecurityDataObjectVsRoles=securityUserBaseinfoDAO.findById(staffId);
		String columnName="";
		String place="";
		if(nSecurityDataObjectVsRoles!=null){
			SecurityDataObjectType nSecurityDataObjectType=securityUserBaseinfoDAO.getSecurityDataObjectTypeById(nSecurityDataObjectVsRoles.getSdotId());
			columnName=nSecurityDataObjectType.getColumnName();
			place=nSecurityDataObjectVsRoles.getSdoValue();
		}
		
		li.add(0, columnName);
		li.add(1,place);
		return li;
	}
	//用于权限管理
	protected List<String[]> getColumnNameAndPlace1(String staffId){
		List<String[]> li=new ArrayList<String[]>();
		List<?> list=securityUserBaseinfoDAO.findManyById(staffId);
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				SecurityDataObjectVsRoles s=(SecurityDataObjectVsRoles) list.get(i);
				SecurityDataObjectType nSecurityDataObjectType=securityUserBaseinfoDAO.getSecurityDataObjectTypeById(s.getSdotId());
				String[] many=new String[2];
				many[0]=nSecurityDataObjectType.getColumnName();
				many[1]=s.getSdoValue();
				li.add(many);
			}	
			return li;
		}
		return null;
	}
	public int getCountUser(String id, String name, String inputCode,String sexId,String idNo, String cardType,String cardNo ,String nameEn,String staffId,String xflag) {
		
		
		if(xflag==null || xflag.trim().length()<=0)
			return 0;
		List<String[]> list=this.getColumnNameAndPlace1(staffId);
		if(list!=null&&list.size()>0)
			return securityUserBaseinfoDAO.getCountUser(id, name, inputCode,sexId,idNo,cardType,cardNo, nameEn,list);
		else 
			return 0;
	}

	public void getSearch(SecurityUserBaseinfoForm form, int curCount, int quChuCount,String staffId) {

		String xflag=form.getXflag();
		if(xflag==null || xflag.trim().length()<=0)
			return ;
		String order = "";
		if (form.getOrderNo().equals("1")) {
			order = " a.pmi";
		} else if (form.getOrderNo().equals("2")) {
			order = " a.name";
		} else if (form.getOrderNo().equals("3")) {
			order = " a.nameEn";
		} else if (form.getOrderNo().equals("4")) {
			order = " a.idNo";
		} else if (form.getOrderNo().equals("5")) {
			order = " a.commConfigLocationId3";
		}else if (form.getOrderNo().equals("6")) {
			order = " a.commConfigLocationTownId";
		}else if (form.getOrderNo().equals("7")) {
			order = " a.commConfigLocationVillageid";
		} else {
			order = " a.pmi";
		}
		if (form.getAsc().equals("1")) {
			order += " desc";
		} else {
			order += " asc";
		}
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		List<String[]> li=this.getColumnNameAndPlace1(staffId);
		List<?> list=null;
		if(li!=null&&li.size()>0){
			list = securityUserBaseinfoDAO.getUser(form.getId()
					, form.getName()
					, form.getInputCode()
					, form.getCommConfigSexId()
					, form.getIdNo()
					, form.getCardType()
					, form.getCardNo()
					, form.getNameEn(), order, curCount, quChuCount,li);
		}
		if (list != null && list.size() > 0) {
			String[] ids = new String[list.size()];
			String[] cardNos=new String[list.size()];
			String[] pmis = new String[list.size()];
			String[] idNos = new String[list.size()];
			String[] names = new String[list.size()];
			String[] commConfigSexIds = new String[list.size()];
			String[] commConfigSexId_names = new String[list.size()];
			//String[] mailingAddresses=new String[list.size()];
			String[] xian_id=new String[list.size()];
			String[] xian_name=new String[list.size()];
			String[] xiang_id=new String[list.size()];
			String[] xiang_name=new String[list.size()];
			String[] zheng_id=new String[list.size()];
			String[] zheng_name=new String[list.size()];
			//String[] dateOfBirths = new String[list.size()];
			//String[] birthPlaces = new String[list.size()];
			String[] inputCodes = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				ids[i] = transNullToString(((Object[]) list.get(i))[0]);
				pmis[i] = transNullToString(((Object[]) list.get(i))[1]);
				idNos[i] = transNullToString(((Object[]) list.get(i))[11]);
				names[i] = transNullToString(((Object[]) list.get(i))[2]);
				commConfigSexIds[i] = transNullToString(((Object[]) list.get(i))[5]);
				commConfigSexId_names[i] = commConfigSexDAO.getItemName(commConfigSexIds[i]);
				xian_id[i] = transNullToString(((Object[]) list.get(i))[19]);
				xian_name[i] = transNullToString(commConfigLocationDAO.getItemName(xian_id[i]));
				xiang_id[i] = transNullToString(((Object[]) list.get(i))[20]);
				xiang_name[i] =transNullToString( commConfigTownDao.getItemName(xiang_id[i]));
				zheng_id[i] = transNullToString(((Object[]) list.get(i))[21]);
				zheng_name[i] =transNullToString( commConfigVillageDao.getItemName(zheng_id[i]));
				//mailingAddresses[i] = transNullToString(((Object[]) list.get(i))[20]);
				//Date date = (Date)((Object[]) list.get(i))[7];                	
            	//String dateString = "";
				//if(date != null){
					//dateString = format.format(date);
				//} 
				//dateOfBirths[i] = dateString;
				//birthPlaces[i] = transNullToString(((Object[]) list.get(i))[6]);
				inputCodes[i] = transNullToString(((Object[]) list.get(i))[4]);
			}
			form.setIdList(ids);
			form.setCardNoList(cardNos);
			form.setPmiList(pmis);
			form.setNameList(names);
			form.setCommConfigSexIdList(commConfigSexIds);
			form.setCommConfigSexNameList(commConfigSexId_names);
			form.setIdNoList(idNos);
			form.setCommconfigLocationId3List(xian_id);
			form.setCommconfigLocationId3_nameList(xian_name);
			form.setCommconfigLocationTownIdList(xiang_id);
			form.setCommconfigLocationTownId_nameList(xiang_name);
			form.setCommconfigLocationVillageIdList(zheng_id);
			form.setCommconfigLocationVillageId_nameList(zheng_name);
			//form.setMailingAddressList(mailingAddresses);
			//form.setDateOfBirthList(dateOfBirths);
			//form.setBirthPlaceList(birthPlaces);
			form.setInputCodeList(inputCodes);
//			//---------------------------------------------
//			private String[] idList;
//			 private String[] pmiList;
//			    private String[] nameList;
//			    private String[] commConfigSexIdList;
//			    private String[] commConfigSexNameList;
//			    private String[] dateOfBirthList;
//			    private String[] birthPlaceList;
//			    private String[] inputCodeList;
//			//---------------------------------------------
		}
	}

	private void setData(SecurityUserBaseinfoForm form, SecurityUserBaseinfo data,SessionForm sessionform) {
		try {
			data.setId(transNullToString(form.getId()));
			data.setPmi(transNullToString(form.getPmi()));
			data.setName(transNullToString(form.getName()));
			data.setNameEn(transNullToString(form.getNameEn()));
			data.setInputCode(transNullToString(form.getInputCode()));
			data.setCommConfigSexId(transNullToString(form.getCommConfigSexId()));
			data.setCommConfigSexName(transNullToString(form.getCommConfigSexId_name()));
//			data.setBirthPlace(transNullToString(form.getBirthPlace()));
			data.setCommConfigCountryId(transNullToString(form.getCommConfigCountryId()));
			data.setCommConfigCountryName(transNullToString(form.getCommConfigCountryId_name()));
			data.setCommConfigNationalityId(transNullToString(form.getCommConfigNationalityId()));
			data.setCommConfigNationalityName(transNullToString(form.getCommConfigNationalityId_name()));
			data.setCommConfigIdTypeId(transNullToString(form.getCommConfigIdTypeId()));
			data.setCommConfigIdTypeName(transNullToString(form.getCommConfigIdTypeId_name()));
			data.setIdNo(transNullToString(form.getIdNo()));
			data.setSscid(transNullToString(form.getSscid()));
			data.setCommConfigAboId(transNullToString(form.getCommConfigAboId()));
			data.setCommConfigAboName(transNullToString(form.getCommConfigAboId_name()));
			data.setCommConfigRhId(transNullToString(form.getCommConfigRhId()));
			data.setCommConfigRhName(transNullToString(form.getCommConfigRhId_name()));
			data.setCommConfigDegreeId(transNullToString(form.getCommConfigDegreeId()));
			data.setCommConfigDegreeName(transNullToString(form.getCommConfigDegreeId_name()));
			data.setCommConfigMaritalStatusId(transNullToString(form.getCommConfigMaritalStatusId()));
			data.setCcmsName(transNullToString(form.getCommConfigMaritalStatusId_name()));
			data.setDoorNo(this.transNullToString(form.getDoorNo()));
			data.setCensusDoorNo(this.transNullToString(form.getCensusDoorNo()));
			data.setBirthDoorNo(this.transNullToString(form.getBirthDoorNo()));
			/*SecurityDataObjectVsRoles nSecurityDataObjectVsRoles=securityUserBaseinfoDAO.findById(sessionform.getStaffId());
			HspConfigBaseinfo nHspConfigBaseinfo=hspConfigBaseinfoDAO.getById(sessionform.getStaffHospitalId());
			int sdotId=Integer.parseInt(nSecurityDataObjectVsRoles.getSdotId());
			switch(sdotId){
				case 1:
					if(form.getCommConfigLocationId1()==null||form.getCommConfigLocationId1().trim().length()==0){
						form.setCommConfigLocationId1(nSecurityDataObjectVsRoles.getSdoValue());
					}
					break;
				case 2:
					form.setCommConfigLocationId1(nHspConfigBaseinfo.getCommConfigLocationId1());
					if(form.getCommConfigLocationId2()==null||form.getCommConfigLocationId2().trim().length()==0){
						form.setCommConfigLocationId2(nSecurityDataObjectVsRoles.getSdoValue());
					}
					break;
				case 3:
					form.setCommConfigLocationId1(nHspConfigBaseinfo.getCommConfigLocationId1());
					form.setCommConfigLocationId2(nHspConfigBaseinfo.getCommConfigLocationId2());
					if(form.getCommConfigLocationId3()==null||form.getCommConfigLocationId3().trim().length()==0){
						form.setCommConfigLocationId3(nSecurityDataObjectVsRoles.getSdoValue());
					}
					break;
			}*/
			data.setCommConfigLocationId1(transNullToString(form.getCommConfigLocationId1()));
			data.setCommConfigLocationName1(transNullToString(form.getCommConfigLocationId1_name()));
			data.setCommConfigLocationId2(transNullToString(form.getCommConfigLocationId2()));
			data.setCommConfigLocationName2(transNullToString(form.getCommConfigLocationId2_name()));
			data.setCommConfigLocationId3(transNullToString(form.getCommConfigLocationId3()));
			data.setCommConfigLocationName3(transNullToString(form.getCommConfigLocationId3_name()));
			data.setCommConfigLocationTownId(transNullToString(form.getCommConfigLocationTownId()));
			data.setCcltName(transNullToString(form.getCommConfigLocationTownId_name()));
			data.setCommConfigLocationVillageid(transNullToString(form.getCommConfigLocationVillageId()));
			data.setCclvName(transNullToString(form.getCommConfigLocationVillageId_name()));
			data.setCommConfigLocationGroupId(transNullToString(form.getCommConfigLocationGroupId()));
			data.setCclgName(transNullToString(form.getCommConfigLocationGroupId_name()));
			//户籍地址
			data.setCensusLocationId1(this.transNullToString(form.getCensusLocationId1()));
			data.setCensusLocationName1(this.transNullToString(form.getCensusLocationName1()));
			data.setCensusLocationId2(this.transNullToString(form.getCensusLocationId2()));
			data.setCensusLocationName2(this.transNullToString(form.getCensusLocationName2()));
			data.setCensusLocationId3(this.transNullToString(form.getCensusLocationId3()));
			data.setCensusLocationName3(this.transNullToString(form.getCensusLocationName3()));
			data.setCensusLocationTownId(this.transNullToString(form.getCensusLocationTownId()));
			data.setCensusLocationTownName(this.transNullToString(form.getCensusLocationTownName()));
			data.setCensusLocationVillageId(this.transNullToString(form.getCensusLocationVillageId()));
			data.setCensusLocationVillageName(this.transNullToString(form.getCensusLocationVillageName()));
			data.setCensusLocationGroupId(this.transNullToString(form.getCensusLocationGroupId()));
			data.setCensusLocationGroupName(this.transNullToString(form.getCensusLocationGroupName()));
			//出生地址
			data.setBirthLocationId1(this.transNullToString(form.getBirthLocationId1()));
			data.setBirthLocationName1(this.transNullToString(form.getBirthLocationName1()));
			data.setBirthLocationId2(this.transNullToString(form.getBirthLocationId2()));
			data.setBirthLocationName2(this.transNullToString(form.getBirthLocationName2()));
			data.setBirthLocationId3(this.transNullToString(form.getBirthLocationId3()));
			data.setBirthLocationName3(this.transNullToString(form.getBirthLocationName3()));
			data.setBirthLocationTownId(this.transNullToString(form.getBirthLocationTownId()));
			data.setBirthLocationTownName(this.transNullToString(form.getBirthLocationTownName()));
			data.setBirthLocationVillageId(this.transNullToString(form.getBirthLocationVillageId()));
			data.setBirthLocationVillageName(this.transNullToString(form.getBirthLocationVillageName()));
			data.setBirthLocationGroupId(this.transNullToString(form.getBirthLocationGroupId()));
			data.setBirthLocationGroupName(this.transNullToString(form.getBirthLocationGroupName()));
			
//			data.setMailingAddress(transNullToString(form.getMailingAddress()));
			data.setZipcode(transNullToString(form.getZipcode()));
			data.setPhone(transNullToString(form.getPhone()));
			data.setEMail(transNullToString(form.getEMail()));
			data.setMobileTel(transNullToString(form.getMobileTel()));
			data.setPhotoPath(transNullToString(form.getPhotoPath()));
			data.setCommConfigRelationshipId(transNullToString(form.getCommConfigRelationshipId()));
			data.setCommConfigRelationshipName(transNullToString(form.getCommConfigRelationshipId_name()));
			data.setContactPersonName(transNullToString(form.getContactPersonName()));
			data.setContactPersonPhone(transNullToString(form.getContactPersonPhone()));
			data.setComments(transNullToString(form.getComments()));
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = form.getBirth_date_year() + "-" + form.getBirth_date_month() + "-" + form.getBirth_date_day();
			Date date = format.parse(dateString);
			data.setDateOfBirth(date);
			if(form.getVerbId()!=null&&form.getVerbId().equals("save")){
				data.setCreateUserId(form.getCreateUserId());
			}
			// data.setOpenBeds (Long.valueOf((form.getOpen_beds() == null || form.getOpen_beds().trim() .equals("")) ? "0" : form.getOpen_beds()));
			data.setDeceasedInd(transNullToString(form.getDeceasedInd()));
			data.setDeceasedIndName(transNullToString(form.getDeceasedIndName()));
			String dateS = form.getDeath_date_year() + "-" + form.getDeath_date_month() + "-" + form.getDeath_date_day();
			Date dateD = format.parse(dateS);
			data.setDeceasedTime(dateD);
			data.setMotherPID(transNullToString(form.getMotherId()));
			data.setMotherName(transNullToString(form.getMotherName()));
			data.setBirthSequence(transNullToString(form.getBirthSequence()));
			data.setMultipleBirthInd(transNullToString(form.getMultipleBirthind()));
			data.setMultipleBirthindName(transNullToString(form.getMultipleBirthindName()));
			data.setMultipleBirthOrderNumber(transNullToString(form.getMultipleBirthorderNo()));
			data.setOccupationCodeId(transNullToString(form.getOccupationCodeId()));
			data.setOccupationCodeName(transNullToString(form.getOccupationCodeName()));
		}
		catch (Exception e) {}
	}

	public String transNullToString(Object obj) {
		String temp = "";
		if (obj != null) {
			temp = String.valueOf(obj).trim();
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

	public void setForm(SecurityUserBaseinfoForm form, SecurityUserBaseinfo data) {
		try {
			form.setId(transNullToString(data.getId()));
			form.setPmi(transNullToString(data.getPmi()));
			form.setName(transNullToString(data.getName()));
			form.setNameEn(transNullToString(data.getNameEn()));
			form.setInputCode(transNullToString(data.getInputCode()));
			form.setCommConfigSexId(transNullToString(data.getCommConfigSexId()));
//			form.setBirthPlace(transNullToString(data.getBirthPlace()));
			form.setCommConfigCountryId(transNullToString(data.getCommConfigCountryId()));
			form.setCommConfigNationalityId(transNullToString(data.getCommConfigNationalityId()));
			form.setCommConfigIdTypeId(transNullToString(data.getCommConfigIdTypeId()));
			form.setIdNo(transNullToString(data.getIdNo()));
			form.setSscid(transNullToString(data.getSscid()));
			form.setCommConfigAboId(transNullToString(data.getCommConfigAboId()));
			form.setCommConfigRhId(transNullToString(data.getCommConfigRhId()));
			form.setCommConfigDegreeId(transNullToString(data.getCommConfigDegreeId()));
			form.setCommConfigMaritalStatusId(transNullToString(data.getCommConfigMaritalStatusId()));
			//居住地址
			form.setCommConfigLocationId1(transNullToString(data.getCommConfigLocationId1()));
			form.setCommConfigLocationId2(transNullToString(data.getCommConfigLocationId2()));
			form.setCommConfigLocationId3(transNullToString(data.getCommConfigLocationId3()));
			form.setCommConfigLocationTownId(transNullToString(data.getCommConfigLocationTownId()));
			form.setCommConfigLocationVillageId(transNullToString(data.getCommConfigLocationVillageid()));
			form.setCommConfigLocationGroupId(transNullToString(data.getCommConfigLocationGroupId()));
			//户籍地址
			form.setCensusLocationId1(this.transNullToString(data.getCensusLocationId1()));
			form.setCensusLocationId2(this.transNullToString(data.getCensusLocationId2()));
			form.setCensusLocationId3(this.transNullToString(data.getCensusLocationId3()));
			form.setCensusLocationTownId(this.transNullToString(data.getCensusLocationTownId()));
			form.setCensusLocationVillageId(this.transNullToString(data.getCensusLocationVillageId()));
			form.setCensusLocationGroupId(this.transNullToString(data.getCensusLocationGroupId()));
			//出生地址
			form.setBirthLocationId1(this.transNullToString(data.getBirthLocationId1()));
			form.setBirthLocationId2(this.transNullToString(data.getBirthLocationId2()));
			form.setBirthLocationId3(this.transNullToString(data.getBirthLocationId3()));
			form.setBirthLocationTownId(this.transNullToString(data.getBirthLocationTownId()));
			form.setBirthLocationVillageId(this.transNullToString(data.getBirthLocationVillageId()));
			form.setBirthLocationGroupId(this.transNullToString(data.getBirthLocationGroupId()));
			
			form.setDoorNo(this.transNullToString(data.getDoorNo()));
			form.setCensusDoorNo(this.transNullToString(data.getCensusDoorNo()));
			form.setBirthDoorNo(this.transNullToString(data.getBirthDoorNo()));
			
//			form.setMailingAddress(transNullToString(data.getMailingAddress()));
			form.setZipcode(transNullToString(data.getZipcode()));
			form.setPhone(transNullToString(data.getPhone()));
			form.setEMail(transNullToString(data.getEMail()));
			form.setPhotoPath(transNullToString(data.getPhotoPath()));
			form.setCommConfigRelationshipId(transNullToString(data.getCommConfigRelationshipId()));
			form.setContactPersonName(transNullToString(data.getContactPersonName()));
			form.setContactPersonPhone(transNullToString(data.getContactPersonPhone()));
			form.setComments(transNullToString(data.getComments()));
			form.setMobileTel(transNullToString(data.getMobileTel()));
			form.setCommConfigCountryId_name(transNullToString(data.getCommConfigCountryName()));
			form.setCommConfigNationalityId_name(transNullToString(data.getCommConfigNationalityName()));
			form.setCommConfigRelationshipId_name(transNullToString(data.getCommConfigRelationshipName()));
			//------------------处理日期----------------------------------------------------
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(data.getDateOfBirth());
			form.setBirth_date_year(String.valueOf(cal.get(Calendar.YEAR)));
			String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
			if (month.length() < 2) {
				month = "0" + month;
			}
			String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
			if (day.length() < 2) {
				day = "0" + day;
			}
			form.setBirth_date_month(month);
			form.setBirth_date_day(day);
			//---------------------------------------------------------------------
			// form.setOpen_beds (data.getOpenBeds() == null ? "0" : String.valueOf(data.getOpenBeds()));
			form.setDeceasedInd(transNullToString(data.getDeceasedInd()));
			form.setDeceasedIndName(transNullToString(data.getDeceasedIndName()));
			GregorianCalendar cal1 = new GregorianCalendar();
			if(data.getDeceasedTime()!=null){
				cal1.setTime(data.getDeceasedTime());
			}
			form.setDeath_date_year(String.valueOf(cal1.get(Calendar.YEAR)));
			String month1 = String.valueOf(cal1.get(Calendar.MONTH) + 1);
			if (month1.length() < 2) {
				month1 = "0" + month1;
			}
			String day1 = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
			if (day1.length() < 2) {
				day1 = "0" + day1;
			}
			form.setDeath_date_month(month1);
			form.setDeath_date_day(day1);
			form.setMotherId(transNullToString(data.getMotherPID()));
			form.setMotherName(transNullToString(data.getMotherName()));
			form.setBirthSequence(transNullToString(data.getBirthSequence()));
			form.setMultipleBirthind(transNullToString(data.getMultipleBirthInd()));
			form.setMultipleBirthindName(transNullToString(data.getMultipleBirthindName()));
			form.setMultipleBirthorderNo(transNullToString(data.getMultipleBirthOrderNumber()));
			
			
			form.setOccupationCodeId(transNullToString(data.getOccupationCodeId()));
			form.setOccupationCodeName(transNullToString(data.getOccupationCodeName()));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getProvinces(SecurityUserBaseinfoForm form) {
		//---------获得省列表----------------------------------------
		List<?> data = securityUserBaseinfoDAO.getByParent("", "1");
		String[] tempId = null;
		String[] tempName = null;
		if (data != null) {
			tempId = new String[data.size() + 1];
			tempName = new String[data.size() + 1];
			tempId[0] = "";
			tempName[0] = "";
			for (int i = 0; i < data.size(); i++) {
				CommConfigLocation district = (CommConfigLocation) data.get(i);
				tempId[i + 1] = district.getId();
				tempName[i + 1] = district.getItemName();
			}
		}
		form.setCommConfigLocationId1s(tempId);
		form.setCommConfigLocationId1_names(tempName);
	}

	public void getCitys(SecurityUserBaseinfoForm form) {
		//-------获得省对应的市列表--------------------------------------
		List<?> data = securityUserBaseinfoDAO.getByParent(form.getCommConfigLocationId1(), "2");
		String[] tempId = null;
		String[] tempName = null;
		if (data != null) {
			tempId = new String[data.size() + 1];
			tempName = new String[data.size() + 1];
			tempId[0] = "";
			tempName[0] = "";
			for (int i = 0; i < data.size(); i++) {
				CommConfigLocation district = (CommConfigLocation) data.get(i);
				tempId[i + 1] = district.getId();
				tempName[i + 1] = district.getItemName();
			}
		}
		form.setCommConfigLocationId2s(tempId);
		form.setCommConfigLocationId2_names(tempName);
	}

	public void getDistricts(SecurityUserBaseinfoForm form) {
		//--------获取市对应的县列表---------------------------------------------
		List<?> data = securityUserBaseinfoDAO.getByParent(form.getCommConfigLocationId2(), "3");
		String[] tempId = null;
		String[] tempName = null;
		if (data != null) {
			tempId = new String[data.size() + 1];
			tempName = new String[data.size() + 1];
			tempId[0] = "";
			tempName[0] = "";
			for (int i = 0; i < data.size(); i++) {
				CommConfigLocation district = (CommConfigLocation) data.get(i);
				tempId[i + 1] = district.getId();
				tempName[i + 1] = district.getItemName();
			}
		}
		form.setCommConfigLocationId3s(tempId);
		form.setCommConfigLocationId3_names(tempName);
	}

	/** 在初始化阶段将字典库传入form中 */
	private void init(SecurityUserBaseinfoForm form,String id) {
		//-----------------------------------------------------------------------------
		this.getDict(form,id);
		//--------所有字典都定义成数组-------
		 /**性别*/
		this.setIdNames("CommConfigCardtype", "itemCode", "itemName","");
    	this.setIdNames("CommConfigIdType", "itemCode", "itemName","");
    	form.setCommConfigIdTypeIds(this.getIds());
    	form.setCommConfigIdTypeId_names(this.getNames());
		form.setCommConfigSexIds(this.getCommConfigSexId());
		form.setCommConfigSexId_names(this.getCommConfigSexName());
//		/**国籍*/
//		form.setCommConfigCountryIds(this.getCommConfigCountryId());
//		form.setCommConfigCountryId_names(this.getCommConfigCountryName());
//		/**民族*/
//		form.setCommConfigNationalityIds(this.getCommConfigNationalityId());
//		form.setCommConfigNationalityId_names(this.getCommConfigNationalityName());
		/**卡类型*/
		form.setCardType_list(this.getCardType_list());
		form.setCardTypeName_list(this.getCardTypeName_list());
		/**yes字典*/
		form.setDeceasedInds(this.getDeceasedInds());
		form.setDeceasedIndNames(this.getDeceasedIndNames());
		/**职业类别*/
		form.setOccupationCodeIds(this.getOccupationCodeIds());
		form.setOccupationCodeNames(this.getOccupationCodeNames());
		/**身份证件类别*/
		form.setCommConfigIdTypeIds(this.getCommConfigIdTypeId());
		form.setCommConfigIdTypeId_names(this.getCommConfigIdTypeName());
		/**ABO血型*/
		form.setCommConfigAboIds(this.getCommConfigAboId());
		form.setCommConfigAboId_names(this.getCommConfigAboName());
		/**RH血型*/
		form.setCommConfigRhIds(this.getCommConfigRhId());
		form.setCommConfigRhId_names(this.getCommConfigRhName());
		/**文化程度*/
		form.setCommConfigDegreeIds(this.getCommConfigDegreeId());
		form.setCommConfigDegreeId_names(this.getCommConfigDegreeName());
		/**婚姻状况*/
		form.setCommConfigMaritalStatusIds(this.getCommConfigMaritalStatusId());
		form.setCommConfigMaritalStatusId_names(this.getCommConfigMaritalStatusName());
		//居住地址
		/**所属省*/
		form.setCommConfigLocationId1s(this.getCommConfigLocationId1());
		form.setCommConfigLocationId1_names(this.getCommConfigLocationName1());
		/**所属市*/
		form.setCommConfigLocationId2s(this.getCommConfigLocationId2());
		form.setCommConfigLocationId2_names(this.getCommConfigLocationName2());
		/**所属县*/
		form.setCommConfigLocationId3s(this.getCommConfigLocationId3());
		form.setCommConfigLocationId3_names(this.getCommConfigLocationName3());
		/**所属乡镇*/
		form.setCommConfigLocationTownIds(this.getCommConfigLocationTownId());
		form.setCommConfigLocationTownId_names(this.getCommConfigLocationTownName());
		/**所属村*/
		form.setCommConfigLocationVillageIds(this.getCommConfigLocationVillageId());
		form.setCommConfigLocationVillageId_names(this.getCommConfigLocationVillageName());
		/**所属组*/
		form.setCommConfigLocationGroupIds(this.getCommConfigLocationGroupId());
		form.setCommConfigLocationGroupId_names(this.getCommConfigLocationGroupName());
		//户籍地址
		/**所属省*/
		form.setCensusLocationId1s(this.getCensusLocationId1());
		form.setCensusLocationId1_names(this.getCensusLocationName1());
		/**所属市*/
		form.setCensusLocationId2s(this.getCensusLocationId2());
		form.setCensusLocationId2_names(this.getCensusLocationName2());
		/**所属县*/
		form.setCensusLocationId3s(this.getCensusLocationId3());
		form.setCensusLocationId3_names(this.getCensusLocationName3());
		/**所属乡镇*/
		form.setCensusLocationTownIds(this.getCensusLocationTownId());
		form.setCensusLocationTownId_names(this.getCensusLocationTownName());
		/**所属村*/
		form.setCensusLocationVillageIds(this.getCensusLocationVillageId());
		form.setCensusLocationVillageId_names(this.getCensusLocationVillageName());
		/**所属组*/
		form.setCensusLocationGroupIds(this.getCensusLocationGroupId());
		form.setCensusLocationGroupId_names(this.getCensusLocationGroupName());
		//出生地址
		/**所属省*/
		form.setBirthLocationId1s(this.getBirthLocationId1());
		form.setBirthLocationId1_names(this.getBirthLocationName1());
		/**所属市*/
		form.setBirthLocationId2s(this.getBirthLocationId2());
		form.setBirthLocationId2_names(this.getBirthLocationName2());
		/**所属县*/
		form.setBirthLocationId3s(this.getBirthLocationId3());
		form.setBirthLocationId3_names(this.getBirthLocationName3());
		/**所属乡镇*/
		form.setBirthLocationTownIds(this.getBirthLocationTownId());
		form.setBirthLocationTownId_names(this.getBirthLocationTownName());
		/**所属村*/
		form.setBirthLocationVillageIds(this.getBirthLocationVillageId());
		form.setBirthLocationVillageId_names(this.getBirthLocationVillageName());
		/**所属组*/
		form.setBirthLocationGroupIds(this.getBirthLocationGroupId());
		form.setBirthLocationGroupId_names(this.getBirthLocationGroupName());
		
		/**联系人关系*/
		form.setCommConfigRelationshipIds(this.getCommConfigRelationshipId());
		form.setCommConfigRelationshipId_names(this.getCommConfigRelationshipName());
		//-------------------------------------------------
		form.setYears(this.getYear());
		form.setMonths(this.getMonth());
		form.setDays(this.getDay());

	}

	public void getDetail(SecurityUserBaseinfoForm form) {
		form.setCommConfigSexId_name(commConfigSexDAO.getItemName( form.getCommConfigSexId()));
		form.setCommConfigCountryId_name(commConfigCountryDAO.getItemName(form.getCommConfigCountryId()));
		form.setCommConfigNationalityId_name(commConfigNationalityDAO.getItemName(form.getCommConfigNationalityId()));
		form.setCommConfigIdTypeId_name(commConfigIdTypeDAO.getItemName(form.getCommConfigIdTypeId()));
		form.setCommConfigAboId_name(commConfigAboDAO.getItemName(form.getCommConfigAboId()));
		form.setCommConfigRhId_name(commConfigRhDAO.getItemName(form.getCommConfigRhId()));
		form.setOccupationCodeName(this.getCommConfigVocationDAO().getNameById(form.getOccupationCodeId()));
		form.setDeceasedIndName(this.getCommConfigYesDAO().getNameById(form.getDeceasedInd()));
		form.setMultipleBirthindName(this.getCommConfigYesDAO().getNameById(form.getMultipleBirthind()));
		List<?> list=commonDAO.findListByHql("from CommDictPublicChar a where a.classCode='CIHA200910_0073' and a.dictCode=? ", new String[]{form.getCommConfigDegreeId()});
		if(list!=null&&list.size()>0){
			CommDictPublicChar nc=(CommDictPublicChar) list.get(0);
			form.setCommConfigDegreeId_name(nc.getDictValue());
		}else{
			form.setCommConfigDegreeId_name("");
		}
		form.setCommConfigMaritalStatusId_name(commConfigMaritalStatusDAO.getItemName(form.getCommConfigMaritalStatusId()));
		//居住地
		form.setCommConfigLocationId1_name(commConfigLocationDAO.getItemName(form.getCommConfigLocationId1()));
		form.setCommConfigLocationId2_name(commConfigLocationDAO.getItemName(form.getCommConfigLocationId2()));
		form.setCommConfigLocationId3_name(commConfigLocationDAO.getItemName(form.getCommConfigLocationId3()));
		form.setCommConfigLocationTownId_name(commConfigTownDao.getItemName(form.getCommConfigLocationTownId()));
		form.setCommConfigLocationVillageId_name(commConfigVillageDao.getItemName(form.getCommConfigLocationVillageId()));
		CommConfigLocationGroup nCommConfigLocationGroup=commConfigGroupDao.findByItemCode(form.getCommConfigLocationGroupId());
		if(nCommConfigLocationGroup!=null){
			form.setCommConfigLocationGroupId_name(nCommConfigLocationGroup.getItemName());
		}
		//户籍地址
		form.setCensusLocationName1(commConfigLocationDAO.getItemName(form.getCensusLocationId1()));
		form.setCensusLocationName2(commConfigLocationDAO.getItemName(form.getCensusLocationId2()));
		form.setCensusLocationName3(commConfigLocationDAO.getItemName(form.getCensusLocationId3()));
		form.setCensusLocationTownName(commConfigTownDao.getItemName(form.getCensusLocationTownId()));
		form.setCensusLocationVillageName(commConfigVillageDao.getItemName(form.getCensusLocationVillageId()));
		CommConfigLocationGroup nCensusLocationGroup=commConfigGroupDao.findByItemCode(form.getCensusLocationGroupId());
		if(nCensusLocationGroup!=null){
			form.setCensusLocationGroupName(nCensusLocationGroup.getItemName());
		}
		//出生地址
		form.setBirthLocationName1(commConfigLocationDAO.getItemName(form.getBirthLocationId1()));
		form.setBirthLocationName2(commConfigLocationDAO.getItemName(form.getBirthLocationId2()));
		form.setBirthLocationName3(commConfigLocationDAO.getItemName(form.getBirthLocationId3()));
		form.setBirthLocationTownName(commConfigTownDao.getItemName(form.getBirthLocationTownId()));
		form.setBirthLocationVillageName(commConfigVillageDao.getItemName(form.getBirthLocationVillageId()));
		CommConfigLocationGroup nBirthLocationGroup=commConfigGroupDao.findByItemCode(form.getBirthLocationGroupId());
		if(nBirthLocationGroup!=null){
			form.setBirthLocationGroupName(nBirthLocationGroup.getItemName());
		}
		form.setCommConfigRelationshipId_name(commConfigRelationshipDAO.getItemName(form.getCommConfigRelationshipId()));
	}
	public void getSomeDetail(SecurityUserBaseinfoForm form) {
		form.setCommConfigSexId_name(commConfigSexDAO.getItemName( form.getCommConfigSexId()));
		form.setCommConfigIdTypeId_name(commConfigIdTypeDAO.getItemName(form.getCommConfigIdTypeId()));
		form.setCommConfigAboId_name(commConfigAboDAO.getItemName(form.getCommConfigAboId()));
		form.setCommConfigRhId_name(commConfigRhDAO.getItemName(form.getCommConfigRhId()));
		form.setCommConfigDegreeId_name(commConfigDegreeDAO.getItemName(form.getCommConfigDegreeId()));
		form.setCommConfigMaritalStatusId_name(commConfigMaritalStatusDAO.getItemName(form.getCommConfigMaritalStatusId()));
		//居住地址
		form.setCommConfigLocationId1_name(commConfigLocationDAO.getItemName(form.getCommConfigLocationId1()));
		form.setCommConfigLocationId2_name(commConfigLocationDAO.getItemName(form.getCommConfigLocationId2()));
		form.setCommConfigLocationId3_name(commConfigLocationDAO.getItemName(form.getCommConfigLocationId3()));
		form.setCommConfigLocationTownId_name(commConfigTownDao.getItemName(form.getCommConfigLocationTownId()));
		form.setCommConfigLocationVillageId_name(commConfigVillageDao.getItemName(form.getCommConfigLocationVillageId()));
		CommConfigLocationGroup nCommConfigLocationGroup=commConfigGroupDao.findByItemCode(form.getCommConfigLocationGroupId());
		if(nCommConfigLocationGroup!=null){
			form.setCommConfigLocationGroupId_name(nCommConfigLocationGroup.getItemName());
		}
		//户籍地址
		form.setCensusLocationName1(commConfigLocationDAO.getItemName(form.getCensusLocationId1()));
		form.setCensusLocationName2(commConfigLocationDAO.getItemName(form.getCensusLocationId2()));
		form.setCensusLocationName3(commConfigLocationDAO.getItemName(form.getCensusLocationId3()));
		form.setCensusLocationTownName(commConfigTownDao.getItemName(form.getCensusLocationTownName()));
		form.setCensusLocationVillageName(commConfigVillageDao.getItemName(form.getCensusLocationVillageName()));
		CommConfigLocationGroup nCensusLocationGroup=commConfigGroupDao.findByItemCode(form.getCensusLocationGroupId());
		if(nCensusLocationGroup!=null){
			form.setCensusLocationGroupName(nCensusLocationGroup.getItemName());
		}
		//出生地址
		form.setBirthLocationName1(commConfigLocationDAO.getItemName(form.getBirthLocationId1()));
		form.setBirthLocationName2(commConfigLocationDAO.getItemName(form.getBirthLocationId2()));
		form.setBirthLocationName3(commConfigLocationDAO.getItemName(form.getBirthLocationId3()));
		form.setBirthLocationTownName(commConfigTownDao.getItemName(form.getBirthLocationTownId()));
		form.setBirthLocationVillageName(commConfigTownDao.getItemName(form.getBirthLocationVillageId()));
		CommConfigLocationGroup nBirthLocationGroup=commConfigGroupDao.findByItemCode(form.getBirthLocationGroupId());
		if(nBirthLocationGroup!=null){
			form.setBirthLocationGroupName(nBirthLocationGroup.getItemName());
		}
		
		form.setCommConfigRelationshipId_name(commConfigRelationshipDAO.getItemName(form.getCommConfigRelationshipId()));
	}
	private String[] ids;  
	
    private String[] names;

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String[] getNames() {
		return names;
	}

	public void setNames(String[] names) {
		this.names = names;
	}
	/** 在进行查找之前将字典库传入form中 */
	public void searchInit(SecurityUserBaseinfoForm form) {
		
    	//form.setHspConfigBaseinfoIds(this.getIds());
    	//form.setHspConfigBaseinfoNames(this.getNames());
    	
		//------------------------yes字典----------------------------------------------------
	
//		this.getDict("A0026");
//		form.setHospital_class_codeIds(this.getDictId());
//		form.setHospital_class_codeNames(this.getDictName());
//		this.getDict("A0027");
//		form.setGrade_codeIds(this.getDictId());
//		form.setGrade_codeNames(this.getDictName());
	}

	/** 根据字典类别获取字典详细信息列表 */
	public void getDict(SecurityUserBaseinfoForm form,String id) {
		List<?> commConfigSex_data = commConfigSexDAO.findAll();
		List<?> commConfigCountry_data = commConfigCountryDAO.findAll();
		List<?> commConfigNationality_data = commConfigNationalityDAO.findAll();
		List<?> commConfigIdTypeId_data = commConfigIdTypeDAO.findAll();
		List<?> commConfigAbo_data = commConfigAboDAO.findAll();
		List<?> commConfigRh_data = commConfigRhDAO.findAll();
		List<?> commConfigDegree_data = commonDAO.findListInTable("CommDictPublicChar", "classCode", "CIHA200910_0073");
		List<?> commConfigMaritalStatus_data =commConfigMaritalStatusDAO.findAll();
		List<?> commConfigVocation_data = commConfigVocationDAO.findAll();
		List<?> commConfigYes_data = commConfigYesDAO.findAll();
		List<?> commConfigCardType_data = this.getCommConfigCardtypeDAO().findAll();
		//SecurityDataObjectVsRoles se=securityUserBaseinfoDAO.findById(id);
		SecurityStaffBaseinfo nSecurityStaffBaseinfo = securityUserBaseinfoDAO.findSecurityStaffBaseinfoById(id);
		String hspId="";
		if(nSecurityStaffBaseinfo!=null){
			hspId = nSecurityStaffBaseinfo.getHspConfigBaseinfoId();
		}
		HspConfigBaseinfo nHspConfigBaseinfo=hspConfigBaseinfoDAO.getById(hspId);
		List<?> commConfigLocationId1_data = null;
		List<?> commConfigLocationId2_data = null;
		List<?> commConfigLocationId3_data = null;
		List<?> commConfigLocationTownId_data = null;
		List<?> commConfigLocationVillageId_data = null;
		List<?> commConfigLocationGroupId_data = null;
		
		List<?> censusLocationId1_data = null;
		List<?> censusLocationId2_data = null;
		List<?> censusLocationId3_data = null;
		List<?> censusLocationTownId_data = null;
		List<?> censusLocationVillageId_data = null;
		List<?> censusLocationGroupId_data = null;
		
		List<?> birthLocationId1_data = null;
		List<?> birthLocationId2_data = null;
		List<?> birthLocationId3_data = null;
		List<?> birthLocationTownId_data = null;
		List<?> birthLocationVillageId_data = null;
		List<?> birthLocationGroupId_data = null;
//		System.out.println(se.getSdotId());
//		System.out.println(se.getSdoValue());
		
		
		commConfigLocationId1_data = securityUserBaseinfoDAO.getByParent("","1");
		//居住地址
		if(form.getCommConfigLocationId1() != null && form.getCommConfigLocationId1().trim().length() > 0){
			//commConfigLocationId1_data=securityUserBaseinfoDAO.findByItemCode(form.getCommConfigLocationId1());
			commConfigLocationId2_data = securityUserBaseinfoDAO.getByParent(form.getCommConfigLocationId1(),"2");
		}
		if(form.getCommConfigLocationId2() != null && form.getCommConfigLocationId2().trim().length() > 0){
			commConfigLocationId3_data = securityUserBaseinfoDAO.getByParent(form.getCommConfigLocationId2(),"3");
		}
		if(form.getCommConfigLocationId3()!=null&&form.getCommConfigLocationId3().trim().length()>0){
			commConfigLocationTownId_data=securityUserBaseinfoDAO.getTownsByParent(form.getCommConfigLocationId3());
		}
		if(form.getCommConfigLocationTownId()!=null&&form.getCommConfigLocationTownId().trim().length()>0){
			commConfigLocationVillageId_data=securityUserBaseinfoDAO.getVillagesByParent(form.getCommConfigLocationTownId());
		}
		if(form.getCommConfigLocationVillageId()!=null&&form.getCommConfigLocationVillageId().trim().length()>0){
			commConfigLocationGroupId_data=securityUserBaseinfoDAO.getGroupsByParent(form.getCommConfigLocationVillageId());
		}
		//户籍地
		censusLocationId1_data = securityUserBaseinfoDAO.getByParent("","1");
		if(form.getCensusLocationId1() != null && form.getCensusLocationId1().trim().length() > 0){
			//commConfigLocationId1_data=securityUserBaseinfoDAO.findByItemCode(form.getCommConfigLocationId1());
			censusLocationId2_data = securityUserBaseinfoDAO.getByParent(form.getCensusLocationId1(),"2");
		}
		if(form.getCensusLocationId2() != null && form.getCensusLocationId2().trim().length() > 0){
			censusLocationId3_data = securityUserBaseinfoDAO.getByParent(form.getCensusLocationId2(),"3");
		}
		if(form.getCensusLocationId3()!=null&&form.getCensusLocationId3().trim().length()>0){
			censusLocationTownId_data=securityUserBaseinfoDAO.getTownsByParent(form.getCensusLocationId3());
		}
		if(form.getCensusLocationTownId()!=null&&form.getCensusLocationTownId().trim().length()>0){
			censusLocationVillageId_data=securityUserBaseinfoDAO.getVillagesByParent(form.getCensusLocationTownId());
		}
		if(form.getCensusLocationVillageId()!=null&&form.getCensusLocationVillageId().trim().length()>0){
			censusLocationGroupId_data=securityUserBaseinfoDAO.getGroupsByParent(form.getCensusLocationVillageId());
		}
		//出生地址
		birthLocationId1_data = securityUserBaseinfoDAO.getByParent("","1");
		if(form.getBirthLocationId1() != null && form.getBirthLocationId1().trim().length() > 0){
			//commConfigLocationId1_data=securityUserBaseinfoDAO.findByItemCode(form.getCommConfigLocationId1());
			birthLocationId2_data = securityUserBaseinfoDAO.getByParent(form.getBirthLocationId1(),"2");
		}
		if(form.getBirthLocationId2() != null && form.getBirthLocationId2().trim().length() > 0){
			birthLocationId3_data = securityUserBaseinfoDAO.getByParent(form.getBirthLocationId2(),"3");
		}
		if(form.getBirthLocationId3()!=null&&form.getBirthLocationId3().trim().length()>0){
			birthLocationTownId_data=securityUserBaseinfoDAO.getTownsByParent(form.getBirthLocationId3());
		}
		if(form.getBirthLocationTownId()!=null&&form.getBirthLocationTownId().trim().length()>0){
			birthLocationVillageId_data=securityUserBaseinfoDAO.getVillagesByParent(form.getBirthLocationTownId());
		}
		if(form.getBirthLocationVillageId()!=null&&form.getBirthLocationVillageId().trim().length()>0){
			birthLocationGroupId_data=securityUserBaseinfoDAO.getGroupsByParent(form.getBirthLocationVillageId());
		}
		
		
		if(form.getVerbId().trim().equals("addInit")){
			if(nHspConfigBaseinfo!=null){
			/**	
			if(transNullToString(nHspConfigBaseinfo.getCommConfigLocationId1()).length()>0){
				commConfigLocationId1_data = securityUserBaseinfoDAO.findByItemCode(nHspConfigBaseinfo.getCommConfigLocationId1());
			}
			if(transNullToString(nHspConfigBaseinfo.getCommConfigLocationId2()).length()>0){
				//commConfigLocationId1_data = securityUserBaseinfoDAO.findByItemCode(nHspConfigBaseinfo.getCommConfigLocationId1());
				commConfigLocationId2_data = securityUserBaseinfoDAO.findByItemCode(nHspConfigBaseinfo.getCommConfigLocationId2());
			}
			if(transNullToString(nHspConfigBaseinfo.getCommConfigLocationId3()).length()>0){
				//commConfigLocationId1_data = securityUserBaseinfoDAO.findByItemCode(nHspConfigBaseinfo.getCommConfigLocationId1());
				//commConfigLocationId2_data = securityUserBaseinfoDAO.findByItemCode(nHspConfigBaseinfo.getCommConfigLocationId2());
				commConfigLocationId3_data = securityUserBaseinfoDAO.findByItemCode(nHspConfigBaseinfo.getCommConfigLocationId3());
			}
			if(transNullToString(nHspConfigBaseinfo.getCommConfigLocationTownId()).length()>0){
				//commConfigLocationId1_data = securityUserBaseinfoDAO.findByItemCode(nHspConfigBaseinfo.getCommConfigLocationId1());
				//commConfigLocationId2_data = securityUserBaseinfoDAO.findByItemCode(nHspConfigBaseinfo.getCommConfigLocationId2());
				commConfigLocationTownId_data = securityUserBaseinfoDAO.findByItemCode(nHspConfigBaseinfo.getCommConfigLocationTownId());
			}
			if(transNullToString(nHspConfigBaseinfo.getCommClvId()).length()>0){
				commConfigLocationVillageId_data = securityUserBaseinfoDAO.findByItemCode(nHspConfigBaseinfo.getCommClvId());
			}
			/*if(se.getSdotId().equals("4")){
				commConfigLocationId1_data = securityUserBaseinfoDAO.findByItemCode(nHspConfigBaseinfo.getCommConfigLocationId1());
				commConfigLocationId2_data = securityUserBaseinfoDAO.findByItemCode(nHspConfigBaseinfo.getCommConfigLocationId2());
				commConfigLocationId3_data=securityUserBaseinfoDAO.findByItemCode(nHspConfigBaseinfo.getCommConfigLocationId3());
				commConfigLocationTownId_data=securityUserBaseinfoDAO.findByItemCode1(se.getSdoValue());
				if(commConfigLocationId3_data==null||commConfigLocationId3_data.size()==0){
					if(commConfigLocationTownId_data!=null&&commConfigLocationTownId_data.size()>0){
						CommConfigLocationTown nCommConfigLocationTown=(CommConfigLocationTown) commConfigLocationTownId_data.get(0);
						commConfigLocationId3_data=securityUserBaseinfoDAO.findByItemCode(nCommConfigLocationTown.getCommConfigLocationId());
					}
				}
			}
			if(se.getSdotId().equals("5")){
				commConfigLocationId1_data = securityUserBaseinfoDAO.findByItemCode(nHspConfigBaseinfo.getCommConfigLocationId1());
				commConfigLocationId2_data = securityUserBaseinfoDAO.findByItemCode(nHspConfigBaseinfo.getCommConfigLocationId2());
				commConfigLocationId3_data=securityUserBaseinfoDAO.findByItemCode(nHspConfigBaseinfo.getCommConfigLocationId3());
				commConfigLocationTownId_data=securityUserBaseinfoDAO.findByItemCode1(nHspConfigBaseinfo.getCommConfigLocationTownId());
				commConfigLocationVillageId_data=securityUserBaseinfoDAO.findByItemCode2(se.getSdoValue());
				if(commConfigLocationTownId_data==null||commConfigLocationTownId_data.size()==0){
					if(commConfigLocationVillageId_data!=null&&commConfigLocationVillageId_data.size()>0){
						CommConfigLocationVillage nCommConfigLocationVillage=(CommConfigLocationVillage) commConfigLocationVillageId_data.get(0);
						commConfigLocationTownId_data=securityUserBaseinfoDAO.findByItemCode1(nCommConfigLocationVillage.getCommCltId());
					}
				}
				if(commConfigLocationId3_data==null||commConfigLocationId3_data.size()==0){
					if(commConfigLocationTownId_data!=null&&commConfigLocationTownId_data.size()>0){
						CommConfigLocationTown nCommConfigLocationTown=(CommConfigLocationTown) commConfigLocationTownId_data.get(0);
						commConfigLocationId3_data=securityUserBaseinfoDAO.findByItemCode(nCommConfigLocationTown.getCommConfigLocationId());
					}
				}
			}*/
		}}else{
			commConfigLocationId1_data=securityUserBaseinfoDAO.getItemName("CommConfigLocation", "levelFlag", "1");
		}
		List<?> commConfigRelationship_data = commConfigRelationshipDAO.findAll();

		//------------------------职业类别----------------------------------------------------
		String[] tempId = null;
		String[] tempName = null;
		if (commConfigVocation_data != null) {
			tempId = new String[commConfigVocation_data.size() + 1];
			tempName = new String[commConfigVocation_data.size() + 1];
			tempId[0] = "";
			tempName[0] = "";
			for (int i = 0; i < commConfigVocation_data.size(); i++) {
				CommConfigVocation vocation = (CommConfigVocation)commConfigVocation_data.get(i);
				tempId[i + 1] = this.transNullToString(vocation.getItemCode());
				tempName[i + 1] =this.transNullToString(vocation.getItemName());
			}
		}
		this.setOccupationCodeIds(tempId);
		this.setOccupationCodeNames(tempName);
		//------------------------yes字典----------------------------------------------------
		tempId = null;
		tempName = null;
		if (commConfigYes_data != null) {
			tempId = new String[commConfigYes_data.size() + 1];
			tempName = new String[commConfigYes_data.size() + 1];
			tempId[0] = "";
			tempName[0] = "";
			for (int i = 0; i < commConfigYes_data.size(); i++) {
				CommConfigYes vocation = (CommConfigYes)commConfigYes_data.get(i);
				tempId[i + 1] = this.transNullToString(vocation.getItemCode());
				tempName[i + 1] =this.transNullToString(vocation.getItemName());
			}
		}
		this.setDeceasedInds(tempId);
		this.setDeceasedIndNames(tempName);
		//------------------------卡类别----------------------------------------------------
		tempId = null;
		tempName = null;
		if (commConfigCardType_data != null) {
			tempId = new String[commConfigCardType_data.size() + 1];
			tempName = new String[commConfigCardType_data.size() + 1];
			tempId[0] = "";
			tempName[0] = "";
			for (int i = 0; i < commConfigCardType_data.size(); i++) {
				CommConfigCardtype cardType = (CommConfigCardtype)commConfigCardType_data.get(i);
				tempId[i + 1] = this.transNullToString(cardType.getItemCode());
				tempName[i + 1] =this.transNullToString(cardType.getItemName());
			}
		}
		this.setCardType_list(tempId);
		this.setCardTypeName_list(tempName);
		//------------------------性别----------------------------------------------------
		tempId = null;
		tempName = null;
		if (commConfigSex_data != null) {
			tempId = new String[commConfigSex_data.size() + 1];
			tempName = new String[commConfigSex_data.size() + 1];
			tempId[0] = "";
			tempName[0] = "";
			for (int i = 0; i < commConfigSex_data.size(); i++) {
				CommConfigSex sex = (CommConfigSex)commConfigSex_data.get(i);
				tempId[i + 1] = this.transNullToString(sex.getItemCode());
				tempName[i + 1] =this.transNullToString(sex.getItemName());
			}
		}
		this.setCommConfigSexId(tempId);
		this.setCommConfigSexName(tempName);
		//------------------------国籍-----------------------------------------------------
		 tempId = null;
		 tempName = null;
		if (commConfigCountry_data != null) {
			tempId = new String[commConfigCountry_data.size() + 1];
			tempName = new String[commConfigCountry_data.size() + 1];
			tempId[0] = "";
			tempName[0] = "";
			for (int i = 0; i < commConfigCountry_data.size(); i++) {
				CommConfigCountry data = (CommConfigCountry)commConfigCountry_data.get(i);
				tempId[i + 1] = this.transNullToString(data.getItemCode());
				tempName[i + 1] =this.transNullToString(data.getItemName());
			}
		}
		this.setCommConfigCountryId(tempId);
		this.setCommConfigCountryName(tempName);
	//------------------------民族-----------------------------------------------------
		 tempId = null;
		 tempName = null;
		if (commConfigNationality_data != null) {
			tempId = new String[commConfigNationality_data.size() + 1];
			tempName = new String[commConfigNationality_data.size() + 1];
			tempId[0] = "";
			tempName[0] = "";
			for (int i = 0; i < commConfigNationality_data.size(); i++) {
				CommConfigNationality data = (CommConfigNationality)commConfigNationality_data.get(i);
				tempId[i + 1] = this.transNullToString(data.getItemCode());
				tempName[i + 1] =this.transNullToString(data.getItemName());
			}
		}
		this.setCommConfigNationalityId(tempId);
		this.setCommConfigNationalityName(tempName);
	
	//------------------------身份证件类别-----------------------------------------------------
	 tempId = null;
	 tempName = null;
	if (commConfigIdTypeId_data != null) {
		tempId = new String[commConfigIdTypeId_data.size() + 1];
		tempName = new String[commConfigIdTypeId_data.size() + 1];
		tempId[0] = "";
		tempName[0] = "";
		for (int i = 0; i < commConfigIdTypeId_data.size(); i++) {
			CommConfigIdType data = (CommConfigIdType)commConfigIdTypeId_data.get(i);
			tempId[i + 1] = this.transNullToString(data.getItemCode());
			tempName[i + 1] =this.transNullToString(data.getItemName());
		}
	}
	this.setCommConfigIdTypeId(tempId);
	this.setCommConfigIdTypeName(tempName);
	//------------------------ABO血型-----------------------------------------------------
	 tempId = null;
	 tempName = null;
	if (commConfigAbo_data != null) {
		tempId = new String[commConfigAbo_data.size() + 1];
		tempName = new String[commConfigAbo_data.size() + 1];
		tempId[0] = "";
		tempName[0] = "";
		for (int i = 0; i < commConfigAbo_data.size(); i++) {
			CommConfigAbo data = (CommConfigAbo)commConfigAbo_data.get(i);
			tempId[i + 1] = this.transNullToString(data.getItemCode());
			tempName[i + 1] =this.transNullToString(data.getItemName());
		}
	}
	this.setCommConfigAboId(tempId);
	this.setCommConfigAboName(tempName);
	//-----------------------RH血型----------------------------------------------------
	 tempId = null;
	 tempName = null;
	if (commConfigRh_data != null) {
		tempId = new String[commConfigRh_data.size() + 1];
		tempName = new String[commConfigRh_data.size() + 1];
		tempId[0] = "";
		tempName[0] = "";
		for (int i = 0; i < commConfigRh_data.size(); i++) {
			CommConfigRh data = (CommConfigRh)commConfigRh_data.get(i);
			tempId[i + 1] = this.transNullToString(data.getItemCode());
			tempName[i + 1] =this.transNullToString(data.getItemName());
		}
	}
	this.setCommConfigRhId(tempId);
	this.setCommConfigRhName(tempName);
	//-----------------------文化程度----------------------------------------------------
	 tempId = null;
	 tempName = null;
	if (commConfigDegree_data != null) {
		tempId = new String[commConfigDegree_data.size() + 1];
		tempName = new String[commConfigDegree_data.size() + 1];
		tempId[0] = "";
		tempName[0] = "";
		for (int i = 0; i < commConfigDegree_data.size(); i++) {
			CommDictPublicChar data = (CommDictPublicChar)commConfigDegree_data.get(i);
			tempId[i + 1] = this.transNullToString(data.getDictCode());
			tempName[i + 1] =this.transNullToString(data.getDictValue());
		}
	}
	this.setCommConfigDegreeId(tempId);
	this.setCommConfigDegreeName(tempName);
	//-----------------------婚姻状况----------------------------------------------------
	 tempId = null;
	 tempName = null;
	if (commConfigMaritalStatus_data != null) {
		tempId = new String[commConfigMaritalStatus_data.size() + 1];
		tempName = new String[commConfigMaritalStatus_data.size() + 1];
		tempId[0] = "";
		tempName[0] = "";
		for (int i = 0; i < commConfigMaritalStatus_data.size(); i++) {
			CommConfigMaritalStatus data = (CommConfigMaritalStatus)commConfigMaritalStatus_data.get(i);
			tempId[i + 1] = this.transNullToString(data.getItemCode());
			tempName[i + 1] =this.transNullToString(data.getItemName());
		}
	}
	this.setCommConfigMaritalStatusId(tempId);
	this.setCommConfigMaritalStatusName(tempName);
	//----------------------联系人关系----------------------------------------------------
	 tempId = null;
	 tempName = null;
	if (commConfigRelationship_data != null) {
		tempId = new String[commConfigRelationship_data.size() + 1];
		tempName = new String[commConfigRelationship_data.size() + 1];
		tempId[0] = "";
		tempName[0] = "";
		for (int i = 0; i < commConfigRelationship_data.size(); i++) {
			CommConfigRelationship data = (CommConfigRelationship)commConfigRelationship_data.get(i);
			tempId[i + 1] = this.transNullToString(data.getItemCode());
			tempName[i + 1] =this.transNullToString(data.getItemName());
		}
	}
	this.setCommConfigRelationshipId(tempId);
	this.setCommConfigRelationshipName(tempName);
	//----------------------省----------------------------------------------------
	 tempId = null;
	 tempName = null;
	if (commConfigLocationId1_data != null) {
		if(commConfigLocationId1_data.size()==1){
			tempId = new String[commConfigLocationId1_data.size() ];
			tempName = new String[commConfigLocationId1_data.size() ];
			for (int i = 0; i < commConfigLocationId1_data.size(); i++) {
				CommConfigLocation data = (CommConfigLocation)commConfigLocationId1_data.get(i);
				tempId[i ] = this.transNullToString(data.getItemCode());
				tempName[i ] =this.transNullToString(data.getItemName());
			}
		}else{
			tempId = new String[commConfigLocationId1_data.size()+1 ];
			tempName = new String[commConfigLocationId1_data.size()+1 ];
			tempId[0] = "";
			tempName[0] = "";
			for (int i = 0; i < commConfigLocationId1_data.size(); i++) {
				CommConfigLocation data = (CommConfigLocation)commConfigLocationId1_data.get(i);
				tempId[i+1 ] = this.transNullToString(data.getItemCode());
				tempName[i+1 ] =this.transNullToString(data.getItemName());
			}
		}
	}
	this.setCommConfigLocationId1(tempId);
	this.setCommConfigLocationName1(tempName);
	//----------------------市----------------------------------------------------
	 tempId = null;
	 tempName = null;
	if (commConfigLocationId2_data != null) {
		if(commConfigLocationId2_data.size()==1){
			tempId = new String[commConfigLocationId2_data.size() ];
			tempName = new String[commConfigLocationId2_data.size() ];
		//tempId[0] = "";
		//tempName[0] = "";
			for (int i = 0; i < commConfigLocationId2_data.size(); i++) {
				CommConfigLocation data = (CommConfigLocation)commConfigLocationId2_data.get(i);
				tempId[i] = this.transNullToString(data.getItemCode());
				tempName[i] =this.transNullToString(data.getItemName());
			}
		}
		else{
			tempId = new String[commConfigLocationId2_data.size()+1 ];
			tempName = new String[commConfigLocationId2_data.size()+1 ];
			tempId[0] = "";
			tempName[0] = "";
			for (int i = 0; i < commConfigLocationId2_data.size(); i++) {
				CommConfigLocation data = (CommConfigLocation)commConfigLocationId2_data.get(i);
				tempId[i+1] = this.transNullToString(data.getItemCode());
				tempName[i+1] =this.transNullToString(data.getItemName());
			}
		}
	} else {
		//--------------如果为空，插入空格-----------
		tempId = new String[1];
		tempName = new String[1];
		tempId[0] = "";
		tempName[0] = "";
	}
	this.setCommConfigLocationId2(tempId);
	this.setCommConfigLocationName2(tempName);
	//----------------------县----------------------------------------------------
	 tempId = null;
	 tempName = null;
	if (commConfigLocationId3_data != null) {
		if(commConfigLocationId3_data.size()==1){
			tempId = new String[commConfigLocationId3_data.size() ];
			tempName = new String[commConfigLocationId3_data.size() ];
		//tempId[0] = "";
		//tempName[0] = "";
			for (int i = 0; i < commConfigLocationId3_data.size(); i++) {
				CommConfigLocation data = (CommConfigLocation)commConfigLocationId3_data.get(i);
				tempId[i ] = this.transNullToString(data.getItemCode());
				tempName[i ] =this.transNullToString(data.getItemName());
			}
		}else{
			tempId = new String[commConfigLocationId3_data.size()+1 ];
			tempName = new String[commConfigLocationId3_data.size()+1 ];
			tempId[0] = "";
			tempName[0] = "";
			for (int i = 0; i < commConfigLocationId3_data.size(); i++) {
				CommConfigLocation data = (CommConfigLocation)commConfigLocationId3_data.get(i);
				tempId[i +1] = this.transNullToString(data.getItemCode());
				tempName[i +1] =this.transNullToString(data.getItemName());
			}
		}
	} else {
		//--------------如果为空，插入空格-----------
		tempId = new String[1];
		tempName = new String[1];
		tempId[0] = "";
		tempName[0] = "";
	}
	this.setCommConfigLocationId3(tempId);
	this.setCommConfigLocationName3(tempName);
	//----------------------乡镇----------------------------------------------------
	 tempId = null;
	 tempName = null;
	if (commConfigLocationTownId_data != null) {
		if(commConfigLocationTownId_data.size()==1){
			tempId = new String[commConfigLocationTownId_data.size() ];
			tempName = new String[commConfigLocationTownId_data.size() ];
		//tempId[0] = "";
		//tempName[0] = "";
			for (int i = 0; i < commConfigLocationTownId_data.size(); i++) {
				CommConfigLocationTown data = (CommConfigLocationTown)commConfigLocationTownId_data.get(i);
				tempId[i ] = this.transNullToString(data.getItemCode());
				tempName[i ] =this.transNullToString(data.getItemName());
			}
		}else{
			tempId = new String[commConfigLocationTownId_data.size()+1 ];
			tempName = new String[commConfigLocationTownId_data.size()+1 ];
			tempId[0] = "";
			tempName[0] = "";
			for (int i = 0; i < commConfigLocationTownId_data.size(); i++) {
				CommConfigLocationTown data = (CommConfigLocationTown)commConfigLocationTownId_data.get(i);
				tempId[i+1 ] = this.transNullToString(data.getItemCode());
				tempName[i+1 ] =this.transNullToString(data.getItemName());
			}
		}
	} else {
		//--------------如果为空，插入空格-----------
		tempId = new String[1];
		tempName = new String[1];
		tempId[0] = "";
		tempName[0] = "";
	}
	this.setCommConfigLocationTownId(tempId);
	this.setCommConfigLocationTownName(tempName);
	//----------------------村----------------------------------------------------
	 tempId = null;
	 tempName = null;
	if (commConfigLocationVillageId_data != null) {
		if(commConfigLocationVillageId_data.size()==1){
			tempId = new String[commConfigLocationVillageId_data.size() ];
			tempName = new String[commConfigLocationVillageId_data.size() ];
			//tempId[0] = "";
			//tempName[0] = "";
			for (int i = 0; i < commConfigLocationVillageId_data.size(); i++) {
				CommConfigLocationVillage data = (CommConfigLocationVillage)commConfigLocationVillageId_data.get(i);
				tempId[i ] = this.transNullToString(data.getItemCode());
				tempName[i ] =this.transNullToString(data.getItemName());
			}
		}else{
			tempId = new String[commConfigLocationVillageId_data.size() +1];
			tempName = new String[commConfigLocationVillageId_data.size()+1 ];
			tempId[0] = "";
			tempName[0] = "";
			for (int i = 0; i < commConfigLocationVillageId_data.size(); i++) {
				CommConfigLocationVillage data = (CommConfigLocationVillage)commConfigLocationVillageId_data.get(i);
				tempId[i +1] = this.transNullToString(data.getItemCode());
				tempName[i +1] =this.transNullToString(data.getItemName());
			}
		}
	} else {
		//--------------如果为空，插入空格-----------
		tempId = new String[1];
		tempName = new String[1];
		tempId[0] = "";
		tempName[0] = "";
	}
	this.setCommConfigLocationVillageId(tempId);
	this.setCommConfigLocationVillageName(tempName);
	//----------------------组----------------------------------------------------
	 tempId = null;
	 tempName = null;
	if (commConfigLocationGroupId_data != null) {
		tempId = new String[commConfigLocationGroupId_data.size()+1 ];
		tempName = new String[commConfigLocationGroupId_data.size()+1 ];
		tempId[0] = "";
		tempName[0] = "";
		for (int i = 0; i < commConfigLocationGroupId_data.size(); i++) {
			CommConfigLocationGroup data = (CommConfigLocationGroup)commConfigLocationGroupId_data.get(i);
			tempId[i +1] = this.transNullToString(data.getItemCode());
			tempName[i+1 ] =this.transNullToString(data.getItemName());
		}
	} else {
		//--------------如果为空，插入空格-----------
		tempId = new String[1];
		tempName = new String[1];
		tempId[0] = "";
		tempName[0] = "";
	}
	this.setCommConfigLocationGroupId(tempId);
	this.setCommConfigLocationGroupName(tempName);
	
	//户籍
	//----------------------省----------------------------------------------------
	 tempId = null;
	 tempName = null;
	if (censusLocationId1_data != null) {
		if(censusLocationId1_data.size()==1){
			tempId = new String[censusLocationId1_data.size() ];
			tempName = new String[censusLocationId1_data.size() ];
			for (int i = 0; i < censusLocationId1_data.size(); i++) {
				CommConfigLocation data = (CommConfigLocation)censusLocationId1_data.get(i);
				tempId[i ] = this.transNullToString(data.getItemCode());
				tempName[i ] =this.transNullToString(data.getItemName());
			}
		}else{
			tempId = new String[censusLocationId1_data.size()+1 ];
			tempName = new String[censusLocationId1_data.size()+1 ];
			tempId[0] = "";
			tempName[0] = "";
			for (int i = 0; i < censusLocationId1_data.size(); i++) {
				CommConfigLocation data = (CommConfigLocation)censusLocationId1_data.get(i);
				tempId[i+1 ] = this.transNullToString(data.getItemCode());
				tempName[i+1 ] =this.transNullToString(data.getItemName());
			}
		}
	}
	this.setCensusLocationId1(tempId);
	this.setCensusLocationName1(tempName);
	//----------------------市----------------------------------------------------
	 tempId = null;
	 tempName = null;
	if (censusLocationId2_data != null) {
		if(censusLocationId2_data.size()==1){
			tempId = new String[censusLocationId2_data.size() ];
			tempName = new String[censusLocationId2_data.size() ];
		//tempId[0] = "";
		//tempName[0] = "";
			for (int i = 0; i < censusLocationId2_data.size(); i++) {
				CommConfigLocation data = (CommConfigLocation)censusLocationId2_data.get(i);
				tempId[i] = this.transNullToString(data.getItemCode());
				tempName[i] =this.transNullToString(data.getItemName());
			}
		}
		else{
			tempId = new String[censusLocationId2_data.size()+1 ];
			tempName = new String[censusLocationId2_data.size()+1 ];
			tempId[0] = "";
			tempName[0] = "";
			for (int i = 0; i < censusLocationId2_data.size(); i++) {
				CommConfigLocation data = (CommConfigLocation)censusLocationId2_data.get(i);
				tempId[i+1] = this.transNullToString(data.getItemCode());
				tempName[i+1] =this.transNullToString(data.getItemName());
			}
		}
	} else {
		//--------------如果为空，插入空格-----------
		tempId = new String[1];
		tempName = new String[1];
		tempId[0] = "";
		tempName[0] = "";
	}
	this.setCensusLocationId2(tempId);
	this.setCensusLocationName2(tempName);
	//----------------------县----------------------------------------------------
	 tempId = null;
	 tempName = null;
	if (censusLocationId3_data != null) {
		if(censusLocationId3_data.size()==1){
			tempId = new String[censusLocationId3_data.size() ];
			tempName = new String[censusLocationId3_data.size() ];
		//tempId[0] = "";
		//tempName[0] = "";
			for (int i = 0; i < censusLocationId3_data.size(); i++) {
				CommConfigLocation data = (CommConfigLocation)censusLocationId3_data.get(i);
				tempId[i ] = this.transNullToString(data.getItemCode());
				tempName[i ] =this.transNullToString(data.getItemName());
			}
		}else{
			tempId = new String[censusLocationId3_data.size()+1 ];
			tempName = new String[censusLocationId3_data.size()+1 ];
			tempId[0] = "";
			tempName[0] = "";
			for (int i = 0; i < censusLocationId3_data.size(); i++) {
				CommConfigLocation data = (CommConfigLocation)censusLocationId3_data.get(i);
				tempId[i +1] = this.transNullToString(data.getItemCode());
				tempName[i +1] =this.transNullToString(data.getItemName());
			}
		}
	} else {
		//--------------如果为空，插入空格-----------
		tempId = new String[1];
		tempName = new String[1];
		tempId[0] = "";
		tempName[0] = "";
	}
	this.setCensusLocationId3(tempId);
	this.setCensusLocationName3(tempName);
	//----------------------乡镇----------------------------------------------------
	 tempId = null;
	 tempName = null;
	if (censusLocationTownId_data != null) {
		if(censusLocationTownId_data.size()==1){
			tempId = new String[censusLocationTownId_data.size() ];
			tempName = new String[censusLocationTownId_data.size() ];
		//tempId[0] = "";
		//tempName[0] = "";
			for (int i = 0; i < censusLocationTownId_data.size(); i++) {
				CommConfigLocationTown data = (CommConfigLocationTown)censusLocationTownId_data.get(i);
				tempId[i ] = this.transNullToString(data.getItemCode());
				tempName[i ] =this.transNullToString(data.getItemName());
			}
		}else{
			tempId = new String[censusLocationTownId_data.size()+1 ];
			tempName = new String[censusLocationTownId_data.size()+1 ];
			tempId[0] = "";
			tempName[0] = "";
			for (int i = 0; i < censusLocationTownId_data.size(); i++) {
				CommConfigLocationTown data = (CommConfigLocationTown)censusLocationTownId_data.get(i);
				tempId[i+1 ] = this.transNullToString(data.getItemCode());
				tempName[i+1 ] =this.transNullToString(data.getItemName());
			}
		}
	} else {
		//--------------如果为空，插入空格-----------
		tempId = new String[1];
		tempName = new String[1];
		tempId[0] = "";
		tempName[0] = "";
	}
	this.setCensusLocationTownId(tempId);
	this.setCensusLocationTownName(tempName);
	//----------------------村----------------------------------------------------
	 tempId = null;
	 tempName = null;
	if (censusLocationVillageId_data != null) {
		if(censusLocationVillageId_data.size()==1){
			tempId = new String[censusLocationVillageId_data.size() ];
			tempName = new String[censusLocationVillageId_data.size() ];
			//tempId[0] = "";
			//tempName[0] = "";
			for (int i = 0; i < censusLocationVillageId_data.size(); i++) {
				CommConfigLocationVillage data = (CommConfigLocationVillage)censusLocationVillageId_data.get(i);
				tempId[i ] = this.transNullToString(data.getItemCode());
				tempName[i ] =this.transNullToString(data.getItemName());
			}
		}else{
			tempId = new String[censusLocationVillageId_data.size() +1];
			tempName = new String[censusLocationVillageId_data.size()+1 ];
			tempId[0] = "";
			tempName[0] = "";
			for (int i = 0; i < censusLocationVillageId_data.size(); i++) {
				CommConfigLocationVillage data = (CommConfigLocationVillage)censusLocationVillageId_data.get(i);
				tempId[i +1] = this.transNullToString(data.getItemCode());
				tempName[i +1] =this.transNullToString(data.getItemName());
			}
		}
	} else {
		//--------------如果为空，插入空格-----------
		tempId = new String[1];
		tempName = new String[1];
		tempId[0] = "";
		tempName[0] = "";
	}
	this.setCensusLocationVillageId(tempId);
	this.setCensusLocationVillageName(tempName);
	//----------------------组----------------------------------------------------
	 tempId = null;
	 tempName = null;
	if (censusLocationGroupId_data != null) {
		tempId = new String[censusLocationGroupId_data.size()+1 ];
		tempName = new String[censusLocationGroupId_data.size()+1 ];
		tempId[0] = "";
		tempName[0] = "";
		for (int i = 0; i < censusLocationGroupId_data.size(); i++) {
			CommConfigLocationGroup data = (CommConfigLocationGroup)censusLocationGroupId_data.get(i);
			tempId[i +1] = this.transNullToString(data.getItemCode());
			tempName[i+1 ] =this.transNullToString(data.getItemName());
		}
	} else {
		//--------------如果为空，插入空格-----------
		tempId = new String[1];
		tempName = new String[1];
		tempId[0] = "";
		tempName[0] = "";
	}
	this.setCensusLocationGroupId(tempId);
	this.setCensusLocationGroupName(tempName);
	
	//出生地址
	//----------------------省----------------------------------------------------
	 tempId = null;
	 tempName = null;
	if (birthLocationId1_data != null) {
		if(birthLocationId1_data.size()==1){
			tempId = new String[birthLocationId1_data.size() ];
			tempName = new String[birthLocationId1_data.size() ];
			for (int i = 0; i < birthLocationId1_data.size(); i++) {
				CommConfigLocation data = (CommConfigLocation)birthLocationId1_data.get(i);
				tempId[i ] = this.transNullToString(data.getItemCode());
				tempName[i ] =this.transNullToString(data.getItemName());
			}
		}else{
			tempId = new String[birthLocationId1_data.size()+1 ];
			tempName = new String[birthLocationId1_data.size()+1 ];
			tempId[0] = "";
			tempName[0] = "";
			for (int i = 0; i < birthLocationId1_data.size(); i++) {
				CommConfigLocation data = (CommConfigLocation)birthLocationId1_data.get(i);
				tempId[i+1 ] = this.transNullToString(data.getItemCode());
				tempName[i+1 ] =this.transNullToString(data.getItemName());
			}
		}
	}
	this.setBirthLocationId1(tempId);
	this.setBirthLocationName1(tempName);
	//----------------------市----------------------------------------------------
	 tempId = null;
	 tempName = null;
	if (birthLocationId2_data != null) {
		if(birthLocationId2_data.size()==1){
			tempId = new String[birthLocationId2_data.size() ];
			tempName = new String[birthLocationId2_data.size() ];
		//tempId[0] = "";
		//tempName[0] = "";
			for (int i = 0; i < birthLocationId2_data.size(); i++) {
				CommConfigLocation data = (CommConfigLocation)birthLocationId2_data.get(i);
				tempId[i] = this.transNullToString(data.getItemCode());
				tempName[i] =this.transNullToString(data.getItemName());
			}
		}
		else{
			tempId = new String[birthLocationId2_data.size()+1 ];
			tempName = new String[birthLocationId2_data.size()+1 ];
			tempId[0] = "";
			tempName[0] = "";
			for (int i = 0; i < birthLocationId2_data.size(); i++) {
				CommConfigLocation data = (CommConfigLocation)birthLocationId2_data.get(i);
				tempId[i+1] = this.transNullToString(data.getItemCode());
				tempName[i+1] =this.transNullToString(data.getItemName());
			}
		}
	} else {
		//--------------如果为空，插入空格-----------
		tempId = new String[1];
		tempName = new String[1];
		tempId[0] = "";
		tempName[0] = "";
	}
	this.setBirthLocationId2(tempId);
	this.setBirthLocationName2(tempName);
	//----------------------县----------------------------------------------------
	 tempId = null;
	 tempName = null;
	if (birthLocationId3_data != null) {
		if(birthLocationId3_data.size()==1){
			tempId = new String[birthLocationId3_data.size() ];
			tempName = new String[birthLocationId3_data.size() ];
		//tempId[0] = "";
		//tempName[0] = "";
			for (int i = 0; i < birthLocationId3_data.size(); i++) {
				CommConfigLocation data = (CommConfigLocation)birthLocationId3_data.get(i);
				tempId[i ] = this.transNullToString(data.getItemCode());
				tempName[i ] =this.transNullToString(data.getItemName());
			}
		}else{
			tempId = new String[birthLocationId3_data.size()+1 ];
			tempName = new String[birthLocationId3_data.size()+1 ];
			tempId[0] = "";
			tempName[0] = "";
			for (int i = 0; i < birthLocationId3_data.size(); i++) {
				CommConfigLocation data = (CommConfigLocation)birthLocationId3_data.get(i);
				tempId[i +1] = this.transNullToString(data.getItemCode());
				tempName[i +1] =this.transNullToString(data.getItemName());
			}
		}
	} else {
		//--------------如果为空，插入空格-----------
		tempId = new String[1];
		tempName = new String[1];
		tempId[0] = "";
		tempName[0] = "";
	}
	this.setBirthLocationId3(tempId);
	this.setBirthLocationName3(tempName);
	//----------------------乡镇----------------------------------------------------
	 tempId = null;
	 tempName = null;
	if (birthLocationTownId_data != null) {
		if(birthLocationTownId_data.size()==1){
			tempId = new String[birthLocationTownId_data.size() ];
			tempName = new String[birthLocationTownId_data.size() ];
		//tempId[0] = "";
		//tempName[0] = "";
			for (int i = 0; i < birthLocationTownId_data.size(); i++) {
				CommConfigLocationTown data = (CommConfigLocationTown)birthLocationTownId_data.get(i);
				tempId[i ] = this.transNullToString(data.getItemCode());
				tempName[i ] =this.transNullToString(data.getItemName());
			}
		}else{
			tempId = new String[birthLocationTownId_data.size()+1 ];
			tempName = new String[birthLocationTownId_data.size()+1 ];
			tempId[0] = "";
			tempName[0] = "";
			for (int i = 0; i < birthLocationTownId_data.size(); i++) {
				CommConfigLocationTown data = (CommConfigLocationTown)birthLocationTownId_data.get(i);
				tempId[i+1 ] = this.transNullToString(data.getItemCode());
				tempName[i+1 ] =this.transNullToString(data.getItemName());
			}
		}
	} else {
		//--------------如果为空，插入空格-----------
		tempId = new String[1];
		tempName = new String[1];
		tempId[0] = "";
		tempName[0] = "";
	}
	this.setBirthLocationTownId(tempId);
	this.setBirthLocationTownName(tempName);
	//----------------------村----------------------------------------------------
	 tempId = null;
	 tempName = null;
	if (birthLocationVillageId_data != null) {
		if(birthLocationVillageId_data.size()==1){
			tempId = new String[birthLocationVillageId_data.size() ];
			tempName = new String[birthLocationVillageId_data.size() ];
			//tempId[0] = "";
			//tempName[0] = "";
			for (int i = 0; i < birthLocationVillageId_data.size(); i++) {
				CommConfigLocationVillage data = (CommConfigLocationVillage)birthLocationVillageId_data.get(i);
				tempId[i ] = this.transNullToString(data.getItemCode());
				tempName[i ] =this.transNullToString(data.getItemName());
			}
		}else{
			tempId = new String[birthLocationVillageId_data.size() +1];
			tempName = new String[birthLocationVillageId_data.size()+1 ];
			tempId[0] = "";
			tempName[0] = "";
			for (int i = 0; i < birthLocationVillageId_data.size(); i++) {
				CommConfigLocationVillage data = (CommConfigLocationVillage)birthLocationVillageId_data.get(i);
				tempId[i +1] = this.transNullToString(data.getItemCode());
				tempName[i +1] =this.transNullToString(data.getItemName());
			}
		}
	} else {
		//--------------如果为空，插入空格-----------
		tempId = new String[1];
		tempName = new String[1];
		tempId[0] = "";
		tempName[0] = "";
	}
	this.setBirthLocationVillageId(tempId);
	this.setBirthLocationVillageName(tempName);
	//----------------------组----------------------------------------------------
	 tempId = null;
	 tempName = null;
	if (birthLocationGroupId_data != null) {
		tempId = new String[birthLocationGroupId_data.size()+1 ];
		tempName = new String[birthLocationGroupId_data.size()+1 ];
		tempId[0] = "";
		tempName[0] = "";
		for (int i = 0; i < birthLocationGroupId_data.size(); i++) {
			CommConfigLocationGroup data = (CommConfigLocationGroup)birthLocationGroupId_data.get(i);
			tempId[i +1] = this.transNullToString(data.getItemCode());
			tempName[i+1 ] =this.transNullToString(data.getItemName());
		}
	} else {
		//--------------如果为空，插入空格-----------
		tempId = new String[1];
		tempName = new String[1];
		tempId[0] = "";
		tempName[0] = "";
	}
	this.setBirthLocationGroupId(tempId);
	this.setBirthLocationGroupName(tempName);
	
}
//-----------------------------------------------------------------------------------------------
	public String[] getYear() {
		String[] temp = null;
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		int yearFrom = 1900;
		int yearTo = cal.get(Calendar.YEAR) + 10;
		temp = new String[yearTo - yearFrom];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = String.valueOf(yearFrom + i);
		}
		return temp;
	}

	public String[] getMonth() {
		String[] temp = null;
		int from = 1;
		int to = 13;
		temp = new String[to - from];
		for (int i = 0; i < temp.length; i++) {
			if (i < 9) {
				temp[i] = "0" + String.valueOf(from + i);
			} else {
				temp[i] = String.valueOf(from + i);
			}
		}
		return temp;
	}

	public String[] getDay() {
		String[] temp = null;
		int from = 1;
		int to = 32;
		temp = new String[to - from];
		for (int i = 0; i < temp.length; i++) {
			if (i < 9) {
				temp[i] = "0" + String.valueOf(from + i);
			} else {
				temp[i] = String.valueOf(from + i);
			}
		}
		return temp;
	}
//-------------------------------------------------------------------------------------
	/** 字典库的处理 */
	private String[] commConfigSexId;
	private String[] commConfigSexName;
	private String[] commConfigCountryId;
	private String[] commConfigCountryName;
	private String[] commConfigNationalityId;
	private String[] commConfigNationalityName;
	private String[] commConfigIdTypeId;
	private String[] commConfigIdTypeName;
	private String[] commConfigAboId;
	private String[] commConfigAboName;
	private String[] commConfigRhId;
	private String[] commConfigRhName;
	private String[] commConfigDegreeId;
	private String[] commConfigDegreeName;
	private String[] commConfigMaritalStatusId;
	private String[] commConfigMaritalStatusName;
	
	private String[] censusLocationId1;
	private String[] censusLocationName1;
	private String[] censusLocationId2;
	private String[] censusLocationName2;
	private String[] censusLocationId3;
	private String[] censusLocationName3;
	private String[] censusLocationTownId;
	private String[] censusLocationTownName;
	private String[] censusLocationVillageId;
	private String[] censusLocationVillageName;
	private String[] censusLocationGroupId;
	private String[] censusLocationGroupName;
	
	
	private String[] birthLocationId1;
	private String[] birthLocationName1;
	private String[] birthLocationId2;
	private String[] birthLocationName2;
	private String[] birthLocationId3;
	private String[] birthLocationName3;
	private String[] birthLocationTownId;
	private String[] birthLocationTownName;
	private String[] birthLocationVillageId;
	private String[] birthLocationVillageName;
	private String[] birthLocationGroupId;
	private String[] birthLocationGroupName;
	
	private String[] commConfigLocationId1;
	private String[] commConfigLocationName1;
	private String[] commConfigLocationId2;
	private String[] commConfigLocationName2;
	private String[] commConfigLocationId3;
	private String[] commConfigLocationName3;
	private String[] commConfigLocationTownId;
	private String[] commConfigLocationTownName;
	private String[] commConfigLocationVillageId;
	private String[] commConfigLocationVillageName;
	private String[] commConfigLocationGroupId;
	private String[] commConfigLocationGroupName;
	
	private String[] commConfigRelationshipId;
	private String[] commConfigRelationshipName;
	private String[] occupationCodeIds;
	private String[] occupationCodeNames;
	private String[] deceasedInds;
	private String[] deceasedIndNames;
	
	private String[] cardType_list;
	private String[] cardTypeName_list;
	
	public String[] getCommConfigSexId() {
		return commConfigSexId;
	}

	
	public void setCommConfigSexId(String[] commConfigSexId) {
		this.commConfigSexId = commConfigSexId;
	}

	
	public String[] getCommConfigSexName() {
		return commConfigSexName;
	}

	
	public void setCommConfigSexName(String[] commConfigSexName) {
		this.commConfigSexName = commConfigSexName;
	}

	
	public String[] getCommConfigCountryId() {
		return commConfigCountryId;
	}

	
	public void setCommConfigCountryId(String[] commConfigCountryId) {
		this.commConfigCountryId = commConfigCountryId;
	}

	
	public String[] getCommConfigCountryName() {
		return commConfigCountryName;
	}

	
	public void setCommConfigCountryName(String[] commConfigCountryName) {
		this.commConfigCountryName = commConfigCountryName;
	}

	
	public String[] getCommConfigNationalityId() {
		return commConfigNationalityId;
	}

	
	public void setCommConfigNationalityId(String[] commConfigNationalityId) {
		this.commConfigNationalityId = commConfigNationalityId;
	}

	
	public String[] getCommConfigNationalityName() {
		return commConfigNationalityName;
	}

	
	public void setCommConfigNationalityName(String[] commConfigNationalityName) {
		this.commConfigNationalityName = commConfigNationalityName;
	}

	
	public String[] getCommConfigIdTypeId() {
		return commConfigIdTypeId;
	}

	
	public void setCommConfigIdTypeId(String[] commConfigIdTypeId) {
		this.commConfigIdTypeId = commConfigIdTypeId;
	}

	
	
	public String[] getOccupationCodeIds() {
		return occupationCodeIds;
	}


	public void setOccupationCodeIds(String[] occupationCodeIds) {
		this.occupationCodeIds = occupationCodeIds;
	}


	public String[] getOccupationCodeNames() {
		return occupationCodeNames;
	}


	public void setOccupationCodeNames(String[] occupationCodeNames) {
		this.occupationCodeNames = occupationCodeNames;
	}


	public String[] getDeceasedInds() {
		return deceasedInds;
	}


	public void setDeceasedInds(String[] deceasedInds) {
		this.deceasedInds = deceasedInds;
	}


	public String[] getDeceasedIndNames() {
		return deceasedIndNames;
	}


	public void setDeceasedIndNames(String[] deceasedIndNames) {
		this.deceasedIndNames = deceasedIndNames;
	}


	public String[] getCommConfigIdTypeName() {
		return commConfigIdTypeName;
	}

	
	public void setCommConfigIdTypeName(String[] commConfigIdTypeName) {
		this.commConfigIdTypeName = commConfigIdTypeName;
	}

	
	public String[] getCommConfigAboId() {
		return commConfigAboId;
	}

	
	public void setCommConfigAboId(String[] commConfigAboId) {
		this.commConfigAboId = commConfigAboId;
	}

	
	public String[] getCommConfigAboName() {
		return commConfigAboName;
	}

	
	public void setCommConfigAboName(String[] commConfigAboName) {
		this.commConfigAboName = commConfigAboName;
	}

	
	public String[] getCommConfigRhId() {
		return commConfigRhId;
	}

	
	public void setCommConfigRhId(String[] commConfigRhId) {
		this.commConfigRhId = commConfigRhId;
	}

	
	public String[] getCommConfigRhName() {
		return commConfigRhName;
	}

	
	public void setCommConfigRhName(String[] commConfigRhName) {
		this.commConfigRhName = commConfigRhName;
	}
	
	
	
	public String[] getCensusLocationId1() {
		return censusLocationId1;
	}


	public void setCensusLocationId1(String[] censusLocationId1) {
		this.censusLocationId1 = censusLocationId1;
	}


	public String[] getCensusLocationName1() {
		return censusLocationName1;
	}


	public void setCensusLocationName1(String[] censusLocationName1) {
		this.censusLocationName1 = censusLocationName1;
	}


	public String[] getCensusLocationId2() {
		return censusLocationId2;
	}


	public void setCensusLocationId2(String[] censusLocationId2) {
		this.censusLocationId2 = censusLocationId2;
	}


	public String[] getCensusLocationName2() {
		return censusLocationName2;
	}


	public void setCensusLocationName2(String[] censusLocationName2) {
		this.censusLocationName2 = censusLocationName2;
	}


	public String[] getCensusLocationId3() {
		return censusLocationId3;
	}


	public void setCensusLocationId3(String[] censusLocationId3) {
		this.censusLocationId3 = censusLocationId3;
	}


	public String[] getCensusLocationName3() {
		return censusLocationName3;
	}


	public void setCensusLocationName3(String[] censusLocationName3) {
		this.censusLocationName3 = censusLocationName3;
	}


	public String[] getCensusLocationTownId() {
		return censusLocationTownId;
	}


	public void setCensusLocationTownId(String[] censusLocationTownId) {
		this.censusLocationTownId = censusLocationTownId;
	}


	public String[] getCensusLocationTownName() {
		return censusLocationTownName;
	}


	public void setCensusLocationTownName(String[] censusLocationTownName) {
		this.censusLocationTownName = censusLocationTownName;
	}


	public String[] getCensusLocationVillageId() {
		return censusLocationVillageId;
	}


	public void setCensusLocationVillageId(String[] censusLocationVillageId) {
		this.censusLocationVillageId = censusLocationVillageId;
	}


	public String[] getCensusLocationVillageName() {
		return censusLocationVillageName;
	}


	public void setCensusLocationVillageName(String[] censusLocationVillageName) {
		this.censusLocationVillageName = censusLocationVillageName;
	}


	public String[] getCensusLocationGroupId() {
		return censusLocationGroupId;
	}


	public void setCensusLocationGroupId(String[] censusLocationGroupId) {
		this.censusLocationGroupId = censusLocationGroupId;
	}


	public String[] getCensusLocationGroupName() {
		return censusLocationGroupName;
	}


	public void setCensusLocationGroupName(String[] censusLocationGroupName) {
		this.censusLocationGroupName = censusLocationGroupName;
	}


	public String[] getBirthLocationId1() {
		return birthLocationId1;
	}


	public void setBirthLocationId1(String[] birthLocationId1) {
		this.birthLocationId1 = birthLocationId1;
	}


	public String[] getBirthLocationName1() {
		return birthLocationName1;
	}


	public void setBirthLocationName1(String[] birthLocationName1) {
		this.birthLocationName1 = birthLocationName1;
	}


	public String[] getBirthLocationId2() {
		return birthLocationId2;
	}


	public void setBirthLocationId2(String[] birthLocationId2) {
		this.birthLocationId2 = birthLocationId2;
	}


	public String[] getBirthLocationName2() {
		return birthLocationName2;
	}


	public void setBirthLocationName2(String[] birthLocationName2) {
		this.birthLocationName2 = birthLocationName2;
	}


	public String[] getBirthLocationId3() {
		return birthLocationId3;
	}


	public void setBirthLocationId3(String[] birthLocationId3) {
		this.birthLocationId3 = birthLocationId3;
	}


	public String[] getBirthLocationName3() {
		return birthLocationName3;
	}


	public void setBirthLocationName3(String[] birthLocationName3) {
		this.birthLocationName3 = birthLocationName3;
	}


	public String[] getBirthLocationTownId() {
		return birthLocationTownId;
	}


	public void setBirthLocationTownId(String[] birthLocationTownId) {
		this.birthLocationTownId = birthLocationTownId;
	}


	public String[] getBirthLocationTownName() {
		return birthLocationTownName;
	}


	public void setBirthLocationTownName(String[] birthLocationTownName) {
		this.birthLocationTownName = birthLocationTownName;
	}


	public String[] getBirthLocationVillageId() {
		return birthLocationVillageId;
	}


	public void setBirthLocationVillageId(String[] birthLocationVillageId) {
		this.birthLocationVillageId = birthLocationVillageId;
	}


	public String[] getBirthLocationVillageName() {
		return birthLocationVillageName;
	}


	public void setBirthLocationVillageName(String[] birthLocationVillageName) {
		this.birthLocationVillageName = birthLocationVillageName;
	}


	public String[] getBirthLocationGroupId() {
		return birthLocationGroupId;
	}


	public void setBirthLocationGroupId(String[] birthLocationGroupId) {
		this.birthLocationGroupId = birthLocationGroupId;
	}


	public String[] getBirthLocationGroupName() {
		return birthLocationGroupName;
	}


	public void setBirthLocationGroupName(String[] birthLocationGroupName) {
		this.birthLocationGroupName = birthLocationGroupName;
	}


	public String[] getCommConfigDegreeId() {
		return commConfigDegreeId;
	}

	
	public void setCommConfigDegreeId(String[] commConfigDegreeId) {
		this.commConfigDegreeId = commConfigDegreeId;
	}

	
	public String[] getCommConfigDegreeName() {
		return commConfigDegreeName;
	}

	
	public void setCommConfigDegreeName(String[] commConfigDegreeName) {
		this.commConfigDegreeName = commConfigDegreeName;
	}

	
	public String[] getCommConfigMaritalStatusId() {
		return commConfigMaritalStatusId;
	}

	
	public void setCommConfigMaritalStatusId(String[] commConfigMaritalStatusId) {
		this.commConfigMaritalStatusId = commConfigMaritalStatusId;
	}

	
	public String[] getCommConfigMaritalStatusName() {
		return commConfigMaritalStatusName;
	}

	
	public void setCommConfigMaritalStatusName(String[] commConfigMaritalStatusName) {
		this.commConfigMaritalStatusName = commConfigMaritalStatusName;
	}

	
	public String[] getCommConfigLocationId1() {
		return commConfigLocationId1;
	}

	
	public void setCommConfigLocationId1(String[] commConfigLocationId1) {
		this.commConfigLocationId1 = commConfigLocationId1;
	}

	
	public String[] getCommConfigLocationName1() {
		return commConfigLocationName1;
	}

	
	public void setCommConfigLocationName1(String[] commConfigLocationName1) {
		this.commConfigLocationName1 = commConfigLocationName1;
	}

	
	public String[] getCommConfigLocationId2() {
		return commConfigLocationId2;
	}

	
	public void setCommConfigLocationId2(String[] commConfigLocationId2) {
		this.commConfigLocationId2 = commConfigLocationId2;
	}

	
	public String[] getCommConfigLocationName2() {
		return commConfigLocationName2;
	}

	
	public void setCommConfigLocationName2(String[] commConfigLocationName2) {
		this.commConfigLocationName2 = commConfigLocationName2;
	}

	
	public String[] getCommConfigLocationId3() {
		return commConfigLocationId3;
	}

	
	public void setCommConfigLocationId3(String[] commConfigLocationId3) {
		this.commConfigLocationId3 = commConfigLocationId3;
	}

	
	public String[] getCommConfigLocationName3() {
		return commConfigLocationName3;
	}

	
	public void setCommConfigLocationName3(String[] commConfigLocationName3) {
		this.commConfigLocationName3 = commConfigLocationName3;
	}

	
	public String[] getCommConfigRelationshipId() {
		return commConfigRelationshipId;
	}

	
	public void setCommConfigRelationshipId(String[] commConfigRelationshipId) {
		this.commConfigRelationshipId = commConfigRelationshipId;
	}

	
	public String[] getCommConfigRelationshipName() {
		return commConfigRelationshipName;
	}

	
	public void setCommConfigRelationshipName(String[] commConfigRelationshipName) {
		this.commConfigRelationshipName = commConfigRelationshipName;
	}
	
	//---------------------------------
	public void picUpById(String id, String photoPath) {
		SecurityUserBaseinfo data = securityUserBaseinfoDAO.getById(id);
		System.out.println("id="+data.getId());
		data.setPhotoPath(photoPath);
		securityUserBaseinfoDAO.update(data);
	}
	/**通过Id获取客户个人信息*/
	public SecurityUserBaseinfo getById(String id){
		return this.getSecurityUserBaseinfoDAO().getById(id);
		
	}
	/**
	 * 根据用户pmi，获得用户基本信息
	 * 
	 * @param userid
	 * @return
	 */
	public SecurityUserBaseinfo findUserInfoById(String userid) {
		return this.getSecurityUserBaseinfoDAO().findUserInfoById(userid);
	}
	/**取性别*/
	public String getcommConfigSex(String commConfigSexId){
		return this.securityUserBaseinfoDAO.getcommConfigSex(commConfigSexId);
	}
	/**取国籍*/
	public String getcommConfigCountry(String commConfigCountryId){
		return this.securityUserBaseinfoDAO.getcommConfigCountry(commConfigCountryId);
	}
	
	public String[] getCardType_list() {
		return cardType_list;
	}

	public void setCardType_list(String[] cardType_list) {
		this.cardType_list = cardType_list;
	}

	public String[] getCardTypeName_list() {
		return cardTypeName_list;
	}

	public void setCardTypeName_list(String[] cardTypeName_list) {
		this.cardTypeName_list = cardTypeName_list;
	}

	/**取民族*/
	public String getcommConfigNationality(String commConfigNationalityId){
		return this.securityUserBaseinfoDAO.getcommConfigNationality(commConfigNationalityId);
	}
	/**取证件*/
	public String getcommConfigIdType(String commConfigIdTypeId){
		return this.securityUserBaseinfoDAO.getcommConfigIdType(commConfigIdTypeId);
	}
	/**取学历*/
	public String getcommConfigDegree(String commConfigDegreeId){
		return this.securityUserBaseinfoDAO.getcommConfigDegree(commConfigDegreeId);
	}
	/**取婚姻*/
	public String getcommConfigMaritalStatus(String commConfigMaritalStatusId){
		return this.securityUserBaseinfoDAO.getcommConfigMaritalStatus(commConfigMaritalStatusId);
	}
	/**取关系*/
	public String getcommConfigRelationship(String commConfigRelationshipId){
		return this.securityUserBaseinfoDAO.getcommConfigRelationship(commConfigRelationshipId);
	}
	
	private void setCellText(HSSFRow row, HSSFCell cell, int index, String text, HSSFCellStyle cellStyle) {
		cell = row.createCell((short)index);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(cellStyle);
		text = getUnicode(text,"gb2312");
		cell.setCellValue(text);
	}
	
	@SuppressWarnings("deprecation")
	public static String getUnicode(String toEncoded,String encoding){
		String retString="";
		if(toEncoded.equals("")||toEncoded.trim().equals("")){
			return toEncoded;
		}
		try {
			byte[] b=toEncoded.getBytes(encoding);
			sun.io.ByteToCharConverter converter=sun.io.ByteToCharConverter.getConverter(encoding);
			char[] c=converter.convertAll(b);
			for(int i=0;i<c.length;i++){
				retString+=String.valueOf(c[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retString;
	}
	//去表的总数
	public int getCount() {
		return this.securityUserBaseinfoDAO.getCount();
	}
	
	public List saveImport(InputStream inputstream){
		List list=new ArrayList();
		String temp="";
		HSSFWorkbook workbook=null;
		try {
			workbook = new HSSFWorkbook(inputstream);
			HSSFSheet sheet = workbook.getSheetAt(0);//第一个工作表  
			//遍历该表格中所有的工作表，i表示工作表的数量 getNumberOfSheets表示工作表的总数
			for(int i=0;i<workbook.getNumberOfSheets();i++){
				sheet=workbook.getSheetAt(i);
				 //遍历该行所有的行,j表示行数 getPhysicalNumberOfRows行的总数
				for(int j= 3;j<sheet.getPhysicalNumberOfRows();j++){
					SecurityUserBaseinfo s=new SecurityUserBaseinfo();
					HSSFRow sheetrows = sheet.getRow(j);
					String commConfigSexId="";
					String location1Id="";
					String location2Id="";
					String location3Id="";
					String locationTId="";
					String locationVId="";
				    if(sheetrows!=null){
					    	HSSFCell pmi = sheetrows.getCell((short) 0);
					    	if(!getValue(pmi).equals("")){
					    		Pattern p=Pattern.compile("[0-9]*");
					    		Matcher isNum=p.matcher(getValue(pmi));
					    		if(isNum.matches()){
				    				 if(securityUserBaseinfoDAO.getCheckPmi(getValue(pmi))!=0){
								    		temp+="第"+(j-2)+"行的客服编码不能重复，请检查。<br>";
								    		list.add(temp);
								    	}else{
								    		try {
								    			if(getValue(pmi).length()>40){
								    				 temp+="第"+(j-2)+"行的客服编码过长！<br>";
								    				 list.add(temp);
								    			}
								    			HSSFCell name=sheetrows.getCell((short) 1);
								    			HSSFCell commConfigSex=sheetrows.getCell((short) 2);
								    			if(getValue(commConfigSex).equals("")){
								    				commConfigSexId="";
								    			}else{
								    				
								    				commConfigSexId=securityUserBaseinfoDAO.getById("CommConfigSex", getValue(commConfigSex));
								    				if(commConfigSexId==null||commConfigSexId.equals("")){
								    					temp+="第"+(j-2)+"行输入的性别不存在，请检查。<br>";
								    					list.add(temp);
								    					continue;
										    	    }
										     	}
								    			 HSSFCell location=sheetrows.getCell((short) 5);
								    			 if(Converter.toBlank(getValue(location)).equals("")){
								    				 temp+="第"+(j-2)+"省不能为空，请检查。<br>";
								    					list.add(temp);
								    					continue;
								    			 }else{
								    				 List<?> ls=this.securityUserBaseinfoDAO.getItemName("CommConfigLocation", "itemName", getValue(location));
								    				 if(ls!=null&&ls.size()>0){
								    					CommConfigLocation location1=(CommConfigLocation)ls.get(0);
								    					location1Id=location1.getItemCode();
								    				 }
								    	
								    			 }
								    			 HSSFCell location2=sheetrows.getCell((short) 6);
								    			 if(Converter.toBlank(getValue(location2)).equals("")){
								    				 temp+="第"+(j-2)+"市不能为空，请检查。<br>";
								    					list.add(temp);
								    					continue;
								    			 }else{
								    				 List<?> ls=this.securityUserBaseinfoDAO.getItemName("CommConfigLocation", "itemName", getValue(location2));
								    				 if(ls!=null&&ls.size()>0){
								    					CommConfigLocation location1=(CommConfigLocation)ls.get(0);
								    					location2Id=location1.getItemCode();
								    				 }
								    	
								    			 }
								    			 HSSFCell location3=sheetrows.getCell((short) 7);
								    			 if(Converter.toBlank(getValue(location3)).equals("")){
								    				 temp+="第"+(j-2)+"县不能为空，请检查。<br>";
								    					list.add(temp);
								    					continue;
								    			 }else{
								    				 List<?> ls=this.securityUserBaseinfoDAO.getItemName("CommConfigLocation", "itemName", getValue(location3));
								    				 if(ls!=null&&ls.size()>0){
								    					CommConfigLocation location1=(CommConfigLocation)ls.get(0);
								    					location3Id=location1.getItemCode();
								    				 }
								    	
								    			 }
								    			 HSSFCell locationT1=sheetrows.getCell((short) 8);
								    			 if(Converter.toBlank(getValue(locationT1)).equals("")){
								    				 s.setCommConfigLocationTownId("");
								    			 }else{
								    				 List<?> ls=this.securityUserBaseinfoDAO.getItemName("CommConfigLocationTown", "itemName", getValue(locationT1));
								    				 if(ls!=null&&ls.size()>0){
								    					 CommConfigLocationTown location1=(CommConfigLocationTown)ls.get(0);
								    					locationTId=location1.getItemCode();
								    				 }
								    	
								    			 }
								    			 HSSFCell locationV1=sheetrows.getCell((short) 9);
								    			 if(Converter.toBlank(getValue(locationV1)).equals("")){
								    				 s.setCommConfigLocationVillageid("");
								    			 }else{
								    				 List<?> ls=this.securityUserBaseinfoDAO.getItemName("CommConfigLocationVillage", "itemName", getValue(locationV1));
								    				 if(ls!=null&&ls.size()>0){
								    					 CommConfigLocationVillage location1=(CommConfigLocationVillage)ls.get(0);
								    					locationVId=location1.getItemCode();
								    				 }
								    			 }
								    			 HSSFCell birthPlace=sheetrows.getCell((short) 3);
											     HSSFCell dateOfBirth=sheetrows.getCell((short) 4);
											     s.setPmi(getValue(pmi));
											     s.setIdNo(getValue(pmi));
											     s.setName(getValue(name));
											     s.setCommConfigSexId(commConfigSexId);
//											     s.setBirthPlace(getValue(birthPlace));
											     s.setCommConfigLocationId1(location1Id);
											     s.setCommConfigLocationId2(location2Id);
											     s.setCommConfigLocationId3(location3Id);
											     s.setCommConfigLocationTownId(locationTId);
											     s.setCommConfigLocationVillageid(locationVId);
											     if(getValue(dateOfBirth).equals("")){
											    	 s.setDateOfBirth(null);
											     }else{
											    	 SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
											    	 s.setDateOfBirth(sdf.parse(getValue(dateOfBirth)));
											     }
											     String inputCode=commConfigInputDictService.getInputCode(getValue(name));
											     s.setInputCode(inputCode);
												 s.setCreateDate(new Date());
												 securityUserBaseinfoDAO.save(s);
												 temp+="第"+ (j-2) +"行导入成功！<br>";
												 list.add(temp);
										      }catch (ParseException e) {
												  temp+="导入失败！请检查Excel格式<br>";
												  list.add(temp);
											 }
										   }
								    	}else{
							    			 temp+="第"+(j-2)+"行的客服编号必须是数字，请检查。<br>";
						    				 list.add(temp);
						    	}
					    	}else{
					    		  temp+="第" +(j-2)+"客户编码不能为空。<br>";
								  list.add(temp);
					    	}
				    }
				}
			}
		}catch (IOException e) {
			temp+="导入失败，请检查Excel版本";
			list.add(temp);
			e.printStackTrace();
		}  
			return list;
	}
	private static String getValue(HSSFCell cell) {
		String value = "";
		  switch (cell.getCellType()) {
		  case HSSFCell.CELL_TYPE_NUMERIC: // 数值型
		   if (HSSFDateUtil.isCellDateFormatted(cell)) {
		    //如果是date类型则 ，获取该cell的date值
		     value = HSSFDateUtil.getJavaDate(cell.getNumericCellValue()).toString();
		   } else {// 纯数字
		     value = String.valueOf(cell.getNumericCellValue());
		     for(int i=0;i<value.length();i++){
		    	 if(value.charAt(i)=='E'||value.charAt(i)=='e'){
		    		 BigDecimal dd = new BigDecimal(value);
		    		 value=dd.longValue()+"";
		    	 }
		    	 if(value.substring(value.length()-2).equals(".0")){
		    		 value=value.substring(0, value.length()-2);
		    	 }
		     }
		   }
		   break;
		  /* 此行表示单元格的内容为string类型 */
		  case HSSFCell.CELL_TYPE_STRING: // 字符串型   
		   value = cell.getStringCellValue().toString();
		   break;
		  case HSSFCell.CELL_TYPE_FORMULA:// 公式型
		   //读公式计算值
		   value = String.valueOf(cell.getNumericCellValue());
		   if (value.equals("NaN")) {// 如果获取的数据值为非法值,则转换为获取字符串
		    value = cell.getStringCellValue().toString();
		   }
		   break;
		  case HSSFCell.CELL_TYPE_BOOLEAN:// 布尔
		   value = " " + cell.getBooleanCellValue();
		   break;
		  /* 此行表示该单元格值为空 */
		  case HSSFCell.CELL_TYPE_BLANK: // 空值
		   value = "";
		   break;
		  case HSSFCell.CELL_TYPE_ERROR: // 故障
		   value = "";
		   break;
		  default:
		  }
		  return value;  

		 }


	public ICommConfigInputDictService getCommConfigInputDictService() {
		return commConfigInputDictService;
	}


	public void setCommConfigInputDictService(
			ICommConfigInputDictService commConfigInputDictService) {
		this.commConfigInputDictService = commConfigInputDictService;
	}


	
	public SecurityUserBaseinfo checkByIdNo(String idNo) {
		// TODO Auto-generated method stub
		return securityUserBaseinfoDAO.getByIdNo(idNo);
	}
    

	
	public SecurityUserBaseinfo getByPmi(String pmi) {
		// TODO Auto-generated method stub
		return securityUserBaseinfoDAO.getByItemCode(pmi);
	}
	//用于权限管理
	protected void getNeedSecurityUserBaseinfoForm(String staffId,SecurityUserBaseinfoForm securityUserBaseinfoForm){
		SecurityDataObjectVsRoles nSecurityDataObjectVsRoles=securityUserBaseinfoDAO.findById(staffId);
		//SecurityDataObjectType nSecurityDataObjectType=securityUserBaseinfoDAO.getColumnNameById(nSecurityDataObjectVsRoles.getSdotId());
		
		if(nSecurityDataObjectVsRoles!=null){
			String place=nSecurityDataObjectVsRoles.getSdoValue();
			if(nSecurityDataObjectVsRoles.getSdotId().equals("1")){
				securityUserBaseinfoForm.setCommConfigLocationId1(place);
			}
			if(nSecurityDataObjectVsRoles.getSdotId().equals("2")){
				securityUserBaseinfoForm.setCommConfigLocationId2(place);
			}
			if(nSecurityDataObjectVsRoles.getSdotId().equals("3")){
				securityUserBaseinfoForm.setCommConfigLocationId3(place);
			}
			if(nSecurityDataObjectVsRoles.getSdotId().equals("4")){
				securityUserBaseinfoForm.setCommConfigLocationTownId(place);
			}
			if(nSecurityDataObjectVsRoles.getSdotId().equals("5")){
				securityUserBaseinfoForm.setCommConfigLocationVillageId(place);
			}
		}
	}
	
	
	
	//获得满足条件的用户个数**/
	public int getcountbymore(SecurityUserBaseinfoForm securityUserBaseinfoForm,String staffId) {
		// TODO Auto-generated method stub
		//this.getNeedSecurityUserBaseinfoForm(staffId, securityUserBaseinfoForm);
		if(securityUserBaseinfoForm.getXflag()==null || securityUserBaseinfoForm.getXflag().trim().length()<=0)
			return 0;
		//List<String[]> list=this.getColumnNameAndPlace1(staffId);
		//if(list!=null&&list.size()>0){
		else
			return securityUserBaseinfoDAO.getcountbymore(securityUserBaseinfoForm);
		//}else{
			//return 0;
		//}
	}

	//获得满足条件的用户,将其需要数据放入form中**/
	
	public void getUsers(SecurityUserBaseinfoForm securityUserBaseinfoForm,
			int count, int pageSize,String staffId) {// TODO Auto-generated method stub
		if(securityUserBaseinfoForm.getXflag()==null || securityUserBaseinfoForm.getXflag().trim().length()<=0)
			return;
		String order = "";
		if (securityUserBaseinfoForm.getOrderNo().trim().equals("1")) {
			order = " a.pmi";
		} else if (securityUserBaseinfoForm.getOrderNo().trim().equals("2")) {
			order = " a.name";
		} else if (securityUserBaseinfoForm.getOrderNo().trim().equals("3")) {
			order = " a.COMM_CONFIG_SEX_ID";
		} else if (securityUserBaseinfoForm.getOrderNo().trim().equals("5")) {
			order = " a.ID_NO";
		} else if (securityUserBaseinfoForm.getOrderNo().trim().equals("4")) {
			order = " a.DATE_OF_BIRTH";
		}else if (securityUserBaseinfoForm.getOrderNo().trim().equals("6")) {
			order = " a.COMM_CONFIG_LOCATION_ID_1";
		}else if (securityUserBaseinfoForm.getOrderNo().trim().equals("7")) {
			order = " a.MOBILE_TEL";
		} else {
			order = " a.name";
		}
		if (securityUserBaseinfoForm.getAsc().equals("1")) {
			order += " desc";
		} else {
			order += " asc";
		}
		securityUserBaseinfoForm.setOrderNo(order);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		//this.getNeedSecurityUserBaseinfoForm(staffId, securityUserBaseinfoForm);
		//List<String[]> li=this.getColumnNameAndPlace1(staffId);
		//List<?> list=null;
		//if(li!=null&&li.size()>0){
		List<?> list = securityUserBaseinfoDAO.getUsers(securityUserBaseinfoForm, count, pageSize);
		//}
		if (list != null && list.size() > 0) {
			String[] ids = new String[list.size()];
			String[] pimList = new String[list.size()];
			String[] idNos = new String[list.size()];
			String[] names = new String[list.size()];
			String[] commConfigSexIds = new String[list.size()];
			String[] commConfigSexId_names = new String[list.size()];
			String[] province_id=new String[list.size()];
			String[] province_name=new String[list.size()];
			String[] shi_id=new String[list.size()];
			String[] shi_name=new String[list.size()];
			String[] xian_id=new String[list.size()];
			String[] xian_name=new String[list.size()];
			String[] xiang_id=new String[list.size()];
			String[] xiang_name=new String[list.size()];
			String[] zheng_id=new String[list.size()];
			String[] zheng_name=new String[list.size()];
			String[] dateOfBirths = new String[list.size()];
			String[] mobileTels = new String[list.size()];
			String[] residentialAddress = new String[list.size()];
			String[] fullAddress = new String[list.size()];
			//String[] birthPlaces = new String[list.size()];
			String[] idTypes = new String[list.size()];
			String[] inputCodes = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				ids[i] = transNullToString(((Object[]) list.get(i))[0]);
				pimList[i] = transNullToString(((Object[]) list.get(i))[1]);
				idNos[i] = transNullToString(((Object[]) list.get(i))[10]);
				names[i] = transNullToString(((Object[]) list.get(i))[2]);
				commConfigSexIds[i] = transNullToString(((Object[]) list.get(i))[5]);
				commConfigSexId_names[i] = commConfigSexDAO.getItemName(commConfigSexIds[i]);
				String idType = transNullToString(((Object[]) list.get(i))[9]);
				idTypes[i] = this.commConfigIdTypeDAO.getItemName(idType);
				
				
				province_id[i] = transNullToString(((Object[]) list.get(i))[16]);//省
				province_name[i] =transNullToString( commConfigLocationDAO.getItemName(province_id[i]));
				shi_id[i] = transNullToString(((Object[]) list.get(i))[17]);//市
				shi_name[i] =transNullToString( commConfigLocationDAO.getItemName(shi_id[i]));
				xian_id[i] = transNullToString(((Object[]) list.get(i))[18]);//县
				xian_name[i] =transNullToString( commConfigLocationDAO.getItemName(xian_id[i]));
				xiang_id[i] = transNullToString(((Object[]) list.get(i))[19]);//乡
				xiang_name[i] =transNullToString( commConfigTownDao.getItemName(xiang_id[i]));
				zheng_id[i] = transNullToString(((Object[]) list.get(i))[20]);//村
				zheng_name[i] =transNullToString( commConfigVillageDao.getItemName(zheng_id[i]));
				
				Date date = (Date)((Object[]) list.get(i))[6];                	
            	String dateString = "";
				if(date != null){
					dateString = format.format(date);
				} 
				fullAddress[i] = province_name[i] + shi_name[i] + xian_name[i] + xiang_name[i] + zheng_name[i];
				
				if(securityUserBaseinfoForm.getLocationLevel()!=null&&securityUserBaseinfoForm.getLocationLevel().equals("1")){
					residentialAddress[i] = xian_name[i] + xiang_name[i] + zheng_name[i];
				}else if(securityUserBaseinfoForm.getLocationLevel()!=null&&securityUserBaseinfoForm.getLocationLevel().equals("2")){
					residentialAddress[i] = xiang_name[i] + zheng_name[i];
				}else{
					residentialAddress[i] = fullAddress[i];
				}
				dateOfBirths[i] = dateString;
				//birthPlaces[i] = transNullToString(((Object[]) list.get(i))[6]);
				inputCodes[i] = transNullToString(((Object[]) list.get(i))[4]);
				mobileTels[i] = this.transNullToString(((Object[]) list.get(i))[29]);
			}
			securityUserBaseinfoForm.setIdList(ids);
			securityUserBaseinfoForm.setNameList(names);
			securityUserBaseinfoForm.setCommConfigSexIdList(commConfigSexIds);
			securityUserBaseinfoForm.setCommConfigSexNameList(commConfigSexId_names);
			securityUserBaseinfoForm.setIdNoList(idNos);
			securityUserBaseinfoForm.setFullAddress(fullAddress);
			securityUserBaseinfoForm.setCommConfigIdTypeList(idTypes);
//			securityUserBaseinfoForm.setCommconfigLocationId3List(xian_id);
//			securityUserBaseinfoForm.setCommconfigLocationId3_nameList(xian_name);
//			securityUserBaseinfoForm.setCommconfigLocationTownIdList(xiang_id);
//			securityUserBaseinfoForm.setCommconfigLocationTownId_nameList(xiang_name);
//			securityUserBaseinfoForm.setCommconfigLocationVillageIdList(zheng_id);
//			securityUserBaseinfoForm.setCommconfigLocationVillageId_nameList(zheng_name);
			securityUserBaseinfoForm.setDateOfBirthList(dateOfBirths);
			securityUserBaseinfoForm.setMobileTelList(mobileTels);
			securityUserBaseinfoForm.setResidentialAddress(residentialAddress);
			//form.setBirthPlaceList(birthPlaces);
			securityUserBaseinfoForm.setInputCodeList(inputCodes);
			securityUserBaseinfoForm.setPmiList(pimList);
		}
	}

	public void getFullForm(SecurityUserBaseinfoForm form,
			SecurityUserBaseinfo data) {
		// TODO Auto-generated method stub
		this.setForm(form, data);
		this.getDetail(form);
	}

	public IHspConfigBaseinfoDAO getHspConfigBaseinfoDAO() {
		return hspConfigBaseinfoDAO;
	}

	public void setHspConfigBaseinfoDAO(IHspConfigBaseinfoDAO hspConfigBaseinfoDAO) {
		this.hspConfigBaseinfoDAO = hspConfigBaseinfoDAO;
	}







	
	public void getTowns(SecurityUserBaseinfoForm form) {
		// TODO Auto-generated method stub
		String[] tempIds = null;
		String[] tempNames = null;
		List<?> list = null;
//		commCltIds & commCltNames;
		if(form.getCommConfigLocationTownId() != null && form.getCommConfigLocationTownId().trim().length() > 0){
			list = this.getSecurityUserBaseinfoDAO().getTownsByParent(form.getCommConfigLocationTownId().trim());
			if(list != null && list.size() > 0){
				tempIds = new String[list.size()+1];
				tempNames = new String[list.size()+1];
				tempIds[0] = "";
				tempNames[0] = "";
				for(int i=0; i<list.size(); i++){
					CommConfigLocationTown town = (CommConfigLocationTown)list.get(i);
					tempIds[i+1] = this.transNullToString(town.getId());
					tempNames[i+1] = this.transNullToString(town.getItemName());
				}
				form.setCommConfigLocationTownIds(tempIds);
				form.setCommConfigLocationTownId_names(tempNames);
			}
		}
	}


	
	public void getVillages(SecurityUserBaseinfoForm form) {
		// TODO Auto-generated method stub
		String[] tempIds = null;
		String[] tempNames = null;
		List<?> list = null;
//		commClvIds & commClvNames;
		if(form.getCommConfigLocationVillageId() != null && form.getCommConfigLocationVillageId().trim().length() > 0){
			list = this.getSecurityUserBaseinfoDAO().getVillagesByParent(form.getCommConfigLocationVillageId().trim());
			if(list != null && list.size() > 0){
				tempIds = new String[list.size()+1];
				tempNames = new String[list.size()+1];
				tempIds[0] = "";
				tempNames[0] = "";
				for(int i=0; i<list.size(); i++){
					CommConfigLocationVillage town = (CommConfigLocationVillage)list.get(i);
					tempIds[i+1] = this.transNullToString(town.getId());
					tempNames[i+1] = this.transNullToString(town.getItemName());
				}
				form.setCommConfigLocationVillageIds(tempIds);
				form.setCommConfigLocationVillageId_names(tempNames);
			}
		}
	}


	public String[] getCommConfigLocationTownId() {
		return commConfigLocationTownId;
	}


	public void setCommConfigLocationTownId(String[] commConfigLocationTownId) {
		this.commConfigLocationTownId = commConfigLocationTownId;
	}


	public String[] getCommConfigLocationTownName() {
		return commConfigLocationTownName;
	}


	public void setCommConfigLocationTownName(String[] commConfigLocationTownName) {
		this.commConfigLocationTownName = commConfigLocationTownName;
	}


	public String[] getCommConfigLocationVillageId() {
		return commConfigLocationVillageId;
	}


	public void setCommConfigLocationVillageId(String[] commConfigLocationVillageId) {
		this.commConfigLocationVillageId = commConfigLocationVillageId;
	}


	public String[] getCommConfigLocationVillageName() {
		return commConfigLocationVillageName;
	}


	public void setCommConfigLocationVillageName(
			String[] commConfigLocationVillageName) {
		this.commConfigLocationVillageName = commConfigLocationVillageName;
	}


	public ICommConfigLocationTownDAO getCommConfigTownDao() {
		return commConfigTownDao;
	}


	public void setCommConfigTownDao(ICommConfigLocationTownDAO commConfigTownDao) {
		this.commConfigTownDao = commConfigTownDao;
	}


	public ICommConfigLocationVillageDAO getCommConfigVillageDao() {
		return commConfigVillageDao;
	}


	public void setCommConfigVillageDao(
			ICommConfigLocationVillageDAO commConfigVillageDao) {
		this.commConfigVillageDao = commConfigVillageDao;
	}


	
	public void getGroup(SecurityUserBaseinfoForm form) {
		// TODO Auto-generated method stub
		String[] tempIds = null;
		String[] tempNames = null;
		List<?> list = null;
//		commClvIds & commClvNames;
		if(form.getCommConfigLocationGroupId() != null && form.getCommConfigLocationGroupId().trim().length() > 0){
			list = this.getSecurityUserBaseinfoDAO().getGroupsByParent(form.getCommConfigLocationGroupId().trim());
			if(list != null && list.size() > 0){
				tempIds = new String[list.size()+1];
				tempNames = new String[list.size()+1];
				tempIds[0] = "";
				tempNames[0] = "";
				for(int i=0; i<list.size(); i++){
					CommConfigLocationGroup town = (CommConfigLocationGroup)list.get(i);
					tempIds[i+1] = this.transNullToString(town.getId());
					tempNames[i+1] = this.transNullToString(town.getItemName());
				}
				form.setCommConfigLocationGroupIds(tempIds);
				form.setCommConfigLocationGroupId_names(tempNames);
			}
		}
	}


	public ICommConfigLocationGroupDAO getCommConfigGroupDao() {
		return commConfigGroupDao;
	}


	public void setCommConfigGroupDao(ICommConfigLocationGroupDAO commConfigGroupDao) {
		this.commConfigGroupDao = commConfigGroupDao;
	}


	public String[] getCommConfigLocationGroupId() {
		return commConfigLocationGroupId;
	}


	public void setCommConfigLocationGroupId(String[] commConfigLocationGroupId) {
		this.commConfigLocationGroupId = commConfigLocationGroupId;
	}


	public String[] getCommConfigLocationGroupName() {
		return commConfigLocationGroupName;
	}


	public void setCommConfigLocationGroupName(String[] commConfigLocationGroupName) {
		this.commConfigLocationGroupName = commConfigLocationGroupName;
	}

	protected void setLocation(SecurityUserBaseinfoForm form,SessionForm sessionForm){
		if(transNullToString(sessionForm.getCommConfigLocationId1()).length()>0)
			form.setCommConfigLocationId1(sessionForm.getCommConfigLocationId1());
		if(transNullToString(sessionForm.getCommConfigLocationId2()).length()>0)
			form.setCommConfigLocationId2(sessionForm.getCommConfigLocationId2());
		if(transNullToString(sessionForm.getCommConfigLocationId3()).length()>0)
			form.setCommConfigLocationId3(sessionForm.getCommConfigLocationId3());
		
	}
	
	public void queryInit(SecurityUserBaseinfoForm form, String id,SessionForm sessionForm) {
		// TODO Auto-generated method stub
		this.setLocation(form, sessionForm);
		this.init(form, id);
	}
	
	
	public ICommonDAO getCommonDAO() {
		return commonDAO;
	}


	public void setCommonDAO(ICommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}
	
	public List<?> getUserBaseinfo(String flag, String input) {
		// TODO Auto-generated method stub
		return this.getSecurityUserBaseinfoDAO().getUserBaseinfo(flag, input);
	}
	
	private void setIdNames(String table, String id, String name,String other){
    	List<?> l = commonDAO.getIdNames(table, id, name,other);
    	if(l != null && l.size() > 0){
    		String[] ids = new String[l.size() + 1];
    		String[] names = new String[l.size() + 1];
    		ids[0] = "";
    		names[0] = "";
    		for(int i = 0; i < l.size(); i++){
    			  ids[i + 1] = this.transNullToString(((Object[])l.get(i))[0]);
    			names[i + 1] = this.transNullToString(((Object[])l.get(i))[1]);
    		}
    		this.setIds(ids);
    		this.setNames(names);
    	} else {
    		String[] ids = new String[1];
    		String[] names = new String[1];
    		ids[0] = "";
    		names[0] = "";
    		this.setIds(ids);
    		this.setNames(names);
    	}
    }
	
	private void setDeprcated(SecurityUserBaseinfo bean, SecurityUserDeprecated data) {
		try {
			data.setId(transNullToString(bean.getId()));
			data.setPmi(transNullToString(bean.getPmi()));
			data.setName(transNullToString(bean.getName()));
			data.setNameEn(transNullToString(bean.getNameEn()));
			data.setInputCode(transNullToString(bean.getInputCode()));
			data.setCommConfigSexId(transNullToString(bean.getCommConfigSexId()));
			data.setCommConfigSexName(transNullToString(bean.getCommConfigSexName()));
			data.setCommConfigCountryId(transNullToString(bean.getCommConfigCountryId()));
			data.setCommConfigCountryName(transNullToString(bean.getCommConfigCountryName()));
			data.setCommConfigNationalityId(transNullToString(bean.getCommConfigNationalityId()));
			data.setCommConfigNationalityName(transNullToString(bean.getCommConfigNationalityName()));
			data.setCommConfigIdTypeId(transNullToString(bean.getCommConfigIdTypeId()));
			data.setCommConfigIdTypeName(transNullToString(bean.getCommConfigIdTypeName()));
			data.setIdNo(transNullToString(bean.getIdNo()));
			data.setSscid(transNullToString(bean.getSscid()));
			data.setCommConfigAboId(transNullToString(bean.getCommConfigAboId()));
			data.setCommConfigAboName(transNullToString(bean.getCommConfigAboName()));
			data.setCommConfigRhId(transNullToString(bean.getCommConfigRhId()));
			data.setCommConfigRhName(transNullToString(bean.getCommConfigRhName()));
			data.setCommConfigDegreeId(transNullToString(bean.getCommConfigDegreeId()));
			data.setCommConfigDegreeName(transNullToString(bean.getCommConfigDegreeName()));
			data.setCommConfigMaritalStatusId(transNullToString(bean.getCommConfigMaritalStatusId()));
			data.setCcmsName(transNullToString(bean.getCcmsName()));
			//居住地址
			data.setCommConfigLocationId1(transNullToString(bean.getCommConfigLocationId1()));
			data.setCommConfigLocationName1(transNullToString(bean.getCommConfigLocationName1()));
			data.setCommConfigLocationId2(transNullToString(bean.getCommConfigLocationId2()));
			data.setCommConfigLocationName2(transNullToString(bean.getCommConfigLocationName2()));
			data.setCommConfigLocationId3(transNullToString(bean.getCommConfigLocationId3()));
			data.setCommConfigLocationName3(transNullToString(bean.getCommConfigLocationName3()));
			data.setCommConfigLocationTownId(transNullToString(bean.getCommConfigLocationTownId()));
			data.setCcltName(transNullToString(bean.getCcltName()));
			data.setCommConfigLocationVillageid(transNullToString(bean.getCommConfigLocationVillageid()));
			data.setCclvName(transNullToString(bean.getCclvName()));
			data.setCommConfigLocationGroupId(transNullToString(bean.getCommConfigLocationGroupId()));
			data.setCclgName(transNullToString(bean.getCclgName()));
			//出生地址
			data.setBirthLocationId1(transNullToString(bean.getBirthLocationId1()));
			data.setBirthLocationName1(transNullToString(bean.getBirthLocationName1()));
			data.setBirthLocationId2(transNullToString(bean.getBirthLocationId2()));
			data.setBirthLocationName2(transNullToString(bean.getBirthLocationName2()));
			data.setBirthLocationId3(transNullToString(bean.getBirthLocationId3()));
			data.setBirthLocationName3(transNullToString(bean.getBirthLocationName3()));
			data.setBirthLocationTownId(transNullToString(bean.getBirthLocationTownId()));
			data.setBirthLocationTownName(transNullToString(bean.getBirthLocationTownName()));
			data.setBirthLocationVillageId(transNullToString(bean.getBirthLocationVillageId()));
			data.setBirthLocationVillageName(transNullToString(bean.getBirthLocationVillageName()));
			data.setBirthLocationGroupId(transNullToString(bean.getBirthLocationGroupId()));
			data.setBirthLocationGroupName(transNullToString(bean.getBirthLocationGroupName()));
			//户籍地址
			data.setCensusLocationId1(transNullToString(bean.getCensusLocationId1()));
			data.setCensusLocationName1(transNullToString(bean.getCensusLocationName1()));
			data.setCensusLocationId2(transNullToString(bean.getCensusLocationId2()));
			data.setCensusLocationName2(transNullToString(bean.getCensusLocationName2()));
			data.setCensusLocationId3(transNullToString(bean.getCensusLocationId3()));
			data.setCensusLocationName3(transNullToString(bean.getCensusLocationName3()));
			data.setCensusLocationTownId(transNullToString(bean.getCensusLocationTownId()));
			data.setCensusLocationTownName(transNullToString(bean.getCensusLocationTownName()));
			data.setCensusLocationVillageId(transNullToString(bean.getCensusLocationVillageId()));
			data.setCensusLocationVillageName(transNullToString(bean.getCensusLocationVillageName()));
			data.setCensusLocationGroupId(transNullToString(bean.getCensusLocationGroupId()));
			data.setCensusLocationGroupName(transNullToString(bean.getCensusLocationGroupName()));
			//门牌号
			data.setDoorNo(transNullToString(bean.getDoorNo()));
			data.setCensusDoorNo(this.transNullToString(bean.getCensusDoorNo()));
			data.setBirthDoorNo(this.transNullToString(bean.getBirthDoorNo()));
			data.setZipcode(transNullToString(bean.getZipcode()));
			data.setPhone(transNullToString(bean.getPhone()));
			data.setEMail(transNullToString(bean.getEMail()));
			data.setMobileTel(transNullToString(bean.getMobileTel()));
			data.setPhotoPath(transNullToString(bean.getPhotoPath()));
			data.setCommConfigRelationshipId(transNullToString(bean.getCommConfigRelationshipId()));
			data.setCommConfigRelationshipName(transNullToString(bean.getCommConfigRelationshipName()));
			data.setContactPersonName(transNullToString(bean.getContactPersonName()));
			data.setContactPersonPhone(transNullToString(bean.getContactPersonPhone()));
			data.setComments(transNullToString(bean.getComments()));
			data.setDateOfBirth(bean.getDateOfBirth());
			data.setCreateUserId(bean.getCreateUserId());
			data.setDeceasedInd(transNullToString(bean.getDeceasedInd()));
			data.setDeceasedIndName(transNullToString(bean.getDeceasedIndName()));
			data.setDeceasedTime(bean.getDeceasedTime());
			data.setMotherPID(transNullToString(bean.getMotherPID()));
			data.setMotherName(transNullToString(bean.getMotherName()));
			data.setBirthSequence(transNullToString(bean.getBirthSequence()));
			data.setMultipleBirthInd(transNullToString(bean.getMultipleBirthInd()));
			data.setMultipleBirthindName(transNullToString(bean.getMultipleBirthindName()));
			data.setMultipleBirthOrderNumber(transNullToString(bean.getMultipleBirthOrderNumber()));
			data.setOccupationCodeId(transNullToString(bean.getOccupationCodeId()));
			data.setOccupationCodeName(transNullToString(bean.getOccupationCodeName()));
			
			data.setLanguageId(transNullToString(bean.getLanguageId()));
			data.setLanguageName(transNullToString(bean.getLanguageName()));
			data.setReligionId(transNullToString(bean.getReligionId()));
			data.setReligionName(transNullToString(bean.getReligionName()));
			data.setDeiverLicense(transNullToString(bean.getDeiverLicense()));
			data.setCitizenShipId(transNullToString(bean.getCitizenShipId()));
			data.setCitizenShipName(transNullToString(bean.getCitizenShipName()));
			data.setRetiredStatus(transNullToString(bean.getRetiredStatus()));
			data.setUserAccount(transNullToString(bean.getUserAccount()));
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public HSSFWorkbook saveExport(SecurityUserBaseinfoForm hosform,String staffId,String staffIdName) {
		//List<?> lists= commonDAO.findListByHql(hql);
		int count = 0;
		int pageSize = 9999;
		List<?> lists= this.getSecurityUserBaseinfoDAO().getUsers(hosform, count, pageSize);
		if(lists!=null&&lists.size()>0){
			return this.saveExoprt(lists, staffId,staffIdName);
		}
		return null;
	}
	
	private HSSFWorkbook saveExoprt(List<?> list,String staffid,String staffname){
		try {
			//申明工作簿的第一张工作表的名字(目前不能中文)
			String table_name="sheet1";
			table_name=getUnicode(table_name, "gb2312");
			//创建对工作表的引用。
			HSSFWorkbook workbook=new HSSFWorkbook();
			HSSFSheet sheet=workbook.createSheet(table_name);
			
			int header_number=0;//表头开始
			int title_number=1;//标题开始行
			int over_number=25;//总列数
			
			HSSFRow row=null;
			HSSFCell cell=null;
			String text="";
			HSSFFont font = workbook.createFont();
			font.setColor(HSSFFont.COLOR_NORMAL); 
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

			HSSFCellStyle cellStyle= workbook.createCellStyle();
			//定义表格样式
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
			row = sheet.createRow((short)header_number);
			cell = row.createCell((short)0);
			row.setHeight((short)100);
			row.setHeightInPoints((float)30);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellStyle(HeaderStyle);
			text += "客户基本信息表";
			text = getUnicode(text,"gb2312");
			cell.setCellValue(text);
			sheet.addMergedRegion(new Region(header_number,(short)(0),header_number,(short)(over_number-1)));
			//插入标题
			row = sheet.createRow((short)title_number);
			row.setHeight((short)585);
			cell = row.createCell((short)0);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellStyle(cellStyle);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
			SimpleDateFormat sdf1=new SimpleDateFormat("yyyyMMddHHmmss");
			text = " 生成时间："+sdf.format(new Date())+"     导出批号："+sdf1.format(new Date());
			text = getUnicode(text,"gb2312");
			cell.setCellValue(text);
			
			for(int i=1; i<over_number; i++){
				cell = row.createCell((short)i);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellStyle(cellStyle);
				text = "";
				text = getUnicode(text,"gb2312");
				cell.setCellValue(text);
			}
			sheet.addMergedRegion(new Region(title_number, (short)(0), title_number, (short)(over_number-1)));
			row = sheet.createRow((short)2);
			row.setHeight((short)585);
			setCellText(row,cell,0,"姓名",cellStyle);
			setCellText(row,cell,1,"性别",cellStyle);
			setCellText(row,cell,2,"出生地",cellStyle);
			setCellText(row,cell,3,"出生日期",cellStyle);
			setCellText(row,cell,4,"国籍",cellStyle);
			setCellText(row,cell,5,"民族",cellStyle);
			setCellText(row,cell,6,"证件号码",cellStyle);
			setCellText(row,cell,7,"实名卡卡号",cellStyle);
			setCellText(row,cell,8,"社会保障卡号",cellStyle);
			
			setCellText(row,cell,9,"新农合卡号",cellStyle);
			setCellText(row,cell,10,"医保卡",cellStyle);
			setCellText(row,cell,11,"所属省",cellStyle);
			setCellText(row,cell,12,"所属市",cellStyle);
			setCellText(row,cell,13,"所属县",cellStyle);
			setCellText(row,cell,14,"所属镇(乡)",cellStyle);
			setCellText(row,cell,15,"村",cellStyle);
			setCellText(row,cell,16,"组",cellStyle);
			setCellText(row,cell,17,"单位名称",cellStyle);
			setCellText(row,cell,18,"邮政编码",cellStyle);
			setCellText(row,cell,19,"居住电话",cellStyle);
			
			setCellText(row,cell,20,"手机号",cellStyle);
			setCellText(row,cell,21,"E_mail",cellStyle);
			setCellText(row,cell,22,"照片地址",cellStyle);
			setCellText(row,cell,23,"备注",cellStyle);
			setCellText(row,cell,24,"居民ID",cellStyle);
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
//					SecurityUserBaseinfo info=(SecurityUserBaseinfo)list.get(i);
					row=sheet.createRow((short)(i+3));
					setCellText(row, cell, 0, transNullToString(((Object[]) list.get(i))[2]), cellStyle);
//					if(!Converter.toBlank(info.getCommConfigSexId()).equals("")){
//						String sex=securityUserBaseinfoDAO.getcommConfigSex(info.getCommConfigSexId());
//						setCellText(row, cell, 1, Converter.toBlank(sex), cellStyle);
//						derail.setCommConfigSexId(info.getCommConfigSexId());
//						derail.setCommConfigSexName(sex);
//					}else{
//						setCellText(row, cell, 1, Converter.toBlank(""), cellStyle);
//					}
					if(transNullToString(((Object[]) list.get(i))[5]) != null && transNullToString(((Object[]) list.get(i))[5]).length()>0){
						setCellText(row, cell, 1, commConfigSexDAO.getItemName(transNullToString(((Object[]) list.get(i))[5])), cellStyle);
					}else{
						setCellText(row, cell, 1, "", cellStyle);
					}
					
					
					setCellText(row, cell, 2, transNullToString(((Object[]) list.get(i))[30])+transNullToString(((Object[]) list.get(i))[31])
							+transNullToString(((Object[]) list.get(i))[32])+transNullToString(((Object[]) list.get(i))[33])
							+transNullToString(((Object[]) list.get(i))[34])+transNullToString(((Object[]) list.get(i))[35]), cellStyle);
					
					if((Date)((Object[]) list.get(i))[6]!=null){
						setCellText(row, cell, 3, format.format((Date)((Object[]) list.get(i))[6]), cellStyle);
						//derail.setDateOfBirth(sdf1.parse(Converter.toBlank(info.getDateOfBirth())));
					}else{
						setCellText(row, cell, 3, Converter.toBlank(""), cellStyle);
					}
					
					if(transNullToString(((Object[]) list.get(i))[7])!=null&&transNullToString(((Object[]) list.get(i))[7]).length()>0){
						String itemName=commonDAO.getNameById("CommConfigCountry", "itemCode", "itemName", transNullToString(((Object[]) list.get(i))[8]));
						setCellText(row, cell, 4, itemName, cellStyle);
//						derail.setCommConfigCountryId(Converter.toBlank(info.getCommConfigCountryId()));
//						derail.setCommConfigCountryName(itemName);
					}else{
						setCellText(row, cell, 4, Converter.toBlank(""), cellStyle);
					}
					
//					setCellText(row, cell, 4, transNullToString(((Object[]) list.get(i))[38]), cellStyle);
					
					if(transNullToString(((Object[]) list.get(i))[8])!=null&&transNullToString(((Object[]) list.get(i))[8]).length()>0){
						String itemName=commonDAO.getNameById("CommConfigNationality", "itemCode", "itemName", transNullToString(((Object[]) list.get(i))[9]));
						setCellText(row, cell, 5, Converter.toBlank(itemName), cellStyle);
//						derail.setCommConfigNationalityId(info.getCommConfigNationalityId());
//						derail.setCommConfigNationalityName(itemName);
					}else{
						setCellText(row, cell, 5, Converter.toBlank(""), cellStyle);
					}
					
//					setCellText(row, cell, 5, transNullToString(((Object[]) list.get(i))[37]), cellStyle);
					
					setCellText(row, cell, 6, transNullToString(((Object[]) list.get(i))[10]), cellStyle);
					setCellText(row, cell, 7, Converter.toBlank(""), cellStyle);
					setCellText(row, cell, 8, transNullToString(((Object[]) list.get(i))[11]), cellStyle);
					setCellText(row, cell, 9, Converter.toBlank(""), cellStyle);
					setCellText(row, cell, 10, Converter.toBlank(""), cellStyle);
					if(transNullToString(((Object[]) list.get(i))[16])!=null&&transNullToString(((Object[]) list.get(i))[16]).length()>0){
						String itemName=commonDAO.getNameById("CommConfigLocation", "itemCode", "itemName", transNullToString(((Object[]) list.get(i))[16]));
						setCellText(row, cell, 11, Converter.toBlank(itemName), cellStyle);
//						derail.setCommConfigLocationId1(info.getCommConfigLocationId1());
//						derail.setCommConfigLocationName1(itemName);
					}else{
						setCellText(row, cell, 11, Converter.toBlank(""), cellStyle);
					}
					
//					setCellText(row, cell, 11, Converter.toBlank(info.getCommConfigLocationName1()), cellStyle);
					
					if(transNullToString(((Object[]) list.get(i))[17])!=null&&transNullToString(((Object[]) list.get(i))[17]).length()>0){
						String itemName=commonDAO.getNameById("CommConfigLocation", "itemCode", "itemName", transNullToString(((Object[]) list.get(i))[17]));
						setCellText(row, cell, 12, Converter.toBlank(itemName), cellStyle);
//						derail.setCommConfigLocationId2(info.getCommConfigLocationId2());
//						derail.setCommConfigLocationName2(itemName);
					}else{
						setCellText(row, cell, 12, Converter.toBlank(""), cellStyle);
					}
					
//					setCellText(row, cell, 12, Converter.toBlank(info.getCommConfigLocationName2()), cellStyle);
					
					if(transNullToString(((Object[]) list.get(i))[18])!=null&&transNullToString(((Object[]) list.get(i))[18]).length()>0){
						String itemName=commonDAO.getNameById("CommConfigLocation", "itemCode", "itemName", transNullToString(((Object[]) list.get(i))[18]));
						setCellText(row, cell, 13, Converter.toBlank(itemName), cellStyle);
//						derail.setCommConfigLocationId3(info.getCommConfigLocationId3());
//						derail.setCommConfigLocationName3(itemName);
					}else{
						setCellText(row, cell, 13, Converter.toBlank(""), cellStyle);
					}
					
//					setCellText(row, cell, 13, Converter.toBlank(info.getCommConfigLocationName3()), cellStyle);
					
					if(transNullToString(((Object[]) list.get(i))[19])!=null&&transNullToString(((Object[]) list.get(i))[19]).length()>0){
						String itemName=commonDAO.getNameById("CommConfigLocationTown", "itemCode", "itemName", transNullToString(((Object[]) list.get(i))[19]));
						setCellText(row, cell, 14, Converter.toBlank(itemName), cellStyle);
//						derail.setCommConfigLocationTownId(info.getCommConfigLocationTownId());
//						derail.setCcltName(itemName);
					}else{
						setCellText(row, cell, 14, Converter.toBlank(""), cellStyle);
					}
					
//					setCellText(row, cell, 14, Converter.toBlank(info.getCcltName()), cellStyle);
					
					
					if(transNullToString(((Object[]) list.get(i))[20])!=null && transNullToString(((Object[]) list.get(i))[20]).length()>0){
						String itemName=commonDAO.getNameById("CommConfigLocationVillage", "itemCode", "itemName", transNullToString(((Object[]) list.get(i))[20]));
						setCellText(row, cell, 15, Converter.toBlank(itemName), cellStyle);
//						derail.setCommConfigLocationVillageid(info.getCommConfigLocationVillageid());
//						derail.setCclvName(itemName);
					}else{
						setCellText(row, cell, 15, Converter.toBlank(""), cellStyle);
					}
					
//					setCellText(row, cell, 15, Converter.toBlank(info.getCclvName()), cellStyle);
					
					if(transNullToString(((Object[]) list.get(i))[36])!=null && transNullToString(((Object[]) list.get(i))[36]).length()>0){
						String itemName=commonDAO.getNameById("CommConfigLocationGroup", "itemCode", "itemName", transNullToString(((Object[]) list.get(i))[36]));
						setCellText(row, cell, 16, Converter.toBlank(itemName), cellStyle);
//						derail.setCommConfigLocationGroupId(info.getCommConfigLocationGroupId());
//						derail.setGroupName(itemName);
					}else{
						setCellText(row, cell, 16, Converter.toBlank(""), cellStyle);
					}
					
//					setCellText(row, cell, 16, Converter.toBlank(info.getCclgName()), cellStyle);
					
					setCellText(row, cell, 17, transNullToString(((Object[]) list.get(i))[37])+transNullToString(((Object[]) list.get(i))[38])+
							transNullToString(((Object[]) list.get(i))[39])+transNullToString(((Object[]) list.get(i))[40])+
							transNullToString(((Object[]) list.get(i))[41])+transNullToString(((Object[]) list.get(i))[42]), cellStyle);
					setCellText(row, cell, 18, transNullToString(((Object[]) list.get(i))[21]), cellStyle);
					setCellText(row, cell, 19, transNullToString(((Object[]) list.get(i))[22]), cellStyle);
					setCellText(row, cell, 20, transNullToString(((Object[]) list.get(i))[29]), cellStyle);
					setCellText(row, cell, 21, transNullToString(((Object[]) list.get(i))[23]), cellStyle);
					setCellText(row, cell, 22, transNullToString(((Object[]) list.get(i))[24]), cellStyle);
					setCellText(row, cell, 23, transNullToString(((Object[]) list.get(i))[28]), cellStyle);
					setCellText(row, cell, 24, transNullToString(((Object[]) list.get(i))[0]), cellStyle);
					//setCellText(row, cell, 25, Converter.toBlank(info.getCreateUserName()), cellStyle);
					sheet.setColumnWidth((short)0, (short)5000);
					sheet.setColumnWidth((short)1, (short)1500);
					sheet.setColumnWidth((short)2, (short)7000);
					sheet.setColumnWidth((short)3, (short)6000);
					sheet.setColumnWidth((short)4, (short)3000);
					sheet.setColumnWidth((short)5, (short)3000);
					sheet.setColumnWidth((short)6, (short)6000);
					sheet.setColumnWidth((short)7, (short)3000);
					sheet.setColumnWidth((short)8, (short)3000);
					sheet.setColumnWidth((short)9, (short)3000);
					sheet.setColumnWidth((short)10, (short)3000);
					sheet.setColumnWidth((short)11, (short)3000);
					sheet.setColumnWidth((short)12, (short)3000);
					sheet.setColumnWidth((short)13, (short)3000);
					sheet.setColumnWidth((short)14, (short)3000);
					sheet.setColumnWidth((short)15, (short)3000);
					sheet.setColumnWidth((short)16, (short)3000);
					sheet.setColumnWidth((short)17, (short)7000);
					sheet.setColumnWidth((short)18, (short)3000);
					sheet.setColumnWidth((short)19, (short)3000);
					sheet.setColumnWidth((short)20, (short)3000);
					sheet.setColumnWidth((short)21, (short)3000);
					sheet.setColumnWidth((short)22, (short)3000);
					sheet.setColumnWidth((short)23, (short)3000);
					sheet.setColumnWidth((short)24, (short)6000);
					//sheet.setColumnWidth((short)25, (short)5000);
				}
				sheet.addMergedRegion(new Region(header_number, (short)(0), header_number, (short)(over_number-1)));
				return workbook;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}


