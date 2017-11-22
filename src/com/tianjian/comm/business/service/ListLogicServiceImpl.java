package com.tianjian.comm.business.service;

 

import java.util.List;

import com.tianjian.util.comm.PageBean;
import com.tianjian.comm.bean.CommDictPublicChar;
import com.tianjian.comm.bean.CommDictPublicClass;
import com.tianjian.comm.bean.CommDictPublicDouble;
import com.tianjian.comm.bean.CommDictPublicInt;
import com.tianjian.comm.business.IListLogicService;
import com.tianjian.comm.dao.IDictListDAO;



public class ListLogicServiceImpl implements IListLogicService{

	private IDictListDAO dao;

	public IDictListDAO getDao() {
		return dao;
	}

	public void setDao(IDictListDAO dao) {
		this.dao = dao;
	}
	 /**获取Public当前页字典列表数目*/
	public Integer getPublicCount(PageBean p,String class_name,String class_code,String input_code) {
		
		String s = "select count(*) from com.tianjian.comm.bean.CommDictPublicClass as d";
		
		String sql =  getPublicSql(s,class_name, class_code, input_code);
		
		p.setTotalCountSQL(sql);
		
		return dao.getCount(p);
	}
	/**获取Public当前字典列表*/
	public List<?> ListPublicDict(PageBean p,String method,String type ,String class_name,String class_code,String input_code) {
		 
		String s = "from com.tianjian.comm.bean.CommDictPublicClass as d";
		
		String sql =  getPublicSql(s,class_name, class_code, input_code);
		
		
		if("classCode".equals(type))
		{
			if("up".equals(method))
			{
				sql = sql +" order by d.classCode asc";
			}else
			{
				sql = sql +" order by d.classCode desc";
							
			}
		}
		if("className".equals(type))
		{
			if("up".equals(method))
			{
				sql = sql +" order by d.className asc";
			}else
			{
				sql = sql +" order by d.className desc";
							
			}
		}
		if("inputCode".equals(type))
		{
			if("up".equals(method))
			{
				sql = sql +" order by d.inputCode asc";
			}else
			{
				sql = sql +" order by d.inputCode desc";
							
			}
		}
		
		
		
		
		System.out.println(sql);
		p.setListSQL(sql);

		List<?> dict = dao.listAllDict(p);
		return dict;

	}
	/**获取Char当前字典列表*/
	public List<?> ListCharDict(PageBean p,String method,String type,String class_code,String dict_code,String dict_value,String input_code) {
		 
		String s = "from com.tianjian.comm.bean.CommDictPublicChar as d";
		String sql =  getCharSql(s,class_code, dict_code,dict_value, input_code);
		
		
		if("classCode".equals(type))
		{
			if("up".equals(method))
			{
				sql = sql +" order by d.classCode asc";
			}else
			{
				sql = sql +" order by d.classCode desc";
							
			}
		}
		if("dictCode".equals(type))
		{
			if("up".equals(method))
			{
				sql = sql +" order by d.dictCode asc";
			}else
			{
				sql = sql +" order by d.dictCode desc";
							
			}
		}
		if("dictValue".equals(type))
		{
			if("up".equals(method))
			{
				sql = sql +" order by d.dictValue asc";
			}else
			{
				sql = sql +" order by d.dictValue desc";
							
			}
		}
		if("inputCode".equals(type))
		{
			if("up".equals(method))
			{
				sql = sql +" order by d.inputCode asc";
			}else
			{
				sql = sql +" order by d.inputCode desc";
							
			}
		} 
		
		
		p.setListSQL(sql);

		List<?> dict = dao.listAllDict(p);
		return dict;

	}
	/**获取Double当前字典列表*/
	public List<?> ListDoubleDict(PageBean p,String method,String type,String class_code,String dict_code,String dict_value,String input_code) {
		String s  = "from com.tianjian.comm.bean.CommDictPublicDouble as d";
		
	 	String sql =  getCharSql(s,class_code, dict_code,dict_value, input_code);
	
		
		
		if("classCode".equals(type))
		{
			if("up".equals(method))
			{
				sql = sql +" order by d.classCode asc";
			}else
			{
				sql = sql +" order by d.classCode desc";
							
			}
		}
		if("dictCode".equals(type))
		{
			if("up".equals(method))
			{
				sql = sql +" order by d.dictCode asc";
			}else
			{
				sql = sql +" order by d.dictCode desc";
							
			}
		}
		if("dictValue".equals(type))
		{
			if("up".equals(method))
			{
				sql = sql +" order by d.dictValue asc";
			}else
			{
				sql = sql +" order by d.dictValue desc";
							
			}
		}
		if("inputCode".equals(type))
		{
			if("up".equals(method))
			{
				sql = sql +" order by d.inputCode asc";
			}else
			{
				sql = sql +" order by d.inputCode desc";
							
			}
		} 
		
		
		p.setListSQL(sql);
	 
		List<?> dict = dao.listAllDict(p);
		return dict;

	}
	/**获取Int当前字典列表*/
	public List<?> ListIntDict(PageBean p,String method,String type,String class_code,String dict_code,String dict_value,String input_code) {
		
		String s = "from com.tianjian.comm.bean.CommDictPublicInt as d";
		
		String sql =  getCharSql(s,class_code, dict_code,dict_value, input_code); 
		
		if("classCode".equals(type))
		{
			if("up".equals(method))
			{
				sql = sql +" order by d.classCode asc";
			}else
			{
				sql = sql +" order by d.classCode desc";
							
			}
		}
		if("dictCode".equals(type))
		{
			if("up".equals(method))
			{
				sql = sql +" order by d.dictCode asc";
			}else
			{
				sql = sql +" order by d.dictCode desc";
							
			}
		}
		if("dictValue".equals(type))
		{
			if("up".equals(method))
			{
				sql = sql +" order by d.dictValue asc";
			}else
			{
				sql = sql +" order by d.dictValue desc";
							
			}
		}
		if("inputCode".equals(type))
		{
			if("up".equals(method))
			{
				sql = sql +" order by d.inputCode asc";
			}else
			{
				sql = sql +" order by d.inputCode desc";
							
			}
		} 
		
		
		p.setListSQL(sql);
		
		 

		List<?> dict = dao.listAllDict(p);
		return dict;

	}
	/**获取Char当前页字典列表数目*/
	public Integer getCharCount(PageBean p,String class_code,String dict_code,String dict_value,String input_code) {
		
		String s = "select count(*) from com.tianjian.comm.bean.CommDictPublicChar as d"; 
		
		String sql =  getCharSql(s,class_code, dict_code,dict_value, input_code);
		
		p.setTotalCountSQL(sql);
		return dao.getCount(p);
	}
	/**获取Double当前页字典列表数目*/
	public Integer getDoubleCount(PageBean p,String class_code,String dict_code,String dict_value,String input_code) {
		
		
		String s = "select count(*) from com.tianjian.comm.bean.CommDictPublicDouble as d"; 
		
		String sql =  getCharSql(s,class_code, dict_code,dict_value, input_code);
		
		p.setTotalCountSQL(sql); 
		
	 	return dao.getCount(p);
	}
	/**获取Int当前页字典列表数目*/
	public Integer getIntCount(PageBean p,String class_code,String dict_code,String dict_value,String input_code) {
	
		String s = "select count(*) from com.tianjian.comm.bean.CommDictPublicInt  as d"; 
		
		String sql =  getCharSql(s,class_code, dict_code,dict_value, input_code);
		
		p.setTotalCountSQL(sql);
		
	 	return dao.getCount(p);
	}
	/**获取Class当前字典对象*/
	public CommDictPublicClass ListOnePublicDict(String  id) {
		 
		 
		return   dao.getOnePublicDict(id);

	}
	/**获取Char当前字典对象*/
	public CommDictPublicChar ListOneCharDict(String  id1,String id2) {
		 
		 
		return   dao.getOneCharDict(id1,id2);

	}
	/**获取Double当前字典对象*/
	public CommDictPublicDouble ListOneDoubleDict(String  id1,String id2) {
		 
		 
		return   dao.getOneDoubleDict(id1,id2);

	}
	/**获取Int当前字典对象*/
	public CommDictPublicInt ListOneIntDict(String  id1,String id2) {
		 
		 
		return   dao.getOneIntDict(id1,id2);

	}
	/**获取所有当前字典对象*/
	public List<?> ListAllPublicDict( ) {
		 
		 
		return   dao.listAllDict();

	}
	 
	//罗巍填加
	/**通过classcode获取所有当前字典对象*/
	public List<?> listpubliccharbyClasscode(String classcode){
		return this.getDao().listpubliccharbyClasscodeDao(classcode);
		
	}
	/**通过classCode和inputCode获取所有当前字典对象*/
	public List<?> listpubliccharbyCcodeAndIcode(String ccode,String icode){
		return this.getDao().listpubliccharbyCcodeAndIcodeDao(ccode,icode);
	}
	/**通过classCode和inputCode获取所有当前字典对象*/
	public CommDictPublicChar listpubliccharbyclasscAnddictcode(String classcode,String dictcode){
		return this.getDao().listpubliccharbyclasscAnddictcode(classcode,dictcode);
	}
	/**通过classCode和inputCode获取所有当前字典对象*/
	public List<?> listpublicchinesebyCcodeAndIcode(String ccode,String icode){
		return this.getDao().listpublicchinesebyCcodeAndIcode(ccode,icode);
	}
	/**通过classCode和dictcode获取所有当前字典对象*/
	public List<?> listpublicnumberbyCcodeAndIcode(String ccode,String icode){
		return this.getDao().listpublicnumberbyCcodeAndIcode(ccode,icode);
	}
	/**获取Public的sql语句*/
	public String getPublicSql(String sql,String class_name,String class_code,String input_code)
	{
		
		if(class_name.trim().length()>0|| class_code.trim().length()>0||input_code.trim().length()>0)
		{
			sql = sql+" where ";
		}
		
	 	if(!"".equals(class_code.trim())&&!"".equals(input_code.trim())&&!"".equals(class_name.trim()))
		{
			sql = sql+"     d.classCode like '%" +class_code.trim()+"%' ";
		 	sql = sql+" and d.inputCode like '%" +input_code.trim()+"%' ";
		 	sql = sql+" and d.className like '%" +class_name.trim()+"%' ";
					
		}
	 	if(!"".equals(class_code.trim())&&"".equals(input_code.trim())&&"".equals(class_name.trim()))
		{
			sql = sql+"  d.classCode like '%" +class_code.trim()+"%' ";					
		}
	 	if("".equals(class_code.trim())&&!"".equals(input_code.trim())&&"".equals(class_name.trim()))
		{
	 		sql = sql+"   d.inputCode like '%" +input_code.trim()+"%' ";					
		}
	 	if("".equals(class_code.trim())&&"".equals(input_code.trim())&&!"".equals(class_name.trim()))
		{
	 		sql = sql+"   d.className like '%" +class_name.trim()+"%' ";					
		}
	 	if(!"".equals(class_code.trim())&&!"".equals(input_code.trim())&&"".equals(class_name.trim()))
		{
			sql = sql+"     d.classCode like '%" +class_code.trim()+"%' ";
		 	sql = sql+" and d.inputCode like '%" +input_code.trim()+"%' ";					
		}
	 	if(!"".equals(class_code.trim())&&"".equals(input_code.trim())&&!"".equals(class_name.trim()))
		{
			sql = sql+"     d.classCode like '%" +class_code.trim()+"%' ";
		 	sql = sql+" and d.className like '%" +class_name.trim()+"%' ";
					
		}
	 	if("".equals(class_code.trim())&&!"".equals(input_code.trim())&&!"".equals(class_name.trim()))
		{			 
		 	sql = sql+"  d.inputCode like '%" +input_code.trim()+"%' ";
		 	sql = sql+" and d.className like '%" +class_name.trim()+"%' ";					
		}
	 	return sql;
	}
	/**获取Char的sql语句*/
	public String getCharSql(String sql,String class_code,String dict_code,String dict_value,String input_code)
	{
		if(class_code.trim().length()>0|| dict_code.trim().length()>0|| dict_value.trim().length()>0||input_code.trim().length()>0)
		{
			sql = sql+" where ";
		}
		
	 	if(!"".equals(class_code.trim())&&!"".equals(dict_code.trim())&&!"".equals(dict_value.trim())&&!"".equals(input_code.trim()))
		{
			sql = sql+"     d.classCode like '%" +class_code.trim()+"%' ";
		 	sql = sql+" and d.dictCode like '%" +dict_code.trim()+"%' ";
		 	sql = sql+" and d.dictValue like '%" +dict_value.trim()+"%' ";
			sql = sql+" and d.inputCode like '%" +input_code.trim()+"%' ";
		} 
	 	if(!"".equals(class_code.trim())&&!"".equals(dict_code.trim())&&!"".equals(dict_value.trim())&&"".equals(input_code.trim()))
		{
			sql = sql+"     d.classCode like '%" +class_code.trim()+"%' ";
		 	sql = sql+" and d.dictCode like '%" +dict_code.trim()+"%' ";
		 	sql = sql+" and d.dictValue like '%" +dict_value.trim()+"%' ";			
		} 
	 	if(!"".equals(class_code.trim())&&!"".equals(dict_code.trim())&&"".equals(dict_value.trim())&&!"".equals(input_code.trim()))
		{
			sql = sql+"     d.classCode like '%" +class_code.trim()+"%' ";
		 	sql = sql+" and d.dictCode like '%" +dict_code.trim()+"%' ";
			sql = sql+" and d.inputCode like '%" +input_code.trim()+"%' ";
		} 
	 	if(!"".equals(class_code.trim())&&"".equals(dict_code.trim())&&!"".equals(dict_value.trim())&&!"".equals(input_code.trim()))
		{
			sql = sql+"     d.classCode like '%" +class_code.trim()+"%' ";
		 	sql = sql+" and d.dictValue like '%" +dict_value.trim()+"%' ";
			sql = sql+" and d.inputCode like '%" +input_code.trim()+"%' ";
		} 
	 	if("".equals(class_code.trim())&&!"".equals(dict_code.trim())&&!"".equals(dict_value.trim())&&!"".equals(input_code.trim()))
		{
		 	sql = sql+"     d.dictCode like '%" +dict_code.trim()+"%' ";
		 	sql = sql+" and d.dictValue like '%" +dict_value.trim()+"%' ";
			sql = sql+" and d.inputCode like '%" +input_code.trim()+"%' ";
		} 
	 	if(!"".equals(class_code.trim())&&!"".equals(dict_code.trim())&&"".equals(dict_value.trim())&&"".equals(input_code.trim()))
		{
			sql = sql+"     d.classCode like '%" +class_code.trim()+"%' ";
		 	sql = sql+" and d.dictCode like '%" +dict_code.trim()+"%' ";
		} 
	 	if(!"".equals(class_code.trim())&&"".equals(dict_code.trim())&&!"".equals(dict_value.trim())&&"".equals(input_code.trim()))
		{
			sql = sql+"     d.classCode like '%" +class_code.trim()+"%' ";
		 	sql = sql+" and d.dictValue like '%" +dict_value.trim()+"%' ";
		} 
	 	if("".equals(class_code.trim())&&!"".equals(dict_code.trim())&&!"".equals(dict_value.trim())&&"".equals(input_code.trim()))
		{
		 	sql = sql+"     d.dictCode like '%" +dict_code.trim()+"%' ";
		 	sql = sql+" and d.dictValue like '%" +dict_value.trim()+"%' ";
			 
		} 
	 	if(!"".equals(class_code.trim())&&"".equals(dict_code.trim())&&"".equals(dict_value.trim())&&!"".equals(input_code.trim()))
		{
			sql = sql+"     d.classCode like '%" +class_code.trim()+"%' ";
			sql = sql+" and d.inputCode like '%" +input_code.trim()+"%' ";
		} 
	 	if("".equals(class_code.trim())&&!"".equals(dict_code.trim())&&"".equals(dict_value.trim())&&!"".equals(input_code.trim()))
		{
			sql = sql+"     d.dictCode like '%" +dict_code.trim()+"%' ";
			sql = sql+" and d.inputCode like '%" +input_code.trim()+"%' ";
		} 
	 	if("".equals(class_code.trim())&&"".equals(dict_code.trim())&&!"".equals(dict_value.trim())&&!"".equals(input_code.trim()))
		{
			sql = sql+"     d.dictValue like '%" +dict_value.trim()+"%' ";
			sql = sql+" and d.inputCode like '%" +input_code.trim()+"%' ";
		} 
	 	if(!"".equals(class_code.trim())&&"".equals(dict_code.trim())&&"".equals(dict_value.trim())&&"".equals(input_code.trim()))
		{
			sql = sql+"     d.classCode like '%" +class_code.trim()+"%' ";
		 	 
		} 
	 	if("".equals(class_code.trim())&&!"".equals(dict_code.trim())&&"".equals(dict_value.trim())&&"".equals(input_code.trim()))
		{
			 
		 	sql = sql+"    d.dictCode like '%" +dict_code.trim()+"%' ";
		 	 
		} 
	 	if("".equals(class_code.trim())&&"".equals(dict_code.trim())&&!"".equals(dict_value.trim())&&"".equals(input_code.trim()))
		{
			 
		 	sql = sql+"   d.dictValue like '%" +dict_value.trim()+"%' ";
			 
		} 
	 	if("".equals(class_code.trim())&&"".equals(dict_code.trim())&&"".equals(dict_value.trim())&&!"".equals(input_code.trim()))
		{
		 
			sql = sql+"   d.inputCode like '%" +input_code.trim()+"%' ";
		} 
	 	
		return sql;
	}
}
