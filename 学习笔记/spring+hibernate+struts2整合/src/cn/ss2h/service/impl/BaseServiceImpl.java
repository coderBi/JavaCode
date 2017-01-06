package cn.ss2h.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.ss2h.service.BaseService;

/**
 * 对BaseService接口的基本实现
 * @version 1.0
 * @author Edwin
 * @since 2016-7-22
 */
@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true) @Service("baseService")
public class BaseServiceImpl implements BaseService {
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * 将hql字符串中的占位符进行填充
	 * @param hql  待填充的hql语句
	 * @param args 用来填充的Object数组
	 * @return 填充之后的Query对象
	 */
	private Query fillHql(String hql, Object... args){
		Session session = sessionFactory.getCurrentSession();
		Query query =  session.createQuery(hql);
		for(int i = 0; i < args.length; ++i){
			query = query.setParameter(i, args[i]);
		}
		return query;
	}
	
	@Override
	public List<?> fetchAll(String hql, Object... args) {
		return fillHql(hql, args).list();
	}

	@Override
	public Object fetchRow(String hql, Object... args) {
		List<?> rows = fetchAll(hql, args);
		return rows.size() > 0 ? rows.get(0) : null;
	}

	@Override
	public Object fetchOne(String hql, Object... args) {
		Object row = fetchRow(hql, args);
		if(null == row){
			return null;
		}
		return fillHql(hql, args).uniqueResult();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public Integer execute(String hql, Object... args) {
		return fillHql(hql, args).executeUpdate();
	}

}
