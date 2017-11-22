package com.tianjian.comm.business;

import com.tianjian.comm.bean.CommConfigDegreeLevel;
import com.tianjian.comm.struts.form.CommConfigDegreeLevelForm;

public interface ICommConfigDegreeLevelService{

	public void addInit(CommConfigDegreeLevelForm form);

	public CommConfigDegreeLevel checkData(CommConfigDegreeLevelForm form);

	public void save(CommConfigDegreeLevelForm form);

	public int getCount(CommConfigDegreeLevelForm form);

	public void getSearch(CommConfigDegreeLevelForm form, int currentPageIndex, int pageSize);

	public void searchInit(CommConfigDegreeLevelForm form);

	public void updateInit(CommConfigDegreeLevelForm form);

	public void update(CommConfigDegreeLevelForm form);

	public void delete(CommConfigDegreeLevelForm form);
}
