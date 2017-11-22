package com.tianjian.security.struts.servlet;

import java.util.ArrayList;


/**
 * 构造一个专门存放子表数据的集合
 * 
 */
@SuppressWarnings( "unchecked" )
public class MoniItemList extends ArrayList{
    /**
	 * 
	 */
	private static final long serialVersionUID = 686131424642838204L;
	private Class itemClass;
    
    public MoniItemList(Class itemClass){
    	
    	this.itemClass = itemClass;
    }
    
    public Object get(int index){
    	try{
    		while(index >=size()){
    			add(itemClass.newInstance());
    		}
    	
    	}catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return super.get(index);
    }
}
