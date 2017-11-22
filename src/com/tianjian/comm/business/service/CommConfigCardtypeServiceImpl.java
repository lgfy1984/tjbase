package com.tianjian.comm.business.service;

import com.tianjian.comm.bean.CommConfigCardtype;
import com.tianjian.comm.business.ICommConfigCardtypeService;
import com.tianjian.comm.dao.ICommConfigCardtypeDAO;
import com.tianjian.comm.struts.form.CommConfigCardtypeForm;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class CommConfigCardtypeServiceImpl
    implements ICommConfigCardtypeService
{

    public CommConfigCardtypeServiceImpl()
    {
    }

    public ICommConfigCardtypeDAO getCommConfigCardtypeDAO()
    {
        return commConfigCardtypeDAO;
    }

    public void setCommConfigCardtypeDAO(ICommConfigCardtypeDAO commConfigCardtypeDAO)
    {
        this.commConfigCardtypeDAO = commConfigCardtypeDAO;
    }

    public void addInit(CommConfigCardtypeForm form)
    {
        init(form);
    }

    public void serchInit(CommConfigCardtypeForm form)
    {
    	 CommConfigCardtype data = commConfigCardtypeDAO.findById(form.getItemCodeHidden());
    	 this.setForm(form, data);
    }

    public CommConfigCardtype checkData(String id)
    {
        CommConfigCardtype data = commConfigCardtypeDAO.findById(id);
        return data;
    }

    public void save(CommConfigCardtypeForm form)
    {
        CommConfigCardtype data = new CommConfigCardtype();
        setData(form, data);
        commConfigCardtypeDAO.save(data);
    }

    public void updateInit(CommConfigCardtypeForm form)
    {
        CommConfigCardtype data = commConfigCardtypeDAO.findById(form.getItemCodeHidden());
        setForm(form, data);
        init(form);
    }

    public void update(CommConfigCardtypeForm form)
    {
        CommConfigCardtype data = commConfigCardtypeDAO.findById(form.getItemCodeHidden());
        //setData(form, data);
        data.setStopFlag(Long.valueOf(form.getStopFlag()));
        commConfigCardtypeDAO.update(data);
    }

    public void showInit(CommConfigCardtypeForm form)
    {
        CommConfigCardtype data = commConfigCardtypeDAO.findById(form.getItemCodeHidden());
        setForm(form, data);
        init(form);
    }

    public void delete(CommConfigCardtypeForm form)
    {
        CommConfigCardtype data = commConfigCardtypeDAO.findById(form.getItemCodeHidden());
        commConfigCardtypeDAO.delete(data);
    }

    public int getCount(String itemCode, String itemName, String inputCode, String seqNo)
    {
        return commConfigCardtypeDAO.getCount(itemCode, itemName, inputCode, seqNo);
    }

    public void getSearch(CommConfigCardtypeForm form, int curCount, int pageSize)
    {
        try
        {
            String order = "";
            if(form.getOrderNo().equals("1"))
                order = " a.itemCode";
            else
            if(form.getOrderNo().equals("2"))
                order = " a.itemName";
            else
            if(form.getOrderNo().equals("3"))
                order = " a.seqNo";
            else
            if(form.getOrderNo().equals("4"))
                order = " a.inputCode";
            else
                order = " a.seqNo";
            if(form.getAsc().equals("1"))
                order = (new StringBuilder(String.valueOf(order))).append(" desc").toString();
            else
                order = (new StringBuilder(String.valueOf(order))).append(" asc").toString();
            List list = commConfigCardtypeDAO.getData(form.getItemCode(), form.getItemName(), form.getInputCode(), form.getSeqNo(), order, curCount, pageSize);
            if(list != null && list.size() > 0)
            {
                String itemCodes[] = new String[list.size()];
                String itemNames[] = new String[list.size()];
                String inputCodes[] = new String[list.size()];
                String comments[] = new String[list.size()];
                String seqNos[] = new String[list.size()];
                String stopFlags[] =new String[list.size()];
                for(int i = 0; i < list.size(); i++)
                {
                	CommConfigCardtype nCommConfigCardtype=(CommConfigCardtype) list.get(i);
                    itemCodes[i] = String.valueOf(transNullToString(nCommConfigCardtype.getItemCode()));
                    itemNames[i] = String.valueOf(transNullToString(nCommConfigCardtype.getItemName()));
                    inputCodes[i] = String.valueOf(transNullToString(nCommConfigCardtype.getInputCode()));
                    comments[i] = String.valueOf(transNullToString(nCommConfigCardtype.getComments()));
                    seqNos[i] = nCommConfigCardtype.getSeqNo() != null ? String.valueOf(nCommConfigCardtype.getSeqNo()) : "0";
                    stopFlags[i]=String.valueOf(transNullToString(nCommConfigCardtype.getStopFlag().toString()));
                }

                form.setItemCodeList(itemCodes);
                form.setItemNameList(itemNames);
                form.setInputCodeList(inputCodes);
                form.setCommentsList(comments);
                form.setSeqNoList(seqNos);
                form.setStopFlagList(stopFlags);
            }
        }
        catch(Exception e)
        {
            log.error("getSearch fail!", e);
            e.printStackTrace();
        }
    }

    private void setData(CommConfigCardtypeForm form, CommConfigCardtype data)
    {
        try
        {
            data.setItemCode(transNullToString(form.getItemCode()));
            data.setItemName(transNullToString(form.getItemName()));
            data.setInputCode(transNullToString(form.getInputCode()));
            data.setComments(transNullToString(form.getComments()));
            data.setSeqNo(Long.valueOf(form.getSeqNo() != null && form.getSeqNo().trim() != "" ? form.getSeqNo().trim() : "0"));
            data.setStopFlag(Long.valueOf(form.getStopFlag() != null && form.getStopFlag().trim() != "" ? form.getStopFlag().trim() : "0"));
        }
        catch(Exception e)
        {
            log.error("setData fail!", e);
            e.printStackTrace();
        }
    }

    public String transNullToString(Object obj)
    {
        String temp = "";
        if(obj != null)
            temp = ((String)obj).trim();
        return temp;
    }

    public String transNullToZero(Object obj)
    {
        String temp = "0";
        if(obj != null)
            temp = ((String)obj).trim();
        return temp;
    }

    private void setForm(CommConfigCardtypeForm form, CommConfigCardtype data)
    {
        try
        {
            form.setItemCodeHidden(transNullToString(data.getItemCode()));
            form.setItemCode(transNullToString(data.getItemCode()));
            form.setItemName(transNullToString(data.getItemName()));
            form.setInputCode(transNullToString(data.getInputCode()));
            form.setComments(transNullToString(data.getComments()));
            form.setSeqNo(data.getSeqNo() != null ? String.valueOf(data.getSeqNo()) : "0");
            form.setStopFlag(transNullToString(data.getStopFlag().toString()));
        }
        catch(Exception e)
        {
            log.error("setForm fail!", e);
            e.printStackTrace();
        }
    }

    private void init(CommConfigCardtypeForm commconfigcardtypeform)
    {
    }

    public void getDetail(CommConfigCardtypeForm commconfigcardtypeform)
    {
    }

    private void searchInit(CommConfigCardtypeForm commconfigcardtypeform)
    {
    }

    private static final Logger log = LogManager.getLogger("com/tianjian/comm/business/service/CommConfigCardtypeServiceImpl");
    private ICommConfigCardtypeDAO commConfigCardtypeDAO;

}