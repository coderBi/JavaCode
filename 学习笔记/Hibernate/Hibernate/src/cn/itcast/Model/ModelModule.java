package cn.itcast.Model;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.hibernate.Session;

import cn.itcast.domain.PageResultSet;
import cn.itcast.tools.HibernateConfigurationFactory;

public abstract class ModelModule {
	/**
	 * 提取所有在本类中各个方法的公共部分构成 “基本操作”
	 * @param mhso MyHibernateSqlOperation对象，自定义的数据库语句操作接口
	 * @param args  这里的args实际上是传给 mhso内部的SqlOperation使用。
	 * @return mhso调用SqlOperation的返回。
	 */
	protected Object baseFunction(MyHibernateSqlOperation mhso, Object... args) {
		Session session = null;
		try {
			session = HibernateConfigurationFactory.getSession();
			session.beginTransaction();
			Object obj = mhso.SqlOperation(session, args);
			
			return obj;
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (null != session) {
				session.getTransaction().rollback();
			}
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public <M> M add(M obj) {
		return (M) baseFunction(new MyHibernateSqlOperation() {

			@Override
			public Object SqlOperation(Session session, Object... args) {
				Object obj = args[0];
				// obj.getClass().c
				session.save(obj);
				session.getTransaction().commit();
				return obj;
			}
		}, obj);
	}

	@SuppressWarnings("unchecked")
	public <T> T update(T obj) {
		return (T) baseFunction(new MyHibernateSqlOperation() {

			@Override
			public Object SqlOperation(Session session, Object... args) {
				Object obj = args[0];
				session.update(obj);
				session.getTransaction().commit();
				return obj;
			}
		}, obj);
	}

	@SuppressWarnings("unchecked")
	public <T> T delete(T obj) {
		return (T) baseFunction(new MyHibernateSqlOperation() {

			@Override
			public Object SqlOperation(Session session, Object... args) {
				Object obj = args[0];
				session.delete(obj);
				session.getTransaction().commit();
				return obj;
			}
		}, obj);
	}

	public <T> T deleteById(Class<T> clazz, int id) {
		T t = find(clazz, id);

		return delete(t);
	}

	@SuppressWarnings("unchecked")
	public <T> T find(Class<T> clazz, int id) {
		return (T) baseFunction(new MyHibernateSqlOperation() {

			@Override
			public Object SqlOperation(Session session, Object... args) {
				Object arg0 = args[0], arg1 = args[1];
				@SuppressWarnings("rawtypes")
				T t = (T) session.get((Class) arg0, (Integer) arg1);
				session.getTransaction().commit();
				return t;
			}
		}, clazz, id);
	}

	@SuppressWarnings("unchecked")
	public <M> PageResultSet findAll(Class<M> clazz, int pageNum) {
		Properties prop = new Properties();
		PageResultSet prs = new PageResultSet();
		int firstResult = 0;
		int maxResult = 0;
		boolean showFirstPage = true;
		boolean showLastPage = true;
		boolean showGoto = true;

		try {
			InputStream is = this
					.getClass()
					.getClassLoader()
					.getResourceAsStream("cn/itcast/domain/page.cfg.properties");
			prop.load(is);
			maxResult = Integer.parseInt(prop.getProperty("resultCountInPage"));
			showFirstPage = Boolean.parseBoolean(prop
					.getProperty("showFirstPage"));
			showLastPage = Boolean.parseBoolean(prop
					.getProperty("showLastPage"));
			showGoto = Boolean.parseBoolean(prop.getProperty("showGoto"));
		} catch (IOException e) {
			e.printStackTrace();
			maxResult = 10; // 没有读取到配置，就设置一页放10个记录
		}
		firstResult = (pageNum - 1) * maxResult;
		prs.setPageNum(pageNum);
		prs.setShowFirstPage(showFirstPage);
		prs.setShowGoto(showGoto);
		prs.setShowLastPage(showLastPage);

		return (PageResultSet) baseFunction(new MyHibernateSqlOperation() {

			@Override
			public Object SqlOperation(Session session, Object... args) {
				@SuppressWarnings("rawtypes")
				Class clazz = (Class)args[0];
				int firstResult = (Integer) args[1];
				int maxResult = (Integer) args[2];
				PageResultSet prs = (PageResultSet) args[3];
				String sql = "from " + clazz.getName();
				List<M> resultSet = session.createQuery(sql)//
						.setFirstResult(firstResult)//
						.setMaxResults(maxResult)//
						.list();
				sql = "select count(*) from " + clazz.getName();
				Long count = (Long) session.createQuery(sql)//
						.uniqueResult();
				session.getTransaction().commit();
				prs.setResultSet(resultSet);
				prs.setTotalPage((int) Math.ceil(count * 1.0 / maxResult));
				return prs;
			}
		}, clazz, firstResult, maxResult, prs);
	}
}
