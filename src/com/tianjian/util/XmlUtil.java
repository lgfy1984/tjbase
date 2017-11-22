package com.tianjian.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**用于创建与转换XML的工具类 将XML转换(Document类型)写入数据,与读取时的转换(成String)*/

public class XmlUtil {
	
	private final Logger log = Logger.getLogger(XmlUtil.class);
	
	/**创建一个空的Document
	 * @author 
	 * @param
	 * @return Document
	 * */
	public static Document create() {
        Document doc = null;
        //通过DocumentFactory取得一个实例,创建一个空文档
        doc = DocumentFactory.getInstance().createDocument();
        return doc;
    }
	
	/**以String创建一个Document
	 * @author 
	 * @param String xmlString
	 * @return Document
	 * */
    public static Document create(String xmlString) {
        StringReader source = new StringReader(xmlString);
        return create(source);
    }
    
    /**以Reader为源创建一个Document
     * @author 
     * @param Reader sourceReader
     * @return Document
     * */
    public static Document create(Reader sourceReader) {
        SAXReader reader = new SAXReader();
        Document doc = null;
        try {
            doc = reader.read(sourceReader);
            //设置文档的编码
            doc.setXMLEncoding("GBK");
        } catch (DocumentException e) {
            e.printStackTrace();
        } 
        return doc;
    }


    /**将Document类型的xml转换成String
     * @author 
     * @param Document xmlDoc
     * @return String
     * */
    public static String toPlanString(Document xmlDoc) {
        StringWriter destWriter = new StringWriter();
        XMLWriter writer = new XMLWriter(destWriter,OutputFormat.createPrettyPrint());
        try {
            writer.write(xmlDoc);            
        } catch (IOException e) {
            e.printStackTrace();
        }
        String xmlStr = destWriter.getBuffer().toString();
        return xmlStr;
    }

    /***
	 * 读取XML文件,获得document对象
	 * @param String fileName
	 * @return Document
	 * @author 刘祥林
	 */
	public static Document file2Doc(String fileName){
		if(new File(fileName).exists()){
			SAXReader sAXReader = new SAXReader();
			try {
				return sAXReader.read(fileName);
			} catch (DocumentException e) {
				e.printStackTrace();
				return null;
			}
		}else{
		
			System.out.println("The following file or directory does not exist\n\t"+fileName);
			return null;
		}
	}
	
	/***
	 * 解析XML形式的文本,得到document对象
	 * @param String text
	 * @return Document
	 * @author 刘祥林
	 */
	public static Document text2XML(String text){
		try {
			return DocumentHelper.parseText(text);
		} catch (DocumentException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**验证XML文档或文档是否符合相应的XSD 
	 * @author 
	 * @param String xmlStr
	 * @param String xsdStr
	 * @return boolean
	 * */
	public static boolean verifyXMLByXSD(String xmlStr, String xsdStr){
		SchemaFactory sf = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
		Source source = new StreamSource(new StringReader(xsdStr));
		try {
			Schema schema = sf.newSchema(source);
			Validator v = schema.newValidator();
			Source source2 = new StreamSource(new StringReader(xmlStr));
			v.validate(source2);
			return true;
		} catch (SAXException e) {
//			e.printStackTrace();
			return false;
		} catch (IOException e) {
//			e.printStackTrace();
			return false;
		}
	}
	
	/**验证XML文档或文档是否符合相应的XSD 
	 * @author 
	 * @param Source xmlStr
	 * @param Source xsdStr
	 * @return boolean
	 * */
	public static boolean verifyXMLByXSD(Source xmlStr, Source xsdStr){
		SchemaFactory sf = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
		try {
			Schema schema = sf.newSchema(xmlStr);
			Validator v = schema.newValidator();
			v.validate(xsdStr);
			return true;
		} catch (SAXException e) {
//			e.printStackTrace();
			return false;
		} catch (IOException e) {
//			e.printStackTrace();
			return false;
		}
	}


	/***
	 * 取头节点标签中的值
	 * 根据xpath取文档中对应标签的值
	 * @param org.w3c.dom.Document doc
	 * @param String xpath
	 * @return String
	 */
	public static String getValueByXPath(org.w3c.dom.Document doc, String xpath){
		Node node = null;
		try {
			XPath xPath = XPathFactory.newInstance().newXPath();
			String expression = xpath;
			node = (Node) xPath.evaluate(expression, doc, XPathConstants.NODE);
			return node.getTextContent();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static String resCreate(String clientID, String exception, String totalCount,
			String result) {
		// 若参数有为NULL，则将其置为长度为0的字符串，增强程序的健壮性
		if (clientID == null)
			clientID = "";
		if (exception == null)
			exception = "";
		if (totalCount == null)
			totalCount = "";
		if (result == null)
			result = "";
		// 生成XML对象
		Document doc = DocumentHelper.createDocument();
		doc.setXMLEncoding("gb2312");
		// 添加根元素
		Element responseElement = doc.addElement("RESPONSE",
				"http://www.medvision.com.cn/response");
		// 添加其他子元素
		Element clientIDElement = responseElement.addElement("CLIENTID");
		clientIDElement.setText(clientID);
		Element exceptionElement = responseElement.addElement("EXCEPTION");
		exceptionElement.setText(exception);
		exceptionElement.setText(exception);
		Element totalCountElement = responseElement.addElement("TOTALCOUNT");
		totalCountElement.setText(totalCount);
		Element resultElement = responseElement.addElement("RESULT");
		resultElement.setText(result);
		// 返回XML字符串
		return doc.asXML();
	}
	
	/**
	 * xml文件转换为 String
	 */
	public static String xmlToStr(String filePath) {
		@SuppressWarnings("unused")
		long lasting = System.currentTimeMillis();
		@SuppressWarnings("unused")
		String text = "";
		String line = "";
		try {
			File f = new File(filePath);
			FileReader fr = new FileReader(f);
			BufferedReader bfr = new BufferedReader(fr);
			while ((line = bfr.readLine()) != null) {
				text += line;
			}
			bfr.close();
			fr.close();
			f.delete();
			String tre;
			tre = new String(text.getBytes("GBK"),"utf-8");
			return tre;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
