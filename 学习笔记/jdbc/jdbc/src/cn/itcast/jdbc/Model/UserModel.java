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
	private JUtils jutils =  JUtils.GetInstance();  //���ݿ�������������
	private Connection conn = null;  //���ݿ�����
	Statement st = null;
	PreparedStatement ps = null;
	ResultSet rs = null; //�淵�صĽ����
	
	/**
	 * ����һ���û�
	 * @param user UserBean����
	 * @return �����UserBean�����������ɹ��ˣ����UserBean�����id�ᱻ�޸�Ϊ����֮���id
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
	 * ���һ�����ӣ�����������conn
	 */
	private void getConnection() {
		conn = jutils.getConnection();
		if(!(conn instanceof Connection)){
			throw new RuntimeException("���ݿ�����ʧ��");
		}
	}
	
	/**
	 * ��������û������Ϣ
	 * @return �����û���ɵ�һ��ArrayList
	 */
	public List<UserBean> read(){
		List<UserBean> users = new ArrayList<UserBean>(); //�洢��ѯ���
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
	 * ����һ����¼
	 * @param user �����µ��û���Ϣ
	 * @return  �ɹ�����true������false
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
	 * ɾ��һ���û�
	 * @param id ��ɾ�����û�id
	 * @return ��Ӱ������������ɾ���ɹ�����1�����򷵻�0
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
	 * ͨ��id����һ���û�	
	 * @param id �����ҵ��û�id
	 * @return ���ҵ����û���Ϣ��װ�ɵ�UserBean�����û�в��ҵ��������UserBean�е�idδ��ֵ��������Ϊ�ж��Ƿ�ɹ�������
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