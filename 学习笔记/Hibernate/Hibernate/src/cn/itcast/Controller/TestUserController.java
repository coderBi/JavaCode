package cn.itcast.Controller;

import org.junit.Test;

import cn.itcast.Model.UserModel;
import cn.itcast.domain.User;

public class TestUserController {
	@Test
	public void testAdd() {
		User user = new User("张三");
		new UserController().add(user);
	}
	
	@Test
	public void testUpdate(){
		User user = new User("张三");
		user.setId(2);
		new UserController().update(user);
	}
	
	@Test
	public void testDelete(){
		User user = new User("张三");
		user.setId(100);
		new UserController().delete(user);
	}
	
	@Test
	public void testDeleteById(){
		int id = 4;
		new UserController().deleteById(id);
	}
	
	@Test
	public void testFind(){
		int id = 3;
		new UserController().find(id);
	}
	
	@Test
	public void testFindAll(){
		int pageNum = 2;
		new UserController().findAll(pageNum);
	}
	
	@Test
	public void testAfterDelete(){
		User user = new UserModel().find(9);
		new UserController().delete(user);
		System.out.println(user); //目前已经被删除了，不过java对象还在内存里面可用
		
		//现在测试get 和重新将useru更新回去
		User user1 = new UserModel().find(10); //得到的是null，说明相应的session里面的对象也被删除了，上面的那个java对象并不在session里面
		System.out.println(user1); //null
		
		new UserController().update(user); //重新更新到数据库，通过debug看到由于更新语句会先执行一个select，这个select得到的是null，所以不会执行update
		
	}
}
