/**
 *
 */
package sm.mapper;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sm.po.Orders;

/**
 * <p>
 * Title:OrdersMapperTest
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author Edwin
 * @since 2016-8-28
 * @version 1.0
 */
public class OrdersMapperTest {
	private static ApplicationContext applicationContext;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring/applicationContext.xml");

	}

	/**
	 * Test method for {@link sm.mapper.OrdersMapper#selectAll()}.
	 */
	@Test
	public void testSelectAll() {
		OrdersMapper ordersMapper = (OrdersMapper) applicationContext.getBean("ordersMapper");
		List<Orders> orders = ordersMapper.selectAll();
		System.out.println(orders.size());
	}

}
