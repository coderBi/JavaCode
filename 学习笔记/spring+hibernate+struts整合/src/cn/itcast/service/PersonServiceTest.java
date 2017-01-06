/**
 *
 */
package cn.itcast.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import cn.itcast.bean.Person;

/**
 * @version 1.0
 * @author Edwin
 * @since 2016-7-19
 */
public class PersonServiceTest {
	private static PersonService personService;
	private static ApplicationContext cxt;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			cxt = new ClassPathXmlApplicationContext("beans.xml");
			personService = (PersonService) cxt.getBean("personService");
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for
	 * {@link cn.itcast.service.PersonService#save(cn.itcast.bean.Person)}.
	 */
	@Test
	public void testSave() {
		personService.save(new Person("傻X"));
	}

	/**
	 * Test method for
	 * {@link cn.itcast.service.PersonService#update(cn.itcast.bean.Person)}.
	 */
	@Test
	public void testUpdate() {
		Person person = personService.getPerson(1);
		person.setName("new name");
		personService.update(person);
		assertTrue("new name".equals(personService.getPerson(1).getName()));
	}

	/**
	 * Test method for
	 * {@link cn.itcast.service.PersonService#getPerson(java.lang.Integer)}.
	 */
	@Test
	public void testGetPerson() {
		Person person = personService.getPerson(1);
		assertTrue(1 == person.getId());
		assertTrue("傻X".equals(person.getName()));
	}

	/**
	 * Test method for
	 * {@link cn.itcast.service.PersonService#delete(java.lang.Integer)}.
	 */
	@Test
	public void testDelete() {
		Person person = personService.getPerson(1);
		personService.delete(person.getId());
		assertTrue(null == personService.getPerson(1));
	}

	/**
	 * Test method for {@link cn.itcast.service.PersonService#getPersons()}.
	 */
	@Test
	public void testGetPersons() {
		List<Person> persons = personService.getPersons();
		assertEquals(1, persons.size());
		assertEquals(1, persons.get(0).getId().intValue());
		assertEquals("new name", persons.get(0).getName());
	}

	@Test
	public void testSecondLevelCache() {
		Person person = personService.getPerson(2);
		System.out.println(person.getName());
		System.out.println("请关闭数据库...");
		try {
			Thread.sleep(1000 * 30);// 挂起半分钟
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(personService.getPerson(2).getName());
		// note:这个说明不正确，留在这里是为了以后分析：
		// 关闭数据库之后还能得到结果，但是这里不说明不了二级缓存的应用，视频中给的这个例子应该只能说明一级缓存

		// 新的说明：
		// 由于加入了事务管理，这里的getPerson事务传递属性是unsupported，每一次调用这个函数，会在开始的时候open一个session，在方法执行之后关闭这个session，所以这里两次调用同一个方法就是两个不同的session，而前一个session关闭之后，将数据库也关闭了，所以第二个session取得的值只能是从二级缓存得到
	}

	@Test
	public void testLazyLoad() {
		Person person = personService.getPerson(2);
		System.out.println(person == null);
		try {
			Thread.sleep(1000 * 5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("重新打印...");
		System.out.println(person.getName());
	}
}
