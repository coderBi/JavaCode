/**
 *
 */
package chartroom.service;

import chartroom.dao.UserDao;
import chartroom.dao.UserDaoImpl;
import chartroom.vo.User;

/**
 * <p>Title:UserService</p>
 * <p>Description:用户service</p>
 * @author Edwin
 * @since 2017-3-4
 * @version 1.0
 */
public class UserService {
	//处理用户登录
	public User login(User user) throws Exception{
		UserDao userDao = new UserDaoImpl();
		return userDao.login(user);
	}
	
	//通过id查询用户
	public User selectById(Integer id) throws Exception{
		UserDao userDao = new UserDaoImpl();
		return userDao.selectById(id);
	}
}
