/**
 *
 */
package sm.mapper;

import java.util.List;

import sm.po.Orders;

/**
 * <p>Title:OrdersMapper</p>
 * <p>Description:</p>
 * @author Edwin
 * @since 2016-8-23
 * @version 1.0
 */
public interface OrdersMapper {
	List<Orders> selectAll();
}
