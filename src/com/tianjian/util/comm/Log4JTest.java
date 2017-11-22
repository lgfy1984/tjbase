package com.tianjian.util.comm;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

import com.tianjian.util.UUIDHexGenerator;

import junit.framework.TestCase;

public class Log4JTest extends TestCase {

	public void testLog1() {
		 Logger logger = Logger.getLogger("SYSTEM");  
	        // System.out.println(Log4JTest.class);  
	        System.out.println("继承：" + logger.getAdditivity());  
	        System.out.println("级别：" + logger.getLevel());  
	        System.out.println("有效的级别：" + logger.getEffectiveLevel());  
	        MDC.put("userId", UUIDHexGenerator.getUUIDHex());  
	        logger.fatal("test_fatal");  
	        logger.error("test_Error");  
	        logger.warn("test_warn");  
	        logger.info("test_Info");  
	        logger.debug("test_debug");  
	        MDC.remove("userId");
	}
	
		    public static void main(String[] args) {  
//		        MDC.put("userId", "用户ID");  
//		        logger.error("testError");    
		    	Log4JTest test = new Log4JTest();
		    	test.testLog1();
		    }  
}