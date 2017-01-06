/**
 *
 */
package practice.mapper;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import practice.dao.po.Orders;

/**
 * <p>
 * Title:OrdersMapperTest
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author Edwin
 * @since 2016-8-23
 * @version 1.0
 */
public class OrdersMapperTest {
	public static SqlSessionFactory sqlSessionFactory;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String resource = "SqlMapConfig.xml";
		try {
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources
					.getResourceAsStream(resource));
		} catch (Exception e) {
			throw new RuntimeException("初始化sql工厂出错", e);
		}
	}

	/**
	 * Test method for {@link practice.mapper.OrdersMapper#selectAll()}.
	 */
	@Test
	public void testSelectAll() {
		OrdersMapper ordersMapper = sqlSessionFactory.openSession().getMapper(
				OrdersMapper.class);
		List<Orders> orders = ordersMapper.selectAll();
		assertTrue(orders.size() == 1);
		for(int i = 0; i < orders.size(); ++i){
			assertTrue(orders.get(i).getUser() != null);
		}
		//其他细节测试略去，可以在debug模式下，查看orders里面的属性。
	}

	/**
	 * Test method for
	 * {@link practice.mapper.OrdersMapper#selectByUserId(java.lang.Integer)}.
	 * 
	 * @throws ParseException
	 */
	@Test
	public void testSelectByUserId() throws ParseException {
		Integer id = 1;
		OrdersMapper ordersMapper = sqlSessionFactory.openSession().getMapper(
				OrdersMapper.class);
		List<Orders> orders = ordersMapper.selectByUserId(id);
		assertTrue(orders.isEmpty());

		id = 32;
		orders = ordersMapper.selectByUserId(id);
		assertFalse(orders.isEmpty());
		assertTrue(orders.size() == 1);

		Orders order1 = orders.get(0);
		assertTrue(order1.getId() == 1);
		assertTrue(order1.getCreatetime().equals(
				Timestamp.valueOf("2016-08-05 23:03:20")));
		assertTrue("测试用".equals(order1.getNote()));
		assertTrue("12143254325".equals(order1.getNumber()));
		assertTrue(order1.getUser_id() == 32);
		assertTrue(order1.getOrderdetails() == null);
		
		//实现了懒加载，当然更详细的观察结果应该在控制台日志的sql上，可以发现，执行到这里的时候，又会执行一条sql语句。
		assertFalse(order1.getUser() == null);
	}
}
