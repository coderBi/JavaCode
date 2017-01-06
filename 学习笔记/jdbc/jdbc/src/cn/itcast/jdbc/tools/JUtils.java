package cn.itcast.jdbc.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class JUtils {
	/**
	 * ע����������飬����һ�����
	 */
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
	}
	// private Connection conn = null;
	private LinkedList<Connection> conns = new LinkedList<Connection>(); // ���ӳ�
	private int count = 0;
	private int maxCount = 10;
	private String url = "jdbc:mysql://localhost:3306/jdbc";
	private String user = "root";
	private String password = "123";
	private static JUtils juts = null;

	/**
	 * ���캯����˽�л�
	 */
	private JUtils() {
		Connection conn = createOneConnection();
		conns.addLast(conn);
	}

	/**
	 * ����һ�������ӣ�������
	 * @return �´���������
	 */
	private Connection createOneConnection(){
		Connection conn;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
		count++;
		return conn;
	}
	
	/**
	 * �õ�����
	 * 
	 * @return ��������
	 */
	public static JUtils GetInstance() {
		if (!(juts instanceof JUtils))
			juts = new JUtils();
		return juts;
	}

	/**
	 * �������ݿ�����
	 * 
	 * @return Connection����
	 */
	public Connection getConnection() {
		Connection conn = null;
		synchronized (conns) {
			if (conns.size() > 0) {
				//������ӳ����滹�����Ӿ�ֱ��ȡ��������
				conn = conns.removeFirst();
			} else if (count < maxCount) {
				//���ӳ�Ϊ�գ�����������û�дﵽ���ޣ��������µ����ӷ���
				conn = createOneConnection();
			}
		}
		return conn;
	}
	
	/**
	 * �ͷ���Դ
	 * @param rs ���������
	 * @param st ����ִ�ж���
	 * @param conn  ���ݿ�����
	 */
	public void free(ResultSet rs, Statement st, Connection conn){
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				if(conn instanceof Connection)
				conns.addLast(conn);
			}
		}
	}
}
