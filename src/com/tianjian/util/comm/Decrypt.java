package com.tianjian.util.comm;

import java.io.UnsupportedEncodingException;

/**
 * 固定加密解密方式
 * @author ch_f001
 *
 */
public class Decrypt {
	/**
	 * 解密字串
	 * @param s
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public  String decryptString(String s) throws UnsupportedEncodingException {
		s = s.trim();
		byte b[] = this.hex2byte(s);
		String temp =  new String(b,0,b.length,"UTF8");
		int len = temp.length();
		//-----获取首字符，为字符
		String s1 = temp.substring(0,1);
		String s2 = temp.substring(len-1);
		temp = temp.substring(Integer.parseInt(s1)+2, len-Integer.parseInt(s2)-2);
		return temp;
	}
		
		/**
		 * 十六进制字符串转换成字节数组
		 * @param hex
		 * @return
		 * @throws IllegalArgumentException
		 */
		public  byte[] hex2byte(String hex) throws IllegalArgumentException {
			if (hex.length() % 2 != 0) {
				throw new IllegalArgumentException();
			}
			char[] arr = hex.toCharArray();
			byte[] b = new byte[hex.length() / 2];
			for (int i = 0, j = 0, l = hex.length(); i < l; i++, j++) {
				String swap = "" + arr[i++] + arr[i];
				int byteint = Integer.parseInt(swap, 16) & 0xFF;
				b[j] = new Integer(byteint).byteValue();
			}
			return b;
		}
		
	   public static void main(String arg[])throws Exception{
		Decrypt e = new Decrypt(); 
		System.out.println(e.decryptString("3132306368656E672066656932303132"));
		
	} 
	
}
