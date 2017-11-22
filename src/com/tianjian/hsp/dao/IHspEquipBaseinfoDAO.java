package com.tianjian.hsp.dao;

import java.util.List;

import com.tianjian.hsp.bean.HspEquipBaseinfo;
import com.tianjian.hsp.struts.form.HspEquipBaseinfoForm;

public interface IHspEquipBaseinfoDAO {

	List<?> findEquipList(HspEquipBaseinfoForm form,int curCount,int count);

	int getCount(HspEquipBaseinfoForm form);
	
	public HspEquipBaseinfo getById(String id);
	
	public String save(HspEquipBaseinfo heb);
	
	public void update(HspEquipBaseinfo heb);
	
	public void delete(HspEquipBaseinfo heb);
	
	public String getHspNameByCode(String hspCode);
	
	public String[] getHspCodeAndNameById(String hspId);
	
	public String getUseageName(String useageCode);
	
    public String getUnitName(String unitCode);
    
    public String getStateName(String stateName);

	Object getCountryName(String source);
	
	public List<?> getUseageList();
	
    public List<?> getUnitList();
    
    public List<?> getStateList();

    public String[] getHspIdNameByCode(String hspCode);

}
