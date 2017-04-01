/**
 *
 */
package chartroom.dao;

import chartroom.vo.User;

/**
 * <p>Title:UserDao</p>
 * <p>Description:用户Dao接口</p>
 * @author Edwin
 * @since 2017-3-4
 * @version 1.0
 */
public interface UserDao {
	//处理用户登录
	public User login(User user) throws Exception;
	
	//通过用户id查询用户
	public User selectById(Integer id) throws Exception;
}
