package cn.itcast.Model;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.hibernate.Session;

import cn.itcast.domain.PageResultSet;
import cn.itcast.tools.HibernateConfigurationFactory;

public abstract class ModelModule_Old {
	public <M> M add(M obj) {
		Session session = null;
		try {
			session = HibernateConfigurationFactory.getSession();
			session.beginTransaction();
			session.save(obj);
			session.getTransaction().commit();
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

	public <T> T update(T obj) {
		Session session = null;
		try {
			session = HibernateConfigurationFactory.getSession();
			session.beginTransaction();
			session.update(obj);
			session.getTransaction().commit();
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

	public <T> T delete(T obj) {
		Session session = null;
		try {
			session = HibernateConfigurationFactory.getSession();
			session.beginTransaction();
			session.delete(obj);
			session.getTransaction().commit();
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

	public <T> T deleteById(Class<T> clazz, int id) {
		T t = find(clazz, id);

		Session session = null;
		try {
			session = HibernateConfigurationFactory.getSession();
			session.beginTransaction();

			session.delete(t);
			session.getTransaction().commit();
			return t;
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

	public <T> T find(Class<T> clazz, int id) {
		Session session = null;
		try {
			session = HibernateConfigurationFactory.getSession();
			session.beginTransaction();

			@SuppressWarnings("unchecked")
			T t = (T) session.get(clazz, id);
			session.getTransaction().commit();
			return t;
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
	public <T> PageResultSet findAll(Class<T> clazz, int pageNum) {
		Session session = null;
		Properties prop = new Properties();
		PageResultSet prs = new PageResultSet();
		int firstResult = 0;
		int maxResult = 0;
		boolean showFirstPage = true;
		boolean showLastPage = true;
		boolean showGoto = true;
		List<T> resultSet = null;
		
		try {
			// /File file = new File("src/page.cfg.properties"); //这个路径不自由
			InputStream is = this.getClass().getClassLoader()
					.getResourceAsStream("cn/itcast/domain/page.cfg.properties");
			prop.load(is);
			maxResult = Integer.parseInt(prop.getProperty("resultCountInPage"));
			showFirstPage = Boolean.parseBoolean(prop.getProperty("showFirstPage"));
			showLastPage = Boolean.parseBoolean(prop.getProperty("showLastPage"));
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
		
		String sql = "from " + clazz.getName();
		try {
			session = HibernateConfigurationFactory.getSession();
			session.beginTransaction();
			resultSet = session.createQuery(sql)//
					.setFirstResult(firstResult)//
					.setMaxResults(maxResult)//
					.list();
			sql = "select count(*) from " + clazz.getName();
			System.out.println("sql: "+sql);
			Long count = (Long) session.createQuery(sql)//
					.uniqueResult();
			session.getTransaction().commit();
			prs.setResultSet(resultSet);
			prs.setTotalPage( (int)Math.ceil(count * 1.0 / maxResult) );
			return prs;
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
}
