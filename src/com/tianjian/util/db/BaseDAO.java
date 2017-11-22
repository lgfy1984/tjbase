package com.tianjian.util.db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.*;

public abstract class BaseDAO {

	protected void removeObj(Class<?> c, Long id) throws DAOException {
		Session session = null;
		Transaction txc = null;
		try {
			session = HibernateUtil.currentSession();
			txc = session.beginTransaction();
			Object obj = session.load(c, id);
			session.delete(obj);
			txc.commit();
		}
		catch (Exception e) {
			try {
				txc.rollback();
			}
			catch (HibernateException he) {
				he.printStackTrace();
			}
			throw new DAOException(e);
		}
		finally {
			closeSession();
		}
	}

	protected void removeObjs(String queryString) throws DAOException {
		Session session = null;
		Transaction txc = null;
		try {
			session = HibernateUtil.currentSession();
			txc = session.beginTransaction();
			session.delete(queryString);
			txc.commit();
		}
		catch (HibernateException ex) {
			try {
				txc.rollback();
			}
			catch (HibernateException he) {
				he.printStackTrace();
			}
			throw new DAOException(ex);
		}
		finally {
			closeSession();
		}
	}

	@SuppressWarnings("unused")
	private void removeObjs(String queryString, List<?> values) throws DAOException {
		Session session = null;
		Transaction txc = null;
		try {
			session = HibernateUtil.currentSession();
			txc = session.beginTransaction();
			//Object[] os = values.toArray();
			// session.delete(queryString, os, List2Types.getTypes(os));
			txc.commit();
		}
		catch (HibernateException ex) {
			try {
				txc.rollback();
			}
			catch (HibernateException he) {
				he.printStackTrace();
			}
			throw new DAOException(ex);
		}
		finally {
			closeSession();
		}
	}

	@SuppressWarnings("finally")
	protected Object retrieveObj(Class<?> c, Long id) throws DAOException {
		Object obj = null;
		try {
			Session session = HibernateUtil.currentSession();
			// obj = session.load(c, id);
			obj = session.get(c, id);
		}
		catch (HibernateException he) {
			he.printStackTrace();
			throw new DAOException(he);
		}
		finally {
			closeSession();
			return obj;
		}
	}

	protected Object retrieveObj(String key) throws DAOException {
		Object value = null;
		return retrieveObj(key, value);
	}

	protected Object retrieveObj(String key, Object value) throws DAOException {
		List<?> objects = retrieveObjs(key, value);
		if (objects != null) {
			if (objects.size() == 0) {
				return null;
			} else {
				return objects.get(0);
			}
		} else {
			return null;
		}
	}

	protected Object retrieveObj(String key, List<Object> value) throws DAOException {
		List<?> objects = retrieveObjs(key, value);
		if (objects != null) {
			if (objects.size() == 0) {
				return null;
			} else {
				return objects.get(0);
			}
		} else {
			return null;
		}
	}

	protected List<?> retrieveObjs(String queryString) throws DAOException {
		Object value = null;
		return this.retrieveObjs(queryString, value);
	}

	protected List<?> retrieveObjs(String queryString, Object value) throws DAOException {
		List<Object> values = new ArrayList<Object>();
		values.add(value);
		return retrieveObjs(queryString, values);
	}

	protected List<?> retrieveObjs(String queryString, List<Object> value) throws DAOException {
		try {
			Session session = HibernateUtil.currentSession();
			Query q = session.createQuery(queryString);
			int len = value.size();
			if (!(1 == len && null == value.get(0))) {
				for (int i = 0; i < len; i++) {
					q.setParameter(i, value.get(i));
				}
			}
			List<?> list = q.list();
			return list;
		}
		catch (HibernateException he) {
			he.printStackTrace();
			throw new DAOException(he);
		}
		finally {
			closeSession();
		}
	}

	protected List<?> retrieveObjs(String queryString, List<Object> value, long first, long number) throws DAOException {
		try {
			Session session = HibernateUtil.currentSession();
			Query q = session.createQuery(queryString);
			int len = value.size();
			if (!(1 == len && null == value.get(0))) {
				for (int i = 0; i < len; i++) {
					q.setParameter(i, value.get(i));
				}
			}
			q.setFirstResult((int) first);
			q.setMaxResults((int) number);
			List<?> list = q.list();
			return list;
		}
		catch (HibernateException he) {
			he.printStackTrace();
			throw new DAOException(he);
		}
		finally {
			closeSession();
		}
	}

	protected List<?> retrieveObjs(String queryString, Object value, long first, long number) throws DAOException {
		List<Object> values = new ArrayList<Object>();
		values.add(value);
		return retrieveObjs(queryString, values, first, number);
	}

	protected List<?> retrieveObjs(String queryString, String p0, List<?> value) throws DAOException {
		try {
			if (value == null || value.size() == 0) {
				return new ArrayList<Object>();
			}
			Session session = HibernateUtil.currentSession();
			Query q = session.createQuery(queryString);
			q.setParameterList(p0, value);
			List<?> list = q.list();
			return list;
		}
		catch (HibernateException he) {
			he.printStackTrace();
			throw new DAOException(he);
		}
		finally {
			closeSession();
		}
	}

	protected void storeObj(Object obj) throws DAOException {
		Session session = null;
		Transaction txc = null;
		try {
			session = HibernateUtil.currentSession();
			txc = session.beginTransaction();
			session.saveOrUpdate(obj);
			txc.commit();
		}
		catch (Exception e) {
			try {
				txc.rollback();
			}
			catch (HibernateException hex) {
				hex.printStackTrace();
			}
			throw new DAOException(e);
		}
		finally {
			closeSession();
		}
	}

	protected void closeSession() {
		try {
			HibernateUtil.closeSession();
		}
		catch (HibernateException he) {
			System.err.println(he.getMessage());
		}
	}

	@SuppressWarnings("deprecation")
	protected void rollback() throws DAOException {
		try {
			Session session = HibernateUtil.currentSession();
			if (session != null) {
				session.connection().rollback();
			}
		}
		catch (HibernateException he) {
			throw new DAOException(he);
		}
		catch (SQLException sqle) {
			throw new DAOException(sqle);
		}
	}

	@SuppressWarnings("unchecked")
	protected long retrieveObjsCount(String queryString) throws DAOException {
		List values = new ArrayList<Object>();
		return retrieveObjsCount(queryString, values);
	}

	@SuppressWarnings("unchecked")
	protected long retrieveObjsCount(String queryString, Object value) throws DAOException {
		List values = new ArrayList();
		values.add(value);
		return retrieveObjsCount(queryString, values);
	}

	protected long retrieveObjsCount(String queryString, List<?> values) throws DAOException {
		try {
			Session session = HibernateUtil.currentSession();
			Query q = session.createQuery("select count(*) " + queryString);
			int len = values.size();
			if (len > 0) {
				for (int i = 0; i < len; i++) {
					q.setParameter(i, values.get(i));
				}
			}
			return ((Long) q.iterate().next()).longValue();
		}
		catch (HibernateException he) {
			throw new DAOException(he);
		}
		finally {
			closeSession();
		}
	}

	@SuppressWarnings({ "unchecked", "finally" })
	protected List retrieveObjsByQuery(String queryString, List<?> value, long first, long max) throws DAOException {
		List results = new ArrayList();
		Iterator it = null;
		try {
			Session session = HibernateUtil.currentSession();
			Query q = session.createQuery(queryString);
			if (value != null) {
				int len = value.size();
				for (int i = 0; i < len; i++) {
					q.setParameter(i, value.get(i));
				}
			}
			q.setFirstResult((int) first);
			q.setMaxResults((int) max);
			it = q.iterate();
			while (it.hasNext()) {
				results.add(it.next());
			}
		}
		catch (HibernateException he) {
			throw new DAOException(he);
		}
		finally {
			closeSession();
			return results;
		}
	}
}
