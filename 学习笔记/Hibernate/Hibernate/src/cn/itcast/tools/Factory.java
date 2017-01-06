package cn.itcast.tools;

public class Factory {
	
	public static <T>T F(Class<T> cls){
		try {
			return cls.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
