/**
 *
 */
package cn.itcast.web.formbean;

import org.apache.struts.action.ActionForm;

/**
 * @version 1.0
 * @author Edwin
 * @since 2016-7-21
 */
public class PersonForm extends ActionForm{
	private static final long serialVersionUID = -96421750310829198L;
	private Integer id;
	private String name;

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
