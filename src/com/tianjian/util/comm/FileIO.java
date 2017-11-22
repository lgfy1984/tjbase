package com.tianjian.util.comm;

import java.io.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 读写文件的基础方法
 */
public class FileIO {

  private static final Log logger = LogFactory.getLog(FileIO.class);

  public static String readFile(String filePath) {
    String info = "";
    try {
      File f = new File(filePath);
      if (f.exists()) {
        FileInputStream bw = new FileInputStream(f);
        int len = bw.available();
        byte[] str = new byte[len];
        if (bw.read(str) == -1) {
          info = "";
        }
        else {
          info = new String(str);
        }
        bw.close();
        bw = null;
      }
      f = null;
    }
    catch (IOException e) {
      logger.error(e);
    }
    return info;
  }

  public static String readFile(String filePath, String charset) {
    String info = "";
    try {
      File f = new File(filePath);
      if (f.exists()) {
        FileInputStream bw = new FileInputStream(f);
        int len = bw.available();
        byte[] str = new byte[len];
        if (bw.read(str) == -1) {
          info = "";
        }
        else {
          info = new String(str, charset);
        }
        bw.close();
        bw = null;
      }
      f = null;
    }
    catch (IOException e) {
      logger.error(e);
    }
    return info;
  }

  public static void writeFile(String msg, String filePath) {
    try {
      File file = new File(filePath);
      if (file.exists()) {
        file.delete();
      }
      FileOutputStream wf = new FileOutputStream(filePath);
      wf.write(msg.getBytes());
      wf.close();
      file = null;
      wf = null;
    }
    catch (IOException e) {
      logger.error(e);
    }
  }

  public static void writeFile(String msg, String filePath, String charset) {
    try {
      File file = new File(filePath);
      if (file.exists()) {
        file.delete();
      }
      FileOutputStream wf = new FileOutputStream(filePath);
      wf.write(msg.getBytes(charset));
      wf.close();
      file = null;
      wf = null;
    }
    catch (IOException e) {
      logger.error(e);
    }
  }

}
