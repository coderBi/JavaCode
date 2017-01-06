package cn.hibernate_homework.controller.test;

import org.hibernate.Session;
import org.junit.Test;

import cn.hibernate_homework.bean.UserAccount;
import cn.hibernate_homework.controller.UserAccountController;
import cn.hibernate_homework.model.UserAccountModel;
import cn.hibernate_homework.utils.SessionFactoryUtils;

public class TestUserAccountController {
	private Session session = SessionFactoryUtils.getSessionFactory()
			.getCurrentSession();
	private UserAccountController uac = new UserAccountController();
	
	{
		session.beginTransaction();
	}
	
	@Test
	public void testAdd(){
		UserAccount ua = new UserAccount();
		ua.setLoginName("ÕÅÈý");
		ua.setPassword("123");
		uac.add(ua);
		session.getTransaction().commit();
	}
	
	@Test
	public void testUpdatePassowrd(){
		UserAccount ua = new UserAccountModel().findById(1L);
		String password = "newPassword";
		uac.updatePassowrd(ua, password);
		session.getTransaction().commit();
	}
}
