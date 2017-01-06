package cn.itcast.jdbc.Controller;

import java.sql.Date;
import java.util.List;

import cn.itcast.jdbc.Model.UserModel;
import cn.itcast.jdbc.Model.Bean.UserBean;

public class userController {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		userController uc = new userController();
		//uc.add("name1", new Date(new java.util.Date().getTime()), 100.12f);
		//uc.update(3, "bww", new Date(new java.util.Date().getTime()), 21.1f);
		//uc.delete(6);
		//uc.read();
		uc.find(5);
	}
	
	/**
	 * ���һ����¼
	 * @param name ����ӵ��û���
	 * @param birthday  ����ӵ��û�����
	 * @param money  ����ӵ��û�money
	 */
	public void add(String name, Date birthday, float money) {
		UserBean user = createUserBean(name, birthday, money); // ����һ��userBean
		UserModel userModel = new UserModel();
		user = userModel.add(user);
		if (user.getId() != 0)
			System.out.println("�����û��ɹ�,������¼id=" + user.getId());
		else
			System.out.println("�����û�ʧ��");
	}

	/**
	 * ����һ��userBean�������е�name birthday money ���и�ֵ������
	 * 
	 * @param name
	 * @param birthday
	 * @param money
	 * @return �����õ�userBean������idû�и�ֵ��
	 */
	private UserBean createUserBean(String name, Date birthday, float money) {
		UserBean user = new UserBean();
		user.setBirthday(birthday);
		user.setName(name);
		user.setMoney(money);
		return user;
	}

	/**
	 * ��ȡ�û��б���ʾ��ӡ�ڿ���̨
	 */
	public void read() {
		UserModel userModel = new UserModel();
		List<UserBean> users = userModel.read();
		for (int i = 0; i < users.size(); i++) {
			printUser(users.get(i));
		}
	}

	/**
	 * ��ӡһ���û���Ϣ
	 * @param user ����ӡ��UserBean
	 */
	private void printUser(UserBean user) {
		String str = new String();
		str += "id=" + user.getId() + ",name=" + user.getName() + ",birthday="
				+ user.getBirthday() + ",money=" + user.getMoney();
		System.out.println(str);
	}
	
	/**
	 * ����һ���û���Ϣ
	 * @param id �����µ��û�id
	 * @param name
	 * @param birthday
	 * @param money
	 */
	public void update(int id, String name, Date birthday, float money) {
		UserBean user = createUserBean(name, birthday, money);
		user.setId(id);
		UserModel userModel = new UserModel();
		boolean res = userModel.update(user);
		if(res == true) System.out.println("���²����ɹ�");
		else System.out.println("���²���ʧ��");
	}
	
	/**
	 * ͨ��idɾ��һ���û�
	 * @param id ��ɾ�����û�id
	 */
	public void delete(int id) {
		UserModel userModel = new UserModel();
		int res = userModel.delete(id);
		if(res == 0){
			System.out.println("ɾ������ʧ��");
		} else {
			System.out.println("ɾ�������ɹ�");
		}
	}
	
	public void find(int id) {
		UserModel userModel = new UserModel();
		UserBean user = userModel.find(id);
		if(user.getId() == 0){
			System.out.println("û�в鵽��Ӧ���û�");
		} else {
			System.out.println("�Ѳ鵽��Ӧ���û���������Ϣ���£�");
			printUser(user);
		}
	}
}
