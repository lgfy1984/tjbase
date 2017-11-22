/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.tianjian.comm.dao.hibernate;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.comm.bean.CommConfigCardtype;
import com.tianjian.comm.dao.ICommConfigCardtypeDAO;

public class CommConfigCardtypeDAO extends HibernateDaoSupport
    implements ICommConfigCardtypeDAO
{

    public CommConfigCardtypeDAO()
    {
    }

    public CommConfigCardtype findById(String id)
    {
        try
        {
            CommConfigCardtype fac = null;
            List ls = getHibernateTemplate().find(" from CommConfigCardtype a where a.itemCode = ? ", id);
            if(ls != null && ls.size() > 0)
                fac = (CommConfigCardtype)ls.get(0);
            log.debug("findById success!");
            return fac;
        }
        catch(Exception re)
        {
            log.error("findById fail!", re);
            re.printStackTrace();
            return null;
        }
    }

    public String getItemName(String id)
    {
        String itemName = "";
        try
        {
            List l = getHibernateTemplate().find(" from CommConfigCardtype aa where aa.itemCode = ? ", id);
            if(l != null && l.size() > 0)
            {
                CommConfigCardtype pub = (CommConfigCardtype)l.get(0);
                itemName = pub.getItemName();
            }
            log.debug("getClassName success!");
            return itemName;
        }
        catch(Exception re)
        {
            log.error("getClassName fail!", re);
            re.printStackTrace();
            return null;
        }
    }

    public void save(CommConfigCardtype commConfigCardtype)
    {
        try
        {
            getHibernateTemplate().save(commConfigCardtype);
            log.debug("save success!");
        }
        catch(Exception re)
        {
            log.error("save fail!", re);
            re.printStackTrace();
        }
    }

    public void update(CommConfigCardtype commConfigCardtype)
    {
        try
        {
            getHibernateTemplate().update(commConfigCardtype);
            log.debug("update success!");
        }
        catch(Exception re)
        {
            log.error("update fail!", re);
            re.printStackTrace();
        }
    }

    public void delete(CommConfigCardtype commConfigCardtype)
    {
        try
        {
            getHibernateTemplate().delete(commConfigCardtype);
            log.debug("delete success!");
        }
        catch(Exception re)
        {
            log.error("delete fail!", re);
            re.printStackTrace();
        }
    }

    public List findAll()
    {
        try
        {
            String sql = " from CommConfigCardtype a  ";
            Query q = getSession().createQuery(sql);
            List l = q.list();
            log.debug("findAll success!");
            return l;
        }
        catch(Exception re)
        {
            log.error("findAll fail", re);
            re.printStackTrace();
            return null;
        }
    }

    public List getData(String itemCode, String itemName, String inputCode, String seqNo, String orderNo, int curCount, int pageSize)
    {
        try
        {
            String sql = " select a  ";
            sql = (new StringBuilder(String.valueOf(sql))).append(" from CommConfigCardtype a  where 1=1 ").toString();
            if(itemCode.trim().length() > 0)
                sql = (new StringBuilder(String.valueOf(sql))).append(" and a.itemCode = '").append(itemCode.trim()).append("' ").toString();
            if(itemName.trim().length() > 0)
                sql = (new StringBuilder(String.valueOf(sql))).append(" and a.itemName like '%").append(itemName.trim()).append("%' ").toString();
            if(inputCode.trim().length() > 0)
                sql = (new StringBuilder(String.valueOf(sql))).append(" and a.inputCode like '").append(inputCode.trim().toUpperCase()).append("%' ").toString();
            if(seqNo.trim().length() > 0)
                sql = (new StringBuilder(String.valueOf(sql))).append(" and a.seqNo = '").append(seqNo.trim()).append("' ").toString();
            if(orderNo.trim().length() > 0)
                sql = (new StringBuilder(String.valueOf(sql))).append(" order by ").append(orderNo).toString();
            Query q = getSession().createQuery(sql);
            q.setFirstResult(curCount);
            q.setMaxResults(pageSize);
            List l = q.list();
            log.debug("getData success!");
            return l;
        }
        catch(Exception re)
        {
            log.error("getData fail!", re);
            re.printStackTrace();
            return null;
        }
    }

    public int getCount(String itemCode, String itemName, String inputCode, String seqNo)
    {
        try
        {
            int count = 0;
            String sql = "select count(*) ";
            sql = (new StringBuilder(String.valueOf(sql))).append(" from CommConfigCardtype a  where 1=1  ").toString();
            if(itemCode.trim().length() > 0)
                sql = (new StringBuilder(String.valueOf(sql))).append(" and a.itemCode = '").append(itemCode.trim()).append("' ").toString();
            if(itemName.trim().length() > 0)
                sql = (new StringBuilder(String.valueOf(sql))).append(" and a.itemName like '%").append(itemName.trim()).append("%' ").toString();
            if(inputCode.trim().length() > 0)
                sql = (new StringBuilder(String.valueOf(sql))).append(" and a.inputCode like '").append(inputCode.trim().toUpperCase()).append("%' ").toString();
            if(seqNo.trim().length() > 0)
                sql = (new StringBuilder(String.valueOf(sql))).append(" and a.seqNo = '").append(seqNo.trim()).append("' ").toString();
            List list = getHibernateTemplate().find(sql);
            if(list != null && list.size() > 0)
                count = Integer.valueOf(String.valueOf(list.get(0))).intValue();
            log.debug("getCount success!");
            return count;
        }
        catch(Exception re)
        {
            log.error("getCount fail!", re);
            re.printStackTrace();
            return (new Integer("0")).intValue();
        }
    }

    public int getMaxSeqNo()
    {
        int temp = 1;
        List l;
        try
        {
            l = getHibernateTemplate().find(" select max(aa.seqNo) from CommConfigCardtype aa ");
            if(l == null || l.size() <= 0)
               // break MISSING_BLOCK_LABEL_58;
              //Object o = l.get(0);
            //if(o == null)
               // return temp;
            return Integer.valueOf(l.get(0).toString());
        }
        catch(Exception re)
        {
            log.error("getMaxSeqNo fail!", re);
            re.printStackTrace();
            return (new Integer("1")).intValue();
        }
        temp = Integer.valueOf(String.valueOf(l.get(0))).intValue() + 1;
        log.debug("getMaxSeqNo success!");
        return temp;
    }

    private static final Logger log = LogManager.getLogger("com/tianjian/comm/dao/hibernate/CommConfigCardtypeDAO");

}


/*
	DECOMPILATION REPORT

	Decompiled from: D:\j2ee-workspace\workspacetjall\EHRPProject\WebRoot\WEB-INF\lib\tj-ddsip2.0.201005101011.jar
	Total time: 63 ms
	Jad reported messages/errors:
Couldn't resolve all exception handlers in method getMaxSeqNo
	Exit status: 0
	Caught exceptions:
*/