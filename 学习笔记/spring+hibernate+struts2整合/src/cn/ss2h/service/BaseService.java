/**
 *
 */
package cn.ss2h.service;

import java.util.List;

/**
 * 基础service接口，里面定义了所有service接口的公共部分，涉及常用的hql操作
 * @version 1.0
 * @author Edwin
 * @since 2016-7-22
 */
public interface BaseService {
	/**
	 * 查询 n 条数据路记录
	 * @param hql  待执行的hql语句
	 * @param args  hql语句中的参数列表
	 * @return
	 */
	List<?> fetchAll(String hql,Object...args);
	
	/**
	 * 查询一行记录
	 * @param hql 待执行的hql语句
	 * @param args hql语句中的参数列表
	 * @return
	 */
	Object fetchRow(String hql,Object...args);
	
	/**
	 * 查询一行中的某一列
	 * @param hql 待执行的hql语句
	 * @param args hql语句中的参数列表
	 * @return
	 */
	Object fetchOne(String hql,Object...args);
	
	/**
	 * 执行一条hql语句
	 * @param hql 待执行的hql语句
	 * @param args hql语句中的参数列表
	 * @return  返回收影响的行数，如果执行失败，返回false
	 */
	Integer execute(String hql,Object...args);
}
