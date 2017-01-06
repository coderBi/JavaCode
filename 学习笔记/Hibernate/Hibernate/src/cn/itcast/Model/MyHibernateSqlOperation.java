package cn.itcast.Model;

import org.hibernate.Session;

public interface MyHibernateSqlOperation {
	public Object SqlOperation(Session session,Object...args);
}
