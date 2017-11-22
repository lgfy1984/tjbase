package com.tianjian.util.comm;

import java.io.UnsupportedEncodingException;

public class password {
/**
 * 
 * @param b
 * @return
 */
	public static int getAsciiNum(byte[] b) {
		int iTemp = 0;
		try {
			iTemp = (int) b[0];
			System.out.println(iTemp);
		}
		catch (Exception Ue) {
			iTemp = 0;
			Ue.printStackTrace();
		}
		return iTemp;
	}
/**
 * ASCII码转换成字符
 * @param digit
 * @return
 */
	public static byte[] getAsciiString(int digit) {
		byte temp[] = new byte[1];
		try {
			temp[0] = (byte) (digit);
		}
		catch (Exception Ue) {
			Ue.printStackTrace();
		}
		return temp;
	}

	/**
	 * 字节数组转换成十六进制字符串
	 * @param b
	 * @return
	 */
	public static String byte2hex(byte[] b) {
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
	 * 十六进制字符串转换成字节数组
	 * @param hex
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static byte[] hex2byte(String hex) throws IllegalArgumentException {
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
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		String s = "陈飞";
		byte temp[] = s.getBytes("utf8");
		System.out.println("Generation sixteen hexadecimal string："+byte2hex(temp));
		byte reb[]=hex2byte(byte2hex(temp));
		System.out.println("Restore the original string："+new String(reb, 0, reb.length, "utf8").trim());
	}

//	 public static void main(String[] args) {
//	 String LcPassword = "陈飞";
//	 try {
//	 byte temp[] = LcPassword.getBytes("gb2312");
//	 for (int i = 0; i < LcPassword.length(); i++) {
//	 temp[i] = (byte) ((int) temp[i]);
//	 }
//	 LcPassword = new String(temp, 0, temp.length, "gb2312").trim();
//	 }
//	 catch (Exception Ue) {
//	 Ue.printStackTrace();
//	 }
//	 }
}