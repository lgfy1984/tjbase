package com.tianjian.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Clob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleTypes;
import oracle.sql.OPAQUE;
import oracle.xdb.XMLType;

import org.dom4j.Document;
import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

import com.tianjian.util.XmlUtil;
/**自定义类型,用于hibernater映射xmltype类型的字段*/
public class XmlTypeUtil implements UserType, Serializable {
	private static final long serialVersionUID = -1042385380684391697L;
	private static final Class returnedClass = String.class; // 属性的java类型
	private static final int[] SQL_TYPES = new int[] { oracle.xdb.XMLType._SQL_TYPECODE }; // 数据中类型
	/**自定义类型所对应的SQL数据类型*/
	public int[] sqlTypes() {
		return SQL_TYPES;
	}
	/**返回nullSafeGet返回的对象的类型*/
	public Class returnedClass() {
		return returnedClass;
	}
	/**脏数据检查*/
	public boolean equals(Object arg0, Object arg1) throws HibernateException {
		if (arg0 == null || arg1 == null) {
			throw new HibernateException("None of the arguments can be null.");
		}
		if (arg0 instanceof oracle.xdb.XMLType
				&& arg1 instanceof oracle.xdb.XMLType) {
			return arg0.equals(arg1);
		}
		return false;
	}
	/**hashCode*/
	public int hashCode(Object arg0) throws HibernateException {
		return 0;
	}
	public Object nullSafeGet(ResultSet rs, String[] names, Object arg2)
    throws HibernateException, SQLException {
		XMLType xmlType = null;
		Document doc = null;
		try {
			OPAQUE value = null;
			OracleResultSet ors = null;
			if (rs instanceof OracleResultSet) {
				ors = (OracleResultSet) rs;
			} else {
				throw new UnsupportedOperationException(
				"ResultSet needs to be of type OracleResultSet");
			}
			value = ors.getOPAQUE(names[0]);
			xmlType = XMLType.createXML(value);
			Clob xmlClob = xmlType.getClobVal();
			doc = XmlUtil.create(xmlClob.getCharacterStream());
		} finally {
			if (null != xmlType) {
				xmlType.close();
			}
		}
		return doc;
	}
	public void nullSafeSet(PreparedStatement stmt, Object value, int index)
    throws HibernateException, SQLException {
		XMLType xmlType = null;
		try {
			// If the value is null then set NULL and return
			if (null == value) {
				stmt.setNull(index, OracleTypes.OPAQUE, "SYS.XMLTYPE");
				return;
			}
			if (stmt instanceof OraclePreparedStatement) {
				InputStream instream = new ByteArrayInputStream(
						XmlUtil.toPlanString((Document) value).getBytes());
		        
				xmlType = XMLType.createXML(stmt.getConnection(), instream);
				OraclePreparedStatement oracleStmt = (OraclePreparedStatement) stmt;
				oracleStmt.setObject(index, xmlType);
			} else {
				throw new HibernateException(
				"PreparedStatement object must be a OraclePreparedStatement");
			}
		} finally {
			if (null != xmlType) {
				xmlType.close();
			}
		}
   }

	/**deepCopy*/
	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}
	/**是否可变*/
	public boolean isMutable() {
		return false;
	}
	/**disassemble*/
	public Serializable disassemble(Object arg0) throws HibernateException {
		return null;
	}
	/**assemble*/
	public Object assemble(Serializable arg0, Object arg1)
	throws HibernateException {
		return null;
	}
	/**replace*/
	public Object replace(Object arg0, Object arg1, Object arg2)
	throws HibernateException {
		return null;
	}
}
