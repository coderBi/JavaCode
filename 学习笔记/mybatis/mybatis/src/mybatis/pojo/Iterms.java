/**
 *
 */
package mybatis.pojo;

import java.sql.Date;

/**
 * <p>Title:Orders</p>
 * <p>Description:</p>
 * @author Edwin
 * @since 2016-8-5
 * @version 1.0
 */
public class Iterms {
	private Integer id;
	private String name;
	private float price;
	private String detail;
	private String pic;
	private Date createtime;
	
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
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
}
