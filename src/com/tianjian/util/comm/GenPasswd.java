package com.tianjian.util.comm;

import java.lang.String;


/**
 *
 * @author ChenFei
 *
 */
public class GenPasswd {
	static char[] digits={ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
		'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
		'U', 'V', 'W', 'X', 'Y', 'Z' };
	
	
	public static void main(String[] args) {
		 
			System.out.println("生成6位密码"+genPassword());
			
		 
	}
	
		public static String genPassword(){
		int temp;
		String password = "";
		for( 	int i=0; i<6; i++ ){
		temp = ( new Double( Math.random() *  997 ) ).intValue() % 36;
		password += String.valueOf( digits[ temp ] );
		}
		return  password;
		}
}

