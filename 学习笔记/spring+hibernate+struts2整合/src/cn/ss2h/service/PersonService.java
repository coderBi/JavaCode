/**
 *
 */
package cn.ss2h.service;

import java.util.List;

import cn.ss2h.domain.Person;

/**
 * @version 1.0
 * @author Edwin
 * @since 2016-7-21
 */
public interface PersonService extends BaseService{
	/**
	 * 向数据库添加一个person
	 * @param perosn  待添加的Person对象
	 */
	void add(Person perosn);
	
	/**
	 * 更新一条数据库记录
	 * @param person  待更新的Person对象
	 */
	void update(Person person);
	
	/**
	 * 删除1个或者n个数据库记录
	 * @param persons  要删除的Person对象列表
	 */
	void delete(Person... persons);
	
	/**
	 * 获取一条Person表记录
	 * @param id 要获取的Person的id
	 * @return 获取到的Person对象
	 */
	Person getPerson(Integer id);
	
	/**
	 * 得到一系列的Person对象
	 * @param ids 要获取的Person对象的id列表
	 * @return 获取到的Person对象的List
	 */
	List<Person> getPersons(Integer... ids);
	
	/**
	 * 获取Person表的所有记录
	 * @return  获取到的Person对象构成的list
	 */
	List<Person> getAllPersons();
}
