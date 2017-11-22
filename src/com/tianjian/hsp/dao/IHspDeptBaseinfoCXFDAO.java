package com.tianjian.hsp.dao;

import java.util.List;

import com.tianjian.hsp.bean.BaseMessage;
import com.tianjian.hsp.bean.HspDeptBaseinfo;

public interface IHspDeptBaseinfoCXFDAO {
	public boolean save(Object obj) throws Exception;
	public HspDeptBaseinfo findById(String deptCode, String hspConfigBaseinfoId);
	public void merge(HspDeptBaseinfo hdb);
	public void delete(HspDeptBaseinfo hdb);
	public List<?> queryByCondition(BaseMessage baseMessage, String seqNo, String hspCode, String hspName,
			String healthBureauCode, String socialSecurityBureauCode, String deptAttrCode, String deptAttrName, String deptCode,
			String deptName, String inputCode);
}
