/**
 *
 */
package cn.itcast.myAOP;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @version 1.0
 * @author Edwin
 * @since 2016-7-18
 */
public class MyCGLibFactory implements MethodInterceptor {
	private Object targetObject;
	
	public Object createCGLibInstance(Object targetObject){
		this.targetObject = targetObject;
		Enhancer enhancer = new Enhancer();
		enhancer.setClassLoader(targetObject.getClass().getClassLoader());
		enhancer.setSuperclass(targetObject.getClass());
		enhancer.setCallback(this);
		return enhancer.create();
	}

	public Object intercept(Object proxy, Method method, Object[] args,
			MethodProxy methodProxy) throws Throwable {
		PersonBean person = (PersonBean) targetObject;
		if(null == person.getName()){
			System.out.println("检测到您没有此操作权限");
		} else {
			return method.invoke(targetObject, args);
		}
		return null;
	}
}
