/**
 *
 */
package practice.mapper;

import java.util.List;

import practice.dao.po.User;

/**
 * <p>Title:UserMapper</p>
 * <p>Description:</p>
 * @author Edwin
 * @since 2016-8-23
 * @version 1.0
 */
public interface UserMapper {
	List<User> selectAll();
	User selectById(Integer id);
	void updateUser(User user);
}
