package com.tianjian.comm.business;

import java.util.List;

import com.tianjian.util.comm.PageBean;
import com.tianjian.comm.bean.CommDictPublicChar;
import com.tianjian.comm.bean.CommDictPublicClass;
import com.tianjian.comm.bean.CommDictPublicDouble;
import com.tianjian.comm.bean.CommDictPublicInt;

public interface IListLogicService {
    /**获取Public当前页字典列表数目*/
	public Integer getPublicCount(PageBean p,String class_name,String class_code,String input_code);
	/**获取Char当前页字典列表数目*/
	public Integer getCharCount(PageBean p,String class_code,String dict_code,String dict_value,String input_code);
	/**获取Double当前页字典列表数目*/
	public Integer getDoubleCount(PageBean p,String class_code,String dict_code,String dict_value,String input_code);
	/**获取Int当前页字典列表数目*/
	public Integer getIntCount(PageBean p,String class_code,String dict_code,String dict_value,String input_code);
	/**获取Public当前字典列表*/
	public List<?> ListPublicDict(PageBean p,String method,String type ,String class_name,String class_code,String input_code);
	/**获取Char当前字典列表*/
	public List<?> ListCharDict(PageBean p,String method,String type,String class_code,String dict_code,String dict_value,String input_code);
	/**获取Double当前字典列表*/
	public List<?> ListDoubleDict(PageBean p,String method,String type,String class_code,String dict_code,String dict_value,String input_code);
	/**获取Int当前字典列表*/
	public List<?> ListIntDict(PageBean p,String method,String type,String class_code,String dict_code,String dict_value,String input_code);
	
	
	/**获取Class当前字典对象*/
	public CommDictPublicClass ListOnePublicDict(String  id);
	/**获取Char当前字典对象*/
	public CommDictPublicChar ListOneCharDict(String  id1,String id2);
	/**获取Double当前字典对象*/
	public CommDictPublicDouble ListOneDoubleDict(String  id1,String id2);
	/**获取Int当前字典对象*/
	public CommDictPublicInt ListOneIntDict(String  id1,String id2);
	/**获取所有当前字典对象*/
	public List<?> ListAllPublicDict();
	
	//罗巍填加
	/**通过classcode获取所有当前字典对象*/
	public List<?> listpubliccharbyClasscode(String classcode);
	/**通过classCode和inputCode获取所有当前字典对象*/
	public List<?> listpubliccharbyCcodeAndIcode(String ccode,String icode);
	/**通过classCode和inputCode获取所有当前字典对象*/
	public List<?> listpublicchinesebyCcodeAndIcode(String ccode,String icode);
	/**通过classCode和inputCode获取所有当前字典对象*/
	public List<?> listpublicnumberbyCcodeAndIcode(String ccode,String icode);
	/**通过classCode和dictcode获取所有当前字典对象*/
	public CommDictPublicChar listpubliccharbyclasscAnddictcode(String classcode,String dictcode);
}
