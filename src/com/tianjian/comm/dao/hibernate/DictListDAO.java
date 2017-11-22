package com.tianjian.comm.dao.hibernate;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.comm.bean.CommDictPublicChar;
import com.tianjian.comm.bean.CommDictPublicClass;
import com.tianjian.comm.bean.CommDictPublicDouble;
import com.tianjian.comm.bean.CommDictPublicInt;
import com.tianjian.comm.dao.IDictListDAO;
import com.tianjian.util.comm.PageBean;

public class DictListDAO extends HibernateDaoSupport implements IDictListDAO {

	private static final Logger log = LogManager.getLogger(DictListDAO.class);
	private static String PUBLICCHARLIST = "from CommDictPublicChar p where p.classCode=?";
	// 根据classcode,inputcode，获得列表
	// private static String MODLIST = "from CommDictPublicChar as dchar where dchar.classCode=? and dchar.inputCode like ?";
	private static String MODLIST2 = "from CommDictPublicChar as dchar where dchar.classCode=? and dchar.dictCode=?";
	private static String MODLISTchinese = "from CommDictPublicChar as dchar where dchar.classCode=? and dchar.dictValue like ?";
	private static String MODLISTenglish = "from CommDictPublicChar as dchar where dchar.classCode=? and dchar.inputCode like ?";
	private static String MODLISTnumber = "from CommDictPublicInt as dint where dint.classCode=? and dint.dictCode like ?";

	public List<?> listAllDict(PageBean p) {
		try {
			Session session = this.getSession();
			Query q = session.createQuery(p.getListSQL());
			q.setFirstResult((p.getPage() - 1) * p.getPageSize());
			q.setMaxResults(p.getPageSize());
			return q.list();
		}
		catch (Exception re) {
			log.error("listAllDict error!", re);
			re.printStackTrace();
			return null;
		}
	}

	public Integer getCount(PageBean p) {
		try {
			Session session = this.getSession();
			Query q = session.createQuery(p.getTotalCountSQL());
			Iterator<?> it = q.iterate();
			Integer num = (Integer) it.next();
			return num;
		}
		catch (Exception re) {
			log.error("getCount error!", re);
			re.printStackTrace();
			return null;
		}
	}

	public CommDictPublicClass getOnePublicDict(String id) {
		try {
			HibernateTemplate template = getHibernateTemplate();
			CommDictPublicClass dict = (CommDictPublicClass) template.get(CommDictPublicClass.class, id);
			return dict;
		}
		catch (Exception re) {
			log.error("listAllDict error!", re);
			re.printStackTrace();
			return null;
		}
	}

	public List<?> listAllDict() {
		try {
			HibernateTemplate template = getHibernateTemplate();
			List<?> result = template.find("from com.tianjian.comm.bean.CommDictPublicClass ");
			return result;
		}
		catch (Exception re) {
			log.error("getOnePublicDict error!", re);
			re.printStackTrace();
			return null;
		}
	}

	public CommDictPublicChar getOneCharDict(String id1, String id2) {
		try {
			HibernateTemplate template = getHibernateTemplate();
			Object[] o = { id1, id2 };
			List<?> result = template.find(MODLIST2, o);
			return (CommDictPublicChar) result.get(0);
		}
		catch (Exception re) {
			log.error("getOneCharDict error!", re);
			re.printStackTrace();
			return null;
		}
	}

	public CommDictPublicInt getOneIntDict(String id1, String id2) {
		try {
			HibernateTemplate template = getHibernateTemplate();
			Object[] o = { id1, id2 };
			List<?> result = template.find("from com.tianjian.comm.bean.CommDictPublicInt as d where d.classCode = ? and d.dictCode = ?", o);
			return (CommDictPublicInt) result.get(0);
		}
		catch (Exception re) {
			log.error("getOneIntDict error!", re);
			re.printStackTrace();
			return null;
		}
	}

	public CommDictPublicDouble getOneDoubleDict(String id1, String id2) {
		try {
			HibernateTemplate template = getHibernateTemplate();
			Object[] o = { id1, id2 };
			List<?> result = template.find("from com.tianjian.comm.bean.CommDictPublicDouble as d where d.classCode = ? and d.dictCode = ?", o);
			return (CommDictPublicDouble) result.get(0);
		}
		catch (Exception re) {
			log.error("getOneDoubleDict error!", re);
			re.printStackTrace();
			return null;
		}
	}

	// 罗巍填加
	/**
	 * 根据class_code 获得对象列表
	 */
	public List<?> listpubliccharbyClasscodeDao(String classcode) {
		try {
			List<?> list = this.getHibernateTemplate().find(PUBLICCHARLIST, classcode);
			if (list == null || list.size() == 0 || list.isEmpty()) {
				return null;
			} else {
				return list;
			}
		}
		catch (Exception re) {
			log.error("listpubliccharbyClasscodeDao error!", re);
			re.printStackTrace();
			return null;
		}
	}

	public List<?> listpubliccharbyCcodeAndIcodeDao(final String ccode, final String icode) {
		try {
			List<?> list = (List<?>) (this.getHibernateTemplate().execute(new HibernateCallback() {

				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					Query q = session.createQuery(MODLISTenglish);
					q.setString(0, ccode);
					q.setString(1, icode.toUpperCase() + "%");
					List<?> l = (List<?>) q.list();
					return l;
				}
			}));
			if (list.isEmpty() || list == null || list.size() == 0) {
				return null;
			}
			return list;
		}
		catch (Exception re) {
			log.error("listpubliccharbyCcodeAndIcodeDao error!", re);
			re.printStackTrace();
			return null;
		}
	}

	public CommDictPublicChar listpubliccharbyclasscAnddictcode(final String classcode, final String dictcode) {
		try {
			List<?> list = (List<?>) (this.getHibernateTemplate().execute(new HibernateCallback() {

				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					Query q = session.createQuery(MODLISTenglish);
					q.setString(0, classcode);
					q.setString(1, dictcode);
					List<?> l = (List<?>) q.list();
					return l;
				}
			}));
			if (list.isEmpty() || list == null || list.size() == 0) {
				return null;
			}
			return (CommDictPublicChar) list.get(0);
		}
		catch (Exception re) {
			log.error("listpubliccharbyclasscAnddictcode error!", re);
			re.printStackTrace();
			return null;
		}
	}

	public List<?> listpublicchinesebyCcodeAndIcode(final String ccode, final String icode) {
		try {
			List<?> list = (List<?>) (this.getHibernateTemplate().execute(new HibernateCallback() {

				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					Query q = session.createQuery(MODLISTchinese);
					q.setString(0, ccode);
					q.setString(1, icode + "%");
					List<?> l = (List<?>) q.list();
					return l;
				}
			}));
			if (list.isEmpty() || list == null || list.size() == 0) {
				return null;
			}
			return list;
		}
		catch (Exception re) {
			log.error("listpublicchinesebyCcodeAndIcode error!", re);
			re.printStackTrace();
			return null;
		}
	}

	public List<?> listpublicnumberbyCcodeAndIcode(final String ccode, final String icode) {
		try {
			List<?> list = (List<?>) (this.getHibernateTemplate().execute(new HibernateCallback() {

				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					Query q = session.createQuery(MODLISTnumber);
					q.setString(0, ccode);
					q.setString(1, icode + "%");
					List<?> l = (List<?>) q.list();
					return l;
				}
			}));
			if (list.isEmpty() || list == null || list.size() == 0) {
				return null;
			}
			return list;
		}
		catch (Exception re) {
			log.error("listpublicnumberbyCcodeAndIcode error!", re);
			re.printStackTrace();
			return null;
		}
	}
}
