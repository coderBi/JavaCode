package cn.itcast.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Base {

	/**
	 * @param args
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		test();
	}

	static void test() throws SQLException, ClassNotFoundException {
		// 注册驱动
		//DriverManager.registerDriver(new com.mysql.jdbc.Driver()); // 或者：
																	// System.setProperty("jdbc.drivers","com.mysql.jdbc.Driver")
																	// ;或者：Class.forName("com.mysql.jdbc.Driver")装载类到虚拟机
		Class.forName("com.mysql.jdbc.Driver");  //这是最推荐的写法
		// 建立连接
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/jdbc", "root", "123");

		// 创建语句
		Statement st = conn.createStatement();

		// 执行语句
		ResultSet rs = st.executeQuery("select * from user");

		// 处理结果
		while (rs.next()) {
			System.out.println(rs.getObject(1) + "\t" + rs.getObject(2) + "\t"
					+ rs.getObject(3) + "\t" + rs.getObject(4));
		}

		// 释放资源
		rs.close();
		st.close();
		conn.close();
	}
}
