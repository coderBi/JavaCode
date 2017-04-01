/**
 *
 */
package chartroom.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * <p>Title:JDBCUtils</p>
 * <p>Description:连接数据库的工具类，在里面引用c3p0的配置使之生效</p>
 * @author Edwin
 * @since 2017-3-4
 * @version 1.0
 */
public class JDBCUtils {
	//dataSource 默认自动加载classpath 下面的 c3p0-config.xl 或者是 c3p0.properties。 否则要自己用set方法一个一个的属性设置。 
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	
	//获取连接池
	public static DataSource getDataSource(){
		return dataSource;
	}
	
	//获取连接
	public static Connection getConnection(){
		Connection connection = null;
		try{
			connection = dataSource.getConnection();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return connection;
	}
}
