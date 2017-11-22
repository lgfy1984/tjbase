//TJInputDict test = new TJInputDict("北京天健");
//获取拼音输入码:test.getInputCode();
//获取五笔输入码:test.getInputCodeWb();
package com.tianjian.util.comm;
 

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.tianjian.util.db.DBTools;

public class TJInputDict {
	
	public TJInputDict() {	
	}
	
	public TJInputDict(String ls_china) {	
		this.ls_china = ls_china;
		excute();
	}	
	
	private String ls_py="";
	private String ls_py_temp="";
	private String ls_wb="";
	private String ls_wb_temp="";
	private String ls_every="";
	private String ls_china="";
	private int li_flag = 0;
	//private int li_asc = 0;

	public void setChina(String ls_china){
		this.ls_china = ls_china.trim();
	}
	
	public String getInputCode() {
	  	String s_tmp = (this.ls_py).toUpperCase();
	  	if (s_tmp.length()>8){
	  		s_tmp = s_tmp.substring(0,8);
	  	}
	  	return s_tmp ;
	}
	   
	public String getInputCodeWb() {
	  	String s_tmp = (this.ls_wb).toUpperCase();
	  	if (s_tmp.length()>8) {
	  		s_tmp = s_tmp.substring(0,8);
	  	}
		return s_tmp ;
	}
	  
	public boolean excute() {
	  try {
		  Connection connection = DBTools.getConnection();
		  do {
			    ls_every = ls_china.substring(li_flag,++li_flag);
				String Str="Select * From comm.COMM_CONFIG_INPUT_DICT t where t.ITEM_NAME ='"+ls_every+"'";
			    
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(Str);
			    if (rs.next()){
			    	ls_py_temp = rs.getString("INPUT_CODE");
					ls_wb_temp=rs.getString("INPUT_CODE_WB");
		
					ls_py = ls_py + ls_py_temp;
			        ls_wb = ls_wb + ls_wb_temp;
				}
			    DBTools.close(rs);	
			    DBTools.close(stmt);	
		   } while (li_flag < ls_china.length());			   
		  DBTools.close(connection);		    
	    } catch (Exception sqle){
		    return false;
	    }
		return true;
	}
}
