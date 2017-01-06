/**
 *
 */
package mybatis.pojo;

import java.sql.Date;

/**
 * <p>
 * Title:OrdersCustom
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author Edwin
 * @since 2016-8-5
 * @version 1.0
 */
public class OrdersCustom extends Orders { // 已经继承了字段较多的类
	private String username;
	private Date birthday;
	private Integer gender;
	private String address;

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
}
