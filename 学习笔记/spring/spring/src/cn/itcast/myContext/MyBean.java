package cn.itcast.myContext;

import java.util.List;

public class MyBean {
	private String id;
	private String className;
	private List<MyProperty> properties;
	
	public MyBean() {
		super();
	}

	public MyBean(String id, String className) {
		super();
		this.id = id;
		this.className = className;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<MyProperty> getProperties() {
		return properties;
	}

	public void setProperties(List<MyProperty> properties) {
		this.properties = properties;
	}

}
