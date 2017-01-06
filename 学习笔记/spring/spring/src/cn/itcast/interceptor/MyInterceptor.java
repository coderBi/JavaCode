/**
 *
 */
package cn.itcast.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @author Edwin
 * @since 2016-7-18
 */
@Aspect //@Component
public class MyInterceptor {
	@Pointcut("execution(* cn.itcast.service.impl.*.*(..))")
	private void method1(){} //申明一个切入点
	
	@Before("method1()")
	public void beforeCut(){
		System.out.println("前置通知");
	}
	
	/**
	 * 对输入参数进行限制。这里要求不仅要满足切入点的表达式，还要求输入一个String类型的参数
	 * @param name 输入的参数。
	 */
	@Before("method1() && args(name)")
	public void beforeCut1(String name){
		System.out.println("带了参数匹配的前置通知");
	}
	
	@AfterReturning("method1()")
	public void afterReturningCut(){
		System.out.println("后置通知");
	}
	
	@After("method1()")
	public void afterCut(){
		System.out.println("最终通知");
	}
	
	@AfterThrowing("method1()")
	public void afterThrowringCut(){
		System.out.println("异常通知");
	}
	
	@Around("method1()")
	public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("进入方法");
		Object toReturn = pjp.proceed(); //执行切入点方法
		System.out.println("退出方法");
		return toReturn;
	}
}
