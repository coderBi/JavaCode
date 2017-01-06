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
		// ע������
		//DriverManager.registerDriver(new com.mysql.jdbc.Driver()); // ���ߣ�
																	// System.setProperty("jdbc.drivers","com.mysql.jdbc.Driver")
																	// ;���ߣ�Class.forName("com.mysql.jdbc.Driver")װ���ൽ�����
		Class.forName("com.mysql.jdbc.Driver");  //�������Ƽ���д��
		// ��������
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/jdbc", "root", "123");

		// �������
		Statement st = conn.createStatement();

		// ִ�����
		ResultSet rs = st.executeQuery("select * from user");

		// ������
		while (rs.next()) {
			System.out.println(rs.getObject(1) + "\t" + rs.getObject(2) + "\t"
					+ rs.getObject(3) + "\t" + rs.getObject(4));
		}

		// �ͷ���Դ
		rs.close();
		st.close();
		conn.close();
	}
}
