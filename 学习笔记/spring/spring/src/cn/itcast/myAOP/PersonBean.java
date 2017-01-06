/**
 *
 */
package cn.itcast.myAOP;

/**
 * @version 1.0
 * @author Edwin
 * @since 2016-7-18
 */
public class PersonBean implements Person {
	private String name;

	public PersonBean() {}
	
	public PersonBean(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void add(){
		System.out.println("add方法执行");
	}
}
