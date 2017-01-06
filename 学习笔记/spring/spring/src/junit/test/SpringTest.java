package junit.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.service.PersonService;


public class SpringTest {
	static ApplicationContext ctx = null;
	@BeforeClass
	public static void setUpBeforeClass(){
		ctx = new ClassPathXmlApplicationContext(new String[]{"beans.xml"});
	}
	@Test
	public void instanceSpring(){
		
		PersonService personService = (PersonService) ctx.getBean("personService");
		personService.save();
		PersonService personService1 = (PersonService) ctx.getBean("personService1");
		personService1.save();
		PersonService personService2 = (PersonService) ctx.getBean("personService2");
		personService2.save();
		System.out.println("personService=personService1?:" + (personService==personService1));
		System.out.println("personService1=personService2?:" + (personService1==personService2));
	}
	
	@Test
	public void isBeansEqual(){
		PersonService personService = (PersonService) ctx.getBean("personService");
		PersonService personService1 = (PersonService) ctx.getBean("personService");
		//下面的结果是相等的。如果要每次得到不同的对象，可以为这个bean的scope设置为prototype
		System.out.println("personService=personService1?:" + (personService==personService1));
	}
	
	@Test
	public void initAndDestroyMethod(){
		PersonService ps = (PersonService) ctx.getBean("personServiceInitDestroy");
		((AbstractApplicationContext) ctx).close();
	}
	
	@Test
	public void refPersonBean(){
		PersonService ps = (PersonService) ctx.getBean("personService");
		ps.add(); //没有出现空指针异常就说明容器自动注入了一个person对象
	}
	
	@Test
	public void setConstructorArgs(){
		PersonService ps = (PersonService) ctx.getBean("personService3");
		ps.add(); //没有出现空指针异常就说明容器自动注入了一个person对象
	}
	
	@Test
	public void testAOP1(){ //测试aop
		PersonService ps = (PersonService) ctx.getBean("personService");
		ps.add(); //出现拦截会在执行前打印前置通知的消息,执行后打印后置通知。
	}
	
	@Test
	public void testAOP2(){ //测试aop,before拦截带参数限制
		PersonService ps = (PersonService) ctx.getBean("personService");
		ps.printValue("哇哈哈"); //会额外的打印匹配了参数限制的前置通知
	}
}
