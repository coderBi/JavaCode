/**
 *
 */
package mybatis.dao.mapper;

import java.util.List;

import mybatis.pojo.Orderdetail;
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
public interface OrderdetailMapper {
	public List<Orderdetail> selectOrderdetailOrders() throws Exception;
	
	public List<Orderdetail> selectOrderdetailOrdersItems() throws Exception;
}
