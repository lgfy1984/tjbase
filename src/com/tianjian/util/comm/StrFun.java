package com.tianjian.util.comm;

import javax.servlet.http.HttpServletRequest;


/**
 * 处理字符的类
 * Create By Chen Fei 
 * @author Administrator
 *
 */
public class StrFun 
{
   /**
    *构造方法
    */
  public StrFun(){}	
  /**
   *中文字符转换成GB2312类型
   */
  public static String chinese(String str)
  {
     if(str!=null){
			        try {
				        str = new String(str.getBytes("iso-8859-1"),"gb2312") ;
				         }
			         catch (Exception e) 
			         	{
			        	 e.printStackTrace();
			         	}
			     }
     return str ;
  }
   /**
    *获取字符串
    */
	public static String getString(HttpServletRequest request, String s)
	{
        String temp = null;
		try{
				temp = chinese(request.getParameter(s).trim());
			}catch(Exception e){
				 e.printStackTrace();
			}
	return temp;
    }
       
    /**
     *获取字符串，如果没有值，取默认字符串
     */
	public static String getString(HttpServletRequest request, String s, String defaultString) throws Exception 
	{
		String s1 = getString(request,s);
		if(s1==null) return defaultString;
		return s1;
	}
    /**
     *获取将字符串转换成整形类型-
     */
	public static int getInt(HttpServletRequest request,String s, int defaultInt)
	{
		try{
			String temp = getString(request,s);
			if(temp==null)
				return defaultInt;
			else
				return Integer.parseInt(temp);
		}catch(NumberFormatException e){
			return 0;
		}
	}
    /**
     *获取将字符串转换成整形类型
     */
	public static int getInt(HttpServletRequest request,String s)
	{
          return getInt(request,s,0);
	}
	/**
	 *获取将字符串转换成浮点类型，带默认类型
	 */
	public static float getFloat(HttpServletRequest request,String s, float defaultfloat){
		try{
			String temp = getString(request,s);
			if(temp==null)
				return defaultfloat;
			else
				return Float.parseFloat(temp);
		}catch(NumberFormatException e){
			return 0;
		}
	}
    /**
     *获取将字符串转换成浮点类型
     */
	public static float getFloat(HttpServletRequest request,String s)
	{
        return getFloat(request,s,0);
	}
    /**
     *获取将字符串转换成long类型，带默认类型
     */
	public static long getLong(HttpServletRequest request, String s, long defaultLong)
	{
		try{
			String temp = getString(request,s);
			if(temp==null)
				return defaultLong;
			else
				return Long.parseLong(temp);
		}catch(NumberFormatException e){
			return 0;
		}
	}  
      /**
       *获取将字符串转换成long类型
       */
	public static long getLong(HttpServletRequest request,String s){
        return getLong(request,s,0);
	}	
	
	/**
	 * 字符串格式化，去的空格，遇null返回“”
	 * */
	public static String transNullToString(Object obj){
		String temp = "";
		if (obj != null){
			temp = ((String)obj).trim();			
		}
		return temp;
	}
	/**
	 * 数字型字符串去空格，去null，返回0
	 * */
	public static String transNullToZero(Object obj){
		String temp = "0";
		if ((obj != null) && (((String)obj).trim() != "")){
			temp = ((String)obj).trim();			
		}
		return temp;
	}
}

