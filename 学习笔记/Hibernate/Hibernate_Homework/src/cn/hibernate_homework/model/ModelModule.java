package cn.hibernate_homework.model;

import java.io.Serializable;

import org.hibernate.Session;

import cn.hibernate_homework.utils.SessionFactoryUtils;

public class ModelModule {
	protected Session session = SessionFactoryUtils.getSessionFactory()
			.getCurrentSession();

	protected <T> T add(T obj) {
		session.save(obj);
		return obj;
	}

	@SuppressWarnings("unchecked")
	protected <T, M> T findById(Class<T> clazz, M id) {
		return (T) session.get(clazz, (Serializable) id);
	}

	protected <T> Long getCount(Class<T> clazz) {
		String hql = "select count(*) from " + clazz.getName();
		Long count = (Long) session.createQuery(hql)
				.uniqueResult();
		return count;
	}
	
	@SuppressWarnings("unchecked")
	public <T,M>boolean isPersistent(Class<T> clazz,T obj, M id) {
		if (null == obj
				|| null == (obj = (T) session.get(clazz,
						(Serializable) id))) {
			return false;
		}
		return true;
	}
}
