package cn.itcast.myContext;

import org.junit.Test;

import cn.itcast.service.PersonService;

public class TestMyContext {
	@Test
	public void test1() {
		MyClassPathXMLApplicationContext myCtx = new MyClassPathXMLApplicationContext("cn/itcast/myContext/beans.xml");
		PersonService ps = (PersonService) myCtx.getBean("personService");
		ps.add();
	}
	@Test
	public void test2() { //测试内部bean的效果
		MyClassPathXMLApplicationContext myCtx = new MyClassPathXMLApplicationContext("cn/itcast/myContext/beans.xml");
		PersonService ps = (PersonService) myCtx.getBean("personService1");
		ps.add();
	}
	@Test
	public void test3() { //测试给属性注入基本值
		MyClassPathXMLApplicationContext myCtx = new MyClassPathXMLApplicationContext("cn/itcast/myContext/beans.xml");
		PersonService ps = (PersonService) myCtx.getBean("personService1");
		ps.printValue();
	}
	
	@Test
	public void test4() { //测试myAnnotation
		MyClassPathXMLApplicationContext myCtx = new MyClassPathXMLApplicationContext("cn/itcast/myContext/beans.xml");
		PersonService ps = (PersonService) myCtx.getBean("personService2");
		ps.add(); //没有发现空指针异常，说明通过注解也可以注入person对象
	}
}
