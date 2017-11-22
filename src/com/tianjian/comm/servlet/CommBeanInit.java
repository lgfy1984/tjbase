package com.tianjian.comm.servlet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;


public class CommBeanInit {
	private static HashMap tjProperty;

	public CommBeanInit() {
		tjProperty = new HashMap();
	}

	static {
		try{
			reloadAll();
		} catch(Exception e) {	
			throw new RuntimeException(e.toString());
		}
	}

	private static void reloadAll() {
		if(tjProperty == null)	tjProperty = new HashMap();
		strageMsg();
	}

	private static void strageMsg() {
		HashMap map = new HashMap();
		Properties p = new Properties();
		try {
			p.load((com.tianjian.comm.servlet.CommBeanInit.class).getClassLoader().getResourceAsStream("conf/comm/commBean.properties"));

			String key;
			String value;
			for(Enumeration enu = p.propertyNames(); enu.hasMoreElements(); map.put(key, value))
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
	public static void println(String message){
		String console = CommBeanInit.getProperty("CONSOLE");
		if(console!=null && console.equalsIgnoreCase("TRUE")){
			System.out.println(message);
		}
	}
	public static void println(Exception e){
		String console = CommBeanInit.getProperty("CONSOLE");
		if(console!=null && console.equalsIgnoreCase("TRUE")){
			e.printStackTrace();
		}
	}
}
