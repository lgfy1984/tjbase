package com.tianjian.tenant.dao;

import java.util.List;

import com.tianjian.tenant.bean.TenantBaseinfo;

public interface ITenantBaseinfoDAO {

	// property constants
	public static final String ITEM_CODE = "itemCode";
	public static final String ITEM_NAME = "itemName";
	public static final String ADDRESS = "address";
	public static final String ZIPCODE = "zipcode";
	public static final String CONTACT_PERSON_NAME = "contactPersonName";
	public static final String PHONE = "phone";
	public static final String COMMENTS = "comments";
	public static final String SEQ_NO = "seqNo";
	public static final String _EMAIL = "EMail";
	public static final String PASSWD = "passwd";
	public static final String CREATE_USER_ID = "createUserId";
	public static final String CREATE_USER_NAME = "createUserName";

	public abstract void save(TenantBaseinfo transientInstance);

	public abstract void delete(TenantBaseinfo persistentInstance);

	public abstract TenantBaseinfo findById(java.lang.String id);

	public abstract List findByExample(TenantBaseinfo instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByItemCode(Object itemCode);

	public abstract List findByItemName(Object itemName);

	public abstract List findByAddress(Object address);

	public abstract List findByZipcode(Object zipcode);

	public abstract List findByContactPersonName(Object contactPersonName);

	public abstract List findByPhone(Object phone);

	public abstract List findByComments(Object comments);

	public abstract List findBySeqNo(Object seqNo);

	public abstract List findByEMail(Object EMail);

	public abstract List findByPasswd(Object passwd);

	public abstract List findByCreateUserId(Object createUserId);

	public abstract List findByCreateUserName(Object createUserName);

	public abstract List findAll();

	public abstract List findIdAndNameList();

	public abstract TenantBaseinfo merge(TenantBaseinfo detachedInstance);

	public abstract void attachDirty(TenantBaseinfo instance);

	public abstract void attachClean(TenantBaseinfo instance);

}