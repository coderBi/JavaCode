/**
 *
 */
package cn.ss2h.domain;

import java.util.Set;

/**
 * 对应数据库中的 t_gender表
 * 
 * @version 1.0
 * @author Edwin
 * @since 2016-7-21
 */
public class Gender {
	private Integer id;
	private String name;
	private Set<Person> persons;

	public Gender() {
		super();
	}

	public Gender(String name) {
		super();
		this.name = name;
	}

	public Set<Person> getPersons() {
		return persons;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
