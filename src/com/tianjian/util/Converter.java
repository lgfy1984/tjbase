package com.tianjian.util;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.hibernate.lob.SerializableClob;
import org.jdom.Element;
import org.jdom.Namespace;

public class Converter {
	public static String getValue(HSSFCell cell) {
		String value = "";
		switch(cell.getCellType()){
		case HSSFCell.CELL_TYPE_NUMERIC: // 数值型
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				//如果是date类型则 ，获取该cell的date值
				value = HSSFDateUtil.getJavaDate(cell.getNumericCellValue()).toString();
			} else {// 纯数字
				value = String.valueOf(cell.getNumericCellValue());
				for(int i=0;i<value.length();i++){
					if(value.charAt(i)=='E'||value.charAt(i)=='e'){
						BigDecimal dd = new BigDecimal(value);
						value=dd.longValue()+"";
					}
					if(value.substring(value.length()-2).equals(".0")){
						value=value.substring(0, value.length()-2);
					}
				}
			}
			break;
			/* 此行表示单元格的内容为string类型 */
		case HSSFCell.CELL_TYPE_STRING: // 字符串型   
			value = cell.getStringCellValue().toString();
			break;
		case HSSFCell.CELL_TYPE_FORMULA:// 公式型
			//读公式计算值
			value = String.valueOf(cell.getNumericCellValue());
			if (value.equals("NaN")) {// 如果获取的数据值为非法值,则转换为获取字符串
				value = cell.getStringCellValue().toString();
			}
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:// 布尔
			value = " " + cell.getBooleanCellValue();
			break;
			/* 此行表示该单元格值为空 */
		case HSSFCell.CELL_TYPE_BLANK: // 空值
			value = "";
			break;
		case HSSFCell.CELL_TYPE_ERROR: // 故障
			value = "";
			break;
		default:
		}
		return value; 
	}
	/**
	 * 构造一个节点名为targetName的XML节点，其内容为text，然后返回之
	 * @param targetName 目标节点名字符串
	 * @param text 目标节点内容字符串
	 * @return Element
	 */
	public static Element newElementWithText(String targetName, String text){		
		Element element = new Element(targetName);
		if(text != null && text.trim().length() > 0){
			element.addContent(text);
		}				
		return element;
	}
	/**
	 * 构造一个命名空间为xmlns且节点名为targetName的XML节点，其内容为text，然后返回之
	 * @param targetName 目标节点名字符串
	 * @param text 目标节点内容字符串
	 * @param xmlns 目标节点命名空间字符串
	 * @return Element
	 */
	public static Element newElementWithText(String targetName, String text, String xmlns){		
		Element element = new Element(targetName, Namespace.getNamespace(xmlns));
		if(text != null && text.trim().length() > 0){
			element.addContent(text);
		}				
		return element;
	}
	/**
	 * 构造一个命名空间为xmlns且节点名为targetName的XML节点，其内容为text，然后返回之
	 * @param targetName 目标节点名字符串
	 * @param text 目标节点内容字符串
	 * @param xmlns 目标节点命名空间对象
	 * @return Element
	 */
	public static Element newElementWithText(String targetName, String text, Namespace xmlns){		
		Element element = new Element(targetName, xmlns);
		if(text != null && text.trim().length() > 0){
			element.addContent(text);
		}				
		return element;
	}
	/**
	 * 构造一个属性及值
	 * 构造一个命名空间为xmlns且节点名为targetName的XML节点，其属性名为attributeName[i]的属性值为attributeValue[i]，然后返回之
	 * @param targetName 目标节点名字符串
	 * @param attributeNames 属性名字符串数组
	 * @param attributeValues 属性值字符串数组
	 * @param xmlns 目标节点命名空间字符串
	 * @return Element
	 */
	public static Element newElementWithAttribute(String targetName, String[] attributeNames, String[] attributeValues, String xmlns){		
		Element element = new Element(targetName, Namespace.getNamespace(xmlns));
		if(attributeNames==null || attributeNames.length<=0)
			return element;
		for(int i=0; i<attributeNames.length; i++){
			String attributeName = Converter.toBlank(attributeNames[i]);
			if(attributeName != null && attributeName.trim().length() > 0){
				element.setAttribute(attributeName, Converter.toBlank(attributeValues[i]));
			}
		}

		return element;
	}
	/**
	 * 构造一个属性及值
	 * 构造一个命名空间为xmlns且节点名为targetName的XML节点，其属性名为attributeName[i]的属性值为attributeValue[i]，然后返回之
	 * @param targetName 目标节点名字符串
	 * @param attributeNames 属性名字符串数组
	 * @param attributeValues 属性值字符串数组
	 * @param xmlns 目标节点命名空间对象
	 * @return Element
	 */
	public static Element newElementWithAttribute(String targetName, String[] attributeNames, String[] attributeValues, Namespace xmlns){		
		Element element = new Element(targetName, xmlns);
		if(attributeNames==null || attributeNames.length<=0)
			return element;
		for(int i=0; i<attributeNames.length; i++){
			String attributeName = Converter.toBlank(attributeNames[i]);
			if(attributeName != null && attributeName.trim().length() > 0){
				element.setAttribute(attributeName, Converter.toBlank(attributeValues[i]));
			}
		}

		return element;
	}
	/**
	 * 构造一个属性及值
	 * 构造一个节点名为targetName的XML节点，其属性名为attributeName[i]的属性值为attributeValue[i]，然后返回之
	 * @param targetName 目标节点名字符串
	 * @param attributeNames 属性名字符串数组
	 * @param attributeValues 属性值字符串数组
	 * @return Element
	 */
	public static Element newElementWithAttribute(String targetName, String[] attributeNames, String[] attributeValues){		
		Element element = new Element(targetName);
		if(attributeNames==null || attributeNames.length<=0)
			return element;
		for(int i=0; i<attributeNames.length; i++){
			String attributeName = Converter.toBlank(attributeNames[i]);
			if(attributeName != null && attributeName.trim().length() > 0){
				element.setAttribute(attributeName, Converter.toBlank(attributeValues[i]));
			}
		}

		return element;
	}
	/**
	 * 构造一个属性及值
	 * 构造一个命名空间为xmlns且节点名为targetName的XML节点，其属性名为attributeName的属性值为attributeValue，然后返回之
	 * @param targetName 目标节点名字符串
	 * @param attributeName 属性名字符串
	 * @param attributeValue 属性值字符串
	 * @param xmlns 目标节点命名空间字符串
	 * @return Element
	 */
	public static Element newElementWithAttribute(String targetName, String attributeName, String attributeValue, String xmlns){		
		Element element = new Element(targetName, Namespace.getNamespace(xmlns));
		attributeName = Converter.toBlank(attributeName);
		if(attributeName != null && attributeName.trim().length() > 0){
			element.setAttribute(attributeName, Converter.toBlank(attributeValue));
		}
		return element;
	}
	/**
	 * 构造一个属性及值
	 * 构造一个命名空间为xmlns且节点名为targetName的XML节点，其属性名为attributeName的属性值为attributeValue，然后返回之
	 * @param targetName 目标节点名字符串
	 * @param attributeName 属性名字符串
	 * @param attributeValue 属性值字符串
	 * @param xmlns 目标节点命名空间对象
	 * @return Element
	 */
	public static Element newElementWithAttribute(String targetName, String attributeName, String attributeValue, Namespace xmlns){		
		Element element = new Element(targetName, xmlns);
		attributeName = Converter.toBlank(attributeName);
		if(attributeName != null && attributeName.trim().length() > 0){
			element.setAttribute(attributeName, Converter.toBlank(attributeValue));
		}
		return element;
	}
	/**
	 * 构造一个属性及值
	 * 构造一个节点名为targetName的XML节点，其属性名为attributeName的属性值为attributeValue，然后返回之
	 * @param targetName 目标节点名字符串
	 * @param attributeName 属性名字符串
	 * @param attributeValue 属性值字符串
	 * @return Element
	 */
	public static Element newElementWithAttribute(String targetName, String attributeName, String attributeValue){		
		Element element = new Element(targetName);
		attributeName = Converter.toBlank(attributeName);
		if(attributeName != null && attributeName.trim().length() > 0){
			element.setAttribute(attributeName, Converter.toBlank(attributeValue));
		}
		return element;
	}
	/**
	 * 查看字符串cell是否在字符串数组pool中，如果在则返回true，否则返回false，当pool无效时也返回false
	 * @param pool:String[]
	 * @param cell:String
	 * @return boolean
	 */
	public static boolean contains(String[] pool, String cell){
		if(pool==null || pool.length<=0){
			return false;
		}
		for(int i=0; i<pool.length; i++){
			if(pool[i].equals(cell)){
				return true;
			}
		}
		return false;
	}
	/**
	 * 往String数组尾部追加数据元素并返回新的String数组，如果参数数组为null则新建一个空数组以添加数据元素
	 * @param array
	 * @param elm
	 * @return String[]/null
	 */
	public static String[] add(String[] array, String elm){
		if(array==null){
			array = new String[]{};
		}
		String[] array_ = new String[array.length+1];
		for(int i=0; i<array.length; i++){
			array_[i] = array[i];
		}
		array_[array.length] = elm;
		array = array_;
		return array;
	}
	public static Long toLong(Object obj){
		try{
			String longString = Converter.toBlank(obj);
			return Long.valueOf(longString);
		}catch(Exception e){
			return null;
		}
	}
	/**
	 * <b>将入参根据其类型进行向字符串的转化</b><br>
	 * 注：一般只转换常见、公有类型,不转换自定义类型<br>
	 * 当前可转特殊类型有:java.util.Date、org.hibernate.lob.SerializableClob、oracle.sql.CLOB，其余类型直接调用入参的toString()方法获取
	 * @param obj 任意Object及其子类
	 * @return String
	 */
	public static String toBlank(Object obj){		
		if(obj==null){
			return "";
		}else{
			String className = obj.getClass().getName();
			String oValue = "";
			if(className.equals("java.util.Date") || className.equals("java.sql.Timestamp")){
				try{
					oValue = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(obj).trim();
				}catch(Exception e){
					try{
						oValue = new SimpleDateFormat("yyyy-MM-dd").format(obj).trim();
					}catch(Exception e_){
						oValue = "";
					}
				}
			}else if(className.equals("org.hibernate.lob.SerializableClob")){
				try{
					SerializableClob sclob = (SerializableClob)obj;
					oValue = sclob.getSubString(1L, (int)sclob.length()).trim();
				}catch(SQLException e){
					oValue = "";
				}
			}else if(className.equals("oracle.sql.CLOB")){
				try{
					Clob clob = (Clob)obj;
					oValue = clob.getSubString(1L, (int)clob.length()).trim();
				}catch(SQLException e){
					oValue = "";
				}
			}else{
				oValue = obj.toString().trim();
			}
			return oValue;
		}
	}
	public static Date toDate(String dateString) {
		dateString = Converter.toBlank(dateString);
		try{
			return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(dateString);
		}catch (ParseException e){
			try{
				return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
			}catch (ParseException e1){
			}
		}
		return null;
	}
	public static String toString(Date date){
		if(date==null){
			return "";
		}else{
			try{
				return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
			}catch(Exception e){
				try{
					return new SimpleDateFormat("yyyy-MM-dd").format(date);
				}catch(Exception e1){
					return "";
				}
			}
		}
	}
	/**
	 * 将日期对象date按照formater格式进行字符串转换，date为null时返回空串，转换异常时调用String:com.tianjian.util.Converter.toString(Date date)方法来处理
	 * @param date java.util.Date
	 * @param formater java.lang.String
	 * @return java.lang.String
	 */
	public static String toString(Date date, String formater){
		if(date==null){
			return "";
		}else{
			try{
				return new SimpleDateFormat(formater).format(date);
			}catch(Exception e){
				return Converter.toString(date);
			}
		}
	}
	public static String toString(Object obj, SimpleDateFormat format1, SimpleDateFormat format2){
		String temp = "";
		if (obj != null){
			try{
				temp = format1.format(format2.parse(obj.toString()));
			} catch(Exception e){
				return "";
			}
		}
		return temp.trim();
	}
	public static Integer toInteger(Object obj) {
		try{
			String integerString = Converter.toBlank(obj);
			return Integer.valueOf(integerString);
		}catch(Exception e){
			return null;
		}
	}
	@SuppressWarnings("deprecation")
	public static String getUnicode(String toEncoded, String encoding) {
		String retString = "";
		if (toEncoded.equals("") || toEncoded.trim().equals("")) {
			return toEncoded;
		}
		try {
			byte[] b = toEncoded.getBytes(encoding);
			sun.io.ByteToCharConverter convertor = sun.io.ByteToCharConverter.getConverter(encoding);
			char[] c = convertor.convertAll(b);
			for (int i = 0; i < c.length; i++) {
				retString += String.valueOf(c[i]);
			}
		} catch (java.io.UnsupportedEncodingException e) {
			System.out.println(e);
		} catch (sun.io.MalformedInputException e) {
			System.out.println(e);
		}
		return retString;
	}
	public static void setCellText(HSSFRow row, HSSFCell cell, int index, String text, HSSFCellStyle cellStyle) {
		cell = row.createCell((short)index);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(cellStyle);
		text = getUnicode(text,"gb2312");
		cell.setCellValue(text);
	}
	

	/**
	 * 获取文件的简易名称，如果有后缀名则包括后缀名。如果入参为null或空串直接返回null<br>
	 * 例如：filePath="..\..\file\demo\xxx.text"被传入方法则返回xxx.text<br>
	 * 又如：filePath="..\..\file\demo\xxx"被传入方法则返回xxx<br>
	 * 又如：filePath="..\..\file\demo\"被传入方法则返回空串<br>
	 * 注：本方法不检测文件是否实际存在
	 * @param filePath 文件路径
	 * @return 文件的简易名称
	 */
	public static String getFileSimpleName(String filePath){
		if(filePath==null || filePath.trim().length()<=0){
			return null;
		}
		filePath = filePath.replace("\\", "/");
		filePath = filePath.substring(filePath.lastIndexOf("/")+1, filePath.length());
		return filePath;
	}
	public static void main(String[] args){
		System.out.println("【1】"+Converter.getFileSimpleName("..\\..\\file\\demo\\xxx.text"));
		System.out.println("【2】"+Converter.getFileSimpleName("..\\..\\file\\demo\\xxx"));
		System.out.println("【3】"+Converter.getFileSimpleName("..\\..\\file\\demo\\"));
	}
	/**
	 * 获取文件的简易名称，如果有后缀名则包括后缀名。如果入参为null直接返回null<br>
	 * 例如：file=new File("..\..\file\demo\xxx.text")被传入方法则返回xxx.text<br>
	 * 又如：file=new File("..\..\file\demo\xxx")被传入方法则返回xxx
	 * 又如：file=new File("..\..\file\demo\")被传入方法则返回空串<br>
	 * 注：本方法不检测文件是否实际存在
	 * @param file 文件对象
	 * @return 文件的简易名称
	 */
	public static String getFileSimpleName(File file){
		return Converter.getFileSimpleName(file.getPath());
	}
	/**
	 * 将keyword的每一个元按照某一依据分隔形成数组后然后求这些数组的并集<br>
	 * 本方法的依据为调用com.tianjian.util.Converter.formatKeyword(keyword)方法
	 * @param keyword:String[]
	 * @return String[]/null
	 */
	public static String[] unin(String[] keyword){
		if(keyword==null || keyword.length<=0){
			return null;
		}
		String[] res = new String[]{};
		for(int i=0; i<keyword.length; i++){
			res = Converter.unin(res, Converter.formatKeyword(keyword[i]));
		}
		return res;
	}
	/**
	 * 将两个String数组合并求其并集
	 * @param to:String[]
	 * @param from:String[]
	 * @return String[]/null
	 */
	public static String[] unin(String[] to, String[] from){
		String flag = "";
		if(from==null || from.length<=0){//from集为空
			if(to==null || to.length<=0){//两个都是空集，并集也为空
				flag = "";
			}else{//to集合非空，并集为to
				flag = "T";
			}
		}else{//from集非空
			if(to==null || to.length<=0){//to集为空，并集为from
				flag = "F";
			}else{//to集非空，处理合并
				flag = "FT";
			}
		}
		List<String> list = new ArrayList<String>();
		if(flag.contains("T")){
			for(int i=0; i<to.length; i++){
				if(!list.contains(to[i]))
					list.add(to[i]);
			}
		}
		if(flag.contains("F")){
			for(int i=0; i<from.length; i++){
				if(!list.contains(from[i]))
					list.add(from[i]);
			}
		}
		if(list==null || list.size()<=0){
			return null;
		}else{
			String[] res = new String[list.size()];
			for(int i=0; i<list.size(); i++){
				res[i] = list.get(i);
			}
			return res;
		}
	}
	/**
	 * 将keyword按照空格分隔然后将分隔之后的有效字符串封装到一个String数组中予以返回，如果没有找到有效字符串则返回null
	 * @param keyword:String
	 * @return String[]/null
	 */
	public static String[] formatKeyword(String keyword){
		if(keyword==null || keyword.trim().length()<=0){
			return null;
		}
		String[] token = keyword.split(" ");
		if(token!=null && token.length>0){
			List<String> list = new ArrayList<String>();
			for(int i=0; i<token.length; i++){
				String temp = (token[i]==null?"":token[i].trim());
				if(!temp.equals(""))
					list.add(token[i].trim());
			}
			if(list==null || list.size()<=0){
				return null;
			}else{
				String[] rs = new String[list.size()];
				for(int i=0; i<list.size(); i++){
					rs[i] = list.get(i);
				}
				return rs;
			}
		}else{
			return null;
		}
	}
	
	/**
	 * <b>将字符串str中的所有的某字符串设置高亮</b><br>
	 * 请查阅String:com.tianjian.util.Converter.highlight(String str, String[] keywords)
	 * @param str:String
	 * @param keywords:List&lt;String>
	 * @return String/null
	 */
	public static String highlight(String str, List<String> keywords){
		if(str!=null && str.trim().length()>0 && keywords!=null && keywords.size()>0){
			for(int i=0; i<keywords.size(); i++){
				String keyword = keywords.get(i);
				str = str.replaceAll(keyword, "<font color='red'>"+keyword+"</font>");
			}
		}
		return str;
	}
	/**
	 * <b>将字符串str中的所有的某字符串设置高亮</b><br>
	 * 某字符串的来源于字符串数组keywords<br>
	 * 高亮原则为将原字符串包裹替换：&lt;font color='red'>原字符串&lt;font><br>
	 * 如果keywords字符串数组为null或空则直接返回str字符串，如果str中没有找到替换的内容时返回str原串
	 * @param str:String
	 * @param keywords:String[]
	 * @return String/null
	 */
	public static String highlight(String str, String[] keywords){
		if(str!=null && str.trim().length()>0 && keywords!=null && keywords.length>0){
			for(int i=0; i<keywords.length; i++){
				String keyword = keywords[i];
				str = str.replaceAll(keyword, "<font color='red'>"+keyword+"</font>");
			}
		}
		return str;
	}
	/**
	 * <b>将Object对象o按照其类型转化为字符串，并返回经过高亮设置后的字符串对象</b><br>
	 * 请参阅String:com.tianjian.util.Converter.toString(Object o, String[] standardKeywords)
	 * @param o:Object
	 * @param standardKeywords:List&lt;String>
	 * @return String
	 */
	public static String toString(Object o, List<String> standardKeywords){
		if(standardKeywords==null || standardKeywords.size()<=0){
			return Converter.toString(o, new String[]{});
		}else{
			return Converter.toString(o, (String[])standardKeywords.toArray());
		}
	}
	/**
	 * <b>将Object对象o按照其类型转化为字符串，并返回经过高亮设置后的字符串对象</b><br>
	 * 转化类型为：o.getClass().getName()，暂只特别考虑了以下类型，注意：在特别考虑的这几种类型转换时，如果异常则会返回空串<br>
	 * &nbsp;&nbsp;&nbsp;java.util.Date<br>
	 * &nbsp;&nbsp;&nbsp;org.hibernate.lob.SerializableClob<br>
	 * &nbsp;&nbsp;&nbsp;oracle.sql.CLOB<br>
	 * 设置高亮依赖于方法：String:com.tianjian.util.Converter.highlight(String str, String[] keywords)<br>
	 * 如果Object对象o为null则直接返回空串
	 * @param o:Object
	 * @param standardKeywords:String[]
	 * @return String
	 */
	public static String toString(Object o, String[] standardKeywords){
		if(o==null){
			return "";
		}
		String className = o.getClass().getName();
		String oValue = "";
		if(className.equals("java.util.Date")){
			try{
				oValue = Converter.highlight(Converter.toBlank(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(o)).trim(), standardKeywords);
			}catch(Exception e){
				try{
					oValue = Converter.highlight(Converter.toBlank(new SimpleDateFormat("yyyy-MM-dd").format(o)).trim(), standardKeywords);
				}catch(Exception e_){
					oValue = "";
				}
			}
		}else if(className.equals("org.hibernate.lob.SerializableClob")){
			try{
				SerializableClob sclob = (SerializableClob)o;
				oValue = Converter.highlight(Converter.toBlank(sclob.getSubString(1L, (int)sclob.length())).trim(), standardKeywords);
			}catch(SQLException e){
				oValue = "";
			}
		}else if(className.equals("oracle.sql.CLOB")){
			try{
				Clob clob = (Clob)o;
				oValue = Converter.highlight(Converter.toBlank(clob.getSubString(1L, (int)clob.length())).trim(), standardKeywords);
			}catch(SQLException e){
				oValue = "";
			}
		}else{
			oValue = Converter.highlight(Converter.toBlank(o).trim(), standardKeywords);
		}
		return oValue;
	}
 
	
	public static Long toLong(Object obj, Long defValue){
		try{
			String longString = Converter.toBlank(obj);
			return Long.valueOf(longString);
		}catch(Exception e){
			return defValue;
		}
	}
	public static Double toDouble(Object obj){
		try{
			String longString = Converter.toBlank(obj);
			return Double.valueOf(longString);
		}catch(Exception e){
			return 0.0;
		}
	}
	/**
	 * 将obj转化为Boolean类型的数据，如果obj为空或null则返回null，如果不能转换则抛出异常
	 * @param obj
	 * @return null/Boolean
	 */
	public static Boolean toBoolean(Object obj){
		String s = Converter.toBlank(obj).toLowerCase();
		if(s.equals("")){
			return null;
		}else{
			return Boolean.valueOf(s);
		}
	}


}
