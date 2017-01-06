package cn.itcast.jdbc.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.itcast.jdbc.Model.Bean.UserBean;
import cn.itcast.jdbc.tools.JUtils;

public class UserModel {
	private JUtils jutils =  JUtils.GetInstance();  //数据库操作工具类对象
	private Connection conn = null;  //数据库连接
	Statement st = null;
	PreparedStatement ps = null;
	ResultSet rs = null; //存返回的结果集
	
	/**
	 * 增加一个用户
	 * @param user UserBean对象
	 * @return 传入的UserBean对象。如果插入成功了，这个UserBean对象的id会被修改为插入之后的id
	 */
	public UserBean add( UserBean user) {
		String sql = "insert into user(name,birthday,money) value(?,?,?)";
		try {
			getConnection();
			ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getName());
			ps.setDate(2, user.getBirthday());
			ps.setFloat(3, user.getMoney());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if(rs.next()){
				user.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		} finally {
			jutils.free(rs, ps, conn);
		}
		return user;
	}

	/**
	 * 获得一个连接，并将它赋给conn
	 */
	private void getConnection() {
		conn = jutils.getConnection();
		if(!(conn instanceof Connection)){
			throw new RuntimeException("数据库连接失败");
		}
	}
	
	/**
	 * 获得整张用户表的信息
	 * @return 所有用户组成的一个ArrayList
	 */
	public List<UserBean> read(){
		List<UserBean> users = new ArrayList<UserBean>(); //存储查询结果
		try {
			getConnection();
			st = conn.createStatement();
			String sql = "select id,name,birthday,money from user";
			rs = st.executeQuery(sql);
			while(rs.next()){
				UserBean user = new UserBean();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setBirthday(rs.getDate("birthday"));
				user.setMoney(rs.getFloat("money"));
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		} finally {
			jutils.free(rs, st, conn);
		}
		return users;
	}
	
	/**
	 * 更新一条记录
	 * @param user 待更新的用户信息
	 * @return  成功返回true，否则false
	 */
	public boolean update(UserBean user){
		int res = 0;
		try {
			getConnection();
			String sql = "update user set name=?,birthday=?,money=? where id=?;";
			ps = conn.prepareStatement(sql,ResultSet.CONCUR_READ_ONLY);
			ps.setString(1, user.getName());
			ps.setDate(2, user.getBirthday());
			ps.setFloat(3, user.getMoney());
			ps.setInt(4, user.getId());
			res = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		return res == 0 ? false : true;
	}
	
	/**
	 * 删除一个用户
	 * @param id 待删除的用户id
	 * @return 受影响的行数，如果删除成功返回1，否则返回0
	 */
	public int delete(int id){
		int res = 0;
		try {
			getConnection();
			String sql = "delete from user where id=?;";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			res = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		return res;
	}
	
	/**
	 * 通过id查找一个用户	
	 * @param id 待查找的用户id
	 * @return 查找到的用户信息包装成的UserBean。如果没有查找到，则这个UserBean中的id未赋值，可以作为判断是否成功的依据
	 */
	public UserBean find(int id) {
		UserBean user = new UserBean();
		try {
			getConnection();
			String sql = "select id,name,birthday,money from user where id=?;";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs =  ps.executeQuery();
			if(rs.next()){
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setMoney(rs.getFloat("money"));
				user.setBirthday(rs.getDate("birthday"));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		return user;
	}
}