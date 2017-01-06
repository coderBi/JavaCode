package cn.itcast.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import cn.itcast.domain.User;

public class HibernateTest {
	private static Configuration cfg = new Configuration();
	private static SessionFactory sessionFactory = null;
	static {
		cfg.configure("hibernate.cfg.xml");

		sessionFactory = cfg.buildSessionFactory();
	}

	@Test
	public void testSave() {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			User user = new User();
			user.setName("张三");
			session.save(user);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
			if(null != session)
			session.getTransaction().rollback();
			throw e;
		} finally {
			if(null != session){
				session.close();
			}
		}
	}

	@Test
	public void testGet() {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			User user = (User) session.get(User.class, 1);
			session.getTransaction().commit();
			System.out.println(user);
		} catch (RuntimeException e) {
			e.printStackTrace();
			if(null != session)
			session.getTransaction().rollback();
			throw e;
		}
	}
	
	@Test
	public void testModelModule(){
		
	}
	
	@Test
	public void testAutoFlush(){
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			User user = (User) session.get(User.class, 1);
			
			System.out.println(user);
			
			user.setName("傻X");
			System.out.println(user);
			//实际上在commit操作的时候，会进行自动的flush操作，将上面的修改同步到数据库中。
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
			if(null != session)
			session.getTransaction().rollback();
			throw e;
		}
	}
	
	public void testDelete(){
		
	}
}
