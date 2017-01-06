package cn.ss2h.domain;

/**
 * Person的java bean，与数据库中的 t_person对应
 * 
 * @version 1.0
 * @author Edwin
 * @since 2016-7-21
 */
public class Person {
	private Integer id;
	private String name;
	private Gender gender;

	public Person() {
		super();
	}

	public Person(String name) {
		super();
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
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
