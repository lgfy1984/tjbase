package com.tianjian.empi.struts.servlet;

import java.util.HashMap;
import java.util.Properties;
import java.util.Enumeration;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.tianjian.hsp.struts.servlet.HspInit;

public class EmpiInit {
	
	private static HashMap<String, String> tjProperty;
	
	public EmpiInit() {
		tjProperty = new HashMap<String, String>();
	}
	
	static {
		try{
			reloadAll();
		} catch(Exception e) {	
			throw new RuntimeException(e.toString());
		}
    }
	
	private static void reloadAll() {
    	if(tjProperty == null)	tjProperty = new HashMap<String, String>();
    	strageMsg();
    }

    private static void strageMsg() {
    	HashMap<String, String> map = new HashMap<String, String>();
    	Properties p = new Properties();
        try {
            p.load((EmpiInit.class).getClassLoader().getResourceAsStream("conf/empi/empiInit.properties"));
            
            String key;
            String value;
            for(Enumeration<?> enu = p.propertyNames(); enu.hasMoreElements(); map.put(key, value))
            {
                key = (String)enu.nextElement();
                value = p.getProperty(key);
            }
        } catch(FileNotFoundException e) {
        	throw new RuntimeException(e.toString());
        } catch(IOException io){
        	throw new RuntimeException(io.toString());
        }
    	tjProperty = map;
    }

    public static int getPageSize(String key) {
        int str = 10;
        if(tjProperty != null) {
            if(tjProperty.containsKey(key)){
            	str = Integer.valueOf((String)tjProperty.get(key)).intValue();            	
            }
                
        }
        return str;
    }
    public static String getProperty(String key) {
		String str = "";
		if(tjProperty != null) {
			if(tjProperty.containsKey(key)){
				str = (String)tjProperty.get(key);            	
			}

		}
		return str;
	}
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

    public static void println(String message){
    	String console = HspInit.getProperty("CONSOLE");
    	if(console!=null && console.trim().equalsIgnoreCase("TRUE")){
    		System.out.println(message);
    	}
    }
    public static void println(Exception e){
    	String console = HspInit.getProperty("CONSOLE");
    	if(console!=null && console.trim().equalsIgnoreCase("TRUE")){
    		e.printStackTrace();
    	}
    }
}
