/**
 *
 */
package cn.itcast.jdbc;

import static org.junit.Assert.*;

import java.util.List;

import javax.sql.DataSource;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @version 1.0
 * @author Edwin
 * @since 2016-7-19
 */
public class TestJdbcInSpring {
	private static ApplicationContext cxt = null;

	@BeforeClass
	public static void setUpBefore() {
		cxt = new ClassPathXmlApplicationContext("beans.xml");
	}

	@Test
	public void testAdd() {
		// /PersonBean person = new PersonBean();交给spring管理之后，就不能这样new了
		PersonBean person = (PersonBean) cxt.getBean("personBean");
		person.add("傻X");
	}

	@Test
	public void testGetDataSource() {
		DataSource dataSource = (DataSource) cxt.getBean("dataSource");
		assertNotNull(dataSource);
	}

	@Test
	public void testGetById() {
		PersonBean person = (PersonBean) cxt.getBean("personBean");
		assertEquals(null, person.getById(100));
		assertNotNull(person.getById(1));
	}

	@Test
	public void testGetAll() {
		PersonBean person = (PersonBean) cxt.getBean("personBean");
		List<PersonBean> persons = person.getAll();
		for (PersonBean pb : persons) {
			System.out.println("id:" + pb.getId() + " name:" + pb.getName());
		}
	}

	@Test
	public void testUpdate() {
		PersonBean person = (PersonBean) cxt.getBean("personBean");
		PersonBean personGet = person.getById(4); // 这样得到的对面里面没有jdbcTemplate，所以只能用person对象操作
		assertFalse("new Name".equals(personGet.getName()));
		personGet.setName("new Name");
		person.update(personGet);
		assertNotNull(person.getById(4).getName());
		assertTrue("new Name".equals(personGet.getName()));
	}

	@Test
	public void testDelete() {
		PersonBean person = (PersonBean) cxt.getBean("personBean");
		PersonBean personGet = person.getById(4);
		assertNotNull(personGet);
		person.delete(personGet);
		assertNull(person.getById(4));
	}
}
