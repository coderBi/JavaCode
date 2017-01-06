package cn.itcast.tools;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConfigurationFactory {
	private static HibernateConfigurationFactory  hcf = null;
	private SessionFactory sf = null;
	private HibernateConfigurationFactory(){
		Configuration cfg = new Configuration().configure();
		sf = cfg.buildSessionFactory();
	}
	public static HibernateConfigurationFactory getInstance(){
		if(!(hcf instanceof HibernateConfigurationFactory)){
			hcf = new HibernateConfigurationFactory();
		}
		return hcf;
	}
	public static Session getSession(){
		return getInstance().sf.openSession();
	}
}
