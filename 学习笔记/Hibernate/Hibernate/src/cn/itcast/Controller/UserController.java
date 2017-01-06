package cn.itcast.Controller;

import cn.itcast.Model.UserModel;
import cn.itcast.domain.PageResultSet;
import cn.itcast.domain.User;

public class UserController {
	public static void main(String[] args){
		UserController uc = new UserController();
		User user = new User("²¼ÍÞÍÞ");
		
		uc.add(user);
	}
	
	public void add(User user) {
		User res = new UserModel().add(user);
		System.out.println(res);
	}
	
	public void update(User user){
		User res = new UserModel().update(user);
		System.out.println(res);
	}
	
	public void delete(User user){
		User res = new UserModel().delete(user);
		System.out.println(res);
	}
	
	public void deleteById(int id){
		User res = new UserModel().deleteById(id);
		System.out.println(res);
	}
	
	public void find(int id){
		User res = new UserModel().find(id);
		System.out.println(res);
	}
	
	public void findAll(int pageNum) {
		PageResultSet prs = new UserModel().findAll(pageNum);
		System.out.println(prs);
	}
}
