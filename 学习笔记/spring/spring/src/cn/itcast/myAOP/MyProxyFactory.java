/**
 *
 */
package cn.itcast.myAOP;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @version 1.0
 * @author Edwin
 * @since 2016-7-18
 */
public class MyProxyFactory implements InvocationHandler {
	private Object targetObject;
	public Object createProxyInstance(Object targetObject) {
		this.targetObject = targetObject;
		return Proxy.newProxyInstance(targetObject.getClass()
				.getClassLoader(), targetObject.getClass().getInterfaces(),
				this);
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		PersonBean person = (PersonBean) targetObject;
		if(null == person.getName()){
			System.out.println("检测到您没有权限进行此操作");
		} else {
			return method.invoke(person, args);
		}
		return null;
	}
}
