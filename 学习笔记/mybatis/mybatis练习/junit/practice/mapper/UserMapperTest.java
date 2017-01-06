/**
 *
 */
package practice.mapper;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import practice.dao.po.User;

/**
 * <p>
 * Title:UserMapperTest
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author Edwin
 * @since 2016-8-25
 * @version 1.0
 */
public class UserMapperTest {
	public static SqlSessionFactory sqlSessionFactory;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String root = System.getProperties().getProperty("user.dir");
		String resource = root + "\\config\\" + "SqlMapConfig.xml";
		sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(new FileReader(new File(resource)));
	}

	/**
	 * Test method for {@link practice.mapper.UserMapper#selectAll()}.
	 */
	@Test
	public void testSelectAll() {
		SqlSession session = sqlSessionFactory.openSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		List<User> users = userMapper.selectAll();

		assertTrue(users.size() == 6);

		// 每一个user的属性详细通过debug查看，这里略去。

		for (int i = 0; i < users.size(); ++i) {
			assertTrue(users.get(i).getOrders() != null);

			// 具体的每一个user的内部有哪些orders，可以通过debug查看。
		}
	}

	/**
	 * Test method for
	 * {@link practice.mapper.UserMapper#selectById(java.lang.Integer)}.
	 */
	@Test
	public void testSelectById() {
		SqlSession session = sqlSessionFactory.openSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		User user = userMapper.selectById(1);
		System.out.println(user);
	}

	@Test
	public void test2Cache() {
		SqlSession s1 = sqlSessionFactory.openSession();
		SqlSession s2 = sqlSessionFactory.openSession();
		SqlSession s3 = sqlSessionFactory.openSession();

		UserMapper u1 = s1.getMapper(UserMapper.class);
		u1.selectById(1);

		// 这里执行s1的关闭操作，将session中的数据写到二级缓存区域。注意二级缓存是在session关闭的时候写入的
		s1.close(); // 如果这一条语句不在这里执行，会出现下面的两个查询语句都差不到缓存，因为这里的查询结果并没有写入到二级缓存区域

		// 使用s3进行commit操作。
		UserMapper u3 = s3.getMapper(UserMapper.class);
		User user = u3.selectById(1);
		user.setAddress("新地址");
		u3.updateUser(user);
		s3.commit();

		// 使用s2进行二次查询
		UserMapper u2 = s2.getMapper(UserMapper.class);
		u2.selectById(1);
	}
}
