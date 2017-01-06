/**
 *
 */
package mybatis.dao.mapper;

import java.util.List;

import mybatis.pojo.Orders;
import mybatis.pojo.Person;
import mybatis.pojo.PersonCustom;
import mybatis.pojo.PersonQueryVo;

/**
 * <p>Title:UserMapper</p>
 * <p>Description:user接口</p>
 * @author Edwin
 * @since 2016-7-30
 * @version 1.0
 */
public interface OrdersMapper {
	
	public List<Orders> findOrdersUser() throws Exception;
	public List<Orders> findOrdersAndOrderDetail() throws Exception;
}
