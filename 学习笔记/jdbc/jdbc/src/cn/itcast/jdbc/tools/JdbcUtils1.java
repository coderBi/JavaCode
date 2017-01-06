package cn.itcast.jdbc.tools;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;


public final class JdbcUtils1 {
	private static DataSource myDataSource= null;
	private JdbcUtils1(){
		
	}
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Properties prop = new Properties();
			InputStream is = JdbcUtils1.class.getClassLoader()
					.getResourceAsStream("dbcpconfig.properties");
			prop.load(is);
			myDataSource = BasicDataSourceFactory.createDataSource(prop);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return myDataSource.getConnection();
	}
}
