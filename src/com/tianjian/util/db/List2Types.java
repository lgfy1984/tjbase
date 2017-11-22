package com.tianjian.util.db;

import org.hibernate.type.*;
import java.util.*;
import org.hibernate.Hibernate;

public class List2Types {

	public static Type[] getTypes(Object[] os) {
		int size = os.length;
		Type[] types = new Type[size];
		for (int i = 0; i < size; i++) {
			if (os[i] instanceof java.lang.String) {
				types[i] = Hibernate.STRING;
				continue;
			}
			if (os[i] instanceof java.lang.Long) {
				types[i] = Hibernate.LONG;
				continue;
			}
			if (os[i] instanceof java.lang.Integer) {
				types[i] = Hibernate.INTEGER;
				continue;
			}
			if (os[i] instanceof java.util.Date) {
				types[i] = Hibernate.DATE;
				continue;
			}
			if (os[i] instanceof java.lang.Short) {
				types[i] = Hibernate.SHORT;
				continue;
			}
			if (os[i] instanceof java.lang.Float) {
				types[i] = Hibernate.FLOAT;
				continue;
			}
			if (os[i] instanceof java.lang.Double) {
				types[i] = Hibernate.DOUBLE;
				continue;
			}
		}
		return types;
	}

	public static Type[] getTypes(List<Object> values) {
		Object[] os = values.toArray();
		Type[] types = getTypes(os);
		return types;
	}
}
