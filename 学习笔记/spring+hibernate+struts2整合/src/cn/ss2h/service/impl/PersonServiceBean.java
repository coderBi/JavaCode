/**
 *
 */
package cn.ss2h.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.ss2h.domain.Person;
import cn.ss2h.service.PersonService;

/**
 * @version 1.0
 * @author Edwin
 * @since 2016-7-22
 */
@Transactional
@Service("personService")
public class PersonServiceBean extends BaseServiceImpl implements PersonService {
	/**
	 * 获取一个session，通过向session工厂调用getCurrentSession。要注意的是这个函数由于用了注解sessionFactory
	 * ，而注解是在对象产生之后才进行的，所以不能再对象构造器里面取引用这个getCurrentSession。
	 * @return 获取到的Session对象
	 */
	private Session getSession() {
		return getSessionFactory().getCurrentSession();
	}

	@Override
	public void add(Person person) {
		getSession().persist(person);
	}

	@Override
	public void update(Person person) {
		getSession().merge(person);
	}

	@Override
	public void delete(Person... persons) {
		for (Person person : persons) {
			getSession().delete(person);
		}
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Person getPerson(Integer id) {
		return (Person) getSession().get(Person.class, id);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Person> getPersons(Integer... ids) {
		List<Person> persons = new ArrayList<Person>();
		for (Integer id : ids) {
			Person person = getPerson(id);
			if (null != person)
				persons.add(person);
		}
		return persons;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Person> getAllPersons() {
		String hql = "from Person";
		return (List<Person>) fetchAll(hql, new Object[] {});
	}
}
