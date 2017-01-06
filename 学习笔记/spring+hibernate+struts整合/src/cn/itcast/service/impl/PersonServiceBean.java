/**
 *
 */
package cn.itcast.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bean.Person;
import cn.itcast.service.PersonService;
/**
 * @version 1.0
 * @author Edwin
 * @since 2016-7-19
 */
@Transactional
@Service("personService")
public class PersonServiceBean implements PersonService {
	@Resource
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void save(Person person) {
		getSession().save(person); // 语义更清晰，可以是用persist，跟save作用相同
	}

	public void update(Person person) {
		getSession().merge(person); // 将游离状态的对象同步回去，返回托管对象，不推荐使用update
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true) //设置不要事务跟只读
	public Person getPerson(Integer personId) {
		return (Person) getSession().get(Person.class, personId);
	}

	public void delete(Integer personId) {
		getSession().delete(getSession().load(Person.class, personId));
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@SuppressWarnings("unchecked")
	public List<Person> getPersons() {
		String hql = "from Person";
		return getSession().createQuery(hql).list();
	}
}
