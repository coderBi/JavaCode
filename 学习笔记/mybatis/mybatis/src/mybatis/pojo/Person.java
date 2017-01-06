/**
 *
 */
package mybatis.pojo;

import java.sql.Date;

/**
 * <p>
 * Title:Person
 * </p>
 * <p>
 * Description:介绍
 * </p>
 * 
 * @author Edwin
 * @since 2016-7-29
 * @version 1.0
 */
public class Person {
	private Integer id;
	private String username;
	private Date birthday;
	private Integer gender;
	private String address;
	private Orders orders;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", username=" + username + ", birthday="
				+ birthday + ", address=" + address + "]";
	}

	
	public Person() {
		super();
	}

	public Person(String username, Date birthday, Integer gender, String address) {
		super();
		this.username = username;
		this.birthday = birthday;
		this.gender = gender;
		this.address = address;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}
}
