package com.tianjian.comm.business.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tianjian.comm.bean.CommTableDateId;
import com.tianjian.comm.business.ICommTableDateIdService;
import com.tianjian.comm.dao.ICommTableDateIdDAO;

public class CommTableDateIdServiceImpl implements ICommTableDateIdService{
	
	private static final Log log = LogFactory.getLog(CommTableDateIdServiceImpl.class);
	
	private ICommTableDateIdDAO dao;

	/**
	 * @return Returns the dao.
	 */
	public ICommTableDateIdDAO getDao() {
		return dao;
	}

	/**
	 * @param dao The dao to set.
	 */
	public void setDao(ICommTableDateIdDAO dao) {
		this.dao = dao;
	}
	
	public String getSeq(String tableName, String columnName, long maxLen){
		String temp = "";
		try{
			long tempLong = 0;
			long tempId = 0;
			tableName = tableName.toUpperCase();
			columnName = columnName.toUpperCase();
			
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd"); 
			Date date = new Date();
			String dateString = format.format(date);
			date = format.parse(dateString);
			
			CommTableDateId tableDate = dao.getByColumn(tableName, columnName, date);
			if(tableDate != null && tableDate.getTableName() != null){
				tempLong = tableDate.getIdvalue().longValue() + 1;
				temp = dateString + this.getObjectPad(Long.valueOf(String.valueOf(tempLong)), maxLen);
				tableDate.setIdvalue(Long.valueOf(String.valueOf(tempLong)));
				dao.update(tableDate) ;
				return temp;
			}
			
			List<?> ls = dao.getTableColumn(tableName, columnName);
			if(ls != null && ls.size() > 0){
				for(int i = 0; i < ls.size(); i++){
					CommTableDateId tempTable = (CommTableDateId) ls.get(i);
					tempId = tempTable.getId().longValue();
					dao.delete(tempTable);
				}
			}
			
			if(tempId < 1){
				tempId = dao.getMaxId();				
			}
			if(tempId < 1){
				tempId = 1;				
			}
			CommTableDateId newColumn = new CommTableDateId();
			newColumn.setId(Long.valueOf(String.valueOf(tempId)));
			newColumn.setTableName(tableName);
			newColumn.setColumnName(columnName);
			newColumn.setSeqDate(date);
			newColumn.setIdvalue(Long.valueOf("1"));
			newColumn.setComments("");
			dao.save(newColumn);
			
			temp = dateString + this.getObjectPad(Long.valueOf("1"), maxLen);
			return temp;
			
		} catch(Exception e){
			log.error(e.toString());        	
		}
		return temp;
		
	}	
	
	public String getObjectPad(Object obj, long len){
		String temp = obj.toString();
		while(temp.length() < len){
			temp = "0" + temp;			
		}
		return temp;
	}
	
	
}
