package com.tianjian.util.comm;

import java.util.Properties;


public class SysProperties {

  private Properties props = new Properties();

  public SysProperties() {
    
    	props = System.getProperties();
  }

  public String getProperty(String key) {
    return this.props.getProperty(key);
  }

  public String getProperty(String key, String defaultValue) {
    return this.props.getProperty(key, defaultValue);
  }

}
