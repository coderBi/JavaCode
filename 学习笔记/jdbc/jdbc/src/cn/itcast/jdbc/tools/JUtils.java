package cn.itcast.jdbc.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class JUtils {
	/**
	 * 注册驱动代码块，跟类一起加载
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
	private LinkedList<Connection> conns = new LinkedList<Connection>(); // 连接池
	private int count = 0;
	private int maxCount = 10;
	private String url = "jdbc:mysql://localhost:3306/jdbc";
	private String user = "root";
	private String password = "123";
	private static JUtils juts = null;

	/**
	 * 构造函数，私有化
	 */
	private JUtils() {
		Connection conn = createOneConnection();
		conns.addLast(conn);
	}

	/**
	 * 创建一个新连接，并返回
	 * @return 新创建的连接
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
	 * 得到单例
	 * 
	 * @return 单例对象
	 */
	public static JUtils GetInstance() {
		if (!(juts instanceof JUtils))
			juts = new JUtils();
		return juts;
	}

	/**
	 * 返回数据库连接
	 * 
	 * @return Connection对象
	 */
	public Connection getConnection() {
		Connection conn = null;
		synchronized (conns) {
			if (conns.size() > 0) {
				//如果连接池里面还有连接就直接取出来返回
				conn = conns.removeFirst();
			} else if (count < maxCount) {
				//连接池为空，并且连接数没有达到上限，就生成新的连接返回
				conn = createOneConnection();
			}
		}
		return conn;
	}
	
	/**
	 * 释放资源
	 * @param rs 结果集对象
	 * @param st 数据执行对象
	 * @param conn  数据库连接
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
