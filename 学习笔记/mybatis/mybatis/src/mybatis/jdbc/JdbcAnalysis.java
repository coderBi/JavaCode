/**
 *
 */
package mybatis.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>
 * Title:JdbcAnalysis
 * </p>
 * <p>
 * Description:通过一个简单的jdbc程序总结直接使用jdbc的问题
 * </p>
 * 
 * @author Edwin
 * @since 2016-7-29
 * @version 1.0
 */
public class JdbcAnalysis {
	public static void main(String[] args) {
		// 数据库连接
		Connection connection = null;
		// 预编译的statement，同一个预编译的sql会被数据库缓存，所以可以提高数据库的性能
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager
					.getConnection(
							"jdbc:mysql://localhost:3306/mybatis?userUnicode=true&characterEncoding=utf-8",
							"root", "123");
			String sql = "select * from user where username=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setObject(1, "张三");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				System.out.println(resultSet.getString("id") + " "
						+ resultSet.getString("address"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						if (null != connection)
							connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
