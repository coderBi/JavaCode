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
	 * 添加一条记录
	 * @param name 待添加的用户名
	 * @param birthday  待添加的用户生日
	 * @param money  待添加的用户money
	 */
	public void add(String name, Date birthday, float money) {
		UserBean user = createUserBean(name, birthday, money); // 创建一个userBean
		UserModel userModel = new UserModel();
		user = userModel.add(user);
		if (user.getId() != 0)
			System.out.println("新增用户成功,新增记录id=" + user.getId());
		else
			System.out.println("新增用户失败");
	}

	/**
	 * 创建一个userBean，将其中的name birthday money 进行赋值并返回
	 * 
	 * @param name
	 * @param birthday
	 * @param money
	 * @return 创建好的userBean，其中id没有赋值。
	 */
	private UserBean createUserBean(String name, Date birthday, float money) {
		UserBean user = new UserBean();
		user.setBirthday(birthday);
		user.setName(name);
		user.setMoney(money);
		return user;
	}

	/**
	 * 读取用户列表，显示打印在控制台
	 */
	public void read() {
		UserModel userModel = new UserModel();
		List<UserBean> users = userModel.read();
		for (int i = 0; i < users.size(); i++) {
			printUser(users.get(i));
		}
	}

	/**
	 * 打印一个用户信息
	 * @param user 待打印的UserBean
	 */
	private void printUser(UserBean user) {
		String str = new String();
		str += "id=" + user.getId() + ",name=" + user.getName() + ",birthday="
				+ user.getBirthday() + ",money=" + user.getMoney();
		System.out.println(str);
	}
	
	/**
	 * 更新一个用户信息
	 * @param id 待更新的用户id
	 * @param name
	 * @param birthday
	 * @param money
	 */
	public void update(int id, String name, Date birthday, float money) {
		UserBean user = createUserBean(name, birthday, money);
		user.setId(id);
		UserModel userModel = new UserModel();
		boolean res = userModel.update(user);
		if(res == true) System.out.println("更新操作成功");
		else System.out.println("更新操作失败");
	}
	
	/**
	 * 通过id删除一个用户
	 * @param id 待删除的用户id
	 */
	public void delete(int id) {
		UserModel userModel = new UserModel();
		int res = userModel.delete(id);
		if(res == 0){
			System.out.println("删除操作失败");
		} else {
			System.out.println("删除操作成功");
		}
	}
	
	public void find(int id) {
		UserModel userModel = new UserModel();
		UserBean user = userModel.find(id);
		if(user.getId() == 0){
			System.out.println("没有查到相应的用户");
		} else {
			System.out.println("已查到相应的用户，具体信息如下：");
			printUser(user);
		}
	}
}
