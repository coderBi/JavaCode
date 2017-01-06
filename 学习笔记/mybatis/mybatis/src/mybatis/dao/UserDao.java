/**
 *
 */
package mybatis.dao;

import mybatis.pojo.Person;

/**
 * <p>Title:UserDao</p>
 * <p>Description:dao接口，用户管理</p>
 * @author Edwin
 * @since 2016-7-30
 * @version 1.0
 */
public interface UserDao {
	//根据id查询用户
	public Person findUserById(Integer id) throws Exception;
	
	//添加用户
	public void insertUser(Person user) throws Exception;
	
	//删除用户
	public void deleteUser(Integer id) throws Exception;
}
