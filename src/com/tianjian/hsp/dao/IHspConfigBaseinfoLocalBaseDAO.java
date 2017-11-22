package com.tianjian.hsp.dao;

import java.util.List;

public interface IHspConfigBaseinfoLocalBaseDAO {
	//医疗机构弹出层
	public List<?> findHspList(String flag,String inputCode,String staffHspId, int curCount, int pageSize);
}
