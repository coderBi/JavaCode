package cn.hibernate_homework.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import cn.hibernate_homework.bean.Department;
import cn.hibernate_homework.bean.Employee;
import cn.hibernate_homework.bean.Privillege;
import cn.hibernate_homework.bean.UserAccount;

final public class SessionFactoryUtils {
	private static SessionFactory sf = null;
	private static Configuration cfg = null;
	static {
		cfg = new Configuration().configure("hibernate.cfg.xml")//
				.addClass(Department.class)//
				.addClass(Employee.class)//
				.addClass(UserAccount.class)//
				.addClass(Privillege.class);
		sf = cfg.buildSessionFactory();
	}

	public static  SessionFactory getSessionFactory(){
		return sf;
	}
}
