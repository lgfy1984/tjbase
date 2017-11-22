package com.tianjian.comm.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianjian.hsp.bean.BaseMessage;
import com.tianjian.comm.dao.ICommTypeQueryDAO;

public class CommTypeQueryDAO extends HibernateDaoSupport implements ICommTypeQueryDAO {

	private static final Logger log = LogManager.getLogger(CommTypeQueryDAO.class);

	public List<?> queryByCondition1(String tableName,BaseMessage baseMessage){
	 	String sql = " select a.id,a.seqNo,a.classCode,a.dictCode,a.dictValue,a.inputCode,a.comments from "+ tableName  +" a where 1=1  ";
	 	String orderBy = " order by ";
		String sortNumber = baseMessage.getSequenceNumber();
		if("1".equals(sortNumber)){
			orderBy += " a.id ";
		}else if("2".equals(sortNumber)){
			orderBy += " a.seqNo ";
		}else if("3".equals(sortNumber)){
			orderBy += " a.classCode ";
		}else if("4".equals(sortNumber)){
			orderBy += " a.dictCode ";
		}else if("5".equals(sortNumber)){
			orderBy += " a.dictValue ";
		}else if("6".equals(sortNumber)){
			orderBy += " a.inputCode ";
		}else if("7".equals(sortNumber)){
			orderBy += " a.comments ";
		}else{
			orderBy += " a.id ";
		}
		if("1".equals(baseMessage.getDirectionCode())){
			orderBy += " desc";
		}else{
			orderBy += " asc";
		}	
	 		
	 	Query q = getSession().createQuery(sql+orderBy);
		if(baseMessage.getInitialQuantity()!=null&&baseMessage.getInitialQuantity().length()>0){
			q.setFirstResult(Integer.valueOf(baseMessage.getInitialQuantity())); 
		}
		if(baseMessage.getPageSize()!=null&&baseMessage.getPageSize().length()>0){
			q.setMaxResults(Integer.valueOf(baseMessage.getPageSize()));
		}
		List<?> l=q.list();
			
		return l;
	 }
	 	
	public List<?> queryByCondition2(String tableName,BaseMessage baseMessage){
	 	String sql = " select a.tableCode,a.seqInLevel,a.parentItemCode,a.itemName,a.itemCode,a.inputCode,a.comments,a.classLevel from "+ tableName  +" a where 1=1  ";
	 	String orderBy = " order by ";
		String sortNumber = baseMessage.getSequenceNumber();
		if("1".equals(sortNumber)){
			orderBy += " a.tableCode ";
		}else if("2".equals(sortNumber)){
			orderBy += " a.seqInLevel ";
		}else if("3".equals(sortNumber)){
			orderBy += " a.parentItemCode ";
		}else if("4".equals(sortNumber)){
			orderBy += " a.itemName ";
		}else if("5".equals(sortNumber)){
			orderBy += " a.itemCode ";
		}else if("6".equals(sortNumber)){
			orderBy += " a.inputCode ";
		}else if("7".equals(sortNumber)){
			orderBy += " a.comments ";
		}else if("8".equals(sortNumber)){
			orderBy += " a.classLevel ";
		}else{
			orderBy += " a.tableCode ";
		}
		if("1".equals(baseMessage.getDirectionCode())){
			orderBy += " desc";
		}else{
			orderBy += " asc";
		}
	 		
	 	Query q = getSession().createQuery(sql+orderBy);
		if(baseMessage.getInitialQuantity()!=null&&baseMessage.getInitialQuantity().length()>0){
			q.setFirstResult(Integer.valueOf(baseMessage.getInitialQuantity())); 
		}
		if(baseMessage.getPageSize()!=null&&baseMessage.getPageSize().length()>0){
			q.setMaxResults(Integer.valueOf(baseMessage.getPageSize()));
		}
		List<?> l=q.list();
			
		return l;
	 }

	
	public List<?> queryByCondition3(String tableName,BaseMessage baseMessage){
	 	String sql = " select a.itemCode,a.itemName,a.inputCode,a.inputCodeWb,a.inputCode1,a.inputCode2,a.comments from "+ tableName  +" a where 1=1  ";
	 	String orderBy = " order by ";
		String sortNumber = baseMessage.getSequenceNumber();
		if("1".equals(sortNumber)){
			orderBy += " a.itemCode ";
		}else if("2".equals(sortNumber)){
			orderBy += " a.itemName ";
		}else if("3".equals(sortNumber)){
			orderBy += " a.inputCode ";
		}else if("4".equals(sortNumber)){
			orderBy += " a.inputCodeWb ";
		}else if("5".equals(sortNumber)){
			orderBy += " a.inputCode1 ";
		}else if("6".equals(sortNumber)){
			orderBy += " a.inputCode2 ";
		}else if("7".equals(sortNumber)){
			orderBy += " a.comments ";
		}else{
			orderBy += " a.itemCode ";
		}
		if("1".equals(baseMessage.getDirectionCode())){
			orderBy += " desc";
		}else{
			orderBy += " asc";
		}
	 	
	 	Query q = getSession().createQuery(sql+orderBy);
		if(baseMessage.getInitialQuantity()!=null&&baseMessage.getInitialQuantity().length()>0){
			q.setFirstResult(Integer.valueOf(baseMessage.getInitialQuantity())); 
		}
		if(baseMessage.getPageSize()!=null&&baseMessage.getPageSize().length()>0){
			q.setMaxResults(Integer.valueOf(baseMessage.getPageSize()));
		}
		List<?> l=q.list();
			
		return l;
	 }
	
	public List<?> queryByCondition4(String tableName,BaseMessage baseMessage){
	 	String sql = " select a.seqNo,a.itemCode,a.itemName,a.inputCode,a.comments from "+ tableName  +" a where 1=1  ";
	 	String orderBy = " order by ";
		String sortNumber = baseMessage.getSequenceNumber();
		if("1".equals(sortNumber)){
			orderBy += " a.seqNo ";
		}else if("2".equals(sortNumber)){
			orderBy += " a.itemCode ";
		}else if("3".equals(sortNumber)){
			orderBy += " a.itemName ";
		}else if("4".equals(sortNumber)){
			orderBy += " a.inputCode ";
		}else if("5".equals(sortNumber)){
			orderBy += " a.comments ";
		}else{
			orderBy += " a.seqNo ";
		}
		if("1".equals(baseMessage.getDirectionCode())){
			orderBy += " desc";
		}else{
			orderBy += " asc";
		}	
	 		
	 	Query q = getSession().createQuery(sql+orderBy);
		if(baseMessage.getInitialQuantity()!=null&&baseMessage.getInitialQuantity().length()>0){
			q.setFirstResult(Integer.valueOf(baseMessage.getInitialQuantity())); 
		}
		if(baseMessage.getPageSize()!=null&&baseMessage.getPageSize().length()>0){
			q.setMaxResults(Integer.valueOf(baseMessage.getPageSize()));
		}
		List<?> l=q.list();
			
		return l;
	 }
	
	public List<?> queryByCondition5(String tableName,BaseMessage baseMessage){
	 	String sql = " select a.id,a.itemCode,a.itemName,a.inputCode,a.levelFlag,a.parentId,a.comments,a.seqNo from "+ tableName  +" a where 1=1  ";
	 	String orderBy = " order by ";
		String sortNumber = baseMessage.getSequenceNumber();
		if("1".equals(sortNumber)){
			orderBy += " a.id ";
		}else if("2".equals(sortNumber)){
			orderBy += " a.itemCode ";
		}else if("3".equals(sortNumber)){
			orderBy += " a.itemName ";
		}else if("4".equals(sortNumber)){
			orderBy += " a.inputCode ";
		}else if("5".equals(sortNumber)){
			orderBy += " a.levelFlag ";
		}else if("6".equals(sortNumber)){
			orderBy += " a.parentId ";
		}else if("7".equals(sortNumber)){
			orderBy += " a.comments ";
		}else if("8".equals(sortNumber)){
			orderBy += " a.seqNo ";
		}else{
			orderBy += " a.id ";
		}
		if("1".equals(baseMessage.getDirectionCode())){
			orderBy += " desc";
		}else{
			orderBy += " asc";
		}	
	 		
	 	Query q = getSession().createQuery(sql+orderBy);
		if(baseMessage.getInitialQuantity()!=null&&baseMessage.getInitialQuantity().length()>0){
			q.setFirstResult(Integer.valueOf(baseMessage.getInitialQuantity())); 
		}
		if(baseMessage.getPageSize()!=null&&baseMessage.getPageSize().length()>0){
			q.setMaxResults(Integer.valueOf(baseMessage.getPageSize()));
		}
		List<?> l=q.list();
			
		return l;
	 }
	
	public List<?> queryByCondition6(String tableName,BaseMessage baseMessage){
	 	String sql = " select a.seqNo,a.itemCode,a.itemName,a.inputCode,a.stopFlag,a.displayFlag,a.comments from "+ tableName  +" a where 1=1  ";
	 	String orderBy = " order by ";
		String sortNumber = baseMessage.getSequenceNumber();
		if("1".equals(sortNumber)){
			orderBy += " a.seqNo ";
		}else if("2".equals(sortNumber)){
			orderBy += " a.itemCode ";
		}else if("3".equals(sortNumber)){
			orderBy += " a.itemName ";
		}else if("4".equals(sortNumber)){
			orderBy += " a.inputCode ";
		}else if("5".equals(sortNumber)){
			orderBy += " a.stopFlag ";
		}else if("6".equals(sortNumber)){
			orderBy += " a.displayFlag ";
		}else if("7".equals(sortNumber)){
			orderBy += " a.comments ";
		}else{
			orderBy += " a.seqNo ";
		}
		if("1".equals(baseMessage.getDirectionCode())){
			orderBy += " desc";
		}else{
			orderBy += " asc";
		}
	 		
	 	Query q = getSession().createQuery(sql+orderBy);
		if(baseMessage.getInitialQuantity()!=null&&baseMessage.getInitialQuantity().length()>0){
			q.setFirstResult(Integer.valueOf(baseMessage.getInitialQuantity())); 
		}
		if(baseMessage.getPageSize()!=null&&baseMessage.getPageSize().length()>0){
			q.setMaxResults(Integer.valueOf(baseMessage.getPageSize()));
		}
		List<?> l=q.list();
			
		return l;
	 }
	
	public List<?> queryByCondition7(String tableName,BaseMessage baseMessage){
	 	String sql = " select a.itemCode,a.itemName,a.inputCode,a.levelFlag,a.parentItemCode,a.comments,a.seqNo from "+ tableName  +" a where 1=1  ";
	 	String orderBy = " order by ";
		String sortNumber = baseMessage.getSequenceNumber();
		if("1".equals(sortNumber)){
			orderBy += " a.itemCode ";
		}else if("2".equals(sortNumber)){
			orderBy += " a.itemName ";
		}else if("3".equals(sortNumber)){
			orderBy += " a.inputCode ";
		}else if("4".equals(sortNumber)){
			orderBy += " a.levelFlag ";
		}else if("5".equals(sortNumber)){
			orderBy += " a.parentItemCode ";
		}else if("6".equals(sortNumber)){
			orderBy += " a.comments ";
		}else if("7".equals(sortNumber)){
			orderBy += " a.seqNo ";
		}else{
			orderBy += " a.itemCode ";
		}
		if("1".equals(baseMessage.getDirectionCode())){
			orderBy += " desc";
		}else{
			orderBy += " asc";
		}	
	 		
	 	Query q = getSession().createQuery(sql+orderBy);
		if(baseMessage.getInitialQuantity()!=null&&baseMessage.getInitialQuantity().length()>0){
			q.setFirstResult(Integer.valueOf(baseMessage.getInitialQuantity())); 
		}
		if(baseMessage.getPageSize()!=null&&baseMessage.getPageSize().length()>0){
			q.setMaxResults(Integer.valueOf(baseMessage.getPageSize()));
		}
		List<?> l=q.list();
			
		return l;
	 }
	
	public List<?> queryByCondition8(String tableName,BaseMessage baseMessage){
	 	String sql = " select a.tableCode,a.seqDate,a.idValue,a.id,a.comments,a.columnName from "+ tableName  +" a where 1=1  ";
	 	String orderBy = " order by ";
		String sortNumber = baseMessage.getSequenceNumber();
		if("1".equals(sortNumber)){
			orderBy += " a.tableCode ";
		}else if("2".equals(sortNumber)){
			orderBy += " a.seqDate ";
		}else if("3".equals(sortNumber)){
			orderBy += " a.idValue ";
		}else if("4".equals(sortNumber)){
			orderBy += " a.id ";
		}else if("5".equals(sortNumber)){
			orderBy += " a.comments ";
		}else if("6".equals(sortNumber)){
			orderBy += " a.columnName ";
		}else{
			orderBy += " a.tableCode ";
		}
		if("1".equals(baseMessage.getDirectionCode())){
			orderBy += " desc";
		}else{
			orderBy += " asc";
		}
	 		
	 	Query q = getSession().createQuery(sql+orderBy);
		if(baseMessage.getInitialQuantity()!=null&&baseMessage.getInitialQuantity().length()>0){
			q.setFirstResult(Integer.valueOf(baseMessage.getInitialQuantity())); 
		}
		if(baseMessage.getPageSize()!=null&&baseMessage.getPageSize().length()>0){
			q.setMaxResults(Integer.valueOf(baseMessage.getPageSize()));
		}
		List<?> l=q.list();
			
		return l;
	 }
	
	public List<?> queryByCondition9(String tableName,BaseMessage baseMessage){
	 	String sql = " select a.id,a.itemCode,a.itemName,a.inputCode,a.commConfigLocationId,a.comments,a.seqNo from "+ tableName  +" a where 1=1  ";
	 	String orderBy = " order by ";
		String sortNumber = baseMessage.getSequenceNumber();
		if("1".equals(sortNumber)){
			orderBy += " a.id ";
		}else if("2".equals(sortNumber)){
			orderBy += " a.itemCode ";
		}else if("3".equals(sortNumber)){
			orderBy += " a.itemName ";
		}else if("4".equals(sortNumber)){
			orderBy += " a.inputCode ";
		}else if("5".equals(sortNumber)){
			orderBy += " a.commConfigLocationId ";
		}else if("6".equals(sortNumber)){
			orderBy += " a.comments ";
		}else if("6".equals(sortNumber)){
			orderBy += " a.seqNo ";
		}else{
			orderBy += " a.id ";
		}
		if("1".equals(baseMessage.getDirectionCode())){
			orderBy += " desc";
		}else{
			orderBy += " asc";
		}	
	 		
	 	Query q = getSession().createQuery(sql+orderBy);
		if(baseMessage.getInitialQuantity()!=null&&baseMessage.getInitialQuantity().length()>0){
			q.setFirstResult(Integer.valueOf(baseMessage.getInitialQuantity())); 
		}
		if(baseMessage.getPageSize()!=null&&baseMessage.getPageSize().length()>0){
			q.setMaxResults(Integer.valueOf(baseMessage.getPageSize()));
		}
		List<?> l=q.list();
			
		return l;
	 }
	
	public List<?> queryByCondition10(String tableName,BaseMessage baseMessage){
	 	String sql = " select a.id,a.itemCode,a.itemName,a.inputCode,a.commCltId,a.villagerNum,a.familyNum,a.contactPersonName,a.phone,a.comments,a.seqNo from "+ tableName  +" a where 1=1  ";
	 	String orderBy = " order by ";
		String sortNumber = baseMessage.getSequenceNumber();
		if("1".equals(sortNumber)){
			orderBy += " a.id ";
		}else if("2".equals(sortNumber)){
			orderBy += " a.itemCode ";
		}else if("3".equals(sortNumber)){
			orderBy += " a.itemName ";
		}else if("4".equals(sortNumber)){
			orderBy += " a.inputCode ";
		}else if("5".equals(sortNumber)){
			orderBy += " a.commCltId ";
		}else if("6".equals(sortNumber)){
			orderBy += " a.villagerNum ";
		}else if("7".equals(sortNumber)){
			orderBy += " a.familyNum ";
		}else if("8".equals(sortNumber)){
			orderBy += " a.contactPersonName ";
		}else if("9".equals(sortNumber)){
			orderBy += " a.phone ";
		}else if("10".equals(sortNumber)){
			orderBy += " a.comments ";
		}else if("11".equals(sortNumber)){
			orderBy += " a.seqNo ";
		}else{
			orderBy += " a.id ";
		}
		if("1".equals(baseMessage.getDirectionCode())){
			orderBy += " desc";
		}else{
			orderBy += " asc";
		}
	 		
	 	Query q = getSession().createQuery(sql+orderBy);
		if(baseMessage.getInitialQuantity()!=null&&baseMessage.getInitialQuantity().length()>0){
			q.setFirstResult(Integer.valueOf(baseMessage.getInitialQuantity())); 
		}
		if(baseMessage.getPageSize()!=null&&baseMessage.getPageSize().length()>0){
			q.setMaxResults(Integer.valueOf(baseMessage.getPageSize()));
		}
		List<?> l=q.list();
			
		return l;
	 }
	
	public List<?> queryByCondition11(String tableName,BaseMessage baseMessage){
	 	String sql = " select a.id,a.itemCode,a.itemName,a.inputCode,a.commClvId,a.comments,a.seqNo from "+ tableName  +" a where 1=1  ";
	 	String orderBy = " order by ";
		String sortNumber = baseMessage.getSequenceNumber();
		if("1".equals(sortNumber)){
			orderBy += " a.id ";
		}else if("2".equals(sortNumber)){
			orderBy += " a.itemCode ";
		}else if("3".equals(sortNumber)){
			orderBy += " a.itemName ";
		}else if("4".equals(sortNumber)){
			orderBy += " a.inputCode ";
		}else if("5".equals(sortNumber)){
			orderBy += " a.commClvId ";
		}else if("6".equals(sortNumber)){
			orderBy += " a.comments ";
		}else if("7".equals(sortNumber)){
			orderBy += " a.seqNo ";
		}else{
			orderBy += " a.id ";
		}
		if("1".equals(baseMessage.getDirectionCode())){
			orderBy += " desc";
		}else{
			orderBy += " asc";
		}	
	 		
	 	Query q = getSession().createQuery(sql+orderBy);
		if(baseMessage.getInitialQuantity()!=null&&baseMessage.getInitialQuantity().length()>0){
			q.setFirstResult(Integer.valueOf(baseMessage.getInitialQuantity())); 
		}
		if(baseMessage.getPageSize()!=null&&baseMessage.getPageSize().length()>0){
			q.setMaxResults(Integer.valueOf(baseMessage.getPageSize()));
		}
		List<?> l=q.list();
			
		return l;
	 }
	
	public List<?> queryByCondition12(String tableName,BaseMessage baseMessage){
	 	String sql = " select a.id,a.seqNo,a.classCode,a.dictCode,a.dictValue,a.inputCode,a.comments from "+ tableName  +" a where 1=1  ";
	 	String orderBy = " order by ";
		String sortNumber = baseMessage.getSequenceNumber();
		if("1".equals(sortNumber)){
			orderBy += " a.id ";
		}else if("2".equals(sortNumber)){
			orderBy += " a.seqNo ";
		}else if("3".equals(sortNumber)){
			orderBy += " a.classCode ";
		}else if("4".equals(sortNumber)){
			orderBy += " a.dictCode ";
		}else if("5".equals(sortNumber)){
			orderBy += " a.dictValue ";
		}else if("6".equals(sortNumber)){
			orderBy += " a.inputCode ";
		}else if("7".equals(sortNumber)){
			orderBy += " a.comments ";
		}else{
			orderBy += " a.id ";
		}
		if("1".equals(baseMessage.getDirectionCode())){
			orderBy += " desc";
		}else{
			orderBy += " asc";
		}		
	 		
	 	Query q = getSession().createQuery(sql+orderBy);
		if(baseMessage.getInitialQuantity()!=null&&baseMessage.getInitialQuantity().length()>0){
			q.setFirstResult(Integer.valueOf(baseMessage.getInitialQuantity())); 
		}
		if(baseMessage.getPageSize()!=null&&baseMessage.getPageSize().length()>0){
			q.setMaxResults(Integer.valueOf(baseMessage.getPageSize()));
		}
		List<?> l=q.list();
			
		return l;
	 }
	
	public List<?> queryByCondition13(String tableName,BaseMessage baseMessage){
	 	String sql = " select a.classCode,a.className,a.inputCode,a.comments,a.seqNo from "+ tableName  +" a where 1=1  ";
	 	String orderBy = " order by ";
		String sortNumber = baseMessage.getSequenceNumber();
		if("1".equals(sortNumber)){
			orderBy += " a.classCode ";
		}else if("2".equals(sortNumber)){
			orderBy += " a.className ";
		}else if("3".equals(sortNumber)){
			orderBy += " a.inputCode ";
		}else if("4".equals(sortNumber)){
			orderBy += " a.comments ";
		}else if("5".equals(sortNumber)){
			orderBy += " a.seqNo ";
		}else{
			orderBy += " a.classCode ";
		}
		if("1".equals(baseMessage.getDirectionCode())){
			orderBy += " desc";
		}else{
			orderBy += " asc";
		}		
	 		
	 	Query q = getSession().createQuery(sql+orderBy);
		if(baseMessage.getInitialQuantity()!=null&&baseMessage.getInitialQuantity().length()>0){
			q.setFirstResult(Integer.valueOf(baseMessage.getInitialQuantity())); 
		}
		if(baseMessage.getPageSize()!=null&&baseMessage.getPageSize().length()>0){
			q.setMaxResults(Integer.valueOf(baseMessage.getPageSize()));
		}
		List<?> l=q.list();
			
		return l;
	 }
	
	public List<?> queryByCondition14(String tableName,BaseMessage baseMessage){
	 	String sql = " select a.itemCode,a.itemName,a.inputCode,a.levelFlag,a.parentId,a.comments,a.seqNo from "+ tableName  +" a where 1=1  ";
	 	String orderBy = " order by ";
		String sortNumber = baseMessage.getSequenceNumber();
		if("1".equals(sortNumber)){
			orderBy += " a.itemCode ";
		}else if("2".equals(sortNumber)){
			orderBy += " a.itemName ";
		}else if("3".equals(sortNumber)){
			orderBy += " a.inputCode ";
		}else if("4".equals(sortNumber)){
			orderBy += " a.levelFlag ";
		}else if("5".equals(sortNumber)){
			orderBy += " a.parentId ";
		}else if("6".equals(sortNumber)){
			orderBy += " a.comments ";
		}else if("7".equals(sortNumber)){
			orderBy += " a.seqNo ";
		}else{
			orderBy += " a.itemCode ";
		}
		if("1".equals(baseMessage.getDirectionCode())){
			orderBy += " desc";
		}else{
			orderBy += " asc";
		}	
	 		
	 	Query q = getSession().createQuery(sql+orderBy);
		if(baseMessage.getInitialQuantity()!=null&&baseMessage.getInitialQuantity().length()>0){
			q.setFirstResult(Integer.valueOf(baseMessage.getInitialQuantity())); 
		}
		if(baseMessage.getPageSize()!=null&&baseMessage.getPageSize().length()>0){
			q.setMaxResults(Integer.valueOf(baseMessage.getPageSize()));
		}
		List<?> l=q.list();
			
		return l;
	 }

}