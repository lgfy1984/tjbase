package com.tianjian.util.comm;

public class A {
	public static void main(String[] args){
		String han = "abdscd";
		String ascii = "";
		A a = new A();
		ascii = a.hanToAscii(han);
		System.out.println("汉字: "+han+"的ascii码是 " + ascii);
		han = "";
		han = a.asciiToHan(ascii);
		System.out.println("ascii: "+ ascii +"的汉字是 " + han);
	}
	public String hanToAscii(String content){
		StringBuffer returnAsciiStr = new StringBuffer("");
		if(content != null && content.trim().length() > 0){
			char[] contentsChar = content.toCharArray();
			for(int i = 0; i < contentsChar.length; i++)
				returnAsciiStr.append((int)contentsChar[i]).append("-");
		}
		return returnAsciiStr.toString();
	}
	public String asciiToHan(String content){
		StringBuffer returnHanStr = new StringBuffer();	
		String[] contentsStr = content.split("-");
		for(int i = 0; i < contentsStr.length; i++){
			returnHanStr.append((char)Integer.parseInt(contentsStr[i]));
		}
		return returnHanStr.toString();
	}
}
