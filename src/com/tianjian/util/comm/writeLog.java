package com.tianjian.util.comm;

import java.io.*;
import java.util.*;
import java.text.*;//不使用日期格式转换时不需要

public class writeLog
{
 private PrintWriter log;
 public writeLog()
 {
        init();
 }
 public void init()
 {
        InputStream is = getClass().getResourceAsStream("/db.properties");
        Properties dbProps = new Properties();
        try{
                dbProps.load(is);
                }
                catch(Exception e){
                        System.err.println("Unable to read the attribute file."+"Please make sure that"+"db.properties"+"File in"+"classpath"+"The specified path!");
                        return;
                         }
     String logFile = dbProps.getProperty("logfile","JAVA.LOG");
     try{
        log = new PrintWriter(new FileWriter(logFile,true),true);
         }
         catch(IOException e){
                System.err.println("Unable to open the log file:"+logFile);
                log = new PrintWriter(System.err);
                                 }
 }
 public void log(String msg)
 {
        log.println(getStringDate() + ":"+msg);
 }
 public void log(Throwable e,String msg)
 {
        log.println(getStringDate()+":"+msg);
        e.printStackTrace(log);
 }

 public static String getExceptionMsg(Exception e){
                 StackTraceElement ste=e.getStackTrace()[0];
                 String msg=ste.getClassName() + "." + ste.getMethodName() + "() Ln " + ste.getLineNumber() + ":   " + e.getMessage();
                 return msg;
	}

 public String getStringDate()
    {
                Date currentTime = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateString = formatter.format(currentTime);
                return dateString;
        }

}
