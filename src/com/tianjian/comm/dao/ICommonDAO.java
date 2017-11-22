package com.tianjian.comm.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface ICommonDAO {

	public static final String OPERATION_EQUALS = "=";
	public static final String OPERATION_LIKE = "like";
	/**
	 * 
	 * @param hql
	 * @param args
	 * @return List&lt;?>/null
	 */
	public List<?> findListByHql(String hql);
	/**
	 * 
	 * @param sql
	 * @return
	 */
	public List<?> findListBySql(String sql);
	/**
	 * 如果args为空或null，则本方法调用结果等同于调用List<?>:findListByHql(String:hql)方法
	 * @param hql
	 * @param args
	 * @return List&lt;?>/null
	 */
	public List<?> findListByHql(String hql, String[] args);
	//从指定table中得到id和name的列表   
	public List<?> getIdNames(String table, String id, String name);
	//从指定table中得到id和name的列表   
	public List<?> getIdNames(String table, String id, String name,String other);

	//从指定table中得到与指定value相等的name列表       
	public String getNameById(String table, String id, String name, String idValue);
	/**
	 * 从nameOfTable表中取出字段nameOfTargetField的第一个值，当nameOfConditionFields[*]字段的值为valueOfConditionFields[*]时，没有找到时返回null<br>
	 * 最终SQL样式：SELECT nameOfTargetField FROM nameOfTable WHERE [AND nameOfConditionFields[*]=valueOfConditionFields[*]]
	 * @param nameOfTable
	 * @param nameOfTargetField
	 * @param nameOfConditionFields
	 * @param valueOfConditionFields
	 * @return String/null
	 */
	public String getValue(String nameOfTable, String nameOfTargetField, String[] nameOfConditionFields, String[] valueOfConditionFields);

	/**
	 * 从nameOfTable表中取出第一条记录，当nameOfConditionFields[*]字段的值为valueOfConditionFields[*]时，没有找到时返回null<br>
	 * 最终SQL样式：FROM nameOfTable WHERE [AND nameOfConditionFields[*]=valueOfConditionFields[*]]
	 * @param nameOfTable
	 * @param nameOfConditionFields
	 * @param valueOfConditionFields
	 * @return Object/null
	 */
	public Object getObject(String nameOfTable, String[] nameOfConditionFields, String[] valueOfConditionFields);

	/**
	 * 从表tableName中取字段filedName系列值中最大值，然后增加一后返回。主要针对数字类型以及能转换为数字类型的字段
	 * @param tableName
	 * @param filedName
	 * @return
	 */
	public String getSequenceNo(String tableName, String filedName);

	public String getSequenceNo(String tableName, String filedName, String[] fieldCondition, String[] fieldConditionValue);

	public List<?> getIdNamesNew(String table, String id, String name, String order);

	public void delete(Object data);

	public void save(Object data);

	public void saveOrUpdate(Object data);

	public void update(Object data);

	public List<?> findListInTable(String nameOfTable, String nameOfProp, String valueOfProp);
	public List<?> findListInTable(String nameOfTable, String nameOfProp, String valueOfProp, String nameOfOrder, String descOrAsc);

	public List<?> findIdNameInTable(String nameOfTable, String nameOfId, String nameOfName);

	public Object findById(String nameOfTable, String nameOfId, String valueOfId);

	public List<?> findIdNameInChar(String classCode);

	/**
	 * 从nameOfTable表中查询nameOfFields的值等于valueOfFields的OR查询记录集<br>
	 * 注意：nameOfFields和valueOfFields的长度一定要对等，且nameOfFields要存在于nameOfTable表中，否则抛出异常
	 * @param nameOfTable
	 * @param nameOfFields
	 * @param valueOfFields
	 * @return List&lt;?&gt;/null
	 */
	public List<?> findListInTable(String nameOfTable, String[] nameOfFields, String[] valueOfFields);

	/**
	 * 从nameOfTable表中查询条件nameOfFields和valueOfFields之间的operators运算的AND查询记录集数目<br>
	 * 本方法查询条件属于非必须类查询，即如果valueOfFields[*]为空串或为null则该查询条件不计入查询，否则将作为查询条件使用<br>
	 * 注意：nameOfFields、operators以及valueOfFields的长度一定要对等，且nameOfFields要存在于nameOfTable表中，且operators要在本类中OPERATION_*常量中选择，否则抛出异常
	 * @param nameOfTable
	 * @param nameOfFields
	 * @param operators
	 * @param valueOfFields
	 * @return int(>=0)
	 */
	public int getCount(String nameOfTable, String[] nameOfFields, String[] operators, String[] valueOfFields);

	/**
	 * 从nameOfTable表中查询条件nameOfFields和valueOfFields之间的operators运算的分页AND查询记录集，本方法得出的是HIBERNATE的POJO类对象集<br>
	 * 本方法查询条件属于非必须类查询，即如果valueOfFields[*]为空串或为null则该查询条件不计入查询，否则将作为查询条件使用<br>
	 * 分页：该页包含从数据库记录的第startIndex条记录开始的pageSize条记录<br>
	 * 注意：nameOfFields、operators以及valueOfFields的长度一定要对等，且nameOfFields要存在于nameOfTable表中，且operators要在本类中OPERATION_*常量中选择，否则抛出异常
	 * @param nameOfTable
	 * @param nameOfFields
	 * @param operators
	 * @param valueOfFields
	 * @param order
	 * @param startIndex
	 * @param pageSize
	 * @return List&lt;?&gt;/null
	 */
	public List<?> getObjectData(String nameOfTable, String[] nameOfFields, String[] operators, String[] valueOfFields, String order, int startIndex, int pageSize);


	/**
	 * 从nameOfTable表中提取所有数据后封装到List集合中予以返回
	 * @param nameOfTable
	 * @return List&lt;?&gt;/null
	 */
	public List<?> findAllInTable(String nameOfTable);


	/**
	 * 从nameOfTable表中按照顺序排序后提取所有数据后封装到List集合中予以返回
	 * @param nameOfTable
	 * @param nameOfOrderFields
	 * @param order
	 * @return List&lt;?&gt;/null
	 */
	public List<?> findAllInTable(String nameOfTable, String[] nameOfOrderFields, String[] order);

	/**
	 * 批量删除，list内封装的是HIBERNATE映射类对象
	 * @param list
	 */
	public void deleteBatch(List<?> list);

	/**
	 * 将nameOfTable中的nameOfId和nameOfName字段的内容取出来依次放入idList和nameList框架中
	 * @param nameOfTable
	 * @param nameOfId
	 * @param nameOfName
	 * @param idList
	 * @param nameList
	 * @param mode all:表示添加“---全部---”选项，blank:表示添加空白，null:表示不添加
	 */
	public void findIdNameList(String nameOfTable, String nameOfId, String nameOfName, List<String> idList, List<String> nameList, String mode, HttpServletRequest request);

	/**
	 * 将nameOfTable中nameOfFields[*]字段值为valueOfFields[*]的nameOfId和nameOfName字段的内容取出来依次放入idList和nameList框架中
	 * @param nameOfTable
	 * @param nameOfId
	 * @param nameOfName
	 * @param nameOfFields
	 * @param valueOfFields
	 * @param idList
	 * @param nameList
	 * @param mode all:表示添加“---全部---”选项，blank:表示添加空白，null:表示不添加
	 */
	public void findIdNameList(String nameOfTable, String nameOfId, String nameOfName, String[] nameOfFields, String[] valueOfFields, List<String> idList, List<String> nameList, String mode, HttpServletRequest request);
	/**
	 * 根据HQL语句查找分页数据
	 * @param hql
	 * @param startIndex
	 * @param pageSize
	 * @return List/null
	 */
	public List<?> findPageListByHql(String hql, int startIndex, int pageSize);
	
	public List<?> findPageListBySql(String sql,int start,int max);
	public int findPageCountByHql(String hql);
	public Object findObjectByHql(String hql);
	/**
	 * 适用于根据ID或者代码取名称等情况 只适用于字符型  SQL语句非HQL
	 * */
	public String getValueByAnotherSQL(String table,String inColumn,String inColumnValue,String outColumn);
}