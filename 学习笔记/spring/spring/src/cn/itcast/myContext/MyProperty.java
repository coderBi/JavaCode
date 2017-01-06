package cn.itcast.myContext;

public class MyProperty {
	private String name; // 存property的name
	private String ref; // 存property里面引用的bean的id
	private String value;  //存property里面的value属性
	private MyBean subBean;
	
	public MyBean getSubBean() {
		return subBean;
	}

	public void setSubBean(MyBean subBean) {
		this.subBean = subBean;
	}


	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public MyProperty(String name, String ref, String value, MyBean subBean) {
		super();
		this.name = name;
		this.ref = ref;
		this.value = value;
		this.subBean = subBean;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
