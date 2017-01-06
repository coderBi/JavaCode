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

import mybatis.dao.mapper.OrderdetailMapper;
import mybatis.dao.mapper.OrdersMapper;
import mybatis.dao.mapper.UserMapper;
import mybatis.pojo.Orderdetail;
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
public class OrderdetailMapperTest {
	private static SqlSessionFactory sqlSessionFactory;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sqlSessionFactory = MySessionFactory.getSqlSessionFactory();
	}

	@Test
	public void testSelectOrderdetailOrders() throws Exception {
		OrderdetailMapper orderdetailMapper = sqlSessionFactory.openSession().getMapper(OrderdetailMapper.class);
		List<Orderdetail> orderdetails = orderdetailMapper.selectOrderdetailOrders();
		System.out.println(orderdetails);
	}
	
	@Test
	public void testSelectOrderdetailOrdersItems() throws Exception {
		OrderdetailMapper orderdetailMapper = sqlSessionFactory.openSession().getMapper(OrderdetailMapper.class);
		List<Orderdetail> orderdetails = orderdetailMapper.selectOrderdetailOrdersItems();
		System.out.println(orderdetails);
	}
}
