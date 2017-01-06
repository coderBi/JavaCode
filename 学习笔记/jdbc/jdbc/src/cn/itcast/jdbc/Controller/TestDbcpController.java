package cn.itcast.jdbc.Controller;

import java.sql.Connection;
import java.sql.SQLException;

import com.sun.xml.internal.bind.v2.runtime.RuntimeUtil.ToStringAdapter;

import cn.itcast.jdbc.tools.JdbcUtils1;

public class TestDbcpController {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		for(int i = 0; i < 10; i++){
			Connection conn = JdbcUtils1.getConnection();
			System.out.println(conn);
		}
	}
}
