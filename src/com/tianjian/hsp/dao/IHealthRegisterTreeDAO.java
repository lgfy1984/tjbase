package com.tianjian.hsp.dao;

import java.util.List;

public interface IHealthRegisterTreeDAO {
	/**
	 * 使用sql查询最大序号数，返回String类型
	 * @param tableName 表面
	 * @param seqNoFieldName 序号的字段名
	 * @return
	 */
	public String getMaxSeqNo(String tableName, String seqNoFieldName);

	public boolean delete(Object obj);
	
	public boolean save(Object obj);

	public boolean merge(Object obj);

	public List<?> getDeptByHspId(String flag, String inputCode, String hspId,
			int currentCount, int pageSize);

	public List<?> getDeptByHspCode(String flag, String inputCode,
			String hspCode, Integer currentCount, Integer pageSize);
	
}
