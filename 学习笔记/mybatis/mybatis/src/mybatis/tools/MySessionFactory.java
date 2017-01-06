/**
 *
 */
package mybatis.tools;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * <p>Title:MySessionFactory</p>
 * <p>Description:SqlSessionFactory单例实现</p>
 * @author Edwin
 * @since 2016-7-30
 * @version 1.0
 */
public class MySessionFactory {
	private static SqlSessionFactory sqlSessionFactory;
	static {
		String resouce = "SqlMapConfig.xml";
		try {
			//采用饿汉式，是因为饱汉式可能出现高并发降低性能的情况。
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(resouce));
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public static SqlSessionFactory getSqlSessionFactory(){
		return sqlSessionFactory;
	}
}
