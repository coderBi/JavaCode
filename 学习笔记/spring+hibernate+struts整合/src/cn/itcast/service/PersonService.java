/**
 *
 */
package cn.itcast.service;

import java.util.List;

import cn.itcast.bean.Person;

/**
 * @version 1.0
 * @author Edwin
 * @since 2016-7-19
 */
public interface PersonService {

	public abstract void save(Person person);

	public abstract void update(Person person);

	public abstract Person getPerson(Integer personId);

	public abstract void delete(Integer personId);

	public abstract List<Person> getPersons();

}