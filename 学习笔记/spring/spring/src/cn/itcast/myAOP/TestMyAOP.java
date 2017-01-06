/**
 * 进行我自己编写的 proxy代理跟 cglib代理的测试
 */
package cn.itcast.myAOP;

import org.junit.Test;

/**
 * @version 1.0
 * @author Edwin
 * @since 2016-7-18
 */
public class TestMyAOP {
	@Test
	public void testProxy() { // 没有权限测试
		Person person = (Person) new MyProxyFactory()
				.createProxyInstance(new PersonBean());
		person.add(); // 如果没有空指针错误并且提示没有权限，就说明生效了
	}

	@Test
	public void testProxy1() { // 有权限测试
		Person person = (Person) new MyProxyFactory()
				.createProxyInstance(new PersonBean("傻X"));
		person.add(); // 拥有权限，会成功执行方法体

	}

	@Test
	public void testCGLib() { // 没有权限测试
		Person person = (Person) new MyCGLibFactory()
				.createCGLibInstance(new PersonBean());
		person.add(); // 如果没有空指针错误并且提示没有权限，就说明生效了
	}
	
	@Test
	public void testCGLib1() { // 有权限测试
		Person person = (Person) new MyCGLibFactory()
		.createCGLibInstance(new PersonBean("傻X"));
		person.add(); // 如果没有空指针错误并且提示没有权限，就说明生效了
	}
}
