package com.tianjian.comm.dao;

import java.util.List;

import com.tianjian.util.comm.PageBean;
import com.tianjian.comm.bean.CommDictPublicChar;
import com.tianjian.comm.bean.CommDictPublicClass;
import com.tianjian.comm.bean.CommDictPublicDouble;
import com.tianjian.comm.bean.CommDictPublicInt;


 
public interface IDictListDAO {
	  
	public List<?> listAllDict(PageBean p);
	public Integer getCount(PageBean p);
	public CommDictPublicClass getOnePublicDict(String id);
	public CommDictPublicChar getOneCharDict(String id1,String id2);
	public CommDictPublicInt getOneIntDict(String id1,String id2);
	public CommDictPublicDouble getOneDoubleDict(String id1,String id2);
	public List<?> listAllDict();
 
	//罗巍填加
	
	public List<?> listpubliccharbyClasscodeDao(String classcode);
	
	public CommDictPublicChar listpubliccharbyclasscAnddictcode(String classcode,String dictcode);
	
	public List<?> listpubliccharbyCcodeAndIcodeDao(String ccode,String icode);
	
	public List<?> listpublicchinesebyCcodeAndIcode(String ccode,String icode);
	
	public List<?> listpublicnumberbyCcodeAndIcode(String ccode,String icode);
}
