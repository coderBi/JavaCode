/**
 *
 */
package junit.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.ss2h.domain.Person;
import cn.ss2h.service.BaseService;

/**
 * @version 1.0
 * @author Edwin
 * @since 2016-7-22
 */
public class BaseServiceTest {
	static BaseService baseService;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext cxt = new ClassPathXmlApplicationContext("beans.xml");
		baseService = (BaseService) cxt.getBean("baseService");
	}

	/**
	 * Test method for
	 * {@link cn.ss2h.service.impl.BaseServiceImpl#fetchAll(java.lang.String, java.lang.Object[])}
	 * .
	 */
	@Test
	public void testFetchAll() {
		@SuppressWarnings("unchecked")
		List<Person> persons = (List<Person>) baseService.fetchAll(
				"from Person", new Object[] {});
		for (Person person : persons) {
			System.out.println(person.getId() + "  " + person.getName());
		}
	}

	/**
	 * Test method for
	 * {@link cn.ss2h.service.impl.BaseServiceImpl#fetchRow(java.lang.String, java.lang.Object[])}
	 * .
	 */
	@Test
	public void testFetchRow() {
		Person person = (Person) baseService.fetchRow("from Person",
				new Object[] {});
		System.out.println(person.getId());
	}

	/**
	 * Test method for
	 * {@link cn.ss2h.service.impl.BaseServiceImpl#fetchOne(java.lang.String, java.lang.Object[])}
	 * .
	 */
	@Test
	public void testFetchOne() {
//		Integer id = ((Number) baseService.fetchOne(
//				"select p.id from Person as p", new Object[] {})).intValue();
//		System.out.println(id);
		Long count = (Long) baseService.fetchOne(
				"select count(*) from Person as p where p.name=?", new Object[] {"呵呵"});
		System.out.println(count);
	}

	/**
	 * Test method for
	 * {@link cn.ss2h.service.impl.BaseServiceImpl#execute(java.lang.String, java.lang.Object[])}
	 * .
	 */
	@Test
	public void testExecute() {
		String hql = "update Person as p set p.name='new name'";
		baseService.execute(hql, new Object[] {});
	}
}
