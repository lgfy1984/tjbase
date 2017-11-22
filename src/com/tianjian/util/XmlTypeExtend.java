package com.tianjian.util;

import java.sql.Connection;
import java.sql.ResultSet;

import org.dom4j.Document;

import com.tianjian.util.db.DBTools;

import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;
import oracle.sql.BLOB;


public class XmlTypeExtend {
	
	public static Document getDocument(String keyColumn, String keyColumnValue, String xmlColumn, String table) {
		String temp = getXML(keyColumn, keyColumnValue, xmlColumn, table);
		if(temp != null){
			return XmlUtil.create(temp);
		}
		return null;		
	}
	
	public static String getXML(String keyColumn, String keyColumnValue, String xmlColumn, String table) {
		String temp = "";
		
		Connection conn = null;
		OraclePreparedStatement stmt = null;
		ResultSet rset = null;
		OracleResultSet orset = null;
		try{
			conn = DBTools.getConnection();
			
		    String sql = "";
		    sql += "select a." + xmlColumn ;
		    sql += ".getBLOBVal(852) poString ";
		    sql += " from " + table + " a ";
		    if (keyColumn != null && keyColumn.trim().length() > 0){
		    	sql += " where a." + keyColumn + " = '" + keyColumnValue.trim() + "' ";
			}
		    
		    stmt = (OraclePreparedStatement) conn.prepareStatement(sql);
			rset = stmt.executeQuery();
			
			orset = (OracleResultSet) rset;
			while(orset.next())	{	
				
				BLOB blob = orset.getBLOB(1);
				temp = blob2string(blob);				
			}	
			
			return temp;			
		} catch (Exception sqle){
			System.out.println(sqle.toString());
		} finally {
	    	try{
	    		orset.close();
				rset.close();
				stmt.close();
				conn.close();
	    	} catch(Exception e){
		    	System.out.println(e.toString());
		    }
	    }
	    
		return temp;
	}
	
	public static String blob2string(oracle.sql.BLOB   BlobContent) {     
		byte[] msgContent= BlobContent.getBytes(); 
		byte[] bytes;
		String newStr = ""; 
		int i=1;            //循环变量
		long BlobLength; 
		try {
			BlobLength=BlobContent.length();
		    if (msgContent == null || BlobLength==0) {
		    	return "";
		    } else {
		    	bytes= BlobContent.getBytes(1,(int)BlobLength) ;
	        	newStr = newStr+new String(bytes,"GBK");    
		        return newStr;
		    }
		} catch(Exception e) {
			e.printStackTrace();
		}  
		return newStr;		
	} 

}
