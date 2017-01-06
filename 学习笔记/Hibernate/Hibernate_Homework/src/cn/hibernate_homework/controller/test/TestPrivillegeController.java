package cn.hibernate_homework.controller.test;

import org.hibernate.Session;
import org.junit.Test;

import cn.hibernate_homework.bean.Privillege;
import cn.hibernate_homework.bean.UserAccount;
import cn.hibernate_homework.controller.PrivillegeController;
import cn.hibernate_homework.model.PrivillegeModel;
import cn.hibernate_homework.model.UserAccountModel;
import cn.hibernate_homework.utils.SessionFactoryUtils;

public class TestPrivillegeController {
	private Session session = SessionFactoryUtils.getSessionFactory()
			.getCurrentSession();
	private PrivillegeController pc = new PrivillegeController();

	{
		session.beginTransaction();
	}

	@Test
	public void testAdd() {
		Privillege pl = new Privillege();
		pl.setAction("ÃÌº”…Ã∆∑");
		pc.add(pl);
		session.getTransaction().commit();
	}
	
	@Test
	public void testAddToUserAccount(){
		UserAccount userAccount = new UserAccountModel().findById(1L);
		Privillege privillege = new PrivillegeModel().findById(8L);
		pc.addToUserAccount(privillege, userAccount);
		session.getTransaction().commit();
	}
	
	@Test
	public void testQueryEmployeesOfOnePrivillege(){
		Privillege privillege = new PrivillegeModel().findById(8L);
		pc.queryEmployeesOfOnePrivillege(privillege);
	}
}
