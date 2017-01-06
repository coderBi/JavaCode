/**
 *
 */
package cn.ss2h.service;

import java.util.List;

import cn.ss2h.domain.Gender;

/**
 * @version 1.0
 * @author Edwin
 * @since 2016-7-21
 */
public interface GenderService extends BaseService{
	/**
	 * 向数据库添加一个gender
	 * @param gender  待添加的gender对象
	 */
	void add(Gender gender);
	
	/**
	 * 更新一条数据库记录
	 * @param gender  待更新的gender对象
	 */
	void update(Gender gender);
	
	/**
	 * 删除1个或者n个数据库记录
	 * @param genders  要删除的gender对象列表
	 */
	void delete(Gender... genders);
	
	/**
	 * 获取一条gender表记录
	 * @param id 要获取的gender的id
	 * @return 获取到的gender对象
	 */
	Gender getGender(Integer id);
	
	/**
	 * 得到一系列的gender对象
	 * @param ids 要获取的gender对象的id列表
	 * @return 获取到的gender对象的List
	 */
	List<Gender> getGenders(Integer... ids);
	
	/**
	 * 获取gender表的所有记录
	 * @return  获取到的gender对象构成的list
	 */
	List<Gender> getAllGenders();
}
