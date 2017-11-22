package com.tianjian.util.comm;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 固定加密方式
 * @author ch_f001
 *
 */
public class Encrypt {
	/**
	 * 加密方式
	 * @param s
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public  String encryptString(String s) throws UnsupportedEncodingException {
		s = s.trim();
		String dateString = getStringTimestamp();
		int len = dateString.length();
		String s1 = dateString.substring(len - 2,len-1);
		String s2 = dateString.substring(len-1);
		s = s1 + dateString.substring(0,Integer.parseInt(s1)+1) + s + dateString.substring(0,Integer.parseInt(s2)+1) + s2;
		byte b[] = s.getBytes("UTF8");
		return byte2hex(b);
	}
	 
		/**
		 * 字节数组转换成十六进制字符串
		 * @param b
		 * @return
		 */
		public  String byte2hex(byte[] b) {
			String hs = "";
			String stmp = "";
			for (int i = 0; i < b.length; i++) {
				stmp = Integer.toHexString(b[i] & 0xFF);
				if (stmp.length() == 1) {
					hs += "0" + stmp;
				} else {
					hs += stmp;
				}
			}
			return hs.toUpperCase();
		}
		
		/**
		 * 功能描述：将当前日期转换成"yyyy-MM-dd HH:mm:ss"格式的字符串
		 * @return:String 返回的"yyyy-MM-dd HH:mm:ss"格式的字符串
		 */
		public static String getStringTimestamp() {
			Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			String dateString = formatter.format(currentTime);
			return dateString;
		}
	   public static void main(String arg[])throws Exception{
		Encrypt e = new Encrypt(); 
		System.out.println(e.encryptString("cheng fei"));
		
	} 
	
}
