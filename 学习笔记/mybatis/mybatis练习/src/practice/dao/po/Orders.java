/**
 *
 */
package practice.dao.po;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * <p>
 * Title:Orders
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author Edwin
 * @since 2016-8-23
 * @version 1.0
 */
public class Orders {
	private Integer id;
	private Integer user_id;
	private String number;
	private Timestamp createtime;
	private String note;
	private User user;
	private List<Orderdetail> orderdetails;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Orderdetail> getOrderdetails() {
		return orderdetails;
	}

	public void setOrderdetails(List<Orderdetail> orderdetails) {
		this.orderdetails = orderdetails;
	}
}
