package com.tianjian.util.db;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.logicalcobwebs.proxool.ProxoolException;
import org.logicalcobwebs.proxool.configuration.JAXPConfigurator;
import org.xml.sax.InputSource;

/**
 * JDBC封装
 *
 */
public class DBTools  {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DBTools.class);
	
	private static Connection connection;
		
	static {
		try{
			init();
		} catch(Exception e) {	
			throw new RuntimeException(e.toString());
		}
    }
	
	private static void init() {
		try {
			InputSource source = new InputSource(DBTools.class.getClassLoader().getResourceAsStream("proxool.xml"));
			JAXPConfigurator.configure(source, false);
		} catch (ProxoolException e) {
			if(e.getCause().getMessage().contains("Attempt to register duplicate pool called")){
				logger.info(e.getCause().getMessage());
			}else{
				logger.error(DBTools.class,e);
			}
			
		}
		
    }

    public static synchronized Connection getConnection() {        
    	try{
    		try {
				Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			connection = DriverManager.getConnection("proxool.oraclePool");
    		return connection;
    	} catch(SQLException e){
    		e.printStackTrace();
    		return null;
    	}   	
    }
	
	public static void close(Connection conn) {
		if(conn!=null) {
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(PreparedStatement pstmt) {
		if(pstmt!=null) {
			try {
				pstmt.close();
				pstmt = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Statement stmt) {
		if(stmt!=null) {
			try {
				stmt.close();
				stmt = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static void close(ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();
				rs = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static List populate(ResultSet rs,Class clazz) throws Exception{
        ResultSetMetaData metaData = rs.getMetaData(); //取得结果集的元元素
       int colCount = metaData.getColumnCount();          //取得所有列的个数
        List ret = new ArrayList();                                       //存放返回结果的容器
        Field[] fields = clazz.getDeclaredFields();               //取得业务对象的属性
       
       while(rs.next()){
            Object newInstance = clazz.newInstance();   //构造业务对象实例
                           //将结果集中每一条记录，每一个字段取出，根据命名规则，对对应的业务对象的属性进行赋值
           for(int i=1;i<=colCount;i++){  //对于该记录的每一列
               try{
                    Object value = rs.getObject(i);
                   for(int j=0;j<fields.length;j++){
                        Field f = fields[j];
                       if(f.getName().equalsIgnoreCase(metaData.getColumnName(i).replaceAll("_",""))){
                            BeanUtils.copyProperty(newInstance,f.getName(),value);
                        }
                    }
                }catch (Exception e) {
                   // TODO: handle exception
                    e.printStackTrace();
                }
            }
            ret.add(newInstance);
        }
       return ret;
   
	}
}