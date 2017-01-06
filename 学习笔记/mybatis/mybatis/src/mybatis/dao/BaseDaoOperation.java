/**
 *
 */
package mybatis.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * <p>
 * Title:BaseDaoOperation
 * </p>
 * <p>
 * Description:介绍
 * </p>
 * 
 * @author Edwin
 * @since 2016-7-30
 * @version 1.0
 */
public class BaseDaoOperation {
	protected SqlSessionFactory sqlSessionFactory;

	protected BaseDaoOperation(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	protected SqlSession getSessin() {
		return this.sqlSessionFactory.openSession();
	}

	public interface HasReturnMapper {
		public Object sqlOperation(SqlSession sqlSession, String statement,
				Object arg) throws Exception;
	}

	public interface NoReturnMapper {
		public void sqlOperation(SqlSession sqlSession, String statement,
				Object arg) throws Exception;
	}

	public Object defaultHasReturnOperation(HasReturnMapper mapper,
			String statement, Object arg) {
		Object objToReturn = null;
		SqlSession sqlSession = null;
		try {
			sqlSession = getSessin();
			objToReturn = mapper.sqlOperation(sqlSession, statement, arg);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != sqlSession) {
				sqlSession.close();
			}
		}
		return objToReturn;
	}

	public void defaultNoReturnOperation(NoReturnMapper mapper,
			String statement, Object arg) throws Exception {
		SqlSession sqlSession = null;
		try {
			sqlSession = getSessin();
			mapper.sqlOperation(sqlSession, statement, arg);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != sqlSession) {
				sqlSession.close();
			}
		}
	}
}
