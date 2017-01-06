/**
 *
 */
package junit;

import static org.junit.Assert.*;

import java.util.List;

import mybatis.dao.mapper.OrdersMapper;
import mybatis.dao.mapper.OrdersMapperCustom;
import mybatis.pojo.Orders;
import mybatis.pojo.OrdersCustom;
import mybatis.tools.MySessionFactory;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * <p>Title:OrdersMapperCustomTest</p>
 * <p>Description:</p>
 * @author Edwin
 * @since 2016-8-5
 * @version 1.0
 */
public class OrdersMapperTest {
	private static SqlSessionFactory sqlSessionFactory;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sqlSessionFactory = MySessionFactory.getSqlSessionFactory();
	}

	/**
	 * Test method for {@link mybatis.dao.mapper.OrdersMapperCustom#findOrdersUser()}.
	 * @throws Exception 
	 */
	@Test
	public void testFindOrdersUser() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		OrdersMapper ordersMapper = sqlSession.getMapper(OrdersMapper.class);
		
		List<Orders> orders = ordersMapper.findOrdersUser();
		
		System.out.println(orders);
		
		sqlSession.close();
	}
	
	@Test
	public void testFindOrdersAndOrderDetail() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		OrdersMapper ordersMapper = sqlSession.getMapper(OrdersMapper.class);
		
		List<Orders> orders = ordersMapper.findOrdersAndOrderDetail();
		
		System.out.println(orders);
		
		sqlSession.close();
	}
	

}
