/**
 *
 */
package sm.dao;

import sm.po.User;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * <p>
 * Title:UserDaoImpl
 * </p>
 * <p>
 * Description:介绍
 * </p>
 * 
 * @author Edwin
 * @since 2016-7-30
 * @version 1.0
 */
public class UserDaoImpl extends BaseDaoOperation implements UserDao {

	/**
	 * 构造，需要传入一个SqlSessionFactory
	 * 
	 * @param sqlSessionFactory
	 */
	public UserDaoImpl(){}
	public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
		super(sqlSessionFactory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mybatis.dao.UserDao#findUserById(java.lang.Integer)
	 */
	@Override
	public User findUserById(Integer id) throws Exception {
		return (User) defaultHasReturnOperation(new HasReturnMapper() {

			@Override
			public Object sqlOperation(SqlSession sqlSession, String statement,
					Object arg) throws Exception {
				return sqlSession.selectOne(statement, arg);
			}
		}, "test.selectById", id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mybatis.dao.UserDao#insertUser(mybatis.dao.UserDao)
	 */
	@Override
	public void insertUser(User user) throws Exception {
		defaultNoReturnOperation(new NoReturnMapper() {

			@Override
			public void sqlOperation(SqlSession sqlSession, String statement,
					Object arg) throws Exception {
				sqlSession.insert(statement, arg);
				sqlSession.commit();
			}
		}, "test.insertUser", user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mybatis.dao.UserDao#deleteUser(java.lang.Integer)
	 */
	@Override
	public void deleteUser(Integer id) throws Exception {
		defaultNoReturnOperation(new NoReturnMapper() {

			@Override
			public void sqlOperation(SqlSession sqlSession, String statement,
					Object arg) throws Exception {
				sqlSession.delete(statement, arg);
				sqlSession.commit();
			}
		}, "test.deleteById", id);
	}

}
