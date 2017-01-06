/**
 *
 */
package practice.mapper;

import java.util.List;

import practice.dao.po.Orders;

/**
 * <p>Title:OrdersMapper</p>
 * <p>Description:</p>
 * @author Edwin
 * @since 2016-8-23
 * @version 1.0
 */
public interface OrdersMapper {
	List<Orders> selectAll();
	List<Orders> selectByUserId(Integer user_id);
}
