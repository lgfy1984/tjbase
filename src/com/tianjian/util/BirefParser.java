package com.tianjian.util;

import java.util.HashMap;
import java.util.Vector;

public class BirefParser {

	//{CIHA_2009_PBI_T1.CDPC_ID_7,[ValueInDb]}
	private HashMap<String, String> briefMap = new HashMap<String, String>();
	//#{c.NAME}在#{c.FOLLOWUP_DOCTOR_NAME}下进行第1次产前随访服务,孕周为#{c.GESTATIONAL_WEEKS},转诊#{c.CDPC_Name_27},总体评估#{c.CDPC_NAME_26},填表日期#{c.SIGN_DATE}
	private String ruleString = "";
	
	public void setBriefMap(HashMap<String, String> briefMap) {
		this.briefMap = briefMap;
	}
	public void setBriefMap(String key, String value) {
		if(this.briefMap==null){
			this.briefMap = new HashMap<String, String>();
		}
		this.briefMap.put(key, value);
	}
	public void setRuleString(String ruleString) {
		this.ruleString = ruleString;
	}

	public String makeBrief(){
		if(ruleString==null || ruleString.trim().length()<=0){
			return ruleString;
		}
		Vector<Integer> vb = new Vector<Integer>();
		int index = 0;
		while(index<ruleString.length()){
			char c = ruleString.charAt(index);
			if(c=='#'){
				if((index+1)<ruleString.length()){
					char c_ = ruleString.charAt(index+1);
					if(c_=='{'){//匹配上了开始
						vb.add(index);
					}
				}
			}
			if(c=='}'){//匹配上了结尾
				int i = 0;
				try{
					i = vb.lastElement();
					vb.remove(vb.lastElement());
				}catch(Exception e){
					throw new RuntimeException();
				}
				String key = ruleString.substring(i+2, index);
				String value = this.briefMap.get(key);
				if(value!=null){//有实际取代值，取代之并重新标定下标
					ruleString = ruleString.substring(0, i)+value+ruleString.substring(index+1, ruleString.length());
					index = i;
				}
			}
			index ++;
		}
		if(vb.size()!=0){
			throw new RuntimeException();
		}
		return ruleString;
	}
	
}
