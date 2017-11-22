package com.tianjian.util.comm;

import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;

/**
 * DES加密使用标准类
 * @author ch_f001
 *
 */
public class DES {

  public static int _DES = 1;
  public static int _DESede = 2;
  public static int _Blowfish = 3;

  private Cipher p_Cipher;
  private SecretKey p_Key;
  private String p_Algorithm;

  private void selectAlgorithm(int al) {
    switch (al) {
      default:
      case 1:
        this.p_Algorithm = "DES";
        break;
      case 2:
        this.p_Algorithm = "DESede";
        break;
      case 3:
        this.p_Algorithm = "Blowfish";
        break;
    }
  }

  public DES(int algorithm) throws Exception {
    this.selectAlgorithm(algorithm);
    Security.addProvider(new com.sun.crypto.provider.SunJCE());
    this.p_Cipher = Cipher.getInstance(this.p_Algorithm);
  }
/**
 * 生成密钥
 * @return
 */
  public String getKey() {
	    return byte2hex(this.checkKey().getEncoded());
	  }
  
  
  
  private SecretKey checkKey() {
    try {
      if (this.p_Key == null) {
        KeyGenerator keygen = KeyGenerator.getInstance(this.p_Algorithm);
        this.p_Key = keygen.generateKey();
      }
    }
    catch (Exception nsae) {}
    return this.p_Key;
  }
/**
 * 
 * @param enckey
 */
  public void setKey(byte[] enckey) {
    this.p_Key = new SecretKeySpec(enckey, this.p_Algorithm);
  }
/**
 * 字节数组加密
 * @param data
 * @return
 * @throws Exception
 */
  public String encode(String data) throws Exception {
    //this.p_Cipher.init(Cipher.ENCRYPT_MODE, this.checkKey());
    this.p_Cipher.init(Cipher.ENCRYPT_MODE, this.p_Key);
    return byte2hex(this.p_Cipher.doFinal(data.getBytes()));
  }
/**
 * 字节数组解密
 * @param encdata
 * @param enckey
 * @return
 * @throws Exception
 */
  public String decode(String encdata, String enckey) throws Exception {
    this.setKey(hex2byte(enckey));
    this.p_Cipher.init(Cipher.DECRYPT_MODE, this.p_Key);
    return new String(this.p_Cipher.doFinal(hex2byte(encdata)));
  }
  /**
   * 字节数组转换成十六进制字符串
   * @param b
   * @return
   */
  public String byte2hex(byte[] b) {
    String hs = "";
    String stmp = "";
    for (int i = 0; i < b.length; i++) {
      stmp = Integer.toHexString(b[i] & 0xFF);
      if (stmp.length() == 1) {
        hs += "0" + stmp;
      }
      else {
        hs += stmp;
      }
    }
    //return hs;
    return hs.toUpperCase();
  }

/**
 * 十六进制字符串转换成字节数组
 * @param hex
 * @return
 * @throws IllegalArgumentException
 */
  public byte[] hex2byte(String hex) throws IllegalArgumentException {
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

//public static void main(String[] args) throws Exception {
//	String data1 = "12345678901234567890123456789012345678901234567890123456789012";
//  String data2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//	System.out.println("本次负责使用密钥加密"); 
//    DES des = new DES(DES._DESede); // 声明DES
//    String tempkey = des.getKey();//注意密钥和加密文件应该一起生成
//    String temp1= des.encode(data1);
//    String temp2= des.encode(data2);
//    System.out.println("key:" + tempkey);
//    System.out.println("decrypted string1:" + temp1); //生成解密文件字符�? 与data相同
//    System.out.println("decrypted string2:" + temp2); //生成解密文件字符�? 与data相同
//  }


public static void main(String[] args) throws Exception {
	String data1 = "12345678901234567890123456789012345678901234567890123456789012";
    String data2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	System.out.println("This is responsible for the decrypted using the key"); 
    DES des = new DES(DES._DESede); // 声明DES
    String encdata1 = "70D87D24CFB11E3762FE55A4615E160B52057CF186D1295E70BEBCCB3904976209D697E348459F5670D87D24CFB11E3762FE55A4615E160B726E9DC99370C604";
    String encdata2 = "A8084ACA0C70FC76E95F1D6DF51E5A921178C3CFC07602333B25D2CF3D6A0767";
    String enckey = "38ADF8FD156E2A40EF62FBC4CD9B165D1CF8049B4A20F8BC";
    String temp1= des.decode(encdata1, enckey);
    System.out.println("decrypted string:" + temp1); //生成解密文件字符�? 与info相同
    String temp2= des.decode(encdata2, enckey);
    System.out.println("decrypted string:" + temp2); //生成解密文件字符�? 与info相同
  }
   
}
