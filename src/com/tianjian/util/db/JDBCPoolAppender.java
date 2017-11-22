package com.tianjian.util.db;

import java.sql.Connection;  
import java.sql.SQLException;  
import org.apache.log4j.jdbc.JDBCAppender;  
  
public class JDBCPoolAppender extends JDBCAppender {  
  
    public JDBCPoolAppender() {    
        super();    
    }    
    @Override  
    protected Connection getConnection() throws SQLException {  
        return DBTools.getConnection();  
    }  
  
}  