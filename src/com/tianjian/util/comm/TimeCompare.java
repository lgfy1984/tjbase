package com.tianjian.util.comm;

import java.text.*;
import java.util.*;

public class TimeCompare{

 public static void main(String[] args){
//  boolean flag = isDateBefore("2004-09-09 12:12:12","2005-09-09 16:00:00");
//  System.out.println(flag);
//  flag = isDateBefore("2006-09-09 01:01:01","2005-09-09 16:00:00");
//  System.out.println(flag);
//  flag = isDateBefore("2005-09-09 01:01:01");
//  System.out.println(flag);
  
  boolean flag = isDateBefore("2010-09-09");
  if(flag==true){
	  System.out.println("The current time is in"+"2010-09-09"+"Prior to");
  };
  System.out.println(flag);
 }

 //判定时间date1是否在时间date2之前
 //时间格式 2005-4-21 16:16:34
 public static boolean isDateTimeBefore(String date1,String date2){
  try{
   DateFormat df = DateFormat.getDateTimeInstance();
   return df.parse(date1).before(df.parse(date2)); 
  }catch(ParseException e){
   System.out.print("[SYS] " + e.getMessage());
   return false;
  }
 }
 
 //判定当前时间是否在时间date2之前
 //时间格式 2005-4-21 16:16:34
 public static boolean isDateTimeBefore(String date2){
  try{
   Date date1 = new Date();
   DateFormat df = DateFormat.getDateTimeInstance();
   return date1.before(df.parse(date2)); 
  }catch(ParseException e){
   System.out.print("[SYS] " + e.getMessage());
   return false;
  }
 }


 //判定时间date1是否在时间date2之前
 //时间格式 2005-4-21
 public static boolean isDateBefore(String date1,String date2){
  try{
   DateFormat df = DateFormat.getDateInstance();
   return df.parse(date1).before(df.parse(date2)); 
  }catch(ParseException e){
   System.out.print("[SYS] " + e.getMessage());
   return false;
  }
 }
 

 
 //判定当前时间是否在时间date2之前
 //时间格式 2005-4-21
 public static boolean isDateBefore(String date2){
  try{
   Date date1 = new Date();
   DateFormat df = DateFormat.getDateInstance();
   return date1.before(df.parse(date2)); 
  }catch(ParseException e){
   System.out.print("[SYS] " + e.getMessage());
   return false;
  }
 }
}
