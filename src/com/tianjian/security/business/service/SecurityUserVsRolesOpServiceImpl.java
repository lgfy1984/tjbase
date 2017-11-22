package com.tianjian.security.business.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.tianjian.comm.dao.ICommonDAO;
import com.tianjian.security.bean.SecurityStaffBaseinfo;
import com.tianjian.security.business.ISecurityUserVsRolesOpService;
import com.tianjian.security.dao.ISecurityUserVsRolesOpDAO;
import com.tianjian.security.struts.form.SecurityUserVsRolesOpForm;
import com.tianjian.util.ResourcesUtil;

public class SecurityUserVsRolesOpServiceImpl implements ISecurityUserVsRolesOpService{
    
	private static final Logger log = LogManager.getLogger(SecurityStaffBaseinfoServiceImpl.class);
	
	private ICommonDAO commonDAO;
	private ISecurityUserVsRolesOpDAO  securityUserVsRolesOpDAO;

	// ------------引入dao接口------------------------------start-------------------------------------
	/**
	 * @return Returns the hspConfigBaseinfoDAO.
	 */
	public ICommonDAO getCommonDAO() {
		return commonDAO;
	}

	public void setCommonDAO(ICommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}
	
	public ISecurityUserVsRolesOpDAO getSecurityUserVsRolesOpDAO() {
		return securityUserVsRolesOpDAO;
	}

	public void setSecurityUserVsRolesOpDAO(
			ISecurityUserVsRolesOpDAO securityUserVsRolesOpDAO) {
		this.securityUserVsRolesOpDAO = securityUserVsRolesOpDAO;
	}

	/** 在进行查找之前将字典库传入form中 */
	public void searchInit(SecurityUserVsRolesOpForm form) {
		init(form);
	}
	
	public int getCount(String securityConfigRolesId, String hspConfigBaseinfoId){
		return securityUserVsRolesOpDAO.getCount(securityConfigRolesId, hspConfigBaseinfoId);
	}

	public void getSearch(SecurityUserVsRolesOpForm form, int curCount, int pageSize) {
		try{
			String order = "";
			if(form.getOrderNo().equals("1")){
				order = " a.staffCode";
			} else if(form.getOrderNo().equals("2")){
				order = " a.name";
			} else if(form.getOrderNo().equals("3")){
				order = " c.itemName";
			} else if(form.getOrderNo().equals("4")){
				order = " b.securityConfigRolesId";
			}   else if(form.getOrderNo().equals("5")){
				order = " a.commConfigSexId";
			} else if(form.getOrderNo().equals("6")){
				order = " a.dateOfBirth";
			} else {
				order = " a.staffCode";
			}

			if(form.getAsc().equals("1")){
				order += " desc";
			} else {
				order += " asc";
			}

			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy"); 
			List<?> list = securityUserVsRolesOpDAO.getData(form.getSecurityConfigRolesId(), form.getHspConfigBaseinfoId(), order, curCount, pageSize);
			if(list != null && list.size() > 0) {
				String[] idList                    = new String[list.size()];
				String[] staffCodeList             = new String[list.size()];
				String[] hspConfigBaseinfoIdList   = new String[list.size()];
				String[] hspConfigBaseinfoNameList = new String[list.size()];
				String[] nameList                  = new String[list.size()];
				String[] commConfigSexIdList       = new String[list.size()];
				String[] commConfigSexNameList     = new String[list.size()];
				String[] dateOfBirthList           = new String[list.size()];
                String[] securityConfigRolesNameList =  new  String[list.size()];
				for(int i = 0; i < list.size(); i++) {
					
					idList[i] 					 	= this.transNullToString(((Object[])list.get(i))[0]);
					staffCodeList[i] 				= this.transNullToString(((Object[])list.get(i))[1]);
					securityConfigRolesNameList[i]   = this.transNullToString(commonDAO.getNameById("SecurityConfigRoles", "id", "roleDetail", this.transNullToString(((Object[])list.get(i))[13])));
					hspConfigBaseinfoIdList[i] 		= this.transNullToString(((Object[])list.get(i))[2]);
					nameList[i] 					= this.transNullToString(((Object[])list.get(i))[3]);
					commConfigSexIdList[i] 			= this.transNullToString(((Object[])list.get(i))[6]);
					Object date = ((Object[])list.get(i))[7];                	
					String dateString = "";
					if(date != null){
						dateString = format.format(date);
					} 
					dateOfBirthList[i] 				= this.transNullToString(dateString);
					hspConfigBaseinfoNameList[i] 	= this.transNullToString(((Object[])list.get(i))[14]);
					commConfigSexNameList[i] 		= this.transNullToString(commonDAO.getNameById("CommConfigSex", "itemCode", "itemName", commConfigSexIdList[i]));
				}

				form.setIdList                    (idList                    );
				form.setStaffCodeList             (staffCodeList             );
				form.setSecurityConfigRolesNameList(securityConfigRolesNameList );
				form.setSecurityConfigRolesNameList(securityConfigRolesNameList);
				form.setHspConfigBaseinfoIdList   (hspConfigBaseinfoIdList   );
				form.setHspConfigBaseinfoNameList (hspConfigBaseinfoNameList );
				form.setNameList                  (nameList                  );
				form.setCommConfigSexIdList       (commConfigSexIdList       );
				form.setCommConfigSexNameList     (commConfigSexNameList     );
				form.setDateOfBirthList           (dateOfBirthList           );

			}
		} catch(Exception e) {
			log.error("getSearch fail!", e);
			e.printStackTrace();
		}
	}
	
	public void getDetail(SecurityUserVsRolesOpForm form){
		SecurityStaffBaseinfo data = securityUserVsRolesOpDAO.findById(form.getId());
		setForm(form, data);
	}
	
	/*在ActionForm中应放置名称Name，有Id的将之换成Name*/
	private void setForm(SecurityUserVsRolesOpForm form, SecurityStaffBaseinfo data) {
		try {
			form.setId                      (this.transNullToString(data.getId                     ()));
			form.setStaffCode               (this.transNullToString(data.getStaffCode              ()));
			form.setHspConfigBaseinfoId     (this.transNullToString(data.getHspConfigBaseinfoId    ()));
			form.setHspConfigBaseinfoName	(this.transNullToString(commonDAO.getNameById("HspConfigBaseinfoLocalBase", "id", "itemName", form.getHspConfigBaseinfoId())));		
			form.setName                    (this.transNullToString(data.getName                   ()));
			form.setNameEn                  (this.transNullToString(data.getNameEn                 ()));
			form.setInputCode               (this.transNullToString(data.getInputCode              ()));
			form.setCommConfigSexId         (this.transNullToString(data.getCommConfigSexId        ()));
			form.setCommConfigSexName		(this.transNullToString(commonDAO.getNameById("CommConfigSex", "itemCode", "itemName", form.getCommConfigSexId())));			
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy"); 
			Date date = data.getDateOfBirth();
			if(date==null){
				form.setDateOfBirth             (this.transNullToString(""));
			}else{
				form.setDateOfBirth             (this.transNullToString(format.format(date)));
			}
			form.setCommConfigStafftypeId   (this.transNullToString(data.getCommConfigStafftypeId  ()));
			form.setCommConfigStafftypeName	(this.transNullToString(commonDAO.getNameById("CommConfigStafftype", "itemCode", "itemName", form.getCommConfigStafftypeId())));
			form.setIdNo                    (this.transNullToString(data.getIdNo                   ()));
			form.setPhone                   (this.transNullToString(data.getPhone                  ()));
			form.setIslocation              (this.transNullToString(data.getIslocation().toString  ()));
			form.setIslocationName			(this.transNullToString(this.getIslocations(form.getIslocation())));
			form.setComments                (this.transNullToString(data.getComments               ()));
		} catch(Exception e) {
			log.error("setForm fail!", e);
			e.printStackTrace();
		}
	}

	private String getIslocations(String id){
		String temp = "";
		if(id.equals("1")){
			//"在";
			temp =  ResourcesUtil.getValue("conf.security.securityInit", "security.java.commom.item", request);
		} else if(id.equals("0")){
			temp =  ResourcesUtil.getValue("conf.security.securityInit", "security.java.commom.item1", request);
			//temp = "不在";
		}
		return temp;
	}
	/** 在初始化阶段将字典库传入form中 */
	private void init(SecurityUserVsRolesOpForm form) {
		
		this.setIdNames("SecurityConfigRoles", "id", "roleDetail");
    	form.setSecurityConfigRolesIds(this.getIds());
    	form.setSecurityConfigRolesNames(this.getNames());
    	this.setIdNames("HspConfigBaseinfo", "id", "itemName");
    	form.setHspConfigBaseinfoIds(this.getIds());
    	form.setHspConfigBaseinfoNames(this.getNames());
	}
	
	private void setIdNames(String table, String id, String name){
    	List<?> l = commonDAO.getIdNames(table, id, name,"");
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
	
	public String transNullToString(Object obj) {
		String temp = "";
		if (obj != null) {
			temp = String.valueOf(obj).trim();
		}
		return temp;
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
	
	
	private HttpServletRequest request;
	
	
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
}

