package com.tianjian.security.struts.servlet;

import java.util.Comparator;

import com.tianjian.security.bean.SecurityConfigPublicClass;

public class SCPCCompareBySerialNo implements Comparator
{
	public int compare(Object o1,Object o2)
    {
		SecurityConfigPublicClass s1 = (SecurityConfigPublicClass)o1;
		SecurityConfigPublicClass s2 = (SecurityConfigPublicClass)o2;
		
		if(s1.getSerialNo()==null){
			if(s2.getSerialNo()==null){
				return 0;
			}else{
				return (Long.valueOf(0L)).compareTo(s2.getSerialNo());
			}
			
		}else{
			if(s2.getSerialNo()==null){
				return s2.getSerialNo().compareTo((Long.valueOf(0L)));
			}else{
				return s1.getSerialNo().compareTo(s2.getSerialNo());
			}
		}
		
    }

}
