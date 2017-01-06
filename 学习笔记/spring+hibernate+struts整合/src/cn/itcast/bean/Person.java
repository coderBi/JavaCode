/**
 *
 */
package cn.itcast.bean;

/**
 * @version 1.0
 * @author Edwin
 * @since 2016-7-19
 */
public class Person {
	private Integer id;
	private String name;

	public Person() {
		super();
	}

	public Person(String name) {
		super();
		this.name = name;
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
