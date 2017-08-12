/**
 *
 */
package change.integer.innerclass.array;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * <p>
 * Title:Main
 * </p>
 * <p>
 * Description:通过修改Integer内部缓存的数组 改变-128~127对应的int值
 * </p>
 * 
 * @author Edwin
 * @since 2017-3-12
 * @version 1.0
 */
public class Main {

	/**
	 * @param args
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	public static void main(String[] args) throws IllegalArgumentException,
			IllegalAccessException, SecurityException, NoSuchFieldException {
		int a = 10, b = 20;

		// 一个小知识点， java中不存在 func(int &a)的写法，所以不能通过这种方式修改实参的值。

		// 修改Integer内部的缓存数组
		// changeInnerArray(a, b);
		changeInnerArray();

		// 打印a=10 没有装箱 使用的是java本身内部对简单类型的输出 sysout是老的java实现 出现在java5之前
		System.out.println("a=" + a);

		// 打印a.....111 进行了装箱 system.out.printf是1.5才引入的 有了自动装箱的实现。
		System.out.printf("a........%d\n", a);

		System.out.println("b=" + b);

		// valueof 获取内部对象
		System.out.println("valueof : " + Integer.valueOf(10));
	}

	/**
	 * 改变Integer内部类的缓存数组
	 * 
	 * @param a
	 * @param b
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	public static void changeInnerArray(int a, int b)
			throws IllegalArgumentException, IllegalAccessException,
			SecurityException, NoSuchFieldException {
		// 获取Integer内部类 IntegerCache
		Class<?> classBySimpleName = ReviewRefactor
				.getDeclaredClassBySimpleName(Integer.class, "IntegerCache");

		// 获取IntegerCache内部的cache字段
		Field cacheField = ReviewRefactor.getDeclaredFieldByName(
				classBySimpleName, "cache");

		// 因为cache 被final关键字修饰，所以在修改之前要移除final修饰
		// 获取Field类的modifiers字段
		Field modifiersField = Field.class.getDeclaredField("modifiers");

		// 设置modifiersField 访问权限
		modifiersField.setAccessible(true);

		// 将final修饰从 cacheField上面移除
		modifiersField.set(cacheField, cacheField.getModifiers()
				& ~Modifier.FINAL);

		// 设置cache 字段的访问权限
		cacheField.setAccessible(true);

		// 从内部类中提取cache 属性 因为属性是static的 所以数据源是null
		Integer[] cache = (Integer[]) cacheField.get(null);

		// 操作提取到的内部类的cache数组 将原来数字10对应的换粗数字由10改成111
		cache[10 + 128] = 111;

		// 修改原来的内部类中的数组
		// set的第一个参数是要设置字段的对象，如果字段是static的可以设置为null。（如果不是static的却设置为null，会抛出异常）
		cacheField.set(null, cache);
		Integer[] newcache = (Integer[]) cacheField.get(null);
		System.out.println(newcache);
	}

	/**
	 * 改变Integer内部类的缓存数组，改进实现。 因为返回的 Integer[] 在java中同样是以前对象的引用。所以直接修改这个数组，也是修改Integer内部类对象的属性
	 * 
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public static void changeInnerArray() throws SecurityException,
			NoSuchFieldException, IllegalArgumentException,
			IllegalAccessException {
		Class<?> classBySimpleName = ReviewRefactor
				.getDeclaredClassBySimpleName(Integer.class, "IntegerCache");
		Field field = classBySimpleName.getDeclaredField("cache");
		field.setAccessible(true);
		Integer[] cache = (Integer[]) field.get(null);

		// 这里修改的就是原来的缓存数组。 要区别的一点是这里修改的是对象数组的元素，
		// final关键字修改的是数组本身，也就是说数组的引用不能改变，所以上面直接进行set方法去试图修改数组引用（虽然那个set的新内容是之前get的）需要先移除final的修改。
		cache[10 + 128] = 250;
	}

}
