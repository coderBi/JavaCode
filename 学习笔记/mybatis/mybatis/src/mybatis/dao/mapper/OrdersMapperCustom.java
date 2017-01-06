/**
 *
 */
package mybatis.dao.mapper;

import java.util.List;

import mybatis.pojo.OrdersCustom;

/**
 * <p>Title:OrdersMapperCustom</p>
 * <p>Description:</p>
 * @author Edwin
 * @since 2016-8-5
 * @version 1.0
 */
public interface OrdersMapperCustom {
	List<OrdersCustom> findOrdersUser() throws Exception;
}
