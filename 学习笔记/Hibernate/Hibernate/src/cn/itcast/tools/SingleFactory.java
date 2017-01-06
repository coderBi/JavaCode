package cn.itcast.tools;

import java.util.LinkedList;
import java.util.List;

/**
 * ������
 * 
 * @author bww
 * @since 2016��6��30��18:42:43
 * 
 */
public class SingleFactory {
	private static List<Object> objs = new LinkedList<Object>();

	private SingleFactory() {

	}

	public static  Object Factory(Class cls) {
		// 在列表中就直接返回
		for (Object obj : objs) {
			if (cls.isInstance(obj)) {
				return obj;
			}
		}

		// 不在列表中需要创建并且添加到列表然后返回
		Object obj = null;
		try {
			obj = cls.newInstance();
			objs.add(obj);
			return obj;
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
