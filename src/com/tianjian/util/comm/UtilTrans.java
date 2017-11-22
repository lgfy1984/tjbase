package com.tianjian.util.comm;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilTrans {
	public static String transNullToString(Object obj, String defaultValue){
		try{
			String temp = "";
			if(obj!=null)
				temp = String.valueOf(obj);
			return temp.trim();
		}catch(Exception e){
			return defaultValue;
		}
	}
	public static String transNullToString(Object obj){
		try{
			String temp = "";
			if(obj!=null)
				temp = String.valueOf(obj);
			return temp.trim();
		}catch(Exception e){
			return "";
		}
	}
	public static Long transNullToLong(Object obj, Long defaultValue){
		try{
			Long temp = 0L;
			if(obj!=null)
				temp = Long.valueOf(String.valueOf(obj));
			return temp;
		}catch(Exception e){
			return defaultValue;
		}
	}
	public static Long transNullToLong(Object obj){
		try{
			Long temp = 0L;
			if(obj!=null)
				temp = Long.valueOf(String.valueOf(obj));
			return temp;
		}catch(Exception e){
			return 0L;
		}
	}
	/**字符型转换成double型*/
	public static Double transStringToDouble(String obj){
		try{
			Double temp = null;
			if(obj!=null)
				temp = Double.valueOf(obj);
			return temp;
		}catch(Exception e){
			return null;
		}
	}
	
	
	/**由字符型转换成日期型-*/
	public static Date transStringToDate(String dateString,String formatString){
		SimpleDateFormat df = new SimpleDateFormat(formatString);
		ParsePosition pos = new ParsePosition(0);
		Date date;
		try {
			date = df.parse(dateString, pos);
			return date;
		} catch (Exception e) {
			return null;
		}
	}
	/**由日期型转换成字符型*/
	public static String transDateToString(Date date){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateString;
		try {
			dateString = df.format(date);
			return dateString;
		} catch (Exception e) {
			return "";
		}
	}
	/**由日期型转换成字符型,带时分秒*/
	public static String transDateToStringFull(Date date){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString;
		try {
			dateString = df.format(date);
			return dateString;
		} catch (Exception e) {
			return "";
		}
	}
	/**由日期型转换成字符型,自定�?*/
	public static String transDateToString(Date date,String formatString){
		try {
			SimpleDateFormat df = new SimpleDateFormat(formatString);
			String dateString;
			dateString = df.format(date);
			return dateString;
		} catch (Exception e) {
			return "";
		}
	}
}
