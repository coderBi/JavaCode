/**
 *
 */
package mybatis.first;

import java.io.InputStream;
import java.sql.Date;
import java.util.List;

import mybatis.pojo.Person;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

/**
 * <p>
 * Title:MybatisFirst
 * </p>
 * <p>
 * Description:入门程序
 * </p>
 * 
 * @author Edwin
 * @since 2016-7-29
 * @version 1.0
 */
public class MybatisFirst {
	// 根据id查询一个用户
	@Test
	public void findUserByIdTest() {
		SqlSession sqlSession = null;
		Person person = null; // 获取结果
		try {
			// 通过mybatis提供的Resources得到配置文件流
			String resource = "SqlMapConfig.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);

			// 创建会话工厂
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
					.build(inputStream);

			// 通过工厂得到session
			sqlSession = sqlSessionFactory.openSession();

			// 通过session执行操作
			person = sqlSession
					.selectOne("test.selectById", Integer.valueOf(1));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != sqlSession)
				// 释放资源
				sqlSession.close();
		}

		// 处理结果
		System.out.println(person);

	}

	// 根据name查询用户
	@Test
	public void findUserByNameTest() {
		SqlSession sqlSession = null;
		List<Person> persons = null; // 获取结果
		try {
			// 通过mybatis提供的Resources得到配置文件流
			String resource = "SqlMapConfig.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);

			// 创建会话工厂
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
					.build(inputStream);

			// 通过工厂得到session
			sqlSession = sqlSessionFactory.openSession();

			// 通过session执行操作
			//Escape.escape("张三"),这里还没法进行escape，因为这里是${}，是进行直接的拼接，而不是参数传入。所以没法简单的解决可能的sql注入。
			persons = sqlSession.selectList("test.selectByName", "张三"); // 要查询多条记录不能用selectOne，否则会报错
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != sqlSession)
				// 释放资源
				sqlSession.close();
		}

		// 处理结果
		System.out.println(persons);

	}

	// 插入新用户
	@Test
	public void insertUserTest() {
		SqlSession sqlSession = null;
		Person person = new Person("傻X", new Date(System.currentTimeMillis()),
				2, "傻X村");
		try {
			// 通过mybatis提供的Resources得到配置文件流
			String resource = "SqlMapConfig.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);

			// 创建会话工厂
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
					.build(inputStream);

			// 通过工厂得到session
			sqlSession = sqlSessionFactory.openSession();

			// 通过session执行操作
			sqlSession.insert("test.insertUser", person);

			// 由于auto commit默认是false，这里需要插入输入，所以要手动提交
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != sqlSession)
				// 释放资源
				sqlSession.close();
		}
		// 打印得到的主键值
		System.out.println(person.getId());
	}

	// 删除
	@Test
	public void deleteByIdTest() {
		SqlSession sqlSession = null;
		Integer id = Integer.valueOf(29);
		try {
			// 通过mybatis提供的Resources得到配置文件流
			String resource = "SqlMapConfig.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);

			// 创建会话工厂
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
					.build(inputStream);

			// 通过工厂得到session
			sqlSession = sqlSessionFactory.openSession();

			// 通过session执行操作
			sqlSession.delete("test.deleteById", id);

			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != sqlSession)
				// 释放资源
				sqlSession.close();
		}
	}

	// 插入新用户
	@Test
	public void updateUserTest() {
		SqlSession sqlSession = null;
		Person person = new Person("傻X11",
				new Date(System.currentTimeMillis()), 2, "傻X村");
		person.setId(28);
		try {
			// 通过mybatis提供的Resources得到配置文件流
			String resource = "SqlMapConfig.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);

			// 创建会话工厂
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
					.build(inputStream);

			// 通过工厂得到session
			sqlSession = sqlSessionFactory.openSession();

			// 通过session执行操作
			sqlSession.update("test.updateUser", person);

			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != sqlSession)
				// 释放资源
				sqlSession.close();
		}
	}
}
