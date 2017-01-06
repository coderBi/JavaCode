/**
 *
 */
package sm.po;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * <p>
 * Title:User
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author Edwin
 * @since 2016-8-23
 * @version 1.0
 */
public class User implements Serializable{
	private static final long serialVersionUID = -13791967045057897L;
	private Integer id;
	private String username;
	private Date birthday;
	private Integer gender;
	private String address;

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

}
