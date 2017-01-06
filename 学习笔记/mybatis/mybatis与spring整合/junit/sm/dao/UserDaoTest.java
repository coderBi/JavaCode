/**
 *
 */
package sm.dao;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sm.po.User;

/**
 * <p>
 * Title:UserDaoTest
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author Edwin
 * @since 2016-8-28
 * @version 1.0
 */
public class UserDaoTest {
	private static ApplicationContext applicationContext;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring/applicationContext.xml");
		
	}

	/**
	 * Test method for {@link sm.dao.UserDao#findUserById(java.lang.Integer)}.
	 * @throws Exception 
	 */
	@Test
	public void testFindUserById() throws Exception {
		UserDao userDao = (UserDao) applicationContext.getBean("userDao");
		User user = userDao.findUserById(1);
		assertTrue(user != null);
		System.out.println(user.getUsername());
	}

	/**
	 * Test method for {@link sm.dao.UserDao#insertUser(sm.po.User)}.
	 */
	@Test
	public void testInsertUser() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link sm.dao.UserDao#deleteUser(java.lang.Integer)}.
	 */
	@Test
	public void testDeleteUser() {
		fail("Not yet implemented");
	}

}
