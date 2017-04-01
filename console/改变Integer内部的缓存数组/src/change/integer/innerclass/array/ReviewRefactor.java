/**
 *
 */
package change.integer.innerclass.array;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * <p>
 * Title:Main
 * </p>
 * <p>
 * Description:复习反射知识
 * </p>
 * 
 * @author Edwin
 * @since 2017-3-12
 * @version 1.0
 */
public class ReviewRefactor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 获取Integer的class对象
		@SuppressWarnings("rawtypes")
		Class cls = Integer.class;

		// 获取一个类的所有方法
		// getDeclaredMethods(cls);
		// getMethods(cls);

		// 获取所有的内部类
		// Class<?>[] allClasses = getAllDeclaredClasses(cls);
		// for(Class<?> item : allClasses){
		// System.out.println("class name: " + item.getName());
		// System.out.println("simple name: " + item.getSimpleName());
		// }

		// 获取指定的内部类
		// Class<?> classBySimpleName = getDeclaredClassBySimpleName(cls,
		// "IntegerCache");
		// System.out.println(classBySimpleName);

		// 获取一个指定的方法
		// getMethod(cls, "decode", java.lang.String.class);
		// getDeclaredMethod(cls, "toUnsignedString", int.class, int.class);

		// 获取所有字段
//		Field[] allFields = getAllFields(cls);
//		printFields(allFields);
		
		//获取指定字段
//		Field field = getFieldByName(cls, "SIZE");
//		printField(field);
		
		//可以利用 Class.newInstance() 实例化一个类  但是前提是有默认的构造方法。
	}

	/**
	 * 获取所有方法 内部使用 Class.getMethods 获取的是自身的以及继承得到的所有的public的方法
	 * 
	 * @param cls
	 *            要获取方法的类
	 */
	public static void getMethods(Class<?> cls) {
		// 获取所有方法
		Method[] methods = cls.getMethods();

		// 输出方法信息
		printMethods(methods);
	}

	/*
	 * 获取所有方法 内部使用的是 Class.getDeclaredMethods 得到的是本身这个类的private public protected
	 * 方法. 不能得到继承的方法
	 * 
	 * @param cls 要获取方法的类
	 */
	public static void getDeclaredMethods(Class<?> cls) {
		// 获取这个类本身的private public protected方法
		Method[] methods = cls.getDeclaredMethods();
		printMethods(methods);
	}

	/**
	 * 获取指定方法 只能获取到自己的或者是继承的public方法
	 * 
	 * @param cls
	 * @param name
	 * @param parameterTypes
	 */
	public static void getMethod(Class<?> cls, String name,
			Class<?>... parameterTypes) {
		Method method = null;

		// 获取指定的函数名 与参数列表 的方法。 java中重载只与方法名跟参数列表有关，没有返回值类型重载
		try {
			method = cls.getMethod(name, parameterTypes);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			System.out.println("没有找到相应方法.");
			e.printStackTrace();
		}

		// 输出method的信息
		printMethod(method);
	}

	/**
	 * 获取一个指定的方法 只能获取本类的方法 不能获取继承的方法
	 * 
	 * @param cls
	 * @param name
	 * @param parameterTypes
	 * @return
	 */
	public static void getDeclaredMethod(Class<?> cls, String name,
			Class<?>... parameterTypes) {
		Method method = null;
		try {
			// 获取指定方法
			method = cls.getDeclaredMethod(name, parameterTypes);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			System.out.println("没有找到相应方法.");
			e.printStackTrace();
		}

		// 打印方法信息
		printMethod(method);
	}

	/**
	 * 获取本身的跟继承的 所有public的内部类
	 * 
	 * @param cls
	 * @return 所有的内部类
	 */
	public static Class<?>[] getAllClasses(Class<?> cls) {
		Class<?>[] classes = cls.getClasses();
		return classes;
	}

	/**
	 * 获取所有内部类 只能获取当前类本身的 不能获取继承的
	 * 
	 * @param cls
	 * @return
	 */
	public static Class<?>[] getAllDeclaredClasses(Class<?> cls) {
		Class<?>[] classes = cls.getDeclaredClasses();
		return classes;
	}

	/**
	 * 获得指定名称的内部类
	 * 
	 * @param cls
	 *            要获取的外部类
	 * @param simpleName
	 *            内部类的简单名称
	 * @return
	 */
	public static Class<?> getClassBySimpleName(Class<?> cls, String simpleName) {
		Class<?>[] classes = getAllClasses(cls);
		for (Class<?> item : classes) {
			if (item.getSimpleName() == simpleName)
				return item;
		}
		return null;
	}

	/**
	 * 获取指定的内部类 只能获取本类的内部类 不能获取继承类的
	 * 
	 * @param cls
	 * @param simpleName
	 * @return
	 */
	public static Class<?> getDeclaredClassBySimpleName(Class<?> cls,
			String simpleName) {
		Class<?>[] classes = getAllDeclaredClasses(cls);
		for (Class<?> item : classes) {
			if (simpleName.equals(item.getSimpleName()))
				return item;
		}
		return null;
	}

	/**
	 * 获取所有成员变量 只能获得自己的跟继承的类的public的成员
	 * 
	 * @param cls
	 * @return
	 */
	public static Field[] getAllFields(Class<?> cls) {
		Field[] fields = cls.getFields();
		return fields;
	}

	/**
	 * 通过字段名获取field  只能获取public的本类跟继承的字段
	 * @param cls
	 * @param name
	 * @return
	 */
	public static Field getFieldByName(Class<?> cls, String name) {
		Field[] allFields = getAllFields(cls);
		for (Field field : allFields) {
			if (name.equals(field.getName()))
				return field;
		}
		return null;
	}
	
	/**
	 * 获取所有成员变量 只能获得本类的  不能获取继承的
	 * 
	 * @param cls
	 * @return
	 */
	public static Field[] getAllDeclaredFields(Class<?> cls) {
		Field[] fields = cls.getDeclaredFields();
		return fields;
	}

	/**
	 * 通过字段名获取field  只能获取本类的  不能获取继承的
	 * @param cls
	 * @param name
	 * @return
	 */
	public static Field getDeclaredFieldByName(Class<?> cls, String name) {
		Field[] allFields = getAllDeclaredFields(cls);
		for (Field field : allFields) {
			if (name.equals(field.getName()))
				return field;
		}
		return null;
	}

	/**
	 * 输出Field输出
	 * 
	 * @param fields
	 */
	public static void printFields(Field[] fields) {
		for (Field field : fields) {
			printField(field);
		}
	}

	/**
	 * 输出一个Field的信息
	 * 
	 * @param field
	 */
	public static void printField(Field field) {
		// 获取字段名
		String name = field.getName();

		// 获取字段类型
		Class<?> class1 = field.getType();

		System.out.println(class1 + " " + name);
	}

	/**
	 * 输出方法数组信息
	 * 
	 * @param methods
	 */
	public static void printMethods(Method[] methods) {
		Integer count = 0; // 方法个数

		// 输出方法数组中每一个方法的信息
		for (Method method : methods) {
			// 输出编号
			System.out.print((++count) + ": ");

			// 输出每一个方法的信息
			printMethod(method);
		}
	}

	/**
	 * 按照自己的格式输出一个Method的信息
	 */
	public static void printMethod(Method method) {
		// 输出前面的modifiers，也就是修饰符，包括public private static interface 等
		// 获取修饰符 这里返回的是一个int 可以通过Modifier.toString(i) 得到相应的字符串
		int modifiers = method.getModifiers();

		// 获取modifier的名称
		String strModifiers = Modifier.toString(modifiers);

		// 输出modifiers字符串
		System.out.print("modifiers=" + strModifiers + ",");

		// 输出方法名称
		System.out.print(" method name=" + method.getName() + ",");

		// 获取参数列表的类型
		Class<?>[] parameterTypes = method.getParameterTypes();

		// 输出参数列表的类型
		System.out.print(" parameter types={ ");

		// 获取参数列表中各个类型的名称列表
		String strParameterList = "";
		for (Class<?> parametertype : parameterTypes) {
			strParameterList += parametertype.getName() + ",";
		}
		System.out.print(strParameterList + " },");

		// 输出返回值类型
		System.out.println("return type=" + method.getReturnType().getName());

		// 输出完整拼接起来的函数申明
		System.out.println(strModifiers + " "
				+ method.getReturnType().getName() + " " + method.getName()
				+ "(" + strParameterList + ")");
	}

}
