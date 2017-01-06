/**
 *
 */
package junit;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import mybatis.dao.mapper.OrdersMapper;
import mybatis.dao.mapper.UserMapper;
import mybatis.pojo.Orders;
import mybatis.pojo.Person;
import mybatis.pojo.PersonCustom;
import mybatis.pojo.PersonQueryVo;
import mybatis.tools.MySessionFactory;

/**
 * <p>
 * Title:UserMapperTest
 * </p>
 * <p>
 * Description:介绍
 * </p>
 * 
 * @author Edwin
 * @since 2016-7-30
 * @version 1.0
 */
public class UserMapperTest {
	private static SqlSessionFactory sqlSessionFactory;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sqlSessionFactory = MySessionFactory.getSqlSessionFactory();
	}

	/**
	 * Test method for
	 * {@link mybatis.dao.mapper.UserMapper#findById(java.lang.Integer)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFindById() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		Person person = userMapper.findById(2);
		assertFalse(null == person);
		assertEquals(Integer.valueOf(2), person.getId());
		assertEquals("张三", person.getUsername());
		assertEquals(Date.valueOf("2016-07-07"), person.getBirthday());
		assertEquals(Integer.valueOf(1), person.getGender());
		assertEquals("傻X村", person.getAddress());
		assertTrue(null == userMapper.findById(100));
	}

	@Test
	public void testFindUserList() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

		// 设置查询条件
		PersonQueryVo personQueryVo = new PersonQueryVo();
		PersonCustom personCustom = new PersonCustom();
		personCustom.setUsername("傻");
		personQueryVo.setPersonCustom(personCustom);

		List<PersonCustom> personCustoms = userMapper
				.findUserList(personQueryVo);
		System.out.println(personCustoms);
	}

	@Test
	public void testMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("10", "1");
		map.put("10", "2");
		System.out.println(map.size());
		for (Iterator<Entry<String, String>> it = map.entrySet().iterator(); it
				.hasNext();) {
			Entry<String, String> item = it.next();
			System.out.println(item.getKey() + "  " + item.getValue());
		}
	}
	

	@Test
	public void testSelectUserOrders() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		
		List<Person> persons = userMapper.selectUserOrders();
		
		System.out.println(persons);
		
		sqlSession.close();
	}
}
