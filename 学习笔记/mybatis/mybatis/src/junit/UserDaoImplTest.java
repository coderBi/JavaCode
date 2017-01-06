/**
 *
 */
package junit;

import static org.junit.Assert.*;

import java.sql.Date;

import mybatis.dao.UserDao;
import mybatis.dao.UserDaoImpl;
import mybatis.pojo.Person;
import mybatis.tools.MySessionFactory;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * <p>Title:UserDaoImplTest</p>
 * <p>Description:介绍</p>
 * @author Edwin
 * @since 2016-7-30
 * @version 1.0
 */
public class UserDaoImplTest {
	private static SqlSessionFactory sqlSessionFactory;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sqlSessionFactory = MySessionFactory.getSqlSessionFactory();
	}

	/**
	 * Test method for {@link mybatis.dao.UserDaoImpl#findUserById(java.lang.Integer)}.
	 */
	@Test
	public void testFindUserById() {
		try {
			Person person = new UserDaoImpl(sqlSessionFactory).findUserById(2);
			assertFalse(null == person);
			assertEquals(Integer.valueOf(2), person.getId());
			assertEquals("张三", person.getUsername());
			assertEquals(Date.valueOf("2016-07-07"), person.getBirthday());
			assertEquals(Integer.valueOf(1), person.getGender());
			assertEquals("傻X村", person.getAddress());
			assertTrue(null == new UserDaoImpl(sqlSessionFactory).findUserById(100));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link mybatis.dao.UserDaoImpl#insertUser(mybatis.dao.UserDao)}.
	 * @throws Exception 
	 */
	@Test
	public void testInsertUser() throws Exception {
		Person person = new Person("xxx",Date.valueOf("2011-1-1"),3,"魂淡村");
		new UserDaoImpl(sqlSessionFactory).insertUser(person);
		assertEquals(Integer.valueOf(34), person.getId());
	}

	/**
	 * Test method for {@link mybatis.dao.UserDaoImpl#deleteUser(java.lang.Integer)}.
	 * @throws Exception 
	 */
	@Test
	public void testDeleteUser() throws Exception {
		UserDao userDao = new UserDaoImpl(sqlSessionFactory);
		assertFalse(null == userDao.findUserById(34));
		userDao.deleteUser(34);
		assertTrue(null == userDao.findUserById(34));
	}

}
