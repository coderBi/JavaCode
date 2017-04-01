/**
 *
 */
package chartroom.dao;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import chartroom.utils.JDBCUtils;
import chartroom.vo.User;

/**
 * <p>Title:UserDao</p>
 * <p>Description:用户Dao接口</p>
 * @author Edwin
 * @since 2017-3-4
 * @version 1.0
 */
public class UserDaoImpl implements UserDao{

	@Override
	public User login(User user) throws Exception {
		//创建一个QueryRunner  用来执行sql语句
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		
		//要执行的sql
		String sql = "select * from user where username=? and password=?";
		
		User userToReturn = null;
		try{
			userToReturn = queryRunner.query(sql, new BeanHandler<User>(User.class),user.getUsername(),user.getPassword());
		} catch(Exception e) {
			e.printStackTrace();
		}
		return userToReturn;
	}

	/* 
	 * 通过用户id查询用的具体实现
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public User selectById(Integer id) throws Exception {
		// 创建QueryRunner
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		
		//待执行的sql
		String sql = "select * from user where id=?";
		
		//通过queryRunner查询并返回相应id用户
		User user = queryRunner.query(sql, new BeanHandler<User>(User.class),id);
		
		return user;
	}
	
}
