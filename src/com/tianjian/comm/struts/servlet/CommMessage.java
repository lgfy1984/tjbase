package com.tianjian.comm.struts.servlet;

import java.util.HashMap;
import java.util.Properties;
import java.util.Enumeration;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CommMessage {
	
	private static HashMap<String, String> tjMsg;
	
	public CommMessage() {
		tjMsg = new HashMap<String, String>();
	}
	
	static {
		try{
			reloadAll();
		} catch(Exception e) {	
			throw new RuntimeException(e.toString());
		}
    }
	
	private static void reloadAll() {
    	if(tjMsg == null)	tjMsg = new HashMap<String, String>();
    	strageMsg();
    }

    private static void strageMsg() {
    	HashMap<String, String> map = new HashMap<String, String>();
    	Properties p = new Properties();
        try {
            p.load((com.tianjian.comm.struts.servlet.CommMessage.class).getClassLoader().getResourceAsStream("conf/comm/CommMessage_zh.properties"));
            
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
    	tjMsg = map;
    }

    public static String getMsg(String key) {
        String str = "";
        if(tjMsg != null) {
            if(tjMsg.containsKey(key)){
            	str = (String)tjMsg.get(key);            	
            }
                
        }
        return str;
    }
    
}
