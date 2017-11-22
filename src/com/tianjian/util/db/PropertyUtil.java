package com.tianjian.util.db;


import java.io.IOException;
import java.util.Properties;
/**
 * 读取配置文件业务逻辑类
 *
 */
public class PropertyUtil {
	private static String filename = "conf/chart/ChartInit.properties";
	
	public static String getProperty(String key){
		try {
			Properties ps = new Properties();
			ps.load(PropertyUtil.class.getClassLoader().getResourceAsStream(filename));
			return ps.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
}
