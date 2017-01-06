/**
 *
 */
package junit.test;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.ss2h.domain.Gender;
import cn.ss2h.domain.Person;
import cn.ss2h.service.PersonService;
import cn.ss2h.service.impl.PersonServiceBean;

/**
 * @version 1.0
 * @author Edwin
 * @since 2016-7-22
 */
public class PersonServiceTest {
	static PersonService personService;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		personService = (PersonService) new ClassPathXmlApplicationContext(
				"beans.xml").getBean("personService");
	}

	/**
	 * Test method for
	 * {@link cn.ss2h.service.PersonService#add(cn.ss2h.domain.Person)}.
	 */
	@Test
	public void testAdd() {
		personService.add(new Person("张三"));
	}

	/**
	 * Test method for
	 * {@link cn.ss2h.service.PersonService#update(cn.ss2h.domain.Person)}.
	 */
	@Test
	public void testUpdate() {
		Person person = personService.getPerson(2);
		person.setName("id为2的新名称");
		personService.update(person);
		System.out.println(personService.getPerson(2).getName());
	}

	/**
	 * Test method for
	 * {@link cn.ss2h.service.PersonService#delete(cn.ss2h.domain.Person[])}.
	 */
	@Test
	public void testDelete() {
		Person person = personService.getPerson(2);
		personService.delete(person);
	}

	/**
	 * Test method for
	 * {@link cn.ss2h.service.PersonService#getPerson(java.lang.Integer)}.
	 */
	@Test
	public void testGetPerson() {
		Person person = personService.getPerson(2);
		System.out.println(person.getName());
	}

	/**
	 * Test method for
	 * {@link cn.ss2h.service.PersonService#getPersons(java.lang.Integer[])}.
	 */
	@Test
	public void testGetPersons() {
		List<Person> persons = personService.getPersons(1, 3, 2);
		for (Person person : persons) {
			System.out.println(person.getName());
		}
	}

	/**
	 * Test method for {@link cn.ss2h.service.PersonService#getAllPersons()}.
	 */
	@Test
	public void testGetAllPersons() {
		List<Person> persons = personService.getAllPersons();
		for (Person person : persons) {
			System.out.println(person.getName());
		}
	}

	@Test
	public void test1Cache() { // 测试一级缓存
		// 得到一个session，这里由于transaction让spring管理了，所以这里的测试要用原生的get或者load方法
		SessionFactory sessionFactory = (SessionFactory) new ClassPathXmlApplicationContext(
				"beans.xml").getBean("sessionFactory");
		Session session = sessionFactory.openSession();

		// 第一次查询
		Person person = (Person) session.get(Person.class, 4);
		System.out.println(person.getName());

		// 进行一次update操作
		session.beginTransaction();
		// Person person1 = (Person)
		// session.get(Person.class,4);//这样本身是持久化对象，进行修改session会同步
		Person person1 = new Person();
		Gender gender = new Gender();
		gender.setId(1);
		gender.setName("耳鼻"); // 由于更新的sql语句中没有用到这一项，所有设置也是无效的(更新的sql: update
								// person set person.genderId=。。。。)

		person1.setGender(gender);
		person1.setId(20);
		person1.setName("傻Xxx");
		session.update(person1);
		session.getTransaction().commit(); // 测试证明，不同的id之间的对象不会相互影响。这里修改了

		// 第二次查询,经过测试，如果在两次查询之间没有update，不会第二次出现sql语句。
		person = (Person) session.get(Person.class, 4);
		System.out.println(person.getName());
	}

	@Test
	public void test1Cache2() { // 测试一级缓存
		// 得到一个session，这里由于transaction让spring管理了，所以这里的测试要用原生的get或者load方法
		SessionFactory sessionFactory = (SessionFactory) new ClassPathXmlApplicationContext(
				"beans.xml").getBean("sessionFactory");
		Session session = sessionFactory.openSession();

		// 第一次查询
		Person person = (Person) session.get(Person.class, 4);
		System.out.println(person.getName());

		//进行hql的更新，通过测试发现，这一条更新语句不会通知缓存进行更新
		String hql = "update Person as p set p.name='????' where id=?";
		personService.execute(hql, 4);

		// 第二次查询,经过测试，上面的更新不会同步到缓存，这里还是直接从缓存中取得的原来的数据。
		person = (Person) session.get(Person.class, 4);
		System.out.println(person.getName());
	}
}
